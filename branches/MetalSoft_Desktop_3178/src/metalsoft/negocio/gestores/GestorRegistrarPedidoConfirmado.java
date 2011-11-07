/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import metalsoft.negocio.gestores.estados.IdsEstadoPedido;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.negocio.access.AccessPedido;
import metalsoft.negocio.access.AccessViews;

/**
 *
 * @author Nino
 */
public class GestorRegistrarPedidoConfirmado {

    public LinkedList<ViewPedidoNoConfirmado> buscarPedidosNoConfirmados() {
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        LinkedList<ViewPedidoNoConfirmado> list=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.allPedidosNoConfirmados(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarPedidoConfirmado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public boolean registrarPedidoConfirmado(long idpedido) {
       PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        boolean result=false;
        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            int update=AccessPedido.update(idpedido, IdsEstadoPedido.CONFIRMADO, cn);
            cn.commit();
            if(update>0)
                result=true;
        } catch (Exception ex) {
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorRegistrarPedidoConfirmado.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(GestorRegistrarPedidoConfirmado.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarPedidoConfirmado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

}
