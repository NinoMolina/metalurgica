/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.DetallempasignadaDB;
import metalsoft.datos.dbobject.DetallempasignadaPKDB;
import metalsoft.datos.dbobject.MpasignadaxpiezarealDB;
import metalsoft.datos.dbobject.MpasignadaxpiezarealPKDB;
import metalsoft.datos.dbobject.PlanificacionproduccionDB;
import metalsoft.datos.dbobject.PlanificacionproduccionPKDB;
import metalsoft.datos.exception.DetallempasignadaException;
import metalsoft.datos.exception.MpasignadaxpiezarealException;
import metalsoft.datos.factory.DAOFactoryCreater;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.DetallempasignadaDAO;
import metalsoft.datos.idao.MpasignadaxpiezarealDAO;
import metalsoft.datos.idao.PlanificacionproduccionDAO;

/**
 *
 * @author Vicky
 */
public class AccessPlanificacion {
    public static long insertDetalleMPAsignada(long idPlan, long idMP, int camtMP,Connection cn)
    {
        DetallempasignadaDAO dao=new DAOFactoryCreater().getFactry().createDetallempasignadaDAO();
        DetallempasignadaDB db=new DetallempasignadaDB();
        db.setCantidadmp(camtMP);
        db.setIdmateriaprima(idMP);
        db.setIdplanificacionproduccion(idPlan);
        long result=-1;
        try {
            result=dao.insert(db, cn);
        } catch (DetallempasignadaException ex) {
            Logger.getLogger(AccessPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int updateDetalleMPAsignada(DetallempasignadaDB db,Connection cn)
    {
        int result=-1;
        DetallempasignadaDAO dao=new DAOFactoryCreater().getFactry().createDetallempasignadaDAO();
        DetallempasignadaPKDB pk=new DetallempasignadaPKDB(db.getId());
        try {
            result=dao.update(pk, db, cn);
        } catch (DetallempasignadaException ex) {
            Logger.getLogger(AccessPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static long insertMPAsignadaXPiezaReal(long idpiezaReal, long iddetalle,Connection cn)
    {
        MpasignadaxpiezarealDAO dao=new DAOFactoryCreater().getFactry().createMpasignadaxpiezarealDAO();
        MpasignadaxpiezarealDB db=new MpasignadaxpiezarealDB();
        db.setIddetallempasignada(iddetalle);
        db.setIdpiezareal(idpiezaReal);
        long result=-1;
        try {
            result=dao.insert(db, cn);
        } catch (MpasignadaxpiezarealException ex) {
            Logger.getLogger(AccessPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int updateMPAsignadaXPiezaReal(MpasignadaxpiezarealDB db,Connection cn)
    {
        int result=-1;
        MpasignadaxpiezarealDAO dao=new DAOFactoryCreater().getFactry().createMpasignadaxpiezarealDAO();
        MpasignadaxpiezarealPKDB pk=new MpasignadaxpiezarealPKDB(db.getId());
        try {
            result=dao.update(pk, db, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static PlanificacionproduccionDB findByIdPedido(long id,Connection cn)
    {
        PlanificacionproduccionDAO dao=new DAOFactoryImpl().createPlanificacionproduccionDAO();
        PlanificacionproduccionDB db = null;

        try {
            db=dao.findByPedido(id, cn)[0];
        } catch (Exception ex) {
            Logger.getLogger(AccessPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }
    public static DetallempasignadaDB findDetalleMPAsignada(DetallempasignadaDB dbDetalle,Connection cn)
    {
        DetallempasignadaDAO dao=new DAOFactoryImpl().createDetallempasignadaDAO();
        DetallempasignadaDB[] db = null;

        try {
            db=dao.findByIdmateriaprimaAndIdPlan(dbDetalle.getIdmateriaprima(),dbDetalle.getIdplanificacionproduccion(), cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(db.length>0) return db[0];
        else return null;
    }
    public static int updateEstadoMPAsignada(long idPlan, Connection cn) {
        int result=-1;
        PlanificacionproduccionDAO dao=new DAOFactoryImpl().createPlanificacionproduccionDAO();
        PlanificacionproduccionDB db = null;
        PlanificacionproduccionPKDB pk=new PlanificacionproduccionPKDB(idPlan);
        try {
            db=dao.findByIdplanificacionproduccion(idPlan, cn)[0];
            db.setIdestado(2);
            result=dao.update(pk,db, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
