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
        if (estadocliente.getClienteSet1() == null) {
            estadocliente.setClienteSet1(new HashSet<Cliente>());
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
            Set<Cliente> attachedClienteSet1 = new HashSet<Cliente>();
            for (Cliente clienteSet1ClienteToAttach : estadocliente.getClienteSet1()) {
                clienteSet1ClienteToAttach = em.getReference(clienteSet1ClienteToAttach.getClass(), clienteSet1ClienteToAttach.getIdcliente());
                attachedClienteSet1.add(clienteSet1ClienteToAttach);
            }
            estadocliente.setClienteSet1(attachedClienteSet1);
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
            for (Cliente clienteSet1Cliente : estadocliente.getClienteSet1()) {
                Estadocliente oldEstado1OfClienteSet1Cliente = clienteSet1Cliente.getEstado1();
                clienteSet1Cliente.setEstado1(estadocliente);
                clienteSet1Cliente = em.merge(clienteSet1Cliente);
                if (oldEstado1OfClienteSet1Cliente != null) {
                    oldEstado1OfClienteSet1Cliente.getClienteSet1().remove(clienteSet1Cliente);
                    oldEstado1OfClienteSet1Cliente = em.merge(oldEstado1OfClienteSet1Cliente);
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
            Set<Cliente> clienteSet1Old = persistentEstadocliente.getClienteSet1();
            Set<Cliente> clienteSet1New = estadocliente.getClienteSet1();
            Set<Cliente> attachedClienteSetNew = new HashSet<Cliente>();
            for (Cliente clienteSetNewClienteToAttach : clienteSetNew) {
                clienteSetNewClienteToAttach = em.getReference(clienteSetNewClienteToAttach.getClass(), clienteSetNewClienteToAttach.getIdcliente());
                attachedClienteSetNew.add(clienteSetNewClienteToAttach);
            }
            clienteSetNew = attachedClienteSetNew;
            estadocliente.setClienteSet(clienteSetNew);
            Set<Cliente> attachedClienteSet1New = new HashSet<Cliente>();
            for (Cliente clienteSet1NewClienteToAttach : clienteSet1New) {
                clienteSet1NewClienteToAttach = em.getReference(clienteSet1NewClienteToAttach.getClass(), clienteSet1NewClienteToAttach.getIdcliente());
                attachedClienteSet1New.add(clienteSet1NewClienteToAttach);
            }
            clienteSet1New = attachedClienteSet1New;
            estadocliente.setClienteSet1(clienteSet1New);
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
            for (Cliente clienteSet1OldCliente : clienteSet1Old) {
                if (!clienteSet1New.contains(clienteSet1OldCliente)) {
                    clienteSet1OldCliente.setEstado1(null);
                    clienteSet1OldCliente = em.merge(clienteSet1OldCliente);
                }
            }
            for (Cliente clienteSet1NewCliente : clienteSet1New) {
                if (!clienteSet1Old.contains(clienteSet1NewCliente)) {
                    Estadocliente oldEstado1OfClienteSet1NewCliente = clienteSet1NewCliente.getEstado1();
                    clienteSet1NewCliente.setEstado1(estadocliente);
                    clienteSet1NewCliente = em.merge(clienteSet1NewCliente);
                    if (oldEstado1OfClienteSet1NewCliente != null && !oldEstado1OfClienteSet1NewCliente.equals(estadocliente)) {
                        oldEstado1OfClienteSet1NewCliente.getClienteSet1().remove(clienteSet1NewCliente);
                        oldEstado1OfClienteSet1NewCliente = em.merge(oldEstado1OfClienteSet1NewCliente);
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
            Set<Cliente> clienteSet1 = estadocliente.getClienteSet1();
            for (Cliente clienteSet1Cliente : clienteSet1) {
                clienteSet1Cliente.setEstado1(null);
                clienteSet1Cliente = em.merge(clienteSet1Cliente);
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
