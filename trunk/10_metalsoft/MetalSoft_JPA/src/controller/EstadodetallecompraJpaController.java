/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Estadodetallecompra;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Detallecompra;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class EstadodetallecompraJpaController {

    public EstadodetallecompraJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadodetallecompra estadodetallecompra) throws PreexistingEntityException, Exception {
        if (estadodetallecompra.getDetallecompraSet() == null) {
            estadodetallecompra.setDetallecompraSet(new HashSet<Detallecompra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Detallecompra> attachedDetallecompraSet = new HashSet<Detallecompra>();
            for (Detallecompra detallecompraSetDetallecompraToAttach : estadodetallecompra.getDetallecompraSet()) {
                detallecompraSetDetallecompraToAttach = em.getReference(detallecompraSetDetallecompraToAttach.getClass(), detallecompraSetDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraSet.add(detallecompraSetDetallecompraToAttach);
            }
            estadodetallecompra.setDetallecompraSet(attachedDetallecompraSet);
            em.persist(estadodetallecompra);
            for (Detallecompra detallecompraSetDetallecompra : estadodetallecompra.getDetallecompraSet()) {
                Estadodetallecompra oldEstadoOfDetallecompraSetDetallecompra = detallecompraSetDetallecompra.getEstado();
                detallecompraSetDetallecompra.setEstado(estadodetallecompra);
                detallecompraSetDetallecompra = em.merge(detallecompraSetDetallecompra);
                if (oldEstadoOfDetallecompraSetDetallecompra != null) {
                    oldEstadoOfDetallecompraSetDetallecompra.getDetallecompraSet().remove(detallecompraSetDetallecompra);
                    oldEstadoOfDetallecompraSetDetallecompra = em.merge(oldEstadoOfDetallecompraSetDetallecompra);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadodetallecompra(estadodetallecompra.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadodetallecompra " + estadodetallecompra + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadodetallecompra estadodetallecompra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadodetallecompra persistentEstadodetallecompra = em.find(Estadodetallecompra.class, estadodetallecompra.getIdestado());
            Set<Detallecompra> detallecompraSetOld = persistentEstadodetallecompra.getDetallecompraSet();
            Set<Detallecompra> detallecompraSetNew = estadodetallecompra.getDetallecompraSet();
            Set<Detallecompra> attachedDetallecompraSetNew = new HashSet<Detallecompra>();
            for (Detallecompra detallecompraSetNewDetallecompraToAttach : detallecompraSetNew) {
                detallecompraSetNewDetallecompraToAttach = em.getReference(detallecompraSetNewDetallecompraToAttach.getClass(), detallecompraSetNewDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraSetNew.add(detallecompraSetNewDetallecompraToAttach);
            }
            detallecompraSetNew = attachedDetallecompraSetNew;
            estadodetallecompra.setDetallecompraSet(detallecompraSetNew);
            estadodetallecompra = em.merge(estadodetallecompra);
            for (Detallecompra detallecompraSetOldDetallecompra : detallecompraSetOld) {
                if (!detallecompraSetNew.contains(detallecompraSetOldDetallecompra)) {
                    detallecompraSetOldDetallecompra.setEstado(null);
                    detallecompraSetOldDetallecompra = em.merge(detallecompraSetOldDetallecompra);
                }
            }
            for (Detallecompra detallecompraSetNewDetallecompra : detallecompraSetNew) {
                if (!detallecompraSetOld.contains(detallecompraSetNewDetallecompra)) {
                    Estadodetallecompra oldEstadoOfDetallecompraSetNewDetallecompra = detallecompraSetNewDetallecompra.getEstado();
                    detallecompraSetNewDetallecompra.setEstado(estadodetallecompra);
                    detallecompraSetNewDetallecompra = em.merge(detallecompraSetNewDetallecompra);
                    if (oldEstadoOfDetallecompraSetNewDetallecompra != null && !oldEstadoOfDetallecompraSetNewDetallecompra.equals(estadodetallecompra)) {
                        oldEstadoOfDetallecompraSetNewDetallecompra.getDetallecompraSet().remove(detallecompraSetNewDetallecompra);
                        oldEstadoOfDetallecompraSetNewDetallecompra = em.merge(oldEstadoOfDetallecompraSetNewDetallecompra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadodetallecompra.getIdestado();
                if (findEstadodetallecompra(id) == null) {
                    throw new NonexistentEntityException("The estadodetallecompra with id " + id + " no longer exists.");
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
            Estadodetallecompra estadodetallecompra;
            try {
                estadodetallecompra = em.getReference(Estadodetallecompra.class, id);
                estadodetallecompra.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadodetallecompra with id " + id + " no longer exists.", enfe);
            }
            Set<Detallecompra> detallecompraSet = estadodetallecompra.getDetallecompraSet();
            for (Detallecompra detallecompraSetDetallecompra : detallecompraSet) {
                detallecompraSetDetallecompra.setEstado(null);
                detallecompraSetDetallecompra = em.merge(detallecompraSetDetallecompra);
            }
            em.remove(estadodetallecompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadodetallecompra> findEstadodetallecompraEntities() {
        return findEstadodetallecompraEntities(true, -1, -1);
    }

    public List<Estadodetallecompra> findEstadodetallecompraEntities(int maxResults, int firstResult) {
        return findEstadodetallecompraEntities(false, maxResults, firstResult);
    }

    private List<Estadodetallecompra> findEstadodetallecompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadodetallecompra.class));
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

    public Estadodetallecompra findEstadodetallecompra(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadodetallecompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadodetallecompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadodetallecompra> rt = cq.from(Estadodetallecompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
