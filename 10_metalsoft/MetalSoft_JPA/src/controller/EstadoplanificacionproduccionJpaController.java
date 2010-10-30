/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Estadoplanificacionproduccion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Planificacionproduccion;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class EstadoplanificacionproduccionJpaController {

    public EstadoplanificacionproduccionJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadoplanificacionproduccion estadoplanificacionproduccion) throws PreexistingEntityException, Exception {
        if (estadoplanificacionproduccion.getPlanificacionproduccionSet() == null) {
            estadoplanificacionproduccion.setPlanificacionproduccionSet(new HashSet<Planificacionproduccion>());
        }
        if (estadoplanificacionproduccion.getPlanificacionproduccionSet1() == null) {
            estadoplanificacionproduccion.setPlanificacionproduccionSet1(new HashSet<Planificacionproduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Planificacionproduccion> attachedPlanificacionproduccionSet = new HashSet<Planificacionproduccion>();
            for (Planificacionproduccion planificacionproduccionSetPlanificacionproduccionToAttach : estadoplanificacionproduccion.getPlanificacionproduccionSet()) {
                planificacionproduccionSetPlanificacionproduccionToAttach = em.getReference(planificacionproduccionSetPlanificacionproduccionToAttach.getClass(), planificacionproduccionSetPlanificacionproduccionToAttach.getIdplanificacionproduccion());
                attachedPlanificacionproduccionSet.add(planificacionproduccionSetPlanificacionproduccionToAttach);
            }
            estadoplanificacionproduccion.setPlanificacionproduccionSet(attachedPlanificacionproduccionSet);
            Set<Planificacionproduccion> attachedPlanificacionproduccionSet1 = new HashSet<Planificacionproduccion>();
            for (Planificacionproduccion planificacionproduccionSet1PlanificacionproduccionToAttach : estadoplanificacionproduccion.getPlanificacionproduccionSet1()) {
                planificacionproduccionSet1PlanificacionproduccionToAttach = em.getReference(planificacionproduccionSet1PlanificacionproduccionToAttach.getClass(), planificacionproduccionSet1PlanificacionproduccionToAttach.getIdplanificacionproduccion());
                attachedPlanificacionproduccionSet1.add(planificacionproduccionSet1PlanificacionproduccionToAttach);
            }
            estadoplanificacionproduccion.setPlanificacionproduccionSet1(attachedPlanificacionproduccionSet1);
            em.persist(estadoplanificacionproduccion);
            for (Planificacionproduccion planificacionproduccionSetPlanificacionproduccion : estadoplanificacionproduccion.getPlanificacionproduccionSet()) {
                Estadoplanificacionproduccion oldIdestadoOfPlanificacionproduccionSetPlanificacionproduccion = planificacionproduccionSetPlanificacionproduccion.getIdestado();
                planificacionproduccionSetPlanificacionproduccion.setIdestado(estadoplanificacionproduccion);
                planificacionproduccionSetPlanificacionproduccion = em.merge(planificacionproduccionSetPlanificacionproduccion);
                if (oldIdestadoOfPlanificacionproduccionSetPlanificacionproduccion != null) {
                    oldIdestadoOfPlanificacionproduccionSetPlanificacionproduccion.getPlanificacionproduccionSet().remove(planificacionproduccionSetPlanificacionproduccion);
                    oldIdestadoOfPlanificacionproduccionSetPlanificacionproduccion = em.merge(oldIdestadoOfPlanificacionproduccionSetPlanificacionproduccion);
                }
            }
            for (Planificacionproduccion planificacionproduccionSet1Planificacionproduccion : estadoplanificacionproduccion.getPlanificacionproduccionSet1()) {
                Estadoplanificacionproduccion oldIdestado1OfPlanificacionproduccionSet1Planificacionproduccion = planificacionproduccionSet1Planificacionproduccion.getIdestado1();
                planificacionproduccionSet1Planificacionproduccion.setIdestado1(estadoplanificacionproduccion);
                planificacionproduccionSet1Planificacionproduccion = em.merge(planificacionproduccionSet1Planificacionproduccion);
                if (oldIdestado1OfPlanificacionproduccionSet1Planificacionproduccion != null) {
                    oldIdestado1OfPlanificacionproduccionSet1Planificacionproduccion.getPlanificacionproduccionSet1().remove(planificacionproduccionSet1Planificacionproduccion);
                    oldIdestado1OfPlanificacionproduccionSet1Planificacionproduccion = em.merge(oldIdestado1OfPlanificacionproduccionSet1Planificacionproduccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoplanificacionproduccion(estadoplanificacionproduccion.getId()) != null) {
                throw new PreexistingEntityException("Estadoplanificacionproduccion " + estadoplanificacionproduccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadoplanificacionproduccion estadoplanificacionproduccion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoplanificacionproduccion persistentEstadoplanificacionproduccion = em.find(Estadoplanificacionproduccion.class, estadoplanificacionproduccion.getId());
            Set<Planificacionproduccion> planificacionproduccionSetOld = persistentEstadoplanificacionproduccion.getPlanificacionproduccionSet();
            Set<Planificacionproduccion> planificacionproduccionSetNew = estadoplanificacionproduccion.getPlanificacionproduccionSet();
            Set<Planificacionproduccion> planificacionproduccionSet1Old = persistentEstadoplanificacionproduccion.getPlanificacionproduccionSet1();
            Set<Planificacionproduccion> planificacionproduccionSet1New = estadoplanificacionproduccion.getPlanificacionproduccionSet1();
            Set<Planificacionproduccion> attachedPlanificacionproduccionSetNew = new HashSet<Planificacionproduccion>();
            for (Planificacionproduccion planificacionproduccionSetNewPlanificacionproduccionToAttach : planificacionproduccionSetNew) {
                planificacionproduccionSetNewPlanificacionproduccionToAttach = em.getReference(planificacionproduccionSetNewPlanificacionproduccionToAttach.getClass(), planificacionproduccionSetNewPlanificacionproduccionToAttach.getIdplanificacionproduccion());
                attachedPlanificacionproduccionSetNew.add(planificacionproduccionSetNewPlanificacionproduccionToAttach);
            }
            planificacionproduccionSetNew = attachedPlanificacionproduccionSetNew;
            estadoplanificacionproduccion.setPlanificacionproduccionSet(planificacionproduccionSetNew);
            Set<Planificacionproduccion> attachedPlanificacionproduccionSet1New = new HashSet<Planificacionproduccion>();
            for (Planificacionproduccion planificacionproduccionSet1NewPlanificacionproduccionToAttach : planificacionproduccionSet1New) {
                planificacionproduccionSet1NewPlanificacionproduccionToAttach = em.getReference(planificacionproduccionSet1NewPlanificacionproduccionToAttach.getClass(), planificacionproduccionSet1NewPlanificacionproduccionToAttach.getIdplanificacionproduccion());
                attachedPlanificacionproduccionSet1New.add(planificacionproduccionSet1NewPlanificacionproduccionToAttach);
            }
            planificacionproduccionSet1New = attachedPlanificacionproduccionSet1New;
            estadoplanificacionproduccion.setPlanificacionproduccionSet1(planificacionproduccionSet1New);
            estadoplanificacionproduccion = em.merge(estadoplanificacionproduccion);
            for (Planificacionproduccion planificacionproduccionSetOldPlanificacionproduccion : planificacionproduccionSetOld) {
                if (!planificacionproduccionSetNew.contains(planificacionproduccionSetOldPlanificacionproduccion)) {
                    planificacionproduccionSetOldPlanificacionproduccion.setIdestado(null);
                    planificacionproduccionSetOldPlanificacionproduccion = em.merge(planificacionproduccionSetOldPlanificacionproduccion);
                }
            }
            for (Planificacionproduccion planificacionproduccionSetNewPlanificacionproduccion : planificacionproduccionSetNew) {
                if (!planificacionproduccionSetOld.contains(planificacionproduccionSetNewPlanificacionproduccion)) {
                    Estadoplanificacionproduccion oldIdestadoOfPlanificacionproduccionSetNewPlanificacionproduccion = planificacionproduccionSetNewPlanificacionproduccion.getIdestado();
                    planificacionproduccionSetNewPlanificacionproduccion.setIdestado(estadoplanificacionproduccion);
                    planificacionproduccionSetNewPlanificacionproduccion = em.merge(planificacionproduccionSetNewPlanificacionproduccion);
                    if (oldIdestadoOfPlanificacionproduccionSetNewPlanificacionproduccion != null && !oldIdestadoOfPlanificacionproduccionSetNewPlanificacionproduccion.equals(estadoplanificacionproduccion)) {
                        oldIdestadoOfPlanificacionproduccionSetNewPlanificacionproduccion.getPlanificacionproduccionSet().remove(planificacionproduccionSetNewPlanificacionproduccion);
                        oldIdestadoOfPlanificacionproduccionSetNewPlanificacionproduccion = em.merge(oldIdestadoOfPlanificacionproduccionSetNewPlanificacionproduccion);
                    }
                }
            }
            for (Planificacionproduccion planificacionproduccionSet1OldPlanificacionproduccion : planificacionproduccionSet1Old) {
                if (!planificacionproduccionSet1New.contains(planificacionproduccionSet1OldPlanificacionproduccion)) {
                    planificacionproduccionSet1OldPlanificacionproduccion.setIdestado1(null);
                    planificacionproduccionSet1OldPlanificacionproduccion = em.merge(planificacionproduccionSet1OldPlanificacionproduccion);
                }
            }
            for (Planificacionproduccion planificacionproduccionSet1NewPlanificacionproduccion : planificacionproduccionSet1New) {
                if (!planificacionproduccionSet1Old.contains(planificacionproduccionSet1NewPlanificacionproduccion)) {
                    Estadoplanificacionproduccion oldIdestado1OfPlanificacionproduccionSet1NewPlanificacionproduccion = planificacionproduccionSet1NewPlanificacionproduccion.getIdestado1();
                    planificacionproduccionSet1NewPlanificacionproduccion.setIdestado1(estadoplanificacionproduccion);
                    planificacionproduccionSet1NewPlanificacionproduccion = em.merge(planificacionproduccionSet1NewPlanificacionproduccion);
                    if (oldIdestado1OfPlanificacionproduccionSet1NewPlanificacionproduccion != null && !oldIdestado1OfPlanificacionproduccionSet1NewPlanificacionproduccion.equals(estadoplanificacionproduccion)) {
                        oldIdestado1OfPlanificacionproduccionSet1NewPlanificacionproduccion.getPlanificacionproduccionSet1().remove(planificacionproduccionSet1NewPlanificacionproduccion);
                        oldIdestado1OfPlanificacionproduccionSet1NewPlanificacionproduccion = em.merge(oldIdestado1OfPlanificacionproduccionSet1NewPlanificacionproduccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadoplanificacionproduccion.getId();
                if (findEstadoplanificacionproduccion(id) == null) {
                    throw new NonexistentEntityException("The estadoplanificacionproduccion with id " + id + " no longer exists.");
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
            Estadoplanificacionproduccion estadoplanificacionproduccion;
            try {
                estadoplanificacionproduccion = em.getReference(Estadoplanificacionproduccion.class, id);
                estadoplanificacionproduccion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoplanificacionproduccion with id " + id + " no longer exists.", enfe);
            }
            Set<Planificacionproduccion> planificacionproduccionSet = estadoplanificacionproduccion.getPlanificacionproduccionSet();
            for (Planificacionproduccion planificacionproduccionSetPlanificacionproduccion : planificacionproduccionSet) {
                planificacionproduccionSetPlanificacionproduccion.setIdestado(null);
                planificacionproduccionSetPlanificacionproduccion = em.merge(planificacionproduccionSetPlanificacionproduccion);
            }
            Set<Planificacionproduccion> planificacionproduccionSet1 = estadoplanificacionproduccion.getPlanificacionproduccionSet1();
            for (Planificacionproduccion planificacionproduccionSet1Planificacionproduccion : planificacionproduccionSet1) {
                planificacionproduccionSet1Planificacionproduccion.setIdestado1(null);
                planificacionproduccionSet1Planificacionproduccion = em.merge(planificacionproduccionSet1Planificacionproduccion);
            }
            em.remove(estadoplanificacionproduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadoplanificacionproduccion> findEstadoplanificacionproduccionEntities() {
        return findEstadoplanificacionproduccionEntities(true, -1, -1);
    }

    public List<Estadoplanificacionproduccion> findEstadoplanificacionproduccionEntities(int maxResults, int firstResult) {
        return findEstadoplanificacionproduccionEntities(false, maxResults, firstResult);
    }

    private List<Estadoplanificacionproduccion> findEstadoplanificacionproduccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadoplanificacionproduccion.class));
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

    public Estadoplanificacionproduccion findEstadoplanificacionproduccion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadoplanificacionproduccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoplanificacionproduccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadoplanificacionproduccion> rt = cq.from(Estadoplanificacionproduccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
