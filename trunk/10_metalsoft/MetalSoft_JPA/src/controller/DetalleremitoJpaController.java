/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detalleremito;
import entity.DetalleremitoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Producto;
import entity.Remito;

/**
 *
 * @author Nino
 */
public class DetalleremitoJpaController {

    public DetalleremitoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleremito detalleremito) throws PreexistingEntityException, Exception {
        if (detalleremito.getDetalleremitoPK() == null) {
            detalleremito.setDetalleremitoPK(new DetalleremitoPK());
        }
        detalleremito.getDetalleremitoPK().setIdremito(detalleremito.getRemito().getIdremito());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto = detalleremito.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getIdproducto());
                detalleremito.setProducto(producto);
            }
            Remito remito = detalleremito.getRemito();
            if (remito != null) {
                remito = em.getReference(remito.getClass(), remito.getIdremito());
                detalleremito.setRemito(remito);
            }
            em.persist(detalleremito);
            if (producto != null) {
                producto.getDetalleremitoSet().add(detalleremito);
                producto = em.merge(producto);
            }
            if (remito != null) {
                remito.getDetalleremitoSet().add(detalleremito);
                remito = em.merge(remito);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleremito(detalleremito.getDetalleremitoPK()) != null) {
                throw new PreexistingEntityException("Detalleremito " + detalleremito + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleremito detalleremito) throws NonexistentEntityException, Exception {
        detalleremito.getDetalleremitoPK().setIdremito(detalleremito.getRemito().getIdremito());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleremito persistentDetalleremito = em.find(Detalleremito.class, detalleremito.getDetalleremitoPK());
            Producto productoOld = persistentDetalleremito.getProducto();
            Producto productoNew = detalleremito.getProducto();
            Remito remitoOld = persistentDetalleremito.getRemito();
            Remito remitoNew = detalleremito.getRemito();
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getIdproducto());
                detalleremito.setProducto(productoNew);
            }
            if (remitoNew != null) {
                remitoNew = em.getReference(remitoNew.getClass(), remitoNew.getIdremito());
                detalleremito.setRemito(remitoNew);
            }
            detalleremito = em.merge(detalleremito);
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getDetalleremitoSet().remove(detalleremito);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getDetalleremitoSet().add(detalleremito);
                productoNew = em.merge(productoNew);
            }
            if (remitoOld != null && !remitoOld.equals(remitoNew)) {
                remitoOld.getDetalleremitoSet().remove(detalleremito);
                remitoOld = em.merge(remitoOld);
            }
            if (remitoNew != null && !remitoNew.equals(remitoOld)) {
                remitoNew.getDetalleremitoSet().add(detalleremito);
                remitoNew = em.merge(remitoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalleremitoPK id = detalleremito.getDetalleremitoPK();
                if (findDetalleremito(id) == null) {
                    throw new NonexistentEntityException("The detalleremito with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetalleremitoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleremito detalleremito;
            try {
                detalleremito = em.getReference(Detalleremito.class, id);
                detalleremito.getDetalleremitoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleremito with id " + id + " no longer exists.", enfe);
            }
            Producto producto = detalleremito.getProducto();
            if (producto != null) {
                producto.getDetalleremitoSet().remove(detalleremito);
                producto = em.merge(producto);
            }
            Remito remito = detalleremito.getRemito();
            if (remito != null) {
                remito.getDetalleremitoSet().remove(detalleremito);
                remito = em.merge(remito);
            }
            em.remove(detalleremito);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleremito> findDetalleremitoEntities() {
        return findDetalleremitoEntities(true, -1, -1);
    }

    public List<Detalleremito> findDetalleremitoEntities(int maxResults, int firstResult) {
        return findDetalleremitoEntities(false, maxResults, firstResult);
    }

    private List<Detalleremito> findDetalleremitoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleremito.class));
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

    public Detalleremito findDetalleremito(DetalleremitoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleremito.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleremitoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleremito> rt = cq.from(Detalleremito.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
