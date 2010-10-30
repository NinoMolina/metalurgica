/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detallereclamocliente;
import entity.DetallereclamoclientePK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Producto;
import entity.Reclamocliente;

/**
 *
 * @author Nino
 */
public class DetallereclamoclienteJpaController {

    public DetallereclamoclienteJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallereclamocliente detallereclamocliente) throws PreexistingEntityException, Exception {
        if (detallereclamocliente.getDetallereclamoclientePK() == null) {
            detallereclamocliente.setDetallereclamoclientePK(new DetallereclamoclientePK());
        }
        detallereclamocliente.getDetallereclamoclientePK().setIdreclamo(detallereclamocliente.getReclamocliente1().getIdreclamo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Producto producto = detallereclamocliente.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getIdproducto());
                detallereclamocliente.setProducto(producto);
            }
            Producto producto1 = detallereclamocliente.getProducto1();
            if (producto1 != null) {
                producto1 = em.getReference(producto1.getClass(), producto1.getIdproducto());
                detallereclamocliente.setProducto1(producto1);
            }
            Reclamocliente reclamocliente = detallereclamocliente.getReclamocliente();
            if (reclamocliente != null) {
                reclamocliente = em.getReference(reclamocliente.getClass(), reclamocliente.getIdreclamo());
                detallereclamocliente.setReclamocliente(reclamocliente);
            }
            Reclamocliente reclamocliente1 = detallereclamocliente.getReclamocliente1();
            if (reclamocliente1 != null) {
                reclamocliente1 = em.getReference(reclamocliente1.getClass(), reclamocliente1.getIdreclamo());
                detallereclamocliente.setReclamocliente1(reclamocliente1);
            }
            em.persist(detallereclamocliente);
            if (producto != null) {
                producto.getDetallereclamoclienteSet().add(detallereclamocliente);
                producto = em.merge(producto);
            }
            if (producto1 != null) {
                producto1.getDetallereclamoclienteSet().add(detallereclamocliente);
                producto1 = em.merge(producto1);
            }
            if (reclamocliente != null) {
                reclamocliente.getDetallereclamoclienteSet().add(detallereclamocliente);
                reclamocliente = em.merge(reclamocliente);
            }
            if (reclamocliente1 != null) {
                reclamocliente1.getDetallereclamoclienteSet().add(detallereclamocliente);
                reclamocliente1 = em.merge(reclamocliente1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallereclamocliente(detallereclamocliente.getDetallereclamoclientePK()) != null) {
                throw new PreexistingEntityException("Detallereclamocliente " + detallereclamocliente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallereclamocliente detallereclamocliente) throws NonexistentEntityException, Exception {
        detallereclamocliente.getDetallereclamoclientePK().setIdreclamo(detallereclamocliente.getReclamocliente1().getIdreclamo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallereclamocliente persistentDetallereclamocliente = em.find(Detallereclamocliente.class, detallereclamocliente.getDetallereclamoclientePK());
            Producto productoOld = persistentDetallereclamocliente.getProducto();
            Producto productoNew = detallereclamocliente.getProducto();
            Producto producto1Old = persistentDetallereclamocliente.getProducto1();
            Producto producto1New = detallereclamocliente.getProducto1();
            Reclamocliente reclamoclienteOld = persistentDetallereclamocliente.getReclamocliente();
            Reclamocliente reclamoclienteNew = detallereclamocliente.getReclamocliente();
            Reclamocliente reclamocliente1Old = persistentDetallereclamocliente.getReclamocliente1();
            Reclamocliente reclamocliente1New = detallereclamocliente.getReclamocliente1();
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getIdproducto());
                detallereclamocliente.setProducto(productoNew);
            }
            if (producto1New != null) {
                producto1New = em.getReference(producto1New.getClass(), producto1New.getIdproducto());
                detallereclamocliente.setProducto1(producto1New);
            }
            if (reclamoclienteNew != null) {
                reclamoclienteNew = em.getReference(reclamoclienteNew.getClass(), reclamoclienteNew.getIdreclamo());
                detallereclamocliente.setReclamocliente(reclamoclienteNew);
            }
            if (reclamocliente1New != null) {
                reclamocliente1New = em.getReference(reclamocliente1New.getClass(), reclamocliente1New.getIdreclamo());
                detallereclamocliente.setReclamocliente1(reclamocliente1New);
            }
            detallereclamocliente = em.merge(detallereclamocliente);
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getDetallereclamoclienteSet().remove(detallereclamocliente);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getDetallereclamoclienteSet().add(detallereclamocliente);
                productoNew = em.merge(productoNew);
            }
            if (producto1Old != null && !producto1Old.equals(producto1New)) {
                producto1Old.getDetallereclamoclienteSet().remove(detallereclamocliente);
                producto1Old = em.merge(producto1Old);
            }
            if (producto1New != null && !producto1New.equals(producto1Old)) {
                producto1New.getDetallereclamoclienteSet().add(detallereclamocliente);
                producto1New = em.merge(producto1New);
            }
            if (reclamoclienteOld != null && !reclamoclienteOld.equals(reclamoclienteNew)) {
                reclamoclienteOld.getDetallereclamoclienteSet().remove(detallereclamocliente);
                reclamoclienteOld = em.merge(reclamoclienteOld);
            }
            if (reclamoclienteNew != null && !reclamoclienteNew.equals(reclamoclienteOld)) {
                reclamoclienteNew.getDetallereclamoclienteSet().add(detallereclamocliente);
                reclamoclienteNew = em.merge(reclamoclienteNew);
            }
            if (reclamocliente1Old != null && !reclamocliente1Old.equals(reclamocliente1New)) {
                reclamocliente1Old.getDetallereclamoclienteSet().remove(detallereclamocliente);
                reclamocliente1Old = em.merge(reclamocliente1Old);
            }
            if (reclamocliente1New != null && !reclamocliente1New.equals(reclamocliente1Old)) {
                reclamocliente1New.getDetallereclamoclienteSet().add(detallereclamocliente);
                reclamocliente1New = em.merge(reclamocliente1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetallereclamoclientePK id = detallereclamocliente.getDetallereclamoclientePK();
                if (findDetallereclamocliente(id) == null) {
                    throw new NonexistentEntityException("The detallereclamocliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetallereclamoclientePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallereclamocliente detallereclamocliente;
            try {
                detallereclamocliente = em.getReference(Detallereclamocliente.class, id);
                detallereclamocliente.getDetallereclamoclientePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallereclamocliente with id " + id + " no longer exists.", enfe);
            }
            Producto producto = detallereclamocliente.getProducto();
            if (producto != null) {
                producto.getDetallereclamoclienteSet().remove(detallereclamocliente);
                producto = em.merge(producto);
            }
            Producto producto1 = detallereclamocliente.getProducto1();
            if (producto1 != null) {
                producto1.getDetallereclamoclienteSet().remove(detallereclamocliente);
                producto1 = em.merge(producto1);
            }
            Reclamocliente reclamocliente = detallereclamocliente.getReclamocliente();
            if (reclamocliente != null) {
                reclamocliente.getDetallereclamoclienteSet().remove(detallereclamocliente);
                reclamocliente = em.merge(reclamocliente);
            }
            Reclamocliente reclamocliente1 = detallereclamocliente.getReclamocliente1();
            if (reclamocliente1 != null) {
                reclamocliente1.getDetallereclamoclienteSet().remove(detallereclamocliente);
                reclamocliente1 = em.merge(reclamocliente1);
            }
            em.remove(detallereclamocliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallereclamocliente> findDetallereclamoclienteEntities() {
        return findDetallereclamoclienteEntities(true, -1, -1);
    }

    public List<Detallereclamocliente> findDetallereclamoclienteEntities(int maxResults, int firstResult) {
        return findDetallereclamoclienteEntities(false, maxResults, firstResult);
    }

    private List<Detallereclamocliente> findDetallereclamoclienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallereclamocliente.class));
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

    public Detallereclamocliente findDetallereclamocliente(DetallereclamoclientePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallereclamocliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallereclamoclienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallereclamocliente> rt = cq.from(Detallereclamocliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
