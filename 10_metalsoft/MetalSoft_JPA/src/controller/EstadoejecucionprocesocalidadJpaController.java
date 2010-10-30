/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Estadoejecucionprocesocalidad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Ejecucionprocesocalidad;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class EstadoejecucionprocesocalidadJpaController {

    public EstadoejecucionprocesocalidadJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadoejecucionprocesocalidad estadoejecucionprocesocalidad) throws PreexistingEntityException, Exception {
        if (estadoejecucionprocesocalidad.getEjecucionprocesocalidadSet() == null) {
            estadoejecucionprocesocalidad.setEjecucionprocesocalidadSet(new HashSet<Ejecucionprocesocalidad>());
        }
        if (estadoejecucionprocesocalidad.getEjecucionprocesocalidadSet1() == null) {
            estadoejecucionprocesocalidad.setEjecucionprocesocalidadSet1(new HashSet<Ejecucionprocesocalidad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Ejecucionprocesocalidad> attachedEjecucionprocesocalidadSet = new HashSet<Ejecucionprocesocalidad>();
            for (Ejecucionprocesocalidad ejecucionprocesocalidadSetEjecucionprocesocalidadToAttach : estadoejecucionprocesocalidad.getEjecucionprocesocalidadSet()) {
                ejecucionprocesocalidadSetEjecucionprocesocalidadToAttach = em.getReference(ejecucionprocesocalidadSetEjecucionprocesocalidadToAttach.getClass(), ejecucionprocesocalidadSetEjecucionprocesocalidadToAttach.getEjecucionprocesocalidadPK());
                attachedEjecucionprocesocalidadSet.add(ejecucionprocesocalidadSetEjecucionprocesocalidadToAttach);
            }
            estadoejecucionprocesocalidad.setEjecucionprocesocalidadSet(attachedEjecucionprocesocalidadSet);
            Set<Ejecucionprocesocalidad> attachedEjecucionprocesocalidadSet1 = new HashSet<Ejecucionprocesocalidad>();
            for (Ejecucionprocesocalidad ejecucionprocesocalidadSet1EjecucionprocesocalidadToAttach : estadoejecucionprocesocalidad.getEjecucionprocesocalidadSet1()) {
                ejecucionprocesocalidadSet1EjecucionprocesocalidadToAttach = em.getReference(ejecucionprocesocalidadSet1EjecucionprocesocalidadToAttach.getClass(), ejecucionprocesocalidadSet1EjecucionprocesocalidadToAttach.getEjecucionprocesocalidadPK());
                attachedEjecucionprocesocalidadSet1.add(ejecucionprocesocalidadSet1EjecucionprocesocalidadToAttach);
            }
            estadoejecucionprocesocalidad.setEjecucionprocesocalidadSet1(attachedEjecucionprocesocalidadSet1);
            em.persist(estadoejecucionprocesocalidad);
            for (Ejecucionprocesocalidad ejecucionprocesocalidadSetEjecucionprocesocalidad : estadoejecucionprocesocalidad.getEjecucionprocesocalidadSet()) {
                Estadoejecucionprocesocalidad oldEstadoOfEjecucionprocesocalidadSetEjecucionprocesocalidad = ejecucionprocesocalidadSetEjecucionprocesocalidad.getEstado();
                ejecucionprocesocalidadSetEjecucionprocesocalidad.setEstado(estadoejecucionprocesocalidad);
                ejecucionprocesocalidadSetEjecucionprocesocalidad = em.merge(ejecucionprocesocalidadSetEjecucionprocesocalidad);
                if (oldEstadoOfEjecucionprocesocalidadSetEjecucionprocesocalidad != null) {
                    oldEstadoOfEjecucionprocesocalidadSetEjecucionprocesocalidad.getEjecucionprocesocalidadSet().remove(ejecucionprocesocalidadSetEjecucionprocesocalidad);
                    oldEstadoOfEjecucionprocesocalidadSetEjecucionprocesocalidad = em.merge(oldEstadoOfEjecucionprocesocalidadSetEjecucionprocesocalidad);
                }
            }
            for (Ejecucionprocesocalidad ejecucionprocesocalidadSet1Ejecucionprocesocalidad : estadoejecucionprocesocalidad.getEjecucionprocesocalidadSet1()) {
                Estadoejecucionprocesocalidad oldEstado1OfEjecucionprocesocalidadSet1Ejecucionprocesocalidad = ejecucionprocesocalidadSet1Ejecucionprocesocalidad.getEstado1();
                ejecucionprocesocalidadSet1Ejecucionprocesocalidad.setEstado1(estadoejecucionprocesocalidad);
                ejecucionprocesocalidadSet1Ejecucionprocesocalidad = em.merge(ejecucionprocesocalidadSet1Ejecucionprocesocalidad);
                if (oldEstado1OfEjecucionprocesocalidadSet1Ejecucionprocesocalidad != null) {
                    oldEstado1OfEjecucionprocesocalidadSet1Ejecucionprocesocalidad.getEjecucionprocesocalidadSet1().remove(ejecucionprocesocalidadSet1Ejecucionprocesocalidad);
                    oldEstado1OfEjecucionprocesocalidadSet1Ejecucionprocesocalidad = em.merge(oldEstado1OfEjecucionprocesocalidadSet1Ejecucionprocesocalidad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoejecucionprocesocalidad(estadoejecucionprocesocalidad.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadoejecucionprocesocalidad " + estadoejecucionprocesocalidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadoejecucionprocesocalidad estadoejecucionprocesocalidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoejecucionprocesocalidad persistentEstadoejecucionprocesocalidad = em.find(Estadoejecucionprocesocalidad.class, estadoejecucionprocesocalidad.getIdestado());
            Set<Ejecucionprocesocalidad> ejecucionprocesocalidadSetOld = persistentEstadoejecucionprocesocalidad.getEjecucionprocesocalidadSet();
            Set<Ejecucionprocesocalidad> ejecucionprocesocalidadSetNew = estadoejecucionprocesocalidad.getEjecucionprocesocalidadSet();
            Set<Ejecucionprocesocalidad> ejecucionprocesocalidadSet1Old = persistentEstadoejecucionprocesocalidad.getEjecucionprocesocalidadSet1();
            Set<Ejecucionprocesocalidad> ejecucionprocesocalidadSet1New = estadoejecucionprocesocalidad.getEjecucionprocesocalidadSet1();
            Set<Ejecucionprocesocalidad> attachedEjecucionprocesocalidadSetNew = new HashSet<Ejecucionprocesocalidad>();
            for (Ejecucionprocesocalidad ejecucionprocesocalidadSetNewEjecucionprocesocalidadToAttach : ejecucionprocesocalidadSetNew) {
                ejecucionprocesocalidadSetNewEjecucionprocesocalidadToAttach = em.getReference(ejecucionprocesocalidadSetNewEjecucionprocesocalidadToAttach.getClass(), ejecucionprocesocalidadSetNewEjecucionprocesocalidadToAttach.getEjecucionprocesocalidadPK());
                attachedEjecucionprocesocalidadSetNew.add(ejecucionprocesocalidadSetNewEjecucionprocesocalidadToAttach);
            }
            ejecucionprocesocalidadSetNew = attachedEjecucionprocesocalidadSetNew;
            estadoejecucionprocesocalidad.setEjecucionprocesocalidadSet(ejecucionprocesocalidadSetNew);
            Set<Ejecucionprocesocalidad> attachedEjecucionprocesocalidadSet1New = new HashSet<Ejecucionprocesocalidad>();
            for (Ejecucionprocesocalidad ejecucionprocesocalidadSet1NewEjecucionprocesocalidadToAttach : ejecucionprocesocalidadSet1New) {
                ejecucionprocesocalidadSet1NewEjecucionprocesocalidadToAttach = em.getReference(ejecucionprocesocalidadSet1NewEjecucionprocesocalidadToAttach.getClass(), ejecucionprocesocalidadSet1NewEjecucionprocesocalidadToAttach.getEjecucionprocesocalidadPK());
                attachedEjecucionprocesocalidadSet1New.add(ejecucionprocesocalidadSet1NewEjecucionprocesocalidadToAttach);
            }
            ejecucionprocesocalidadSet1New = attachedEjecucionprocesocalidadSet1New;
            estadoejecucionprocesocalidad.setEjecucionprocesocalidadSet1(ejecucionprocesocalidadSet1New);
            estadoejecucionprocesocalidad = em.merge(estadoejecucionprocesocalidad);
            for (Ejecucionprocesocalidad ejecucionprocesocalidadSetOldEjecucionprocesocalidad : ejecucionprocesocalidadSetOld) {
                if (!ejecucionprocesocalidadSetNew.contains(ejecucionprocesocalidadSetOldEjecucionprocesocalidad)) {
                    ejecucionprocesocalidadSetOldEjecucionprocesocalidad.setEstado(null);
                    ejecucionprocesocalidadSetOldEjecucionprocesocalidad = em.merge(ejecucionprocesocalidadSetOldEjecucionprocesocalidad);
                }
            }
            for (Ejecucionprocesocalidad ejecucionprocesocalidadSetNewEjecucionprocesocalidad : ejecucionprocesocalidadSetNew) {
                if (!ejecucionprocesocalidadSetOld.contains(ejecucionprocesocalidadSetNewEjecucionprocesocalidad)) {
                    Estadoejecucionprocesocalidad oldEstadoOfEjecucionprocesocalidadSetNewEjecucionprocesocalidad = ejecucionprocesocalidadSetNewEjecucionprocesocalidad.getEstado();
                    ejecucionprocesocalidadSetNewEjecucionprocesocalidad.setEstado(estadoejecucionprocesocalidad);
                    ejecucionprocesocalidadSetNewEjecucionprocesocalidad = em.merge(ejecucionprocesocalidadSetNewEjecucionprocesocalidad);
                    if (oldEstadoOfEjecucionprocesocalidadSetNewEjecucionprocesocalidad != null && !oldEstadoOfEjecucionprocesocalidadSetNewEjecucionprocesocalidad.equals(estadoejecucionprocesocalidad)) {
                        oldEstadoOfEjecucionprocesocalidadSetNewEjecucionprocesocalidad.getEjecucionprocesocalidadSet().remove(ejecucionprocesocalidadSetNewEjecucionprocesocalidad);
                        oldEstadoOfEjecucionprocesocalidadSetNewEjecucionprocesocalidad = em.merge(oldEstadoOfEjecucionprocesocalidadSetNewEjecucionprocesocalidad);
                    }
                }
            }
            for (Ejecucionprocesocalidad ejecucionprocesocalidadSet1OldEjecucionprocesocalidad : ejecucionprocesocalidadSet1Old) {
                if (!ejecucionprocesocalidadSet1New.contains(ejecucionprocesocalidadSet1OldEjecucionprocesocalidad)) {
                    ejecucionprocesocalidadSet1OldEjecucionprocesocalidad.setEstado1(null);
                    ejecucionprocesocalidadSet1OldEjecucionprocesocalidad = em.merge(ejecucionprocesocalidadSet1OldEjecucionprocesocalidad);
                }
            }
            for (Ejecucionprocesocalidad ejecucionprocesocalidadSet1NewEjecucionprocesocalidad : ejecucionprocesocalidadSet1New) {
                if (!ejecucionprocesocalidadSet1Old.contains(ejecucionprocesocalidadSet1NewEjecucionprocesocalidad)) {
                    Estadoejecucionprocesocalidad oldEstado1OfEjecucionprocesocalidadSet1NewEjecucionprocesocalidad = ejecucionprocesocalidadSet1NewEjecucionprocesocalidad.getEstado1();
                    ejecucionprocesocalidadSet1NewEjecucionprocesocalidad.setEstado1(estadoejecucionprocesocalidad);
                    ejecucionprocesocalidadSet1NewEjecucionprocesocalidad = em.merge(ejecucionprocesocalidadSet1NewEjecucionprocesocalidad);
                    if (oldEstado1OfEjecucionprocesocalidadSet1NewEjecucionprocesocalidad != null && !oldEstado1OfEjecucionprocesocalidadSet1NewEjecucionprocesocalidad.equals(estadoejecucionprocesocalidad)) {
                        oldEstado1OfEjecucionprocesocalidadSet1NewEjecucionprocesocalidad.getEjecucionprocesocalidadSet1().remove(ejecucionprocesocalidadSet1NewEjecucionprocesocalidad);
                        oldEstado1OfEjecucionprocesocalidadSet1NewEjecucionprocesocalidad = em.merge(oldEstado1OfEjecucionprocesocalidadSet1NewEjecucionprocesocalidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadoejecucionprocesocalidad.getIdestado();
                if (findEstadoejecucionprocesocalidad(id) == null) {
                    throw new NonexistentEntityException("The estadoejecucionprocesocalidad with id " + id + " no longer exists.");
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
            Estadoejecucionprocesocalidad estadoejecucionprocesocalidad;
            try {
                estadoejecucionprocesocalidad = em.getReference(Estadoejecucionprocesocalidad.class, id);
                estadoejecucionprocesocalidad.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoejecucionprocesocalidad with id " + id + " no longer exists.", enfe);
            }
            Set<Ejecucionprocesocalidad> ejecucionprocesocalidadSet = estadoejecucionprocesocalidad.getEjecucionprocesocalidadSet();
            for (Ejecucionprocesocalidad ejecucionprocesocalidadSetEjecucionprocesocalidad : ejecucionprocesocalidadSet) {
                ejecucionprocesocalidadSetEjecucionprocesocalidad.setEstado(null);
                ejecucionprocesocalidadSetEjecucionprocesocalidad = em.merge(ejecucionprocesocalidadSetEjecucionprocesocalidad);
            }
            Set<Ejecucionprocesocalidad> ejecucionprocesocalidadSet1 = estadoejecucionprocesocalidad.getEjecucionprocesocalidadSet1();
            for (Ejecucionprocesocalidad ejecucionprocesocalidadSet1Ejecucionprocesocalidad : ejecucionprocesocalidadSet1) {
                ejecucionprocesocalidadSet1Ejecucionprocesocalidad.setEstado1(null);
                ejecucionprocesocalidadSet1Ejecucionprocesocalidad = em.merge(ejecucionprocesocalidadSet1Ejecucionprocesocalidad);
            }
            em.remove(estadoejecucionprocesocalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadoejecucionprocesocalidad> findEstadoejecucionprocesocalidadEntities() {
        return findEstadoejecucionprocesocalidadEntities(true, -1, -1);
    }

    public List<Estadoejecucionprocesocalidad> findEstadoejecucionprocesocalidadEntities(int maxResults, int firstResult) {
        return findEstadoejecucionprocesocalidadEntities(false, maxResults, firstResult);
    }

    private List<Estadoejecucionprocesocalidad> findEstadoejecucionprocesocalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadoejecucionprocesocalidad.class));
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

    public Estadoejecucionprocesocalidad findEstadoejecucionprocesocalidad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadoejecucionprocesocalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoejecucionprocesocalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadoejecucionprocesocalidad> rt = cq.from(Estadoejecucionprocesocalidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
