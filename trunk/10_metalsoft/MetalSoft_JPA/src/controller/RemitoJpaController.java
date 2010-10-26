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
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoremito estado = remito.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                remito.setEstado(estado);
            }
            Pedido pedido = remito.getPedido();
            if (pedido != null) {
                pedido = em.getReference(pedido.getClass(), pedido.getIdpedido());
                remito.setPedido(pedido);
            }
            Set<Detalleremito> attachedDetalleremitoSet = new HashSet<Detalleremito>();
            for (Detalleremito detalleremitoSetDetalleremitoToAttach : remito.getDetalleremitoSet()) {
                detalleremitoSetDetalleremitoToAttach = em.getReference(detalleremitoSetDetalleremitoToAttach.getClass(), detalleremitoSetDetalleremitoToAttach.getDetalleremitoPK());
                attachedDetalleremitoSet.add(detalleremitoSetDetalleremitoToAttach);
            }
            remito.setDetalleremitoSet(attachedDetalleremitoSet);
            em.persist(remito);
            if (estado != null) {
                estado.getRemitoSet().add(remito);
                estado = em.merge(estado);
            }
            if (pedido != null) {
                pedido.getRemitoSet().add(remito);
                pedido = em.merge(pedido);
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
            Pedido pedidoOld = persistentRemito.getPedido();
            Pedido pedidoNew = remito.getPedido();
            Set<Detalleremito> detalleremitoSetOld = persistentRemito.getDetalleremitoSet();
            Set<Detalleremito> detalleremitoSetNew = remito.getDetalleremitoSet();
            List<String> illegalOrphanMessages = null;
            for (Detalleremito detalleremitoSetOldDetalleremito : detalleremitoSetOld) {
                if (!detalleremitoSetNew.contains(detalleremitoSetOldDetalleremito)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleremito " + detalleremitoSetOldDetalleremito + " since its remito field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                remito.setEstado(estadoNew);
            }
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getIdpedido());
                remito.setPedido(pedidoNew);
            }
            Set<Detalleremito> attachedDetalleremitoSetNew = new HashSet<Detalleremito>();
            for (Detalleremito detalleremitoSetNewDetalleremitoToAttach : detalleremitoSetNew) {
                detalleremitoSetNewDetalleremitoToAttach = em.getReference(detalleremitoSetNewDetalleremitoToAttach.getClass(), detalleremitoSetNewDetalleremitoToAttach.getDetalleremitoPK());
                attachedDetalleremitoSetNew.add(detalleremitoSetNewDetalleremitoToAttach);
            }
            detalleremitoSetNew = attachedDetalleremitoSetNew;
            remito.setDetalleremitoSet(detalleremitoSetNew);
            remito = em.merge(remito);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getRemitoSet().remove(remito);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getRemitoSet().add(remito);
                estadoNew = em.merge(estadoNew);
            }
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getRemitoSet().remove(remito);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getRemitoSet().add(remito);
                pedidoNew = em.merge(pedidoNew);
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
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Estadoremito estado = remito.getEstado();
            if (estado != null) {
                estado.getRemitoSet().remove(remito);
                estado = em.merge(estado);
            }
            Pedido pedido = remito.getPedido();
            if (pedido != null) {
                pedido.getRemitoSet().remove(remito);
                pedido = em.merge(pedido);
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
