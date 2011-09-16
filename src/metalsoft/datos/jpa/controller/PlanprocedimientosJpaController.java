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
import metalsoft.datos.jpa.entity.Detalleplanprocedimientos;
import metalsoft.datos.jpa.entity.Planprocedimientos;

/**
 *
 * @author Nino
 */
public class PlanprocedimientosJpaController implements Serializable {

    public PlanprocedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planprocedimientos planprocedimientos) throws PreexistingEntityException, Exception {
        if (planprocedimientos.getPedidoList() == null) {
            planprocedimientos.setPedidoList(new ArrayList<Pedido>());
        }
        if (planprocedimientos.getDetalleplanprocedimientosList() == null) {
            planprocedimientos.setDetalleplanprocedimientosList(new ArrayList<Detalleplanprocedimientos>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pedido> attachedPedidoList = new ArrayList<Pedido>();
            for (Pedido pedidoListPedidoToAttach : planprocedimientos.getPedidoList()) {
                pedidoListPedidoToAttach = em.getReference(pedidoListPedidoToAttach.getClass(), pedidoListPedidoToAttach.getIdpedido());
                attachedPedidoList.add(pedidoListPedidoToAttach);
            }
            planprocedimientos.setPedidoList(attachedPedidoList);
            List<Detalleplanprocedimientos> attachedDetalleplanprocedimientosList = new ArrayList<Detalleplanprocedimientos>();
            for (Detalleplanprocedimientos detalleplanprocedimientosListDetalleplanprocedimientosToAttach : planprocedimientos.getDetalleplanprocedimientosList()) {
                detalleplanprocedimientosListDetalleplanprocedimientosToAttach = em.getReference(detalleplanprocedimientosListDetalleplanprocedimientosToAttach.getClass(), detalleplanprocedimientosListDetalleplanprocedimientosToAttach.getDetalleplanprocedimientosPK());
                attachedDetalleplanprocedimientosList.add(detalleplanprocedimientosListDetalleplanprocedimientosToAttach);
            }
            planprocedimientos.setDetalleplanprocedimientosList(attachedDetalleplanprocedimientosList);
            em.persist(planprocedimientos);
            for (Pedido pedidoListPedido : planprocedimientos.getPedidoList()) {
                Planprocedimientos oldPlanprocedimientosOfPedidoListPedido = pedidoListPedido.getPlanprocedimientos();
                pedidoListPedido.setPlanprocedimientos(planprocedimientos);
                pedidoListPedido = em.merge(pedidoListPedido);
                if (oldPlanprocedimientosOfPedidoListPedido != null) {
                    oldPlanprocedimientosOfPedidoListPedido.getPedidoList().remove(pedidoListPedido);
                    oldPlanprocedimientosOfPedidoListPedido = em.merge(oldPlanprocedimientosOfPedidoListPedido);
                }
            }
            for (Detalleplanprocedimientos detalleplanprocedimientosListDetalleplanprocedimientos : planprocedimientos.getDetalleplanprocedimientosList()) {
                Planprocedimientos oldPlanprocedimientosOfDetalleplanprocedimientosListDetalleplanprocedimientos = detalleplanprocedimientosListDetalleplanprocedimientos.getPlanprocedimientos();
                detalleplanprocedimientosListDetalleplanprocedimientos.setPlanprocedimientos(planprocedimientos);
                detalleplanprocedimientosListDetalleplanprocedimientos = em.merge(detalleplanprocedimientosListDetalleplanprocedimientos);
                if (oldPlanprocedimientosOfDetalleplanprocedimientosListDetalleplanprocedimientos != null) {
                    oldPlanprocedimientosOfDetalleplanprocedimientosListDetalleplanprocedimientos.getDetalleplanprocedimientosList().remove(detalleplanprocedimientosListDetalleplanprocedimientos);
                    oldPlanprocedimientosOfDetalleplanprocedimientosListDetalleplanprocedimientos = em.merge(oldPlanprocedimientosOfDetalleplanprocedimientosListDetalleplanprocedimientos);
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
            List<Pedido> pedidoListOld = persistentPlanprocedimientos.getPedidoList();
            List<Pedido> pedidoListNew = planprocedimientos.getPedidoList();
            List<Detalleplanprocedimientos> detalleplanprocedimientosListOld = persistentPlanprocedimientos.getDetalleplanprocedimientosList();
            List<Detalleplanprocedimientos> detalleplanprocedimientosListNew = planprocedimientos.getDetalleplanprocedimientosList();
            List<String> illegalOrphanMessages = null;
            for (Detalleplanprocedimientos detalleplanprocedimientosListOldDetalleplanprocedimientos : detalleplanprocedimientosListOld) {
                if (!detalleplanprocedimientosListNew.contains(detalleplanprocedimientosListOldDetalleplanprocedimientos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleplanprocedimientos " + detalleplanprocedimientosListOldDetalleplanprocedimientos + " since its planprocedimientos field is not nullable.");
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
            planprocedimientos.setPedidoList(pedidoListNew);
            List<Detalleplanprocedimientos> attachedDetalleplanprocedimientosListNew = new ArrayList<Detalleplanprocedimientos>();
            for (Detalleplanprocedimientos detalleplanprocedimientosListNewDetalleplanprocedimientosToAttach : detalleplanprocedimientosListNew) {
                detalleplanprocedimientosListNewDetalleplanprocedimientosToAttach = em.getReference(detalleplanprocedimientosListNewDetalleplanprocedimientosToAttach.getClass(), detalleplanprocedimientosListNewDetalleplanprocedimientosToAttach.getDetalleplanprocedimientosPK());
                attachedDetalleplanprocedimientosListNew.add(detalleplanprocedimientosListNewDetalleplanprocedimientosToAttach);
            }
            detalleplanprocedimientosListNew = attachedDetalleplanprocedimientosListNew;
            planprocedimientos.setDetalleplanprocedimientosList(detalleplanprocedimientosListNew);
            planprocedimientos = em.merge(planprocedimientos);
            for (Pedido pedidoListOldPedido : pedidoListOld) {
                if (!pedidoListNew.contains(pedidoListOldPedido)) {
                    pedidoListOldPedido.setPlanprocedimientos(null);
                    pedidoListOldPedido = em.merge(pedidoListOldPedido);
                }
            }
            for (Pedido pedidoListNewPedido : pedidoListNew) {
                if (!pedidoListOld.contains(pedidoListNewPedido)) {
                    Planprocedimientos oldPlanprocedimientosOfPedidoListNewPedido = pedidoListNewPedido.getPlanprocedimientos();
                    pedidoListNewPedido.setPlanprocedimientos(planprocedimientos);
                    pedidoListNewPedido = em.merge(pedidoListNewPedido);
                    if (oldPlanprocedimientosOfPedidoListNewPedido != null && !oldPlanprocedimientosOfPedidoListNewPedido.equals(planprocedimientos)) {
                        oldPlanprocedimientosOfPedidoListNewPedido.getPedidoList().remove(pedidoListNewPedido);
                        oldPlanprocedimientosOfPedidoListNewPedido = em.merge(oldPlanprocedimientosOfPedidoListNewPedido);
                    }
                }
            }
            for (Detalleplanprocedimientos detalleplanprocedimientosListNewDetalleplanprocedimientos : detalleplanprocedimientosListNew) {
                if (!detalleplanprocedimientosListOld.contains(detalleplanprocedimientosListNewDetalleplanprocedimientos)) {
                    Planprocedimientos oldPlanprocedimientosOfDetalleplanprocedimientosListNewDetalleplanprocedimientos = detalleplanprocedimientosListNewDetalleplanprocedimientos.getPlanprocedimientos();
                    detalleplanprocedimientosListNewDetalleplanprocedimientos.setPlanprocedimientos(planprocedimientos);
                    detalleplanprocedimientosListNewDetalleplanprocedimientos = em.merge(detalleplanprocedimientosListNewDetalleplanprocedimientos);
                    if (oldPlanprocedimientosOfDetalleplanprocedimientosListNewDetalleplanprocedimientos != null && !oldPlanprocedimientosOfDetalleplanprocedimientosListNewDetalleplanprocedimientos.equals(planprocedimientos)) {
                        oldPlanprocedimientosOfDetalleplanprocedimientosListNewDetalleplanprocedimientos.getDetalleplanprocedimientosList().remove(detalleplanprocedimientosListNewDetalleplanprocedimientos);
                        oldPlanprocedimientosOfDetalleplanprocedimientosListNewDetalleplanprocedimientos = em.merge(oldPlanprocedimientosOfDetalleplanprocedimientosListNewDetalleplanprocedimientos);
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
            List<Detalleplanprocedimientos> detalleplanprocedimientosListOrphanCheck = planprocedimientos.getDetalleplanprocedimientosList();
            for (Detalleplanprocedimientos detalleplanprocedimientosListOrphanCheckDetalleplanprocedimientos : detalleplanprocedimientosListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planprocedimientos (" + planprocedimientos + ") cannot be destroyed since the Detalleplanprocedimientos " + detalleplanprocedimientosListOrphanCheckDetalleplanprocedimientos + " in its detalleplanprocedimientosList field has a non-nullable planprocedimientos field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Pedido> pedidoList = planprocedimientos.getPedidoList();
            for (Pedido pedidoListPedido : pedidoList) {
                pedidoListPedido.setPlanprocedimientos(null);
                pedidoListPedido = em.merge(pedidoListPedido);
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
