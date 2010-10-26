/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Estadocliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Cliente;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class EstadoclienteJpaController {

    public EstadoclienteJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadocliente estadocliente) throws PreexistingEntityException, Exception {
        if (estadocliente.getClienteSet() == null) {
            estadocliente.setClienteSet(new HashSet<Cliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Cliente> attachedClienteSet = new HashSet<Cliente>();
            for (Cliente clienteSetClienteToAttach : estadocliente.getClienteSet()) {
                clienteSetClienteToAttach = em.getReference(clienteSetClienteToAttach.getClass(), clienteSetClienteToAttach.getIdcliente());
                attachedClienteSet.add(clienteSetClienteToAttach);
            }
            estadocliente.setClienteSet(attachedClienteSet);
            em.persist(estadocliente);
            for (Cliente clienteSetCliente : estadocliente.getClienteSet()) {
                Estadocliente oldEstadoOfClienteSetCliente = clienteSetCliente.getEstado();
                clienteSetCliente.setEstado(estadocliente);
                clienteSetCliente = em.merge(clienteSetCliente);
                if (oldEstadoOfClienteSetCliente != null) {
                    oldEstadoOfClienteSetCliente.getClienteSet().remove(clienteSetCliente);
                    oldEstadoOfClienteSetCliente = em.merge(oldEstadoOfClienteSetCliente);
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
            Set<Cliente> clienteSetOld = persistentEstadocliente.getClienteSet();
            Set<Cliente> clienteSetNew = estadocliente.getClienteSet();
            Set<Cliente> attachedClienteSetNew = new HashSet<Cliente>();
            for (Cliente clienteSetNewClienteToAttach : clienteSetNew) {
                clienteSetNewClienteToAttach = em.getReference(clienteSetNewClienteToAttach.getClass(), clienteSetNewClienteToAttach.getIdcliente());
                attachedClienteSetNew.add(clienteSetNewClienteToAttach);
            }
            clienteSetNew = attachedClienteSetNew;
            estadocliente.setClienteSet(clienteSetNew);
            estadocliente = em.merge(estadocliente);
            for (Cliente clienteSetOldCliente : clienteSetOld) {
                if (!clienteSetNew.contains(clienteSetOldCliente)) {
                    clienteSetOldCliente.setEstado(null);
                    clienteSetOldCliente = em.merge(clienteSetOldCliente);
                }
            }
            for (Cliente clienteSetNewCliente : clienteSetNew) {
                if (!clienteSetOld.contains(clienteSetNewCliente)) {
                    Estadocliente oldEstadoOfClienteSetNewCliente = clienteSetNewCliente.getEstado();
                    clienteSetNewCliente.setEstado(estadocliente);
                    clienteSetNewCliente = em.merge(clienteSetNewCliente);
                    if (oldEstadoOfClienteSetNewCliente != null && !oldEstadoOfClienteSetNewCliente.equals(estadocliente)) {
                        oldEstadoOfClienteSetNewCliente.getClienteSet().remove(clienteSetNewCliente);
                        oldEstadoOfClienteSetNewCliente = em.merge(oldEstadoOfClienteSetNewCliente);
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
            Set<Cliente> clienteSet = estadocliente.getClienteSet();
            for (Cliente clienteSetCliente : clienteSet) {
                clienteSetCliente.setEstado(null);
                clienteSetCliente = em.merge(clienteSetCliente);
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
