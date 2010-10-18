/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.Detallempasignada;
import metalsoft.datos.dbobject.DetallempasignadaPK;
import metalsoft.datos.dbobject.Mpasignadaxpiezareal;
import metalsoft.datos.dbobject.MpasignadaxpiezarealPK;
import metalsoft.datos.dbobject.Planificacionproduccion;
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
        Detallempasignada db=new Detallempasignada();
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

    public static int updateDetalleMPAsignada(Detallempasignada db,Connection cn)
    {
        int result=-1;
        DetallempasignadaDAO dao=new DAOFactoryCreater().getFactry().createDetallempasignadaDAO();
        DetallempasignadaPK pk=new DetallempasignadaPK(db.getId());
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
        Mpasignadaxpiezareal db=new Mpasignadaxpiezareal();
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

    public static int updateMPAsignadaXPiezaReal(Mpasignadaxpiezareal db,Connection cn)
    {
        int result=-1;
        MpasignadaxpiezarealDAO dao=new DAOFactoryCreater().getFactry().createMpasignadaxpiezarealDAO();
        MpasignadaxpiezarealPK pk=new MpasignadaxpiezarealPK(db.getId());
        try {
            result=dao.update(pk, db, cn);
        } catch (MpasignadaxpiezarealException ex) {
            Logger.getLogger(AccessPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static Planificacionproduccion findByIdPedido(long id,Connection cn)
    {
        PlanificacionproduccionDAO dao=new DAOFactoryImpl().createPlanificacionproduccionDAO();
        Planificacionproduccion db = null;

        try {
            db=dao.findByPedido(id, cn)[0];
        } catch (Exception ex) {
            Logger.getLogger(AccessPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }
}
