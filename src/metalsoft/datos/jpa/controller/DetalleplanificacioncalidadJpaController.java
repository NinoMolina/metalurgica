/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos.jpa.controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;
import metalsoft.datos.jpa.entity.DetalleplanificacioncalidadPK;
import metalsoft.datos.jpa.entity.Planificacioncalidad;
import metalsoft.datos.jpa.entity.Procesocalidad;

/**
 *
 * @author Nino
 */
public class DetalleplanificacioncalidadJpaController {

    public DetalleplanificacioncalidadJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleplanificacioncalidad detalleplanificacioncalidad) throws PreexistingEntityException, Exception {
        if (detalleplanificacioncalidad.getDetalleplanificacioncalidadPK() == null) {
            detalleplanificacioncalidad.setDetalleplanificacioncalidadPK(new DetalleplanificacioncalidadPK());
        }
        detalleplanificacioncalidad.getDetalleplanificacioncalidadPK().setIdplanificacioncalidad(detalleplanificacioncalidad.getPlanificacioncalidad().getIdplanificacion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad = detalleplanificacioncalidad.getDetalleejecucionplanificacioncalidad();
            if (detalleejecucionplanificacioncalidad != null) {
                detalleejecucionplanificacioncalidad = em.getReference(detalleejecucionplanificacioncalidad.getClass(), detalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadPK());
                detalleplanificacioncalidad.setDetalleejecucionplanificacioncalidad(detalleejecucionplanificacioncalidad);
            }
            Planificacioncalidad planificacioncalidad = detalleplanificacioncalidad.getPlanificacioncalidad();
            if (planificacioncalidad != null) {
                planificacioncalidad = em.getReference(planificacioncalidad.getClass(), planificacioncalidad.getIdplanificacion());
                detalleplanificacioncalidad.setPlanificacioncalidad(planificacioncalidad);
            }
            Procesocalidad procesocalidad = detalleplanificacioncalidad.getProcesocalidad();
            if (procesocalidad != null) {
                procesocalidad = em.getReference(procesocalidad.getClass(), procesocalidad.getIdprocesocalidad());
                detalleplanificacioncalidad.setProcesocalidad(procesocalidad);
            }
            em.persist(detalleplanificacioncalidad);
            if (detalleejecucionplanificacioncalidad != null) {
                detalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadList().add(detalleplanificacioncalidad);
                detalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidad);
            }
            if (planificacioncalidad != null) {
                planificacioncalidad.getDetalleplanificacioncalidadList().add(detalleplanificacioncalidad);
                planificacioncalidad = em.merge(planificacioncalidad);
            }
            if (procesocalidad != null) {
                procesocalidad.getDetalleplanificacioncalidadList().add(detalleplanificacioncalidad);
                procesocalidad = em.merge(procesocalidad);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleplanificacioncalidad(detalleplanificacioncalidad.getDetalleplanificacioncalidadPK()) != null) {
                throw new PreexistingEntityException("Detalleplanificacioncalidad " + detalleplanificacioncalidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleplanificacioncalidad detalleplanificacioncalidad) throws NonexistentEntityException, Exception {
        detalleplanificacioncalidad.getDetalleplanificacioncalidadPK().setIdplanificacioncalidad(detalleplanificacioncalidad.getPlanificacioncalidad().getIdplanificacion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleplanificacioncalidad persistentDetalleplanificacioncalidad = em.find(Detalleplanificacioncalidad.class, detalleplanificacioncalidad.getDetalleplanificacioncalidadPK());
            Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadOld = persistentDetalleplanificacioncalidad.getDetalleejecucionplanificacioncalidad();
            Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadNew = detalleplanificacioncalidad.getDetalleejecucionplanificacioncalidad();
            Planificacioncalidad planificacioncalidadOld = persistentDetalleplanificacioncalidad.getPlanificacioncalidad();
            Planificacioncalidad planificacioncalidadNew = detalleplanificacioncalidad.getPlanificacioncalidad();
            Procesocalidad procesocalidadOld = persistentDetalleplanificacioncalidad.getProcesocalidad();
            Procesocalidad procesocalidadNew = detalleplanificacioncalidad.getProcesocalidad();
            if (detalleejecucionplanificacioncalidadNew != null) {
                detalleejecucionplanificacioncalidadNew = em.getReference(detalleejecucionplanificacioncalidadNew.getClass(), detalleejecucionplanificacioncalidadNew.getDetalleejecucionplanificacioncalidadPK());
                detalleplanificacioncalidad.setDetalleejecucionplanificacioncalidad(detalleejecucionplanificacioncalidadNew);
            }
            if (planificacioncalidadNew != null) {
                planificacioncalidadNew = em.getReference(planificacioncalidadNew.getClass(), planificacioncalidadNew.getIdplanificacion());
                detalleplanificacioncalidad.setPlanificacioncalidad(planificacioncalidadNew);
            }
            if (procesocalidadNew != null) {
                procesocalidadNew = em.getReference(procesocalidadNew.getClass(), procesocalidadNew.getIdprocesocalidad());
                detalleplanificacioncalidad.setProcesocalidad(procesocalidadNew);
            }
            detalleplanificacioncalidad = em.merge(detalleplanificacioncalidad);
            if (detalleejecucionplanificacioncalidadOld != null && !detalleejecucionplanificacioncalidadOld.equals(detalleejecucionplanificacioncalidadNew)) {
                detalleejecucionplanificacioncalidadOld.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidad);
                detalleejecucionplanificacioncalidadOld = em.merge(detalleejecucionplanificacioncalidadOld);
            }
            if (detalleejecucionplanificacioncalidadNew != null && !detalleejecucionplanificacioncalidadNew.equals(detalleejecucionplanificacioncalidadOld)) {
                detalleejecucionplanificacioncalidadNew.getDetalleplanificacioncalidadList().add(detalleplanificacioncalidad);
                detalleejecucionplanificacioncalidadNew = em.merge(detalleejecucionplanificacioncalidadNew);
            }
            if (planificacioncalidadOld != null && !planificacioncalidadOld.equals(planificacioncalidadNew)) {
                planificacioncalidadOld.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidad);
                planificacioncalidadOld = em.merge(planificacioncalidadOld);
            }
            if (planificacioncalidadNew != null && !planificacioncalidadNew.equals(planificacioncalidadOld)) {
                planificacioncalidadNew.getDetalleplanificacioncalidadList().add(detalleplanificacioncalidad);
                planificacioncalidadNew = em.merge(planificacioncalidadNew);
            }
            if (procesocalidadOld != null && !procesocalidadOld.equals(procesocalidadNew)) {
                procesocalidadOld.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidad);
                procesocalidadOld = em.merge(procesocalidadOld);
            }
            if (procesocalidadNew != null && !procesocalidadNew.equals(procesocalidadOld)) {
                procesocalidadNew.getDetalleplanificacioncalidadList().add(detalleplanificacioncalidad);
                procesocalidadNew = em.merge(procesocalidadNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalleplanificacioncalidadPK id = detalleplanificacioncalidad.getDetalleplanificacioncalidadPK();
                if (findDetalleplanificacioncalidad(id) == null) {
                    throw new NonexistentEntityException("The detalleplanificacioncalidad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetalleplanificacioncalidadPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleplanificacioncalidad detalleplanificacioncalidad;
            try {
                detalleplanificacioncalidad = em.getReference(Detalleplanificacioncalidad.class, id);
                detalleplanificacioncalidad.getDetalleplanificacioncalidadPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleplanificacioncalidad with id " + id + " no longer exists.", enfe);
            }
            Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad = detalleplanificacioncalidad.getDetalleejecucionplanificacioncalidad();
            if (detalleejecucionplanificacioncalidad != null) {
                detalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidad);
                detalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidad);
            }
            Planificacioncalidad planificacioncalidad = detalleplanificacioncalidad.getPlanificacioncalidad();
            if (planificacioncalidad != null) {
                planificacioncalidad.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidad);
                planificacioncalidad = em.merge(planificacioncalidad);
            }
            Procesocalidad procesocalidad = detalleplanificacioncalidad.getProcesocalidad();
            if (procesocalidad != null) {
                procesocalidad.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidad);
                procesocalidad = em.merge(procesocalidad);
            }
            em.remove(detalleplanificacioncalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleplanificacioncalidad> findDetalleplanificacioncalidadEntities() {
        return findDetalleplanificacioncalidadEntities(true, -1, -1);
    }

    public List<Detalleplanificacioncalidad> findDetalleplanificacioncalidadEntities(int maxResults, int firstResult) {
        return findDetalleplanificacioncalidadEntities(false, maxResults, firstResult);
    }

    private List<Detalleplanificacioncalidad> findDetalleplanificacioncalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleplanificacioncalidad.class));
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

    public Detalleplanificacioncalidad findDetalleplanificacioncalidad(DetalleplanificacioncalidadPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleplanificacioncalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleplanificacioncalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleplanificacioncalidad> rt = cq.from(Detalleplanificacioncalidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
