/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import metalsoft.datos.dbobject.Ejecucionetapaproduccion;
import metalsoft.datos.jpa.controller.BarrioJpaController;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.Detallefactura;
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import metalsoft.datos.jpa.entity.Detalleproductopresupuesto;
import metalsoft.datos.jpa.entity.Detalleremito;
import metalsoft.datos.jpa.entity.Detalletrabajotercerizado;
import metalsoft.datos.jpa.entity.Disponibilidadhoraria;
import metalsoft.datos.jpa.entity.Ejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.Ejecucionplanificacionproduccion;
import metalsoft.datos.jpa.entity.Empleado;
import metalsoft.datos.jpa.entity.Factura;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.datos.jpa.entity.Planificacioncalidad;
import metalsoft.datos.jpa.entity.Planificacionproduccion;
import metalsoft.datos.jpa.entity.Remito;
import metalsoft.datos.jpa.entity.Rol;
import metalsoft.datos.jpa.entity.Trabajotercerizado;
import metalsoft.datos.jpa.entity.Usuarioxrol;

/**
 *
 * @author Nino
 */
public class JpaUtil {

    private static EntityManagerFactory emf = null;

    public static EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("MetalSoft_DesktopPU");
        }
        return emf.createEntityManager();
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("MetalSoft_DesktopPU");
        }
        return emf;
    }

    public static void main(String arg[]) {
        BarrioJpaController c = new BarrioJpaController(getEntityManagerFactory());
        c.findBarrio(1l);
    }

    public static List query(String query, Class clase) {
        EntityManager em = JpaUtil.getEntityManager();
        TypedQuery q = em.createQuery(query, clase);
        return q.getResultList();
    }

    public static List query(String query, String paramName, Object paramValue, Class clase) {
        EntityManager em = JpaUtil.getEntityManager();
        TypedQuery q = em.createQuery(query, clase);
        q.setParameter(paramName, paramValue);
        return q.getResultList();
    }

    public static Query getNamedQuery(String name) {
        return getEntityManager().createNamedQuery(name);
    }

    public static List getEjecucionplanificacionproduccionSegunEstado(long idestado) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT e FROM Ejecucionplanificacionproduccion e"
                + " WHERE e.estado.idestado = :id";
        try {
            Query q = em.createQuery(sql, Ejecucionplanificacionproduccion.class);
            q.setParameter("id", idestado);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static Detalleplanificacionproduccion getDetalleplanificacionproduccionPorIdDetalleejecucion(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT e FROM Detalleplanificacionproduccion e"
                + " WHERE e.iddetalleejecucionplanificacion.id = :id";
        Query q = em.createQuery(sql, Detalleplanificacionproduccion.class);
        try {
            q.setParameter("id", id);
            return (Detalleplanificacionproduccion) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public static List getPedidosNoFinalizados() {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT e FROM Pedido e"
                + " WHERE e.estado.idestado not IN (10,12,15)";
        try {
            Query q = em.createQuery(sql, Pedido.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List getPedidosNoFinalizadosLIKE(String param) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM Pedido e"
                + " WHERE e.estado not IN (10,12,15) and CAST(e.nropedido as VARCHAR) LIKE '" + param + "%'";
        try {
            Query q = em.createNativeQuery(sql, Pedido.class);
//        Query q = em.createQuery(sql, Pedido.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static Detalletrabajotercerizado getUltimoDetalleTrabajoTercerizado() {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM detalletrabajotercerizado e "
                + "WHERE e.iddetalle=(Select max(d.iddetalle)FROM detalletrabajotercerizado d)";
        try {
            Query q = em.createNativeQuery(sql, Detalletrabajotercerizado.class);
//        Query q = em.createQuery(sql, Pedido.class);
            return (Detalletrabajotercerizado) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public static Detalleejecucionplanificacion getDetalleejecucionplanificacionByEjecucionetapa(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT e FROM Detalleejecucionplanificacion e"
                + " WHERE e.ejecucionetapa.id = :id";
        try {
            Query q = em.createQuery(sql, Detalleejecucionplanificacion.class);
            q.setParameter("id", id);
            return (Detalleejecucionplanificacion) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public static List getTrabajosTercerizadosByEstado(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM trabajotercerizado"
                + " WHERE estado=" + id;
        try {
            Query q = em.createNativeQuery(sql, Trabajotercerizado.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List getDetalleTrabajoTercerizadoByTrabajo(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM detalletrabajotercerizado"
                + " WHERE idtrabajotercerizado=" + id;
        try {
            Query q = em.createNativeQuery(sql, Detalletrabajotercerizado.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List getTrabajosTercerizadosCanCancel() {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM trabajotercerizado"
                + " WHERE estado IN (1,2,3)";
        try {
            Query q = em.createNativeQuery(sql, Trabajotercerizado.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List getUsuarioXRolByUsuario(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM usuarioxrol"
                + " WHERE idusuario=" + id;
        try {
            Query q = em.createNativeQuery(sql, Usuarioxrol.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List getRolesNoTieneUsuario(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT r.* FROM rol r"
                + " WHERE r.idrol NOT IN (SELECT ur.idrol FROM usuarioxrol ur WHERE idusuario=" + id + ")";
        try {
            Query q = em.createNativeQuery(sql, Rol.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List getFacturasByNroLIKE(String param) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM Factura e"
                + " WHERE CAST(e.nrofactura as VARCHAR) LIKE '" + param + "%'";
        try {
            Query q = em.createNativeQuery(sql, Factura.class);
//        Query q = em.createQuery(sql, Pedido.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List getFacturasByFechaEmision(String param) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM Factura e"
                + " WHERE e.fechaemision='" + param + "'";
        try {
            Query q = em.createNativeQuery(sql, Factura.class);
//        Query q = em.createQuery(sql, Pedido.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List getFacturasByFechaVto(String param) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM Factura e"
                + " WHERE e.fechavencimiento='" + param + "'";
        try {
            Query q = em.createNativeQuery(sql, Factura.class);
//        Query q = em.createQuery(sql, Pedido.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List getDetalleFacturaByFactura(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM detallefactura e"
                + " WHERE e.idfactura=" + id;
        try {
            Query q = em.createNativeQuery(sql, Detallefactura.class);
//        Query q = em.createQuery(sql, Pedido.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List getDetalleRemitoByRemito(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM detalleremito e"
                + " WHERE e.idremito=" + id;
        try {
            Query q = em.createNativeQuery(sql, Detalleremito.class);
//        Query q = em.createQuery(sql, Pedido.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List getRemitosByNroLIKE(String param) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM remito e"
                + " WHERE CAST(e.nroremito as VARCHAR) LIKE '" + param + "%'";
        try {
            Query q = em.createNativeQuery(sql, Remito.class);
//        Query q = em.createQuery(sql, Pedido.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List getRemitosByFechaEmision(String param) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM remito e"
                + " WHERE e.fechaemision='" + param + "'";
        try {
            Query q = em.createNativeQuery(sql, Remito.class);
//        Query q = em.createQuery(sql, Pedido.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<Planificacionproduccion> findPlanificacionProduccionByFechafinprevistaMayorActual(Date fecha) {
        EntityManager em = getEntityManager();
        String query = "SELECT p FROM Planificacionproduccion p WHERE p.fechafinprevista > :fechaActual";
        try {
            Query q = em.createQuery(query, Planificacionproduccion.class);
            q.setParameter("fechaActual", fecha);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<Planificacioncalidad> findPlanificacionCalidadByFechafinprevistaMayorActual(Date fecha) {
        EntityManager em = getEntityManager();
        String query = "SELECT p FROM Planificacioncalidad p WHERE p.fechafinprevista > :fechaActual";
        try {
            Query q = em.createQuery(query, Planificacioncalidad.class);
            q.setParameter("fechaActual", fecha);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List getListadoMateriaPrimaAcomprar(String param) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT dpp.* FROM detalleproductopresupuesto dpp, detallepresupuesto dpre, detallepedido dped"
                + " WHERE dpp.iddetallepresupuesto=dpre.iddetalle and dpre.iddetallepedido=dped.iddetalle and dped.idpedido=" + param;
        try {
            Query q = em.createNativeQuery(sql, Detalleproductopresupuesto.class);
//        Query q = em.createQuery(sql, Pedido.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List getPedidosByNroLIKE(String param) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM Pedido e"
                + " WHERE CAST(e.nropedido as VARCHAR) LIKE '" + param + "%'";
        try {
            Query q = em.createNativeQuery(sql, Pedido.class);
//        Query q = em.createQuery(sql, Pedido.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List getPedidosSinArmar() {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT p.* FROM pedido p, planificacioncalidad pc, ejecucionplanificacioncalidad epc"
                + " WHERE pc.pedido = p.idpedido and epc.idplanificacioncalidad = pc.idplanificacion and p.estado = 7 and epc.estado = 4";
        try {
            Query q = em.createNativeQuery(sql, Pedido.class);
//        Query q = em.createQuery(sql, Pedido.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<Pedido> getPedidosSinArmarLIKE(String param) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT p.* FROM pedido p, planificacioncalidad pc, ejecucionplanificacioncalidad epc"
                + " WHERE pc.pedido = p.idpedido and epc.idplanificacioncalidad = pc.idplanificacion and p.estado = 7 and epc.estado = 4 and CAST(p.nropedido as VARCHAR) LIKE '" + param + "%'";
        try {
            Query q = em.createNativeQuery(sql, Pedido.class);
//        Query q = em.createQuery(sql, Pedido.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static Detalleejecucionplanificacioncalidad getDetalleejecucionplanificacioncalidadByEjecucionProcesoCalidad(Long idejecucion) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT e FROM Detalleejecucionplanificacioncalidad e"
                + " WHERE e.ejecucionprocesocalidad.idejecucion = :id";
        try {
            Query q = em.createQuery(sql, Detalleejecucionplanificacioncalidad.class);
            q.setParameter("id", idejecucion);
            return (Detalleejecucionplanificacioncalidad) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public static Detalleplanificacioncalidad getDetalleplanificacioncalidadPorIdDetalleejecucion(Long iddetalle) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT e FROM Detalleplanificacioncalidad e"
                + " WHERE e.iddetalleejecucionplanificacioncalidad.iddetalle = :id";
        Query q = em.createQuery(sql, Detalleplanificacioncalidad.class);
        try {
            q.setParameter("id", iddetalle);
            return (Detalleplanificacioncalidad) q.getSingleResult();
        } finally {
            em.close();
        }
    }

    public static List<Ejecucionplanificacioncalidad> getEjecucionplanificacioncalidadSegunEstado(long idestado) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT e FROM Ejecucionplanificacioncalidad e"
                + " WHERE e.estado.idestado = :id";
        try {
            Query q = em.createQuery(sql, Ejecucionplanificacioncalidad.class);
            q.setParameter("id", idestado);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List<Disponibilidadhoraria> getDisponibilidadEmpleado(Empleado empleado) {

        EntityManager em = JpaUtil.getEntityManager();
//        String sql = "SELECT e FROM Disponibilidadhoraria e"
//                + " WHERE e.idempleado.idempleado = :id"
//                + " AND e.fecha >= :fecha"
//                + " ORDER BY e.fecha, e.horainicio";
        String sql = "SELECT * FROM disponibilidadhoraria e"
                + " WHERE e.idempleado = " + empleado.getIdempleado()
                + " AND e.fecha >= CURRENT_DATE"
                + " ORDER BY e.fecha, e.horainicio";
        try {
            Query q = em.createNativeQuery(sql, Disponibilidadhoraria.class);
//            q.setParameter("id", empleado.getIdempleado());
//            q.setParameter("fecha", Fecha.fechaActualDate());
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public static List<metalsoft.datos.jpa.entity.Ejecucionetapaproduccion> getEjecucionetapaproduccionByEstado(Long estado){
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT e FROM Ejecucionetapaproduccion e"
                + " WHERE e.estado.idestado = :id";
        try {
            Query q = em.createQuery(sql, Ejecucionetapaproduccion.class);
            q.setParameter("id", estado);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}
