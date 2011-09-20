/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Estadoplanificacionproduccion;
import metalsoft.datos.jpa.entity.Planificacionproduccion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class EstadoplanificacionproduccionJpaController implements Serializable {

    public EstadoplanificacionproduccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadoplanificacionproduccion estadoplanificacionproduccion) throws PreexistingEntityException, Exception {
        if (estadoplanificacionproduccion.getPlanificacionproduccionList() == null) {
            estadoplanificacionproduccion.setPlanificacionproduccionList(new ArrayList<Planificacionproduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Planificacionproduccion> attachedPlanificacionproduccionList = new ArrayList<Planificacionproduccion>();
            for (Planificacionproduccion planificacionproduccionListPlanificacionproduccionToAttach : estadoplanificacionproduccion.getPlanificacionproduccionList()) {
                planificacionproduccionListPlanificacionproduccionToAttach = em.getReference(planificacionproduccionListPlanificacionproduccionToAttach.getClass(), planificacionproduccionListPlanificacionproduccionToAttach.getIdplanificacionproduccion());
                attachedPlanificacionproduccionList.add(planificacionproduccionListPlanificacionproduccionToAttach);
            }
            estadoplanificacionproduccion.setPlanificacionproduccionList(attachedPlanificacionproduccionList);
            em.persist(estadoplanificacionproduccion);
            for (Planificacionproduccion planificacionproduccionListPlanificacionproduccion : estadoplanificacionproduccion.getPlanificacionproduccionList()) {
                Estadoplanificacionproduccion oldIdestadoOfPlanificacionproduccionListPlanificacionproduccion = planificacionproduccionListPlanificacionproduccion.getIdestado();
                planificacionproduccionListPlanificacionproduccion.setIdestado(estadoplanificacionproduccion);
                planificacionproduccionListPlanificacionproduccion = em.merge(planificacionproduccionListPlanificacionproduccion);
                if (oldIdestadoOfPlanificacionproduccionListPlanificacionproduccion != null) {
                    oldIdestadoOfPlanificacionproduccionListPlanificacionproduccion.getPlanificacionproduccionList().remove(planificacionproduccionListPlanificacionproduccion);
                    oldIdestadoOfPlanificacionproduccionListPlanificacionproduccion = em.merge(oldIdestadoOfPlanificacionproduccionListPlanificacionproduccion);
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
            List<Planificacionproduccion> planificacionproduccionListOld = persistentEstadoplanificacionproduccion.getPlanificacionproduccionList();
            List<Planificacionproduccion> planificacionproduccionListNew = estadoplanificacionproduccion.getPlanificacionproduccionList();
            List<Planificacionproduccion> attachedPlanificacionproduccionListNew = new ArrayList<Planificacionproduccion>();
            for (Planificacionproduccion planificacionproduccionListNewPlanificacionproduccionToAttach : planificacionproduccionListNew) {
                planificacionproduccionListNewPlanificacionproduccionToAttach = em.getReference(planificacionproduccionListNewPlanificacionproduccionToAttach.getClass(), planificacionproduccionListNewPlanificacionproduccionToAttach.getIdplanificacionproduccion());
                attachedPlanificacionproduccionListNew.add(planificacionproduccionListNewPlanificacionproduccionToAttach);
            }
            planificacionproduccionListNew = attachedPlanificacionproduccionListNew;
            estadoplanificacionproduccion.setPlanificacionproduccionList(planificacionproduccionListNew);
            estadoplanificacionproduccion = em.merge(estadoplanificacionproduccion);
            for (Planificacionproduccion planificacionproduccionListOldPlanificacionproduccion : planificacionproduccionListOld) {
                if (!planificacionproduccionListNew.contains(planificacionproduccionListOldPlanificacionproduccion)) {
                    planificacionproduccionListOldPlanificacionproduccion.setIdestado(null);
                    planificacionproduccionListOldPlanificacionproduccion = em.merge(planificacionproduccionListOldPlanificacionproduccion);
                }
            }
            for (Planificacionproduccion planificacionproduccionListNewPlanificacionproduccion : planificacionproduccionListNew) {
                if (!planificacionproduccionListOld.contains(planificacionproduccionListNewPlanificacionproduccion)) {
                    Estadoplanificacionproduccion oldIdestadoOfPlanificacionproduccionListNewPlanificacionproduccion = planificacionproduccionListNewPlanificacionproduccion.getIdestado();
                    planificacionproduccionListNewPlanificacionproduccion.setIdestado(estadoplanificacionproduccion);
                    planificacionproduccionListNewPlanificacionproduccion = em.merge(planificacionproduccionListNewPlanificacionproduccion);
                    if (oldIdestadoOfPlanificacionproduccionListNewPlanificacionproduccion != null && !oldIdestadoOfPlanificacionproduccionListNewPlanificacionproduccion.equals(estadoplanificacionproduccion)) {
                        oldIdestadoOfPlanificacionproduccionListNewPlanificacionproduccion.getPlanificacionproduccionList().remove(planificacionproduccionListNewPlanificacionproduccion);
                        oldIdestadoOfPlanificacionproduccionListNewPlanificacionproduccion = em.merge(oldIdestadoOfPlanificacionproduccionListNewPlanificacionproduccion);
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
            List<Planificacionproduccion> planificacionproduccionList = estadoplanificacionproduccion.getPlanificacionproduccionList();
            for (Planificacionproduccion planificacionproduccionListPlanificacionproduccion : planificacionproduccionList) {
                planificacionproduccionListPlanificacionproduccion.setIdestado(null);
                planificacionproduccionListPlanificacionproduccion = em.merge(planificacionproduccionListPlanificacionproduccion);
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
