/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.PresupuestoJpaController;
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;
import metalsoft.datos.jpa.entity.Planificacioncalidad;
import metalsoft.datos.jpa.entity.Presupuesto;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.access.AccessViews;

/**
 *
 * @author Nino
 */
public class GestorRegistrarPlanificacionCalidad {

    public LinkedList<ViewPedidoConPlanificacionProduccion> buscarPedidosConPlanificacionProduccion() {
        PostgreSQLManager pg = new PostgreSQLManager();
        Connection cn = null;
        LinkedList<ViewPedidoConPlanificacionProduccion> list = null;
        try {
            cn = pg.concectGetCn();
            list = AccessViews.allPedidosConPlanificacionProduccion(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarPlanificacionProduccion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarPlanificacionProduccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public boolean guardarPlanificacionCalidad(Planificacioncalidad plan, List<Detalleplanificacioncalidad> detalle) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Presupuesto buscarPresupuesto(long idPresupuesto) {
        PresupuestoJpaController ctrl = new PresupuestoJpaController(JpaUtil.getEntityManagerFactory());
        return ctrl.findPresupuesto(idPresupuesto);
    }

    public Date obtenerHoraFinPrevista(Long idPlanificacionProduccion) {
        Date horaFinPrevista = null;
        horaFinPrevista = AccessFunctions.calcularHoraFinPrevistaPlanificacionProduccion(idPlanificacionProduccion);
        return horaFinPrevista;
    }

}
