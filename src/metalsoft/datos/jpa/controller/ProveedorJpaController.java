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
import metalsoft.datos.jpa.entity.Proveedor;
import metalsoft.datos.jpa.entity.Responsable;
import metalsoft.datos.jpa.entity.Domicilio;
import metalsoft.datos.jpa.entity.Condicioniva;
import metalsoft.datos.jpa.entity.Compra;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Proveedorxmateriaprima;

/**
 *
 * @author Nino
 */
public class ProveedorJpaController implements Serializable {

    public ProveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedor proveedor) throws PreexistingEntityException, Exception {
        if (proveedor.getCompraList() == null) {
            proveedor.setCompraList(new ArrayList<Compra>());
        }
        if (proveedor.getProveedorxmateriaprimaList() == null) {
            proveedor.setProveedorxmateriaprimaList(new ArrayList<Proveedorxmateriaprima>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Responsable responsable = proveedor.getResponsable();
            if (responsable != null) {
                responsable = em.getReference(responsable.getClass(), responsable.getIdresponsable());
                proveedor.setResponsable(responsable);
            }
            Domicilio domicilio = proveedor.getDomicilio();
            if (domicilio != null) {
                domicilio = em.getReference(domicilio.getClass(), domicilio.getIddomicilio());
                proveedor.setDomicilio(domicilio);
            }
            Condicioniva condicion = proveedor.getCondicion();
            if (condicion != null) {
                condicion = em.getReference(condicion.getClass(), condicion.getIdcondicioniva());
                proveedor.setCondicion(condicion);
            }
            List<Compra> attachedCompraList = new ArrayList<Compra>();
            for (Compra compraListCompraToAttach : proveedor.getCompraList()) {
                compraListCompraToAttach = em.getReference(compraListCompraToAttach.getClass(), compraListCompraToAttach.getIdcompra());
                attachedCompraList.add(compraListCompraToAttach);
            }
            proveedor.setCompraList(attachedCompraList);
            List<Proveedorxmateriaprima> attachedProveedorxmateriaprimaList = new ArrayList<Proveedorxmateriaprima>();
            for (Proveedorxmateriaprima proveedorxmateriaprimaListProveedorxmateriaprimaToAttach : proveedor.getProveedorxmateriaprimaList()) {
                proveedorxmateriaprimaListProveedorxmateriaprimaToAttach = em.getReference(proveedorxmateriaprimaListProveedorxmateriaprimaToAttach.getClass(), proveedorxmateriaprimaListProveedorxmateriaprimaToAttach.getProveedorxmateriaprimaPK());
                attachedProveedorxmateriaprimaList.add(proveedorxmateriaprimaListProveedorxmateriaprimaToAttach);
            }
            proveedor.setProveedorxmateriaprimaList(attachedProveedorxmateriaprimaList);
            em.persist(proveedor);
            if (responsable != null) {
                responsable.getProveedorList().add(proveedor);
                responsable = em.merge(responsable);
            }
            if (domicilio != null) {
                domicilio.getProveedorList().add(proveedor);
                domicilio = em.merge(domicilio);
            }
            if (condicion != null) {
                condicion.getProveedorList().add(proveedor);
                condicion = em.merge(condicion);
            }
            for (Compra compraListCompra : proveedor.getCompraList()) {
                Proveedor oldProveedorOfCompraListCompra = compraListCompra.getProveedor();
                compraListCompra.setProveedor(proveedor);
                compraListCompra = em.merge(compraListCompra);
                if (oldProveedorOfCompraListCompra != null) {
                    oldProveedorOfCompraListCompra.getCompraList().remove(compraListCompra);
                    oldProveedorOfCompraListCompra = em.merge(oldProveedorOfCompraListCompra);
                }
            }
            for (Proveedorxmateriaprima proveedorxmateriaprimaListProveedorxmateriaprima : proveedor.getProveedorxmateriaprimaList()) {
                Proveedor oldProveedorOfProveedorxmateriaprimaListProveedorxmateriaprima = proveedorxmateriaprimaListProveedorxmateriaprima.getProveedor();
                proveedorxmateriaprimaListProveedorxmateriaprima.setProveedor(proveedor);
                proveedorxmateriaprimaListProveedorxmateriaprima = em.merge(proveedorxmateriaprimaListProveedorxmateriaprima);
                if (oldProveedorOfProveedorxmateriaprimaListProveedorxmateriaprima != null) {
                    oldProveedorOfProveedorxmateriaprimaListProveedorxmateriaprima.getProveedorxmateriaprimaList().remove(proveedorxmateriaprimaListProveedorxmateriaprima);
                    oldProveedorOfProveedorxmateriaprimaListProveedorxmateriaprima = em.merge(oldProveedorOfProveedorxmateriaprimaListProveedorxmateriaprima);
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
            Responsable responsableOld = persistentProveedor.getResponsable();
            Responsable responsableNew = proveedor.getResponsable();
            Domicilio domicilioOld = persistentProveedor.getDomicilio();
            Domicilio domicilioNew = proveedor.getDomicilio();
            Condicioniva condicionOld = persistentProveedor.getCondicion();
            Condicioniva condicionNew = proveedor.getCondicion();
            List<Compra> compraListOld = persistentProveedor.getCompraList();
            List<Compra> compraListNew = proveedor.getCompraList();
            List<Proveedorxmateriaprima> proveedorxmateriaprimaListOld = persistentProveedor.getProveedorxmateriaprimaList();
            List<Proveedorxmateriaprima> proveedorxmateriaprimaListNew = proveedor.getProveedorxmateriaprimaList();
            List<String> illegalOrphanMessages = null;
            for (Proveedorxmateriaprima proveedorxmateriaprimaListOldProveedorxmateriaprima : proveedorxmateriaprimaListOld) {
                if (!proveedorxmateriaprimaListNew.contains(proveedorxmateriaprimaListOldProveedorxmateriaprima)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Proveedorxmateriaprima " + proveedorxmateriaprimaListOldProveedorxmateriaprima + " since its proveedor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (responsableNew != null) {
                responsableNew = em.getReference(responsableNew.getClass(), responsableNew.getIdresponsable());
                proveedor.setResponsable(responsableNew);
            }
            if (domicilioNew != null) {
                domicilioNew = em.getReference(domicilioNew.getClass(), domicilioNew.getIddomicilio());
                proveedor.setDomicilio(domicilioNew);
            }
            if (condicionNew != null) {
                condicionNew = em.getReference(condicionNew.getClass(), condicionNew.getIdcondicioniva());
                proveedor.setCondicion(condicionNew);
            }
            List<Compra> attachedCompraListNew = new ArrayList<Compra>();
            for (Compra compraListNewCompraToAttach : compraListNew) {
                compraListNewCompraToAttach = em.getReference(compraListNewCompraToAttach.getClass(), compraListNewCompraToAttach.getIdcompra());
                attachedCompraListNew.add(compraListNewCompraToAttach);
            }
            compraListNew = attachedCompraListNew;
            proveedor.setCompraList(compraListNew);
            List<Proveedorxmateriaprima> attachedProveedorxmateriaprimaListNew = new ArrayList<Proveedorxmateriaprima>();
            for (Proveedorxmateriaprima proveedorxmateriaprimaListNewProveedorxmateriaprimaToAttach : proveedorxmateriaprimaListNew) {
                proveedorxmateriaprimaListNewProveedorxmateriaprimaToAttach = em.getReference(proveedorxmateriaprimaListNewProveedorxmateriaprimaToAttach.getClass(), proveedorxmateriaprimaListNewProveedorxmateriaprimaToAttach.getProveedorxmateriaprimaPK());
                attachedProveedorxmateriaprimaListNew.add(proveedorxmateriaprimaListNewProveedorxmateriaprimaToAttach);
            }
            proveedorxmateriaprimaListNew = attachedProveedorxmateriaprimaListNew;
            proveedor.setProveedorxmateriaprimaList(proveedorxmateriaprimaListNew);
            proveedor = em.merge(proveedor);
            if (responsableOld != null && !responsableOld.equals(responsableNew)) {
                responsableOld.getProveedorList().remove(proveedor);
                responsableOld = em.merge(responsableOld);
            }
            if (responsableNew != null && !responsableNew.equals(responsableOld)) {
                responsableNew.getProveedorList().add(proveedor);
                responsableNew = em.merge(responsableNew);
            }
            if (domicilioOld != null && !domicilioOld.equals(domicilioNew)) {
                domicilioOld.getProveedorList().remove(proveedor);
                domicilioOld = em.merge(domicilioOld);
            }
            if (domicilioNew != null && !domicilioNew.equals(domicilioOld)) {
                domicilioNew.getProveedorList().add(proveedor);
                domicilioNew = em.merge(domicilioNew);
            }
            if (condicionOld != null && !condicionOld.equals(condicionNew)) {
                condicionOld.getProveedorList().remove(proveedor);
                condicionOld = em.merge(condicionOld);
            }
            if (condicionNew != null && !condicionNew.equals(condicionOld)) {
                condicionNew.getProveedorList().add(proveedor);
                condicionNew = em.merge(condicionNew);
            }
            for (Compra compraListOldCompra : compraListOld) {
                if (!compraListNew.contains(compraListOldCompra)) {
                    compraListOldCompra.setProveedor(null);
                    compraListOldCompra = em.merge(compraListOldCompra);
                }
            }
            for (Compra compraListNewCompra : compraListNew) {
                if (!compraListOld.contains(compraListNewCompra)) {
                    Proveedor oldProveedorOfCompraListNewCompra = compraListNewCompra.getProveedor();
                    compraListNewCompra.setProveedor(proveedor);
                    compraListNewCompra = em.merge(compraListNewCompra);
                    if (oldProveedorOfCompraListNewCompra != null && !oldProveedorOfCompraListNewCompra.equals(proveedor)) {
                        oldProveedorOfCompraListNewCompra.getCompraList().remove(compraListNewCompra);
                        oldProveedorOfCompraListNewCompra = em.merge(oldProveedorOfCompraListNewCompra);
                    }
                }
            }
            for (Proveedorxmateriaprima proveedorxmateriaprimaListNewProveedorxmateriaprima : proveedorxmateriaprimaListNew) {
                if (!proveedorxmateriaprimaListOld.contains(proveedorxmateriaprimaListNewProveedorxmateriaprima)) {
                    Proveedor oldProveedorOfProveedorxmateriaprimaListNewProveedorxmateriaprima = proveedorxmateriaprimaListNewProveedorxmateriaprima.getProveedor();
                    proveedorxmateriaprimaListNewProveedorxmateriaprima.setProveedor(proveedor);
                    proveedorxmateriaprimaListNewProveedorxmateriaprima = em.merge(proveedorxmateriaprimaListNewProveedorxmateriaprima);
                    if (oldProveedorOfProveedorxmateriaprimaListNewProveedorxmateriaprima != null && !oldProveedorOfProveedorxmateriaprimaListNewProveedorxmateriaprima.equals(proveedor)) {
                        oldProveedorOfProveedorxmateriaprimaListNewProveedorxmateriaprima.getProveedorxmateriaprimaList().remove(proveedorxmateriaprimaListNewProveedorxmateriaprima);
                        oldProveedorOfProveedorxmateriaprimaListNewProveedorxmateriaprima = em.merge(oldProveedorOfProveedorxmateriaprimaListNewProveedorxmateriaprima);
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
            List<Proveedorxmateriaprima> proveedorxmateriaprimaListOrphanCheck = proveedor.getProveedorxmateriaprimaList();
            for (Proveedorxmateriaprima proveedorxmateriaprimaListOrphanCheckProveedorxmateriaprima : proveedorxmateriaprimaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Proveedor (" + proveedor + ") cannot be destroyed since the Proveedorxmateriaprima " + proveedorxmateriaprimaListOrphanCheckProveedorxmateriaprima + " in its proveedorxmateriaprimaList field has a non-nullable proveedor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Responsable responsable = proveedor.getResponsable();
            if (responsable != null) {
                responsable.getProveedorList().remove(proveedor);
                responsable = em.merge(responsable);
            }
            Domicilio domicilio = proveedor.getDomicilio();
            if (domicilio != null) {
                domicilio.getProveedorList().remove(proveedor);
                domicilio = em.merge(domicilio);
            }
            Condicioniva condicion = proveedor.getCondicion();
            if (condicion != null) {
                condicion.getProveedorList().remove(proveedor);
                condicion = em.merge(condicion);
            }
            List<Compra> compraList = proveedor.getCompraList();
            for (Compra compraListCompra : compraList) {
                compraListCompra.setProveedor(null);
                compraListCompra = em.merge(compraListCompra);
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
