/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Presupuesto;
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
import entity.Detallepresupuesto;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class PresupuestoJpaController {

    public PresupuestoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Presupuesto presupuesto) throws PreexistingEntityException, Exception {
        if (presupuesto.getPedidoSet() == null) {
            presupuesto.setPedidoSet(new HashSet<Pedido>());
        }
        if (presupuesto.getDetallepresupuestoSet() == null) {
            presupuesto.setDetallepresupuestoSet(new HashSet<Detallepresupuesto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Pedido> attachedPedidoSet = new HashSet<Pedido>();
            for (Pedido pedidoSetPedidoToAttach : presupuesto.getPedidoSet()) {
                pedidoSetPedidoToAttach = em.getReference(pedidoSetPedidoToAttach.getClass(), pedidoSetPedidoToAttach.getIdpedido());
                attachedPedidoSet.add(pedidoSetPedidoToAttach);
            }
            presupuesto.setPedidoSet(attachedPedidoSet);
            Set<Detallepresupuesto> attachedDetallepresupuestoSet = new HashSet<Detallepresupuesto>();
            for (Detallepresupuesto detallepresupuestoSetDetallepresupuestoToAttach : presupuesto.getDetallepresupuestoSet()) {
                detallepresupuestoSetDetallepresupuestoToAttach = em.getReference(detallepresupuestoSetDetallepresupuestoToAttach.getClass(), detallepresupuestoSetDetallepresupuestoToAttach.getIddetalle());
                attachedDetallepresupuestoSet.add(detallepresupuestoSetDetallepresupuestoToAttach);
            }
            presupuesto.setDetallepresupuestoSet(attachedDetallepresupuestoSet);
            em.persist(presupuesto);
            for (Pedido pedidoSetPedido : presupuesto.getPedidoSet()) {
                Presupuesto oldPresupuestoOfPedidoSetPedido = pedidoSetPedido.getPresupuesto();
                pedidoSetPedido.setPresupuesto(presupuesto);
                pedidoSetPedido = em.merge(pedidoSetPedido);
                if (oldPresupuestoOfPedidoSetPedido != null) {
                    oldPresupuestoOfPedidoSetPedido.getPedidoSet().remove(pedidoSetPedido);
                    oldPresupuestoOfPedidoSetPedido = em.merge(oldPresupuestoOfPedidoSetPedido);
                }
            }
            for (Detallepresupuesto detallepresupuestoSetDetallepresupuesto : presupuesto.getDetallepresupuestoSet()) {
                Presupuesto oldIdpresupuestoOfDetallepresupuestoSetDetallepresupuesto = detallepresupuestoSetDetallepresupuesto.getIdpresupuesto();
                detallepresupuestoSetDetallepresupuesto.setIdpresupuesto(presupuesto);
                detallepresupuestoSetDetallepresupuesto = em.merge(detallepresupuestoSetDetallepresupuesto);
                if (oldIdpresupuestoOfDetallepresupuestoSetDetallepresupuesto != null) {
                    oldIdpresupuestoOfDetallepresupuestoSetDetallepresupuesto.getDetallepresupuestoSet().remove(detallepresupuestoSetDetallepresupuesto);
                    oldIdpresupuestoOfDetallepresupuestoSetDetallepresupuesto = em.merge(oldIdpresupuestoOfDetallepresupuestoSetDetallepresupuesto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPresupuesto(presupuesto.getIdpresupuesto()) != null) {
                throw new PreexistingEntityException("Presupuesto " + presupuesto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Presupuesto presupuesto) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Presupuesto persistentPresupuesto = em.find(Presupuesto.class, presupuesto.getIdpresupuesto());
            Set<Pedido> pedidoSetOld = persistentPresupuesto.getPedidoSet();
            Set<Pedido> pedidoSetNew = presupuesto.getPedidoSet();
            Set<Detallepresupuesto> detallepresupuestoSetOld = persistentPresupuesto.getDetallepresupuestoSet();
            Set<Detallepresupuesto> detallepresupuestoSetNew = presupuesto.getDetallepresupuestoSet();
            List<String> illegalOrphanMessages = null;
            for (Detallepresupuesto detallepresupuestoSetOldDetallepresupuesto : detallepresupuestoSetOld) {
                if (!detallepresupuestoSetNew.contains(detallepresupuestoSetOldDetallepresupuesto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallepresupuesto " + detallepresupuestoSetOldDetallepresupuesto + " since its idpresupuesto field is not nullable.");
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
            presupuesto.setPedidoSet(pedidoSetNew);
            Set<Detallepresupuesto> attachedDetallepresupuestoSetNew = new HashSet<Detallepresupuesto>();
            for (Detallepresupuesto detallepresupuestoSetNewDetallepresupuestoToAttach : detallepresupuestoSetNew) {
                detallepresupuestoSetNewDetallepresupuestoToAttach = em.getReference(detallepresupuestoSetNewDetallepresupuestoToAttach.getClass(), detallepresupuestoSetNewDetallepresupuestoToAttach.getIddetalle());
                attachedDetallepresupuestoSetNew.add(detallepresupuestoSetNewDetallepresupuestoToAttach);
            }
            detallepresupuestoSetNew = attachedDetallepresupuestoSetNew;
            presupuesto.setDetallepresupuestoSet(detallepresupuestoSetNew);
            presupuesto = em.merge(presupuesto);
            for (Pedido pedidoSetOldPedido : pedidoSetOld) {
                if (!pedidoSetNew.contains(pedidoSetOldPedido)) {
                    pedidoSetOldPedido.setPresupuesto(null);
                    pedidoSetOldPedido = em.merge(pedidoSetOldPedido);
                }
            }
            for (Pedido pedidoSetNewPedido : pedidoSetNew) {
                if (!pedidoSetOld.contains(pedidoSetNewPedido)) {
                    Presupuesto oldPresupuestoOfPedidoSetNewPedido = pedidoSetNewPedido.getPresupuesto();
                    pedidoSetNewPedido.setPresupuesto(presupuesto);
                    pedidoSetNewPedido = em.merge(pedidoSetNewPedido);
                    if (oldPresupuestoOfPedidoSetNewPedido != null && !oldPresupuestoOfPedidoSetNewPedido.equals(presupuesto)) {
                        oldPresupuestoOfPedidoSetNewPedido.getPedidoSet().remove(pedidoSetNewPedido);
                        oldPresupuestoOfPedidoSetNewPedido = em.merge(oldPresupuestoOfPedidoSetNewPedido);
                    }
                }
            }
            for (Detallepresupuesto detallepresupuestoSetNewDetallepresupuesto : detallepresupuestoSetNew) {
                if (!detallepresupuestoSetOld.contains(detallepresupuestoSetNewDetallepresupuesto)) {
                    Presupuesto oldIdpresupuestoOfDetallepresupuestoSetNewDetallepresupuesto = detallepresupuestoSetNewDetallepresupuesto.getIdpresupuesto();
                    detallepresupuestoSetNewDetallepresupuesto.setIdpresupuesto(presupuesto);
                    detallepresupuestoSetNewDetallepresupuesto = em.merge(detallepresupuestoSetNewDetallepresupuesto);
                    if (oldIdpresupuestoOfDetallepresupuestoSetNewDetallepresupuesto != null && !oldIdpresupuestoOfDetallepresupuestoSetNewDetallepresupuesto.equals(presupuesto)) {
                        oldIdpresupuestoOfDetallepresupuestoSetNewDetallepresupuesto.getDetallepresupuestoSet().remove(detallepresupuestoSetNewDetallepresupuesto);
                        oldIdpresupuestoOfDetallepresupuestoSetNewDetallepresupuesto = em.merge(oldIdpresupuestoOfDetallepresupuestoSetNewDetallepresupuesto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = presupuesto.getIdpresupuesto();
                if (findPresupuesto(id) == null) {
                    throw new NonexistentEntityException("The presupuesto with id " + id + " no longer exists.");
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
            Presupuesto presupuesto;
            try {
                presupuesto = em.getReference(Presupuesto.class, id);
                presupuesto.getIdpresupuesto();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The presupuesto with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detallepresupuesto> detallepresupuestoSetOrphanCheck = presupuesto.getDetallepresupuestoSet();
            for (Detallepresupuesto detallepresupuestoSetOrphanCheckDetallepresupuesto : detallepresupuestoSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Presupuesto (" + presupuesto + ") cannot be destroyed since the Detallepresupuesto " + detallepresupuestoSetOrphanCheckDetallepresupuesto + " in its detallepresupuestoSet field has a non-nullable idpresupuesto field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Pedido> pedidoSet = presupuesto.getPedidoSet();
            for (Pedido pedidoSetPedido : pedidoSet) {
                pedidoSetPedido.setPresupuesto(null);
                pedidoSetPedido = em.merge(pedidoSetPedido);
            }
            em.remove(presupuesto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Presupuesto> findPresupuestoEntities() {
        return findPresupuestoEntities(true, -1, -1);
    }

    public List<Presupuesto> findPresupuestoEntities(int maxResults, int firstResult) {
        return findPresupuestoEntities(false, maxResults, firstResult);
    }

    private List<Presupuesto> findPresupuestoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Presupuesto.class));
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

    public Presupuesto findPresupuesto(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Presupuesto.class, id);
        } finally {
            em.close();
        }
    }

    public int getPresupuestoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Presupuesto> rt = cq.from(Presupuesto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
