/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.FormadepagoDB;
import metalsoft.datos.exception.FormadepagoException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.FormadepagoDAO;

/**
 *
 * @author Vicky
 */
public class AccessFormaDePago {
    public static FormadepagoDB[] findAll(Connection cn) {
        FormadepagoDB[] vDB=null;
        FormadepagoDAO dao=new DAOFactoryImpl().createFormadepagoDAO();
        try {
            vDB = dao.findAll(cn);
        } catch (FormadepagoException ex) {
            Logger.getLogger(AccessFormaDePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vDB;
    }
}
