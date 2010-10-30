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
        if (servicio.getDetallemantenimientopreventivoSet1() == null) {
            servicio.setDetallemantenimientopreventivoSet1(new HashSet<Detallemantenimientopreventivo>());
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
            Set<Detallemantenimientopreventivo> attachedDetallemantenimientopreventivoSet1 = new HashSet<Detallemantenimientopreventivo>();
            for (Detallemantenimientopreventivo detallemantenimientopreventivoSet1DetallemantenimientopreventivoToAttach : servicio.getDetallemantenimientopreventivoSet1()) {
                detallemantenimientopreventivoSet1DetallemantenimientopreventivoToAttach = em.getReference(detallemantenimientopreventivoSet1DetallemantenimientopreventivoToAttach.getClass(), detallemantenimientopreventivoSet1DetallemantenimientopreventivoToAttach.getDetallemantenimientopreventivoPK());
                attachedDetallemantenimientopreventivoSet1.add(detallemantenimientopreventivoSet1DetallemantenimientopreventivoToAttach);
            }
            servicio.setDetallemantenimientopreventivoSet1(attachedDetallemantenimientopreventivoSet1);
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
            for (Detallemantenimientopreventivo detallemantenimientopreventivoSet1Detallemantenimientopreventivo : servicio.getDetallemantenimientopreventivoSet1()) {
                Servicio oldServicio1OfDetallemantenimientopreventivoSet1Detallemantenimientopreventivo = detallemantenimientopreventivoSet1Detallemantenimientopreventivo.getServicio1();
                detallemantenimientopreventivoSet1Detallemantenimientopreventivo.setServicio1(servicio);
                detallemantenimientopreventivoSet1Detallemantenimientopreventivo = em.merge(detallemantenimientopreventivoSet1Detallemantenimientopreventivo);
                if (oldServicio1OfDetallemantenimientopreventivoSet1Detallemantenimientopreventivo != null) {
                    oldServicio1OfDetallemantenimientopreventivoSet1Detallemantenimientopreventivo.getDetallemantenimientopreventivoSet1().remove(detallemantenimientopreventivoSet1Detallemantenimientopreventivo);
                    oldServicio1OfDetallemantenimientopreventivoSet1Detallemantenimientopreventivo = em.merge(oldServicio1OfDetallemantenimientopreventivoSet1Detallemantenimientopreventivo);
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
            Set<Detallemantenimientopreventivo> detallemantenimientopreventivoSet1Old = persistentServicio.getDetallemantenimientopreventivoSet1();
            Set<Detallemantenimientopreventivo> detallemantenimientopreventivoSet1New = servicio.getDetallemantenimientopreventivoSet1();
            Set<Detallemantenimientopreventivo> attachedDetallemantenimientopreventivoSetNew = new HashSet<Detallemantenimientopreventivo>();
            for (Detallemantenimientopreventivo detallemantenimientopreventivoSetNewDetallemantenimientopreventivoToAttach : detallemantenimientopreventivoSetNew) {
                detallemantenimientopreventivoSetNewDetallemantenimientopreventivoToAttach = em.getReference(detallemantenimientopreventivoSetNewDetallemantenimientopreventivoToAttach.getClass(), detallemantenimientopreventivoSetNewDetallemantenimientopreventivoToAttach.getDetallemantenimientopreventivoPK());
                attachedDetallemantenimientopreventivoSetNew.add(detallemantenimientopreventivoSetNewDetallemantenimientopreventivoToAttach);
            }
            detallemantenimientopreventivoSetNew = attachedDetallemantenimientopreventivoSetNew;
            servicio.setDetallemantenimientopreventivoSet(detallemantenimientopreventivoSetNew);
            Set<Detallemantenimientopreventivo> attachedDetallemantenimientopreventivoSet1New = new HashSet<Detallemantenimientopreventivo>();
            for (Detallemantenimientopreventivo detallemantenimientopreventivoSet1NewDetallemantenimientopreventivoToAttach : detallemantenimientopreventivoSet1New) {
                detallemantenimientopreventivoSet1NewDetallemantenimientopreventivoToAttach = em.getReference(detallemantenimientopreventivoSet1NewDetallemantenimientopreventivoToAttach.getClass(), detallemantenimientopreventivoSet1NewDetallemantenimientopreventivoToAttach.getDetallemantenimientopreventivoPK());
                attachedDetallemantenimientopreventivoSet1New.add(detallemantenimientopreventivoSet1NewDetallemantenimientopreventivoToAttach);
            }
            detallemantenimientopreventivoSet1New = attachedDetallemantenimientopreventivoSet1New;
            servicio.setDetallemantenimientopreventivoSet1(detallemantenimientopreventivoSet1New);
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
            for (Detallemantenimientopreventivo detallemantenimientopreventivoSet1OldDetallemantenimientopreventivo : detallemantenimientopreventivoSet1Old) {
                if (!detallemantenimientopreventivoSet1New.contains(detallemantenimientopreventivoSet1OldDetallemantenimientopreventivo)) {
                    detallemantenimientopreventivoSet1OldDetallemantenimientopreventivo.setServicio1(null);
                    detallemantenimientopreventivoSet1OldDetallemantenimientopreventivo = em.merge(detallemantenimientopreventivoSet1OldDetallemantenimientopreventivo);
                }
            }
            for (Detallemantenimientopreventivo detallemantenimientopreventivoSet1NewDetallemantenimientopreventivo : detallemantenimientopreventivoSet1New) {
                if (!detallemantenimientopreventivoSet1Old.contains(detallemantenimientopreventivoSet1NewDetallemantenimientopreventivo)) {
                    Servicio oldServicio1OfDetallemantenimientopreventivoSet1NewDetallemantenimientopreventivo = detallemantenimientopreventivoSet1NewDetallemantenimientopreventivo.getServicio1();
                    detallemantenimientopreventivoSet1NewDetallemantenimientopreventivo.setServicio1(servicio);
                    detallemantenimientopreventivoSet1NewDetallemantenimientopreventivo = em.merge(detallemantenimientopreventivoSet1NewDetallemantenimientopreventivo);
                    if (oldServicio1OfDetallemantenimientopreventivoSet1NewDetallemantenimientopreventivo != null && !oldServicio1OfDetallemantenimientopreventivoSet1NewDetallemantenimientopreventivo.equals(servicio)) {
                        oldServicio1OfDetallemantenimientopreventivoSet1NewDetallemantenimientopreventivo.getDetallemantenimientopreventivoSet1().remove(detallemantenimientopreventivoSet1NewDetallemantenimientopreventivo);
                        oldServicio1OfDetallemantenimientopreventivoSet1NewDetallemantenimientopreventivo = em.merge(oldServicio1OfDetallemantenimientopreventivoSet1NewDetallemantenimientopreventivo);
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
            Set<Detallemantenimientopreventivo> detallemantenimientopreventivoSet1 = servicio.getDetallemantenimientopreventivoSet1();
            for (Detallemantenimientopreventivo detallemantenimientopreventivoSet1Detallemantenimientopreventivo : detallemantenimientopreventivoSet1) {
                detallemantenimientopreventivoSet1Detallemantenimientopreventivo.setServicio1(null);
                detallemantenimientopreventivoSet1Detallemantenimientopreventivo = em.merge(detallemantenimientopreventivoSet1Detallemantenimientopreventivo);
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
