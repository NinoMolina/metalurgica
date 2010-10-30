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
        if (estadoejecetapaprod.getEjecucionetapaproduccionSet1() == null) {
            estadoejecetapaprod.setEjecucionetapaproduccionSet1(new HashSet<Ejecucionetapaproduccion>());
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
            Set<Ejecucionetapaproduccion> attachedEjecucionetapaproduccionSet1 = new HashSet<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSet1EjecucionetapaproduccionToAttach : estadoejecetapaprod.getEjecucionetapaproduccionSet1()) {
                ejecucionetapaproduccionSet1EjecucionetapaproduccionToAttach = em.getReference(ejecucionetapaproduccionSet1EjecucionetapaproduccionToAttach.getClass(), ejecucionetapaproduccionSet1EjecucionetapaproduccionToAttach.getId());
                attachedEjecucionetapaproduccionSet1.add(ejecucionetapaproduccionSet1EjecucionetapaproduccionToAttach);
            }
            estadoejecetapaprod.setEjecucionetapaproduccionSet1(attachedEjecucionetapaproduccionSet1);
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
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSet1Ejecucionetapaproduccion : estadoejecetapaprod.getEjecucionetapaproduccionSet1()) {
                Estadoejecetapaprod oldEstado1OfEjecucionetapaproduccionSet1Ejecucionetapaproduccion = ejecucionetapaproduccionSet1Ejecucionetapaproduccion.getEstado1();
                ejecucionetapaproduccionSet1Ejecucionetapaproduccion.setEstado1(estadoejecetapaprod);
                ejecucionetapaproduccionSet1Ejecucionetapaproduccion = em.merge(ejecucionetapaproduccionSet1Ejecucionetapaproduccion);
                if (oldEstado1OfEjecucionetapaproduccionSet1Ejecucionetapaproduccion != null) {
                    oldEstado1OfEjecucionetapaproduccionSet1Ejecucionetapaproduccion.getEjecucionetapaproduccionSet1().remove(ejecucionetapaproduccionSet1Ejecucionetapaproduccion);
                    oldEstado1OfEjecucionetapaproduccionSet1Ejecucionetapaproduccion = em.merge(oldEstado1OfEjecucionetapaproduccionSet1Ejecucionetapaproduccion);
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
            Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSet1Old = persistentEstadoejecetapaprod.getEjecucionetapaproduccionSet1();
            Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSet1New = estadoejecetapaprod.getEjecucionetapaproduccionSet1();
            Set<Ejecucionetapaproduccion> attachedEjecucionetapaproduccionSetNew = new HashSet<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach : ejecucionetapaproduccionSetNew) {
                ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach = em.getReference(ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach.getClass(), ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach.getId());
                attachedEjecucionetapaproduccionSetNew.add(ejecucionetapaproduccionSetNewEjecucionetapaproduccionToAttach);
            }
            ejecucionetapaproduccionSetNew = attachedEjecucionetapaproduccionSetNew;
            estadoejecetapaprod.setEjecucionetapaproduccionSet(ejecucionetapaproduccionSetNew);
            Set<Ejecucionetapaproduccion> attachedEjecucionetapaproduccionSet1New = new HashSet<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSet1NewEjecucionetapaproduccionToAttach : ejecucionetapaproduccionSet1New) {
                ejecucionetapaproduccionSet1NewEjecucionetapaproduccionToAttach = em.getReference(ejecucionetapaproduccionSet1NewEjecucionetapaproduccionToAttach.getClass(), ejecucionetapaproduccionSet1NewEjecucionetapaproduccionToAttach.getId());
                attachedEjecucionetapaproduccionSet1New.add(ejecucionetapaproduccionSet1NewEjecucionetapaproduccionToAttach);
            }
            ejecucionetapaproduccionSet1New = attachedEjecucionetapaproduccionSet1New;
            estadoejecetapaprod.setEjecucionetapaproduccionSet1(ejecucionetapaproduccionSet1New);
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
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSet1OldEjecucionetapaproduccion : ejecucionetapaproduccionSet1Old) {
                if (!ejecucionetapaproduccionSet1New.contains(ejecucionetapaproduccionSet1OldEjecucionetapaproduccion)) {
                    ejecucionetapaproduccionSet1OldEjecucionetapaproduccion.setEstado1(null);
                    ejecucionetapaproduccionSet1OldEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionSet1OldEjecucionetapaproduccion);
                }
            }
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSet1NewEjecucionetapaproduccion : ejecucionetapaproduccionSet1New) {
                if (!ejecucionetapaproduccionSet1Old.contains(ejecucionetapaproduccionSet1NewEjecucionetapaproduccion)) {
                    Estadoejecetapaprod oldEstado1OfEjecucionetapaproduccionSet1NewEjecucionetapaproduccion = ejecucionetapaproduccionSet1NewEjecucionetapaproduccion.getEstado1();
                    ejecucionetapaproduccionSet1NewEjecucionetapaproduccion.setEstado1(estadoejecetapaprod);
                    ejecucionetapaproduccionSet1NewEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionSet1NewEjecucionetapaproduccion);
                    if (oldEstado1OfEjecucionetapaproduccionSet1NewEjecucionetapaproduccion != null && !oldEstado1OfEjecucionetapaproduccionSet1NewEjecucionetapaproduccion.equals(estadoejecetapaprod)) {
                        oldEstado1OfEjecucionetapaproduccionSet1NewEjecucionetapaproduccion.getEjecucionetapaproduccionSet1().remove(ejecucionetapaproduccionSet1NewEjecucionetapaproduccion);
                        oldEstado1OfEjecucionetapaproduccionSet1NewEjecucionetapaproduccion = em.merge(oldEstado1OfEjecucionetapaproduccionSet1NewEjecucionetapaproduccion);
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
            Set<Ejecucionetapaproduccion> ejecucionetapaproduccionSet1 = estadoejecetapaprod.getEjecucionetapaproduccionSet1();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionSet1Ejecucionetapaproduccion : ejecucionetapaproduccionSet1) {
                ejecucionetapaproduccionSet1Ejecucionetapaproduccion.setEstado1(null);
                ejecucionetapaproduccionSet1Ejecucionetapaproduccion = em.merge(ejecucionetapaproduccionSet1Ejecucionetapaproduccion);
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
