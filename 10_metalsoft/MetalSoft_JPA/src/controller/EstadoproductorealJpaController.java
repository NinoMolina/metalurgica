/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Estadoproductoreal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Productoreal;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class EstadoproductorealJpaController {

    public EstadoproductorealJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadoproductoreal estadoproductoreal) throws PreexistingEntityException, Exception {
        if (estadoproductoreal.getProductorealSet() == null) {
            estadoproductoreal.setProductorealSet(new HashSet<Productoreal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Productoreal> attachedProductorealSet = new HashSet<Productoreal>();
            for (Productoreal productorealSetProductorealToAttach : estadoproductoreal.getProductorealSet()) {
                productorealSetProductorealToAttach = em.getReference(productorealSetProductorealToAttach.getClass(), productorealSetProductorealToAttach.getIdproductoreal());
                attachedProductorealSet.add(productorealSetProductorealToAttach);
            }
            estadoproductoreal.setProductorealSet(attachedProductorealSet);
            em.persist(estadoproductoreal);
            for (Productoreal productorealSetProductoreal : estadoproductoreal.getProductorealSet()) {
                Estadoproductoreal oldEstadoOfProductorealSetProductoreal = productorealSetProductoreal.getEstado();
                productorealSetProductoreal.setEstado(estadoproductoreal);
                productorealSetProductoreal = em.merge(productorealSetProductoreal);
                if (oldEstadoOfProductorealSetProductoreal != null) {
                    oldEstadoOfProductorealSetProductoreal.getProductorealSet().remove(productorealSetProductoreal);
                    oldEstadoOfProductorealSetProductoreal = em.merge(oldEstadoOfProductorealSetProductoreal);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoproductoreal(estadoproductoreal.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadoproductoreal " + estadoproductoreal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadoproductoreal estadoproductoreal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoproductoreal persistentEstadoproductoreal = em.find(Estadoproductoreal.class, estadoproductoreal.getIdestado());
            Set<Productoreal> productorealSetOld = persistentEstadoproductoreal.getProductorealSet();
            Set<Productoreal> productorealSetNew = estadoproductoreal.getProductorealSet();
            Set<Productoreal> attachedProductorealSetNew = new HashSet<Productoreal>();
            for (Productoreal productorealSetNewProductorealToAttach : productorealSetNew) {
                productorealSetNewProductorealToAttach = em.getReference(productorealSetNewProductorealToAttach.getClass(), productorealSetNewProductorealToAttach.getIdproductoreal());
                attachedProductorealSetNew.add(productorealSetNewProductorealToAttach);
            }
            productorealSetNew = attachedProductorealSetNew;
            estadoproductoreal.setProductorealSet(productorealSetNew);
            estadoproductoreal = em.merge(estadoproductoreal);
            for (Productoreal productorealSetOldProductoreal : productorealSetOld) {
                if (!productorealSetNew.contains(productorealSetOldProductoreal)) {
                    productorealSetOldProductoreal.setEstado(null);
                    productorealSetOldProductoreal = em.merge(productorealSetOldProductoreal);
                }
            }
            for (Productoreal productorealSetNewProductoreal : productorealSetNew) {
                if (!productorealSetOld.contains(productorealSetNewProductoreal)) {
                    Estadoproductoreal oldEstadoOfProductorealSetNewProductoreal = productorealSetNewProductoreal.getEstado();
                    productorealSetNewProductoreal.setEstado(estadoproductoreal);
                    productorealSetNewProductoreal = em.merge(productorealSetNewProductoreal);
                    if (oldEstadoOfProductorealSetNewProductoreal != null && !oldEstadoOfProductorealSetNewProductoreal.equals(estadoproductoreal)) {
                        oldEstadoOfProductorealSetNewProductoreal.getProductorealSet().remove(productorealSetNewProductoreal);
                        oldEstadoOfProductorealSetNewProductoreal = em.merge(oldEstadoOfProductorealSetNewProductoreal);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadoproductoreal.getIdestado();
                if (findEstadoproductoreal(id) == null) {
                    throw new NonexistentEntityException("The estadoproductoreal with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoproductoreal estadoproductoreal;
            try {
                estadoproductoreal = em.getReference(Estadoproductoreal.class, id);
                estadoproductoreal.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoproductoreal with id " + id + " no longer exists.", enfe);
            }
            Set<Productoreal> productorealSet = estadoproductoreal.getProductorealSet();
            for (Productoreal productorealSetProductoreal : productorealSet) {
                productorealSetProductoreal.setEstado(null);
                productorealSetProductoreal = em.merge(productorealSetProductoreal);
            }
            em.remove(estadoproductoreal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadoproductoreal> findEstadoproductorealEntities() {
        return findEstadoproductorealEntities(true, -1, -1);
    }

    public List<Estadoproductoreal> findEstadoproductorealEntities(int maxResults, int firstResult) {
        return findEstadoproductorealEntities(false, maxResults, firstResult);
    }

    private List<Estadoproductoreal> findEstadoproductorealEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadoproductoreal.class));
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

    public Estadoproductoreal findEstadoproductoreal(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadoproductoreal.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoproductorealCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadoproductoreal> rt = cq.from(Estadoproductoreal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
