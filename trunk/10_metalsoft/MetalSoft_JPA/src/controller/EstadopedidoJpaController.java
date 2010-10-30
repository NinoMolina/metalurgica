/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Estadopedido;
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
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class EstadopedidoJpaController {

    public EstadopedidoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadopedido estadopedido) throws PreexistingEntityException, Exception {
        if (estadopedido.getPedidoSet() == null) {
            estadopedido.setPedidoSet(new HashSet<Pedido>());
        }
        if (estadopedido.getPedidoSet1() == null) {
            estadopedido.setPedidoSet1(new HashSet<Pedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Pedido> attachedPedidoSet = new HashSet<Pedido>();
            for (Pedido pedidoSetPedidoToAttach : estadopedido.getPedidoSet()) {
                pedidoSetPedidoToAttach = em.getReference(pedidoSetPedidoToAttach.getClass(), pedidoSetPedidoToAttach.getIdpedido());
                attachedPedidoSet.add(pedidoSetPedidoToAttach);
            }
            estadopedido.setPedidoSet(attachedPedidoSet);
            Set<Pedido> attachedPedidoSet1 = new HashSet<Pedido>();
            for (Pedido pedidoSet1PedidoToAttach : estadopedido.getPedidoSet1()) {
                pedidoSet1PedidoToAttach = em.getReference(pedidoSet1PedidoToAttach.getClass(), pedidoSet1PedidoToAttach.getIdpedido());
                attachedPedidoSet1.add(pedidoSet1PedidoToAttach);
            }
            estadopedido.setPedidoSet1(attachedPedidoSet1);
            em.persist(estadopedido);
            for (Pedido pedidoSetPedido : estadopedido.getPedidoSet()) {
                Estadopedido oldEstadoOfPedidoSetPedido = pedidoSetPedido.getEstado();
                pedidoSetPedido.setEstado(estadopedido);
                pedidoSetPedido = em.merge(pedidoSetPedido);
                if (oldEstadoOfPedidoSetPedido != null) {
                    oldEstadoOfPedidoSetPedido.getPedidoSet().remove(pedidoSetPedido);
                    oldEstadoOfPedidoSetPedido = em.merge(oldEstadoOfPedidoSetPedido);
                }
            }
            for (Pedido pedidoSet1Pedido : estadopedido.getPedidoSet1()) {
                Estadopedido oldEstado1OfPedidoSet1Pedido = pedidoSet1Pedido.getEstado1();
                pedidoSet1Pedido.setEstado1(estadopedido);
                pedidoSet1Pedido = em.merge(pedidoSet1Pedido);
                if (oldEstado1OfPedidoSet1Pedido != null) {
                    oldEstado1OfPedidoSet1Pedido.getPedidoSet1().remove(pedidoSet1Pedido);
                    oldEstado1OfPedidoSet1Pedido = em.merge(oldEstado1OfPedidoSet1Pedido);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadopedido(estadopedido.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadopedido " + estadopedido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadopedido estadopedido) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadopedido persistentEstadopedido = em.find(Estadopedido.class, estadopedido.getIdestado());
            Set<Pedido> pedidoSetOld = persistentEstadopedido.getPedidoSet();
            Set<Pedido> pedidoSetNew = estadopedido.getPedidoSet();
            Set<Pedido> pedidoSet1Old = persistentEstadopedido.getPedidoSet1();
            Set<Pedido> pedidoSet1New = estadopedido.getPedidoSet1();
            List<String> illegalOrphanMessages = null;
            for (Pedido pedidoSetOldPedido : pedidoSetOld) {
                if (!pedidoSetNew.contains(pedidoSetOldPedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pedido " + pedidoSetOldPedido + " since its estado field is not nullable.");
                }
            }
            for (Pedido pedidoSet1OldPedido : pedidoSet1Old) {
                if (!pedidoSet1New.contains(pedidoSet1OldPedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pedido " + pedidoSet1OldPedido + " since its estado1 field is not nullable.");
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
            estadopedido.setPedidoSet(pedidoSetNew);
            Set<Pedido> attachedPedidoSet1New = new HashSet<Pedido>();
            for (Pedido pedidoSet1NewPedidoToAttach : pedidoSet1New) {
                pedidoSet1NewPedidoToAttach = em.getReference(pedidoSet1NewPedidoToAttach.getClass(), pedidoSet1NewPedidoToAttach.getIdpedido());
                attachedPedidoSet1New.add(pedidoSet1NewPedidoToAttach);
            }
            pedidoSet1New = attachedPedidoSet1New;
            estadopedido.setPedidoSet1(pedidoSet1New);
            estadopedido = em.merge(estadopedido);
            for (Pedido pedidoSetNewPedido : pedidoSetNew) {
                if (!pedidoSetOld.contains(pedidoSetNewPedido)) {
                    Estadopedido oldEstadoOfPedidoSetNewPedido = pedidoSetNewPedido.getEstado();
                    pedidoSetNewPedido.setEstado(estadopedido);
                    pedidoSetNewPedido = em.merge(pedidoSetNewPedido);
                    if (oldEstadoOfPedidoSetNewPedido != null && !oldEstadoOfPedidoSetNewPedido.equals(estadopedido)) {
                        oldEstadoOfPedidoSetNewPedido.getPedidoSet().remove(pedidoSetNewPedido);
                        oldEstadoOfPedidoSetNewPedido = em.merge(oldEstadoOfPedidoSetNewPedido);
                    }
                }
            }
            for (Pedido pedidoSet1NewPedido : pedidoSet1New) {
                if (!pedidoSet1Old.contains(pedidoSet1NewPedido)) {
                    Estadopedido oldEstado1OfPedidoSet1NewPedido = pedidoSet1NewPedido.getEstado1();
                    pedidoSet1NewPedido.setEstado1(estadopedido);
                    pedidoSet1NewPedido = em.merge(pedidoSet1NewPedido);
                    if (oldEstado1OfPedidoSet1NewPedido != null && !oldEstado1OfPedidoSet1NewPedido.equals(estadopedido)) {
                        oldEstado1OfPedidoSet1NewPedido.getPedidoSet1().remove(pedidoSet1NewPedido);
                        oldEstado1OfPedidoSet1NewPedido = em.merge(oldEstado1OfPedidoSet1NewPedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadopedido.getIdestado();
                if (findEstadopedido(id) == null) {
                    throw new NonexistentEntityException("The estadopedido with id " + id + " no longer exists.");
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
            Estadopedido estadopedido;
            try {
                estadopedido = em.getReference(Estadopedido.class, id);
                estadopedido.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadopedido with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Pedido> pedidoSetOrphanCheck = estadopedido.getPedidoSet();
            for (Pedido pedidoSetOrphanCheckPedido : pedidoSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estadopedido (" + estadopedido + ") cannot be destroyed since the Pedido " + pedidoSetOrphanCheckPedido + " in its pedidoSet field has a non-nullable estado field.");
            }
            Set<Pedido> pedidoSet1OrphanCheck = estadopedido.getPedidoSet1();
            for (Pedido pedidoSet1OrphanCheckPedido : pedidoSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estadopedido (" + estadopedido + ") cannot be destroyed since the Pedido " + pedidoSet1OrphanCheckPedido + " in its pedidoSet1 field has a non-nullable estado1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estadopedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadopedido> findEstadopedidoEntities() {
        return findEstadopedidoEntities(true, -1, -1);
    }

    public List<Estadopedido> findEstadopedidoEntities(int maxResults, int firstResult) {
        return findEstadopedidoEntities(false, maxResults, firstResult);
    }

    private List<Estadopedido> findEstadopedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadopedido.class));
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

    public Estadopedido findEstadopedido(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadopedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadopedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadopedido> rt = cq.from(Estadopedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
