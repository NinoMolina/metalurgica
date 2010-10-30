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
        if (reclamocliente.getDetallereclamoclienteSet1() == null) {
            reclamocliente.setDetallereclamoclienteSet1(new HashSet<Detallereclamocliente>());
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
            Cliente cliente1 = reclamocliente.getCliente1();
            if (cliente1 != null) {
                cliente1 = em.getReference(cliente1.getClass(), cliente1.getIdcliente());
                reclamocliente.setCliente1(cliente1);
            }
            Tiporeclamo tiporeclamo = reclamocliente.getTiporeclamo();
            if (tiporeclamo != null) {
                tiporeclamo = em.getReference(tiporeclamo.getClass(), tiporeclamo.getIdtiporeclamo());
                reclamocliente.setTiporeclamo(tiporeclamo);
            }
            Tiporeclamo tiporeclamo1 = reclamocliente.getTiporeclamo1();
            if (tiporeclamo1 != null) {
                tiporeclamo1 = em.getReference(tiporeclamo1.getClass(), tiporeclamo1.getIdtiporeclamo());
                reclamocliente.setTiporeclamo1(tiporeclamo1);
            }
            Set<Detallereclamocliente> attachedDetallereclamoclienteSet = new HashSet<Detallereclamocliente>();
            for (Detallereclamocliente detallereclamoclienteSetDetallereclamoclienteToAttach : reclamocliente.getDetallereclamoclienteSet()) {
                detallereclamoclienteSetDetallereclamoclienteToAttach = em.getReference(detallereclamoclienteSetDetallereclamoclienteToAttach.getClass(), detallereclamoclienteSetDetallereclamoclienteToAttach.getDetallereclamoclientePK());
                attachedDetallereclamoclienteSet.add(detallereclamoclienteSetDetallereclamoclienteToAttach);
            }
            reclamocliente.setDetallereclamoclienteSet(attachedDetallereclamoclienteSet);
            Set<Detallereclamocliente> attachedDetallereclamoclienteSet1 = new HashSet<Detallereclamocliente>();
            for (Detallereclamocliente detallereclamoclienteSet1DetallereclamoclienteToAttach : reclamocliente.getDetallereclamoclienteSet1()) {
                detallereclamoclienteSet1DetallereclamoclienteToAttach = em.getReference(detallereclamoclienteSet1DetallereclamoclienteToAttach.getClass(), detallereclamoclienteSet1DetallereclamoclienteToAttach.getDetallereclamoclientePK());
                attachedDetallereclamoclienteSet1.add(detallereclamoclienteSet1DetallereclamoclienteToAttach);
            }
            reclamocliente.setDetallereclamoclienteSet1(attachedDetallereclamoclienteSet1);
            em.persist(reclamocliente);
            if (cliente != null) {
                cliente.getReclamoclienteSet().add(reclamocliente);
                cliente = em.merge(cliente);
            }
            if (cliente1 != null) {
                cliente1.getReclamoclienteSet().add(reclamocliente);
                cliente1 = em.merge(cliente1);
            }
            if (tiporeclamo != null) {
                tiporeclamo.getReclamoclienteSet().add(reclamocliente);
                tiporeclamo = em.merge(tiporeclamo);
            }
            if (tiporeclamo1 != null) {
                tiporeclamo1.getReclamoclienteSet().add(reclamocliente);
                tiporeclamo1 = em.merge(tiporeclamo1);
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
            for (Detallereclamocliente detallereclamoclienteSet1Detallereclamocliente : reclamocliente.getDetallereclamoclienteSet1()) {
                Reclamocliente oldReclamocliente1OfDetallereclamoclienteSet1Detallereclamocliente = detallereclamoclienteSet1Detallereclamocliente.getReclamocliente1();
                detallereclamoclienteSet1Detallereclamocliente.setReclamocliente1(reclamocliente);
                detallereclamoclienteSet1Detallereclamocliente = em.merge(detallereclamoclienteSet1Detallereclamocliente);
                if (oldReclamocliente1OfDetallereclamoclienteSet1Detallereclamocliente != null) {
                    oldReclamocliente1OfDetallereclamoclienteSet1Detallereclamocliente.getDetallereclamoclienteSet1().remove(detallereclamoclienteSet1Detallereclamocliente);
                    oldReclamocliente1OfDetallereclamoclienteSet1Detallereclamocliente = em.merge(oldReclamocliente1OfDetallereclamoclienteSet1Detallereclamocliente);
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
            Cliente cliente1Old = persistentReclamocliente.getCliente1();
            Cliente cliente1New = reclamocliente.getCliente1();
            Tiporeclamo tiporeclamoOld = persistentReclamocliente.getTiporeclamo();
            Tiporeclamo tiporeclamoNew = reclamocliente.getTiporeclamo();
            Tiporeclamo tiporeclamo1Old = persistentReclamocliente.getTiporeclamo1();
            Tiporeclamo tiporeclamo1New = reclamocliente.getTiporeclamo1();
            Set<Detallereclamocliente> detallereclamoclienteSetOld = persistentReclamocliente.getDetallereclamoclienteSet();
            Set<Detallereclamocliente> detallereclamoclienteSetNew = reclamocliente.getDetallereclamoclienteSet();
            Set<Detallereclamocliente> detallereclamoclienteSet1Old = persistentReclamocliente.getDetallereclamoclienteSet1();
            Set<Detallereclamocliente> detallereclamoclienteSet1New = reclamocliente.getDetallereclamoclienteSet1();
            List<String> illegalOrphanMessages = null;
            for (Detallereclamocliente detallereclamoclienteSetOldDetallereclamocliente : detallereclamoclienteSetOld) {
                if (!detallereclamoclienteSetNew.contains(detallereclamoclienteSetOldDetallereclamocliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallereclamocliente " + detallereclamoclienteSetOldDetallereclamocliente + " since its reclamocliente field is not nullable.");
                }
            }
            for (Detallereclamocliente detallereclamoclienteSet1OldDetallereclamocliente : detallereclamoclienteSet1Old) {
                if (!detallereclamoclienteSet1New.contains(detallereclamoclienteSet1OldDetallereclamocliente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallereclamocliente " + detallereclamoclienteSet1OldDetallereclamocliente + " since its reclamocliente1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getIdcliente());
                reclamocliente.setCliente(clienteNew);
            }
            if (cliente1New != null) {
                cliente1New = em.getReference(cliente1New.getClass(), cliente1New.getIdcliente());
                reclamocliente.setCliente1(cliente1New);
            }
            if (tiporeclamoNew != null) {
                tiporeclamoNew = em.getReference(tiporeclamoNew.getClass(), tiporeclamoNew.getIdtiporeclamo());
                reclamocliente.setTiporeclamo(tiporeclamoNew);
            }
            if (tiporeclamo1New != null) {
                tiporeclamo1New = em.getReference(tiporeclamo1New.getClass(), tiporeclamo1New.getIdtiporeclamo());
                reclamocliente.setTiporeclamo1(tiporeclamo1New);
            }
            Set<Detallereclamocliente> attachedDetallereclamoclienteSetNew = new HashSet<Detallereclamocliente>();
            for (Detallereclamocliente detallereclamoclienteSetNewDetallereclamoclienteToAttach : detallereclamoclienteSetNew) {
                detallereclamoclienteSetNewDetallereclamoclienteToAttach = em.getReference(detallereclamoclienteSetNewDetallereclamoclienteToAttach.getClass(), detallereclamoclienteSetNewDetallereclamoclienteToAttach.getDetallereclamoclientePK());
                attachedDetallereclamoclienteSetNew.add(detallereclamoclienteSetNewDetallereclamoclienteToAttach);
            }
            detallereclamoclienteSetNew = attachedDetallereclamoclienteSetNew;
            reclamocliente.setDetallereclamoclienteSet(detallereclamoclienteSetNew);
            Set<Detallereclamocliente> attachedDetallereclamoclienteSet1New = new HashSet<Detallereclamocliente>();
            for (Detallereclamocliente detallereclamoclienteSet1NewDetallereclamoclienteToAttach : detallereclamoclienteSet1New) {
                detallereclamoclienteSet1NewDetallereclamoclienteToAttach = em.getReference(detallereclamoclienteSet1NewDetallereclamoclienteToAttach.getClass(), detallereclamoclienteSet1NewDetallereclamoclienteToAttach.getDetallereclamoclientePK());
                attachedDetallereclamoclienteSet1New.add(detallereclamoclienteSet1NewDetallereclamoclienteToAttach);
            }
            detallereclamoclienteSet1New = attachedDetallereclamoclienteSet1New;
            reclamocliente.setDetallereclamoclienteSet1(detallereclamoclienteSet1New);
            reclamocliente = em.merge(reclamocliente);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getReclamoclienteSet().remove(reclamocliente);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getReclamoclienteSet().add(reclamocliente);
                clienteNew = em.merge(clienteNew);
            }
            if (cliente1Old != null && !cliente1Old.equals(cliente1New)) {
                cliente1Old.getReclamoclienteSet().remove(reclamocliente);
                cliente1Old = em.merge(cliente1Old);
            }
            if (cliente1New != null && !cliente1New.equals(cliente1Old)) {
                cliente1New.getReclamoclienteSet().add(reclamocliente);
                cliente1New = em.merge(cliente1New);
            }
            if (tiporeclamoOld != null && !tiporeclamoOld.equals(tiporeclamoNew)) {
                tiporeclamoOld.getReclamoclienteSet().remove(reclamocliente);
                tiporeclamoOld = em.merge(tiporeclamoOld);
            }
            if (tiporeclamoNew != null && !tiporeclamoNew.equals(tiporeclamoOld)) {
                tiporeclamoNew.getReclamoclienteSet().add(reclamocliente);
                tiporeclamoNew = em.merge(tiporeclamoNew);
            }
            if (tiporeclamo1Old != null && !tiporeclamo1Old.equals(tiporeclamo1New)) {
                tiporeclamo1Old.getReclamoclienteSet().remove(reclamocliente);
                tiporeclamo1Old = em.merge(tiporeclamo1Old);
            }
            if (tiporeclamo1New != null && !tiporeclamo1New.equals(tiporeclamo1Old)) {
                tiporeclamo1New.getReclamoclienteSet().add(reclamocliente);
                tiporeclamo1New = em.merge(tiporeclamo1New);
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
            for (Detallereclamocliente detallereclamoclienteSet1NewDetallereclamocliente : detallereclamoclienteSet1New) {
                if (!detallereclamoclienteSet1Old.contains(detallereclamoclienteSet1NewDetallereclamocliente)) {
                    Reclamocliente oldReclamocliente1OfDetallereclamoclienteSet1NewDetallereclamocliente = detallereclamoclienteSet1NewDetallereclamocliente.getReclamocliente1();
                    detallereclamoclienteSet1NewDetallereclamocliente.setReclamocliente1(reclamocliente);
                    detallereclamoclienteSet1NewDetallereclamocliente = em.merge(detallereclamoclienteSet1NewDetallereclamocliente);
                    if (oldReclamocliente1OfDetallereclamoclienteSet1NewDetallereclamocliente != null && !oldReclamocliente1OfDetallereclamoclienteSet1NewDetallereclamocliente.equals(reclamocliente)) {
                        oldReclamocliente1OfDetallereclamoclienteSet1NewDetallereclamocliente.getDetallereclamoclienteSet1().remove(detallereclamoclienteSet1NewDetallereclamocliente);
                        oldReclamocliente1OfDetallereclamoclienteSet1NewDetallereclamocliente = em.merge(oldReclamocliente1OfDetallereclamoclienteSet1NewDetallereclamocliente);
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
            Set<Detallereclamocliente> detallereclamoclienteSet1OrphanCheck = reclamocliente.getDetallereclamoclienteSet1();
            for (Detallereclamocliente detallereclamoclienteSet1OrphanCheckDetallereclamocliente : detallereclamoclienteSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Reclamocliente (" + reclamocliente + ") cannot be destroyed since the Detallereclamocliente " + detallereclamoclienteSet1OrphanCheckDetallereclamocliente + " in its detallereclamoclienteSet1 field has a non-nullable reclamocliente1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente cliente = reclamocliente.getCliente();
            if (cliente != null) {
                cliente.getReclamoclienteSet().remove(reclamocliente);
                cliente = em.merge(cliente);
            }
            Cliente cliente1 = reclamocliente.getCliente1();
            if (cliente1 != null) {
                cliente1.getReclamoclienteSet().remove(reclamocliente);
                cliente1 = em.merge(cliente1);
            }
            Tiporeclamo tiporeclamo = reclamocliente.getTiporeclamo();
            if (tiporeclamo != null) {
                tiporeclamo.getReclamoclienteSet().remove(reclamocliente);
                tiporeclamo = em.merge(tiporeclamo);
            }
            Tiporeclamo tiporeclamo1 = reclamocliente.getTiporeclamo1();
            if (tiporeclamo1 != null) {
                tiporeclamo1.getReclamoclienteSet().remove(reclamocliente);
                tiporeclamo1 = em.merge(tiporeclamo1);
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
