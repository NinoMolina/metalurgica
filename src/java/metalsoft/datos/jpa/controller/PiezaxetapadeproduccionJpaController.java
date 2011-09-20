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
import metalsoft.datos.jpa.entity.Etapadeproduccion;
import metalsoft.datos.jpa.entity.Piezaxetapadeproduccion;
import metalsoft.datos.jpa.entity.PiezaxetapadeproduccionPK;

/**
 *
 * @author Nino
 */
public class PiezaxetapadeproduccionJpaController implements Serializable {

    public PiezaxetapadeproduccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Piezaxetapadeproduccion piezaxetapadeproduccion) throws PreexistingEntityException, Exception {
        if (piezaxetapadeproduccion.getPiezaxetapadeproduccionPK() == null) {
            piezaxetapadeproduccion.setPiezaxetapadeproduccionPK(new PiezaxetapadeproduccionPK());
        }
        piezaxetapadeproduccion.getPiezaxetapadeproduccionPK().setIdetapaproduccion(piezaxetapadeproduccion.getEtapadeproduccion().getIdetapaproduccion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Etapadeproduccion etapadeproduccion = piezaxetapadeproduccion.getEtapadeproduccion();
            if (etapadeproduccion != null) {
                etapadeproduccion = em.getReference(etapadeproduccion.getClass(), etapadeproduccion.getIdetapaproduccion());
                piezaxetapadeproduccion.setEtapadeproduccion(etapadeproduccion);
            }
            em.persist(piezaxetapadeproduccion);
            if (etapadeproduccion != null) {
                etapadeproduccion.getPiezaxetapadeproduccionList().add(piezaxetapadeproduccion);
                etapadeproduccion = em.merge(etapadeproduccion);
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
        piezaxetapadeproduccion.getPiezaxetapadeproduccionPK().setIdetapaproduccion(piezaxetapadeproduccion.getEtapadeproduccion().getIdetapaproduccion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Piezaxetapadeproduccion persistentPiezaxetapadeproduccion = em.find(Piezaxetapadeproduccion.class, piezaxetapadeproduccion.getPiezaxetapadeproduccionPK());
            Etapadeproduccion etapadeproduccionOld = persistentPiezaxetapadeproduccion.getEtapadeproduccion();
            Etapadeproduccion etapadeproduccionNew = piezaxetapadeproduccion.getEtapadeproduccion();
            if (etapadeproduccionNew != null) {
                etapadeproduccionNew = em.getReference(etapadeproduccionNew.getClass(), etapadeproduccionNew.getIdetapaproduccion());
                piezaxetapadeproduccion.setEtapadeproduccion(etapadeproduccionNew);
            }
            piezaxetapadeproduccion = em.merge(piezaxetapadeproduccion);
            if (etapadeproduccionOld != null && !etapadeproduccionOld.equals(etapadeproduccionNew)) {
                etapadeproduccionOld.getPiezaxetapadeproduccionList().remove(piezaxetapadeproduccion);
                etapadeproduccionOld = em.merge(etapadeproduccionOld);
            }
            if (etapadeproduccionNew != null && !etapadeproduccionNew.equals(etapadeproduccionOld)) {
                etapadeproduccionNew.getPiezaxetapadeproduccionList().add(piezaxetapadeproduccion);
                etapadeproduccionNew = em.merge(etapadeproduccionNew);
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
                etapadeproduccion.getPiezaxetapadeproduccionList().remove(piezaxetapadeproduccion);
                etapadeproduccion = em.merge(etapadeproduccion);
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
