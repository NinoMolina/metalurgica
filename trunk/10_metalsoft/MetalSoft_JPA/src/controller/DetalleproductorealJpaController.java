/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detalleproductoreal;
import entity.DetalleproductorealPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Productoreal;

/**
 *
 * @author Nino
 */
public class DetalleproductorealJpaController {

    public DetalleproductorealJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleproductoreal detalleproductoreal) throws PreexistingEntityException, Exception {
        if (detalleproductoreal.getDetalleproductorealPK() == null) {
            detalleproductoreal.setDetalleproductorealPK(new DetalleproductorealPK());
        }
        detalleproductoreal.getDetalleproductorealPK().setIdproductoreal(detalleproductoreal.getProductoreal1().getIdproductoreal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productoreal productoreal = detalleproductoreal.getProductoreal();
            if (productoreal != null) {
                productoreal = em.getReference(productoreal.getClass(), productoreal.getIdproductoreal());
                detalleproductoreal.setProductoreal(productoreal);
            }
            Productoreal productoreal1 = detalleproductoreal.getProductoreal1();
            if (productoreal1 != null) {
                productoreal1 = em.getReference(productoreal1.getClass(), productoreal1.getIdproductoreal());
                detalleproductoreal.setProductoreal1(productoreal1);
            }
            em.persist(detalleproductoreal);
            if (productoreal != null) {
                productoreal.getDetalleproductorealSet().add(detalleproductoreal);
                productoreal = em.merge(productoreal);
            }
            if (productoreal1 != null) {
                productoreal1.getDetalleproductorealSet().add(detalleproductoreal);
                productoreal1 = em.merge(productoreal1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleproductoreal(detalleproductoreal.getDetalleproductorealPK()) != null) {
                throw new PreexistingEntityException("Detalleproductoreal " + detalleproductoreal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleproductoreal detalleproductoreal) throws NonexistentEntityException, Exception {
        detalleproductoreal.getDetalleproductorealPK().setIdproductoreal(detalleproductoreal.getProductoreal1().getIdproductoreal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleproductoreal persistentDetalleproductoreal = em.find(Detalleproductoreal.class, detalleproductoreal.getDetalleproductorealPK());
            Productoreal productorealOld = persistentDetalleproductoreal.getProductoreal();
            Productoreal productorealNew = detalleproductoreal.getProductoreal();
            Productoreal productoreal1Old = persistentDetalleproductoreal.getProductoreal1();
            Productoreal productoreal1New = detalleproductoreal.getProductoreal1();
            if (productorealNew != null) {
                productorealNew = em.getReference(productorealNew.getClass(), productorealNew.getIdproductoreal());
                detalleproductoreal.setProductoreal(productorealNew);
            }
            if (productoreal1New != null) {
                productoreal1New = em.getReference(productoreal1New.getClass(), productoreal1New.getIdproductoreal());
                detalleproductoreal.setProductoreal1(productoreal1New);
            }
            detalleproductoreal = em.merge(detalleproductoreal);
            if (productorealOld != null && !productorealOld.equals(productorealNew)) {
                productorealOld.getDetalleproductorealSet().remove(detalleproductoreal);
                productorealOld = em.merge(productorealOld);
            }
            if (productorealNew != null && !productorealNew.equals(productorealOld)) {
                productorealNew.getDetalleproductorealSet().add(detalleproductoreal);
                productorealNew = em.merge(productorealNew);
            }
            if (productoreal1Old != null && !productoreal1Old.equals(productoreal1New)) {
                productoreal1Old.getDetalleproductorealSet().remove(detalleproductoreal);
                productoreal1Old = em.merge(productoreal1Old);
            }
            if (productoreal1New != null && !productoreal1New.equals(productoreal1Old)) {
                productoreal1New.getDetalleproductorealSet().add(detalleproductoreal);
                productoreal1New = em.merge(productoreal1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalleproductorealPK id = detalleproductoreal.getDetalleproductorealPK();
                if (findDetalleproductoreal(id) == null) {
                    throw new NonexistentEntityException("The detalleproductoreal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetalleproductorealPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleproductoreal detalleproductoreal;
            try {
                detalleproductoreal = em.getReference(Detalleproductoreal.class, id);
                detalleproductoreal.getDetalleproductorealPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleproductoreal with id " + id + " no longer exists.", enfe);
            }
            Productoreal productoreal = detalleproductoreal.getProductoreal();
            if (productoreal != null) {
                productoreal.getDetalleproductorealSet().remove(detalleproductoreal);
                productoreal = em.merge(productoreal);
            }
            Productoreal productoreal1 = detalleproductoreal.getProductoreal1();
            if (productoreal1 != null) {
                productoreal1.getDetalleproductorealSet().remove(detalleproductoreal);
                productoreal1 = em.merge(productoreal1);
            }
            em.remove(detalleproductoreal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleproductoreal> findDetalleproductorealEntities() {
        return findDetalleproductorealEntities(true, -1, -1);
    }

    public List<Detalleproductoreal> findDetalleproductorealEntities(int maxResults, int firstResult) {
        return findDetalleproductorealEntities(false, maxResults, firstResult);
    }

    private List<Detalleproductoreal> findDetalleproductorealEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleproductoreal.class));
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

    public Detalleproductoreal findDetalleproductoreal(DetalleproductorealPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleproductoreal.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleproductorealCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleproductoreal> rt = cq.from(Detalleproductoreal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
