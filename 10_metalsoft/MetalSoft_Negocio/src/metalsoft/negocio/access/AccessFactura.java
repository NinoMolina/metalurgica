/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.FacturaDB;
import metalsoft.datos.dbobject.FacturaPK;
import metalsoft.datos.exception.FacturaException;
import metalsoft.datos.factory.DAOFactoryCreater;
import metalsoft.datos.idao.FacturaDAO;
import metalsoft.negocio.ventas.Factura;

/**
 *
 * @author Vicky
 */
public class AccessFactura {
    public static long insert(FacturaDB db,Connection cn)
    {
        FacturaDAO dao=new DAOFactoryCreater().getFactry().createFacturaDAO();
        long result=-1;
        try {
            result=dao.insert(db, cn);
        } catch (FacturaException ex) {
            Logger.getLogger(AccessFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static FacturaDB findByIdPresupuesto(long idPres, Connection cn) {
        FacturaDAO dao=new DAOFactoryCreater().getFactry().createFacturaDAO();
        FacturaDB db=null;
        try {
            db=dao.findByIdfactura(idPres, cn)[0];
        } catch (FacturaException ex) {
            Logger.getLogger(AccessFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }

    public static int update(FacturaDB db,Connection cn)
    {
        int result=-1;
        FacturaDAO dao=new DAOFactoryCreater().getFactry().createFacturaDAO();
        FacturaPK pk=new FacturaPK(db.getIdfactura());
        try {
            result=dao.update(pk, db, cn);
        } catch (FacturaException ex) {
            Logger.getLogger(AccessFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
