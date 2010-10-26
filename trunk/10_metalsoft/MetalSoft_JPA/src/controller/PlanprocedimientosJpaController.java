/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Planprocedimientos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Pedido;
import java.util.HashSet;
import java.util.Set;
import entity.Detalleplanprocedimientos;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class PlanprocedimientosJpaController {

    public PlanprocedimientosJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planprocedimientos planprocedimientos) throws PreexistingEntityException, Exception {
        if (planprocedimientos.getPedidoSet() == null) {
            planprocedimientos.setPedidoSet(new HashSet<Pedido>());
        }
        if (planprocedimientos.getDetalleplanprocedimientosSet() == null) {
            planprocedimientos.setDetalleplanprocedimientosSet(new HashSet<Detalleplanprocedimientos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Pedido> attachedPedidoSet = new HashSet<Pedido>();
            for (Pedido pedidoSetPedidoToAttach : planprocedimientos.getPedidoSet()) {
                pedidoSetPedidoToAttach = em.getReference(pedidoSetPedidoToAttach.getClass(), pedidoSetPedidoToAttach.getIdpedido());
                attachedPedidoSet.add(pedidoSetPedidoToAttach);
            }
            planprocedimientos.setPedidoSet(attachedPedidoSet);
            Set<Detalleplanprocedimientos> attachedDetalleplanprocedimientosSet = new HashSet<Detalleplanprocedimientos>();
            for (Detalleplanprocedimientos detalleplanprocedimientosSetDetalleplanprocedimientosToAttach : planprocedimientos.getDetalleplanprocedimientosSet()) {
                detalleplanprocedimientosSetDetalleplanprocedimientosToAttach = em.getReference(detalleplanprocedimientosSetDetalleplanprocedimientosToAttach.getClass(), detalleplanprocedimientosSetDetalleplanprocedimientosToAttach.getDetalleplanprocedimientosPK());
                attachedDetalleplanprocedimientosSet.add(detalleplanprocedimientosSetDetalleplanprocedimientosToAttach);
            }
            planprocedimientos.setDetalleplanprocedimientosSet(attachedDetalleplanprocedimientosSet);
            em.persist(planprocedimientos);
            for (Pedido pedidoSetPedido : planprocedimientos.getPedidoSet()) {
                Planprocedimientos oldPlanprocedimientosOfPedidoSetPedido = pedidoSetPedido.getPlanprocedimientos();
                pedidoSetPedido.setPlanprocedimientos(planprocedimientos);
                pedidoSetPedido = em.merge(pedidoSetPedido);
                if (oldPlanprocedimientosOfPedidoSetPedido != null) {
                    oldPlanprocedimientosOfPedidoSetPedido.getPedidoSet().remove(pedidoSetPedido);
                    oldPlanprocedimientosOfPedidoSetPedido = em.merge(oldPlanprocedimientosOfPedidoSetPedido);
                }
            }
            for (Detalleplanprocedimientos detalleplanprocedimientosSetDetalleplanprocedimientos : planprocedimientos.getDetalleplanprocedimientosSet()) {
                Planprocedimientos oldPlanprocedimientosOfDetalleplanprocedimientosSetDetalleplanprocedimientos = detalleplanprocedimientosSetDetalleplanprocedimientos.getPlanprocedimientos();
                detalleplanprocedimientosSetDetalleplanprocedimientos.setPlanprocedimientos(planprocedimientos);
                detalleplanprocedimientosSetDetalleplanprocedimientos = em.merge(detalleplanprocedimientosSetDetalleplanprocedimientos);
                if (oldPlanprocedimientosOfDetalleplanprocedimientosSetDetalleplanprocedimientos != null) {
                    oldPlanprocedimientosOfDetalleplanprocedimientosSetDetalleplanprocedimientos.getDetalleplanprocedimientosSet().remove(detalleplanprocedimientosSetDetalleplanprocedimientos);
                    oldPlanprocedimientosOfDetalleplanprocedimientosSetDetalleplanprocedimientos = em.merge(oldPlanprocedimientosOfDetalleplanprocedimientosSetDetalleplanprocedimientos);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPlanprocedimientos(planprocedimientos.getIdplanprocedimientos()) != null) {
                throw new PreexistingEntityException("Planprocedimientos " + planprocedimientos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Planprocedimientos planprocedimientos) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planprocedimientos persistentPlanprocedimientos = em.find(Planprocedimientos.class, planprocedimientos.getIdplanprocedimientos());
            Set<Pedido> pedidoSetOld = persistentPlanprocedimientos.getPedidoSet();
            Set<Pedido> pedidoSetNew = planprocedimientos.getPedidoSet();
            Set<Detalleplanprocedimientos> detalleplanprocedimientosSetOld = persistentPlanprocedimientos.getDetalleplanprocedimientosSet();
            Set<Detalleplanprocedimientos> detalleplanprocedimientosSetNew = planprocedimientos.getDetalleplanprocedimientosSet();
            List<String> illegalOrphanMessages = null;
            for (Detalleplanprocedimientos detalleplanprocedimientosSetOldDetalleplanprocedimientos : detalleplanprocedimientosSetOld) {
                if (!detalleplanprocedimientosSetNew.contains(detalleplanprocedimientosSetOldDetalleplanprocedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleplanprocedimientos " + detalleplanprocedimientosSetOldDetalleplanprocedimientos + " since its planprocedimientos field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Pedido> attachedPedidoSetNew = new HashSet<Pedido>();
            for (Pedido pedidoSetNewPedidoToAttach : pedidoSetNew) {
                pedidoSetNewPedidoToAttach = em.getReference(pedidoSetNewPedidoToAttach.getClass(), pedidoSetNewPedidoToAttach.getIdpedido());
                attachedPedidoSetNew.add(pedidoSetNewPedidoToAttach);
            }
            pedidoSetNew = attachedPedidoSetNew;
            planprocedimientos.setPedidoSet(pedidoSetNew);
            Set<Detalleplanprocedimientos> attachedDetalleplanprocedimientosSetNew = new HashSet<Detalleplanprocedimientos>();
            for (Detalleplanprocedimientos detalleplanprocedimientosSetNewDetalleplanprocedimientosToAttach : detalleplanprocedimientosSetNew) {
                detalleplanprocedimientosSetNewDetalleplanprocedimientosToAttach = em.getReference(detalleplanprocedimientosSetNewDetalleplanprocedimientosToAttach.getClass(), detalleplanprocedimientosSetNewDetalleplanprocedimientosToAttach.getDetalleplanprocedimientosPK());
                attachedDetalleplanprocedimientosSetNew.add(detalleplanprocedimientosSetNewDetalleplanprocedimientosToAttach);
            }
            detalleplanprocedimientosSetNew = attachedDetalleplanprocedimientosSetNew;
            planprocedimientos.setDetalleplanprocedimientosSet(detalleplanprocedimientosSetNew);
            planprocedimientos = em.merge(planprocedimientos);
            for (Pedido pedidoSetOldPedido : pedidoSetOld) {
                if (!pedidoSetNew.contains(pedidoSetOldPedido)) {
                    pedidoSetOldPedido.setPlanprocedimientos(null);
                    pedidoSetOldPedido = em.merge(pedidoSetOldPedido);
                }
            }
            for (Pedido pedidoSetNewPedido : pedidoSetNew) {
                if (!pedidoSetOld.contains(pedidoSetNewPedido)) {
                    Planprocedimientos oldPlanprocedimientosOfPedidoSetNewPedido = pedidoSetNewPedido.getPlanprocedimientos();
                    pedidoSetNewPedido.setPlanprocedimientos(planprocedimientos);
                    pedidoSetNewPedido = em.merge(pedidoSetNewPedido);
                    if (oldPlanprocedimientosOfPedidoSetNewPedido != null && !oldPlanprocedimientosOfPedidoSetNewPedido.equals(planprocedimientos)) {
                        oldPlanprocedimientosOfPedidoSetNewPedido.getPedidoSet().remove(pedidoSetNewPedido);
                        oldPlanprocedimientosOfPedidoSetNewPedido = em.merge(oldPlanprocedimientosOfPedidoSetNewPedido);
                    }
                }
            }
            for (Detalleplanprocedimientos detalleplanprocedimientosSetNewDetalleplanprocedimientos : detalleplanprocedimientosSetNew) {
                if (!detalleplanprocedimientosSetOld.contains(detalleplanprocedimientosSetNewDetalleplanprocedimientos)) {
                    Planprocedimientos oldPlanprocedimientosOfDetalleplanprocedimientosSetNewDetalleplanprocedimientos = detalleplanprocedimientosSetNewDetalleplanprocedimientos.getPlanprocedimientos();
                    detalleplanprocedimientosSetNewDetalleplanprocedimientos.setPlanprocedimientos(planprocedimientos);
                    detalleplanprocedimientosSetNewDetalleplanprocedimientos = em.merge(detalleplanprocedimientosSetNewDetalleplanprocedimientos);
                    if (oldPlanprocedimientosOfDetalleplanprocedimientosSetNewDetalleplanprocedimientos != null && !oldPlanprocedimientosOfDetalleplanprocedimientosSetNewDetalleplanprocedimientos.equals(planprocedimientos)) {
                        oldPlanprocedimientosOfDetalleplanprocedimientosSetNewDetalleplanprocedimientos.getDetalleplanprocedimientosSet().remove(detalleplanprocedimientosSetNewDetalleplanprocedimientos);
                        oldPlanprocedimientosOfDetalleplanprocedimientosSetNewDetalleplanprocedimientos = em.merge(oldPlanprocedimientosOfDetalleplanprocedimientosSetNewDetalleplanprocedimientos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = planprocedimientos.getIdplanprocedimientos();
                if (findPlanprocedimientos(id) == null) {
                    throw new NonexistentEntityException("The planprocedimientos with id " + id + " no longer exists.");
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
            Planprocedimientos planprocedimientos;
            try {
                planprocedimientos = em.getReference(Planprocedimientos.class, id);
                planprocedimientos.getIdplanprocedimientos();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planprocedimientos with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detalleplanprocedimientos> detalleplanprocedimientosSetOrphanCheck = planprocedimientos.getDetalleplanprocedimientosSet();
            for (Detalleplanprocedimientos detalleplanprocedimientosSetOrphanCheckDetalleplanprocedimientos : detalleplanprocedimientosSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planprocedimientos (" + planprocedimientos + ") cannot be destroyed since the Detalleplanprocedimientos " + detalleplanprocedimientosSetOrphanCheckDetalleplanprocedimientos + " in its detalleplanprocedimientosSet field has a non-nullable planprocedimientos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Pedido> pedidoSet = planprocedimientos.getPedidoSet();
            for (Pedido pedidoSetPedido : pedidoSet) {
                pedidoSetPedido.setPlanprocedimientos(null);
                pedidoSetPedido = em.merge(pedidoSetPedido);
            }
            em.remove(planprocedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Planprocedimientos> findPlanprocedimientosEntities() {
        return findPlanprocedimientosEntities(true, -1, -1);
    }

    public List<Planprocedimientos> findPlanprocedimientosEntities(int maxResults, int firstResult) {
        return findPlanprocedimientosEntities(false, maxResults, firstResult);
    }

    private List<Planprocedimientos> findPlanprocedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Planprocedimientos.class));
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

    public Planprocedimientos findPlanprocedimientos(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Planprocedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanprocedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Planprocedimientos> rt = cq.from(Planprocedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
