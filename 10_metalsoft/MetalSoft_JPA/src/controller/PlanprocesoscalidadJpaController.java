/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Planprocesoscalidad;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Detalleplanprocesoscalidad;
import java.util.HashSet;
import java.util.Set;
import entity.Pedido;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class PlanprocesoscalidadJpaController {

    public PlanprocesoscalidadJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planprocesoscalidad planprocesoscalidad) throws PreexistingEntityException, Exception {
        if (planprocesoscalidad.getDetalleplanprocesoscalidadSet() == null) {
            planprocesoscalidad.setDetalleplanprocesoscalidadSet(new HashSet<Detalleplanprocesoscalidad>());
        }
        if (planprocesoscalidad.getDetalleplanprocesoscalidadSet1() == null) {
            planprocesoscalidad.setDetalleplanprocesoscalidadSet1(new HashSet<Detalleplanprocesoscalidad>());
        }
        if (planprocesoscalidad.getPedidoSet() == null) {
            planprocesoscalidad.setPedidoSet(new HashSet<Pedido>());
        }
        if (planprocesoscalidad.getPedidoSet1() == null) {
            planprocesoscalidad.setPedidoSet1(new HashSet<Pedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Detalleplanprocesoscalidad> attachedDetalleplanprocesoscalidadSet = new HashSet<Detalleplanprocesoscalidad>();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetDetalleplanprocesoscalidadToAttach : planprocesoscalidad.getDetalleplanprocesoscalidadSet()) {
                detalleplanprocesoscalidadSetDetalleplanprocesoscalidadToAttach = em.getReference(detalleplanprocesoscalidadSetDetalleplanprocesoscalidadToAttach.getClass(), detalleplanprocesoscalidadSetDetalleplanprocesoscalidadToAttach.getDetalleplanprocesoscalidadPK());
                attachedDetalleplanprocesoscalidadSet.add(detalleplanprocesoscalidadSetDetalleplanprocesoscalidadToAttach);
            }
            planprocesoscalidad.setDetalleplanprocesoscalidadSet(attachedDetalleplanprocesoscalidadSet);
            Set<Detalleplanprocesoscalidad> attachedDetalleplanprocesoscalidadSet1 = new HashSet<Detalleplanprocesoscalidad>();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSet1DetalleplanprocesoscalidadToAttach : planprocesoscalidad.getDetalleplanprocesoscalidadSet1()) {
                detalleplanprocesoscalidadSet1DetalleplanprocesoscalidadToAttach = em.getReference(detalleplanprocesoscalidadSet1DetalleplanprocesoscalidadToAttach.getClass(), detalleplanprocesoscalidadSet1DetalleplanprocesoscalidadToAttach.getDetalleplanprocesoscalidadPK());
                attachedDetalleplanprocesoscalidadSet1.add(detalleplanprocesoscalidadSet1DetalleplanprocesoscalidadToAttach);
            }
            planprocesoscalidad.setDetalleplanprocesoscalidadSet1(attachedDetalleplanprocesoscalidadSet1);
            Set<Pedido> attachedPedidoSet = new HashSet<Pedido>();
            for (Pedido pedidoSetPedidoToAttach : planprocesoscalidad.getPedidoSet()) {
                pedidoSetPedidoToAttach = em.getReference(pedidoSetPedidoToAttach.getClass(), pedidoSetPedidoToAttach.getIdpedido());
                attachedPedidoSet.add(pedidoSetPedidoToAttach);
            }
            planprocesoscalidad.setPedidoSet(attachedPedidoSet);
            Set<Pedido> attachedPedidoSet1 = new HashSet<Pedido>();
            for (Pedido pedidoSet1PedidoToAttach : planprocesoscalidad.getPedidoSet1()) {
                pedidoSet1PedidoToAttach = em.getReference(pedidoSet1PedidoToAttach.getClass(), pedidoSet1PedidoToAttach.getIdpedido());
                attachedPedidoSet1.add(pedidoSet1PedidoToAttach);
            }
            planprocesoscalidad.setPedidoSet1(attachedPedidoSet1);
            em.persist(planprocesoscalidad);
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetDetalleplanprocesoscalidad : planprocesoscalidad.getDetalleplanprocesoscalidadSet()) {
                Planprocesoscalidad oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetDetalleplanprocesoscalidad = detalleplanprocesoscalidadSetDetalleplanprocesoscalidad.getPlanprocesoscalidad();
                detalleplanprocesoscalidadSetDetalleplanprocesoscalidad.setPlanprocesoscalidad(planprocesoscalidad);
                detalleplanprocesoscalidadSetDetalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadSetDetalleplanprocesoscalidad);
                if (oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetDetalleplanprocesoscalidad != null) {
                    oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetDetalleplanprocesoscalidad.getDetalleplanprocesoscalidadSet().remove(detalleplanprocesoscalidadSetDetalleplanprocesoscalidad);
                    oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetDetalleplanprocesoscalidad = em.merge(oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetDetalleplanprocesoscalidad);
                }
            }
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSet1Detalleplanprocesoscalidad : planprocesoscalidad.getDetalleplanprocesoscalidadSet1()) {
                Planprocesoscalidad oldPlanprocesoscalidad1OfDetalleplanprocesoscalidadSet1Detalleplanprocesoscalidad = detalleplanprocesoscalidadSet1Detalleplanprocesoscalidad.getPlanprocesoscalidad1();
                detalleplanprocesoscalidadSet1Detalleplanprocesoscalidad.setPlanprocesoscalidad1(planprocesoscalidad);
                detalleplanprocesoscalidadSet1Detalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadSet1Detalleplanprocesoscalidad);
                if (oldPlanprocesoscalidad1OfDetalleplanprocesoscalidadSet1Detalleplanprocesoscalidad != null) {
                    oldPlanprocesoscalidad1OfDetalleplanprocesoscalidadSet1Detalleplanprocesoscalidad.getDetalleplanprocesoscalidadSet1().remove(detalleplanprocesoscalidadSet1Detalleplanprocesoscalidad);
                    oldPlanprocesoscalidad1OfDetalleplanprocesoscalidadSet1Detalleplanprocesoscalidad = em.merge(oldPlanprocesoscalidad1OfDetalleplanprocesoscalidadSet1Detalleplanprocesoscalidad);
                }
            }
            for (Pedido pedidoSetPedido : planprocesoscalidad.getPedidoSet()) {
                Planprocesoscalidad oldPlanprocesoscalidadOfPedidoSetPedido = pedidoSetPedido.getPlanprocesoscalidad();
                pedidoSetPedido.setPlanprocesoscalidad(planprocesoscalidad);
                pedidoSetPedido = em.merge(pedidoSetPedido);
                if (oldPlanprocesoscalidadOfPedidoSetPedido != null) {
                    oldPlanprocesoscalidadOfPedidoSetPedido.getPedidoSet().remove(pedidoSetPedido);
                    oldPlanprocesoscalidadOfPedidoSetPedido = em.merge(oldPlanprocesoscalidadOfPedidoSetPedido);
                }
            }
            for (Pedido pedidoSet1Pedido : planprocesoscalidad.getPedidoSet1()) {
                Planprocesoscalidad oldPlanprocesoscalidad1OfPedidoSet1Pedido = pedidoSet1Pedido.getPlanprocesoscalidad1();
                pedidoSet1Pedido.setPlanprocesoscalidad1(planprocesoscalidad);
                pedidoSet1Pedido = em.merge(pedidoSet1Pedido);
                if (oldPlanprocesoscalidad1OfPedidoSet1Pedido != null) {
                    oldPlanprocesoscalidad1OfPedidoSet1Pedido.getPedidoSet1().remove(pedidoSet1Pedido);
                    oldPlanprocesoscalidad1OfPedidoSet1Pedido = em.merge(oldPlanprocesoscalidad1OfPedidoSet1Pedido);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPlanprocesoscalidad(planprocesoscalidad.getIdplanprocesoscalidad()) != null) {
                throw new PreexistingEntityException("Planprocesoscalidad " + planprocesoscalidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Planprocesoscalidad planprocesoscalidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planprocesoscalidad persistentPlanprocesoscalidad = em.find(Planprocesoscalidad.class, planprocesoscalidad.getIdplanprocesoscalidad());
            Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSetOld = persistentPlanprocesoscalidad.getDetalleplanprocesoscalidadSet();
            Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSetNew = planprocesoscalidad.getDetalleplanprocesoscalidadSet();
            Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSet1Old = persistentPlanprocesoscalidad.getDetalleplanprocesoscalidadSet1();
            Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSet1New = planprocesoscalidad.getDetalleplanprocesoscalidadSet1();
            Set<Pedido> pedidoSetOld = persistentPlanprocesoscalidad.getPedidoSet();
            Set<Pedido> pedidoSetNew = planprocesoscalidad.getPedidoSet();
            Set<Pedido> pedidoSet1Old = persistentPlanprocesoscalidad.getPedidoSet1();
            Set<Pedido> pedidoSet1New = planprocesoscalidad.getPedidoSet1();
            List<String> illegalOrphanMessages = null;
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetOldDetalleplanprocesoscalidad : detalleplanprocesoscalidadSetOld) {
                if (!detalleplanprocesoscalidadSetNew.contains(detalleplanprocesoscalidadSetOldDetalleplanprocesoscalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleplanprocesoscalidad " + detalleplanprocesoscalidadSetOldDetalleplanprocesoscalidad + " since its planprocesoscalidad field is not nullable.");
                }
            }
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSet1OldDetalleplanprocesoscalidad : detalleplanprocesoscalidadSet1Old) {
                if (!detalleplanprocesoscalidadSet1New.contains(detalleplanprocesoscalidadSet1OldDetalleplanprocesoscalidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detalleplanprocesoscalidad " + detalleplanprocesoscalidadSet1OldDetalleplanprocesoscalidad + " since its planprocesoscalidad1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Detalleplanprocesoscalidad> attachedDetalleplanprocesoscalidadSetNew = new HashSet<Detalleplanprocesoscalidad>();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidadToAttach : detalleplanprocesoscalidadSetNew) {
                detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidadToAttach = em.getReference(detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidadToAttach.getClass(), detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidadToAttach.getDetalleplanprocesoscalidadPK());
                attachedDetalleplanprocesoscalidadSetNew.add(detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidadToAttach);
            }
            detalleplanprocesoscalidadSetNew = attachedDetalleplanprocesoscalidadSetNew;
            planprocesoscalidad.setDetalleplanprocesoscalidadSet(detalleplanprocesoscalidadSetNew);
            Set<Detalleplanprocesoscalidad> attachedDetalleplanprocesoscalidadSet1New = new HashSet<Detalleplanprocesoscalidad>();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidadToAttach : detalleplanprocesoscalidadSet1New) {
                detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidadToAttach = em.getReference(detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidadToAttach.getClass(), detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidadToAttach.getDetalleplanprocesoscalidadPK());
                attachedDetalleplanprocesoscalidadSet1New.add(detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidadToAttach);
            }
            detalleplanprocesoscalidadSet1New = attachedDetalleplanprocesoscalidadSet1New;
            planprocesoscalidad.setDetalleplanprocesoscalidadSet1(detalleplanprocesoscalidadSet1New);
            Set<Pedido> attachedPedidoSetNew = new HashSet<Pedido>();
            for (Pedido pedidoSetNewPedidoToAttach : pedidoSetNew) {
                pedidoSetNewPedidoToAttach = em.getReference(pedidoSetNewPedidoToAttach.getClass(), pedidoSetNewPedidoToAttach.getIdpedido());
                attachedPedidoSetNew.add(pedidoSetNewPedidoToAttach);
            }
            pedidoSetNew = attachedPedidoSetNew;
            planprocesoscalidad.setPedidoSet(pedidoSetNew);
            Set<Pedido> attachedPedidoSet1New = new HashSet<Pedido>();
            for (Pedido pedidoSet1NewPedidoToAttach : pedidoSet1New) {
                pedidoSet1NewPedidoToAttach = em.getReference(pedidoSet1NewPedidoToAttach.getClass(), pedidoSet1NewPedidoToAttach.getIdpedido());
                attachedPedidoSet1New.add(pedidoSet1NewPedidoToAttach);
            }
            pedidoSet1New = attachedPedidoSet1New;
            planprocesoscalidad.setPedidoSet1(pedidoSet1New);
            planprocesoscalidad = em.merge(planprocesoscalidad);
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad : detalleplanprocesoscalidadSetNew) {
                if (!detalleplanprocesoscalidadSetOld.contains(detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad)) {
                    Planprocesoscalidad oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad = detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad.getPlanprocesoscalidad();
                    detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad.setPlanprocesoscalidad(planprocesoscalidad);
                    detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad);
                    if (oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad != null && !oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad.equals(planprocesoscalidad)) {
                        oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad.getDetalleplanprocesoscalidadSet().remove(detalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad);
                        oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad = em.merge(oldPlanprocesoscalidadOfDetalleplanprocesoscalidadSetNewDetalleplanprocesoscalidad);
                    }
                }
            }
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad : detalleplanprocesoscalidadSet1New) {
                if (!detalleplanprocesoscalidadSet1Old.contains(detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad)) {
                    Planprocesoscalidad oldPlanprocesoscalidad1OfDetalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad = detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad.getPlanprocesoscalidad1();
                    detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad.setPlanprocesoscalidad1(planprocesoscalidad);
                    detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad = em.merge(detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad);
                    if (oldPlanprocesoscalidad1OfDetalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad != null && !oldPlanprocesoscalidad1OfDetalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad.equals(planprocesoscalidad)) {
                        oldPlanprocesoscalidad1OfDetalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad.getDetalleplanprocesoscalidadSet1().remove(detalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad);
                        oldPlanprocesoscalidad1OfDetalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad = em.merge(oldPlanprocesoscalidad1OfDetalleplanprocesoscalidadSet1NewDetalleplanprocesoscalidad);
                    }
                }
            }
            for (Pedido pedidoSetOldPedido : pedidoSetOld) {
                if (!pedidoSetNew.contains(pedidoSetOldPedido)) {
                    pedidoSetOldPedido.setPlanprocesoscalidad(null);
                    pedidoSetOldPedido = em.merge(pedidoSetOldPedido);
                }
            }
            for (Pedido pedidoSetNewPedido : pedidoSetNew) {
                if (!pedidoSetOld.contains(pedidoSetNewPedido)) {
                    Planprocesoscalidad oldPlanprocesoscalidadOfPedidoSetNewPedido = pedidoSetNewPedido.getPlanprocesoscalidad();
                    pedidoSetNewPedido.setPlanprocesoscalidad(planprocesoscalidad);
                    pedidoSetNewPedido = em.merge(pedidoSetNewPedido);
                    if (oldPlanprocesoscalidadOfPedidoSetNewPedido != null && !oldPlanprocesoscalidadOfPedidoSetNewPedido.equals(planprocesoscalidad)) {
                        oldPlanprocesoscalidadOfPedidoSetNewPedido.getPedidoSet().remove(pedidoSetNewPedido);
                        oldPlanprocesoscalidadOfPedidoSetNewPedido = em.merge(oldPlanprocesoscalidadOfPedidoSetNewPedido);
                    }
                }
            }
            for (Pedido pedidoSet1OldPedido : pedidoSet1Old) {
                if (!pedidoSet1New.contains(pedidoSet1OldPedido)) {
                    pedidoSet1OldPedido.setPlanprocesoscalidad1(null);
                    pedidoSet1OldPedido = em.merge(pedidoSet1OldPedido);
                }
            }
            for (Pedido pedidoSet1NewPedido : pedidoSet1New) {
                if (!pedidoSet1Old.contains(pedidoSet1NewPedido)) {
                    Planprocesoscalidad oldPlanprocesoscalidad1OfPedidoSet1NewPedido = pedidoSet1NewPedido.getPlanprocesoscalidad1();
                    pedidoSet1NewPedido.setPlanprocesoscalidad1(planprocesoscalidad);
                    pedidoSet1NewPedido = em.merge(pedidoSet1NewPedido);
                    if (oldPlanprocesoscalidad1OfPedidoSet1NewPedido != null && !oldPlanprocesoscalidad1OfPedidoSet1NewPedido.equals(planprocesoscalidad)) {
                        oldPlanprocesoscalidad1OfPedidoSet1NewPedido.getPedidoSet1().remove(pedidoSet1NewPedido);
                        oldPlanprocesoscalidad1OfPedidoSet1NewPedido = em.merge(oldPlanprocesoscalidad1OfPedidoSet1NewPedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = planprocesoscalidad.getIdplanprocesoscalidad();
                if (findPlanprocesoscalidad(id) == null) {
                    throw new NonexistentEntityException("The planprocesoscalidad with id " + id + " no longer exists.");
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
            Planprocesoscalidad planprocesoscalidad;
            try {
                planprocesoscalidad = em.getReference(Planprocesoscalidad.class, id);
                planprocesoscalidad.getIdplanprocesoscalidad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planprocesoscalidad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSetOrphanCheck = planprocesoscalidad.getDetalleplanprocesoscalidadSet();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSetOrphanCheckDetalleplanprocesoscalidad : detalleplanprocesoscalidadSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planprocesoscalidad (" + planprocesoscalidad + ") cannot be destroyed since the Detalleplanprocesoscalidad " + detalleplanprocesoscalidadSetOrphanCheckDetalleplanprocesoscalidad + " in its detalleplanprocesoscalidadSet field has a non-nullable planprocesoscalidad field.");
            }
            Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidadSet1OrphanCheck = planprocesoscalidad.getDetalleplanprocesoscalidadSet1();
            for (Detalleplanprocesoscalidad detalleplanprocesoscalidadSet1OrphanCheckDetalleplanprocesoscalidad : detalleplanprocesoscalidadSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planprocesoscalidad (" + planprocesoscalidad + ") cannot be destroyed since the Detalleplanprocesoscalidad " + detalleplanprocesoscalidadSet1OrphanCheckDetalleplanprocesoscalidad + " in its detalleplanprocesoscalidadSet1 field has a non-nullable planprocesoscalidad1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Pedido> pedidoSet = planprocesoscalidad.getPedidoSet();
            for (Pedido pedidoSetPedido : pedidoSet) {
                pedidoSetPedido.setPlanprocesoscalidad(null);
                pedidoSetPedido = em.merge(pedidoSetPedido);
            }
            Set<Pedido> pedidoSet1 = planprocesoscalidad.getPedidoSet1();
            for (Pedido pedidoSet1Pedido : pedidoSet1) {
                pedidoSet1Pedido.setPlanprocesoscalidad1(null);
                pedidoSet1Pedido = em.merge(pedidoSet1Pedido);
            }
            em.remove(planprocesoscalidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Planprocesoscalidad> findPlanprocesoscalidadEntities() {
        return findPlanprocesoscalidadEntities(true, -1, -1);
    }

    public List<Planprocesoscalidad> findPlanprocesoscalidadEntities(int maxResults, int firstResult) {
        return findPlanprocesoscalidadEntities(false, maxResults, firstResult);
    }

    private List<Planprocesoscalidad> findPlanprocesoscalidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Planprocesoscalidad.class));
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

    public Planprocesoscalidad findPlanprocesoscalidad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Planprocesoscalidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanprocesoscalidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Planprocesoscalidad> rt = cq.from(Planprocesoscalidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
