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
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Ejecucionetapaproduccion;
import metalsoft.datos.jpa.entity.Etapadeproduccion;
import metalsoft.datos.jpa.entity.Estadoejecetapaprod;
import metalsoft.datos.jpa.entity.Empleado;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class EjecucionetapaproduccionJpaController implements Serializable {

    public EjecucionetapaproduccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ejecucionetapaproduccion ejecucionetapaproduccion) throws PreexistingEntityException, Exception {
        if (ejecucionetapaproduccion.getDetalleejecucionplanificacionList() == null) {
            ejecucionetapaproduccion.setDetalleejecucionplanificacionList(new ArrayList<Detalleejecucionplanificacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Etapadeproduccion idetapaproduccion = ejecucionetapaproduccion.getIdetapaproduccion();
            if (idetapaproduccion != null) {
                idetapaproduccion = em.getReference(idetapaproduccion.getClass(), idetapaproduccion.getIdetapaproduccion());
                ejecucionetapaproduccion.setIdetapaproduccion(idetapaproduccion);
            }
            Estadoejecetapaprod estado = ejecucionetapaproduccion.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                ejecucionetapaproduccion.setEstado(estado);
            }
            Empleado empleado = ejecucionetapaproduccion.getEmpleado();
            if (empleado != null) {
                empleado = em.getReference(empleado.getClass(), empleado.getIdempleado());
                ejecucionetapaproduccion.setEmpleado(empleado);
            }
            List<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionList = new ArrayList<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach : ejecucionetapaproduccion.getDetalleejecucionplanificacionList()) {
                detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionList.add(detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach);
            }
            ejecucionetapaproduccion.setDetalleejecucionplanificacionList(attachedDetalleejecucionplanificacionList);
            em.persist(ejecucionetapaproduccion);
            if (idetapaproduccion != null) {
                idetapaproduccion.getEjecucionetapaproduccionList().add(ejecucionetapaproduccion);
                idetapaproduccion = em.merge(idetapaproduccion);
            }
            if (estado != null) {
                estado.getEjecucionetapaproduccionList().add(ejecucionetapaproduccion);
                estado = em.merge(estado);
            }
            if (empleado != null) {
                empleado.getEjecucionetapaproduccionList().add(ejecucionetapaproduccion);
                empleado = em.merge(empleado);
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListDetalleejecucionplanificacion : ejecucionetapaproduccion.getDetalleejecucionplanificacionList()) {
                Ejecucionetapaproduccion oldEjecucionetapaOfDetalleejecucionplanificacionListDetalleejecucionplanificacion = detalleejecucionplanificacionListDetalleejecucionplanificacion.getEjecucionetapa();
                detalleejecucionplanificacionListDetalleejecucionplanificacion.setEjecucionetapa(ejecucionetapaproduccion);
                detalleejecucionplanificacionListDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionListDetalleejecucionplanificacion);
                if (oldEjecucionetapaOfDetalleejecucionplanificacionListDetalleejecucionplanificacion != null) {
                    oldEjecucionetapaOfDetalleejecucionplanificacionListDetalleejecucionplanificacion.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacionListDetalleejecucionplanificacion);
                    oldEjecucionetapaOfDetalleejecucionplanificacionListDetalleejecucionplanificacion = em.merge(oldEjecucionetapaOfDetalleejecucionplanificacionListDetalleejecucionplanificacion);
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
            Etapadeproduccion idetapaproduccionOld = persistentEjecucionetapaproduccion.getIdetapaproduccion();
            Etapadeproduccion idetapaproduccionNew = ejecucionetapaproduccion.getIdetapaproduccion();
            Estadoejecetapaprod estadoOld = persistentEjecucionetapaproduccion.getEstado();
            Estadoejecetapaprod estadoNew = ejecucionetapaproduccion.getEstado();
            Empleado empleadoOld = persistentEjecucionetapaproduccion.getEmpleado();
            Empleado empleadoNew = ejecucionetapaproduccion.getEmpleado();
            List<Detalleejecucionplanificacion> detalleejecucionplanificacionListOld = persistentEjecucionetapaproduccion.getDetalleejecucionplanificacionList();
            List<Detalleejecucionplanificacion> detalleejecucionplanificacionListNew = ejecucionetapaproduccion.getDetalleejecucionplanificacionList();
            List<String> illegalOrphanMessages = null;
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListOldDetalleejecucionplanificacion : detalleejecucionplanificacionListOld) {
                if (!detalleejecucionplanificacionListNew.contains(detalleejecucionplanificacionListOldDetalleejecucionplanificacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleejecucionplanificacion " + detalleejecucionplanificacionListOldDetalleejecucionplanificacion + " since its ejecucionetapa field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idetapaproduccionNew != null) {
                idetapaproduccionNew = em.getReference(idetapaproduccionNew.getClass(), idetapaproduccionNew.getIdetapaproduccion());
                ejecucionetapaproduccion.setIdetapaproduccion(idetapaproduccionNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                ejecucionetapaproduccion.setEstado(estadoNew);
            }
            if (empleadoNew != null) {
                empleadoNew = em.getReference(empleadoNew.getClass(), empleadoNew.getIdempleado());
                ejecucionetapaproduccion.setEmpleado(empleadoNew);
            }
            List<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionListNew = new ArrayList<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach : detalleejecucionplanificacionListNew) {
                detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionListNew.add(detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach);
            }
            detalleejecucionplanificacionListNew = attachedDetalleejecucionplanificacionListNew;
            ejecucionetapaproduccion.setDetalleejecucionplanificacionList(detalleejecucionplanificacionListNew);
            ejecucionetapaproduccion = em.merge(ejecucionetapaproduccion);
            if (idetapaproduccionOld != null && !idetapaproduccionOld.equals(idetapaproduccionNew)) {
                idetapaproduccionOld.getEjecucionetapaproduccionList().remove(ejecucionetapaproduccion);
                idetapaproduccionOld = em.merge(idetapaproduccionOld);
            }
            if (idetapaproduccionNew != null && !idetapaproduccionNew.equals(idetapaproduccionOld)) {
                idetapaproduccionNew.getEjecucionetapaproduccionList().add(ejecucionetapaproduccion);
                idetapaproduccionNew = em.merge(idetapaproduccionNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getEjecucionetapaproduccionList().remove(ejecucionetapaproduccion);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getEjecucionetapaproduccionList().add(ejecucionetapaproduccion);
                estadoNew = em.merge(estadoNew);
            }
            if (empleadoOld != null && !empleadoOld.equals(empleadoNew)) {
                empleadoOld.getEjecucionetapaproduccionList().remove(ejecucionetapaproduccion);
                empleadoOld = em.merge(empleadoOld);
            }
            if (empleadoNew != null && !empleadoNew.equals(empleadoOld)) {
                empleadoNew.getEjecucionetapaproduccionList().add(ejecucionetapaproduccion);
                empleadoNew = em.merge(empleadoNew);
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListNewDetalleejecucionplanificacion : detalleejecucionplanificacionListNew) {
                if (!detalleejecucionplanificacionListOld.contains(detalleejecucionplanificacionListNewDetalleejecucionplanificacion)) {
                    Ejecucionetapaproduccion oldEjecucionetapaOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion = detalleejecucionplanificacionListNewDetalleejecucionplanificacion.getEjecucionetapa();
                    detalleejecucionplanificacionListNewDetalleejecucionplanificacion.setEjecucionetapa(ejecucionetapaproduccion);
                    detalleejecucionplanificacionListNewDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionListNewDetalleejecucionplanificacion);
                    if (oldEjecucionetapaOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion != null && !oldEjecucionetapaOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion.equals(ejecucionetapaproduccion)) {
                        oldEjecucionetapaOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacionListNewDetalleejecucionplanificacion);
                        oldEjecucionetapaOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion = em.merge(oldEjecucionetapaOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion);
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
            List<Detalleejecucionplanificacion> detalleejecucionplanificacionListOrphanCheck = ejecucionetapaproduccion.getDetalleejecucionplanificacionList();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListOrphanCheckDetalleejecucionplanificacion : detalleejecucionplanificacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ejecucionetapaproduccion (" + ejecucionetapaproduccion + ") cannot be destroyed since the Detalleejecucionplanificacion " + detalleejecucionplanificacionListOrphanCheckDetalleejecucionplanificacion + " in its detalleejecucionplanificacionList field has a non-nullable ejecucionetapa field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Etapadeproduccion idetapaproduccion = ejecucionetapaproduccion.getIdetapaproduccion();
            if (idetapaproduccion != null) {
                idetapaproduccion.getEjecucionetapaproduccionList().remove(ejecucionetapaproduccion);
                idetapaproduccion = em.merge(idetapaproduccion);
            }
            Estadoejecetapaprod estado = ejecucionetapaproduccion.getEstado();
            if (estado != null) {
                estado.getEjecucionetapaproduccionList().remove(ejecucionetapaproduccion);
                estado = em.merge(estado);
            }
            Empleado empleado = ejecucionetapaproduccion.getEmpleado();
            if (empleado != null) {
                empleado.getEjecucionetapaproduccionList().remove(ejecucionetapaproduccion);
                empleado = em.merge(empleado);
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
