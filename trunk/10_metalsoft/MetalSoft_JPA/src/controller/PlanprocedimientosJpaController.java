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
        if (planprocedimientos.getPedidoSet1() == null) {
            planprocedimientos.setPedidoSet1(new HashSet<Pedido>());
        }
        if (planprocedimientos.getDetalleplanprocedimientosSet() == null) {
            planprocedimientos.setDetalleplanprocedimientosSet(new HashSet<Detalleplanprocedimientos>());
        }
        if (planprocedimientos.getDetalleplanprocedimientosSet1() == null) {
            planprocedimientos.setDetalleplanprocedimientosSet1(new HashSet<Detalleplanprocedimientos>());
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
            Set<Pedido> attachedPedidoSet1 = new HashSet<Pedido>();
            for (Pedido pedidoSet1PedidoToAttach : planprocedimientos.getPedidoSet1()) {
                pedidoSet1PedidoToAttach = em.getReference(pedidoSet1PedidoToAttach.getClass(), pedidoSet1PedidoToAttach.getIdpedido());
                attachedPedidoSet1.add(pedidoSet1PedidoToAttach);
            }
            planprocedimientos.setPedidoSet1(attachedPedidoSet1);
            Set<Detalleplanprocedimientos> attachedDetalleplanprocedimientosSet = new HashSet<Detalleplanprocedimientos>();
            for (Detalleplanprocedimientos detalleplanprocedimientosSetDetalleplanprocedimientosToAttach : planprocedimientos.getDetalleplanprocedimientosSet()) {
                detalleplanprocedimientosSetDetalleplanprocedimientosToAttach = em.getReference(detalleplanprocedimientosSetDetalleplanprocedimientosToAttach.getClass(), detalleplanprocedimientosSetDetalleplanprocedimientosToAttach.getDetalleplanprocedimientosPK());
                attachedDetalleplanprocedimientosSet.add(detalleplanprocedimientosSetDetalleplanprocedimientosToAttach);
            }
            planprocedimientos.setDetalleplanprocedimientosSet(attachedDetalleplanprocedimientosSet);
            Set<Detalleplanprocedimientos> attachedDetalleplanprocedimientosSet1 = new HashSet<Detalleplanprocedimientos>();
            for (Detalleplanprocedimientos detalleplanprocedimientosSet1DetalleplanprocedimientosToAttach : planprocedimientos.getDetalleplanprocedimientosSet1()) {
                detalleplanprocedimientosSet1DetalleplanprocedimientosToAttach = em.getReference(detalleplanprocedimientosSet1DetalleplanprocedimientosToAttach.getClass(), detalleplanprocedimientosSet1DetalleplanprocedimientosToAttach.getDetalleplanprocedimientosPK());
                attachedDetalleplanprocedimientosSet1.add(detalleplanprocedimientosSet1DetalleplanprocedimientosToAttach);
            }
            planprocedimientos.setDetalleplanprocedimientosSet1(attachedDetalleplanprocedimientosSet1);
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
            for (Pedido pedidoSet1Pedido : planprocedimientos.getPedidoSet1()) {
                Planprocedimientos oldPlanprocedimientos1OfPedidoSet1Pedido = pedidoSet1Pedido.getPlanprocedimientos1();
                pedidoSet1Pedido.setPlanprocedimientos1(planprocedimientos);
                pedidoSet1Pedido = em.merge(pedidoSet1Pedido);
                if (oldPlanprocedimientos1OfPedidoSet1Pedido != null) {
                    oldPlanprocedimientos1OfPedidoSet1Pedido.getPedidoSet1().remove(pedidoSet1Pedido);
                    oldPlanprocedimientos1OfPedidoSet1Pedido = em.merge(oldPlanprocedimientos1OfPedidoSet1Pedido);
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
            for (Detalleplanprocedimientos detalleplanprocedimientosSet1Detalleplanprocedimientos : planprocedimientos.getDetalleplanprocedimientosSet1()) {
                Planprocedimientos oldPlanprocedimientos1OfDetalleplanprocedimientosSet1Detalleplanprocedimientos = detalleplanprocedimientosSet1Detalleplanprocedimientos.getPlanprocedimientos1();
                detalleplanprocedimientosSet1Detalleplanprocedimientos.setPlanprocedimientos1(planprocedimientos);
                detalleplanprocedimientosSet1Detalleplanprocedimientos = em.merge(detalleplanprocedimientosSet1Detalleplanprocedimientos);
                if (oldPlanprocedimientos1OfDetalleplanprocedimientosSet1Detalleplanprocedimientos != null) {
                    oldPlanprocedimientos1OfDetalleplanprocedimientosSet1Detalleplanprocedimientos.getDetalleplanprocedimientosSet1().remove(detalleplanprocedimientosSet1Detalleplanprocedimientos);
                    oldPlanprocedimientos1OfDetalleplanprocedimientosSet1Detalleplanprocedimientos = em.merge(oldPlanprocedimientos1OfDetalleplanprocedimientosSet1Detalleplanprocedimientos);
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
            Set<Pedido> pedidoSet1Old = persistentPlanprocedimientos.getPedidoSet1();
            Set<Pedido> pedidoSet1New = planprocedimientos.getPedidoSet1();
            Set<Detalleplanprocedimientos> detalleplanprocedimientosSetOld = persistentPlanprocedimientos.getDetalleplanprocedimientosSet();
            Set<Detalleplanprocedimientos> detalleplanprocedimientosSetNew = planprocedimientos.getDetalleplanprocedimientosSet();
            Set<Detalleplanprocedimientos> detalleplanprocedimientosSet1Old = persistentPlanprocedimientos.getDetalleplanprocedimientosSet1();
            Set<Detalleplanprocedimientos> detalleplanprocedimientosSet1New = planprocedimientos.getDetalleplanprocedimientosSet1();
            List<String> illegalOrphanMessages = null;
            for (Detalleplanprocedimientos detalleplanprocedimientosSetOldDetalleplanprocedimientos : detalleplanprocedimientosSetOld) {
                if (!detalleplanprocedimientosSetNew.contains(detalleplanprocedimientosSetOldDetalleplanprocedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleplanprocedimientos " + detalleplanprocedimientosSetOldDetalleplanprocedimientos + " since its planprocedimientos field is not nullable.");
                }
            }
            for (Detalleplanprocedimientos detalleplanprocedimientosSet1OldDetalleplanprocedimientos : detalleplanprocedimientosSet1Old) {
                if (!detalleplanprocedimientosSet1New.contains(detalleplanprocedimientosSet1OldDetalleplanprocedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleplanprocedimientos " + detalleplanprocedimientosSet1OldDetalleplanprocedimientos + " since its planprocedimientos1 field is not nullable.");
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
            Set<Pedido> attachedPedidoSet1New = new HashSet<Pedido>();
            for (Pedido pedidoSet1NewPedidoToAttach : pedidoSet1New) {
                pedidoSet1NewPedidoToAttach = em.getReference(pedidoSet1NewPedidoToAttach.getClass(), pedidoSet1NewPedidoToAttach.getIdpedido());
                attachedPedidoSet1New.add(pedidoSet1NewPedidoToAttach);
            }
            pedidoSet1New = attachedPedidoSet1New;
            planprocedimientos.setPedidoSet1(pedidoSet1New);
            Set<Detalleplanprocedimientos> attachedDetalleplanprocedimientosSetNew = new HashSet<Detalleplanprocedimientos>();
            for (Detalleplanprocedimientos detalleplanprocedimientosSetNewDetalleplanprocedimientosToAttach : detalleplanprocedimientosSetNew) {
                detalleplanprocedimientosSetNewDetalleplanprocedimientosToAttach = em.getReference(detalleplanprocedimientosSetNewDetalleplanprocedimientosToAttach.getClass(), detalleplanprocedimientosSetNewDetalleplanprocedimientosToAttach.getDetalleplanprocedimientosPK());
                attachedDetalleplanprocedimientosSetNew.add(detalleplanprocedimientosSetNewDetalleplanprocedimientosToAttach);
            }
            detalleplanprocedimientosSetNew = attachedDetalleplanprocedimientosSetNew;
            planprocedimientos.setDetalleplanprocedimientosSet(detalleplanprocedimientosSetNew);
            Set<Detalleplanprocedimientos> attachedDetalleplanprocedimientosSet1New = new HashSet<Detalleplanprocedimientos>();
            for (Detalleplanprocedimientos detalleplanprocedimientosSet1NewDetalleplanprocedimientosToAttach : detalleplanprocedimientosSet1New) {
                detalleplanprocedimientosSet1NewDetalleplanprocedimientosToAttach = em.getReference(detalleplanprocedimientosSet1NewDetalleplanprocedimientosToAttach.getClass(), detalleplanprocedimientosSet1NewDetalleplanprocedimientosToAttach.getDetalleplanprocedimientosPK());
                attachedDetalleplanprocedimientosSet1New.add(detalleplanprocedimientosSet1NewDetalleplanprocedimientosToAttach);
            }
            detalleplanprocedimientosSet1New = attachedDetalleplanprocedimientosSet1New;
            planprocedimientos.setDetalleplanprocedimientosSet1(detalleplanprocedimientosSet1New);
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
            for (Pedido pedidoSet1OldPedido : pedidoSet1Old) {
                if (!pedidoSet1New.contains(pedidoSet1OldPedido)) {
                    pedidoSet1OldPedido.setPlanprocedimientos1(null);
                    pedidoSet1OldPedido = em.merge(pedidoSet1OldPedido);
                }
            }
            for (Pedido pedidoSet1NewPedido : pedidoSet1New) {
                if (!pedidoSet1Old.contains(pedidoSet1NewPedido)) {
                    Planprocedimientos oldPlanprocedimientos1OfPedidoSet1NewPedido = pedidoSet1NewPedido.getPlanprocedimientos1();
                    pedidoSet1NewPedido.setPlanprocedimientos1(planprocedimientos);
                    pedidoSet1NewPedido = em.merge(pedidoSet1NewPedido);
                    if (oldPlanprocedimientos1OfPedidoSet1NewPedido != null && !oldPlanprocedimientos1OfPedidoSet1NewPedido.equals(planprocedimientos)) {
                        oldPlanprocedimientos1OfPedidoSet1NewPedido.getPedidoSet1().remove(pedidoSet1NewPedido);
                        oldPlanprocedimientos1OfPedidoSet1NewPedido = em.merge(oldPlanprocedimientos1OfPedidoSet1NewPedido);
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
            for (Detalleplanprocedimientos detalleplanprocedimientosSet1NewDetalleplanprocedimientos : detalleplanprocedimientosSet1New) {
                if (!detalleplanprocedimientosSet1Old.contains(detalleplanprocedimientosSet1NewDetalleplanprocedimientos)) {
                    Planprocedimientos oldPlanprocedimientos1OfDetalleplanprocedimientosSet1NewDetalleplanprocedimientos = detalleplanprocedimientosSet1NewDetalleplanprocedimientos.getPlanprocedimientos1();
                    detalleplanprocedimientosSet1NewDetalleplanprocedimientos.setPlanprocedimientos1(planprocedimientos);
                    detalleplanprocedimientosSet1NewDetalleplanprocedimientos = em.merge(detalleplanprocedimientosSet1NewDetalleplanprocedimientos);
                    if (oldPlanprocedimientos1OfDetalleplanprocedimientosSet1NewDetalleplanprocedimientos != null && !oldPlanprocedimientos1OfDetalleplanprocedimientosSet1NewDetalleplanprocedimientos.equals(planprocedimientos)) {
                        oldPlanprocedimientos1OfDetalleplanprocedimientosSet1NewDetalleplanprocedimientos.getDetalleplanprocedimientosSet1().remove(detalleplanprocedimientosSet1NewDetalleplanprocedimientos);
                        oldPlanprocedimientos1OfDetalleplanprocedimientosSet1NewDetalleplanprocedimientos = em.merge(oldPlanprocedimientos1OfDetalleplanprocedimientosSet1NewDetalleplanprocedimientos);
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
            Set<Detalleplanprocedimientos> detalleplanprocedimientosSet1OrphanCheck = planprocedimientos.getDetalleplanprocedimientosSet1();
            for (Detalleplanprocedimientos detalleplanprocedimientosSet1OrphanCheckDetalleplanprocedimientos : detalleplanprocedimientosSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planprocedimientos (" + planprocedimientos + ") cannot be destroyed since the Detalleplanprocedimientos " + detalleplanprocedimientosSet1OrphanCheckDetalleplanprocedimientos + " in its detalleplanprocedimientosSet1 field has a non-nullable planprocedimientos1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Pedido> pedidoSet = planprocedimientos.getPedidoSet();
            for (Pedido pedidoSetPedido : pedidoSet) {
                pedidoSetPedido.setPlanprocedimientos(null);
                pedidoSetPedido = em.merge(pedidoSetPedido);
            }
            Set<Pedido> pedidoSet1 = planprocedimientos.getPedidoSet1();
            for (Pedido pedidoSet1Pedido : pedidoSet1) {
                pedidoSet1Pedido.setPlanprocedimientos1(null);
                pedidoSet1Pedido = em.merge(pedidoSet1Pedido);
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
