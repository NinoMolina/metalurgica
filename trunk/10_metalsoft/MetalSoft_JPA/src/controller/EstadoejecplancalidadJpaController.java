/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Estadoejecplancalidad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Ejecucionplanificacioncalidad;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class EstadoejecplancalidadJpaController {

    public EstadoejecplancalidadJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadoejecplancalidad estadoejecplancalidad) throws PreexistingEntityException, Exception {
        if (estadoejecplancalidad.getEjecucionplanificacioncalidadSet() == null) {
            estadoejecplancalidad.setEjecucionplanificacioncalidadSet(new HashSet<Ejecucionplanificacioncalidad>());
        }
        if (estadoejecplancalidad.getEjecucionplanificacioncalidadSet1() == null) {
            estadoejecplancalidad.setEjecucionplanificacioncalidadSet1(new HashSet<Ejecucionplanificacioncalidad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Ejecucionplanificacioncalidad> attachedEjecucionplanificacioncalidadSet = new HashSet<Ejecucionplanificacioncalidad>();
            for (Ejecucionplanificacioncalidad ejecucionplanificacioncalidadSetEjecucionplanificacioncalidadToAttach : estadoejecplancalidad.getEjecucionplanificacioncalidadSet()) {
                ejecucionplanificacioncalidadSetEjecucionplanificacioncalidadToAttach = em.getReference(ejecucionplanificacioncalidadSetEjecucionplanificacioncalidadToAttach.getClass(), ejecucionplanificacioncalidadSetEjecucionplanificacioncalidadToAttach.getEjecucionplanificacioncalidadPK());
                attachedEjecucionplanificacioncalidadSet.add(ejecucionplanificacioncalidadSetEjecucionplanificacioncalidadToAttach);
            }
            estadoejecplancalidad.setEjecucionplanificacioncalidadSet(attachedEjecucionplanificacioncalidadSet);
            Set<Ejecucionplanificacioncalidad> attachedEjecucionplanificacioncalidadSet1 = new HashSet<Ejecucionplanificacioncalidad>();
            for (Ejecucionplanificacioncalidad ejecucionplanificacioncalidadSet1EjecucionplanificacioncalidadToAttach : estadoejecplancalidad.getEjecucionplanificacioncalidadSet1()) {
                ejecucionplanificacioncalidadSet1EjecucionplanificacioncalidadToAttach = em.getReference(ejecucionplanificacioncalidadSet1EjecucionplanificacioncalidadToAttach.getClass(), ejecucionplanificacioncalidadSet1EjecucionplanificacioncalidadToAttach.getEjecucionplanificacioncalidadPK());
                attachedEjecucionplanificacioncalidadSet1.add(ejecucionplanificacioncalidadSet1EjecucionplanificacioncalidadToAttach);
            }
            estadoejecplancalidad.setEjecucionplanificacioncalidadSet1(attachedEjecucionplanificacioncalidadSet1);
            em.persist(estadoejecplancalidad);
            for (Ejecucionplanificacioncalidad ejecucionplanificacioncalidadSetEjecucionplanificacioncalidad : estadoejecplancalidad.getEjecucionplanificacioncalidadSet()) {
                Estadoejecplancalidad oldEstadoOfEjecucionplanificacioncalidadSetEjecucionplanificacioncalidad = ejecucionplanificacioncalidadSetEjecucionplanificacioncalidad.getEstado();
                ejecucionplanificacioncalidadSetEjecucionplanificacioncalidad.setEstado(estadoejecplancalidad);
                ejecucionplanificacioncalidadSetEjecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidadSetEjecucionplanificacioncalidad);
                if (oldEstadoOfEjecucionplanificacioncalidadSetEjecucionplanificacioncalidad != null) {
                    oldEstadoOfEjecucionplanificacioncalidadSetEjecucionplanificacioncalidad.getEjecucionplanificacioncalidadSet().remove(ejecucionplanificacioncalidadSetEjecucionplanificacioncalidad);
                    oldEstadoOfEjecucionplanificacioncalidadSetEjecucionplanificacioncalidad = em.merge(oldEstadoOfEjecucionplanificacioncalidadSetEjecucionplanificacioncalidad);
                }
            }
            for (Ejecucionplanificacioncalidad ejecucionplanificacioncalidadSet1Ejecucionplanificacioncalidad : estadoejecplancalidad.getEjecucionplanificacioncalidadSet1()) {
                Estadoejecplancalidad oldEstado1OfEjecucionplanificacioncalidadSet1Ejecucionplanificacioncalidad = ejecucionplanificacioncalidadSet1Ejecucionplanificacioncalidad.getEstado1();
                ejecucionplanificacioncalidadSet1Ejecucionplanificacioncalidad.setEstado1(estadoejecplancalidad);
                ejecucionplanificacioncalidadSet1Ejecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidadSet1Ejecucionplanificacioncalidad);
                if (oldEstado1OfEjecucionplanificacioncalidadSet1Ejecucionplanificacioncalidad != null) {
                    oldEstado1OfEjecucionplanificacioncalidadSet1Ejecucionplanificacioncalidad.getEjecucionplanificacioncalidadSet1().remove(ejecucionplanificacioncalidadSet1Ejecucionplanificacioncalidad);
                    oldEstado1OfEjecucionplanificacioncalidadSet1Ejecucionplanificacioncalidad = em.merge(oldEstado1OfEjecucionplanificacioncalidadSet1Ejecucionplanificacioncalidad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoejecplancalidad(estadoejecplancalidad.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadoejecplancalidad " + estadoejecplancalidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadoejecplancalidad estadoejecplancalidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoejecplancalidad persistentEstadoejecplancalidad = em.find(Estadoejecplancalidad.class, estadoejecplancalidad.getIdestado());
            Set<Ejecucionplanificacioncalidad> ejecucionplanificacioncalidadSetOld = persistentEstadoejecplancalidad.getEjecucionplanificacioncalidadSet();
            Set<Ejecucionplanificacioncalidad> ejecucionplanificacioncalidadSetNew = estadoejecplancalidad.getEjecucionplanificacioncalidadSet();
            Set<Ejecucionplanificacioncalidad> ejecucionplanificacioncalidadSet1Old = persistentEstadoejecplancalidad.getEjecucionplanificacioncalidadSet1();
            Set<Ejecucionplanificacioncalidad> ejecucionplanificacioncalidadSet1New = estadoejecplancalidad.getEjecucionplanificacioncalidadSet1();
            Set<Ejecucionplanificacioncalidad> attachedEjecucionplanificacioncalidadSetNew = new HashSet<Ejecucionplanificacioncalidad>();
            for (Ejecucionplanificacioncalidad ejecucionplanificacioncalidadSetNewEjecucionplanificacioncalidadToAttach : ejecucionplanificacioncalidadSetNew) {
                ejecucionplanificacioncalidadSetNewEjecucionplanificacioncalidadToAttach = em.getReference(ejecucionplanificacioncalidadSetNewEjecucionplanificacioncalidadToAttach.getClass(), ejecucionplanificacioncalidadSetNewEjecucionplanificacioncalidadToAttach.getEjecucionplanificacioncalidadPK());
                attachedEjecucionplanificacioncalidadSetNew.add(ejecucionplanificacioncalidadSetNewEjecucionplanificacioncalidadToAttach);
            }
            ejecucionplanificacioncalidadSetNew = attachedEjecucionplanificacioncalidadSetNew;
            estadoejecplancalidad.setEjecucionplanificacioncalidadSet(ejecucionplanificacioncalidadSetNew);
            Set<Ejecucionplanificacioncalidad> attachedEjecucionplanificacioncalidadSet1New = new HashSet<Ejecucionplanificacioncalidad>();
            for (Ejecucionplanificacioncalidad ejecucionplanificacioncalidadSet1NewEjecucionplanificacioncalidadToAttach : ejecucionplanificacioncalidadSet1New) {
                ejecucionplanificacioncalidadSet1NewEjecucionplanificacioncalidadToAttach = em.getReference(ejecucionplanificacioncalidadSet1NewEjecucionplanificacioncalidadToAttach.getClass(), ejecucionplanificacioncalidadSet1NewEjecucionplanificacioncalidadToAttach.getEjecucionplanificacioncalidadPK());
                attachedEjecucionplanificacioncalidadSet1New.add(ejecucionplanificacioncalidadSet1NewEjecucionplanificacioncalidadToAttach);
            }
            ejecucionplanificacioncalidadSet1New = attachedEjecucionplanificacioncalidadSet1New;
            estadoejecplancalidad.setEjecucionplanificacioncalidadSet1(ejecucionplanificacioncalidadSet1New);
            estadoejecplancalidad = em.merge(estadoejecplancalidad);
            for (Ejecucionplanificacioncalidad ejecucionplanificacioncalidadSetOldEjecucionplanificacioncalidad : ejecucionplanificacioncalidadSetOld) {
                if (!ejecucionplanificacioncalidadSetNew.contains(ejecucionplanificacioncalidadSetOldEjecucionplanificacioncalidad)) {
                    ejecucionplanificacioncalidadSetOldEjecucionplanificacioncalidad.setEstado(null);
                    ejecucionplanificacioncalidadSetOldEjecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidadSetOldEjecucionplanificacioncalidad);
                }
            }
            for (Ejecucionplanificacioncalidad ejecucionplanificacioncalidadSetNewEjecucionplanificacioncalidad : ejecucionplanificacioncalidadSetNew) {
                if (!ejecucionplanificacioncalidadSetOld.contains(ejecucionplanificacioncalidadSetNewEjecucionplanificacioncalidad)) {
                    Estadoejecplancalidad oldEstadoOfEjecucionplanificacioncalidadSetNewEjecucionplanificacioncalidad = ejecucionplanificacioncalidadSetNewEjecucionplanificacioncalidad.getEstado();
                    ejecucionplanificacioncalidadSetNewEjecucionplanificacioncalidad.setEstado(estadoejecplancalidad);
                    ejecucionplanificacioncalidadSetNewEjecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidadSetNewEjecucionplanificacioncalidad);
                    if (oldEstadoOfEjecucionplanificacioncalidadSetNewEjecucionplanificacioncalidad != null && !oldEstadoOfEjecucionplanificacioncalidadSetNewEjecucionplanificacioncalidad.equals(estadoejecplancalidad)) {
                        oldEstadoOfEjecucionplanificacioncalidadSetNewEjecucionplanificacioncalidad.getEjecucionplanificacioncalidadSet().remove(ejecucionplanificacioncalidadSetNewEjecucionplanificacioncalidad);
                        oldEstadoOfEjecucionplanificacioncalidadSetNewEjecucionplanificacioncalidad = em.merge(oldEstadoOfEjecucionplanificacioncalidadSetNewEjecucionplanificacioncalidad);
                    }
                }
            }
            for (Ejecucionplanificacioncalidad ejecucionplanificacioncalidadSet1OldEjecucionplanificacioncalidad : ejecucionplanificacioncalidadSet1Old) {
                if (!ejecucionplanificacioncalidadSet1New.contains(ejecucionplanificacioncalidadSet1OldEjecucionplanificacioncalidad)) {
                    ejecucionplanificacioncalidadSet1OldEjecucionplanificacioncalidad.setEstado1(null);
                    ejecucionplanificacioncalidadSet1OldEjecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidadSet1OldEjecucionplanificacioncalidad);
                }
            }
            for (Ejecucionplanificacioncalidad ejecucionplanificacioncalidadSet1NewEjecucionplanificacioncalidad : ejecucionplanificacioncalidadSet1New) {
                if (!ejecucionplanificacioncalidadSet1Old.contains(ejecucionplanificacioncalidadSet1NewEjecucionplanificacioncalidad)) {
                    Estadoejecplancalidad oldEstado1OfEjecucionplanificacioncalidadSet1NewEjecucionplanificacioncalidad = ejecucionplanificacioncalidadSet1NewEjecucionplanificacioncalidad.getEstado1();
                    ejecucionplanificacioncalidadSet1NewEjecucionplanificacioncalidad.setEstado1(estadoejecplancalidad);
                    ejecucionplanificacioncalidadSet1NewEjecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidadSet1NewEjecucionplanificacioncalidad);
                    if (oldEstado1OfEjecucionplanificacioncalidadSet1NewEjecucionplanificacioncalidad != null && !oldEstado1OfEjecucionplanificacioncalidadSet1NewEjecucionplanificacioncalidad.equals(estadoejecplancalidad)) {
                        oldEstado1OfEjecucionplanificacioncalidadSet1NewEjecucionplanificacioncalidad.getEjecucionplanificacioncalidadSet1().remove(ejecucionplanificacioncalidadSet1NewEjecucionplanificacioncalidad);
                        oldEstado1OfEjecucionplanificacioncalidadSet1NewEjecucionplanificacioncalidad = em.merge(oldEstado1OfEjecucionplanificacioncalidadSet1NewEjecucionplanificacioncalidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadoejecplancalidad.getIdestado();
                if (findEstadoejecplancalidad(id) == null) {
                    throw new NonexistentEntityException("The estadoejecplancalidad with id " + id + " no longer exists.");
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
            Estadoejecplancalidad estadoejecplancalidad;
            try {
                estadoejecplancalidad = em.getReference(Estadoejecplancalidad.class, id);
                estadoejecplancalidad.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoejecplancalidad with id " + id + " no longer exists.", enfe);
            }
            Set<Ejecucionplanificacioncalidad> ejecucionplanificacioncalidadSet = estadoejecplancalidad.getEjecucionplanificacioncalidadSet();
            for (Ejecucionplanificacioncalidad ejecucionplanificacioncalidadSetEjecucionplanificacioncalidad : ejecucionplanificacioncalidadSet) {
                ejecucionplanificacioncalidadSetEjecucionplanificacioncalidad.setEstado(null);
                ejecucionplanificacioncalidadSetEjecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidadSetEjecucionplanificacioncalidad);
            }
            Set<Ejecucionplanificacioncalidad> ejecucionplanificacioncalidadSet1 = estadoejecplancalidad.getEjecucionplanificacioncalidadSet1();
            for (Ejecucionplanificacioncalidad ejecucionplanificacioncalidadSet1Ejecucionplanificacioncalidad : ejecucionplanificacioncalidadSet1) {
                ejecucionplanificacioncalidadSet1Ejecucionplanificacioncalidad.setEstado1(null);
                ejecucionplanificacioncalidadSet1Ejecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidadSet1Ejecucionplanificacioncalidad);
            }
            em.remove(estadoejecplancalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadoejecplancalidad> findEstadoejecplancalidadEntities() {
        return findEstadoejecplancalidadEntities(true, -1, -1);
    }

    public List<Estadoejecplancalidad> findEstadoejecplancalidadEntities(int maxResults, int firstResult) {
        return findEstadoejecplancalidadEntities(false, maxResults, firstResult);
    }

    private List<Estadoejecplancalidad> findEstadoejecplancalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadoejecplancalidad.class));
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

    public Estadoejecplancalidad findEstadoejecplancalidad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadoejecplancalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoejecplancalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadoejecplancalidad> rt = cq.from(Estadoejecplancalidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
