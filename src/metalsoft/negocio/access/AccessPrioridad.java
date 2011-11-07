/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.PrioridadDB;
import metalsoft.datos.exception.PrioridadException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.PrioridadDAO;

/**
 *
 * @author Nino
 */
public class AccessPrioridad {

    public static PrioridadDB[] findAll(Connection cn) {
        PrioridadDB[] vDB=null;
        PrioridadDAO dao=new DAOFactoryImpl().createPrioridadDAO();
        try {
            vDB = dao.findAll(cn);
        } catch (PrioridadException ex) {
            Logger.getLogger(AccessPrioridad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vDB;
    }
}
