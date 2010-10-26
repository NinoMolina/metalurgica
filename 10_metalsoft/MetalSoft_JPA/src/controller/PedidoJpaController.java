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
        if (pedido.getProductorealSet() == null) {
            pedido.setProductorealSet(new HashSet<Productoreal>());
        }
        if (pedido.getPlanoSet() == null) {
            pedido.setPlanoSet(new HashSet<Plano>());
        }
        if (pedido.getTrabajotercerizadoSet() == null) {
            pedido.setTrabajotercerizadoSet(new HashSet<Trabajotercerizado>());
        }
        if (pedido.getPlanificacioncalidadSet() == null) {
            pedido.setPlanificacioncalidadSet(new HashSet<Planificacioncalidad>());
        }
        if (pedido.getRemitoSet() == null) {
            pedido.setRemitoSet(new HashSet<Remito>());
        }
        if (pedido.getDetallefacturaSet() == null) {
            pedido.setDetallefacturaSet(new HashSet<Detallefactura>());
        }
        if (pedido.getDetallepedidoSet() == null) {
            pedido.setDetallepedidoSet(new HashSet<Detallepedido>());
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
            Estadopedido estado = pedido.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                pedido.setEstado(estado);
            }
            Factura factura = pedido.getFactura();
            if (factura != null) {
                factura = em.getReference(factura.getClass(), factura.getIdfactura());
                pedido.setFactura(factura);
            }
            Plano plano = pedido.getPlano();
            if (plano != null) {
                plano = em.getReference(plano.getClass(), plano.getIdplano());
                pedido.setPlano(plano);
            }
            Planprocedimientos planprocedimientos = pedido.getPlanprocedimientos();
            if (planprocedimientos != null) {
                planprocedimientos = em.getReference(planprocedimientos.getClass(), planprocedimientos.getIdplanprocedimientos());
                pedido.setPlanprocedimientos(planprocedimientos);
            }
            Planprocesoscalidad planprocesoscalidad = pedido.getPlanprocesoscalidad();
            if (planprocesoscalidad != null) {
                planprocesoscalidad = em.getReference(planprocesoscalidad.getClass(), planprocesoscalidad.getIdplanprocesoscalidad());
                pedido.setPlanprocesoscalidad(planprocesoscalidad);
            }
            Planrequerimientosmateriaprima planrequerimientosmateriaprima = pedido.getPlanrequerimientosmateriaprima();
            if (planrequerimientosmateriaprima != null) {
                planrequerimientosmateriaprima = em.getReference(planrequerimientosmateriaprima.getClass(), planrequerimientosmateriaprima.getIdplanrequerimientosmateriaprima());
                pedido.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprima);
            }
            Presupuesto presupuesto = pedido.getPresupuesto();
            if (presupuesto != null) {
                presupuesto = em.getReference(presupuesto.getClass(), presupuesto.getIdpresupuesto());
                pedido.setPresupuesto(presupuesto);
            }
            Prioridad prioridad = pedido.getPrioridad();
            if (prioridad != null) {
                prioridad = em.getReference(prioridad.getClass(), prioridad.getIdprioridad());
                pedido.setPrioridad(prioridad);
            }
            Set<Planificacionproduccion> attachedPlanificacionproduccionSet = new HashSet<Planificacionproduccion>();
            for (Planificacionproduccion planificacionproduccionSetPlanificacionproduccionToAttach : pedido.getPlanificacionproduccionSet()) {
                planificacionproduccionSetPlanificacionproduccionToAttach = em.getReference(planificacionproduccionSetPlanificacionproduccionToAttach.getClass(), planificacionproduccionSetPlanificacionproduccionToAttach.getIdplanificacionproduccion());
                attachedPlanificacionproduccionSet.add(planificacionproduccionSetPlanificacionproduccionToAttach);
            }
            pedido.setPlanificacionproduccionSet(attachedPlanificacionproduccionSet);
            Set<Productoreal> attachedProductorealSet = new HashSet<Productoreal>();
            for (Productoreal productorealSetProductorealToAttach : pedido.getProductorealSet()) {
                productorealSetProductorealToAttach = em.getReference(productorealSetProductorealToAttach.getClass(), productorealSetProductorealToAttach.getIdproductoreal());
                attachedProductorealSet.add(productorealSetProductorealToAttach);
            }
            pedido.setProductorealSet(attachedProductorealSet);
            Set<Plano> attachedPlanoSet = new HashSet<Plano>();
            for (Plano planoSetPlanoToAttach : pedido.getPlanoSet()) {
                planoSetPlanoToAttach = em.getReference(planoSetPlanoToAttach.getClass(), planoSetPlanoToAttach.getIdplano());
                attachedPlanoSet.add(planoSetPlanoToAttach);
            }
            pedido.setPlanoSet(attachedPlanoSet);
            Set<Trabajotercerizado> attachedTrabajotercerizadoSet = new HashSet<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoSetTrabajotercerizadoToAttach : pedido.getTrabajotercerizadoSet()) {
                trabajotercerizadoSetTrabajotercerizadoToAttach = em.getReference(trabajotercerizadoSetTrabajotercerizadoToAttach.getClass(), trabajotercerizadoSetTrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoSet.add(trabajotercerizadoSetTrabajotercerizadoToAttach);
            }
            pedido.setTrabajotercerizadoSet(attachedTrabajotercerizadoSet);
            Set<Planificacioncalidad> attachedPlanificacioncalidadSet = new HashSet<Planificacioncalidad>();
            for (Planificacioncalidad planificacioncalidadSetPlanificacioncalidadToAttach : pedido.getPlanificacioncalidadSet()) {
                planificacioncalidadSetPlanificacioncalidadToAttach = em.getReference(planificacioncalidadSetPlanificacioncalidadToAttach.getClass(), planificacioncalidadSetPlanificacioncalidadToAttach.getIdplanificacion());
                attachedPlanificacioncalidadSet.add(planificacioncalidadSetPlanificacioncalidadToAttach);
            }
            pedido.setPlanificacioncalidadSet(attachedPlanificacioncalidadSet);
            Set<Remito> attachedRemitoSet = new HashSet<Remito>();
            for (Remito remitoSetRemitoToAttach : pedido.getRemitoSet()) {
                remitoSetRemitoToAttach = em.getReference(remitoSetRemitoToAttach.getClass(), remitoSetRemitoToAttach.getIdremito());
                attachedRemitoSet.add(remitoSetRemitoToAttach);
            }
            pedido.setRemitoSet(attachedRemitoSet);
            Set<Detallefactura> attachedDetallefacturaSet = new HashSet<Detallefactura>();
            for (Detallefactura detallefacturaSetDetallefacturaToAttach : pedido.getDetallefacturaSet()) {
                detallefacturaSetDetallefacturaToAttach = em.getReference(detallefacturaSetDetallefacturaToAttach.getClass(), detallefacturaSetDetallefacturaToAttach.getDetallefacturaPK());
                attachedDetallefacturaSet.add(detallefacturaSetDetallefacturaToAttach);
            }
            pedido.setDetallefacturaSet(attachedDetallefacturaSet);
            Set<Detallepedido> attachedDetallepedidoSet = new HashSet<Detallepedido>();
            for (Detallepedido detallepedidoSetDetallepedidoToAttach : pedido.getDetallepedidoSet()) {
                detallepedidoSetDetallepedidoToAttach = em.getReference(detallepedidoSetDetallepedidoToAttach.getClass(), detallepedidoSetDetallepedidoToAttach.getIddetalle());
                attachedDetallepedidoSet.add(detallepedidoSetDetallepedidoToAttach);
            }
            pedido.setDetallepedidoSet(attachedDetallepedidoSet);
            em.persist(pedido);
            if (cliente != null) {
                cliente.getPedidoSet().add(pedido);
                cliente = em.merge(cliente);
            }
            if (estado != null) {
                estado.getPedidoSet().add(pedido);
                estado = em.merge(estado);
            }
            if (factura != null) {
                factura.getPedidoSet().add(pedido);
                factura = em.merge(factura);
            }
            if (plano != null) {
                plano.getPedidoSet().add(pedido);
                plano = em.merge(plano);
            }
            if (planprocedimientos != null) {
                planprocedimientos.getPedidoSet().add(pedido);
                planprocedimientos = em.merge(planprocedimientos);
            }
            if (planprocesoscalidad != null) {
                planprocesoscalidad.getPedidoSet().add(pedido);
                planprocesoscalidad = em.merge(planprocesoscalidad);
            }
            if (planrequerimientosmateriaprima != null) {
                planrequerimientosmateriaprima.getPedidoSet().add(pedido);
                planrequerimientosmateriaprima = em.merge(planrequerimientosmateriaprima);
            }
            if (presupuesto != null) {
                presupuesto.getPedidoSet().add(pedido);
                presupuesto = em.merge(presupuesto);
            }
            if (prioridad != null) {
                prioridad.getPedidoSet().add(pedido);
                prioridad = em.merge(prioridad);
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
            for (Productoreal productorealSetProductoreal : pedido.getProductorealSet()) {
                Pedido oldIdpedidoOfProductorealSetProductoreal = productorealSetProductoreal.getIdpedido();
                productorealSetProductoreal.setIdpedido(pedido);
                productorealSetProductoreal = em.merge(productorealSetProductoreal);
                if (oldIdpedidoOfProductorealSetProductoreal != null) {
                    oldIdpedidoOfProductorealSetProductoreal.getProductorealSet().remove(productorealSetProductoreal);
                    oldIdpedidoOfProductorealSetProductoreal = em.merge(oldIdpedidoOfProductorealSetProductoreal);
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
            for (Trabajotercerizado trabajotercerizadoSetTrabajotercerizado : pedido.getTrabajotercerizadoSet()) {
                Pedido oldPedidoOfTrabajotercerizadoSetTrabajotercerizado = trabajotercerizadoSetTrabajotercerizado.getPedido();
                trabajotercerizadoSetTrabajotercerizado.setPedido(pedido);
                trabajotercerizadoSetTrabajotercerizado = em.merge(trabajotercerizadoSetTrabajotercerizado);
                if (oldPedidoOfTrabajotercerizadoSetTrabajotercerizado != null) {
                    oldPedidoOfTrabajotercerizadoSetTrabajotercerizado.getTrabajotercerizadoSet().remove(trabajotercerizadoSetTrabajotercerizado);
                    oldPedidoOfTrabajotercerizadoSetTrabajotercerizado = em.merge(oldPedidoOfTrabajotercerizadoSetTrabajotercerizado);
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
            for (Remito remitoSetRemito : pedido.getRemitoSet()) {
                Pedido oldPedidoOfRemitoSetRemito = remitoSetRemito.getPedido();
                remitoSetRemito.setPedido(pedido);
                remitoSetRemito = em.merge(remitoSetRemito);
                if (oldPedidoOfRemitoSetRemito != null) {
                    oldPedidoOfRemitoSetRemito.getRemitoSet().remove(remitoSetRemito);
                    oldPedidoOfRemitoSetRemito = em.merge(oldPedidoOfRemitoSetRemito);
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
            for (Detallepedido detallepedidoSetDetallepedido : pedido.getDetallepedidoSet()) {
                Pedido oldIdpedidoOfDetallepedidoSetDetallepedido = detallepedidoSetDetallepedido.getIdpedido();
                detallepedidoSetDetallepedido.setIdpedido(pedido);
                detallepedidoSetDetallepedido = em.merge(detallepedidoSetDetallepedido);
                if (oldIdpedidoOfDetallepedidoSetDetallepedido != null) {
                    oldIdpedidoOfDetallepedidoSetDetallepedido.getDetallepedidoSet().remove(detallepedidoSetDetallepedido);
                    oldIdpedidoOfDetallepedidoSetDetallepedido = em.merge(oldIdpedidoOfDetallepedidoSetDetallepedido);
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
            Estadopedido estadoOld = persistentPedido.getEstado();
            Estadopedido estadoNew = pedido.getEstado();
            Factura facturaOld = persistentPedido.getFactura();
            Factura facturaNew = pedido.getFactura();
            Plano planoOld = persistentPedido.getPlano();
            Plano planoNew = pedido.getPlano();
            Planprocedimientos planprocedimientosOld = persistentPedido.getPlanprocedimientos();
            Planprocedimientos planprocedimientosNew = pedido.getPlanprocedimientos();
            Planprocesoscalidad planprocesoscalidadOld = persistentPedido.getPlanprocesoscalidad();
            Planprocesoscalidad planprocesoscalidadNew = pedido.getPlanprocesoscalidad();
            Planrequerimientosmateriaprima planrequerimientosmateriaprimaOld = persistentPedido.getPlanrequerimientosmateriaprima();
            Planrequerimientosmateriaprima planrequerimientosmateriaprimaNew = pedido.getPlanrequerimientosmateriaprima();
            Presupuesto presupuestoOld = persistentPedido.getPresupuesto();
            Presupuesto presupuestoNew = pedido.getPresupuesto();
            Prioridad prioridadOld = persistentPedido.getPrioridad();
            Prioridad prioridadNew = pedido.getPrioridad();
            Set<Planificacionproduccion> planificacionproduccionSetOld = persistentPedido.getPlanificacionproduccionSet();
            Set<Planificacionproduccion> planificacionproduccionSetNew = pedido.getPlanificacionproduccionSet();
            Set<Productoreal> productorealSetOld = persistentPedido.getProductorealSet();
            Set<Productoreal> productorealSetNew = pedido.getProductorealSet();
            Set<Plano> planoSetOld = persistentPedido.getPlanoSet();
            Set<Plano> planoSetNew = pedido.getPlanoSet();
            Set<Trabajotercerizado> trabajotercerizadoSetOld = persistentPedido.getTrabajotercerizadoSet();
            Set<Trabajotercerizado> trabajotercerizadoSetNew = pedido.getTrabajotercerizadoSet();
            Set<Planificacioncalidad> planificacioncalidadSetOld = persistentPedido.getPlanificacioncalidadSet();
            Set<Planificacioncalidad> planificacioncalidadSetNew = pedido.getPlanificacioncalidadSet();
            Set<Remito> remitoSetOld = persistentPedido.getRemitoSet();
            Set<Remito> remitoSetNew = pedido.getRemitoSet();
            Set<Detallefactura> detallefacturaSetOld = persistentPedido.getDetallefacturaSet();
            Set<Detallefactura> detallefacturaSetNew = pedido.getDetallefacturaSet();
            Set<Detallepedido> detallepedidoSetOld = persistentPedido.getDetallepedidoSet();
            Set<Detallepedido> detallepedidoSetNew = pedido.getDetallepedidoSet();
            List<String> illegalOrphanMessages = null;
            for (Detallepedido detallepedidoSetOldDetallepedido : detallepedidoSetOld) {
                if (!detallepedidoSetNew.contains(detallepedidoSetOldDetallepedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallepedido " + detallepedidoSetOldDetallepedido + " since its idpedido field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getIdcliente());
                pedido.setCliente(clienteNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                pedido.setEstado(estadoNew);
            }
            if (facturaNew != null) {
                facturaNew = em.getReference(facturaNew.getClass(), facturaNew.getIdfactura());
                pedido.setFactura(facturaNew);
            }
            if (planoNew != null) {
                planoNew = em.getReference(planoNew.getClass(), planoNew.getIdplano());
                pedido.setPlano(planoNew);
            }
            if (planprocedimientosNew != null) {
                planprocedimientosNew = em.getReference(planprocedimientosNew.getClass(), planprocedimientosNew.getIdplanprocedimientos());
                pedido.setPlanprocedimientos(planprocedimientosNew);
            }
            if (planprocesoscalidadNew != null) {
                planprocesoscalidadNew = em.getReference(planprocesoscalidadNew.getClass(), planprocesoscalidadNew.getIdplanprocesoscalidad());
                pedido.setPlanprocesoscalidad(planprocesoscalidadNew);
            }
            if (planrequerimientosmateriaprimaNew != null) {
                planrequerimientosmateriaprimaNew = em.getReference(planrequerimientosmateriaprimaNew.getClass(), planrequerimientosmateriaprimaNew.getIdplanrequerimientosmateriaprima());
                pedido.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprimaNew);
            }
            if (presupuestoNew != null) {
                presupuestoNew = em.getReference(presupuestoNew.getClass(), presupuestoNew.getIdpresupuesto());
                pedido.setPresupuesto(presupuestoNew);
            }
            if (prioridadNew != null) {
                prioridadNew = em.getReference(prioridadNew.getClass(), prioridadNew.getIdprioridad());
                pedido.setPrioridad(prioridadNew);
            }
            Set<Planificacionproduccion> attachedPlanificacionproduccionSetNew = new HashSet<Planificacionproduccion>();
            for (Planificacionproduccion planificacionproduccionSetNewPlanificacionproduccionToAttach : planificacionproduccionSetNew) {
                planificacionproduccionSetNewPlanificacionproduccionToAttach = em.getReference(planificacionproduccionSetNewPlanificacionproduccionToAttach.getClass(), planificacionproduccionSetNewPlanificacionproduccionToAttach.getIdplanificacionproduccion());
                attachedPlanificacionproduccionSetNew.add(planificacionproduccionSetNewPlanificacionproduccionToAttach);
            }
            planificacionproduccionSetNew = attachedPlanificacionproduccionSetNew;
            pedido.setPlanificacionproduccionSet(planificacionproduccionSetNew);
            Set<Productoreal> attachedProductorealSetNew = new HashSet<Productoreal>();
            for (Productoreal productorealSetNewProductorealToAttach : productorealSetNew) {
                productorealSetNewProductorealToAttach = em.getReference(productorealSetNewProductorealToAttach.getClass(), productorealSetNewProductorealToAttach.getIdproductoreal());
                attachedProductorealSetNew.add(productorealSetNewProductorealToAttach);
            }
            productorealSetNew = attachedProductorealSetNew;
            pedido.setProductorealSet(productorealSetNew);
            Set<Plano> attachedPlanoSetNew = new HashSet<Plano>();
            for (Plano planoSetNewPlanoToAttach : planoSetNew) {
                planoSetNewPlanoToAttach = em.getReference(planoSetNewPlanoToAttach.getClass(), planoSetNewPlanoToAttach.getIdplano());
                attachedPlanoSetNew.add(planoSetNewPlanoToAttach);
            }
            planoSetNew = attachedPlanoSetNew;
            pedido.setPlanoSet(planoSetNew);
            Set<Trabajotercerizado> attachedTrabajotercerizadoSetNew = new HashSet<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoSetNewTrabajotercerizadoToAttach : trabajotercerizadoSetNew) {
                trabajotercerizadoSetNewTrabajotercerizadoToAttach = em.getReference(trabajotercerizadoSetNewTrabajotercerizadoToAttach.getClass(), trabajotercerizadoSetNewTrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoSetNew.add(trabajotercerizadoSetNewTrabajotercerizadoToAttach);
            }
            trabajotercerizadoSetNew = attachedTrabajotercerizadoSetNew;
            pedido.setTrabajotercerizadoSet(trabajotercerizadoSetNew);
            Set<Planificacioncalidad> attachedPlanificacioncalidadSetNew = new HashSet<Planificacioncalidad>();
            for (Planificacioncalidad planificacioncalidadSetNewPlanificacioncalidadToAttach : planificacioncalidadSetNew) {
                planificacioncalidadSetNewPlanificacioncalidadToAttach = em.getReference(planificacioncalidadSetNewPlanificacioncalidadToAttach.getClass(), planificacioncalidadSetNewPlanificacioncalidadToAttach.getIdplanificacion());
                attachedPlanificacioncalidadSetNew.add(planificacioncalidadSetNewPlanificacioncalidadToAttach);
            }
            planificacioncalidadSetNew = attachedPlanificacioncalidadSetNew;
            pedido.setPlanificacioncalidadSet(planificacioncalidadSetNew);
            Set<Remito> attachedRemitoSetNew = new HashSet<Remito>();
            for (Remito remitoSetNewRemitoToAttach : remitoSetNew) {
                remitoSetNewRemitoToAttach = em.getReference(remitoSetNewRemitoToAttach.getClass(), remitoSetNewRemitoToAttach.getIdremito());
                attachedRemitoSetNew.add(remitoSetNewRemitoToAttach);
            }
            remitoSetNew = attachedRemitoSetNew;
            pedido.setRemitoSet(remitoSetNew);
            Set<Detallefactura> attachedDetallefacturaSetNew = new HashSet<Detallefactura>();
            for (Detallefactura detallefacturaSetNewDetallefacturaToAttach : detallefacturaSetNew) {
                detallefacturaSetNewDetallefacturaToAttach = em.getReference(detallefacturaSetNewDetallefacturaToAttach.getClass(), detallefacturaSetNewDetallefacturaToAttach.getDetallefacturaPK());
                attachedDetallefacturaSetNew.add(detallefacturaSetNewDetallefacturaToAttach);
            }
            detallefacturaSetNew = attachedDetallefacturaSetNew;
            pedido.setDetallefacturaSet(detallefacturaSetNew);
            Set<Detallepedido> attachedDetallepedidoSetNew = new HashSet<Detallepedido>();
            for (Detallepedido detallepedidoSetNewDetallepedidoToAttach : detallepedidoSetNew) {
                detallepedidoSetNewDetallepedidoToAttach = em.getReference(detallepedidoSetNewDetallepedidoToAttach.getClass(), detallepedidoSetNewDetallepedidoToAttach.getIddetalle());
                attachedDetallepedidoSetNew.add(detallepedidoSetNewDetallepedidoToAttach);
            }
            detallepedidoSetNew = attachedDetallepedidoSetNew;
            pedido.setDetallepedidoSet(detallepedidoSetNew);
            pedido = em.merge(pedido);
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getPedidoSet().remove(pedido);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getPedidoSet().add(pedido);
                clienteNew = em.merge(clienteNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getPedidoSet().remove(pedido);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getPedidoSet().add(pedido);
                estadoNew = em.merge(estadoNew);
            }
            if (facturaOld != null && !facturaOld.equals(facturaNew)) {
                facturaOld.getPedidoSet().remove(pedido);
                facturaOld = em.merge(facturaOld);
            }
            if (facturaNew != null && !facturaNew.equals(facturaOld)) {
                facturaNew.getPedidoSet().add(pedido);
                facturaNew = em.merge(facturaNew);
            }
            if (planoOld != null && !planoOld.equals(planoNew)) {
                planoOld.getPedidoSet().remove(pedido);
                planoOld = em.merge(planoOld);
            }
            if (planoNew != null && !planoNew.equals(planoOld)) {
                planoNew.getPedidoSet().add(pedido);
                planoNew = em.merge(planoNew);
            }
            if (planprocedimientosOld != null && !planprocedimientosOld.equals(planprocedimientosNew)) {
                planprocedimientosOld.getPedidoSet().remove(pedido);
                planprocedimientosOld = em.merge(planprocedimientosOld);
            }
            if (planprocedimientosNew != null && !planprocedimientosNew.equals(planprocedimientosOld)) {
                planprocedimientosNew.getPedidoSet().add(pedido);
                planprocedimientosNew = em.merge(planprocedimientosNew);
            }
            if (planprocesoscalidadOld != null && !planprocesoscalidadOld.equals(planprocesoscalidadNew)) {
                planprocesoscalidadOld.getPedidoSet().remove(pedido);
                planprocesoscalidadOld = em.merge(planprocesoscalidadOld);
            }
            if (planprocesoscalidadNew != null && !planprocesoscalidadNew.equals(planprocesoscalidadOld)) {
                planprocesoscalidadNew.getPedidoSet().add(pedido);
                planprocesoscalidadNew = em.merge(planprocesoscalidadNew);
            }
            if (planrequerimientosmateriaprimaOld != null && !planrequerimientosmateriaprimaOld.equals(planrequerimientosmateriaprimaNew)) {
                planrequerimientosmateriaprimaOld.getPedidoSet().remove(pedido);
                planrequerimientosmateriaprimaOld = em.merge(planrequerimientosmateriaprimaOld);
            }
            if (planrequerimientosmateriaprimaNew != null && !planrequerimientosmateriaprimaNew.equals(planrequerimientosmateriaprimaOld)) {
                planrequerimientosmateriaprimaNew.getPedidoSet().add(pedido);
                planrequerimientosmateriaprimaNew = em.merge(planrequerimientosmateriaprimaNew);
            }
            if (presupuestoOld != null && !presupuestoOld.equals(presupuestoNew)) {
                presupuestoOld.getPedidoSet().remove(pedido);
                presupuestoOld = em.merge(presupuestoOld);
            }
            if (presupuestoNew != null && !presupuestoNew.equals(presupuestoOld)) {
                presupuestoNew.getPedidoSet().add(pedido);
                presupuestoNew = em.merge(presupuestoNew);
            }
            if (prioridadOld != null && !prioridadOld.equals(prioridadNew)) {
                prioridadOld.getPedidoSet().remove(pedido);
                prioridadOld = em.merge(prioridadOld);
            }
            if (prioridadNew != null && !prioridadNew.equals(prioridadOld)) {
                prioridadNew.getPedidoSet().add(pedido);
                prioridadNew = em.merge(prioridadNew);
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
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Cliente cliente = pedido.getCliente();
            if (cliente != null) {
                cliente.getPedidoSet().remove(pedido);
                cliente = em.merge(cliente);
            }
            Estadopedido estado = pedido.getEstado();
            if (estado != null) {
                estado.getPedidoSet().remove(pedido);
                estado = em.merge(estado);
            }
            Factura factura = pedido.getFactura();
            if (factura != null) {
                factura.getPedidoSet().remove(pedido);
                factura = em.merge(factura);
            }
            Plano plano = pedido.getPlano();
            if (plano != null) {
                plano.getPedidoSet().remove(pedido);
                plano = em.merge(plano);
            }
            Planprocedimientos planprocedimientos = pedido.getPlanprocedimientos();
            if (planprocedimientos != null) {
                planprocedimientos.getPedidoSet().remove(pedido);
                planprocedimientos = em.merge(planprocedimientos);
            }
            Planprocesoscalidad planprocesoscalidad = pedido.getPlanprocesoscalidad();
            if (planprocesoscalidad != null) {
                planprocesoscalidad.getPedidoSet().remove(pedido);
                planprocesoscalidad = em.merge(planprocesoscalidad);
            }
            Planrequerimientosmateriaprima planrequerimientosmateriaprima = pedido.getPlanrequerimientosmateriaprima();
            if (planrequerimientosmateriaprima != null) {
                planrequerimientosmateriaprima.getPedidoSet().remove(pedido);
                planrequerimientosmateriaprima = em.merge(planrequerimientosmateriaprima);
            }
            Presupuesto presupuesto = pedido.getPresupuesto();
            if (presupuesto != null) {
                presupuesto.getPedidoSet().remove(pedido);
                presupuesto = em.merge(presupuesto);
            }
            Prioridad prioridad = pedido.getPrioridad();
            if (prioridad != null) {
                prioridad.getPedidoSet().remove(pedido);
                prioridad = em.merge(prioridad);
            }
            Set<Planificacionproduccion> planificacionproduccionSet = pedido.getPlanificacionproduccionSet();
            for (Planificacionproduccion planificacionproduccionSetPlanificacionproduccion : planificacionproduccionSet) {
                planificacionproduccionSetPlanificacionproduccion.setPedido(null);
                planificacionproduccionSetPlanificacionproduccion = em.merge(planificacionproduccionSetPlanificacionproduccion);
            }
            Set<Productoreal> productorealSet = pedido.getProductorealSet();
            for (Productoreal productorealSetProductoreal : productorealSet) {
                productorealSetProductoreal.setIdpedido(null);
                productorealSetProductoreal = em.merge(productorealSetProductoreal);
            }
            Set<Plano> planoSet = pedido.getPlanoSet();
            for (Plano planoSetPlano : planoSet) {
                planoSetPlano.setPedido(null);
                planoSetPlano = em.merge(planoSetPlano);
            }
            Set<Trabajotercerizado> trabajotercerizadoSet = pedido.getTrabajotercerizadoSet();
            for (Trabajotercerizado trabajotercerizadoSetTrabajotercerizado : trabajotercerizadoSet) {
                trabajotercerizadoSetTrabajotercerizado.setPedido(null);
                trabajotercerizadoSetTrabajotercerizado = em.merge(trabajotercerizadoSetTrabajotercerizado);
            }
            Set<Planificacioncalidad> planificacioncalidadSet = pedido.getPlanificacioncalidadSet();
            for (Planificacioncalidad planificacioncalidadSetPlanificacioncalidad : planificacioncalidadSet) {
                planificacioncalidadSetPlanificacioncalidad.setPedido(null);
                planificacioncalidadSetPlanificacioncalidad = em.merge(planificacioncalidadSetPlanificacioncalidad);
            }
            Set<Remito> remitoSet = pedido.getRemitoSet();
            for (Remito remitoSetRemito : remitoSet) {
                remitoSetRemito.setPedido(null);
                remitoSetRemito = em.merge(remitoSetRemito);
            }
            Set<Detallefactura> detallefacturaSet = pedido.getDetallefacturaSet();
            for (Detallefactura detallefacturaSetDetallefactura : detallefacturaSet) {
                detallefacturaSetDetallefactura.setIdpedido(null);
                detallefacturaSetDetallefactura = em.merge(detallefacturaSetDetallefactura);
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
