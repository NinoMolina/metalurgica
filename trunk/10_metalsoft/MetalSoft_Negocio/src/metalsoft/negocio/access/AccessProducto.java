/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.ProductoDB;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.ProductoDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.ventas.Producto;

/**
 *
 * @author Nino
 */
public class AccessProducto {

    public static long insert(Producto x, Connection cn) {
        long result=-1;
        ProductoDAO dao=new DAOFactoryImpl().createProductoDAO();
        ProductoDB db = null;

        try {
            db=Parser.parseToProductoDB(x);
            result=dao.insert(db, cn);
            db.setIdproducto(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
