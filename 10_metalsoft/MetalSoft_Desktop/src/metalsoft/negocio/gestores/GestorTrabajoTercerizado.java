/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.DetalletrabajotercerizadoJpaController;
import metalsoft.datos.jpa.controller.EmpresametalurgicaJpaController;
import metalsoft.datos.jpa.controller.EstadodetalletrabajotercerizadoJpaController;
import metalsoft.datos.jpa.controller.EstadotrabajotercerizadoJpaController;
import metalsoft.datos.jpa.controller.EtapadeproduccionJpaController;
import metalsoft.datos.jpa.controller.TrabajotercerizadoJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Detalletrabajotercerizado;
import metalsoft.datos.jpa.entity.DetalletrabajotercerizadoPK;
import metalsoft.datos.jpa.entity.Empresametalurgica;
import metalsoft.datos.jpa.entity.Estadodetalletrabajotercerizado;
import metalsoft.datos.jpa.entity.Estadotrabajotercerizado;
import metalsoft.datos.jpa.entity.Etapadeproduccion;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.access.AccessViews;

/**
 *
 * @author Vicky
 */
public class GestorTrabajoTercerizado {

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public List<Pedido> buscarPedidosNoFinalizados() {
        return JpaUtil.getPedidosNoFinalizados();
    }

    public List<Pedido> buscarPedidosNoFinalizadosLIKE(String param) {
        return JpaUtil.getPedidosNoFinalizadosLIKE(param);
    }

    public LinkedList<ViewDetallePedidoCotizacion> buscarDetallePedido(long idPed) {
        PostgreSQLManager pg = new PostgreSQLManager();
        LinkedList<ViewDetallePedidoCotizacion> list = null;
        ViewDetallePedidoCotizacion v = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            list = AccessViews.listDetallePedidoCotizacion(idPed, cn);
            Iterator<ViewDetallePedidoCotizacion> iter = list.iterator();
            while (iter.hasNext()) {
                v = iter.next();
                int cantPiezas = AccessFunctions.cantPiezasXProducto(v.getIdProducto(), cn);
                v.setCantidadPiezas(cantPiezas);
            }
        } catch (Exception ex) {
            Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public LinkedList<ViewDetalleProducto> buscarDetalleProducto(long idPro) {
        PostgreSQLManager pg = new PostgreSQLManager();
        LinkedList<ViewDetalleProducto> list = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            list = AccessViews.listDetalleProducto(idPro, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public long generarNumeroPedido() {
        long result = -1;
        PostgreSQLManager pg = null;
        Connection cn = null;
        pg = new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
            result = AccessFunctions.nvoNroTrabajoTercerizado(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorTrabajoTercerizado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorTrabajoTercerizado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public Etapadeproduccion buscarEtapa(long id) {
        EtapadeproduccionJpaController controller = new EtapadeproduccionJpaController();
        return controller.findEtapadeproduccion(id);
    }

    public Estadotrabajotercerizado buscarEstadoGenerado() {
        EstadotrabajotercerizadoJpaController controller = new EstadotrabajotercerizadoJpaController();
        return controller.findEstadotrabajotercerizado((long) 1);
    }

    public Estadodetalletrabajotercerizado buscarEstadoGeneradoDetalle() {
        EstadodetalletrabajotercerizadoJpaController controller = new EstadodetalletrabajotercerizadoJpaController();
        return controller.findEstadodetalletrabajotercerizado((long) 1);
    }


    public boolean nuevoPedido(metalsoft.datos.jpa.entity.Trabajotercerizado trabajo, LinkedList<ViewPedidoCotizacion> detalle) {
        TrabajotercerizadoJpaController controller = new TrabajotercerizadoJpaController();
        DetalletrabajotercerizadoJpaController controllerDetalle = new DetalletrabajotercerizadoJpaController();
        EntityManager em = null;
        try {
            emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
            em = getEntityManager();
            em.getTransaction().begin();
            
            trabajo.setEstado(buscarEstadoGenerado());
            controller.create(trabajo);
            
            long c=JpaUtil.getUltimoDetalleTrabajoTercerizado().getDetalletrabajotercerizadoPK().getIddetalle();
            for (ViewPedidoCotizacion v : detalle) {
                c=c+1;
                DetalletrabajotercerizadoPK pk=new DetalletrabajotercerizadoPK(c,99);
                Detalletrabajotercerizado de = new Detalletrabajotercerizado(pk);
                de.setCantidad(v.getCantidad());
                de.setPieza(BigInteger.valueOf(v.getIdPieza()));
                de.setProceso(buscarEtapa(v.getIdetapa()));
                de.setEstado(buscarEstadoGeneradoDetalle());
                de.setTrabajotercerizado(trabajo);
                controllerDetalle.create(de);
            }
            em.getTransaction().commit();

        } catch (PreexistingEntityException ex) {
            em.getTransaction().rollback();
            Logger.getLogger(GestorTrabajoTercerizado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            em.getTransaction().rollback();
            Logger.getLogger(GestorTrabajoTercerizado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public Empresametalurgica buscarEmpresa(long id) {
        EmpresametalurgicaJpaController controller = new EmpresametalurgicaJpaController();
        return controller.findEmpresametalurgica(id);
    }
}
