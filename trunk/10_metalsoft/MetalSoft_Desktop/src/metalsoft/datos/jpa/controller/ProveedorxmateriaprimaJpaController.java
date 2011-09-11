/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Proveedor;
import metalsoft.datos.jpa.entity.Materiaprima;
import metalsoft.datos.jpa.entity.Proveedorxmateriaprima;
import metalsoft.datos.jpa.entity.ProveedorxmateriaprimaPK;

/**
 *
 * @author Nino
 */
public class ProveedorxmateriaprimaJpaController implements Serializable {

    public ProveedorxmateriaprimaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedorxmateriaprima proveedorxmateriaprima) throws PreexistingEntityException, Exception {
        if (proveedorxmateriaprima.getProveedorxmateriaprimaPK() == null) {
            proveedorxmateriaprima.setProveedorxmateriaprimaPK(new ProveedorxmateriaprimaPK());
        }
        proveedorxmateriaprima.getProveedorxmateriaprimaPK().setIdproveedor(proveedorxmateriaprima.getProveedor().getIdproveedor());
        proveedorxmateriaprima.getProveedorxmateriaprimaPK().setIdmateriaprima(proveedorxmateriaprima.getMateriaprima().getIdmateriaprima());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedor proveedor = proveedorxmateriaprima.getProveedor();
            if (proveedor != null) {
                proveedor = em.getReference(proveedor.getClass(), proveedor.getIdproveedor());
                proveedorxmateriaprima.setProveedor(proveedor);
            }
            Materiaprima materiaprima = proveedorxmateriaprima.getMateriaprima();
            if (materiaprima != null) {
                materiaprima = em.getReference(materiaprima.getClass(), materiaprima.getIdmateriaprima());
                proveedorxmateriaprima.setMateriaprima(materiaprima);
            }
            em.persist(proveedorxmateriaprima);
            if (proveedor != null) {
                proveedor.getProveedorxmateriaprimaList().add(proveedorxmateriaprima);
                proveedor = em.merge(proveedor);
            }
            if (materiaprima != null) {
                materiaprima.getProveedorxmateriaprimaList().add(proveedorxmateriaprima);
                materiaprima = em.merge(materiaprima);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findProveedorxmateriaprima(proveedorxmateriaprima.getProveedorxmateriaprimaPK()) != null) {
                throw new PreexistingEntityException("Proveedorxmateriaprima " + proveedorxmateriaprima + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedorxmateriaprima proveedorxmateriaprima) throws NonexistentEntityException, Exception {
        proveedorxmateriaprima.getProveedorxmateriaprimaPK().setIdproveedor(proveedorxmateriaprima.getProveedor().getIdproveedor());
        proveedorxmateriaprima.getProveedorxmateriaprimaPK().setIdmateriaprima(proveedorxmateriaprima.getMateriaprima().getIdmateriaprima());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedorxmateriaprima persistentProveedorxmateriaprima = em.find(Proveedorxmateriaprima.class, proveedorxmateriaprima.getProveedorxmateriaprimaPK());
            Proveedor proveedorOld = persistentProveedorxmateriaprima.getProveedor();
            Proveedor proveedorNew = proveedorxmateriaprima.getProveedor();
            Materiaprima materiaprimaOld = persistentProveedorxmateriaprima.getMateriaprima();
            Materiaprima materiaprimaNew = proveedorxmateriaprima.getMateriaprima();
            if (proveedorNew != null) {
                proveedorNew = em.getReference(proveedorNew.getClass(), proveedorNew.getIdproveedor());
                proveedorxmateriaprima.setProveedor(proveedorNew);
            }
            if (materiaprimaNew != null) {
                materiaprimaNew = em.getReference(materiaprimaNew.getClass(), materiaprimaNew.getIdmateriaprima());
                proveedorxmateriaprima.setMateriaprima(materiaprimaNew);
            }
            proveedorxmateriaprima = em.merge(proveedorxmateriaprima);
            if (proveedorOld != null && !proveedorOld.equals(proveedorNew)) {
                proveedorOld.getProveedorxmateriaprimaList().remove(proveedorxmateriaprima);
                proveedorOld = em.merge(proveedorOld);
            }
            if (proveedorNew != null && !proveedorNew.equals(proveedorOld)) {
                proveedorNew.getProveedorxmateriaprimaList().add(proveedorxmateriaprima);
                proveedorNew = em.merge(proveedorNew);
            }
            if (materiaprimaOld != null && !materiaprimaOld.equals(materiaprimaNew)) {
                materiaprimaOld.getProveedorxmateriaprimaList().remove(proveedorxmateriaprima);
                materiaprimaOld = em.merge(materiaprimaOld);
            }
            if (materiaprimaNew != null && !materiaprimaNew.equals(materiaprimaOld)) {
                materiaprimaNew.getProveedorxmateriaprimaList().add(proveedorxmateriaprima);
                materiaprimaNew = em.merge(materiaprimaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ProveedorxmateriaprimaPK id = proveedorxmateriaprima.getProveedorxmateriaprimaPK();
                if (findProveedorxmateriaprima(id) == null) {
                    throw new NonexistentEntityException("The proveedorxmateriaprima with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ProveedorxmateriaprimaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedorxmateriaprima proveedorxmateriaprima;
            try {
                proveedorxmateriaprima = em.getReference(Proveedorxmateriaprima.class, id);
                proveedorxmateriaprima.getProveedorxmateriaprimaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The proveedorxmateriaprima with id " + id + " no longer exists.", enfe);
            }
            Proveedor proveedor = proveedorxmateriaprima.getProveedor();
            if (proveedor != null) {
                proveedor.getProveedorxmateriaprimaList().remove(proveedorxmateriaprima);
                proveedor = em.merge(proveedor);
            }
            Materiaprima materiaprima = proveedorxmateriaprima.getMateriaprima();
            if (materiaprima != null) {
                materiaprima.getProveedorxmateriaprimaList().remove(proveedorxmateriaprima);
                materiaprima = em.merge(materiaprima);
            }
            em.remove(proveedorxmateriaprima);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedorxmateriaprima> findProveedorxmateriaprimaEntities() {
        return findProveedorxmateriaprimaEntities(true, -1, -1);
    }

    public List<Proveedorxmateriaprima> findProveedorxmateriaprimaEntities(int maxResults, int firstResult) {
        return findProveedorxmateriaprimaEntities(false, maxResults, firstResult);
    }

    private List<Proveedorxmateriaprima> findProveedorxmateriaprimaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedorxmateriaprima.class));
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

    public Proveedorxmateriaprima findProveedorxmateriaprima(ProveedorxmateriaprimaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedorxmateriaprima.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedorxmateriaprimaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedorxmateriaprima> rt = cq.from(Proveedorxmateriaprima.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
