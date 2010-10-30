/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.PedidoDB;
import metalsoft.negocio.access.AccessPedido;
import metalsoft.negocio.access.AccessViews;

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




}
