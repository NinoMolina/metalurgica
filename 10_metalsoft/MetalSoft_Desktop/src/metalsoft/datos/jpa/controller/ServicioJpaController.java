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
import metalsoft.datos.jpa.entity.Detallemantenimientopreventivo;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Servicio;

/**
 *
 * @author Nino
 */
public class ServicioJpaController implements Serializable {

    public ServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicio servicio) throws PreexistingEntityException, Exception {
        if (servicio.getDetallemantenimientopreventivoList() == null) {
            servicio.setDetallemantenimientopreventivoList(new ArrayList<Detallemantenimientopreventivo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Detallemantenimientopreventivo> attachedDetallemantenimientopreventivoList = new ArrayList<Detallemantenimientopreventivo>();
            for (Detallemantenimientopreventivo detallemantenimientopreventivoListDetallemantenimientopreventivoToAttach : servicio.getDetallemantenimientopreventivoList()) {
                detallemantenimientopreventivoListDetallemantenimientopreventivoToAttach = em.getReference(detallemantenimientopreventivoListDetallemantenimientopreventivoToAttach.getClass(), detallemantenimientopreventivoListDetallemantenimientopreventivoToAttach.getDetallemantenimientopreventivoPK());
                attachedDetallemantenimientopreventivoList.add(detallemantenimientopreventivoListDetallemantenimientopreventivoToAttach);
            }
            servicio.setDetallemantenimientopreventivoList(attachedDetallemantenimientopreventivoList);
            em.persist(servicio);
            for (Detallemantenimientopreventivo detallemantenimientopreventivoListDetallemantenimientopreventivo : servicio.getDetallemantenimientopreventivoList()) {
                Servicio oldServicioOfDetallemantenimientopreventivoListDetallemantenimientopreventivo = detallemantenimientopreventivoListDetallemantenimientopreventivo.getServicio();
                detallemantenimientopreventivoListDetallemantenimientopreventivo.setServicio(servicio);
                detallemantenimientopreventivoListDetallemantenimientopreventivo = em.merge(detallemantenimientopreventivoListDetallemantenimientopreventivo);
                if (oldServicioOfDetallemantenimientopreventivoListDetallemantenimientopreventivo != null) {
                    oldServicioOfDetallemantenimientopreventivoListDetallemantenimientopreventivo.getDetallemantenimientopreventivoList().remove(detallemantenimientopreventivoListDetallemantenimientopreventivo);
                    oldServicioOfDetallemantenimientopreventivoListDetallemantenimientopreventivo = em.merge(oldServicioOfDetallemantenimientopreventivoListDetallemantenimientopreventivo);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findServicio(servicio.getIdservicio()) != null) {
                throw new PreexistingEntityException("Servicio " + servicio + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicio servicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio persistentServicio = em.find(Servicio.class, servicio.getIdservicio());
            List<Detallemantenimientopreventivo> detallemantenimientopreventivoListOld = persistentServicio.getDetallemantenimientopreventivoList();
            List<Detallemantenimientopreventivo> detallemantenimientopreventivoListNew = servicio.getDetallemantenimientopreventivoList();
            List<Detallemantenimientopreventivo> attachedDetallemantenimientopreventivoListNew = new ArrayList<Detallemantenimientopreventivo>();
            for (Detallemantenimientopreventivo detallemantenimientopreventivoListNewDetallemantenimientopreventivoToAttach : detallemantenimientopreventivoListNew) {
                detallemantenimientopreventivoListNewDetallemantenimientopreventivoToAttach = em.getReference(detallemantenimientopreventivoListNewDetallemantenimientopreventivoToAttach.getClass(), detallemantenimientopreventivoListNewDetallemantenimientopreventivoToAttach.getDetallemantenimientopreventivoPK());
                attachedDetallemantenimientopreventivoListNew.add(detallemantenimientopreventivoListNewDetallemantenimientopreventivoToAttach);
            }
            detallemantenimientopreventivoListNew = attachedDetallemantenimientopreventivoListNew;
            servicio.setDetallemantenimientopreventivoList(detallemantenimientopreventivoListNew);
            servicio = em.merge(servicio);
            for (Detallemantenimientopreventivo detallemantenimientopreventivoListOldDetallemantenimientopreventivo : detallemantenimientopreventivoListOld) {
                if (!detallemantenimientopreventivoListNew.contains(detallemantenimientopreventivoListOldDetallemantenimientopreventivo)) {
                    detallemantenimientopreventivoListOldDetallemantenimientopreventivo.setServicio(null);
                    detallemantenimientopreventivoListOldDetallemantenimientopreventivo = em.merge(detallemantenimientopreventivoListOldDetallemantenimientopreventivo);
                }
            }
            for (Detallemantenimientopreventivo detallemantenimientopreventivoListNewDetallemantenimientopreventivo : detallemantenimientopreventivoListNew) {
                if (!detallemantenimientopreventivoListOld.contains(detallemantenimientopreventivoListNewDetallemantenimientopreventivo)) {
                    Servicio oldServicioOfDetallemantenimientopreventivoListNewDetallemantenimientopreventivo = detallemantenimientopreventivoListNewDetallemantenimientopreventivo.getServicio();
                    detallemantenimientopreventivoListNewDetallemantenimientopreventivo.setServicio(servicio);
                    detallemantenimientopreventivoListNewDetallemantenimientopreventivo = em.merge(detallemantenimientopreventivoListNewDetallemantenimientopreventivo);
                    if (oldServicioOfDetallemantenimientopreventivoListNewDetallemantenimientopreventivo != null && !oldServicioOfDetallemantenimientopreventivoListNewDetallemantenimientopreventivo.equals(servicio)) {
                        oldServicioOfDetallemantenimientopreventivoListNewDetallemantenimientopreventivo.getDetallemantenimientopreventivoList().remove(detallemantenimientopreventivoListNewDetallemantenimientopreventivo);
                        oldServicioOfDetallemantenimientopreventivoListNewDetallemantenimientopreventivo = em.merge(oldServicioOfDetallemantenimientopreventivoListNewDetallemantenimientopreventivo);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = servicio.getIdservicio();
                if (findServicio(id) == null) {
                    throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.");
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
            Servicio servicio;
            try {
                servicio = em.getReference(Servicio.class, id);
                servicio.getIdservicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.", enfe);
            }
            List<Detallemantenimientopreventivo> detallemantenimientopreventivoList = servicio.getDetallemantenimientopreventivoList();
            for (Detallemantenimientopreventivo detallemantenimientopreventivoListDetallemantenimientopreventivo : detallemantenimientopreventivoList) {
                detallemantenimientopreventivoListDetallemantenimientopreventivo.setServicio(null);
                detallemantenimientopreventivoListDetallemantenimientopreventivo = em.merge(detallemantenimientopreventivoListDetallemantenimientopreventivo);
            }
            em.remove(servicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicio> findServicioEntities() {
        return findServicioEntities(true, -1, -1);
    }

    public List<Servicio> findServicioEntities(int maxResults, int firstResult) {
        return findServicioEntities(false, maxResults, firstResult);
    }

    private List<Servicio> findServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicio.class));
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

    public Servicio findServicio(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicio> rt = cq.from(Servicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
