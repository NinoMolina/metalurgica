/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.datos.jpa.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Ejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Planificacioncalidad;

/**
 *
 * @author Nino
 */
public class PlanificacioncalidadJpaController {

    public PlanificacioncalidadJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_Desktop_PU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planificacioncalidad planificacioncalidad) throws PreexistingEntityException, Exception {
        if (planificacioncalidad.getDetalleplanificacioncalidadList() == null) {
            planificacioncalidad.setDetalleplanificacioncalidadList(new ArrayList<Detalleplanificacioncalidad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidad = planificacioncalidad.getEjecucionplanificacioncalidad();
            if (ejecucionplanificacioncalidad != null) {
                ejecucionplanificacioncalidad = em.getReference(ejecucionplanificacioncalidad.getClass(), ejecucionplanificacioncalidad.getEjecucionplanificacioncalidadPK());
                planificacioncalidad.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidad);
            }
            Pedido pedido = planificacioncalidad.getPedido();
            if (pedido != null) {
                pedido = em.getReference(pedido.getClass(), pedido.getIdpedido());
                planificacioncalidad.setPedido(pedido);
            }
            List<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadList = new ArrayList<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach : planificacioncalidad.getDetalleplanificacioncalidadList()) {
                detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadList.add(detalleplanificacioncalidadListDetalleplanificacioncalidadToAttach);
            }
            planificacioncalidad.setDetalleplanificacioncalidadList(attachedDetalleplanificacioncalidadList);
            em.persist(planificacioncalidad);
            if (ejecucionplanificacioncalidad != null) {
                Planificacioncalidad oldPlanificacioncalidadOfEjecucionplanificacioncalidad = ejecucionplanificacioncalidad.getPlanificacioncalidad();
                if (oldPlanificacioncalidadOfEjecucionplanificacioncalidad != null) {
                    oldPlanificacioncalidadOfEjecucionplanificacioncalidad.setEjecucionplanificacioncalidad(null);
                    oldPlanificacioncalidadOfEjecucionplanificacioncalidad = em.merge(oldPlanificacioncalidadOfEjecucionplanificacioncalidad);
                }
                ejecucionplanificacioncalidad.setPlanificacioncalidad(planificacioncalidad);
                ejecucionplanificacioncalidad = em.merge(ejecucionplanificacioncalidad);
            }
            if (pedido != null) {
                pedido.getPlanificacioncalidadList().add(planificacioncalidad);
                pedido = em.merge(pedido);
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListDetalleplanificacioncalidad : planificacioncalidad.getDetalleplanificacioncalidadList()) {
                Planificacioncalidad oldPlanificacioncalidadOfDetalleplanificacioncalidadListDetalleplanificacioncalidad = detalleplanificacioncalidadListDetalleplanificacioncalidad.getPlanificacioncalidad();
                detalleplanificacioncalidadListDetalleplanificacioncalidad.setPlanificacioncalidad(planificacioncalidad);
                detalleplanificacioncalidadListDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListDetalleplanificacioncalidad);
                if (oldPlanificacioncalidadOfDetalleplanificacioncalidadListDetalleplanificacioncalidad != null) {
                    oldPlanificacioncalidadOfDetalleplanificacioncalidadListDetalleplanificacioncalidad.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidadListDetalleplanificacioncalidad);
                    oldPlanificacioncalidadOfDetalleplanificacioncalidadListDetalleplanificacioncalidad = em.merge(oldPlanificacioncalidadOfDetalleplanificacioncalidadListDetalleplanificacioncalidad);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPlanificacioncalidad(planificacioncalidad.getIdplanificacion()) != null) {
                throw new PreexistingEntityException("Planificacioncalidad " + planificacioncalidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Planificacioncalidad planificacioncalidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planificacioncalidad persistentPlanificacioncalidad = em.find(Planificacioncalidad.class, planificacioncalidad.getIdplanificacion());
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidadOld = persistentPlanificacioncalidad.getEjecucionplanificacioncalidad();
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidadNew = planificacioncalidad.getEjecucionplanificacioncalidad();
            Pedido pedidoOld = persistentPlanificacioncalidad.getPedido();
            Pedido pedidoNew = planificacioncalidad.getPedido();
            List<Detalleplanificacioncalidad> detalleplanificacioncalidadListOld = persistentPlanificacioncalidad.getDetalleplanificacioncalidadList();
            List<Detalleplanificacioncalidad> detalleplanificacioncalidadListNew = planificacioncalidad.getDetalleplanificacioncalidadList();
            List<String> illegalOrphanMessages = null;
            if (ejecucionplanificacioncalidadOld != null && !ejecucionplanificacioncalidadOld.equals(ejecucionplanificacioncalidadNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Ejecucionplanificacioncalidad " + ejecucionplanificacioncalidadOld + " since its planificacioncalidad field is not nullable.");
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListOldDetalleplanificacioncalidad : detalleplanificacioncalidadListOld) {
                if (!detalleplanificacioncalidadListNew.contains(detalleplanificacioncalidadListOldDetalleplanificacioncalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleplanificacioncalidad " + detalleplanificacioncalidadListOldDetalleplanificacioncalidad + " since its planificacioncalidad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (ejecucionplanificacioncalidadNew != null) {
                ejecucionplanificacioncalidadNew = em.getReference(ejecucionplanificacioncalidadNew.getClass(), ejecucionplanificacioncalidadNew.getEjecucionplanificacioncalidadPK());
                planificacioncalidad.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidadNew);
            }
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getIdpedido());
                planificacioncalidad.setPedido(pedidoNew);
            }
            List<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadListNew = new ArrayList<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach : detalleplanificacioncalidadListNew) {
                detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadListNew.add(detalleplanificacioncalidadListNewDetalleplanificacioncalidadToAttach);
            }
            detalleplanificacioncalidadListNew = attachedDetalleplanificacioncalidadListNew;
            planificacioncalidad.setDetalleplanificacioncalidadList(detalleplanificacioncalidadListNew);
            planificacioncalidad = em.merge(planificacioncalidad);
            if (ejecucionplanificacioncalidadNew != null && !ejecucionplanificacioncalidadNew.equals(ejecucionplanificacioncalidadOld)) {
                Planificacioncalidad oldPlanificacioncalidadOfEjecucionplanificacioncalidad = ejecucionplanificacioncalidadNew.getPlanificacioncalidad();
                if (oldPlanificacioncalidadOfEjecucionplanificacioncalidad != null) {
                    oldPlanificacioncalidadOfEjecucionplanificacioncalidad.setEjecucionplanificacioncalidad(null);
                    oldPlanificacioncalidadOfEjecucionplanificacioncalidad = em.merge(oldPlanificacioncalidadOfEjecucionplanificacioncalidad);
                }
                ejecucionplanificacioncalidadNew.setPlanificacioncalidad(planificacioncalidad);
                ejecucionplanificacioncalidadNew = em.merge(ejecucionplanificacioncalidadNew);
            }
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getPlanificacioncalidadList().remove(planificacioncalidad);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getPlanificacioncalidadList().add(planificacioncalidad);
                pedidoNew = em.merge(pedidoNew);
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListNewDetalleplanificacioncalidad : detalleplanificacioncalidadListNew) {
                if (!detalleplanificacioncalidadListOld.contains(detalleplanificacioncalidadListNewDetalleplanificacioncalidad)) {
                    Planificacioncalidad oldPlanificacioncalidadOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad = detalleplanificacioncalidadListNewDetalleplanificacioncalidad.getPlanificacioncalidad();
                    detalleplanificacioncalidadListNewDetalleplanificacioncalidad.setPlanificacioncalidad(planificacioncalidad);
                    detalleplanificacioncalidadListNewDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadListNewDetalleplanificacioncalidad);
                    if (oldPlanificacioncalidadOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad != null && !oldPlanificacioncalidadOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad.equals(planificacioncalidad)) {
                        oldPlanificacioncalidadOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad.getDetalleplanificacioncalidadList().remove(detalleplanificacioncalidadListNewDetalleplanificacioncalidad);
                        oldPlanificacioncalidadOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad = em.merge(oldPlanificacioncalidadOfDetalleplanificacioncalidadListNewDetalleplanificacioncalidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = planificacioncalidad.getIdplanificacion();
                if (findPlanificacioncalidad(id) == null) {
                    throw new NonexistentEntityException("The planificacioncalidad with id " + id + " no longer exists.");
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
            Planificacioncalidad planificacioncalidad;
            try {
                planificacioncalidad = em.getReference(Planificacioncalidad.class, id);
                planificacioncalidad.getIdplanificacion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planificacioncalidad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidadOrphanCheck = planificacioncalidad.getEjecucionplanificacioncalidad();
            if (ejecucionplanificacioncalidadOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planificacioncalidad (" + planificacioncalidad + ") cannot be destroyed since the Ejecucionplanificacioncalidad " + ejecucionplanificacioncalidadOrphanCheck + " in its ejecucionplanificacioncalidad field has a non-nullable planificacioncalidad field.");
            }
            List<Detalleplanificacioncalidad> detalleplanificacioncalidadListOrphanCheck = planificacioncalidad.getDetalleplanificacioncalidadList();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadListOrphanCheckDetalleplanificacioncalidad : detalleplanificacioncalidadListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planificacioncalidad (" + planificacioncalidad + ") cannot be destroyed since the Detalleplanificacioncalidad " + detalleplanificacioncalidadListOrphanCheckDetalleplanificacioncalidad + " in its detalleplanificacioncalidadList field has a non-nullable planificacioncalidad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Pedido pedido = planificacioncalidad.getPedido();
            if (pedido != null) {
                pedido.getPlanificacioncalidadList().remove(planificacioncalidad);
                pedido = em.merge(pedido);
            }
            em.remove(planificacioncalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Planificacioncalidad> findPlanificacioncalidadEntities() {
        return findPlanificacioncalidadEntities(true, -1, -1);
    }

    public List<Planificacioncalidad> findPlanificacioncalidadEntities(int maxResults, int firstResult) {
        return findPlanificacioncalidadEntities(false, maxResults, firstResult);
    }

    private List<Planificacioncalidad> findPlanificacioncalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Planificacioncalidad.class));
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

    public Planificacioncalidad findPlanificacioncalidad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Planificacioncalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanificacioncalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Planificacioncalidad> rt = cq.from(Planificacioncalidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
