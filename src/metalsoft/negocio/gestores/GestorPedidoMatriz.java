/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.negocio.access.AccessFunctions;

/**
 *
 * @author Vicky
 */
public class GestorPedidoMatriz {

    public long generarNvoNroPedido(){
        long result=-1;
        PostgreSQLManager pg=null;
        Connection cn=null;
        pg=new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
            result=AccessFunctions.nvoNroEmpresaMetalurgica(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoMatriz.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPedidoMatriz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

}
