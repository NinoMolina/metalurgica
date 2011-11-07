/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;
import metalsoft.datos.dbobject.ProcesocalidadDB;
import metalsoft.datos.idao.ProcesocalidadDAO;
import metalsoft.negocio.calidad.ProcesoCalidad;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.ProcesocalidadPKDB;
import metalsoft.datos.exception.ProcesocalidadException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.negocio.gestores.Parser;

/**
 *
 * @author Vicky
 */
public class AccessProcesoCalidad {
    public static long delete(long id, Connection cn) {
        long result=-1;
        ProcesocalidadDAO dao=new DAOFactoryImpl().createProcesocalidadDAO();

        ProcesocalidadPKDB pk=new ProcesocalidadPKDB(id);
        try {
            result=dao.delete(pk, cn);
        } catch (ProcesocalidadException ex) {
            Logger.getLogger(AccessProcesoCalidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static ProcesocalidadDB findById(long valor, Connection cn) {
        ProcesocalidadDB x=null;
        ProcesocalidadDAO dao=new DAOFactoryImpl().createProcesocalidadDAO();
        try {
            x = dao.findByIdprocesocalidad(valor, cn)[0];
        } catch (Exception ex) {
            Logger.getLogger(AccessProcesoCalidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }

    public static ProcesocalidadDB[] findByNombreILIKE(String valor, Connection cn) {
        ProcesocalidadDB[] x=null;
        ProcesocalidadDAO dao=new DAOFactoryImpl().createProcesocalidadDAO();
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            x = dao.findExecutingUserWhere("nombre ILIKE '"+valor+"%'", sqlParams, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessProcesoCalidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }

    public static long insert(ProcesoCalidad procesoCalidad, long idAccionCalidad, Connection cn) {
        long result=-1;
        ProcesocalidadDAO dao=new DAOFactoryImpl().createProcesocalidadDAO();
        ProcesocalidadDB db = null;

        try {
            db=Parser.parseToProcesoCalidadDB(procesoCalidad);
            db.setAccioncalidad(idAccionCalidad);

            result=dao.insert(db, cn);
            db.setIdprocesocalidad(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessProcesoCalidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long update(ProcesoCalidad procesoCalidad, long idProcesoCalidad, long idAccionCalidad, Connection cn) {
        long result=-1;
        ProcesocalidadDAO dao=new DAOFactoryImpl().createProcesocalidadDAO();

        ProcesocalidadDB db=null;

        ProcesocalidadPKDB pk=new ProcesocalidadPKDB(idProcesoCalidad);
        try {
            db=Parser.parseToProcesoCalidadDB(procesoCalidad);
            db.setAccioncalidad(idAccionCalidad);

            result=dao.update(pk,db, cn);
        } catch (ProcesocalidadException ex) {
            Logger.getLogger(AccessProcesoCalidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
