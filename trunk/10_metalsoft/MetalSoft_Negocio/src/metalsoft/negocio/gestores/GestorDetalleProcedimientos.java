/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.PiezaxetapadeproduccionDB;
import metalsoft.negocio.access.AccessDetallePresupuesto;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.access.AccessPedido;
import metalsoft.negocio.access.AccessPiezaXEtapaDeProduccion;
import metalsoft.negocio.access.AccessPresupuesto;
import metalsoft.negocio.access.AccessViews;
import metalsoft.negocio.ventas.DetallePiezaPresupuesto;
import metalsoft.negocio.ventas.DetallePresupuesto;
import metalsoft.negocio.ventas.Presupuesto;

/**
 *
 * @author Nino
 */
public class GestorDetalleProcedimientos {

    private ArrayList<PiezaXEtapas> arlPiezaXEtapas;
    public GestorDetalleProcedimientos() {
        arlPiezaXEtapas=null;
    }

    public LinkedList<ViewPedidoEnListadoProcedimientos> buscarPedidosGenerados() {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewPedidoEnListadoProcedimientos> list=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.pedidosSegunEstado(IdsEstadoPedido.GENERADO,cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public LinkedList<ViewDetallePedidoCotizacion> buscarDetallePedido(long idPed) {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewDetallePedidoCotizacion> list=null;
        ViewDetallePedidoCotizacion v=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.listDetallePedidoCotizacion(idPed, cn);
            Iterator<ViewDetallePedidoCotizacion> iter=list.iterator();
            while(iter.hasNext())
            {
                v=iter.next();
                int cantPiezas=AccessFunctions.cantPiezasXProducto(v.getIdProducto(), cn);
                v.setCantidadPiezas(cantPiezas);
            }
        } catch (Exception ex) {
            Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public LinkedList<ViewDetalleProducto> buscarDetalleProducto(long idPro) {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewDetalleProducto> list=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.listDetalleProducto(idPro, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public LinkedList<ViewEtapaDeProduccion> obtenerEtapasDeProduccion()
    {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewEtapaDeProduccion> list=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.allEtapasDeProduccion(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public boolean esProductoSinAlgunaEtapa(long idProd) {
        PostgreSQLManager pg=new PostgreSQLManager();
        boolean result=false;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            result=AccessFunctions.esProductoSinAlgunaEtapa(idProd,cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public PiezaxetapadeproduccionDB[] buscarEtapasDePieza(long idPieza) {
        PostgreSQLManager pg=new PostgreSQLManager();
        PiezaxetapadeproduccionDB[] result=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            result=AccessPiezaXEtapaDeProduccion.findByIdpieza(idPieza, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public boolean addPiezaXEtapas(PiezaXEtapas pxe) {
        if(arlPiezaXEtapas==null)arlPiezaXEtapas=new ArrayList<PiezaXEtapas>();
        return arlPiezaXEtapas.add(pxe);
    }

    public boolean guardarEtapasPiezaPresupuesto() {
        if(arlPiezaXEtapas.isEmpty())return false;

        long idPed=arlPiezaXEtapas.get(0).getIdPedido();
        long idPro=arlPiezaXEtapas.get(0).getIdProducto();
        long idPi=arlPiezaXEtapas.get(0).getIdPieza();
        double precioProd=arlPiezaXEtapas.get(0).getPrecioProducto();

        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        long idPres=-1;
        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);

            Presupuesto pres=new Presupuesto();
            idPres=AccessPresupuesto.insert(pres, cn);

            AccessPedido.update(idPed,idPres,cn);
            DetallePresupuesto dpres=new DetallePresupuesto();
            dpres.setPrecio(precioProd);
            dpres.setCantidad(cantidad)
            AccessDetallePresupuesto.insert(dpres,idPres,cn);
            cn.commit();
        } catch (Exception ex) {
            try {
                Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex1);
            }

        }

        DetallePiezaPresupuesto detPiPre=null;
        Iterator<PiezaXEtapas> iter=arlPiezaXEtapas.iterator();
        PiezaXEtapas pxe=null;

        while(iter.hasNext())
        {

            detPiPre=new DetallePiezaPresupuesto();
            pxe=iter.next();
            LinkedList<ViewEtapaDeProduccion> ll=pxe.getEtapas();
            Iterator<ViewEtapaDeProduccion> i=ll.iterator();
            ViewEtapaDeProduccion v=null;
            while(i.hasNext())
            {
                v=i.next();

            }
            detPiPre.setDuracionEtapaXPieza(detPiPre.calcularDuracion(pxe));
        }
    }

}
