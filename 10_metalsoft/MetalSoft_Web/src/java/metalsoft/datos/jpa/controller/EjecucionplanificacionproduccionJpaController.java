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
import metalsoft.datos.jpa.entity.Ejecucionplanificacionproduccion;
import metalsoft.datos.jpa.entity.Planificacionproduccion;
import metalsoft.datos.jpa.entity.Estadoejecplanifpedido;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nino
 */
public class EjecucionplanificacionproduccionJpaController implements Serializable {

    public EjecucionplanificacionproduccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ejecucionplanificacionproduccion ejecucionplanificacionproduccion) throws PreexistingEntityException, Exception {
        if (ejecucionplanificacionproduccion.getDetalleejecucionplanificacionList() == null) {
            ejecucionplanificacionproduccion.setDetalleejecucionplanificacionList(new ArrayList<Detalleejecucionplanificacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planificacionproduccion idplanificacionproduccion = ejecucionplanificacionproduccion.getIdplanificacionproduccion();
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion = em.getReference(idplanificacionproduccion.getClass(), idplanificacionproduccion.getIdplanificacionproduccion());
                ejecucionplanificacionproduccion.setIdplanificacionproduccion(idplanificacionproduccion);
            }
            Estadoejecplanifpedido estado = ejecucionplanificacionproduccion.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                ejecucionplanificacionproduccion.setEstado(estado);
            }
            List<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionList = new ArrayList<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach : ejecucionplanificacionproduccion.getDetalleejecucionplanificacionList()) {
                detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionList.add(detalleejecucionplanificacionListDetalleejecucionplanificacionToAttach);
            }
            ejecucionplanificacionproduccion.setDetalleejecucionplanificacionList(attachedDetalleejecucionplanificacionList);
            em.persist(ejecucionplanificacionproduccion);
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion.getEjecucionplanificacionproduccionList().add(ejecucionplanificacionproduccion);
                idplanificacionproduccion = em.merge(idplanificacionproduccion);
            }
            if (estado != null) {
                estado.getEjecucionplanificacionproduccionList().add(ejecucionplanificacionproduccion);
                estado = em.merge(estado);
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListDetalleejecucionplanificacion : ejecucionplanificacionproduccion.getDetalleejecucionplanificacionList()) {
                Ejecucionplanificacionproduccion oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionListDetalleejecucionplanificacion = detalleejecucionplanificacionListDetalleejecucionplanificacion.getIdejecucionplanificacionproduccion();
                detalleejecucionplanificacionListDetalleejecucionplanificacion.setIdejecucionplanificacionproduccion(ejecucionplanificacionproduccion);
                detalleejecucionplanificacionListDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionListDetalleejecucionplanificacion);
                if (oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionListDetalleejecucionplanificacion != null) {
                    oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionListDetalleejecucionplanificacion.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacionListDetalleejecucionplanificacion);
                    oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionListDetalleejecucionplanificacion = em.merge(oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionListDetalleejecucionplanificacion);
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
            Planificacionproduccion idplanificacionproduccionOld = persistentEjecucionplanificacionproduccion.getIdplanificacionproduccion();
            Planificacionproduccion idplanificacionproduccionNew = ejecucionplanificacionproduccion.getIdplanificacionproduccion();
            Estadoejecplanifpedido estadoOld = persistentEjecucionplanificacionproduccion.getEstado();
            Estadoejecplanifpedido estadoNew = ejecucionplanificacionproduccion.getEstado();
            List<Detalleejecucionplanificacion> detalleejecucionplanificacionListOld = persistentEjecucionplanificacionproduccion.getDetalleejecucionplanificacionList();
            List<Detalleejecucionplanificacion> detalleejecucionplanificacionListNew = ejecucionplanificacionproduccion.getDetalleejecucionplanificacionList();
            List<String> illegalOrphanMessages = null;
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListOldDetalleejecucionplanificacion : detalleejecucionplanificacionListOld) {
                if (!detalleejecucionplanificacionListNew.contains(detalleejecucionplanificacionListOldDetalleejecucionplanificacion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleejecucionplanificacion " + detalleejecucionplanificacionListOldDetalleejecucionplanificacion + " since its idejecucionplanificacionproduccion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idplanificacionproduccionNew != null) {
                idplanificacionproduccionNew = em.getReference(idplanificacionproduccionNew.getClass(), idplanificacionproduccionNew.getIdplanificacionproduccion());
                ejecucionplanificacionproduccion.setIdplanificacionproduccion(idplanificacionproduccionNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                ejecucionplanificacionproduccion.setEstado(estadoNew);
            }
            List<Detalleejecucionplanificacion> attachedDetalleejecucionplanificacionListNew = new ArrayList<Detalleejecucionplanificacion>();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach : detalleejecucionplanificacionListNew) {
                detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach = em.getReference(detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach.getClass(), detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach.getId());
                attachedDetalleejecucionplanificacionListNew.add(detalleejecucionplanificacionListNewDetalleejecucionplanificacionToAttach);
            }
            detalleejecucionplanificacionListNew = attachedDetalleejecucionplanificacionListNew;
            ejecucionplanificacionproduccion.setDetalleejecucionplanificacionList(detalleejecucionplanificacionListNew);
            ejecucionplanificacionproduccion = em.merge(ejecucionplanificacionproduccion);
            if (idplanificacionproduccionOld != null && !idplanificacionproduccionOld.equals(idplanificacionproduccionNew)) {
                idplanificacionproduccionOld.getEjecucionplanificacionproduccionList().remove(ejecucionplanificacionproduccion);
                idplanificacionproduccionOld = em.merge(idplanificacionproduccionOld);
            }
            if (idplanificacionproduccionNew != null && !idplanificacionproduccionNew.equals(idplanificacionproduccionOld)) {
                idplanificacionproduccionNew.getEjecucionplanificacionproduccionList().add(ejecucionplanificacionproduccion);
                idplanificacionproduccionNew = em.merge(idplanificacionproduccionNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getEjecucionplanificacionproduccionList().remove(ejecucionplanificacionproduccion);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getEjecucionplanificacionproduccionList().add(ejecucionplanificacionproduccion);
                estadoNew = em.merge(estadoNew);
            }
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListNewDetalleejecucionplanificacion : detalleejecucionplanificacionListNew) {
                if (!detalleejecucionplanificacionListOld.contains(detalleejecucionplanificacionListNewDetalleejecucionplanificacion)) {
                    Ejecucionplanificacionproduccion oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion = detalleejecucionplanificacionListNewDetalleejecucionplanificacion.getIdejecucionplanificacionproduccion();
                    detalleejecucionplanificacionListNewDetalleejecucionplanificacion.setIdejecucionplanificacionproduccion(ejecucionplanificacionproduccion);
                    detalleejecucionplanificacionListNewDetalleejecucionplanificacion = em.merge(detalleejecucionplanificacionListNewDetalleejecucionplanificacion);
                    if (oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion != null && !oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion.equals(ejecucionplanificacionproduccion)) {
                        oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion.getDetalleejecucionplanificacionList().remove(detalleejecucionplanificacionListNewDetalleejecucionplanificacion);
                        oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion = em.merge(oldIdejecucionplanificacionproduccionOfDetalleejecucionplanificacionListNewDetalleejecucionplanificacion);
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
            List<Detalleejecucionplanificacion> detalleejecucionplanificacionListOrphanCheck = ejecucionplanificacionproduccion.getDetalleejecucionplanificacionList();
            for (Detalleejecucionplanificacion detalleejecucionplanificacionListOrphanCheckDetalleejecucionplanificacion : detalleejecucionplanificacionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ejecucionplanificacionproduccion (" + ejecucionplanificacionproduccion + ") cannot be destroyed since the Detalleejecucionplanificacion " + detalleejecucionplanificacionListOrphanCheckDetalleejecucionplanificacion + " in its detalleejecucionplanificacionList field has a non-nullable idejecucionplanificacionproduccion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Planificacionproduccion idplanificacionproduccion = ejecucionplanificacionproduccion.getIdplanificacionproduccion();
            if (idplanificacionproduccion != null) {
                idplanificacionproduccion.getEjecucionplanificacionproduccionList().remove(ejecucionplanificacionproduccion);
                idplanificacionproduccion = em.merge(idplanificacionproduccion);
            }
            Estadoejecplanifpedido estado = ejecucionplanificacionproduccion.getEstado();
            if (estado != null) {
                estado.getEjecucionplanificacionproduccionList().remove(ejecucionplanificacionproduccion);
                estado = em.merge(estado);
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
