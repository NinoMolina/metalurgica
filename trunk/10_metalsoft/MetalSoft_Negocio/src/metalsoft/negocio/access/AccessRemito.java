/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.Detalleremito;
import metalsoft.datos.dbobject.Remito;
import metalsoft.datos.dbobject.RemitoPK;
import metalsoft.datos.exception.RemitoException;
import metalsoft.datos.factory.DAOFactoryCreater;
import metalsoft.datos.idao.DetalleremitoDAO;
import metalsoft.datos.idao.RemitoDAO;

/**
 *
 * @author Vicky
 */
public class AccessRemito {
    public static long insert(Remito db,Connection cn)
    {
        RemitoDAO dao=new DAOFactoryCreater().getFactry().createRemitoDAO();
        long result=-1;
        try {
            result=dao.insert(db, cn);
        } catch (RemitoException ex) {
            Logger.getLogger(AccessRemito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static Remito findByIdRemito(long idPres, Connection cn) {
        RemitoDAO dao=new DAOFactoryCreater().getFactry().createRemitoDAO();
        Remito db=null;
        try {
            db=dao.findByIdremito(idPres, cn)[0];
        } catch (RemitoException ex) {
            Logger.getLogger(AccessRemito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }
    public static Detalleremito[] findByIdDetalle(long idDet, Connection cn) {
        DetalleremitoDAO dao=new DAOFactoryCreater().getFactry().createDetalleremitoDAO();
        Detalleremito[] db = null;
        try {
            db=dao.findByIddetalle(idDet, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessRemito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }
    public static long insertDetalleFactura(Detalleremito db,Connection cn)
    {
        DetalleremitoDAO dao=new DAOFactoryCreater().getFactry().createDetalleremitoDAO();
        long result=-1;
        try {
            result=dao.insert(db, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessRemito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static int update(Remito db,Connection cn)
    {
        int result=-1;
        RemitoDAO dao=new DAOFactoryCreater().getFactry().createRemitoDAO();
        RemitoPK pk=new RemitoPK(db.getIdremito());
        try {
            result=dao.update(pk, db, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessRemito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
