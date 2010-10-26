/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Planprocesoscalidad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Detalleplanprocesoscalidad;
import java.util.HashSet;
import java.util.Set;
import entity.Pedido;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class PlanprocesoscalidadJpaController {

    public PlanprocesoscalidadJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planprocesoscalidad planprocesoscalidad) throws PreexistingEntityException, Exception {
        if (planprocesoscalidad.getDetalleplanprocesoscalidadSet() == null) {
            planprocesoscalidad.setDetalleplanprocesoscalidadSet(new HashSet<Detalleplanprocesoscalidad>());
        }
        if (planprocesoscalidad.getPedidoSet() == null) {
            planprocesoscalidad.setPedidoSet(new HashSet<Pedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Detalleplanprocesoscalidad> attachedDetalleplanprocesoscalidadSet = new HashSet<Detalleplanprocesoscalidad>();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetDetalleplanprocesoscalidadToAttach : planprocesoscalidad.getDetalleplanprocesoscalidadSet()) {
                detalleplanprocesoscalidadSetDetalleplanprocesoscalidadToAttach = em.getReference(detalleplanprocesoscalidadSetDetalleplanprocesoscalidadToAttach.getClass(), detalleplanprocesoscalidadSetDetalleplanprocesoscalidadToAttach.getDetalleplanprocesoscalidadPK());
                attachedDetalleplanprocesoscalidadSet.add(detalleplanprocesoscalidadSetDetalleplanprocesoscalidadToAttach);
            }
            planprocesoscalidad.setDetalleplanprocesoscalidadSet(attachedDetalleplanprocesoscalidadSet);
            Set<Pedido> attachedPedidoSet = new HashSet<Pedido>();
            for (Pedido pedidoSetPedidoToAttach : planprocesoscalidad.getPedidoSet()) {
                pedidoSetPedidoToAttach = em.getReference(pedidoSetPedidoToAttach.getClass(), pedidoSetPedidoToAttach.getIdpedido());
                attachedPedidoSet.add(pedidoSetPedidoToAttach);
            }
            planprocesoscalidad.setPedidoSet(attachedPedidoSet);
            em.persist(planprocesoscalidad);
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetDetalleplanprocesoscalidad : planprocesoscalidad.getDetalleplanprocesoscalidadSet()) {
                Planprocesoscalidad oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetDetalleplanprocesoscalidad = detalleplanprocesoscalidadSetDetalleplanprocesoscalidad.getPlanprocesoscalidad();
                detalleplanprocesoscalidadSetDetalleplanprocesoscalidad.setPlanprocesoscalidad(planprocesoscalidad);
                detalleplanprocesoscalidadSetDetalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadSetDetalleplanprocesoscalidad);
                if (oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetDetalleplanprocesoscalidad != null) {
                    oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetDetalleplanprocesoscalidad.getDetalleplanprocesoscalidadSet().remove(detalleplanprocesoscalidadSetDetalleplanprocesoscalidad);
                    oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetDetalleplanprocesoscalidad = em.merge(oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetDetalleplanprocesoscalidad);
                }
            }
            for (Pedido pedidoSetPedido : planprocesoscalidad.getPedidoSet()) {
                Planprocesoscalidad oldPlanprocesoscalidadOfPedidoSetPedido = pedidoSetPedido.getPlanprocesoscalidad();
                pedidoSetPedido.setPlanprocesoscalidad(planprocesoscalidad);
                pedidoSetPedido = em.merge(pedidoSetPedido);
                if (oldPlanprocesoscalidadOfPedidoSetPedido != null) {
                    oldPlanprocesoscalidadOfPedidoSetPedido.getPedidoSet().remove(pedidoSetPedido);
                    oldPlanprocesoscalidadOfPedidoSetPedido = em.merge(oldPlanprocesoscalidadOfPedidoSetPedido);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPlanprocesoscalidad(planprocesoscalidad.getIdplanprocesoscalidad()) != null) {
                throw new PreexistingEntityException("Planprocesoscalidad " + planprocesoscalidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Planprocesoscalidad planprocesoscalidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planprocesoscalidad persistentPlanprocesoscalidad = em.find(Planprocesoscalidad.class, planprocesoscalidad.getIdplanprocesoscalidad());
            Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSetOld = persistentPlanprocesoscalidad.getDetalleplanprocesoscalidadSet();
            Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSetNew = planprocesoscalidad.getDetalleplanprocesoscalidadSet();
            Set<Pedido> pedidoSetOld = persistentPlanprocesoscalidad.getPedidoSet();
            Set<Pedido> pedidoSetNew = planprocesoscalidad.getPedidoSet();
            List<String> illegalOrphanMessages = null;
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetOldDetalleplanprocesoscalidad : detalleplanprocesoscalidadSetOld) {
                if (!detalleplanprocesoscalidadSetNew.contains(detalleplanprocesoscalidadSetOldDetalleplanprocesoscalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleplanprocesoscalidad " + detalleplanprocesoscalidadSetOldDetalleplanprocesoscalidad + " since its planprocesoscalidad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Detalleplanprocesoscalidad> attachedDetalleplanprocesoscalidadSetNew = new HashSet<Detalleplanprocesoscalidad>();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidadToAttach : detalleplanprocesoscalidadSetNew) {
                detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidadToAttach = em.getReference(detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidadToAttach.getClass(), detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidadToAttach.getDetalleplanprocesoscalidadPK());
                attachedDetalleplanprocesoscalidadSetNew.add(detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidadToAttach);
            }
            detalleplanprocesoscalidadSetNew = attachedDetalleplanprocesoscalidadSetNew;
            planprocesoscalidad.setDetalleplanprocesoscalidadSet(detalleplanprocesoscalidadSetNew);
            Set<Pedido> attachedPedidoSetNew = new HashSet<Pedido>();
            for (Pedido pedidoSetNewPedidoToAttach : pedidoSetNew) {
                pedidoSetNewPedidoToAttach = em.getReference(pedidoSetNewPedidoToAttach.getClass(), pedidoSetNewPedidoToAttach.getIdpedido());
                attachedPedidoSetNew.add(pedidoSetNewPedidoToAttach);
            }
            pedidoSetNew = attachedPedidoSetNew;
            planprocesoscalidad.setPedidoSet(pedidoSetNew);
            planprocesoscalidad = em.merge(planprocesoscalidad);
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad : detalleplanprocesoscalidadSetNew) {
                if (!detalleplanprocesoscalidadSetOld.contains(detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad)) {
                    Planprocesoscalidad oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad = detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad.getPlanprocesoscalidad();
                    detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad.setPlanprocesoscalidad(planprocesoscalidad);
                    detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad);
                    if (oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad != null && !oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad.equals(planprocesoscalidad)) {
                        oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad.getDetalleplanprocesoscalidadSet().remove(detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad);
                        oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad = em.merge(oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad);
                    }
                }
            }
            for (Pedido pedidoSetOldPedido : pedidoSetOld) {
                if (!pedidoSetNew.contains(pedidoSetOldPedido)) {
                    pedidoSetOldPedido.setPlanprocesoscalidad(null);
                    pedidoSetOldPedido = em.merge(pedidoSetOldPedido);
                }
            }
            for (Pedido pedidoSetNewPedido : pedidoSetNew) {
                if (!pedidoSetOld.contains(pedidoSetNewPedido)) {
                    Planprocesoscalidad oldPlanprocesoscalidadOfPedidoSetNewPedido = pedidoSetNewPedido.getPlanprocesoscalidad();
                    pedidoSetNewPedido.setPlanprocesoscalidad(planprocesoscalidad);
                    pedidoSetNewPedido = em.merge(pedidoSetNewPedido);
                    if (oldPlanprocesoscalidadOfPedidoSetNewPedido != null && !oldPlanprocesoscalidadOfPedidoSetNewPedido.equals(planprocesoscalidad)) {
                        oldPlanprocesoscalidadOfPedidoSetNewPedido.getPedidoSet().remove(pedidoSetNewPedido);
                        oldPlanprocesoscalidadOfPedidoSetNewPedido = em.merge(oldPlanprocesoscalidadOfPedidoSetNewPedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = planprocesoscalidad.getIdplanprocesoscalidad();
                if (findPlanprocesoscalidad(id) == null) {
                    throw new NonexistentEntityException("The planprocesoscalidad with id " + id + " no longer exists.");
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
            Planprocesoscalidad planprocesoscalidad;
            try {
                planprocesoscalidad = em.getReference(Planprocesoscalidad.class, id);
                planprocesoscalidad.getIdplanprocesoscalidad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planprocesoscalidad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSetOrphanCheck = planprocesoscalidad.getDetalleplanprocesoscalidadSet();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetOrphanCheckDetalleplanprocesoscalidad : detalleplanprocesoscalidadSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planprocesoscalidad (" + planprocesoscalidad + ") cannot be destroyed since the Detalleplanprocesoscalidad " + detalleplanprocesoscalidadSetOrphanCheckDetalleplanprocesoscalidad + " in its detalleplanprocesoscalidadSet field has a non-nullable planprocesoscalidad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Pedido> pedidoSet = planprocesoscalidad.getPedidoSet();
            for (Pedido pedidoSetPedido : pedidoSet) {
                pedidoSetPedido.setPlanprocesoscalidad(null);
                pedidoSetPedido = em.merge(pedidoSetPedido);
            }
            em.remove(planprocesoscalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Planprocesoscalidad> findPlanprocesoscalidadEntities() {
        return findPlanprocesoscalidadEntities(true, -1, -1);
    }

    public List<Planprocesoscalidad> findPlanprocesoscalidadEntities(int maxResults, int firstResult) {
        return findPlanprocesoscalidadEntities(false, maxResults, firstResult);
    }

    private List<Planprocesoscalidad> findPlanprocesoscalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Planprocesoscalidad.class));
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

    public Planprocesoscalidad findPlanprocesoscalidad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Planprocesoscalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanprocesoscalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Planprocesoscalidad> rt = cq.from(Planprocesoscalidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
