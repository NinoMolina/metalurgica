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
import metalsoft.datos.jpa.entity.Prueba;

/**
 *
 * @author Nino
 */
public class PruebaJpaController implements Serializable {

    public PruebaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Prueba prueba) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(prueba);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrueba(prueba.getId()) != null) {
                throw new PreexistingEntityException("Prueba " + prueba + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Prueba prueba) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            prueba = em.merge(prueba);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = prueba.getId();
                if (findPrueba(id) == null) {
                    throw new NonexistentEntityException("The prueba with id " + id + " no longer exists.");
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
            Prueba prueba;
            try {
                prueba = em.getReference(Prueba.class, id);
                prueba.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The prueba with id " + id + " no longer exists.", enfe);
            }
            em.remove(prueba);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Prueba> findPruebaEntities() {
        return findPruebaEntities(true, -1, -1);
    }

    public List<Prueba> findPruebaEntities(int maxResults, int firstResult) {
        return findPruebaEntities(false, maxResults, firstResult);
    }

    private List<Prueba> findPruebaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Prueba.class));
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

    public Prueba findPrueba(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Prueba.class, id);
        } finally {
            em.close();
        }
    }

    public int getPruebaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Prueba> rt = cq.from(Prueba.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
