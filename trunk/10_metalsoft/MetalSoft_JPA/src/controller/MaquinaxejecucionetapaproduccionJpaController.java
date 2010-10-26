/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Maquinaxejecucionetapaproduccion;
import entity.MaquinaxejecucionetapaproduccionPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Ejecucionetapaproduccion;

/**
 *
 * @author Nino
 */
public class MaquinaxejecucionetapaproduccionJpaController {

    public MaquinaxejecucionetapaproduccionJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Maquinaxejecucionetapaproduccion maquinaxejecucionetapaproduccion) throws PreexistingEntityException, Exception {
        if (maquinaxejecucionetapaproduccion.getMaquinaxejecucionetapaproduccionPK() == null) {
            maquinaxejecucionetapaproduccion.setMaquinaxejecucionetapaproduccionPK(new MaquinaxejecucionetapaproduccionPK());
        }
        maquinaxejecucionetapaproduccion.getMaquinaxejecucionetapaproduccionPK().setIdejecucionetapaproduccion(maquinaxejecucionetapaproduccion.getEjecucionetapaproduccion().getEjecucionetapaproduccionPK().getIdejecucion());
        maquinaxejecucionetapaproduccion.getMaquinaxejecucionetapaproduccionPK().setIdetapaproduccion(maquinaxejecucionetapaproduccion.getEjecucionetapaproduccion().getEjecucionetapaproduccionPK().getIdetapaproduccion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionetapaproduccion ejecucionetapaproduccion = maquinaxejecucionetapaproduccion.getEjecucionetapaproduccion();
            if (ejecucionetapaproduccion != null) {
                ejecucionetapaproduccion = em.getReference(ejecucionetapaproduccion.getClass(), ejecucionetapaproduccion.getEjecucionetapaproduccionPK());
                maquinaxejecucionetapaproduccion.setEjecucionetapaproduccion(ejecucionetapaproduccion);
            }
            em.persist(maquinaxejecucionetapaproduccion);
            if (ejecucionetapaproduccion != null) {
                ejecucionetapaproduccion.getMaquinaxejecucionetapaproduccionSet().add(maquinaxejecucionetapaproduccion);
                ejecucionetapaproduccion = em.merge(ejecucionetapaproduccion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMaquinaxejecucionetapaproduccion(maquinaxejecucionetapaproduccion.getMaquinaxejecucionetapaproduccionPK()) != null) {
                throw new PreexistingEntityException("Maquinaxejecucionetapaproduccion " + maquinaxejecucionetapaproduccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Maquinaxejecucionetapaproduccion maquinaxejecucionetapaproduccion) throws NonexistentEntityException, Exception {
        maquinaxejecucionetapaproduccion.getMaquinaxejecucionetapaproduccionPK().setIdejecucionetapaproduccion(maquinaxejecucionetapaproduccion.getEjecucionetapaproduccion().getEjecucionetapaproduccionPK().getIdejecucion());
        maquinaxejecucionetapaproduccion.getMaquinaxejecucionetapaproduccionPK().setIdetapaproduccion(maquinaxejecucionetapaproduccion.getEjecucionetapaproduccion().getEjecucionetapaproduccionPK().getIdetapaproduccion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Maquinaxejecucionetapaproduccion persistentMaquinaxejecucionetapaproduccion = em.find(Maquinaxejecucionetapaproduccion.class, maquinaxejecucionetapaproduccion.getMaquinaxejecucionetapaproduccionPK());
            Ejecucionetapaproduccion ejecucionetapaproduccionOld = persistentMaquinaxejecucionetapaproduccion.getEjecucionetapaproduccion();
            Ejecucionetapaproduccion ejecucionetapaproduccionNew = maquinaxejecucionetapaproduccion.getEjecucionetapaproduccion();
            if (ejecucionetapaproduccionNew != null) {
                ejecucionetapaproduccionNew = em.getReference(ejecucionetapaproduccionNew.getClass(), ejecucionetapaproduccionNew.getEjecucionetapaproduccionPK());
                maquinaxejecucionetapaproduccion.setEjecucionetapaproduccion(ejecucionetapaproduccionNew);
            }
            maquinaxejecucionetapaproduccion = em.merge(maquinaxejecucionetapaproduccion);
            if (ejecucionetapaproduccionOld != null && !ejecucionetapaproduccionOld.equals(ejecucionetapaproduccionNew)) {
                ejecucionetapaproduccionOld.getMaquinaxejecucionetapaproduccionSet().remove(maquinaxejecucionetapaproduccion);
                ejecucionetapaproduccionOld = em.merge(ejecucionetapaproduccionOld);
            }
            if (ejecucionetapaproduccionNew != null && !ejecucionetapaproduccionNew.equals(ejecucionetapaproduccionOld)) {
                ejecucionetapaproduccionNew.getMaquinaxejecucionetapaproduccionSet().add(maquinaxejecucionetapaproduccion);
                ejecucionetapaproduccionNew = em.merge(ejecucionetapaproduccionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MaquinaxejecucionetapaproduccionPK id = maquinaxejecucionetapaproduccion.getMaquinaxejecucionetapaproduccionPK();
                if (findMaquinaxejecucionetapaproduccion(id) == null) {
                    throw new NonexistentEntityException("The maquinaxejecucionetapaproduccion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MaquinaxejecucionetapaproduccionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Maquinaxejecucionetapaproduccion maquinaxejecucionetapaproduccion;
            try {
                maquinaxejecucionetapaproduccion = em.getReference(Maquinaxejecucionetapaproduccion.class, id);
                maquinaxejecucionetapaproduccion.getMaquinaxejecucionetapaproduccionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The maquinaxejecucionetapaproduccion with id " + id + " no longer exists.", enfe);
            }
            Ejecucionetapaproduccion ejecucionetapaproduccion = maquinaxejecucionetapaproduccion.getEjecucionetapaproduccion();
            if (ejecucionetapaproduccion != null) {
                ejecucionetapaproduccion.getMaquinaxejecucionetapaproduccionSet().remove(maquinaxejecucionetapaproduccion);
                ejecucionetapaproduccion = em.merge(ejecucionetapaproduccion);
            }
            em.remove(maquinaxejecucionetapaproduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Maquinaxejecucionetapaproduccion> findMaquinaxejecucionetapaproduccionEntities() {
        return findMaquinaxejecucionetapaproduccionEntities(true, -1, -1);
    }

    public List<Maquinaxejecucionetapaproduccion> findMaquinaxejecucionetapaproduccionEntities(int maxResults, int firstResult) {
        return findMaquinaxejecucionetapaproduccionEntities(false, maxResults, firstResult);
    }

    private List<Maquinaxejecucionetapaproduccion> findMaquinaxejecucionetapaproduccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Maquinaxejecucionetapaproduccion.class));
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

    public Maquinaxejecucionetapaproduccion findMaquinaxejecucionetapaproduccion(MaquinaxejecucionetapaproduccionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Maquinaxejecucionetapaproduccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaquinaxejecucionetapaproduccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Maquinaxejecucionetapaproduccion> rt = cq.from(Maquinaxejecucionetapaproduccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
