/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Compra;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Estadocompra;
import entity.Proveedor;
import entity.Detallecompra;
import java.util.HashSet;
import java.util.Set;
import entity.Reclamoproveedor;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class CompraJpaController {

    public CompraJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Compra compra) throws PreexistingEntityException, Exception {
        if (compra.getDetallecompraSet() == null) {
            compra.setDetallecompraSet(new HashSet<Detallecompra>());
        }
        if (compra.getReclamoproveedorSet() == null) {
            compra.setReclamoproveedorSet(new HashSet<Reclamoproveedor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadocompra estado = compra.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                compra.setEstado(estado);
            }
            Proveedor proveedor = compra.getProveedor();
            if (proveedor != null) {
                proveedor = em.getReference(proveedor.getClass(), proveedor.getIdproveedor());
                compra.setProveedor(proveedor);
            }
            Set<Detallecompra> attachedDetallecompraSet = new HashSet<Detallecompra>();
            for (Detallecompra detallecompraSetDetallecompraToAttach : compra.getDetallecompraSet()) {
                detallecompraSetDetallecompraToAttach = em.getReference(detallecompraSetDetallecompraToAttach.getClass(), detallecompraSetDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraSet.add(detallecompraSetDetallecompraToAttach);
            }
            compra.setDetallecompraSet(attachedDetallecompraSet);
            Set<Reclamoproveedor> attachedReclamoproveedorSet = new HashSet<Reclamoproveedor>();
            for (Reclamoproveedor reclamoproveedorSetReclamoproveedorToAttach : compra.getReclamoproveedorSet()) {
                reclamoproveedorSetReclamoproveedorToAttach = em.getReference(reclamoproveedorSetReclamoproveedorToAttach.getClass(), reclamoproveedorSetReclamoproveedorToAttach.getIdreclamo());
                attachedReclamoproveedorSet.add(reclamoproveedorSetReclamoproveedorToAttach);
            }
            compra.setReclamoproveedorSet(attachedReclamoproveedorSet);
            em.persist(compra);
            if (estado != null) {
                estado.getCompraSet().add(compra);
                estado = em.merge(estado);
            }
            if (proveedor != null) {
                proveedor.getCompraSet().add(compra);
                proveedor = em.merge(proveedor);
            }
            for (Detallecompra detallecompraSetDetallecompra : compra.getDetallecompraSet()) {
                Compra oldCompraOfDetallecompraSetDetallecompra = detallecompraSetDetallecompra.getCompra();
                detallecompraSetDetallecompra.setCompra(compra);
                detallecompraSetDetallecompra = em.merge(detallecompraSetDetallecompra);
                if (oldCompraOfDetallecompraSetDetallecompra != null) {
                    oldCompraOfDetallecompraSetDetallecompra.getDetallecompraSet().remove(detallecompraSetDetallecompra);
                    oldCompraOfDetallecompraSetDetallecompra = em.merge(oldCompraOfDetallecompraSetDetallecompra);
                }
            }
            for (Reclamoproveedor reclamoproveedorSetReclamoproveedor : compra.getReclamoproveedorSet()) {
                Compra oldCompraOfReclamoproveedorSetReclamoproveedor = reclamoproveedorSetReclamoproveedor.getCompra();
                reclamoproveedorSetReclamoproveedor.setCompra(compra);
                reclamoproveedorSetReclamoproveedor = em.merge(reclamoproveedorSetReclamoproveedor);
                if (oldCompraOfReclamoproveedorSetReclamoproveedor != null) {
                    oldCompraOfReclamoproveedorSetReclamoproveedor.getReclamoproveedorSet().remove(reclamoproveedorSetReclamoproveedor);
                    oldCompraOfReclamoproveedorSetReclamoproveedor = em.merge(oldCompraOfReclamoproveedorSetReclamoproveedor);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCompra(compra.getIdcompra()) != null) {
                throw new PreexistingEntityException("Compra " + compra + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Compra compra) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Compra persistentCompra = em.find(Compra.class, compra.getIdcompra());
            Estadocompra estadoOld = persistentCompra.getEstado();
            Estadocompra estadoNew = compra.getEstado();
            Proveedor proveedorOld = persistentCompra.getProveedor();
            Proveedor proveedorNew = compra.getProveedor();
            Set<Detallecompra> detallecompraSetOld = persistentCompra.getDetallecompraSet();
            Set<Detallecompra> detallecompraSetNew = compra.getDetallecompraSet();
            Set<Reclamoproveedor> reclamoproveedorSetOld = persistentCompra.getReclamoproveedorSet();
            Set<Reclamoproveedor> reclamoproveedorSetNew = compra.getReclamoproveedorSet();
            List<String> illegalOrphanMessages = null;
            for (Detallecompra detallecompraSetOldDetallecompra : detallecompraSetOld) {
                if (!detallecompraSetNew.contains(detallecompraSetOldDetallecompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallecompra " + detallecompraSetOldDetallecompra + " since its compra field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                compra.setEstado(estadoNew);
            }
            if (proveedorNew != null) {
                proveedorNew = em.getReference(proveedorNew.getClass(), proveedorNew.getIdproveedor());
                compra.setProveedor(proveedorNew);
            }
            Set<Detallecompra> attachedDetallecompraSetNew = new HashSet<Detallecompra>();
            for (Detallecompra detallecompraSetNewDetallecompraToAttach : detallecompraSetNew) {
                detallecompraSetNewDetallecompraToAttach = em.getReference(detallecompraSetNewDetallecompraToAttach.getClass(), detallecompraSetNewDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraSetNew.add(detallecompraSetNewDetallecompraToAttach);
            }
            detallecompraSetNew = attachedDetallecompraSetNew;
            compra.setDetallecompraSet(detallecompraSetNew);
            Set<Reclamoproveedor> attachedReclamoproveedorSetNew = new HashSet<Reclamoproveedor>();
            for (Reclamoproveedor reclamoproveedorSetNewReclamoproveedorToAttach : reclamoproveedorSetNew) {
                reclamoproveedorSetNewReclamoproveedorToAttach = em.getReference(reclamoproveedorSetNewReclamoproveedorToAttach.getClass(), reclamoproveedorSetNewReclamoproveedorToAttach.getIdreclamo());
                attachedReclamoproveedorSetNew.add(reclamoproveedorSetNewReclamoproveedorToAttach);
            }
            reclamoproveedorSetNew = attachedReclamoproveedorSetNew;
            compra.setReclamoproveedorSet(reclamoproveedorSetNew);
            compra = em.merge(compra);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getCompraSet().remove(compra);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getCompraSet().add(compra);
                estadoNew = em.merge(estadoNew);
            }
            if (proveedorOld != null && !proveedorOld.equals(proveedorNew)) {
                proveedorOld.getCompraSet().remove(compra);
                proveedorOld = em.merge(proveedorOld);
            }
            if (proveedorNew != null && !proveedorNew.equals(proveedorOld)) {
                proveedorNew.getCompraSet().add(compra);
                proveedorNew = em.merge(proveedorNew);
            }
            for (Detallecompra detallecompraSetNewDetallecompra : detallecompraSetNew) {
                if (!detallecompraSetOld.contains(detallecompraSetNewDetallecompra)) {
                    Compra oldCompraOfDetallecompraSetNewDetallecompra = detallecompraSetNewDetallecompra.getCompra();
                    detallecompraSetNewDetallecompra.setCompra(compra);
                    detallecompraSetNewDetallecompra = em.merge(detallecompraSetNewDetallecompra);
                    if (oldCompraOfDetallecompraSetNewDetallecompra != null && !oldCompraOfDetallecompraSetNewDetallecompra.equals(compra)) {
                        oldCompraOfDetallecompraSetNewDetallecompra.getDetallecompraSet().remove(detallecompraSetNewDetallecompra);
                        oldCompraOfDetallecompraSetNewDetallecompra = em.merge(oldCompraOfDetallecompraSetNewDetallecompra);
                    }
                }
            }
            for (Reclamoproveedor reclamoproveedorSetOldReclamoproveedor : reclamoproveedorSetOld) {
                if (!reclamoproveedorSetNew.contains(reclamoproveedorSetOldReclamoproveedor)) {
                    reclamoproveedorSetOldReclamoproveedor.setCompra(null);
                    reclamoproveedorSetOldReclamoproveedor = em.merge(reclamoproveedorSetOldReclamoproveedor);
                }
            }
            for (Reclamoproveedor reclamoproveedorSetNewReclamoproveedor : reclamoproveedorSetNew) {
                if (!reclamoproveedorSetOld.contains(reclamoproveedorSetNewReclamoproveedor)) {
                    Compra oldCompraOfReclamoproveedorSetNewReclamoproveedor = reclamoproveedorSetNewReclamoproveedor.getCompra();
                    reclamoproveedorSetNewReclamoproveedor.setCompra(compra);
                    reclamoproveedorSetNewReclamoproveedor = em.merge(reclamoproveedorSetNewReclamoproveedor);
                    if (oldCompraOfReclamoproveedorSetNewReclamoproveedor != null && !oldCompraOfReclamoproveedorSetNewReclamoproveedor.equals(compra)) {
                        oldCompraOfReclamoproveedorSetNewReclamoproveedor.getReclamoproveedorSet().remove(reclamoproveedorSetNewReclamoproveedor);
                        oldCompraOfReclamoproveedorSetNewReclamoproveedor = em.merge(oldCompraOfReclamoproveedorSetNewReclamoproveedor);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = compra.getIdcompra();
                if (findCompra(id) == null) {
                    throw new NonexistentEntityException("The compra with id " + id + " no longer exists.");
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
            Compra compra;
            try {
                compra = em.getReference(Compra.class, id);
                compra.getIdcompra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compra with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detallecompra> detallecompraSetOrphanCheck = compra.getDetallecompraSet();
            for (Detallecompra detallecompraSetOrphanCheckDetallecompra : detallecompraSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Compra (" + compra + ") cannot be destroyed since the Detallecompra " + detallecompraSetOrphanCheckDetallecompra + " in its detallecompraSet field has a non-nullable compra field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Estadocompra estado = compra.getEstado();
            if (estado != null) {
                estado.getCompraSet().remove(compra);
                estado = em.merge(estado);
            }
            Proveedor proveedor = compra.getProveedor();
            if (proveedor != null) {
                proveedor.getCompraSet().remove(compra);
                proveedor = em.merge(proveedor);
            }
            Set<Reclamoproveedor> reclamoproveedorSet = compra.getReclamoproveedorSet();
            for (Reclamoproveedor reclamoproveedorSetReclamoproveedor : reclamoproveedorSet) {
                reclamoproveedorSetReclamoproveedor.setCompra(null);
                reclamoproveedorSetReclamoproveedor = em.merge(reclamoproveedorSetReclamoproveedor);
            }
            em.remove(compra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Compra> findCompraEntities() {
        return findCompraEntities(true, -1, -1);
    }

    public List<Compra> findCompraEntities(int maxResults, int firstResult) {
        return findCompraEntities(false, maxResults, firstResult);
    }

    private List<Compra> findCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Compra.class));
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

    public Compra findCompra(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Compra.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Compra> rt = cq.from(Compra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
