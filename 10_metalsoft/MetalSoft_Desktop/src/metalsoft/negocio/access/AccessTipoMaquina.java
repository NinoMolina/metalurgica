/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.TipomaquinaDB;
import metalsoft.datos.exception.TipomaquinaException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.TipomaquinaDAO;

/**
 *
 * @author Vicky
 */
public class AccessTipoMaquina {
public static TipomaquinaDB[] findAll(Connection cn)
    {
        TipomaquinaDB[] db=null;
        TipomaquinaDAO dao=new DAOFactoryImpl().createTipomaquinaDAO();
        try {
            db = dao.findAll(cn);
        } catch (TipomaquinaException ex) {
            Logger.getLogger(AccessTipoMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }

        return db;
    }

    public static TipomaquinaDB findById(long valor) {
        TipomaquinaDB[] x=null;
        TipomaquinaDB tm=null;
        TipomaquinaDAO dao=new DAOFactoryImpl().createTipomaquinaDAO();
        Connection cn=null;
        try {
            cn=new PostgreSQLManager().concectGetCn();
            x = dao.findByIdtipomaquina(valor, cn);
           tm = x[0];
        } catch (Exception ex) {
            System.out.println(AccessTipoMaquina.class.getName() + ": " + ex.getMessage());
        }
        return tm;
    }
}
