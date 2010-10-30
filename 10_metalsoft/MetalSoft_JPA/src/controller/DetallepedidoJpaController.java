/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detallepedido;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Pedido;
import entity.Producto;

/**
 *
 * @author Nino
 */
public class DetallepedidoJpaController {

    public DetallepedidoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallepedido detallepedido) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido idpedido = detallepedido.getIdpedido();
            if (idpedido != null) {
                idpedido = em.getReference(idpedido.getClass(), idpedido.getIdpedido());
                detallepedido.setIdpedido(idpedido);
            }
            Pedido idpedido1 = detallepedido.getIdpedido1();
            if (idpedido1 != null) {
                idpedido1 = em.getReference(idpedido1.getClass(), idpedido1.getIdpedido());
                detallepedido.setIdpedido1(idpedido1);
            }
            Producto producto = detallepedido.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getIdproducto());
                detallepedido.setProducto(producto);
            }
            Producto producto1 = detallepedido.getProducto1();
            if (producto1 != null) {
                producto1 = em.getReference(producto1.getClass(), producto1.getIdproducto());
                detallepedido.setProducto1(producto1);
            }
            em.persist(detallepedido);
            if (idpedido != null) {
                idpedido.getDetallepedidoSet().add(detallepedido);
                idpedido = em.merge(idpedido);
            }
            if (idpedido1 != null) {
                idpedido1.getDetallepedidoSet().add(detallepedido);
                idpedido1 = em.merge(idpedido1);
            }
            if (producto != null) {
                producto.getDetallepedidoSet().add(detallepedido);
                producto = em.merge(producto);
            }
            if (producto1 != null) {
                producto1.getDetallepedidoSet().add(detallepedido);
                producto1 = em.merge(producto1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallepedido(detallepedido.getIddetalle()) != null) {
                throw new PreexistingEntityException("Detallepedido " + detallepedido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallepedido detallepedido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallepedido persistentDetallepedido = em.find(Detallepedido.class, detallepedido.getIddetalle());
            Pedido idpedidoOld = persistentDetallepedido.getIdpedido();
            Pedido idpedidoNew = detallepedido.getIdpedido();
            Pedido idpedido1Old = persistentDetallepedido.getIdpedido1();
            Pedido idpedido1New = detallepedido.getIdpedido1();
            Producto productoOld = persistentDetallepedido.getProducto();
            Producto productoNew = detallepedido.getProducto();
            Producto producto1Old = persistentDetallepedido.getProducto1();
            Producto producto1New = detallepedido.getProducto1();
            if (idpedidoNew != null) {
                idpedidoNew = em.getReference(idpedidoNew.getClass(), idpedidoNew.getIdpedido());
                detallepedido.setIdpedido(idpedidoNew);
            }
            if (idpedido1New != null) {
                idpedido1New = em.getReference(idpedido1New.getClass(), idpedido1New.getIdpedido());
                detallepedido.setIdpedido1(idpedido1New);
            }
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getIdproducto());
                detallepedido.setProducto(productoNew);
            }
            if (producto1New != null) {
                producto1New = em.getReference(producto1New.getClass(), producto1New.getIdproducto());
                detallepedido.setProducto1(producto1New);
            }
            detallepedido = em.merge(detallepedido);
            if (idpedidoOld != null && !idpedidoOld.equals(idpedidoNew)) {
                idpedidoOld.getDetallepedidoSet().remove(detallepedido);
                idpedidoOld = em.merge(idpedidoOld);
            }
            if (idpedidoNew != null && !idpedidoNew.equals(idpedidoOld)) {
                idpedidoNew.getDetallepedidoSet().add(detallepedido);
                idpedidoNew = em.merge(idpedidoNew);
            }
            if (idpedido1Old != null && !idpedido1Old.equals(idpedido1New)) {
                idpedido1Old.getDetallepedidoSet().remove(detallepedido);
                idpedido1Old = em.merge(idpedido1Old);
            }
            if (idpedido1New != null && !idpedido1New.equals(idpedido1Old)) {
                idpedido1New.getDetallepedidoSet().add(detallepedido);
                idpedido1New = em.merge(idpedido1New);
            }
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getDetallepedidoSet().remove(detallepedido);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getDetallepedidoSet().add(detallepedido);
                productoNew = em.merge(productoNew);
            }
            if (producto1Old != null && !producto1Old.equals(producto1New)) {
                producto1Old.getDetallepedidoSet().remove(detallepedido);
                producto1Old = em.merge(producto1Old);
            }
            if (producto1New != null && !producto1New.equals(producto1Old)) {
                producto1New.getDetallepedidoSet().add(detallepedido);
                producto1New = em.merge(producto1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detallepedido.getIddetalle();
                if (findDetallepedido(id) == null) {
                    throw new NonexistentEntityException("The detallepedido with id " + id + " no longer exists.");
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
            Detallepedido detallepedido;
            try {
                detallepedido = em.getReference(Detallepedido.class, id);
                detallepedido.getIddetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallepedido with id " + id + " no longer exists.", enfe);
            }
            Pedido idpedido = detallepedido.getIdpedido();
            if (idpedido != null) {
                idpedido.getDetallepedidoSet().remove(detallepedido);
                idpedido = em.merge(idpedido);
            }
            Pedido idpedido1 = detallepedido.getIdpedido1();
            if (idpedido1 != null) {
                idpedido1.getDetallepedidoSet().remove(detallepedido);
                idpedido1 = em.merge(idpedido1);
            }
            Producto producto = detallepedido.getProducto();
            if (producto != null) {
                producto.getDetallepedidoSet().remove(detallepedido);
                producto = em.merge(producto);
            }
            Producto producto1 = detallepedido.getProducto1();
            if (producto1 != null) {
                producto1.getDetallepedidoSet().remove(detallepedido);
                producto1 = em.merge(producto1);
            }
            em.remove(detallepedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallepedido> findDetallepedidoEntities() {
        return findDetallepedidoEntities(true, -1, -1);
    }

    public List<Detallepedido> findDetallepedidoEntities(int maxResults, int firstResult) {
        return findDetallepedidoEntities(false, maxResults, firstResult);
    }

    private List<Detallepedido> findDetallepedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallepedido.class));
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

    public Detallepedido findDetallepedido(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallepedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallepedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallepedido> rt = cq.from(Detallepedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
