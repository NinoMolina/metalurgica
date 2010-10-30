/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Planrequerimientosmateriaprima;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Pedido;
import java.util.HashSet;
import java.util.Set;
import entity.Detallerequerimientosmateriaprima;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class PlanrequerimientosmateriaprimaJpaController {

    public PlanrequerimientosmateriaprimaJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planrequerimientosmateriaprima planrequerimientosmateriaprima) throws PreexistingEntityException, Exception {
        if (planrequerimientosmateriaprima.getPedidoSet() == null) {
            planrequerimientosmateriaprima.setPedidoSet(new HashSet<Pedido>());
        }
        if (planrequerimientosmateriaprima.getPedidoSet1() == null) {
            planrequerimientosmateriaprima.setPedidoSet1(new HashSet<Pedido>());
        }
        if (planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet() == null) {
            planrequerimientosmateriaprima.setDetallerequerimientosmateriaprimaSet(new HashSet<Detallerequerimientosmateriaprima>());
        }
        if (planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet1() == null) {
            planrequerimientosmateriaprima.setDetallerequerimientosmateriaprimaSet1(new HashSet<Detallerequerimientosmateriaprima>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Set<Pedido> attachedPedidoSet = new HashSet<Pedido>();
            for (Pedido pedidoSetPedidoToAttach : planrequerimientosmateriaprima.getPedidoSet()) {
                pedidoSetPedidoToAttach = em.getReference(pedidoSetPedidoToAttach.getClass(), pedidoSetPedidoToAttach.getIdpedido());
                attachedPedidoSet.add(pedidoSetPedidoToAttach);
            }
            planrequerimientosmateriaprima.setPedidoSet(attachedPedidoSet);
            Set<Pedido> attachedPedidoSet1 = new HashSet<Pedido>();
            for (Pedido pedidoSet1PedidoToAttach : planrequerimientosmateriaprima.getPedidoSet1()) {
                pedidoSet1PedidoToAttach = em.getReference(pedidoSet1PedidoToAttach.getClass(), pedidoSet1PedidoToAttach.getIdpedido());
                attachedPedidoSet1.add(pedidoSet1PedidoToAttach);
            }
            planrequerimientosmateriaprima.setPedidoSet1(attachedPedidoSet1);
            Set<Detallerequerimientosmateriaprima> attachedDetallerequerimientosmateriaprimaSet = new HashSet<Detallerequerimientosmateriaprima>();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprimaToAttach : planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet()) {
                detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprimaToAttach = em.getReference(detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprimaToAttach.getClass(), detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprimaToAttach.getDetallerequerimientosmateriaprimaPK());
                attachedDetallerequerimientosmateriaprimaSet.add(detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprimaToAttach);
            }
            planrequerimientosmateriaprima.setDetallerequerimientosmateriaprimaSet(attachedDetallerequerimientosmateriaprimaSet);
            Set<Detallerequerimientosmateriaprima> attachedDetallerequerimientosmateriaprimaSet1 = new HashSet<Detallerequerimientosmateriaprima>();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSet1DetallerequerimientosmateriaprimaToAttach : planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet1()) {
                detallerequerimientosmateriaprimaSet1DetallerequerimientosmateriaprimaToAttach = em.getReference(detallerequerimientosmateriaprimaSet1DetallerequerimientosmateriaprimaToAttach.getClass(), detallerequerimientosmateriaprimaSet1DetallerequerimientosmateriaprimaToAttach.getDetallerequerimientosmateriaprimaPK());
                attachedDetallerequerimientosmateriaprimaSet1.add(detallerequerimientosmateriaprimaSet1DetallerequerimientosmateriaprimaToAttach);
            }
            planrequerimientosmateriaprima.setDetallerequerimientosmateriaprimaSet1(attachedDetallerequerimientosmateriaprimaSet1);
            em.persist(planrequerimientosmateriaprima);
            for (Pedido pedidoSetPedido : planrequerimientosmateriaprima.getPedidoSet()) {
                Planrequerimientosmateriaprima oldPlanrequerimientosmateriaprimaOfPedidoSetPedido = pedidoSetPedido.getPlanrequerimientosmateriaprima();
                pedidoSetPedido.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprima);
                pedidoSetPedido = em.merge(pedidoSetPedido);
                if (oldPlanrequerimientosmateriaprimaOfPedidoSetPedido != null) {
                    oldPlanrequerimientosmateriaprimaOfPedidoSetPedido.getPedidoSet().remove(pedidoSetPedido);
                    oldPlanrequerimientosmateriaprimaOfPedidoSetPedido = em.merge(oldPlanrequerimientosmateriaprimaOfPedidoSetPedido);
                }
            }
            for (Pedido pedidoSet1Pedido : planrequerimientosmateriaprima.getPedidoSet1()) {
                Planrequerimientosmateriaprima oldPlanrequerimientosmateriaprima1OfPedidoSet1Pedido = pedidoSet1Pedido.getPlanrequerimientosmateriaprima1();
                pedidoSet1Pedido.setPlanrequerimientosmateriaprima1(planrequerimientosmateriaprima);
                pedidoSet1Pedido = em.merge(pedidoSet1Pedido);
                if (oldPlanrequerimientosmateriaprima1OfPedidoSet1Pedido != null) {
                    oldPlanrequerimientosmateriaprima1OfPedidoSet1Pedido.getPedidoSet1().remove(pedidoSet1Pedido);
                    oldPlanrequerimientosmateriaprima1OfPedidoSet1Pedido = em.merge(oldPlanrequerimientosmateriaprima1OfPedidoSet1Pedido);
                }
            }
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima : planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet()) {
                Planrequerimientosmateriaprima oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima = detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima();
                detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprima);
                detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima);
                if (oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima != null) {
                    oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet().remove(detallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima);
                    oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima = em.merge(oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetDetallerequerimientosmateriaprima);
                }
            }
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima : planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet1()) {
                Planrequerimientosmateriaprima oldPlanrequerimientosmateriaprima1OfDetallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima = detallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima1();
                detallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima.setPlanrequerimientosmateriaprima1(planrequerimientosmateriaprima);
                detallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima);
                if (oldPlanrequerimientosmateriaprima1OfDetallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima != null) {
                    oldPlanrequerimientosmateriaprima1OfDetallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet1().remove(detallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima);
                    oldPlanrequerimientosmateriaprima1OfDetallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima = em.merge(oldPlanrequerimientosmateriaprima1OfDetallerequerimientosmateriaprimaSet1Detallerequerimientosmateriaprima);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPlanrequerimientosmateriaprima(planrequerimientosmateriaprima.getIdplanrequerimientosmateriaprima()) != null) {
                throw new PreexistingEntityException("Planrequerimientosmateriaprima " + planrequerimientosmateriaprima + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Planrequerimientosmateriaprima planrequerimientosmateriaprima) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planrequerimientosmateriaprima persistentPlanrequerimientosmateriaprima = em.find(Planrequerimientosmateriaprima.class, planrequerimientosmateriaprima.getIdplanrequerimientosmateriaprima());
            Set<Pedido> pedidoSetOld = persistentPlanrequerimientosmateriaprima.getPedidoSet();
            Set<Pedido> pedidoSetNew = planrequerimientosmateriaprima.getPedidoSet();
            Set<Pedido> pedidoSet1Old = persistentPlanrequerimientosmateriaprima.getPedidoSet1();
            Set<Pedido> pedidoSet1New = planrequerimientosmateriaprima.getPedidoSet1();
            Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSetOld = persistentPlanrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet();
            Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSetNew = planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet();
            Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSet1Old = persistentPlanrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet1();
            Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSet1New = planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet1();
            List<String> illegalOrphanMessages = null;
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSetOldDetallerequerimientosmateriaprima : detallerequerimientosmateriaprimaSetOld) {
                if (!detallerequerimientosmateriaprimaSetNew.contains(detallerequerimientosmateriaprimaSetOldDetallerequerimientosmateriaprima)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallerequerimientosmateriaprima " + detallerequerimientosmateriaprimaSetOldDetallerequerimientosmateriaprima + " since its planrequerimientosmateriaprima field is not nullable.");
                }
            }
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSet1OldDetallerequerimientosmateriaprima : detallerequerimientosmateriaprimaSet1Old) {
                if (!detallerequerimientosmateriaprimaSet1New.contains(detallerequerimientosmateriaprimaSet1OldDetallerequerimientosmateriaprima)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallerequerimientosmateriaprima " + detallerequerimientosmateriaprimaSet1OldDetallerequerimientosmateriaprima + " since its planrequerimientosmateriaprima1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Pedido> attachedPedidoSetNew = new HashSet<Pedido>();
            for (Pedido pedidoSetNewPedidoToAttach : pedidoSetNew) {
                pedidoSetNewPedidoToAttach = em.getReference(pedidoSetNewPedidoToAttach.getClass(), pedidoSetNewPedidoToAttach.getIdpedido());
                attachedPedidoSetNew.add(pedidoSetNewPedidoToAttach);
            }
            pedidoSetNew = attachedPedidoSetNew;
            planrequerimientosmateriaprima.setPedidoSet(pedidoSetNew);
            Set<Pedido> attachedPedidoSet1New = new HashSet<Pedido>();
            for (Pedido pedidoSet1NewPedidoToAttach : pedidoSet1New) {
                pedidoSet1NewPedidoToAttach = em.getReference(pedidoSet1NewPedidoToAttach.getClass(), pedidoSet1NewPedidoToAttach.getIdpedido());
                attachedPedidoSet1New.add(pedidoSet1NewPedidoToAttach);
            }
            pedidoSet1New = attachedPedidoSet1New;
            planrequerimientosmateriaprima.setPedidoSet1(pedidoSet1New);
            Set<Detallerequerimientosmateriaprima> attachedDetallerequerimientosmateriaprimaSetNew = new HashSet<Detallerequerimientosmateriaprima>();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprimaToAttach : detallerequerimientosmateriaprimaSetNew) {
                detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprimaToAttach = em.getReference(detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprimaToAttach.getClass(), detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprimaToAttach.getDetallerequerimientosmateriaprimaPK());
                attachedDetallerequerimientosmateriaprimaSetNew.add(detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprimaToAttach);
            }
            detallerequerimientosmateriaprimaSetNew = attachedDetallerequerimientosmateriaprimaSetNew;
            planrequerimientosmateriaprima.setDetallerequerimientosmateriaprimaSet(detallerequerimientosmateriaprimaSetNew);
            Set<Detallerequerimientosmateriaprima> attachedDetallerequerimientosmateriaprimaSet1New = new HashSet<Detallerequerimientosmateriaprima>();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprimaToAttach : detallerequerimientosmateriaprimaSet1New) {
                detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprimaToAttach = em.getReference(detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprimaToAttach.getClass(), detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprimaToAttach.getDetallerequerimientosmateriaprimaPK());
                attachedDetallerequerimientosmateriaprimaSet1New.add(detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprimaToAttach);
            }
            detallerequerimientosmateriaprimaSet1New = attachedDetallerequerimientosmateriaprimaSet1New;
            planrequerimientosmateriaprima.setDetallerequerimientosmateriaprimaSet1(detallerequerimientosmateriaprimaSet1New);
            planrequerimientosmateriaprima = em.merge(planrequerimientosmateriaprima);
            for (Pedido pedidoSetOldPedido : pedidoSetOld) {
                if (!pedidoSetNew.contains(pedidoSetOldPedido)) {
                    pedidoSetOldPedido.setPlanrequerimientosmateriaprima(null);
                    pedidoSetOldPedido = em.merge(pedidoSetOldPedido);
                }
            }
            for (Pedido pedidoSetNewPedido : pedidoSetNew) {
                if (!pedidoSetOld.contains(pedidoSetNewPedido)) {
                    Planrequerimientosmateriaprima oldPlanrequerimientosmateriaprimaOfPedidoSetNewPedido = pedidoSetNewPedido.getPlanrequerimientosmateriaprima();
                    pedidoSetNewPedido.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprima);
                    pedidoSetNewPedido = em.merge(pedidoSetNewPedido);
                    if (oldPlanrequerimientosmateriaprimaOfPedidoSetNewPedido != null && !oldPlanrequerimientosmateriaprimaOfPedidoSetNewPedido.equals(planrequerimientosmateriaprima)) {
                        oldPlanrequerimientosmateriaprimaOfPedidoSetNewPedido.getPedidoSet().remove(pedidoSetNewPedido);
                        oldPlanrequerimientosmateriaprimaOfPedidoSetNewPedido = em.merge(oldPlanrequerimientosmateriaprimaOfPedidoSetNewPedido);
                    }
                }
            }
            for (Pedido pedidoSet1OldPedido : pedidoSet1Old) {
                if (!pedidoSet1New.contains(pedidoSet1OldPedido)) {
                    pedidoSet1OldPedido.setPlanrequerimientosmateriaprima1(null);
                    pedidoSet1OldPedido = em.merge(pedidoSet1OldPedido);
                }
            }
            for (Pedido pedidoSet1NewPedido : pedidoSet1New) {
                if (!pedidoSet1Old.contains(pedidoSet1NewPedido)) {
                    Planrequerimientosmateriaprima oldPlanrequerimientosmateriaprima1OfPedidoSet1NewPedido = pedidoSet1NewPedido.getPlanrequerimientosmateriaprima1();
                    pedidoSet1NewPedido.setPlanrequerimientosmateriaprima1(planrequerimientosmateriaprima);
                    pedidoSet1NewPedido = em.merge(pedidoSet1NewPedido);
                    if (oldPlanrequerimientosmateriaprima1OfPedidoSet1NewPedido != null && !oldPlanrequerimientosmateriaprima1OfPedidoSet1NewPedido.equals(planrequerimientosmateriaprima)) {
                        oldPlanrequerimientosmateriaprima1OfPedidoSet1NewPedido.getPedidoSet1().remove(pedidoSet1NewPedido);
                        oldPlanrequerimientosmateriaprima1OfPedidoSet1NewPedido = em.merge(oldPlanrequerimientosmateriaprima1OfPedidoSet1NewPedido);
                    }
                }
            }
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima : detallerequerimientosmateriaprimaSetNew) {
                if (!detallerequerimientosmateriaprimaSetOld.contains(detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima)) {
                    Planrequerimientosmateriaprima oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima = detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima();
                    detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprima);
                    detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima);
                    if (oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima != null && !oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima.equals(planrequerimientosmateriaprima)) {
                        oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet().remove(detallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima);
                        oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima = em.merge(oldPlanrequerimientosmateriaprimaOfDetallerequerimientosmateriaprimaSetNewDetallerequerimientosmateriaprima);
                    }
                }
            }
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima : detallerequerimientosmateriaprimaSet1New) {
                if (!detallerequerimientosmateriaprimaSet1Old.contains(detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima)) {
                    Planrequerimientosmateriaprima oldPlanrequerimientosmateriaprima1OfDetallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima = detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima.getPlanrequerimientosmateriaprima1();
                    detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima.setPlanrequerimientosmateriaprima1(planrequerimientosmateriaprima);
                    detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima = em.merge(detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima);
                    if (oldPlanrequerimientosmateriaprima1OfDetallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima != null && !oldPlanrequerimientosmateriaprima1OfDetallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima.equals(planrequerimientosmateriaprima)) {
                        oldPlanrequerimientosmateriaprima1OfDetallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet1().remove(detallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima);
                        oldPlanrequerimientosmateriaprima1OfDetallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima = em.merge(oldPlanrequerimientosmateriaprima1OfDetallerequerimientosmateriaprimaSet1NewDetallerequerimientosmateriaprima);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = planrequerimientosmateriaprima.getIdplanrequerimientosmateriaprima();
                if (findPlanrequerimientosmateriaprima(id) == null) {
                    throw new NonexistentEntityException("The planrequerimientosmateriaprima with id " + id + " no longer exists.");
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
            Planrequerimientosmateriaprima planrequerimientosmateriaprima;
            try {
                planrequerimientosmateriaprima = em.getReference(Planrequerimientosmateriaprima.class, id);
                planrequerimientosmateriaprima.getIdplanrequerimientosmateriaprima();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planrequerimientosmateriaprima with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSetOrphanCheck = planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSetOrphanCheckDetallerequerimientosmateriaprima : detallerequerimientosmateriaprimaSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planrequerimientosmateriaprima (" + planrequerimientosmateriaprima + ") cannot be destroyed since the Detallerequerimientosmateriaprima " + detallerequerimientosmateriaprimaSetOrphanCheckDetallerequerimientosmateriaprima + " in its detallerequerimientosmateriaprimaSet field has a non-nullable planrequerimientosmateriaprima field.");
            }
            Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimaSet1OrphanCheck = planrequerimientosmateriaprima.getDetallerequerimientosmateriaprimaSet1();
            for (Detallerequerimientosmateriaprima detallerequerimientosmateriaprimaSet1OrphanCheckDetallerequerimientosmateriaprima : detallerequerimientosmateriaprimaSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planrequerimientosmateriaprima (" + planrequerimientosmateriaprima + ") cannot be destroyed since the Detallerequerimientosmateriaprima " + detallerequerimientosmateriaprimaSet1OrphanCheckDetallerequerimientosmateriaprima + " in its detallerequerimientosmateriaprimaSet1 field has a non-nullable planrequerimientosmateriaprima1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Set<Pedido> pedidoSet = planrequerimientosmateriaprima.getPedidoSet();
            for (Pedido pedidoSetPedido : pedidoSet) {
                pedidoSetPedido.setPlanrequerimientosmateriaprima(null);
                pedidoSetPedido = em.merge(pedidoSetPedido);
            }
            Set<Pedido> pedidoSet1 = planrequerimientosmateriaprima.getPedidoSet1();
            for (Pedido pedidoSet1Pedido : pedidoSet1) {
                pedidoSet1Pedido.setPlanrequerimientosmateriaprima1(null);
                pedidoSet1Pedido = em.merge(pedidoSet1Pedido);
            }
            em.remove(planrequerimientosmateriaprima);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Planrequerimientosmateriaprima> findPlanrequerimientosmateriaprimaEntities() {
        return findPlanrequerimientosmateriaprimaEntities(true, -1, -1);
    }

    public List<Planrequerimientosmateriaprima> findPlanrequerimientosmateriaprimaEntities(int maxResults, int firstResult) {
        return findPlanrequerimientosmateriaprimaEntities(false, maxResults, firstResult);
    }

    private List<Planrequerimientosmateriaprima> findPlanrequerimientosmateriaprimaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Planrequerimientosmateriaprima.class));
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

    public Planrequerimientosmateriaprima findPlanrequerimientosmateriaprima(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Planrequerimientosmateriaprima.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanrequerimientosmateriaprimaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Planrequerimientosmateriaprima> rt = cq.from(Planrequerimientosmateriaprima.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
