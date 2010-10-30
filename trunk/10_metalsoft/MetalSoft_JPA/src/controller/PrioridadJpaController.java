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
        if (prioridad.getPedidoSet1() == null) {
            prioridad.setPedidoSet1(new HashSet<Pedido>());
        }
        if (prioridad.getClienteSet() == null) {
            prioridad.setClienteSet(new HashSet<Cliente>());
        }
        if (prioridad.getClienteSet1() == null) {
            prioridad.setClienteSet1(new HashSet<Cliente>());
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
            Set<Pedido> attachedPedidoSet1 = new HashSet<Pedido>();
            for (Pedido pedidoSet1PedidoToAttach : prioridad.getPedidoSet1()) {
                pedidoSet1PedidoToAttach = em.getReference(pedidoSet1PedidoToAttach.getClass(), pedidoSet1PedidoToAttach.getIdpedido());
                attachedPedidoSet1.add(pedidoSet1PedidoToAttach);
            }
            prioridad.setPedidoSet1(attachedPedidoSet1);
            Set<Cliente> attachedClienteSet = new HashSet<Cliente>();
            for (Cliente clienteSetClienteToAttach : prioridad.getClienteSet()) {
                clienteSetClienteToAttach = em.getReference(clienteSetClienteToAttach.getClass(), clienteSetClienteToAttach.getIdcliente());
                attachedClienteSet.add(clienteSetClienteToAttach);
            }
            prioridad.setClienteSet(attachedClienteSet);
            Set<Cliente> attachedClienteSet1 = new HashSet<Cliente>();
            for (Cliente clienteSet1ClienteToAttach : prioridad.getClienteSet1()) {
                clienteSet1ClienteToAttach = em.getReference(clienteSet1ClienteToAttach.getClass(), clienteSet1ClienteToAttach.getIdcliente());
                attachedClienteSet1.add(clienteSet1ClienteToAttach);
            }
            prioridad.setClienteSet1(attachedClienteSet1);
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
            for (Pedido pedidoSet1Pedido : prioridad.getPedidoSet1()) {
                Prioridad oldPrioridad1OfPedidoSet1Pedido = pedidoSet1Pedido.getPrioridad1();
                pedidoSet1Pedido.setPrioridad1(prioridad);
                pedidoSet1Pedido = em.merge(pedidoSet1Pedido);
                if (oldPrioridad1OfPedidoSet1Pedido != null) {
                    oldPrioridad1OfPedidoSet1Pedido.getPedidoSet1().remove(pedidoSet1Pedido);
                    oldPrioridad1OfPedidoSet1Pedido = em.merge(oldPrioridad1OfPedidoSet1Pedido);
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
            for (Cliente clienteSet1Cliente : prioridad.getClienteSet1()) {
                Prioridad oldPrioridad1OfClienteSet1Cliente = clienteSet1Cliente.getPrioridad1();
                clienteSet1Cliente.setPrioridad1(prioridad);
                clienteSet1Cliente = em.merge(clienteSet1Cliente);
                if (oldPrioridad1OfClienteSet1Cliente != null) {
                    oldPrioridad1OfClienteSet1Cliente.getClienteSet1().remove(clienteSet1Cliente);
                    oldPrioridad1OfClienteSet1Cliente = em.merge(oldPrioridad1OfClienteSet1Cliente);
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
            Set<Pedido> pedidoSet1Old = persistentPrioridad.getPedidoSet1();
            Set<Pedido> pedidoSet1New = prioridad.getPedidoSet1();
            Set<Cliente> clienteSetOld = persistentPrioridad.getClienteSet();
            Set<Cliente> clienteSetNew = prioridad.getClienteSet();
            Set<Cliente> clienteSet1Old = persistentPrioridad.getClienteSet1();
            Set<Cliente> clienteSet1New = prioridad.getClienteSet1();
            List<String> illegalOrphanMessages = null;
            for (Pedido pedidoSetOldPedido : pedidoSetOld) {
                if (!pedidoSetNew.contains(pedidoSetOldPedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pedido " + pedidoSetOldPedido + " since its prioridad field is not nullable.");
                }
            }
            for (Pedido pedidoSet1OldPedido : pedidoSet1Old) {
                if (!pedidoSet1New.contains(pedidoSet1OldPedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pedido " + pedidoSet1OldPedido + " since its prioridad1 field is not nullable.");
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
            Set<Pedido> attachedPedidoSet1New = new HashSet<Pedido>();
            for (Pedido pedidoSet1NewPedidoToAttach : pedidoSet1New) {
                pedidoSet1NewPedidoToAttach = em.getReference(pedidoSet1NewPedidoToAttach.getClass(), pedidoSet1NewPedidoToAttach.getIdpedido());
                attachedPedidoSet1New.add(pedidoSet1NewPedidoToAttach);
            }
            pedidoSet1New = attachedPedidoSet1New;
            prioridad.setPedidoSet1(pedidoSet1New);
            Set<Cliente> attachedClienteSetNew = new HashSet<Cliente>();
            for (Cliente clienteSetNewClienteToAttach : clienteSetNew) {
                clienteSetNewClienteToAttach = em.getReference(clienteSetNewClienteToAttach.getClass(), clienteSetNewClienteToAttach.getIdcliente());
                attachedClienteSetNew.add(clienteSetNewClienteToAttach);
            }
            clienteSetNew = attachedClienteSetNew;
            prioridad.setClienteSet(clienteSetNew);
            Set<Cliente> attachedClienteSet1New = new HashSet<Cliente>();
            for (Cliente clienteSet1NewClienteToAttach : clienteSet1New) {
                clienteSet1NewClienteToAttach = em.getReference(clienteSet1NewClienteToAttach.getClass(), clienteSet1NewClienteToAttach.getIdcliente());
                attachedClienteSet1New.add(clienteSet1NewClienteToAttach);
            }
            clienteSet1New = attachedClienteSet1New;
            prioridad.setClienteSet1(clienteSet1New);
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
            for (Pedido pedidoSet1NewPedido : pedidoSet1New) {
                if (!pedidoSet1Old.contains(pedidoSet1NewPedido)) {
                    Prioridad oldPrioridad1OfPedidoSet1NewPedido = pedidoSet1NewPedido.getPrioridad1();
                    pedidoSet1NewPedido.setPrioridad1(prioridad);
                    pedidoSet1NewPedido = em.merge(pedidoSet1NewPedido);
                    if (oldPrioridad1OfPedidoSet1NewPedido != null && !oldPrioridad1OfPedidoSet1NewPedido.equals(prioridad)) {
                        oldPrioridad1OfPedidoSet1NewPedido.getPedidoSet1().remove(pedidoSet1NewPedido);
                        oldPrioridad1OfPedidoSet1NewPedido = em.merge(oldPrioridad1OfPedidoSet1NewPedido);
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
            for (Cliente clienteSet1OldCliente : clienteSet1Old) {
                if (!clienteSet1New.contains(clienteSet1OldCliente)) {
                    clienteSet1OldCliente.setPrioridad1(null);
                    clienteSet1OldCliente = em.merge(clienteSet1OldCliente);
                }
            }
            for (Cliente clienteSet1NewCliente : clienteSet1New) {
                if (!clienteSet1Old.contains(clienteSet1NewCliente)) {
                    Prioridad oldPrioridad1OfClienteSet1NewCliente = clienteSet1NewCliente.getPrioridad1();
                    clienteSet1NewCliente.setPrioridad1(prioridad);
                    clienteSet1NewCliente = em.merge(clienteSet1NewCliente);
                    if (oldPrioridad1OfClienteSet1NewCliente != null && !oldPrioridad1OfClienteSet1NewCliente.equals(prioridad)) {
                        oldPrioridad1OfClienteSet1NewCliente.getClienteSet1().remove(clienteSet1NewCliente);
                        oldPrioridad1OfClienteSet1NewCliente = em.merge(oldPrioridad1OfClienteSet1NewCliente);
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
            Set<Pedido> pedidoSet1OrphanCheck = prioridad.getPedidoSet1();
            for (Pedido pedidoSet1OrphanCheckPedido : pedidoSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Prioridad (" + prioridad + ") cannot be destroyed since the Pedido " + pedidoSet1OrphanCheckPedido + " in its pedidoSet1 field has a non-nullable prioridad1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Cliente> clienteSet = prioridad.getClienteSet();
            for (Cliente clienteSetCliente : clienteSet) {
                clienteSetCliente.setPrioridad(null);
                clienteSetCliente = em.merge(clienteSetCliente);
            }
            Set<Cliente> clienteSet1 = prioridad.getClienteSet1();
            for (Cliente clienteSet1Cliente : clienteSet1) {
                clienteSet1Cliente.setPrioridad1(null);
                clienteSet1Cliente = em.merge(clienteSet1Cliente);
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
