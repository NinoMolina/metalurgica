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
import metalsoft.datos.jpa.entity.Ejecucionprocesocalidad;
import metalsoft.datos.jpa.entity.Estadoejecucionprocesocalidad;

/**
 *
 * @author Nino
 */
public class EjecucionprocesocalidadJpaController implements Serializable {

    public EjecucionprocesocalidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ejecucionprocesocalidad ejecucionprocesocalidad) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoejecucionprocesocalidad estado = ejecucionprocesocalidad.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                ejecucionprocesocalidad.setEstado(estado);
            }
            em.persist(ejecucionprocesocalidad);
            if (estado != null) {
                estado.getEjecucionprocesocalidadList().add(ejecucionprocesocalidad);
                estado = em.merge(estado);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ejecucionprocesocalidad ejecucionprocesocalidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionprocesocalidad persistentEjecucionprocesocalidad = em.find(Ejecucionprocesocalidad.class, ejecucionprocesocalidad.getIdejecucion());
            Estadoejecucionprocesocalidad estadoOld = persistentEjecucionprocesocalidad.getEstado();
            Estadoejecucionprocesocalidad estadoNew = ejecucionprocesocalidad.getEstado();
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                ejecucionprocesocalidad.setEstado(estadoNew);
            }
            ejecucionprocesocalidad = em.merge(ejecucionprocesocalidad);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getEjecucionprocesocalidadList().remove(ejecucionprocesocalidad);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getEjecucionprocesocalidadList().add(ejecucionprocesocalidad);
                estadoNew = em.merge(estadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = ejecucionprocesocalidad.getIdejecucion();
                if (findEjecucionprocesocalidad(id) == null) {
                    throw new NonexistentEntityException("The ejecucionprocesocalidad with id " + id + " no longer exists.");
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
            Ejecucionprocesocalidad ejecucionprocesocalidad;
            try {
                ejecucionprocesocalidad = em.getReference(Ejecucionprocesocalidad.class, id);
                ejecucionprocesocalidad.getIdejecucion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ejecucionprocesocalidad with id " + id + " no longer exists.", enfe);
            }
            Estadoejecucionprocesocalidad estado = ejecucionprocesocalidad.getEstado();
            if (estado != null) {
                estado.getEjecucionprocesocalidadList().remove(ejecucionprocesocalidad);
                estado = em.merge(estado);
            }
            em.remove(ejecucionprocesocalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ejecucionprocesocalidad> findEjecucionprocesocalidadEntities() {
        return findEjecucionprocesocalidadEntities(true, -1, -1);
    }

    public List<Ejecucionprocesocalidad> findEjecucionprocesocalidadEntities(int maxResults, int firstResult) {
        return findEjecucionprocesocalidadEntities(false, maxResults, firstResult);
    }

    private List<Ejecucionprocesocalidad> findEjecucionprocesocalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ejecucionprocesocalidad.class));
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

    public Ejecucionprocesocalidad findEjecucionprocesocalidad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ejecucionprocesocalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getEjecucionprocesocalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ejecucionprocesocalidad> rt = cq.from(Ejecucionprocesocalidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
