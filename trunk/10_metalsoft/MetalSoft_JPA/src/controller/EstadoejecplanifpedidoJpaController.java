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
        if (estadoejecplanifpedido.getEjecucionplanificacionproduccionSet1() == null) {
            estadoejecplanifpedido.setEjecucionplanificacionproduccionSet1(new HashSet<Ejecucionplanificacionproduccion>());
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
            Set<Ejecucionplanificacionproduccion> attachedEjecucionplanificacionproduccionSet1 = new HashSet<Ejecucionplanificacionproduccion>();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSet1EjecucionplanificacionproduccionToAttach : estadoejecplanifpedido.getEjecucionplanificacionproduccionSet1()) {
                ejecucionplanificacionproduccionSet1EjecucionplanificacionproduccionToAttach = em.getReference(ejecucionplanificacionproduccionSet1EjecucionplanificacionproduccionToAttach.getClass(), ejecucionplanificacionproduccionSet1EjecucionplanificacionproduccionToAttach.getIdejecucion());
                attachedEjecucionplanificacionproduccionSet1.add(ejecucionplanificacionproduccionSet1EjecucionplanificacionproduccionToAttach);
            }
            estadoejecplanifpedido.setEjecucionplanificacionproduccionSet1(attachedEjecucionplanificacionproduccionSet1);
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
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion : estadoejecplanifpedido.getEjecucionplanificacionproduccionSet1()) {
                Estadoejecplanifpedido oldEstado1OfEjecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion = ejecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion.getEstado1();
                ejecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion.setEstado1(estadoejecplanifpedido);
                ejecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion = em.merge(ejecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion);
                if (oldEstado1OfEjecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion != null) {
                    oldEstado1OfEjecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion.getEjecucionplanificacionproduccionSet1().remove(ejecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion);
                    oldEstado1OfEjecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion = em.merge(oldEstado1OfEjecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion);
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
            Set<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionSet1Old = persistentEstadoejecplanifpedido.getEjecucionplanificacionproduccionSet1();
            Set<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionSet1New = estadoejecplanifpedido.getEjecucionplanificacionproduccionSet1();
            Set<Ejecucionplanificacionproduccion> attachedEjecucionplanificacionproduccionSetNew = new HashSet<Ejecucionplanificacionproduccion>();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccionToAttach : ejecucionplanificacionproduccionSetNew) {
                ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccionToAttach = em.getReference(ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccionToAttach.getClass(), ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccionToAttach.getIdejecucion());
                attachedEjecucionplanificacionproduccionSetNew.add(ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccionToAttach);
            }
            ejecucionplanificacionproduccionSetNew = attachedEjecucionplanificacionproduccionSetNew;
            estadoejecplanifpedido.setEjecucionplanificacionproduccionSet(ejecucionplanificacionproduccionSetNew);
            Set<Ejecucionplanificacionproduccion> attachedEjecucionplanificacionproduccionSet1New = new HashSet<Ejecucionplanificacionproduccion>();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccionToAttach : ejecucionplanificacionproduccionSet1New) {
                ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccionToAttach = em.getReference(ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccionToAttach.getClass(), ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccionToAttach.getIdejecucion());
                attachedEjecucionplanificacionproduccionSet1New.add(ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccionToAttach);
            }
            ejecucionplanificacionproduccionSet1New = attachedEjecucionplanificacionproduccionSet1New;
            estadoejecplanifpedido.setEjecucionplanificacionproduccionSet1(ejecucionplanificacionproduccionSet1New);
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
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSet1OldEjecucionplanificacionproduccion : ejecucionplanificacionproduccionSet1Old) {
                if (!ejecucionplanificacionproduccionSet1New.contains(ejecucionplanificacionproduccionSet1OldEjecucionplanificacionproduccion)) {
                    ejecucionplanificacionproduccionSet1OldEjecucionplanificacionproduccion.setEstado1(null);
                    ejecucionplanificacionproduccionSet1OldEjecucionplanificacionproduccion = em.merge(ejecucionplanificacionproduccionSet1OldEjecucionplanificacionproduccion);
                }
            }
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion : ejecucionplanificacionproduccionSet1New) {
                if (!ejecucionplanificacionproduccionSet1Old.contains(ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion)) {
                    Estadoejecplanifpedido oldEstado1OfEjecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion = ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion.getEstado1();
                    ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion.setEstado1(estadoejecplanifpedido);
                    ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion = em.merge(ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion);
                    if (oldEstado1OfEjecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion != null && !oldEstado1OfEjecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion.equals(estadoejecplanifpedido)) {
                        oldEstado1OfEjecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion.getEjecucionplanificacionproduccionSet1().remove(ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion);
                        oldEstado1OfEjecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion = em.merge(oldEstado1OfEjecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion);
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
            Set<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionSet1 = estadoejecplanifpedido.getEjecucionplanificacionproduccionSet1();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion : ejecucionplanificacionproduccionSet1) {
                ejecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion.setEstado1(null);
                ejecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion = em.merge(ejecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion);
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
