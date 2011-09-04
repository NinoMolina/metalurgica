/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;
import metalsoft.datos.jpa.entity.Detallefactura;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import metalsoft.datos.jpa.entity.Detalleremito;
import metalsoft.datos.jpa.entity.Detalletrabajotercerizado;
import metalsoft.datos.jpa.entity.Ejecucionplanificacionproduccion;
import metalsoft.datos.jpa.entity.Factura;
import metalsoft.datos.jpa.entity.Pedido;
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
    
    public static void main(String arg[]){
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
        Query q = em.createQuery(sql, Ejecucionplanificacionproduccion.class);
        q.setParameter("id", idestado);
        return q.getResultList();
    }

    public static Detalleplanificacionproduccion getDetalleplanificacionproduccionPorIdDetalleejecucion(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT e FROM Detalleplanificacionproduccion e"
                + " WHERE e.iddetalleejecucionplanificacion.id = :id";
        Query q = em.createQuery(sql, Detalleplanificacionproduccion.class);
        q.setParameter("id", id);
        return (Detalleplanificacionproduccion) q.getSingleResult();
    }

    public static List getPedidosNoFinalizados() {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT e FROM Pedido e"
                + " WHERE e.estado.idestado not IN (10,12,15)";
        Query q = em.createQuery(sql, Pedido.class);
        return q.getResultList();
    }

    public static List getPedidosNoFinalizadosLIKE(String param) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM Pedido e"
                + " WHERE e.estado not IN (10,12,15) and CAST(e.nropedido as VARCHAR) LIKE '" + param + "%'";
        Query q = em.createNativeQuery(sql, Pedido.class);
//        Query q = em.createQuery(sql, Pedido.class);
        return q.getResultList();
    }

    public static Detalletrabajotercerizado getUltimoDetalleTrabajoTercerizado() {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM detalletrabajotercerizado e "
                + "WHERE e.iddetalle=(Select max(d.iddetalle)FROM detalletrabajotercerizado d)";
        Query q = em.createNativeQuery(sql, Detalletrabajotercerizado.class);
//        Query q = em.createQuery(sql, Pedido.class);
        return (Detalletrabajotercerizado) q.getSingleResult();
    }

    public static Detalleejecucionplanificacion getDetalleejecucionplanificacionByEjecucionetapa(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT e FROM Detalleejecucionplanificacion e"
                + " WHERE e.ejecucionetapa.id = :id";
        Query q = em.createQuery(sql, Detalleejecucionplanificacion.class);
        q.setParameter("id", id);
        return (Detalleejecucionplanificacion) q.getSingleResult();
    }
    public static List getTrabajosTercerizadosByEstado(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM trabajotercerizado"
                + " WHERE estado="+id;
        Query q = em.createNativeQuery(sql, Trabajotercerizado.class);
        return q.getResultList();
    }
    public static List getDetalleTrabajoTercerizadoByTrabajo(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM detalletrabajotercerizado"
                + " WHERE idtrabajotercerizado="+id;
        Query q = em.createNativeQuery(sql, Detalletrabajotercerizado.class);
        return q.getResultList();
    }
   public static List getTrabajosTercerizadosCanCancel() {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM trabajotercerizado"
                + " WHERE estado IN (1,2,3)";
        Query q = em.createNativeQuery(sql, Trabajotercerizado.class);
        return q.getResultList();
    }
   public static List getUsuarioXRolByUsuario(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM usuarioxrol"
                + " WHERE idusuario="+id;
        Query q = em.createNativeQuery(sql, Usuarioxrol.class);
        return q.getResultList();
    }
   public static List getRolesNoTieneUsuario(long id){
       EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT r.* FROM rol r"
                    +" WHERE r.idrol NOT IN (SELECT ur.idrol FROM usuarioxrol ur WHERE idusuario="+id+")";
        Query q = em.createNativeQuery(sql, Rol.class);
        return q.getResultList();
   }
   public static List getFacturasByNroLIKE(String param) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM Factura e"
                + " WHERE CAST(e.nrofactura as VARCHAR) LIKE '" + param + "%'";
        Query q = em.createNativeQuery(sql, Factura.class);
//        Query q = em.createQuery(sql, Pedido.class);
        return q.getResultList();
    }
   public static List getFacturasByFechaEmision(String param) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM Factura e"
                + " WHERE e.fechaemision='"+param+"'";
        Query q = em.createNativeQuery(sql, Factura.class);
//        Query q = em.createQuery(sql, Pedido.class);
        return q.getResultList();
    }
   public static List getFacturasByFechaVto(String param) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM Factura e"
                + " WHERE e.fechavencimiento='"+param+"'";
        Query q = em.createNativeQuery(sql, Factura.class);
//        Query q = em.createQuery(sql, Pedido.class);
        return q.getResultList();
    }
   public static List getDetalleFacturaByFactura(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM detallefactura e"
                + " WHERE e.idfactura="+id;
        Query q = em.createNativeQuery(sql, Detallefactura.class);
//        Query q = em.createQuery(sql, Pedido.class);
        return q.getResultList();
    }
   public static List getDetalleRemitoByRemito(long id) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM detalleremito e"
                + " WHERE e.idremito="+id;
        Query q = em.createNativeQuery(sql, Detalleremito.class);
//        Query q = em.createQuery(sql, Pedido.class);
        return q.getResultList();
    }
   public static List getRemitosByNroLIKE(String param) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM remito e"
                + " WHERE CAST(e.nroremito as VARCHAR) LIKE '" + param + "%'";
        Query q = em.createNativeQuery(sql, Remito.class);
//        Query q = em.createQuery(sql, Pedido.class);
        return q.getResultList();
    }
   public static List getRemitosByFechaEmision(String param) {
        EntityManager em = JpaUtil.getEntityManager();
        String sql = "SELECT * FROM remito e"
                + " WHERE e.fechaemision='"+param+"'";
        Query q = em.createNativeQuery(sql, Remito.class);
//        Query q = em.createQuery(sql, Pedido.class);
        return q.getResultList();
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
