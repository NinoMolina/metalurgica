/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Reclamocliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Cliente;
import entity.Tiporeclamo;
import entity.Detallereclamocliente;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class ReclamoclienteJpaController {

    public ReclamoclienteJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reclamocliente reclamocliente) throws PreexistingEntityException, Exception {
        if (reclamocliente.getDetallereclamoclienteSet() == null) {
            reclamocliente.setDetallereclamoclienteSet(new HashSet<Detallereclamocliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente = reclamocliente.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getIdcliente());
                reclamocliente.setCliente(cliente);
            }
            Tiporeclamo tiporeclamo = reclamocliente.getTiporeclamo();
            if (tiporeclamo != null) {
                tiporeclamo = em.getReference(tiporeclamo.getClass(), tiporeclamo.getIdtiporeclamo());
                reclamocliente.setTiporeclamo(tiporeclamo);
            }
            Set<Detallereclamocliente> attachedDetallereclamoclienteSet = new HashSet<Detallereclamocliente>();
            for (Detallereclamocliente detallereclamoclienteSetDetallereclamoclienteToAttach : reclamocliente.getDetallereclamoclienteSet()) {
                detallereclamoclienteSetDetallereclamoclienteToAttach = em.getReference(detallereclamoclienteSetDetallereclamoclienteToAttach.getClass(), detallereclamoclienteSetDetallereclamoclienteToAttach.getDetallereclamoclientePK());
                attachedDetallereclamoclienteSet.add(detallereclamoclienteSetDetallereclamoclienteToAttach);
            }
            reclamocliente.setDetallereclamoclienteSet(attachedDetallereclamoclienteSet);
            em.persist(reclamocliente);
            if (cliente != null) {
                cliente.getReclamoclienteSet().add(reclamocliente);
                cliente = em.merge(cliente);
            }
            if (tiporeclamo != null) {
                tiporeclamo.getReclamoclienteSet().add(reclamocliente);
                tiporeclamo = em.merge(tiporeclamo);
            }
            for (Detallereclamocliente detallereclamoclienteSetDetallereclamocliente : reclamocliente.getDetallereclamoclienteSet()) {
                Reclamocliente oldReclamoclienteOfDetallereclamoclienteSetDetallereclamocliente = detallereclamoclienteSetDetallereclamocliente.getReclamocliente();
                detallereclamoclienteSetDetallereclamocliente.setReclamocliente(reclamocliente);
                detallereclamoclienteSetDetallereclamocliente = em.merge(detallereclamoclienteSetDetallereclamocliente);
                if (oldReclamoclienteOfDetallereclamoclienteSetDetallereclamocliente != null) {
                    oldReclamoclienteOfDetallereclamoclienteSetDetallereclamocliente.getDetallereclamoclienteSet().remove(detallereclamoclienteSetDetallereclamocliente);
                    oldReclamoclienteOfDetallereclamoclienteSetDetallereclamocliente = em.merge(oldReclamoclienteOfDetallereclamoclienteSetDetallereclamocliente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReclamocliente(reclamocliente.getIdreclamo()) != null) {
                throw new PreexistingEntityException("Reclamocliente " + reclamocliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reclamocliente reclamocliente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reclamocliente persistentReclamocliente = em.find(Reclamocliente.class, reclamocliente.getIdreclamo());
            Cliente clienteOld = persistentReclamocliente.getCliente();
            Cliente clienteNew = reclamocliente.getCliente();
            Tiporeclamo tiporeclamoOld = persistentReclamocliente.getTiporeclamo();
            Tiporeclamo tiporeclamoNew = reclamocliente.getTiporeclamo();
            Set<Detallereclamocliente> detallereclamoclienteSetOld = persistentReclamocliente.getDetallereclamoclienteSet();
            Set<Detallereclamocliente> detallereclamoclienteSetNew = reclamocliente.getDetallereclamoclienteSet();
            List<String> illegalOrphanMessages = null;
            for (Detallereclamocliente detallereclamoclienteSetOldDetallereclamocliente : detallereclamoclienteSetOld) {
                if (!detallereclamoclienteSetNew.contains(detallereclamoclienteSetOldDetallereclamocliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallereclamocliente " + detallereclamoclienteSetOldDetallereclamocliente + " since its reclamocliente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getIdcliente());
                reclamocliente.setCliente(clienteNew);
            }
            if (tiporeclamoNew != null) {
                tiporeclamoNew = em.getReference(tiporeclamoNew.getClass(), tiporeclamoNew.getIdtiporeclamo());
                reclamocliente.setTiporeclamo(tiporeclamoNew);
            }
            Set<Detallereclamocliente> attachedDetallereclamoclienteSetNew = new HashSet<Detallereclamocliente>();
            for (Detallereclamocliente detallereclamoclienteSetNewDetallereclamoclienteToAttach : detallereclamoclienteSetNew) {
                detallereclamoclienteSetNewDetallereclamoclienteToAttach = em.getReference(detallereclamoclienteSetNewDetallereclamoclienteToAttach.getClass(), detallereclamoclienteSetNewDetallereclamoclienteToAttach.getDetallereclamoclientePK());
                attachedDetallereclamoclienteSetNew.add(detallereclamoclienteSetNewDetallereclamoclienteToAttach);
            }
            detallereclamoclienteSetNew = attachedDetallereclamoclienteSetNew;
            reclamocliente.setDetallereclamoclienteSet(detallereclamoclienteSetNew);
            reclamocliente = em.merge(reclamocliente);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getReclamoclienteSet().remove(reclamocliente);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getReclamoclienteSet().add(reclamocliente);
                clienteNew = em.merge(clienteNew);
            }
            if (tiporeclamoOld != null && !tiporeclamoOld.equals(tiporeclamoNew)) {
                tiporeclamoOld.getReclamoclienteSet().remove(reclamocliente);
                tiporeclamoOld = em.merge(tiporeclamoOld);
            }
            if (tiporeclamoNew != null && !tiporeclamoNew.equals(tiporeclamoOld)) {
                tiporeclamoNew.getReclamoclienteSet().add(reclamocliente);
                tiporeclamoNew = em.merge(tiporeclamoNew);
            }
            for (Detallereclamocliente detallereclamoclienteSetNewDetallereclamocliente : detallereclamoclienteSetNew) {
                if (!detallereclamoclienteSetOld.contains(detallereclamoclienteSetNewDetallereclamocliente)) {
                    Reclamocliente oldReclamoclienteOfDetallereclamoclienteSetNewDetallereclamocliente = detallereclamoclienteSetNewDetallereclamocliente.getReclamocliente();
                    detallereclamoclienteSetNewDetallereclamocliente.setReclamocliente(reclamocliente);
                    detallereclamoclienteSetNewDetallereclamocliente = em.merge(detallereclamoclienteSetNewDetallereclamocliente);
                    if (oldReclamoclienteOfDetallereclamoclienteSetNewDetallereclamocliente != null && !oldReclamoclienteOfDetallereclamoclienteSetNewDetallereclamocliente.equals(reclamocliente)) {
                        oldReclamoclienteOfDetallereclamoclienteSetNewDetallereclamocliente.getDetallereclamoclienteSet().remove(detallereclamoclienteSetNewDetallereclamocliente);
                        oldReclamoclienteOfDetallereclamoclienteSetNewDetallereclamocliente = em.merge(oldReclamoclienteOfDetallereclamoclienteSetNewDetallereclamocliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = reclamocliente.getIdreclamo();
                if (findReclamocliente(id) == null) {
                    throw new NonexistentEntityException("The reclamocliente with id " + id + " no longer exists.");
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
            Reclamocliente reclamocliente;
            try {
                reclamocliente = em.getReference(Reclamocliente.class, id);
                reclamocliente.getIdreclamo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reclamocliente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detallereclamocliente> detallereclamoclienteSetOrphanCheck = reclamocliente.getDetallereclamoclienteSet();
            for (Detallereclamocliente detallereclamoclienteSetOrphanCheckDetallereclamocliente : detallereclamoclienteSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reclamocliente (" + reclamocliente + ") cannot be destroyed since the Detallereclamocliente " + detallereclamoclienteSetOrphanCheckDetallereclamocliente + " in its detallereclamoclienteSet field has a non-nullable reclamocliente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente cliente = reclamocliente.getCliente();
            if (cliente != null) {
                cliente.getReclamoclienteSet().remove(reclamocliente);
                cliente = em.merge(cliente);
            }
            Tiporeclamo tiporeclamo = reclamocliente.getTiporeclamo();
            if (tiporeclamo != null) {
                tiporeclamo.getReclamoclienteSet().remove(reclamocliente);
                tiporeclamo = em.merge(tiporeclamo);
            }
            em.remove(reclamocliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reclamocliente> findReclamoclienteEntities() {
        return findReclamoclienteEntities(true, -1, -1);
    }

    public List<Reclamocliente> findReclamoclienteEntities(int maxResults, int firstResult) {
        return findReclamoclienteEntities(false, maxResults, firstResult);
    }

    private List<Reclamocliente> findReclamoclienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reclamocliente.class));
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

    public Reclamocliente findReclamocliente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reclamocliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getReclamoclienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reclamocliente> rt = cq.from(Reclamocliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
