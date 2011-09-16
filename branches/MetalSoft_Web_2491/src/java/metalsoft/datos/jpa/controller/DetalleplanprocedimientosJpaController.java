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
import metalsoft.datos.jpa.entity.Detalleplanprocedimientos;
import metalsoft.datos.jpa.entity.DetalleplanprocedimientosPK;
import metalsoft.datos.jpa.entity.Planprocedimientos;
import metalsoft.datos.jpa.entity.Etapadeproduccion;

/**
 *
 * @author Nino
 */
public class DetalleplanprocedimientosJpaController implements Serializable {

    public DetalleplanprocedimientosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Detalleplanprocedimientos detalleplanprocedimientos) throws PreexistingEntityException, Exception {
        if (detalleplanprocedimientos.getDetalleplanprocedimientosPK() == null) {
            detalleplanprocedimientos.setDetalleplanprocedimientosPK(new DetalleplanprocedimientosPK());
        }
        detalleplanprocedimientos.getDetalleplanprocedimientosPK().setIdplanpprocedimientos(detalleplanprocedimientos.getPlanprocedimientos().getIdplanprocedimientos());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planprocedimientos planprocedimientos = detalleplanprocedimientos.getPlanprocedimientos();
            if (planprocedimientos != null) {
                planprocedimientos = em.getReference(planprocedimientos.getClass(), planprocedimientos.getIdplanprocedimientos());
                detalleplanprocedimientos.setPlanprocedimientos(planprocedimientos);
            }
            Etapadeproduccion idetapaproduccion = detalleplanprocedimientos.getIdetapaproduccion();
            if (idetapaproduccion != null) {
                idetapaproduccion = em.getReference(idetapaproduccion.getClass(), idetapaproduccion.getIdetapaproduccion());
                detalleplanprocedimientos.setIdetapaproduccion(idetapaproduccion);
            }
            em.persist(detalleplanprocedimientos);
            if (planprocedimientos != null) {
                planprocedimientos.getDetalleplanprocedimientosList().add(detalleplanprocedimientos);
                planprocedimientos = em.merge(planprocedimientos);
            }
            if (idetapaproduccion != null) {
                idetapaproduccion.getDetalleplanprocedimientosList().add(detalleplanprocedimientos);
                idetapaproduccion = em.merge(idetapaproduccion);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDetalleplanprocedimientos(detalleplanprocedimientos.getDetalleplanprocedimientosPK()) != null) {
                throw new PreexistingEntityException("Detalleplanprocedimientos " + detalleplanprocedimientos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Detalleplanprocedimientos detalleplanprocedimientos) throws NonexistentEntityException, Exception {
        detalleplanprocedimientos.getDetalleplanprocedimientosPK().setIdplanpprocedimientos(detalleplanprocedimientos.getPlanprocedimientos().getIdplanprocedimientos());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleplanprocedimientos persistentDetalleplanprocedimientos = em.find(Detalleplanprocedimientos.class, detalleplanprocedimientos.getDetalleplanprocedimientosPK());
            Planprocedimientos planprocedimientosOld = persistentDetalleplanprocedimientos.getPlanprocedimientos();
            Planprocedimientos planprocedimientosNew = detalleplanprocedimientos.getPlanprocedimientos();
            Etapadeproduccion idetapaproduccionOld = persistentDetalleplanprocedimientos.getIdetapaproduccion();
            Etapadeproduccion idetapaproduccionNew = detalleplanprocedimientos.getIdetapaproduccion();
            if (planprocedimientosNew != null) {
                planprocedimientosNew = em.getReference(planprocedimientosNew.getClass(), planprocedimientosNew.getIdplanprocedimientos());
                detalleplanprocedimientos.setPlanprocedimientos(planprocedimientosNew);
            }
            if (idetapaproduccionNew != null) {
                idetapaproduccionNew = em.getReference(idetapaproduccionNew.getClass(), idetapaproduccionNew.getIdetapaproduccion());
                detalleplanprocedimientos.setIdetapaproduccion(idetapaproduccionNew);
            }
            detalleplanprocedimientos = em.merge(detalleplanprocedimientos);
            if (planprocedimientosOld != null && !planprocedimientosOld.equals(planprocedimientosNew)) {
                planprocedimientosOld.getDetalleplanprocedimientosList().remove(detalleplanprocedimientos);
                planprocedimientosOld = em.merge(planprocedimientosOld);
            }
            if (planprocedimientosNew != null && !planprocedimientosNew.equals(planprocedimientosOld)) {
                planprocedimientosNew.getDetalleplanprocedimientosList().add(detalleplanprocedimientos);
                planprocedimientosNew = em.merge(planprocedimientosNew);
            }
            if (idetapaproduccionOld != null && !idetapaproduccionOld.equals(idetapaproduccionNew)) {
                idetapaproduccionOld.getDetalleplanprocedimientosList().remove(detalleplanprocedimientos);
                idetapaproduccionOld = em.merge(idetapaproduccionOld);
            }
            if (idetapaproduccionNew != null && !idetapaproduccionNew.equals(idetapaproduccionOld)) {
                idetapaproduccionNew.getDetalleplanprocedimientosList().add(detalleplanprocedimientos);
                idetapaproduccionNew = em.merge(idetapaproduccionNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DetalleplanprocedimientosPK id = detalleplanprocedimientos.getDetalleplanprocedimientosPK();
                if (findDetalleplanprocedimientos(id) == null) {
                    throw new NonexistentEntityException("The detalleplanprocedimientos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DetalleplanprocedimientosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Detalleplanprocedimientos detalleplanprocedimientos;
            try {
                detalleplanprocedimientos = em.getReference(Detalleplanprocedimientos.class, id);
                detalleplanprocedimientos.getDetalleplanprocedimientosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The detalleplanprocedimientos with id " + id + " no longer exists.", enfe);
            }
            Planprocedimientos planprocedimientos = detalleplanprocedimientos.getPlanprocedimientos();
            if (planprocedimientos != null) {
                planprocedimientos.getDetalleplanprocedimientosList().remove(detalleplanprocedimientos);
                planprocedimientos = em.merge(planprocedimientos);
            }
            Etapadeproduccion idetapaproduccion = detalleplanprocedimientos.getIdetapaproduccion();
            if (idetapaproduccion != null) {
                idetapaproduccion.getDetalleplanprocedimientosList().remove(detalleplanprocedimientos);
                idetapaproduccion = em.merge(idetapaproduccion);
            }
            em.remove(detalleplanprocedimientos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Detalleplanprocedimientos> findDetalleplanprocedimientosEntities() {
        return findDetalleplanprocedimientosEntities(true, -1, -1);
    }

    public List<Detalleplanprocedimientos> findDetalleplanprocedimientosEntities(int maxResults, int firstResult) {
        return findDetalleplanprocedimientosEntities(false, maxResults, firstResult);
    }

    private List<Detalleplanprocedimientos> findDetalleplanprocedimientosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Detalleplanprocedimientos.class));
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

    public Detalleplanprocedimientos findDetalleplanprocedimientos(DetalleplanprocedimientosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Detalleplanprocedimientos.class, id);
        } finally {
            em.close();
        }
    }

    public int getDetalleplanprocedimientosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Detalleplanprocedimientos> rt = cq.from(Detalleplanprocedimientos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
