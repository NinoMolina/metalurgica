/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Condicioniva;
import entity.Domicilio;
import entity.Estadocliente;
import entity.Prioridad;
import entity.Responsable;
import entity.Usuario;
import entity.Pedido;
import java.util.HashSet;
import java.util.Set;
import entity.Reclamocliente;

/**
 *
 * @author Nino
 */
public class ClienteJpaController {

    public ClienteJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) throws PreexistingEntityException, Exception {
        if (cliente.getPedidoSet() == null) {
            cliente.setPedidoSet(new HashSet<Pedido>());
        }
        if (cliente.getReclamoclienteSet() == null) {
            cliente.setReclamoclienteSet(new HashSet<Reclamocliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Condicioniva condicioniva = cliente.getCondicioniva();
            if (condicioniva != null) {
                condicioniva = em.getReference(condicioniva.getClass(), condicioniva.getIdcondicioniva());
                cliente.setCondicioniva(condicioniva);
            }
            Domicilio domicilio = cliente.getDomicilio();
            if (domicilio != null) {
                domicilio = em.getReference(domicilio.getClass(), domicilio.getIddomicilio());
                cliente.setDomicilio(domicilio);
            }
            Estadocliente estado = cliente.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                cliente.setEstado(estado);
            }
            Prioridad prioridad = cliente.getPrioridad();
            if (prioridad != null) {
                prioridad = em.getReference(prioridad.getClass(), prioridad.getIdprioridad());
                cliente.setPrioridad(prioridad);
            }
            Responsable responsable = cliente.getResponsable();
            if (responsable != null) {
                responsable = em.getReference(responsable.getClass(), responsable.getIdresponsable());
                cliente.setResponsable(responsable);
            }
            Usuario usuario = cliente.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getIdusuario());
                cliente.setUsuario(usuario);
            }
            Set<Pedido> attachedPedidoSet = new HashSet<Pedido>();
            for (Pedido pedidoSetPedidoToAttach : cliente.getPedidoSet()) {
                pedidoSetPedidoToAttach = em.getReference(pedidoSetPedidoToAttach.getClass(), pedidoSetPedidoToAttach.getIdpedido());
                attachedPedidoSet.add(pedidoSetPedidoToAttach);
            }
            cliente.setPedidoSet(attachedPedidoSet);
            Set<Reclamocliente> attachedReclamoclienteSet = new HashSet<Reclamocliente>();
            for (Reclamocliente reclamoclienteSetReclamoclienteToAttach : cliente.getReclamoclienteSet()) {
                reclamoclienteSetReclamoclienteToAttach = em.getReference(reclamoclienteSetReclamoclienteToAttach.getClass(), reclamoclienteSetReclamoclienteToAttach.getIdreclamo());
                attachedReclamoclienteSet.add(reclamoclienteSetReclamoclienteToAttach);
            }
            cliente.setReclamoclienteSet(attachedReclamoclienteSet);
            em.persist(cliente);
            if (condicioniva != null) {
                condicioniva.getClienteSet().add(cliente);
                condicioniva = em.merge(condicioniva);
            }
            if (domicilio != null) {
                domicilio.getClienteSet().add(cliente);
                domicilio = em.merge(domicilio);
            }
            if (estado != null) {
                estado.getClienteSet().add(cliente);
                estado = em.merge(estado);
            }
            if (prioridad != null) {
                prioridad.getClienteSet().add(cliente);
                prioridad = em.merge(prioridad);
            }
            if (responsable != null) {
                responsable.getClienteSet().add(cliente);
                responsable = em.merge(responsable);
            }
            if (usuario != null) {
                usuario.getClienteSet().add(cliente);
                usuario = em.merge(usuario);
            }
            for (Pedido pedidoSetPedido : cliente.getPedidoSet()) {
                Cliente oldClienteOfPedidoSetPedido = pedidoSetPedido.getCliente();
                pedidoSetPedido.setCliente(cliente);
                pedidoSetPedido = em.merge(pedidoSetPedido);
                if (oldClienteOfPedidoSetPedido != null) {
                    oldClienteOfPedidoSetPedido.getPedidoSet().remove(pedidoSetPedido);
                    oldClienteOfPedidoSetPedido = em.merge(oldClienteOfPedidoSetPedido);
                }
            }
            for (Reclamocliente reclamoclienteSetReclamocliente : cliente.getReclamoclienteSet()) {
                Cliente oldClienteOfReclamoclienteSetReclamocliente = reclamoclienteSetReclamocliente.getCliente();
                reclamoclienteSetReclamocliente.setCliente(cliente);
                reclamoclienteSetReclamocliente = em.merge(reclamoclienteSetReclamocliente);
                if (oldClienteOfReclamoclienteSetReclamocliente != null) {
                    oldClienteOfReclamoclienteSetReclamocliente.getReclamoclienteSet().remove(reclamoclienteSetReclamocliente);
                    oldClienteOfReclamoclienteSetReclamocliente = em.merge(oldClienteOfReclamoclienteSetReclamocliente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCliente(cliente.getIdcliente()) != null) {
                throw new PreexistingEntityException("Cliente " + cliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getIdcliente());
            Condicioniva condicionivaOld = persistentCliente.getCondicioniva();
            Condicioniva condicionivaNew = cliente.getCondicioniva();
            Domicilio domicilioOld = persistentCliente.getDomicilio();
            Domicilio domicilioNew = cliente.getDomicilio();
            Estadocliente estadoOld = persistentCliente.getEstado();
            Estadocliente estadoNew = cliente.getEstado();
            Prioridad prioridadOld = persistentCliente.getPrioridad();
            Prioridad prioridadNew = cliente.getPrioridad();
            Responsable responsableOld = persistentCliente.getResponsable();
            Responsable responsableNew = cliente.getResponsable();
            Usuario usuarioOld = persistentCliente.getUsuario();
            Usuario usuarioNew = cliente.getUsuario();
            Set<Pedido> pedidoSetOld = persistentCliente.getPedidoSet();
            Set<Pedido> pedidoSetNew = cliente.getPedidoSet();
            Set<Reclamocliente> reclamoclienteSetOld = persistentCliente.getReclamoclienteSet();
            Set<Reclamocliente> reclamoclienteSetNew = cliente.getReclamoclienteSet();
            if (condicionivaNew != null) {
                condicionivaNew = em.getReference(condicionivaNew.getClass(), condicionivaNew.getIdcondicioniva());
                cliente.setCondicioniva(condicionivaNew);
            }
            if (domicilioNew != null) {
                domicilioNew = em.getReference(domicilioNew.getClass(), domicilioNew.getIddomicilio());
                cliente.setDomicilio(domicilioNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                cliente.setEstado(estadoNew);
            }
            if (prioridadNew != null) {
                prioridadNew = em.getReference(prioridadNew.getClass(), prioridadNew.getIdprioridad());
                cliente.setPrioridad(prioridadNew);
            }
            if (responsableNew != null) {
                responsableNew = em.getReference(responsableNew.getClass(), responsableNew.getIdresponsable());
                cliente.setResponsable(responsableNew);
            }
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getIdusuario());
                cliente.setUsuario(usuarioNew);
            }
            Set<Pedido> attachedPedidoSetNew = new HashSet<Pedido>();
            for (Pedido pedidoSetNewPedidoToAttach : pedidoSetNew) {
                pedidoSetNewPedidoToAttach = em.getReference(pedidoSetNewPedidoToAttach.getClass(), pedidoSetNewPedidoToAttach.getIdpedido());
                attachedPedidoSetNew.add(pedidoSetNewPedidoToAttach);
            }
            pedidoSetNew = attachedPedidoSetNew;
            cliente.setPedidoSet(pedidoSetNew);
            Set<Reclamocliente> attachedReclamoclienteSetNew = new HashSet<Reclamocliente>();
            for (Reclamocliente reclamoclienteSetNewReclamoclienteToAttach : reclamoclienteSetNew) {
                reclamoclienteSetNewReclamoclienteToAttach = em.getReference(reclamoclienteSetNewReclamoclienteToAttach.getClass(), reclamoclienteSetNewReclamoclienteToAttach.getIdreclamo());
                attachedReclamoclienteSetNew.add(reclamoclienteSetNewReclamoclienteToAttach);
            }
            reclamoclienteSetNew = attachedReclamoclienteSetNew;
            cliente.setReclamoclienteSet(reclamoclienteSetNew);
            cliente = em.merge(cliente);
            if (condicionivaOld != null && !condicionivaOld.equals(condicionivaNew)) {
                condicionivaOld.getClienteSet().remove(cliente);
                condicionivaOld = em.merge(condicionivaOld);
            }
            if (condicionivaNew != null && !condicionivaNew.equals(condicionivaOld)) {
                condicionivaNew.getClienteSet().add(cliente);
                condicionivaNew = em.merge(condicionivaNew);
            }
            if (domicilioOld != null && !domicilioOld.equals(domicilioNew)) {
                domicilioOld.getClienteSet().remove(cliente);
                domicilioOld = em.merge(domicilioOld);
            }
            if (domicilioNew != null && !domicilioNew.equals(domicilioOld)) {
                domicilioNew.getClienteSet().add(cliente);
                domicilioNew = em.merge(domicilioNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getClienteSet().remove(cliente);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getClienteSet().add(cliente);
                estadoNew = em.merge(estadoNew);
            }
            if (prioridadOld != null && !prioridadOld.equals(prioridadNew)) {
                prioridadOld.getClienteSet().remove(cliente);
                prioridadOld = em.merge(prioridadOld);
            }
            if (prioridadNew != null && !prioridadNew.equals(prioridadOld)) {
                prioridadNew.getClienteSet().add(cliente);
                prioridadNew = em.merge(prioridadNew);
            }
            if (responsableOld != null && !responsableOld.equals(responsableNew)) {
                responsableOld.getClienteSet().remove(cliente);
                responsableOld = em.merge(responsableOld);
            }
            if (responsableNew != null && !responsableNew.equals(responsableOld)) {
                responsableNew.getClienteSet().add(cliente);
                responsableNew = em.merge(responsableNew);
            }
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getClienteSet().remove(cliente);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getClienteSet().add(cliente);
                usuarioNew = em.merge(usuarioNew);
            }
            for (Pedido pedidoSetOldPedido : pedidoSetOld) {
                if (!pedidoSetNew.contains(pedidoSetOldPedido)) {
                    pedidoSetOldPedido.setCliente(null);
                    pedidoSetOldPedido = em.merge(pedidoSetOldPedido);
                }
            }
            for (Pedido pedidoSetNewPedido : pedidoSetNew) {
                if (!pedidoSetOld.contains(pedidoSetNewPedido)) {
                    Cliente oldClienteOfPedidoSetNewPedido = pedidoSetNewPedido.getCliente();
                    pedidoSetNewPedido.setCliente(cliente);
                    pedidoSetNewPedido = em.merge(pedidoSetNewPedido);
                    if (oldClienteOfPedidoSetNewPedido != null && !oldClienteOfPedidoSetNewPedido.equals(cliente)) {
                        oldClienteOfPedidoSetNewPedido.getPedidoSet().remove(pedidoSetNewPedido);
                        oldClienteOfPedidoSetNewPedido = em.merge(oldClienteOfPedidoSetNewPedido);
                    }
                }
            }
            for (Reclamocliente reclamoclienteSetOldReclamocliente : reclamoclienteSetOld) {
                if (!reclamoclienteSetNew.contains(reclamoclienteSetOldReclamocliente)) {
                    reclamoclienteSetOldReclamocliente.setCliente(null);
                    reclamoclienteSetOldReclamocliente = em.merge(reclamoclienteSetOldReclamocliente);
                }
            }
            for (Reclamocliente reclamoclienteSetNewReclamocliente : reclamoclienteSetNew) {
                if (!reclamoclienteSetOld.contains(reclamoclienteSetNewReclamocliente)) {
                    Cliente oldClienteOfReclamoclienteSetNewReclamocliente = reclamoclienteSetNewReclamocliente.getCliente();
                    reclamoclienteSetNewReclamocliente.setCliente(cliente);
                    reclamoclienteSetNewReclamocliente = em.merge(reclamoclienteSetNewReclamocliente);
                    if (oldClienteOfReclamoclienteSetNewReclamocliente != null && !oldClienteOfReclamoclienteSetNewReclamocliente.equals(cliente)) {
                        oldClienteOfReclamoclienteSetNewReclamocliente.getReclamoclienteSet().remove(reclamoclienteSetNewReclamocliente);
                        oldClienteOfReclamoclienteSetNewReclamocliente = em.merge(oldClienteOfReclamoclienteSetNewReclamocliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = cliente.getIdcliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getIdcliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            Condicioniva condicioniva = cliente.getCondicioniva();
            if (condicioniva != null) {
                condicioniva.getClienteSet().remove(cliente);
                condicioniva = em.merge(condicioniva);
            }
            Domicilio domicilio = cliente.getDomicilio();
            if (domicilio != null) {
                domicilio.getClienteSet().remove(cliente);
                domicilio = em.merge(domicilio);
            }
            Estadocliente estado = cliente.getEstado();
            if (estado != null) {
                estado.getClienteSet().remove(cliente);
                estado = em.merge(estado);
            }
            Prioridad prioridad = cliente.getPrioridad();
            if (prioridad != null) {
                prioridad.getClienteSet().remove(cliente);
                prioridad = em.merge(prioridad);
            }
            Responsable responsable = cliente.getResponsable();
            if (responsable != null) {
                responsable.getClienteSet().remove(cliente);
                responsable = em.merge(responsable);
            }
            Usuario usuario = cliente.getUsuario();
            if (usuario != null) {
                usuario.getClienteSet().remove(cliente);
                usuario = em.merge(usuario);
            }
            Set<Pedido> pedidoSet = cliente.getPedidoSet();
            for (Pedido pedidoSetPedido : pedidoSet) {
                pedidoSetPedido.setCliente(null);
                pedidoSetPedido = em.merge(pedidoSetPedido);
            }
            Set<Reclamocliente> reclamoclienteSet = cliente.getReclamoclienteSet();
            for (Reclamocliente reclamoclienteSetReclamocliente : reclamoclienteSet) {
                reclamoclienteSetReclamocliente.setCliente(null);
                reclamoclienteSetReclamocliente = em.merge(reclamoclienteSetReclamocliente);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
