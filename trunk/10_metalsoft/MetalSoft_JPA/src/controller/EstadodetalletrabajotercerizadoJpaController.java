/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Estadodetalletrabajotercerizado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Detalletrabajotercerizado;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class EstadodetalletrabajotercerizadoJpaController {

    public EstadodetalletrabajotercerizadoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadodetalletrabajotercerizado estadodetalletrabajotercerizado) throws PreexistingEntityException, Exception {
        if (estadodetalletrabajotercerizado.getDetalletrabajotercerizadoSet() == null) {
            estadodetalletrabajotercerizado.setDetalletrabajotercerizadoSet(new HashSet<Detalletrabajotercerizado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoSet = new HashSet<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach : estadodetalletrabajotercerizado.getDetalletrabajotercerizadoSet()) {
                detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach.getDetalletrabajotercerizadoPK());
                attachedDetalletrabajotercerizadoSet.add(detalletrabajotercerizadoSetDetalletrabajotercerizadoToAttach);
            }
            estadodetalletrabajotercerizado.setDetalletrabajotercerizadoSet(attachedDetalletrabajotercerizadoSet);
            em.persist(estadodetalletrabajotercerizado);
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetDetalletrabajotercerizado : estadodetalletrabajotercerizado.getDetalletrabajotercerizadoSet()) {
                Estadodetalletrabajotercerizado oldEstadoOfDetalletrabajotercerizadoSetDetalletrabajotercerizado = detalletrabajotercerizadoSetDetalletrabajotercerizado.getEstado();
                detalletrabajotercerizadoSetDetalletrabajotercerizado.setEstado(estadodetalletrabajotercerizado);
                detalletrabajotercerizadoSetDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoSetDetalletrabajotercerizado);
                if (oldEstadoOfDetalletrabajotercerizadoSetDetalletrabajotercerizado != null) {
                    oldEstadoOfDetalletrabajotercerizadoSetDetalletrabajotercerizado.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizadoSetDetalletrabajotercerizado);
                    oldEstadoOfDetalletrabajotercerizadoSetDetalletrabajotercerizado = em.merge(oldEstadoOfDetalletrabajotercerizadoSetDetalletrabajotercerizado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadodetalletrabajotercerizado(estadodetalletrabajotercerizado.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadodetalletrabajotercerizado " + estadodetalletrabajotercerizado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadodetalletrabajotercerizado estadodetalletrabajotercerizado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadodetalletrabajotercerizado persistentEstadodetalletrabajotercerizado = em.find(Estadodetalletrabajotercerizado.class, estadodetalletrabajotercerizado.getIdestado());
            Set<Detalletrabajotercerizado> detalletrabajotercerizadoSetOld = persistentEstadodetalletrabajotercerizado.getDetalletrabajotercerizadoSet();
            Set<Detalletrabajotercerizado> detalletrabajotercerizadoSetNew = estadodetalletrabajotercerizado.getDetalletrabajotercerizadoSet();
            Set<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoSetNew = new HashSet<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach : detalletrabajotercerizadoSetNew) {
                detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach.getDetalletrabajotercerizadoPK());
                attachedDetalletrabajotercerizadoSetNew.add(detalletrabajotercerizadoSetNewDetalletrabajotercerizadoToAttach);
            }
            detalletrabajotercerizadoSetNew = attachedDetalletrabajotercerizadoSetNew;
            estadodetalletrabajotercerizado.setDetalletrabajotercerizadoSet(detalletrabajotercerizadoSetNew);
            estadodetalletrabajotercerizado = em.merge(estadodetalletrabajotercerizado);
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetOldDetalletrabajotercerizado : detalletrabajotercerizadoSetOld) {
                if (!detalletrabajotercerizadoSetNew.contains(detalletrabajotercerizadoSetOldDetalletrabajotercerizado)) {
                    detalletrabajotercerizadoSetOldDetalletrabajotercerizado.setEstado(null);
                    detalletrabajotercerizadoSetOldDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoSetOldDetalletrabajotercerizado);
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetNewDetalletrabajotercerizado : detalletrabajotercerizadoSetNew) {
                if (!detalletrabajotercerizadoSetOld.contains(detalletrabajotercerizadoSetNewDetalletrabajotercerizado)) {
                    Estadodetalletrabajotercerizado oldEstadoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado = detalletrabajotercerizadoSetNewDetalletrabajotercerizado.getEstado();
                    detalletrabajotercerizadoSetNewDetalletrabajotercerizado.setEstado(estadodetalletrabajotercerizado);
                    detalletrabajotercerizadoSetNewDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoSetNewDetalletrabajotercerizado);
                    if (oldEstadoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado != null && !oldEstadoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado.equals(estadodetalletrabajotercerizado)) {
                        oldEstadoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado.getDetalletrabajotercerizadoSet().remove(detalletrabajotercerizadoSetNewDetalletrabajotercerizado);
                        oldEstadoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado = em.merge(oldEstadoOfDetalletrabajotercerizadoSetNewDetalletrabajotercerizado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadodetalletrabajotercerizado.getIdestado();
                if (findEstadodetalletrabajotercerizado(id) == null) {
                    throw new NonexistentEntityException("The estadodetalletrabajotercerizado with id " + id + " no longer exists.");
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
            Estadodetalletrabajotercerizado estadodetalletrabajotercerizado;
            try {
                estadodetalletrabajotercerizado = em.getReference(Estadodetalletrabajotercerizado.class, id);
                estadodetalletrabajotercerizado.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadodetalletrabajotercerizado with id " + id + " no longer exists.", enfe);
            }
            Set<Detalletrabajotercerizado> detalletrabajotercerizadoSet = estadodetalletrabajotercerizado.getDetalletrabajotercerizadoSet();
            for (Detalletrabajotercerizado detalletrabajotercerizadoSetDetalletrabajotercerizado : detalletrabajotercerizadoSet) {
                detalletrabajotercerizadoSetDetalletrabajotercerizado.setEstado(null);
                detalletrabajotercerizadoSetDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoSetDetalletrabajotercerizado);
            }
            em.remove(estadodetalletrabajotercerizado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadodetalletrabajotercerizado> findEstadodetalletrabajotercerizadoEntities() {
        return findEstadodetalletrabajotercerizadoEntities(true, -1, -1);
    }

    public List<Estadodetalletrabajotercerizado> findEstadodetalletrabajotercerizadoEntities(int maxResults, int firstResult) {
        return findEstadodetalletrabajotercerizadoEntities(false, maxResults, firstResult);
    }

    private List<Estadodetalletrabajotercerizado> findEstadodetalletrabajotercerizadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadodetalletrabajotercerizado.class));
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

    public Estadodetalletrabajotercerizado findEstadodetalletrabajotercerizado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadodetalletrabajotercerizado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadodetalletrabajotercerizadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadodetalletrabajotercerizado> rt = cq.from(Estadodetalletrabajotercerizado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
