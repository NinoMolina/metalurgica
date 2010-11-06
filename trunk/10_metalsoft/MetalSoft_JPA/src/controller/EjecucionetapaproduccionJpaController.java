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
import java.util.ArrayList;
import java.util.HashSet;

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

    public void create(Ejecucionetapaproduccion ejecucionetapaproduccion) throws IllegalOrphanException, PreexistingEntityException, Exception {
//        List<String> illegalOrphanMessages = null;
//        Etapadeproduccion idetapaproduccionOrphanCheck = ejecucionetapaproduccion.getIdetapaproduccion();
//        if (idetapaproduccionOrphanCheck != null) {
//            Ejecucionetapaproduccion oldEjecucionetapaproduccionOfIdetapaproduccion = idetapaproduccionOrphanCheck.getEjecucionetapaproduccionSet();
//            if (oldEjecucionetapaproduccionOfIdetapaproduccion != null) {
//                if (illegalOrphanMessages == null) {
//                    illegalOrphanMessages = new ArrayList<String>();
//                }
//                illegalOrphanMessages.add("The Etapadeproduccion " + idetapaproduccionOrphanCheck + " already has an item of type Ejecucionetapaproduccion whose idetapaproduccion column cannot be null. Please make another selection for the idetapaproduccion field.");
//            }
//        }
//        if (illegalOrphanMessages != null) {
//            throw new IllegalOrphanException(illegalOrphanMessages);
//        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado = ejecucionetapaproduccion.getEmpleado();
            if (empleado != null) {
                empleado = em.getReference(empleado.getClass(), empleado.getIdempleado());
                ejecucionetapaproduccion.setEmpleado(empleado);
            }
            Estadoejecetapaprod estado = ejecucionetapaproduccion.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                ejecucionetapaproduccion.setEstado(estado);
            }
            Etapadeproduccion idetapaproduccion = ejecucionetapaproduccion.getIdetapaproduccion();
            if (idetapaproduccion != null) {
                idetapaproduccion = em.getReference(idetapaproduccion.getClass(), idetapaproduccion.getIdetapaproduccion());
                ejecucionetapaproduccion.setIdetapaproduccion(idetapaproduccion);
            }
            em.persist(ejecucionetapaproduccion);
            if (empleado != null) {
                empleado.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                empleado = em.merge(empleado);
            }
            if (estado != null) {
                estado.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                estado = em.merge(estado);
            }
            if (idetapaproduccion != null) {
                idetapaproduccion.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                idetapaproduccion = em.merge(idetapaproduccion);
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
            Estadoejecetapaprod estadoOld = persistentEjecucionetapaproduccion.getEstado();
            Estadoejecetapaprod estadoNew = ejecucionetapaproduccion.getEstado();
            Etapadeproduccion idetapaproduccionOld = persistentEjecucionetapaproduccion.getIdetapaproduccion();
            Etapadeproduccion idetapaproduccionNew = ejecucionetapaproduccion.getIdetapaproduccion();
//            List<String> illegalOrphanMessages = null;
//            if (idetapaproduccionNew != null && !idetapaproduccionNew.equals(idetapaproduccionOld)) {
//                Ejecucionetapaproduccion oldEjecucionetapaproduccionOfIdetapaproduccion = idetapaproduccionNew.getEjecucionetapaproduccion();
//                if (oldEjecucionetapaproduccionOfIdetapaproduccion != null) {
//                    if (illegalOrphanMessages == null) {
//                        illegalOrphanMessages = new ArrayList<String>();
//                    }
//                    illegalOrphanMessages.add("The Etapadeproduccion " + idetapaproduccionNew + " already has an item of type Ejecucionetapaproduccion whose idetapaproduccion column cannot be null. Please make another selection for the idetapaproduccion field.");
//                }
//            }
//            if (illegalOrphanMessages != null) {
//                throw new IllegalOrphanException(illegalOrphanMessages);
//            }
            if (empleadoNew != null) {
                empleadoNew = em.getReference(empleadoNew.getClass(), empleadoNew.getIdempleado());
                ejecucionetapaproduccion.setEmpleado(empleadoNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                ejecucionetapaproduccion.setEstado(estadoNew);
            }
            if (idetapaproduccionNew != null) {
                idetapaproduccionNew = em.getReference(idetapaproduccionNew.getClass(), idetapaproduccionNew.getIdetapaproduccion());
                ejecucionetapaproduccion.setIdetapaproduccion(idetapaproduccionNew);
            }
            ejecucionetapaproduccion = em.merge(ejecucionetapaproduccion);
            if (empleadoOld != null && !empleadoOld.equals(empleadoNew)) {
                empleadoOld.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                empleadoOld = em.merge(empleadoOld);
            }
            if (empleadoNew != null && !empleadoNew.equals(empleadoOld)) {
                empleadoNew.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                empleadoNew = em.merge(empleadoNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                estadoNew = em.merge(estadoNew);
            }
            if (idetapaproduccionOld != null && !idetapaproduccionOld.equals(idetapaproduccionNew)) {
                idetapaproduccionOld.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                idetapaproduccionOld = em.merge(idetapaproduccionOld);
            }
            if (idetapaproduccionNew != null && !idetapaproduccionNew.equals(idetapaproduccionOld)) {
                idetapaproduccionNew.getEjecucionetapaproduccionSet().add(ejecucionetapaproduccion);
                idetapaproduccionNew = em.merge(idetapaproduccionNew);
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

    public void destroy(Long id) throws NonexistentEntityException {
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
            Empleado empleado = ejecucionetapaproduccion.getEmpleado();
            if (empleado != null) {
                empleado.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                empleado = em.merge(empleado);
            }
            Estadoejecetapaprod estado = ejecucionetapaproduccion.getEstado();
            if (estado != null) {
                estado.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                estado = em.merge(estado);
            }
            Etapadeproduccion idetapaproduccion = ejecucionetapaproduccion.getIdetapaproduccion();
            if (idetapaproduccion != null) {
                idetapaproduccion.getEjecucionetapaproduccionSet().remove(ejecucionetapaproduccion);
                idetapaproduccion = em.merge(idetapaproduccion);
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
