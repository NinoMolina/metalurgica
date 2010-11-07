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
import metalsoft.datos.dbobject.Detallempasignada;
import metalsoft.datos.dbobject.MateriaprimaDB;
import metalsoft.datos.dbobject.Planificacionproduccion;
import metalsoft.datos.dbobject.Tipomaterial;
import metalsoft.datos.dbobject.UnidadmedidaDB;
import metalsoft.negocio.access.AccessMateriaPrima;
import metalsoft.negocio.access.AccessPedido;
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

    public GestorPlanificacion() {
    }

    public Planificacionproduccion buscarPlanificacionPorPedido(long idPedido, Connection cn) {
        Planificacionproduccion db = new Planificacionproduccion();

        db = AccessPlanificacion.findByIdPedido(idPedido, cn);

        return db;
    }
    public Detallempasignada buscarDetalleMPAsisnada(long idMP, long idPlan, Connection cn) {
        Detallempasignada db = new Detallempasignada();
        db.setIdmateriaprima(idMP);
        db.setIdplanificacionproduccion(idPlan);

        db = AccessPlanificacion.findDetalleMPAsignada(db, cn);

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

    public long guardarDetalleAsignacionMP(long idPlan, long idMP, int cant, Connection cn) {
        long result = -1;
        result = AccessPlanificacion.insertDetalleMPAsignada(idPlan, idMP, cant, cn);

        return result;
    }
    public long modificarDetalleAsignacionMP(long id,long idPlan, long idMP, int cant, Connection cn) {
        long result = -1;
        Detallempasignada db=new Detallempasignada(id, idMP, cant, idPlan);
        result = AccessPlanificacion.updateDetalleMPAsignada(db, cn);

        return result;
    }

    public long guardarMPAsignadaXPieza(long idPieza, long idDetalleMP, Connection cn) {
        long result = -1;
        result = AccessPlanificacion.insertMPAsignadaXPiezaReal(idPieza, idDetalleMP, cn);

        return result;
    }

    public long mpPermitidaAAsignar(long idPedido, long idmp, Connection cn) {

        long result = -1;
        result = AccessViews.cantidadMPFaltaAsignar(idPedido, idmp, cn);

        return result;

    }
    public boolean mpEstaTodaAsignada(long idPedido, Connection cn) {

        boolean result = false;
        try {
            result = AccessViews.mpEstaTodaAsignada(idPedido, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return result;

    }
    public void setEstadoMateriaPrimaAsignada(long idPedido,Connection cn)
    {
        Planificacionproduccion idPlan=null;
        try {
            AccessPedido.update(idPedido, IdsEstadoPedido.CONMATERIAPRIMAASIGNADA, cn);
            idPlan=AccessPlanificacion.findByIdPedido(idPedido, cn);
            AccessPlanificacion.updateEstadoMPAsignada(idPlan.getIdplanificacionproduccion(), cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }
}
