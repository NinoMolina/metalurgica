/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Piezareal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Codigodebarra;
import entity.Estadopiezareal;
import entity.Detalleejecucionplanificacion;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class PiezarealJpaController {

    public PiezarealJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Piezareal piezareal) throws PreexistingEntityException, Exception {
        if (piezareal.getDetalleejecucionplanificacionSet() == null) {
            piezareal.setDetalleejecucionplanificacionSet(new HashSet<Detalleejecucionplanificacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Codigodebarra idcodigobarra = piezareal.getIdcodigobarra();
            if (idcodigobarra != null) {
                idcodigobarra = em.getReference(idcodigobarra.getClass(), idcodigobarra.getIdcodigo());
                piezareal.setIdcodigobarra(idcodigobarra);
            }
            Codigodebarra idcodigobarra1 = piezareal.getIdcodigobarra1();
            if (idcodigobarra1 != null) {
                idcodigobarra1 = em.getReference(idcodigobarra1.getClass(), idcodigobarra1.getIdcodigo());
                piezareal.setIdcodigobarra1(idcodigobarra1);
            }
            Estadopiezareal estado = piezareal.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                piezareal.setEstado(estado);
            }
            Estadopiezareal estado1 = piezareal.getEstado1();
            if (estado1 != null) {
                estado1 = em.getReference(estado1.getClass(), estado1.getIdestado());
                piezareal.setEstado1(estado1);
            }
            Set<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionSet = new HashSet<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach : piezareal.getDetalleejecucionplanificacionSet()) {
                detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionSet.add(detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach);
            }
            piezareal.setDetalleejecucionplanificacionSet(attachedDetalleejecucionplanificacionSet);
            em.persist(piezareal);
            if (idcodigobarra != null) {
                idcodigobarra.getPiezarealSet().add(piezareal);
                idcodigobarra = em.merge(idcodigobarra);
            }
            if (idcodigobarra1 != null) {
                idcodigobarra1.getPiezarealSet().add(piezareal);
                idcodigobarra1 = em.merge(idcodigobarra1);
            }
            if (estado != null) {
                estado.getPiezarealSet().add(piezareal);
                estado = em.merge(estado);
            }
            if (estado1 != null) {
                estado1.getPiezarealSet().add(piezareal);
                estado1 = em.merge(estado1);
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetDetalleejecucionplanificacion : piezareal.getDetalleejecucionplanificacionSet()) {
                Piezareal oldPiezarealOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion = detalleejecucionplanificacionSetDetalleejecucionplanificacion.getPiezareal();
                detalleejecucionplanificacionSetDetalleejecucionplanificacion.setPiezareal(piezareal);
                detalleejecucionplanificacionSetDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionSetDetalleejecucionplanificacion);
                if (oldPiezarealOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion != null) {
                    oldPiezarealOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacionSetDetalleejecucionplanificacion);
                    oldPiezarealOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion = em.merge(oldPiezarealOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPiezareal(piezareal.getIdpiezareal()) != null) {
                throw new PreexistingEntityException("Piezareal " + piezareal + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Piezareal piezareal) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Piezareal persistentPiezareal = em.find(Piezareal.class, piezareal.getIdpiezareal());
            Codigodebarra idcodigobarraOld = persistentPiezareal.getIdcodigobarra();
            Codigodebarra idcodigobarraNew = piezareal.getIdcodigobarra();
            Codigodebarra idcodigobarra1Old = persistentPiezareal.getIdcodigobarra1();
            Codigodebarra idcodigobarra1New = piezareal.getIdcodigobarra1();
            Estadopiezareal estadoOld = persistentPiezareal.getEstado();
            Estadopiezareal estadoNew = piezareal.getEstado();
            Estadopiezareal estado1Old = persistentPiezareal.getEstado1();
            Estadopiezareal estado1New = piezareal.getEstado1();
            Set<Detalleejecucionplanificacion> detalleejecucionplanificacionSetOld = persistentPiezareal.getDetalleejecucionplanificacionSet();
            Set<Detalleejecucionplanificacion> detalleejecucionplanificacionSetNew = piezareal.getDetalleejecucionplanificacionSet();
            if (idcodigobarraNew != null) {
                idcodigobarraNew = em.getReference(idcodigobarraNew.getClass(), idcodigobarraNew.getIdcodigo());
                piezareal.setIdcodigobarra(idcodigobarraNew);
            }
            if (idcodigobarra1New != null) {
                idcodigobarra1New = em.getReference(idcodigobarra1New.getClass(), idcodigobarra1New.getIdcodigo());
                piezareal.setIdcodigobarra1(idcodigobarra1New);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                piezareal.setEstado(estadoNew);
            }
            if (estado1New != null) {
                estado1New = em.getReference(estado1New.getClass(), estado1New.getIdestado());
                piezareal.setEstado1(estado1New);
            }
            Set<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionSetNew = new HashSet<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach : detalleejecucionplanificacionSetNew) {
                detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionSetNew.add(detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach);
            }
            detalleejecucionplanificacionSetNew = attachedDetalleejecucionplanificacionSetNew;
            piezareal.setDetalleejecucionplanificacionSet(detalleejecucionplanificacionSetNew);
            piezareal = em.merge(piezareal);
            if (idcodigobarraOld != null && !idcodigobarraOld.equals(idcodigobarraNew)) {
                idcodigobarraOld.getPiezarealSet().remove(piezareal);
                idcodigobarraOld = em.merge(idcodigobarraOld);
            }
            if (idcodigobarraNew != null && !idcodigobarraNew.equals(idcodigobarraOld)) {
                idcodigobarraNew.getPiezarealSet().add(piezareal);
                idcodigobarraNew = em.merge(idcodigobarraNew);
            }
            if (idcodigobarra1Old != null && !idcodigobarra1Old.equals(idcodigobarra1New)) {
                idcodigobarra1Old.getPiezarealSet().remove(piezareal);
                idcodigobarra1Old = em.merge(idcodigobarra1Old);
            }
            if (idcodigobarra1New != null && !idcodigobarra1New.equals(idcodigobarra1Old)) {
                idcodigobarra1New.getPiezarealSet().add(piezareal);
                idcodigobarra1New = em.merge(idcodigobarra1New);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getPiezarealSet().remove(piezareal);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getPiezarealSet().add(piezareal);
                estadoNew = em.merge(estadoNew);
            }
            if (estado1Old != null && !estado1Old.equals(estado1New)) {
                estado1Old.getPiezarealSet().remove(piezareal);
                estado1Old = em.merge(estado1Old);
            }
            if (estado1New != null && !estado1New.equals(estado1Old)) {
                estado1New.getPiezarealSet().add(piezareal);
                estado1New = em.merge(estado1New);
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetOldDetalleejecucionplanificacion : detalleejecucionplanificacionSetOld) {
                if (!detalleejecucionplanificacionSetNew.contains(detalleejecucionplanificacionSetOldDetalleejecucionplanificacion)) {
                    detalleejecucionplanificacionSetOldDetalleejecucionplanificacion.setPiezareal(null);
                    detalleejecucionplanificacionSetOldDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionSetOldDetalleejecucionplanificacion);
                }
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetNewDetalleejecucionplanificacion : detalleejecucionplanificacionSetNew) {
                if (!detalleejecucionplanificacionSetOld.contains(detalleejecucionplanificacionSetNewDetalleejecucionplanificacion)) {
                    Piezareal oldPiezarealOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion = detalleejecucionplanificacionSetNewDetalleejecucionplanificacion.getPiezareal();
                    detalleejecucionplanificacionSetNewDetalleejecucionplanificacion.setPiezareal(piezareal);
                    detalleejecucionplanificacionSetNewDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionSetNewDetalleejecucionplanificacion);
                    if (oldPiezarealOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion != null && !oldPiezarealOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion.equals(piezareal)) {
                        oldPiezarealOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacionSetNewDetalleejecucionplanificacion);
                        oldPiezarealOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion = em.merge(oldPiezarealOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = piezareal.getIdpiezareal();
                if (findPiezareal(id) == null) {
                    throw new NonexistentEntityException("The piezareal with id " + id + " no longer exists.");
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
            Piezareal piezareal;
            try {
                piezareal = em.getReference(Piezareal.class, id);
                piezareal.getIdpiezareal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The piezareal with id " + id + " no longer exists.", enfe);
            }
            Codigodebarra idcodigobarra = piezareal.getIdcodigobarra();
            if (idcodigobarra != null) {
                idcodigobarra.getPiezarealSet().remove(piezareal);
                idcodigobarra = em.merge(idcodigobarra);
            }
            Codigodebarra idcodigobarra1 = piezareal.getIdcodigobarra1();
            if (idcodigobarra1 != null) {
                idcodigobarra1.getPiezarealSet().remove(piezareal);
                idcodigobarra1 = em.merge(idcodigobarra1);
            }
            Estadopiezareal estado = piezareal.getEstado();
            if (estado != null) {
                estado.getPiezarealSet().remove(piezareal);
                estado = em.merge(estado);
            }
            Estadopiezareal estado1 = piezareal.getEstado1();
            if (estado1 != null) {
                estado1.getPiezarealSet().remove(piezareal);
                estado1 = em.merge(estado1);
            }
            Set<Detalleejecucionplanificacion> detalleejecucionplanificacionSet = piezareal.getDetalleejecucionplanificacionSet();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetDetalleejecucionplanificacion : detalleejecucionplanificacionSet) {
                detalleejecucionplanificacionSetDetalleejecucionplanificacion.setPiezareal(null);
                detalleejecucionplanificacionSetDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionSetDetalleejecucionplanificacion);
            }
            em.remove(piezareal);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Piezareal> findPiezarealEntities() {
        return findPiezarealEntities(true, -1, -1);
    }

    public List<Piezareal> findPiezarealEntities(int maxResults, int firstResult) {
        return findPiezarealEntities(false, maxResults, firstResult);
    }

    private List<Piezareal> findPiezarealEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Piezareal.class));
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

    public Piezareal findPiezareal(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Piezareal.class, id);
        } finally {
            em.close();
        }
    }

    public int getPiezarealCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Piezareal> rt = cq.from(Piezareal.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
