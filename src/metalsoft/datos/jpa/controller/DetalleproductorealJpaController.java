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
import metalsoft.datos.jpa.entity.Detalleproductoreal;
import metalsoft.datos.jpa.entity.DetalleproductorealPK;
import metalsoft.datos.jpa.entity.Productoreal;

/**
 *
 * @author Nino
 */
public class DetalleproductorealJpaController {

    public DetalleproductorealJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleproductoreal detalleproductoreal) throws PreexistingEntityException, Exception {
        if (detalleproductoreal.getDetalleproductorealPK() == null) {
            detalleproductoreal.setDetalleproductorealPK(new DetalleproductorealPK());
        }
        detalleproductoreal.getDetalleproductorealPK().setIdproductoreal(detalleproductoreal.getProductoreal().getIdproductoreal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Productoreal productoreal = detalleproductoreal.getProductoreal();
            if (productoreal != null) {
                productoreal = em.getReference(productoreal.getClass(), productoreal.getIdproductoreal());
                detalleproductoreal.setProductoreal(productoreal);
            }
            em.persist(detalleproductoreal);
            if (productoreal != null) {
                productoreal.getDetalleproductorealList().add(detalleproductoreal);
                productoreal = em.merge(productoreal);
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
        detalleproductoreal.getDetalleproductorealPK().setIdproductoreal(detalleproductoreal.getProductoreal().getIdproductoreal());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleproductoreal persistentDetalleproductoreal = em.find(Detalleproductoreal.class, detalleproductoreal.getDetalleproductorealPK());
            Productoreal productorealOld = persistentDetalleproductoreal.getProductoreal();
            Productoreal productorealNew = detalleproductoreal.getProductoreal();
            if (productorealNew != null) {
                productorealNew = em.getReference(productorealNew.getClass(), productorealNew.getIdproductoreal());
                detalleproductoreal.setProductoreal(productorealNew);
            }
            detalleproductoreal = em.merge(detalleproductoreal);
            if (productorealOld != null && !productorealOld.equals(productorealNew)) {
                productorealOld.getDetalleproductorealList().remove(detalleproductoreal);
                productorealOld = em.merge(productorealOld);
            }
            if (productorealNew != null && !productorealNew.equals(productorealOld)) {
                productorealNew.getDetalleproductorealList().add(detalleproductoreal);
                productorealNew = em.merge(productorealNew);
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
                productoreal.getDetalleproductorealList().remove(detalleproductoreal);
                productoreal = em.merge(productoreal);
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
