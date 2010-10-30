/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Proveedorxmateriaprima;
import entity.ProveedorxmateriaprimaPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Materiaprima;
import entity.Proveedor;

/**
 *
 * @author Nino
 */
public class ProveedorxmateriaprimaJpaController {

    public ProveedorxmateriaprimaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedorxmateriaprima proveedorxmateriaprima) throws PreexistingEntityException, Exception {
        if (proveedorxmateriaprima.getProveedorxmateriaprimaPK() == null) {
            proveedorxmateriaprima.setProveedorxmateriaprimaPK(new ProveedorxmateriaprimaPK());
        }
        proveedorxmateriaprima.getProveedorxmateriaprimaPK().setIdproveedor(proveedorxmateriaprima.getProveedor1().getIdproveedor());
        proveedorxmateriaprima.getProveedorxmateriaprimaPK().setIdmateriaprima(proveedorxmateriaprima.getMateriaprima1().getIdmateriaprima());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materiaprima materiaprima = proveedorxmateriaprima.getMateriaprima();
            if (materiaprima != null) {
                materiaprima = em.getReference(materiaprima.getClass(), materiaprima.getIdmateriaprima());
                proveedorxmateriaprima.setMateriaprima(materiaprima);
            }
            Materiaprima materiaprima1 = proveedorxmateriaprima.getMateriaprima1();
            if (materiaprima1 != null) {
                materiaprima1 = em.getReference(materiaprima1.getClass(), materiaprima1.getIdmateriaprima());
                proveedorxmateriaprima.setMateriaprima1(materiaprima1);
            }
            Proveedor proveedor = proveedorxmateriaprima.getProveedor();
            if (proveedor != null) {
                proveedor = em.getReference(proveedor.getClass(), proveedor.getIdproveedor());
                proveedorxmateriaprima.setProveedor(proveedor);
            }
            Proveedor proveedor1 = proveedorxmateriaprima.getProveedor1();
            if (proveedor1 != null) {
                proveedor1 = em.getReference(proveedor1.getClass(), proveedor1.getIdproveedor());
                proveedorxmateriaprima.setProveedor1(proveedor1);
            }
            em.persist(proveedorxmateriaprima);
            if (materiaprima != null) {
                materiaprima.getProveedorxmateriaprimaSet().add(proveedorxmateriaprima);
                materiaprima = em.merge(materiaprima);
            }
            if (materiaprima1 != null) {
                materiaprima1.getProveedorxmateriaprimaSet().add(proveedorxmateriaprima);
                materiaprima1 = em.merge(materiaprima1);
            }
            if (proveedor != null) {
                proveedor.getProveedorxmateriaprimaSet().add(proveedorxmateriaprima);
                proveedor = em.merge(proveedor);
            }
            if (proveedor1 != null) {
                proveedor1.getProveedorxmateriaprimaSet().add(proveedorxmateriaprima);
                proveedor1 = em.merge(proveedor1);
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
        proveedorxmateriaprima.getProveedorxmateriaprimaPK().setIdproveedor(proveedorxmateriaprima.getProveedor1().getIdproveedor());
        proveedorxmateriaprima.getProveedorxmateriaprimaPK().setIdmateriaprima(proveedorxmateriaprima.getMateriaprima1().getIdmateriaprima());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Proveedorxmateriaprima persistentProveedorxmateriaprima = em.find(Proveedorxmateriaprima.class, proveedorxmateriaprima.getProveedorxmateriaprimaPK());
            Materiaprima materiaprimaOld = persistentProveedorxmateriaprima.getMateriaprima();
            Materiaprima materiaprimaNew = proveedorxmateriaprima.getMateriaprima();
            Materiaprima materiaprima1Old = persistentProveedorxmateriaprima.getMateriaprima1();
            Materiaprima materiaprima1New = proveedorxmateriaprima.getMateriaprima1();
            Proveedor proveedorOld = persistentProveedorxmateriaprima.getProveedor();
            Proveedor proveedorNew = proveedorxmateriaprima.getProveedor();
            Proveedor proveedor1Old = persistentProveedorxmateriaprima.getProveedor1();
            Proveedor proveedor1New = proveedorxmateriaprima.getProveedor1();
            if (materiaprimaNew != null) {
                materiaprimaNew = em.getReference(materiaprimaNew.getClass(), materiaprimaNew.getIdmateriaprima());
                proveedorxmateriaprima.setMateriaprima(materiaprimaNew);
            }
            if (materiaprima1New != null) {
                materiaprima1New = em.getReference(materiaprima1New.getClass(), materiaprima1New.getIdmateriaprima());
                proveedorxmateriaprima.setMateriaprima1(materiaprima1New);
            }
            if (proveedorNew != null) {
                proveedorNew = em.getReference(proveedorNew.getClass(), proveedorNew.getIdproveedor());
                proveedorxmateriaprima.setProveedor(proveedorNew);
            }
            if (proveedor1New != null) {
                proveedor1New = em.getReference(proveedor1New.getClass(), proveedor1New.getIdproveedor());
                proveedorxmateriaprima.setProveedor1(proveedor1New);
            }
            proveedorxmateriaprima = em.merge(proveedorxmateriaprima);
            if (materiaprimaOld != null && !materiaprimaOld.equals(materiaprimaNew)) {
                materiaprimaOld.getProveedorxmateriaprimaSet().remove(proveedorxmateriaprima);
                materiaprimaOld = em.merge(materiaprimaOld);
            }
            if (materiaprimaNew != null && !materiaprimaNew.equals(materiaprimaOld)) {
                materiaprimaNew.getProveedorxmateriaprimaSet().add(proveedorxmateriaprima);
                materiaprimaNew = em.merge(materiaprimaNew);
            }
            if (materiaprima1Old != null && !materiaprima1Old.equals(materiaprima1New)) {
                materiaprima1Old.getProveedorxmateriaprimaSet().remove(proveedorxmateriaprima);
                materiaprima1Old = em.merge(materiaprima1Old);
            }
            if (materiaprima1New != null && !materiaprima1New.equals(materiaprima1Old)) {
                materiaprima1New.getProveedorxmateriaprimaSet().add(proveedorxmateriaprima);
                materiaprima1New = em.merge(materiaprima1New);
            }
            if (proveedorOld != null && !proveedorOld.equals(proveedorNew)) {
                proveedorOld.getProveedorxmateriaprimaSet().remove(proveedorxmateriaprima);
                proveedorOld = em.merge(proveedorOld);
            }
            if (proveedorNew != null && !proveedorNew.equals(proveedorOld)) {
                proveedorNew.getProveedorxmateriaprimaSet().add(proveedorxmateriaprima);
                proveedorNew = em.merge(proveedorNew);
            }
            if (proveedor1Old != null && !proveedor1Old.equals(proveedor1New)) {
                proveedor1Old.getProveedorxmateriaprimaSet().remove(proveedorxmateriaprima);
                proveedor1Old = em.merge(proveedor1Old);
            }
            if (proveedor1New != null && !proveedor1New.equals(proveedor1Old)) {
                proveedor1New.getProveedorxmateriaprimaSet().add(proveedorxmateriaprima);
                proveedor1New = em.merge(proveedor1New);
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
                materiaprima.getProveedorxmateriaprimaSet().remove(proveedorxmateriaprima);
                materiaprima = em.merge(materiaprima);
            }
            Materiaprima materiaprima1 = proveedorxmateriaprima.getMateriaprima1();
            if (materiaprima1 != null) {
                materiaprima1.getProveedorxmateriaprimaSet().remove(proveedorxmateriaprima);
                materiaprima1 = em.merge(materiaprima1);
            }
            Proveedor proveedor = proveedorxmateriaprima.getProveedor();
            if (proveedor != null) {
                proveedor.getProveedorxmateriaprimaSet().remove(proveedorxmateriaprima);
                proveedor = em.merge(proveedor);
            }
            Proveedor proveedor1 = proveedorxmateriaprima.getProveedor1();
            if (proveedor1 != null) {
                proveedor1.getProveedorxmateriaprimaSet().remove(proveedorxmateriaprima);
                proveedor1 = em.merge(proveedor1);
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
