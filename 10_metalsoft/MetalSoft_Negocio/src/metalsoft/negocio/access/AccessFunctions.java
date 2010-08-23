/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;

/**
 *
 * @author Nino
 */
public class AccessFunctions {

    public static int nvoNroPedido(Connection cn)
    {
        String query="{ ? = call nvonropedido()";
        int result=-1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.INTEGER);
            cs.execute();
            result=cs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
