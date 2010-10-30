/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Empleadoxturno;
import entity.EmpleadoxturnoPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Empleado;
import entity.Turno;

/**
 *
 * @author Nino
 */
public class EmpleadoxturnoJpaController {

    public EmpleadoxturnoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleadoxturno empleadoxturno) throws PreexistingEntityException, Exception {
        if (empleadoxturno.getEmpleadoxturnoPK() == null) {
            empleadoxturno.setEmpleadoxturnoPK(new EmpleadoxturnoPK());
        }
        empleadoxturno.getEmpleadoxturnoPK().setIdempleado(empleadoxturno.getEmpleado1().getIdempleado());
        empleadoxturno.getEmpleadoxturnoPK().setIdturno(empleadoxturno.getTurno1().getIdturno());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado = empleadoxturno.getEmpleado();
            if (empleado != null) {
                empleado = em.getReference(empleado.getClass(), empleado.getIdempleado());
                empleadoxturno.setEmpleado(empleado);
            }
            Empleado empleado1 = empleadoxturno.getEmpleado1();
            if (empleado1 != null) {
                empleado1 = em.getReference(empleado1.getClass(), empleado1.getIdempleado());
                empleadoxturno.setEmpleado1(empleado1);
            }
            Turno turno = empleadoxturno.getTurno();
            if (turno != null) {
                turno = em.getReference(turno.getClass(), turno.getIdturno());
                empleadoxturno.setTurno(turno);
            }
            Turno turno1 = empleadoxturno.getTurno1();
            if (turno1 != null) {
                turno1 = em.getReference(turno1.getClass(), turno1.getIdturno());
                empleadoxturno.setTurno1(turno1);
            }
            em.persist(empleadoxturno);
            if (empleado != null) {
                empleado.getEmpleadoxturnoSet().add(empleadoxturno);
                empleado = em.merge(empleado);
            }
            if (empleado1 != null) {
                empleado1.getEmpleadoxturnoSet().add(empleadoxturno);
                empleado1 = em.merge(empleado1);
            }
            if (turno != null) {
                turno.getEmpleadoxturnoSet().add(empleadoxturno);
                turno = em.merge(turno);
            }
            if (turno1 != null) {
                turno1.getEmpleadoxturnoSet().add(empleadoxturno);
                turno1 = em.merge(turno1);
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
        empleadoxturno.getEmpleadoxturnoPK().setIdempleado(empleadoxturno.getEmpleado1().getIdempleado());
        empleadoxturno.getEmpleadoxturnoPK().setIdturno(empleadoxturno.getTurno1().getIdturno());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleadoxturno persistentEmpleadoxturno = em.find(Empleadoxturno.class, empleadoxturno.getEmpleadoxturnoPK());
            Empleado empleadoOld = persistentEmpleadoxturno.getEmpleado();
            Empleado empleadoNew = empleadoxturno.getEmpleado();
            Empleado empleado1Old = persistentEmpleadoxturno.getEmpleado1();
            Empleado empleado1New = empleadoxturno.getEmpleado1();
            Turno turnoOld = persistentEmpleadoxturno.getTurno();
            Turno turnoNew = empleadoxturno.getTurno();
            Turno turno1Old = persistentEmpleadoxturno.getTurno1();
            Turno turno1New = empleadoxturno.getTurno1();
            if (empleadoNew != null) {
                empleadoNew = em.getReference(empleadoNew.getClass(), empleadoNew.getIdempleado());
                empleadoxturno.setEmpleado(empleadoNew);
            }
            if (empleado1New != null) {
                empleado1New = em.getReference(empleado1New.getClass(), empleado1New.getIdempleado());
                empleadoxturno.setEmpleado1(empleado1New);
            }
            if (turnoNew != null) {
                turnoNew = em.getReference(turnoNew.getClass(), turnoNew.getIdturno());
                empleadoxturno.setTurno(turnoNew);
            }
            if (turno1New != null) {
                turno1New = em.getReference(turno1New.getClass(), turno1New.getIdturno());
                empleadoxturno.setTurno1(turno1New);
            }
            empleadoxturno = em.merge(empleadoxturno);
            if (empleadoOld != null && !empleadoOld.equals(empleadoNew)) {
                empleadoOld.getEmpleadoxturnoSet().remove(empleadoxturno);
                empleadoOld = em.merge(empleadoOld);
            }
            if (empleadoNew != null && !empleadoNew.equals(empleadoOld)) {
                empleadoNew.getEmpleadoxturnoSet().add(empleadoxturno);
                empleadoNew = em.merge(empleadoNew);
            }
            if (empleado1Old != null && !empleado1Old.equals(empleado1New)) {
                empleado1Old.getEmpleadoxturnoSet().remove(empleadoxturno);
                empleado1Old = em.merge(empleado1Old);
            }
            if (empleado1New != null && !empleado1New.equals(empleado1Old)) {
                empleado1New.getEmpleadoxturnoSet().add(empleadoxturno);
                empleado1New = em.merge(empleado1New);
            }
            if (turnoOld != null && !turnoOld.equals(turnoNew)) {
                turnoOld.getEmpleadoxturnoSet().remove(empleadoxturno);
                turnoOld = em.merge(turnoOld);
            }
            if (turnoNew != null && !turnoNew.equals(turnoOld)) {
                turnoNew.getEmpleadoxturnoSet().add(empleadoxturno);
                turnoNew = em.merge(turnoNew);
            }
            if (turno1Old != null && !turno1Old.equals(turno1New)) {
                turno1Old.getEmpleadoxturnoSet().remove(empleadoxturno);
                turno1Old = em.merge(turno1Old);
            }
            if (turno1New != null && !turno1New.equals(turno1Old)) {
                turno1New.getEmpleadoxturnoSet().add(empleadoxturno);
                turno1New = em.merge(turno1New);
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
            Empleado empleado = empleadoxturno.getEmpleado();
            if (empleado != null) {
                empleado.getEmpleadoxturnoSet().remove(empleadoxturno);
                empleado = em.merge(empleado);
            }
            Empleado empleado1 = empleadoxturno.getEmpleado1();
            if (empleado1 != null) {
                empleado1.getEmpleadoxturnoSet().remove(empleadoxturno);
                empleado1 = em.merge(empleado1);
            }
            Turno turno = empleadoxturno.getTurno();
            if (turno != null) {
                turno.getEmpleadoxturnoSet().remove(empleadoxturno);
                turno = em.merge(turno);
            }
            Turno turno1 = empleadoxturno.getTurno1();
            if (turno1 != null) {
                turno1.getEmpleadoxturnoSet().remove(empleadoxturno);
                turno1 = em.merge(turno1);
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
