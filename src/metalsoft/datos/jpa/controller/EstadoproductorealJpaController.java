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
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Estadoproductoreal;
import metalsoft.datos.jpa.entity.Productoreal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class EstadoproductorealJpaController implements Serializable {

    public EstadoproductorealJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadoproductoreal estadoproductoreal) throws PreexistingEntityException, Exception {
        if (estadoproductoreal.getProductorealList() == null) {
            estadoproductoreal.setProductorealList(new ArrayList<Productoreal>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Productoreal> attachedProductorealList = new ArrayList<Productoreal>();
            for (Productoreal productorealListProductorealToAttach : estadoproductoreal.getProductorealList()) {
                productorealListProductorealToAttach = em.getReference(productorealListProductorealToAttach.getClass(), productorealListProductorealToAttach.getIdproductoreal());
                attachedProductorealList.add(productorealListProductorealToAttach);
            }
            estadoproductoreal.setProductorealList(attachedProductorealList);
            em.persist(estadoproductoreal);
            for (Productoreal productorealListProductoreal : estadoproductoreal.getProductorealList()) {
                Estadoproductoreal oldEstadoOfProductorealListProductoreal = productorealListProductoreal.getEstado();
                productorealListProductoreal.setEstado(estadoproductoreal);
                productorealListProductoreal = em.merge(productorealListProductoreal);
                if (oldEstadoOfProductorealListProductoreal != null) {
                    oldEstadoOfProductorealListProductoreal.getProductorealList().remove(productorealListProductoreal);
                    oldEstadoOfProductorealListProductoreal = em.merge(oldEstadoOfProductorealListProductoreal);
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
            List<Productoreal> productorealListOld = persistentEstadoproductoreal.getProductorealList();
            List<Productoreal> productorealListNew = estadoproductoreal.getProductorealList();
            List<Productoreal> attachedProductorealListNew = new ArrayList<Productoreal>();
            for (Productoreal productorealListNewProductorealToAttach : productorealListNew) {
                productorealListNewProductorealToAttach = em.getReference(productorealListNewProductorealToAttach.getClass(), productorealListNewProductorealToAttach.getIdproductoreal());
                attachedProductorealListNew.add(productorealListNewProductorealToAttach);
            }
            productorealListNew = attachedProductorealListNew;
            estadoproductoreal.setProductorealList(productorealListNew);
            estadoproductoreal = em.merge(estadoproductoreal);
            for (Productoreal productorealListOldProductoreal : productorealListOld) {
                if (!productorealListNew.contains(productorealListOldProductoreal)) {
                    productorealListOldProductoreal.setEstado(null);
                    productorealListOldProductoreal = em.merge(productorealListOldProductoreal);
                }
            }
            for (Productoreal productorealListNewProductoreal : productorealListNew) {
                if (!productorealListOld.contains(productorealListNewProductoreal)) {
                    Estadoproductoreal oldEstadoOfProductorealListNewProductoreal = productorealListNewProductoreal.getEstado();
                    productorealListNewProductoreal.setEstado(estadoproductoreal);
                    productorealListNewProductoreal = em.merge(productorealListNewProductoreal);
                    if (oldEstadoOfProductorealListNewProductoreal != null && !oldEstadoOfProductorealListNewProductoreal.equals(estadoproductoreal)) {
                        oldEstadoOfProductorealListNewProductoreal.getProductorealList().remove(productorealListNewProductoreal);
                        oldEstadoOfProductorealListNewProductoreal = em.merge(oldEstadoOfProductorealListNewProductoreal);
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
            List<Productoreal> productorealList = estadoproductoreal.getProductorealList();
            for (Productoreal productorealListProductoreal : productorealList) {
                productorealListProductoreal.setEstado(null);
                productorealListProductoreal = em.merge(productorealListProductoreal);
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
