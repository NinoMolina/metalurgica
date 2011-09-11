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
import metalsoft.datos.jpa.entity.Detallecompra;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Estadodetallecompra;

/**
 *
 * @author Nino
 */
public class EstadodetallecompraJpaController implements Serializable {

    public EstadodetallecompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estadodetallecompra estadodetallecompra) throws PreexistingEntityException, Exception {
        if (estadodetallecompra.getDetallecompraList() == null) {
            estadodetallecompra.setDetallecompraList(new ArrayList<Detallecompra>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Detallecompra> attachedDetallecompraList = new ArrayList<Detallecompra>();
            for (Detallecompra detallecompraListDetallecompraToAttach : estadodetallecompra.getDetallecompraList()) {
                detallecompraListDetallecompraToAttach = em.getReference(detallecompraListDetallecompraToAttach.getClass(), detallecompraListDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraList.add(detallecompraListDetallecompraToAttach);
            }
            estadodetallecompra.setDetallecompraList(attachedDetallecompraList);
            em.persist(estadodetallecompra);
            for (Detallecompra detallecompraListDetallecompra : estadodetallecompra.getDetallecompraList()) {
                Estadodetallecompra oldEstadoOfDetallecompraListDetallecompra = detallecompraListDetallecompra.getEstado();
                detallecompraListDetallecompra.setEstado(estadodetallecompra);
                detallecompraListDetallecompra = em.merge(detallecompraListDetallecompra);
                if (oldEstadoOfDetallecompraListDetallecompra != null) {
                    oldEstadoOfDetallecompraListDetallecompra.getDetallecompraList().remove(detallecompraListDetallecompra);
                    oldEstadoOfDetallecompraListDetallecompra = em.merge(oldEstadoOfDetallecompraListDetallecompra);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstadodetallecompra(estadodetallecompra.getIdestado()) != null) {
                throw new PreexistingEntityException("Estadodetallecompra " + estadodetallecompra + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estadodetallecompra estadodetallecompra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadodetallecompra persistentEstadodetallecompra = em.find(Estadodetallecompra.class, estadodetallecompra.getIdestado());
            List<Detallecompra> detallecompraListOld = persistentEstadodetallecompra.getDetallecompraList();
            List<Detallecompra> detallecompraListNew = estadodetallecompra.getDetallecompraList();
            List<Detallecompra> attachedDetallecompraListNew = new ArrayList<Detallecompra>();
            for (Detallecompra detallecompraListNewDetallecompraToAttach : detallecompraListNew) {
                detallecompraListNewDetallecompraToAttach = em.getReference(detallecompraListNewDetallecompraToAttach.getClass(), detallecompraListNewDetallecompraToAttach.getDetallecompraPK());
                attachedDetallecompraListNew.add(detallecompraListNewDetallecompraToAttach);
            }
            detallecompraListNew = attachedDetallecompraListNew;
            estadodetallecompra.setDetallecompraList(detallecompraListNew);
            estadodetallecompra = em.merge(estadodetallecompra);
            for (Detallecompra detallecompraListOldDetallecompra : detallecompraListOld) {
                if (!detallecompraListNew.contains(detallecompraListOldDetallecompra)) {
                    detallecompraListOldDetallecompra.setEstado(null);
                    detallecompraListOldDetallecompra = em.merge(detallecompraListOldDetallecompra);
                }
            }
            for (Detallecompra detallecompraListNewDetallecompra : detallecompraListNew) {
                if (!detallecompraListOld.contains(detallecompraListNewDetallecompra)) {
                    Estadodetallecompra oldEstadoOfDetallecompraListNewDetallecompra = detallecompraListNewDetallecompra.getEstado();
                    detallecompraListNewDetallecompra.setEstado(estadodetallecompra);
                    detallecompraListNewDetallecompra = em.merge(detallecompraListNewDetallecompra);
                    if (oldEstadoOfDetallecompraListNewDetallecompra != null && !oldEstadoOfDetallecompraListNewDetallecompra.equals(estadodetallecompra)) {
                        oldEstadoOfDetallecompraListNewDetallecompra.getDetallecompraList().remove(detallecompraListNewDetallecompra);
                        oldEstadoOfDetallecompraListNewDetallecompra = em.merge(oldEstadoOfDetallecompraListNewDetallecompra);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = estadodetallecompra.getIdestado();
                if (findEstadodetallecompra(id) == null) {
                    throw new NonexistentEntityException("The estadodetallecompra with id " + id + " no longer exists.");
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
            Estadodetallecompra estadodetallecompra;
            try {
                estadodetallecompra = em.getReference(Estadodetallecompra.class, id);
                estadodetallecompra.getIdestado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadodetallecompra with id " + id + " no longer exists.", enfe);
            }
            List<Detallecompra> detallecompraList = estadodetallecompra.getDetallecompraList();
            for (Detallecompra detallecompraListDetallecompra : detallecompraList) {
                detallecompraListDetallecompra.setEstado(null);
                detallecompraListDetallecompra = em.merge(detallecompraListDetallecompra);
            }
            em.remove(estadodetallecompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estadodetallecompra> findEstadodetallecompraEntities() {
        return findEstadodetallecompraEntities(true, -1, -1);
    }

    public List<Estadodetallecompra> findEstadodetallecompraEntities(int maxResults, int firstResult) {
        return findEstadodetallecompraEntities(false, maxResults, firstResult);
    }

    private List<Estadodetallecompra> findEstadodetallecompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estadodetallecompra.class));
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

    public Estadodetallecompra findEstadodetallecompra(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estadodetallecompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadodetallecompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estadodetallecompra> rt = cq.from(Estadodetallecompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
