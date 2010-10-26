/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Proveedor;
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
import entity.Responsable;
import entity.Compra;
import java.util.HashSet;
import java.util.Set;
import entity.Proveedorxmateriaprima;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class ProveedorJpaController {

    public ProveedorJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedor proveedor) throws PreexistingEntityException, Exception {
        if (proveedor.getCompraSet() == null) {
            proveedor.setCompraSet(new HashSet<Compra>());
        }
        if (proveedor.getProveedorxmateriaprimaSet() == null) {
            proveedor.setProveedorxmateriaprimaSet(new HashSet<Proveedorxmateriaprima>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Condicioniva condicion = proveedor.getCondicion();
            if (condicion != null) {
                condicion = em.getReference(condicion.getClass(), condicion.getIdcondicioniva());
                proveedor.setCondicion(condicion);
            }
            Domicilio domicilio = proveedor.getDomicilio();
            if (domicilio != null) {
                domicilio = em.getReference(domicilio.getClass(), domicilio.getIddomicilio());
                proveedor.setDomicilio(domicilio);
            }
            Responsable responsable = proveedor.getResponsable();
            if (responsable != null) {
                responsable = em.getReference(responsable.getClass(), responsable.getIdresponsable());
                proveedor.setResponsable(responsable);
            }
            Set<Compra> attachedCompraSet = new HashSet<Compra>();
            for (Compra compraSetCompraToAttach : proveedor.getCompraSet()) {
                compraSetCompraToAttach = em.getReference(compraSetCompraToAttach.getClass(), compraSetCompraToAttach.getIdcompra());
                attachedCompraSet.add(compraSetCompraToAttach);
            }
            proveedor.setCompraSet(attachedCompraSet);
            Set<Proveedorxmateriaprima> attachedProveedorxmateriaprimaSet = new HashSet<Proveedorxmateriaprima>();
            for (Proveedorxmateriaprima proveedorxmateriaprimaSetProveedorxmateriaprimaToAttach : proveedor.getProveedorxmateriaprimaSet()) {
                proveedorxmateriaprimaSetProveedorxmateriaprimaToAttach = em.getReference(proveedorxmateriaprimaSetProveedorxmateriaprimaToAttach.getClass(), proveedorxmateriaprimaSetProveedorxmateriaprimaToAttach.getProveedorxmateriaprimaPK());
                attachedProveedorxmateriaprimaSet.add(proveedorxmateriaprimaSetProveedorxmateriaprimaToAttach);
            }
            proveedor.setProveedorxmateriaprimaSet(attachedProveedorxmateriaprimaSet);
            em.persist(proveedor);
            if (condicion != null) {
                condicion.getProveedorSet().add(proveedor);
                condicion = em.merge(condicion);
            }
            if (domicilio != null) {
                domicilio.getProveedorSet().add(proveedor);
                domicilio = em.merge(domicilio);
            }
            if (responsable != null) {
                responsable.getProveedorSet().add(proveedor);
                responsable = em.merge(responsable);
            }
            for (Compra compraSetCompra : proveedor.getCompraSet()) {
                Proveedor oldProveedorOfCompraSetCompra = compraSetCompra.getProveedor();
                compraSetCompra.setProveedor(proveedor);
                compraSetCompra = em.merge(compraSetCompra);
                if (oldProveedorOfCompraSetCompra != null) {
                    oldProveedorOfCompraSetCompra.getCompraSet().remove(compraSetCompra);
                    oldProveedorOfCompraSetCompra = em.merge(oldProveedorOfCompraSetCompra);
                }
            }
            for (Proveedorxmateriaprima proveedorxmateriaprimaSetProveedorxmateriaprima : proveedor.getProveedorxmateriaprimaSet()) {
                Proveedor oldProveedorOfProveedorxmateriaprimaSetProveedorxmateriaprima = proveedorxmateriaprimaSetProveedorxmateriaprima.getProveedor();
                proveedorxmateriaprimaSetProveedorxmateriaprima.setProveedor(proveedor);
                proveedorxmateriaprimaSetProveedorxmateriaprima = em.merge(proveedorxmateriaprimaSetProveedorxmateriaprima);
                if (oldProveedorOfProveedorxmateriaprimaSetProveedorxmateriaprima != null) {
                    oldProveedorOfProveedorxmateriaprimaSetProveedorxmateriaprima.getProveedorxmateriaprimaSet().remove(proveedorxmateriaprimaSetProveedorxmateriaprima);
                    oldProveedorOfProveedorxmateriaprimaSetProveedorxmateriaprima = em.merge(oldProveedorOfProveedorxmateriaprimaSetProveedorxmateriaprima);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProveedor(proveedor.getIdproveedor()) != null) {
                throw new PreexistingEntityException("Proveedor " + proveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedor proveedor) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor persistentProveedor = em.find(Proveedor.class, proveedor.getIdproveedor());
            Condicioniva condicionOld = persistentProveedor.getCondicion();
            Condicioniva condicionNew = proveedor.getCondicion();
            Domicilio domicilioOld = persistentProveedor.getDomicilio();
            Domicilio domicilioNew = proveedor.getDomicilio();
            Responsable responsableOld = persistentProveedor.getResponsable();
            Responsable responsableNew = proveedor.getResponsable();
            Set<Compra> compraSetOld = persistentProveedor.getCompraSet();
            Set<Compra> compraSetNew = proveedor.getCompraSet();
            Set<Proveedorxmateriaprima> proveedorxmateriaprimaSetOld = persistentProveedor.getProveedorxmateriaprimaSet();
            Set<Proveedorxmateriaprima> proveedorxmateriaprimaSetNew = proveedor.getProveedorxmateriaprimaSet();
            List<String> illegalOrphanMessages = null;
            for (Proveedorxmateriaprima proveedorxmateriaprimaSetOldProveedorxmateriaprima : proveedorxmateriaprimaSetOld) {
                if (!proveedorxmateriaprimaSetNew.contains(proveedorxmateriaprimaSetOldProveedorxmateriaprima)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Proveedorxmateriaprima " + proveedorxmateriaprimaSetOldProveedorxmateriaprima + " since its proveedor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (condicionNew != null) {
                condicionNew = em.getReference(condicionNew.getClass(), condicionNew.getIdcondicioniva());
                proveedor.setCondicion(condicionNew);
            }
            if (domicilioNew != null) {
                domicilioNew = em.getReference(domicilioNew.getClass(), domicilioNew.getIddomicilio());
                proveedor.setDomicilio(domicilioNew);
            }
            if (responsableNew != null) {
                responsableNew = em.getReference(responsableNew.getClass(), responsableNew.getIdresponsable());
                proveedor.setResponsable(responsableNew);
            }
            Set<Compra> attachedCompraSetNew = new HashSet<Compra>();
            for (Compra compraSetNewCompraToAttach : compraSetNew) {
                compraSetNewCompraToAttach = em.getReference(compraSetNewCompraToAttach.getClass(), compraSetNewCompraToAttach.getIdcompra());
                attachedCompraSetNew.add(compraSetNewCompraToAttach);
            }
            compraSetNew = attachedCompraSetNew;
            proveedor.setCompraSet(compraSetNew);
            Set<Proveedorxmateriaprima> attachedProveedorxmateriaprimaSetNew = new HashSet<Proveedorxmateriaprima>();
            for (Proveedorxmateriaprima proveedorxmateriaprimaSetNewProveedorxmateriaprimaToAttach : proveedorxmateriaprimaSetNew) {
                proveedorxmateriaprimaSetNewProveedorxmateriaprimaToAttach = em.getReference(proveedorxmateriaprimaSetNewProveedorxmateriaprimaToAttach.getClass(), proveedorxmateriaprimaSetNewProveedorxmateriaprimaToAttach.getProveedorxmateriaprimaPK());
                attachedProveedorxmateriaprimaSetNew.add(proveedorxmateriaprimaSetNewProveedorxmateriaprimaToAttach);
            }
            proveedorxmateriaprimaSetNew = attachedProveedorxmateriaprimaSetNew;
            proveedor.setProveedorxmateriaprimaSet(proveedorxmateriaprimaSetNew);
            proveedor = em.merge(proveedor);
            if (condicionOld != null && !condicionOld.equals(condicionNew)) {
                condicionOld.getProveedorSet().remove(proveedor);
                condicionOld = em.merge(condicionOld);
            }
            if (condicionNew != null && !condicionNew.equals(condicionOld)) {
                condicionNew.getProveedorSet().add(proveedor);
                condicionNew = em.merge(condicionNew);
            }
            if (domicilioOld != null && !domicilioOld.equals(domicilioNew)) {
                domicilioOld.getProveedorSet().remove(proveedor);
                domicilioOld = em.merge(domicilioOld);
            }
            if (domicilioNew != null && !domicilioNew.equals(domicilioOld)) {
                domicilioNew.getProveedorSet().add(proveedor);
                domicilioNew = em.merge(domicilioNew);
            }
            if (responsableOld != null && !responsableOld.equals(responsableNew)) {
                responsableOld.getProveedorSet().remove(proveedor);
                responsableOld = em.merge(responsableOld);
            }
            if (responsableNew != null && !responsableNew.equals(responsableOld)) {
                responsableNew.getProveedorSet().add(proveedor);
                responsableNew = em.merge(responsableNew);
            }
            for (Compra compraSetOldCompra : compraSetOld) {
                if (!compraSetNew.contains(compraSetOldCompra)) {
                    compraSetOldCompra.setProveedor(null);
                    compraSetOldCompra = em.merge(compraSetOldCompra);
                }
            }
            for (Compra compraSetNewCompra : compraSetNew) {
                if (!compraSetOld.contains(compraSetNewCompra)) {
                    Proveedor oldProveedorOfCompraSetNewCompra = compraSetNewCompra.getProveedor();
                    compraSetNewCompra.setProveedor(proveedor);
                    compraSetNewCompra = em.merge(compraSetNewCompra);
                    if (oldProveedorOfCompraSetNewCompra != null && !oldProveedorOfCompraSetNewCompra.equals(proveedor)) {
                        oldProveedorOfCompraSetNewCompra.getCompraSet().remove(compraSetNewCompra);
                        oldProveedorOfCompraSetNewCompra = em.merge(oldProveedorOfCompraSetNewCompra);
                    }
                }
            }
            for (Proveedorxmateriaprima proveedorxmateriaprimaSetNewProveedorxmateriaprima : proveedorxmateriaprimaSetNew) {
                if (!proveedorxmateriaprimaSetOld.contains(proveedorxmateriaprimaSetNewProveedorxmateriaprima)) {
                    Proveedor oldProveedorOfProveedorxmateriaprimaSetNewProveedorxmateriaprima = proveedorxmateriaprimaSetNewProveedorxmateriaprima.getProveedor();
                    proveedorxmateriaprimaSetNewProveedorxmateriaprima.setProveedor(proveedor);
                    proveedorxmateriaprimaSetNewProveedorxmateriaprima = em.merge(proveedorxmateriaprimaSetNewProveedorxmateriaprima);
                    if (oldProveedorOfProveedorxmateriaprimaSetNewProveedorxmateriaprima != null && !oldProveedorOfProveedorxmateriaprimaSetNewProveedorxmateriaprima.equals(proveedor)) {
                        oldProveedorOfProveedorxmateriaprimaSetNewProveedorxmateriaprima.getProveedorxmateriaprimaSet().remove(proveedorxmateriaprimaSetNewProveedorxmateriaprima);
                        oldProveedorOfProveedorxmateriaprimaSetNewProveedorxmateriaprima = em.merge(oldProveedorOfProveedorxmateriaprimaSetNewProveedorxmateriaprima);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = proveedor.getIdproveedor();
                if (findProveedor(id) == null) {
                    throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.");
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
            Proveedor proveedor;
            try {
                proveedor = em.getReference(Proveedor.class, id);
                proveedor.getIdproveedor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Proveedorxmateriaprima> proveedorxmateriaprimaSetOrphanCheck = proveedor.getProveedorxmateriaprimaSet();
            for (Proveedorxmateriaprima proveedorxmateriaprimaSetOrphanCheckProveedorxmateriaprima : proveedorxmateriaprimaSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedor (" + proveedor + ") cannot be destroyed since the Proveedorxmateriaprima " + proveedorxmateriaprimaSetOrphanCheckProveedorxmateriaprima + " in its proveedorxmateriaprimaSet field has a non-nullable proveedor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Condicioniva condicion = proveedor.getCondicion();
            if (condicion != null) {
                condicion.getProveedorSet().remove(proveedor);
                condicion = em.merge(condicion);
            }
            Domicilio domicilio = proveedor.getDomicilio();
            if (domicilio != null) {
                domicilio.getProveedorSet().remove(proveedor);
                domicilio = em.merge(domicilio);
            }
            Responsable responsable = proveedor.getResponsable();
            if (responsable != null) {
                responsable.getProveedorSet().remove(proveedor);
                responsable = em.merge(responsable);
            }
            Set<Compra> compraSet = proveedor.getCompraSet();
            for (Compra compraSetCompra : compraSet) {
                compraSetCompra.setProveedor(null);
                compraSetCompra = em.merge(compraSetCompra);
            }
            em.remove(proveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedor> findProveedorEntities() {
        return findProveedorEntities(true, -1, -1);
    }

    public List<Proveedor> findProveedorEntities(int maxResults, int firstResult) {
        return findProveedorEntities(false, maxResults, firstResult);
    }

    private List<Proveedor> findProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedor.class));
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

    public Proveedor findProveedor(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedor> rt = cq.from(Proveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
