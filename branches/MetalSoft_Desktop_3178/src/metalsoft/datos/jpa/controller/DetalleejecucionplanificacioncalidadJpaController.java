/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.datos.jpa.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.Procesocalidad;
import metalsoft.datos.jpa.entity.Piezareal;
import metalsoft.datos.jpa.entity.Pieza;
import metalsoft.datos.jpa.entity.Ejecucionplanificacioncalidad;

/**
 *
 * @author Nino
 */
public class DetalleejecucionplanificacioncalidadJpaController implements Serializable {

    public DetalleejecucionplanificacioncalidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Procesocalidad idprocesocalidad = detalleejecucionplanificacioncalidad.getIdprocesocalidad();
            if (idprocesocalidad != null) {
                idprocesocalidad = em.getReference(idprocesocalidad.getClass(), idprocesocalidad.getIdprocesocalidad());
                detalleejecucionplanificacioncalidad.setIdprocesocalidad(idprocesocalidad);
            }
            Piezareal piezareal = detalleejecucionplanificacioncalidad.getPiezareal();
            if (piezareal != null) {
                piezareal = em.getReference(piezareal.getClass(), piezareal.getIdpiezareal());
                detalleejecucionplanificacioncalidad.setPiezareal(piezareal);
            }
            Pieza pieza = detalleejecucionplanificacioncalidad.getPieza();
            if (pieza != null) {
                pieza = em.getReference(pieza.getClass(), pieza.getIdpieza());
                detalleejecucionplanificacioncalidad.setPieza(pieza);
            }
            Ejecucionplanificacioncalidad idejecucionplanificacioncalidad = detalleejecucionplanificacioncalidad.getIdejecucionplanificacioncalidad();
            if (idejecucionplanificacioncalidad != null) {
                idejecucionplanificacioncalidad = em.getReference(idejecucionplanificacioncalidad.getClass(), idejecucionplanificacioncalidad.getIdejecucion());
                detalleejecucionplanificacioncalidad.setIdejecucionplanificacioncalidad(idejecucionplanificacioncalidad);
            }
            em.persist(detalleejecucionplanificacioncalidad);
            if (idprocesocalidad != null) {
                idprocesocalidad.getDetalleejecucionplanificacioncalidadList().add(detalleejecucionplanificacioncalidad);
                idprocesocalidad = em.merge(idprocesocalidad);
            }
            if (piezareal != null) {
                piezareal.getDetalleejecucionplanificacioncalidadList().add(detalleejecucionplanificacioncalidad);
                piezareal = em.merge(piezareal);
            }
            if (pieza != null) {
                pieza.getDetalleejecucionplanificacioncalidadList().add(detalleejecucionplanificacioncalidad);
                pieza = em.merge(pieza);
            }
            if (idejecucionplanificacioncalidad != null) {
                idejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList().add(detalleejecucionplanificacioncalidad);
                idejecucionplanificacioncalidad = em.merge(idejecucionplanificacioncalidad);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleejecucionplanificacioncalidad persistentDetalleejecucionplanificacioncalidad = em.find(Detalleejecucionplanificacioncalidad.class, detalleejecucionplanificacioncalidad.getIddetalle());
            Procesocalidad idprocesocalidadOld = persistentDetalleejecucionplanificacioncalidad.getIdprocesocalidad();
            Procesocalidad idprocesocalidadNew = detalleejecucionplanificacioncalidad.getIdprocesocalidad();
            Piezareal piezarealOld = persistentDetalleejecucionplanificacioncalidad.getPiezareal();
            Piezareal piezarealNew = detalleejecucionplanificacioncalidad.getPiezareal();
            Pieza piezaOld = persistentDetalleejecucionplanificacioncalidad.getPieza();
            Pieza piezaNew = detalleejecucionplanificacioncalidad.getPieza();
            Ejecucionplanificacioncalidad idejecucionplanificacioncalidadOld = persistentDetalleejecucionplanificacioncalidad.getIdejecucionplanificacioncalidad();
            Ejecucionplanificacioncalidad idejecucionplanificacioncalidadNew = detalleejecucionplanificacioncalidad.getIdejecucionplanificacioncalidad();
            if (idprocesocalidadNew != null) {
                idprocesocalidadNew = em.getReference(idprocesocalidadNew.getClass(), idprocesocalidadNew.getIdprocesocalidad());
                detalleejecucionplanificacioncalidad.setIdprocesocalidad(idprocesocalidadNew);
            }
            if (piezarealNew != null) {
                piezarealNew = em.getReference(piezarealNew.getClass(), piezarealNew.getIdpiezareal());
                detalleejecucionplanificacioncalidad.setPiezareal(piezarealNew);
            }
            if (piezaNew != null) {
                piezaNew = em.getReference(piezaNew.getClass(), piezaNew.getIdpieza());
                detalleejecucionplanificacioncalidad.setPieza(piezaNew);
            }
            if (idejecucionplanificacioncalidadNew != null) {
                idejecucionplanificacioncalidadNew = em.getReference(idejecucionplanificacioncalidadNew.getClass(), idejecucionplanificacioncalidadNew.getIdejecucion());
                detalleejecucionplanificacioncalidad.setIdejecucionplanificacioncalidad(idejecucionplanificacioncalidadNew);
            }
            detalleejecucionplanificacioncalidad = em.merge(detalleejecucionplanificacioncalidad);
            if (idprocesocalidadOld != null && !idprocesocalidadOld.equals(idprocesocalidadNew)) {
                idprocesocalidadOld.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidad);
                idprocesocalidadOld = em.merge(idprocesocalidadOld);
            }
            if (idprocesocalidadNew != null && !idprocesocalidadNew.equals(idprocesocalidadOld)) {
                idprocesocalidadNew.getDetalleejecucionplanificacioncalidadList().add(detalleejecucionplanificacioncalidad);
                idprocesocalidadNew = em.merge(idprocesocalidadNew);
            }
            if (piezarealOld != null && !piezarealOld.equals(piezarealNew)) {
                piezarealOld.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidad);
                piezarealOld = em.merge(piezarealOld);
            }
            if (piezarealNew != null && !piezarealNew.equals(piezarealOld)) {
                piezarealNew.getDetalleejecucionplanificacioncalidadList().add(detalleejecucionplanificacioncalidad);
                piezarealNew = em.merge(piezarealNew);
            }
            if (piezaOld != null && !piezaOld.equals(piezaNew)) {
                piezaOld.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidad);
                piezaOld = em.merge(piezaOld);
            }
            if (piezaNew != null && !piezaNew.equals(piezaOld)) {
                piezaNew.getDetalleejecucionplanificacioncalidadList().add(detalleejecucionplanificacioncalidad);
                piezaNew = em.merge(piezaNew);
            }
            if (idejecucionplanificacioncalidadOld != null && !idejecucionplanificacioncalidadOld.equals(idejecucionplanificacioncalidadNew)) {
                idejecucionplanificacioncalidadOld.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidad);
                idejecucionplanificacioncalidadOld = em.merge(idejecucionplanificacioncalidadOld);
            }
            if (idejecucionplanificacioncalidadNew != null && !idejecucionplanificacioncalidadNew.equals(idejecucionplanificacioncalidadOld)) {
                idejecucionplanificacioncalidadNew.getDetalleejecucionplanificacioncalidadList().add(detalleejecucionplanificacioncalidad);
                idejecucionplanificacioncalidadNew = em.merge(idejecucionplanificacioncalidadNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detalleejecucionplanificacioncalidad.getIddetalle();
                if (findDetalleejecucionplanificacioncalidad(id) == null) {
                    throw new NonexistentEntityException("The detalleejecucionplanificacioncalidad with id " + id + " no longer exists.");
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
            Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad;
            try {
                detalleejecucionplanificacioncalidad = em.getReference(Detalleejecucionplanificacioncalidad.class, id);
                detalleejecucionplanificacioncalidad.getIddetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleejecucionplanificacioncalidad with id " + id + " no longer exists.", enfe);
            }
            Procesocalidad idprocesocalidad = detalleejecucionplanificacioncalidad.getIdprocesocalidad();
            if (idprocesocalidad != null) {
                idprocesocalidad.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidad);
                idprocesocalidad = em.merge(idprocesocalidad);
            }
            Piezareal piezareal = detalleejecucionplanificacioncalidad.getPiezareal();
            if (piezareal != null) {
                piezareal.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidad);
                piezareal = em.merge(piezareal);
            }
            Pieza pieza = detalleejecucionplanificacioncalidad.getPieza();
            if (pieza != null) {
                pieza.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidad);
                pieza = em.merge(pieza);
            }
            Ejecucionplanificacioncalidad idejecucionplanificacioncalidad = detalleejecucionplanificacioncalidad.getIdejecucionplanificacioncalidad();
            if (idejecucionplanificacioncalidad != null) {
                idejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList().remove(detalleejecucionplanificacioncalidad);
                idejecucionplanificacioncalidad = em.merge(idejecucionplanificacioncalidad);
            }
            em.remove(detalleejecucionplanificacioncalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleejecucionplanificacioncalidad> findDetalleejecucionplanificacioncalidadEntities() {
        return findDetalleejecucionplanificacioncalidadEntities(true, -1, -1);
    }

    public List<Detalleejecucionplanificacioncalidad> findDetalleejecucionplanificacioncalidadEntities(int maxResults, int firstResult) {
        return findDetalleejecucionplanificacioncalidadEntities(false, maxResults, firstResult);
    }

    private List<Detalleejecucionplanificacioncalidad> findDetalleejecucionplanificacioncalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleejecucionplanificacioncalidad.class));
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

    public Detalleejecucionplanificacioncalidad findDetalleejecucionplanificacioncalidad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleejecucionplanificacioncalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleejecucionplanificacioncalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleejecucionplanificacioncalidad> rt = cq.from(Detalleejecucionplanificacioncalidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
