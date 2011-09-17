/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.UnidadmedidaDB;
import metalsoft.datos.exception.UnidadmedidaException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.UnidadmedidaDAO;
/**
 *
 * @author Vicky
 */
public class AccessUnidadDeMedida {
    public static UnidadmedidaDB[] findAll(Connection cn)
    {
        UnidadmedidaDB[] db=null;
        UnidadmedidaDAO dao=new DAOFactoryImpl().createUnidadmedidaDAO();
        try {
            db = dao.findAll(cn);
        } catch (UnidadmedidaException ex) {
            Logger.getLogger(AccessUnidadDeMedida.class.getName()).log(Level.SEVERE, null, ex);
        }

        return db;
    }
}
