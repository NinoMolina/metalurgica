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
import metalsoft.datos.jpa.entity.Detalletrabajotercerizado;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Estadodetalletrabajotercerizado;

/**
 *
 * @author Nino
 */
public class EstadodetalletrabajotercerizadoJpaController implements Serializable {

    public EstadodetalletrabajotercerizadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadodetalletrabajotercerizado estadodetalletrabajotercerizado) throws PreexistingEntityException, Exception {
        if (estadodetalletrabajotercerizado.getDetalletrabajotercerizadoList() == null) {
            estadodetalletrabajotercerizado.setDetalletrabajotercerizadoList(new ArrayList<Detalletrabajotercerizado>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoList = new ArrayList<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoListDetalletrabajotercerizadoToAttach : estadodetalletrabajotercerizado.getDetalletrabajotercerizadoList()) {
                detalletrabajotercerizadoListDetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoListDetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoListDetalletrabajotercerizadoToAttach.getIddetalle());
                attachedDetalletrabajotercerizadoList.add(detalletrabajotercerizadoListDetalletrabajotercerizadoToAttach);
            }
            estadodetalletrabajotercerizado.setDetalletrabajotercerizadoList(attachedDetalletrabajotercerizadoList);
            em.persist(estadodetalletrabajotercerizado);
            for (Detalletrabajotercerizado detalletrabajotercerizadoListDetalletrabajotercerizado : estadodetalletrabajotercerizado.getDetalletrabajotercerizadoList()) {
                Estadodetalletrabajotercerizado oldEstadoOfDetalletrabajotercerizadoListDetalletrabajotercerizado = detalletrabajotercerizadoListDetalletrabajotercerizado.getEstado();
                detalletrabajotercerizadoListDetalletrabajotercerizado.setEstado(estadodetalletrabajotercerizado);
                detalletrabajotercerizadoListDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoListDetalletrabajotercerizado);
                if (oldEstadoOfDetalletrabajotercerizadoListDetalletrabajotercerizado != null) {
                    oldEstadoOfDetalletrabajotercerizadoListDetalletrabajotercerizado.getDetalletrabajotercerizadoList().remove(detalletrabajotercerizadoListDetalletrabajotercerizado);
                    oldEstadoOfDetalletrabajotercerizadoListDetalletrabajotercerizado = em.merge(oldEstadoOfDetalletrabajotercerizadoListDetalletrabajotercerizado);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadodetalletrabajotercerizado(estadodetalletrabajotercerizado.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadodetalletrabajotercerizado " + estadodetalletrabajotercerizado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadodetalletrabajotercerizado estadodetalletrabajotercerizado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadodetalletrabajotercerizado persistentEstadodetalletrabajotercerizado = em.find(Estadodetalletrabajotercerizado.class, estadodetalletrabajotercerizado.getIdestado());
            List<Detalletrabajotercerizado> detalletrabajotercerizadoListOld = persistentEstadodetalletrabajotercerizado.getDetalletrabajotercerizadoList();
            List<Detalletrabajotercerizado> detalletrabajotercerizadoListNew = estadodetalletrabajotercerizado.getDetalletrabajotercerizadoList();
            List<Detalletrabajotercerizado> attachedDetalletrabajotercerizadoListNew = new ArrayList<Detalletrabajotercerizado>();
            for (Detalletrabajotercerizado detalletrabajotercerizadoListNewDetalletrabajotercerizadoToAttach : detalletrabajotercerizadoListNew) {
                detalletrabajotercerizadoListNewDetalletrabajotercerizadoToAttach = em.getReference(detalletrabajotercerizadoListNewDetalletrabajotercerizadoToAttach.getClass(), detalletrabajotercerizadoListNewDetalletrabajotercerizadoToAttach.getIddetalle());
                attachedDetalletrabajotercerizadoListNew.add(detalletrabajotercerizadoListNewDetalletrabajotercerizadoToAttach);
            }
            detalletrabajotercerizadoListNew = attachedDetalletrabajotercerizadoListNew;
            estadodetalletrabajotercerizado.setDetalletrabajotercerizadoList(detalletrabajotercerizadoListNew);
            estadodetalletrabajotercerizado = em.merge(estadodetalletrabajotercerizado);
            for (Detalletrabajotercerizado detalletrabajotercerizadoListOldDetalletrabajotercerizado : detalletrabajotercerizadoListOld) {
                if (!detalletrabajotercerizadoListNew.contains(detalletrabajotercerizadoListOldDetalletrabajotercerizado)) {
                    detalletrabajotercerizadoListOldDetalletrabajotercerizado.setEstado(null);
                    detalletrabajotercerizadoListOldDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoListOldDetalletrabajotercerizado);
                }
            }
            for (Detalletrabajotercerizado detalletrabajotercerizadoListNewDetalletrabajotercerizado : detalletrabajotercerizadoListNew) {
                if (!detalletrabajotercerizadoListOld.contains(detalletrabajotercerizadoListNewDetalletrabajotercerizado)) {
                    Estadodetalletrabajotercerizado oldEstadoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado = detalletrabajotercerizadoListNewDetalletrabajotercerizado.getEstado();
                    detalletrabajotercerizadoListNewDetalletrabajotercerizado.setEstado(estadodetalletrabajotercerizado);
                    detalletrabajotercerizadoListNewDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoListNewDetalletrabajotercerizado);
                    if (oldEstadoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado != null && !oldEstadoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado.equals(estadodetalletrabajotercerizado)) {
                        oldEstadoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado.getDetalletrabajotercerizadoList().remove(detalletrabajotercerizadoListNewDetalletrabajotercerizado);
                        oldEstadoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado = em.merge(oldEstadoOfDetalletrabajotercerizadoListNewDetalletrabajotercerizado);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadodetalletrabajotercerizado.getIdestado();
                if (findEstadodetalletrabajotercerizado(id) == null) {
                    throw new NonexistentEntityException("The estadodetalletrabajotercerizado with id " + id + " no longer exists.");
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
            Estadodetalletrabajotercerizado estadodetalletrabajotercerizado;
            try {
                estadodetalletrabajotercerizado = em.getReference(Estadodetalletrabajotercerizado.class, id);
                estadodetalletrabajotercerizado.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadodetalletrabajotercerizado with id " + id + " no longer exists.", enfe);
            }
            List<Detalletrabajotercerizado> detalletrabajotercerizadoList = estadodetalletrabajotercerizado.getDetalletrabajotercerizadoList();
            for (Detalletrabajotercerizado detalletrabajotercerizadoListDetalletrabajotercerizado : detalletrabajotercerizadoList) {
                detalletrabajotercerizadoListDetalletrabajotercerizado.setEstado(null);
                detalletrabajotercerizadoListDetalletrabajotercerizado = em.merge(detalletrabajotercerizadoListDetalletrabajotercerizado);
            }
            em.remove(estadodetalletrabajotercerizado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadodetalletrabajotercerizado> findEstadodetalletrabajotercerizadoEntities() {
        return findEstadodetalletrabajotercerizadoEntities(true, -1, -1);
    }

    public List<Estadodetalletrabajotercerizado> findEstadodetalletrabajotercerizadoEntities(int maxResults, int firstResult) {
        return findEstadodetalletrabajotercerizadoEntities(false, maxResults, firstResult);
    }

    private List<Estadodetalletrabajotercerizado> findEstadodetalletrabajotercerizadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadodetalletrabajotercerizado.class));
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

    public Estadodetalletrabajotercerizado findEstadodetalletrabajotercerizado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadodetalletrabajotercerizado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadodetalletrabajotercerizadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadodetalletrabajotercerizado> rt = cq.from(Estadodetalletrabajotercerizado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
