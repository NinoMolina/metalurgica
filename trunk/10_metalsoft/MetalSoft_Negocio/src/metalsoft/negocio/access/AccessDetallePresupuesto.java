/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import metalsoft.negocio.ventas.DetallePresupuesto;

/**
 *
 * @author Nino
 */
public class AccessDetallePresupuesto {

    public static long insert(DetallePresupuesto dp,long idPresupuesto,Connection cn)
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
