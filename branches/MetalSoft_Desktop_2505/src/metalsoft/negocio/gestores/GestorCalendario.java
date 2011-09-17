/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.CalendarioDB;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.CalendarioJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Calendario;
import metalsoft.negocio.access.AccessCalendario;
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

        CalendarioJpaController controller=new CalendarioJpaController(JpaUtil.getEntityManagerFactory());
        try {
            controller.create(cal);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(GestorCalendario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GestorCalendario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cal.getId();
    }
}
