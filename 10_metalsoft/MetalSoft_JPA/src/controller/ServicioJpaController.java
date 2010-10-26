/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Servicio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Detallemantenimientopreventivo;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class ServicioJpaController {

    public ServicioJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicio servicio) throws PreexistingEntityException, Exception {
        if (servicio.getDetallemantenimientopreventivoSet() == null) {
            servicio.setDetallemantenimientopreventivoSet(new HashSet<Detallemantenimientopreventivo>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Detallemantenimientopreventivo> attachedDetallemantenimientopreventivoSet = new HashSet<Detallemantenimientopreventivo>();
            for (Detallemantenimientopreventivo detallemantenimientopreventivoSetDetallemantenimientopreventivoToAttach : servicio.getDetallemantenimientopreventivoSet()) {
                detallemantenimientopreventivoSetDetallemantenimientopreventivoToAttach = em.getReference(detallemantenimientopreventivoSetDetallemantenimientopreventivoToAttach.getClass(), detallemantenimientopreventivoSetDetallemantenimientopreventivoToAttach.getDetallemantenimientopreventivoPK());
                attachedDetallemantenimientopreventivoSet.add(detallemantenimientopreventivoSetDetallemantenimientopreventivoToAttach);
            }
            servicio.setDetallemantenimientopreventivoSet(attachedDetallemantenimientopreventivoSet);
            em.persist(servicio);
            for (Detallemantenimientopreventivo detallemantenimientopreventivoSetDetallemantenimientopreventivo : servicio.getDetallemantenimientopreventivoSet()) {
                Servicio oldServicioOfDetallemantenimientopreventivoSetDetallemantenimientopreventivo = detallemantenimientopreventivoSetDetallemantenimientopreventivo.getServicio();
                detallemantenimientopreventivoSetDetallemantenimientopreventivo.setServicio(servicio);
                detallemantenimientopreventivoSetDetallemantenimientopreventivo = em.merge(detallemantenimientopreventivoSetDetallemantenimientopreventivo);
                if (oldServicioOfDetallemantenimientopreventivoSetDetallemantenimientopreventivo != null) {
                    oldServicioOfDetallemantenimientopreventivoSetDetallemantenimientopreventivo.getDetallemantenimientopreventivoSet().remove(detallemantenimientopreventivoSetDetallemantenimientopreventivo);
                    oldServicioOfDetallemantenimientopreventivoSetDetallemantenimientopreventivo = em.merge(oldServicioOfDetallemantenimientopreventivoSetDetallemantenimientopreventivo);
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
            Set<Detallemantenimientopreventivo> detallemantenimientopreventivoSetOld = persistentServicio.getDetallemantenimientopreventivoSet();
            Set<Detallemantenimientopreventivo> detallemantenimientopreventivoSetNew = servicio.getDetallemantenimientopreventivoSet();
            Set<Detallemantenimientopreventivo> attachedDetallemantenimientopreventivoSetNew = new HashSet<Detallemantenimientopreventivo>();
            for (Detallemantenimientopreventivo detallemantenimientopreventivoSetNewDetallemantenimientopreventivoToAttach : detallemantenimientopreventivoSetNew) {
                detallemantenimientopreventivoSetNewDetallemantenimientopreventivoToAttach = em.getReference(detallemantenimientopreventivoSetNewDetallemantenimientopreventivoToAttach.getClass(), detallemantenimientopreventivoSetNewDetallemantenimientopreventivoToAttach.getDetallemantenimientopreventivoPK());
                attachedDetallemantenimientopreventivoSetNew.add(detallemantenimientopreventivoSetNewDetallemantenimientopreventivoToAttach);
            }
            detallemantenimientopreventivoSetNew = attachedDetallemantenimientopreventivoSetNew;
            servicio.setDetallemantenimientopreventivoSet(detallemantenimientopreventivoSetNew);
            servicio = em.merge(servicio);
            for (Detallemantenimientopreventivo detallemantenimientopreventivoSetOldDetallemantenimientopreventivo : detallemantenimientopreventivoSetOld) {
                if (!detallemantenimientopreventivoSetNew.contains(detallemantenimientopreventivoSetOldDetallemantenimientopreventivo)) {
                    detallemantenimientopreventivoSetOldDetallemantenimientopreventivo.setServicio(null);
                    detallemantenimientopreventivoSetOldDetallemantenimientopreventivo = em.merge(detallemantenimientopreventivoSetOldDetallemantenimientopreventivo);
                }
            }
            for (Detallemantenimientopreventivo detallemantenimientopreventivoSetNewDetallemantenimientopreventivo : detallemantenimientopreventivoSetNew) {
                if (!detallemantenimientopreventivoSetOld.contains(detallemantenimientopreventivoSetNewDetallemantenimientopreventivo)) {
                    Servicio oldServicioOfDetallemantenimientopreventivoSetNewDetallemantenimientopreventivo = detallemantenimientopreventivoSetNewDetallemantenimientopreventivo.getServicio();
                    detallemantenimientopreventivoSetNewDetallemantenimientopreventivo.setServicio(servicio);
                    detallemantenimientopreventivoSetNewDetallemantenimientopreventivo = em.merge(detallemantenimientopreventivoSetNewDetallemantenimientopreventivo);
                    if (oldServicioOfDetallemantenimientopreventivoSetNewDetallemantenimientopreventivo != null && !oldServicioOfDetallemantenimientopreventivoSetNewDetallemantenimientopreventivo.equals(servicio)) {
                        oldServicioOfDetallemantenimientopreventivoSetNewDetallemantenimientopreventivo.getDetallemantenimientopreventivoSet().remove(detallemantenimientopreventivoSetNewDetallemantenimientopreventivo);
                        oldServicioOfDetallemantenimientopreventivoSetNewDetallemantenimientopreventivo = em.merge(oldServicioOfDetallemantenimientopreventivoSetNewDetallemantenimientopreventivo);
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
            Set<Detallemantenimientopreventivo> detallemantenimientopreventivoSet = servicio.getDetallemantenimientopreventivoSet();
            for (Detallemantenimientopreventivo detallemantenimientopreventivoSetDetallemantenimientopreventivo : detallemantenimientopreventivoSet) {
                detallemantenimientopreventivoSetDetallemantenimientopreventivo.setServicio(null);
                detallemantenimientopreventivoSetDetallemantenimientopreventivo = em.merge(detallemantenimientopreventivoSetDetallemantenimientopreventivo);
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
