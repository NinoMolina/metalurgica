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
import metalsoft.datos.jpa.entity.Detallereclamoempresametalurgica;
import metalsoft.datos.jpa.entity.DetallereclamoempresametalurgicaPK;
import metalsoft.datos.jpa.entity.Reclamoempresametalurgica;

/**
 *
 * @author Nino
 */
public class DetallereclamoempresametalurgicaJpaController implements Serializable {

    public DetallereclamoempresametalurgicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallereclamoempresametalurgica detallereclamoempresametalurgica) throws PreexistingEntityException, Exception {
        if (detallereclamoempresametalurgica.getDetallereclamoempresametalurgicaPK() == null) {
            detallereclamoempresametalurgica.setDetallereclamoempresametalurgicaPK(new DetallereclamoempresametalurgicaPK());
        }
        detallereclamoempresametalurgica.getDetallereclamoempresametalurgicaPK().setIdreclamo(detallereclamoempresametalurgica.getReclamoempresametalurgica().getIdreclamo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reclamoempresametalurgica reclamoempresametalurgica = detallereclamoempresametalurgica.getReclamoempresametalurgica();
            if (reclamoempresametalurgica != null) {
                reclamoempresametalurgica = em.getReference(reclamoempresametalurgica.getClass(), reclamoempresametalurgica.getIdreclamo());
                detallereclamoempresametalurgica.setReclamoempresametalurgica(reclamoempresametalurgica);
            }
            em.persist(detallereclamoempresametalurgica);
            if (reclamoempresametalurgica != null) {
                reclamoempresametalurgica.getDetallereclamoempresametalurgicaList().add(detallereclamoempresametalurgica);
                reclamoempresametalurgica = em.merge(reclamoempresametalurgica);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallereclamoempresametalurgica(detallereclamoempresametalurgica.getDetallereclamoempresametalurgicaPK()) != null) {
                throw new PreexistingEntityException("Detallereclamoempresametalurgica " + detallereclamoempresametalurgica + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallereclamoempresametalurgica detallereclamoempresametalurgica) throws NonexistentEntityException, Exception {
        detallereclamoempresametalurgica.getDetallereclamoempresametalurgicaPK().setIdreclamo(detallereclamoempresametalurgica.getReclamoempresametalurgica().getIdreclamo());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallereclamoempresametalurgica persistentDetallereclamoempresametalurgica = em.find(Detallereclamoempresametalurgica.class, detallereclamoempresametalurgica.getDetallereclamoempresametalurgicaPK());
            Reclamoempresametalurgica reclamoempresametalurgicaOld = persistentDetallereclamoempresametalurgica.getReclamoempresametalurgica();
            Reclamoempresametalurgica reclamoempresametalurgicaNew = detallereclamoempresametalurgica.getReclamoempresametalurgica();
            if (reclamoempresametalurgicaNew != null) {
                reclamoempresametalurgicaNew = em.getReference(reclamoempresametalurgicaNew.getClass(), reclamoempresametalurgicaNew.getIdreclamo());
                detallereclamoempresametalurgica.setReclamoempresametalurgica(reclamoempresametalurgicaNew);
            }
            detallereclamoempresametalurgica = em.merge(detallereclamoempresametalurgica);
            if (reclamoempresametalurgicaOld != null && !reclamoempresametalurgicaOld.equals(reclamoempresametalurgicaNew)) {
                reclamoempresametalurgicaOld.getDetallereclamoempresametalurgicaList().remove(detallereclamoempresametalurgica);
                reclamoempresametalurgicaOld = em.merge(reclamoempresametalurgicaOld);
            }
            if (reclamoempresametalurgicaNew != null && !reclamoempresametalurgicaNew.equals(reclamoempresametalurgicaOld)) {
                reclamoempresametalurgicaNew.getDetallereclamoempresametalurgicaList().add(detallereclamoempresametalurgica);
                reclamoempresametalurgicaNew = em.merge(reclamoempresametalurgicaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetallereclamoempresametalurgicaPK id = detallereclamoempresametalurgica.getDetallereclamoempresametalurgicaPK();
                if (findDetallereclamoempresametalurgica(id) == null) {
                    throw new NonexistentEntityException("The detallereclamoempresametalurgica with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetallereclamoempresametalurgicaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallereclamoempresametalurgica detallereclamoempresametalurgica;
            try {
                detallereclamoempresametalurgica = em.getReference(Detallereclamoempresametalurgica.class, id);
                detallereclamoempresametalurgica.getDetallereclamoempresametalurgicaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallereclamoempresametalurgica with id " + id + " no longer exists.", enfe);
            }
            Reclamoempresametalurgica reclamoempresametalurgica = detallereclamoempresametalurgica.getReclamoempresametalurgica();
            if (reclamoempresametalurgica != null) {
                reclamoempresametalurgica.getDetallereclamoempresametalurgicaList().remove(detallereclamoempresametalurgica);
                reclamoempresametalurgica = em.merge(reclamoempresametalurgica);
            }
            em.remove(detallereclamoempresametalurgica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallereclamoempresametalurgica> findDetallereclamoempresametalurgicaEntities() {
        return findDetallereclamoempresametalurgicaEntities(true, -1, -1);
    }

    public List<Detallereclamoempresametalurgica> findDetallereclamoempresametalurgicaEntities(int maxResults, int firstResult) {
        return findDetallereclamoempresametalurgicaEntities(false, maxResults, firstResult);
    }

    private List<Detallereclamoempresametalurgica> findDetallereclamoempresametalurgicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallereclamoempresametalurgica.class));
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

    public Detallereclamoempresametalurgica findDetallereclamoempresametalurgica(DetallereclamoempresametalurgicaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallereclamoempresametalurgica.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallereclamoempresametalurgicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallereclamoempresametalurgica> rt = cq.from(Detallereclamoempresametalurgica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
