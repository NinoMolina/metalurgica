/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Ejecucionplanificacionproduccion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Estadoejecplanifpedido;
import entity.Planificacionproduccion;
import entity.Detalleejecucionplanificacion;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class EjecucionplanificacionproduccionJpaController {

    public EjecucionplanificacionproduccionJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ejecucionplanificacionproduccion ejecucionplanificacionproduccion) throws PreexistingEntityException, Exception {
        if (ejecucionplanificacionproduccion.getDetalleejecucionplanificacionSet() == null) {
            ejecucionplanificacionproduccion.setDetalleejecucionplanificacionSet(new HashSet<Detalleejecucionplanificacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoejecplanifpedido estado = ejecucionplanificacionproduccion.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                ejecucionplanificacionproduccion.setEstado(estado);
            }
            Planificacionproduccion idplanificacionproduccion = ejecucionplanificacionproduccion.getIdplanificacionproduccion();
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion = em.getReference(idplanificacionproduccion.getClass(), idplanificacionproduccion.getIdplanificacionproduccion());
                ejecucionplanificacionproduccion.setIdplanificacionproduccion(idplanificacionproduccion);
            }
            Set<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionSet = new HashSet<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach : ejecucionplanificacionproduccion.getDetalleejecucionplanificacionSet()) {
                detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionSet.add(detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach);
            }
            ejecucionplanificacionproduccion.setDetalleejecucionplanificacionSet(attachedDetalleejecucionplanificacionSet);
            em.persist(ejecucionplanificacionproduccion);
            if (estado != null) {
                estado.getEjecucionplanificacionproduccionSet().add(ejecucionplanificacionproduccion);
                estado = em.merge(estado);
            }
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion.getEjecucionplanificacionproduccionSet().add(ejecucionplanificacionproduccion);
                idplanificacionproduccion = em.merge(idplanificacionproduccion);
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetDetalleejecucionplanificacion : ejecucionplanificacionproduccion.getDetalleejecucionplanificacionSet()) {
                Ejecucionplanificacionproduccion oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion = detalleejecucionplanificacionSetDetalleejecucionplanificacion.getIdejecucionplanificacionproduccion();
                detalleejecucionplanificacionSetDetalleejecucionplanificacion.setIdejecucionplanificacionproduccion(ejecucionplanificacionproduccion);
                detalleejecucionplanificacionSetDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionSetDetalleejecucionplanificacion);
                if (oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion != null) {
                    oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacionSetDetalleejecucionplanificacion);
                    oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion = em.merge(oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEjecucionplanificacionproduccion(ejecucionplanificacionproduccion.getIdejecucion()) != null) {
                throw new PreexistingEntityException("Ejecucionplanificacionproduccion " + ejecucionplanificacionproduccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ejecucionplanificacionproduccion ejecucionplanificacionproduccion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionplanificacionproduccion persistentEjecucionplanificacionproduccion = em.find(Ejecucionplanificacionproduccion.class, ejecucionplanificacionproduccion.getIdejecucion());
            Estadoejecplanifpedido estadoOld = persistentEjecucionplanificacionproduccion.getEstado();
            Estadoejecplanifpedido estadoNew = ejecucionplanificacionproduccion.getEstado();
            Planificacionproduccion idplanificacionproduccionOld = persistentEjecucionplanificacionproduccion.getIdplanificacionproduccion();
            Planificacionproduccion idplanificacionproduccionNew = ejecucionplanificacionproduccion.getIdplanificacionproduccion();
            Set<Detalleejecucionplanificacion> detalleejecucionplanificacionSetOld = persistentEjecucionplanificacionproduccion.getDetalleejecucionplanificacionSet();
            Set<Detalleejecucionplanificacion> detalleejecucionplanificacionSetNew = ejecucionplanificacionproduccion.getDetalleejecucionplanificacionSet();
            List<String> illegalOrphanMessages = null;
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetOldDetalleejecucionplanificacion : detalleejecucionplanificacionSetOld) {
                if (!detalleejecucionplanificacionSetNew.contains(detalleejecucionplanificacionSetOldDetalleejecucionplanificacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleejecucionplanificacion " + detalleejecucionplanificacionSetOldDetalleejecucionplanificacion + " since its idejecucionplanificacionproduccion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                ejecucionplanificacionproduccion.setEstado(estadoNew);
            }
            if (idplanificacionproduccionNew != null) {
                idplanificacionproduccionNew = em.getReference(idplanificacionproduccionNew.getClass(), idplanificacionproduccionNew.getIdplanificacionproduccion());
                ejecucionplanificacionproduccion.setIdplanificacionproduccion(idplanificacionproduccionNew);
            }
            Set<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionSetNew = new HashSet<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach : detalleejecucionplanificacionSetNew) {
                detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionSetNew.add(detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach);
            }
            detalleejecucionplanificacionSetNew = attachedDetalleejecucionplanificacionSetNew;
            ejecucionplanificacionproduccion.setDetalleejecucionplanificacionSet(detalleejecucionplanificacionSetNew);
            ejecucionplanificacionproduccion = em.merge(ejecucionplanificacionproduccion);
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getEjecucionplanificacionproduccionSet().remove(ejecucionplanificacionproduccion);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getEjecucionplanificacionproduccionSet().add(ejecucionplanificacionproduccion);
                estadoNew = em.merge(estadoNew);
            }
            if (idplanificacionproduccionOld != null && !idplanificacionproduccionOld.equals(idplanificacionproduccionNew)) {
                idplanificacionproduccionOld.getEjecucionplanificacionproduccionSet().remove(ejecucionplanificacionproduccion);
                idplanificacionproduccionOld = em.merge(idplanificacionproduccionOld);
            }
            if (idplanificacionproduccionNew != null && !idplanificacionproduccionNew.equals(idplanificacionproduccionOld)) {
                idplanificacionproduccionNew.getEjecucionplanificacionproduccionSet().add(ejecucionplanificacionproduccion);
                idplanificacionproduccionNew = em.merge(idplanificacionproduccionNew);
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetNewDetalleejecucionplanificacion : detalleejecucionplanificacionSetNew) {
                if (!detalleejecucionplanificacionSetOld.contains(detalleejecucionplanificacionSetNewDetalleejecucionplanificacion)) {
                    Ejecucionplanificacionproduccion oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion = detalleejecucionplanificacionSetNewDetalleejecucionplanificacion.getIdejecucionplanificacionproduccion();
                    detalleejecucionplanificacionSetNewDetalleejecucionplanificacion.setIdejecucionplanificacionproduccion(ejecucionplanificacionproduccion);
                    detalleejecucionplanificacionSetNewDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionSetNewDetalleejecucionplanificacion);
                    if (oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion != null && !oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion.equals(ejecucionplanificacionproduccion)) {
                        oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacionSetNewDetalleejecucionplanificacion);
                        oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion = em.merge(oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = ejecucionplanificacionproduccion.getIdejecucion();
                if (findEjecucionplanificacionproduccion(id) == null) {
                    throw new NonexistentEntityException("The ejecucionplanificacionproduccion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionplanificacionproduccion ejecucionplanificacionproduccion;
            try {
                ejecucionplanificacionproduccion = em.getReference(Ejecucionplanificacionproduccion.class, id);
                ejecucionplanificacionproduccion.getIdejecucion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ejecucionplanificacionproduccion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detalleejecucionplanificacion> detalleejecucionplanificacionSetOrphanCheck = ejecucionplanificacionproduccion.getDetalleejecucionplanificacionSet();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetOrphanCheckDetalleejecucionplanificacion : detalleejecucionplanificacionSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ejecucionplanificacionproduccion (" + ejecucionplanificacionproduccion + ") cannot be destroyed since the Detalleejecucionplanificacion " + detalleejecucionplanificacionSetOrphanCheckDetalleejecucionplanificacion + " in its detalleejecucionplanificacionSet field has a non-nullable idejecucionplanificacionproduccion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Estadoejecplanifpedido estado = ejecucionplanificacionproduccion.getEstado();
            if (estado != null) {
                estado.getEjecucionplanificacionproduccionSet().remove(ejecucionplanificacionproduccion);
                estado = em.merge(estado);
            }
            Planificacionproduccion idplanificacionproduccion = ejecucionplanificacionproduccion.getIdplanificacionproduccion();
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion.getEjecucionplanificacionproduccionSet().remove(ejecucionplanificacionproduccion);
                idplanificacionproduccion = em.merge(idplanificacionproduccion);
            }
            em.remove(ejecucionplanificacionproduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ejecucionplanificacionproduccion> findEjecucionplanificacionproduccionEntities() {
        return findEjecucionplanificacionproduccionEntities(true, -1, -1);
    }

    public List<Ejecucionplanificacionproduccion> findEjecucionplanificacionproduccionEntities(int maxResults, int firstResult) {
        return findEjecucionplanificacionproduccionEntities(false, maxResults, firstResult);
    }

    private List<Ejecucionplanificacionproduccion> findEjecucionplanificacionproduccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ejecucionplanificacionproduccion.class));
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

    public Ejecucionplanificacionproduccion findEjecucionplanificacionproduccion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ejecucionplanificacionproduccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getEjecucionplanificacionproduccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ejecucionplanificacionproduccion> rt = cq.from(Ejecucionplanificacionproduccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
