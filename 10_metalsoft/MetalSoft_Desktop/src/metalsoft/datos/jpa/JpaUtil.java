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
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import metalsoft.datos.jpa.entity.Detalletrabajotercerizado;
import metalsoft.datos.jpa.entity.Ejecucionplanificacionproduccion;
import metalsoft.datos.jpa.entity.Pedido;

/**
 *
 * @author Nino
 */
public class JpaUtil {

    public static EntityManager getEntityManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
        return emf.createEntityManager();
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
}
