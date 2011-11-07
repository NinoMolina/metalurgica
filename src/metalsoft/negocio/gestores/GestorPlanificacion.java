/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import metalsoft.negocio.gestores.estados.IdsEstadoPedido;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.DetallempasignadaDB;
import metalsoft.datos.dbobject.PlanificacionproduccionDB;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.access.AccessPedido;
import metalsoft.negocio.access.AccessPlanificacion;
import metalsoft.negocio.access.AccessViews;

/**
 *
 * @author Vicky
 */
public class GestorPlanificacion {

    public GestorPlanificacion() {
    }

    public PlanificacionproduccionDB buscarPlanificacionPorPedido(long idPedido, Connection cn) {
        PlanificacionproduccionDB db = new PlanificacionproduccionDB();

        db = AccessPlanificacion.findByIdPedido(idPedido, cn);

        return db;
    }
    public DetallempasignadaDB buscarDetalleMPAsisnada(long idMP, long idPlan, Connection cn) {
        DetallempasignadaDB db = new DetallempasignadaDB();
        db.setIdmateriaprima(idMP);
        db.setIdplanificacionproduccion(idPlan);

        db = AccessPlanificacion.findDetalleMPAsignada(db, cn);

        return db;
    }

    public LinkedList<ViewPlanificacion> buscarPlanificacionConRecursosAsignados(Connection cn) {
        LinkedList<ViewPlanificacion> list = null;
        PostgreSQLManager pg = null;
        boolean flag=false;
        if(cn==null){
            pg=new PostgreSQLManager();
            flag=true;
        }
        try {
            if(flag)
                cn = pg.concectGetCn();
            list = AccessViews.planificacionConRecursosAsignados(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if(flag)
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
        DetallempasignadaDB db=new DetallempasignadaDB(id, idMP, cant, idPlan);
        result = AccessPlanificacion.updateDetalleMPAsignada(db, cn);

        return result;
    }

    public long guardarMPAsignadaXPieza(long idPieza, long idDetalleMP, Connection cn) {
        long result = -1;
        result = AccessPlanificacion.insertMPAsignadaXPiezaReal(idPieza, idDetalleMP, cn);

        return result;
    }

    public long mpPermitidaAAsignar(long idPedido, long idmp, Connection cn) {

        int cantRequerida = 0;
        int cantAsignada = 0;
        
        cantRequerida = AccessFunctions.cantidadmpenpedido(idPedido,idmp, cn);
        cantAsignada = AccessFunctions.cantidadmpasignada(idPedido, idmp, cn);
        
        return cantRequerida - cantAsignada;

    }
    public boolean mpEstaTodaAsignada(long idPedido, Connection cn) {

        int cantidadmpenpedido = -1;
        int cantidadmpasignada = -2;
        
        boolean result = false;
        
        try {
            cantidadmpenpedido = AccessFunctions.cantidadmpenpedido(idPedido, cn);
            cantidadmpasignada = AccessFunctions.cantidadmpasignada(idPedido, cn);
            
            if(cantidadmpenpedido == cantidadmpasignada){
                result = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return result;

    }
    public void setEstadoMateriaPrimaAsignada(long idPedido,Connection cn)
    {
        PlanificacionproduccionDB idPlan=null;
        try {
            AccessPedido.update(idPedido, IdsEstadoPedido.CONMATERIAPRIMAASIGNADA, cn);
            idPlan=AccessPlanificacion.findByIdPedido(idPedido, cn);
            AccessPlanificacion.updateEstadoMPAsignada(idPlan.getIdplanificacionproduccion(), cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }
}
