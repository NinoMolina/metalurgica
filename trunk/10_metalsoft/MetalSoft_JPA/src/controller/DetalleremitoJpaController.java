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
        detalleremito.getDetalleremitoPK().setIdremito(detalleremito.getRemito1().getIdremito());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto = detalleremito.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getIdproducto());
                detalleremito.setProducto(producto);
            }
            Producto producto1 = detalleremito.getProducto1();
            if (producto1 != null) {
                producto1 = em.getReference(producto1.getClass(), producto1.getIdproducto());
                detalleremito.setProducto1(producto1);
            }
            Remito remito = detalleremito.getRemito();
            if (remito != null) {
                remito = em.getReference(remito.getClass(), remito.getIdremito());
                detalleremito.setRemito(remito);
            }
            Remito remito1 = detalleremito.getRemito1();
            if (remito1 != null) {
                remito1 = em.getReference(remito1.getClass(), remito1.getIdremito());
                detalleremito.setRemito1(remito1);
            }
            em.persist(detalleremito);
            if (producto != null) {
                producto.getDetalleremitoSet().add(detalleremito);
                producto = em.merge(producto);
            }
            if (producto1 != null) {
                producto1.getDetalleremitoSet().add(detalleremito);
                producto1 = em.merge(producto1);
            }
            if (remito != null) {
                remito.getDetalleremitoSet().add(detalleremito);
                remito = em.merge(remito);
            }
            if (remito1 != null) {
                remito1.getDetalleremitoSet().add(detalleremito);
                remito1 = em.merge(remito1);
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
        detalleremito.getDetalleremitoPK().setIdremito(detalleremito.getRemito1().getIdremito());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleremito persistentDetalleremito = em.find(Detalleremito.class, detalleremito.getDetalleremitoPK());
            Producto productoOld = persistentDetalleremito.getProducto();
            Producto productoNew = detalleremito.getProducto();
            Producto producto1Old = persistentDetalleremito.getProducto1();
            Producto producto1New = detalleremito.getProducto1();
            Remito remitoOld = persistentDetalleremito.getRemito();
            Remito remitoNew = detalleremito.getRemito();
            Remito remito1Old = persistentDetalleremito.getRemito1();
            Remito remito1New = detalleremito.getRemito1();
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getIdproducto());
                detalleremito.setProducto(productoNew);
            }
            if (producto1New != null) {
                producto1New = em.getReference(producto1New.getClass(), producto1New.getIdproducto());
                detalleremito.setProducto1(producto1New);
            }
            if (remitoNew != null) {
                remitoNew = em.getReference(remitoNew.getClass(), remitoNew.getIdremito());
                detalleremito.setRemito(remitoNew);
            }
            if (remito1New != null) {
                remito1New = em.getReference(remito1New.getClass(), remito1New.getIdremito());
                detalleremito.setRemito1(remito1New);
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
            if (producto1Old != null && !producto1Old.equals(producto1New)) {
                producto1Old.getDetalleremitoSet().remove(detalleremito);
                producto1Old = em.merge(producto1Old);
            }
            if (producto1New != null && !producto1New.equals(producto1Old)) {
                producto1New.getDetalleremitoSet().add(detalleremito);
                producto1New = em.merge(producto1New);
            }
            if (remitoOld != null && !remitoOld.equals(remitoNew)) {
                remitoOld.getDetalleremitoSet().remove(detalleremito);
                remitoOld = em.merge(remitoOld);
            }
            if (remitoNew != null && !remitoNew.equals(remitoOld)) {
                remitoNew.getDetalleremitoSet().add(detalleremito);
                remitoNew = em.merge(remitoNew);
            }
            if (remito1Old != null && !remito1Old.equals(remito1New)) {
                remito1Old.getDetalleremitoSet().remove(detalleremito);
                remito1Old = em.merge(remito1Old);
            }
            if (remito1New != null && !remito1New.equals(remito1Old)) {
                remito1New.getDetalleremitoSet().add(detalleremito);
                remito1New = em.merge(remito1New);
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
            Producto producto1 = detalleremito.getProducto1();
            if (producto1 != null) {
                producto1.getDetalleremitoSet().remove(detalleremito);
                producto1 = em.merge(producto1);
            }
            Remito remito = detalleremito.getRemito();
            if (remito != null) {
                remito.getDetalleremitoSet().remove(detalleremito);
                remito = em.merge(remito);
            }
            Remito remito1 = detalleremito.getRemito1();
            if (remito1 != null) {
                remito1.getDetalleremitoSet().remove(detalleremito);
                remito1 = em.merge(remito1);
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
