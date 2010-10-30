/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Mpasignadaxpiezareal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Detallempasignada;

/**
 *
 * @author Nino
 */
public class MpasignadaxpiezarealJpaController {

    public MpasignadaxpiezarealJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mpasignadaxpiezareal mpasignadaxpiezareal) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallempasignada iddetallempasignada = mpasignadaxpiezareal.getIddetallempasignada();
            if (iddetallempasignada != null) {
                iddetallempasignada = em.getReference(iddetallempasignada.getClass(), iddetallempasignada.getId());
                mpasignadaxpiezareal.setIddetallempasignada(iddetallempasignada);
            }
            Detallempasignada iddetallempasignada1 = mpasignadaxpiezareal.getIddetallempasignada1();
            if (iddetallempasignada1 != null) {
                iddetallempasignada1 = em.getReference(iddetallempasignada1.getClass(), iddetallempasignada1.getId());
                mpasignadaxpiezareal.setIddetallempasignada1(iddetallempasignada1);
            }
            em.persist(mpasignadaxpiezareal);
            if (iddetallempasignada != null) {
                iddetallempasignada.getMpasignadaxpiezarealSet().add(mpasignadaxpiezareal);
                iddetallempasignada = em.merge(iddetallempasignada);
            }
            if (iddetallempasignada1 != null) {
                iddetallempasignada1.getMpasignadaxpiezarealSet().add(mpasignadaxpiezareal);
                iddetallempasignada1 = em.merge(iddetallempasignada1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMpasignadaxpiezareal(mpasignadaxpiezareal.getId()) != null) {
                throw new PreexistingEntityException("Mpasignadaxpiezareal " + mpasignadaxpiezareal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mpasignadaxpiezareal mpasignadaxpiezareal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mpasignadaxpiezareal persistentMpasignadaxpiezareal = em.find(Mpasignadaxpiezareal.class, mpasignadaxpiezareal.getId());
            Detallempasignada iddetallempasignadaOld = persistentMpasignadaxpiezareal.getIddetallempasignada();
            Detallempasignada iddetallempasignadaNew = mpasignadaxpiezareal.getIddetallempasignada();
            Detallempasignada iddetallempasignada1Old = persistentMpasignadaxpiezareal.getIddetallempasignada1();
            Detallempasignada iddetallempasignada1New = mpasignadaxpiezareal.getIddetallempasignada1();
            if (iddetallempasignadaNew != null) {
                iddetallempasignadaNew = em.getReference(iddetallempasignadaNew.getClass(), iddetallempasignadaNew.getId());
                mpasignadaxpiezareal.setIddetallempasignada(iddetallempasignadaNew);
            }
            if (iddetallempasignada1New != null) {
                iddetallempasignada1New = em.getReference(iddetallempasignada1New.getClass(), iddetallempasignada1New.getId());
                mpasignadaxpiezareal.setIddetallempasignada1(iddetallempasignada1New);
            }
            mpasignadaxpiezareal = em.merge(mpasignadaxpiezareal);
            if (iddetallempasignadaOld != null && !iddetallempasignadaOld.equals(iddetallempasignadaNew)) {
                iddetallempasignadaOld.getMpasignadaxpiezarealSet().remove(mpasignadaxpiezareal);
                iddetallempasignadaOld = em.merge(iddetallempasignadaOld);
            }
            if (iddetallempasignadaNew != null && !iddetallempasignadaNew.equals(iddetallempasignadaOld)) {
                iddetallempasignadaNew.getMpasignadaxpiezarealSet().add(mpasignadaxpiezareal);
                iddetallempasignadaNew = em.merge(iddetallempasignadaNew);
            }
            if (iddetallempasignada1Old != null && !iddetallempasignada1Old.equals(iddetallempasignada1New)) {
                iddetallempasignada1Old.getMpasignadaxpiezarealSet().remove(mpasignadaxpiezareal);
                iddetallempasignada1Old = em.merge(iddetallempasignada1Old);
            }
            if (iddetallempasignada1New != null && !iddetallempasignada1New.equals(iddetallempasignada1Old)) {
                iddetallempasignada1New.getMpasignadaxpiezarealSet().add(mpasignadaxpiezareal);
                iddetallempasignada1New = em.merge(iddetallempasignada1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = mpasignadaxpiezareal.getId();
                if (findMpasignadaxpiezareal(id) == null) {
                    throw new NonexistentEntityException("The mpasignadaxpiezareal with id " + id + " no longer exists.");
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
            Mpasignadaxpiezareal mpasignadaxpiezareal;
            try {
                mpasignadaxpiezareal = em.getReference(Mpasignadaxpiezareal.class, id);
                mpasignadaxpiezareal.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mpasignadaxpiezareal with id " + id + " no longer exists.", enfe);
            }
            Detallempasignada iddetallempasignada = mpasignadaxpiezareal.getIddetallempasignada();
            if (iddetallempasignada != null) {
                iddetallempasignada.getMpasignadaxpiezarealSet().remove(mpasignadaxpiezareal);
                iddetallempasignada = em.merge(iddetallempasignada);
            }
            Detallempasignada iddetallempasignada1 = mpasignadaxpiezareal.getIddetallempasignada1();
            if (iddetallempasignada1 != null) {
                iddetallempasignada1.getMpasignadaxpiezarealSet().remove(mpasignadaxpiezareal);
                iddetallempasignada1 = em.merge(iddetallempasignada1);
            }
            em.remove(mpasignadaxpiezareal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mpasignadaxpiezareal> findMpasignadaxpiezarealEntities() {
        return findMpasignadaxpiezarealEntities(true, -1, -1);
    }

    public List<Mpasignadaxpiezareal> findMpasignadaxpiezarealEntities(int maxResults, int firstResult) {
        return findMpasignadaxpiezarealEntities(false, maxResults, firstResult);
    }

    private List<Mpasignadaxpiezareal> findMpasignadaxpiezarealEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mpasignadaxpiezareal.class));
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

    public Mpasignadaxpiezareal findMpasignadaxpiezareal(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mpasignadaxpiezareal.class, id);
        } finally {
            em.close();
        }
    }

    public int getMpasignadaxpiezarealCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mpasignadaxpiezareal> rt = cq.from(Mpasignadaxpiezareal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
