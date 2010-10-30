/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detallerequerimientosmateriaprima;
import entity.DetallerequerimientosmateriaprimaPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Materiaprima;
import entity.Planrequerimientosmateriaprima;

/**
 *
 * @author Nino
 */
public class DetallerequerimientosmateriaprimaJpaController {

    public DetallerequerimientosmateriaprimaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallerequerimientosmateriaprima detallerequerimientosmateriaprima) throws PreexistingEntityException, Exception {
        if (detallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaPK() == null) {
            detallerequerimientosmateriaprima.setDetallerequerimientosmateriaprimaPK(new DetallerequerimientosmateriaprimaPK());
        }
        detallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaPK().setIdplanrequerimientosmateriaprima(detallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima1().getIdplanrequerimientosmateriaprima());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Materiaprima idmateriaprima = detallerequerimientosmateriaprima.getIdmateriaprima();
            if (idmateriaprima != null) {
                idmateriaprima = em.getReference(idmateriaprima.getClass(), idmateriaprima.getIdmateriaprima());
                detallerequerimientosmateriaprima.setIdmateriaprima(idmateriaprima);
            }
            Materiaprima idmateriaprima1 = detallerequerimientosmateriaprima.getIdmateriaprima1();
            if (idmateriaprima1 != null) {
                idmateriaprima1 = em.getReference(idmateriaprima1.getClass(), idmateriaprima1.getIdmateriaprima());
                detallerequerimientosmateriaprima.setIdmateriaprima1(idmateriaprima1);
            }
            Planrequerimientosmateriaprima planrequerimientosmateriaprima = detallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima();
            if (planrequerimientosmateriaprima != null) {
                planrequerimientosmateriaprima = em.getReference(planrequerimientosmateriaprima.getClass(), planrequerimientosmateriaprima.getIdplanrequerimientosmateriaprima());
                detallerequerimientosmateriaprima.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprima);
            }
            Planrequerimientosmateriaprima planrequerimientosmateriaprima1 = detallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima1();
            if (planrequerimientosmateriaprima1 != null) {
                planrequerimientosmateriaprima1 = em.getReference(planrequerimientosmateriaprima1.getClass(), planrequerimientosmateriaprima1.getIdplanrequerimientosmateriaprima());
                detallerequerimientosmateriaprima.setPlanrequerimientosmateriaprima1(planrequerimientosmateriaprima1);
            }
            em.persist(detallerequerimientosmateriaprima);
            if (idmateriaprima != null) {
                idmateriaprima.getDetallerequerimientosmateriaprimaSet().add(detallerequerimientosmateriaprima);
                idmateriaprima = em.merge(idmateriaprima);
            }
            if (idmateriaprima1 != null) {
                idmateriaprima1.getDetallerequerimientosmateriaprimaSet().add(detallerequerimientosmateriaprima);
                idmateriaprima1 = em.merge(idmateriaprima1);
            }
            if (planrequerimientosmateriaprima != null) {
                planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet().add(detallerequerimientosmateriaprima);
                planrequerimientosmateriaprima = em.merge(planrequerimientosmateriaprima);
            }
            if (planrequerimientosmateriaprima1 != null) {
                planrequerimientosmateriaprima1.getDetallerequerimientosmateriaprimaSet().add(detallerequerimientosmateriaprima);
                planrequerimientosmateriaprima1 = em.merge(planrequerimientosmateriaprima1);
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
        detallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaPK().setIdplanrequerimientosmateriaprima(detallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima1().getIdplanrequerimientosmateriaprima());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallerequerimientosmateriaprima persistentDetallerequerimientosmateriaprima = em.find(Detallerequerimientosmateriaprima.class, detallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaPK());
            Materiaprima idmateriaprimaOld = persistentDetallerequerimientosmateriaprima.getIdmateriaprima();
            Materiaprima idmateriaprimaNew = detallerequerimientosmateriaprima.getIdmateriaprima();
            Materiaprima idmateriaprima1Old = persistentDetallerequerimientosmateriaprima.getIdmateriaprima1();
            Materiaprima idmateriaprima1New = detallerequerimientosmateriaprima.getIdmateriaprima1();
            Planrequerimientosmateriaprima planrequerimientosmateriaprimaOld = persistentDetallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima();
            Planrequerimientosmateriaprima planrequerimientosmateriaprimaNew = detallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima();
            Planrequerimientosmateriaprima planrequerimientosmateriaprima1Old = persistentDetallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima1();
            Planrequerimientosmateriaprima planrequerimientosmateriaprima1New = detallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima1();
            if (idmateriaprimaNew != null) {
                idmateriaprimaNew = em.getReference(idmateriaprimaNew.getClass(), idmateriaprimaNew.getIdmateriaprima());
                detallerequerimientosmateriaprima.setIdmateriaprima(idmateriaprimaNew);
            }
            if (idmateriaprima1New != null) {
                idmateriaprima1New = em.getReference(idmateriaprima1New.getClass(), idmateriaprima1New.getIdmateriaprima());
                detallerequerimientosmateriaprima.setIdmateriaprima1(idmateriaprima1New);
            }
            if (planrequerimientosmateriaprimaNew != null) {
                planrequerimientosmateriaprimaNew = em.getReference(planrequerimientosmateriaprimaNew.getClass(), planrequerimientosmateriaprimaNew.getIdplanrequerimientosmateriaprima());
                detallerequerimientosmateriaprima.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprimaNew);
            }
            if (planrequerimientosmateriaprima1New != null) {
                planrequerimientosmateriaprima1New = em.getReference(planrequerimientosmateriaprima1New.getClass(), planrequerimientosmateriaprima1New.getIdplanrequerimientosmateriaprima());
                detallerequerimientosmateriaprima.setPlanrequerimientosmateriaprima1(planrequerimientosmateriaprima1New);
            }
            detallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprima);
            if (idmateriaprimaOld != null && !idmateriaprimaOld.equals(idmateriaprimaNew)) {
                idmateriaprimaOld.getDetallerequerimientosmateriaprimaSet().remove(detallerequerimientosmateriaprima);
                idmateriaprimaOld = em.merge(idmateriaprimaOld);
            }
            if (idmateriaprimaNew != null && !idmateriaprimaNew.equals(idmateriaprimaOld)) {
                idmateriaprimaNew.getDetallerequerimientosmateriaprimaSet().add(detallerequerimientosmateriaprima);
                idmateriaprimaNew = em.merge(idmateriaprimaNew);
            }
            if (idmateriaprima1Old != null && !idmateriaprima1Old.equals(idmateriaprima1New)) {
                idmateriaprima1Old.getDetallerequerimientosmateriaprimaSet().remove(detallerequerimientosmateriaprima);
                idmateriaprima1Old = em.merge(idmateriaprima1Old);
            }
            if (idmateriaprima1New != null && !idmateriaprima1New.equals(idmateriaprima1Old)) {
                idmateriaprima1New.getDetallerequerimientosmateriaprimaSet().add(detallerequerimientosmateriaprima);
                idmateriaprima1New = em.merge(idmateriaprima1New);
            }
            if (planrequerimientosmateriaprimaOld != null && !planrequerimientosmateriaprimaOld.equals(planrequerimientosmateriaprimaNew)) {
                planrequerimientosmateriaprimaOld.getDetallerequerimientosmateriaprimaSet().remove(detallerequerimientosmateriaprima);
                planrequerimientosmateriaprimaOld = em.merge(planrequerimientosmateriaprimaOld);
            }
            if (planrequerimientosmateriaprimaNew != null && !planrequerimientosmateriaprimaNew.equals(planrequerimientosmateriaprimaOld)) {
                planrequerimientosmateriaprimaNew.getDetallerequerimientosmateriaprimaSet().add(detallerequerimientosmateriaprima);
                planrequerimientosmateriaprimaNew = em.merge(planrequerimientosmateriaprimaNew);
            }
            if (planrequerimientosmateriaprima1Old != null && !planrequerimientosmateriaprima1Old.equals(planrequerimientosmateriaprima1New)) {
                planrequerimientosmateriaprima1Old.getDetallerequerimientosmateriaprimaSet().remove(detallerequerimientosmateriaprima);
                planrequerimientosmateriaprima1Old = em.merge(planrequerimientosmateriaprima1Old);
            }
            if (planrequerimientosmateriaprima1New != null && !planrequerimientosmateriaprima1New.equals(planrequerimientosmateriaprima1Old)) {
                planrequerimientosmateriaprima1New.getDetallerequerimientosmateriaprimaSet().add(detallerequerimientosmateriaprima);
                planrequerimientosmateriaprima1New = em.merge(planrequerimientosmateriaprima1New);
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
            Materiaprima idmateriaprima = detallerequerimientosmateriaprima.getIdmateriaprima();
            if (idmateriaprima != null) {
                idmateriaprima.getDetallerequerimientosmateriaprimaSet().remove(detallerequerimientosmateriaprima);
                idmateriaprima = em.merge(idmateriaprima);
            }
            Materiaprima idmateriaprima1 = detallerequerimientosmateriaprima.getIdmateriaprima1();
            if (idmateriaprima1 != null) {
                idmateriaprima1.getDetallerequerimientosmateriaprimaSet().remove(detallerequerimientosmateriaprima);
                idmateriaprima1 = em.merge(idmateriaprima1);
            }
            Planrequerimientosmateriaprima planrequerimientosmateriaprima = detallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima();
            if (planrequerimientosmateriaprima != null) {
                planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet().remove(detallerequerimientosmateriaprima);
                planrequerimientosmateriaprima = em.merge(planrequerimientosmateriaprima);
            }
            Planrequerimientosmateriaprima planrequerimientosmateriaprima1 = detallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima1();
            if (planrequerimientosmateriaprima1 != null) {
                planrequerimientosmateriaprima1.getDetallerequerimientosmateriaprimaSet().remove(detallerequerimientosmateriaprima);
                planrequerimientosmateriaprima1 = em.merge(planrequerimientosmateriaprima1);
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
