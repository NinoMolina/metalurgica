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
import metalsoft.datos.jpa.entity.Ejecucionplanificacioncalidad;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Estadoejecplancalidad;

/**
 *
 * @author Nino
 */
public class EstadoejecplancalidadJpaController implements Serializable {

    public EstadoejecplancalidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadoejecplancalidad estadoejecplancalidad) throws PreexistingEntityException, Exception {
        if (estadoejecplancalidad.getEjecucionplanificacioncalidadList() == null) {
            estadoejecplancalidad.setEjecucionplanificacioncalidadList(new ArrayList<Ejecucionplanificacioncalidad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ejecucionplanificacioncalidad> attachedEjecucionplanificacioncalidadList = new ArrayList<Ejecucionplanificacioncalidad>();
            for (Ejecucionplanificacioncalidad ejecucionplanificacioncalidadListEjecucionplanificacioncalidadToAttach : estadoejecplancalidad.getEjecucionplanificacioncalidadList()) {
                ejecucionplanificacioncalidadListEjecucionplanificacioncalidadToAttach = em.getReference(ejecucionplanificacioncalidadListEjecucionplanificacioncalidadToAttach.getClass(), ejecucionplanificacioncalidadListEjecucionplanificacioncalidadToAttach.getIdejecucion());
                attachedEjecucionplanificacioncalidadList.add(ejecucionplanificacioncalidadListEjecucionplanificacioncalidadToAttach);
            }
            estadoejecplancalidad.setEjecucionplanificacioncalidadList(attachedEjecucionplanificacioncalidadList);
            em.persist(estadoejecplancalidad);
            for (Ejecucionplanificacioncalidad ejecucionplanificacioncalidadListEjecucionplanificacioncalidad : estadoejecplancalidad.getEjecucionplanificacioncalidadList()) {
                Estadoejecplancalidad oldEstadoOfEjecucionplanificacioncalidadListEjecucionplanificacioncalidad = ejecucionplanificacioncalidadListEjecucionplanificacioncalidad.getEstado();
                ejecucionplanificacioncalidadListEjecucionplanificacioncalidad.setEstado(estadoejecplancalidad);
                ejecucionplanificacioncalidadListEjecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidadListEjecucionplanificacioncalidad);
                if (oldEstadoOfEjecucionplanificacioncalidadListEjecucionplanificacioncalidad != null) {
                    oldEstadoOfEjecucionplanificacioncalidadListEjecucionplanificacioncalidad.getEjecucionplanificacioncalidadList().remove(ejecucionplanificacioncalidadListEjecucionplanificacioncalidad);
                    oldEstadoOfEjecucionplanificacioncalidadListEjecucionplanificacioncalidad = em.merge(oldEstadoOfEjecucionplanificacioncalidadListEjecucionplanificacioncalidad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoejecplancalidad(estadoejecplancalidad.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadoejecplancalidad " + estadoejecplancalidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadoejecplancalidad estadoejecplancalidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoejecplancalidad persistentEstadoejecplancalidad = em.find(Estadoejecplancalidad.class, estadoejecplancalidad.getIdestado());
            List<Ejecucionplanificacioncalidad> ejecucionplanificacioncalidadListOld = persistentEstadoejecplancalidad.getEjecucionplanificacioncalidadList();
            List<Ejecucionplanificacioncalidad> ejecucionplanificacioncalidadListNew = estadoejecplancalidad.getEjecucionplanificacioncalidadList();
            List<Ejecucionplanificacioncalidad> attachedEjecucionplanificacioncalidadListNew = new ArrayList<Ejecucionplanificacioncalidad>();
            for (Ejecucionplanificacioncalidad ejecucionplanificacioncalidadListNewEjecucionplanificacioncalidadToAttach : ejecucionplanificacioncalidadListNew) {
                ejecucionplanificacioncalidadListNewEjecucionplanificacioncalidadToAttach = em.getReference(ejecucionplanificacioncalidadListNewEjecucionplanificacioncalidadToAttach.getClass(), ejecucionplanificacioncalidadListNewEjecucionplanificacioncalidadToAttach.getIdejecucion());
                attachedEjecucionplanificacioncalidadListNew.add(ejecucionplanificacioncalidadListNewEjecucionplanificacioncalidadToAttach);
            }
            ejecucionplanificacioncalidadListNew = attachedEjecucionplanificacioncalidadListNew;
            estadoejecplancalidad.setEjecucionplanificacioncalidadList(ejecucionplanificacioncalidadListNew);
            estadoejecplancalidad = em.merge(estadoejecplancalidad);
            for (Ejecucionplanificacioncalidad ejecucionplanificacioncalidadListOldEjecucionplanificacioncalidad : ejecucionplanificacioncalidadListOld) {
                if (!ejecucionplanificacioncalidadListNew.contains(ejecucionplanificacioncalidadListOldEjecucionplanificacioncalidad)) {
                    ejecucionplanificacioncalidadListOldEjecucionplanificacioncalidad.setEstado(null);
                    ejecucionplanificacioncalidadListOldEjecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidadListOldEjecucionplanificacioncalidad);
                }
            }
            for (Ejecucionplanificacioncalidad ejecucionplanificacioncalidadListNewEjecucionplanificacioncalidad : ejecucionplanificacioncalidadListNew) {
                if (!ejecucionplanificacioncalidadListOld.contains(ejecucionplanificacioncalidadListNewEjecucionplanificacioncalidad)) {
                    Estadoejecplancalidad oldEstadoOfEjecucionplanificacioncalidadListNewEjecucionplanificacioncalidad = ejecucionplanificacioncalidadListNewEjecucionplanificacioncalidad.getEstado();
                    ejecucionplanificacioncalidadListNewEjecucionplanificacioncalidad.setEstado(estadoejecplancalidad);
                    ejecucionplanificacioncalidadListNewEjecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidadListNewEjecucionplanificacioncalidad);
                    if (oldEstadoOfEjecucionplanificacioncalidadListNewEjecucionplanificacioncalidad != null && !oldEstadoOfEjecucionplanificacioncalidadListNewEjecucionplanificacioncalidad.equals(estadoejecplancalidad)) {
                        oldEstadoOfEjecucionplanificacioncalidadListNewEjecucionplanificacioncalidad.getEjecucionplanificacioncalidadList().remove(ejecucionplanificacioncalidadListNewEjecucionplanificacioncalidad);
                        oldEstadoOfEjecucionplanificacioncalidadListNewEjecucionplanificacioncalidad = em.merge(oldEstadoOfEjecucionplanificacioncalidadListNewEjecucionplanificacioncalidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadoejecplancalidad.getIdestado();
                if (findEstadoejecplancalidad(id) == null) {
                    throw new NonexistentEntityException("The estadoejecplancalidad with id " + id + " no longer exists.");
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
            Estadoejecplancalidad estadoejecplancalidad;
            try {
                estadoejecplancalidad = em.getReference(Estadoejecplancalidad.class, id);
                estadoejecplancalidad.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoejecplancalidad with id " + id + " no longer exists.", enfe);
            }
            List<Ejecucionplanificacioncalidad> ejecucionplanificacioncalidadList = estadoejecplancalidad.getEjecucionplanificacioncalidadList();
            for (Ejecucionplanificacioncalidad ejecucionplanificacioncalidadListEjecucionplanificacioncalidad : ejecucionplanificacioncalidadList) {
                ejecucionplanificacioncalidadListEjecucionplanificacioncalidad.setEstado(null);
                ejecucionplanificacioncalidadListEjecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidadListEjecucionplanificacioncalidad);
            }
            em.remove(estadoejecplancalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadoejecplancalidad> findEstadoejecplancalidadEntities() {
        return findEstadoejecplancalidadEntities(true, -1, -1);
    }

    public List<Estadoejecplancalidad> findEstadoejecplancalidadEntities(int maxResults, int firstResult) {
        return findEstadoejecplancalidadEntities(false, maxResults, firstResult);
    }

    private List<Estadoejecplancalidad> findEstadoejecplancalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadoejecplancalidad.class));
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

    public Estadoejecplancalidad findEstadoejecplancalidad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadoejecplancalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoejecplancalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadoejecplancalidad> rt = cq.from(Estadoejecplancalidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
