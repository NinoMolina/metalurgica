/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Prioridad;
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
import entity.Cliente;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class PrioridadJpaController {

    public PrioridadJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Prioridad prioridad) throws PreexistingEntityException, Exception {
        if (prioridad.getPedidoSet() == null) {
            prioridad.setPedidoSet(new HashSet<Pedido>());
        }
        if (prioridad.getClienteSet() == null) {
            prioridad.setClienteSet(new HashSet<Cliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Pedido> attachedPedidoSet = new HashSet<Pedido>();
            for (Pedido pedidoSetPedidoToAttach : prioridad.getPedidoSet()) {
                pedidoSetPedidoToAttach = em.getReference(pedidoSetPedidoToAttach.getClass(), pedidoSetPedidoToAttach.getIdpedido());
                attachedPedidoSet.add(pedidoSetPedidoToAttach);
            }
            prioridad.setPedidoSet(attachedPedidoSet);
            Set<Cliente> attachedClienteSet = new HashSet<Cliente>();
            for (Cliente clienteSetClienteToAttach : prioridad.getClienteSet()) {
                clienteSetClienteToAttach = em.getReference(clienteSetClienteToAttach.getClass(), clienteSetClienteToAttach.getIdcliente());
                attachedClienteSet.add(clienteSetClienteToAttach);
            }
            prioridad.setClienteSet(attachedClienteSet);
            em.persist(prioridad);
            for (Pedido pedidoSetPedido : prioridad.getPedidoSet()) {
                Prioridad oldPrioridadOfPedidoSetPedido = pedidoSetPedido.getPrioridad();
                pedidoSetPedido.setPrioridad(prioridad);
                pedidoSetPedido = em.merge(pedidoSetPedido);
                if (oldPrioridadOfPedidoSetPedido != null) {
                    oldPrioridadOfPedidoSetPedido.getPedidoSet().remove(pedidoSetPedido);
                    oldPrioridadOfPedidoSetPedido = em.merge(oldPrioridadOfPedidoSetPedido);
                }
            }
            for (Cliente clienteSetCliente : prioridad.getClienteSet()) {
                Prioridad oldPrioridadOfClienteSetCliente = clienteSetCliente.getPrioridad();
                clienteSetCliente.setPrioridad(prioridad);
                clienteSetCliente = em.merge(clienteSetCliente);
                if (oldPrioridadOfClienteSetCliente != null) {
                    oldPrioridadOfClienteSetCliente.getClienteSet().remove(clienteSetCliente);
                    oldPrioridadOfClienteSetCliente = em.merge(oldPrioridadOfClienteSetCliente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrioridad(prioridad.getIdprioridad()) != null) {
                throw new PreexistingEntityException("Prioridad " + prioridad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Prioridad prioridad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prioridad persistentPrioridad = em.find(Prioridad.class, prioridad.getIdprioridad());
            Set<Pedido> pedidoSetOld = persistentPrioridad.getPedidoSet();
            Set<Pedido> pedidoSetNew = prioridad.getPedidoSet();
            Set<Cliente> clienteSetOld = persistentPrioridad.getClienteSet();
            Set<Cliente> clienteSetNew = prioridad.getClienteSet();
            List<String> illegalOrphanMessages = null;
            for (Pedido pedidoSetOldPedido : pedidoSetOld) {
                if (!pedidoSetNew.contains(pedidoSetOldPedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pedido " + pedidoSetOldPedido + " since its prioridad field is not nullable.");
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
            prioridad.setPedidoSet(pedidoSetNew);
            Set<Cliente> attachedClienteSetNew = new HashSet<Cliente>();
            for (Cliente clienteSetNewClienteToAttach : clienteSetNew) {
                clienteSetNewClienteToAttach = em.getReference(clienteSetNewClienteToAttach.getClass(), clienteSetNewClienteToAttach.getIdcliente());
                attachedClienteSetNew.add(clienteSetNewClienteToAttach);
            }
            clienteSetNew = attachedClienteSetNew;
            prioridad.setClienteSet(clienteSetNew);
            prioridad = em.merge(prioridad);
            for (Pedido pedidoSetNewPedido : pedidoSetNew) {
                if (!pedidoSetOld.contains(pedidoSetNewPedido)) {
                    Prioridad oldPrioridadOfPedidoSetNewPedido = pedidoSetNewPedido.getPrioridad();
                    pedidoSetNewPedido.setPrioridad(prioridad);
                    pedidoSetNewPedido = em.merge(pedidoSetNewPedido);
                    if (oldPrioridadOfPedidoSetNewPedido != null && !oldPrioridadOfPedidoSetNewPedido.equals(prioridad)) {
                        oldPrioridadOfPedidoSetNewPedido.getPedidoSet().remove(pedidoSetNewPedido);
                        oldPrioridadOfPedidoSetNewPedido = em.merge(oldPrioridadOfPedidoSetNewPedido);
                    }
                }
            }
            for (Cliente clienteSetOldCliente : clienteSetOld) {
                if (!clienteSetNew.contains(clienteSetOldCliente)) {
                    clienteSetOldCliente.setPrioridad(null);
                    clienteSetOldCliente = em.merge(clienteSetOldCliente);
                }
            }
            for (Cliente clienteSetNewCliente : clienteSetNew) {
                if (!clienteSetOld.contains(clienteSetNewCliente)) {
                    Prioridad oldPrioridadOfClienteSetNewCliente = clienteSetNewCliente.getPrioridad();
                    clienteSetNewCliente.setPrioridad(prioridad);
                    clienteSetNewCliente = em.merge(clienteSetNewCliente);
                    if (oldPrioridadOfClienteSetNewCliente != null && !oldPrioridadOfClienteSetNewCliente.equals(prioridad)) {
                        oldPrioridadOfClienteSetNewCliente.getClienteSet().remove(clienteSetNewCliente);
                        oldPrioridadOfClienteSetNewCliente = em.merge(oldPrioridadOfClienteSetNewCliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = prioridad.getIdprioridad();
                if (findPrioridad(id) == null) {
                    throw new NonexistentEntityException("The prioridad with id " + id + " no longer exists.");
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
            Prioridad prioridad;
            try {
                prioridad = em.getReference(Prioridad.class, id);
                prioridad.getIdprioridad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prioridad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Pedido> pedidoSetOrphanCheck = prioridad.getPedidoSet();
            for (Pedido pedidoSetOrphanCheckPedido : pedidoSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Prioridad (" + prioridad + ") cannot be destroyed since the Pedido " + pedidoSetOrphanCheckPedido + " in its pedidoSet field has a non-nullable prioridad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Cliente> clienteSet = prioridad.getClienteSet();
            for (Cliente clienteSetCliente : clienteSet) {
                clienteSetCliente.setPrioridad(null);
                clienteSetCliente = em.merge(clienteSetCliente);
            }
            em.remove(prioridad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Prioridad> findPrioridadEntities() {
        return findPrioridadEntities(true, -1, -1);
    }

    public List<Prioridad> findPrioridadEntities(int maxResults, int firstResult) {
        return findPrioridadEntities(false, maxResults, firstResult);
    }

    private List<Prioridad> findPrioridadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Prioridad.class));
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

    public Prioridad findPrioridad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Prioridad.class, id);
        } finally {
            em.close();
        }
    }

    public int getPrioridadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Prioridad> rt = cq.from(Prioridad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
