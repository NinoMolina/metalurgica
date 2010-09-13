/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.DetallepresupuestoDB;
import metalsoft.datos.dbobject.PedidoDB;
import metalsoft.datos.dbobject.PresupuestoDB;
import metalsoft.negocio.access.AccessDetallePresupuesto;
import metalsoft.negocio.access.AccessPedido;
import metalsoft.negocio.access.AccessPresupuesto;
import metalsoft.negocio.access.AccessViews;
import metalsoft.negocio.ventas.Pedido;
import metalsoft.negocio.ventas.Presupuesto;
import metalsoft.util.Decimales;
import metalsoft.util.Fecha;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Nino
 */
public class GestorPresupuesto {
    private PedidoDB pedidoSeleccionadoDB;
    private PresupuestoDB presupuestoPedSelecDB;
    private DetallepresupuestoDB[] vDetallePresupuestoDB;
    private Date fechaPresupuesto;
    private Date fechaVencimientoPresupuesto;
    private Date fechaEstimadaFinProduccion;
    private double montoTotal;

    public GestorPresupuesto() {
    }


    public Date getFechaEstimadaFinProduccion() {
        return fechaEstimadaFinProduccion;
    }

    public Date getFechaPresupuesto() {
        return fechaPresupuesto;
    }

    public Date getFechaVencimientoPresupuesto() {
        return fechaVencimientoPresupuesto;
    }

    public double getMontoTotal() {
        return montoTotal;
    }


    public LinkedList<ViewPedidoEnListadoProcedimientos> buscarPedidosConDetalleProcesoCalidad() {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewPedidoEnListadoProcedimientos> list=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.pedidosSegunEstado(IdsEstadoPedido.PEDIDOCONDETALLEDEPROCESOSDECALIDAD,cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public PedidoDB buscarPedidoSeleccionado(long idPed) {
        PostgreSQLManager pg=new PostgreSQLManager();
        PedidoDB db=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            db=AccessPedido.findByIdPedido(idPed, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return db;
    }

    public PresupuestoDB buscarPresupuestoDePedido(long idPresupuesto) {
        PostgreSQLManager pg=new PostgreSQLManager();
        PresupuestoDB db=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            db=AccessPresupuesto.findByIdPresupuesto(idPresupuesto, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return db;
    }

    public long buscarNroPresupuesto(long idPed) {
        pedidoSeleccionadoDB=buscarPedidoSeleccionado(idPed);
        presupuestoPedSelecDB=buscarPresupuestoDePedido(pedidoSeleccionadoDB.getPresupuesto());
        return presupuestoPedSelecDB.getNropresupuesto();
    }

    public void calcularTotales() {
        PostgreSQLManager pg=new PostgreSQLManager();
        PresupuestoDB db=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            vDetallePresupuestoDB=AccessDetallePresupuesto.findByIdPresupuesto(presupuestoPedSelecDB.getIdpresupuesto(), cn);
            //vDetalleProductoPresupuestoDB=buscarDetallesProdPresupuesto(vDetallePresupuestoDB)
        } catch (Exception ex) {
            Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ;

    }

    public LinkedList<ViewEtapasXPiezaPresupuesto> buscarEtapasXPiezaPresupuesto() {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewEtapasXPiezaPresupuesto> list=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.listEtapasXPiezaPresupuesto(presupuestoPedSelecDB.getIdpresupuesto(), cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public LinkedList<ViewMateriaPrimaXPiezaPresupuesto> buscarMatPrimaXPiezaPresupuesto() {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewMateriaPrimaXPiezaPresupuesto> list=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.listMateriaPrimaXPiezaPresupuesto(presupuestoPedSelecDB.getIdpresupuesto(), cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public LinkedList<ViewProcesoCalidadXPiezaPresupuesto> buscarProCalidadXPiezaPresupuesto() {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewProcesoCalidadXPiezaPresupuesto> list=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.listProCalidadXPiezaPresupuesto(presupuestoPedSelecDB.getIdpresupuesto(), cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public void setFechaPresupuesto(Calendar c) {
        fechaPresupuesto=c.getTime();
    }

    public void setFechaVencimientoPresupuesto(Calendar c) {
        fechaVencimientoPresupuesto=c.getTime();
    }

    public void setFechaEstimadaFinProduccion(Calendar c) {
        fechaEstimadaFinProduccion=c.getTime();
    }

    public void setMontoTotal(String text) {
        montoTotal=Decimales.parseToDouble(text);
    }

    public int guardarPresupuesto() {
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        int result=-1;
        presupuestoPedSelecDB.setFechapresupuesto(Fecha.parseToDateSQL(fechaPresupuesto));
        presupuestoPedSelecDB.setFechavencimiento(Fecha.parseToDateSQL(fechaVencimientoPresupuesto));
        presupuestoPedSelecDB.setMontototal(montoTotal);
        pedidoSeleccionadoDB.setFechaentregaestipulada(Fecha.parseToDateSQL(fechaEstimadaFinProduccion));
        pedidoSeleccionadoDB.setEstado(IdsEstadoPedido.PRESUPUESTADO);
        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result=AccessPresupuesto.update(presupuestoPedSelecDB, cn);
            result+=AccessPedido.update(pedidoSeleccionadoDB, cn);
            cn.commit();
        } catch (Exception ex) {
            Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //tiene que devolver 1 cuando se guarda correctamente
        //como son 2 actualizaciones entonces la suma de las filas acutalizadas
        //dividido 2 tiene que ser 1
        return result/2;
        
    }

    public void imprimirPresupuesto() {
        
        String sourceFile="G:\\RptPresupuesto.jasper";
        PostgreSQLManager pg=new PostgreSQLManager();
        System.out.println(sourceFile);
        JasperPrint jasperPrint=null;
        Connection cn=null;
        Map param=new HashMap();
        JasperReport masterReport = null;
//        PreparedStatement ps=null;
//        ResultSet rs=null;
//        String query="SELECT ped.idpedido,dpre.cantidad, pro.nombre," +
//                "dpre.precio,cli.razonsocial,pre.fechapresupuesto," +
//                "pre.fechavencimiento,pre.nropresupuesto" +
//                " FROM pedido ped,presupuesto pre,detallepresupuesto dpre," +
//                "producto pro, cliente cli"+
//                " WHERE   ped.presupuesto = pre.idpresupuesto" +
//                " AND ped.idpedido = "+pedidoSeleccionadoDB.getIdpedido()+
//                " AND dpre.idpresupuesto = pre.idpresupuesto"+
//                " AND pro.idproducto = dpre.idproducto"+
//                " AND cli.idcliente = ped.cliente";
        try {
            cn = pg.concectGetCn();
//            ps=cn.prepareStatement(query);
//            rs=ps.executeQuery();
            masterReport = (JasperReport) JRLoader.loadObject(sourceFile);
            param.put("ID_PEDIDO", String.valueOf(pedidoSeleccionadoDB.getIdpedido()));
//            JRResultSetDataSource rsDataSource = new JRResultSetDataSource(rs);
            jasperPrint = JasperFillManager.fillReport(masterReport, param,cn);
            JasperViewer jviewer = new JasperViewer(jasperPrint,false);
            jviewer.setTitle("Presupuesto");
            jviewer.setVisible(true);
            //Se exporta a PDF
            //JasperExportManager.exportReportToPdfFile(jasperPrint,"D:\\RptPresupuesto.pdf");
            // Visualizar el reporte en el Jasperviwer
            //JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }



}
