/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Planificacioncalidad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Ejecucionplanificacioncalidad;
import entity.Pedido;
import entity.Detalleplanificacioncalidad;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class PlanificacioncalidadJpaController {

    public PlanificacioncalidadJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planificacioncalidad planificacioncalidad) throws PreexistingEntityException, Exception {
        if (planificacioncalidad.getDetalleplanificacioncalidadSet() == null) {
            planificacioncalidad.setDetalleplanificacioncalidadSet(new HashSet<Detalleplanificacioncalidad>());
        }
        if (planificacioncalidad.getDetalleplanificacioncalidadSet1() == null) {
            planificacioncalidad.setDetalleplanificacioncalidadSet1(new HashSet<Detalleplanificacioncalidad>());
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
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidad1 = planificacioncalidad.getEjecucionplanificacioncalidad1();
            if (ejecucionplanificacioncalidad1 != null) {
                ejecucionplanificacioncalidad1 = em.getReference(ejecucionplanificacioncalidad1.getClass(), ejecucionplanificacioncalidad1.getEjecucionplanificacioncalidadPK());
                planificacioncalidad.setEjecucionplanificacioncalidad1(ejecucionplanificacioncalidad1);
            }
            Pedido pedido = planificacioncalidad.getPedido();
            if (pedido != null) {
                pedido = em.getReference(pedido.getClass(), pedido.getIdpedido());
                planificacioncalidad.setPedido(pedido);
            }
            Pedido pedido1 = planificacioncalidad.getPedido1();
            if (pedido1 != null) {
                pedido1 = em.getReference(pedido1.getClass(), pedido1.getIdpedido());
                planificacioncalidad.setPedido1(pedido1);
            }
            Set<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadSet = new HashSet<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach : planificacioncalidad.getDetalleplanificacioncalidadSet()) {
                detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadSet.add(detalleplanificacioncalidadSetDetalleplanificacioncalidadToAttach);
            }
            planificacioncalidad.setDetalleplanificacioncalidadSet(attachedDetalleplanificacioncalidadSet);
            Set<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadSet1 = new HashSet<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSet1DetalleplanificacioncalidadToAttach : planificacioncalidad.getDetalleplanificacioncalidadSet1()) {
                detalleplanificacioncalidadSet1DetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadSet1DetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadSet1DetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadSet1.add(detalleplanificacioncalidadSet1DetalleplanificacioncalidadToAttach);
            }
            planificacioncalidad.setDetalleplanificacioncalidadSet1(attachedDetalleplanificacioncalidadSet1);
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
            if (ejecucionplanificacioncalidad1 != null) {
                Planificacioncalidad oldPlanificacioncalidad1OfEjecucionplanificacioncalidad1 = ejecucionplanificacioncalidad1.getPlanificacioncalidad1();
                if (oldPlanificacioncalidad1OfEjecucionplanificacioncalidad1 != null) {
                    oldPlanificacioncalidad1OfEjecucionplanificacioncalidad1.setEjecucionplanificacioncalidad1(null);
                    oldPlanificacioncalidad1OfEjecucionplanificacioncalidad1 = em.merge(oldPlanificacioncalidad1OfEjecucionplanificacioncalidad1);
                }
                ejecucionplanificacioncalidad1.setPlanificacioncalidad1(planificacioncalidad);
                ejecucionplanificacioncalidad1 = em.merge(ejecucionplanificacioncalidad1);
            }
            if (pedido != null) {
                pedido.getPlanificacioncalidadSet().add(planificacioncalidad);
                pedido = em.merge(pedido);
            }
            if (pedido1 != null) {
                pedido1.getPlanificacioncalidadSet().add(planificacioncalidad);
                pedido1 = em.merge(pedido1);
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetDetalleplanificacioncalidad : planificacioncalidad.getDetalleplanificacioncalidadSet()) {
                Planificacioncalidad oldPlanificacioncalidadOfDetalleplanificacioncalidadSetDetalleplanificacioncalidad = detalleplanificacioncalidadSetDetalleplanificacioncalidad.getPlanificacioncalidad();
                detalleplanificacioncalidadSetDetalleplanificacioncalidad.setPlanificacioncalidad(planificacioncalidad);
                detalleplanificacioncalidadSetDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadSetDetalleplanificacioncalidad);
                if (oldPlanificacioncalidadOfDetalleplanificacioncalidadSetDetalleplanificacioncalidad != null) {
                    oldPlanificacioncalidadOfDetalleplanificacioncalidadSetDetalleplanificacioncalidad.getDetalleplanificacioncalidadSet().remove(detalleplanificacioncalidadSetDetalleplanificacioncalidad);
                    oldPlanificacioncalidadOfDetalleplanificacioncalidadSetDetalleplanificacioncalidad = em.merge(oldPlanificacioncalidadOfDetalleplanificacioncalidadSetDetalleplanificacioncalidad);
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSet1Detalleplanificacioncalidad : planificacioncalidad.getDetalleplanificacioncalidadSet1()) {
                Planificacioncalidad oldPlanificacioncalidad1OfDetalleplanificacioncalidadSet1Detalleplanificacioncalidad = detalleplanificacioncalidadSet1Detalleplanificacioncalidad.getPlanificacioncalidad1();
                detalleplanificacioncalidadSet1Detalleplanificacioncalidad.setPlanificacioncalidad1(planificacioncalidad);
                detalleplanificacioncalidadSet1Detalleplanificacioncalidad = em.merge(detalleplanificacioncalidadSet1Detalleplanificacioncalidad);
                if (oldPlanificacioncalidad1OfDetalleplanificacioncalidadSet1Detalleplanificacioncalidad != null) {
                    oldPlanificacioncalidad1OfDetalleplanificacioncalidadSet1Detalleplanificacioncalidad.getDetalleplanificacioncalidadSet1().remove(detalleplanificacioncalidadSet1Detalleplanificacioncalidad);
                    oldPlanificacioncalidad1OfDetalleplanificacioncalidadSet1Detalleplanificacioncalidad = em.merge(oldPlanificacioncalidad1OfDetalleplanificacioncalidadSet1Detalleplanificacioncalidad);
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
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidad1Old = persistentPlanificacioncalidad.getEjecucionplanificacioncalidad1();
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidad1New = planificacioncalidad.getEjecucionplanificacioncalidad1();
            Pedido pedidoOld = persistentPlanificacioncalidad.getPedido();
            Pedido pedidoNew = planificacioncalidad.getPedido();
            Pedido pedido1Old = persistentPlanificacioncalidad.getPedido1();
            Pedido pedido1New = planificacioncalidad.getPedido1();
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSetOld = persistentPlanificacioncalidad.getDetalleplanificacioncalidadSet();
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSetNew = planificacioncalidad.getDetalleplanificacioncalidadSet();
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSet1Old = persistentPlanificacioncalidad.getDetalleplanificacioncalidadSet1();
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSet1New = planificacioncalidad.getDetalleplanificacioncalidadSet1();
            List<String> illegalOrphanMessages = null;
            if (ejecucionplanificacioncalidadOld != null && !ejecucionplanificacioncalidadOld.equals(ejecucionplanificacioncalidadNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Ejecucionplanificacioncalidad " + ejecucionplanificacioncalidadOld + " since its planificacioncalidad field is not nullable.");
            }
            if (ejecucionplanificacioncalidad1Old != null && !ejecucionplanificacioncalidad1Old.equals(ejecucionplanificacioncalidad1New)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Ejecucionplanificacioncalidad " + ejecucionplanificacioncalidad1Old + " since its planificacioncalidad1 field is not nullable.");
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetOldDetalleplanificacioncalidad : detalleplanificacioncalidadSetOld) {
                if (!detalleplanificacioncalidadSetNew.contains(detalleplanificacioncalidadSetOldDetalleplanificacioncalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleplanificacioncalidad " + detalleplanificacioncalidadSetOldDetalleplanificacioncalidad + " since its planificacioncalidad field is not nullable.");
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSet1OldDetalleplanificacioncalidad : detalleplanificacioncalidadSet1Old) {
                if (!detalleplanificacioncalidadSet1New.contains(detalleplanificacioncalidadSet1OldDetalleplanificacioncalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleplanificacioncalidad " + detalleplanificacioncalidadSet1OldDetalleplanificacioncalidad + " since its planificacioncalidad1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (ejecucionplanificacioncalidadNew != null) {
                ejecucionplanificacioncalidadNew = em.getReference(ejecucionplanificacioncalidadNew.getClass(), ejecucionplanificacioncalidadNew.getEjecucionplanificacioncalidadPK());
                planificacioncalidad.setEjecucionplanificacioncalidad(ejecucionplanificacioncalidadNew);
            }
            if (ejecucionplanificacioncalidad1New != null) {
                ejecucionplanificacioncalidad1New = em.getReference(ejecucionplanificacioncalidad1New.getClass(), ejecucionplanificacioncalidad1New.getEjecucionplanificacioncalidadPK());
                planificacioncalidad.setEjecucionplanificacioncalidad1(ejecucionplanificacioncalidad1New);
            }
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getIdpedido());
                planificacioncalidad.setPedido(pedidoNew);
            }
            if (pedido1New != null) {
                pedido1New = em.getReference(pedido1New.getClass(), pedido1New.getIdpedido());
                planificacioncalidad.setPedido1(pedido1New);
            }
            Set<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadSetNew = new HashSet<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach : detalleplanificacioncalidadSetNew) {
                detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadSetNew.add(detalleplanificacioncalidadSetNewDetalleplanificacioncalidadToAttach);
            }
            detalleplanificacioncalidadSetNew = attachedDetalleplanificacioncalidadSetNew;
            planificacioncalidad.setDetalleplanificacioncalidadSet(detalleplanificacioncalidadSetNew);
            Set<Detalleplanificacioncalidad> attachedDetalleplanificacioncalidadSet1New = new HashSet<Detalleplanificacioncalidad>();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSet1NewDetalleplanificacioncalidadToAttach : detalleplanificacioncalidadSet1New) {
                detalleplanificacioncalidadSet1NewDetalleplanificacioncalidadToAttach = em.getReference(detalleplanificacioncalidadSet1NewDetalleplanificacioncalidadToAttach.getClass(), detalleplanificacioncalidadSet1NewDetalleplanificacioncalidadToAttach.getDetalleplanificacioncalidadPK());
                attachedDetalleplanificacioncalidadSet1New.add(detalleplanificacioncalidadSet1NewDetalleplanificacioncalidadToAttach);
            }
            detalleplanificacioncalidadSet1New = attachedDetalleplanificacioncalidadSet1New;
            planificacioncalidad.setDetalleplanificacioncalidadSet1(detalleplanificacioncalidadSet1New);
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
            if (ejecucionplanificacioncalidad1New != null && !ejecucionplanificacioncalidad1New.equals(ejecucionplanificacioncalidad1Old)) {
                Planificacioncalidad oldPlanificacioncalidad1OfEjecucionplanificacioncalidad1 = ejecucionplanificacioncalidad1New.getPlanificacioncalidad1();
                if (oldPlanificacioncalidad1OfEjecucionplanificacioncalidad1 != null) {
                    oldPlanificacioncalidad1OfEjecucionplanificacioncalidad1.setEjecucionplanificacioncalidad1(null);
                    oldPlanificacioncalidad1OfEjecucionplanificacioncalidad1 = em.merge(oldPlanificacioncalidad1OfEjecucionplanificacioncalidad1);
                }
                ejecucionplanificacioncalidad1New.setPlanificacioncalidad1(planificacioncalidad);
                ejecucionplanificacioncalidad1New = em.merge(ejecucionplanificacioncalidad1New);
            }
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getPlanificacioncalidadSet().remove(planificacioncalidad);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getPlanificacioncalidadSet().add(planificacioncalidad);
                pedidoNew = em.merge(pedidoNew);
            }
            if (pedido1Old != null && !pedido1Old.equals(pedido1New)) {
                pedido1Old.getPlanificacioncalidadSet().remove(planificacioncalidad);
                pedido1Old = em.merge(pedido1Old);
            }
            if (pedido1New != null && !pedido1New.equals(pedido1Old)) {
                pedido1New.getPlanificacioncalidadSet().add(planificacioncalidad);
                pedido1New = em.merge(pedido1New);
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetNewDetalleplanificacioncalidad : detalleplanificacioncalidadSetNew) {
                if (!detalleplanificacioncalidadSetOld.contains(detalleplanificacioncalidadSetNewDetalleplanificacioncalidad)) {
                    Planificacioncalidad oldPlanificacioncalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad = detalleplanificacioncalidadSetNewDetalleplanificacioncalidad.getPlanificacioncalidad();
                    detalleplanificacioncalidadSetNewDetalleplanificacioncalidad.setPlanificacioncalidad(planificacioncalidad);
                    detalleplanificacioncalidadSetNewDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadSetNewDetalleplanificacioncalidad);
                    if (oldPlanificacioncalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad != null && !oldPlanificacioncalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad.equals(planificacioncalidad)) {
                        oldPlanificacioncalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad.getDetalleplanificacioncalidadSet().remove(detalleplanificacioncalidadSetNewDetalleplanificacioncalidad);
                        oldPlanificacioncalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad = em.merge(oldPlanificacioncalidadOfDetalleplanificacioncalidadSetNewDetalleplanificacioncalidad);
                    }
                }
            }
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSet1NewDetalleplanificacioncalidad : detalleplanificacioncalidadSet1New) {
                if (!detalleplanificacioncalidadSet1Old.contains(detalleplanificacioncalidadSet1NewDetalleplanificacioncalidad)) {
                    Planificacioncalidad oldPlanificacioncalidad1OfDetalleplanificacioncalidadSet1NewDetalleplanificacioncalidad = detalleplanificacioncalidadSet1NewDetalleplanificacioncalidad.getPlanificacioncalidad1();
                    detalleplanificacioncalidadSet1NewDetalleplanificacioncalidad.setPlanificacioncalidad1(planificacioncalidad);
                    detalleplanificacioncalidadSet1NewDetalleplanificacioncalidad = em.merge(detalleplanificacioncalidadSet1NewDetalleplanificacioncalidad);
                    if (oldPlanificacioncalidad1OfDetalleplanificacioncalidadSet1NewDetalleplanificacioncalidad != null && !oldPlanificacioncalidad1OfDetalleplanificacioncalidadSet1NewDetalleplanificacioncalidad.equals(planificacioncalidad)) {
                        oldPlanificacioncalidad1OfDetalleplanificacioncalidadSet1NewDetalleplanificacioncalidad.getDetalleplanificacioncalidadSet1().remove(detalleplanificacioncalidadSet1NewDetalleplanificacioncalidad);
                        oldPlanificacioncalidad1OfDetalleplanificacioncalidadSet1NewDetalleplanificacioncalidad = em.merge(oldPlanificacioncalidad1OfDetalleplanificacioncalidadSet1NewDetalleplanificacioncalidad);
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
            Ejecucionplanificacioncalidad ejecucionplanificacioncalidad1OrphanCheck = planificacioncalidad.getEjecucionplanificacioncalidad1();
            if (ejecucionplanificacioncalidad1OrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planificacioncalidad (" + planificacioncalidad + ") cannot be destroyed since the Ejecucionplanificacioncalidad " + ejecucionplanificacioncalidad1OrphanCheck + " in its ejecucionplanificacioncalidad1 field has a non-nullable planificacioncalidad1 field.");
            }
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSetOrphanCheck = planificacioncalidad.getDetalleplanificacioncalidadSet();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSetOrphanCheckDetalleplanificacioncalidad : detalleplanificacioncalidadSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planificacioncalidad (" + planificacioncalidad + ") cannot be destroyed since the Detalleplanificacioncalidad " + detalleplanificacioncalidadSetOrphanCheckDetalleplanificacioncalidad + " in its detalleplanificacioncalidadSet field has a non-nullable planificacioncalidad field.");
            }
            Set<Detalleplanificacioncalidad> detalleplanificacioncalidadSet1OrphanCheck = planificacioncalidad.getDetalleplanificacioncalidadSet1();
            for (Detalleplanificacioncalidad detalleplanificacioncalidadSet1OrphanCheckDetalleplanificacioncalidad : detalleplanificacioncalidadSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planificacioncalidad (" + planificacioncalidad + ") cannot be destroyed since the Detalleplanificacioncalidad " + detalleplanificacioncalidadSet1OrphanCheckDetalleplanificacioncalidad + " in its detalleplanificacioncalidadSet1 field has a non-nullable planificacioncalidad1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Pedido pedido = planificacioncalidad.getPedido();
            if (pedido != null) {
                pedido.getPlanificacioncalidadSet().remove(planificacioncalidad);
                pedido = em.merge(pedido);
            }
            Pedido pedido1 = planificacioncalidad.getPedido1();
            if (pedido1 != null) {
                pedido1.getPlanificacioncalidadSet().remove(planificacioncalidad);
                pedido1 = em.merge(pedido1);
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
