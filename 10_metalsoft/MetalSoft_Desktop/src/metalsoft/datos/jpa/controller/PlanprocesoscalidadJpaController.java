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
import metalsoft.datos.jpa.entity.Detalleplanprocesoscalidad;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.datos.jpa.entity.Planprocesoscalidad;

/**
 *
 * @author Nino
 */
public class PlanprocesoscalidadJpaController implements Serializable {

    public PlanprocesoscalidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planprocesoscalidad planprocesoscalidad) throws PreexistingEntityException, Exception {
        if (planprocesoscalidad.getDetalleplanprocesoscalidadList() == null) {
            planprocesoscalidad.setDetalleplanprocesoscalidadList(new ArrayList<Detalleplanprocesoscalidad>());
        }
        if (planprocesoscalidad.getPedidoList() == null) {
            planprocesoscalidad.setPedidoList(new ArrayList<Pedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Detalleplanprocesoscalidad> attachedDetalleplanprocesoscalidadList = new ArrayList<Detalleplanprocesoscalidad>();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadListDetalleplanprocesoscalidadToAttach : planprocesoscalidad.getDetalleplanprocesoscalidadList()) {
                detalleplanprocesoscalidadListDetalleplanprocesoscalidadToAttach = em.getReference(detalleplanprocesoscalidadListDetalleplanprocesoscalidadToAttach.getClass(), detalleplanprocesoscalidadListDetalleplanprocesoscalidadToAttach.getDetalleplanprocesoscalidadPK());
                attachedDetalleplanprocesoscalidadList.add(detalleplanprocesoscalidadListDetalleplanprocesoscalidadToAttach);
            }
            planprocesoscalidad.setDetalleplanprocesoscalidadList(attachedDetalleplanprocesoscalidadList);
            List<Pedido> attachedPedidoList = new ArrayList<Pedido>();
            for (Pedido pedidoListPedidoToAttach : planprocesoscalidad.getPedidoList()) {
                pedidoListPedidoToAttach = em.getReference(pedidoListPedidoToAttach.getClass(), pedidoListPedidoToAttach.getIdpedido());
                attachedPedidoList.add(pedidoListPedidoToAttach);
            }
            planprocesoscalidad.setPedidoList(attachedPedidoList);
            em.persist(planprocesoscalidad);
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadListDetalleplanprocesoscalidad : planprocesoscalidad.getDetalleplanprocesoscalidadList()) {
                Planprocesoscalidad oldPlanprocesoscalidadOfDetalleplanprocesoscalidadListDetalleplanprocesoscalidad = detalleplanprocesoscalidadListDetalleplanprocesoscalidad.getPlanprocesoscalidad();
                detalleplanprocesoscalidadListDetalleplanprocesoscalidad.setPlanprocesoscalidad(planprocesoscalidad);
                detalleplanprocesoscalidadListDetalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadListDetalleplanprocesoscalidad);
                if (oldPlanprocesoscalidadOfDetalleplanprocesoscalidadListDetalleplanprocesoscalidad != null) {
                    oldPlanprocesoscalidadOfDetalleplanprocesoscalidadListDetalleplanprocesoscalidad.getDetalleplanprocesoscalidadList().remove(detalleplanprocesoscalidadListDetalleplanprocesoscalidad);
                    oldPlanprocesoscalidadOfDetalleplanprocesoscalidadListDetalleplanprocesoscalidad = em.merge(oldPlanprocesoscalidadOfDetalleplanprocesoscalidadListDetalleplanprocesoscalidad);
                }
            }
            for (Pedido pedidoListPedido : planprocesoscalidad.getPedidoList()) {
                Planprocesoscalidad oldPlanprocesoscalidadOfPedidoListPedido = pedidoListPedido.getPlanprocesoscalidad();
                pedidoListPedido.setPlanprocesoscalidad(planprocesoscalidad);
                pedidoListPedido = em.merge(pedidoListPedido);
                if (oldPlanprocesoscalidadOfPedidoListPedido != null) {
                    oldPlanprocesoscalidadOfPedidoListPedido.getPedidoList().remove(pedidoListPedido);
                    oldPlanprocesoscalidadOfPedidoListPedido = em.merge(oldPlanprocesoscalidadOfPedidoListPedido);
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
            List<Detalleplanprocesoscalidad> detalleplanprocesoscalidadListOld = persistentPlanprocesoscalidad.getDetalleplanprocesoscalidadList();
            List<Detalleplanprocesoscalidad> detalleplanprocesoscalidadListNew = planprocesoscalidad.getDetalleplanprocesoscalidadList();
            List<Pedido> pedidoListOld = persistentPlanprocesoscalidad.getPedidoList();
            List<Pedido> pedidoListNew = planprocesoscalidad.getPedidoList();
            List<String> illegalOrphanMessages = null;
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadListOldDetalleplanprocesoscalidad : detalleplanprocesoscalidadListOld) {
                if (!detalleplanprocesoscalidadListNew.contains(detalleplanprocesoscalidadListOldDetalleplanprocesoscalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleplanprocesoscalidad " + detalleplanprocesoscalidadListOldDetalleplanprocesoscalidad + " since its planprocesoscalidad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Detalleplanprocesoscalidad> attachedDetalleplanprocesoscalidadListNew = new ArrayList<Detalleplanprocesoscalidad>();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadListNewDetalleplanprocesoscalidadToAttach : detalleplanprocesoscalidadListNew) {
                detalleplanprocesoscalidadListNewDetalleplanprocesoscalidadToAttach = em.getReference(detalleplanprocesoscalidadListNewDetalleplanprocesoscalidadToAttach.getClass(), detalleplanprocesoscalidadListNewDetalleplanprocesoscalidadToAttach.getDetalleplanprocesoscalidadPK());
                attachedDetalleplanprocesoscalidadListNew.add(detalleplanprocesoscalidadListNewDetalleplanprocesoscalidadToAttach);
            }
            detalleplanprocesoscalidadListNew = attachedDetalleplanprocesoscalidadListNew;
            planprocesoscalidad.setDetalleplanprocesoscalidadList(detalleplanprocesoscalidadListNew);
            List<Pedido> attachedPedidoListNew = new ArrayList<Pedido>();
            for (Pedido pedidoListNewPedidoToAttach : pedidoListNew) {
                pedidoListNewPedidoToAttach = em.getReference(pedidoListNewPedidoToAttach.getClass(), pedidoListNewPedidoToAttach.getIdpedido());
                attachedPedidoListNew.add(pedidoListNewPedidoToAttach);
            }
            pedidoListNew = attachedPedidoListNew;
            planprocesoscalidad.setPedidoList(pedidoListNew);
            planprocesoscalidad = em.merge(planprocesoscalidad);
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadListNewDetalleplanprocesoscalidad : detalleplanprocesoscalidadListNew) {
                if (!detalleplanprocesoscalidadListOld.contains(detalleplanprocesoscalidadListNewDetalleplanprocesoscalidad)) {
                    Planprocesoscalidad oldPlanprocesoscalidadOfDetalleplanprocesoscalidadListNewDetalleplanprocesoscalidad = detalleplanprocesoscalidadListNewDetalleplanprocesoscalidad.getPlanprocesoscalidad();
                    detalleplanprocesoscalidadListNewDetalleplanprocesoscalidad.setPlanprocesoscalidad(planprocesoscalidad);
                    detalleplanprocesoscalidadListNewDetalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadListNewDetalleplanprocesoscalidad);
                    if (oldPlanprocesoscalidadOfDetalleplanprocesoscalidadListNewDetalleplanprocesoscalidad != null && !oldPlanprocesoscalidadOfDetalleplanprocesoscalidadListNewDetalleplanprocesoscalidad.equals(planprocesoscalidad)) {
                        oldPlanprocesoscalidadOfDetalleplanprocesoscalidadListNewDetalleplanprocesoscalidad.getDetalleplanprocesoscalidadList().remove(detalleplanprocesoscalidadListNewDetalleplanprocesoscalidad);
                        oldPlanprocesoscalidadOfDetalleplanprocesoscalidadListNewDetalleplanprocesoscalidad = em.merge(oldPlanprocesoscalidadOfDetalleplanprocesoscalidadListNewDetalleplanprocesoscalidad);
                    }
                }
            }
            for (Pedido pedidoListOldPedido : pedidoListOld) {
                if (!pedidoListNew.contains(pedidoListOldPedido)) {
                    pedidoListOldPedido.setPlanprocesoscalidad(null);
                    pedidoListOldPedido = em.merge(pedidoListOldPedido);
                }
            }
            for (Pedido pedidoListNewPedido : pedidoListNew) {
                if (!pedidoListOld.contains(pedidoListNewPedido)) {
                    Planprocesoscalidad oldPlanprocesoscalidadOfPedidoListNewPedido = pedidoListNewPedido.getPlanprocesoscalidad();
                    pedidoListNewPedido.setPlanprocesoscalidad(planprocesoscalidad);
                    pedidoListNewPedido = em.merge(pedidoListNewPedido);
                    if (oldPlanprocesoscalidadOfPedidoListNewPedido != null && !oldPlanprocesoscalidadOfPedidoListNewPedido.equals(planprocesoscalidad)) {
                        oldPlanprocesoscalidadOfPedidoListNewPedido.getPedidoList().remove(pedidoListNewPedido);
                        oldPlanprocesoscalidadOfPedidoListNewPedido = em.merge(oldPlanprocesoscalidadOfPedidoListNewPedido);
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
            List<Detalleplanprocesoscalidad> detalleplanprocesoscalidadListOrphanCheck = planprocesoscalidad.getDetalleplanprocesoscalidadList();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadListOrphanCheckDetalleplanprocesoscalidad : detalleplanprocesoscalidadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planprocesoscalidad (" + planprocesoscalidad + ") cannot be destroyed since the Detalleplanprocesoscalidad " + detalleplanprocesoscalidadListOrphanCheckDetalleplanprocesoscalidad + " in its detalleplanprocesoscalidadList field has a non-nullable planprocesoscalidad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Pedido> pedidoList = planprocesoscalidad.getPedidoList();
            for (Pedido pedidoListPedido : pedidoList) {
                pedidoListPedido.setPlanprocesoscalidad(null);
                pedidoListPedido = em.merge(pedidoListPedido);
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
