/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Estadoejecplanifpedido;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Ejecucionplanificacionproduccion;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class EstadoejecplanifpedidoJpaController {

    public EstadoejecplanifpedidoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadoejecplanifpedido estadoejecplanifpedido) throws PreexistingEntityException, Exception {
        if (estadoejecplanifpedido.getEjecucionplanificacionproduccionSet() == null) {
            estadoejecplanifpedido.setEjecucionplanificacionproduccionSet(new HashSet<Ejecucionplanificacionproduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Ejecucionplanificacionproduccion> attachedEjecucionplanificacionproduccionSet = new HashSet<Ejecucionplanificacionproduccion>();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSetEjecucionplanificacionproduccionToAttach : estadoejecplanifpedido.getEjecucionplanificacionproduccionSet()) {
                ejecucionplanificacionproduccionSetEjecucionplanificacionproduccionToAttach = em.getReference(ejecucionplanificacionproduccionSetEjecucionplanificacionproduccionToAttach.getClass(), ejecucionplanificacionproduccionSetEjecucionplanificacionproduccionToAttach.getIdejecucion());
                attachedEjecucionplanificacionproduccionSet.add(ejecucionplanificacionproduccionSetEjecucionplanificacionproduccionToAttach);
            }
            estadoejecplanifpedido.setEjecucionplanificacionproduccionSet(attachedEjecucionplanificacionproduccionSet);
            em.persist(estadoejecplanifpedido);
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSetEjecucionplanificacionproduccion : estadoejecplanifpedido.getEjecucionplanificacionproduccionSet()) {
                Estadoejecplanifpedido oldEstadoOfEjecucionplanificacionproduccionSetEjecucionplanificacionproduccion = ejecucionplanificacionproduccionSetEjecucionplanificacionproduccion.getEstado();
                ejecucionplanificacionproduccionSetEjecucionplanificacionproduccion.setEstado(estadoejecplanifpedido);
                ejecucionplanificacionproduccionSetEjecucionplanificacionproduccion = em.merge(ejecucionplanificacionproduccionSetEjecucionplanificacionproduccion);
                if (oldEstadoOfEjecucionplanificacionproduccionSetEjecucionplanificacionproduccion != null) {
                    oldEstadoOfEjecucionplanificacionproduccionSetEjecucionplanificacionproduccion.getEjecucionplanificacionproduccionSet().remove(ejecucionplanificacionproduccionSetEjecucionplanificacionproduccion);
                    oldEstadoOfEjecucionplanificacionproduccionSetEjecucionplanificacionproduccion = em.merge(oldEstadoOfEjecucionplanificacionproduccionSetEjecucionplanificacionproduccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoejecplanifpedido(estadoejecplanifpedido.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadoejecplanifpedido " + estadoejecplanifpedido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadoejecplanifpedido estadoejecplanifpedido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoejecplanifpedido persistentEstadoejecplanifpedido = em.find(Estadoejecplanifpedido.class, estadoejecplanifpedido.getIdestado());
            Set<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionSetOld = persistentEstadoejecplanifpedido.getEjecucionplanificacionproduccionSet();
            Set<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionSetNew = estadoejecplanifpedido.getEjecucionplanificacionproduccionSet();
            Set<Ejecucionplanificacionproduccion> attachedEjecucionplanificacionproduccionSetNew = new HashSet<Ejecucionplanificacionproduccion>();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccionToAttach : ejecucionplanificacionproduccionSetNew) {
                ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccionToAttach = em.getReference(ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccionToAttach.getClass(), ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccionToAttach.getIdejecucion());
                attachedEjecucionplanificacionproduccionSetNew.add(ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccionToAttach);
            }
            ejecucionplanificacionproduccionSetNew = attachedEjecucionplanificacionproduccionSetNew;
            estadoejecplanifpedido.setEjecucionplanificacionproduccionSet(ejecucionplanificacionproduccionSetNew);
            estadoejecplanifpedido = em.merge(estadoejecplanifpedido);
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSetOldEjecucionplanificacionproduccion : ejecucionplanificacionproduccionSetOld) {
                if (!ejecucionplanificacionproduccionSetNew.contains(ejecucionplanificacionproduccionSetOldEjecucionplanificacionproduccion)) {
                    ejecucionplanificacionproduccionSetOldEjecucionplanificacionproduccion.setEstado(null);
                    ejecucionplanificacionproduccionSetOldEjecucionplanificacionproduccion = em.merge(ejecucionplanificacionproduccionSetOldEjecucionplanificacionproduccion);
                }
            }
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion : ejecucionplanificacionproduccionSetNew) {
                if (!ejecucionplanificacionproduccionSetOld.contains(ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion)) {
                    Estadoejecplanifpedido oldEstadoOfEjecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion = ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion.getEstado();
                    ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion.setEstado(estadoejecplanifpedido);
                    ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion = em.merge(ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion);
                    if (oldEstadoOfEjecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion != null && !oldEstadoOfEjecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion.equals(estadoejecplanifpedido)) {
                        oldEstadoOfEjecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion.getEjecucionplanificacionproduccionSet().remove(ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion);
                        oldEstadoOfEjecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion = em.merge(oldEstadoOfEjecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadoejecplanifpedido.getIdestado();
                if (findEstadoejecplanifpedido(id) == null) {
                    throw new NonexistentEntityException("The estadoejecplanifpedido with id " + id + " no longer exists.");
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
            Estadoejecplanifpedido estadoejecplanifpedido;
            try {
                estadoejecplanifpedido = em.getReference(Estadoejecplanifpedido.class, id);
                estadoejecplanifpedido.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoejecplanifpedido with id " + id + " no longer exists.", enfe);
            }
            Set<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionSet = estadoejecplanifpedido.getEjecucionplanificacionproduccionSet();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSetEjecucionplanificacionproduccion : ejecucionplanificacionproduccionSet) {
                ejecucionplanificacionproduccionSetEjecucionplanificacionproduccion.setEstado(null);
                ejecucionplanificacionproduccionSetEjecucionplanificacionproduccion = em.merge(ejecucionplanificacionproduccionSetEjecucionplanificacionproduccion);
            }
            em.remove(estadoejecplanifpedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadoejecplanifpedido> findEstadoejecplanifpedidoEntities() {
        return findEstadoejecplanifpedidoEntities(true, -1, -1);
    }

    public List<Estadoejecplanifpedido> findEstadoejecplanifpedidoEntities(int maxResults, int firstResult) {
        return findEstadoejecplanifpedidoEntities(false, maxResults, firstResult);
    }

    private List<Estadoejecplanifpedido> findEstadoejecplanifpedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadoejecplanifpedido.class));
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

    public Estadoejecplanifpedido findEstadoejecplanifpedido(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadoejecplanifpedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoejecplanifpedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadoejecplanifpedido> rt = cq.from(Estadoejecplanifpedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
