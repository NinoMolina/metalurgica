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
            Estadoejecplanifpedido estado1 = ejecucionplanificacionproduccion.getEstado1();
            if (estado1 != null) {
                estado1 = em.getReference(estado1.getClass(), estado1.getIdestado());
                ejecucionplanificacionproduccion.setEstado1(estado1);
            }
            Planificacionproduccion idplanificacionproduccion = ejecucionplanificacionproduccion.getIdplanificacionproduccion();
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion = em.getReference(idplanificacionproduccion.getClass(), idplanificacionproduccion.getIdplanificacionproduccion());
                ejecucionplanificacionproduccion.setIdplanificacionproduccion(idplanificacionproduccion);
            }
            Planificacionproduccion idplanificacionproduccion1 = ejecucionplanificacionproduccion.getIdplanificacionproduccion1();
            if (idplanificacionproduccion1 != null) {
                idplanificacionproduccion1 = em.getReference(idplanificacionproduccion1.getClass(), idplanificacionproduccion1.getIdplanificacionproduccion());
                ejecucionplanificacionproduccion.setIdplanificacionproduccion1(idplanificacionproduccion1);
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
            if (estado1 != null) {
                estado1.getEjecucionplanificacionproduccionSet().add(ejecucionplanificacionproduccion);
                estado1 = em.merge(estado1);
            }
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion.getEjecucionplanificacionproduccionSet().add(ejecucionplanificacionproduccion);
                idplanificacionproduccion = em.merge(idplanificacionproduccion);
            }
            if (idplanificacionproduccion1 != null) {
                idplanificacionproduccion1.getEjecucionplanificacionproduccionSet().add(ejecucionplanificacionproduccion);
                idplanificacionproduccion1 = em.merge(idplanificacionproduccion1);
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
            Estadoejecplanifpedido estado1Old = persistentEjecucionplanificacionproduccion.getEstado1();
            Estadoejecplanifpedido estado1New = ejecucionplanificacionproduccion.getEstado1();
            Planificacionproduccion idplanificacionproduccionOld = persistentEjecucionplanificacionproduccion.getIdplanificacionproduccion();
            Planificacionproduccion idplanificacionproduccionNew = ejecucionplanificacionproduccion.getIdplanificacionproduccion();
            Planificacionproduccion idplanificacionproduccion1Old = persistentEjecucionplanificacionproduccion.getIdplanificacionproduccion1();
            Planificacionproduccion idplanificacionproduccion1New = ejecucionplanificacionproduccion.getIdplanificacionproduccion1();
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
            if (estado1New != null) {
                estado1New = em.getReference(estado1New.getClass(), estado1New.getIdestado());
                ejecucionplanificacionproduccion.setEstado1(estado1New);
            }
            if (idplanificacionproduccionNew != null) {
                idplanificacionproduccionNew = em.getReference(idplanificacionproduccionNew.getClass(), idplanificacionproduccionNew.getIdplanificacionproduccion());
                ejecucionplanificacionproduccion.setIdplanificacionproduccion(idplanificacionproduccionNew);
            }
            if (idplanificacionproduccion1New != null) {
                idplanificacionproduccion1New = em.getReference(idplanificacionproduccion1New.getClass(), idplanificacionproduccion1New.getIdplanificacionproduccion());
                ejecucionplanificacionproduccion.setIdplanificacionproduccion1(idplanificacionproduccion1New);
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
            if (estado1Old != null && !estado1Old.equals(estado1New)) {
                estado1Old.getEjecucionplanificacionproduccionSet().remove(ejecucionplanificacionproduccion);
                estado1Old = em.merge(estado1Old);
            }
            if (estado1New != null && !estado1New.equals(estado1Old)) {
                estado1New.getEjecucionplanificacionproduccionSet().add(ejecucionplanificacionproduccion);
                estado1New = em.merge(estado1New);
            }
            if (idplanificacionproduccionOld != null && !idplanificacionproduccionOld.equals(idplanificacionproduccionNew)) {
                idplanificacionproduccionOld.getEjecucionplanificacionproduccionSet().remove(ejecucionplanificacionproduccion);
                idplanificacionproduccionOld = em.merge(idplanificacionproduccionOld);
            }
            if (idplanificacionproduccionNew != null && !idplanificacionproduccionNew.equals(idplanificacionproduccionOld)) {
                idplanificacionproduccionNew.getEjecucionplanificacionproduccionSet().add(ejecucionplanificacionproduccion);
                idplanificacionproduccionNew = em.merge(idplanificacionproduccionNew);
            }
            if (idplanificacionproduccion1Old != null && !idplanificacionproduccion1Old.equals(idplanificacionproduccion1New)) {
                idplanificacionproduccion1Old.getEjecucionplanificacionproduccionSet().remove(ejecucionplanificacionproduccion);
                idplanificacionproduccion1Old = em.merge(idplanificacionproduccion1Old);
            }
            if (idplanificacionproduccion1New != null && !idplanificacionproduccion1New.equals(idplanificacionproduccion1Old)) {
                idplanificacionproduccion1New.getEjecucionplanificacionproduccionSet().add(ejecucionplanificacionproduccion);
                idplanificacionproduccion1New = em.merge(idplanificacionproduccion1New);
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
            Estadoejecplanifpedido estado1 = ejecucionplanificacionproduccion.getEstado1();
            if (estado1 != null) {
                estado1.getEjecucionplanificacionproduccionSet().remove(ejecucionplanificacionproduccion);
                estado1 = em.merge(estado1);
            }
            Planificacionproduccion idplanificacionproduccion = ejecucionplanificacionproduccion.getIdplanificacionproduccion();
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion.getEjecucionplanificacionproduccionSet().remove(ejecucionplanificacionproduccion);
                idplanificacionproduccion = em.merge(idplanificacionproduccion);
            }
            Planificacionproduccion idplanificacionproduccion1 = ejecucionplanificacionproduccion.getIdplanificacionproduccion1();
            if (idplanificacionproduccion1 != null) {
                idplanificacionproduccion1.getEjecucionplanificacionproduccionSet().remove(ejecucionplanificacionproduccion);
                idplanificacionproduccion1 = em.merge(idplanificacionproduccion1);
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