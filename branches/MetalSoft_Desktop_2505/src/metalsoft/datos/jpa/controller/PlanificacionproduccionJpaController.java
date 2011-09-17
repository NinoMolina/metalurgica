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
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.datos.jpa.entity.Estadoplanificacionproduccion;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Detallempasignada;
import metalsoft.datos.jpa.entity.Ejecucionplanificacionproduccion;
import metalsoft.datos.jpa.entity.Planificacionproduccion;

/**
 *
 * @author Nino
 */
public class PlanificacionproduccionJpaController implements Serializable {

    public PlanificacionproduccionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planificacionproduccion planificacionproduccion) throws PreexistingEntityException, Exception {
        if (planificacionproduccion.getDetalleplanificacionproduccionList() == null) {
            planificacionproduccion.setDetalleplanificacionproduccionList(new ArrayList<Detalleplanificacionproduccion>());
        }
        if (planificacionproduccion.getDetallempasignadaList() == null) {
            planificacionproduccion.setDetallempasignadaList(new ArrayList<Detallempasignada>());
        }
        if (planificacionproduccion.getEjecucionplanificacionproduccionList() == null) {
            planificacionproduccion.setEjecucionplanificacionproduccionList(new ArrayList<Ejecucionplanificacionproduccion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido pedido = planificacionproduccion.getPedido();
            if (pedido != null) {
                pedido = em.getReference(pedido.getClass(), pedido.getIdpedido());
                planificacionproduccion.setPedido(pedido);
            }
            Estadoplanificacionproduccion idestado = planificacionproduccion.getIdestado();
            if (idestado != null) {
                idestado = em.getReference(idestado.getClass(), idestado.getId());
                planificacionproduccion.setIdestado(idestado);
            }
            List<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionList = new ArrayList<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach : planificacionproduccion.getDetalleplanificacionproduccionList()) {
                detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionList.add(detalleplanificacionproduccionListDetalleplanificacionproduccionToAttach);
            }
            planificacionproduccion.setDetalleplanificacionproduccionList(attachedDetalleplanificacionproduccionList);
            List<Detallempasignada> attachedDetallempasignadaList = new ArrayList<Detallempasignada>();
            for (Detallempasignada detallempasignadaListDetallempasignadaToAttach : planificacionproduccion.getDetallempasignadaList()) {
                detallempasignadaListDetallempasignadaToAttach = em.getReference(detallempasignadaListDetallempasignadaToAttach.getClass(), detallempasignadaListDetallempasignadaToAttach.getId());
                attachedDetallempasignadaList.add(detallempasignadaListDetallempasignadaToAttach);
            }
            planificacionproduccion.setDetallempasignadaList(attachedDetallempasignadaList);
            List<Ejecucionplanificacionproduccion> attachedEjecucionplanificacionproduccionList = new ArrayList<Ejecucionplanificacionproduccion>();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionListEjecucionplanificacionproduccionToAttach : planificacionproduccion.getEjecucionplanificacionproduccionList()) {
                ejecucionplanificacionproduccionListEjecucionplanificacionproduccionToAttach = em.getReference(ejecucionplanificacionproduccionListEjecucionplanificacionproduccionToAttach.getClass(), ejecucionplanificacionproduccionListEjecucionplanificacionproduccionToAttach.getIdejecucion());
                attachedEjecucionplanificacionproduccionList.add(ejecucionplanificacionproduccionListEjecucionplanificacionproduccionToAttach);
            }
            planificacionproduccion.setEjecucionplanificacionproduccionList(attachedEjecucionplanificacionproduccionList);
            em.persist(planificacionproduccion);
            if (pedido != null) {
                pedido.getPlanificacionproduccionList().add(planificacionproduccion);
                pedido = em.merge(pedido);
            }
            if (idestado != null) {
                idestado.getPlanificacionproduccionList().add(planificacionproduccion);
                idestado = em.merge(idestado);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListDetalleplanificacionproduccion : planificacionproduccion.getDetalleplanificacionproduccionList()) {
                Planificacionproduccion oldIdplanificacionproduccionOfDetalleplanificacionproduccionListDetalleplanificacionproduccion = detalleplanificacionproduccionListDetalleplanificacionproduccion.getIdplanificacionproduccion();
                detalleplanificacionproduccionListDetalleplanificacionproduccion.setIdplanificacionproduccion(planificacionproduccion);
                detalleplanificacionproduccionListDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListDetalleplanificacionproduccion);
                if (oldIdplanificacionproduccionOfDetalleplanificacionproduccionListDetalleplanificacionproduccion != null) {
                    oldIdplanificacionproduccionOfDetalleplanificacionproduccionListDetalleplanificacionproduccion.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccionListDetalleplanificacionproduccion);
                    oldIdplanificacionproduccionOfDetalleplanificacionproduccionListDetalleplanificacionproduccion = em.merge(oldIdplanificacionproduccionOfDetalleplanificacionproduccionListDetalleplanificacionproduccion);
                }
            }
            for (Detallempasignada detallempasignadaListDetallempasignada : planificacionproduccion.getDetallempasignadaList()) {
                Planificacionproduccion oldIdplanificacionproduccionOfDetallempasignadaListDetallempasignada = detallempasignadaListDetallempasignada.getIdplanificacionproduccion();
                detallempasignadaListDetallempasignada.setIdplanificacionproduccion(planificacionproduccion);
                detallempasignadaListDetallempasignada = em.merge(detallempasignadaListDetallempasignada);
                if (oldIdplanificacionproduccionOfDetallempasignadaListDetallempasignada != null) {
                    oldIdplanificacionproduccionOfDetallempasignadaListDetallempasignada.getDetallempasignadaList().remove(detallempasignadaListDetallempasignada);
                    oldIdplanificacionproduccionOfDetallempasignadaListDetallempasignada = em.merge(oldIdplanificacionproduccionOfDetallempasignadaListDetallempasignada);
                }
            }
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionListEjecucionplanificacionproduccion : planificacionproduccion.getEjecucionplanificacionproduccionList()) {
                Planificacionproduccion oldIdplanificacionproduccionOfEjecucionplanificacionproduccionListEjecucionplanificacionproduccion = ejecucionplanificacionproduccionListEjecucionplanificacionproduccion.getIdplanificacionproduccion();
                ejecucionplanificacionproduccionListEjecucionplanificacionproduccion.setIdplanificacionproduccion(planificacionproduccion);
                ejecucionplanificacionproduccionListEjecucionplanificacionproduccion = em.merge(ejecucionplanificacionproduccionListEjecucionplanificacionproduccion);
                if (oldIdplanificacionproduccionOfEjecucionplanificacionproduccionListEjecucionplanificacionproduccion != null) {
                    oldIdplanificacionproduccionOfEjecucionplanificacionproduccionListEjecucionplanificacionproduccion.getEjecucionplanificacionproduccionList().remove(ejecucionplanificacionproduccionListEjecucionplanificacionproduccion);
                    oldIdplanificacionproduccionOfEjecucionplanificacionproduccionListEjecucionplanificacionproduccion = em.merge(oldIdplanificacionproduccionOfEjecucionplanificacionproduccionListEjecucionplanificacionproduccion);
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
            Pedido pedidoOld = persistentPlanificacionproduccion.getPedido();
            Pedido pedidoNew = planificacionproduccion.getPedido();
            Estadoplanificacionproduccion idestadoOld = persistentPlanificacionproduccion.getIdestado();
            Estadoplanificacionproduccion idestadoNew = planificacionproduccion.getIdestado();
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionListOld = persistentPlanificacionproduccion.getDetalleplanificacionproduccionList();
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionListNew = planificacionproduccion.getDetalleplanificacionproduccionList();
            List<Detallempasignada> detallempasignadaListOld = persistentPlanificacionproduccion.getDetallempasignadaList();
            List<Detallempasignada> detallempasignadaListNew = planificacionproduccion.getDetallempasignadaList();
            List<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionListOld = persistentPlanificacionproduccion.getEjecucionplanificacionproduccionList();
            List<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionListNew = planificacionproduccion.getEjecucionplanificacionproduccionList();
            List<String> illegalOrphanMessages = null;
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListOldDetalleplanificacionproduccion : detalleplanificacionproduccionListOld) {
                if (!detalleplanificacionproduccionListNew.contains(detalleplanificacionproduccionListOldDetalleplanificacionproduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleplanificacionproduccion " + detalleplanificacionproduccionListOldDetalleplanificacionproduccion + " since its idplanificacionproduccion field is not nullable.");
                }
            }
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionListOldEjecucionplanificacionproduccion : ejecucionplanificacionproduccionListOld) {
                if (!ejecucionplanificacionproduccionListNew.contains(ejecucionplanificacionproduccionListOldEjecucionplanificacionproduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ejecucionplanificacionproduccion " + ejecucionplanificacionproduccionListOldEjecucionplanificacionproduccion + " since its idplanificacionproduccion field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getIdpedido());
                planificacionproduccion.setPedido(pedidoNew);
            }
            if (idestadoNew != null) {
                idestadoNew = em.getReference(idestadoNew.getClass(), idestadoNew.getId());
                planificacionproduccion.setIdestado(idestadoNew);
            }
            List<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionListNew = new ArrayList<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionListNew) {
                detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionListNew.add(detalleplanificacionproduccionListNewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionListNew = attachedDetalleplanificacionproduccionListNew;
            planificacionproduccion.setDetalleplanificacionproduccionList(detalleplanificacionproduccionListNew);
            List<Detallempasignada> attachedDetallempasignadaListNew = new ArrayList<Detallempasignada>();
            for (Detallempasignada detallempasignadaListNewDetallempasignadaToAttach : detallempasignadaListNew) {
                detallempasignadaListNewDetallempasignadaToAttach = em.getReference(detallempasignadaListNewDetallempasignadaToAttach.getClass(), detallempasignadaListNewDetallempasignadaToAttach.getId());
                attachedDetallempasignadaListNew.add(detallempasignadaListNewDetallempasignadaToAttach);
            }
            detallempasignadaListNew = attachedDetallempasignadaListNew;
            planificacionproduccion.setDetallempasignadaList(detallempasignadaListNew);
            List<Ejecucionplanificacionproduccion> attachedEjecucionplanificacionproduccionListNew = new ArrayList<Ejecucionplanificacionproduccion>();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccionToAttach : ejecucionplanificacionproduccionListNew) {
                ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccionToAttach = em.getReference(ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccionToAttach.getClass(), ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccionToAttach.getIdejecucion());
                attachedEjecucionplanificacionproduccionListNew.add(ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccionToAttach);
            }
            ejecucionplanificacionproduccionListNew = attachedEjecucionplanificacionproduccionListNew;
            planificacionproduccion.setEjecucionplanificacionproduccionList(ejecucionplanificacionproduccionListNew);
            planificacionproduccion = em.merge(planificacionproduccion);
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getPlanificacionproduccionList().remove(planificacionproduccion);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getPlanificacionproduccionList().add(planificacionproduccion);
                pedidoNew = em.merge(pedidoNew);
            }
            if (idestadoOld != null && !idestadoOld.equals(idestadoNew)) {
                idestadoOld.getPlanificacionproduccionList().remove(planificacionproduccion);
                idestadoOld = em.merge(idestadoOld);
            }
            if (idestadoNew != null && !idestadoNew.equals(idestadoOld)) {
                idestadoNew.getPlanificacionproduccionList().add(planificacionproduccion);
                idestadoNew = em.merge(idestadoNew);
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListNewDetalleplanificacionproduccion : detalleplanificacionproduccionListNew) {
                if (!detalleplanificacionproduccionListOld.contains(detalleplanificacionproduccionListNewDetalleplanificacionproduccion)) {
                    Planificacionproduccion oldIdplanificacionproduccionOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion = detalleplanificacionproduccionListNewDetalleplanificacionproduccion.getIdplanificacionproduccion();
                    detalleplanificacionproduccionListNewDetalleplanificacionproduccion.setIdplanificacionproduccion(planificacionproduccion);
                    detalleplanificacionproduccionListNewDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionListNewDetalleplanificacionproduccion);
                    if (oldIdplanificacionproduccionOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion != null && !oldIdplanificacionproduccionOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion.equals(planificacionproduccion)) {
                        oldIdplanificacionproduccionOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion.getDetalleplanificacionproduccionList().remove(detalleplanificacionproduccionListNewDetalleplanificacionproduccion);
                        oldIdplanificacionproduccionOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion = em.merge(oldIdplanificacionproduccionOfDetalleplanificacionproduccionListNewDetalleplanificacionproduccion);
                    }
                }
            }
            for (Detallempasignada detallempasignadaListOldDetallempasignada : detallempasignadaListOld) {
                if (!detallempasignadaListNew.contains(detallempasignadaListOldDetallempasignada)) {
                    detallempasignadaListOldDetallempasignada.setIdplanificacionproduccion(null);
                    detallempasignadaListOldDetallempasignada = em.merge(detallempasignadaListOldDetallempasignada);
                }
            }
            for (Detallempasignada detallempasignadaListNewDetallempasignada : detallempasignadaListNew) {
                if (!detallempasignadaListOld.contains(detallempasignadaListNewDetallempasignada)) {
                    Planificacionproduccion oldIdplanificacionproduccionOfDetallempasignadaListNewDetallempasignada = detallempasignadaListNewDetallempasignada.getIdplanificacionproduccion();
                    detallempasignadaListNewDetallempasignada.setIdplanificacionproduccion(planificacionproduccion);
                    detallempasignadaListNewDetallempasignada = em.merge(detallempasignadaListNewDetallempasignada);
                    if (oldIdplanificacionproduccionOfDetallempasignadaListNewDetallempasignada != null && !oldIdplanificacionproduccionOfDetallempasignadaListNewDetallempasignada.equals(planificacionproduccion)) {
                        oldIdplanificacionproduccionOfDetallempasignadaListNewDetallempasignada.getDetallempasignadaList().remove(detallempasignadaListNewDetallempasignada);
                        oldIdplanificacionproduccionOfDetallempasignadaListNewDetallempasignada = em.merge(oldIdplanificacionproduccionOfDetallempasignadaListNewDetallempasignada);
                    }
                }
            }
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccion : ejecucionplanificacionproduccionListNew) {
                if (!ejecucionplanificacionproduccionListOld.contains(ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccion)) {
                    Planificacionproduccion oldIdplanificacionproduccionOfEjecucionplanificacionproduccionListNewEjecucionplanificacionproduccion = ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccion.getIdplanificacionproduccion();
                    ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccion.setIdplanificacionproduccion(planificacionproduccion);
                    ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccion = em.merge(ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccion);
                    if (oldIdplanificacionproduccionOfEjecucionplanificacionproduccionListNewEjecucionplanificacionproduccion != null && !oldIdplanificacionproduccionOfEjecucionplanificacionproduccionListNewEjecucionplanificacionproduccion.equals(planificacionproduccion)) {
                        oldIdplanificacionproduccionOfEjecucionplanificacionproduccionListNewEjecucionplanificacionproduccion.getEjecucionplanificacionproduccionList().remove(ejecucionplanificacionproduccionListNewEjecucionplanificacionproduccion);
                        oldIdplanificacionproduccionOfEjecucionplanificacionproduccionListNewEjecucionplanificacionproduccion = em.merge(oldIdplanificacionproduccionOfEjecucionplanificacionproduccionListNewEjecucionplanificacionproduccion);
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
            List<Detalleplanificacionproduccion> detalleplanificacionproduccionListOrphanCheck = planificacionproduccion.getDetalleplanificacionproduccionList();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionListOrphanCheckDetalleplanificacionproduccion : detalleplanificacionproduccionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planificacionproduccion (" + planificacionproduccion + ") cannot be destroyed since the Detalleplanificacionproduccion " + detalleplanificacionproduccionListOrphanCheckDetalleplanificacionproduccion + " in its detalleplanificacionproduccionList field has a non-nullable idplanificacionproduccion field.");
            }
            List<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionListOrphanCheck = planificacionproduccion.getEjecucionplanificacionproduccionList();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionListOrphanCheckEjecucionplanificacionproduccion : ejecucionplanificacionproduccionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planificacionproduccion (" + planificacionproduccion + ") cannot be destroyed since the Ejecucionplanificacionproduccion " + ejecucionplanificacionproduccionListOrphanCheckEjecucionplanificacionproduccion + " in its ejecucionplanificacionproduccionList field has a non-nullable idplanificacionproduccion field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Pedido pedido = planificacionproduccion.getPedido();
            if (pedido != null) {
                pedido.getPlanificacionproduccionList().remove(planificacionproduccion);
                pedido = em.merge(pedido);
            }
            Estadoplanificacionproduccion idestado = planificacionproduccion.getIdestado();
            if (idestado != null) {
                idestado.getPlanificacionproduccionList().remove(planificacionproduccion);
                idestado = em.merge(idestado);
            }
            List<Detallempasignada> detallempasignadaList = planificacionproduccion.getDetallempasignadaList();
            for (Detallempasignada detallempasignadaListDetallempasignada : detallempasignadaList) {
                detallempasignadaListDetallempasignada.setIdplanificacionproduccion(null);
                detallempasignadaListDetallempasignada = em.merge(detallempasignadaListDetallempasignada);
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
