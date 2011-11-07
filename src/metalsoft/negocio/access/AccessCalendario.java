/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.CalendarioDB;
import metalsoft.datos.exception.CalendarioException;
import metalsoft.datos.factory.DAOFactoryCreater;
import metalsoft.datos.idao.CalendarioDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.rrhh.Calendario;
import metalsoft.util.Fecha;

/**
 *
 * @author Nino
 */
public class AccessCalendario {

    public static CalendarioDB[] findByFechaMayorA(Date date,Connection cn){
        CalendarioDAO dao=new DAOFactoryCreater().getFactry().createCalendarioDAO();
        CalendarioDB[] db=null;
        Object[] param=new Object[0];
        String query="fecha>'"+Fecha.parseToDateSQL(date)+"' ORDER BY fecha";
        try {
            db=dao.findExecutingUserWhere(query, param, cn);
        } catch (CalendarioException ex) {
            Logger.getLogger(AccessPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }

    public static long insert(Calendario cal,Connection cn){
        CalendarioDAO dao=new DAOFactoryCreater().getFactry().createCalendarioDAO();
//        Object[] param=new Object[0];
//        String query="fecha>'"+Fecha.parseToDateSQL(date)+"' ORDER BY fecha";
        CalendarioDB db=null;
        long result=-1;
        try {
            db=Parser.parseToCalendarioDB(cal);
            result=dao.insert(db, cn);
        } catch (CalendarioException ex) {
            Logger.getLogger(AccessPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
