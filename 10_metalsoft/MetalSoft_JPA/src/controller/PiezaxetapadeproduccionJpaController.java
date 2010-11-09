/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Piezaxetapadeproduccion;
import entity.PiezaxetapadeproduccionPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Etapadeproduccion;

/**
 *
 * @author Nino
 */
public class PiezaxetapadeproduccionJpaController {

    public PiezaxetapadeproduccionJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Piezaxetapadeproduccion piezaxetapadeproduccion) throws PreexistingEntityException, Exception {
        if (piezaxetapadeproduccion.getPiezaxetapadeproduccionPK() == null) {
            piezaxetapadeproduccion.setPiezaxetapadeproduccionPK(new PiezaxetapadeproduccionPK());
        }
        piezaxetapadeproduccion.getPiezaxetapadeproduccionPK().setIdetapaproduccion(piezaxetapadeproduccion.getEtapadeproduccion1().getIdetapaproduccion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Etapadeproduccion etapadeproduccion = piezaxetapadeproduccion.getEtapadeproduccion();
            if (etapadeproduccion != null) {
                etapadeproduccion = em.getReference(etapadeproduccion.getClass(), etapadeproduccion.getIdetapaproduccion());
                piezaxetapadeproduccion.setEtapadeproduccion(etapadeproduccion);
            }
            Etapadeproduccion etapadeproduccion1 = piezaxetapadeproduccion.getEtapadeproduccion1();
            if (etapadeproduccion1 != null) {
                etapadeproduccion1 = em.getReference(etapadeproduccion1.getClass(), etapadeproduccion1.getIdetapaproduccion());
                piezaxetapadeproduccion.setEtapadeproduccion1(etapadeproduccion1);
            }
            em.persist(piezaxetapadeproduccion);
            if (etapadeproduccion != null) {
                etapadeproduccion.getPiezaxetapadeproduccionSet().add(piezaxetapadeproduccion);
                etapadeproduccion = em.merge(etapadeproduccion);
            }
            if (etapadeproduccion1 != null) {
                etapadeproduccion1.getPiezaxetapadeproduccionSet().add(piezaxetapadeproduccion);
                etapadeproduccion1 = em.merge(etapadeproduccion1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPiezaxetapadeproduccion(piezaxetapadeproduccion.getPiezaxetapadeproduccionPK()) != null) {
                throw new PreexistingEntityException("Piezaxetapadeproduccion " + piezaxetapadeproduccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Piezaxetapadeproduccion piezaxetapadeproduccion) throws NonexistentEntityException, Exception {
        piezaxetapadeproduccion.getPiezaxetapadeproduccionPK().setIdetapaproduccion(piezaxetapadeproduccion.getEtapadeproduccion1().getIdetapaproduccion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Piezaxetapadeproduccion persistentPiezaxetapadeproduccion = em.find(Piezaxetapadeproduccion.class, piezaxetapadeproduccion.getPiezaxetapadeproduccionPK());
            Etapadeproduccion etapadeproduccionOld = persistentPiezaxetapadeproduccion.getEtapadeproduccion();
            Etapadeproduccion etapadeproduccionNew = piezaxetapadeproduccion.getEtapadeproduccion();
            Etapadeproduccion etapadeproduccion1Old = persistentPiezaxetapadeproduccion.getEtapadeproduccion1();
            Etapadeproduccion etapadeproduccion1New = piezaxetapadeproduccion.getEtapadeproduccion1();
            if (etapadeproduccionNew != null) {
                etapadeproduccionNew = em.getReference(etapadeproduccionNew.getClass(), etapadeproduccionNew.getIdetapaproduccion());
                piezaxetapadeproduccion.setEtapadeproduccion(etapadeproduccionNew);
            }
            if (etapadeproduccion1New != null) {
                etapadeproduccion1New = em.getReference(etapadeproduccion1New.getClass(), etapadeproduccion1New.getIdetapaproduccion());
                piezaxetapadeproduccion.setEtapadeproduccion1(etapadeproduccion1New);
            }
            piezaxetapadeproduccion = em.merge(piezaxetapadeproduccion);
            if (etapadeproduccionOld != null && !etapadeproduccionOld.equals(etapadeproduccionNew)) {
                etapadeproduccionOld.getPiezaxetapadeproduccionSet().remove(piezaxetapadeproduccion);
                etapadeproduccionOld = em.merge(etapadeproduccionOld);
            }
            if (etapadeproduccionNew != null && !etapadeproduccionNew.equals(etapadeproduccionOld)) {
                etapadeproduccionNew.getPiezaxetapadeproduccionSet().add(piezaxetapadeproduccion);
                etapadeproduccionNew = em.merge(etapadeproduccionNew);
            }
            if (etapadeproduccion1Old != null && !etapadeproduccion1Old.equals(etapadeproduccion1New)) {
                etapadeproduccion1Old.getPiezaxetapadeproduccionSet().remove(piezaxetapadeproduccion);
                etapadeproduccion1Old = em.merge(etapadeproduccion1Old);
            }
            if (etapadeproduccion1New != null && !etapadeproduccion1New.equals(etapadeproduccion1Old)) {
                etapadeproduccion1New.getPiezaxetapadeproduccionSet().add(piezaxetapadeproduccion);
                etapadeproduccion1New = em.merge(etapadeproduccion1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                PiezaxetapadeproduccionPK id = piezaxetapadeproduccion.getPiezaxetapadeproduccionPK();
                if (findPiezaxetapadeproduccion(id) == null) {
                    throw new NonexistentEntityException("The piezaxetapadeproduccion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(PiezaxetapadeproduccionPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Piezaxetapadeproduccion piezaxetapadeproduccion;
            try {
                piezaxetapadeproduccion = em.getReference(Piezaxetapadeproduccion.class, id);
                piezaxetapadeproduccion.getPiezaxetapadeproduccionPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The piezaxetapadeproduccion with id " + id + " no longer exists.", enfe);
            }
            Etapadeproduccion etapadeproduccion = piezaxetapadeproduccion.getEtapadeproduccion();
            if (etapadeproduccion != null) {
                etapadeproduccion.getPiezaxetapadeproduccionSet().remove(piezaxetapadeproduccion);
                etapadeproduccion = em.merge(etapadeproduccion);
            }
            Etapadeproduccion etapadeproduccion1 = piezaxetapadeproduccion.getEtapadeproduccion1();
            if (etapadeproduccion1 != null) {
                etapadeproduccion1.getPiezaxetapadeproduccionSet().remove(piezaxetapadeproduccion);
                etapadeproduccion1 = em.merge(etapadeproduccion1);
            }
            em.remove(piezaxetapadeproduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Piezaxetapadeproduccion> findPiezaxetapadeproduccionEntities() {
        return findPiezaxetapadeproduccionEntities(true, -1, -1);
    }

    public List<Piezaxetapadeproduccion> findPiezaxetapadeproduccionEntities(int maxResults, int firstResult) {
        return findPiezaxetapadeproduccionEntities(false, maxResults, firstResult);
    }

    private List<Piezaxetapadeproduccion> findPiezaxetapadeproduccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Piezaxetapadeproduccion.class));
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

    public Piezaxetapadeproduccion findPiezaxetapadeproduccion(PiezaxetapadeproduccionPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Piezaxetapadeproduccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPiezaxetapadeproduccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Piezaxetapadeproduccion> rt = cq.from(Piezaxetapadeproduccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}