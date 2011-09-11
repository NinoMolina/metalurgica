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
import metalsoft.datos.jpa.entity.Cliente;
import metalsoft.datos.jpa.entity.Prioridad;

/**
 *
 * @author Nino
 */
public class PrioridadJpaController implements Serializable {

    public PrioridadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Prioridad prioridad) throws PreexistingEntityException, Exception {
        if (prioridad.getPedidoList() == null) {
            prioridad.setPedidoList(new ArrayList<Pedido>());
        }
        if (prioridad.getClienteList() == null) {
            prioridad.setClienteList(new ArrayList<Cliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Pedido> attachedPedidoList = new ArrayList<Pedido>();
            for (Pedido pedidoListPedidoToAttach : prioridad.getPedidoList()) {
                pedidoListPedidoToAttach = em.getReference(pedidoListPedidoToAttach.getClass(), pedidoListPedidoToAttach.getIdpedido());
                attachedPedidoList.add(pedidoListPedidoToAttach);
            }
            prioridad.setPedidoList(attachedPedidoList);
            List<Cliente> attachedClienteList = new ArrayList<Cliente>();
            for (Cliente clienteListClienteToAttach : prioridad.getClienteList()) {
                clienteListClienteToAttach = em.getReference(clienteListClienteToAttach.getClass(), clienteListClienteToAttach.getIdcliente());
                attachedClienteList.add(clienteListClienteToAttach);
            }
            prioridad.setClienteList(attachedClienteList);
            em.persist(prioridad);
            for (Pedido pedidoListPedido : prioridad.getPedidoList()) {
                Prioridad oldPrioridadOfPedidoListPedido = pedidoListPedido.getPrioridad();
                pedidoListPedido.setPrioridad(prioridad);
                pedidoListPedido = em.merge(pedidoListPedido);
                if (oldPrioridadOfPedidoListPedido != null) {
                    oldPrioridadOfPedidoListPedido.getPedidoList().remove(pedidoListPedido);
                    oldPrioridadOfPedidoListPedido = em.merge(oldPrioridadOfPedidoListPedido);
                }
            }
            for (Cliente clienteListCliente : prioridad.getClienteList()) {
                Prioridad oldPrioridadOfClienteListCliente = clienteListCliente.getPrioridad();
                clienteListCliente.setPrioridad(prioridad);
                clienteListCliente = em.merge(clienteListCliente);
                if (oldPrioridadOfClienteListCliente != null) {
                    oldPrioridadOfClienteListCliente.getClienteList().remove(clienteListCliente);
                    oldPrioridadOfClienteListCliente = em.merge(oldPrioridadOfClienteListCliente);
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
            List<Pedido> pedidoListOld = persistentPrioridad.getPedidoList();
            List<Pedido> pedidoListNew = prioridad.getPedidoList();
            List<Cliente> clienteListOld = persistentPrioridad.getClienteList();
            List<Cliente> clienteListNew = prioridad.getClienteList();
            List<String> illegalOrphanMessages = null;
            for (Pedido pedidoListOldPedido : pedidoListOld) {
                if (!pedidoListNew.contains(pedidoListOldPedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Pedido " + pedidoListOldPedido + " since its prioridad field is not nullable.");
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
            prioridad.setPedidoList(pedidoListNew);
            List<Cliente> attachedClienteListNew = new ArrayList<Cliente>();
            for (Cliente clienteListNewClienteToAttach : clienteListNew) {
                clienteListNewClienteToAttach = em.getReference(clienteListNewClienteToAttach.getClass(), clienteListNewClienteToAttach.getIdcliente());
                attachedClienteListNew.add(clienteListNewClienteToAttach);
            }
            clienteListNew = attachedClienteListNew;
            prioridad.setClienteList(clienteListNew);
            prioridad = em.merge(prioridad);
            for (Pedido pedidoListNewPedido : pedidoListNew) {
                if (!pedidoListOld.contains(pedidoListNewPedido)) {
                    Prioridad oldPrioridadOfPedidoListNewPedido = pedidoListNewPedido.getPrioridad();
                    pedidoListNewPedido.setPrioridad(prioridad);
                    pedidoListNewPedido = em.merge(pedidoListNewPedido);
                    if (oldPrioridadOfPedidoListNewPedido != null && !oldPrioridadOfPedidoListNewPedido.equals(prioridad)) {
                        oldPrioridadOfPedidoListNewPedido.getPedidoList().remove(pedidoListNewPedido);
                        oldPrioridadOfPedidoListNewPedido = em.merge(oldPrioridadOfPedidoListNewPedido);
                    }
                }
            }
            for (Cliente clienteListOldCliente : clienteListOld) {
                if (!clienteListNew.contains(clienteListOldCliente)) {
                    clienteListOldCliente.setPrioridad(null);
                    clienteListOldCliente = em.merge(clienteListOldCliente);
                }
            }
            for (Cliente clienteListNewCliente : clienteListNew) {
                if (!clienteListOld.contains(clienteListNewCliente)) {
                    Prioridad oldPrioridadOfClienteListNewCliente = clienteListNewCliente.getPrioridad();
                    clienteListNewCliente.setPrioridad(prioridad);
                    clienteListNewCliente = em.merge(clienteListNewCliente);
                    if (oldPrioridadOfClienteListNewCliente != null && !oldPrioridadOfClienteListNewCliente.equals(prioridad)) {
                        oldPrioridadOfClienteListNewCliente.getClienteList().remove(clienteListNewCliente);
                        oldPrioridadOfClienteListNewCliente = em.merge(oldPrioridadOfClienteListNewCliente);
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
            List<Pedido> pedidoListOrphanCheck = prioridad.getPedidoList();
            for (Pedido pedidoListOrphanCheckPedido : pedidoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Prioridad (" + prioridad + ") cannot be destroyed since the Pedido " + pedidoListOrphanCheckPedido + " in its pedidoList field has a non-nullable prioridad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Cliente> clienteList = prioridad.getClienteList();
            for (Cliente clienteListCliente : clienteList) {
                clienteListCliente.setPrioridad(null);
                clienteListCliente = em.merge(clienteListCliente);
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
