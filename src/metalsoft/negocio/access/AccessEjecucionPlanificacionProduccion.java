/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.EjecucionplanificacionproduccionDB;
import metalsoft.datos.exception.EjecucionplanificacionproduccionException;
import metalsoft.datos.factory.DAOFactoryCreater;
import metalsoft.datos.idao.EjecucionplanificacionproduccionDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.produccion.EjecucionPlanificacionProduccion;

/**
 *
 * @author Nino
 */
public class AccessEjecucionPlanificacionProduccion {

    public static long insert(EjecucionPlanificacionProduccion ejecucion,long estado,long idplanificacionproduccion, Connection cn) {
        EjecucionplanificacionproduccionDAO dao=new DAOFactoryCreater().getFactry().createEjecucionplanificacionproduccionDAO();
        EjecucionplanificacionproduccionDB db=null;
        long result=-1;
        try {
            db=Parser.parseToDetallepiezapresupuestoDB(ejecucion);
            db.setEstado(estado);
            db.setIdplanificacionproduccion(idplanificacionproduccion);
            result=dao.insert(db, cn);
        } catch (EjecucionplanificacionproduccionException ex) {
            Logger.getLogger(AccessEjecucionPlanificacionProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
