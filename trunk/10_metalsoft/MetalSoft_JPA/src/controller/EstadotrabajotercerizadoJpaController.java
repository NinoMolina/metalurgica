/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Estadotrabajotercerizado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Trabajotercerizado;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class EstadotrabajotercerizadoJpaController {

    public EstadotrabajotercerizadoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadotrabajotercerizado estadotrabajotercerizado) throws PreexistingEntityException, Exception {
        if (estadotrabajotercerizado.getTrabajotercerizadoSet() == null) {
            estadotrabajotercerizado.setTrabajotercerizadoSet(new HashSet<Trabajotercerizado>());
        }
        if (estadotrabajotercerizado.getTrabajotercerizadoSet1() == null) {
            estadotrabajotercerizado.setTrabajotercerizadoSet1(new HashSet<Trabajotercerizado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Trabajotercerizado> attachedTrabajotercerizadoSet = new HashSet<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoSetTrabajotercerizadoToAttach : estadotrabajotercerizado.getTrabajotercerizadoSet()) {
                trabajotercerizadoSetTrabajotercerizadoToAttach = em.getReference(trabajotercerizadoSetTrabajotercerizadoToAttach.getClass(), trabajotercerizadoSetTrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoSet.add(trabajotercerizadoSetTrabajotercerizadoToAttach);
            }
            estadotrabajotercerizado.setTrabajotercerizadoSet(attachedTrabajotercerizadoSet);
            Set<Trabajotercerizado> attachedTrabajotercerizadoSet1 = new HashSet<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoSet1TrabajotercerizadoToAttach : estadotrabajotercerizado.getTrabajotercerizadoSet1()) {
                trabajotercerizadoSet1TrabajotercerizadoToAttach = em.getReference(trabajotercerizadoSet1TrabajotercerizadoToAttach.getClass(), trabajotercerizadoSet1TrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoSet1.add(trabajotercerizadoSet1TrabajotercerizadoToAttach);
            }
            estadotrabajotercerizado.setTrabajotercerizadoSet1(attachedTrabajotercerizadoSet1);
            em.persist(estadotrabajotercerizado);
            for (Trabajotercerizado trabajotercerizadoSetTrabajotercerizado : estadotrabajotercerizado.getTrabajotercerizadoSet()) {
                Estadotrabajotercerizado oldEstadoOfTrabajotercerizadoSetTrabajotercerizado = trabajotercerizadoSetTrabajotercerizado.getEstado();
                trabajotercerizadoSetTrabajotercerizado.setEstado(estadotrabajotercerizado);
                trabajotercerizadoSetTrabajotercerizado = em.merge(trabajotercerizadoSetTrabajotercerizado);
                if (oldEstadoOfTrabajotercerizadoSetTrabajotercerizado != null) {
                    oldEstadoOfTrabajotercerizadoSetTrabajotercerizado.getTrabajotercerizadoSet().remove(trabajotercerizadoSetTrabajotercerizado);
                    oldEstadoOfTrabajotercerizadoSetTrabajotercerizado = em.merge(oldEstadoOfTrabajotercerizadoSetTrabajotercerizado);
                }
            }
            for (Trabajotercerizado trabajotercerizadoSet1Trabajotercerizado : estadotrabajotercerizado.getTrabajotercerizadoSet1()) {
                Estadotrabajotercerizado oldEstado1OfTrabajotercerizadoSet1Trabajotercerizado = trabajotercerizadoSet1Trabajotercerizado.getEstado1();
                trabajotercerizadoSet1Trabajotercerizado.setEstado1(estadotrabajotercerizado);
                trabajotercerizadoSet1Trabajotercerizado = em.merge(trabajotercerizadoSet1Trabajotercerizado);
                if (oldEstado1OfTrabajotercerizadoSet1Trabajotercerizado != null) {
                    oldEstado1OfTrabajotercerizadoSet1Trabajotercerizado.getTrabajotercerizadoSet1().remove(trabajotercerizadoSet1Trabajotercerizado);
                    oldEstado1OfTrabajotercerizadoSet1Trabajotercerizado = em.merge(oldEstado1OfTrabajotercerizadoSet1Trabajotercerizado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadotrabajotercerizado(estadotrabajotercerizado.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadotrabajotercerizado " + estadotrabajotercerizado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadotrabajotercerizado estadotrabajotercerizado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadotrabajotercerizado persistentEstadotrabajotercerizado = em.find(Estadotrabajotercerizado.class, estadotrabajotercerizado.getIdestado());
            Set<Trabajotercerizado> trabajotercerizadoSetOld = persistentEstadotrabajotercerizado.getTrabajotercerizadoSet();
            Set<Trabajotercerizado> trabajotercerizadoSetNew = estadotrabajotercerizado.getTrabajotercerizadoSet();
            Set<Trabajotercerizado> trabajotercerizadoSet1Old = persistentEstadotrabajotercerizado.getTrabajotercerizadoSet1();
            Set<Trabajotercerizado> trabajotercerizadoSet1New = estadotrabajotercerizado.getTrabajotercerizadoSet1();
            Set<Trabajotercerizado> attachedTrabajotercerizadoSetNew = new HashSet<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoSetNewTrabajotercerizadoToAttach : trabajotercerizadoSetNew) {
                trabajotercerizadoSetNewTrabajotercerizadoToAttach = em.getReference(trabajotercerizadoSetNewTrabajotercerizadoToAttach.getClass(), trabajotercerizadoSetNewTrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoSetNew.add(trabajotercerizadoSetNewTrabajotercerizadoToAttach);
            }
            trabajotercerizadoSetNew = attachedTrabajotercerizadoSetNew;
            estadotrabajotercerizado.setTrabajotercerizadoSet(trabajotercerizadoSetNew);
            Set<Trabajotercerizado> attachedTrabajotercerizadoSet1New = new HashSet<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoSet1NewTrabajotercerizadoToAttach : trabajotercerizadoSet1New) {
                trabajotercerizadoSet1NewTrabajotercerizadoToAttach = em.getReference(trabajotercerizadoSet1NewTrabajotercerizadoToAttach.getClass(), trabajotercerizadoSet1NewTrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoSet1New.add(trabajotercerizadoSet1NewTrabajotercerizadoToAttach);
            }
            trabajotercerizadoSet1New = attachedTrabajotercerizadoSet1New;
            estadotrabajotercerizado.setTrabajotercerizadoSet1(trabajotercerizadoSet1New);
            estadotrabajotercerizado = em.merge(estadotrabajotercerizado);
            for (Trabajotercerizado trabajotercerizadoSetOldTrabajotercerizado : trabajotercerizadoSetOld) {
                if (!trabajotercerizadoSetNew.contains(trabajotercerizadoSetOldTrabajotercerizado)) {
                    trabajotercerizadoSetOldTrabajotercerizado.setEstado(null);
                    trabajotercerizadoSetOldTrabajotercerizado = em.merge(trabajotercerizadoSetOldTrabajotercerizado);
                }
            }
            for (Trabajotercerizado trabajotercerizadoSetNewTrabajotercerizado : trabajotercerizadoSetNew) {
                if (!trabajotercerizadoSetOld.contains(trabajotercerizadoSetNewTrabajotercerizado)) {
                    Estadotrabajotercerizado oldEstadoOfTrabajotercerizadoSetNewTrabajotercerizado = trabajotercerizadoSetNewTrabajotercerizado.getEstado();
                    trabajotercerizadoSetNewTrabajotercerizado.setEstado(estadotrabajotercerizado);
                    trabajotercerizadoSetNewTrabajotercerizado = em.merge(trabajotercerizadoSetNewTrabajotercerizado);
                    if (oldEstadoOfTrabajotercerizadoSetNewTrabajotercerizado != null && !oldEstadoOfTrabajotercerizadoSetNewTrabajotercerizado.equals(estadotrabajotercerizado)) {
                        oldEstadoOfTrabajotercerizadoSetNewTrabajotercerizado.getTrabajotercerizadoSet().remove(trabajotercerizadoSetNewTrabajotercerizado);
                        oldEstadoOfTrabajotercerizadoSetNewTrabajotercerizado = em.merge(oldEstadoOfTrabajotercerizadoSetNewTrabajotercerizado);
                    }
                }
            }
            for (Trabajotercerizado trabajotercerizadoSet1OldTrabajotercerizado : trabajotercerizadoSet1Old) {
                if (!trabajotercerizadoSet1New.contains(trabajotercerizadoSet1OldTrabajotercerizado)) {
                    trabajotercerizadoSet1OldTrabajotercerizado.setEstado1(null);
                    trabajotercerizadoSet1OldTrabajotercerizado = em.merge(trabajotercerizadoSet1OldTrabajotercerizado);
                }
            }
            for (Trabajotercerizado trabajotercerizadoSet1NewTrabajotercerizado : trabajotercerizadoSet1New) {
                if (!trabajotercerizadoSet1Old.contains(trabajotercerizadoSet1NewTrabajotercerizado)) {
                    Estadotrabajotercerizado oldEstado1OfTrabajotercerizadoSet1NewTrabajotercerizado = trabajotercerizadoSet1NewTrabajotercerizado.getEstado1();
                    trabajotercerizadoSet1NewTrabajotercerizado.setEstado1(estadotrabajotercerizado);
                    trabajotercerizadoSet1NewTrabajotercerizado = em.merge(trabajotercerizadoSet1NewTrabajotercerizado);
                    if (oldEstado1OfTrabajotercerizadoSet1NewTrabajotercerizado != null && !oldEstado1OfTrabajotercerizadoSet1NewTrabajotercerizado.equals(estadotrabajotercerizado)) {
                        oldEstado1OfTrabajotercerizadoSet1NewTrabajotercerizado.getTrabajotercerizadoSet1().remove(trabajotercerizadoSet1NewTrabajotercerizado);
                        oldEstado1OfTrabajotercerizadoSet1NewTrabajotercerizado = em.merge(oldEstado1OfTrabajotercerizadoSet1NewTrabajotercerizado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadotrabajotercerizado.getIdestado();
                if (findEstadotrabajotercerizado(id) == null) {
                    throw new NonexistentEntityException("The estadotrabajotercerizado with id " + id + " no longer exists.");
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
            Estadotrabajotercerizado estadotrabajotercerizado;
            try {
                estadotrabajotercerizado = em.getReference(Estadotrabajotercerizado.class, id);
                estadotrabajotercerizado.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadotrabajotercerizado with id " + id + " no longer exists.", enfe);
            }
            Set<Trabajotercerizado> trabajotercerizadoSet = estadotrabajotercerizado.getTrabajotercerizadoSet();
            for (Trabajotercerizado trabajotercerizadoSetTrabajotercerizado : trabajotercerizadoSet) {
                trabajotercerizadoSetTrabajotercerizado.setEstado(null);
                trabajotercerizadoSetTrabajotercerizado = em.merge(trabajotercerizadoSetTrabajotercerizado);
            }
            Set<Trabajotercerizado> trabajotercerizadoSet1 = estadotrabajotercerizado.getTrabajotercerizadoSet1();
            for (Trabajotercerizado trabajotercerizadoSet1Trabajotercerizado : trabajotercerizadoSet1) {
                trabajotercerizadoSet1Trabajotercerizado.setEstado1(null);
                trabajotercerizadoSet1Trabajotercerizado = em.merge(trabajotercerizadoSet1Trabajotercerizado);
            }
            em.remove(estadotrabajotercerizado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadotrabajotercerizado> findEstadotrabajotercerizadoEntities() {
        return findEstadotrabajotercerizadoEntities(true, -1, -1);
    }

    public List<Estadotrabajotercerizado> findEstadotrabajotercerizadoEntities(int maxResults, int firstResult) {
        return findEstadotrabajotercerizadoEntities(false, maxResults, firstResult);
    }

    private List<Estadotrabajotercerizado> findEstadotrabajotercerizadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadotrabajotercerizado.class));
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

    public Estadotrabajotercerizado findEstadotrabajotercerizado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadotrabajotercerizado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadotrabajotercerizadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadotrabajotercerizado> rt = cq.from(Estadotrabajotercerizado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
