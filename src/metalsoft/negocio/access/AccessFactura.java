/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.DetallefacturaDB;
import metalsoft.datos.dbobject.FacturaDB;
import metalsoft.datos.dbobject.FacturaPKDB;
import metalsoft.datos.exception.DetallefacturaException;
import metalsoft.datos.exception.FacturaException;
import metalsoft.datos.factory.DAOFactoryCreater;
import metalsoft.datos.idao.DetallefacturaDAO;
import metalsoft.datos.idao.FacturaDAO;
import metalsoft.negocio.ventas.DetalleFactura;
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

    public static FacturaDB findByIdFactura(long idPres, Connection cn) {
        FacturaDAO dao=new DAOFactoryCreater().getFactry().createFacturaDAO();
        FacturaDB db=null;
        try {
            db=dao.findByIdfactura(idPres, cn)[0];
        } catch (FacturaException ex) {
            Logger.getLogger(AccessFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }
    public static DetallefacturaDB[] findByIdDetalle(long idDet, Connection cn) {
        DetallefacturaDAO dao=new DAOFactoryCreater().getFactry().createDetallefacturaDAO();
        DetallefacturaDB[] db = null;
        try {
            db=dao.findByIddetalle(idDet, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }
    public static DetallefacturaDB[] findDetalles(long idDet, Connection cn) {
        DetallefacturaDAO dao=new DAOFactoryCreater().getFactry().createDetallefacturaDAO();
        DetallefacturaDB[] db = null;
        try {
            db=dao.findByIdfactura(idDet, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }
    public static long insertDetalleFactura(DetallefacturaDB db,Connection cn)
    {
        DetallefacturaDAO dao=new DAOFactoryCreater().getFactry().createDetallefacturaDAO();
        long result=-1;
        try {
            result=dao.insert(db, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static int update(FacturaDB db,Connection cn)
    {
        int result=-1;
        FacturaDAO dao=new DAOFactoryCreater().getFactry().createFacturaDAO();
        FacturaPKDB pk=new FacturaPKDB(db.getIdfactura());
        try {
            result=dao.update(pk, db, cn);
        } catch (FacturaException ex) {
            Logger.getLogger(AccessFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
