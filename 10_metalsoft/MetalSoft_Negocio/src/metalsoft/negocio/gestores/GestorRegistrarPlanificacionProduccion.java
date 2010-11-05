/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import controller.DetalleplanificacionproduccionJpaController;
import controller.EmpleadoJpaController;
import controller.MaquinaJpaController;
import controller.PedidoJpaController;
import controller.PlanificacionproduccionJpaController;
import controller.PresupuestoJpaController;
import controller.exceptions.PreexistingEntityException;
import dao.Dao;
import dao.DaoPedido;
import entity.Detalleplanificacionproduccion;
import entity.Estadoplanificacionproduccion;
import entity.Planificacionproduccion;
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
import metalsoft.util.Fecha;
import pojos.Estadopedido;
import pojos.Pedido;

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

    public entity.Presupuesto buscarPresupuesto(long idPresupuesto) {
//        JPA*********
        PresupuestoJpaController ctrl = new PresupuestoJpaController();
        return ctrl.findPresupuesto(idPresupuesto);


////        HIBERNATE*********
//        Dao<Presupuesto> dao=new DaoPresupuesto<Presupuesto>();
//        return dao.findById(idPresupuesto, Presupuesto.class.getSimpleName());
    }

    public List<entity.Empleado> obtenerEmpleados() {
        EmpleadoJpaController ctrl = new EmpleadoJpaController();
        return ctrl.findEmpleadoEntities();

//        Dao<Empleado> daoEmpleado=new DaoEmpleado<Empleado>();
//        return daoEmpleado.findAll(Empleado.class.getSimpleName());
    }

    public List<entity.Maquina> obtenerMaquinas() {
        MaquinaJpaController ctrl = new MaquinaJpaController();
        return ctrl.findMaquinaEntities();
//        Dao<Maquina> daoMaquina=new DaoMaquina<Maquina>();
//        return daoMaquina.findAll(Maquina.class.getSimpleName());
    }

    public entity.Pedido buscarPedido(long idpedido) {
        PedidoJpaController ctrl = new PedidoJpaController();
        return ctrl.findPedido(idpedido);
//        Dao<Pedido> daoPedido=new DaoPedido<Pedido>();
//        return daoPedido.findById(idpedido, Pedido.class.getSimpleName());
    }

    public boolean guardarPlanificacionProduccion(Planificacionproduccion planificacionproduccion,Set<Detalleplanificacionproduccion> setDetalle) {
        //SETEO A ESTADO RECURSOS ASIGNADOS
        planificacionproduccion.setIdestado(new Estadoplanificacionproduccion(1L));
        PlanificacionproduccionJpaController ctrlPlanificacion = new PlanificacionproduccionJpaController();
        DetalleplanificacionproduccionJpaController ctrlDetalle=new DetalleplanificacionproduccionJpaController();
        PedidoJpaController ctrlPedido=new PedidoJpaController();
        EntityManager em=ctrlDetalle.getEntityManager();
        boolean result=false;
        long nvoNroPlanifProd=-1;
        PostgreSQLManager pg=new PostgreSQLManager();
        try {
            em.getTransaction().begin();
            Connection cn=pg.concectGetCn();
            nvoNroPlanifProd=AccessFunctions.nvoNroPlanificacionProduccion(cn);
            planificacionproduccion.setNroplanificacion(nvoNroPlanifProd);
            ctrlPlanificacion.create(planificacionproduccion);
            for (Detalleplanificacionproduccion detalle : setDetalle){
                detalle.setIdplanificacionproduccion(planificacionproduccion);
                ctrlDetalle.create(detalle);
            }
//            planificacionproduccion.setDetalleplanificacionproduccionSet(setDetalle);
//            ctrlPlanificacion.edit(planificacionproduccion,em);
            entity.Pedido ped=planificacionproduccion.getPedido();
            ped.setEstado(new entity.Estadopedido(5L));
            ctrlPedido.edit(ped,em);
            em.getTransaction().commit();
            result=true;
        } catch (PreexistingEntityException ex) {
            result=false;
            Logger.getLogger(GestorRegistrarPlanificacionProduccion.class.getName()).log(Level.SEVERE, null, ex);
            em.getTransaction().rollback();
        } catch (Exception ex) {
            result=false;
            Logger.getLogger(GestorRegistrarPlanificacionProduccion.class.getName()).log(Level.SEVERE, null, ex);
            em.getTransaction().rollback();
        }
        finally{
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarPlanificacionProduccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
        //        Dao<Planificacionproduccion> daoPlanificacion=new DaoPlanificacionProduccion<Planificacionproduccion>();
        //        Dao<Detalleplanificacionproduccion> daoDetalle=new DaoDetallePlanificacionProduccion<Detalleplanificacionproduccion>();
        //        planificacionproduccion.setEstadoplanificacionproduccion(new Estadoplanificacionproduccion(1));
        //        daoPlanificacion.save(planificacionproduccion);
        //        Iterator<Detalleplanificacionproduccion> it=planificacionproduccion.getDetalleplanificacionproduccions().iterator();
        //        Detalleplanificacionproduccion detalle=null;
        //        while(it.hasNext()){
        //            detalle=it.next();
        //            detalle.setPlanificacionproduccion(planificacionproduccion);
        //            daoDetalle.save(detalle);
        //        }
        //falta insertar una nueva disponibilidad del empleado para las tareas
        //asignadas en la planificacion.
        //Tambien hay que ver las fechas de inicio y fin del detalle de planificacion


    }

    public void actualizarEstadoPedido(Pedido pedido) {
        Dao<Pedido> daoPedido = new DaoPedido<Pedido>();
        pedido.setEstadopedido(new Estadopedido(IdsEstadoPedido.PLANIFICADO));
        daoPedido.update(pedido);
    }

    public List<entity.Planificacionproduccion> buscarPlanificacionesProduccion() {
        PlanificacionproduccionJpaController ctrl = new PlanificacionproduccionJpaController();
        System.out.println(Fecha.fechaActualDate());
        return (List<entity.Planificacionproduccion>) ctrl.findByFechafinprevistaMayorActual(Fecha.fechaActualDate());
    }
}
