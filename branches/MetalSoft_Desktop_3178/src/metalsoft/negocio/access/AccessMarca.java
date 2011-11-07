/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.MarcaDB;
import metalsoft.datos.dbobject.MarcaPKDB;
import metalsoft.datos.exception.MarcaException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.MarcaDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.mantmaquinarias.Marca;
/**
 *
 * @author Vicky
 */
public class AccessMarca {
    public static MarcaDB[] findAll(Connection cn)
    {
        MarcaDB[] db=null;
        MarcaDAO dao=new DAOFactoryImpl().createMarcaDAO();
        try {
            db = dao.findAll(cn);
        } catch (MarcaException ex) {
            Logger.getLogger(AccessMarca.class.getName()).log(Level.SEVERE, null, ex);
        }

        return db;
    }
}
