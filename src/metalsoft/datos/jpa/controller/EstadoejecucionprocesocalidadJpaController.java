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
import metalsoft.datos.jpa.entity.Ejecucionprocesocalidad;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Estadoejecucionprocesocalidad;

/**
 *
 * @author Nino
 */
public class EstadoejecucionprocesocalidadJpaController implements Serializable {

    public EstadoejecucionprocesocalidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadoejecucionprocesocalidad estadoejecucionprocesocalidad) throws PreexistingEntityException, Exception {
        if (estadoejecucionprocesocalidad.getEjecucionprocesocalidadList() == null) {
            estadoejecucionprocesocalidad.setEjecucionprocesocalidadList(new ArrayList<Ejecucionprocesocalidad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ejecucionprocesocalidad> attachedEjecucionprocesocalidadList = new ArrayList<Ejecucionprocesocalidad>();
            for (Ejecucionprocesocalidad ejecucionprocesocalidadListEjecucionprocesocalidadToAttach : estadoejecucionprocesocalidad.getEjecucionprocesocalidadList()) {
                ejecucionprocesocalidadListEjecucionprocesocalidadToAttach = em.getReference(ejecucionprocesocalidadListEjecucionprocesocalidadToAttach.getClass(), ejecucionprocesocalidadListEjecucionprocesocalidadToAttach.getIdejecucion());
                attachedEjecucionprocesocalidadList.add(ejecucionprocesocalidadListEjecucionprocesocalidadToAttach);
            }
            estadoejecucionprocesocalidad.setEjecucionprocesocalidadList(attachedEjecucionprocesocalidadList);
            em.persist(estadoejecucionprocesocalidad);
            for (Ejecucionprocesocalidad ejecucionprocesocalidadListEjecucionprocesocalidad : estadoejecucionprocesocalidad.getEjecucionprocesocalidadList()) {
                Estadoejecucionprocesocalidad oldEstadoOfEjecucionprocesocalidadListEjecucionprocesocalidad = ejecucionprocesocalidadListEjecucionprocesocalidad.getEstado();
                ejecucionprocesocalidadListEjecucionprocesocalidad.setEstado(estadoejecucionprocesocalidad);
                ejecucionprocesocalidadListEjecucionprocesocalidad = em.merge(ejecucionprocesocalidadListEjecucionprocesocalidad);
                if (oldEstadoOfEjecucionprocesocalidadListEjecucionprocesocalidad != null) {
                    oldEstadoOfEjecucionprocesocalidadListEjecucionprocesocalidad.getEjecucionprocesocalidadList().remove(ejecucionprocesocalidadListEjecucionprocesocalidad);
                    oldEstadoOfEjecucionprocesocalidadListEjecucionprocesocalidad = em.merge(oldEstadoOfEjecucionprocesocalidadListEjecucionprocesocalidad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoejecucionprocesocalidad(estadoejecucionprocesocalidad.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadoejecucionprocesocalidad " + estadoejecucionprocesocalidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadoejecucionprocesocalidad estadoejecucionprocesocalidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoejecucionprocesocalidad persistentEstadoejecucionprocesocalidad = em.find(Estadoejecucionprocesocalidad.class, estadoejecucionprocesocalidad.getIdestado());
            List<Ejecucionprocesocalidad> ejecucionprocesocalidadListOld = persistentEstadoejecucionprocesocalidad.getEjecucionprocesocalidadList();
            List<Ejecucionprocesocalidad> ejecucionprocesocalidadListNew = estadoejecucionprocesocalidad.getEjecucionprocesocalidadList();
            List<Ejecucionprocesocalidad> attachedEjecucionprocesocalidadListNew = new ArrayList<Ejecucionprocesocalidad>();
            for (Ejecucionprocesocalidad ejecucionprocesocalidadListNewEjecucionprocesocalidadToAttach : ejecucionprocesocalidadListNew) {
                ejecucionprocesocalidadListNewEjecucionprocesocalidadToAttach = em.getReference(ejecucionprocesocalidadListNewEjecucionprocesocalidadToAttach.getClass(), ejecucionprocesocalidadListNewEjecucionprocesocalidadToAttach.getIdejecucion());
                attachedEjecucionprocesocalidadListNew.add(ejecucionprocesocalidadListNewEjecucionprocesocalidadToAttach);
            }
            ejecucionprocesocalidadListNew = attachedEjecucionprocesocalidadListNew;
            estadoejecucionprocesocalidad.setEjecucionprocesocalidadList(ejecucionprocesocalidadListNew);
            estadoejecucionprocesocalidad = em.merge(estadoejecucionprocesocalidad);
            for (Ejecucionprocesocalidad ejecucionprocesocalidadListOldEjecucionprocesocalidad : ejecucionprocesocalidadListOld) {
                if (!ejecucionprocesocalidadListNew.contains(ejecucionprocesocalidadListOldEjecucionprocesocalidad)) {
                    ejecucionprocesocalidadListOldEjecucionprocesocalidad.setEstado(null);
                    ejecucionprocesocalidadListOldEjecucionprocesocalidad = em.merge(ejecucionprocesocalidadListOldEjecucionprocesocalidad);
                }
            }
            for (Ejecucionprocesocalidad ejecucionprocesocalidadListNewEjecucionprocesocalidad : ejecucionprocesocalidadListNew) {
                if (!ejecucionprocesocalidadListOld.contains(ejecucionprocesocalidadListNewEjecucionprocesocalidad)) {
                    Estadoejecucionprocesocalidad oldEstadoOfEjecucionprocesocalidadListNewEjecucionprocesocalidad = ejecucionprocesocalidadListNewEjecucionprocesocalidad.getEstado();
                    ejecucionprocesocalidadListNewEjecucionprocesocalidad.setEstado(estadoejecucionprocesocalidad);
                    ejecucionprocesocalidadListNewEjecucionprocesocalidad = em.merge(ejecucionprocesocalidadListNewEjecucionprocesocalidad);
                    if (oldEstadoOfEjecucionprocesocalidadListNewEjecucionprocesocalidad != null && !oldEstadoOfEjecucionprocesocalidadListNewEjecucionprocesocalidad.equals(estadoejecucionprocesocalidad)) {
                        oldEstadoOfEjecucionprocesocalidadListNewEjecucionprocesocalidad.getEjecucionprocesocalidadList().remove(ejecucionprocesocalidadListNewEjecucionprocesocalidad);
                        oldEstadoOfEjecucionprocesocalidadListNewEjecucionprocesocalidad = em.merge(oldEstadoOfEjecucionprocesocalidadListNewEjecucionprocesocalidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadoejecucionprocesocalidad.getIdestado();
                if (findEstadoejecucionprocesocalidad(id) == null) {
                    throw new NonexistentEntityException("The estadoejecucionprocesocalidad with id " + id + " no longer exists.");
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
            Estadoejecucionprocesocalidad estadoejecucionprocesocalidad;
            try {
                estadoejecucionprocesocalidad = em.getReference(Estadoejecucionprocesocalidad.class, id);
                estadoejecucionprocesocalidad.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoejecucionprocesocalidad with id " + id + " no longer exists.", enfe);
            }
            List<Ejecucionprocesocalidad> ejecucionprocesocalidadList = estadoejecucionprocesocalidad.getEjecucionprocesocalidadList();
            for (Ejecucionprocesocalidad ejecucionprocesocalidadListEjecucionprocesocalidad : ejecucionprocesocalidadList) {
                ejecucionprocesocalidadListEjecucionprocesocalidad.setEstado(null);
                ejecucionprocesocalidadListEjecucionprocesocalidad = em.merge(ejecucionprocesocalidadListEjecucionprocesocalidad);
            }
            em.remove(estadoejecucionprocesocalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadoejecucionprocesocalidad> findEstadoejecucionprocesocalidadEntities() {
        return findEstadoejecucionprocesocalidadEntities(true, -1, -1);
    }

    public List<Estadoejecucionprocesocalidad> findEstadoejecucionprocesocalidadEntities(int maxResults, int firstResult) {
        return findEstadoejecucionprocesocalidadEntities(false, maxResults, firstResult);
    }

    private List<Estadoejecucionprocesocalidad> findEstadoejecucionprocesocalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadoejecucionprocesocalidad.class));
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

    public Estadoejecucionprocesocalidad findEstadoejecucionprocesocalidad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadoejecucionprocesocalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoejecucionprocesocalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadoejecucionprocesocalidad> rt = cq.from(Estadoejecucionprocesocalidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
