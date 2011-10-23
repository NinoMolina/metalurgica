/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;


import metalsoft.negocio.gestores.estados.IdsEstadoPedido;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.ClienteDB;
import metalsoft.datos.dbobject.ComprobantepagoDB;
import metalsoft.datos.dbobject.DetallefacturaDB;
import metalsoft.datos.dbobject.DetalleremitoDB;
import metalsoft.datos.dbobject.FacturaDB;
import metalsoft.datos.dbobject.FormadepagoDB;
import metalsoft.datos.dbobject.PedidoDB;
import metalsoft.datos.dbobject.RemitoDB;
import metalsoft.negocio.access.AccessCliente;
import metalsoft.negocio.access.AccessComprobantePago;
import metalsoft.negocio.access.AccessFactura;
import metalsoft.negocio.access.AccessFormaDePago;
import metalsoft.negocio.access.AccessPedido;
import metalsoft.negocio.access.AccessRemito;
import metalsoft.negocio.access.AccessViews;
import metalsoft.util.Combo;
import metalsoft.util.Fecha;
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

    private LinkedList<ViewPresupuestoParaFactura> detallePedidoDB;

    public LinkedList<ViewPedidosClienteSegunEstado> buscarPedidosClienteEnArmado(long idCliente) {
        PostgreSQLManager pg = new PostgreSQLManager();
        LinkedList<ViewPedidosClienteSegunEstado> list = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            list = AccessViews.pedidosClienteSegunEstado(idCliente, IdsEstadoPedido.ENARMADO, cn);
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

    public LinkedList<ViewPedidoEnListadoProcedimientos> buscarPedidosEntregados() {
        PostgreSQLManager pg = new PostgreSQLManager();
        LinkedList<ViewPedidoEnListadoProcedimientos> list = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            list = AccessViews.pedidosSegunEstado(IdsEstadoPedido.ENTREGADO, cn);
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

    public LinkedList<ViewPresupuestoParaFactura> buscarDetallePedidoSeleccionado(long idPedido) {
        PostgreSQLManager pg = new PostgreSQLManager();
        LinkedList<ViewPresupuestoParaFactura> list = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            list = AccessViews.listDetallePresupuestoParaFactura(idPedido, cn);
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

    public void imprimirFactura(long id, long idformapago, String tipofactura, Date fechaVencimiento,double monto) {

        String sourceFile = "D:\\rpt\\RptFactura.jasper";

        PostgreSQLManager pg = new PostgreSQLManager();
        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            guardarFactura(id, idformapago, tipofactura,fechaVencimiento, monto, cn);

            masterReport = (JasperReport) JRLoader.loadObject(sourceFile);

            param.put("ID_PEDIDO", new Long(id));
            param.put("MONTO", new Double(monto));
//            JRResultSetDataSource rsDatparam.put("ID_PEDIDO", new Long(pedidoSeleccionadoDB.getIdpedido()));aSource = new JRResultSetDataSource(rs);
            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);


            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("Factura");
            jviewer.setVisible(true);
            cn.commit();
            
        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void imprimirRemito(long id) {
//        URL sourceFile = null;
//        try {
//           sourceFile = new URL("https://metalurgica.googlecode.com/svn/trunk/10_metalsoft/Reportes/RptRemito.jasper");
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
//        }
        String sourceFile = "D:\\rpt\\RptRemito.jasper";
        PostgreSQLManager pg = new PostgreSQLManager();
        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            guardarRemito(id, cn);
//            ps=cn.prepareStatement(query);
//            rs=ps.executeQuery();
            masterReport = (JasperReport) JRLoader.loadObject(sourceFile);
            param.put("ID_PEDIDO", new Long(id));
//            JRResultSetDataSource rsDatparam.put("ID_PEDIDO", new Long(pedidoSeleccionadoDB.getIdpedido()));aSource = new JRResultSetDataSource(rs);
            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);


            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("Remito");
            jviewer.setVisible(true);
            cn.commit();

        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public boolean estaTodaPagada(long id)
    {
        Double diferenciaMontos=(double)-1;
        diferenciaMontos=montoFactura(id)-montoPagadoPorFactura(id);
        if(diferenciaMontos==0) return true;
        else return false;
    }
    public long registrarCobroTotal(long idPedido)
    {
        long result=-1;
        PostgreSQLManager pg = new PostgreSQLManager();

        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            PedidoDB ped=AccessPedido.findByIdPedido(idPedido, cn);
            FacturaDB fact=AccessFactura.findByIdFactura(ped.getFactura(), cn);
            ped.setEstado(IdsEstadoPedido.COBRADO);
            fact.setEstado(2);
            result = AccessPedido.update(ped, cn);
            result = AccessFactura.update(fact, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    public void imprimirComprobantePago(long id, Double monto, long idformapago) {
//        URL sourceFile = null;
//        try {
//            sourceFile = new URL("https://metalurgica.googlecode.com/svn/trunk/10_metalsoft/Reportes/RptComprobanteDePago.jasper");
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
//        }
        String sourceFile = "D:\\rpt\\RptComprobanteDePago.jasper";
        PostgreSQLManager pg = new PostgreSQLManager();
        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            long result=guardarComprobanteDePago(id, monto, idformapago, cn);
//            ps=cn.prepareStatement(query);
//            rs=ps.executeQuery();
            masterReport = (JasperReport) JRLoader.loadObject(sourceFile);
            param.put("ID_COMPROBANTE", new Long(result));
//            JRResultSetDataSource rsDatparam.put("ID_PEDIDO", new Long(pedidoSeleccionadoDB.getIdpedido()));aSource = new JRResultSetDataSource(rs);
            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);


            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("Recibo");
            jviewer.setVisible(true);
            cn.commit();

        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public LinkedList<ViewPresupuestoParaFactura> buscarDetallePedidoCotizacion(long idPedido) {
        PostgreSQLManager pg = new PostgreSQLManager();
        LinkedList<ViewPresupuestoParaFactura> list = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            list = AccessViews.listDetallePresupuestoParaFactura(idPedido, cn);
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

    public long guardarFactura(long idPedido, long fp, String tipofactura,Date fechaVencimiento, double monto, Connection cn) {
        long result = -1;
        long resultPedido = -1;
        long resultDetalle = -1;
        FacturaDB facturaDB = new FacturaDB();
        PedidoDB ped = AccessPedido.findByIdPedido(idPedido, cn);
        ClienteDB cli = AccessCliente.findByIdCliente(ped.getCliente(), cn);
        detallePedidoDB = buscarDetallePedidoCotizacion(idPedido);
        facturaDB.setEstado(1);
        facturaDB.setFechaemision(Fecha.parseToDateSQL(Fecha.parseToDate(Fecha.fechaActual())));
        facturaDB.setFormapago(fp);
        facturaDB.setTipofactura(tipofactura);
        //facturaDB.setTipoiva(cli.getCondicioniva());
        facturaDB.setFechavencimiento(Fecha.parseToDateSQL(fechaVencimiento));
        //remDB.setUsuario(idPedido);

        result = AccessFactura.insert(facturaDB, cn);
        ped.setFactura(result);
        resultPedido = AccessPedido.update(ped, cn);

        DetallefacturaDB db = new DetallefacturaDB();
        Iterator<ViewPresupuestoParaFactura> iter = detallePedidoDB.iterator();
        ViewPresupuestoParaFactura view = null;
        while (iter.hasNext()) {
            view = iter.next();

            db.setCantidad(view.getCantidad());
            db.setIddetallepedido(view.getIddetalle());
            db.setIdfactura(result);
            db.setIdpedido(idPedido);
            db.setMontoparcial(view.getCantidad() * view.getPrecio());
            
            resultDetalle = AccessFactura.insertDetalleFactura(db, cn);
        }
        
        facturaDB.setIdfactura(result);
        facturaDB.setNrofactura(result);
        facturaDB.setMontototal(monto);
        
        AccessFactura.update(facturaDB, cn);

        //tiene que devolver 1 cuando se guarda correctamente
        //como son 2 actualizaciones entonces la suma de las filas acutalizadas
        //dividido 2 tiene que ser 1
        return result;

    }

    public long guardarRemito(long idPedido, Connection cn) {

        long result = -1;
        long resultPedido = -1;
        long resultDetalle = -1;
        RemitoDB remDB = new RemitoDB();

        PedidoDB ped = AccessPedido.findByIdPedido(idPedido, cn);
        ClienteDB cli = AccessCliente.findByIdCliente(ped.getCliente(), cn);
        detallePedidoDB = buscarDetallePedidoCotizacion(idPedido);
        remDB.setEstado(1);
        remDB.setFechaemision(Fecha.parseToDateSQL(Fecha.parseToDate(Fecha.fechaActual())));
        remDB.setPedido(idPedido);

        result = AccessRemito.insert(remDB, cn);

        DetalleremitoDB db = new DetalleremitoDB();
        Iterator<ViewPresupuestoParaFactura> iter = detallePedidoDB.iterator();
        ViewPresupuestoParaFactura view = null;
        while (iter.hasNext()) {
            view = iter.next();

            db.setCantidad(view.getCantidad());
            db.setDescripcion(view.getDescripcion());
            db.setIdremito(result);
            db.setProducto(view.getIdproducto());
            resultDetalle = AccessRemito.insertDetalleRemito(db, cn);
        }
        remDB.setIdremito(result);
        remDB.setNroremito(result);
        AccessRemito.update(remDB, cn);

        return result;

    }

    public long guardarComprobanteDePago(long idPedido, Double monto, long formaPago, Connection cn) {

        long result = -1;
        long resultPedido = -1;
        long resultDetalle = -1;
        ComprobantepagoDB remDB = new ComprobantepagoDB();

        PedidoDB ped = AccessPedido.findByIdPedido(idPedido, cn);
        ClienteDB cli = AccessCliente.findByIdCliente(ped.getCliente(), cn);
        detallePedidoDB = buscarDetallePedidoCotizacion(idPedido);
        remDB.setFechaemision(Fecha.parseToDateSQL(Fecha.parseToDate(Fecha.fechaActual())));
        remDB.setFactura(ped.getFactura());
        remDB.setFormadepago(formaPago);
        remDB.setMonto(monto);
        //remDB.setUsuario();

        result = AccessComprobantePago.insert(remDB, cn);


        remDB.setIdcomprobantepago(result);
        remDB.setNrocomprobantepago(result);
        AccessComprobantePago.update(remDB, cn);
        Double diferecia = montoFactura(idPedido) - montoPagadoPorFactura(idPedido) - monto;
        if (diferecia == 0) {
            ped.setEstado(IdsEstadoPedido.COBRADO);
            updatePedido(ped);
        }
        return result;

    }

    public PedidoDB buscarPedidoPorID(long id) {
        PostgreSQLManager pg = new PostgreSQLManager();
        PedidoDB pedido = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();

            pedido = AccessPedido.findByIdPedido(id, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pedido;
    }

    public Double montoPagadoPorFactura(long id) {
        PostgreSQLManager pg = new PostgreSQLManager();
        PedidoDB pedido = buscarPedidoPorID(id);
        ComprobantepagoDB[] cp = null;
        Double monto = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            cp = AccessComprobantePago.findByIdFactura(pedido.getFactura(), cn);
            monto = (double) 0;
            for (int i = 0; i < cp.length; i++) {
                monto = monto + cp[i].getMonto();
            }

        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return monto;
    }

    public Double montoFactura(long id) {
        PostgreSQLManager pg = new PostgreSQLManager();
        PedidoDB pedido = buscarPedidoPorID(id);
        DetallefacturaDB[] cp = null;
        Double monto = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            cp = AccessFactura.findDetalles(pedido.getFactura(), cn);
            monto = (double) 0;
            for (int i = 0; i < cp.length; i++) {
                monto = monto + cp[i].getMontoparcial();
            }

        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return monto;
    }

    public int updatePedido(PedidoDB pedido) {
        int result = -1;
        PostgreSQLManager pg = new PostgreSQLManager();
        Connection cn = null;
        try {

            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result = AccessPedido.update(pedido, cn);
            cn.commit();
        } catch (Exception ex) {
            try {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public void obtenerFormasDePago(JComboBox combo) {
        FormadepagoDB[] prioridades = null;
        Connection cn = null;
        PostgreSQLManager pg = null;
        combo.removeAllItems();
        try {
            pg = new PostgreSQLManager();
            cn = pg.concectGetCn();
            prioridades = AccessFormaDePago.findAll(cn);

            for (int i = 0; i < prioridades.length; i++) {
                Combo.cargarCombo(combo, String.valueOf(prioridades[i].getIdformapago()), prioridades[i].getNombre());
            }
            combo.setSelectedIndex(0);
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
}
