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
        if (presupuesto.getPedidoSet1() == null) {
            presupuesto.setPedidoSet1(new HashSet<Pedido>());
        }
        if (presupuesto.getDetallepresupuestoSet() == null) {
            presupuesto.setDetallepresupuestoSet(new HashSet<Detallepresupuesto>());
        }
        if (presupuesto.getDetallepresupuestoSet1() == null) {
            presupuesto.setDetallepresupuestoSet1(new HashSet<Detallepresupuesto>());
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
            Set<Pedido> attachedPedidoSet1 = new HashSet<Pedido>();
            for (Pedido pedidoSet1PedidoToAttach : presupuesto.getPedidoSet1()) {
                pedidoSet1PedidoToAttach = em.getReference(pedidoSet1PedidoToAttach.getClass(), pedidoSet1PedidoToAttach.getIdpedido());
                attachedPedidoSet1.add(pedidoSet1PedidoToAttach);
            }
            presupuesto.setPedidoSet1(attachedPedidoSet1);
            Set<Detallepresupuesto> attachedDetallepresupuestoSet = new HashSet<Detallepresupuesto>();
            for (Detallepresupuesto detallepresupuestoSetDetallepresupuestoToAttach : presupuesto.getDetallepresupuestoSet()) {
                detallepresupuestoSetDetallepresupuestoToAttach = em.getReference(detallepresupuestoSetDetallepresupuestoToAttach.getClass(), detallepresupuestoSetDetallepresupuestoToAttach.getIddetalle());
                attachedDetallepresupuestoSet.add(detallepresupuestoSetDetallepresupuestoToAttach);
            }
            presupuesto.setDetallepresupuestoSet(attachedDetallepresupuestoSet);
            Set<Detallepresupuesto> attachedDetallepresupuestoSet1 = new HashSet<Detallepresupuesto>();
            for (Detallepresupuesto detallepresupuestoSet1DetallepresupuestoToAttach : presupuesto.getDetallepresupuestoSet1()) {
                detallepresupuestoSet1DetallepresupuestoToAttach = em.getReference(detallepresupuestoSet1DetallepresupuestoToAttach.getClass(), detallepresupuestoSet1DetallepresupuestoToAttach.getIddetalle());
                attachedDetallepresupuestoSet1.add(detallepresupuestoSet1DetallepresupuestoToAttach);
            }
            presupuesto.setDetallepresupuestoSet1(attachedDetallepresupuestoSet1);
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
            for (Pedido pedidoSet1Pedido : presupuesto.getPedidoSet1()) {
                Presupuesto oldPresupuesto1OfPedidoSet1Pedido = pedidoSet1Pedido.getPresupuesto1();
                pedidoSet1Pedido.setPresupuesto1(presupuesto);
                pedidoSet1Pedido = em.merge(pedidoSet1Pedido);
                if (oldPresupuesto1OfPedidoSet1Pedido != null) {
                    oldPresupuesto1OfPedidoSet1Pedido.getPedidoSet1().remove(pedidoSet1Pedido);
                    oldPresupuesto1OfPedidoSet1Pedido = em.merge(oldPresupuesto1OfPedidoSet1Pedido);
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
            for (Detallepresupuesto detallepresupuestoSet1Detallepresupuesto : presupuesto.getDetallepresupuestoSet1()) {
                Presupuesto oldIdpresupuesto1OfDetallepresupuestoSet1Detallepresupuesto = detallepresupuestoSet1Detallepresupuesto.getIdpresupuesto1();
                detallepresupuestoSet1Detallepresupuesto.setIdpresupuesto1(presupuesto);
                detallepresupuestoSet1Detallepresupuesto = em.merge(detallepresupuestoSet1Detallepresupuesto);
                if (oldIdpresupuesto1OfDetallepresupuestoSet1Detallepresupuesto != null) {
                    oldIdpresupuesto1OfDetallepresupuestoSet1Detallepresupuesto.getDetallepresupuestoSet1().remove(detallepresupuestoSet1Detallepresupuesto);
                    oldIdpresupuesto1OfDetallepresupuestoSet1Detallepresupuesto = em.merge(oldIdpresupuesto1OfDetallepresupuestoSet1Detallepresupuesto);
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
            Set<Pedido> pedidoSet1Old = persistentPresupuesto.getPedidoSet1();
            Set<Pedido> pedidoSet1New = presupuesto.getPedidoSet1();
            Set<Detallepresupuesto> detallepresupuestoSetOld = persistentPresupuesto.getDetallepresupuestoSet();
            Set<Detallepresupuesto> detallepresupuestoSetNew = presupuesto.getDetallepresupuestoSet();
            Set<Detallepresupuesto> detallepresupuestoSet1Old = persistentPresupuesto.getDetallepresupuestoSet1();
            Set<Detallepresupuesto> detallepresupuestoSet1New = presupuesto.getDetallepresupuestoSet1();
            List<String> illegalOrphanMessages = null;
            for (Detallepresupuesto detallepresupuestoSetOldDetallepresupuesto : detallepresupuestoSetOld) {
                if (!detallepresupuestoSetNew.contains(detallepresupuestoSetOldDetallepresupuesto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallepresupuesto " + detallepresupuestoSetOldDetallepresupuesto + " since its idpresupuesto field is not nullable.");
                }
            }
            for (Detallepresupuesto detallepresupuestoSet1OldDetallepresupuesto : detallepresupuestoSet1Old) {
                if (!detallepresupuestoSet1New.contains(detallepresupuestoSet1OldDetallepresupuesto)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallepresupuesto " + detallepresupuestoSet1OldDetallepresupuesto + " since its idpresupuesto1 field is not nullable.");
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
            Set<Pedido> attachedPedidoSet1New = new HashSet<Pedido>();
            for (Pedido pedidoSet1NewPedidoToAttach : pedidoSet1New) {
                pedidoSet1NewPedidoToAttach = em.getReference(pedidoSet1NewPedidoToAttach.getClass(), pedidoSet1NewPedidoToAttach.getIdpedido());
                attachedPedidoSet1New.add(pedidoSet1NewPedidoToAttach);
            }
            pedidoSet1New = attachedPedidoSet1New;
            presupuesto.setPedidoSet1(pedidoSet1New);
            Set<Detallepresupuesto> attachedDetallepresupuestoSetNew = new HashSet<Detallepresupuesto>();
            for (Detallepresupuesto detallepresupuestoSetNewDetallepresupuestoToAttach : detallepresupuestoSetNew) {
                detallepresupuestoSetNewDetallepresupuestoToAttach = em.getReference(detallepresupuestoSetNewDetallepresupuestoToAttach.getClass(), detallepresupuestoSetNewDetallepresupuestoToAttach.getIddetalle());
                attachedDetallepresupuestoSetNew.add(detallepresupuestoSetNewDetallepresupuestoToAttach);
            }
            detallepresupuestoSetNew = attachedDetallepresupuestoSetNew;
            presupuesto.setDetallepresupuestoSet(detallepresupuestoSetNew);
            Set<Detallepresupuesto> attachedDetallepresupuestoSet1New = new HashSet<Detallepresupuesto>();
            for (Detallepresupuesto detallepresupuestoSet1NewDetallepresupuestoToAttach : detallepresupuestoSet1New) {
                detallepresupuestoSet1NewDetallepresupuestoToAttach = em.getReference(detallepresupuestoSet1NewDetallepresupuestoToAttach.getClass(), detallepresupuestoSet1NewDetallepresupuestoToAttach.getIddetalle());
                attachedDetallepresupuestoSet1New.add(detallepresupuestoSet1NewDetallepresupuestoToAttach);
            }
            detallepresupuestoSet1New = attachedDetallepresupuestoSet1New;
            presupuesto.setDetallepresupuestoSet1(detallepresupuestoSet1New);
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
            for (Pedido pedidoSet1OldPedido : pedidoSet1Old) {
                if (!pedidoSet1New.contains(pedidoSet1OldPedido)) {
                    pedidoSet1OldPedido.setPresupuesto1(null);
                    pedidoSet1OldPedido = em.merge(pedidoSet1OldPedido);
                }
            }
            for (Pedido pedidoSet1NewPedido : pedidoSet1New) {
                if (!pedidoSet1Old.contains(pedidoSet1NewPedido)) {
                    Presupuesto oldPresupuesto1OfPedidoSet1NewPedido = pedidoSet1NewPedido.getPresupuesto1();
                    pedidoSet1NewPedido.setPresupuesto1(presupuesto);
                    pedidoSet1NewPedido = em.merge(pedidoSet1NewPedido);
                    if (oldPresupuesto1OfPedidoSet1NewPedido != null && !oldPresupuesto1OfPedidoSet1NewPedido.equals(presupuesto)) {
                        oldPresupuesto1OfPedidoSet1NewPedido.getPedidoSet1().remove(pedidoSet1NewPedido);
                        oldPresupuesto1OfPedidoSet1NewPedido = em.merge(oldPresupuesto1OfPedidoSet1NewPedido);
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
            for (Detallepresupuesto detallepresupuestoSet1NewDetallepresupuesto : detallepresupuestoSet1New) {
                if (!detallepresupuestoSet1Old.contains(detallepresupuestoSet1NewDetallepresupuesto)) {
                    Presupuesto oldIdpresupuesto1OfDetallepresupuestoSet1NewDetallepresupuesto = detallepresupuestoSet1NewDetallepresupuesto.getIdpresupuesto1();
                    detallepresupuestoSet1NewDetallepresupuesto.setIdpresupuesto1(presupuesto);
                    detallepresupuestoSet1NewDetallepresupuesto = em.merge(detallepresupuestoSet1NewDetallepresupuesto);
                    if (oldIdpresupuesto1OfDetallepresupuestoSet1NewDetallepresupuesto != null && !oldIdpresupuesto1OfDetallepresupuestoSet1NewDetallepresupuesto.equals(presupuesto)) {
                        oldIdpresupuesto1OfDetallepresupuestoSet1NewDetallepresupuesto.getDetallepresupuestoSet1().remove(detallepresupuestoSet1NewDetallepresupuesto);
                        oldIdpresupuesto1OfDetallepresupuestoSet1NewDetallepresupuesto = em.merge(oldIdpresupuesto1OfDetallepresupuestoSet1NewDetallepresupuesto);
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
            Set<Detallepresupuesto> detallepresupuestoSet1OrphanCheck = presupuesto.getDetallepresupuestoSet1();
            for (Detallepresupuesto detallepresupuestoSet1OrphanCheckDetallepresupuesto : detallepresupuestoSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Presupuesto (" + presupuesto + ") cannot be destroyed since the Detallepresupuesto " + detallepresupuestoSet1OrphanCheckDetallepresupuesto + " in its detallepresupuestoSet1 field has a non-nullable idpresupuesto1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Pedido> pedidoSet = presupuesto.getPedidoSet();
            for (Pedido pedidoSetPedido : pedidoSet) {
                pedidoSetPedido.setPresupuesto(null);
                pedidoSetPedido = em.merge(pedidoSetPedido);
            }
            Set<Pedido> pedidoSet1 = presupuesto.getPedidoSet1();
            for (Pedido pedidoSet1Pedido : pedidoSet1) {
                pedidoSet1Pedido.setPresupuesto1(null);
                pedidoSet1Pedido = em.merge(pedidoSet1Pedido);
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
