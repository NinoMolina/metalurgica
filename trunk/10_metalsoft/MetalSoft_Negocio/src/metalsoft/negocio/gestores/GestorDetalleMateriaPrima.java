/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.access.AccessMateriaPrima;
import metalsoft.negocio.access.AccessViews;

/**
 *
 * @author Nino
 */
public class GestorDetalleMateriaPrima {

    public LinkedList<ViewPedidoEnListadoProcedimientos> buscarPedidosCDetalleProcedimientos() {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewPedidoEnListadoProcedimientos> list=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.pedidosSegunEstado(IdsEstadoPedido.PEDIDOCONDETALLEDEPROCEDIMIENTOS,cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorDetalleMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
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

    public LinkedList<ViewMateriaPrima> obtenerAllMateriaPrima() {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewMateriaPrima> list=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            //list=AccessViews.allMateriaPrima(cn);
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

}
