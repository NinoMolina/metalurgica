/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.MateriaprimaDB;
import metalsoft.datos.dbobject.Planificacionproduccion;
import metalsoft.datos.dbobject.Tipomaterial;
import metalsoft.datos.dbobject.UnidadmedidaDB;
import metalsoft.negocio.access.AccessMateriaPrima;
import metalsoft.negocio.access.AccessPlanificacion;
import metalsoft.negocio.access.AccessTipoMaterial;
import metalsoft.negocio.access.AccessUnidadDeMedida;
import metalsoft.negocio.access.AccessViews;
import metalsoft.util.Combo;
import metalsoft.util.ItemCombo;

/**
 *
 * @author Vicky
 */
public class GestorPlanificacion {

    public GestorPlanificacion(){

    }
    public Planificacionproduccion buscarPlanificacionPorPedido(long idPedido) {
        PostgreSQLManager pg = new PostgreSQLManager();
        Planificacionproduccion db=new Planificacionproduccion();
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            db = AccessPlanificacion.findByIdPedido(idPedido, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return db;
    }
    public LinkedList<ViewPlanificacion> buscarPlanificacionConRecursosAsignados() {
        PostgreSQLManager pg = new PostgreSQLManager();
        LinkedList<ViewPlanificacion> list = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            list = AccessViews.planificacionConRecursosAsignados(cn);
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
    public long guardarDetalleAsignacionMP(long idPlan,long idMP, int cant)
    {
        PostgreSQLManager pg=null;
        Connection cn=null;

        pg=new PostgreSQLManager();
        long result=-1;

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result=AccessPlanificacion.insertDetalleMPAsignada(idPlan, idMP, cant, cn);
            cn.commit();
        } catch (Exception ex) {
            Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    public long guardarMPAsignadaXPieza(long idPieza,long idDetalleMP)
    {
        PostgreSQLManager pg=null;
        Connection cn=null;

        pg=new PostgreSQLManager();
        long result=-1;

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result=AccessPlanificacion.insertMPAsignadaXPiezaReal(idPieza, idDetalleMP, cn);
            cn.commit();
        } catch (Exception ex) {
            Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    public long mpPermitidaAAsignar(long idPedido,long idmp)
    {
        PostgreSQLManager pg=null;
        Connection cn=null;

        pg=new PostgreSQLManager();
        long result=-1;

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result=AccessViews.cantidadMPFaltaAsignar(idPedido, idmp, cn);
            cn.commit();
        } catch (Exception ex) {
            Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;

    }

}
