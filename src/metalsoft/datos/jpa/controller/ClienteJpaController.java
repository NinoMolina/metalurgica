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
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Cliente;
import metalsoft.datos.jpa.entity.Usuario;
import metalsoft.datos.jpa.entity.Responsable;
import metalsoft.datos.jpa.entity.Prioridad;
import metalsoft.datos.jpa.entity.Estadocliente;
import metalsoft.datos.jpa.entity.Domicilio;
import metalsoft.datos.jpa.entity.Condicioniva;
import metalsoft.datos.jpa.entity.Pedido;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Reclamocliente;

/**
 *
 * @author Nino
 */
public class ClienteJpaController implements Serializable {

    public ClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) throws PreexistingEntityException, Exception {
        if (cliente.getPedidoList() == null) {
            cliente.setPedidoList(new ArrayList<Pedido>());
        }
        if (cliente.getReclamoclienteList() == null) {
            cliente.setReclamoclienteList(new ArrayList<Reclamocliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario = cliente.getUsuario();
            if (usuario != null) {
                usuario = em.getReference(usuario.getClass(), usuario.getIdusuario());
                cliente.setUsuario(usuario);
            }
            Responsable responsable = cliente.getResponsable();
            if (responsable != null) {
                responsable = em.getReference(responsable.getClass(), responsable.getIdresponsable());
                cliente.setResponsable(responsable);
            }
            Prioridad prioridad = cliente.getPrioridad();
            if (prioridad != null) {
                prioridad = em.getReference(prioridad.getClass(), prioridad.getIdprioridad());
                cliente.setPrioridad(prioridad);
            }
            Estadocliente estado = cliente.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                cliente.setEstado(estado);
            }
            Domicilio domicilio = cliente.getDomicilio();
            if (domicilio != null) {
                domicilio = em.getReference(domicilio.getClass(), domicilio.getIddomicilio());
                cliente.setDomicilio(domicilio);
            }
            Condicioniva condicioniva = cliente.getCondicioniva();
            if (condicioniva != null) {
                condicioniva = em.getReference(condicioniva.getClass(), condicioniva.getIdcondicioniva());
                cliente.setCondicioniva(condicioniva);
            }
            List<Pedido> attachedPedidoList = new ArrayList<Pedido>();
            for (Pedido pedidoListPedidoToAttach : cliente.getPedidoList()) {
                pedidoListPedidoToAttach = em.getReference(pedidoListPedidoToAttach.getClass(), pedidoListPedidoToAttach.getIdpedido());
                attachedPedidoList.add(pedidoListPedidoToAttach);
            }
            cliente.setPedidoList(attachedPedidoList);
            List<Reclamocliente> attachedReclamoclienteList = new ArrayList<Reclamocliente>();
            for (Reclamocliente reclamoclienteListReclamoclienteToAttach : cliente.getReclamoclienteList()) {
                reclamoclienteListReclamoclienteToAttach = em.getReference(reclamoclienteListReclamoclienteToAttach.getClass(), reclamoclienteListReclamoclienteToAttach.getIdreclamo());
                attachedReclamoclienteList.add(reclamoclienteListReclamoclienteToAttach);
            }
            cliente.setReclamoclienteList(attachedReclamoclienteList);
            em.persist(cliente);
            if (usuario != null) {
                usuario.getClienteList().add(cliente);
                usuario = em.merge(usuario);
            }
            if (responsable != null) {
                responsable.getClienteList().add(cliente);
                responsable = em.merge(responsable);
            }
            if (prioridad != null) {
                prioridad.getClienteList().add(cliente);
                prioridad = em.merge(prioridad);
            }
            if (estado != null) {
                estado.getClienteList().add(cliente);
                estado = em.merge(estado);
            }
            if (domicilio != null) {
                domicilio.getClienteList().add(cliente);
                domicilio = em.merge(domicilio);
            }
            if (condicioniva != null) {
                condicioniva.getClienteList().add(cliente);
                condicioniva = em.merge(condicioniva);
            }
            for (Pedido pedidoListPedido : cliente.getPedidoList()) {
                Cliente oldClienteOfPedidoListPedido = pedidoListPedido.getCliente();
                pedidoListPedido.setCliente(cliente);
                pedidoListPedido = em.merge(pedidoListPedido);
                if (oldClienteOfPedidoListPedido != null) {
                    oldClienteOfPedidoListPedido.getPedidoList().remove(pedidoListPedido);
                    oldClienteOfPedidoListPedido = em.merge(oldClienteOfPedidoListPedido);
                }
            }
            for (Reclamocliente reclamoclienteListReclamocliente : cliente.getReclamoclienteList()) {
                Cliente oldClienteOfReclamoclienteListReclamocliente = reclamoclienteListReclamocliente.getCliente();
                reclamoclienteListReclamocliente.setCliente(cliente);
                reclamoclienteListReclamocliente = em.merge(reclamoclienteListReclamocliente);
                if (oldClienteOfReclamoclienteListReclamocliente != null) {
                    oldClienteOfReclamoclienteListReclamocliente.getReclamoclienteList().remove(reclamoclienteListReclamocliente);
                    oldClienteOfReclamoclienteListReclamocliente = em.merge(oldClienteOfReclamoclienteListReclamocliente);
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
            Usuario usuarioOld = persistentCliente.getUsuario();
            Usuario usuarioNew = cliente.getUsuario();
            Responsable responsableOld = persistentCliente.getResponsable();
            Responsable responsableNew = cliente.getResponsable();
            Prioridad prioridadOld = persistentCliente.getPrioridad();
            Prioridad prioridadNew = cliente.getPrioridad();
            Estadocliente estadoOld = persistentCliente.getEstado();
            Estadocliente estadoNew = cliente.getEstado();
            Domicilio domicilioOld = persistentCliente.getDomicilio();
            Domicilio domicilioNew = cliente.getDomicilio();
            Condicioniva condicionivaOld = persistentCliente.getCondicioniva();
            Condicioniva condicionivaNew = cliente.getCondicioniva();
            List<Pedido> pedidoListOld = persistentCliente.getPedidoList();
            List<Pedido> pedidoListNew = cliente.getPedidoList();
            List<Reclamocliente> reclamoclienteListOld = persistentCliente.getReclamoclienteList();
            List<Reclamocliente> reclamoclienteListNew = cliente.getReclamoclienteList();
            if (usuarioNew != null) {
                usuarioNew = em.getReference(usuarioNew.getClass(), usuarioNew.getIdusuario());
                cliente.setUsuario(usuarioNew);
            }
            if (responsableNew != null) {
                responsableNew = em.getReference(responsableNew.getClass(), responsableNew.getIdresponsable());
                cliente.setResponsable(responsableNew);
            }
            if (prioridadNew != null) {
                prioridadNew = em.getReference(prioridadNew.getClass(), prioridadNew.getIdprioridad());
                cliente.setPrioridad(prioridadNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                cliente.setEstado(estadoNew);
            }
            if (domicilioNew != null) {
                domicilioNew = em.getReference(domicilioNew.getClass(), domicilioNew.getIddomicilio());
                cliente.setDomicilio(domicilioNew);
            }
            if (condicionivaNew != null) {
                condicionivaNew = em.getReference(condicionivaNew.getClass(), condicionivaNew.getIdcondicioniva());
                cliente.setCondicioniva(condicionivaNew);
            }
            List<Pedido> attachedPedidoListNew = new ArrayList<Pedido>();
            for (Pedido pedidoListNewPedidoToAttach : pedidoListNew) {
                pedidoListNewPedidoToAttach = em.getReference(pedidoListNewPedidoToAttach.getClass(), pedidoListNewPedidoToAttach.getIdpedido());
                attachedPedidoListNew.add(pedidoListNewPedidoToAttach);
            }
            pedidoListNew = attachedPedidoListNew;
            cliente.setPedidoList(pedidoListNew);
            List<Reclamocliente> attachedReclamoclienteListNew = new ArrayList<Reclamocliente>();
            for (Reclamocliente reclamoclienteListNewReclamoclienteToAttach : reclamoclienteListNew) {
                reclamoclienteListNewReclamoclienteToAttach = em.getReference(reclamoclienteListNewReclamoclienteToAttach.getClass(), reclamoclienteListNewReclamoclienteToAttach.getIdreclamo());
                attachedReclamoclienteListNew.add(reclamoclienteListNewReclamoclienteToAttach);
            }
            reclamoclienteListNew = attachedReclamoclienteListNew;
            cliente.setReclamoclienteList(reclamoclienteListNew);
            cliente = em.merge(cliente);
            if (usuarioOld != null && !usuarioOld.equals(usuarioNew)) {
                usuarioOld.getClienteList().remove(cliente);
                usuarioOld = em.merge(usuarioOld);
            }
            if (usuarioNew != null && !usuarioNew.equals(usuarioOld)) {
                usuarioNew.getClienteList().add(cliente);
                usuarioNew = em.merge(usuarioNew);
            }
            if (responsableOld != null && !responsableOld.equals(responsableNew)) {
                responsableOld.getClienteList().remove(cliente);
                responsableOld = em.merge(responsableOld);
            }
            if (responsableNew != null && !responsableNew.equals(responsableOld)) {
                responsableNew.getClienteList().add(cliente);
                responsableNew = em.merge(responsableNew);
            }
            if (prioridadOld != null && !prioridadOld.equals(prioridadNew)) {
                prioridadOld.getClienteList().remove(cliente);
                prioridadOld = em.merge(prioridadOld);
            }
            if (prioridadNew != null && !prioridadNew.equals(prioridadOld)) {
                prioridadNew.getClienteList().add(cliente);
                prioridadNew = em.merge(prioridadNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getClienteList().remove(cliente);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getClienteList().add(cliente);
                estadoNew = em.merge(estadoNew);
            }
            if (domicilioOld != null && !domicilioOld.equals(domicilioNew)) {
                domicilioOld.getClienteList().remove(cliente);
                domicilioOld = em.merge(domicilioOld);
            }
            if (domicilioNew != null && !domicilioNew.equals(domicilioOld)) {
                domicilioNew.getClienteList().add(cliente);
                domicilioNew = em.merge(domicilioNew);
            }
            if (condicionivaOld != null && !condicionivaOld.equals(condicionivaNew)) {
                condicionivaOld.getClienteList().remove(cliente);
                condicionivaOld = em.merge(condicionivaOld);
            }
            if (condicionivaNew != null && !condicionivaNew.equals(condicionivaOld)) {
                condicionivaNew.getClienteList().add(cliente);
                condicionivaNew = em.merge(condicionivaNew);
            }
            for (Pedido pedidoListOldPedido : pedidoListOld) {
                if (!pedidoListNew.contains(pedidoListOldPedido)) {
                    pedidoListOldPedido.setCliente(null);
                    pedidoListOldPedido = em.merge(pedidoListOldPedido);
                }
            }
            for (Pedido pedidoListNewPedido : pedidoListNew) {
                if (!pedidoListOld.contains(pedidoListNewPedido)) {
                    Cliente oldClienteOfPedidoListNewPedido = pedidoListNewPedido.getCliente();
                    pedidoListNewPedido.setCliente(cliente);
                    pedidoListNewPedido = em.merge(pedidoListNewPedido);
                    if (oldClienteOfPedidoListNewPedido != null && !oldClienteOfPedidoListNewPedido.equals(cliente)) {
                        oldClienteOfPedidoListNewPedido.getPedidoList().remove(pedidoListNewPedido);
                        oldClienteOfPedidoListNewPedido = em.merge(oldClienteOfPedidoListNewPedido);
                    }
                }
            }
            for (Reclamocliente reclamoclienteListOldReclamocliente : reclamoclienteListOld) {
                if (!reclamoclienteListNew.contains(reclamoclienteListOldReclamocliente)) {
                    reclamoclienteListOldReclamocliente.setCliente(null);
                    reclamoclienteListOldReclamocliente = em.merge(reclamoclienteListOldReclamocliente);
                }
            }
            for (Reclamocliente reclamoclienteListNewReclamocliente : reclamoclienteListNew) {
                if (!reclamoclienteListOld.contains(reclamoclienteListNewReclamocliente)) {
                    Cliente oldClienteOfReclamoclienteListNewReclamocliente = reclamoclienteListNewReclamocliente.getCliente();
                    reclamoclienteListNewReclamocliente.setCliente(cliente);
                    reclamoclienteListNewReclamocliente = em.merge(reclamoclienteListNewReclamocliente);
                    if (oldClienteOfReclamoclienteListNewReclamocliente != null && !oldClienteOfReclamoclienteListNewReclamocliente.equals(cliente)) {
                        oldClienteOfReclamoclienteListNewReclamocliente.getReclamoclienteList().remove(reclamoclienteListNewReclamocliente);
                        oldClienteOfReclamoclienteListNewReclamocliente = em.merge(oldClienteOfReclamoclienteListNewReclamocliente);
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
            Usuario usuario = cliente.getUsuario();
            if (usuario != null) {
                usuario.getClienteList().remove(cliente);
                usuario = em.merge(usuario);
            }
            Responsable responsable = cliente.getResponsable();
            if (responsable != null) {
                responsable.getClienteList().remove(cliente);
                responsable = em.merge(responsable);
            }
            Prioridad prioridad = cliente.getPrioridad();
            if (prioridad != null) {
                prioridad.getClienteList().remove(cliente);
                prioridad = em.merge(prioridad);
            }
            Estadocliente estado = cliente.getEstado();
            if (estado != null) {
                estado.getClienteList().remove(cliente);
                estado = em.merge(estado);
            }
            Domicilio domicilio = cliente.getDomicilio();
            if (domicilio != null) {
                domicilio.getClienteList().remove(cliente);
                domicilio = em.merge(domicilio);
            }
            Condicioniva condicioniva = cliente.getCondicioniva();
            if (condicioniva != null) {
                condicioniva.getClienteList().remove(cliente);
                condicioniva = em.merge(condicioniva);
            }
            List<Pedido> pedidoList = cliente.getPedidoList();
            for (Pedido pedidoListPedido : pedidoList) {
                pedidoListPedido.setCliente(null);
                pedidoListPedido = em.merge(pedidoListPedido);
            }
            List<Reclamocliente> reclamoclienteList = cliente.getReclamoclienteList();
            for (Reclamocliente reclamoclienteListReclamocliente : reclamoclienteList) {
                reclamoclienteListReclamocliente.setCliente(null);
                reclamoclienteListReclamocliente = em.merge(reclamoclienteListReclamocliente);
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
