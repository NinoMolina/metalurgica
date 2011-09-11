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
import metalsoft.datos.jpa.entity.Detallerequerimientosmateriaprima;
import metalsoft.datos.jpa.entity.DetallerequerimientosmateriaprimaPK;
import metalsoft.datos.jpa.entity.Planrequerimientosmateriaprima;
import metalsoft.datos.jpa.entity.Materiaprima;

/**
 *
 * @author Nino
 */
public class DetallerequerimientosmateriaprimaJpaController implements Serializable {

    public DetallerequerimientosmateriaprimaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallerequerimientosmateriaprima detallerequerimientosmateriaprima) throws PreexistingEntityException, Exception {
        if (detallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaPK() == null) {
            detallerequerimientosmateriaprima.setDetallerequerimientosmateriaprimaPK(new DetallerequerimientosmateriaprimaPK());
        }
        detallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaPK().setIdplanrequerimientosmateriaprima(detallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima().getIdplanrequerimientosmateriaprima());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planrequerimientosmateriaprima planrequerimientosmateriaprima = detallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima();
            if (planrequerimientosmateriaprima != null) {
                planrequerimientosmateriaprima = em.getReference(planrequerimientosmateriaprima.getClass(), planrequerimientosmateriaprima.getIdplanrequerimientosmateriaprima());
                detallerequerimientosmateriaprima.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprima);
            }
            Materiaprima idmateriaprima = detallerequerimientosmateriaprima.getIdmateriaprima();
            if (idmateriaprima != null) {
                idmateriaprima = em.getReference(idmateriaprima.getClass(), idmateriaprima.getIdmateriaprima());
                detallerequerimientosmateriaprima.setIdmateriaprima(idmateriaprima);
            }
            em.persist(detallerequerimientosmateriaprima);
            if (planrequerimientosmateriaprima != null) {
                planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaList().add(detallerequerimientosmateriaprima);
                planrequerimientosmateriaprima = em.merge(planrequerimientosmateriaprima);
            }
            if (idmateriaprima != null) {
                idmateriaprima.getDetallerequerimientosmateriaprimaList().add(detallerequerimientosmateriaprima);
                idmateriaprima = em.merge(idmateriaprima);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallerequerimientosmateriaprima(detallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaPK()) != null) {
                throw new PreexistingEntityException("Detallerequerimientosmateriaprima " + detallerequerimientosmateriaprima + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallerequerimientosmateriaprima detallerequerimientosmateriaprima) throws NonexistentEntityException, Exception {
        detallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaPK().setIdplanrequerimientosmateriaprima(detallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima().getIdplanrequerimientosmateriaprima());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallerequerimientosmateriaprima persistentDetallerequerimientosmateriaprima = em.find(Detallerequerimientosmateriaprima.class, detallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaPK());
            Planrequerimientosmateriaprima planrequerimientosmateriaprimaOld = persistentDetallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima();
            Planrequerimientosmateriaprima planrequerimientosmateriaprimaNew = detallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima();
            Materiaprima idmateriaprimaOld = persistentDetallerequerimientosmateriaprima.getIdmateriaprima();
            Materiaprima idmateriaprimaNew = detallerequerimientosmateriaprima.getIdmateriaprima();
            if (planrequerimientosmateriaprimaNew != null) {
                planrequerimientosmateriaprimaNew = em.getReference(planrequerimientosmateriaprimaNew.getClass(), planrequerimientosmateriaprimaNew.getIdplanrequerimientosmateriaprima());
                detallerequerimientosmateriaprima.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprimaNew);
            }
            if (idmateriaprimaNew != null) {
                idmateriaprimaNew = em.getReference(idmateriaprimaNew.getClass(), idmateriaprimaNew.getIdmateriaprima());
                detallerequerimientosmateriaprima.setIdmateriaprima(idmateriaprimaNew);
            }
            detallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprima);
            if (planrequerimientosmateriaprimaOld != null && !planrequerimientosmateriaprimaOld.equals(planrequerimientosmateriaprimaNew)) {
                planrequerimientosmateriaprimaOld.getDetallerequerimientosmateriaprimaList().remove(detallerequerimientosmateriaprima);
                planrequerimientosmateriaprimaOld = em.merge(planrequerimientosmateriaprimaOld);
            }
            if (planrequerimientosmateriaprimaNew != null && !planrequerimientosmateriaprimaNew.equals(planrequerimientosmateriaprimaOld)) {
                planrequerimientosmateriaprimaNew.getDetallerequerimientosmateriaprimaList().add(detallerequerimientosmateriaprima);
                planrequerimientosmateriaprimaNew = em.merge(planrequerimientosmateriaprimaNew);
            }
            if (idmateriaprimaOld != null && !idmateriaprimaOld.equals(idmateriaprimaNew)) {
                idmateriaprimaOld.getDetallerequerimientosmateriaprimaList().remove(detallerequerimientosmateriaprima);
                idmateriaprimaOld = em.merge(idmateriaprimaOld);
            }
            if (idmateriaprimaNew != null && !idmateriaprimaNew.equals(idmateriaprimaOld)) {
                idmateriaprimaNew.getDetallerequerimientosmateriaprimaList().add(detallerequerimientosmateriaprima);
                idmateriaprimaNew = em.merge(idmateriaprimaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetallerequerimientosmateriaprimaPK id = detallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaPK();
                if (findDetallerequerimientosmateriaprima(id) == null) {
                    throw new NonexistentEntityException("The detallerequerimientosmateriaprima with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetallerequerimientosmateriaprimaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallerequerimientosmateriaprima detallerequerimientosmateriaprima;
            try {
                detallerequerimientosmateriaprima = em.getReference(Detallerequerimientosmateriaprima.class, id);
                detallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallerequerimientosmateriaprima with id " + id + " no longer exists.", enfe);
            }
            Planrequerimientosmateriaprima planrequerimientosmateriaprima = detallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima();
            if (planrequerimientosmateriaprima != null) {
                planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaList().remove(detallerequerimientosmateriaprima);
                planrequerimientosmateriaprima = em.merge(planrequerimientosmateriaprima);
            }
            Materiaprima idmateriaprima = detallerequerimientosmateriaprima.getIdmateriaprima();
            if (idmateriaprima != null) {
                idmateriaprima.getDetallerequerimientosmateriaprimaList().remove(detallerequerimientosmateriaprima);
                idmateriaprima = em.merge(idmateriaprima);
            }
            em.remove(detallerequerimientosmateriaprima);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallerequerimientosmateriaprima> findDetallerequerimientosmateriaprimaEntities() {
        return findDetallerequerimientosmateriaprimaEntities(true, -1, -1);
    }

    public List<Detallerequerimientosmateriaprima> findDetallerequerimientosmateriaprimaEntities(int maxResults, int firstResult) {
        return findDetallerequerimientosmateriaprimaEntities(false, maxResults, firstResult);
    }

    private List<Detallerequerimientosmateriaprima> findDetallerequerimientosmateriaprimaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallerequerimientosmateriaprima.class));
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

    public Detallerequerimientosmateriaprima findDetallerequerimientosmateriaprima(DetallerequerimientosmateriaprimaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallerequerimientosmateriaprima.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallerequerimientosmateriaprimaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallerequerimientosmateriaprima> rt = cq.from(Detallerequerimientosmateriaprima.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
