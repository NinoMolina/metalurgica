/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Ejecucionetapaproduccion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Empleado;
import entity.Estadoejecetapaprod;
import entity.Etapadeproduccion;
import entity.Detalleejecucionplanificacion;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class EjecucionetapaproduccionJpaController {

    public EjecucionetapaproduccionJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ejecucionetapaproduccion ejecucionetapaproduccion) throws PreexistingEntityException, Exception {
        if (ejecucionetapaproduccion.getDetalleejecucionplanificacionSet() == null) {
            ejecucionetapaproduccion.setDetalleejecucionplanificacionSet(new HashSet<Detalleejecucionplanificacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado = ejecucionetapaproduccion.getEmpleado();
            if (empleado != null) {
                empleado = em.getReference(empleado.getClass(), empleado.getIdempleado());
                ejecucionetapaproduccion.setEmpleado(empleado);
            }
            Empleado empleado1 = ejecucionetapaproduccion.getEmpleado1();
            if (empleado1 != null) {
                empleado1 = em.getReference(empleado1.getClass(), empleado1.getIdempleado());
                ejecucionetapaproduccion.setEmpleado1(empleado1);
            }
            Estadoejecetapaprod estado = ejecucionetapaproduccion.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                ejecucionetapaproduccion.setEstado(estado);
            }
            Estadoejecetapaprod estado1 = ejecucionetapaproduccion.getEstado1();
            if (estado1 != null) {
                estado1 = em.getReference(estado1.getClass(), estado1.getIdestado());
                ejecucionetapaproduccion.setEstado1(estado1);
            }
            Etapadeproduccion idetapaproduccion = ejecucionetapaproduccion.getIdetapaproduccion();
            if (idetapaproduccion != null) {
                idetapaproduccion = em.getReference(idetapaproduccion.getClass(), idetapaproduccion.getIdetapaproduccion());
                ejecucionetapaproduccion.setIdetapaproduccion(idetapaproduccion);
            }
            Etapadeproduccion idetapaproduccion1 = ejecucionetapaproduccion.getIdetapaproduccion1();
            if (idetapaproduccion1 != null) {
                idetapaproduccion1 = em.getReference(idetapaproduccion1.getClass(), idetapaproduccion1.getIdetapaproduccion());
                ejecucionetapaproduccion.setIdetapaproduccion1(idetapaproduccion1);
            }
            Set<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionSet = new HashSet<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach : ejecucionetapaproduccion.getDetalleejecucionplanificacionSet()) {
                detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionSet.add(detalleejecucionplanificacionSetDetalleejecucionplanificacionToAttach);
            }
            ejecucionetapaproduccion.setDetalleejecucionplanificacionSet(attachedDetalleejecucionplanificacionSet);
            em.persist(ejecucionetapaproduccion);
            if (empleado != null) {
                empleado.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                empleado = em.merge(empleado);
            }
            if (empleado1 != null) {
                empleado1.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                empleado1 = em.merge(empleado1);
            }
            if (estado != null) {
                estado.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                estado = em.merge(estado);
            }
            if (estado1 != null) {
                estado1.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                estado1 = em.merge(estado1);
            }
            if (idetapaproduccion != null) {
                idetapaproduccion.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                idetapaproduccion = em.merge(idetapaproduccion);
            }
            if (idetapaproduccion1 != null) {
                idetapaproduccion1.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                idetapaproduccion1 = em.merge(idetapaproduccion1);
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetDetalleejecucionplanificacion : ejecucionetapaproduccion.getDetalleejecucionplanificacionSet()) {
                Ejecucionetapaproduccion oldEjecucionetapaOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion = detalleejecucionplanificacionSetDetalleejecucionplanificacion.getEjecucionetapa();
                detalleejecucionplanificacionSetDetalleejecucionplanificacion.setEjecucionetapa(ejecucionetapaproduccion);
                detalleejecucionplanificacionSetDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionSetDetalleejecucionplanificacion);
                if (oldEjecucionetapaOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion != null) {
                    oldEjecucionetapaOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacionSetDetalleejecucionplanificacion);
                    oldEjecucionetapaOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion = em.merge(oldEjecucionetapaOfDetalleejecucionplanificacionSetDetalleejecucionplanificacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEjecucionetapaproduccion(ejecucionetapaproduccion.getId()) != null) {
                throw new PreexistingEntityException("Ejecucionetapaproduccion " + ejecucionetapaproduccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ejecucionetapaproduccion ejecucionetapaproduccion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionetapaproduccion persistentEjecucionetapaproduccion = em.find(Ejecucionetapaproduccion.class, ejecucionetapaproduccion.getId());
            Empleado empleadoOld = persistentEjecucionetapaproduccion.getEmpleado();
            Empleado empleadoNew = ejecucionetapaproduccion.getEmpleado();
            Empleado empleado1Old = persistentEjecucionetapaproduccion.getEmpleado1();
            Empleado empleado1New = ejecucionetapaproduccion.getEmpleado1();
            Estadoejecetapaprod estadoOld = persistentEjecucionetapaproduccion.getEstado();
            Estadoejecetapaprod estadoNew = ejecucionetapaproduccion.getEstado();
            Estadoejecetapaprod estado1Old = persistentEjecucionetapaproduccion.getEstado1();
            Estadoejecetapaprod estado1New = ejecucionetapaproduccion.getEstado1();
            Etapadeproduccion idetapaproduccionOld = persistentEjecucionetapaproduccion.getIdetapaproduccion();
            Etapadeproduccion idetapaproduccionNew = ejecucionetapaproduccion.getIdetapaproduccion();
            Etapadeproduccion idetapaproduccion1Old = persistentEjecucionetapaproduccion.getIdetapaproduccion1();
            Etapadeproduccion idetapaproduccion1New = ejecucionetapaproduccion.getIdetapaproduccion1();
            Set<Detalleejecucionplanificacion> detalleejecucionplanificacionSetOld = persistentEjecucionetapaproduccion.getDetalleejecucionplanificacionSet();
            Set<Detalleejecucionplanificacion> detalleejecucionplanificacionSetNew = ejecucionetapaproduccion.getDetalleejecucionplanificacionSet();
            List<String> illegalOrphanMessages = null;
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetOldDetalleejecucionplanificacion : detalleejecucionplanificacionSetOld) {
                if (!detalleejecucionplanificacionSetNew.contains(detalleejecucionplanificacionSetOldDetalleejecucionplanificacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleejecucionplanificacion " + detalleejecucionplanificacionSetOldDetalleejecucionplanificacion + " since its ejecucionetapa field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (empleadoNew != null) {
                empleadoNew = em.getReference(empleadoNew.getClass(), empleadoNew.getIdempleado());
                ejecucionetapaproduccion.setEmpleado(empleadoNew);
            }
            if (empleado1New != null) {
                empleado1New = em.getReference(empleado1New.getClass(), empleado1New.getIdempleado());
                ejecucionetapaproduccion.setEmpleado1(empleado1New);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                ejecucionetapaproduccion.setEstado(estadoNew);
            }
            if (estado1New != null) {
                estado1New = em.getReference(estado1New.getClass(), estado1New.getIdestado());
                ejecucionetapaproduccion.setEstado1(estado1New);
            }
            if (idetapaproduccionNew != null) {
                idetapaproduccionNew = em.getReference(idetapaproduccionNew.getClass(), idetapaproduccionNew.getIdetapaproduccion());
                ejecucionetapaproduccion.setIdetapaproduccion(idetapaproduccionNew);
            }
            if (idetapaproduccion1New != null) {
                idetapaproduccion1New = em.getReference(idetapaproduccion1New.getClass(), idetapaproduccion1New.getIdetapaproduccion());
                ejecucionetapaproduccion.setIdetapaproduccion1(idetapaproduccion1New);
            }
            Set<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionSetNew = new HashSet<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach : detalleejecucionplanificacionSetNew) {
                detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionSetNew.add(detalleejecucionplanificacionSetNewDetalleejecucionplanificacionToAttach);
            }
            detalleejecucionplanificacionSetNew = attachedDetalleejecucionplanificacionSetNew;
            ejecucionetapaproduccion.setDetalleejecucionplanificacionSet(detalleejecucionplanificacionSetNew);
            ejecucionetapaproduccion = em.merge(ejecucionetapaproduccion);
            if (empleadoOld != null && !empleadoOld.equals(empleadoNew)) {
                empleadoOld.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                empleadoOld = em.merge(empleadoOld);
            }
            if (empleadoNew != null && !empleadoNew.equals(empleadoOld)) {
                empleadoNew.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                empleadoNew = em.merge(empleadoNew);
            }
            if (empleado1Old != null && !empleado1Old.equals(empleado1New)) {
                empleado1Old.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                empleado1Old = em.merge(empleado1Old);
            }
            if (empleado1New != null && !empleado1New.equals(empleado1Old)) {
                empleado1New.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                empleado1New = em.merge(empleado1New);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                estadoNew = em.merge(estadoNew);
            }
            if (estado1Old != null && !estado1Old.equals(estado1New)) {
                estado1Old.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                estado1Old = em.merge(estado1Old);
            }
            if (estado1New != null && !estado1New.equals(estado1Old)) {
                estado1New.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                estado1New = em.merge(estado1New);
            }
            if (idetapaproduccionOld != null && !idetapaproduccionOld.equals(idetapaproduccionNew)) {
                idetapaproduccionOld.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                idetapaproduccionOld = em.merge(idetapaproduccionOld);
            }
            if (idetapaproduccionNew != null && !idetapaproduccionNew.equals(idetapaproduccionOld)) {
                idetapaproduccionNew.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                idetapaproduccionNew = em.merge(idetapaproduccionNew);
            }
            if (idetapaproduccion1Old != null && !idetapaproduccion1Old.equals(idetapaproduccion1New)) {
                idetapaproduccion1Old.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                idetapaproduccion1Old = em.merge(idetapaproduccion1Old);
            }
            if (idetapaproduccion1New != null && !idetapaproduccion1New.equals(idetapaproduccion1Old)) {
                idetapaproduccion1New.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                idetapaproduccion1New = em.merge(idetapaproduccion1New);
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetNewDetalleejecucionplanificacion : detalleejecucionplanificacionSetNew) {
                if (!detalleejecucionplanificacionSetOld.contains(detalleejecucionplanificacionSetNewDetalleejecucionplanificacion)) {
                    Ejecucionetapaproduccion oldEjecucionetapaOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion = detalleejecucionplanificacionSetNewDetalleejecucionplanificacion.getEjecucionetapa();
                    detalleejecucionplanificacionSetNewDetalleejecucionplanificacion.setEjecucionetapa(ejecucionetapaproduccion);
                    detalleejecucionplanificacionSetNewDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionSetNewDetalleejecucionplanificacion);
                    if (oldEjecucionetapaOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion != null && !oldEjecucionetapaOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion.equals(ejecucionetapaproduccion)) {
                        oldEjecucionetapaOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion.getDetalleejecucionplanificacionSet().remove(detalleejecucionplanificacionSetNewDetalleejecucionplanificacion);
                        oldEjecucionetapaOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion = em.merge(oldEjecucionetapaOfDetalleejecucionplanificacionSetNewDetalleejecucionplanificacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = ejecucionetapaproduccion.getId();
                if (findEjecucionetapaproduccion(id) == null) {
                    throw new NonexistentEntityException("The ejecucionetapaproduccion with id " + id + " no longer exists.");
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
            Ejecucionetapaproduccion ejecucionetapaproduccion;
            try {
                ejecucionetapaproduccion = em.getReference(Ejecucionetapaproduccion.class, id);
                ejecucionetapaproduccion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ejecucionetapaproduccion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detalleejecucionplanificacion> detalleejecucionplanificacionSetOrphanCheck = ejecucionetapaproduccion.getDetalleejecucionplanificacionSet();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionSetOrphanCheckDetalleejecucionplanificacion : detalleejecucionplanificacionSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ejecucionetapaproduccion (" + ejecucionetapaproduccion + ") cannot be destroyed since the Detalleejecucionplanificacion " + detalleejecucionplanificacionSetOrphanCheckDetalleejecucionplanificacion + " in its detalleejecucionplanificacionSet field has a non-nullable ejecucionetapa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Empleado empleado = ejecucionetapaproduccion.getEmpleado();
            if (empleado != null) {
                empleado.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                empleado = em.merge(empleado);
            }
            Empleado empleado1 = ejecucionetapaproduccion.getEmpleado1();
            if (empleado1 != null) {
                empleado1.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                empleado1 = em.merge(empleado1);
            }
            Estadoejecetapaprod estado = ejecucionetapaproduccion.getEstado();
            if (estado != null) {
                estado.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                estado = em.merge(estado);
            }
            Estadoejecetapaprod estado1 = ejecucionetapaproduccion.getEstado1();
            if (estado1 != null) {
                estado1.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                estado1 = em.merge(estado1);
            }
            Etapadeproduccion idetapaproduccion = ejecucionetapaproduccion.getIdetapaproduccion();
            if (idetapaproduccion != null) {
                idetapaproduccion.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                idetapaproduccion = em.merge(idetapaproduccion);
            }
            Etapadeproduccion idetapaproduccion1 = ejecucionetapaproduccion.getIdetapaproduccion1();
            if (idetapaproduccion1 != null) {
                idetapaproduccion1.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                idetapaproduccion1 = em.merge(idetapaproduccion1);
            }
            em.remove(ejecucionetapaproduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ejecucionetapaproduccion> findEjecucionetapaproduccionEntities() {
        return findEjecucionetapaproduccionEntities(true, -1, -1);
    }

    public List<Ejecucionetapaproduccion> findEjecucionetapaproduccionEntities(int maxResults, int firstResult) {
        return findEjecucionetapaproduccionEntities(false, maxResults, firstResult);
    }

    private List<Ejecucionetapaproduccion> findEjecucionetapaproduccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ejecucionetapaproduccion.class));
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

    public Ejecucionetapaproduccion findEjecucionetapaproduccion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ejecucionetapaproduccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getEjecucionetapaproduccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ejecucionetapaproduccion> rt = cq.from(Ejecucionetapaproduccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
