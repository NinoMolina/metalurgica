/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Planificacionproduccion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Estadoplanificacionproduccion;
import entity.Pedido;
import entity.Detalleplanificacionproduccion;
import java.util.HashSet;
import java.util.Set;
import entity.Detallempasignada;
import entity.Ejecucionplanificacionproduccion;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class PlanificacionproduccionJpaController1 {

    public PlanificacionproduccionJpaController1() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planificacionproduccion planificacionproduccion) throws PreexistingEntityException, Exception {
        if (planificacionproduccion.getDetalleplanificacionproduccionSet() == null) {
            planificacionproduccion.setDetalleplanificacionproduccionSet(new HashSet<Detalleplanificacionproduccion>());
        }
        if (planificacionproduccion.getDetallempasignadaSet() == null) {
            planificacionproduccion.setDetallempasignadaSet(new HashSet<Detallempasignada>());
        }
        if (planificacionproduccion.getEjecucionplanificacionproduccionSet() == null) {
            planificacionproduccion.setEjecucionplanificacionproduccionSet(new HashSet<Ejecucionplanificacionproduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estadoplanificacionproduccion idestado = planificacionproduccion.getIdestado();
            if (idestado != null) {
                idestado = em.getReference(idestado.getClass(), idestado.getId());
                planificacionproduccion.setIdestado(idestado);
            }
            Pedido pedido = planificacionproduccion.getPedido();
            if (pedido != null) {
                pedido = em.getReference(pedido.getClass(), pedido.getIdpedido());
                planificacionproduccion.setPedido(pedido);
            }
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach : planificacionproduccion.getDetalleplanificacionproduccionSet()) {
                detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet.add(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach);
            }
            planificacionproduccion.setDetalleplanificacionproduccionSet(attachedDetalleplanificacionproduccionSet);
            Set<Detallempasignada> attachedDetallempasignadaSet = new HashSet<Detallempasignada>();
            for (Detallempasignada detallempasignadaSetDetallempasignadaToAttach : planificacionproduccion.getDetallempasignadaSet()) {
                detallempasignadaSetDetallempasignadaToAttach = em.getReference(detallempasignadaSetDetallempasignadaToAttach.getClass(), detallempasignadaSetDetallempasignadaToAttach.getId());
                attachedDetallempasignadaSet.add(detallempasignadaSetDetallempasignadaToAttach);
            }
            planificacionproduccion.setDetallempasignadaSet(attachedDetallempasignadaSet);
            Set<Ejecucionplanificacionproduccion> attachedEjecucionplanificacionproduccionSet = new HashSet<Ejecucionplanificacionproduccion>();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSetEjecucionplanificacionproduccionToAttach : planificacionproduccion.getEjecucionplanificacionproduccionSet()) {
                ejecucionplanificacionproduccionSetEjecucionplanificacionproduccionToAttach = em.getReference(ejecucionplanificacionproduccionSetEjecucionplanificacionproduccionToAttach.getClass(), ejecucionplanificacionproduccionSetEjecucionplanificacionproduccionToAttach.getIdejecucion());
                attachedEjecucionplanificacionproduccionSet.add(ejecucionplanificacionproduccionSetEjecucionplanificacionproduccionToAttach);
            }
            planificacionproduccion.setEjecucionplanificacionproduccionSet(attachedEjecucionplanificacionproduccionSet);
            em.persist(planificacionproduccion);
            if (idestado != null) {
                idestado.getPlanificacionproduccionSet().add(planificacionproduccion);
                idestado = em.merge(idestado);
            }
            if (pedido != null) {
                pedido.getPlanificacionproduccionSet().add(planificacionproduccion);
                pedido = em.merge(pedido);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccion : planificacionproduccion.getDetalleplanificacionproduccionSet()) {
                Planificacionproduccion oldIdplanificacionproduccionOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion = detalleplanificacionproduccionSetDetalleplanificacionproduccion.getIdplanificacionproduccion();
                detalleplanificacionproduccionSetDetalleplanificacionproduccion.setIdplanificacionproduccion(planificacionproduccion);
                detalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
                if (oldIdplanificacionproduccionOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion != null) {
                    oldIdplanificacionproduccionOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccionSetDetalleplanificacionproduccion);
                    oldIdplanificacionproduccionOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion = em.merge(oldIdplanificacionproduccionOfDetalleplanificacionproduccionSetDetalleplanificacionproduccion);
                }
            }
            for (Detallempasignada detallempasignadaSetDetallempasignada : planificacionproduccion.getDetallempasignadaSet()) {
                Planificacionproduccion oldIdplanificacionproduccionOfDetallempasignadaSetDetallempasignada = detallempasignadaSetDetallempasignada.getIdplanificacionproduccion();
                detallempasignadaSetDetallempasignada.setIdplanificacionproduccion(planificacionproduccion);
                detallempasignadaSetDetallempasignada = em.merge(detallempasignadaSetDetallempasignada);
                if (oldIdplanificacionproduccionOfDetallempasignadaSetDetallempasignada != null) {
                    oldIdplanificacionproduccionOfDetallempasignadaSetDetallempasignada.getDetallempasignadaSet().remove(detallempasignadaSetDetallempasignada);
                    oldIdplanificacionproduccionOfDetallempasignadaSetDetallempasignada = em.merge(oldIdplanificacionproduccionOfDetallempasignadaSetDetallempasignada);
                }
            }
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSetEjecucionplanificacionproduccion : planificacionproduccion.getEjecucionplanificacionproduccionSet()) {
                Planificacionproduccion oldIdplanificacionproduccionOfEjecucionplanificacionproduccionSetEjecucionplanificacionproduccion = ejecucionplanificacionproduccionSetEjecucionplanificacionproduccion.getIdplanificacionproduccion();
                ejecucionplanificacionproduccionSetEjecucionplanificacionproduccion.setIdplanificacionproduccion(planificacionproduccion);
                ejecucionplanificacionproduccionSetEjecucionplanificacionproduccion = em.merge(ejecucionplanificacionproduccionSetEjecucionplanificacionproduccion);
                if (oldIdplanificacionproduccionOfEjecucionplanificacionproduccionSetEjecucionplanificacionproduccion != null) {
                    oldIdplanificacionproduccionOfEjecucionplanificacionproduccionSetEjecucionplanificacionproduccion.getEjecucionplanificacionproduccionSet().remove(ejecucionplanificacionproduccionSetEjecucionplanificacionproduccion);
                    oldIdplanificacionproduccionOfEjecucionplanificacionproduccionSetEjecucionplanificacionproduccion = em.merge(oldIdplanificacionproduccionOfEjecucionplanificacionproduccionSetEjecucionplanificacionproduccion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPlanificacionproduccion(planificacionproduccion.getIdplanificacionproduccion()) != null) {
                throw new PreexistingEntityException("Planificacionproduccion " + planificacionproduccion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Planificacionproduccion planificacionproduccion) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planificacionproduccion persistentPlanificacionproduccion = em.find(Planificacionproduccion.class, planificacionproduccion.getIdplanificacionproduccion());
            Estadoplanificacionproduccion idestadoOld = persistentPlanificacionproduccion.getIdestado();
            Estadoplanificacionproduccion idestadoNew = planificacionproduccion.getIdestado();
            Pedido pedidoOld = persistentPlanificacionproduccion.getPedido();
            Pedido pedidoNew = planificacionproduccion.getPedido();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetOld = persistentPlanificacionproduccion.getDetalleplanificacionproduccionSet();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetNew = planificacionproduccion.getDetalleplanificacionproduccionSet();
            Set<Detallempasignada> detallempasignadaSetOld = persistentPlanificacionproduccion.getDetallempasignadaSet();
            Set<Detallempasignada> detallempasignadaSetNew = planificacionproduccion.getDetallempasignadaSet();
            Set<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionSetOld = persistentPlanificacionproduccion.getEjecucionplanificacionproduccionSet();
            Set<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionSetNew = planificacionproduccion.getEjecucionplanificacionproduccionSet();
            List<String> illegalOrphanMessages = null;
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetOldDetalleplanificacionproduccion : detalleplanificacionproduccionSetOld) {
                if (!detalleplanificacionproduccionSetNew.contains(detalleplanificacionproduccionSetOldDetalleplanificacionproduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleplanificacionproduccion " + detalleplanificacionproduccionSetOldDetalleplanificacionproduccion + " since its idplanificacionproduccion field is not nullable.");
                }
            }
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSetOldEjecucionplanificacionproduccion : ejecucionplanificacionproduccionSetOld) {
                if (!ejecucionplanificacionproduccionSetNew.contains(ejecucionplanificacionproduccionSetOldEjecucionplanificacionproduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ejecucionplanificacionproduccion " + ejecucionplanificacionproduccionSetOldEjecucionplanificacionproduccion + " since its idplanificacionproduccion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idestadoNew != null) {
                idestadoNew = em.getReference(idestadoNew.getClass(), idestadoNew.getId());
                planificacionproduccion.setIdestado(idestadoNew);
            }
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getIdpedido());
                planificacionproduccion.setPedido(pedidoNew);
            }
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSetNew = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionSetNew) {
                detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSetNew.add(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionSetNew = attachedDetalleplanificacionproduccionSetNew;
            planificacionproduccion.setDetalleplanificacionproduccionSet(detalleplanificacionproduccionSetNew);
            Set<Detallempasignada> attachedDetallempasignadaSetNew = new HashSet<Detallempasignada>();
            for (Detallempasignada detallempasignadaSetNewDetallempasignadaToAttach : detallempasignadaSetNew) {
                detallempasignadaSetNewDetallempasignadaToAttach = em.getReference(detallempasignadaSetNewDetallempasignadaToAttach.getClass(), detallempasignadaSetNewDetallempasignadaToAttach.getId());
                attachedDetallempasignadaSetNew.add(detallempasignadaSetNewDetallempasignadaToAttach);
            }
            detallempasignadaSetNew = attachedDetallempasignadaSetNew;
            planificacionproduccion.setDetallempasignadaSet(detallempasignadaSetNew);
            Set<Ejecucionplanificacionproduccion> attachedEjecucionplanificacionproduccionSetNew = new HashSet<Ejecucionplanificacionproduccion>();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccionToAttach : ejecucionplanificacionproduccionSetNew) {
                ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccionToAttach = em.getReference(ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccionToAttach.getClass(), ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccionToAttach.getIdejecucion());
                attachedEjecucionplanificacionproduccionSetNew.add(ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccionToAttach);
            }
            ejecucionplanificacionproduccionSetNew = attachedEjecucionplanificacionproduccionSetNew;
            planificacionproduccion.setEjecucionplanificacionproduccionSet(ejecucionplanificacionproduccionSetNew);
            planificacionproduccion = em.merge(planificacionproduccion);
            if (idestadoOld != null && !idestadoOld.equals(idestadoNew)) {
                idestadoOld.getPlanificacionproduccionSet().remove(planificacionproduccion);
                idestadoOld = em.merge(idestadoOld);
            }
            if (idestadoNew != null && !idestadoNew.equals(idestadoOld)) {
                idestadoNew.getPlanificacionproduccionSet().add(planificacionproduccion);
                idestadoNew = em.merge(idestadoNew);
            }
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getPlanificacionproduccionSet().remove(planificacionproduccion);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getPlanificacionproduccionSet().add(planificacionproduccion);
                pedidoNew = em.merge(pedidoNew);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetNewDetalleplanificacionproduccion : detalleplanificacionproduccionSetNew) {
                if (!detalleplanificacionproduccionSetOld.contains(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion)) {
                    Planificacionproduccion oldIdplanificacionproduccionOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion = detalleplanificacionproduccionSetNewDetalleplanificacionproduccion.getIdplanificacionproduccion();
                    detalleplanificacionproduccionSetNewDetalleplanificacionproduccion.setIdplanificacionproduccion(planificacionproduccion);
                    detalleplanificacionproduccionSetNewDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                    if (oldIdplanificacionproduccionOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion != null && !oldIdplanificacionproduccionOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion.equals(planificacionproduccion)) {
                        oldIdplanificacionproduccionOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet().remove(detalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                        oldIdplanificacionproduccionOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion = em.merge(oldIdplanificacionproduccionOfDetalleplanificacionproduccionSetNewDetalleplanificacionproduccion);
                    }
                }
            }
            for (Detallempasignada detallempasignadaSetOldDetallempasignada : detallempasignadaSetOld) {
                if (!detallempasignadaSetNew.contains(detallempasignadaSetOldDetallempasignada)) {
                    detallempasignadaSetOldDetallempasignada.setIdplanificacionproduccion(null);
                    detallempasignadaSetOldDetallempasignada = em.merge(detallempasignadaSetOldDetallempasignada);
                }
            }
            for (Detallempasignada detallempasignadaSetNewDetallempasignada : detallempasignadaSetNew) {
                if (!detallempasignadaSetOld.contains(detallempasignadaSetNewDetallempasignada)) {
                    Planificacionproduccion oldIdplanificacionproduccionOfDetallempasignadaSetNewDetallempasignada = detallempasignadaSetNewDetallempasignada.getIdplanificacionproduccion();
                    detallempasignadaSetNewDetallempasignada.setIdplanificacionproduccion(planificacionproduccion);
                    detallempasignadaSetNewDetallempasignada = em.merge(detallempasignadaSetNewDetallempasignada);
                    if (oldIdplanificacionproduccionOfDetallempasignadaSetNewDetallempasignada != null && !oldIdplanificacionproduccionOfDetallempasignadaSetNewDetallempasignada.equals(planificacionproduccion)) {
                        oldIdplanificacionproduccionOfDetallempasignadaSetNewDetallempasignada.getDetallempasignadaSet().remove(detallempasignadaSetNewDetallempasignada);
                        oldIdplanificacionproduccionOfDetallempasignadaSetNewDetallempasignada = em.merge(oldIdplanificacionproduccionOfDetallempasignadaSetNewDetallempasignada);
                    }
                }
            }
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion : ejecucionplanificacionproduccionSetNew) {
                if (!ejecucionplanificacionproduccionSetOld.contains(ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion)) {
                    Planificacionproduccion oldIdplanificacionproduccionOfEjecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion = ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion.getIdplanificacionproduccion();
                    ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion.setIdplanificacionproduccion(planificacionproduccion);
                    ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion = em.merge(ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion);
                    if (oldIdplanificacionproduccionOfEjecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion != null && !oldIdplanificacionproduccionOfEjecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion.equals(planificacionproduccion)) {
                        oldIdplanificacionproduccionOfEjecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion.getEjecucionplanificacionproduccionSet().remove(ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion);
                        oldIdplanificacionproduccionOfEjecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion = em.merge(oldIdplanificacionproduccionOfEjecucionplanificacionproduccionSetNewEjecucionplanificacionproduccion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = planificacionproduccion.getIdplanificacionproduccion();
                if (findPlanificacionproduccion(id) == null) {
                    throw new NonexistentEntityException("The planificacionproduccion with id " + id + " no longer exists.");
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
            Planificacionproduccion planificacionproduccion;
            try {
                planificacionproduccion = em.getReference(Planificacionproduccion.class, id);
                planificacionproduccion.getIdplanificacionproduccion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planificacionproduccion with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetOrphanCheck = planificacionproduccion.getDetalleplanificacionproduccionSet();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetOrphanCheckDetalleplanificacionproduccion : detalleplanificacionproduccionSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planificacionproduccion (" + planificacionproduccion + ") cannot be destroyed since the Detalleplanificacionproduccion " + detalleplanificacionproduccionSetOrphanCheckDetalleplanificacionproduccion + " in its detalleplanificacionproduccionSet field has a non-nullable idplanificacionproduccion field.");
            }
            Set<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionSetOrphanCheck = planificacionproduccion.getEjecucionplanificacionproduccionSet();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSetOrphanCheckEjecucionplanificacionproduccion : ejecucionplanificacionproduccionSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planificacionproduccion (" + planificacionproduccion + ") cannot be destroyed since the Ejecucionplanificacionproduccion " + ejecucionplanificacionproduccionSetOrphanCheckEjecucionplanificacionproduccion + " in its ejecucionplanificacionproduccionSet field has a non-nullable idplanificacionproduccion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Estadoplanificacionproduccion idestado = planificacionproduccion.getIdestado();
            if (idestado != null) {
                idestado.getPlanificacionproduccionSet().remove(planificacionproduccion);
                idestado = em.merge(idestado);
            }
            Pedido pedido = planificacionproduccion.getPedido();
            if (pedido != null) {
                pedido.getPlanificacionproduccionSet().remove(planificacionproduccion);
                pedido = em.merge(pedido);
            }
            Set<Detallempasignada> detallempasignadaSet = planificacionproduccion.getDetallempasignadaSet();
            for (Detallempasignada detallempasignadaSetDetallempasignada : detallempasignadaSet) {
                detallempasignadaSetDetallempasignada.setIdplanificacionproduccion(null);
                detallempasignadaSetDetallempasignada = em.merge(detallempasignadaSetDetallempasignada);
            }
            em.remove(planificacionproduccion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Planificacionproduccion> findPlanificacionproduccionEntities() {
        return findPlanificacionproduccionEntities(true, -1, -1);
    }

    public List<Planificacionproduccion> findPlanificacionproduccionEntities(int maxResults, int firstResult) {
        return findPlanificacionproduccionEntities(false, maxResults, firstResult);
    }

    private List<Planificacionproduccion> findPlanificacionproduccionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Planificacionproduccion.class));
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

    public Planificacionproduccion findPlanificacionproduccion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Planificacionproduccion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanificacionproduccionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Planificacionproduccion> rt = cq.from(Planificacionproduccion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
