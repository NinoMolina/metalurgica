/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Maquinaxprocesocalidad;
import entity.MaquinaxprocesocalidadPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Procesocalidad;

/**
 *
 * @author Nino
 */
public class MaquinaxprocesocalidadJpaController {

    public MaquinaxprocesocalidadJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Maquinaxprocesocalidad maquinaxprocesocalidad) throws PreexistingEntityException, Exception {
        if (maquinaxprocesocalidad.getMaquinaxprocesocalidadPK() == null) {
            maquinaxprocesocalidad.setMaquinaxprocesocalidadPK(new MaquinaxprocesocalidadPK());
        }
        maquinaxprocesocalidad.getMaquinaxprocesocalidadPK().setIdprocesocalidad(maquinaxprocesocalidad.getProcesocalidad1().getIdprocesocalidad());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procesocalidad procesocalidad = maquinaxprocesocalidad.getProcesocalidad();
            if (procesocalidad != null) {
                procesocalidad = em.getReference(procesocalidad.getClass(), procesocalidad.getIdprocesocalidad());
                maquinaxprocesocalidad.setProcesocalidad(procesocalidad);
            }
            Procesocalidad procesocalidad1 = maquinaxprocesocalidad.getProcesocalidad1();
            if (procesocalidad1 != null) {
                procesocalidad1 = em.getReference(procesocalidad1.getClass(), procesocalidad1.getIdprocesocalidad());
                maquinaxprocesocalidad.setProcesocalidad1(procesocalidad1);
            }
            em.persist(maquinaxprocesocalidad);
            if (procesocalidad != null) {
                procesocalidad.getMaquinaxprocesocalidadSet().add(maquinaxprocesocalidad);
                procesocalidad = em.merge(procesocalidad);
            }
            if (procesocalidad1 != null) {
                procesocalidad1.getMaquinaxprocesocalidadSet().add(maquinaxprocesocalidad);
                procesocalidad1 = em.merge(procesocalidad1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMaquinaxprocesocalidad(maquinaxprocesocalidad.getMaquinaxprocesocalidadPK()) != null) {
                throw new PreexistingEntityException("Maquinaxprocesocalidad " + maquinaxprocesocalidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Maquinaxprocesocalidad maquinaxprocesocalidad) throws NonexistentEntityException, Exception {
        maquinaxprocesocalidad.getMaquinaxprocesocalidadPK().setIdprocesocalidad(maquinaxprocesocalidad.getProcesocalidad1().getIdprocesocalidad());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Maquinaxprocesocalidad persistentMaquinaxprocesocalidad = em.find(Maquinaxprocesocalidad.class, maquinaxprocesocalidad.getMaquinaxprocesocalidadPK());
            Procesocalidad procesocalidadOld = persistentMaquinaxprocesocalidad.getProcesocalidad();
            Procesocalidad procesocalidadNew = maquinaxprocesocalidad.getProcesocalidad();
            Procesocalidad procesocalidad1Old = persistentMaquinaxprocesocalidad.getProcesocalidad1();
            Procesocalidad procesocalidad1New = maquinaxprocesocalidad.getProcesocalidad1();
            if (procesocalidadNew != null) {
                procesocalidadNew = em.getReference(procesocalidadNew.getClass(), procesocalidadNew.getIdprocesocalidad());
                maquinaxprocesocalidad.setProcesocalidad(procesocalidadNew);
            }
            if (procesocalidad1New != null) {
                procesocalidad1New = em.getReference(procesocalidad1New.getClass(), procesocalidad1New.getIdprocesocalidad());
                maquinaxprocesocalidad.setProcesocalidad1(procesocalidad1New);
            }
            maquinaxprocesocalidad = em.merge(maquinaxprocesocalidad);
            if (procesocalidadOld != null && !procesocalidadOld.equals(procesocalidadNew)) {
                procesocalidadOld.getMaquinaxprocesocalidadSet().remove(maquinaxprocesocalidad);
                procesocalidadOld = em.merge(procesocalidadOld);
            }
            if (procesocalidadNew != null && !procesocalidadNew.equals(procesocalidadOld)) {
                procesocalidadNew.getMaquinaxprocesocalidadSet().add(maquinaxprocesocalidad);
                procesocalidadNew = em.merge(procesocalidadNew);
            }
            if (procesocalidad1Old != null && !procesocalidad1Old.equals(procesocalidad1New)) {
                procesocalidad1Old.getMaquinaxprocesocalidadSet().remove(maquinaxprocesocalidad);
                procesocalidad1Old = em.merge(procesocalidad1Old);
            }
            if (procesocalidad1New != null && !procesocalidad1New.equals(procesocalidad1Old)) {
                procesocalidad1New.getMaquinaxprocesocalidadSet().add(maquinaxprocesocalidad);
                procesocalidad1New = em.merge(procesocalidad1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                MaquinaxprocesocalidadPK id = maquinaxprocesocalidad.getMaquinaxprocesocalidadPK();
                if (findMaquinaxprocesocalidad(id) == null) {
                    throw new NonexistentEntityException("The maquinaxprocesocalidad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(MaquinaxprocesocalidadPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Maquinaxprocesocalidad maquinaxprocesocalidad;
            try {
                maquinaxprocesocalidad = em.getReference(Maquinaxprocesocalidad.class, id);
                maquinaxprocesocalidad.getMaquinaxprocesocalidadPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The maquinaxprocesocalidad with id " + id + " no longer exists.", enfe);
            }
            Procesocalidad procesocalidad = maquinaxprocesocalidad.getProcesocalidad();
            if (procesocalidad != null) {
                procesocalidad.getMaquinaxprocesocalidadSet().remove(maquinaxprocesocalidad);
                procesocalidad = em.merge(procesocalidad);
            }
            Procesocalidad procesocalidad1 = maquinaxprocesocalidad.getProcesocalidad1();
            if (procesocalidad1 != null) {
                procesocalidad1.getMaquinaxprocesocalidadSet().remove(maquinaxprocesocalidad);
                procesocalidad1 = em.merge(procesocalidad1);
            }
            em.remove(maquinaxprocesocalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Maquinaxprocesocalidad> findMaquinaxprocesocalidadEntities() {
        return findMaquinaxprocesocalidadEntities(true, -1, -1);
    }

    public List<Maquinaxprocesocalidad> findMaquinaxprocesocalidadEntities(int maxResults, int firstResult) {
        return findMaquinaxprocesocalidadEntities(false, maxResults, firstResult);
    }

    private List<Maquinaxprocesocalidad> findMaquinaxprocesocalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Maquinaxprocesocalidad.class));
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

    public Maquinaxprocesocalidad findMaquinaxprocesocalidad(MaquinaxprocesocalidadPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Maquinaxprocesocalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getMaquinaxprocesocalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Maquinaxprocesocalidad> rt = cq.from(Maquinaxprocesocalidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
