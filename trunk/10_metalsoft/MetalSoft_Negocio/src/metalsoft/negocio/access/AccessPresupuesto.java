/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.PresupuestoDB;
import metalsoft.datos.exception.PresupuestoException;
import metalsoft.datos.factory.DAOFactory;
import metalsoft.datos.factory.DAOFactoryCreater;
import metalsoft.datos.idao.PresupuestoDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.ventas.Presupuesto;



/**
 *
 * @author Nino
 */
public class AccessPresupuesto {

    public static long insert(Presupuesto p,Connection cn)
    {
        PresupuestoDAO dao=new DAOFactoryCreater().getFactry().createPresupuestoDAO();
        PresupuestoDB db=null;
        long result=-1;
        try {
            db=Parser.parseToPresupuestoDB(p);
            result=dao.insert(db, cn);
        } catch (PresupuestoException ex) {
            Logger.getLogger(AccessPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
