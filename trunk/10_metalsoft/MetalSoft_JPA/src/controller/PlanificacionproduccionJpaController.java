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
import java.util.Date;

/**
 *
 * @author Nino
 */
public class PlanificacionproduccionJpaController {

    public PlanificacionproduccionJpaController() {
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
        if (planificacionproduccion.getDetalleplanificacionproduccionSet1() == null) {
            planificacionproduccion.setDetalleplanificacionproduccionSet1(new HashSet<Detalleplanificacionproduccion>());
        }
        if (planificacionproduccion.getDetallempasignadaSet() == null) {
            planificacionproduccion.setDetallempasignadaSet(new HashSet<Detallempasignada>());
        }
        if (planificacionproduccion.getDetallempasignadaSet1() == null) {
            planificacionproduccion.setDetallempasignadaSet1(new HashSet<Detallempasignada>());
        }
        if (planificacionproduccion.getEjecucionplanificacionproduccionSet() == null) {
            planificacionproduccion.setEjecucionplanificacionproduccionSet(new HashSet<Ejecucionplanificacionproduccion>());
        }
        if (planificacionproduccion.getEjecucionplanificacionproduccionSet1() == null) {
            planificacionproduccion.setEjecucionplanificacionproduccionSet1(new HashSet<Ejecucionplanificacionproduccion>());
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
            Estadoplanificacionproduccion idestado1 = planificacionproduccion.getIdestado1();
            if (idestado1 != null) {
                idestado1 = em.getReference(idestado1.getClass(), idestado1.getId());
                planificacionproduccion.setIdestado1(idestado1);
            }
            Pedido pedido = planificacionproduccion.getPedido();
            if (pedido != null) {
                pedido = em.getReference(pedido.getClass(), pedido.getIdpedido());
                planificacionproduccion.setPedido(pedido);
            }
            Pedido pedido1 = planificacionproduccion.getPedido1();
            if (pedido1 != null) {
                pedido1 = em.getReference(pedido1.getClass(), pedido1.getIdpedido());
                planificacionproduccion.setPedido1(pedido1);
            }
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach : planificacionproduccion.getDetalleplanificacionproduccionSet()) {
                detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet.add(detalleplanificacionproduccionSetDetalleplanificacionproduccionToAttach);
            }
            planificacionproduccion.setDetalleplanificacionproduccionSet(attachedDetalleplanificacionproduccionSet);
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet1 = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach : planificacionproduccion.getDetalleplanificacionproduccionSet1()) {
                detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet1.add(detalleplanificacionproduccionSet1DetalleplanificacionproduccionToAttach);
            }
            planificacionproduccion.setDetalleplanificacionproduccionSet1(attachedDetalleplanificacionproduccionSet1);
            Set<Detallempasignada> attachedDetallempasignadaSet = new HashSet<Detallempasignada>();
            for (Detallempasignada detallempasignadaSetDetallempasignadaToAttach : planificacionproduccion.getDetallempasignadaSet()) {
                detallempasignadaSetDetallempasignadaToAttach = em.getReference(detallempasignadaSetDetallempasignadaToAttach.getClass(), detallempasignadaSetDetallempasignadaToAttach.getId());
                attachedDetallempasignadaSet.add(detallempasignadaSetDetallempasignadaToAttach);
            }
            planificacionproduccion.setDetallempasignadaSet(attachedDetallempasignadaSet);
            Set<Detallempasignada> attachedDetallempasignadaSet1 = new HashSet<Detallempasignada>();
            for (Detallempasignada detallempasignadaSet1DetallempasignadaToAttach : planificacionproduccion.getDetallempasignadaSet1()) {
                detallempasignadaSet1DetallempasignadaToAttach = em.getReference(detallempasignadaSet1DetallempasignadaToAttach.getClass(), detallempasignadaSet1DetallempasignadaToAttach.getId());
                attachedDetallempasignadaSet1.add(detallempasignadaSet1DetallempasignadaToAttach);
            }
            planificacionproduccion.setDetallempasignadaSet1(attachedDetallempasignadaSet1);
            Set<Ejecucionplanificacionproduccion> attachedEjecucionplanificacionproduccionSet = new HashSet<Ejecucionplanificacionproduccion>();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSetEjecucionplanificacionproduccionToAttach : planificacionproduccion.getEjecucionplanificacionproduccionSet()) {
                ejecucionplanificacionproduccionSetEjecucionplanificacionproduccionToAttach = em.getReference(ejecucionplanificacionproduccionSetEjecucionplanificacionproduccionToAttach.getClass(), ejecucionplanificacionproduccionSetEjecucionplanificacionproduccionToAttach.getIdejecucion());
                attachedEjecucionplanificacionproduccionSet.add(ejecucionplanificacionproduccionSetEjecucionplanificacionproduccionToAttach);
            }
            planificacionproduccion.setEjecucionplanificacionproduccionSet(attachedEjecucionplanificacionproduccionSet);
            Set<Ejecucionplanificacionproduccion> attachedEjecucionplanificacionproduccionSet1 = new HashSet<Ejecucionplanificacionproduccion>();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSet1EjecucionplanificacionproduccionToAttach : planificacionproduccion.getEjecucionplanificacionproduccionSet1()) {
                ejecucionplanificacionproduccionSet1EjecucionplanificacionproduccionToAttach = em.getReference(ejecucionplanificacionproduccionSet1EjecucionplanificacionproduccionToAttach.getClass(), ejecucionplanificacionproduccionSet1EjecucionplanificacionproduccionToAttach.getIdejecucion());
                attachedEjecucionplanificacionproduccionSet1.add(ejecucionplanificacionproduccionSet1EjecucionplanificacionproduccionToAttach);
            }
            planificacionproduccion.setEjecucionplanificacionproduccionSet1(attachedEjecucionplanificacionproduccionSet1);
            em.persist(planificacionproduccion);
            if (idestado != null) {
                idestado.getPlanificacionproduccionSet().add(planificacionproduccion);
                idestado = em.merge(idestado);
            }
            if (idestado1 != null) {
                idestado1.getPlanificacionproduccionSet().add(planificacionproduccion);
                idestado1 = em.merge(idestado1);
            }
            if (pedido != null) {
                pedido.getPlanificacionproduccionSet().add(planificacionproduccion);
                pedido = em.merge(pedido);
            }
            if (pedido1 != null) {
                pedido1.getPlanificacionproduccionSet().add(planificacionproduccion);
                pedido1 = em.merge(pedido1);
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
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1Detalleplanificacionproduccion : planificacionproduccion.getDetalleplanificacionproduccionSet1()) {
                Planificacionproduccion oldIdplanificacionproduccion1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion = detalleplanificacionproduccionSet1Detalleplanificacionproduccion.getIdplanificacionproduccion1();
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion.setIdplanificacionproduccion1(planificacionproduccion);
                detalleplanificacionproduccionSet1Detalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1Detalleplanificacionproduccion);
                if (oldIdplanificacionproduccion1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion != null) {
                    oldIdplanificacionproduccion1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion.getDetalleplanificacionproduccionSet1().remove(detalleplanificacionproduccionSet1Detalleplanificacionproduccion);
                    oldIdplanificacionproduccion1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion = em.merge(oldIdplanificacionproduccion1OfDetalleplanificacionproduccionSet1Detalleplanificacionproduccion);
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
            for (Detallempasignada detallempasignadaSet1Detallempasignada : planificacionproduccion.getDetallempasignadaSet1()) {
                Planificacionproduccion oldIdplanificacionproduccion1OfDetallempasignadaSet1Detallempasignada = detallempasignadaSet1Detallempasignada.getIdplanificacionproduccion1();
                detallempasignadaSet1Detallempasignada.setIdplanificacionproduccion1(planificacionproduccion);
                detallempasignadaSet1Detallempasignada = em.merge(detallempasignadaSet1Detallempasignada);
                if (oldIdplanificacionproduccion1OfDetallempasignadaSet1Detallempasignada != null) {
                    oldIdplanificacionproduccion1OfDetallempasignadaSet1Detallempasignada.getDetallempasignadaSet1().remove(detallempasignadaSet1Detallempasignada);
                    oldIdplanificacionproduccion1OfDetallempasignadaSet1Detallempasignada = em.merge(oldIdplanificacionproduccion1OfDetallempasignadaSet1Detallempasignada);
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
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion : planificacionproduccion.getEjecucionplanificacionproduccionSet1()) {
                Planificacionproduccion oldIdplanificacionproduccion1OfEjecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion = ejecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion.getIdplanificacionproduccion1();
                ejecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion.setIdplanificacionproduccion1(planificacionproduccion);
                ejecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion = em.merge(ejecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion);
                if (oldIdplanificacionproduccion1OfEjecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion != null) {
                    oldIdplanificacionproduccion1OfEjecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion.getEjecucionplanificacionproduccionSet1().remove(ejecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion);
                    oldIdplanificacionproduccion1OfEjecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion = em.merge(oldIdplanificacionproduccion1OfEjecucionplanificacionproduccionSet1Ejecucionplanificacionproduccion);
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
            Estadoplanificacionproduccion idestado1Old = persistentPlanificacionproduccion.getIdestado1();
            Estadoplanificacionproduccion idestado1New = planificacionproduccion.getIdestado1();
            Pedido pedidoOld = persistentPlanificacionproduccion.getPedido();
            Pedido pedidoNew = planificacionproduccion.getPedido();
            Pedido pedido1Old = persistentPlanificacionproduccion.getPedido1();
            Pedido pedido1New = planificacionproduccion.getPedido1();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetOld = persistentPlanificacionproduccion.getDetalleplanificacionproduccionSet();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSetNew = planificacionproduccion.getDetalleplanificacionproduccionSet();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1Old = persistentPlanificacionproduccion.getDetalleplanificacionproduccionSet1();
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1New = planificacionproduccion.getDetalleplanificacionproduccionSet1();
            Set<Detallempasignada> detallempasignadaSetOld = persistentPlanificacionproduccion.getDetallempasignadaSet();
            Set<Detallempasignada> detallempasignadaSetNew = planificacionproduccion.getDetallempasignadaSet();
            Set<Detallempasignada> detallempasignadaSet1Old = persistentPlanificacionproduccion.getDetallempasignadaSet1();
            Set<Detallempasignada> detallempasignadaSet1New = planificacionproduccion.getDetallempasignadaSet1();
            Set<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionSetOld = persistentPlanificacionproduccion.getEjecucionplanificacionproduccionSet();
            Set<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionSetNew = planificacionproduccion.getEjecucionplanificacionproduccionSet();
            Set<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionSet1Old = persistentPlanificacionproduccion.getEjecucionplanificacionproduccionSet1();
            Set<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionSet1New = planificacionproduccion.getEjecucionplanificacionproduccionSet1();
            List<String> illegalOrphanMessages = null;
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetOldDetalleplanificacionproduccion : detalleplanificacionproduccionSetOld) {
                if (!detalleplanificacionproduccionSetNew.contains(detalleplanificacionproduccionSetOldDetalleplanificacionproduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleplanificacionproduccion " + detalleplanificacionproduccionSetOldDetalleplanificacionproduccion + " since its idplanificacionproduccion field is not nullable.");
                }
            }
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion : detalleplanificacionproduccionSet1Old) {
                if (!detalleplanificacionproduccionSet1New.contains(detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleplanificacionproduccion " + detalleplanificacionproduccionSet1OldDetalleplanificacionproduccion + " since its idplanificacionproduccion1 field is not nullable.");
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
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSet1OldEjecucionplanificacionproduccion : ejecucionplanificacionproduccionSet1Old) {
                if (!ejecucionplanificacionproduccionSet1New.contains(ejecucionplanificacionproduccionSet1OldEjecucionplanificacionproduccion)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ejecucionplanificacionproduccion " + ejecucionplanificacionproduccionSet1OldEjecucionplanificacionproduccion + " since its idplanificacionproduccion1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idestadoNew != null) {
                idestadoNew = em.getReference(idestadoNew.getClass(), idestadoNew.getId());
                planificacionproduccion.setIdestado(idestadoNew);
            }
            if (idestado1New != null) {
                idestado1New = em.getReference(idestado1New.getClass(), idestado1New.getId());
                planificacionproduccion.setIdestado1(idestado1New);
            }
            if (pedidoNew != null) {
                pedidoNew = em.getReference(pedidoNew.getClass(), pedidoNew.getIdpedido());
                planificacionproduccion.setPedido(pedidoNew);
            }
            if (pedido1New != null) {
                pedido1New = em.getReference(pedido1New.getClass(), pedido1New.getIdpedido());
                planificacionproduccion.setPedido1(pedido1New);
            }
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSetNew = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionSetNew) {
                detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSetNew.add(detalleplanificacionproduccionSetNewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionSetNew = attachedDetalleplanificacionproduccionSetNew;
            planificacionproduccion.setDetalleplanificacionproduccionSet(detalleplanificacionproduccionSetNew);
            Set<Detalleplanificacionproduccion> attachedDetalleplanificacionproduccionSet1New = new HashSet<Detalleplanificacionproduccion>();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach : detalleplanificacionproduccionSet1New) {
                detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach = em.getReference(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach.getClass(), detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach.getId());
                attachedDetalleplanificacionproduccionSet1New.add(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccionToAttach);
            }
            detalleplanificacionproduccionSet1New = attachedDetalleplanificacionproduccionSet1New;
            planificacionproduccion.setDetalleplanificacionproduccionSet1(detalleplanificacionproduccionSet1New);
            Set<Detallempasignada> attachedDetallempasignadaSetNew = new HashSet<Detallempasignada>();
            for (Detallempasignada detallempasignadaSetNewDetallempasignadaToAttach : detallempasignadaSetNew) {
                detallempasignadaSetNewDetallempasignadaToAttach = em.getReference(detallempasignadaSetNewDetallempasignadaToAttach.getClass(), detallempasignadaSetNewDetallempasignadaToAttach.getId());
                attachedDetallempasignadaSetNew.add(detallempasignadaSetNewDetallempasignadaToAttach);
            }
            detallempasignadaSetNew = attachedDetallempasignadaSetNew;
            planificacionproduccion.setDetallempasignadaSet(detallempasignadaSetNew);
            Set<Detallempasignada> attachedDetallempasignadaSet1New = new HashSet<Detallempasignada>();
            for (Detallempasignada detallempasignadaSet1NewDetallempasignadaToAttach : detallempasignadaSet1New) {
                detallempasignadaSet1NewDetallempasignadaToAttach = em.getReference(detallempasignadaSet1NewDetallempasignadaToAttach.getClass(), detallempasignadaSet1NewDetallempasignadaToAttach.getId());
                attachedDetallempasignadaSet1New.add(detallempasignadaSet1NewDetallempasignadaToAttach);
            }
            detallempasignadaSet1New = attachedDetallempasignadaSet1New;
            planificacionproduccion.setDetallempasignadaSet1(detallempasignadaSet1New);
            Set<Ejecucionplanificacionproduccion> attachedEjecucionplanificacionproduccionSetNew = new HashSet<Ejecucionplanificacionproduccion>();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccionToAttach : ejecucionplanificacionproduccionSetNew) {
                ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccionToAttach = em.getReference(ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccionToAttach.getClass(), ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccionToAttach.getIdejecucion());
                attachedEjecucionplanificacionproduccionSetNew.add(ejecucionplanificacionproduccionSetNewEjecucionplanificacionproduccionToAttach);
            }
            ejecucionplanificacionproduccionSetNew = attachedEjecucionplanificacionproduccionSetNew;
            planificacionproduccion.setEjecucionplanificacionproduccionSet(ejecucionplanificacionproduccionSetNew);
            Set<Ejecucionplanificacionproduccion> attachedEjecucionplanificacionproduccionSet1New = new HashSet<Ejecucionplanificacionproduccion>();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccionToAttach : ejecucionplanificacionproduccionSet1New) {
                ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccionToAttach = em.getReference(ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccionToAttach.getClass(), ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccionToAttach.getIdejecucion());
                attachedEjecucionplanificacionproduccionSet1New.add(ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccionToAttach);
            }
            ejecucionplanificacionproduccionSet1New = attachedEjecucionplanificacionproduccionSet1New;
            planificacionproduccion.setEjecucionplanificacionproduccionSet1(ejecucionplanificacionproduccionSet1New);
            planificacionproduccion = em.merge(planificacionproduccion);
            if (idestadoOld != null && !idestadoOld.equals(idestadoNew)) {
                idestadoOld.getPlanificacionproduccionSet().remove(planificacionproduccion);
                idestadoOld = em.merge(idestadoOld);
            }
            if (idestadoNew != null && !idestadoNew.equals(idestadoOld)) {
                idestadoNew.getPlanificacionproduccionSet().add(planificacionproduccion);
                idestadoNew = em.merge(idestadoNew);
            }
            if (idestado1Old != null && !idestado1Old.equals(idestado1New)) {
                idestado1Old.getPlanificacionproduccionSet().remove(planificacionproduccion);
                idestado1Old = em.merge(idestado1Old);
            }
            if (idestado1New != null && !idestado1New.equals(idestado1Old)) {
                idestado1New.getPlanificacionproduccionSet().add(planificacionproduccion);
                idestado1New = em.merge(idestado1New);
            }
            if (pedidoOld != null && !pedidoOld.equals(pedidoNew)) {
                pedidoOld.getPlanificacionproduccionSet().remove(planificacionproduccion);
                pedidoOld = em.merge(pedidoOld);
            }
            if (pedidoNew != null && !pedidoNew.equals(pedidoOld)) {
                pedidoNew.getPlanificacionproduccionSet().add(planificacionproduccion);
                pedidoNew = em.merge(pedidoNew);
            }
            if (pedido1Old != null && !pedido1Old.equals(pedido1New)) {
                pedido1Old.getPlanificacionproduccionSet().remove(planificacionproduccion);
                pedido1Old = em.merge(pedido1Old);
            }
            if (pedido1New != null && !pedido1New.equals(pedido1Old)) {
                pedido1New.getPlanificacionproduccionSet().add(planificacionproduccion);
                pedido1New = em.merge(pedido1New);
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
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion : detalleplanificacionproduccionSet1New) {
                if (!detalleplanificacionproduccionSet1Old.contains(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion)) {
                    Planificacionproduccion oldIdplanificacionproduccion1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion = detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.getIdplanificacionproduccion1();
                    detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.setIdplanificacionproduccion1(planificacionproduccion);
                    detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion = em.merge(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion);
                    if (oldIdplanificacionproduccion1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion != null && !oldIdplanificacionproduccion1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.equals(planificacionproduccion)) {
                        oldIdplanificacionproduccion1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion.getDetalleplanificacionproduccionSet1().remove(detalleplanificacionproduccionSet1NewDetalleplanificacionproduccion);
                        oldIdplanificacionproduccion1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion = em.merge(oldIdplanificacionproduccion1OfDetalleplanificacionproduccionSet1NewDetalleplanificacionproduccion);
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
            for (Detallempasignada detallempasignadaSet1OldDetallempasignada : detallempasignadaSet1Old) {
                if (!detallempasignadaSet1New.contains(detallempasignadaSet1OldDetallempasignada)) {
                    detallempasignadaSet1OldDetallempasignada.setIdplanificacionproduccion1(null);
                    detallempasignadaSet1OldDetallempasignada = em.merge(detallempasignadaSet1OldDetallempasignada);
                }
            }
            for (Detallempasignada detallempasignadaSet1NewDetallempasignada : detallempasignadaSet1New) {
                if (!detallempasignadaSet1Old.contains(detallempasignadaSet1NewDetallempasignada)) {
                    Planificacionproduccion oldIdplanificacionproduccion1OfDetallempasignadaSet1NewDetallempasignada = detallempasignadaSet1NewDetallempasignada.getIdplanificacionproduccion1();
                    detallempasignadaSet1NewDetallempasignada.setIdplanificacionproduccion1(planificacionproduccion);
                    detallempasignadaSet1NewDetallempasignada = em.merge(detallempasignadaSet1NewDetallempasignada);
                    if (oldIdplanificacionproduccion1OfDetallempasignadaSet1NewDetallempasignada != null && !oldIdplanificacionproduccion1OfDetallempasignadaSet1NewDetallempasignada.equals(planificacionproduccion)) {
                        oldIdplanificacionproduccion1OfDetallempasignadaSet1NewDetallempasignada.getDetallempasignadaSet1().remove(detallempasignadaSet1NewDetallempasignada);
                        oldIdplanificacionproduccion1OfDetallempasignadaSet1NewDetallempasignada = em.merge(oldIdplanificacionproduccion1OfDetallempasignadaSet1NewDetallempasignada);
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
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion : ejecucionplanificacionproduccionSet1New) {
                if (!ejecucionplanificacionproduccionSet1Old.contains(ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion)) {
                    Planificacionproduccion oldIdplanificacionproduccion1OfEjecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion = ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion.getIdplanificacionproduccion1();
                    ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion.setIdplanificacionproduccion1(planificacionproduccion);
                    ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion = em.merge(ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion);
                    if (oldIdplanificacionproduccion1OfEjecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion != null && !oldIdplanificacionproduccion1OfEjecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion.equals(planificacionproduccion)) {
                        oldIdplanificacionproduccion1OfEjecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion.getEjecucionplanificacionproduccionSet1().remove(ejecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion);
                        oldIdplanificacionproduccion1OfEjecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion = em.merge(oldIdplanificacionproduccion1OfEjecucionplanificacionproduccionSet1NewEjecucionplanificacionproduccion);
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
            Set<Detalleplanificacionproduccion> detalleplanificacionproduccionSet1OrphanCheck = planificacionproduccion.getDetalleplanificacionproduccionSet1();
            for (Detalleplanificacionproduccion detalleplanificacionproduccionSet1OrphanCheckDetalleplanificacionproduccion : detalleplanificacionproduccionSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planificacionproduccion (" + planificacionproduccion + ") cannot be destroyed since the Detalleplanificacionproduccion " + detalleplanificacionproduccionSet1OrphanCheckDetalleplanificacionproduccion + " in its detalleplanificacionproduccionSet1 field has a non-nullable idplanificacionproduccion1 field.");
            }
            Set<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionSetOrphanCheck = planificacionproduccion.getEjecucionplanificacionproduccionSet();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSetOrphanCheckEjecucionplanificacionproduccion : ejecucionplanificacionproduccionSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planificacionproduccion (" + planificacionproduccion + ") cannot be destroyed since the Ejecucionplanificacionproduccion " + ejecucionplanificacionproduccionSetOrphanCheckEjecucionplanificacionproduccion + " in its ejecucionplanificacionproduccionSet field has a non-nullable idplanificacionproduccion field.");
            }
            Set<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccionSet1OrphanCheck = planificacionproduccion.getEjecucionplanificacionproduccionSet1();
            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccionSet1OrphanCheckEjecucionplanificacionproduccion : ejecucionplanificacionproduccionSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planificacionproduccion (" + planificacionproduccion + ") cannot be destroyed since the Ejecucionplanificacionproduccion " + ejecucionplanificacionproduccionSet1OrphanCheckEjecucionplanificacionproduccion + " in its ejecucionplanificacionproduccionSet1 field has a non-nullable idplanificacionproduccion1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Estadoplanificacionproduccion idestado = planificacionproduccion.getIdestado();
            if (idestado != null) {
                idestado.getPlanificacionproduccionSet().remove(planificacionproduccion);
                idestado = em.merge(idestado);
            }
            Estadoplanificacionproduccion idestado1 = planificacionproduccion.getIdestado1();
            if (idestado1 != null) {
                idestado1.getPlanificacionproduccionSet().remove(planificacionproduccion);
                idestado1 = em.merge(idestado1);
            }
            Pedido pedido = planificacionproduccion.getPedido();
            if (pedido != null) {
                pedido.getPlanificacionproduccionSet().remove(planificacionproduccion);
                pedido = em.merge(pedido);
            }
            Pedido pedido1 = planificacionproduccion.getPedido1();
            if (pedido1 != null) {
                pedido1.getPlanificacionproduccionSet().remove(planificacionproduccion);
                pedido1 = em.merge(pedido1);
            }
            Set<Detallempasignada> detallempasignadaSet = planificacionproduccion.getDetallempasignadaSet();
            for (Detallempasignada detallempasignadaSetDetallempasignada : detallempasignadaSet) {
                detallempasignadaSetDetallempasignada.setIdplanificacionproduccion(null);
                detallempasignadaSetDetallempasignada = em.merge(detallempasignadaSetDetallempasignada);
            }
            Set<Detallempasignada> detallempasignadaSet1 = planificacionproduccion.getDetallempasignadaSet1();
            for (Detallempasignada detallempasignadaSet1Detallempasignada : detallempasignadaSet1) {
                detallempasignadaSet1Detallempasignada.setIdplanificacionproduccion1(null);
                detallempasignadaSet1Detallempasignada = em.merge(detallempasignadaSet1Detallempasignada);
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

    public List<Planificacionproduccion> findByFechafinprevistaMayorActual(Date fecha){
        EntityManager em = getEntityManager();
        Query q = em.createNamedQuery("Planificacionproduccion.findByFechafinMayorActual");
        q.setParameter("fechafinprevista", fecha);
        return q.getResultList();
    }

}
