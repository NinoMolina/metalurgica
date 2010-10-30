/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Turno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Empleadoxturno;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class TurnoJpaController {

    public TurnoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Turno turno) throws PreexistingEntityException, Exception {
        if (turno.getEmpleadoxturnoSet() == null) {
            turno.setEmpleadoxturnoSet(new HashSet<Empleadoxturno>());
        }
        if (turno.getEmpleadoxturnoSet1() == null) {
            turno.setEmpleadoxturnoSet1(new HashSet<Empleadoxturno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Empleadoxturno> attachedEmpleadoxturnoSet = new HashSet<Empleadoxturno>();
            for (Empleadoxturno empleadoxturnoSetEmpleadoxturnoToAttach : turno.getEmpleadoxturnoSet()) {
                empleadoxturnoSetEmpleadoxturnoToAttach = em.getReference(empleadoxturnoSetEmpleadoxturnoToAttach.getClass(), empleadoxturnoSetEmpleadoxturnoToAttach.getEmpleadoxturnoPK());
                attachedEmpleadoxturnoSet.add(empleadoxturnoSetEmpleadoxturnoToAttach);
            }
            turno.setEmpleadoxturnoSet(attachedEmpleadoxturnoSet);
            Set<Empleadoxturno> attachedEmpleadoxturnoSet1 = new HashSet<Empleadoxturno>();
            for (Empleadoxturno empleadoxturnoSet1EmpleadoxturnoToAttach : turno.getEmpleadoxturnoSet1()) {
                empleadoxturnoSet1EmpleadoxturnoToAttach = em.getReference(empleadoxturnoSet1EmpleadoxturnoToAttach.getClass(), empleadoxturnoSet1EmpleadoxturnoToAttach.getEmpleadoxturnoPK());
                attachedEmpleadoxturnoSet1.add(empleadoxturnoSet1EmpleadoxturnoToAttach);
            }
            turno.setEmpleadoxturnoSet1(attachedEmpleadoxturnoSet1);
            em.persist(turno);
            for (Empleadoxturno empleadoxturnoSetEmpleadoxturno : turno.getEmpleadoxturnoSet()) {
                Turno oldTurnoOfEmpleadoxturnoSetEmpleadoxturno = empleadoxturnoSetEmpleadoxturno.getTurno();
                empleadoxturnoSetEmpleadoxturno.setTurno(turno);
                empleadoxturnoSetEmpleadoxturno = em.merge(empleadoxturnoSetEmpleadoxturno);
                if (oldTurnoOfEmpleadoxturnoSetEmpleadoxturno != null) {
                    oldTurnoOfEmpleadoxturnoSetEmpleadoxturno.getEmpleadoxturnoSet().remove(empleadoxturnoSetEmpleadoxturno);
                    oldTurnoOfEmpleadoxturnoSetEmpleadoxturno = em.merge(oldTurnoOfEmpleadoxturnoSetEmpleadoxturno);
                }
            }
            for (Empleadoxturno empleadoxturnoSet1Empleadoxturno : turno.getEmpleadoxturnoSet1()) {
                Turno oldTurno1OfEmpleadoxturnoSet1Empleadoxturno = empleadoxturnoSet1Empleadoxturno.getTurno1();
                empleadoxturnoSet1Empleadoxturno.setTurno1(turno);
                empleadoxturnoSet1Empleadoxturno = em.merge(empleadoxturnoSet1Empleadoxturno);
                if (oldTurno1OfEmpleadoxturnoSet1Empleadoxturno != null) {
                    oldTurno1OfEmpleadoxturnoSet1Empleadoxturno.getEmpleadoxturnoSet1().remove(empleadoxturnoSet1Empleadoxturno);
                    oldTurno1OfEmpleadoxturnoSet1Empleadoxturno = em.merge(oldTurno1OfEmpleadoxturnoSet1Empleadoxturno);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTurno(turno.getIdturno()) != null) {
                throw new PreexistingEntityException("Turno " + turno + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Turno turno) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turno persistentTurno = em.find(Turno.class, turno.getIdturno());
            Set<Empleadoxturno> empleadoxturnoSetOld = persistentTurno.getEmpleadoxturnoSet();
            Set<Empleadoxturno> empleadoxturnoSetNew = turno.getEmpleadoxturnoSet();
            Set<Empleadoxturno> empleadoxturnoSet1Old = persistentTurno.getEmpleadoxturnoSet1();
            Set<Empleadoxturno> empleadoxturnoSet1New = turno.getEmpleadoxturnoSet1();
            List<String> illegalOrphanMessages = null;
            for (Empleadoxturno empleadoxturnoSetOldEmpleadoxturno : empleadoxturnoSetOld) {
                if (!empleadoxturnoSetNew.contains(empleadoxturnoSetOldEmpleadoxturno)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empleadoxturno " + empleadoxturnoSetOldEmpleadoxturno + " since its turno field is not nullable.");
                }
            }
            for (Empleadoxturno empleadoxturnoSet1OldEmpleadoxturno : empleadoxturnoSet1Old) {
                if (!empleadoxturnoSet1New.contains(empleadoxturnoSet1OldEmpleadoxturno)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empleadoxturno " + empleadoxturnoSet1OldEmpleadoxturno + " since its turno1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Empleadoxturno> attachedEmpleadoxturnoSetNew = new HashSet<Empleadoxturno>();
            for (Empleadoxturno empleadoxturnoSetNewEmpleadoxturnoToAttach : empleadoxturnoSetNew) {
                empleadoxturnoSetNewEmpleadoxturnoToAttach = em.getReference(empleadoxturnoSetNewEmpleadoxturnoToAttach.getClass(), empleadoxturnoSetNewEmpleadoxturnoToAttach.getEmpleadoxturnoPK());
                attachedEmpleadoxturnoSetNew.add(empleadoxturnoSetNewEmpleadoxturnoToAttach);
            }
            empleadoxturnoSetNew = attachedEmpleadoxturnoSetNew;
            turno.setEmpleadoxturnoSet(empleadoxturnoSetNew);
            Set<Empleadoxturno> attachedEmpleadoxturnoSet1New = new HashSet<Empleadoxturno>();
            for (Empleadoxturno empleadoxturnoSet1NewEmpleadoxturnoToAttach : empleadoxturnoSet1New) {
                empleadoxturnoSet1NewEmpleadoxturnoToAttach = em.getReference(empleadoxturnoSet1NewEmpleadoxturnoToAttach.getClass(), empleadoxturnoSet1NewEmpleadoxturnoToAttach.getEmpleadoxturnoPK());
                attachedEmpleadoxturnoSet1New.add(empleadoxturnoSet1NewEmpleadoxturnoToAttach);
            }
            empleadoxturnoSet1New = attachedEmpleadoxturnoSet1New;
            turno.setEmpleadoxturnoSet1(empleadoxturnoSet1New);
            turno = em.merge(turno);
            for (Empleadoxturno empleadoxturnoSetNewEmpleadoxturno : empleadoxturnoSetNew) {
                if (!empleadoxturnoSetOld.contains(empleadoxturnoSetNewEmpleadoxturno)) {
                    Turno oldTurnoOfEmpleadoxturnoSetNewEmpleadoxturno = empleadoxturnoSetNewEmpleadoxturno.getTurno();
                    empleadoxturnoSetNewEmpleadoxturno.setTurno(turno);
                    empleadoxturnoSetNewEmpleadoxturno = em.merge(empleadoxturnoSetNewEmpleadoxturno);
                    if (oldTurnoOfEmpleadoxturnoSetNewEmpleadoxturno != null && !oldTurnoOfEmpleadoxturnoSetNewEmpleadoxturno.equals(turno)) {
                        oldTurnoOfEmpleadoxturnoSetNewEmpleadoxturno.getEmpleadoxturnoSet().remove(empleadoxturnoSetNewEmpleadoxturno);
                        oldTurnoOfEmpleadoxturnoSetNewEmpleadoxturno = em.merge(oldTurnoOfEmpleadoxturnoSetNewEmpleadoxturno);
                    }
                }
            }
            for (Empleadoxturno empleadoxturnoSet1NewEmpleadoxturno : empleadoxturnoSet1New) {
                if (!empleadoxturnoSet1Old.contains(empleadoxturnoSet1NewEmpleadoxturno)) {
                    Turno oldTurno1OfEmpleadoxturnoSet1NewEmpleadoxturno = empleadoxturnoSet1NewEmpleadoxturno.getTurno1();
                    empleadoxturnoSet1NewEmpleadoxturno.setTurno1(turno);
                    empleadoxturnoSet1NewEmpleadoxturno = em.merge(empleadoxturnoSet1NewEmpleadoxturno);
                    if (oldTurno1OfEmpleadoxturnoSet1NewEmpleadoxturno != null && !oldTurno1OfEmpleadoxturnoSet1NewEmpleadoxturno.equals(turno)) {
                        oldTurno1OfEmpleadoxturnoSet1NewEmpleadoxturno.getEmpleadoxturnoSet1().remove(empleadoxturnoSet1NewEmpleadoxturno);
                        oldTurno1OfEmpleadoxturnoSet1NewEmpleadoxturno = em.merge(oldTurno1OfEmpleadoxturnoSet1NewEmpleadoxturno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = turno.getIdturno();
                if (findTurno(id) == null) {
                    throw new NonexistentEntityException("The turno with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Turno turno;
            try {
                turno = em.getReference(Turno.class, id);
                turno.getIdturno();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The turno with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Empleadoxturno> empleadoxturnoSetOrphanCheck = turno.getEmpleadoxturnoSet();
            for (Empleadoxturno empleadoxturnoSetOrphanCheckEmpleadoxturno : empleadoxturnoSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Turno (" + turno + ") cannot be destroyed since the Empleadoxturno " + empleadoxturnoSetOrphanCheckEmpleadoxturno + " in its empleadoxturnoSet field has a non-nullable turno field.");
            }
            Set<Empleadoxturno> empleadoxturnoSet1OrphanCheck = turno.getEmpleadoxturnoSet1();
            for (Empleadoxturno empleadoxturnoSet1OrphanCheckEmpleadoxturno : empleadoxturnoSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Turno (" + turno + ") cannot be destroyed since the Empleadoxturno " + empleadoxturnoSet1OrphanCheckEmpleadoxturno + " in its empleadoxturnoSet1 field has a non-nullable turno1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(turno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Turno> findTurnoEntities() {
        return findTurnoEntities(true, -1, -1);
    }

    public List<Turno> findTurnoEntities(int maxResults, int firstResult) {
        return findTurnoEntities(false, maxResults, firstResult);
    }

    private List<Turno> findTurnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Turno.class));
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

    public Turno findTurno(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Turno.class, id);
        } finally {
            em.close();
        }
    }

    public int getTurnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Turno> rt = cq.from(Turno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
