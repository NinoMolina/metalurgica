/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.negocio.access.AccessEjecucionPlanificacionProduccion;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.access.AccessViews;
import metalsoft.negocio.produccion.EjecucionPlanificacionProduccion;
import metalsoft.util.Fecha;

/**
 *
 * @author Nino
 */
public class GestorRegistrarLanzamientoProduccion {

    public LinkedList<ViewPedidosConMPAsignada> buscarPedidosConMPAsignada() {
        PostgreSQLManager pg = new PostgreSQLManager();
        LinkedList<ViewPedidosConMPAsignada> list = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            list = AccessViews.listPedidosConMPAsignada(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public Date calcularFechaFin(Date fechaActual, Date fechaInicioPrevista, Date fechafinprevista) {
        GregorianCalendar inicio = (GregorianCalendar) Fecha.parseToCalendar(fechaInicioPrevista);
//        System.out.println(Fecha.parseToString(inicio.getTime()));
        GregorianCalendar fin = (GregorianCalendar) Fecha.parseToCalendar(fechafinprevista);
//        System.out.println(Fecha.parseToString(fin.getTime()));
        int year = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
        int month = fin.get(Calendar.MONTH) - inicio.get(Calendar.MONTH);
        int day = fin.get(Calendar.DATE) - inicio.get(Calendar.DATE);
        inicio.add(Calendar.YEAR, year);
        GregorianCalendar actual = (GregorianCalendar) Fecha.parseToCalendar(fechaActual);
//        System.out.println(Fecha.parseToString(actual.getTime()));
        actual.add(Calendar.YEAR, year);
        actual.add(Calendar.MONTH, month);
        actual.add(Calendar.DATE, day);
//        System.out.println(Fecha.parseToString(actual.getTime()));
        return actual.getTime();
    }

    public long generarNvoNroEjecucionPlanificacionProduccion() {
        PostgreSQLManager pg = new PostgreSQLManager();
        Connection cn = null;
        long result = -1;
        try {
            cn = pg.concectGetCn();
            result = AccessFunctions.nvoNroEjecucionPlanificacionProduccion(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarLanzamientoProduccion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarLanzamientoProduccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public long guardarEjecucionPlanificacion(EjecucionPlanificacionProduccion ejecucion, long idPlanificacionProduccion) {
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        long result=-1;
        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result=AccessEjecucionPlanificacionProduccion.insert(ejecucion,1,idPlanificacionProduccion,cn);
            cn.commit();
        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarLanzamientoProduccion.class.getName()).log(Level.SEVERE, null, ex);
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorRegistrarLanzamientoProduccion.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }finally{
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarLanzamientoProduccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
