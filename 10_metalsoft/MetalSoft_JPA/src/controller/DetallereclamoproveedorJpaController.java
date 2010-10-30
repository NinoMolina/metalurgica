/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detallereclamoproveedor;
import entity.DetallereclamoproveedorPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Detallecompra;
import entity.Reclamoproveedor;

/**
 *
 * @author Nino
 */
public class DetallereclamoproveedorJpaController {

    public DetallereclamoproveedorJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallereclamoproveedor detallereclamoproveedor) throws PreexistingEntityException, Exception {
        if (detallereclamoproveedor.getDetallereclamoproveedorPK() == null) {
            detallereclamoproveedor.setDetallereclamoproveedorPK(new DetallereclamoproveedorPK());
        }
        detallereclamoproveedor.getDetallereclamoproveedorPK().setIdreclamo(detallereclamoproveedor.getReclamoproveedor1().getIdreclamo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallecompra detallecompra = detallereclamoproveedor.getDetallecompra();
            if (detallecompra != null) {
                detallecompra = em.getReference(detallecompra.getClass(), detallecompra.getDetallecompraPK());
                detallereclamoproveedor.setDetallecompra(detallecompra);
            }
            Detallecompra detallecompra1 = detallereclamoproveedor.getDetallecompra1();
            if (detallecompra1 != null) {
                detallecompra1 = em.getReference(detallecompra1.getClass(), detallecompra1.getDetallecompraPK());
                detallereclamoproveedor.setDetallecompra1(detallecompra1);
            }
            Reclamoproveedor reclamoproveedor = detallereclamoproveedor.getReclamoproveedor();
            if (reclamoproveedor != null) {
                reclamoproveedor = em.getReference(reclamoproveedor.getClass(), reclamoproveedor.getIdreclamo());
                detallereclamoproveedor.setReclamoproveedor(reclamoproveedor);
            }
            Reclamoproveedor reclamoproveedor1 = detallereclamoproveedor.getReclamoproveedor1();
            if (reclamoproveedor1 != null) {
                reclamoproveedor1 = em.getReference(reclamoproveedor1.getClass(), reclamoproveedor1.getIdreclamo());
                detallereclamoproveedor.setReclamoproveedor1(reclamoproveedor1);
            }
            em.persist(detallereclamoproveedor);
            if (detallecompra != null) {
                detallecompra.getDetallereclamoproveedorSet().add(detallereclamoproveedor);
                detallecompra = em.merge(detallecompra);
            }
            if (detallecompra1 != null) {
                detallecompra1.getDetallereclamoproveedorSet().add(detallereclamoproveedor);
                detallecompra1 = em.merge(detallecompra1);
            }
            if (reclamoproveedor != null) {
                reclamoproveedor.getDetallereclamoproveedorSet().add(detallereclamoproveedor);
                reclamoproveedor = em.merge(reclamoproveedor);
            }
            if (reclamoproveedor1 != null) {
                reclamoproveedor1.getDetallereclamoproveedorSet().add(detallereclamoproveedor);
                reclamoproveedor1 = em.merge(reclamoproveedor1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallereclamoproveedor(detallereclamoproveedor.getDetallereclamoproveedorPK()) != null) {
                throw new PreexistingEntityException("Detallereclamoproveedor " + detallereclamoproveedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallereclamoproveedor detallereclamoproveedor) throws NonexistentEntityException, Exception {
        detallereclamoproveedor.getDetallereclamoproveedorPK().setIdreclamo(detallereclamoproveedor.getReclamoproveedor1().getIdreclamo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallereclamoproveedor persistentDetallereclamoproveedor = em.find(Detallereclamoproveedor.class, detallereclamoproveedor.getDetallereclamoproveedorPK());
            Detallecompra detallecompraOld = persistentDetallereclamoproveedor.getDetallecompra();
            Detallecompra detallecompraNew = detallereclamoproveedor.getDetallecompra();
            Detallecompra detallecompra1Old = persistentDetallereclamoproveedor.getDetallecompra1();
            Detallecompra detallecompra1New = detallereclamoproveedor.getDetallecompra1();
            Reclamoproveedor reclamoproveedorOld = persistentDetallereclamoproveedor.getReclamoproveedor();
            Reclamoproveedor reclamoproveedorNew = detallereclamoproveedor.getReclamoproveedor();
            Reclamoproveedor reclamoproveedor1Old = persistentDetallereclamoproveedor.getReclamoproveedor1();
            Reclamoproveedor reclamoproveedor1New = detallereclamoproveedor.getReclamoproveedor1();
            if (detallecompraNew != null) {
                detallecompraNew = em.getReference(detallecompraNew.getClass(), detallecompraNew.getDetallecompraPK());
                detallereclamoproveedor.setDetallecompra(detallecompraNew);
            }
            if (detallecompra1New != null) {
                detallecompra1New = em.getReference(detallecompra1New.getClass(), detallecompra1New.getDetallecompraPK());
                detallereclamoproveedor.setDetallecompra1(detallecompra1New);
            }
            if (reclamoproveedorNew != null) {
                reclamoproveedorNew = em.getReference(reclamoproveedorNew.getClass(), reclamoproveedorNew.getIdreclamo());
                detallereclamoproveedor.setReclamoproveedor(reclamoproveedorNew);
            }
            if (reclamoproveedor1New != null) {
                reclamoproveedor1New = em.getReference(reclamoproveedor1New.getClass(), reclamoproveedor1New.getIdreclamo());
                detallereclamoproveedor.setReclamoproveedor1(reclamoproveedor1New);
            }
            detallereclamoproveedor = em.merge(detallereclamoproveedor);
            if (detallecompraOld != null && !detallecompraOld.equals(detallecompraNew)) {
                detallecompraOld.getDetallereclamoproveedorSet().remove(detallereclamoproveedor);
                detallecompraOld = em.merge(detallecompraOld);
            }
            if (detallecompraNew != null && !detallecompraNew.equals(detallecompraOld)) {
                detallecompraNew.getDetallereclamoproveedorSet().add(detallereclamoproveedor);
                detallecompraNew = em.merge(detallecompraNew);
            }
            if (detallecompra1Old != null && !detallecompra1Old.equals(detallecompra1New)) {
                detallecompra1Old.getDetallereclamoproveedorSet().remove(detallereclamoproveedor);
                detallecompra1Old = em.merge(detallecompra1Old);
            }
            if (detallecompra1New != null && !detallecompra1New.equals(detallecompra1Old)) {
                detallecompra1New.getDetallereclamoproveedorSet().add(detallereclamoproveedor);
                detallecompra1New = em.merge(detallecompra1New);
            }
            if (reclamoproveedorOld != null && !reclamoproveedorOld.equals(reclamoproveedorNew)) {
                reclamoproveedorOld.getDetallereclamoproveedorSet().remove(detallereclamoproveedor);
                reclamoproveedorOld = em.merge(reclamoproveedorOld);
            }
            if (reclamoproveedorNew != null && !reclamoproveedorNew.equals(reclamoproveedorOld)) {
                reclamoproveedorNew.getDetallereclamoproveedorSet().add(detallereclamoproveedor);
                reclamoproveedorNew = em.merge(reclamoproveedorNew);
            }
            if (reclamoproveedor1Old != null && !reclamoproveedor1Old.equals(reclamoproveedor1New)) {
                reclamoproveedor1Old.getDetallereclamoproveedorSet().remove(detallereclamoproveedor);
                reclamoproveedor1Old = em.merge(reclamoproveedor1Old);
            }
            if (reclamoproveedor1New != null && !reclamoproveedor1New.equals(reclamoproveedor1Old)) {
                reclamoproveedor1New.getDetallereclamoproveedorSet().add(detallereclamoproveedor);
                reclamoproveedor1New = em.merge(reclamoproveedor1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetallereclamoproveedorPK id = detallereclamoproveedor.getDetallereclamoproveedorPK();
                if (findDetallereclamoproveedor(id) == null) {
                    throw new NonexistentEntityException("The detallereclamoproveedor with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetallereclamoproveedorPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallereclamoproveedor detallereclamoproveedor;
            try {
                detallereclamoproveedor = em.getReference(Detallereclamoproveedor.class, id);
                detallereclamoproveedor.getDetallereclamoproveedorPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallereclamoproveedor with id " + id + " no longer exists.", enfe);
            }
            Detallecompra detallecompra = detallereclamoproveedor.getDetallecompra();
            if (detallecompra != null) {
                detallecompra.getDetallereclamoproveedorSet().remove(detallereclamoproveedor);
                detallecompra = em.merge(detallecompra);
            }
            Detallecompra detallecompra1 = detallereclamoproveedor.getDetallecompra1();
            if (detallecompra1 != null) {
                detallecompra1.getDetallereclamoproveedorSet().remove(detallereclamoproveedor);
                detallecompra1 = em.merge(detallecompra1);
            }
            Reclamoproveedor reclamoproveedor = detallereclamoproveedor.getReclamoproveedor();
            if (reclamoproveedor != null) {
                reclamoproveedor.getDetallereclamoproveedorSet().remove(detallereclamoproveedor);
                reclamoproveedor = em.merge(reclamoproveedor);
            }
            Reclamoproveedor reclamoproveedor1 = detallereclamoproveedor.getReclamoproveedor1();
            if (reclamoproveedor1 != null) {
                reclamoproveedor1.getDetallereclamoproveedorSet().remove(detallereclamoproveedor);
                reclamoproveedor1 = em.merge(reclamoproveedor1);
            }
            em.remove(detallereclamoproveedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallereclamoproveedor> findDetallereclamoproveedorEntities() {
        return findDetallereclamoproveedorEntities(true, -1, -1);
    }

    public List<Detallereclamoproveedor> findDetallereclamoproveedorEntities(int maxResults, int firstResult) {
        return findDetallereclamoproveedorEntities(false, maxResults, firstResult);
    }

    private List<Detallereclamoproveedor> findDetallereclamoproveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallereclamoproveedor.class));
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

    public Detallereclamoproveedor findDetallereclamoproveedor(DetallereclamoproveedorPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallereclamoproveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallereclamoproveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallereclamoproveedor> rt = cq.from(Detallereclamoproveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
