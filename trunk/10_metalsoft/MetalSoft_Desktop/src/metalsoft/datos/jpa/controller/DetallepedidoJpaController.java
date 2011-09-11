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
import metalsoft.datos.jpa.entity.Detallepedido;
import metalsoft.datos.jpa.entity.Producto;
import metalsoft.datos.jpa.entity.Pedido;

/**
 *
 * @author Nino
 */
public class DetallepedidoJpaController implements Serializable {

    public DetallepedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
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
            Producto producto = detallepedido.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getIdproducto());
                detallepedido.setProducto(producto);
            }
            Pedido idpedido = detallepedido.getIdpedido();
            if (idpedido != null) {
                idpedido = em.getReference(idpedido.getClass(), idpedido.getIdpedido());
                detallepedido.setIdpedido(idpedido);
            }
            em.persist(detallepedido);
            if (producto != null) {
                producto.getDetallepedidoList().add(detallepedido);
                producto = em.merge(producto);
            }
            if (idpedido != null) {
                idpedido.getDetallepedidoList().add(detallepedido);
                idpedido = em.merge(idpedido);
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
            Producto productoOld = persistentDetallepedido.getProducto();
            Producto productoNew = detallepedido.getProducto();
            Pedido idpedidoOld = persistentDetallepedido.getIdpedido();
            Pedido idpedidoNew = detallepedido.getIdpedido();
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getIdproducto());
                detallepedido.setProducto(productoNew);
            }
            if (idpedidoNew != null) {
                idpedidoNew = em.getReference(idpedidoNew.getClass(), idpedidoNew.getIdpedido());
                detallepedido.setIdpedido(idpedidoNew);
            }
            detallepedido = em.merge(detallepedido);
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getDetallepedidoList().remove(detallepedido);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getDetallepedidoList().add(detallepedido);
                productoNew = em.merge(productoNew);
            }
            if (idpedidoOld != null && !idpedidoOld.equals(idpedidoNew)) {
                idpedidoOld.getDetallepedidoList().remove(detallepedido);
                idpedidoOld = em.merge(idpedidoOld);
            }
            if (idpedidoNew != null && !idpedidoNew.equals(idpedidoOld)) {
                idpedidoNew.getDetallepedidoList().add(detallepedido);
                idpedidoNew = em.merge(idpedidoNew);
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
            Producto producto = detallepedido.getProducto();
            if (producto != null) {
                producto.getDetallepedidoList().remove(detallepedido);
                producto = em.merge(producto);
            }
            Pedido idpedido = detallepedido.getIdpedido();
            if (idpedido != null) {
                idpedido.getDetallepedidoList().remove(detallepedido);
                idpedido = em.merge(idpedido);
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
