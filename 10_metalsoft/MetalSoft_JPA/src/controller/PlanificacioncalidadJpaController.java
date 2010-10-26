/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Planificacioncalidad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Ejecucionplanificacioncalidad;
import entity.Pedido;
import entity.Detalleplanificacioncalidad;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class PlanificacioncalidadJpaController {

    public PlanificacioncalidadJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planificacioncalidad planificacioncalidad) throws PreexistingEntityException, Exception {
        if (planificacioncalidad.getDetalleplanificacioncalidadSet() == null) {
            planificacioncalidad.setDetalleplanificacioncalidadSet(new HashSet<Detalleplanificacioncalidad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidad = planificacioncalidad.getEjecucionplanificacioncalidad();
            if (ejecucionplanificacioncalidad != null) {
                ejecucionplanificacioncalidad = em.getReference(ejecucionplanificacioncalidad.getClass(), ejecucionplanificacioncalidad.getEjecucionplanificacioncalidadPK());
                planificacioncalidad.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidad);
            }
            Pedido pedido = planificacioncalidad.getPedido();
            if (pedido != null) {
                pedido = em.getReference(pedido.getClass(), pedido.getIdpedido());
                planificacioncalidad.setPedido(pedido);
            }
            Set<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadSet = new HashSet<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach : planificacioncalidad.getDetalleplanificacioncalidadSet()) {
                detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadSet.add(detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach);
            }
            planificacioncalidad.setDetalleplanificacioncalidadSet(attachedDetalleplanificacioncalidadSet);
            em.persist(planificacioncalidad);
            if (ejecucionplanificacioncalidad != null) {
                Planificacioncalidad oldPlanificacioncalidadOfEjecucionplanificacioncalidad = ejecucionplanificacioncalidad.getPlanificacioncalidad();
                if (oldPlanificacioncalidadOfEjecucionplanificacioncalidad != null) {
                    oldPlanificacioncalidadOfEjecucionplanificacioncalidad.setEjecucionplanificacioncalidad(null);
                    oldPlanificacioncalidadOfEjecucionplanificacioncalidad = em.merge(oldPlanificacioncalidadOfEjecucionplanificacioncalidad);
                }
                ejecucionplanificacioncalidad.setPlanificacioncalidad(planificacioncalidad);
                ejecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidad);
            }
            if (pedido != null) {
                pedido.getPlanificacioncalidadSet().add(planificacioncalidad);
                pedido = em.merge(pedido);
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetDetalleplanificacioncalidad : planificacioncalidad.getDetalleplanificacioncalidadSet()) {
                Planificacioncalidad oldPlanificacioncalidadOfDetalleplanificacioncalidadSetDetalleplanificacioncalidad = detalleplanificacioncalidadSetDetalleplanificacioncalidad.getPlanificacioncalidad();
                detalleplanificacioncalidadSetDetalleplanificacioncalidad.setPlanificacioncalidad(planificacioncalidad);
                detalleplanificacioncalidadSetDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadSetDetalleplanificacioncalidad);
                if (oldPlanificacioncalidadOfDetalleplanificacioncalidadSetDetalleplanificacioncalidad != null) {
                    oldPlanificacioncalidadOfDetalleplanificacioncalidadSetDetalleplanificacioncalidad.getDetalleplanificacioncalidadSet().remove(detalleplanificacioncalidadSetDetalleplanificacioncalidad);
                    oldPlanificacioncalidadOfDetalleplanificacioncalidadSetDetalleplanificacioncalidad = em.merge(oldPlanificacioncalidadOfDetalleplanificacioncalidadSetDetalleplanificacioncalidad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPlanificacioncalidad(planificacioncalidad.getIdplanificacion()) != null) {
                throw new PreexistingEntityException("Planificacioncalidad " + planificacioncalidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Planificacioncalidad planificacioncalidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planificacioncalidad persistentPlanificacioncalidad = em.find(Planificacioncalidad.class, planificacioncalidad.getIdplanificacion());
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidadOld = persistentPlanificacioncalidad.getEjecucionplanificacioncalidad();
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidadNew = planificacioncalidad.getEjecucionplanificacioncalidad();
            Pedido pedidoOld = persistentPlanificacioncalidad.getPedido();
            Pedido pedidoNew = planificacioncalidad.getPedido();
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSetOld = persistentPlanificacioncalidad.getDetalleplanificacioncalidadSet();
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSetNew = planificacioncalidad.getDetalleplanificacioncalidadSet();
            List<String> illegalOrphanMessages = null;
            if (ejecucionplanificacioncalidadOld != null && !ejecucionplanificacioncalidadOld.equals(ejecucionplanificacioncalidadNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Ejecucionplanificacioncalidad " + ejecucionplanificacioncalidadOld + " since its planificacioncalidad field is not nullable.");
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetOldDetalleplanificacioncalidad : detalleplanificacioncalidadSetOld) {
                if (!detalleplanificacioncalidadSetNew.contains(detalleplanificacioncalidadSetOldDetalleplanificacioncalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleplanificacioncalidad " + detalleplanificacioncalidadSetOldDetalleplanificacioncalidad + " since its planificacioncalidad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (ejecucionplanificacioncalidadNew != null) {
                ejecucionplanificacioncalidadNew = em.getReference(ejecucionplanificacioncalidadNew.getClass(), ejecucionplanificacioncalidadNew.getEjecucionplanificacioncalidadPK());
                planificacioncalidad.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidadNew);
            }
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getIdpedido());
                planificacioncalidad.setPedido(pedidoNew);
            }
            Set<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadSetNew = new HashSet<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach : detalleplanificacioncalidadSetNew) {
                detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadSetNew.add(detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach);
            }
            detalleplanificacioncalidadSetNew = attachedDetalleplanificacioncalidadSetNew;
            planificacioncalidad.setDetalleplanificacioncalidadSet(detalleplanificacioncalidadSetNew);
            planificacioncalidad = em.merge(planificacioncalidad);
            if (ejecucionplanificacioncalidadNew != null && !ejecucionplanificacioncalidadNew.equals(ejecucionplanificacioncalidadOld)) {
                Planificacioncalidad oldPlanificacioncalidadOfEjecucionplanificacioncalidad = ejecucionplanificacioncalidadNew.getPlanificacioncalidad();
                if (oldPlanificacioncalidadOfEjecucionplanificacioncalidad != null) {
                    oldPlanificacioncalidadOfEjecucionplanificacioncalidad.setEjecucionplanificacioncalidad(null);
                    oldPlanificacioncalidadOfEjecucionplanificacioncalidad = em.merge(oldPlanificacioncalidadOfEjecucionplanificacioncalidad);
                }
                ejecucionplanificacioncalidadNew.setPlanificacioncalidad(planificacioncalidad);
                ejecucionplanificacioncalidadNew = em.merge(ejecucionplanificacioncalidadNew);
            }
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getPlanificacioncalidadSet().remove(planificacioncalidad);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getPlanificacioncalidadSet().add(planificacioncalidad);
                pedidoNew = em.merge(pedidoNew);
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetNewDetalleplanificacioncalidad : detalleplanificacioncalidadSetNew) {
                if (!detalleplanificacioncalidadSetOld.contains(detalleplanificacioncalidadSetNewDetalleplanificacioncalidad)) {
                    Planificacioncalidad oldPlanificacioncalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad = detalleplanificacioncalidadSetNewDetalleplanificacioncalidad.getPlanificacioncalidad();
                    detalleplanificacioncalidadSetNewDetalleplanificacioncalidad.setPlanificacioncalidad(planificacioncalidad);
                    detalleplanificacioncalidadSetNewDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadSetNewDetalleplanificacioncalidad);
                    if (oldPlanificacioncalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad != null && !oldPlanificacioncalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad.equals(planificacioncalidad)) {
                        oldPlanificacioncalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad.getDetalleplanificacioncalidadSet().remove(detalleplanificacioncalidadSetNewDetalleplanificacioncalidad);
                        oldPlanificacioncalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad = em.merge(oldPlanificacioncalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = planificacioncalidad.getIdplanificacion();
                if (findPlanificacioncalidad(id) == null) {
                    throw new NonexistentEntityException("The planificacioncalidad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planificacioncalidad planificacioncalidad;
            try {
                planificacioncalidad = em.getReference(Planificacioncalidad.class, id);
                planificacioncalidad.getIdplanificacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planificacioncalidad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidadOrphanCheck = planificacioncalidad.getEjecucionplanificacioncalidad();
            if (ejecucionplanificacioncalidadOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planificacioncalidad (" + planificacioncalidad + ") cannot be destroyed since the Ejecucionplanificacioncalidad " + ejecucionplanificacioncalidadOrphanCheck + " in its ejecucionplanificacioncalidad field has a non-nullable planificacioncalidad field.");
            }
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSetOrphanCheck = planificacioncalidad.getDetalleplanificacioncalidadSet();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetOrphanCheckDetalleplanificacioncalidad : detalleplanificacioncalidadSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planificacioncalidad (" + planificacioncalidad + ") cannot be destroyed since the Detalleplanificacioncalidad " + detalleplanificacioncalidadSetOrphanCheckDetalleplanificacioncalidad + " in its detalleplanificacioncalidadSet field has a non-nullable planificacioncalidad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Pedido pedido = planificacioncalidad.getPedido();
            if (pedido != null) {
                pedido.getPlanificacioncalidadSet().remove(planificacioncalidad);
                pedido = em.merge(pedido);
            }
            em.remove(planificacioncalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Planificacioncalidad> findPlanificacioncalidadEntities() {
        return findPlanificacioncalidadEntities(true, -1, -1);
    }

    public List<Planificacioncalidad> findPlanificacioncalidadEntities(int maxResults, int firstResult) {
        return findPlanificacioncalidadEntities(false, maxResults, firstResult);
    }

    private List<Planificacioncalidad> findPlanificacioncalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Planificacioncalidad.class));
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

    public Planificacioncalidad findPlanificacioncalidad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Planificacioncalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanificacioncalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Planificacioncalidad> rt = cq.from(Planificacioncalidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
