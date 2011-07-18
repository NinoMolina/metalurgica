/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.math.BigInteger;
import metalsoft.datos.jpa.controller.DetalleplanificacionproduccionJpaController;
import metalsoft.datos.jpa.controller.EmpleadoJpaController;
import metalsoft.datos.jpa.controller.MaquinaJpaController;
import metalsoft.datos.jpa.controller.PedidoJpaController;
import metalsoft.datos.jpa.controller.PlanificacionproduccionJpaController;
import metalsoft.datos.jpa.controller.PresupuestoJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;

import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import metalsoft.datos.jpa.entity.Estadoplanificacionproduccion;
import metalsoft.datos.jpa.entity.Planificacionproduccion;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.access.AccessViews;
import metalsoft.negocio.gestores.estados.IdsEstadoPlanificacionProduccion;
import metalsoft.util.Fecha;

/**
 *
 * @author Nino
 */
public class GestorRegistrarPlanificacionProduccion {

    public GestorRegistrarPlanificacionProduccion() {
    }

    public LinkedList<ViewPedidoNoPlanificado> buscarPedidosNoPlanificados() {
        PostgreSQLManager pg = new PostgreSQLManager();
        Connection cn = null;
        LinkedList<ViewPedidoNoPlanificado> list = null;
        try {
            cn = pg.concectGetCn();
            list = AccessViews.allPedidosNoPlanificados(cn);
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

    public metalsoft.datos.jpa.entity.Presupuesto buscarPresupuesto(long idPresupuesto) {
//        JPA*********
        PresupuestoJpaController ctrl = new PresupuestoJpaController();
        return ctrl.findPresupuesto(idPresupuesto);


////        HIBERNATE*********
//        Dao<Presupuesto> dao=new DaoPresupuesto<Presupuesto>();
//        return dao.findById(idPresupuesto, Presupuesto.class.getSimpleName());
    }

    public List<metalsoft.datos.jpa.entity.Empleado> obtenerEmpleados() {
        EmpleadoJpaController ctrl = new EmpleadoJpaController();
        return ctrl.findEmpleadoEntities();

//        Dao<Empleado> daoEmpleado=new DaoEmpleado<Empleado>();
//        return daoEmpleado.findAll(Empleado.class.getSimpleName());
    }

    public List<metalsoft.datos.jpa.entity.Maquina> obtenerMaquinas() {
        MaquinaJpaController ctrl = new MaquinaJpaController();
        return ctrl.findMaquinaEntities();
//        Dao<Maquina> daoMaquina=new DaoMaquina<Maquina>();
//        return daoMaquina.findAll(Maquina.class.getSimpleName());
    }

    public metalsoft.datos.jpa.entity.Pedido buscarPedido(long idpedido) {
        PedidoJpaController ctrl = new PedidoJpaController();
        return ctrl.findPedido(idpedido);
//        Dao<Pedido> daoPedido=new DaoPedido<Pedido>();
//        return daoPedido.findById(idpedido, Pedido.class.getSimpleName());
    }

    public boolean guardarPlanificacionProduccion(Planificacionproduccion planificacionproduccion, List<Detalleplanificacionproduccion> lstDetalle) {
        //SETEO A ESTADO RECURSOS ASIGNADOS
        planificacionproduccion.setIdestado(new Estadoplanificacionproduccion(IdsEstadoPlanificacionProduccion.REC_ASIG));
        PlanificacionproduccionJpaController ctrlPlanificacion = new PlanificacionproduccionJpaController();
        DetalleplanificacionproduccionJpaController ctrlDetalle = new DetalleplanificacionproduccionJpaController();
        PedidoJpaController ctrlPedido = new PedidoJpaController();
        EntityManager em = ctrlDetalle.getEntityManager();
        boolean result = false;
        long nvoNroPlanifProd = -1;
        PostgreSQLManager pg = new PostgreSQLManager();
        try {
            em.getTransaction().begin();
            Connection cn = pg.concectGetCn();
            nvoNroPlanifProd = AccessFunctions.nvoNroPlanificacionProduccion(cn);
            planificacionproduccion.setNroplanificacion(BigInteger.valueOf(nvoNroPlanifProd));
            ctrlPlanificacion.create(planificacionproduccion);
            for (Detalleplanificacionproduccion detalle : lstDetalle) {
                detalle.setIdplanificacionproduccion(planificacionproduccion);
                ctrlDetalle.create(detalle);
            }
//            planificacionproduccion.setDetalleplanificacionproduccionSet(setDetalle);
//            ctrlPlanificacion.edit(planificacionproduccion,em);
            metalsoft.datos.jpa.entity.Pedido ped = planificacionproduccion.getPedido();
            ped.setEstado(new metalsoft.datos.jpa.entity.Estadopedido(5L));
            ctrlPedido.edit(ped, em);
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

    public List<metalsoft.datos.jpa.entity.Planificacionproduccion> buscarPlanificacionesProduccion() {
        PlanificacionproduccionJpaController ctrl = new PlanificacionproduccionJpaController();
        System.out.println(Fecha.fechaActualDate());
        return (List<metalsoft.datos.jpa.entity.Planificacionproduccion>) ctrl.findByFechafinprevistaMayorActual(Fecha.fechaActualDate());
    }
}
