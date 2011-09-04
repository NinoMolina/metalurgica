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
import metalsoft.datos.jpa.entity.Asistencia;
import metalsoft.datos.jpa.entity.AsistenciaPK;
import metalsoft.datos.jpa.entity.Empleado;

/**
 *
 * @author Nino
 */
public class AsistenciaJpaController implements Serializable {

    public AsistenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asistencia asistencia) throws PreexistingEntityException, Exception {
        if (asistencia.getAsistenciaPK() == null) {
            asistencia.setAsistenciaPK(new AsistenciaPK());
        }
        asistencia.getAsistenciaPK().setEmpleado(asistencia.getEmpleado1().getIdempleado());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado1 = asistencia.getEmpleado1();
            if (empleado1 != null) {
                empleado1 = em.getReference(empleado1.getClass(), empleado1.getIdempleado());
                asistencia.setEmpleado1(empleado1);
            }
            em.persist(asistencia);
            if (empleado1 != null) {
                empleado1.getAsistenciaList().add(asistencia);
                empleado1 = em.merge(empleado1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAsistencia(asistencia.getAsistenciaPK()) != null) {
                throw new PreexistingEntityException("Asistencia " + asistencia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asistencia asistencia) throws NonexistentEntityException, Exception {
        asistencia.getAsistenciaPK().setEmpleado(asistencia.getEmpleado1().getIdempleado());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asistencia persistentAsistencia = em.find(Asistencia.class, asistencia.getAsistenciaPK());
            Empleado empleado1Old = persistentAsistencia.getEmpleado1();
            Empleado empleado1New = asistencia.getEmpleado1();
            if (empleado1New != null) {
                empleado1New = em.getReference(empleado1New.getClass(), empleado1New.getIdempleado());
                asistencia.setEmpleado1(empleado1New);
            }
            asistencia = em.merge(asistencia);
            if (empleado1Old != null && !empleado1Old.equals(empleado1New)) {
                empleado1Old.getAsistenciaList().remove(asistencia);
                empleado1Old = em.merge(empleado1Old);
            }
            if (empleado1New != null && !empleado1New.equals(empleado1Old)) {
                empleado1New.getAsistenciaList().add(asistencia);
                empleado1New = em.merge(empleado1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                AsistenciaPK id = asistencia.getAsistenciaPK();
                if (findAsistencia(id) == null) {
                    throw new NonexistentEntityException("The asistencia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(AsistenciaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asistencia asistencia;
            try {
                asistencia = em.getReference(Asistencia.class, id);
                asistencia.getAsistenciaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asistencia with id " + id + " no longer exists.", enfe);
            }
            Empleado empleado1 = asistencia.getEmpleado1();
            if (empleado1 != null) {
                empleado1.getAsistenciaList().remove(asistencia);
                empleado1 = em.merge(empleado1);
            }
            em.remove(asistencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asistencia> findAsistenciaEntities() {
        return findAsistenciaEntities(true, -1, -1);
    }

    public List<Asistencia> findAsistenciaEntities(int maxResults, int firstResult) {
        return findAsistenciaEntities(false, maxResults, firstResult);
    }

    private List<Asistencia> findAsistenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Asistencia.class));
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

    public Asistencia findAsistencia(AsistenciaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asistencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsistenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Asistencia> rt = cq.from(Asistencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
