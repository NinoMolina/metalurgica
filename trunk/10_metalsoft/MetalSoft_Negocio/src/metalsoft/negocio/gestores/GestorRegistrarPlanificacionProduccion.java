/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import com.sun.org.apache.bcel.internal.util.JavaWrapper;
import dao.Dao;
import dao.DaoDetallePlanificacionProduccion;
import dao.DaoEmpleado;
import dao.DaoMaquina;
import dao.DaoPedido;
import dao.DaoPlanificacionProduccion;
import dao.DaoPresupuesto;
import dao.HibernateUtil;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.negocio.access.AccessViews;
import metalsoft.util.Fecha;
import pojos.Detalleplanificacionproduccion;
import pojos.Empleado;
import pojos.Estadopedido;
import pojos.Estadoplanificacionproduccion;
import pojos.Maquina;
import pojos.Pedido;
import pojos.Planificacionproduccion;
import pojos.Presupuesto;

/**
 *
 * @author Nino
 */
public class GestorRegistrarPlanificacionProduccion {

    public GestorRegistrarPlanificacionProduccion() {
    }

    public LinkedList<ViewPedidoNoPlanificado> buscarPedidosNoPlanificados() {
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        LinkedList<ViewPedidoNoPlanificado> list=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.allPedidosNoPlanificados(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarPlanificacionProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarPlanificacionProduccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public Presupuesto buscarPresupuesto(long idPresupuesto) {
        Dao<Presupuesto> dao=new DaoPresupuesto<Presupuesto>();
        return dao.findById(idPresupuesto, Presupuesto.class.getSimpleName());
    }

    public List<Empleado> obtenerEmpleados() {
        Dao<Empleado> daoEmpleado=new DaoEmpleado<Empleado>();
        return daoEmpleado.findAll(Empleado.class.getSimpleName());
    }

    public List<Maquina> obtenerMaquinas() {
        Dao<Maquina> daoMaquina=new DaoMaquina<Maquina>();
        return daoMaquina.findAll(Maquina.class.getSimpleName());
    }

    public Pedido buscarPedido(long idpedido) {
        Dao<Pedido> daoPedido=new DaoPedido<Pedido>();
        return daoPedido.findById(idpedido, Pedido.class.getSimpleName());
    }

    public void guardarPlanificacionProduccion(Planificacionproduccion planificacionproduccion) {
        Dao<Planificacionproduccion> daoPlanificacion=new DaoPlanificacionProduccion<Planificacionproduccion>();
        Dao<Detalleplanificacionproduccion> daoDetalle=new DaoDetallePlanificacionProduccion<Detalleplanificacionproduccion>();
        planificacionproduccion.setFechacreacion(Fecha.fechaActualDate());
        planificacionproduccion.setEstadoplanificacionproduccion(new Estadoplanificacionproduccion(1));
        daoPlanificacion.save(planificacionproduccion);
        Iterator<Detalleplanificacionproduccion> it=planificacionproduccion.getDetalleplanificacionproduccions().iterator();
        Detalleplanificacionproduccion detalle=null;
        while(it.hasNext()){
            detalle=it.next();
            detalle.setPlanificacionproduccion(planificacionproduccion);
            daoDetalle.save(detalle);
        }
        //falta insertar una nueva disponibilidad del empleado para las tareas
        //asignadas en la planificacion.
        //Tambien hay que ver las fechas de inicio y fin del detalle de planificacion
        
    }

    public void limpiarSessionHibernate() {
        HibernateUtil.limpiarSession();
    }

    public void actualizarEstadoPedido(Pedido pedido) {
        Dao<Pedido> daoPedido=new DaoPedido<Pedido>();
        pedido.setEstadopedido(new Estadopedido(IdsEstadoPedido.PLANIFICADO));
        daoPedido.update(pedido);
    }

}
