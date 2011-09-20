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
import metalsoft.datos.jpa.entity.Detallereclamoproveedor;
import metalsoft.datos.jpa.entity.DetallereclamoproveedorPK;
import metalsoft.datos.jpa.entity.Reclamoproveedor;
import metalsoft.datos.jpa.entity.Detallecompra;

/**
 *
 * @author Nino
 */
public class DetallereclamoproveedorJpaController implements Serializable {

    public DetallereclamoproveedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallereclamoproveedor detallereclamoproveedor) throws PreexistingEntityException, Exception {
        if (detallereclamoproveedor.getDetallereclamoproveedorPK() == null) {
            detallereclamoproveedor.setDetallereclamoproveedorPK(new DetallereclamoproveedorPK());
        }
        detallereclamoproveedor.getDetallereclamoproveedorPK().setIdreclamo(detallereclamoproveedor.getReclamoproveedor().getIdreclamo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reclamoproveedor reclamoproveedor = detallereclamoproveedor.getReclamoproveedor();
            if (reclamoproveedor != null) {
                reclamoproveedor = em.getReference(reclamoproveedor.getClass(), reclamoproveedor.getIdreclamo());
                detallereclamoproveedor.setReclamoproveedor(reclamoproveedor);
            }
            Detallecompra detallecompra = detallereclamoproveedor.getDetallecompra();
            if (detallecompra != null) {
                detallecompra = em.getReference(detallecompra.getClass(), detallecompra.getDetallecompraPK());
                detallereclamoproveedor.setDetallecompra(detallecompra);
            }
            em.persist(detallereclamoproveedor);
            if (reclamoproveedor != null) {
                reclamoproveedor.getDetallereclamoproveedorList().add(detallereclamoproveedor);
                reclamoproveedor = em.merge(reclamoproveedor);
            }
            if (detallecompra != null) {
                detallecompra.getDetallereclamoproveedorList().add(detallereclamoproveedor);
                detallecompra = em.merge(detallecompra);
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
        detallereclamoproveedor.getDetallereclamoproveedorPK().setIdreclamo(detallereclamoproveedor.getReclamoproveedor().getIdreclamo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallereclamoproveedor persistentDetallereclamoproveedor = em.find(Detallereclamoproveedor.class, detallereclamoproveedor.getDetallereclamoproveedorPK());
            Reclamoproveedor reclamoproveedorOld = persistentDetallereclamoproveedor.getReclamoproveedor();
            Reclamoproveedor reclamoproveedorNew = detallereclamoproveedor.getReclamoproveedor();
            Detallecompra detallecompraOld = persistentDetallereclamoproveedor.getDetallecompra();
            Detallecompra detallecompraNew = detallereclamoproveedor.getDetallecompra();
            if (reclamoproveedorNew != null) {
                reclamoproveedorNew = em.getReference(reclamoproveedorNew.getClass(), reclamoproveedorNew.getIdreclamo());
                detallereclamoproveedor.setReclamoproveedor(reclamoproveedorNew);
            }
            if (detallecompraNew != null) {
                detallecompraNew = em.getReference(detallecompraNew.getClass(), detallecompraNew.getDetallecompraPK());
                detallereclamoproveedor.setDetallecompra(detallecompraNew);
            }
            detallereclamoproveedor = em.merge(detallereclamoproveedor);
            if (reclamoproveedorOld != null && !reclamoproveedorOld.equals(reclamoproveedorNew)) {
                reclamoproveedorOld.getDetallereclamoproveedorList().remove(detallereclamoproveedor);
                reclamoproveedorOld = em.merge(reclamoproveedorOld);
            }
            if (reclamoproveedorNew != null && !reclamoproveedorNew.equals(reclamoproveedorOld)) {
                reclamoproveedorNew.getDetallereclamoproveedorList().add(detallereclamoproveedor);
                reclamoproveedorNew = em.merge(reclamoproveedorNew);
            }
            if (detallecompraOld != null && !detallecompraOld.equals(detallecompraNew)) {
                detallecompraOld.getDetallereclamoproveedorList().remove(detallereclamoproveedor);
                detallecompraOld = em.merge(detallecompraOld);
            }
            if (detallecompraNew != null && !detallecompraNew.equals(detallecompraOld)) {
                detallecompraNew.getDetallereclamoproveedorList().add(detallereclamoproveedor);
                detallecompraNew = em.merge(detallecompraNew);
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
            Reclamoproveedor reclamoproveedor = detallereclamoproveedor.getReclamoproveedor();
            if (reclamoproveedor != null) {
                reclamoproveedor.getDetallereclamoproveedorList().remove(detallereclamoproveedor);
                reclamoproveedor = em.merge(reclamoproveedor);
            }
            Detallecompra detallecompra = detallereclamoproveedor.getDetallecompra();
            if (detallecompra != null) {
                detallecompra.getDetallereclamoproveedorList().remove(detallereclamoproveedor);
                detallecompra = em.merge(detallecompra);
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
