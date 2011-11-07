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
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Detallereclamoempresamantenimiento;
import metalsoft.datos.jpa.entity.Trabajotercerizado;
import metalsoft.datos.jpa.entity.Reclamoempresamantenimiento;
import metalsoft.datos.jpa.entity.Detalletrabajotercerizado;

/**
 *
 * @author Nino
 */
public class DetallereclamoempresamantenimientoJpaController implements Serializable {

    public DetallereclamoempresamantenimientoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detallereclamoempresamantenimiento detallereclamoempresamantenimiento) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Trabajotercerizado idtrabajo = detallereclamoempresamantenimiento.getIdtrabajo();
            if (idtrabajo != null) {
                idtrabajo = em.getReference(idtrabajo.getClass(), idtrabajo.getIdtrabajo());
                detallereclamoempresamantenimiento.setIdtrabajo(idtrabajo);
            }
            Reclamoempresamantenimiento idreclamo = detallereclamoempresamantenimiento.getIdreclamo();
            if (idreclamo != null) {
                idreclamo = em.getReference(idreclamo.getClass(), idreclamo.getIdreclamo());
                detallereclamoempresamantenimiento.setIdreclamo(idreclamo);
            }
            Detalletrabajotercerizado iddetalletrabajo = detallereclamoempresamantenimiento.getIddetalletrabajo();
            if (iddetalletrabajo != null) {
                iddetalletrabajo = em.getReference(iddetalletrabajo.getClass(), iddetalletrabajo.getIddetalle());
                detallereclamoempresamantenimiento.setIddetalletrabajo(iddetalletrabajo);
            }
            em.persist(detallereclamoempresamantenimiento);
            if (idtrabajo != null) {
                idtrabajo.getDetallereclamoempresamantenimientoList().add(detallereclamoempresamantenimiento);
                idtrabajo = em.merge(idtrabajo);
            }
            if (idreclamo != null) {
                idreclamo.getDetallereclamoempresamantenimientoList().add(detallereclamoempresamantenimiento);
                idreclamo = em.merge(idreclamo);
            }
            if (iddetalletrabajo != null) {
                iddetalletrabajo.getDetallereclamoempresamantenimientoList().add(detallereclamoempresamantenimiento);
                iddetalletrabajo = em.merge(iddetalletrabajo);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetallereclamoempresamantenimiento(detallereclamoempresamantenimiento.getIddetalle()) != null) {
                throw new PreexistingEntityException("Detallereclamoempresamantenimiento " + detallereclamoempresamantenimiento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detallereclamoempresamantenimiento detallereclamoempresamantenimiento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detallereclamoempresamantenimiento persistentDetallereclamoempresamantenimiento = em.find(Detallereclamoempresamantenimiento.class, detallereclamoempresamantenimiento.getIddetalle());
            Trabajotercerizado idtrabajoOld = persistentDetallereclamoempresamantenimiento.getIdtrabajo();
            Trabajotercerizado idtrabajoNew = detallereclamoempresamantenimiento.getIdtrabajo();
            Reclamoempresamantenimiento idreclamoOld = persistentDetallereclamoempresamantenimiento.getIdreclamo();
            Reclamoempresamantenimiento idreclamoNew = detallereclamoempresamantenimiento.getIdreclamo();
            Detalletrabajotercerizado iddetalletrabajoOld = persistentDetallereclamoempresamantenimiento.getIddetalletrabajo();
            Detalletrabajotercerizado iddetalletrabajoNew = detallereclamoempresamantenimiento.getIddetalletrabajo();
            if (idtrabajoNew != null) {
                idtrabajoNew = em.getReference(idtrabajoNew.getClass(), idtrabajoNew.getIdtrabajo());
                detallereclamoempresamantenimiento.setIdtrabajo(idtrabajoNew);
            }
            if (idreclamoNew != null) {
                idreclamoNew = em.getReference(idreclamoNew.getClass(), idreclamoNew.getIdreclamo());
                detallereclamoempresamantenimiento.setIdreclamo(idreclamoNew);
            }
            if (iddetalletrabajoNew != null) {
                iddetalletrabajoNew = em.getReference(iddetalletrabajoNew.getClass(), iddetalletrabajoNew.getIddetalle());
                detallereclamoempresamantenimiento.setIddetalletrabajo(iddetalletrabajoNew);
            }
            detallereclamoempresamantenimiento = em.merge(detallereclamoempresamantenimiento);
            if (idtrabajoOld != null && !idtrabajoOld.equals(idtrabajoNew)) {
                idtrabajoOld.getDetallereclamoempresamantenimientoList().remove(detallereclamoempresamantenimiento);
                idtrabajoOld = em.merge(idtrabajoOld);
            }
            if (idtrabajoNew != null && !idtrabajoNew.equals(idtrabajoOld)) {
                idtrabajoNew.getDetallereclamoempresamantenimientoList().add(detallereclamoempresamantenimiento);
                idtrabajoNew = em.merge(idtrabajoNew);
            }
            if (idreclamoOld != null && !idreclamoOld.equals(idreclamoNew)) {
                idreclamoOld.getDetallereclamoempresamantenimientoList().remove(detallereclamoempresamantenimiento);
                idreclamoOld = em.merge(idreclamoOld);
            }
            if (idreclamoNew != null && !idreclamoNew.equals(idreclamoOld)) {
                idreclamoNew.getDetallereclamoempresamantenimientoList().add(detallereclamoempresamantenimiento);
                idreclamoNew = em.merge(idreclamoNew);
            }
            if (iddetalletrabajoOld != null && !iddetalletrabajoOld.equals(iddetalletrabajoNew)) {
                iddetalletrabajoOld.getDetallereclamoempresamantenimientoList().remove(detallereclamoempresamantenimiento);
                iddetalletrabajoOld = em.merge(iddetalletrabajoOld);
            }
            if (iddetalletrabajoNew != null && !iddetalletrabajoNew.equals(iddetalletrabajoOld)) {
                iddetalletrabajoNew.getDetallereclamoempresamantenimientoList().add(detallereclamoempresamantenimiento);
                iddetalletrabajoNew = em.merge(iddetalletrabajoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = detallereclamoempresamantenimiento.getIddetalle();
                if (findDetallereclamoempresamantenimiento(id) == null) {
                    throw new NonexistentEntityException("The detallereclamoempresamantenimiento with id " + id + " no longer exists.");
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
            Detallereclamoempresamantenimiento detallereclamoempresamantenimiento;
            try {
                detallereclamoempresamantenimiento = em.getReference(Detallereclamoempresamantenimiento.class, id);
                detallereclamoempresamantenimiento.getIddetalle();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detallereclamoempresamantenimiento with id " + id + " no longer exists.", enfe);
            }
            Trabajotercerizado idtrabajo = detallereclamoempresamantenimiento.getIdtrabajo();
            if (idtrabajo != null) {
                idtrabajo.getDetallereclamoempresamantenimientoList().remove(detallereclamoempresamantenimiento);
                idtrabajo = em.merge(idtrabajo);
            }
            Reclamoempresamantenimiento idreclamo = detallereclamoempresamantenimiento.getIdreclamo();
            if (idreclamo != null) {
                idreclamo.getDetallereclamoempresamantenimientoList().remove(detallereclamoempresamantenimiento);
                idreclamo = em.merge(idreclamo);
            }
            Detalletrabajotercerizado iddetalletrabajo = detallereclamoempresamantenimiento.getIddetalletrabajo();
            if (iddetalletrabajo != null) {
                iddetalletrabajo.getDetallereclamoempresamantenimientoList().remove(detallereclamoempresamantenimiento);
                iddetalletrabajo = em.merge(iddetalletrabajo);
            }
            em.remove(detallereclamoempresamantenimiento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detallereclamoempresamantenimiento> findDetallereclamoempresamantenimientoEntities() {
        return findDetallereclamoempresamantenimientoEntities(true, -1, -1);
    }

    public List<Detallereclamoempresamantenimiento> findDetallereclamoempresamantenimientoEntities(int maxResults, int firstResult) {
        return findDetallereclamoempresamantenimientoEntities(false, maxResults, firstResult);
    }

    private List<Detallereclamoempresamantenimiento> findDetallereclamoempresamantenimientoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detallereclamoempresamantenimiento.class));
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

    public Detallereclamoempresamantenimiento findDetallereclamoempresamantenimiento(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detallereclamoempresamantenimiento.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetallereclamoempresamantenimientoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detallereclamoempresamantenimiento> rt = cq.from(Detallereclamoempresamantenimiento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
