/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos.jpa.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.entity.Detalletrabajotercerizado;
import metalsoft.datos.jpa.entity.Estadodetalletrabajotercerizado;
import metalsoft.datos.jpa.entity.Etapadeproduccion;
import metalsoft.datos.jpa.entity.Trabajotercerizado;

/**
 *
 * @author Nino
 */
public class DetalletrabajotercerizadoJpaController {

    public DetalletrabajotercerizadoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalletrabajotercerizado detalletrabajotercerizado) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadodetalletrabajotercerizado estado = detalletrabajotercerizado.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                detalletrabajotercerizado.setEstado(estado);
            }
            Etapadeproduccion proceso = detalletrabajotercerizado.getProceso();
            if (proceso != null) {
                proceso = em.getReference(proceso.getClass(), proceso.getIdetapaproduccion());
                detalletrabajotercerizado.setProceso(proceso);
            }
            Trabajotercerizado idtrabajotercerizado = detalletrabajotercerizado.getIdtrabajotercerizado();
            if (idtrabajotercerizado != null) {
                idtrabajotercerizado = em.getReference(idtrabajotercerizado.getClass(), idtrabajotercerizado.getIdtrabajo());
                detalletrabajotercerizado.setIdtrabajotercerizado(idtrabajotercerizado);
            }
            em.persist(detalletrabajotercerizado);
            if (estado != null) {
                estado.getDetalletrabajotercerizadoList().add(detalletrabajotercerizado);
                estado = em.merge(estado);
            }
            if (proceso != null) {
                proceso.getDetalletrabajotercerizadoList().add(detalletrabajotercerizado);
                proceso = em.merge(proceso);
            }
            if (idtrabajotercerizado != null) {
                idtrabajotercerizado.getDetalletrabajotercerizadoList().add(detalletrabajotercerizado);
                idtrabajotercerizado = em.merge(idtrabajotercerizado);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalletrabajotercerizado detalletrabajotercerizado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalletrabajotercerizado persistentDetalletrabajotercerizado = em.find(Detalletrabajotercerizado.class, detalletrabajotercerizado.getIddetalle());
            Estadodetalletrabajotercerizado estadoOld = persistentDetalletrabajotercerizado.getEstado();
            Estadodetalletrabajotercerizado estadoNew = detalletrabajotercerizado.getEstado();
            Etapadeproduccion procesoOld = persistentDetalletrabajotercerizado.getProceso();
            Etapadeproduccion procesoNew = detalletrabajotercerizado.getProceso();
            Trabajotercerizado idtrabajotercerizadoOld = persistentDetalletrabajotercerizado.getIdtrabajotercerizado();
            Trabajotercerizado idtrabajotercerizadoNew = detalletrabajotercerizado.getIdtrabajotercerizado();
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                detalletrabajotercerizado.setEstado(estadoNew);
            }
            if (procesoNew != null) {
                procesoNew = em.getReference(procesoNew.getClass(), procesoNew.getIdetapaproduccion());
                detalletrabajotercerizado.setProceso(procesoNew);
            }
            if (idtrabajotercerizadoNew != null) {
                idtrabajotercerizadoNew = em.getReference(idtrabajotercerizadoNew.getClass(), idtrabajotercerizadoNew.getIdtrabajo());
                detalletrabajotercerizado.setIdtrabajotercerizado(idtrabajotercerizadoNew);
            }
            detalletrabajotercerizado = em.merge(detalletrabajotercerizado);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getDetalletrabajotercerizadoList().remove(detalletrabajotercerizado);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getDetalletrabajotercerizadoList().add(detalletrabajotercerizado);
                estadoNew = em.merge(estadoNew);
            }
            if (procesoOld != null && !procesoOld.equals(procesoNew)) {
                procesoOld.getDetalletrabajotercerizadoList().remove(detalletrabajotercerizado);
                procesoOld = em.merge(procesoOld);
            }
            if (procesoNew != null && !procesoNew.equals(procesoOld)) {
                procesoNew.getDetalletrabajotercerizadoList().add(detalletrabajotercerizado);
                procesoNew = em.merge(procesoNew);
            }
            if (idtrabajotercerizadoOld != null && !idtrabajotercerizadoOld.equals(idtrabajotercerizadoNew)) {
                idtrabajotercerizadoOld.getDetalletrabajotercerizadoList().remove(detalletrabajotercerizado);
                idtrabajotercerizadoOld = em.merge(idtrabajotercerizadoOld);
            }
            if (idtrabajotercerizadoNew != null && !idtrabajotercerizadoNew.equals(idtrabajotercerizadoOld)) {
                idtrabajotercerizadoNew.getDetalletrabajotercerizadoList().add(detalletrabajotercerizado);
                idtrabajotercerizadoNew = em.merge(idtrabajotercerizadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detalletrabajotercerizado.getIddetalle();
                if (findDetalletrabajotercerizado(id) == null) {
                    throw new NonexistentEntityException("The detalletrabajotercerizado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalletrabajotercerizado detalletrabajotercerizado;
            try {
                detalletrabajotercerizado = em.getReference(Detalletrabajotercerizado.class, id);
                detalletrabajotercerizado.getIddetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalletrabajotercerizado with id " + id + " no longer exists.", enfe);
            }
            Estadodetalletrabajotercerizado estado = detalletrabajotercerizado.getEstado();
            if (estado != null) {
                estado.getDetalletrabajotercerizadoList().remove(detalletrabajotercerizado);
                estado = em.merge(estado);
            }
            Etapadeproduccion proceso = detalletrabajotercerizado.getProceso();
            if (proceso != null) {
                proceso.getDetalletrabajotercerizadoList().remove(detalletrabajotercerizado);
                proceso = em.merge(proceso);
            }
            Trabajotercerizado idtrabajotercerizado = detalletrabajotercerizado.getIdtrabajotercerizado();
            if (idtrabajotercerizado != null) {
                idtrabajotercerizado.getDetalletrabajotercerizadoList().remove(detalletrabajotercerizado);
                idtrabajotercerizado = em.merge(idtrabajotercerizado);
            }
            em.remove(detalletrabajotercerizado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalletrabajotercerizado> findDetalletrabajotercerizadoEntities() {
        return findDetalletrabajotercerizadoEntities(true, -1, -1);
    }

    public List<Detalletrabajotercerizado> findDetalletrabajotercerizadoEntities(int maxResults, int firstResult) {
        return findDetalletrabajotercerizadoEntities(false, maxResults, firstResult);
    }

    private List<Detalletrabajotercerizado> findDetalletrabajotercerizadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalletrabajotercerizado.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Detalletrabajotercerizado findDetalletrabajotercerizado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalletrabajotercerizado.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalletrabajotercerizadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalletrabajotercerizado> rt = cq.from(Detalletrabajotercerizado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
