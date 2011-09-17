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
import metalsoft.datos.jpa.entity.Ejecucionplanificacionproduccion;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Estadoejecplanifpedido;

/**
 *
 * @author Nino
 */
public class EstadoejecplanifpedidoJpaController implements Serializable {

    public EstadoejecplanifpedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadoejecplanifpedido estadoejecplanifpedido) throws PreexistingEntityException, Exception {
        if (estadoejecplanifpedido.getEjecucionplanificacionproduccionList() == null) {
            estadoejecplanifpedido.setEjecucionplanificacionproduccionList(new ArrayList<Ejecucionplanificacionproduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Ejecucionplanificacionproduccion> attachedEjecucionplanificacionproduccionList = new ArrayList<Ejecucionplanificacionproduccion>();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionListEjecucionplanificacionproduccionToAttach : estadoejecplanifpedido.getEjecucionplanificacionproduccionList()) {
                ejecucionplanificacionproduccionListEjecucionplanificacionproduccionToAttach = em.getReference(ejecucionplanificacionproduccionListEjecucionplanificacionproduccionToAttach.getClass(), ejecucionplanificacionproduccionListEjecucionplanificacionproduccionToAttach.getIdejecucion());
                attachedEjecucionplanificacionproduccionList.add(ejecucionplanificacionproduccionListEjecucionplanificacionproduccionToAttach);
            }
            estadoejecplanifpedido.setEjecucionplanificacionproduccionList(attachedEjecucionplanificacionproduccionList);
            em.persist(estadoejecplanifpedido);
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionListEjecucionplanificacionproduccion : estadoejecplanifpedido.getEjecucionplanificacionproduccionList()) {
                Estadoejecplanifpedido oldEstadoOfEjecucionplanificacionproduccionListEjecucionplanificacionproduccion = ejecucionplanificacionproduccionListEjecucionplanificacionproduccion.getEstado();
                ejecucionplanificacionproduccionListEjecucionplanificacionproduccion.setEstado(estadoejecplanifpedido);
                ejecucionplanificacionproduccionListEjecucionplanificacionproduccion = em.merge(ejecucionplanificacionproduccionListEjecucionplanificacionproduccion);
                if (oldEstadoOfEjecucionplanificacionproduccionListEjecucionplanificacionproduccion != null) {
                    oldEstadoOfEjecucionplanificacionproduccionListEjecucionplanificacionproduccion.getEjecucionplanificacionproduccionList().remove(ejecucionplanificacionproduccionListEjecucionplanificacionproduccion);
                    oldEstadoOfEjecucionplanificacionproduccionListEjecucionplanificacionproduccion = em.merge(oldEstadoOfEjecucionplanificacionproduccionListEjecucionplanificacionproduccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadoejecplanifpedido(estadoejecplanifpedido.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadoejecplanifpedido " + estadoejecplanifpedido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadoejecplanifpedido estadoejecplanifpedido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoejecplanifpedido persistentEstadoejecplanifpedido = em.find(Estadoejecplanifpedido.class, estadoejecplanifpedido.getIdestado());
            List<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionListOld = persistentEstadoejecplanifpedido.getEjecucionplanificacionproduccionList();
            List<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionListNew = estadoejecplanifpedido.getEjecucionplanificacionproduccionList();
            List<Ejecucionplanificacionproduccion> attachedEjecucionplanificacionproduccionListNew = new ArrayList<Ejecucionplanificacionproduccion>();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccionToAttach : ejecucionplanificacionproduccionListNew) {
                ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccionToAttach = em.getReference(ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccionToAttach.getClass(), ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccionToAttach.getIdejecucion());
                attachedEjecucionplanificacionproduccionListNew.add(ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccionToAttach);
            }
            ejecucionplanificacionproduccionListNew = attachedEjecucionplanificacionproduccionListNew;
            estadoejecplanifpedido.setEjecucionplanificacionproduccionList(ejecucionplanificacionproduccionListNew);
            estadoejecplanifpedido = em.merge(estadoejecplanifpedido);
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionListOldEjecucionplanificacionproduccion : ejecucionplanificacionproduccionListOld) {
                if (!ejecucionplanificacionproduccionListNew.contains(ejecucionplanificacionproduccionListOldEjecucionplanificacionproduccion)) {
                    ejecucionplanificacionproduccionListOldEjecucionplanificacionproduccion.setEstado(null);
                    ejecucionplanificacionproduccionListOldEjecucionplanificacionproduccion = em.merge(ejecucionplanificacionproduccionListOldEjecucionplanificacionproduccion);
                }
            }
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccion : ejecucionplanificacionproduccionListNew) {
                if (!ejecucionplanificacionproduccionListOld.contains(ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccion)) {
                    Estadoejecplanifpedido oldEstadoOfEjecucionplanificacionproduccionListNewEjecucionplanificacionproduccion = ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccion.getEstado();
                    ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccion.setEstado(estadoejecplanifpedido);
                    ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccion = em.merge(ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccion);
                    if (oldEstadoOfEjecucionplanificacionproduccionListNewEjecucionplanificacionproduccion != null && !oldEstadoOfEjecucionplanificacionproduccionListNewEjecucionplanificacionproduccion.equals(estadoejecplanifpedido)) {
                        oldEstadoOfEjecucionplanificacionproduccionListNewEjecucionplanificacionproduccion.getEjecucionplanificacionproduccionList().remove(ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccion);
                        oldEstadoOfEjecucionplanificacionproduccionListNewEjecucionplanificacionproduccion = em.merge(oldEstadoOfEjecucionplanificacionproduccionListNewEjecucionplanificacionproduccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadoejecplanifpedido.getIdestado();
                if (findEstadoejecplanifpedido(id) == null) {
                    throw new NonexistentEntityException("The estadoejecplanifpedido with id " + id + " no longer exists.");
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
            Estadoejecplanifpedido estadoejecplanifpedido;
            try {
                estadoejecplanifpedido = em.getReference(Estadoejecplanifpedido.class, id);
                estadoejecplanifpedido.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoejecplanifpedido with id " + id + " no longer exists.", enfe);
            }
            List<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionList = estadoejecplanifpedido.getEjecucionplanificacionproduccionList();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionListEjecucionplanificacionproduccion : ejecucionplanificacionproduccionList) {
                ejecucionplanificacionproduccionListEjecucionplanificacionproduccion.setEstado(null);
                ejecucionplanificacionproduccionListEjecucionplanificacionproduccion = em.merge(ejecucionplanificacionproduccionListEjecucionplanificacionproduccion);
            }
            em.remove(estadoejecplanifpedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadoejecplanifpedido> findEstadoejecplanifpedidoEntities() {
        return findEstadoejecplanifpedidoEntities(true, -1, -1);
    }

    public List<Estadoejecplanifpedido> findEstadoejecplanifpedidoEntities(int maxResults, int firstResult) {
        return findEstadoejecplanifpedidoEntities(false, maxResults, firstResult);
    }

    private List<Estadoejecplanifpedido> findEstadoejecplanifpedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadoejecplanifpedido.class));
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

    public Estadoejecplanifpedido findEstadoejecplanifpedido(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadoejecplanifpedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoejecplanifpedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadoejecplanifpedido> rt = cq.from(Estadoejecplanifpedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
