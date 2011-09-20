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
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Estadotrabajotercerizado;
import metalsoft.datos.jpa.entity.Trabajotercerizado;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class EstadotrabajotercerizadoJpaController implements Serializable {

    public EstadotrabajotercerizadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadotrabajotercerizado estadotrabajotercerizado) throws PreexistingEntityException, Exception {
        if (estadotrabajotercerizado.getTrabajotercerizadoList() == null) {
            estadotrabajotercerizado.setTrabajotercerizadoList(new ArrayList<Trabajotercerizado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Trabajotercerizado> attachedTrabajotercerizadoList = new ArrayList<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoListTrabajotercerizadoToAttach : estadotrabajotercerizado.getTrabajotercerizadoList()) {
                trabajotercerizadoListTrabajotercerizadoToAttach = em.getReference(trabajotercerizadoListTrabajotercerizadoToAttach.getClass(), trabajotercerizadoListTrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoList.add(trabajotercerizadoListTrabajotercerizadoToAttach);
            }
            estadotrabajotercerizado.setTrabajotercerizadoList(attachedTrabajotercerizadoList);
            em.persist(estadotrabajotercerizado);
            for (Trabajotercerizado trabajotercerizadoListTrabajotercerizado : estadotrabajotercerizado.getTrabajotercerizadoList()) {
                Estadotrabajotercerizado oldEstadoOfTrabajotercerizadoListTrabajotercerizado = trabajotercerizadoListTrabajotercerizado.getEstado();
                trabajotercerizadoListTrabajotercerizado.setEstado(estadotrabajotercerizado);
                trabajotercerizadoListTrabajotercerizado = em.merge(trabajotercerizadoListTrabajotercerizado);
                if (oldEstadoOfTrabajotercerizadoListTrabajotercerizado != null) {
                    oldEstadoOfTrabajotercerizadoListTrabajotercerizado.getTrabajotercerizadoList().remove(trabajotercerizadoListTrabajotercerizado);
                    oldEstadoOfTrabajotercerizadoListTrabajotercerizado = em.merge(oldEstadoOfTrabajotercerizadoListTrabajotercerizado);
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
            List<Trabajotercerizado> trabajotercerizadoListOld = persistentEstadotrabajotercerizado.getTrabajotercerizadoList();
            List<Trabajotercerizado> trabajotercerizadoListNew = estadotrabajotercerizado.getTrabajotercerizadoList();
            List<Trabajotercerizado> attachedTrabajotercerizadoListNew = new ArrayList<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoListNewTrabajotercerizadoToAttach : trabajotercerizadoListNew) {
                trabajotercerizadoListNewTrabajotercerizadoToAttach = em.getReference(trabajotercerizadoListNewTrabajotercerizadoToAttach.getClass(), trabajotercerizadoListNewTrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoListNew.add(trabajotercerizadoListNewTrabajotercerizadoToAttach);
            }
            trabajotercerizadoListNew = attachedTrabajotercerizadoListNew;
            estadotrabajotercerizado.setTrabajotercerizadoList(trabajotercerizadoListNew);
            estadotrabajotercerizado = em.merge(estadotrabajotercerizado);
            for (Trabajotercerizado trabajotercerizadoListOldTrabajotercerizado : trabajotercerizadoListOld) {
                if (!trabajotercerizadoListNew.contains(trabajotercerizadoListOldTrabajotercerizado)) {
                    trabajotercerizadoListOldTrabajotercerizado.setEstado(null);
                    trabajotercerizadoListOldTrabajotercerizado = em.merge(trabajotercerizadoListOldTrabajotercerizado);
                }
            }
            for (Trabajotercerizado trabajotercerizadoListNewTrabajotercerizado : trabajotercerizadoListNew) {
                if (!trabajotercerizadoListOld.contains(trabajotercerizadoListNewTrabajotercerizado)) {
                    Estadotrabajotercerizado oldEstadoOfTrabajotercerizadoListNewTrabajotercerizado = trabajotercerizadoListNewTrabajotercerizado.getEstado();
                    trabajotercerizadoListNewTrabajotercerizado.setEstado(estadotrabajotercerizado);
                    trabajotercerizadoListNewTrabajotercerizado = em.merge(trabajotercerizadoListNewTrabajotercerizado);
                    if (oldEstadoOfTrabajotercerizadoListNewTrabajotercerizado != null && !oldEstadoOfTrabajotercerizadoListNewTrabajotercerizado.equals(estadotrabajotercerizado)) {
                        oldEstadoOfTrabajotercerizadoListNewTrabajotercerizado.getTrabajotercerizadoList().remove(trabajotercerizadoListNewTrabajotercerizado);
                        oldEstadoOfTrabajotercerizadoListNewTrabajotercerizado = em.merge(oldEstadoOfTrabajotercerizadoListNewTrabajotercerizado);
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
            List<Trabajotercerizado> trabajotercerizadoList = estadotrabajotercerizado.getTrabajotercerizadoList();
            for (Trabajotercerizado trabajotercerizadoListTrabajotercerizado : trabajotercerizadoList) {
                trabajotercerizadoListTrabajotercerizado.setEstado(null);
                trabajotercerizadoListTrabajotercerizado = em.merge(trabajotercerizadoListTrabajotercerizado);
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
