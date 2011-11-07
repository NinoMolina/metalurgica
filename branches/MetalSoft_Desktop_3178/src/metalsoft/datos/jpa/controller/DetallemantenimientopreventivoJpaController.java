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
import metalsoft.datos.jpa.entity.Detallemantenimientopreventivo;
import metalsoft.datos.jpa.entity.Servicio;
import metalsoft.datos.jpa.entity.Mantenimientopreventivo;

/**
 *
 * @author Nino
 */
public class DetallemantenimientopreventivoJpaController implements Serializable {

    public DetallemantenimientopreventivoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallemantenimientopreventivo detallemantenimientopreventivo) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio servicio = detallemantenimientopreventivo.getServicio();
            if (servicio != null) {
                servicio = em.getReference(servicio.getClass(), servicio.getIdservicio());
                detallemantenimientopreventivo.setServicio(servicio);
            }
            Mantenimientopreventivo idmantenimientopreventivo = detallemantenimientopreventivo.getIdmantenimientopreventivo();
            if (idmantenimientopreventivo != null) {
                idmantenimientopreventivo = em.getReference(idmantenimientopreventivo.getClass(), idmantenimientopreventivo.getIdmantenimientopreventivo());
                detallemantenimientopreventivo.setIdmantenimientopreventivo(idmantenimientopreventivo);
            }
            em.persist(detallemantenimientopreventivo);
            if (servicio != null) {
                servicio.getDetallemantenimientopreventivoList().add(detallemantenimientopreventivo);
                servicio = em.merge(servicio);
            }
            if (idmantenimientopreventivo != null) {
                idmantenimientopreventivo.getDetallemantenimientopreventivoList().add(detallemantenimientopreventivo);
                idmantenimientopreventivo = em.merge(idmantenimientopreventivo);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallemantenimientopreventivo detallemantenimientopreventivo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallemantenimientopreventivo persistentDetallemantenimientopreventivo = em.find(Detallemantenimientopreventivo.class, detallemantenimientopreventivo.getIddetalle());
            Servicio servicioOld = persistentDetallemantenimientopreventivo.getServicio();
            Servicio servicioNew = detallemantenimientopreventivo.getServicio();
            Mantenimientopreventivo idmantenimientopreventivoOld = persistentDetallemantenimientopreventivo.getIdmantenimientopreventivo();
            Mantenimientopreventivo idmantenimientopreventivoNew = detallemantenimientopreventivo.getIdmantenimientopreventivo();
            if (servicioNew != null) {
                servicioNew = em.getReference(servicioNew.getClass(), servicioNew.getIdservicio());
                detallemantenimientopreventivo.setServicio(servicioNew);
            }
            if (idmantenimientopreventivoNew != null) {
                idmantenimientopreventivoNew = em.getReference(idmantenimientopreventivoNew.getClass(), idmantenimientopreventivoNew.getIdmantenimientopreventivo());
                detallemantenimientopreventivo.setIdmantenimientopreventivo(idmantenimientopreventivoNew);
            }
            detallemantenimientopreventivo = em.merge(detallemantenimientopreventivo);
            if (servicioOld != null && !servicioOld.equals(servicioNew)) {
                servicioOld.getDetallemantenimientopreventivoList().remove(detallemantenimientopreventivo);
                servicioOld = em.merge(servicioOld);
            }
            if (servicioNew != null && !servicioNew.equals(servicioOld)) {
                servicioNew.getDetallemantenimientopreventivoList().add(detallemantenimientopreventivo);
                servicioNew = em.merge(servicioNew);
            }
            if (idmantenimientopreventivoOld != null && !idmantenimientopreventivoOld.equals(idmantenimientopreventivoNew)) {
                idmantenimientopreventivoOld.getDetallemantenimientopreventivoList().remove(detallemantenimientopreventivo);
                idmantenimientopreventivoOld = em.merge(idmantenimientopreventivoOld);
            }
            if (idmantenimientopreventivoNew != null && !idmantenimientopreventivoNew.equals(idmantenimientopreventivoOld)) {
                idmantenimientopreventivoNew.getDetallemantenimientopreventivoList().add(detallemantenimientopreventivo);
                idmantenimientopreventivoNew = em.merge(idmantenimientopreventivoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detallemantenimientopreventivo.getIddetalle();
                if (findDetallemantenimientopreventivo(id) == null) {
                    throw new NonexistentEntityException("The detallemantenimientopreventivo with id " + id + " no longer exists.");
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
            Detallemantenimientopreventivo detallemantenimientopreventivo;
            try {
                detallemantenimientopreventivo = em.getReference(Detallemantenimientopreventivo.class, id);
                detallemantenimientopreventivo.getIddetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallemantenimientopreventivo with id " + id + " no longer exists.", enfe);
            }
            Servicio servicio = detallemantenimientopreventivo.getServicio();
            if (servicio != null) {
                servicio.getDetallemantenimientopreventivoList().remove(detallemantenimientopreventivo);
                servicio = em.merge(servicio);
            }
            Mantenimientopreventivo idmantenimientopreventivo = detallemantenimientopreventivo.getIdmantenimientopreventivo();
            if (idmantenimientopreventivo != null) {
                idmantenimientopreventivo.getDetallemantenimientopreventivoList().remove(detallemantenimientopreventivo);
                idmantenimientopreventivo = em.merge(idmantenimientopreventivo);
            }
            em.remove(detallemantenimientopreventivo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallemantenimientopreventivo> findDetallemantenimientopreventivoEntities() {
        return findDetallemantenimientopreventivoEntities(true, -1, -1);
    }

    public List<Detallemantenimientopreventivo> findDetallemantenimientopreventivoEntities(int maxResults, int firstResult) {
        return findDetallemantenimientopreventivoEntities(false, maxResults, firstResult);
    }

    private List<Detallemantenimientopreventivo> findDetallemantenimientopreventivoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallemantenimientopreventivo.class));
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

    public Detallemantenimientopreventivo findDetallemantenimientopreventivo(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallemantenimientopreventivo.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallemantenimientopreventivoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallemantenimientopreventivo> rt = cq.from(Detallemantenimientopreventivo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
