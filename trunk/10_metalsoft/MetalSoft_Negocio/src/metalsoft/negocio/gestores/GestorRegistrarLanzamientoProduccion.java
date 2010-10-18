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
public class GestorRegistrarLanzamientoProduccion {

    public LinkedList<ViewPedidosConMPAsignada> buscarPedidosConMPAsignada() {
        PostgreSQLManager pg = new PostgreSQLManager();
        LinkedList<ViewPedidosConMPAsignada> list = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            list = AccessViews.listPedidosConMPAsignada(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

}
