/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.PedidoJpaController;
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.negocio.access.AccessViews;

/**
 *
 * @author Mariana
 */
public class GestorArmadoPedido {

    public List<Pedido> buscarPedidosNoArmadosLIKE(String pedido) {
        return JpaUtil.getPedidosSinArmarLIKE(pedido);
    }

    public List<Pedido> buscarPedidosNoArmados() {
        return JpaUtil.getPedidosSinArmar();
    }

    public LinkedList<ViewDetallePedidoReal> buscarDetallePedido(long idPed) {
        PostgreSQLManager pg = new PostgreSQLManager();
        LinkedList<ViewDetallePedidoReal> list = null;
        ViewDetallePedidoReal v = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            list = AccessViews.listDetallePedidoReal(idPed, cn);
          } catch (Exception ex) {
            Logger.getLogger(GestorArmadoPedido.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorArmadoPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public long modificarPedido(Pedido ped) {
        PedidoJpaController con=new  PedidoJpaController(JpaUtil.getEntityManagerFactory());
        try {
            con.edit(ped);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(GestorArmadoPedido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(GestorArmadoPedido.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GestorArmadoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ped.getIdpedido();
    }

}
