/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Detalleplanprocesoscalidad;
import metalsoft.datos.jpa.entity.DetalleplanprocesoscalidadPK;
import metalsoft.datos.jpa.entity.Procesocalidad;
import metalsoft.datos.jpa.entity.Planprocesoscalidad;

/**
 *
 * @author Nino
 */
public class DetalleplanprocesoscalidadJpaController implements Serializable {

    public DetalleplanprocesoscalidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleplanprocesoscalidad detalleplanprocesoscalidad) throws PreexistingEntityException, Exception {
        if (detalleplanprocesoscalidad.getDetalleplanprocesoscalidadPK() == null) {
            detalleplanprocesoscalidad.setDetalleplanprocesoscalidadPK(new DetalleplanprocesoscalidadPK());
        }
        detalleplanprocesoscalidad.getDetalleplanprocesoscalidadPK().setIdplanprocesoscalidad(detalleplanprocesoscalidad.getPlanprocesoscalidad().getIdplanprocesoscalidad());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procesocalidad idprocesocalidad = detalleplanprocesoscalidad.getIdprocesocalidad();
            if (idprocesocalidad != null) {
                idprocesocalidad = em.getReference(idprocesocalidad.getClass(), idprocesocalidad.getIdprocesocalidad());
                detalleplanprocesoscalidad.setIdprocesocalidad(idprocesocalidad);
            }
            Planprocesoscalidad planprocesoscalidad = detalleplanprocesoscalidad.getPlanprocesoscalidad();
            if (planprocesoscalidad != null) {
                planprocesoscalidad = em.getReference(planprocesoscalidad.getClass(), planprocesoscalidad.getIdplanprocesoscalidad());
                detalleplanprocesoscalidad.setPlanprocesoscalidad(planprocesoscalidad);
            }
            em.persist(detalleplanprocesoscalidad);
            if (idprocesocalidad != null) {
                idprocesocalidad.getDetalleplanprocesoscalidadList().add(detalleplanprocesoscalidad);
                idprocesocalidad = em.merge(idprocesocalidad);
            }
            if (planprocesoscalidad != null) {
                planprocesoscalidad.getDetalleplanprocesoscalidadList().add(detalleplanprocesoscalidad);
                planprocesoscalidad = em.merge(planprocesoscalidad);
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
        detalleplanprocesoscalidad.getDetalleplanprocesoscalidadPK().setIdplanprocesoscalidad(detalleplanprocesoscalidad.getPlanprocesoscalidad().getIdplanprocesoscalidad());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleplanprocesoscalidad persistentDetalleplanprocesoscalidad = em.find(Detalleplanprocesoscalidad.class, detalleplanprocesoscalidad.getDetalleplanprocesoscalidadPK());
            Procesocalidad idprocesocalidadOld = persistentDetalleplanprocesoscalidad.getIdprocesocalidad();
            Procesocalidad idprocesocalidadNew = detalleplanprocesoscalidad.getIdprocesocalidad();
            Planprocesoscalidad planprocesoscalidadOld = persistentDetalleplanprocesoscalidad.getPlanprocesoscalidad();
            Planprocesoscalidad planprocesoscalidadNew = detalleplanprocesoscalidad.getPlanprocesoscalidad();
            if (idprocesocalidadNew != null) {
                idprocesocalidadNew = em.getReference(idprocesocalidadNew.getClass(), idprocesocalidadNew.getIdprocesocalidad());
                detalleplanprocesoscalidad.setIdprocesocalidad(idprocesocalidadNew);
            }
            if (planprocesoscalidadNew != null) {
                planprocesoscalidadNew = em.getReference(planprocesoscalidadNew.getClass(), planprocesoscalidadNew.getIdplanprocesoscalidad());
                detalleplanprocesoscalidad.setPlanprocesoscalidad(planprocesoscalidadNew);
            }
            detalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidad);
            if (idprocesocalidadOld != null && !idprocesocalidadOld.equals(idprocesocalidadNew)) {
                idprocesocalidadOld.getDetalleplanprocesoscalidadList().remove(detalleplanprocesoscalidad);
                idprocesocalidadOld = em.merge(idprocesocalidadOld);
            }
            if (idprocesocalidadNew != null && !idprocesocalidadNew.equals(idprocesocalidadOld)) {
                idprocesocalidadNew.getDetalleplanprocesoscalidadList().add(detalleplanprocesoscalidad);
                idprocesocalidadNew = em.merge(idprocesocalidadNew);
            }
            if (planprocesoscalidadOld != null && !planprocesoscalidadOld.equals(planprocesoscalidadNew)) {
                planprocesoscalidadOld.getDetalleplanprocesoscalidadList().remove(detalleplanprocesoscalidad);
                planprocesoscalidadOld = em.merge(planprocesoscalidadOld);
            }
            if (planprocesoscalidadNew != null && !planprocesoscalidadNew.equals(planprocesoscalidadOld)) {
                planprocesoscalidadNew.getDetalleplanprocesoscalidadList().add(detalleplanprocesoscalidad);
                planprocesoscalidadNew = em.merge(planprocesoscalidadNew);
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
            Procesocalidad idprocesocalidad = detalleplanprocesoscalidad.getIdprocesocalidad();
            if (idprocesocalidad != null) {
                idprocesocalidad.getDetalleplanprocesoscalidadList().remove(detalleplanprocesoscalidad);
                idprocesocalidad = em.merge(idprocesocalidad);
            }
            Planprocesoscalidad planprocesoscalidad = detalleplanprocesoscalidad.getPlanprocesoscalidad();
            if (planprocesoscalidad != null) {
                planprocesoscalidad.getDetalleplanprocesoscalidadList().remove(detalleplanprocesoscalidad);
                planprocesoscalidad = em.merge(planprocesoscalidad);
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
