/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.DetalleplanificacioncalidadJpaController;
import metalsoft.datos.jpa.controller.EmpleadoJpaController;
import metalsoft.datos.jpa.controller.MaquinaJpaController;
import metalsoft.datos.jpa.controller.PedidoJpaController;
import metalsoft.datos.jpa.controller.PlanificacioncalidadJpaController;
import metalsoft.datos.jpa.controller.PresupuestoJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;
import metalsoft.datos.jpa.entity.Planificacioncalidad;
import metalsoft.datos.jpa.entity.Planificacionproduccion;
import metalsoft.datos.jpa.entity.Presupuesto;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.access.AccessViews;
import metalsoft.negocio.gestores.estados.IdsEstadoPedido;
import metalsoft.util.Fecha;

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

    public boolean guardarPlanificacionCalidad(Planificacioncalidad planificacionCalidad, List<Detalleplanificacioncalidad> lstDetalle) {
        
        PlanificacioncalidadJpaController ctrlPlanificacion = new PlanificacioncalidadJpaController(JpaUtil.getEntityManagerFactory());
        DetalleplanificacioncalidadJpaController ctrlDetalle = new DetalleplanificacioncalidadJpaController(JpaUtil.getEntityManagerFactory());
        PedidoJpaController ctrlPedido = new PedidoJpaController(JpaUtil.getEntityManagerFactory());
        EntityManager em = JpaUtil.getEntityManager();
        boolean result = false;
        long nvoNroPlanifProd = -1;
        PostgreSQLManager pg = new PostgreSQLManager();
        try {
            em.getTransaction().begin();
            Connection cn = pg.concectGetCn();
            nvoNroPlanifProd = AccessFunctions.nvoNroPlanificacionCalidad(cn);
            planificacionCalidad.setNroplanificacion(BigInteger.valueOf(nvoNroPlanifProd));
            ctrlPlanificacion.create(planificacionCalidad);
            for (Detalleplanificacioncalidad detalle : lstDetalle) {
                detalle.setIdplanificacioncalidad(planificacionCalidad);
                ctrlDetalle.create(detalle);
            }
            metalsoft.datos.jpa.entity.Pedido ped = planificacionCalidad.getPedido();
            ped.setEstado(new metalsoft.datos.jpa.entity.Estadopedido(IdsEstadoPedido.PLANIFICADO_CALIDAD));
            ctrlPedido.edit(ped);
            em.getTransaction().commit();
            result = true;
        } catch (PreexistingEntityException ex) {
            result = false;
            Logger.getLogger(GestorRegistrarPlanificacionProduccion.class.getName()).log(Level.SEVERE, null, ex);
            em.getTransaction().rollback();
        } catch (Exception ex) {
            result = false;
            Logger.getLogger(GestorRegistrarPlanificacionProduccion.class.getName()).log(Level.SEVERE, null, ex);
            em.getTransaction().rollback();
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarPlanificacionProduccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;

        //falta insertar una nueva disponibilidad del empleado para las tareas
        //asignadas en la planificacion.
        //Tambien hay que ver las fechas de inicio y fin del detalle de planificacion
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

    public List<metalsoft.datos.jpa.entity.Empleado> obtenerEmpleados() {
        EmpleadoJpaController ctrl = new EmpleadoJpaController(JpaUtil.getEntityManagerFactory());
        return ctrl.findEmpleadoEntities();
    }

    public List<metalsoft.datos.jpa.entity.Maquina> obtenerMaquinas() {
        MaquinaJpaController ctrl = new MaquinaJpaController(JpaUtil.getEntityManagerFactory());
        return ctrl.findMaquinaEntities();
    }

    public List<Planificacioncalidad> buscarPlanificacionesCalidad() {
        return (List<metalsoft.datos.jpa.entity.Planificacioncalidad>) JpaUtil.findPlanificacionCalidadByFechafinprevistaMayorActual(Fecha.fechaActualDate());
    }

}
