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
import metalsoft.datos.jpa.entity.Detallereclamocliente;
import metalsoft.datos.jpa.entity.DetallereclamoclientePK;
import metalsoft.datos.jpa.entity.Reclamocliente;
import metalsoft.datos.jpa.entity.Producto;

/**
 *
 * @author Nino
 */
public class DetallereclamoclienteJpaController implements Serializable {

    public DetallereclamoclienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallereclamocliente detallereclamocliente) throws PreexistingEntityException, Exception {
        if (detallereclamocliente.getDetallereclamoclientePK() == null) {
            detallereclamocliente.setDetallereclamoclientePK(new DetallereclamoclientePK());
        }
        detallereclamocliente.getDetallereclamoclientePK().setIdreclamo(detallereclamocliente.getReclamocliente().getIdreclamo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reclamocliente reclamocliente = detallereclamocliente.getReclamocliente();
            if (reclamocliente != null) {
                reclamocliente = em.getReference(reclamocliente.getClass(), reclamocliente.getIdreclamo());
                detallereclamocliente.setReclamocliente(reclamocliente);
            }
            Producto producto = detallereclamocliente.getProducto();
            if (producto != null) {
                producto = em.getReference(producto.getClass(), producto.getIdproducto());
                detallereclamocliente.setProducto(producto);
            }
            em.persist(detallereclamocliente);
            if (reclamocliente != null) {
                reclamocliente.getDetallereclamoclienteList().add(detallereclamocliente);
                reclamocliente = em.merge(reclamocliente);
            }
            if (producto != null) {
                producto.getDetallereclamoclienteList().add(detallereclamocliente);
                producto = em.merge(producto);
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
        detallereclamocliente.getDetallereclamoclientePK().setIdreclamo(detallereclamocliente.getReclamocliente().getIdreclamo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallereclamocliente persistentDetallereclamocliente = em.find(Detallereclamocliente.class, detallereclamocliente.getDetallereclamoclientePK());
            Reclamocliente reclamoclienteOld = persistentDetallereclamocliente.getReclamocliente();
            Reclamocliente reclamoclienteNew = detallereclamocliente.getReclamocliente();
            Producto productoOld = persistentDetallereclamocliente.getProducto();
            Producto productoNew = detallereclamocliente.getProducto();
            if (reclamoclienteNew != null) {
                reclamoclienteNew = em.getReference(reclamoclienteNew.getClass(), reclamoclienteNew.getIdreclamo());
                detallereclamocliente.setReclamocliente(reclamoclienteNew);
            }
            if (productoNew != null) {
                productoNew = em.getReference(productoNew.getClass(), productoNew.getIdproducto());
                detallereclamocliente.setProducto(productoNew);
            }
            detallereclamocliente = em.merge(detallereclamocliente);
            if (reclamoclienteOld != null && !reclamoclienteOld.equals(reclamoclienteNew)) {
                reclamoclienteOld.getDetallereclamoclienteList().remove(detallereclamocliente);
                reclamoclienteOld = em.merge(reclamoclienteOld);
            }
            if (reclamoclienteNew != null && !reclamoclienteNew.equals(reclamoclienteOld)) {
                reclamoclienteNew.getDetallereclamoclienteList().add(detallereclamocliente);
                reclamoclienteNew = em.merge(reclamoclienteNew);
            }
            if (productoOld != null && !productoOld.equals(productoNew)) {
                productoOld.getDetallereclamoclienteList().remove(detallereclamocliente);
                productoOld = em.merge(productoOld);
            }
            if (productoNew != null && !productoNew.equals(productoOld)) {
                productoNew.getDetallereclamoclienteList().add(detallereclamocliente);
                productoNew = em.merge(productoNew);
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
            Reclamocliente reclamocliente = detallereclamocliente.getReclamocliente();
            if (reclamocliente != null) {
                reclamocliente.getDetallereclamoclienteList().remove(detallereclamocliente);
                reclamocliente = em.merge(reclamocliente);
            }
            Producto producto = detallereclamocliente.getProducto();
            if (producto != null) {
                producto.getDetallereclamoclienteList().remove(detallereclamocliente);
                producto = em.merge(producto);
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
