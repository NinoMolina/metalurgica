/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Remito;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Estadoremito;
import entity.Pedido;
import entity.Detalleremito;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class RemitoJpaController {

    public RemitoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Remito remito) throws PreexistingEntityException, Exception {
        if (remito.getDetalleremitoSet() == null) {
            remito.setDetalleremitoSet(new HashSet<Detalleremito>());
        }
        if (remito.getDetalleremitoSet1() == null) {
            remito.setDetalleremitoSet1(new HashSet<Detalleremito>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoremito estado = remito.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                remito.setEstado(estado);
            }
            Estadoremito estado1 = remito.getEstado1();
            if (estado1 != null) {
                estado1 = em.getReference(estado1.getClass(), estado1.getIdestado());
                remito.setEstado1(estado1);
            }
            Pedido pedido = remito.getPedido();
            if (pedido != null) {
                pedido = em.getReference(pedido.getClass(), pedido.getIdpedido());
                remito.setPedido(pedido);
            }
            Pedido pedido1 = remito.getPedido1();
            if (pedido1 != null) {
                pedido1 = em.getReference(pedido1.getClass(), pedido1.getIdpedido());
                remito.setPedido1(pedido1);
            }
            Set<Detalleremito> attachedDetalleremitoSet = new HashSet<Detalleremito>();
            for (Detalleremito detalleremitoSetDetalleremitoToAttach : remito.getDetalleremitoSet()) {
                detalleremitoSetDetalleremitoToAttach = em.getReference(detalleremitoSetDetalleremitoToAttach.getClass(), detalleremitoSetDetalleremitoToAttach.getDetalleremitoPK());
                attachedDetalleremitoSet.add(detalleremitoSetDetalleremitoToAttach);
            }
            remito.setDetalleremitoSet(attachedDetalleremitoSet);
            Set<Detalleremito> attachedDetalleremitoSet1 = new HashSet<Detalleremito>();
            for (Detalleremito detalleremitoSet1DetalleremitoToAttach : remito.getDetalleremitoSet1()) {
                detalleremitoSet1DetalleremitoToAttach = em.getReference(detalleremitoSet1DetalleremitoToAttach.getClass(), detalleremitoSet1DetalleremitoToAttach.getDetalleremitoPK());
                attachedDetalleremitoSet1.add(detalleremitoSet1DetalleremitoToAttach);
            }
            remito.setDetalleremitoSet1(attachedDetalleremitoSet1);
            em.persist(remito);
            if (estado != null) {
                estado.getRemitoSet().add(remito);
                estado = em.merge(estado);
            }
            if (estado1 != null) {
                estado1.getRemitoSet().add(remito);
                estado1 = em.merge(estado1);
            }
            if (pedido != null) {
                pedido.getRemitoSet().add(remito);
                pedido = em.merge(pedido);
            }
            if (pedido1 != null) {
                pedido1.getRemitoSet().add(remito);
                pedido1 = em.merge(pedido1);
            }
            for (Detalleremito detalleremitoSetDetalleremito : remito.getDetalleremitoSet()) {
                Remito oldRemitoOfDetalleremitoSetDetalleremito = detalleremitoSetDetalleremito.getRemito();
                detalleremitoSetDetalleremito.setRemito(remito);
                detalleremitoSetDetalleremito = em.merge(detalleremitoSetDetalleremito);
                if (oldRemitoOfDetalleremitoSetDetalleremito != null) {
                    oldRemitoOfDetalleremitoSetDetalleremito.getDetalleremitoSet().remove(detalleremitoSetDetalleremito);
                    oldRemitoOfDetalleremitoSetDetalleremito = em.merge(oldRemitoOfDetalleremitoSetDetalleremito);
                }
            }
            for (Detalleremito detalleremitoSet1Detalleremito : remito.getDetalleremitoSet1()) {
                Remito oldRemito1OfDetalleremitoSet1Detalleremito = detalleremitoSet1Detalleremito.getRemito1();
                detalleremitoSet1Detalleremito.setRemito1(remito);
                detalleremitoSet1Detalleremito = em.merge(detalleremitoSet1Detalleremito);
                if (oldRemito1OfDetalleremitoSet1Detalleremito != null) {
                    oldRemito1OfDetalleremitoSet1Detalleremito.getDetalleremitoSet1().remove(detalleremitoSet1Detalleremito);
                    oldRemito1OfDetalleremitoSet1Detalleremito = em.merge(oldRemito1OfDetalleremitoSet1Detalleremito);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findRemito(remito.getIdremito()) != null) {
                throw new PreexistingEntityException("Remito " + remito + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Remito remito) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Remito persistentRemito = em.find(Remito.class, remito.getIdremito());
            Estadoremito estadoOld = persistentRemito.getEstado();
            Estadoremito estadoNew = remito.getEstado();
            Estadoremito estado1Old = persistentRemito.getEstado1();
            Estadoremito estado1New = remito.getEstado1();
            Pedido pedidoOld = persistentRemito.getPedido();
            Pedido pedidoNew = remito.getPedido();
            Pedido pedido1Old = persistentRemito.getPedido1();
            Pedido pedido1New = remito.getPedido1();
            Set<Detalleremito> detalleremitoSetOld = persistentRemito.getDetalleremitoSet();
            Set<Detalleremito> detalleremitoSetNew = remito.getDetalleremitoSet();
            Set<Detalleremito> detalleremitoSet1Old = persistentRemito.getDetalleremitoSet1();
            Set<Detalleremito> detalleremitoSet1New = remito.getDetalleremitoSet1();
            List<String> illegalOrphanMessages = null;
            for (Detalleremito detalleremitoSetOldDetalleremito : detalleremitoSetOld) {
                if (!detalleremitoSetNew.contains(detalleremitoSetOldDetalleremito)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleremito " + detalleremitoSetOldDetalleremito + " since its remito field is not nullable.");
                }
            }
            for (Detalleremito detalleremitoSet1OldDetalleremito : detalleremitoSet1Old) {
                if (!detalleremitoSet1New.contains(detalleremitoSet1OldDetalleremito)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleremito " + detalleremitoSet1OldDetalleremito + " since its remito1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                remito.setEstado(estadoNew);
            }
            if (estado1New != null) {
                estado1New = em.getReference(estado1New.getClass(), estado1New.getIdestado());
                remito.setEstado1(estado1New);
            }
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getIdpedido());
                remito.setPedido(pedidoNew);
            }
            if (pedido1New != null) {
                pedido1New = em.getReference(pedido1New.getClass(), pedido1New.getIdpedido());
                remito.setPedido1(pedido1New);
            }
            Set<Detalleremito> attachedDetalleremitoSetNew = new HashSet<Detalleremito>();
            for (Detalleremito detalleremitoSetNewDetalleremitoToAttach : detalleremitoSetNew) {
                detalleremitoSetNewDetalleremitoToAttach = em.getReference(detalleremitoSetNewDetalleremitoToAttach.getClass(), detalleremitoSetNewDetalleremitoToAttach.getDetalleremitoPK());
                attachedDetalleremitoSetNew.add(detalleremitoSetNewDetalleremitoToAttach);
            }
            detalleremitoSetNew = attachedDetalleremitoSetNew;
            remito.setDetalleremitoSet(detalleremitoSetNew);
            Set<Detalleremito> attachedDetalleremitoSet1New = new HashSet<Detalleremito>();
            for (Detalleremito detalleremitoSet1NewDetalleremitoToAttach : detalleremitoSet1New) {
                detalleremitoSet1NewDetalleremitoToAttach = em.getReference(detalleremitoSet1NewDetalleremitoToAttach.getClass(), detalleremitoSet1NewDetalleremitoToAttach.getDetalleremitoPK());
                attachedDetalleremitoSet1New.add(detalleremitoSet1NewDetalleremitoToAttach);
            }
            detalleremitoSet1New = attachedDetalleremitoSet1New;
            remito.setDetalleremitoSet1(detalleremitoSet1New);
            remito = em.merge(remito);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getRemitoSet().remove(remito);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getRemitoSet().add(remito);
                estadoNew = em.merge(estadoNew);
            }
            if (estado1Old != null && !estado1Old.equals(estado1New)) {
                estado1Old.getRemitoSet().remove(remito);
                estado1Old = em.merge(estado1Old);
            }
            if (estado1New != null && !estado1New.equals(estado1Old)) {
                estado1New.getRemitoSet().add(remito);
                estado1New = em.merge(estado1New);
            }
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getRemitoSet().remove(remito);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getRemitoSet().add(remito);
                pedidoNew = em.merge(pedidoNew);
            }
            if (pedido1Old != null && !pedido1Old.equals(pedido1New)) {
                pedido1Old.getRemitoSet().remove(remito);
                pedido1Old = em.merge(pedido1Old);
            }
            if (pedido1New != null && !pedido1New.equals(pedido1Old)) {
                pedido1New.getRemitoSet().add(remito);
                pedido1New = em.merge(pedido1New);
            }
            for (Detalleremito detalleremitoSetNewDetalleremito : detalleremitoSetNew) {
                if (!detalleremitoSetOld.contains(detalleremitoSetNewDetalleremito)) {
                    Remito oldRemitoOfDetalleremitoSetNewDetalleremito = detalleremitoSetNewDetalleremito.getRemito();
                    detalleremitoSetNewDetalleremito.setRemito(remito);
                    detalleremitoSetNewDetalleremito = em.merge(detalleremitoSetNewDetalleremito);
                    if (oldRemitoOfDetalleremitoSetNewDetalleremito != null && !oldRemitoOfDetalleremitoSetNewDetalleremito.equals(remito)) {
                        oldRemitoOfDetalleremitoSetNewDetalleremito.getDetalleremitoSet().remove(detalleremitoSetNewDetalleremito);
                        oldRemitoOfDetalleremitoSetNewDetalleremito = em.merge(oldRemitoOfDetalleremitoSetNewDetalleremito);
                    }
                }
            }
            for (Detalleremito detalleremitoSet1NewDetalleremito : detalleremitoSet1New) {
                if (!detalleremitoSet1Old.contains(detalleremitoSet1NewDetalleremito)) {
                    Remito oldRemito1OfDetalleremitoSet1NewDetalleremito = detalleremitoSet1NewDetalleremito.getRemito1();
                    detalleremitoSet1NewDetalleremito.setRemito1(remito);
                    detalleremitoSet1NewDetalleremito = em.merge(detalleremitoSet1NewDetalleremito);
                    if (oldRemito1OfDetalleremitoSet1NewDetalleremito != null && !oldRemito1OfDetalleremitoSet1NewDetalleremito.equals(remito)) {
                        oldRemito1OfDetalleremitoSet1NewDetalleremito.getDetalleremitoSet1().remove(detalleremitoSet1NewDetalleremito);
                        oldRemito1OfDetalleremitoSet1NewDetalleremito = em.merge(oldRemito1OfDetalleremitoSet1NewDetalleremito);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = remito.getIdremito();
                if (findRemito(id) == null) {
                    throw new NonexistentEntityException("The remito with id " + id + " no longer exists.");
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
            Remito remito;
            try {
                remito = em.getReference(Remito.class, id);
                remito.getIdremito();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The remito with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detalleremito> detalleremitoSetOrphanCheck = remito.getDetalleremitoSet();
            for (Detalleremito detalleremitoSetOrphanCheckDetalleremito : detalleremitoSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Remito (" + remito + ") cannot be destroyed since the Detalleremito " + detalleremitoSetOrphanCheckDetalleremito + " in its detalleremitoSet field has a non-nullable remito field.");
            }
            Set<Detalleremito> detalleremitoSet1OrphanCheck = remito.getDetalleremitoSet1();
            for (Detalleremito detalleremitoSet1OrphanCheckDetalleremito : detalleremitoSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Remito (" + remito + ") cannot be destroyed since the Detalleremito " + detalleremitoSet1OrphanCheckDetalleremito + " in its detalleremitoSet1 field has a non-nullable remito1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Estadoremito estado = remito.getEstado();
            if (estado != null) {
                estado.getRemitoSet().remove(remito);
                estado = em.merge(estado);
            }
            Estadoremito estado1 = remito.getEstado1();
            if (estado1 != null) {
                estado1.getRemitoSet().remove(remito);
                estado1 = em.merge(estado1);
            }
            Pedido pedido = remito.getPedido();
            if (pedido != null) {
                pedido.getRemitoSet().remove(remito);
                pedido = em.merge(pedido);
            }
            Pedido pedido1 = remito.getPedido1();
            if (pedido1 != null) {
                pedido1.getRemitoSet().remove(remito);
                pedido1 = em.merge(pedido1);
            }
            em.remove(remito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Remito> findRemitoEntities() {
        return findRemitoEntities(true, -1, -1);
    }

    public List<Remito> findRemitoEntities(int maxResults, int firstResult) {
        return findRemitoEntities(false, maxResults, firstResult);
    }

    private List<Remito> findRemitoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Remito.class));
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

    public Remito findRemito(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Remito.class, id);
        } finally {
            em.close();
        }
    }

    public int getRemitoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Remito> rt = cq.from(Remito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
