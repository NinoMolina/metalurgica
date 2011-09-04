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
import metalsoft.datos.jpa.entity.Empleadoxturno;
import metalsoft.datos.jpa.entity.EmpleadoxturnoPK;
import metalsoft.datos.jpa.entity.Turno;
import metalsoft.datos.jpa.entity.Empleado;

/**
 *
 * @author Nino
 */
public class EmpleadoxturnoJpaController implements Serializable {

    public EmpleadoxturnoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleadoxturno empleadoxturno) throws PreexistingEntityException, Exception {
        if (empleadoxturno.getEmpleadoxturnoPK() == null) {
            empleadoxturno.setEmpleadoxturnoPK(new EmpleadoxturnoPK());
        }
        empleadoxturno.getEmpleadoxturnoPK().setIdturno(empleadoxturno.getTurno().getIdturno());
        empleadoxturno.getEmpleadoxturnoPK().setIdempleado(empleadoxturno.getEmpleado().getIdempleado());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turno turno = empleadoxturno.getTurno();
            if (turno != null) {
                turno = em.getReference(turno.getClass(), turno.getIdturno());
                empleadoxturno.setTurno(turno);
            }
            Empleado empleado = empleadoxturno.getEmpleado();
            if (empleado != null) {
                empleado = em.getReference(empleado.getClass(), empleado.getIdempleado());
                empleadoxturno.setEmpleado(empleado);
            }
            em.persist(empleadoxturno);
            if (turno != null) {
                turno.getEmpleadoxturnoList().add(empleadoxturno);
                turno = em.merge(turno);
            }
            if (empleado != null) {
                empleado.getEmpleadoxturnoList().add(empleadoxturno);
                empleado = em.merge(empleado);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmpleadoxturno(empleadoxturno.getEmpleadoxturnoPK()) != null) {
                throw new PreexistingEntityException("Empleadoxturno " + empleadoxturno + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleadoxturno empleadoxturno) throws NonexistentEntityException, Exception {
        empleadoxturno.getEmpleadoxturnoPK().setIdturno(empleadoxturno.getTurno().getIdturno());
        empleadoxturno.getEmpleadoxturnoPK().setIdempleado(empleadoxturno.getEmpleado().getIdempleado());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleadoxturno persistentEmpleadoxturno = em.find(Empleadoxturno.class, empleadoxturno.getEmpleadoxturnoPK());
            Turno turnoOld = persistentEmpleadoxturno.getTurno();
            Turno turnoNew = empleadoxturno.getTurno();
            Empleado empleadoOld = persistentEmpleadoxturno.getEmpleado();
            Empleado empleadoNew = empleadoxturno.getEmpleado();
            if (turnoNew != null) {
                turnoNew = em.getReference(turnoNew.getClass(), turnoNew.getIdturno());
                empleadoxturno.setTurno(turnoNew);
            }
            if (empleadoNew != null) {
                empleadoNew = em.getReference(empleadoNew.getClass(), empleadoNew.getIdempleado());
                empleadoxturno.setEmpleado(empleadoNew);
            }
            empleadoxturno = em.merge(empleadoxturno);
            if (turnoOld != null && !turnoOld.equals(turnoNew)) {
                turnoOld.getEmpleadoxturnoList().remove(empleadoxturno);
                turnoOld = em.merge(turnoOld);
            }
            if (turnoNew != null && !turnoNew.equals(turnoOld)) {
                turnoNew.getEmpleadoxturnoList().add(empleadoxturno);
                turnoNew = em.merge(turnoNew);
            }
            if (empleadoOld != null && !empleadoOld.equals(empleadoNew)) {
                empleadoOld.getEmpleadoxturnoList().remove(empleadoxturno);
                empleadoOld = em.merge(empleadoOld);
            }
            if (empleadoNew != null && !empleadoNew.equals(empleadoOld)) {
                empleadoNew.getEmpleadoxturnoList().add(empleadoxturno);
                empleadoNew = em.merge(empleadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                EmpleadoxturnoPK id = empleadoxturno.getEmpleadoxturnoPK();
                if (findEmpleadoxturno(id) == null) {
                    throw new NonexistentEntityException("The empleadoxturno with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(EmpleadoxturnoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleadoxturno empleadoxturno;
            try {
                empleadoxturno = em.getReference(Empleadoxturno.class, id);
                empleadoxturno.getEmpleadoxturnoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleadoxturno with id " + id + " no longer exists.", enfe);
            }
            Turno turno = empleadoxturno.getTurno();
            if (turno != null) {
                turno.getEmpleadoxturnoList().remove(empleadoxturno);
                turno = em.merge(turno);
            }
            Empleado empleado = empleadoxturno.getEmpleado();
            if (empleado != null) {
                empleado.getEmpleadoxturnoList().remove(empleadoxturno);
                empleado = em.merge(empleado);
            }
            em.remove(empleadoxturno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleadoxturno> findEmpleadoxturnoEntities() {
        return findEmpleadoxturnoEntities(true, -1, -1);
    }

    public List<Empleadoxturno> findEmpleadoxturnoEntities(int maxResults, int firstResult) {
        return findEmpleadoxturnoEntities(false, maxResults, firstResult);
    }

    private List<Empleadoxturno> findEmpleadoxturnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleadoxturno.class));
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

    public Empleadoxturno findEmpleadoxturno(EmpleadoxturnoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleadoxturno.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoxturnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleadoxturno> rt = cq.from(Empleadoxturno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
