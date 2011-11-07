/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.controller;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Empleadoxturno;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Turno;

/**
 *
 * @author Nino
 */
public class TurnoJpaController implements Serializable {

    public TurnoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Turno turno) throws PreexistingEntityException, Exception {
        if (turno.getEmpleadoxturnoList() == null) {
            turno.setEmpleadoxturnoList(new ArrayList<Empleadoxturno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Empleadoxturno> attachedEmpleadoxturnoList = new ArrayList<Empleadoxturno>();
            for (Empleadoxturno empleadoxturnoListEmpleadoxturnoToAttach : turno.getEmpleadoxturnoList()) {
                empleadoxturnoListEmpleadoxturnoToAttach = em.getReference(empleadoxturnoListEmpleadoxturnoToAttach.getClass(), empleadoxturnoListEmpleadoxturnoToAttach.getEmpleadoxturnoPK());
                attachedEmpleadoxturnoList.add(empleadoxturnoListEmpleadoxturnoToAttach);
            }
            turno.setEmpleadoxturnoList(attachedEmpleadoxturnoList);
            em.persist(turno);
            for (Empleadoxturno empleadoxturnoListEmpleadoxturno : turno.getEmpleadoxturnoList()) {
                Turno oldTurnoOfEmpleadoxturnoListEmpleadoxturno = empleadoxturnoListEmpleadoxturno.getTurno();
                empleadoxturnoListEmpleadoxturno.setTurno(turno);
                empleadoxturnoListEmpleadoxturno = em.merge(empleadoxturnoListEmpleadoxturno);
                if (oldTurnoOfEmpleadoxturnoListEmpleadoxturno != null) {
                    oldTurnoOfEmpleadoxturnoListEmpleadoxturno.getEmpleadoxturnoList().remove(empleadoxturnoListEmpleadoxturno);
                    oldTurnoOfEmpleadoxturnoListEmpleadoxturno = em.merge(oldTurnoOfEmpleadoxturnoListEmpleadoxturno);
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
            List<Empleadoxturno> empleadoxturnoListOld = persistentTurno.getEmpleadoxturnoList();
            List<Empleadoxturno> empleadoxturnoListNew = turno.getEmpleadoxturnoList();
            List<String> illegalOrphanMessages = null;
            for (Empleadoxturno empleadoxturnoListOldEmpleadoxturno : empleadoxturnoListOld) {
                if (!empleadoxturnoListNew.contains(empleadoxturnoListOldEmpleadoxturno)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Empleadoxturno " + empleadoxturnoListOldEmpleadoxturno + " since its turno field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Empleadoxturno> attachedEmpleadoxturnoListNew = new ArrayList<Empleadoxturno>();
            for (Empleadoxturno empleadoxturnoListNewEmpleadoxturnoToAttach : empleadoxturnoListNew) {
                empleadoxturnoListNewEmpleadoxturnoToAttach = em.getReference(empleadoxturnoListNewEmpleadoxturnoToAttach.getClass(), empleadoxturnoListNewEmpleadoxturnoToAttach.getEmpleadoxturnoPK());
                attachedEmpleadoxturnoListNew.add(empleadoxturnoListNewEmpleadoxturnoToAttach);
            }
            empleadoxturnoListNew = attachedEmpleadoxturnoListNew;
            turno.setEmpleadoxturnoList(empleadoxturnoListNew);
            turno = em.merge(turno);
            for (Empleadoxturno empleadoxturnoListNewEmpleadoxturno : empleadoxturnoListNew) {
                if (!empleadoxturnoListOld.contains(empleadoxturnoListNewEmpleadoxturno)) {
                    Turno oldTurnoOfEmpleadoxturnoListNewEmpleadoxturno = empleadoxturnoListNewEmpleadoxturno.getTurno();
                    empleadoxturnoListNewEmpleadoxturno.setTurno(turno);
                    empleadoxturnoListNewEmpleadoxturno = em.merge(empleadoxturnoListNewEmpleadoxturno);
                    if (oldTurnoOfEmpleadoxturnoListNewEmpleadoxturno != null && !oldTurnoOfEmpleadoxturnoListNewEmpleadoxturno.equals(turno)) {
                        oldTurnoOfEmpleadoxturnoListNewEmpleadoxturno.getEmpleadoxturnoList().remove(empleadoxturnoListNewEmpleadoxturno);
                        oldTurnoOfEmpleadoxturnoListNewEmpleadoxturno = em.merge(oldTurnoOfEmpleadoxturnoListNewEmpleadoxturno);
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
            List<Empleadoxturno> empleadoxturnoListOrphanCheck = turno.getEmpleadoxturnoList();
            for (Empleadoxturno empleadoxturnoListOrphanCheckEmpleadoxturno : empleadoxturnoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Turno (" + turno + ") cannot be destroyed since the Empleadoxturno " + empleadoxturnoListOrphanCheckEmpleadoxturno + " in its empleadoxturnoList field has a non-nullable turno field.");
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
