/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detalleplanificacioncalidad;
import entity.DetalleplanificacioncalidadPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Detalleejecucionplanificacioncalidad;
import entity.Planificacioncalidad;
import entity.Procesocalidad;

/**
 *
 * @author Nino
 */
public class DetalleplanificacioncalidadJpaController {

    public DetalleplanificacioncalidadJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleplanificacioncalidad detalleplanificacioncalidad) throws PreexistingEntityException, Exception {
        if (detalleplanificacioncalidad.getDetalleplanificacioncalidadPK() == null) {
            detalleplanificacioncalidad.setDetalleplanificacioncalidadPK(new DetalleplanificacioncalidadPK());
        }
        detalleplanificacioncalidad.getDetalleplanificacioncalidadPK().setIdplanificacioncalidad(detalleplanificacioncalidad.getPlanificacioncalidad1().getIdplanificacion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad = detalleplanificacioncalidad.getDetalleejecucionplanificacioncalidad();
            if (detalleejecucionplanificacioncalidad != null) {
                detalleejecucionplanificacioncalidad = em.getReference(detalleejecucionplanificacioncalidad.getClass(), detalleejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadPK());
                detalleplanificacioncalidad.setDetalleejecucionplanificacioncalidad(detalleejecucionplanificacioncalidad);
            }
            Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad1 = detalleplanificacioncalidad.getDetalleejecucionplanificacioncalidad1();
            if (detalleejecucionplanificacioncalidad1 != null) {
                detalleejecucionplanificacioncalidad1 = em.getReference(detalleejecucionplanificacioncalidad1.getClass(), detalleejecucionplanificacioncalidad1.getDetalleejecucionplanificacioncalidadPK());
                detalleplanificacioncalidad.setDetalleejecucionplanificacioncalidad1(detalleejecucionplanificacioncalidad1);
            }
            Planificacioncalidad planificacioncalidad = detalleplanificacioncalidad.getPlanificacioncalidad();
            if (planificacioncalidad != null) {
                planificacioncalidad = em.getReference(planificacioncalidad.getClass(), planificacioncalidad.getIdplanificacion());
                detalleplanificacioncalidad.setPlanificacioncalidad(planificacioncalidad);
            }
            Planificacioncalidad planificacioncalidad1 = detalleplanificacioncalidad.getPlanificacioncalidad1();
            if (planificacioncalidad1 != null) {
                planificacioncalidad1 = em.getReference(planificacioncalidad1.getClass(), planificacioncalidad1.getIdplanificacion());
                detalleplanificacioncalidad.setPlanificacioncalidad1(planificacioncalidad1);
            }
            Procesocalidad procesocalidad = detalleplanificacioncalidad.getProcesocalidad();
            if (procesocalidad != null) {
                procesocalidad = em.getReference(procesocalidad.getClass(), procesocalidad.getIdprocesocalidad());
                detalleplanificacioncalidad.setProcesocalidad(procesocalidad);
            }
            Procesocalidad procesocalidad1 = detalleplanificacioncalidad.getProcesocalidad1();
            if (procesocalidad1 != null) {
                procesocalidad1 = em.getReference(procesocalidad1.getClass(), procesocalidad1.getIdprocesocalidad());
                detalleplanificacioncalidad.setProcesocalidad1(procesocalidad1);
            }
            em.persist(detalleplanificacioncalidad);
            if (detalleejecucionplanificacioncalidad != null) {
                detalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadSet().add(detalleplanificacioncalidad);
                detalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidad);
            }
            if (detalleejecucionplanificacioncalidad1 != null) {
                detalleejecucionplanificacioncalidad1.getDetalleplanificacioncalidadSet().add(detalleplanificacioncalidad);
                detalleejecucionplanificacioncalidad1 = em.merge(detalleejecucionplanificacioncalidad1);
            }
            if (planificacioncalidad != null) {
                planificacioncalidad.getDetalleplanificacioncalidadSet().add(detalleplanificacioncalidad);
                planificacioncalidad = em.merge(planificacioncalidad);
            }
            if (planificacioncalidad1 != null) {
                planificacioncalidad1.getDetalleplanificacioncalidadSet().add(detalleplanificacioncalidad);
                planificacioncalidad1 = em.merge(planificacioncalidad1);
            }
            if (procesocalidad != null) {
                procesocalidad.getDetalleplanificacioncalidadSet().add(detalleplanificacioncalidad);
                procesocalidad = em.merge(procesocalidad);
            }
            if (procesocalidad1 != null) {
                procesocalidad1.getDetalleplanificacioncalidadSet().add(detalleplanificacioncalidad);
                procesocalidad1 = em.merge(procesocalidad1);
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
        detalleplanificacioncalidad.getDetalleplanificacioncalidadPK().setIdplanificacioncalidad(detalleplanificacioncalidad.getPlanificacioncalidad1().getIdplanificacion());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleplanificacioncalidad persistentDetalleplanificacioncalidad = em.find(Detalleplanificacioncalidad.class, detalleplanificacioncalidad.getDetalleplanificacioncalidadPK());
            Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadOld = persistentDetalleplanificacioncalidad.getDetalleejecucionplanificacioncalidad();
            Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidadNew = detalleplanificacioncalidad.getDetalleejecucionplanificacioncalidad();
            Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad1Old = persistentDetalleplanificacioncalidad.getDetalleejecucionplanificacioncalidad1();
            Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad1New = detalleplanificacioncalidad.getDetalleejecucionplanificacioncalidad1();
            Planificacioncalidad planificacioncalidadOld = persistentDetalleplanificacioncalidad.getPlanificacioncalidad();
            Planificacioncalidad planificacioncalidadNew = detalleplanificacioncalidad.getPlanificacioncalidad();
            Planificacioncalidad planificacioncalidad1Old = persistentDetalleplanificacioncalidad.getPlanificacioncalidad1();
            Planificacioncalidad planificacioncalidad1New = detalleplanificacioncalidad.getPlanificacioncalidad1();
            Procesocalidad procesocalidadOld = persistentDetalleplanificacioncalidad.getProcesocalidad();
            Procesocalidad procesocalidadNew = detalleplanificacioncalidad.getProcesocalidad();
            Procesocalidad procesocalidad1Old = persistentDetalleplanificacioncalidad.getProcesocalidad1();
            Procesocalidad procesocalidad1New = detalleplanificacioncalidad.getProcesocalidad1();
            if (detalleejecucionplanificacioncalidadNew != null) {
                detalleejecucionplanificacioncalidadNew = em.getReference(detalleejecucionplanificacioncalidadNew.getClass(), detalleejecucionplanificacioncalidadNew.getDetalleejecucionplanificacioncalidadPK());
                detalleplanificacioncalidad.setDetalleejecucionplanificacioncalidad(detalleejecucionplanificacioncalidadNew);
            }
            if (detalleejecucionplanificacioncalidad1New != null) {
                detalleejecucionplanificacioncalidad1New = em.getReference(detalleejecucionplanificacioncalidad1New.getClass(), detalleejecucionplanificacioncalidad1New.getDetalleejecucionplanificacioncalidadPK());
                detalleplanificacioncalidad.setDetalleejecucionplanificacioncalidad1(detalleejecucionplanificacioncalidad1New);
            }
            if (planificacioncalidadNew != null) {
                planificacioncalidadNew = em.getReference(planificacioncalidadNew.getClass(), planificacioncalidadNew.getIdplanificacion());
                detalleplanificacioncalidad.setPlanificacioncalidad(planificacioncalidadNew);
            }
            if (planificacioncalidad1New != null) {
                planificacioncalidad1New = em.getReference(planificacioncalidad1New.getClass(), planificacioncalidad1New.getIdplanificacion());
                detalleplanificacioncalidad.setPlanificacioncalidad1(planificacioncalidad1New);
            }
            if (procesocalidadNew != null) {
                procesocalidadNew = em.getReference(procesocalidadNew.getClass(), procesocalidadNew.getIdprocesocalidad());
                detalleplanificacioncalidad.setProcesocalidad(procesocalidadNew);
            }
            if (procesocalidad1New != null) {
                procesocalidad1New = em.getReference(procesocalidad1New.getClass(), procesocalidad1New.getIdprocesocalidad());
                detalleplanificacioncalidad.setProcesocalidad1(procesocalidad1New);
            }
            detalleplanificacioncalidad = em.merge(detalleplanificacioncalidad);
            if (detalleejecucionplanificacioncalidadOld != null && !detalleejecucionplanificacioncalidadOld.equals(detalleejecucionplanificacioncalidadNew)) {
                detalleejecucionplanificacioncalidadOld.getDetalleplanificacioncalidadSet().remove(detalleplanificacioncalidad);
                detalleejecucionplanificacioncalidadOld = em.merge(detalleejecucionplanificacioncalidadOld);
            }
            if (detalleejecucionplanificacioncalidadNew != null && !detalleejecucionplanificacioncalidadNew.equals(detalleejecucionplanificacioncalidadOld)) {
                detalleejecucionplanificacioncalidadNew.getDetalleplanificacioncalidadSet().add(detalleplanificacioncalidad);
                detalleejecucionplanificacioncalidadNew = em.merge(detalleejecucionplanificacioncalidadNew);
            }
            if (detalleejecucionplanificacioncalidad1Old != null && !detalleejecucionplanificacioncalidad1Old.equals(detalleejecucionplanificacioncalidad1New)) {
                detalleejecucionplanificacioncalidad1Old.getDetalleplanificacioncalidadSet().remove(detalleplanificacioncalidad);
                detalleejecucionplanificacioncalidad1Old = em.merge(detalleejecucionplanificacioncalidad1Old);
            }
            if (detalleejecucionplanificacioncalidad1New != null && !detalleejecucionplanificacioncalidad1New.equals(detalleejecucionplanificacioncalidad1Old)) {
                detalleejecucionplanificacioncalidad1New.getDetalleplanificacioncalidadSet().add(detalleplanificacioncalidad);
                detalleejecucionplanificacioncalidad1New = em.merge(detalleejecucionplanificacioncalidad1New);
            }
            if (planificacioncalidadOld != null && !planificacioncalidadOld.equals(planificacioncalidadNew)) {
                planificacioncalidadOld.getDetalleplanificacioncalidadSet().remove(detalleplanificacioncalidad);
                planificacioncalidadOld = em.merge(planificacioncalidadOld);
            }
            if (planificacioncalidadNew != null && !planificacioncalidadNew.equals(planificacioncalidadOld)) {
                planificacioncalidadNew.getDetalleplanificacioncalidadSet().add(detalleplanificacioncalidad);
                planificacioncalidadNew = em.merge(planificacioncalidadNew);
            }
            if (planificacioncalidad1Old != null && !planificacioncalidad1Old.equals(planificacioncalidad1New)) {
                planificacioncalidad1Old.getDetalleplanificacioncalidadSet().remove(detalleplanificacioncalidad);
                planificacioncalidad1Old = em.merge(planificacioncalidad1Old);
            }
            if (planificacioncalidad1New != null && !planificacioncalidad1New.equals(planificacioncalidad1Old)) {
                planificacioncalidad1New.getDetalleplanificacioncalidadSet().add(detalleplanificacioncalidad);
                planificacioncalidad1New = em.merge(planificacioncalidad1New);
            }
            if (procesocalidadOld != null && !procesocalidadOld.equals(procesocalidadNew)) {
                procesocalidadOld.getDetalleplanificacioncalidadSet().remove(detalleplanificacioncalidad);
                procesocalidadOld = em.merge(procesocalidadOld);
            }
            if (procesocalidadNew != null && !procesocalidadNew.equals(procesocalidadOld)) {
                procesocalidadNew.getDetalleplanificacioncalidadSet().add(detalleplanificacioncalidad);
                procesocalidadNew = em.merge(procesocalidadNew);
            }
            if (procesocalidad1Old != null && !procesocalidad1Old.equals(procesocalidad1New)) {
                procesocalidad1Old.getDetalleplanificacioncalidadSet().remove(detalleplanificacioncalidad);
                procesocalidad1Old = em.merge(procesocalidad1Old);
            }
            if (procesocalidad1New != null && !procesocalidad1New.equals(procesocalidad1Old)) {
                procesocalidad1New.getDetalleplanificacioncalidadSet().add(detalleplanificacioncalidad);
                procesocalidad1New = em.merge(procesocalidad1New);
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
                detalleejecucionplanificacioncalidad.getDetalleplanificacioncalidadSet().remove(detalleplanificacioncalidad);
                detalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidad);
            }
            Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad1 = detalleplanificacioncalidad.getDetalleejecucionplanificacioncalidad1();
            if (detalleejecucionplanificacioncalidad1 != null) {
                detalleejecucionplanificacioncalidad1.getDetalleplanificacioncalidadSet().remove(detalleplanificacioncalidad);
                detalleejecucionplanificacioncalidad1 = em.merge(detalleejecucionplanificacioncalidad1);
            }
            Planificacioncalidad planificacioncalidad = detalleplanificacioncalidad.getPlanificacioncalidad();
            if (planificacioncalidad != null) {
                planificacioncalidad.getDetalleplanificacioncalidadSet().remove(detalleplanificacioncalidad);
                planificacioncalidad = em.merge(planificacioncalidad);
            }
            Planificacioncalidad planificacioncalidad1 = detalleplanificacioncalidad.getPlanificacioncalidad1();
            if (planificacioncalidad1 != null) {
                planificacioncalidad1.getDetalleplanificacioncalidadSet().remove(detalleplanificacioncalidad);
                planificacioncalidad1 = em.merge(planificacioncalidad1);
            }
            Procesocalidad procesocalidad = detalleplanificacioncalidad.getProcesocalidad();
            if (procesocalidad != null) {
                procesocalidad.getDetalleplanificacioncalidadSet().remove(detalleplanificacioncalidad);
                procesocalidad = em.merge(procesocalidad);
            }
            Procesocalidad procesocalidad1 = detalleplanificacioncalidad.getProcesocalidad1();
            if (procesocalidad1 != null) {
                procesocalidad1.getDetalleplanificacioncalidadSet().remove(detalleplanificacioncalidad);
                procesocalidad1 = em.merge(procesocalidad1);
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
