/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos.jpa.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Ejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.EjecucionplanificacioncalidadPK;
import metalsoft.datos.jpa.entity.Estadoejecplancalidad;
import metalsoft.datos.jpa.entity.Planificacioncalidad;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacioncalidad;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class EjecucionplanificacioncalidadJpaController {

    public EjecucionplanificacioncalidadJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ejecucionplanificacioncalidad ejecucionplanificacioncalidad) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (ejecucionplanificacioncalidad.getEjecucionplanificacioncalidadPK() == null) {
            ejecucionplanificacioncalidad.setEjecucionplanificacioncalidadPK(new EjecucionplanificacioncalidadPK());
        }
        if (ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList() == null) {
            ejecucionplanificacioncalidad.setDetalleejecucionplanificacioncalidadList(new ArrayList<Detalleejecucionplanificacioncalidad>());
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
            List<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadList = new ArrayList<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach : ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList()) {
                detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach.getDetalleejecucionplanificacioncalidadPK());
                attachedDetalleejecucionplanificacioncalidadList.add(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidadToAttach);
            }
            ejecucionplanificacioncalidad.setDetalleejecucionplanificacioncalidadList(attachedDetalleejecucionplanificacioncalidadList);
            em.persist(ejecucionplanificacioncalidad);
            if (estado != null) {
                estado.getEjecucionplanificacioncalidadList().add(ejecucionplanificacioncalidad);
                estado = em.merge(estado);
            }
            if (planificacioncalidad != null) {
                planificacioncalidad.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidad);
                planificacioncalidad = em.merge(planificacioncalidad);
            }
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad : ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList()) {
                Ejecucionplanificacioncalidad oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad();
                detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidad);
                detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad);
                if (oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad != null) {
                    oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad);
                    oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad = em.merge(oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListDetalleejecucionplanificacioncalidad);
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
            List<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadListOld = persistentEjecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList();
            List<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadListNew = ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList();
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
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListOldDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadListOld) {
                if (!detalleejecucionplanificacioncalidadListNew.contains(detalleejecucionplanificacioncalidadListOldDetalleejecucionplanificacioncalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleejecucionplanificacioncalidad " + detalleejecucionplanificacioncalidadListOldDetalleejecucionplanificacioncalidad + " since its ejecucionplanificacioncalidad field is not nullable.");
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
            List<Detalleejecucionplanificacioncalidad> attachedDetalleejecucionplanificacioncalidadListNew = new ArrayList<Detalleejecucionplanificacioncalidad>();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach : detalleejecucionplanificacioncalidadListNew) {
                detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach = em.getReference(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach.getClass(), detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach.getDetalleejecucionplanificacioncalidadPK());
                attachedDetalleejecucionplanificacioncalidadListNew.add(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidadToAttach);
            }
            detalleejecucionplanificacioncalidadListNew = attachedDetalleejecucionplanificacioncalidadListNew;
            ejecucionplanificacioncalidad.setDetalleejecucionplanificacioncalidadList(detalleejecucionplanificacioncalidadListNew);
            ejecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidad);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getEjecucionplanificacioncalidadList().remove(ejecucionplanificacioncalidad);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getEjecucionplanificacioncalidadList().add(ejecucionplanificacioncalidad);
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
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadListNew) {
                if (!detalleejecucionplanificacioncalidadListOld.contains(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad)) {
                    Ejecucionplanificacioncalidad oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad.getEjecucionplanificacioncalidad();
                    detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidad);
                    detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad);
                    if (oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad != null && !oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad.equals(ejecucionplanificacioncalidad)) {
                        oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad);
                        oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad = em.merge(oldEjecucionplanificacioncalidadOfDetalleejecucionplanificacioncalidadListNewDetalleejecucionplanificacioncalidad);
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
            List<Detalleejecucionplanificacioncalidad> detalleejecucionplanificacioncalidadListOrphanCheck = ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList();
            for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadListOrphanCheckDetalleejecucionplanificacioncalidad : detalleejecucionplanificacioncalidadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ejecucionplanificacioncalidad (" + ejecucionplanificacioncalidad + ") cannot be destroyed since the Detalleejecucionplanificacioncalidad " + detalleejecucionplanificacioncalidadListOrphanCheckDetalleejecucionplanificacioncalidad + " in its detalleejecucionplanificacioncalidadList field has a non-nullable ejecucionplanificacioncalidad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Estadoejecplancalidad estado = ejecucionplanificacioncalidad.getEstado();
            if (estado != null) {
                estado.getEjecucionplanificacioncalidadList().remove(ejecucionplanificacioncalidad);
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
