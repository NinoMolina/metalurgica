/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Detalleejecucionplanificacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Ejecucionetapaproduccion;
import entity.Ejecucionplanificacionproduccion;
import entity.Piezareal;
import entity.Detalleplanificacionproduccion;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Nino
 */
public class DetalleejecucionplanificacionJpaController {

    public DetalleejecucionplanificacionJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleejecucionplanificacion detalleejecucionplanificacion) throws PreexistingEntityException, Exception {
        if (detalleejecucionplanificacion.getDetalleplanificacionproduccionSet() == null) {
            detalleejecucionplanificacion.setDetalleplanificacionproduccionSet(new HashSet<Detalleplanificacionproduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionetapaproduccion ejecucionetapaproduccion = detalleejecucionplanificacion.getEjecucionetapaproduccion();
            if (ejecucionetapaproduccion != null) {
                ejecucionetapaproduccion = em.getReference(ejecucionetapaproduccion.getClass(), ejecucionetapaproduccion.getId());
                detalleejecucionplanificacion.setEjecucionetapaproduccion(ejecucionetapaproduccion);
            }
            Ejecucionplanificacionproduccion idejecucionplanificacionproduccion = detalleejecucionplanificacion.getIdejecucionplanificacionproduccion();
            if (idejecucionplanificacionproduccion != null) {
                idejecucionplanificacionproduccion = em.getReference(idejecucionplanificacionproduccion.getClass(), idejecucionplanificacionproduccion.getIdejecucion());
                detalleejecucionplanificacion.setIdejecucionplanificacionproduccion(idejecucionplanificacionproduccion);
            }
            Piezareal piezareal = detalleejecucionplanificacion.getPiezareal();
            if (piezareal != null) {
                piezareal = em.getReference(piezareal.getClass(), piezareal.getIdpiezareal());
                detalleejecucionplanificacion.setPiezareal(piezareal);
            }
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach : detalleejecucionplanificacion.getDetalleplanificacionproduccionSet()) {
                detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet.add(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach);
            }
            detalleejecucionplanificacion.setDetalleplanificacionproduccionSet(attachedDetalleplanificacionproduccionSet);
            em.persist(detalleejecucionplanificacion);
            if (ejecucionetapaproduccion != null) {
                ejecucionetapaproduccion.getDetalleejecucionplanificacionSet().add(detalleejecucionplanificacion);
                ejecucionetapaproduccion = em.merge(ejecucionetapaproduccion);
            }
            if (idejecucionplanificacionproduccion != null) {
                idejecucionplanificacionproduccion.getDetalleejecucionplanificacionSet().add(detalleejecucionplanificacion);
                idejecucionplanificacionproduccion = em.merge(idejecucionplanificacionproduccion);
            }
            if (piezareal != null) {
                piezareal.getDetalleejecucionplanificacionSet().add(detalleejecucionplanificacion);
                piezareal = em.merge(piezareal);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccion : detalleejecucionplanificacion.getDetalleplanificacionproduccionSet()) {
                Detalleejecucionplanificacion oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion = detalleplanificacionproduccionSetDetalleplanificacionproduccion.getIddetalleejecucionplanificacion();
                detalleplanificacionproduccionSetDetalleplanificacionproduccion.setIddetalleejecucionplanificacion(detalleejecucionplanificacion);
                detalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
                if (oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion != null) {
                    oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
                    oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleejecucionplanificacion(detalleejecucionplanificacion.getId()) != null) {
                throw new PreexistingEntityException("Detalleejecucionplanificacion " + detalleejecucionplanificacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleejecucionplanificacion detalleejecucionplanificacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleejecucionplanificacion persistentDetalleejecucionplanificacion = em.find(Detalleejecucionplanificacion.class, detalleejecucionplanificacion.getId());
            Ejecucionetapaproduccion ejecucionetapaproduccionOld = persistentDetalleejecucionplanificacion.getEjecucionetapaproduccion();
            Ejecucionetapaproduccion ejecucionetapaproduccionNew = detalleejecucionplanificacion.getEjecucionetapaproduccion();
            Ejecucionplanificacionproduccion idejecucionplanificacionproduccionOld = persistentDetalleejecucionplanificacion.getIdejecucionplanificacionproduccion();
            Ejecucionplanificacionproduccion idejecucionplanificacionproduccionNew = detalleejecucionplanificacion.getIdejecucionplanificacionproduccion();
            Piezareal piezarealOld = persistentDetalleejecucionplanificacion.getPiezareal();
            Piezareal piezarealNew = detalleejecucionplanificacion.getPiezareal();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetOld = persistentDetalleejecucionplanificacion.getDetalleplanificacionproduccionSet();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetNew = detalleejecucionplanificacion.getDetalleplanificacionproduccionSet();
            if (ejecucionetapaproduccionNew != null) {
                ejecucionetapaproduccionNew = em.getReference(ejecucionetapaproduccionNew.getClass(), ejecucionetapaproduccionNew.getId());
                detalleejecucionplanificacion.setEjecucionetapaproduccion(ejecucionetapaproduccionNew);
            }
            if (idejecucionplanificacionproduccionNew != null) {
                idejecucionplanificacionproduccionNew = em.getReference(idejecucionplanificacionproduccionNew.getClass(), idejecucionplanificacionproduccionNew.getIdejecucion());
                detalleejecucionplanificacion.setIdejecucionplanificacionproduccion(idejecucionplanificacionproduccionNew);
            }
            if (piezarealNew != null) {
                piezarealNew = em.getReference(piezarealNew.getClass(), piezarealNew.getIdpiezareal());
                detalleejecucionplanificacion.setPiezareal(piezarealNew);
            }
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSetNew = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionSetNew) {
                detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSetNew.add(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionSetNew = attachedDetalleplanificacionproduccionSetNew;
            detalleejecucionplanificacion.setDetalleplanificacionproduccionSet(detalleplanificacionproduccionSetNew);
            detalleejecucionplanificacion = em.merge(detalleejecucionplanificacion);
            if (ejecucionetapaproduccionOld != null && !ejecucionetapaproduccionOld.equals(ejecucionetapaproduccionNew)) {
                ejecucionetapaproduccionOld.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacion);
                ejecucionetapaproduccionOld = em.merge(ejecucionetapaproduccionOld);
            }
            if (ejecucionetapaproduccionNew != null && !ejecucionetapaproduccionNew.equals(ejecucionetapaproduccionOld)) {
                ejecucionetapaproduccionNew.getDetalleejecucionplanificacionSet().add(detalleejecucionplanificacion);
                ejecucionetapaproduccionNew = em.merge(ejecucionetapaproduccionNew);
            }
            if (idejecucionplanificacionproduccionOld != null && !idejecucionplanificacionproduccionOld.equals(idejecucionplanificacionproduccionNew)) {
                idejecucionplanificacionproduccionOld.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacion);
                idejecucionplanificacionproduccionOld = em.merge(idejecucionplanificacionproduccionOld);
            }
            if (idejecucionplanificacionproduccionNew != null && !idejecucionplanificacionproduccionNew.equals(idejecucionplanificacionproduccionOld)) {
                idejecucionplanificacionproduccionNew.getDetalleejecucionplanificacionSet().add(detalleejecucionplanificacion);
                idejecucionplanificacionproduccionNew = em.merge(idejecucionplanificacionproduccionNew);
            }
            if (piezarealOld != null && !piezarealOld.equals(piezarealNew)) {
                piezarealOld.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacion);
                piezarealOld = em.merge(piezarealOld);
            }
            if (piezarealNew != null && !piezarealNew.equals(piezarealOld)) {
                piezarealNew.getDetalleejecucionplanificacionSet().add(detalleejecucionplanificacion);
                piezarealNew = em.merge(piezarealNew);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetOldDetalleplanificacionproduccion : detalleplanificacionproduccionSetOld) {
                if (!detalleplanificacionproduccionSetNew.contains(detalleplanificacionproduccionSetOldDetalleplanificacionproduccion)) {
                    detalleplanificacionproduccionSetOldDetalleplanificacionproduccion.setIddetalleejecucionplanificacion(null);
                    detalleplanificacionproduccionSetOldDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetOldDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetNewDetalleplanificacionproduccion : detalleplanificacionproduccionSetNew) {
                if (!detalleplanificacionproduccionSetOld.contains(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion)) {
                    Detalleejecucionplanificacion oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion = detalleplanificacionproduccionSetNewDetalleplanificacionproduccion.getIddetalleejecucionplanificacion();
                    detalleplanificacionproduccionSetNewDetalleplanificacionproduccion.setIddetalleejecucionplanificacion(detalleejecucionplanificacion);
                    detalleplanificacionproduccionSetNewDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                    if (oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion != null && !oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion.equals(detalleejecucionplanificacion)) {
                        oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                        oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion = em.merge(oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detalleejecucionplanificacion.getId();
                if (findDetalleejecucionplanificacion(id) == null) {
                    throw new NonexistentEntityException("The detalleejecucionplanificacion with id " + id + " no longer exists.");
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
            Detalleejecucionplanificacion detalleejecucionplanificacion;
            try {
                detalleejecucionplanificacion = em.getReference(Detalleejecucionplanificacion.class, id);
                detalleejecucionplanificacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleejecucionplanificacion with id " + id + " no longer exists.", enfe);
            }
            Ejecucionetapaproduccion ejecucionetapaproduccion = detalleejecucionplanificacion.getEjecucionetapaproduccion();
            if (ejecucionetapaproduccion != null) {
                ejecucionetapaproduccion.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacion);
                ejecucionetapaproduccion = em.merge(ejecucionetapaproduccion);
            }
            Ejecucionplanificacionproduccion idejecucionplanificacionproduccion = detalleejecucionplanificacion.getIdejecucionplanificacionproduccion();
            if (idejecucionplanificacionproduccion != null) {
                idejecucionplanificacionproduccion.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacion);
                idejecucionplanificacionproduccion = em.merge(idejecucionplanificacionproduccion);
            }
            Piezareal piezareal = detalleejecucionplanificacion.getPiezareal();
            if (piezareal != null) {
                piezareal.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacion);
                piezareal = em.merge(piezareal);
            }
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet = detalleejecucionplanificacion.getDetalleplanificacionproduccionSet();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccion : detalleplanificacionproduccionSet) {
                detalleplanificacionproduccionSetDetalleplanificacionproduccion.setIddetalleejecucionplanificacion(null);
                detalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
            }
            em.remove(detalleejecucionplanificacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleejecucionplanificacion> findDetalleejecucionplanificacionEntities() {
        return findDetalleejecucionplanificacionEntities(true, -1, -1);
    }

    public List<Detalleejecucionplanificacion> findDetalleejecucionplanificacionEntities(int maxResults, int firstResult) {
        return findDetalleejecucionplanificacionEntities(false, maxResults, firstResult);
    }

    private List<Detalleejecucionplanificacion> findDetalleejecucionplanificacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleejecucionplanificacion.class));
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

    public Detalleejecucionplanificacion findDetalleejecucionplanificacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleejecucionplanificacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleejecucionplanificacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleejecucionplanificacion> rt = cq.from(Detalleejecucionplanificacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
