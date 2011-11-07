/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.DetalleremitoDB;
import metalsoft.datos.dbobject.RemitoDB;
import metalsoft.datos.dbobject.RemitoPKDB;
import metalsoft.datos.exception.RemitoException;
import metalsoft.datos.factory.DAOFactoryCreater;
import metalsoft.datos.idao.DetalleremitoDAO;
import metalsoft.datos.idao.RemitoDAO;

/**
 *
 * @author Vicky
 */
public class AccessRemito {
    public static long insert(RemitoDB db,Connection cn)
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

    public static RemitoDB findByIdRemito(long idPres, Connection cn) {
        RemitoDAO dao=new DAOFactoryCreater().getFactry().createRemitoDAO();
        RemitoDB db=null;
        try {
            db=dao.findByIdremito(idPres, cn)[0];
        } catch (RemitoException ex) {
            Logger.getLogger(AccessRemito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }
    public static DetalleremitoDB[] findByIdDetalle(long idDet, Connection cn) {
        DetalleremitoDAO dao=new DAOFactoryCreater().getFactry().createDetalleremitoDAO();
        DetalleremitoDB[] db = null;
        try {
            db=dao.findByIddetalle(idDet, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessRemito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }
    public static long insertDetalleRemito(DetalleremitoDB db,Connection cn)
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
    public static int update(RemitoDB db,Connection cn)
    {
        int result=-1;
        RemitoDAO dao=new DAOFactoryCreater().getFactry().createRemitoDAO();
        RemitoPKDB pk=new RemitoPKDB(db.getIdremito());
        try {
            result=dao.update(pk, db, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessRemito.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
