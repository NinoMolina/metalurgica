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
import metalsoft.datos.jpa.entity.Ejecucionetapaproduccion;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Estadoejecetapaprod;

/**
 *
 * @author Nino
 */
public class EstadoejecetapaprodJpaController implements Serializable {

    public EstadoejecetapaprodJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadoejecetapaprod estadoejecetapaprod) throws PreexistingEntityException, Exception {
        if (estadoejecetapaprod.getEjecucionetapaproduccionList() == null) {
            estadoejecetapaprod.setEjecucionetapaproduccionList(new ArrayList<Ejecucionetapaproduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ejecucionetapaproduccion> attachedEjecucionetapaproduccionList = new ArrayList<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionListEjecucionetapaproduccionToAttach : estadoejecetapaprod.getEjecucionetapaproduccionList()) {
                ejecucionetapaproduccionListEjecucionetapaproduccionToAttach = em.getReference(ejecucionetapaproduccionListEjecucionetapaproduccionToAttach.getClass(), ejecucionetapaproduccionListEjecucionetapaproduccionToAttach.getId());
                attachedEjecucionetapaproduccionList.add(ejecucionetapaproduccionListEjecucionetapaproduccionToAttach);
            }
            estadoejecetapaprod.setEjecucionetapaproduccionList(attachedEjecucionetapaproduccionList);
            em.persist(estadoejecetapaprod);
            for (Ejecucionetapaproduccion ejecucionetapaproduccionListEjecucionetapaproduccion : estadoejecetapaprod.getEjecucionetapaproduccionList()) {
                Estadoejecetapaprod oldEstadoOfEjecucionetapaproduccionListEjecucionetapaproduccion = ejecucionetapaproduccionListEjecucionetapaproduccion.getEstado();
                ejecucionetapaproduccionListEjecucionetapaproduccion.setEstado(estadoejecetapaprod);
                ejecucionetapaproduccionListEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionListEjecucionetapaproduccion);
                if (oldEstadoOfEjecucionetapaproduccionListEjecucionetapaproduccion != null) {
                    oldEstadoOfEjecucionetapaproduccionListEjecucionetapaproduccion.getEjecucionetapaproduccionList().remove(ejecucionetapaproduccionListEjecucionetapaproduccion);
                    oldEstadoOfEjecucionetapaproduccionListEjecucionetapaproduccion = em.merge(oldEstadoOfEjecucionetapaproduccionListEjecucionetapaproduccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoejecetapaprod(estadoejecetapaprod.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadoejecetapaprod " + estadoejecetapaprod + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadoejecetapaprod estadoejecetapaprod) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoejecetapaprod persistentEstadoejecetapaprod = em.find(Estadoejecetapaprod.class, estadoejecetapaprod.getIdestado());
            List<Ejecucionetapaproduccion> ejecucionetapaproduccionListOld = persistentEstadoejecetapaprod.getEjecucionetapaproduccionList();
            List<Ejecucionetapaproduccion> ejecucionetapaproduccionListNew = estadoejecetapaprod.getEjecucionetapaproduccionList();
            List<Ejecucionetapaproduccion> attachedEjecucionetapaproduccionListNew = new ArrayList<Ejecucionetapaproduccion>();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionListNewEjecucionetapaproduccionToAttach : ejecucionetapaproduccionListNew) {
                ejecucionetapaproduccionListNewEjecucionetapaproduccionToAttach = em.getReference(ejecucionetapaproduccionListNewEjecucionetapaproduccionToAttach.getClass(), ejecucionetapaproduccionListNewEjecucionetapaproduccionToAttach.getId());
                attachedEjecucionetapaproduccionListNew.add(ejecucionetapaproduccionListNewEjecucionetapaproduccionToAttach);
            }
            ejecucionetapaproduccionListNew = attachedEjecucionetapaproduccionListNew;
            estadoejecetapaprod.setEjecucionetapaproduccionList(ejecucionetapaproduccionListNew);
            estadoejecetapaprod = em.merge(estadoejecetapaprod);
            for (Ejecucionetapaproduccion ejecucionetapaproduccionListOldEjecucionetapaproduccion : ejecucionetapaproduccionListOld) {
                if (!ejecucionetapaproduccionListNew.contains(ejecucionetapaproduccionListOldEjecucionetapaproduccion)) {
                    ejecucionetapaproduccionListOldEjecucionetapaproduccion.setEstado(null);
                    ejecucionetapaproduccionListOldEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionListOldEjecucionetapaproduccion);
                }
            }
            for (Ejecucionetapaproduccion ejecucionetapaproduccionListNewEjecucionetapaproduccion : ejecucionetapaproduccionListNew) {
                if (!ejecucionetapaproduccionListOld.contains(ejecucionetapaproduccionListNewEjecucionetapaproduccion)) {
                    Estadoejecetapaprod oldEstadoOfEjecucionetapaproduccionListNewEjecucionetapaproduccion = ejecucionetapaproduccionListNewEjecucionetapaproduccion.getEstado();
                    ejecucionetapaproduccionListNewEjecucionetapaproduccion.setEstado(estadoejecetapaprod);
                    ejecucionetapaproduccionListNewEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionListNewEjecucionetapaproduccion);
                    if (oldEstadoOfEjecucionetapaproduccionListNewEjecucionetapaproduccion != null && !oldEstadoOfEjecucionetapaproduccionListNewEjecucionetapaproduccion.equals(estadoejecetapaprod)) {
                        oldEstadoOfEjecucionetapaproduccionListNewEjecucionetapaproduccion.getEjecucionetapaproduccionList().remove(ejecucionetapaproduccionListNewEjecucionetapaproduccion);
                        oldEstadoOfEjecucionetapaproduccionListNewEjecucionetapaproduccion = em.merge(oldEstadoOfEjecucionetapaproduccionListNewEjecucionetapaproduccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadoejecetapaprod.getIdestado();
                if (findEstadoejecetapaprod(id) == null) {
                    throw new NonexistentEntityException("The estadoejecetapaprod with id " + id + " no longer exists.");
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
            Estadoejecetapaprod estadoejecetapaprod;
            try {
                estadoejecetapaprod = em.getReference(Estadoejecetapaprod.class, id);
                estadoejecetapaprod.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoejecetapaprod with id " + id + " no longer exists.", enfe);
            }
            List<Ejecucionetapaproduccion> ejecucionetapaproduccionList = estadoejecetapaprod.getEjecucionetapaproduccionList();
            for (Ejecucionetapaproduccion ejecucionetapaproduccionListEjecucionetapaproduccion : ejecucionetapaproduccionList) {
                ejecucionetapaproduccionListEjecucionetapaproduccion.setEstado(null);
                ejecucionetapaproduccionListEjecucionetapaproduccion = em.merge(ejecucionetapaproduccionListEjecucionetapaproduccion);
            }
            em.remove(estadoejecetapaprod);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadoejecetapaprod> findEstadoejecetapaprodEntities() {
        return findEstadoejecetapaprodEntities(true, -1, -1);
    }

    public List<Estadoejecetapaprod> findEstadoejecetapaprodEntities(int maxResults, int firstResult) {
        return findEstadoejecetapaprodEntities(false, maxResults, firstResult);
    }

    private List<Estadoejecetapaprod> findEstadoejecetapaprodEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadoejecetapaprod.class));
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

    public Estadoejecetapaprod findEstadoejecetapaprod(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadoejecetapaprod.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoejecetapaprodCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadoejecetapaprod> rt = cq.from(Estadoejecetapaprod.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
