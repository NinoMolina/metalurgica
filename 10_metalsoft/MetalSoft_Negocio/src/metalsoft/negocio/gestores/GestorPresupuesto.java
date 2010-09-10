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
import metalsoft.negocio.access.AccessViews;

/**
 *
 * @author Nino
 */
public class GestorPresupuesto {

    public GestorPresupuesto() {
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


}
