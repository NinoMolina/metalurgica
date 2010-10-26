/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Planrequerimientosmateriaprima;
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
import entity.Detallerequerimientosmateriaprima;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class PlanrequerimientosmateriaprimaJpaController {

    public PlanrequerimientosmateriaprimaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planrequerimientosmateriaprima planrequerimientosmateriaprima) throws PreexistingEntityException, Exception {
        if (planrequerimientosmateriaprima.getPedidoSet() == null) {
            planrequerimientosmateriaprima.setPedidoSet(new HashSet<Pedido>());
        }
        if (planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet() == null) {
            planrequerimientosmateriaprima.setDetallerequerimientosmateriaprimaSet(new HashSet<Detallerequerimientosmateriaprima>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Pedido> attachedPedidoSet = new HashSet<Pedido>();
            for (Pedido pedidoSetPedidoToAttach : planrequerimientosmateriaprima.getPedidoSet()) {
                pedidoSetPedidoToAttach = em.getReference(pedidoSetPedidoToAttach.getClass(), pedidoSetPedidoToAttach.getIdpedido());
                attachedPedidoSet.add(pedidoSetPedidoToAttach);
            }
            planrequerimientosmateriaprima.setPedidoSet(attachedPedidoSet);
            Set<Detallerequerimientosmateriaprima> attachedDetallerequerimientosmateriaprimaSet = new HashSet<Detallerequerimientosmateriaprima>();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprimaToAttach : planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet()) {
                detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprimaToAttach = em.getReference(detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprimaToAttach.getClass(), detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprimaToAttach.getDetallerequerimientosmateriaprimaPK());
                attachedDetallerequerimientosmateriaprimaSet.add(detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprimaToAttach);
            }
            planrequerimientosmateriaprima.setDetallerequerimientosmateriaprimaSet(attachedDetallerequerimientosmateriaprimaSet);
            em.persist(planrequerimientosmateriaprima);
            for (Pedido pedidoSetPedido : planrequerimientosmateriaprima.getPedidoSet()) {
                Planrequerimientosmateriaprima oldPlanrequerimientosmateriaprimaOfPedidoSetPedido = pedidoSetPedido.getPlanrequerimientosmateriaprima();
                pedidoSetPedido.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprima);
                pedidoSetPedido = em.merge(pedidoSetPedido);
                if (oldPlanrequerimientosmateriaprimaOfPedidoSetPedido != null) {
                    oldPlanrequerimientosmateriaprimaOfPedidoSetPedido.getPedidoSet().remove(pedidoSetPedido);
                    oldPlanrequerimientosmateriaprimaOfPedidoSetPedido = em.merge(oldPlanrequerimientosmateriaprimaOfPedidoSetPedido);
                }
            }
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima : planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet()) {
                Planrequerimientosmateriaprima oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima = detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima();
                detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprima);
                detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima);
                if (oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima != null) {
                    oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet().remove(detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima);
                    oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima = em.merge(oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPlanrequerimientosmateriaprima(planrequerimientosmateriaprima.getIdplanrequerimientosmateriaprima()) != null) {
                throw new PreexistingEntityException("Planrequerimientosmateriaprima " + planrequerimientosmateriaprima + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Planrequerimientosmateriaprima planrequerimientosmateriaprima) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planrequerimientosmateriaprima persistentPlanrequerimientosmateriaprima = em.find(Planrequerimientosmateriaprima.class, planrequerimientosmateriaprima.getIdplanrequerimientosmateriaprima());
            Set<Pedido> pedidoSetOld = persistentPlanrequerimientosmateriaprima.getPedidoSet();
            Set<Pedido> pedidoSetNew = planrequerimientosmateriaprima.getPedidoSet();
            Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSetOld = persistentPlanrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet();
            Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSetNew = planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet();
            List<String> illegalOrphanMessages = null;
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSetOldDetallerequerimientosmateriaprima : detallerequerimientosmateriaprimaSetOld) {
                if (!detallerequerimientosmateriaprimaSetNew.contains(detallerequerimientosmateriaprimaSetOldDetallerequerimientosmateriaprima)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallerequerimientosmateriaprima " + detallerequerimientosmateriaprimaSetOldDetallerequerimientosmateriaprima + " since its planrequerimientosmateriaprima field is not nullable.");
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
            planrequerimientosmateriaprima.setPedidoSet(pedidoSetNew);
            Set<Detallerequerimientosmateriaprima> attachedDetallerequerimientosmateriaprimaSetNew = new HashSet<Detallerequerimientosmateriaprima>();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprimaToAttach : detallerequerimientosmateriaprimaSetNew) {
                detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprimaToAttach = em.getReference(detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprimaToAttach.getClass(), detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprimaToAttach.getDetallerequerimientosmateriaprimaPK());
                attachedDetallerequerimientosmateriaprimaSetNew.add(detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprimaToAttach);
            }
            detallerequerimientosmateriaprimaSetNew = attachedDetallerequerimientosmateriaprimaSetNew;
            planrequerimientosmateriaprima.setDetallerequerimientosmateriaprimaSet(detallerequerimientosmateriaprimaSetNew);
            planrequerimientosmateriaprima = em.merge(planrequerimientosmateriaprima);
            for (Pedido pedidoSetOldPedido : pedidoSetOld) {
                if (!pedidoSetNew.contains(pedidoSetOldPedido)) {
                    pedidoSetOldPedido.setPlanrequerimientosmateriaprima(null);
                    pedidoSetOldPedido = em.merge(pedidoSetOldPedido);
                }
            }
            for (Pedido pedidoSetNewPedido : pedidoSetNew) {
                if (!pedidoSetOld.contains(pedidoSetNewPedido)) {
                    Planrequerimientosmateriaprima oldPlanrequerimientosmateriaprimaOfPedidoSetNewPedido = pedidoSetNewPedido.getPlanrequerimientosmateriaprima();
                    pedidoSetNewPedido.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprima);
                    pedidoSetNewPedido = em.merge(pedidoSetNewPedido);
                    if (oldPlanrequerimientosmateriaprimaOfPedidoSetNewPedido != null && !oldPlanrequerimientosmateriaprimaOfPedidoSetNewPedido.equals(planrequerimientosmateriaprima)) {
                        oldPlanrequerimientosmateriaprimaOfPedidoSetNewPedido.getPedidoSet().remove(pedidoSetNewPedido);
                        oldPlanrequerimientosmateriaprimaOfPedidoSetNewPedido = em.merge(oldPlanrequerimientosmateriaprimaOfPedidoSetNewPedido);
                    }
                }
            }
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima : detallerequerimientosmateriaprimaSetNew) {
                if (!detallerequerimientosmateriaprimaSetOld.contains(detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima)) {
                    Planrequerimientosmateriaprima oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima = detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima();
                    detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprima);
                    detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima);
                    if (oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima != null && !oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima.equals(planrequerimientosmateriaprima)) {
                        oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet().remove(detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima);
                        oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima = em.merge(oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = planrequerimientosmateriaprima.getIdplanrequerimientosmateriaprima();
                if (findPlanrequerimientosmateriaprima(id) == null) {
                    throw new NonexistentEntityException("The planrequerimientosmateriaprima with id " + id + " no longer exists.");
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
            Planrequerimientosmateriaprima planrequerimientosmateriaprima;
            try {
                planrequerimientosmateriaprima = em.getReference(Planrequerimientosmateriaprima.class, id);
                planrequerimientosmateriaprima.getIdplanrequerimientosmateriaprima();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planrequerimientosmateriaprima with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSetOrphanCheck = planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSetOrphanCheckDetallerequerimientosmateriaprima : detallerequerimientosmateriaprimaSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planrequerimientosmateriaprima (" + planrequerimientosmateriaprima + ") cannot be destroyed since the Detallerequerimientosmateriaprima " + detallerequerimientosmateriaprimaSetOrphanCheckDetallerequerimientosmateriaprima + " in its detallerequerimientosmateriaprimaSet field has a non-nullable planrequerimientosmateriaprima field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Pedido> pedidoSet = planrequerimientosmateriaprima.getPedidoSet();
            for (Pedido pedidoSetPedido : pedidoSet) {
                pedidoSetPedido.setPlanrequerimientosmateriaprima(null);
                pedidoSetPedido = em.merge(pedidoSetPedido);
            }
            em.remove(planrequerimientosmateriaprima);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Planrequerimientosmateriaprima> findPlanrequerimientosmateriaprimaEntities() {
        return findPlanrequerimientosmateriaprimaEntities(true, -1, -1);
    }

    public List<Planrequerimientosmateriaprima> findPlanrequerimientosmateriaprimaEntities(int maxResults, int firstResult) {
        return findPlanrequerimientosmateriaprimaEntities(false, maxResults, firstResult);
    }

    private List<Planrequerimientosmateriaprima> findPlanrequerimientosmateriaprimaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Planrequerimientosmateriaprima.class));
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

    public Planrequerimientosmateriaprima findPlanrequerimientosmateriaprima(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Planrequerimientosmateriaprima.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanrequerimientosmateriaprimaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Planrequerimientosmateriaprima> rt = cq.from(Planrequerimientosmateriaprima.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
