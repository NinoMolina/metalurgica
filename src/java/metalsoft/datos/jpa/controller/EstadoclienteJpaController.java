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
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Estadocliente;

/**
 *
 * @author Nino
 */
public class EstadoclienteJpaController implements Serializable {

    public EstadoclienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadocliente estadocliente) throws PreexistingEntityException, Exception {
        if (estadocliente.getClienteList() == null) {
            estadocliente.setClienteList(new ArrayList<Cliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Cliente> attachedClienteList = new ArrayList<Cliente>();
            for (Cliente clienteListClienteToAttach : estadocliente.getClienteList()) {
                clienteListClienteToAttach = em.getReference(clienteListClienteToAttach.getClass(), clienteListClienteToAttach.getIdcliente());
                attachedClienteList.add(clienteListClienteToAttach);
            }
            estadocliente.setClienteList(attachedClienteList);
            em.persist(estadocliente);
            for (Cliente clienteListCliente : estadocliente.getClienteList()) {
                Estadocliente oldEstadoOfClienteListCliente = clienteListCliente.getEstado();
                clienteListCliente.setEstado(estadocliente);
                clienteListCliente = em.merge(clienteListCliente);
                if (oldEstadoOfClienteListCliente != null) {
                    oldEstadoOfClienteListCliente.getClienteList().remove(clienteListCliente);
                    oldEstadoOfClienteListCliente = em.merge(oldEstadoOfClienteListCliente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadocliente(estadocliente.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadocliente " + estadocliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadocliente estadocliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadocliente persistentEstadocliente = em.find(Estadocliente.class, estadocliente.getIdestado());
            List<Cliente> clienteListOld = persistentEstadocliente.getClienteList();
            List<Cliente> clienteListNew = estadocliente.getClienteList();
            List<Cliente> attachedClienteListNew = new ArrayList<Cliente>();
            for (Cliente clienteListNewClienteToAttach : clienteListNew) {
                clienteListNewClienteToAttach = em.getReference(clienteListNewClienteToAttach.getClass(), clienteListNewClienteToAttach.getIdcliente());
                attachedClienteListNew.add(clienteListNewClienteToAttach);
            }
            clienteListNew = attachedClienteListNew;
            estadocliente.setClienteList(clienteListNew);
            estadocliente = em.merge(estadocliente);
            for (Cliente clienteListOldCliente : clienteListOld) {
                if (!clienteListNew.contains(clienteListOldCliente)) {
                    clienteListOldCliente.setEstado(null);
                    clienteListOldCliente = em.merge(clienteListOldCliente);
                }
            }
            for (Cliente clienteListNewCliente : clienteListNew) {
                if (!clienteListOld.contains(clienteListNewCliente)) {
                    Estadocliente oldEstadoOfClienteListNewCliente = clienteListNewCliente.getEstado();
                    clienteListNewCliente.setEstado(estadocliente);
                    clienteListNewCliente = em.merge(clienteListNewCliente);
                    if (oldEstadoOfClienteListNewCliente != null && !oldEstadoOfClienteListNewCliente.equals(estadocliente)) {
                        oldEstadoOfClienteListNewCliente.getClienteList().remove(clienteListNewCliente);
                        oldEstadoOfClienteListNewCliente = em.merge(oldEstadoOfClienteListNewCliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadocliente.getIdestado();
                if (findEstadocliente(id) == null) {
                    throw new NonexistentEntityException("The estadocliente with id " + id + " no longer exists.");
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
            Estadocliente estadocliente;
            try {
                estadocliente = em.getReference(Estadocliente.class, id);
                estadocliente.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadocliente with id " + id + " no longer exists.", enfe);
            }
            List<Cliente> clienteList = estadocliente.getClienteList();
            for (Cliente clienteListCliente : clienteList) {
                clienteListCliente.setEstado(null);
                clienteListCliente = em.merge(clienteListCliente);
            }
            em.remove(estadocliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadocliente> findEstadoclienteEntities() {
        return findEstadoclienteEntities(true, -1, -1);
    }

    public List<Estadocliente> findEstadoclienteEntities(int maxResults, int firstResult) {
        return findEstadoclienteEntities(false, maxResults, firstResult);
    }

    private List<Estadocliente> findEstadoclienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadocliente.class));
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

    public Estadocliente findEstadocliente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadocliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoclienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadocliente> rt = cq.from(Estadocliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
