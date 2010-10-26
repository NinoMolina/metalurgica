/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Ejecucionplanificacioncalidad;
import entity.EjecucionplanificacioncalidadPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Estadoejecplancalidad;
import entity.Planificacioncalidad;
import entity.Detalleejecucionplanificacioncalidad;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class EjecucionplanificacioncalidadJpaController {

    public EjecucionplanificacioncalidadJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ejecucionplanificacioncalidad ejecucionplanificacioncalidad) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (ejecucionplanificacioncalidad.getEjecucionplanificacioncalidadPK() == null) {
            ejecucionplanificacioncalidad.setEjecucionplanificacioncalidadPK(new EjecucionplanificacioncalidadPK());
        }
        if (ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet() == null) {
            ejecucionplanificacioncalidad.setDetalleejecucionplanificacioncalidadSet(new HashSet<Detalleejecucionplanificacioncalidad>());
        }
        ejecucionplanificacioncalidad.getEjecucionplanificacioncalidadPK().setIdplanificacioncalidad(ejecucionplanificacioncalidad.getPlanificacioncalidad().getIdplanificacion());
        List<String> illegalOrphanMessages = null;
        Planificacioncalidad planificacioncalidadOrphanCheck = ejecucionplanificacioncalidad.getPlanificacioncalidad();
        if (planificacioncalidadOrphanCheck != null) {
            Ejecucionplanificacioncalidad oldEjecucionplanificacioncalidadOfPlanificacioncalidad = planificacioncalidadOrphanCheck.getEjecucionplanificacioncalidad();
            if (oldEjecucionplanificacioncalidadOfPlanificacioncalidad != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Planificacioncalidad " + planificacioncalidadOrphanCheck + " already has an item of type Ejecucionplanificacioncalidad whose planificacioncalidad column cannot be null. Please make another selection for the planificacioncalidad field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoejecplancalidad estado = ejecucionplanificacioncalidad.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                ejecucionplanificacioncalidad.setEstado(estado);
            }
            Planificacioncalidad planificacioncalidad = ejecucionplanificacioncalidad.getPlanificacioncalidad();
            if (planificacioncalidad != null) {
                planificacioncalidad = em.getReference(planificacioncalidad.getClass(), planificacioncalidad.getIdplanificacion());
                ejecucionplanificacioncalidad.setPlanificacioncalidad(planificacioncalidad);
            }
            Set<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadSet = new HashSet<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidadToAttach : ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet()) {
                detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidadToAttach.getDetalleejecucionplanificacioncalidadPK());
                attachedDetalleejecucionplanificacioncalidadSet.add(detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidadToAttach);
            }
            ejecucionplanificacioncalidad.setDetalleejecucionplanificacioncalidadSet(attachedDetalleejecucionplanificacioncalidadSet);
            em.persist(ejecucionplanificacioncalidad);
            if (estado != null) {
                estado.getEjecucionplanificacioncalidadSet().add(ejecucionplanificacioncalidad);
                estado = em.merge(estado);
            }
            if (planificacioncalidad != null) {
                planificacioncalidad.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidad);
                planificacioncalidad = em.merge(planificacioncalidad);
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad : ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet()) {
                Ejecucionplanificacioncalidad oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad();
                detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidad);
                detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad);
                if (oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad != null) {
                    oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet().remove(detalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad);
                    oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad = em.merge(oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetDetalleejecucionplanificacioncalidad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEjecucionplanificacioncalidad(ejecucionplanificacioncalidad.getEjecucionplanificacioncalidadPK()) != null) {
                throw new PreexistingEntityException("Ejecucionplanificacioncalidad " + ejecucionplanificacioncalidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ejecucionplanificacioncalidad ejecucionplanificacioncalidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        ejecucionplanificacioncalidad.getEjecucionplanificacioncalidadPK().setIdplanificacioncalidad(ejecucionplanificacioncalidad.getPlanificacioncalidad().getIdplanificacion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionplanificacioncalidad persistentEjecucionplanificacioncalidad = em.find(Ejecucionplanificacioncalidad.class, ejecucionplanificacioncalidad.getEjecucionplanificacioncalidadPK());
            Estadoejecplancalidad estadoOld = persistentEjecucionplanificacioncalidad.getEstado();
            Estadoejecplancalidad estadoNew = ejecucionplanificacioncalidad.getEstado();
            Planificacioncalidad planificacioncalidadOld = persistentEjecucionplanificacioncalidad.getPlanificacioncalidad();
            Planificacioncalidad planificacioncalidadNew = ejecucionplanificacioncalidad.getPlanificacioncalidad();
            Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSetOld = persistentEjecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet();
            Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSetNew = ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet();
            List<String> illegalOrphanMessages = null;
            if (planificacioncalidadNew != null && !planificacioncalidadNew.equals(planificacioncalidadOld)) {
                Ejecucionplanificacioncalidad oldEjecucionplanificacioncalidadOfPlanificacioncalidad = planificacioncalidadNew.getEjecucionplanificacioncalidad();
                if (oldEjecucionplanificacioncalidadOfPlanificacioncalidad != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Planificacioncalidad " + planificacioncalidadNew + " already has an item of type Ejecucionplanificacioncalidad whose planificacioncalidad column cannot be null. Please make another selection for the planificacioncalidad field.");
                }
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSetOldDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadSetOld) {
                if (!detalleejecucionplanificacioncalidadSetNew.contains(detalleejecucionplanificacioncalidadSetOldDetalleejecucionplanificacioncalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleejecucionplanificacioncalidad " + detalleejecucionplanificacioncalidadSetOldDetalleejecucionplanificacioncalidad + " since its ejecucionplanificacioncalidad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                ejecucionplanificacioncalidad.setEstado(estadoNew);
            }
            if (planificacioncalidadNew != null) {
                planificacioncalidadNew = em.getReference(planificacioncalidadNew.getClass(), planificacioncalidadNew.getIdplanificacion());
                ejecucionplanificacioncalidad.setPlanificacioncalidad(planificacioncalidadNew);
            }
            Set<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadSetNew = new HashSet<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidadToAttach : detalleejecucionplanificacioncalidadSetNew) {
                detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidadToAttach.getDetalleejecucionplanificacioncalidadPK());
                attachedDetalleejecucionplanificacioncalidadSetNew.add(detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidadToAttach);
            }
            detalleejecucionplanificacioncalidadSetNew = attachedDetalleejecucionplanificacioncalidadSetNew;
            ejecucionplanificacioncalidad.setDetalleejecucionplanificacioncalidadSet(detalleejecucionplanificacioncalidadSetNew);
            ejecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidad);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getEjecucionplanificacioncalidadSet().remove(ejecucionplanificacioncalidad);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getEjecucionplanificacioncalidadSet().add(ejecucionplanificacioncalidad);
                estadoNew = em.merge(estadoNew);
            }
            if (planificacioncalidadOld != null && !planificacioncalidadOld.equals(planificacioncalidadNew)) {
                planificacioncalidadOld.setEjecucionplanificacioncalidad(null);
                planificacioncalidadOld = em.merge(planificacioncalidadOld);
            }
            if (planificacioncalidadNew != null && !planificacioncalidadNew.equals(planificacioncalidadOld)) {
                planificacioncalidadNew.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidad);
                planificacioncalidadNew = em.merge(planificacioncalidadNew);
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadSetNew) {
                if (!detalleejecucionplanificacioncalidadSetOld.contains(detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad)) {
                    Ejecucionplanificacioncalidad oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad();
                    detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidad);
                    detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad);
                    if (oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad != null && !oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad.equals(ejecucionplanificacioncalidad)) {
                        oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet().remove(detalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad);
                        oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad = em.merge(oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadSetNewDetalleejecucionplanificacioncalidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                EjecucionplanificacioncalidadPK id = ejecucionplanificacioncalidad.getEjecucionplanificacioncalidadPK();
                if (findEjecucionplanificacioncalidad(id) == null) {
                    throw new NonexistentEntityException("The ejecucionplanificacioncalidad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(EjecucionplanificacioncalidadPK id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidad;
            try {
                ejecucionplanificacioncalidad = em.getReference(Ejecucionplanificacioncalidad.class, id);
                ejecucionplanificacioncalidad.getEjecucionplanificacioncalidadPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ejecucionplanificacioncalidad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadSetOrphanCheck = ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadSet();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadSetOrphanCheckDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ejecucionplanificacioncalidad (" + ejecucionplanificacioncalidad + ") cannot be destroyed since the Detalleejecucionplanificacioncalidad " + detalleejecucionplanificacioncalidadSetOrphanCheckDetalleejecucionplanificacioncalidad + " in its detalleejecucionplanificacioncalidadSet field has a non-nullable ejecucionplanificacioncalidad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Estadoejecplancalidad estado = ejecucionplanificacioncalidad.getEstado();
            if (estado != null) {
                estado.getEjecucionplanificacioncalidadSet().remove(ejecucionplanificacioncalidad);
                estado = em.merge(estado);
            }
            Planificacioncalidad planificacioncalidad = ejecucionplanificacioncalidad.getPlanificacioncalidad();
            if (planificacioncalidad != null) {
                planificacioncalidad.setEjecucionplanificacioncalidad(null);
                planificacioncalidad = em.merge(planificacioncalidad);
            }
            em.remove(ejecucionplanificacioncalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ejecucionplanificacioncalidad> findEjecucionplanificacioncalidadEntities() {
        return findEjecucionplanificacioncalidadEntities(true, -1, -1);
    }

    public List<Ejecucionplanificacioncalidad> findEjecucionplanificacioncalidadEntities(int maxResults, int firstResult) {
        return findEjecucionplanificacioncalidadEntities(false, maxResults, firstResult);
    }

    private List<Ejecucionplanificacioncalidad> findEjecucionplanificacioncalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ejecucionplanificacioncalidad.class));
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

    public Ejecucionplanificacioncalidad findEjecucionplanificacioncalidad(EjecucionplanificacioncalidadPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ejecucionplanificacioncalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getEjecucionplanificacioncalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ejecucionplanificacioncalidad> rt = cq.from(Ejecucionplanificacioncalidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
