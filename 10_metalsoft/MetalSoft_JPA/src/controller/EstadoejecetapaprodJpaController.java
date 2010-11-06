/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Estadoejecetapaprod;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Ejecucionetapaproduccion;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class EstadoejecetapaprodJpaController {

    public EstadoejecetapaprodJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadoejecetapaprod estadoejecetapaprod) throws PreexistingEntityException, Exception {
        if (estadoejecetapaprod.getEjecucionetapaproduccionSet() == null) {
            estadoejecetapaprod.setEjecucionetapaproduccionSet(new HashSet<Ejecucionetapaproduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Ejecucionetapaproduccion> attachedEjecucionetapaproduccionSet = new HashSet<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetEjecucionetapaproduccionToAttach : estadoejecetapaprod.getEjecucionetapaproduccionSet()) {
                ejecucionetapaproduccionSetEjecucionetapaproduccionToAttach = em.getReference(ejecucionetapaproduccionSetEjecucionetapaproduccionToAttach.getClass(), ejecucionetapaproduccionSetEjecucionetapaproduccionToAttach.getId());
                attachedEjecucionetapaproduccionSet.add(ejecucionetapaproduccionSetEjecucionetapaproduccionToAttach);
            }
            estadoejecetapaprod.setEjecucionetapaproduccionSet(attachedEjecucionetapaproduccionSet);
            em.persist(estadoejecetapaprod);
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetEjecucionetapaproduccion : estadoejecetapaprod.getEjecucionetapaproduccionSet()) {
                Estadoejecetapaprod oldEstadoOfEjecucionetapaproduccionSetEjecucionetapaproduccion = ejecucionetapaproduccionSetEjecucionetapaproduccion.getEstado();
                ejecucionetapaproduccionSetEjecucionetapaproduccion.setEstado(estadoejecetapaprod);
                ejecucionetapaproduccionSetEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionSetEjecucionetapaproduccion);
                if (oldEstadoOfEjecucionetapaproduccionSetEjecucionetapaproduccion != null) {
                    oldEstadoOfEjecucionetapaproduccionSetEjecucionetapaproduccion.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccionSetEjecucionetapaproduccion);
                    oldEstadoOfEjecucionetapaproduccionSetEjecucionetapaproduccion = em.merge(oldEstadoOfEjecucionetapaproduccionSetEjecucionetapaproduccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoejecetapaprod(estadoejecetapaprod.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadoejecetapaprod " + estadoejecetapaprod + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadoejecetapaprod estadoejecetapaprod) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoejecetapaprod persistentEstadoejecetapaprod = em.find(Estadoejecetapaprod.class, estadoejecetapaprod.getIdestado());
            Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSetOld = persistentEstadoejecetapaprod.getEjecucionetapaproduccionSet();
            Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSetNew = estadoejecetapaprod.getEjecucionetapaproduccionSet();
            Set<Ejecucionetapaproduccion> attachedEjecucionetapaproduccionSetNew = new HashSet<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach : ejecucionetapaproduccionSetNew) {
                ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach = em.getReference(ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach.getClass(), ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach.getId());
                attachedEjecucionetapaproduccionSetNew.add(ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach);
            }
            ejecucionetapaproduccionSetNew = attachedEjecucionetapaproduccionSetNew;
            estadoejecetapaprod.setEjecucionetapaproduccionSet(ejecucionetapaproduccionSetNew);
            estadoejecetapaprod = em.merge(estadoejecetapaprod);
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetOldEjecucionetapaproduccion : ejecucionetapaproduccionSetOld) {
                if (!ejecucionetapaproduccionSetNew.contains(ejecucionetapaproduccionSetOldEjecucionetapaproduccion)) {
                    ejecucionetapaproduccionSetOldEjecucionetapaproduccion.setEstado(null);
                    ejecucionetapaproduccionSetOldEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionSetOldEjecucionetapaproduccion);
                }
            }
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetNewEjecucionetapaproduccion : ejecucionetapaproduccionSetNew) {
                if (!ejecucionetapaproduccionSetOld.contains(ejecucionetapaproduccionSetNewEjecucionetapaproduccion)) {
                    Estadoejecetapaprod oldEstadoOfEjecucionetapaproduccionSetNewEjecucionetapaproduccion = ejecucionetapaproduccionSetNewEjecucionetapaproduccion.getEstado();
                    ejecucionetapaproduccionSetNewEjecucionetapaproduccion.setEstado(estadoejecetapaprod);
                    ejecucionetapaproduccionSetNewEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionSetNewEjecucionetapaproduccion);
                    if (oldEstadoOfEjecucionetapaproduccionSetNewEjecucionetapaproduccion != null && !oldEstadoOfEjecucionetapaproduccionSetNewEjecucionetapaproduccion.equals(estadoejecetapaprod)) {
                        oldEstadoOfEjecucionetapaproduccionSetNewEjecucionetapaproduccion.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccionSetNewEjecucionetapaproduccion);
                        oldEstadoOfEjecucionetapaproduccionSetNewEjecucionetapaproduccion = em.merge(oldEstadoOfEjecucionetapaproduccionSetNewEjecucionetapaproduccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadoejecetapaprod.getIdestado();
                if (findEstadoejecetapaprod(id) == null) {
                    throw new NonexistentEntityException("The estadoejecetapaprod with id " + id + " no longer exists.");
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
            Estadoejecetapaprod estadoejecetapaprod;
            try {
                estadoejecetapaprod = em.getReference(Estadoejecetapaprod.class, id);
                estadoejecetapaprod.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoejecetapaprod with id " + id + " no longer exists.", enfe);
            }
            Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSet = estadoejecetapaprod.getEjecucionetapaproduccionSet();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetEjecucionetapaproduccion : ejecucionetapaproduccionSet) {
                ejecucionetapaproduccionSetEjecucionetapaproduccion.setEstado(null);
                ejecucionetapaproduccionSetEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionSetEjecucionetapaproduccion);
            }
            em.remove(estadoejecetapaprod);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadoejecetapaprod> findEstadoejecetapaprodEntities() {
        return findEstadoejecetapaprodEntities(true, -1, -1);
    }

    public List<Estadoejecetapaprod> findEstadoejecetapaprodEntities(int maxResults, int firstResult) {
        return findEstadoejecetapaprodEntities(false, maxResults, firstResult);
    }

    private List<Estadoejecetapaprod> findEstadoejecetapaprodEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadoejecetapaprod.class));
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

    public Estadoejecetapaprod findEstadoejecetapaprod(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadoejecetapaprod.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoejecetapaprodCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadoejecetapaprod> rt = cq.from(Estadoejecetapaprod.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
