/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.ComprobantepagoDB;
import metalsoft.datos.dbobject.ComprobantepagoPKDB;
import metalsoft.datos.exception.ComprobantepagoException;
import metalsoft.datos.factory.DAOFactoryCreater;
import metalsoft.datos.idao.ComprobantepagoDAO;

/**
 *
 * @author Vicky
 */
public class AccessComprobantePago {
 public static long insert(ComprobantepagoDB db,Connection cn)
    {
        ComprobantepagoDAO dao=new DAOFactoryCreater().getFactry().createComprobantepagoDAO();
        long result=-1;
        try {
            result=dao.insert(db, cn);
        } catch (ComprobantepagoException ex) {
            Logger.getLogger(AccessComprobantePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static ComprobantepagoDB findByIdComprobante(long idPres, Connection cn) {
        ComprobantepagoDAO dao=new DAOFactoryCreater().getFactry().createComprobantepagoDAO();
        ComprobantepagoDB db=null;
        try {
            db=dao.findByIdcomprobantepago(idPres, cn)[0];
        } catch (ComprobantepagoException ex) {
            Logger.getLogger(AccessComprobantePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }
    public static ComprobantepagoDB[] findByIdFactura(long idPres, Connection cn) {
        ComprobantepagoDAO dao=new DAOFactoryCreater().getFactry().createComprobantepagoDAO();
        ComprobantepagoDB[] db=null;
        try {
            db=dao.findByFactura(idPres, cn);
        } catch (ComprobantepagoException ex) {
            Logger.getLogger(AccessComprobantePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }
    public static int update(ComprobantepagoDB db,Connection cn)
    {
        int result=-1;
        ComprobantepagoDAO dao=new DAOFactoryCreater().getFactry().createComprobantepagoDAO();
        ComprobantepagoPKDB pk=new ComprobantepagoPKDB(db.getIdcomprobantepago());
        try {
            result=dao.update(pk, db, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessComprobantePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
