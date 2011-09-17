/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.TipomaterialDB;
import metalsoft.datos.exception.TipomaterialException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.TipomaterialDAO;


/**
 *
 * @author Nino
 */
public class AccessTipoMaterial {

    public static TipomaterialDB buscarTipoMaterial(long id)
    {
        TipomaterialDAO dao=new DAOFactoryImpl().createTipomaterialDAO();
        Connection cn=null;

        try {
            cn=new PostgreSQLManager().concectGetCn();
            return dao.findByIdtipomaterial(id, cn)[0];
        } catch (TipomaterialException ex) {
            Logger.getLogger(AccessTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AccessTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static TipomaterialDB[] findAll(Connection cn) {
        TipomaterialDB[] db=null;
        TipomaterialDAO dao=new DAOFactoryImpl().createTipomaterialDAO();
        try {
            db = dao.findAll(cn);
        } catch (TipomaterialException ex) {
            Logger.getLogger(AccessTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }

        return db;
    }

}
