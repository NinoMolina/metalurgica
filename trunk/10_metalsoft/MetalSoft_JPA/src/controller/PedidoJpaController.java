/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import controller.exceptions.IllegalOrphanException;
import controller.exceptions.NonexistentEntityException;
import controller.exceptions.PreexistingEntityException;
import entity.Pedido;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entity.Cliente;
import entity.Estadopedido;
import entity.Factura;
import entity.Plano;
import entity.Planprocedimientos;
import entity.Planprocesoscalidad;
import entity.Planrequerimientosmateriaprima;
import entity.Presupuesto;
import entity.Prioridad;
import entity.Planificacionproduccion;
import java.util.HashSet;
import java.util.Set;
import entity.Productoreal;
import entity.Trabajotercerizado;
import entity.Planificacioncalidad;
import entity.Remito;
import entity.Detallefactura;
import entity.Detallepedido;
import java.util.ArrayList;

/**
 *
 * @author Nino
 */
public class PedidoJpaController {

    public PedidoJpaController() {
        emf = Persistence.createEntityManagerFactory("MetalSoft_JPAPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pedido pedido) throws PreexistingEntityException, Exception {
        if (pedido.getPlanificacionproduccionSet() == null) {
            pedido.setPlanificacionproduccionSet(new HashSet<Planificacionproduccion>());
        }
        if (pedido.getPlanificacionproduccionSet1() == null) {
            pedido.setPlanificacionproduccionSet1(new HashSet<Planificacionproduccion>());
        }
        if (pedido.getProductorealSet() == null) {
            pedido.setProductorealSet(new HashSet<Productoreal>());
        }
        if (pedido.getProductorealSet1() == null) {
            pedido.setProductorealSet1(new HashSet<Productoreal>());
        }
        if (pedido.getPlanoSet() == null) {
            pedido.setPlanoSet(new HashSet<Plano>());
        }
        if (pedido.getPlanoSet1() == null) {
            pedido.setPlanoSet1(new HashSet<Plano>());
        }
        if (pedido.getTrabajotercerizadoSet() == null) {
            pedido.setTrabajotercerizadoSet(new HashSet<Trabajotercerizado>());
        }
        if (pedido.getTrabajotercerizadoSet1() == null) {
            pedido.setTrabajotercerizadoSet1(new HashSet<Trabajotercerizado>());
        }
        if (pedido.getPlanificacioncalidadSet() == null) {
            pedido.setPlanificacioncalidadSet(new HashSet<Planificacioncalidad>());
        }
        if (pedido.getPlanificacioncalidadSet1() == null) {
            pedido.setPlanificacioncalidadSet1(new HashSet<Planificacioncalidad>());
        }
        if (pedido.getRemitoSet() == null) {
            pedido.setRemitoSet(new HashSet<Remito>());
        }
        if (pedido.getRemitoSet1() == null) {
            pedido.setRemitoSet1(new HashSet<Remito>());
        }
        if (pedido.getDetallefacturaSet() == null) {
            pedido.setDetallefacturaSet(new HashSet<Detallefactura>());
        }
        if (pedido.getDetallefacturaSet1() == null) {
            pedido.setDetallefacturaSet1(new HashSet<Detallefactura>());
        }
        if (pedido.getDetallepedidoSet() == null) {
            pedido.setDetallepedidoSet(new HashSet<Detallepedido>());
        }
        if (pedido.getDetallepedidoSet1() == null) {
            pedido.setDetallepedidoSet1(new HashSet<Detallepedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente cliente = pedido.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getIdcliente());
                pedido.setCliente(cliente);
            }
            Cliente cliente1 = pedido.getCliente1();
            if (cliente1 != null) {
                cliente1 = em.getReference(cliente1.getClass(), cliente1.getIdcliente());
                pedido.setCliente1(cliente1);
            }
            Estadopedido estado = pedido.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                pedido.setEstado(estado);
            }
            Estadopedido estado1 = pedido.getEstado1();
            if (estado1 != null) {
                estado1 = em.getReference(estado1.getClass(), estado1.getIdestado());
                pedido.setEstado1(estado1);
            }
            Factura factura = pedido.getFactura();
            if (factura != null) {
                factura = em.getReference(factura.getClass(), factura.getIdfactura());
                pedido.setFactura(factura);
            }
            Factura factura1 = pedido.getFactura1();
            if (factura1 != null) {
                factura1 = em.getReference(factura1.getClass(), factura1.getIdfactura());
                pedido.setFactura1(factura1);
            }
            Plano plano = pedido.getPlano();
            if (plano != null) {
                plano = em.getReference(plano.getClass(), plano.getIdplano());
                pedido.setPlano(plano);
            }
            Plano plano1 = pedido.getPlano1();
            if (plano1 != null) {
                plano1 = em.getReference(plano1.getClass(), plano1.getIdplano());
                pedido.setPlano1(plano1);
            }
            Planprocedimientos planprocedimientos = pedido.getPlanprocedimientos();
            if (planprocedimientos != null) {
                planprocedimientos = em.getReference(planprocedimientos.getClass(), planprocedimientos.getIdplanprocedimientos());
                pedido.setPlanprocedimientos(planprocedimientos);
            }
            Planprocedimientos planprocedimientos1 = pedido.getPlanprocedimientos1();
            if (planprocedimientos1 != null) {
                planprocedimientos1 = em.getReference(planprocedimientos1.getClass(), planprocedimientos1.getIdplanprocedimientos());
                pedido.setPlanprocedimientos1(planprocedimientos1);
            }
            Planprocesoscalidad planprocesoscalidad = pedido.getPlanprocesoscalidad();
            if (planprocesoscalidad != null) {
                planprocesoscalidad = em.getReference(planprocesoscalidad.getClass(), planprocesoscalidad.getIdplanprocesoscalidad());
                pedido.setPlanprocesoscalidad(planprocesoscalidad);
            }
            Planprocesoscalidad planprocesoscalidad1 = pedido.getPlanprocesoscalidad1();
            if (planprocesoscalidad1 != null) {
                planprocesoscalidad1 = em.getReference(planprocesoscalidad1.getClass(), planprocesoscalidad1.getIdplanprocesoscalidad());
                pedido.setPlanprocesoscalidad1(planprocesoscalidad1);
            }
            Planrequerimientosmateriaprima planrequerimientosmateriaprima = pedido.getPlanrequerimientosmateriaprima();
            if (planrequerimientosmateriaprima != null) {
                planrequerimientosmateriaprima = em.getReference(planrequerimientosmateriaprima.getClass(), planrequerimientosmateriaprima.getIdplanrequerimientosmateriaprima());
                pedido.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprima);
            }
            Planrequerimientosmateriaprima planrequerimientosmateriaprima1 = pedido.getPlanrequerimientosmateriaprima1();
            if (planrequerimientosmateriaprima1 != null) {
                planrequerimientosmateriaprima1 = em.getReference(planrequerimientosmateriaprima1.getClass(), planrequerimientosmateriaprima1.getIdplanrequerimientosmateriaprima());
                pedido.setPlanrequerimientosmateriaprima1(planrequerimientosmateriaprima1);
            }
            Presupuesto presupuesto = pedido.getPresupuesto();
            if (presupuesto != null) {
                presupuesto = em.getReference(presupuesto.getClass(), presupuesto.getIdpresupuesto());
                pedido.setPresupuesto(presupuesto);
            }
            Presupuesto presupuesto1 = pedido.getPresupuesto1();
            if (presupuesto1 != null) {
                presupuesto1 = em.getReference(presupuesto1.getClass(), presupuesto1.getIdpresupuesto());
                pedido.setPresupuesto1(presupuesto1);
            }
            Prioridad prioridad = pedido.getPrioridad();
            if (prioridad != null) {
                prioridad = em.getReference(prioridad.getClass(), prioridad.getIdprioridad());
                pedido.setPrioridad(prioridad);
            }
            Prioridad prioridad1 = pedido.getPrioridad1();
            if (prioridad1 != null) {
                prioridad1 = em.getReference(prioridad1.getClass(), prioridad1.getIdprioridad());
                pedido.setPrioridad1(prioridad1);
            }
            Set<Planificacionproduccion> attachedPlanificacionproduccionSet = new HashSet<Planificacionproduccion>();
            for (Planificacionproduccion planificacionproduccionSetPlanificacionproduccionToAttach : pedido.getPlanificacionproduccionSet()) {
                planificacionproduccionSetPlanificacionproduccionToAttach = em.getReference(planificacionproduccionSetPlanificacionproduccionToAttach.getClass(), planificacionproduccionSetPlanificacionproduccionToAttach.getIdplanificacionproduccion());
                attachedPlanificacionproduccionSet.add(planificacionproduccionSetPlanificacionproduccionToAttach);
            }
            pedido.setPlanificacionproduccionSet(attachedPlanificacionproduccionSet);
            Set<Planificacionproduccion> attachedPlanificacionproduccionSet1 = new HashSet<Planificacionproduccion>();
            for (Planificacionproduccion planificacionproduccionSet1PlanificacionproduccionToAttach : pedido.getPlanificacionproduccionSet1()) {
                planificacionproduccionSet1PlanificacionproduccionToAttach = em.getReference(planificacionproduccionSet1PlanificacionproduccionToAttach.getClass(), planificacionproduccionSet1PlanificacionproduccionToAttach.getIdplanificacionproduccion());
                attachedPlanificacionproduccionSet1.add(planificacionproduccionSet1PlanificacionproduccionToAttach);
            }
            pedido.setPlanificacionproduccionSet1(attachedPlanificacionproduccionSet1);
            Set<Productoreal> attachedProductorealSet = new HashSet<Productoreal>();
            for (Productoreal productorealSetProductorealToAttach : pedido.getProductorealSet()) {
                productorealSetProductorealToAttach = em.getReference(productorealSetProductorealToAttach.getClass(), productorealSetProductorealToAttach.getIdproductoreal());
                attachedProductorealSet.add(productorealSetProductorealToAttach);
            }
            pedido.setProductorealSet(attachedProductorealSet);
            Set<Productoreal> attachedProductorealSet1 = new HashSet<Productoreal>();
            for (Productoreal productorealSet1ProductorealToAttach : pedido.getProductorealSet1()) {
                productorealSet1ProductorealToAttach = em.getReference(productorealSet1ProductorealToAttach.getClass(), productorealSet1ProductorealToAttach.getIdproductoreal());
                attachedProductorealSet1.add(productorealSet1ProductorealToAttach);
            }
            pedido.setProductorealSet1(attachedProductorealSet1);
            Set<Plano> attachedPlanoSet = new HashSet<Plano>();
            for (Plano planoSetPlanoToAttach : pedido.getPlanoSet()) {
                planoSetPlanoToAttach = em.getReference(planoSetPlanoToAttach.getClass(), planoSetPlanoToAttach.getIdplano());
                attachedPlanoSet.add(planoSetPlanoToAttach);
            }
            pedido.setPlanoSet(attachedPlanoSet);
            Set<Plano> attachedPlanoSet1 = new HashSet<Plano>();
            for (Plano planoSet1PlanoToAttach : pedido.getPlanoSet1()) {
                planoSet1PlanoToAttach = em.getReference(planoSet1PlanoToAttach.getClass(), planoSet1PlanoToAttach.getIdplano());
                attachedPlanoSet1.add(planoSet1PlanoToAttach);
            }
            pedido.setPlanoSet1(attachedPlanoSet1);
            Set<Trabajotercerizado> attachedTrabajotercerizadoSet = new HashSet<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoSetTrabajotercerizadoToAttach : pedido.getTrabajotercerizadoSet()) {
                trabajotercerizadoSetTrabajotercerizadoToAttach = em.getReference(trabajotercerizadoSetTrabajotercerizadoToAttach.getClass(), trabajotercerizadoSetTrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoSet.add(trabajotercerizadoSetTrabajotercerizadoToAttach);
            }
            pedido.setTrabajotercerizadoSet(attachedTrabajotercerizadoSet);
            Set<Trabajotercerizado> attachedTrabajotercerizadoSet1 = new HashSet<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoSet1TrabajotercerizadoToAttach : pedido.getTrabajotercerizadoSet1()) {
                trabajotercerizadoSet1TrabajotercerizadoToAttach = em.getReference(trabajotercerizadoSet1TrabajotercerizadoToAttach.getClass(), trabajotercerizadoSet1TrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoSet1.add(trabajotercerizadoSet1TrabajotercerizadoToAttach);
            }
            pedido.setTrabajotercerizadoSet1(attachedTrabajotercerizadoSet1);
            Set<Planificacioncalidad> attachedPlanificacioncalidadSet = new HashSet<Planificacioncalidad>();
            for (Planificacioncalidad planificacioncalidadSetPlanificacioncalidadToAttach : pedido.getPlanificacioncalidadSet()) {
                planificacioncalidadSetPlanificacioncalidadToAttach = em.getReference(planificacioncalidadSetPlanificacioncalidadToAttach.getClass(), planificacioncalidadSetPlanificacioncalidadToAttach.getIdplanificacion());
                attachedPlanificacioncalidadSet.add(planificacioncalidadSetPlanificacioncalidadToAttach);
            }
            pedido.setPlanificacioncalidadSet(attachedPlanificacioncalidadSet);
            Set<Planificacioncalidad> attachedPlanificacioncalidadSet1 = new HashSet<Planificacioncalidad>();
            for (Planificacioncalidad planificacioncalidadSet1PlanificacioncalidadToAttach : pedido.getPlanificacioncalidadSet1()) {
                planificacioncalidadSet1PlanificacioncalidadToAttach = em.getReference(planificacioncalidadSet1PlanificacioncalidadToAttach.getClass(), planificacioncalidadSet1PlanificacioncalidadToAttach.getIdplanificacion());
                attachedPlanificacioncalidadSet1.add(planificacioncalidadSet1PlanificacioncalidadToAttach);
            }
            pedido.setPlanificacioncalidadSet1(attachedPlanificacioncalidadSet1);
            Set<Remito> attachedRemitoSet = new HashSet<Remito>();
            for (Remito remitoSetRemitoToAttach : pedido.getRemitoSet()) {
                remitoSetRemitoToAttach = em.getReference(remitoSetRemitoToAttach.getClass(), remitoSetRemitoToAttach.getIdremito());
                attachedRemitoSet.add(remitoSetRemitoToAttach);
            }
            pedido.setRemitoSet(attachedRemitoSet);
            Set<Remito> attachedRemitoSet1 = new HashSet<Remito>();
            for (Remito remitoSet1RemitoToAttach : pedido.getRemitoSet1()) {
                remitoSet1RemitoToAttach = em.getReference(remitoSet1RemitoToAttach.getClass(), remitoSet1RemitoToAttach.getIdremito());
                attachedRemitoSet1.add(remitoSet1RemitoToAttach);
            }
            pedido.setRemitoSet1(attachedRemitoSet1);
            Set<Detallefactura> attachedDetallefacturaSet = new HashSet<Detallefactura>();
            for (Detallefactura detallefacturaSetDetallefacturaToAttach : pedido.getDetallefacturaSet()) {
                detallefacturaSetDetallefacturaToAttach = em.getReference(detallefacturaSetDetallefacturaToAttach.getClass(), detallefacturaSetDetallefacturaToAttach.getDetallefacturaPK());
                attachedDetallefacturaSet.add(detallefacturaSetDetallefacturaToAttach);
            }
            pedido.setDetallefacturaSet(attachedDetallefacturaSet);
            Set<Detallefactura> attachedDetallefacturaSet1 = new HashSet<Detallefactura>();
            for (Detallefactura detallefacturaSet1DetallefacturaToAttach : pedido.getDetallefacturaSet1()) {
                detallefacturaSet1DetallefacturaToAttach = em.getReference(detallefacturaSet1DetallefacturaToAttach.getClass(), detallefacturaSet1DetallefacturaToAttach.getDetallefacturaPK());
                attachedDetallefacturaSet1.add(detallefacturaSet1DetallefacturaToAttach);
            }
            pedido.setDetallefacturaSet1(attachedDetallefacturaSet1);
            Set<Detallepedido> attachedDetallepedidoSet = new HashSet<Detallepedido>();
            for (Detallepedido detallepedidoSetDetallepedidoToAttach : pedido.getDetallepedidoSet()) {
                detallepedidoSetDetallepedidoToAttach = em.getReference(detallepedidoSetDetallepedidoToAttach.getClass(), detallepedidoSetDetallepedidoToAttach.getIddetalle());
                attachedDetallepedidoSet.add(detallepedidoSetDetallepedidoToAttach);
            }
            pedido.setDetallepedidoSet(attachedDetallepedidoSet);
            Set<Detallepedido> attachedDetallepedidoSet1 = new HashSet<Detallepedido>();
            for (Detallepedido detallepedidoSet1DetallepedidoToAttach : pedido.getDetallepedidoSet1()) {
                detallepedidoSet1DetallepedidoToAttach = em.getReference(detallepedidoSet1DetallepedidoToAttach.getClass(), detallepedidoSet1DetallepedidoToAttach.getIddetalle());
                attachedDetallepedidoSet1.add(detallepedidoSet1DetallepedidoToAttach);
            }
            pedido.setDetallepedidoSet1(attachedDetallepedidoSet1);
            em.persist(pedido);
            if (cliente != null) {
                cliente.getPedidoSet().add(pedido);
                cliente = em.merge(cliente);
            }
            if (cliente1 != null) {
                cliente1.getPedidoSet().add(pedido);
                cliente1 = em.merge(cliente1);
            }
            if (estado != null) {
                estado.getPedidoSet().add(pedido);
                estado = em.merge(estado);
            }
            if (estado1 != null) {
                estado1.getPedidoSet().add(pedido);
                estado1 = em.merge(estado1);
            }
            if (factura != null) {
                factura.getPedidoSet().add(pedido);
                factura = em.merge(factura);
            }
            if (factura1 != null) {
                factura1.getPedidoSet().add(pedido);
                factura1 = em.merge(factura1);
            }
            if (plano != null) {
                plano.getPedidoSet().add(pedido);
                plano = em.merge(plano);
            }
            if (plano1 != null) {
                plano1.getPedidoSet().add(pedido);
                plano1 = em.merge(plano1);
            }
            if (planprocedimientos != null) {
                planprocedimientos.getPedidoSet().add(pedido);
                planprocedimientos = em.merge(planprocedimientos);
            }
            if (planprocedimientos1 != null) {
                planprocedimientos1.getPedidoSet().add(pedido);
                planprocedimientos1 = em.merge(planprocedimientos1);
            }
            if (planprocesoscalidad != null) {
                planprocesoscalidad.getPedidoSet().add(pedido);
                planprocesoscalidad = em.merge(planprocesoscalidad);
            }
            if (planprocesoscalidad1 != null) {
                planprocesoscalidad1.getPedidoSet().add(pedido);
                planprocesoscalidad1 = em.merge(planprocesoscalidad1);
            }
            if (planrequerimientosmateriaprima != null) {
                planrequerimientosmateriaprima.getPedidoSet().add(pedido);
                planrequerimientosmateriaprima = em.merge(planrequerimientosmateriaprima);
            }
            if (planrequerimientosmateriaprima1 != null) {
                planrequerimientosmateriaprima1.getPedidoSet().add(pedido);
                planrequerimientosmateriaprima1 = em.merge(planrequerimientosmateriaprima1);
            }
            if (presupuesto != null) {
                presupuesto.getPedidoSet().add(pedido);
                presupuesto = em.merge(presupuesto);
            }
            if (presupuesto1 != null) {
                presupuesto1.getPedidoSet().add(pedido);
                presupuesto1 = em.merge(presupuesto1);
            }
            if (prioridad != null) {
                prioridad.getPedidoSet().add(pedido);
                prioridad = em.merge(prioridad);
            }
            if (prioridad1 != null) {
                prioridad1.getPedidoSet().add(pedido);
                prioridad1 = em.merge(prioridad1);
            }
            for (Planificacionproduccion planificacionproduccionSetPlanificacionproduccion : pedido.getPlanificacionproduccionSet()) {
                Pedido oldPedidoOfPlanificacionproduccionSetPlanificacionproduccion = planificacionproduccionSetPlanificacionproduccion.getPedido();
                planificacionproduccionSetPlanificacionproduccion.setPedido(pedido);
                planificacionproduccionSetPlanificacionproduccion = em.merge(planificacionproduccionSetPlanificacionproduccion);
                if (oldPedidoOfPlanificacionproduccionSetPlanificacionproduccion != null) {
                    oldPedidoOfPlanificacionproduccionSetPlanificacionproduccion.getPlanificacionproduccionSet().remove(planificacionproduccionSetPlanificacionproduccion);
                    oldPedidoOfPlanificacionproduccionSetPlanificacionproduccion = em.merge(oldPedidoOfPlanificacionproduccionSetPlanificacionproduccion);
                }
            }
            for (Planificacionproduccion planificacionproduccionSet1Planificacionproduccion : pedido.getPlanificacionproduccionSet1()) {
                Pedido oldPedido1OfPlanificacionproduccionSet1Planificacionproduccion = planificacionproduccionSet1Planificacionproduccion.getPedido1();
                planificacionproduccionSet1Planificacionproduccion.setPedido1(pedido);
                planificacionproduccionSet1Planificacionproduccion = em.merge(planificacionproduccionSet1Planificacionproduccion);
                if (oldPedido1OfPlanificacionproduccionSet1Planificacionproduccion != null) {
                    oldPedido1OfPlanificacionproduccionSet1Planificacionproduccion.getPlanificacionproduccionSet1().remove(planificacionproduccionSet1Planificacionproduccion);
                    oldPedido1OfPlanificacionproduccionSet1Planificacionproduccion = em.merge(oldPedido1OfPlanificacionproduccionSet1Planificacionproduccion);
                }
            }
            for (Productoreal productorealSetProductoreal : pedido.getProductorealSet()) {
                Pedido oldIdpedidoOfProductorealSetProductoreal = productorealSetProductoreal.getIdpedido();
                productorealSetProductoreal.setIdpedido(pedido);
                productorealSetProductoreal = em.merge(productorealSetProductoreal);
                if (oldIdpedidoOfProductorealSetProductoreal != null) {
                    oldIdpedidoOfProductorealSetProductoreal.getProductorealSet().remove(productorealSetProductoreal);
                    oldIdpedidoOfProductorealSetProductoreal = em.merge(oldIdpedidoOfProductorealSetProductoreal);
                }
            }
            for (Productoreal productorealSet1Productoreal : pedido.getProductorealSet1()) {
                Pedido oldIdpedido1OfProductorealSet1Productoreal = productorealSet1Productoreal.getIdpedido1();
                productorealSet1Productoreal.setIdpedido1(pedido);
                productorealSet1Productoreal = em.merge(productorealSet1Productoreal);
                if (oldIdpedido1OfProductorealSet1Productoreal != null) {
                    oldIdpedido1OfProductorealSet1Productoreal.getProductorealSet1().remove(productorealSet1Productoreal);
                    oldIdpedido1OfProductorealSet1Productoreal = em.merge(oldIdpedido1OfProductorealSet1Productoreal);
                }
            }
            for (Plano planoSetPlano : pedido.getPlanoSet()) {
                Pedido oldPedidoOfPlanoSetPlano = planoSetPlano.getPedido();
                planoSetPlano.setPedido(pedido);
                planoSetPlano = em.merge(planoSetPlano);
                if (oldPedidoOfPlanoSetPlano != null) {
                    oldPedidoOfPlanoSetPlano.getPlanoSet().remove(planoSetPlano);
                    oldPedidoOfPlanoSetPlano = em.merge(oldPedidoOfPlanoSetPlano);
                }
            }
            for (Plano planoSet1Plano : pedido.getPlanoSet1()) {
                Pedido oldPedido1OfPlanoSet1Plano = planoSet1Plano.getPedido1();
                planoSet1Plano.setPedido1(pedido);
                planoSet1Plano = em.merge(planoSet1Plano);
                if (oldPedido1OfPlanoSet1Plano != null) {
                    oldPedido1OfPlanoSet1Plano.getPlanoSet1().remove(planoSet1Plano);
                    oldPedido1OfPlanoSet1Plano = em.merge(oldPedido1OfPlanoSet1Plano);
                }
            }
            for (Trabajotercerizado trabajotercerizadoSetTrabajotercerizado : pedido.getTrabajotercerizadoSet()) {
                Pedido oldPedidoOfTrabajotercerizadoSetTrabajotercerizado = trabajotercerizadoSetTrabajotercerizado.getPedido();
                trabajotercerizadoSetTrabajotercerizado.setPedido(pedido);
                trabajotercerizadoSetTrabajotercerizado = em.merge(trabajotercerizadoSetTrabajotercerizado);
                if (oldPedidoOfTrabajotercerizadoSetTrabajotercerizado != null) {
                    oldPedidoOfTrabajotercerizadoSetTrabajotercerizado.getTrabajotercerizadoSet().remove(trabajotercerizadoSetTrabajotercerizado);
                    oldPedidoOfTrabajotercerizadoSetTrabajotercerizado = em.merge(oldPedidoOfTrabajotercerizadoSetTrabajotercerizado);
                }
            }
            for (Trabajotercerizado trabajotercerizadoSet1Trabajotercerizado : pedido.getTrabajotercerizadoSet1()) {
                Pedido oldPedido1OfTrabajotercerizadoSet1Trabajotercerizado = trabajotercerizadoSet1Trabajotercerizado.getPedido1();
                trabajotercerizadoSet1Trabajotercerizado.setPedido1(pedido);
                trabajotercerizadoSet1Trabajotercerizado = em.merge(trabajotercerizadoSet1Trabajotercerizado);
                if (oldPedido1OfTrabajotercerizadoSet1Trabajotercerizado != null) {
                    oldPedido1OfTrabajotercerizadoSet1Trabajotercerizado.getTrabajotercerizadoSet1().remove(trabajotercerizadoSet1Trabajotercerizado);
                    oldPedido1OfTrabajotercerizadoSet1Trabajotercerizado = em.merge(oldPedido1OfTrabajotercerizadoSet1Trabajotercerizado);
                }
            }
            for (Planificacioncalidad planificacioncalidadSetPlanificacioncalidad : pedido.getPlanificacioncalidadSet()) {
                Pedido oldPedidoOfPlanificacioncalidadSetPlanificacioncalidad = planificacioncalidadSetPlanificacioncalidad.getPedido();
                planificacioncalidadSetPlanificacioncalidad.setPedido(pedido);
                planificacioncalidadSetPlanificacioncalidad = em.merge(planificacioncalidadSetPlanificacioncalidad);
                if (oldPedidoOfPlanificacioncalidadSetPlanificacioncalidad != null) {
                    oldPedidoOfPlanificacioncalidadSetPlanificacioncalidad.getPlanificacioncalidadSet().remove(planificacioncalidadSetPlanificacioncalidad);
                    oldPedidoOfPlanificacioncalidadSetPlanificacioncalidad = em.merge(oldPedidoOfPlanificacioncalidadSetPlanificacioncalidad);
                }
            }
            for (Planificacioncalidad planificacioncalidadSet1Planificacioncalidad : pedido.getPlanificacioncalidadSet1()) {
                Pedido oldPedido1OfPlanificacioncalidadSet1Planificacioncalidad = planificacioncalidadSet1Planificacioncalidad.getPedido1();
                planificacioncalidadSet1Planificacioncalidad.setPedido1(pedido);
                planificacioncalidadSet1Planificacioncalidad = em.merge(planificacioncalidadSet1Planificacioncalidad);
                if (oldPedido1OfPlanificacioncalidadSet1Planificacioncalidad != null) {
                    oldPedido1OfPlanificacioncalidadSet1Planificacioncalidad.getPlanificacioncalidadSet1().remove(planificacioncalidadSet1Planificacioncalidad);
                    oldPedido1OfPlanificacioncalidadSet1Planificacioncalidad = em.merge(oldPedido1OfPlanificacioncalidadSet1Planificacioncalidad);
                }
            }
            for (Remito remitoSetRemito : pedido.getRemitoSet()) {
                Pedido oldPedidoOfRemitoSetRemito = remitoSetRemito.getPedido();
                remitoSetRemito.setPedido(pedido);
                remitoSetRemito = em.merge(remitoSetRemito);
                if (oldPedidoOfRemitoSetRemito != null) {
                    oldPedidoOfRemitoSetRemito.getRemitoSet().remove(remitoSetRemito);
                    oldPedidoOfRemitoSetRemito = em.merge(oldPedidoOfRemitoSetRemito);
                }
            }
            for (Remito remitoSet1Remito : pedido.getRemitoSet1()) {
                Pedido oldPedido1OfRemitoSet1Remito = remitoSet1Remito.getPedido1();
                remitoSet1Remito.setPedido1(pedido);
                remitoSet1Remito = em.merge(remitoSet1Remito);
                if (oldPedido1OfRemitoSet1Remito != null) {
                    oldPedido1OfRemitoSet1Remito.getRemitoSet1().remove(remitoSet1Remito);
                    oldPedido1OfRemitoSet1Remito = em.merge(oldPedido1OfRemitoSet1Remito);
                }
            }
            for (Detallefactura detallefacturaSetDetallefactura : pedido.getDetallefacturaSet()) {
                Pedido oldIdpedidoOfDetallefacturaSetDetallefactura = detallefacturaSetDetallefactura.getIdpedido();
                detallefacturaSetDetallefactura.setIdpedido(pedido);
                detallefacturaSetDetallefactura = em.merge(detallefacturaSetDetallefactura);
                if (oldIdpedidoOfDetallefacturaSetDetallefactura != null) {
                    oldIdpedidoOfDetallefacturaSetDetallefactura.getDetallefacturaSet().remove(detallefacturaSetDetallefactura);
                    oldIdpedidoOfDetallefacturaSetDetallefactura = em.merge(oldIdpedidoOfDetallefacturaSetDetallefactura);
                }
            }
            for (Detallefactura detallefacturaSet1Detallefactura : pedido.getDetallefacturaSet1()) {
                Pedido oldIdpedido1OfDetallefacturaSet1Detallefactura = detallefacturaSet1Detallefactura.getIdpedido1();
                detallefacturaSet1Detallefactura.setIdpedido1(pedido);
                detallefacturaSet1Detallefactura = em.merge(detallefacturaSet1Detallefactura);
                if (oldIdpedido1OfDetallefacturaSet1Detallefactura != null) {
                    oldIdpedido1OfDetallefacturaSet1Detallefactura.getDetallefacturaSet1().remove(detallefacturaSet1Detallefactura);
                    oldIdpedido1OfDetallefacturaSet1Detallefactura = em.merge(oldIdpedido1OfDetallefacturaSet1Detallefactura);
                }
            }
            for (Detallepedido detallepedidoSetDetallepedido : pedido.getDetallepedidoSet()) {
                Pedido oldIdpedidoOfDetallepedidoSetDetallepedido = detallepedidoSetDetallepedido.getIdpedido();
                detallepedidoSetDetallepedido.setIdpedido(pedido);
                detallepedidoSetDetallepedido = em.merge(detallepedidoSetDetallepedido);
                if (oldIdpedidoOfDetallepedidoSetDetallepedido != null) {
                    oldIdpedidoOfDetallepedidoSetDetallepedido.getDetallepedidoSet().remove(detallepedidoSetDetallepedido);
                    oldIdpedidoOfDetallepedidoSetDetallepedido = em.merge(oldIdpedidoOfDetallepedidoSetDetallepedido);
                }
            }
            for (Detallepedido detallepedidoSet1Detallepedido : pedido.getDetallepedidoSet1()) {
                Pedido oldIdpedido1OfDetallepedidoSet1Detallepedido = detallepedidoSet1Detallepedido.getIdpedido1();
                detallepedidoSet1Detallepedido.setIdpedido1(pedido);
                detallepedidoSet1Detallepedido = em.merge(detallepedidoSet1Detallepedido);
                if (oldIdpedido1OfDetallepedidoSet1Detallepedido != null) {
                    oldIdpedido1OfDetallepedidoSet1Detallepedido.getDetallepedidoSet1().remove(detallepedidoSet1Detallepedido);
                    oldIdpedido1OfDetallepedidoSet1Detallepedido = em.merge(oldIdpedido1OfDetallepedidoSet1Detallepedido);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPedido(pedido.getIdpedido()) != null) {
                throw new PreexistingEntityException("Pedido " + pedido + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pedido pedido) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedido persistentPedido = em.find(Pedido.class, pedido.getIdpedido());
            Cliente clienteOld = persistentPedido.getCliente();
            Cliente clienteNew = pedido.getCliente();
            Cliente cliente1Old = persistentPedido.getCliente1();
            Cliente cliente1New = pedido.getCliente1();
            Estadopedido estadoOld = persistentPedido.getEstado();
            Estadopedido estadoNew = pedido.getEstado();
            Estadopedido estado1Old = persistentPedido.getEstado1();
            Estadopedido estado1New = pedido.getEstado1();
            Factura facturaOld = persistentPedido.getFactura();
            Factura facturaNew = pedido.getFactura();
            Factura factura1Old = persistentPedido.getFactura1();
            Factura factura1New = pedido.getFactura1();
            Plano planoOld = persistentPedido.getPlano();
            Plano planoNew = pedido.getPlano();
            Plano plano1Old = persistentPedido.getPlano1();
            Plano plano1New = pedido.getPlano1();
            Planprocedimientos planprocedimientosOld = persistentPedido.getPlanprocedimientos();
            Planprocedimientos planprocedimientosNew = pedido.getPlanprocedimientos();
            Planprocedimientos planprocedimientos1Old = persistentPedido.getPlanprocedimientos1();
            Planprocedimientos planprocedimientos1New = pedido.getPlanprocedimientos1();
            Planprocesoscalidad planprocesoscalidadOld = persistentPedido.getPlanprocesoscalidad();
            Planprocesoscalidad planprocesoscalidadNew = pedido.getPlanprocesoscalidad();
            Planprocesoscalidad planprocesoscalidad1Old = persistentPedido.getPlanprocesoscalidad1();
            Planprocesoscalidad planprocesoscalidad1New = pedido.getPlanprocesoscalidad1();
            Planrequerimientosmateriaprima planrequerimientosmateriaprimaOld = persistentPedido.getPlanrequerimientosmateriaprima();
            Planrequerimientosmateriaprima planrequerimientosmateriaprimaNew = pedido.getPlanrequerimientosmateriaprima();
            Planrequerimientosmateriaprima planrequerimientosmateriaprima1Old = persistentPedido.getPlanrequerimientosmateriaprima1();
            Planrequerimientosmateriaprima planrequerimientosmateriaprima1New = pedido.getPlanrequerimientosmateriaprima1();
            Presupuesto presupuestoOld = persistentPedido.getPresupuesto();
            Presupuesto presupuestoNew = pedido.getPresupuesto();
            Presupuesto presupuesto1Old = persistentPedido.getPresupuesto1();
            Presupuesto presupuesto1New = pedido.getPresupuesto1();
            Prioridad prioridadOld = persistentPedido.getPrioridad();
            Prioridad prioridadNew = pedido.getPrioridad();
            Prioridad prioridad1Old = persistentPedido.getPrioridad1();
            Prioridad prioridad1New = pedido.getPrioridad1();
            Set<Planificacionproduccion> planificacionproduccionSetOld = persistentPedido.getPlanificacionproduccionSet();
            Set<Planificacionproduccion> planificacionproduccionSetNew = pedido.getPlanificacionproduccionSet();
            Set<Planificacionproduccion> planificacionproduccionSet1Old = persistentPedido.getPlanificacionproduccionSet1();
            Set<Planificacionproduccion> planificacionproduccionSet1New = pedido.getPlanificacionproduccionSet1();
            Set<Productoreal> productorealSetOld = persistentPedido.getProductorealSet();
            Set<Productoreal> productorealSetNew = pedido.getProductorealSet();
            Set<Productoreal> productorealSet1Old = persistentPedido.getProductorealSet1();
            Set<Productoreal> productorealSet1New = pedido.getProductorealSet1();
            Set<Plano> planoSetOld = persistentPedido.getPlanoSet();
            Set<Plano> planoSetNew = pedido.getPlanoSet();
            Set<Plano> planoSet1Old = persistentPedido.getPlanoSet1();
            Set<Plano> planoSet1New = pedido.getPlanoSet1();
            Set<Trabajotercerizado> trabajotercerizadoSetOld = persistentPedido.getTrabajotercerizadoSet();
            Set<Trabajotercerizado> trabajotercerizadoSetNew = pedido.getTrabajotercerizadoSet();
            Set<Trabajotercerizado> trabajotercerizadoSet1Old = persistentPedido.getTrabajotercerizadoSet1();
            Set<Trabajotercerizado> trabajotercerizadoSet1New = pedido.getTrabajotercerizadoSet1();
            Set<Planificacioncalidad> planificacioncalidadSetOld = persistentPedido.getPlanificacioncalidadSet();
            Set<Planificacioncalidad> planificacioncalidadSetNew = pedido.getPlanificacioncalidadSet();
            Set<Planificacioncalidad> planificacioncalidadSet1Old = persistentPedido.getPlanificacioncalidadSet1();
            Set<Planificacioncalidad> planificacioncalidadSet1New = pedido.getPlanificacioncalidadSet1();
            Set<Remito> remitoSetOld = persistentPedido.getRemitoSet();
            Set<Remito> remitoSetNew = pedido.getRemitoSet();
            Set<Remito> remitoSet1Old = persistentPedido.getRemitoSet1();
            Set<Remito> remitoSet1New = pedido.getRemitoSet1();
            Set<Detallefactura> detallefacturaSetOld = persistentPedido.getDetallefacturaSet();
            Set<Detallefactura> detallefacturaSetNew = pedido.getDetallefacturaSet();
            Set<Detallefactura> detallefacturaSet1Old = persistentPedido.getDetallefacturaSet1();
            Set<Detallefactura> detallefacturaSet1New = pedido.getDetallefacturaSet1();
            Set<Detallepedido> detallepedidoSetOld = persistentPedido.getDetallepedidoSet();
            Set<Detallepedido> detallepedidoSetNew = pedido.getDetallepedidoSet();
            Set<Detallepedido> detallepedidoSet1Old = persistentPedido.getDetallepedidoSet1();
            Set<Detallepedido> detallepedidoSet1New = pedido.getDetallepedidoSet1();
            List<String> illegalOrphanMessages = null;
            for (Detallepedido detallepedidoSetOldDetallepedido : detallepedidoSetOld) {
                if (!detallepedidoSetNew.contains(detallepedidoSetOldDetallepedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallepedido " + detallepedidoSetOldDetallepedido + " since its idpedido field is not nullable.");
                }
            }
            for (Detallepedido detallepedidoSet1OldDetallepedido : detallepedidoSet1Old) {
                if (!detallepedidoSet1New.contains(detallepedidoSet1OldDetallepedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallepedido " + detallepedidoSet1OldDetallepedido + " since its idpedido1 field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getIdcliente());
                pedido.setCliente(clienteNew);
            }
            if (cliente1New != null) {
                cliente1New = em.getReference(cliente1New.getClass(), cliente1New.getIdcliente());
                pedido.setCliente1(cliente1New);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                pedido.setEstado(estadoNew);
            }
            if (estado1New != null) {
                estado1New = em.getReference(estado1New.getClass(), estado1New.getIdestado());
                pedido.setEstado1(estado1New);
            }
            if (facturaNew != null) {
                facturaNew = em.getReference(facturaNew.getClass(), facturaNew.getIdfactura());
                pedido.setFactura(facturaNew);
            }
            if (factura1New != null) {
                factura1New = em.getReference(factura1New.getClass(), factura1New.getIdfactura());
                pedido.setFactura1(factura1New);
            }
            if (planoNew != null) {
                planoNew = em.getReference(planoNew.getClass(), planoNew.getIdplano());
                pedido.setPlano(planoNew);
            }
            if (plano1New != null) {
                plano1New = em.getReference(plano1New.getClass(), plano1New.getIdplano());
                pedido.setPlano1(plano1New);
            }
            if (planprocedimientosNew != null) {
                planprocedimientosNew = em.getReference(planprocedimientosNew.getClass(), planprocedimientosNew.getIdplanprocedimientos());
                pedido.setPlanprocedimientos(planprocedimientosNew);
            }
            if (planprocedimientos1New != null) {
                planprocedimientos1New = em.getReference(planprocedimientos1New.getClass(), planprocedimientos1New.getIdplanprocedimientos());
                pedido.setPlanprocedimientos1(planprocedimientos1New);
            }
            if (planprocesoscalidadNew != null) {
                planprocesoscalidadNew = em.getReference(planprocesoscalidadNew.getClass(), planprocesoscalidadNew.getIdplanprocesoscalidad());
                pedido.setPlanprocesoscalidad(planprocesoscalidadNew);
            }
            if (planprocesoscalidad1New != null) {
                planprocesoscalidad1New = em.getReference(planprocesoscalidad1New.getClass(), planprocesoscalidad1New.getIdplanprocesoscalidad());
                pedido.setPlanprocesoscalidad1(planprocesoscalidad1New);
            }
            if (planrequerimientosmateriaprimaNew != null) {
                planrequerimientosmateriaprimaNew = em.getReference(planrequerimientosmateriaprimaNew.getClass(), planrequerimientosmateriaprimaNew.getIdplanrequerimientosmateriaprima());
                pedido.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprimaNew);
            }
            if (planrequerimientosmateriaprima1New != null) {
                planrequerimientosmateriaprima1New = em.getReference(planrequerimientosmateriaprima1New.getClass(), planrequerimientosmateriaprima1New.getIdplanrequerimientosmateriaprima());
                pedido.setPlanrequerimientosmateriaprima1(planrequerimientosmateriaprima1New);
            }
            if (presupuestoNew != null) {
                presupuestoNew = em.getReference(presupuestoNew.getClass(), presupuestoNew.getIdpresupuesto());
                pedido.setPresupuesto(presupuestoNew);
            }
            if (presupuesto1New != null) {
                presupuesto1New = em.getReference(presupuesto1New.getClass(), presupuesto1New.getIdpresupuesto());
                pedido.setPresupuesto1(presupuesto1New);
            }
            if (prioridadNew != null) {
                prioridadNew = em.getReference(prioridadNew.getClass(), prioridadNew.getIdprioridad());
                pedido.setPrioridad(prioridadNew);
            }
            if (prioridad1New != null) {
                prioridad1New = em.getReference(prioridad1New.getClass(), prioridad1New.getIdprioridad());
                pedido.setPrioridad1(prioridad1New);
            }
            Set<Planificacionproduccion> attachedPlanificacionproduccionSetNew = new HashSet<Planificacionproduccion>();
            for (Planificacionproduccion planificacionproduccionSetNewPlanificacionproduccionToAttach : planificacionproduccionSetNew) {
                planificacionproduccionSetNewPlanificacionproduccionToAttach = em.getReference(planificacionproduccionSetNewPlanificacionproduccionToAttach.getClass(), planificacionproduccionSetNewPlanificacionproduccionToAttach.getIdplanificacionproduccion());
                attachedPlanificacionproduccionSetNew.add(planificacionproduccionSetNewPlanificacionproduccionToAttach);
            }
            planificacionproduccionSetNew = attachedPlanificacionproduccionSetNew;
            pedido.setPlanificacionproduccionSet(planificacionproduccionSetNew);
            Set<Planificacionproduccion> attachedPlanificacionproduccionSet1New = new HashSet<Planificacionproduccion>();
            for (Planificacionproduccion planificacionproduccionSet1NewPlanificacionproduccionToAttach : planificacionproduccionSet1New) {
                planificacionproduccionSet1NewPlanificacionproduccionToAttach = em.getReference(planificacionproduccionSet1NewPlanificacionproduccionToAttach.getClass(), planificacionproduccionSet1NewPlanificacionproduccionToAttach.getIdplanificacionproduccion());
                attachedPlanificacionproduccionSet1New.add(planificacionproduccionSet1NewPlanificacionproduccionToAttach);
            }
            planificacionproduccionSet1New = attachedPlanificacionproduccionSet1New;
            pedido.setPlanificacionproduccionSet1(planificacionproduccionSet1New);
            Set<Productoreal> attachedProductorealSetNew = new HashSet<Productoreal>();
            for (Productoreal productorealSetNewProductorealToAttach : productorealSetNew) {
                productorealSetNewProductorealToAttach = em.getReference(productorealSetNewProductorealToAttach.getClass(), productorealSetNewProductorealToAttach.getIdproductoreal());
                attachedProductorealSetNew.add(productorealSetNewProductorealToAttach);
            }
            productorealSetNew = attachedProductorealSetNew;
            pedido.setProductorealSet(productorealSetNew);
            Set<Productoreal> attachedProductorealSet1New = new HashSet<Productoreal>();
            for (Productoreal productorealSet1NewProductorealToAttach : productorealSet1New) {
                productorealSet1NewProductorealToAttach = em.getReference(productorealSet1NewProductorealToAttach.getClass(), productorealSet1NewProductorealToAttach.getIdproductoreal());
                attachedProductorealSet1New.add(productorealSet1NewProductorealToAttach);
            }
            productorealSet1New = attachedProductorealSet1New;
            pedido.setProductorealSet1(productorealSet1New);
            Set<Plano> attachedPlanoSetNew = new HashSet<Plano>();
            for (Plano planoSetNewPlanoToAttach : planoSetNew) {
                planoSetNewPlanoToAttach = em.getReference(planoSetNewPlanoToAttach.getClass(), planoSetNewPlanoToAttach.getIdplano());
                attachedPlanoSetNew.add(planoSetNewPlanoToAttach);
            }
            planoSetNew = attachedPlanoSetNew;
            pedido.setPlanoSet(planoSetNew);
            Set<Plano> attachedPlanoSet1New = new HashSet<Plano>();
            for (Plano planoSet1NewPlanoToAttach : planoSet1New) {
                planoSet1NewPlanoToAttach = em.getReference(planoSet1NewPlanoToAttach.getClass(), planoSet1NewPlanoToAttach.getIdplano());
                attachedPlanoSet1New.add(planoSet1NewPlanoToAttach);
            }
            planoSet1New = attachedPlanoSet1New;
            pedido.setPlanoSet1(planoSet1New);
            Set<Trabajotercerizado> attachedTrabajotercerizadoSetNew = new HashSet<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoSetNewTrabajotercerizadoToAttach : trabajotercerizadoSetNew) {
                trabajotercerizadoSetNewTrabajotercerizadoToAttach = em.getReference(trabajotercerizadoSetNewTrabajotercerizadoToAttach.getClass(), trabajotercerizadoSetNewTrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoSetNew.add(trabajotercerizadoSetNewTrabajotercerizadoToAttach);
            }
            trabajotercerizadoSetNew = attachedTrabajotercerizadoSetNew;
            pedido.setTrabajotercerizadoSet(trabajotercerizadoSetNew);
            Set<Trabajotercerizado> attachedTrabajotercerizadoSet1New = new HashSet<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoSet1NewTrabajotercerizadoToAttach : trabajotercerizadoSet1New) {
                trabajotercerizadoSet1NewTrabajotercerizadoToAttach = em.getReference(trabajotercerizadoSet1NewTrabajotercerizadoToAttach.getClass(), trabajotercerizadoSet1NewTrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoSet1New.add(trabajotercerizadoSet1NewTrabajotercerizadoToAttach);
            }
            trabajotercerizadoSet1New = attachedTrabajotercerizadoSet1New;
            pedido.setTrabajotercerizadoSet1(trabajotercerizadoSet1New);
            Set<Planificacioncalidad> attachedPlanificacioncalidadSetNew = new HashSet<Planificacioncalidad>();
            for (Planificacioncalidad planificacioncalidadSetNewPlanificacioncalidadToAttach : planificacioncalidadSetNew) {
                planificacioncalidadSetNewPlanificacioncalidadToAttach = em.getReference(planificacioncalidadSetNewPlanificacioncalidadToAttach.getClass(), planificacioncalidadSetNewPlanificacioncalidadToAttach.getIdplanificacion());
                attachedPlanificacioncalidadSetNew.add(planificacioncalidadSetNewPlanificacioncalidadToAttach);
            }
            planificacioncalidadSetNew = attachedPlanificacioncalidadSetNew;
            pedido.setPlanificacioncalidadSet(planificacioncalidadSetNew);
            Set<Planificacioncalidad> attachedPlanificacioncalidadSet1New = new HashSet<Planificacioncalidad>();
            for (Planificacioncalidad planificacioncalidadSet1NewPlanificacioncalidadToAttach : planificacioncalidadSet1New) {
                planificacioncalidadSet1NewPlanificacioncalidadToAttach = em.getReference(planificacioncalidadSet1NewPlanificacioncalidadToAttach.getClass(), planificacioncalidadSet1NewPlanificacioncalidadToAttach.getIdplanificacion());
                attachedPlanificacioncalidadSet1New.add(planificacioncalidadSet1NewPlanificacioncalidadToAttach);
            }
            planificacioncalidadSet1New = attachedPlanificacioncalidadSet1New;
            pedido.setPlanificacioncalidadSet1(planificacioncalidadSet1New);
            Set<Remito> attachedRemitoSetNew = new HashSet<Remito>();
            for (Remito remitoSetNewRemitoToAttach : remitoSetNew) {
                remitoSetNewRemitoToAttach = em.getReference(remitoSetNewRemitoToAttach.getClass(), remitoSetNewRemitoToAttach.getIdremito());
                attachedRemitoSetNew.add(remitoSetNewRemitoToAttach);
            }
            remitoSetNew = attachedRemitoSetNew;
            pedido.setRemitoSet(remitoSetNew);
            Set<Remito> attachedRemitoSet1New = new HashSet<Remito>();
            for (Remito remitoSet1NewRemitoToAttach : remitoSet1New) {
                remitoSet1NewRemitoToAttach = em.getReference(remitoSet1NewRemitoToAttach.getClass(), remitoSet1NewRemitoToAttach.getIdremito());
                attachedRemitoSet1New.add(remitoSet1NewRemitoToAttach);
            }
            remitoSet1New = attachedRemitoSet1New;
            pedido.setRemitoSet1(remitoSet1New);
            Set<Detallefactura> attachedDetallefacturaSetNew = new HashSet<Detallefactura>();
            for (Detallefactura detallefacturaSetNewDetallefacturaToAttach : detallefacturaSetNew) {
                detallefacturaSetNewDetallefacturaToAttach = em.getReference(detallefacturaSetNewDetallefacturaToAttach.getClass(), detallefacturaSetNewDetallefacturaToAttach.getDetallefacturaPK());
                attachedDetallefacturaSetNew.add(detallefacturaSetNewDetallefacturaToAttach);
            }
            detallefacturaSetNew = attachedDetallefacturaSetNew;
            pedido.setDetallefacturaSet(detallefacturaSetNew);
            Set<Detallefactura> attachedDetallefacturaSet1New = new HashSet<Detallefactura>();
            for (Detallefactura detallefacturaSet1NewDetallefacturaToAttach : detallefacturaSet1New) {
                detallefacturaSet1NewDetallefacturaToAttach = em.getReference(detallefacturaSet1NewDetallefacturaToAttach.getClass(), detallefacturaSet1NewDetallefacturaToAttach.getDetallefacturaPK());
                attachedDetallefacturaSet1New.add(detallefacturaSet1NewDetallefacturaToAttach);
            }
            detallefacturaSet1New = attachedDetallefacturaSet1New;
            pedido.setDetallefacturaSet1(detallefacturaSet1New);
            Set<Detallepedido> attachedDetallepedidoSetNew = new HashSet<Detallepedido>();
            for (Detallepedido detallepedidoSetNewDetallepedidoToAttach : detallepedidoSetNew) {
                detallepedidoSetNewDetallepedidoToAttach = em.getReference(detallepedidoSetNewDetallepedidoToAttach.getClass(), detallepedidoSetNewDetallepedidoToAttach.getIddetalle());
                attachedDetallepedidoSetNew.add(detallepedidoSetNewDetallepedidoToAttach);
            }
            detallepedidoSetNew = attachedDetallepedidoSetNew;
            pedido.setDetallepedidoSet(detallepedidoSetNew);
            Set<Detallepedido> attachedDetallepedidoSet1New = new HashSet<Detallepedido>();
            for (Detallepedido detallepedidoSet1NewDetallepedidoToAttach : detallepedidoSet1New) {
                detallepedidoSet1NewDetallepedidoToAttach = em.getReference(detallepedidoSet1NewDetallepedidoToAttach.getClass(), detallepedidoSet1NewDetallepedidoToAttach.getIddetalle());
                attachedDetallepedidoSet1New.add(detallepedidoSet1NewDetallepedidoToAttach);
            }
            detallepedidoSet1New = attachedDetallepedidoSet1New;
            pedido.setDetallepedidoSet1(detallepedidoSet1New);
            pedido = em.merge(pedido);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getPedidoSet().remove(pedido);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getPedidoSet().add(pedido);
                clienteNew = em.merge(clienteNew);
            }
            if (cliente1Old != null && !cliente1Old.equals(cliente1New)) {
                cliente1Old.getPedidoSet().remove(pedido);
                cliente1Old = em.merge(cliente1Old);
            }
            if (cliente1New != null && !cliente1New.equals(cliente1Old)) {
                cliente1New.getPedidoSet().add(pedido);
                cliente1New = em.merge(cliente1New);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getPedidoSet().remove(pedido);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getPedidoSet().add(pedido);
                estadoNew = em.merge(estadoNew);
            }
            if (estado1Old != null && !estado1Old.equals(estado1New)) {
                estado1Old.getPedidoSet().remove(pedido);
                estado1Old = em.merge(estado1Old);
            }
            if (estado1New != null && !estado1New.equals(estado1Old)) {
                estado1New.getPedidoSet().add(pedido);
                estado1New = em.merge(estado1New);
            }
            if (facturaOld != null && !facturaOld.equals(facturaNew)) {
                facturaOld.getPedidoSet().remove(pedido);
                facturaOld = em.merge(facturaOld);
            }
            if (facturaNew != null && !facturaNew.equals(facturaOld)) {
                facturaNew.getPedidoSet().add(pedido);
                facturaNew = em.merge(facturaNew);
            }
            if (factura1Old != null && !factura1Old.equals(factura1New)) {
                factura1Old.getPedidoSet().remove(pedido);
                factura1Old = em.merge(factura1Old);
            }
            if (factura1New != null && !factura1New.equals(factura1Old)) {
                factura1New.getPedidoSet().add(pedido);
                factura1New = em.merge(factura1New);
            }
            if (planoOld != null && !planoOld.equals(planoNew)) {
                planoOld.getPedidoSet().remove(pedido);
                planoOld = em.merge(planoOld);
            }
            if (planoNew != null && !planoNew.equals(planoOld)) {
                planoNew.getPedidoSet().add(pedido);
                planoNew = em.merge(planoNew);
            }
            if (plano1Old != null && !plano1Old.equals(plano1New)) {
                plano1Old.getPedidoSet().remove(pedido);
                plano1Old = em.merge(plano1Old);
            }
            if (plano1New != null && !plano1New.equals(plano1Old)) {
                plano1New.getPedidoSet().add(pedido);
                plano1New = em.merge(plano1New);
            }
            if (planprocedimientosOld != null && !planprocedimientosOld.equals(planprocedimientosNew)) {
                planprocedimientosOld.getPedidoSet().remove(pedido);
                planprocedimientosOld = em.merge(planprocedimientosOld);
            }
            if (planprocedimientosNew != null && !planprocedimientosNew.equals(planprocedimientosOld)) {
                planprocedimientosNew.getPedidoSet().add(pedido);
                planprocedimientosNew = em.merge(planprocedimientosNew);
            }
            if (planprocedimientos1Old != null && !planprocedimientos1Old.equals(planprocedimientos1New)) {
                planprocedimientos1Old.getPedidoSet().remove(pedido);
                planprocedimientos1Old = em.merge(planprocedimientos1Old);
            }
            if (planprocedimientos1New != null && !planprocedimientos1New.equals(planprocedimientos1Old)) {
                planprocedimientos1New.getPedidoSet().add(pedido);
                planprocedimientos1New = em.merge(planprocedimientos1New);
            }
            if (planprocesoscalidadOld != null && !planprocesoscalidadOld.equals(planprocesoscalidadNew)) {
                planprocesoscalidadOld.getPedidoSet().remove(pedido);
                planprocesoscalidadOld = em.merge(planprocesoscalidadOld);
            }
            if (planprocesoscalidadNew != null && !planprocesoscalidadNew.equals(planprocesoscalidadOld)) {
                planprocesoscalidadNew.getPedidoSet().add(pedido);
                planprocesoscalidadNew = em.merge(planprocesoscalidadNew);
            }
            if (planprocesoscalidad1Old != null && !planprocesoscalidad1Old.equals(planprocesoscalidad1New)) {
                planprocesoscalidad1Old.getPedidoSet().remove(pedido);
                planprocesoscalidad1Old = em.merge(planprocesoscalidad1Old);
            }
            if (planprocesoscalidad1New != null && !planprocesoscalidad1New.equals(planprocesoscalidad1Old)) {
                planprocesoscalidad1New.getPedidoSet().add(pedido);
                planprocesoscalidad1New = em.merge(planprocesoscalidad1New);
            }
            if (planrequerimientosmateriaprimaOld != null && !planrequerimientosmateriaprimaOld.equals(planrequerimientosmateriaprimaNew)) {
                planrequerimientosmateriaprimaOld.getPedidoSet().remove(pedido);
                planrequerimientosmateriaprimaOld = em.merge(planrequerimientosmateriaprimaOld);
            }
            if (planrequerimientosmateriaprimaNew != null && !planrequerimientosmateriaprimaNew.equals(planrequerimientosmateriaprimaOld)) {
                planrequerimientosmateriaprimaNew.getPedidoSet().add(pedido);
                planrequerimientosmateriaprimaNew = em.merge(planrequerimientosmateriaprimaNew);
            }
            if (planrequerimientosmateriaprima1Old != null && !planrequerimientosmateriaprima1Old.equals(planrequerimientosmateriaprima1New)) {
                planrequerimientosmateriaprima1Old.getPedidoSet().remove(pedido);
                planrequerimientosmateriaprima1Old = em.merge(planrequerimientosmateriaprima1Old);
            }
            if (planrequerimientosmateriaprima1New != null && !planrequerimientosmateriaprima1New.equals(planrequerimientosmateriaprima1Old)) {
                planrequerimientosmateriaprima1New.getPedidoSet().add(pedido);
                planrequerimientosmateriaprima1New = em.merge(planrequerimientosmateriaprima1New);
            }
            if (presupuestoOld != null && !presupuestoOld.equals(presupuestoNew)) {
                presupuestoOld.getPedidoSet().remove(pedido);
                presupuestoOld = em.merge(presupuestoOld);
            }
            if (presupuestoNew != null && !presupuestoNew.equals(presupuestoOld)) {
                presupuestoNew.getPedidoSet().add(pedido);
                presupuestoNew = em.merge(presupuestoNew);
            }
            if (presupuesto1Old != null && !presupuesto1Old.equals(presupuesto1New)) {
                presupuesto1Old.getPedidoSet().remove(pedido);
                presupuesto1Old = em.merge(presupuesto1Old);
            }
            if (presupuesto1New != null && !presupuesto1New.equals(presupuesto1Old)) {
                presupuesto1New.getPedidoSet().add(pedido);
                presupuesto1New = em.merge(presupuesto1New);
            }
            if (prioridadOld != null && !prioridadOld.equals(prioridadNew)) {
                prioridadOld.getPedidoSet().remove(pedido);
                prioridadOld = em.merge(prioridadOld);
            }
            if (prioridadNew != null && !prioridadNew.equals(prioridadOld)) {
                prioridadNew.getPedidoSet().add(pedido);
                prioridadNew = em.merge(prioridadNew);
            }
            if (prioridad1Old != null && !prioridad1Old.equals(prioridad1New)) {
                prioridad1Old.getPedidoSet().remove(pedido);
                prioridad1Old = em.merge(prioridad1Old);
            }
            if (prioridad1New != null && !prioridad1New.equals(prioridad1Old)) {
                prioridad1New.getPedidoSet().add(pedido);
                prioridad1New = em.merge(prioridad1New);
            }
            for (Planificacionproduccion planificacionproduccionSetOldPlanificacionproduccion : planificacionproduccionSetOld) {
                if (!planificacionproduccionSetNew.contains(planificacionproduccionSetOldPlanificacionproduccion)) {
                    planificacionproduccionSetOldPlanificacionproduccion.setPedido(null);
                    planificacionproduccionSetOldPlanificacionproduccion = em.merge(planificacionproduccionSetOldPlanificacionproduccion);
                }
            }
            for (Planificacionproduccion planificacionproduccionSetNewPlanificacionproduccion : planificacionproduccionSetNew) {
                if (!planificacionproduccionSetOld.contains(planificacionproduccionSetNewPlanificacionproduccion)) {
                    Pedido oldPedidoOfPlanificacionproduccionSetNewPlanificacionproduccion = planificacionproduccionSetNewPlanificacionproduccion.getPedido();
                    planificacionproduccionSetNewPlanificacionproduccion.setPedido(pedido);
                    planificacionproduccionSetNewPlanificacionproduccion = em.merge(planificacionproduccionSetNewPlanificacionproduccion);
                    if (oldPedidoOfPlanificacionproduccionSetNewPlanificacionproduccion != null && !oldPedidoOfPlanificacionproduccionSetNewPlanificacionproduccion.equals(pedido)) {
                        oldPedidoOfPlanificacionproduccionSetNewPlanificacionproduccion.getPlanificacionproduccionSet().remove(planificacionproduccionSetNewPlanificacionproduccion);
                        oldPedidoOfPlanificacionproduccionSetNewPlanificacionproduccion = em.merge(oldPedidoOfPlanificacionproduccionSetNewPlanificacionproduccion);
                    }
                }
            }
            for (Planificacionproduccion planificacionproduccionSet1OldPlanificacionproduccion : planificacionproduccionSet1Old) {
                if (!planificacionproduccionSet1New.contains(planificacionproduccionSet1OldPlanificacionproduccion)) {
                    planificacionproduccionSet1OldPlanificacionproduccion.setPedido1(null);
                    planificacionproduccionSet1OldPlanificacionproduccion = em.merge(planificacionproduccionSet1OldPlanificacionproduccion);
                }
            }
            for (Planificacionproduccion planificacionproduccionSet1NewPlanificacionproduccion : planificacionproduccionSet1New) {
                if (!planificacionproduccionSet1Old.contains(planificacionproduccionSet1NewPlanificacionproduccion)) {
                    Pedido oldPedido1OfPlanificacionproduccionSet1NewPlanificacionproduccion = planificacionproduccionSet1NewPlanificacionproduccion.getPedido1();
                    planificacionproduccionSet1NewPlanificacionproduccion.setPedido1(pedido);
                    planificacionproduccionSet1NewPlanificacionproduccion = em.merge(planificacionproduccionSet1NewPlanificacionproduccion);
                    if (oldPedido1OfPlanificacionproduccionSet1NewPlanificacionproduccion != null && !oldPedido1OfPlanificacionproduccionSet1NewPlanificacionproduccion.equals(pedido)) {
                        oldPedido1OfPlanificacionproduccionSet1NewPlanificacionproduccion.getPlanificacionproduccionSet1().remove(planificacionproduccionSet1NewPlanificacionproduccion);
                        oldPedido1OfPlanificacionproduccionSet1NewPlanificacionproduccion = em.merge(oldPedido1OfPlanificacionproduccionSet1NewPlanificacionproduccion);
                    }
                }
            }
            for (Productoreal productorealSetOldProductoreal : productorealSetOld) {
                if (!productorealSetNew.contains(productorealSetOldProductoreal)) {
                    productorealSetOldProductoreal.setIdpedido(null);
                    productorealSetOldProductoreal = em.merge(productorealSetOldProductoreal);
                }
            }
            for (Productoreal productorealSetNewProductoreal : productorealSetNew) {
                if (!productorealSetOld.contains(productorealSetNewProductoreal)) {
                    Pedido oldIdpedidoOfProductorealSetNewProductoreal = productorealSetNewProductoreal.getIdpedido();
                    productorealSetNewProductoreal.setIdpedido(pedido);
                    productorealSetNewProductoreal = em.merge(productorealSetNewProductoreal);
                    if (oldIdpedidoOfProductorealSetNewProductoreal != null && !oldIdpedidoOfProductorealSetNewProductoreal.equals(pedido)) {
                        oldIdpedidoOfProductorealSetNewProductoreal.getProductorealSet().remove(productorealSetNewProductoreal);
                        oldIdpedidoOfProductorealSetNewProductoreal = em.merge(oldIdpedidoOfProductorealSetNewProductoreal);
                    }
                }
            }
            for (Productoreal productorealSet1OldProductoreal : productorealSet1Old) {
                if (!productorealSet1New.contains(productorealSet1OldProductoreal)) {
                    productorealSet1OldProductoreal.setIdpedido1(null);
                    productorealSet1OldProductoreal = em.merge(productorealSet1OldProductoreal);
                }
            }
            for (Productoreal productorealSet1NewProductoreal : productorealSet1New) {
                if (!productorealSet1Old.contains(productorealSet1NewProductoreal)) {
                    Pedido oldIdpedido1OfProductorealSet1NewProductoreal = productorealSet1NewProductoreal.getIdpedido1();
                    productorealSet1NewProductoreal.setIdpedido1(pedido);
                    productorealSet1NewProductoreal = em.merge(productorealSet1NewProductoreal);
                    if (oldIdpedido1OfProductorealSet1NewProductoreal != null && !oldIdpedido1OfProductorealSet1NewProductoreal.equals(pedido)) {
                        oldIdpedido1OfProductorealSet1NewProductoreal.getProductorealSet1().remove(productorealSet1NewProductoreal);
                        oldIdpedido1OfProductorealSet1NewProductoreal = em.merge(oldIdpedido1OfProductorealSet1NewProductoreal);
                    }
                }
            }
            for (Plano planoSetOldPlano : planoSetOld) {
                if (!planoSetNew.contains(planoSetOldPlano)) {
                    planoSetOldPlano.setPedido(null);
                    planoSetOldPlano = em.merge(planoSetOldPlano);
                }
            }
            for (Plano planoSetNewPlano : planoSetNew) {
                if (!planoSetOld.contains(planoSetNewPlano)) {
                    Pedido oldPedidoOfPlanoSetNewPlano = planoSetNewPlano.getPedido();
                    planoSetNewPlano.setPedido(pedido);
                    planoSetNewPlano = em.merge(planoSetNewPlano);
                    if (oldPedidoOfPlanoSetNewPlano != null && !oldPedidoOfPlanoSetNewPlano.equals(pedido)) {
                        oldPedidoOfPlanoSetNewPlano.getPlanoSet().remove(planoSetNewPlano);
                        oldPedidoOfPlanoSetNewPlano = em.merge(oldPedidoOfPlanoSetNewPlano);
                    }
                }
            }
            for (Plano planoSet1OldPlano : planoSet1Old) {
                if (!planoSet1New.contains(planoSet1OldPlano)) {
                    planoSet1OldPlano.setPedido1(null);
                    planoSet1OldPlano = em.merge(planoSet1OldPlano);
                }
            }
            for (Plano planoSet1NewPlano : planoSet1New) {
                if (!planoSet1Old.contains(planoSet1NewPlano)) {
                    Pedido oldPedido1OfPlanoSet1NewPlano = planoSet1NewPlano.getPedido1();
                    planoSet1NewPlano.setPedido1(pedido);
                    planoSet1NewPlano = em.merge(planoSet1NewPlano);
                    if (oldPedido1OfPlanoSet1NewPlano != null && !oldPedido1OfPlanoSet1NewPlano.equals(pedido)) {
                        oldPedido1OfPlanoSet1NewPlano.getPlanoSet1().remove(planoSet1NewPlano);
                        oldPedido1OfPlanoSet1NewPlano = em.merge(oldPedido1OfPlanoSet1NewPlano);
                    }
                }
            }
            for (Trabajotercerizado trabajotercerizadoSetOldTrabajotercerizado : trabajotercerizadoSetOld) {
                if (!trabajotercerizadoSetNew.contains(trabajotercerizadoSetOldTrabajotercerizado)) {
                    trabajotercerizadoSetOldTrabajotercerizado.setPedido(null);
                    trabajotercerizadoSetOldTrabajotercerizado = em.merge(trabajotercerizadoSetOldTrabajotercerizado);
                }
            }
            for (Trabajotercerizado trabajotercerizadoSetNewTrabajotercerizado : trabajotercerizadoSetNew) {
                if (!trabajotercerizadoSetOld.contains(trabajotercerizadoSetNewTrabajotercerizado)) {
                    Pedido oldPedidoOfTrabajotercerizadoSetNewTrabajotercerizado = trabajotercerizadoSetNewTrabajotercerizado.getPedido();
                    trabajotercerizadoSetNewTrabajotercerizado.setPedido(pedido);
                    trabajotercerizadoSetNewTrabajotercerizado = em.merge(trabajotercerizadoSetNewTrabajotercerizado);
                    if (oldPedidoOfTrabajotercerizadoSetNewTrabajotercerizado != null && !oldPedidoOfTrabajotercerizadoSetNewTrabajotercerizado.equals(pedido)) {
                        oldPedidoOfTrabajotercerizadoSetNewTrabajotercerizado.getTrabajotercerizadoSet().remove(trabajotercerizadoSetNewTrabajotercerizado);
                        oldPedidoOfTrabajotercerizadoSetNewTrabajotercerizado = em.merge(oldPedidoOfTrabajotercerizadoSetNewTrabajotercerizado);
                    }
                }
            }
            for (Trabajotercerizado trabajotercerizadoSet1OldTrabajotercerizado : trabajotercerizadoSet1Old) {
                if (!trabajotercerizadoSet1New.contains(trabajotercerizadoSet1OldTrabajotercerizado)) {
                    trabajotercerizadoSet1OldTrabajotercerizado.setPedido1(null);
                    trabajotercerizadoSet1OldTrabajotercerizado = em.merge(trabajotercerizadoSet1OldTrabajotercerizado);
                }
            }
            for (Trabajotercerizado trabajotercerizadoSet1NewTrabajotercerizado : trabajotercerizadoSet1New) {
                if (!trabajotercerizadoSet1Old.contains(trabajotercerizadoSet1NewTrabajotercerizado)) {
                    Pedido oldPedido1OfTrabajotercerizadoSet1NewTrabajotercerizado = trabajotercerizadoSet1NewTrabajotercerizado.getPedido1();
                    trabajotercerizadoSet1NewTrabajotercerizado.setPedido1(pedido);
                    trabajotercerizadoSet1NewTrabajotercerizado = em.merge(trabajotercerizadoSet1NewTrabajotercerizado);
                    if (oldPedido1OfTrabajotercerizadoSet1NewTrabajotercerizado != null && !oldPedido1OfTrabajotercerizadoSet1NewTrabajotercerizado.equals(pedido)) {
                        oldPedido1OfTrabajotercerizadoSet1NewTrabajotercerizado.getTrabajotercerizadoSet1().remove(trabajotercerizadoSet1NewTrabajotercerizado);
                        oldPedido1OfTrabajotercerizadoSet1NewTrabajotercerizado = em.merge(oldPedido1OfTrabajotercerizadoSet1NewTrabajotercerizado);
                    }
                }
            }
            for (Planificacioncalidad planificacioncalidadSetOldPlanificacioncalidad : planificacioncalidadSetOld) {
                if (!planificacioncalidadSetNew.contains(planificacioncalidadSetOldPlanificacioncalidad)) {
                    planificacioncalidadSetOldPlanificacioncalidad.setPedido(null);
                    planificacioncalidadSetOldPlanificacioncalidad = em.merge(planificacioncalidadSetOldPlanificacioncalidad);
                }
            }
            for (Planificacioncalidad planificacioncalidadSetNewPlanificacioncalidad : planificacioncalidadSetNew) {
                if (!planificacioncalidadSetOld.contains(planificacioncalidadSetNewPlanificacioncalidad)) {
                    Pedido oldPedidoOfPlanificacioncalidadSetNewPlanificacioncalidad = planificacioncalidadSetNewPlanificacioncalidad.getPedido();
                    planificacioncalidadSetNewPlanificacioncalidad.setPedido(pedido);
                    planificacioncalidadSetNewPlanificacioncalidad = em.merge(planificacioncalidadSetNewPlanificacioncalidad);
                    if (oldPedidoOfPlanificacioncalidadSetNewPlanificacioncalidad != null && !oldPedidoOfPlanificacioncalidadSetNewPlanificacioncalidad.equals(pedido)) {
                        oldPedidoOfPlanificacioncalidadSetNewPlanificacioncalidad.getPlanificacioncalidadSet().remove(planificacioncalidadSetNewPlanificacioncalidad);
                        oldPedidoOfPlanificacioncalidadSetNewPlanificacioncalidad = em.merge(oldPedidoOfPlanificacioncalidadSetNewPlanificacioncalidad);
                    }
                }
            }
            for (Planificacioncalidad planificacioncalidadSet1OldPlanificacioncalidad : planificacioncalidadSet1Old) {
                if (!planificacioncalidadSet1New.contains(planificacioncalidadSet1OldPlanificacioncalidad)) {
                    planificacioncalidadSet1OldPlanificacioncalidad.setPedido1(null);
                    planificacioncalidadSet1OldPlanificacioncalidad = em.merge(planificacioncalidadSet1OldPlanificacioncalidad);
                }
            }
            for (Planificacioncalidad planificacioncalidadSet1NewPlanificacioncalidad : planificacioncalidadSet1New) {
                if (!planificacioncalidadSet1Old.contains(planificacioncalidadSet1NewPlanificacioncalidad)) {
                    Pedido oldPedido1OfPlanificacioncalidadSet1NewPlanificacioncalidad = planificacioncalidadSet1NewPlanificacioncalidad.getPedido1();
                    planificacioncalidadSet1NewPlanificacioncalidad.setPedido1(pedido);
                    planificacioncalidadSet1NewPlanificacioncalidad = em.merge(planificacioncalidadSet1NewPlanificacioncalidad);
                    if (oldPedido1OfPlanificacioncalidadSet1NewPlanificacioncalidad != null && !oldPedido1OfPlanificacioncalidadSet1NewPlanificacioncalidad.equals(pedido)) {
                        oldPedido1OfPlanificacioncalidadSet1NewPlanificacioncalidad.getPlanificacioncalidadSet1().remove(planificacioncalidadSet1NewPlanificacioncalidad);
                        oldPedido1OfPlanificacioncalidadSet1NewPlanificacioncalidad = em.merge(oldPedido1OfPlanificacioncalidadSet1NewPlanificacioncalidad);
                    }
                }
            }
            for (Remito remitoSetOldRemito : remitoSetOld) {
                if (!remitoSetNew.contains(remitoSetOldRemito)) {
                    remitoSetOldRemito.setPedido(null);
                    remitoSetOldRemito = em.merge(remitoSetOldRemito);
                }
            }
            for (Remito remitoSetNewRemito : remitoSetNew) {
                if (!remitoSetOld.contains(remitoSetNewRemito)) {
                    Pedido oldPedidoOfRemitoSetNewRemito = remitoSetNewRemito.getPedido();
                    remitoSetNewRemito.setPedido(pedido);
                    remitoSetNewRemito = em.merge(remitoSetNewRemito);
                    if (oldPedidoOfRemitoSetNewRemito != null && !oldPedidoOfRemitoSetNewRemito.equals(pedido)) {
                        oldPedidoOfRemitoSetNewRemito.getRemitoSet().remove(remitoSetNewRemito);
                        oldPedidoOfRemitoSetNewRemito = em.merge(oldPedidoOfRemitoSetNewRemito);
                    }
                }
            }
            for (Remito remitoSet1OldRemito : remitoSet1Old) {
                if (!remitoSet1New.contains(remitoSet1OldRemito)) {
                    remitoSet1OldRemito.setPedido1(null);
                    remitoSet1OldRemito = em.merge(remitoSet1OldRemito);
                }
            }
            for (Remito remitoSet1NewRemito : remitoSet1New) {
                if (!remitoSet1Old.contains(remitoSet1NewRemito)) {
                    Pedido oldPedido1OfRemitoSet1NewRemito = remitoSet1NewRemito.getPedido1();
                    remitoSet1NewRemito.setPedido1(pedido);
                    remitoSet1NewRemito = em.merge(remitoSet1NewRemito);
                    if (oldPedido1OfRemitoSet1NewRemito != null && !oldPedido1OfRemitoSet1NewRemito.equals(pedido)) {
                        oldPedido1OfRemitoSet1NewRemito.getRemitoSet1().remove(remitoSet1NewRemito);
                        oldPedido1OfRemitoSet1NewRemito = em.merge(oldPedido1OfRemitoSet1NewRemito);
                    }
                }
            }
            for (Detallefactura detallefacturaSetOldDetallefactura : detallefacturaSetOld) {
                if (!detallefacturaSetNew.contains(detallefacturaSetOldDetallefactura)) {
                    detallefacturaSetOldDetallefactura.setIdpedido(null);
                    detallefacturaSetOldDetallefactura = em.merge(detallefacturaSetOldDetallefactura);
                }
            }
            for (Detallefactura detallefacturaSetNewDetallefactura : detallefacturaSetNew) {
                if (!detallefacturaSetOld.contains(detallefacturaSetNewDetallefactura)) {
                    Pedido oldIdpedidoOfDetallefacturaSetNewDetallefactura = detallefacturaSetNewDetallefactura.getIdpedido();
                    detallefacturaSetNewDetallefactura.setIdpedido(pedido);
                    detallefacturaSetNewDetallefactura = em.merge(detallefacturaSetNewDetallefactura);
                    if (oldIdpedidoOfDetallefacturaSetNewDetallefactura != null && !oldIdpedidoOfDetallefacturaSetNewDetallefactura.equals(pedido)) {
                        oldIdpedidoOfDetallefacturaSetNewDetallefactura.getDetallefacturaSet().remove(detallefacturaSetNewDetallefactura);
                        oldIdpedidoOfDetallefacturaSetNewDetallefactura = em.merge(oldIdpedidoOfDetallefacturaSetNewDetallefactura);
                    }
                }
            }
            for (Detallefactura detallefacturaSet1OldDetallefactura : detallefacturaSet1Old) {
                if (!detallefacturaSet1New.contains(detallefacturaSet1OldDetallefactura)) {
                    detallefacturaSet1OldDetallefactura.setIdpedido1(null);
                    detallefacturaSet1OldDetallefactura = em.merge(detallefacturaSet1OldDetallefactura);
                }
            }
            for (Detallefactura detallefacturaSet1NewDetallefactura : detallefacturaSet1New) {
                if (!detallefacturaSet1Old.contains(detallefacturaSet1NewDetallefactura)) {
                    Pedido oldIdpedido1OfDetallefacturaSet1NewDetallefactura = detallefacturaSet1NewDetallefactura.getIdpedido1();
                    detallefacturaSet1NewDetallefactura.setIdpedido1(pedido);
                    detallefacturaSet1NewDetallefactura = em.merge(detallefacturaSet1NewDetallefactura);
                    if (oldIdpedido1OfDetallefacturaSet1NewDetallefactura != null && !oldIdpedido1OfDetallefacturaSet1NewDetallefactura.equals(pedido)) {
                        oldIdpedido1OfDetallefacturaSet1NewDetallefactura.getDetallefacturaSet1().remove(detallefacturaSet1NewDetallefactura);
                        oldIdpedido1OfDetallefacturaSet1NewDetallefactura = em.merge(oldIdpedido1OfDetallefacturaSet1NewDetallefactura);
                    }
                }
            }
            for (Detallepedido detallepedidoSetNewDetallepedido : detallepedidoSetNew) {
                if (!detallepedidoSetOld.contains(detallepedidoSetNewDetallepedido)) {
                    Pedido oldIdpedidoOfDetallepedidoSetNewDetallepedido = detallepedidoSetNewDetallepedido.getIdpedido();
                    detallepedidoSetNewDetallepedido.setIdpedido(pedido);
                    detallepedidoSetNewDetallepedido = em.merge(detallepedidoSetNewDetallepedido);
                    if (oldIdpedidoOfDetallepedidoSetNewDetallepedido != null && !oldIdpedidoOfDetallepedidoSetNewDetallepedido.equals(pedido)) {
                        oldIdpedidoOfDetallepedidoSetNewDetallepedido.getDetallepedidoSet().remove(detallepedidoSetNewDetallepedido);
                        oldIdpedidoOfDetallepedidoSetNewDetallepedido = em.merge(oldIdpedidoOfDetallepedidoSetNewDetallepedido);
                    }
                }
            }
            for (Detallepedido detallepedidoSet1NewDetallepedido : detallepedidoSet1New) {
                if (!detallepedidoSet1Old.contains(detallepedidoSet1NewDetallepedido)) {
                    Pedido oldIdpedido1OfDetallepedidoSet1NewDetallepedido = detallepedidoSet1NewDetallepedido.getIdpedido1();
                    detallepedidoSet1NewDetallepedido.setIdpedido1(pedido);
                    detallepedidoSet1NewDetallepedido = em.merge(detallepedidoSet1NewDetallepedido);
                    if (oldIdpedido1OfDetallepedidoSet1NewDetallepedido != null && !oldIdpedido1OfDetallepedidoSet1NewDetallepedido.equals(pedido)) {
                        oldIdpedido1OfDetallepedidoSet1NewDetallepedido.getDetallepedidoSet1().remove(detallepedidoSet1NewDetallepedido);
                        oldIdpedido1OfDetallepedidoSet1NewDetallepedido = em.merge(oldIdpedido1OfDetallepedidoSet1NewDetallepedido);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pedido.getIdpedido();
                if (findPedido(id) == null) {
                    throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.");
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
            Pedido pedido;
            try {
                pedido = em.getReference(Pedido.class, id);
                pedido.getIdpedido();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedido with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Set<Detallepedido> detallepedidoSetOrphanCheck = pedido.getDetallepedidoSet();
            for (Detallepedido detallepedidoSetOrphanCheckDetallepedido : detallepedidoSetOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pedido (" + pedido + ") cannot be destroyed since the Detallepedido " + detallepedidoSetOrphanCheckDetallepedido + " in its detallepedidoSet field has a non-nullable idpedido field.");
            }
            Set<Detallepedido> detallepedidoSet1OrphanCheck = pedido.getDetallepedidoSet1();
            for (Detallepedido detallepedidoSet1OrphanCheckDetallepedido : detallepedidoSet1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pedido (" + pedido + ") cannot be destroyed since the Detallepedido " + detallepedidoSet1OrphanCheckDetallepedido + " in its detallepedidoSet1 field has a non-nullable idpedido1 field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente cliente = pedido.getCliente();
            if (cliente != null) {
                cliente.getPedidoSet().remove(pedido);
                cliente = em.merge(cliente);
            }
            Cliente cliente1 = pedido.getCliente1();
            if (cliente1 != null) {
                cliente1.getPedidoSet().remove(pedido);
                cliente1 = em.merge(cliente1);
            }
            Estadopedido estado = pedido.getEstado();
            if (estado != null) {
                estado.getPedidoSet().remove(pedido);
                estado = em.merge(estado);
            }
            Estadopedido estado1 = pedido.getEstado1();
            if (estado1 != null) {
                estado1.getPedidoSet().remove(pedido);
                estado1 = em.merge(estado1);
            }
            Factura factura = pedido.getFactura();
            if (factura != null) {
                factura.getPedidoSet().remove(pedido);
                factura = em.merge(factura);
            }
            Factura factura1 = pedido.getFactura1();
            if (factura1 != null) {
                factura1.getPedidoSet().remove(pedido);
                factura1 = em.merge(factura1);
            }
            Plano plano = pedido.getPlano();
            if (plano != null) {
                plano.getPedidoSet().remove(pedido);
                plano = em.merge(plano);
            }
            Plano plano1 = pedido.getPlano1();
            if (plano1 != null) {
                plano1.getPedidoSet().remove(pedido);
                plano1 = em.merge(plano1);
            }
            Planprocedimientos planprocedimientos = pedido.getPlanprocedimientos();
            if (planprocedimientos != null) {
                planprocedimientos.getPedidoSet().remove(pedido);
                planprocedimientos = em.merge(planprocedimientos);
            }
            Planprocedimientos planprocedimientos1 = pedido.getPlanprocedimientos1();
            if (planprocedimientos1 != null) {
                planprocedimientos1.getPedidoSet().remove(pedido);
                planprocedimientos1 = em.merge(planprocedimientos1);
            }
            Planprocesoscalidad planprocesoscalidad = pedido.getPlanprocesoscalidad();
            if (planprocesoscalidad != null) {
                planprocesoscalidad.getPedidoSet().remove(pedido);
                planprocesoscalidad = em.merge(planprocesoscalidad);
            }
            Planprocesoscalidad planprocesoscalidad1 = pedido.getPlanprocesoscalidad1();
            if (planprocesoscalidad1 != null) {
                planprocesoscalidad1.getPedidoSet().remove(pedido);
                planprocesoscalidad1 = em.merge(planprocesoscalidad1);
            }
            Planrequerimientosmateriaprima planrequerimientosmateriaprima = pedido.getPlanrequerimientosmateriaprima();
            if (planrequerimientosmateriaprima != null) {
                planrequerimientosmateriaprima.getPedidoSet().remove(pedido);
                planrequerimientosmateriaprima = em.merge(planrequerimientosmateriaprima);
            }
            Planrequerimientosmateriaprima planrequerimientosmateriaprima1 = pedido.getPlanrequerimientosmateriaprima1();
            if (planrequerimientosmateriaprima1 != null) {
                planrequerimientosmateriaprima1.getPedidoSet().remove(pedido);
                planrequerimientosmateriaprima1 = em.merge(planrequerimientosmateriaprima1);
            }
            Presupuesto presupuesto = pedido.getPresupuesto();
            if (presupuesto != null) {
                presupuesto.getPedidoSet().remove(pedido);
                presupuesto = em.merge(presupuesto);
            }
            Presupuesto presupuesto1 = pedido.getPresupuesto1();
            if (presupuesto1 != null) {
                presupuesto1.getPedidoSet().remove(pedido);
                presupuesto1 = em.merge(presupuesto1);
            }
            Prioridad prioridad = pedido.getPrioridad();
            if (prioridad != null) {
                prioridad.getPedidoSet().remove(pedido);
                prioridad = em.merge(prioridad);
            }
            Prioridad prioridad1 = pedido.getPrioridad1();
            if (prioridad1 != null) {
                prioridad1.getPedidoSet().remove(pedido);
                prioridad1 = em.merge(prioridad1);
            }
            Set<Planificacionproduccion> planificacionproduccionSet = pedido.getPlanificacionproduccionSet();
            for (Planificacionproduccion planificacionproduccionSetPlanificacionproduccion : planificacionproduccionSet) {
                planificacionproduccionSetPlanificacionproduccion.setPedido(null);
                planificacionproduccionSetPlanificacionproduccion = em.merge(planificacionproduccionSetPlanificacionproduccion);
            }
            Set<Planificacionproduccion> planificacionproduccionSet1 = pedido.getPlanificacionproduccionSet1();
            for (Planificacionproduccion planificacionproduccionSet1Planificacionproduccion : planificacionproduccionSet1) {
                planificacionproduccionSet1Planificacionproduccion.setPedido1(null);
                planificacionproduccionSet1Planificacionproduccion = em.merge(planificacionproduccionSet1Planificacionproduccion);
            }
            Set<Productoreal> productorealSet = pedido.getProductorealSet();
            for (Productoreal productorealSetProductoreal : productorealSet) {
                productorealSetProductoreal.setIdpedido(null);
                productorealSetProductoreal = em.merge(productorealSetProductoreal);
            }
            Set<Productoreal> productorealSet1 = pedido.getProductorealSet1();
            for (Productoreal productorealSet1Productoreal : productorealSet1) {
                productorealSet1Productoreal.setIdpedido1(null);
                productorealSet1Productoreal = em.merge(productorealSet1Productoreal);
            }
            Set<Plano> planoSet = pedido.getPlanoSet();
            for (Plano planoSetPlano : planoSet) {
                planoSetPlano.setPedido(null);
                planoSetPlano = em.merge(planoSetPlano);
            }
            Set<Plano> planoSet1 = pedido.getPlanoSet1();
            for (Plano planoSet1Plano : planoSet1) {
                planoSet1Plano.setPedido1(null);
                planoSet1Plano = em.merge(planoSet1Plano);
            }
            Set<Trabajotercerizado> trabajotercerizadoSet = pedido.getTrabajotercerizadoSet();
            for (Trabajotercerizado trabajotercerizadoSetTrabajotercerizado : trabajotercerizadoSet) {
                trabajotercerizadoSetTrabajotercerizado.setPedido(null);
                trabajotercerizadoSetTrabajotercerizado = em.merge(trabajotercerizadoSetTrabajotercerizado);
            }
            Set<Trabajotercerizado> trabajotercerizadoSet1 = pedido.getTrabajotercerizadoSet1();
            for (Trabajotercerizado trabajotercerizadoSet1Trabajotercerizado : trabajotercerizadoSet1) {
                trabajotercerizadoSet1Trabajotercerizado.setPedido1(null);
                trabajotercerizadoSet1Trabajotercerizado = em.merge(trabajotercerizadoSet1Trabajotercerizado);
            }
            Set<Planificacioncalidad> planificacioncalidadSet = pedido.getPlanificacioncalidadSet();
            for (Planificacioncalidad planificacioncalidadSetPlanificacioncalidad : planificacioncalidadSet) {
                planificacioncalidadSetPlanificacioncalidad.setPedido(null);
                planificacioncalidadSetPlanificacioncalidad = em.merge(planificacioncalidadSetPlanificacioncalidad);
            }
            Set<Planificacioncalidad> planificacioncalidadSet1 = pedido.getPlanificacioncalidadSet1();
            for (Planificacioncalidad planificacioncalidadSet1Planificacioncalidad : planificacioncalidadSet1) {
                planificacioncalidadSet1Planificacioncalidad.setPedido1(null);
                planificacioncalidadSet1Planificacioncalidad = em.merge(planificacioncalidadSet1Planificacioncalidad);
            }
            Set<Remito> remitoSet = pedido.getRemitoSet();
            for (Remito remitoSetRemito : remitoSet) {
                remitoSetRemito.setPedido(null);
                remitoSetRemito = em.merge(remitoSetRemito);
            }
            Set<Remito> remitoSet1 = pedido.getRemitoSet1();
            for (Remito remitoSet1Remito : remitoSet1) {
                remitoSet1Remito.setPedido1(null);
                remitoSet1Remito = em.merge(remitoSet1Remito);
            }
            Set<Detallefactura> detallefacturaSet = pedido.getDetallefacturaSet();
            for (Detallefactura detallefacturaSetDetallefactura : detallefacturaSet) {
                detallefacturaSetDetallefactura.setIdpedido(null);
                detallefacturaSetDetallefactura = em.merge(detallefacturaSetDetallefactura);
            }
            Set<Detallefactura> detallefacturaSet1 = pedido.getDetallefacturaSet1();
            for (Detallefactura detallefacturaSet1Detallefactura : detallefacturaSet1) {
                detallefacturaSet1Detallefactura.setIdpedido1(null);
                detallefacturaSet1Detallefactura = em.merge(detallefacturaSet1Detallefactura);
            }
            em.remove(pedido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pedido> findPedidoEntities() {
        return findPedidoEntities(true, -1, -1);
    }

    public List<Pedido> findPedidoEntities(int maxResults, int firstResult) {
        return findPedidoEntities(false, maxResults, firstResult);
    }

    private List<Pedido> findPedidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pedido.class));
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

    public Pedido findPedido(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedido.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pedido> rt = cq.from(Pedido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
