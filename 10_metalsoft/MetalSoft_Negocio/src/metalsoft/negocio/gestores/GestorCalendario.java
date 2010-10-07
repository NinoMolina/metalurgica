/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.CalendarioDB;
import metalsoft.negocio.access.AccessCalendario;
import metalsoft.negocio.rrhh.Calendario;
import metalsoft.util.Fecha;

/**
 *
 * @author Nino
 */
public class GestorCalendario {

    public HashMap<GregorianCalendar, CalendarioDB> buscarDiasMayoresActual(Date date) {
        PostgreSQLManager pg = new PostgreSQLManager();
        Connection cn = null;
        CalendarioDB[] vDB = null;
        HashMap<GregorianCalendar, CalendarioDB> hash = new HashMap<GregorianCalendar, CalendarioDB>();
        try {
            cn = pg.concectGetCn();
            vDB = AccessCalendario.findByFechaMayorA(date, cn);
            for (int i = 0; i < vDB.length; i++) {
                CalendarioDB calendarioDB = vDB[i];
                hash.put((GregorianCalendar) Fecha.parseToCalendar(calendarioDB.getFecha()), calendarioDB);
            }
        } catch (Exception ex) {
            Logger.getLogger(GestorCalendario.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (hash.isEmpty()) {
            return null;
        }
        return hash;
    }

    public long guardarDiaNoLaboral(Calendario cal) {
        //HACER que no ingrese una fecha ya existente en la bd
        //poner not null en el campo fecha en la bd
        //o buscar la fecha a guardar para ver si ya existe
        PostgreSQLManager pg = new PostgreSQLManager();
        Connection cn = null;
        long result = -1;
        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result = AccessCalendario.insert(cal, cn);
            cn.commit();
        } catch (Exception ex) {
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorCalendario.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(GestorCalendario.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCalendario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
