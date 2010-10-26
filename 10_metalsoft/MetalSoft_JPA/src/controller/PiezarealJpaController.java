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
            Estadopiezareal estado = piezareal.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                piezareal.setEstado(estado);
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
            if (estado != null) {
                estado.getPiezarealSet().add(piezareal);
                estado = em.merge(estado);
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
            Estadopiezareal estadoOld = persistentPiezareal.getEstado();
            Estadopiezareal estadoNew = piezareal.getEstado();
            Set<Detalleejecucionplanificacion> detalleejecucionplanificacionSetOld = persistentPiezareal.getDetalleejecucionplanificacionSet();
            Set<Detalleejecucionplanificacion> detalleejecucionplanificacionSetNew = piezareal.getDetalleejecucionplanificacionSet();
            if (idcodigobarraNew != null) {
                idcodigobarraNew = em.getReference(idcodigobarraNew.getClass(), idcodigobarraNew.getIdcodigo());
                piezareal.setIdcodigobarra(idcodigobarraNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                piezareal.setEstado(estadoNew);
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
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getPiezarealSet().remove(piezareal);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getPiezarealSet().add(piezareal);
                estadoNew = em.merge(estadoNew);
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
            Estadopiezareal estado = piezareal.getEstado();
            if (estado != null) {
                estado.getPiezarealSet().remove(piezareal);
                estado = em.merge(estado);
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
