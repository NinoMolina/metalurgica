/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.PedidoDB;
import metalsoft.negocio.access.AccessPedido;
import metalsoft.negocio.access.AccessViews;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Vicky
 */
public class GestorRegistrarEntregaPedido {
    public LinkedList<ViewPedidosClienteSegunEstado> buscarPedidosClienteEnArmado(long idCliente){
       PostgreSQLManager pg = new PostgreSQLManager();
        LinkedList<ViewPedidosClienteSegunEstado> list = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            list = AccessViews.pedidosClienteSegunEstado(idCliente,IdsEstadoPedido.ENARMADO, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public LinkedList<ViewDetallePedidoCotizacion> buscarDetallePedidoSeleccionado(long idPedido){
       PostgreSQLManager pg = new PostgreSQLManager();
        LinkedList<ViewDetallePedidoCotizacion> list = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            list = AccessViews.listDetallePedidoCotizacion(idPedido, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    public void imprimirFactura(long id) {
        URL sourceFile = null;
        try {
            sourceFile = new URL("https://metalurgica.googlecode.com/svn/trunk/10_metalsoft/Reportes/RptPresupuesto.jasper");
        } catch (MalformedURLException ex) {
            Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        //String sourceFile = "G:\\ReportesRptPresupuesto.jasper";
        PostgreSQLManager pg = new PostgreSQLManager();
        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();

            masterReport = (JasperReport) JRLoader.loadObject(sourceFile);

            param.put("ID_PEDIDO", new Long(id));
//            JRResultSetDataSource rsDatparam.put("ID_PEDIDO", new Long(pedidoSeleccionadoDB.getIdpedido()));aSource = new JRResultSetDataSource(rs);
            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);


            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("Factura");
            jviewer.setVisible(true);

            String nroPre = NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PRESUPUESTO, presupuestoPedSelecDB.getNropresupuesto());
            //Se exporta a PDF
            //JasperExportManager.exportReportToPdfFile(jasperPrint,"G:\\"+nroPre+"-"+Fecha.fechaActual(Fecha.YYYY_MM_DD_GUION)+".pdf");
            // Visualizar el reporte en el Jasperviwer
            //JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void imprimirRemito(long id) {
        URL sourceFile = null;
        try {
            sourceFile = new URL("https://metalurgica.googlecode.com/svn/trunk/10_metalsoft/Reportes/RptPresupuesto.jasper");
        } catch (MalformedURLException ex) {
            Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        //String sourceFile = "G:\\ReportesRptPresupuesto.jasper";
        PostgreSQLManager pg = new PostgreSQLManager();
        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();
//            ps=cn.prepareStatement(query);
//            rs=ps.executeQuery();
            masterReport = (JasperReport) JRLoader.loadObject(sourceFile);
            param.put("ID_PEDIDO", new Long(id));
//            JRResultSetDataSource rsDatparam.put("ID_PEDIDO", new Long(pedidoSeleccionadoDB.getIdpedido()));aSource = new JRResultSetDataSource(rs);
            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);


            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("Remito");
            jviewer.setVisible(true);

            String nroPre = NumerosAMostrar.getNumeroString(NumerosAMostrar.NRO_PRESUPUESTO, presupuestoPedSelecDB.getNropresupuesto());

        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public PedidoDB buscarPedidoPorID(long id)
    {
        PostgreSQLManager pg = new PostgreSQLManager();
        PedidoDB pedido=null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();

            pedido=AccessPedido.findByIdPedido(id, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pedido;
    }
    public int updatePedidoaEntregado(PedidoDB pedido){
        int result=-1;
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        try {

            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result=AccessPedido.update(pedido, cn);
            cn.commit();
        } catch (Exception ex) {
            try {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }



}
