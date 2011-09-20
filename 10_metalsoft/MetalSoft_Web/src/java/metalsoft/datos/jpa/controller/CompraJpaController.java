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
import metalsoft.datos.jpa.entity.Compra;
import metalsoft.datos.jpa.entity.Proveedor;
import metalsoft.datos.jpa.entity.Estadocompra;
import metalsoft.datos.jpa.entity.Detallecompra;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Reclamoproveedor;

/**
 *
 * @author Nino
 */
public class CompraJpaController implements Serializable {

    public CompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Compra compra) throws PreexistingEntityException, Exception {
        if (compra.getDetallecompraList() == null) {
            compra.setDetallecompraList(new ArrayList<Detallecompra>());
        }
        if (compra.getReclamoproveedorList() == null) {
            compra.setReclamoproveedorList(new ArrayList<Reclamoproveedor>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor proveedor = compra.getProveedor();
            if (proveedor != null) {
                proveedor = em.getReference(proveedor.getClass(), proveedor.getIdproveedor());
                compra.setProveedor(proveedor);
            }
            Estadocompra estado = compra.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                compra.setEstado(estado);
            }
            List<Detallecompra> attachedDetallecompraList = new ArrayList<Detallecompra>();
            for (Detallecompra detallecompraListDetallecompraToAttach : compra.getDetallecompraList()) {
                detallecompraListDetallecompraToAttach = em.getReference(detallecompraListDetallecompraToAttach.getClass(), detallecompraListDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraList.add(detallecompraListDetallecompraToAttach);
            }
            compra.setDetallecompraList(attachedDetallecompraList);
            List<Reclamoproveedor> attachedReclamoproveedorList = new ArrayList<Reclamoproveedor>();
            for (Reclamoproveedor reclamoproveedorListReclamoproveedorToAttach : compra.getReclamoproveedorList()) {
                reclamoproveedorListReclamoproveedorToAttach = em.getReference(reclamoproveedorListReclamoproveedorToAttach.getClass(), reclamoproveedorListReclamoproveedorToAttach.getIdreclamo());
                attachedReclamoproveedorList.add(reclamoproveedorListReclamoproveedorToAttach);
            }
            compra.setReclamoproveedorList(attachedReclamoproveedorList);
            em.persist(compra);
            if (proveedor != null) {
                proveedor.getCompraList().add(compra);
                proveedor = em.merge(proveedor);
            }
            if (estado != null) {
                estado.getCompraList().add(compra);
                estado = em.merge(estado);
            }
            for (Detallecompra detallecompraListDetallecompra : compra.getDetallecompraList()) {
                Compra oldCompraOfDetallecompraListDetallecompra = detallecompraListDetallecompra.getCompra();
                detallecompraListDetallecompra.setCompra(compra);
                detallecompraListDetallecompra = em.merge(detallecompraListDetallecompra);
                if (oldCompraOfDetallecompraListDetallecompra != null) {
                    oldCompraOfDetallecompraListDetallecompra.getDetallecompraList().remove(detallecompraListDetallecompra);
                    oldCompraOfDetallecompraListDetallecompra = em.merge(oldCompraOfDetallecompraListDetallecompra);
                }
            }
            for (Reclamoproveedor reclamoproveedorListReclamoproveedor : compra.getReclamoproveedorList()) {
                Compra oldCompraOfReclamoproveedorListReclamoproveedor = reclamoproveedorListReclamoproveedor.getCompra();
                reclamoproveedorListReclamoproveedor.setCompra(compra);
                reclamoproveedorListReclamoproveedor = em.merge(reclamoproveedorListReclamoproveedor);
                if (oldCompraOfReclamoproveedorListReclamoproveedor != null) {
                    oldCompraOfReclamoproveedorListReclamoproveedor.getReclamoproveedorList().remove(reclamoproveedorListReclamoproveedor);
                    oldCompraOfReclamoproveedorListReclamoproveedor = em.merge(oldCompraOfReclamoproveedorListReclamoproveedor);
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
            Proveedor proveedorOld = persistentCompra.getProveedor();
            Proveedor proveedorNew = compra.getProveedor();
            Estadocompra estadoOld = persistentCompra.getEstado();
            Estadocompra estadoNew = compra.getEstado();
            List<Detallecompra> detallecompraListOld = persistentCompra.getDetallecompraList();
            List<Detallecompra> detallecompraListNew = compra.getDetallecompraList();
            List<Reclamoproveedor> reclamoproveedorListOld = persistentCompra.getReclamoproveedorList();
            List<Reclamoproveedor> reclamoproveedorListNew = compra.getReclamoproveedorList();
            List<String> illegalOrphanMessages = null;
            for (Detallecompra detallecompraListOldDetallecompra : detallecompraListOld) {
                if (!detallecompraListNew.contains(detallecompraListOldDetallecompra)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallecompra " + detallecompraListOldDetallecompra + " since its compra field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (proveedorNew != null) {
                proveedorNew = em.getReference(proveedorNew.getClass(), proveedorNew.getIdproveedor());
                compra.setProveedor(proveedorNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                compra.setEstado(estadoNew);
            }
            List<Detallecompra> attachedDetallecompraListNew = new ArrayList<Detallecompra>();
            for (Detallecompra detallecompraListNewDetallecompraToAttach : detallecompraListNew) {
                detallecompraListNewDetallecompraToAttach = em.getReference(detallecompraListNewDetallecompraToAttach.getClass(), detallecompraListNewDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraListNew.add(detallecompraListNewDetallecompraToAttach);
            }
            detallecompraListNew = attachedDetallecompraListNew;
            compra.setDetallecompraList(detallecompraListNew);
            List<Reclamoproveedor> attachedReclamoproveedorListNew = new ArrayList<Reclamoproveedor>();
            for (Reclamoproveedor reclamoproveedorListNewReclamoproveedorToAttach : reclamoproveedorListNew) {
                reclamoproveedorListNewReclamoproveedorToAttach = em.getReference(reclamoproveedorListNewReclamoproveedorToAttach.getClass(), reclamoproveedorListNewReclamoproveedorToAttach.getIdreclamo());
                attachedReclamoproveedorListNew.add(reclamoproveedorListNewReclamoproveedorToAttach);
            }
            reclamoproveedorListNew = attachedReclamoproveedorListNew;
            compra.setReclamoproveedorList(reclamoproveedorListNew);
            compra = em.merge(compra);
            if (proveedorOld != null && !proveedorOld.equals(proveedorNew)) {
                proveedorOld.getCompraList().remove(compra);
                proveedorOld = em.merge(proveedorOld);
            }
            if (proveedorNew != null && !proveedorNew.equals(proveedorOld)) {
                proveedorNew.getCompraList().add(compra);
                proveedorNew = em.merge(proveedorNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getCompraList().remove(compra);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getCompraList().add(compra);
                estadoNew = em.merge(estadoNew);
            }
            for (Detallecompra detallecompraListNewDetallecompra : detallecompraListNew) {
                if (!detallecompraListOld.contains(detallecompraListNewDetallecompra)) {
                    Compra oldCompraOfDetallecompraListNewDetallecompra = detallecompraListNewDetallecompra.getCompra();
                    detallecompraListNewDetallecompra.setCompra(compra);
                    detallecompraListNewDetallecompra = em.merge(detallecompraListNewDetallecompra);
                    if (oldCompraOfDetallecompraListNewDetallecompra != null && !oldCompraOfDetallecompraListNewDetallecompra.equals(compra)) {
                        oldCompraOfDetallecompraListNewDetallecompra.getDetallecompraList().remove(detallecompraListNewDetallecompra);
                        oldCompraOfDetallecompraListNewDetallecompra = em.merge(oldCompraOfDetallecompraListNewDetallecompra);
                    }
                }
            }
            for (Reclamoproveedor reclamoproveedorListOldReclamoproveedor : reclamoproveedorListOld) {
                if (!reclamoproveedorListNew.contains(reclamoproveedorListOldReclamoproveedor)) {
                    reclamoproveedorListOldReclamoproveedor.setCompra(null);
                    reclamoproveedorListOldReclamoproveedor = em.merge(reclamoproveedorListOldReclamoproveedor);
                }
            }
            for (Reclamoproveedor reclamoproveedorListNewReclamoproveedor : reclamoproveedorListNew) {
                if (!reclamoproveedorListOld.contains(reclamoproveedorListNewReclamoproveedor)) {
                    Compra oldCompraOfReclamoproveedorListNewReclamoproveedor = reclamoproveedorListNewReclamoproveedor.getCompra();
                    reclamoproveedorListNewReclamoproveedor.setCompra(compra);
                    reclamoproveedorListNewReclamoproveedor = em.merge(reclamoproveedorListNewReclamoproveedor);
                    if (oldCompraOfReclamoproveedorListNewReclamoproveedor != null && !oldCompraOfReclamoproveedorListNewReclamoproveedor.equals(compra)) {
                        oldCompraOfReclamoproveedorListNewReclamoproveedor.getReclamoproveedorList().remove(reclamoproveedorListNewReclamoproveedor);
                        oldCompraOfReclamoproveedorListNewReclamoproveedor = em.merge(oldCompraOfReclamoproveedorListNewReclamoproveedor);
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
            List<Detallecompra> detallecompraListOrphanCheck = compra.getDetallecompraList();
            for (Detallecompra detallecompraListOrphanCheckDetallecompra : detallecompraListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Compra (" + compra + ") cannot be destroyed since the Detallecompra " + detallecompraListOrphanCheckDetallecompra + " in its detallecompraList field has a non-nullable compra field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Proveedor proveedor = compra.getProveedor();
            if (proveedor != null) {
                proveedor.getCompraList().remove(compra);
                proveedor = em.merge(proveedor);
            }
            Estadocompra estado = compra.getEstado();
            if (estado != null) {
                estado.getCompraList().remove(compra);
                estado = em.merge(estado);
            }
            List<Reclamoproveedor> reclamoproveedorList = compra.getReclamoproveedorList();
            for (Reclamoproveedor reclamoproveedorListReclamoproveedor : reclamoproveedorList) {
                reclamoproveedorListReclamoproveedor.setCompra(null);
                reclamoproveedorListReclamoproveedor = em.merge(reclamoproveedorListReclamoproveedor);
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
