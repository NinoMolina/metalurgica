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
import metalsoft.datos.jpa.controller.BarrioJpaController;
import metalsoft.datos.jpa.entity.Cliente;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;
import metalsoft.datos.jpa.entity.Detallefactura;
import metalsoft.datos.jpa.entity.Detallepedido;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import metalsoft.datos.jpa.entity.Detalleproductopresupuesto;
import metalsoft.datos.jpa.entity.Detalleremito;
import metalsoft.datos.jpa.entity.Detalletrabajotercerizado;
import metalsoft.datos.jpa.entity.Ejecucionplanificacionproduccion;
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

    private static EntityManagerFactory emf;

    public static EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("MetalSoft_WebPU");
        }
//        try {
//            if (emf == null) {
////            return null;
//                Context ctx = new InitialContext();
//                emf = ((EntityManager) ctx.lookup("java:comp/env/persistence/MetalSoft_WebPU")).getEntityManagerFactory();
//            }
//        } catch (Exception e) {
//            emf = null;
//        }

//        if (emf == null) {
//            emf = new PersistenceProvider().createEntityManagerFactory("MetalSoft_WebPU", null);
//        }

        return (emf != null ? emf.createEntityManager() : null);
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("MetalSoft_WebPU");
        }
//        try {
//            if (emf == null) {
////            return null;
//                Context ctx = new InitialContext();
//                emf = ((EntityManager) ctx.lookup("java:comp/env/persistence/MetalSoft_WebPU")).getEntityManagerFactory();
//            }
//        } catch (Exception e) {
//            emf = null;
//        }
//        if (emf == null) {
//            emf = new PersistenceProvider().createEntityManagerFactory("MetalSoft_WebPU", null);
//        }
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

    public static List getDetallePedidoByPedido(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM detallePedido e"
                + " WHERE e.idpedido=" + id;
        try {
            Query q = em.createNativeQuery(sql, Detallepedido.class);
//        Query q = em.createQuery(sql, Pedido.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List getPedidosByCliente(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM Pedido e"
                + " WHERE e.cliente=" + id;
        try {
            Query q = em.createNativeQuery(sql, Pedido.class);
//        Query q = em.createQuery(sql, Pedido.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public static List getRolByUsuario(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "Select r.*"
                + " from rol r,	usuario u, usuarioxrol ur"
                + " WHERE u.idusuario=ur.idusuario"
                + " AND ur.idrol=r.idrol"
                + " AND u.idusuario=" + id;
        try {
            Query q = em.createNativeQuery(sql, Rol.class);
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public static Cliente getClienteByUsuario(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT c.*"
                + " FROM  usuario u, cliente c"
                + " WHERE u.idusuario=c.usuario"
                + " AND u.idusuario=" + id;
        try {
            Query q = em.createNativeQuery(sql, Cliente.class);
            return (Cliente) q.getSingleResult();
        } finally {
            em.close();
        }
    }
    public static String getNvoNumPedido() {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT *"
                + " FROM  nvonropedido";
        try {
            Query q = em.createNativeQuery(sql, String.class);
            return String.valueOf(q.getSingleResult());
        } finally {
            em.close();
        }
    }
}
