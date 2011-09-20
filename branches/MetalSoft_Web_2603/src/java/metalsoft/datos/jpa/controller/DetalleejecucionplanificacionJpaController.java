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
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;
import metalsoft.datos.jpa.entity.Piezareal;
import metalsoft.datos.jpa.entity.Pieza;
import metalsoft.datos.jpa.entity.Etapadeproduccion;
import metalsoft.datos.jpa.entity.Ejecucionplanificacionproduccion;
import metalsoft.datos.jpa.entity.Ejecucionetapaproduccion;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class DetalleejecucionplanificacionJpaController implements Serializable {

    public DetalleejecucionplanificacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleejecucionplanificacion detalleejecucionplanificacion) throws PreexistingEntityException, Exception {
        if (detalleejecucionplanificacion.getDetalleplanificacionproduccionList() == null) {
            detalleejecucionplanificacion.setDetalleplanificacionproduccionList(new ArrayList<Detalleplanificacionproduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Piezareal piezareal = detalleejecucionplanificacion.getPiezareal();
            if (piezareal != null) {
                piezareal = em.getReference(piezareal.getClass(), piezareal.getIdpiezareal());
                detalleejecucionplanificacion.setPiezareal(piezareal);
            }
            Pieza pieza = detalleejecucionplanificacion.getPieza();
            if (pieza != null) {
                pieza = em.getReference(pieza.getClass(), pieza.getIdpieza());
                detalleejecucionplanificacion.setPieza(pieza);
            }
            Etapadeproduccion idetapaproduccion = detalleejecucionplanificacion.getIdetapaproduccion();
            if (idetapaproduccion != null) {
                idetapaproduccion = em.getReference(idetapaproduccion.getClass(), idetapaproduccion.getIdetapaproduccion());
                detalleejecucionplanificacion.setIdetapaproduccion(idetapaproduccion);
            }
            Ejecucionplanificacionproduccion idejecucionplanificacionproduccion = detalleejecucionplanificacion.getIdejecucionplanificacionproduccion();
            if (idejecucionplanificacionproduccion != null) {
                idejecucionplanificacionproduccion = em.getReference(idejecucionplanificacionproduccion.getClass(), idejecucionplanificacionproduccion.getIdejecucion());
                detalleejecucionplanificacion.setIdejecucionplanificacionproduccion(idejecucionplanificacionproduccion);
            }
            Ejecucionetapaproduccion ejecucionetapa = detalleejecucionplanificacion.getEjecucionetapa();
            if (ejecucionetapa != null) {
                ejecucionetapa = em.getReference(ejecucionetapa.getClass(), ejecucionetapa.getId());
                detalleejecucionplanificacion.setEjecucionetapa(ejecucionetapa);
            }
            List<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionList = new ArrayList<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach : detalleejecucionplanificacion.getDetalleplanificacionproduccionList()) {
                detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionList.add(detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach);
            }
            detalleejecucionplanificacion.setDetalleplanificacionproduccionList(attachedDetalleplanificacionproduccionList);
            em.persist(detalleejecucionplanificacion);
            if (piezareal != null) {
                piezareal.getDetalleejecucionplanificacionList().add(detalleejecucionplanificacion);
                piezareal = em.merge(piezareal);
            }
            if (pieza != null) {
                pieza.getDetalleejecucionplanificacionList().add(detalleejecucionplanificacion);
                pieza = em.merge(pieza);
            }
            if (idetapaproduccion != null) {
                idetapaproduccion.getDetalleejecucionplanificacionList().add(detalleejecucionplanificacion);
                idetapaproduccion = em.merge(idetapaproduccion);
            }
            if (idejecucionplanificacionproduccion != null) {
                idejecucionplanificacionproduccion.getDetalleejecucionplanificacionList().add(detalleejecucionplanificacion);
                idejecucionplanificacionproduccion = em.merge(idejecucionplanificacionproduccion);
            }
            if (ejecucionetapa != null) {
                ejecucionetapa.getDetalleejecucionplanificacionList().add(detalleejecucionplanificacion);
                ejecucionetapa = em.merge(ejecucionetapa);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccion : detalleejecucionplanificacion.getDetalleplanificacionproduccionList()) {
                Detalleejecucionplanificacion oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionListDetalleplanificacionproduccion = detalleplanificacionproduccionListDetalleplanificacionproduccion.getIddetalleejecucionplanificacion();
                detalleplanificacionproduccionListDetalleplanificacionproduccion.setIddetalleejecucionplanificacion(detalleejecucionplanificacion);
                detalleplanificacionproduccionListDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListDetalleplanificacionproduccion);
                if (oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionListDetalleplanificacionproduccion != null) {
                    oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionListDetalleplanificacionproduccion.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccionListDetalleplanificacionproduccion);
                    oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionListDetalleplanificacionproduccion = em.merge(oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionListDetalleplanificacionproduccion);
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
            Piezareal piezarealOld = persistentDetalleejecucionplanificacion.getPiezareal();
            Piezareal piezarealNew = detalleejecucionplanificacion.getPiezareal();
            Pieza piezaOld = persistentDetalleejecucionplanificacion.getPieza();
            Pieza piezaNew = detalleejecucionplanificacion.getPieza();
            Etapadeproduccion idetapaproduccionOld = persistentDetalleejecucionplanificacion.getIdetapaproduccion();
            Etapadeproduccion idetapaproduccionNew = detalleejecucionplanificacion.getIdetapaproduccion();
            Ejecucionplanificacionproduccion idejecucionplanificacionproduccionOld = persistentDetalleejecucionplanificacion.getIdejecucionplanificacionproduccion();
            Ejecucionplanificacionproduccion idejecucionplanificacionproduccionNew = detalleejecucionplanificacion.getIdejecucionplanificacionproduccion();
            Ejecucionetapaproduccion ejecucionetapaOld = persistentDetalleejecucionplanificacion.getEjecucionetapa();
            Ejecucionetapaproduccion ejecucionetapaNew = detalleejecucionplanificacion.getEjecucionetapa();
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionListOld = persistentDetalleejecucionplanificacion.getDetalleplanificacionproduccionList();
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionListNew = detalleejecucionplanificacion.getDetalleplanificacionproduccionList();
            if (piezarealNew != null) {
                piezarealNew = em.getReference(piezarealNew.getClass(), piezarealNew.getIdpiezareal());
                detalleejecucionplanificacion.setPiezareal(piezarealNew);
            }
            if (piezaNew != null) {
                piezaNew = em.getReference(piezaNew.getClass(), piezaNew.getIdpieza());
                detalleejecucionplanificacion.setPieza(piezaNew);
            }
            if (idetapaproduccionNew != null) {
                idetapaproduccionNew = em.getReference(idetapaproduccionNew.getClass(), idetapaproduccionNew.getIdetapaproduccion());
                detalleejecucionplanificacion.setIdetapaproduccion(idetapaproduccionNew);
            }
            if (idejecucionplanificacionproduccionNew != null) {
                idejecucionplanificacionproduccionNew = em.getReference(idejecucionplanificacionproduccionNew.getClass(), idejecucionplanificacionproduccionNew.getIdejecucion());
                detalleejecucionplanificacion.setIdejecucionplanificacionproduccion(idejecucionplanificacionproduccionNew);
            }
            if (ejecucionetapaNew != null) {
                ejecucionetapaNew = em.getReference(ejecucionetapaNew.getClass(), ejecucionetapaNew.getId());
                detalleejecucionplanificacion.setEjecucionetapa(ejecucionetapaNew);
            }
            List<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionListNew = new ArrayList<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionListNew) {
                detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionListNew.add(detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionListNew = attachedDetalleplanificacionproduccionListNew;
            detalleejecucionplanificacion.setDetalleplanificacionproduccionList(detalleplanificacionproduccionListNew);
            detalleejecucionplanificacion = em.merge(detalleejecucionplanificacion);
            if (piezarealOld != null && !piezarealOld.equals(piezarealNew)) {
                piezarealOld.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacion);
                piezarealOld = em.merge(piezarealOld);
            }
            if (piezarealNew != null && !piezarealNew.equals(piezarealOld)) {
                piezarealNew.getDetalleejecucionplanificacionList().add(detalleejecucionplanificacion);
                piezarealNew = em.merge(piezarealNew);
            }
            if (piezaOld != null && !piezaOld.equals(piezaNew)) {
                piezaOld.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacion);
                piezaOld = em.merge(piezaOld);
            }
            if (piezaNew != null && !piezaNew.equals(piezaOld)) {
                piezaNew.getDetalleejecucionplanificacionList().add(detalleejecucionplanificacion);
                piezaNew = em.merge(piezaNew);
            }
            if (idetapaproduccionOld != null && !idetapaproduccionOld.equals(idetapaproduccionNew)) {
                idetapaproduccionOld.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacion);
                idetapaproduccionOld = em.merge(idetapaproduccionOld);
            }
            if (idetapaproduccionNew != null && !idetapaproduccionNew.equals(idetapaproduccionOld)) {
                idetapaproduccionNew.getDetalleejecucionplanificacionList().add(detalleejecucionplanificacion);
                idetapaproduccionNew = em.merge(idetapaproduccionNew);
            }
            if (idejecucionplanificacionproduccionOld != null && !idejecucionplanificacionproduccionOld.equals(idejecucionplanificacionproduccionNew)) {
                idejecucionplanificacionproduccionOld.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacion);
                idejecucionplanificacionproduccionOld = em.merge(idejecucionplanificacionproduccionOld);
            }
            if (idejecucionplanificacionproduccionNew != null && !idejecucionplanificacionproduccionNew.equals(idejecucionplanificacionproduccionOld)) {
                idejecucionplanificacionproduccionNew.getDetalleejecucionplanificacionList().add(detalleejecucionplanificacion);
                idejecucionplanificacionproduccionNew = em.merge(idejecucionplanificacionproduccionNew);
            }
            if (ejecucionetapaOld != null && !ejecucionetapaOld.equals(ejecucionetapaNew)) {
                ejecucionetapaOld.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacion);
                ejecucionetapaOld = em.merge(ejecucionetapaOld);
            }
            if (ejecucionetapaNew != null && !ejecucionetapaNew.equals(ejecucionetapaOld)) {
                ejecucionetapaNew.getDetalleejecucionplanificacionList().add(detalleejecucionplanificacion);
                ejecucionetapaNew = em.merge(ejecucionetapaNew);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListOldDetalleplanificacionproduccion : detalleplanificacionproduccionListOld) {
                if (!detalleplanificacionproduccionListNew.contains(detalleplanificacionproduccionListOldDetalleplanificacionproduccion)) {
                    detalleplanificacionproduccionListOldDetalleplanificacionproduccion.setIddetalleejecucionplanificacion(null);
                    detalleplanificacionproduccionListOldDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListOldDetalleplanificacionproduccion);
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListNewDetalleplanificacionproduccion : detalleplanificacionproduccionListNew) {
                if (!detalleplanificacionproduccionListOld.contains(detalleplanificacionproduccionListNewDetalleplanificacionproduccion)) {
                    Detalleejecucionplanificacion oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion = detalleplanificacionproduccionListNewDetalleplanificacionproduccion.getIddetalleejecucionplanificacion();
                    detalleplanificacionproduccionListNewDetalleplanificacionproduccion.setIddetalleejecucionplanificacion(detalleejecucionplanificacion);
                    detalleplanificacionproduccionListNewDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListNewDetalleplanificacionproduccion);
                    if (oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion != null && !oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion.equals(detalleejecucionplanificacion)) {
                        oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccionListNewDetalleplanificacionproduccion);
                        oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion = em.merge(oldIddetalleejecucionplanificacionOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion);
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
            Piezareal piezareal = detalleejecucionplanificacion.getPiezareal();
            if (piezareal != null) {
                piezareal.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacion);
                piezareal = em.merge(piezareal);
            }
            Pieza pieza = detalleejecucionplanificacion.getPieza();
            if (pieza != null) {
                pieza.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacion);
                pieza = em.merge(pieza);
            }
            Etapadeproduccion idetapaproduccion = detalleejecucionplanificacion.getIdetapaproduccion();
            if (idetapaproduccion != null) {
                idetapaproduccion.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacion);
                idetapaproduccion = em.merge(idetapaproduccion);
            }
            Ejecucionplanificacionproduccion idejecucionplanificacionproduccion = detalleejecucionplanificacion.getIdejecucionplanificacionproduccion();
            if (idejecucionplanificacionproduccion != null) {
                idejecucionplanificacionproduccion.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacion);
                idejecucionplanificacionproduccion = em.merge(idejecucionplanificacionproduccion);
            }
            Ejecucionetapaproduccion ejecucionetapa = detalleejecucionplanificacion.getEjecucionetapa();
            if (ejecucionetapa != null) {
                ejecucionetapa.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacion);
                ejecucionetapa = em.merge(ejecucionetapa);
            }
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionList = detalleejecucionplanificacion.getDetalleplanificacionproduccionList();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccion : detalleplanificacionproduccionList) {
                detalleplanificacionproduccionListDetalleplanificacionproduccion.setIddetalleejecucionplanificacion(null);
                detalleplanificacionproduccionListDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListDetalleplanificacionproduccion);
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
