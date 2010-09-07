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
        String query="{ ? = call nvonropedido()}";
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
    public static String nvoNroProducto(Connection cn)
    {
        String query="{ ? = call nvonroproducto()}";
        String result="-1";
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.VARCHAR);
            cs.execute();
            result=cs.getString(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static int cantPiezasXProducto(long id,Connection cn)
    {
        String query="{ ? = call cantpiezasxproducto(?)}";
        int result=-1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.INTEGER);
            cs.setLong(2, id);
            cs.execute();
            result=cs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static boolean esProductoSinAlgunaEtapa(long idProd, Connection cn) {
        String query="{ ? = call esproductosinalgunaetapa(?)}";
        boolean result = false;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.BOOLEAN);
            cs.setLong(2, idProd);
            cs.execute();
            result=cs.getBoolean(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int cantPiezasDePedido(long idPed, Connection cn) {
        String query="{ ? = call cantpiezasdepedido(?)}";
        int result = -1;
        try {
            CallableStatement cs = cn.prepareCall(query);
            cs.registerOutParameter(1, java.sql.Types.INTEGER);
            cs.setLong(2, idPed);
            cs.execute();
            result=cs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(AccessFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
