/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detalleplanprocesoscalidad;
import entity.DetalleplanprocesoscalidadPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Planprocesoscalidad;
import entity.Procesocalidad;

/**
 *
 * @author Nino
 */
public class DetalleplanprocesoscalidadJpaController {

    public DetalleplanprocesoscalidadJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleplanprocesoscalidad detalleplanprocesoscalidad) throws PreexistingEntityException, Exception {
        if (detalleplanprocesoscalidad.getDetalleplanprocesoscalidadPK() == null) {
            detalleplanprocesoscalidad.setDetalleplanprocesoscalidadPK(new DetalleplanprocesoscalidadPK());
        }
        detalleplanprocesoscalidad.getDetalleplanprocesoscalidadPK().setIdplanprocesoscalidad(detalleplanprocesoscalidad.getPlanprocesoscalidad1().getIdplanprocesoscalidad());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planprocesoscalidad planprocesoscalidad = detalleplanprocesoscalidad.getPlanprocesoscalidad();
            if (planprocesoscalidad != null) {
                planprocesoscalidad = em.getReference(planprocesoscalidad.getClass(), planprocesoscalidad.getIdplanprocesoscalidad());
                detalleplanprocesoscalidad.setPlanprocesoscalidad(planprocesoscalidad);
            }
            Planprocesoscalidad planprocesoscalidad1 = detalleplanprocesoscalidad.getPlanprocesoscalidad1();
            if (planprocesoscalidad1 != null) {
                planprocesoscalidad1 = em.getReference(planprocesoscalidad1.getClass(), planprocesoscalidad1.getIdplanprocesoscalidad());
                detalleplanprocesoscalidad.setPlanprocesoscalidad1(planprocesoscalidad1);
            }
            Procesocalidad idprocesocalidad = detalleplanprocesoscalidad.getIdprocesocalidad();
            if (idprocesocalidad != null) {
                idprocesocalidad = em.getReference(idprocesocalidad.getClass(), idprocesocalidad.getIdprocesocalidad());
                detalleplanprocesoscalidad.setIdprocesocalidad(idprocesocalidad);
            }
            Procesocalidad idprocesocalidad1 = detalleplanprocesoscalidad.getIdprocesocalidad1();
            if (idprocesocalidad1 != null) {
                idprocesocalidad1 = em.getReference(idprocesocalidad1.getClass(), idprocesocalidad1.getIdprocesocalidad());
                detalleplanprocesoscalidad.setIdprocesocalidad1(idprocesocalidad1);
            }
            em.persist(detalleplanprocesoscalidad);
            if (planprocesoscalidad != null) {
                planprocesoscalidad.getDetalleplanprocesoscalidadSet().add(detalleplanprocesoscalidad);
                planprocesoscalidad = em.merge(planprocesoscalidad);
            }
            if (planprocesoscalidad1 != null) {
                planprocesoscalidad1.getDetalleplanprocesoscalidadSet().add(detalleplanprocesoscalidad);
                planprocesoscalidad1 = em.merge(planprocesoscalidad1);
            }
            if (idprocesocalidad != null) {
                idprocesocalidad.getDetalleplanprocesoscalidadSet().add(detalleplanprocesoscalidad);
                idprocesocalidad = em.merge(idprocesocalidad);
            }
            if (idprocesocalidad1 != null) {
                idprocesocalidad1.getDetalleplanprocesoscalidadSet().add(detalleplanprocesoscalidad);
                idprocesocalidad1 = em.merge(idprocesocalidad1);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleplanprocesoscalidad(detalleplanprocesoscalidad.getDetalleplanprocesoscalidadPK()) != null) {
                throw new PreexistingEntityException("Detalleplanprocesoscalidad " + detalleplanprocesoscalidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleplanprocesoscalidad detalleplanprocesoscalidad) throws NonexistentEntityException, Exception {
        detalleplanprocesoscalidad.getDetalleplanprocesoscalidadPK().setIdplanprocesoscalidad(detalleplanprocesoscalidad.getPlanprocesoscalidad1().getIdplanprocesoscalidad());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleplanprocesoscalidad persistentDetalleplanprocesoscalidad = em.find(Detalleplanprocesoscalidad.class, detalleplanprocesoscalidad.getDetalleplanprocesoscalidadPK());
            Planprocesoscalidad planprocesoscalidadOld = persistentDetalleplanprocesoscalidad.getPlanprocesoscalidad();
            Planprocesoscalidad planprocesoscalidadNew = detalleplanprocesoscalidad.getPlanprocesoscalidad();
            Planprocesoscalidad planprocesoscalidad1Old = persistentDetalleplanprocesoscalidad.getPlanprocesoscalidad1();
            Planprocesoscalidad planprocesoscalidad1New = detalleplanprocesoscalidad.getPlanprocesoscalidad1();
            Procesocalidad idprocesocalidadOld = persistentDetalleplanprocesoscalidad.getIdprocesocalidad();
            Procesocalidad idprocesocalidadNew = detalleplanprocesoscalidad.getIdprocesocalidad();
            Procesocalidad idprocesocalidad1Old = persistentDetalleplanprocesoscalidad.getIdprocesocalidad1();
            Procesocalidad idprocesocalidad1New = detalleplanprocesoscalidad.getIdprocesocalidad1();
            if (planprocesoscalidadNew != null) {
                planprocesoscalidadNew = em.getReference(planprocesoscalidadNew.getClass(), planprocesoscalidadNew.getIdplanprocesoscalidad());
                detalleplanprocesoscalidad.setPlanprocesoscalidad(planprocesoscalidadNew);
            }
            if (planprocesoscalidad1New != null) {
                planprocesoscalidad1New = em.getReference(planprocesoscalidad1New.getClass(), planprocesoscalidad1New.getIdplanprocesoscalidad());
                detalleplanprocesoscalidad.setPlanprocesoscalidad1(planprocesoscalidad1New);
            }
            if (idprocesocalidadNew != null) {
                idprocesocalidadNew = em.getReference(idprocesocalidadNew.getClass(), idprocesocalidadNew.getIdprocesocalidad());
                detalleplanprocesoscalidad.setIdprocesocalidad(idprocesocalidadNew);
            }
            if (idprocesocalidad1New != null) {
                idprocesocalidad1New = em.getReference(idprocesocalidad1New.getClass(), idprocesocalidad1New.getIdprocesocalidad());
                detalleplanprocesoscalidad.setIdprocesocalidad1(idprocesocalidad1New);
            }
            detalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidad);
            if (planprocesoscalidadOld != null && !planprocesoscalidadOld.equals(planprocesoscalidadNew)) {
                planprocesoscalidadOld.getDetalleplanprocesoscalidadSet().remove(detalleplanprocesoscalidad);
                planprocesoscalidadOld = em.merge(planprocesoscalidadOld);
            }
            if (planprocesoscalidadNew != null && !planprocesoscalidadNew.equals(planprocesoscalidadOld)) {
                planprocesoscalidadNew.getDetalleplanprocesoscalidadSet().add(detalleplanprocesoscalidad);
                planprocesoscalidadNew = em.merge(planprocesoscalidadNew);
            }
            if (planprocesoscalidad1Old != null && !planprocesoscalidad1Old.equals(planprocesoscalidad1New)) {
                planprocesoscalidad1Old.getDetalleplanprocesoscalidadSet().remove(detalleplanprocesoscalidad);
                planprocesoscalidad1Old = em.merge(planprocesoscalidad1Old);
            }
            if (planprocesoscalidad1New != null && !planprocesoscalidad1New.equals(planprocesoscalidad1Old)) {
                planprocesoscalidad1New.getDetalleplanprocesoscalidadSet().add(detalleplanprocesoscalidad);
                planprocesoscalidad1New = em.merge(planprocesoscalidad1New);
            }
            if (idprocesocalidadOld != null && !idprocesocalidadOld.equals(idprocesocalidadNew)) {
                idprocesocalidadOld.getDetalleplanprocesoscalidadSet().remove(detalleplanprocesoscalidad);
                idprocesocalidadOld = em.merge(idprocesocalidadOld);
            }
            if (idprocesocalidadNew != null && !idprocesocalidadNew.equals(idprocesocalidadOld)) {
                idprocesocalidadNew.getDetalleplanprocesoscalidadSet().add(detalleplanprocesoscalidad);
                idprocesocalidadNew = em.merge(idprocesocalidadNew);
            }
            if (idprocesocalidad1Old != null && !idprocesocalidad1Old.equals(idprocesocalidad1New)) {
                idprocesocalidad1Old.getDetalleplanprocesoscalidadSet().remove(detalleplanprocesoscalidad);
                idprocesocalidad1Old = em.merge(idprocesocalidad1Old);
            }
            if (idprocesocalidad1New != null && !idprocesocalidad1New.equals(idprocesocalidad1Old)) {
                idprocesocalidad1New.getDetalleplanprocesoscalidadSet().add(detalleplanprocesoscalidad);
                idprocesocalidad1New = em.merge(idprocesocalidad1New);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalleplanprocesoscalidadPK id = detalleplanprocesoscalidad.getDetalleplanprocesoscalidadPK();
                if (findDetalleplanprocesoscalidad(id) == null) {
                    throw new NonexistentEntityException("The detalleplanprocesoscalidad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetalleplanprocesoscalidadPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleplanprocesoscalidad detalleplanprocesoscalidad;
            try {
                detalleplanprocesoscalidad = em.getReference(Detalleplanprocesoscalidad.class, id);
                detalleplanprocesoscalidad.getDetalleplanprocesoscalidadPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleplanprocesoscalidad with id " + id + " no longer exists.", enfe);
            }
            Planprocesoscalidad planprocesoscalidad = detalleplanprocesoscalidad.getPlanprocesoscalidad();
            if (planprocesoscalidad != null) {
                planprocesoscalidad.getDetalleplanprocesoscalidadSet().remove(detalleplanprocesoscalidad);
                planprocesoscalidad = em.merge(planprocesoscalidad);
            }
            Planprocesoscalidad planprocesoscalidad1 = detalleplanprocesoscalidad.getPlanprocesoscalidad1();
            if (planprocesoscalidad1 != null) {
                planprocesoscalidad1.getDetalleplanprocesoscalidadSet().remove(detalleplanprocesoscalidad);
                planprocesoscalidad1 = em.merge(planprocesoscalidad1);
            }
            Procesocalidad idprocesocalidad = detalleplanprocesoscalidad.getIdprocesocalidad();
            if (idprocesocalidad != null) {
                idprocesocalidad.getDetalleplanprocesoscalidadSet().remove(detalleplanprocesoscalidad);
                idprocesocalidad = em.merge(idprocesocalidad);
            }
            Procesocalidad idprocesocalidad1 = detalleplanprocesoscalidad.getIdprocesocalidad1();
            if (idprocesocalidad1 != null) {
                idprocesocalidad1.getDetalleplanprocesoscalidadSet().remove(detalleplanprocesoscalidad);
                idprocesocalidad1 = em.merge(idprocesocalidad1);
            }
            em.remove(detalleplanprocesoscalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleplanprocesoscalidad> findDetalleplanprocesoscalidadEntities() {
        return findDetalleplanprocesoscalidadEntities(true, -1, -1);
    }

    public List<Detalleplanprocesoscalidad> findDetalleplanprocesoscalidadEntities(int maxResults, int firstResult) {
        return findDetalleplanprocesoscalidadEntities(false, maxResults, firstResult);
    }

    private List<Detalleplanprocesoscalidad> findDetalleplanprocesoscalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleplanprocesoscalidad.class));
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

    public Detalleplanprocesoscalidad findDetalleplanprocesoscalidad(DetalleplanprocesoscalidadPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleplanprocesoscalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleplanprocesoscalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleplanprocesoscalidad> rt = cq.from(Detalleplanprocesoscalidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
