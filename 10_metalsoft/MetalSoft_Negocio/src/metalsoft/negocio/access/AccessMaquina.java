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
import metalsoft.datos.dbobject.MaquinaDB;
import metalsoft.datos.dbobject.MaquinaPK;
import metalsoft.datos.exception.MaquinaException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.MaquinaDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.mantmaquinarias.Maquina;
/**
 *
 * @author Vicky
 */
public class AccessMaquina {

    public static MaquinaDB[] findAll(Connection cn)
    {
        MaquinaDB[] db=null;
        MaquinaDAO dao=new DAOFactoryImpl().createMaquinaDAO();
        try {
            db = dao.findAll(cn);
        } catch (MaquinaException ex) {
            Logger.getLogger(AccessMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }

        return db;
    }

}
