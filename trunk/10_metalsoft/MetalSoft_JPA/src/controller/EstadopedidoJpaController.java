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
            List<String> illegalOrphanMessages = null;
            for (Pedido pedidoSetOldPedido : pedidoSetOld) {
                if (!pedidoSetNew.contains(pedidoSetOldPedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pedido " + pedidoSetOldPedido + " since its estado field is not nullable.");
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
