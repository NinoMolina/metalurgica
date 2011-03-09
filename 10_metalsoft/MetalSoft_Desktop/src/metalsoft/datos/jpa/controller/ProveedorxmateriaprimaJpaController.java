/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos.jpa.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Materiaprima;
import metalsoft.datos.jpa.entity.Proveedor;
import metalsoft.datos.jpa.entity.Proveedorxmateriaprima;
import metalsoft.datos.jpa.entity.ProveedorxmateriaprimaPK;

/**
 *
 * @author Nino
 */
public class ProveedorxmateriaprimaJpaController {

    public ProveedorxmateriaprimaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedorxmateriaprima proveedorxmateriaprima) throws PreexistingEntityException, Exception {
        if (proveedorxmateriaprima.getProveedorxmateriaprimaPK() == null) {
            proveedorxmateriaprima.setProveedorxmateriaprimaPK(new ProveedorxmateriaprimaPK());
        }
        proveedorxmateriaprima.getProveedorxmateriaprimaPK().setIdmateriaprima(proveedorxmateriaprima.getMateriaprima().getIdmateriaprima());
        proveedorxmateriaprima.getProveedorxmateriaprimaPK().setIdproveedor(proveedorxmateriaprima.getProveedor().getIdproveedor());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materiaprima materiaprima = proveedorxmateriaprima.getMateriaprima();
            if (materiaprima != null) {
                materiaprima = em.getReference(materiaprima.getClass(), materiaprima.getIdmateriaprima());
                proveedorxmateriaprima.setMateriaprima(materiaprima);
            }
            Proveedor proveedor = proveedorxmateriaprima.getProveedor();
            if (proveedor != null) {
                proveedor = em.getReference(proveedor.getClass(), proveedor.getIdproveedor());
                proveedorxmateriaprima.setProveedor(proveedor);
            }
            em.persist(proveedorxmateriaprima);
            if (materiaprima != null) {
                materiaprima.getProveedorxmateriaprimaList().add(proveedorxmateriaprima);
                materiaprima = em.merge(materiaprima);
            }
            if (proveedor != null) {
                proveedor.getProveedorxmateriaprimaList().add(proveedorxmateriaprima);
                proveedor = em.merge(proveedor);
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
        proveedorxmateriaprima.getProveedorxmateriaprimaPK().setIdmateriaprima(proveedorxmateriaprima.getMateriaprima().getIdmateriaprima());
        proveedorxmateriaprima.getProveedorxmateriaprimaPK().setIdproveedor(proveedorxmateriaprima.getProveedor().getIdproveedor());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedorxmateriaprima persistentProveedorxmateriaprima = em.find(Proveedorxmateriaprima.class, proveedorxmateriaprima.getProveedorxmateriaprimaPK());
            Materiaprima materiaprimaOld = persistentProveedorxmateriaprima.getMateriaprima();
            Materiaprima materiaprimaNew = proveedorxmateriaprima.getMateriaprima();
            Proveedor proveedorOld = persistentProveedorxmateriaprima.getProveedor();
            Proveedor proveedorNew = proveedorxmateriaprima.getProveedor();
            if (materiaprimaNew != null) {
                materiaprimaNew = em.getReference(materiaprimaNew.getClass(), materiaprimaNew.getIdmateriaprima());
                proveedorxmateriaprima.setMateriaprima(materiaprimaNew);
            }
            if (proveedorNew != null) {
                proveedorNew = em.getReference(proveedorNew.getClass(), proveedorNew.getIdproveedor());
                proveedorxmateriaprima.setProveedor(proveedorNew);
            }
            proveedorxmateriaprima = em.merge(proveedorxmateriaprima);
            if (materiaprimaOld != null && !materiaprimaOld.equals(materiaprimaNew)) {
                materiaprimaOld.getProveedorxmateriaprimaList().remove(proveedorxmateriaprima);
                materiaprimaOld = em.merge(materiaprimaOld);
            }
            if (materiaprimaNew != null && !materiaprimaNew.equals(materiaprimaOld)) {
                materiaprimaNew.getProveedorxmateriaprimaList().add(proveedorxmateriaprima);
                materiaprimaNew = em.merge(materiaprimaNew);
            }
            if (proveedorOld != null && !proveedorOld.equals(proveedorNew)) {
                proveedorOld.getProveedorxmateriaprimaList().remove(proveedorxmateriaprima);
                proveedorOld = em.merge(proveedorOld);
            }
            if (proveedorNew != null && !proveedorNew.equals(proveedorOld)) {
                proveedorNew.getProveedorxmateriaprimaList().add(proveedorxmateriaprima);
                proveedorNew = em.merge(proveedorNew);
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
            Materiaprima materiaprima = proveedorxmateriaprima.getMateriaprima();
            if (materiaprima != null) {
                materiaprima.getProveedorxmateriaprimaList().remove(proveedorxmateriaprima);
                materiaprima = em.merge(materiaprima);
            }
            Proveedor proveedor = proveedorxmateriaprima.getProveedor();
            if (proveedor != null) {
                proveedor.getProveedorxmateriaprimaList().remove(proveedorxmateriaprima);
                proveedor = em.merge(proveedor);
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
