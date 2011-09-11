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
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Pedido;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Detallerequerimientosmateriaprima;
import metalsoft.datos.jpa.entity.Planrequerimientosmateriaprima;

/**
 *
 * @author Nino
 */
public class PlanrequerimientosmateriaprimaJpaController implements Serializable {

    public PlanrequerimientosmateriaprimaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planrequerimientosmateriaprima planrequerimientosmateriaprima) throws PreexistingEntityException, Exception {
        if (planrequerimientosmateriaprima.getPedidoList() == null) {
            planrequerimientosmateriaprima.setPedidoList(new ArrayList<Pedido>());
        }
        if (planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaList() == null) {
            planrequerimientosmateriaprima.setDetallerequerimientosmateriaprimaList(new ArrayList<Detallerequerimientosmateriaprima>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pedido> attachedPedidoList = new ArrayList<Pedido>();
            for (Pedido pedidoListPedidoToAttach : planrequerimientosmateriaprima.getPedidoList()) {
                pedidoListPedidoToAttach = em.getReference(pedidoListPedidoToAttach.getClass(), pedidoListPedidoToAttach.getIdpedido());
                attachedPedidoList.add(pedidoListPedidoToAttach);
            }
            planrequerimientosmateriaprima.setPedidoList(attachedPedidoList);
            List<Detallerequerimientosmateriaprima> attachedDetallerequerimientosmateriaprimaList = new ArrayList<Detallerequerimientosmateriaprima>();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprimaToAttach : planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaList()) {
                detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprimaToAttach = em.getReference(detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprimaToAttach.getClass(), detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprimaToAttach.getDetallerequerimientosmateriaprimaPK());
                attachedDetallerequerimientosmateriaprimaList.add(detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprimaToAttach);
            }
            planrequerimientosmateriaprima.setDetallerequerimientosmateriaprimaList(attachedDetallerequerimientosmateriaprimaList);
            em.persist(planrequerimientosmateriaprima);
            for (Pedido pedidoListPedido : planrequerimientosmateriaprima.getPedidoList()) {
                Planrequerimientosmateriaprima oldPlanrequerimientosmateriaprimaOfPedidoListPedido = pedidoListPedido.getPlanrequerimientosmateriaprima();
                pedidoListPedido.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprima);
                pedidoListPedido = em.merge(pedidoListPedido);
                if (oldPlanrequerimientosmateriaprimaOfPedidoListPedido != null) {
                    oldPlanrequerimientosmateriaprimaOfPedidoListPedido.getPedidoList().remove(pedidoListPedido);
                    oldPlanrequerimientosmateriaprimaOfPedidoListPedido = em.merge(oldPlanrequerimientosmateriaprimaOfPedidoListPedido);
                }
            }
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima : planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaList()) {
                Planrequerimientosmateriaprima oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima = detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima();
                detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprima);
                detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima);
                if (oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima != null) {
                    oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaList().remove(detallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima);
                    oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima = em.merge(oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaListDetallerequerimientosmateriaprima);
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
            List<Pedido> pedidoListOld = persistentPlanrequerimientosmateriaprima.getPedidoList();
            List<Pedido> pedidoListNew = planrequerimientosmateriaprima.getPedidoList();
            List<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaListOld = persistentPlanrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaList();
            List<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaListNew = planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaList();
            List<String> illegalOrphanMessages = null;
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaListOldDetallerequerimientosmateriaprima : detallerequerimientosmateriaprimaListOld) {
                if (!detallerequerimientosmateriaprimaListNew.contains(detallerequerimientosmateriaprimaListOldDetallerequerimientosmateriaprima)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallerequerimientosmateriaprima " + detallerequerimientosmateriaprimaListOldDetallerequerimientosmateriaprima + " since its planrequerimientosmateriaprima field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Pedido> attachedPedidoListNew = new ArrayList<Pedido>();
            for (Pedido pedidoListNewPedidoToAttach : pedidoListNew) {
                pedidoListNewPedidoToAttach = em.getReference(pedidoListNewPedidoToAttach.getClass(), pedidoListNewPedidoToAttach.getIdpedido());
                attachedPedidoListNew.add(pedidoListNewPedidoToAttach);
            }
            pedidoListNew = attachedPedidoListNew;
            planrequerimientosmateriaprima.setPedidoList(pedidoListNew);
            List<Detallerequerimientosmateriaprima> attachedDetallerequerimientosmateriaprimaListNew = new ArrayList<Detallerequerimientosmateriaprima>();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprimaToAttach : detallerequerimientosmateriaprimaListNew) {
                detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprimaToAttach = em.getReference(detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprimaToAttach.getClass(), detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprimaToAttach.getDetallerequerimientosmateriaprimaPK());
                attachedDetallerequerimientosmateriaprimaListNew.add(detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprimaToAttach);
            }
            detallerequerimientosmateriaprimaListNew = attachedDetallerequerimientosmateriaprimaListNew;
            planrequerimientosmateriaprima.setDetallerequerimientosmateriaprimaList(detallerequerimientosmateriaprimaListNew);
            planrequerimientosmateriaprima = em.merge(planrequerimientosmateriaprima);
            for (Pedido pedidoListOldPedido : pedidoListOld) {
                if (!pedidoListNew.contains(pedidoListOldPedido)) {
                    pedidoListOldPedido.setPlanrequerimientosmateriaprima(null);
                    pedidoListOldPedido = em.merge(pedidoListOldPedido);
                }
            }
            for (Pedido pedidoListNewPedido : pedidoListNew) {
                if (!pedidoListOld.contains(pedidoListNewPedido)) {
                    Planrequerimientosmateriaprima oldPlanrequerimientosmateriaprimaOfPedidoListNewPedido = pedidoListNewPedido.getPlanrequerimientosmateriaprima();
                    pedidoListNewPedido.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprima);
                    pedidoListNewPedido = em.merge(pedidoListNewPedido);
                    if (oldPlanrequerimientosmateriaprimaOfPedidoListNewPedido != null && !oldPlanrequerimientosmateriaprimaOfPedidoListNewPedido.equals(planrequerimientosmateriaprima)) {
                        oldPlanrequerimientosmateriaprimaOfPedidoListNewPedido.getPedidoList().remove(pedidoListNewPedido);
                        oldPlanrequerimientosmateriaprimaOfPedidoListNewPedido = em.merge(oldPlanrequerimientosmateriaprimaOfPedidoListNewPedido);
                    }
                }
            }
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima : detallerequerimientosmateriaprimaListNew) {
                if (!detallerequerimientosmateriaprimaListOld.contains(detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima)) {
                    Planrequerimientosmateriaprima oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima = detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima();
                    detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprima);
                    detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima);
                    if (oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima != null && !oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima.equals(planrequerimientosmateriaprima)) {
                        oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaList().remove(detallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima);
                        oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima = em.merge(oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaListNewDetallerequerimientosmateriaprima);
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
            List<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaListOrphanCheck = planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaList();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaListOrphanCheckDetallerequerimientosmateriaprima : detallerequerimientosmateriaprimaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planrequerimientosmateriaprima (" + planrequerimientosmateriaprima + ") cannot be destroyed since the Detallerequerimientosmateriaprima " + detallerequerimientosmateriaprimaListOrphanCheckDetallerequerimientosmateriaprima + " in its detallerequerimientosmateriaprimaList field has a non-nullable planrequerimientosmateriaprima field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Pedido> pedidoList = planrequerimientosmateriaprima.getPedidoList();
            for (Pedido pedidoListPedido : pedidoList) {
                pedidoListPedido.setPlanrequerimientosmateriaprima(null);
                pedidoListPedido = em.merge(pedidoListPedido);
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
