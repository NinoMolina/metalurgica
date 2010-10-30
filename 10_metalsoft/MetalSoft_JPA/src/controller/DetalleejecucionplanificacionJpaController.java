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
import entity.Pieza;
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
        if (detalleejecucionplanificacion.getDetalleplanificacionproduccionSet1() == null) {
            detalleejecucionplanificacion.setDetalleplanificacionproduccionSet1(new HashSet<Detalleplanificacionproduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionetapaproduccion ejecucionetapa = detalleejecucionplanificacion.getEjecucionetapa();
            if (ejecucionetapa != null) {
                ejecucionetapa = em.getReference(ejecucionetapa.getClass(), ejecucionetapa.getId());
                detalleejecucionplanificacion.setEjecucionetapa(ejecucionetapa);
            }
            Ejecucionplanificacionproduccion idejecucionplanificacionproduccion = detalleejecucionplanificacion.getIdejecucionplanificacionproduccion();
            if (idejecucionplanificacionproduccion != null) {
                idejecucionplanificacionproduccion = em.getReference(idejecucionplanificacionproduccion.getClass(), idejecucionplanificacionproduccion.getIdejecucion());
                detalleejecucionplanificacion.setIdejecucionplanificacionproduccion(idejecucionplanificacionproduccion);
            }
            Pieza pieza = detalleejecucionplanificacion.getPieza();
            if (pieza != null) {
                pieza = em.getReference(pieza.getClass(), pieza.getIdpieza());
                detalleejecucionplanificacion.setPieza(pieza);
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
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet1 = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach : detalleejecucionplanificacion.getDetalleplanificacionproduccionSet1()) {
                detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet1.add(detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach);
            }
            detalleejecucionplanificacion.setDetalleplanificacionproduccionSet1(attachedDetalleplanificacionproduccionSet1);
            em.persist(detalleejecucionplanificacion);
            if (ejecucionetapa != null) {
                ejecucionetapa.getDetalleejecucionplanificacionSet().add(detalleejecucionplanificacion);
                ejecucionetapa = em.merge(ejecucionetapa);
            }
            if (idejecucionplanificacionproduccion != null) {
                idejecucionplanificacionproduccion.getDetalleejecucionplanificacionSet().add(detalleejecucionplanificacion);
                idejecucionplanificacionproduccion = em.merge(idejecucionplanificacionproduccion);
            }
            if (pieza != null) {
                pieza.getDetalleejecucionplanificacionSet().add(detalleejecucionplanificacion);
                pieza = em.merge(pieza);
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
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1Detalleplanificacionproduccion : detalleejecucionplanificacion.getDetalleplanificacionproduccionSet1()) {
                Detalleejecucionplanificacion oldIddetalleejecucionplanificacion1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion = detalleplanificacionproduccionSet1Detalleplanificacionproduccion.getIddetalleejecucionplanificacion1();
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion.setIddetalleejecucionplanificacion1(detalleejecucionplanificacion);
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1Detalleplanificacionproduccion);
                if (oldIddetalleejecucionplanificacion1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion != null) {
                    oldIddetalleejecucionplanificacion1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion.getDetalleplanificacionproduccionSet1().remove(detalleplanificacionproduccionSet1Detalleplanificacionproduccion);
                    oldIddetalleejecucionplanificacion1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion = em.merge(oldIddetalleejecucionplanificacion1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion);
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
            Ejecucionetapaproduccion ejecucionetapaOld = persistentDetalleejecucionplanificacion.getEjecucionetapa();
            Ejecucionetapaproduccion ejecucionetapaNew = detalleejecucionplanificacion.getEjecucionetapa();
            Ejecucionplanificacionproduccion idejecucionplanificacionproduccionOld = persistentDetalleejecucionplanificacion.getIdejecucionplanificacionproduccion();
            Ejecucionplanificacionproduccion idejecucionplanificacionproduccionNew = detalleejecucionplanificacion.getIdejecucionplanificacionproduccion();
            Pieza piezaOld = persistentDetalleejecucionplanificacion.getPieza();
            Pieza piezaNew = detalleejecucionplanificacion.getPieza();
            Piezareal piezarealOld = persistentDetalleejecucionplanificacion.getPiezareal();
            Piezareal piezarealNew = detalleejecucionplanificacion.getPiezareal();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetOld = persistentDetalleejecucionplanificacion.getDetalleplanificacionproduccionSet();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetNew = detalleejecucionplanificacion.getDetalleplanificacionproduccionSet();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1Old = persistentDetalleejecucionplanificacion.getDetalleplanificacionproduccionSet1();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1New = detalleejecucionplanificacion.getDetalleplanificacionproduccionSet1();
            if (ejecucionetapaNew != null) {
                ejecucionetapaNew = em.getReference(ejecucionetapaNew.getClass(), ejecucionetapaNew.getId());
                detalleejecucionplanificacion.setEjecucionetapa(ejecucionetapaNew);
            }
            if (idejecucionplanificacionproduccionNew != null) {
                idejecucionplanificacionproduccionNew = em.getReference(idejecucionplanificacionproduccionNew.getClass(), idejecucionplanificacionproduccionNew.getIdejecucion());
                detalleejecucionplanificacion.setIdejecucionplanificacionproduccion(idejecucionplanificacionproduccionNew);
            }
            if (piezaNew != null) {
                piezaNew = em.getReference(piezaNew.getClass(), piezaNew.getIdpieza());
                detalleejecucionplanificacion.setPieza(piezaNew);
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
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet1New = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionSet1New) {
                detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet1New.add(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionSet1New = attachedDetalleplanificacionproduccionSet1New;
            detalleejecucionplanificacion.setDetalleplanificacionproduccionSet1(detalleplanificacionproduccionSet1New);
            detalleejecucionplanificacion = em.merge(detalleejecucionplanificacion);
            if (ejecucionetapaOld != null && !ejecucionetapaOld.equals(ejecucionetapaNew)) {
                ejecucionetapaOld.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacion);
                ejecucionetapaOld = em.merge(ejecucionetapaOld);
            }
            if (ejecucionetapaNew != null && !ejecucionetapaNew.equals(ejecucionetapaOld)) {
                ejecucionetapaNew.getDetalleejecucionplanificacionSet().add(detalleejecucionplanificacion);
                ejecucionetapaNew = em.merge(ejecucionetapaNew);
            }
            if (idejecucionplanificacionproduccionOld != null && !idejecucionplanificacionproduccionOld.equals(idejecucionplanificacionproduccionNew)) {
                idejecucionplanificacionproduccionOld.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacion);
                idejecucionplanificacionproduccionOld = em.merge(idejecucionplanificacionproduccionOld);
            }
            if (idejecucionplanificacionproduccionNew != null && !idejecucionplanificacionproduccionNew.equals(idejecucionplanificacionproduccionOld)) {
                idejecucionplanificacionproduccionNew.getDetalleejecucionplanificacionSet().add(detalleejecucionplanificacion);
                idejecucionplanificacionproduccionNew = em.merge(idejecucionplanificacionproduccionNew);
            }
            if (piezaOld != null && !piezaOld.equals(piezaNew)) {
                piezaOld.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacion);
                piezaOld = em.merge(piezaOld);
            }
            if (piezaNew != null && !piezaNew.equals(piezaOld)) {
                piezaNew.getDetalleejecucionplanificacionSet().add(detalleejecucionplanificacion);
                piezaNew = em.merge(piezaNew);
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
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion : detalleplanificacionproduccionSet1Old) {
                if (!detalleplanificacionproduccionSet1New.contains(detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion)) {
                    detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion.setIddetalleejecucionplanificacion1(null);
                    detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion : detalleplanificacionproduccionSet1New) {
                if (!detalleplanificacionproduccionSet1Old.contains(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion)) {
                    Detalleejecucionplanificacion oldIddetalleejecucionplanificacion1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion = detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.getIddetalleejecucionplanificacion1();
                    detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.setIddetalleejecucionplanificacion1(detalleejecucionplanificacion);
                    detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion);
                    if (oldIddetalleejecucionplanificacion1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion != null && !oldIddetalleejecucionplanificacion1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.equals(detalleejecucionplanificacion)) {
                        oldIddetalleejecucionplanificacion1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet1().remove(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion);
                        oldIddetalleejecucionplanificacion1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion = em.merge(oldIddetalleejecucionplanificacion1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion);
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
            Ejecucionetapaproduccion ejecucionetapa = detalleejecucionplanificacion.getEjecucionetapa();
            if (ejecucionetapa != null) {
                ejecucionetapa.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacion);
                ejecucionetapa = em.merge(ejecucionetapa);
            }
            Ejecucionplanificacionproduccion idejecucionplanificacionproduccion = detalleejecucionplanificacion.getIdejecucionplanificacionproduccion();
            if (idejecucionplanificacionproduccion != null) {
                idejecucionplanificacionproduccion.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacion);
                idejecucionplanificacionproduccion = em.merge(idejecucionplanificacionproduccion);
            }
            Pieza pieza = detalleejecucionplanificacion.getPieza();
            if (pieza != null) {
                pieza.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacion);
                pieza = em.merge(pieza);
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
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1 = detalleejecucionplanificacion.getDetalleplanificacionproduccionSet1();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1Detalleplanificacionproduccion : detalleplanificacionproduccionSet1) {
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion.setIddetalleejecucionplanificacion1(null);
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1Detalleplanificacionproduccion);
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
