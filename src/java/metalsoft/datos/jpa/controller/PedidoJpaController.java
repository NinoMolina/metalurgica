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
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.datos.jpa.entity.Prioridad;
import metalsoft.datos.jpa.entity.Presupuesto;
import metalsoft.datos.jpa.entity.Planrequerimientosmateriaprima;
import metalsoft.datos.jpa.entity.Planprocesoscalidad;
import metalsoft.datos.jpa.entity.Planprocedimientos;
import metalsoft.datos.jpa.entity.Factura;
import metalsoft.datos.jpa.entity.Estadopedido;
import metalsoft.datos.jpa.entity.Cliente;
import metalsoft.datos.jpa.entity.Planificacionproduccion;
import java.util.ArrayList;
import java.util.List;
import metalsoft.datos.jpa.entity.Productoreal;
import metalsoft.datos.jpa.entity.Plano;
import metalsoft.datos.jpa.entity.Trabajotercerizado;
import metalsoft.datos.jpa.entity.Planificacioncalidad;
import metalsoft.datos.jpa.entity.Remito;
import metalsoft.datos.jpa.entity.Detallefactura;
import metalsoft.datos.jpa.entity.Detallepedido;

/**
 *
 * @author Nino
 */
public class PedidoJpaController implements Serializable {

    public PedidoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pedido pedido) {
        if (pedido.getPlanificacionproduccionList() == null) {
            pedido.setPlanificacionproduccionList(new ArrayList<Planificacionproduccion>());
        }
        if (pedido.getProductorealList() == null) {
            pedido.setProductorealList(new ArrayList<Productoreal>());
        }
        if (pedido.getPlanoList() == null) {
            pedido.setPlanoList(new ArrayList<Plano>());
        }
        if (pedido.getTrabajotercerizadoList() == null) {
            pedido.setTrabajotercerizadoList(new ArrayList<Trabajotercerizado>());
        }
        if (pedido.getPlanificacioncalidadList() == null) {
            pedido.setPlanificacioncalidadList(new ArrayList<Planificacioncalidad>());
        }
        if (pedido.getRemitoList() == null) {
            pedido.setRemitoList(new ArrayList<Remito>());
        }
        if (pedido.getDetallefacturaList() == null) {
            pedido.setDetallefacturaList(new ArrayList<Detallefactura>());
        }
        if (pedido.getDetallepedidoList() == null) {
            pedido.setDetallepedidoList(new ArrayList<Detallepedido>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Prioridad prioridad = pedido.getPrioridad();
            if (prioridad != null) {
                prioridad = em.getReference(prioridad.getClass(), prioridad.getIdprioridad());
                pedido.setPrioridad(prioridad);
            }
            Presupuesto presupuesto = pedido.getPresupuesto();
            if (presupuesto != null) {
                presupuesto = em.getReference(presupuesto.getClass(), presupuesto.getIdpresupuesto());
                pedido.setPresupuesto(presupuesto);
            }
            Planrequerimientosmateriaprima planrequerimientosmateriaprima = pedido.getPlanrequerimientosmateriaprima();
            if (planrequerimientosmateriaprima != null) {
                planrequerimientosmateriaprima = em.getReference(planrequerimientosmateriaprima.getClass(), planrequerimientosmateriaprima.getIdplanrequerimientosmateriaprima());
                pedido.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprima);
            }
            Planprocesoscalidad planprocesoscalidad = pedido.getPlanprocesoscalidad();
            if (planprocesoscalidad != null) {
                planprocesoscalidad = em.getReference(planprocesoscalidad.getClass(), planprocesoscalidad.getIdplanprocesoscalidad());
                pedido.setPlanprocesoscalidad(planprocesoscalidad);
            }
            Planprocedimientos planprocedimientos = pedido.getPlanprocedimientos();
            if (planprocedimientos != null) {
                planprocedimientos = em.getReference(planprocedimientos.getClass(), planprocedimientos.getIdplanprocedimientos());
                pedido.setPlanprocedimientos(planprocedimientos);
            }
            Factura factura = pedido.getFactura();
            if (factura != null) {
                factura = em.getReference(factura.getClass(), factura.getIdfactura());
                pedido.setFactura(factura);
            }
            Estadopedido estado = pedido.getEstado();
            if (estado != null) {
                estado = em.getReference(estado.getClass(), estado.getIdestado());
                pedido.setEstado(estado);
            }
            Cliente cliente = pedido.getCliente();
            if (cliente != null) {
                cliente = em.getReference(cliente.getClass(), cliente.getIdcliente());
                pedido.setCliente(cliente);
            }
            List<Planificacionproduccion> attachedPlanificacionproduccionList = new ArrayList<Planificacionproduccion>();
            for (Planificacionproduccion planificacionproduccionListPlanificacionproduccionToAttach : pedido.getPlanificacionproduccionList()) {
                planificacionproduccionListPlanificacionproduccionToAttach = em.getReference(planificacionproduccionListPlanificacionproduccionToAttach.getClass(), planificacionproduccionListPlanificacionproduccionToAttach.getIdplanificacionproduccion());
                attachedPlanificacionproduccionList.add(planificacionproduccionListPlanificacionproduccionToAttach);
            }
            pedido.setPlanificacionproduccionList(attachedPlanificacionproduccionList);
            List<Productoreal> attachedProductorealList = new ArrayList<Productoreal>();
            for (Productoreal productorealListProductorealToAttach : pedido.getProductorealList()) {
                productorealListProductorealToAttach = em.getReference(productorealListProductorealToAttach.getClass(), productorealListProductorealToAttach.getIdproductoreal());
                attachedProductorealList.add(productorealListProductorealToAttach);
            }
            pedido.setProductorealList(attachedProductorealList);
            List<Plano> attachedPlanoList = new ArrayList<Plano>();
            for (Plano planoListPlanoToAttach : pedido.getPlanoList()) {
                planoListPlanoToAttach = em.getReference(planoListPlanoToAttach.getClass(), planoListPlanoToAttach.getIdplano());
                attachedPlanoList.add(planoListPlanoToAttach);
            }
            pedido.setPlanoList(attachedPlanoList);
            List<Trabajotercerizado> attachedTrabajotercerizadoList = new ArrayList<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoListTrabajotercerizadoToAttach : pedido.getTrabajotercerizadoList()) {
                trabajotercerizadoListTrabajotercerizadoToAttach = em.getReference(trabajotercerizadoListTrabajotercerizadoToAttach.getClass(), trabajotercerizadoListTrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoList.add(trabajotercerizadoListTrabajotercerizadoToAttach);
            }
            pedido.setTrabajotercerizadoList(attachedTrabajotercerizadoList);
            List<Planificacioncalidad> attachedPlanificacioncalidadList = new ArrayList<Planificacioncalidad>();
            for (Planificacioncalidad planificacioncalidadListPlanificacioncalidadToAttach : pedido.getPlanificacioncalidadList()) {
                planificacioncalidadListPlanificacioncalidadToAttach = em.getReference(planificacioncalidadListPlanificacioncalidadToAttach.getClass(), planificacioncalidadListPlanificacioncalidadToAttach.getIdplanificacion());
                attachedPlanificacioncalidadList.add(planificacioncalidadListPlanificacioncalidadToAttach);
            }
            pedido.setPlanificacioncalidadList(attachedPlanificacioncalidadList);
            List<Remito> attachedRemitoList = new ArrayList<Remito>();
            for (Remito remitoListRemitoToAttach : pedido.getRemitoList()) {
                remitoListRemitoToAttach = em.getReference(remitoListRemitoToAttach.getClass(), remitoListRemitoToAttach.getIdremito());
                attachedRemitoList.add(remitoListRemitoToAttach);
            }
            pedido.setRemitoList(attachedRemitoList);
            List<Detallefactura> attachedDetallefacturaList = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListDetallefacturaToAttach : pedido.getDetallefacturaList()) {
                detallefacturaListDetallefacturaToAttach = em.getReference(detallefacturaListDetallefacturaToAttach.getClass(), detallefacturaListDetallefacturaToAttach.getDetallefacturaPK());
                attachedDetallefacturaList.add(detallefacturaListDetallefacturaToAttach);
            }
            pedido.setDetallefacturaList(attachedDetallefacturaList);
            List<Detallepedido> attachedDetallepedidoList = new ArrayList<Detallepedido>();
            for (Detallepedido detallepedidoListDetallepedidoToAttach : pedido.getDetallepedidoList()) {
                detallepedidoListDetallepedidoToAttach = em.getReference(detallepedidoListDetallepedidoToAttach.getClass(), detallepedidoListDetallepedidoToAttach.getIddetalle());
                attachedDetallepedidoList.add(detallepedidoListDetallepedidoToAttach);
            }
            pedido.setDetallepedidoList(attachedDetallepedidoList);
            em.persist(pedido);
            if (prioridad != null) {
                prioridad.getPedidoList().add(pedido);
                prioridad = em.merge(prioridad);
            }
            if (presupuesto != null) {
                presupuesto.getPedidoList().add(pedido);
                presupuesto = em.merge(presupuesto);
            }
            if (planrequerimientosmateriaprima != null) {
                planrequerimientosmateriaprima.getPedidoList().add(pedido);
                planrequerimientosmateriaprima = em.merge(planrequerimientosmateriaprima);
            }
            if (planprocesoscalidad != null) {
                planprocesoscalidad.getPedidoList().add(pedido);
                planprocesoscalidad = em.merge(planprocesoscalidad);
            }
            if (planprocedimientos != null) {
                planprocedimientos.getPedidoList().add(pedido);
                planprocedimientos = em.merge(planprocedimientos);
            }
            if (factura != null) {
                factura.getPedidoList().add(pedido);
                factura = em.merge(factura);
            }
            if (estado != null) {
                estado.getPedidoList().add(pedido);
                estado = em.merge(estado);
            }
            if (cliente != null) {
                cliente.getPedidoList().add(pedido);
                cliente = em.merge(cliente);
            }
            for (Planificacionproduccion planificacionproduccionListPlanificacionproduccion : pedido.getPlanificacionproduccionList()) {
                Pedido oldPedidoOfPlanificacionproduccionListPlanificacionproduccion = planificacionproduccionListPlanificacionproduccion.getPedido();
                planificacionproduccionListPlanificacionproduccion.setPedido(pedido);
                planificacionproduccionListPlanificacionproduccion = em.merge(planificacionproduccionListPlanificacionproduccion);
                if (oldPedidoOfPlanificacionproduccionListPlanificacionproduccion != null) {
                    oldPedidoOfPlanificacionproduccionListPlanificacionproduccion.getPlanificacionproduccionList().remove(planificacionproduccionListPlanificacionproduccion);
                    oldPedidoOfPlanificacionproduccionListPlanificacionproduccion = em.merge(oldPedidoOfPlanificacionproduccionListPlanificacionproduccion);
                }
            }
            for (Productoreal productorealListProductoreal : pedido.getProductorealList()) {
                Pedido oldIdpedidoOfProductorealListProductoreal = productorealListProductoreal.getIdpedido();
                productorealListProductoreal.setIdpedido(pedido);
                productorealListProductoreal = em.merge(productorealListProductoreal);
                if (oldIdpedidoOfProductorealListProductoreal != null) {
                    oldIdpedidoOfProductorealListProductoreal.getProductorealList().remove(productorealListProductoreal);
                    oldIdpedidoOfProductorealListProductoreal = em.merge(oldIdpedidoOfProductorealListProductoreal);
                }
            }
            for (Plano planoListPlano : pedido.getPlanoList()) {
                Pedido oldPedidoOfPlanoListPlano = planoListPlano.getPedido();
                planoListPlano.setPedido(pedido);
                planoListPlano = em.merge(planoListPlano);
                if (oldPedidoOfPlanoListPlano != null) {
                    oldPedidoOfPlanoListPlano.getPlanoList().remove(planoListPlano);
                    oldPedidoOfPlanoListPlano = em.merge(oldPedidoOfPlanoListPlano);
                }
            }
            for (Trabajotercerizado trabajotercerizadoListTrabajotercerizado : pedido.getTrabajotercerizadoList()) {
                Pedido oldPedidoOfTrabajotercerizadoListTrabajotercerizado = trabajotercerizadoListTrabajotercerizado.getPedido();
                trabajotercerizadoListTrabajotercerizado.setPedido(pedido);
                trabajotercerizadoListTrabajotercerizado = em.merge(trabajotercerizadoListTrabajotercerizado);
                if (oldPedidoOfTrabajotercerizadoListTrabajotercerizado != null) {
                    oldPedidoOfTrabajotercerizadoListTrabajotercerizado.getTrabajotercerizadoList().remove(trabajotercerizadoListTrabajotercerizado);
                    oldPedidoOfTrabajotercerizadoListTrabajotercerizado = em.merge(oldPedidoOfTrabajotercerizadoListTrabajotercerizado);
                }
            }
            for (Planificacioncalidad planificacioncalidadListPlanificacioncalidad : pedido.getPlanificacioncalidadList()) {
                Pedido oldPedidoOfPlanificacioncalidadListPlanificacioncalidad = planificacioncalidadListPlanificacioncalidad.getPedido();
                planificacioncalidadListPlanificacioncalidad.setPedido(pedido);
                planificacioncalidadListPlanificacioncalidad = em.merge(planificacioncalidadListPlanificacioncalidad);
                if (oldPedidoOfPlanificacioncalidadListPlanificacioncalidad != null) {
                    oldPedidoOfPlanificacioncalidadListPlanificacioncalidad.getPlanificacioncalidadList().remove(planificacioncalidadListPlanificacioncalidad);
                    oldPedidoOfPlanificacioncalidadListPlanificacioncalidad = em.merge(oldPedidoOfPlanificacioncalidadListPlanificacioncalidad);
                }
            }
            for (Remito remitoListRemito : pedido.getRemitoList()) {
                Pedido oldPedidoOfRemitoListRemito = remitoListRemito.getPedido();
                remitoListRemito.setPedido(pedido);
                remitoListRemito = em.merge(remitoListRemito);
                if (oldPedidoOfRemitoListRemito != null) {
                    oldPedidoOfRemitoListRemito.getRemitoList().remove(remitoListRemito);
                    oldPedidoOfRemitoListRemito = em.merge(oldPedidoOfRemitoListRemito);
                }
            }
            for (Detallefactura detallefacturaListDetallefactura : pedido.getDetallefacturaList()) {
                Pedido oldIdpedidoOfDetallefacturaListDetallefactura = detallefacturaListDetallefactura.getIdpedido();
                detallefacturaListDetallefactura.setIdpedido(pedido);
                detallefacturaListDetallefactura = em.merge(detallefacturaListDetallefactura);
                if (oldIdpedidoOfDetallefacturaListDetallefactura != null) {
                    oldIdpedidoOfDetallefacturaListDetallefactura.getDetallefacturaList().remove(detallefacturaListDetallefactura);
                    oldIdpedidoOfDetallefacturaListDetallefactura = em.merge(oldIdpedidoOfDetallefacturaListDetallefactura);
                }
            }
            for (Detallepedido detallepedidoListDetallepedido : pedido.getDetallepedidoList()) {
                Pedido oldIdpedidoOfDetallepedidoListDetallepedido = detallepedidoListDetallepedido.getIdpedido();
                detallepedidoListDetallepedido.setIdpedido(pedido);
                detallepedidoListDetallepedido = em.merge(detallepedidoListDetallepedido);
                if (oldIdpedidoOfDetallepedidoListDetallepedido != null) {
                    oldIdpedidoOfDetallepedidoListDetallepedido.getDetallepedidoList().remove(detallepedidoListDetallepedido);
                    oldIdpedidoOfDetallepedidoListDetallepedido = em.merge(oldIdpedidoOfDetallepedidoListDetallepedido);
                }
            }
            em.getTransaction().commit();
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
            Prioridad prioridadOld = persistentPedido.getPrioridad();
            Prioridad prioridadNew = pedido.getPrioridad();
            Presupuesto presupuestoOld = persistentPedido.getPresupuesto();
            Presupuesto presupuestoNew = pedido.getPresupuesto();
            Planrequerimientosmateriaprima planrequerimientosmateriaprimaOld = persistentPedido.getPlanrequerimientosmateriaprima();
            Planrequerimientosmateriaprima planrequerimientosmateriaprimaNew = pedido.getPlanrequerimientosmateriaprima();
            Planprocesoscalidad planprocesoscalidadOld = persistentPedido.getPlanprocesoscalidad();
            Planprocesoscalidad planprocesoscalidadNew = pedido.getPlanprocesoscalidad();
            Planprocedimientos planprocedimientosOld = persistentPedido.getPlanprocedimientos();
            Planprocedimientos planprocedimientosNew = pedido.getPlanprocedimientos();
            Factura facturaOld = persistentPedido.getFactura();
            Factura facturaNew = pedido.getFactura();
            Estadopedido estadoOld = persistentPedido.getEstado();
            Estadopedido estadoNew = pedido.getEstado();
            Cliente clienteOld = persistentPedido.getCliente();
            Cliente clienteNew = pedido.getCliente();
            List<Planificacionproduccion> planificacionproduccionListOld = persistentPedido.getPlanificacionproduccionList();
            List<Planificacionproduccion> planificacionproduccionListNew = pedido.getPlanificacionproduccionList();
            List<Productoreal> productorealListOld = persistentPedido.getProductorealList();
            List<Productoreal> productorealListNew = pedido.getProductorealList();
            List<Plano> planoListOld = persistentPedido.getPlanoList();
            List<Plano> planoListNew = pedido.getPlanoList();
            List<Trabajotercerizado> trabajotercerizadoListOld = persistentPedido.getTrabajotercerizadoList();
            List<Trabajotercerizado> trabajotercerizadoListNew = pedido.getTrabajotercerizadoList();
            List<Planificacioncalidad> planificacioncalidadListOld = persistentPedido.getPlanificacioncalidadList();
            List<Planificacioncalidad> planificacioncalidadListNew = pedido.getPlanificacioncalidadList();
            List<Remito> remitoListOld = persistentPedido.getRemitoList();
            List<Remito> remitoListNew = pedido.getRemitoList();
            List<Detallefactura> detallefacturaListOld = persistentPedido.getDetallefacturaList();
            List<Detallefactura> detallefacturaListNew = pedido.getDetallefacturaList();
            List<Detallepedido> detallepedidoListOld = persistentPedido.getDetallepedidoList();
            List<Detallepedido> detallepedidoListNew = pedido.getDetallepedidoList();
            List<String> illegalOrphanMessages = null;
            for (Detallepedido detallepedidoListOldDetallepedido : detallepedidoListOld) {
                if (!detallepedidoListNew.contains(detallepedidoListOldDetallepedido)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Detallepedido " + detallepedidoListOldDetallepedido + " since its idpedido field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (prioridadNew != null) {
                prioridadNew = em.getReference(prioridadNew.getClass(), prioridadNew.getIdprioridad());
                pedido.setPrioridad(prioridadNew);
            }
            if (presupuestoNew != null) {
                presupuestoNew = em.getReference(presupuestoNew.getClass(), presupuestoNew.getIdpresupuesto());
                pedido.setPresupuesto(presupuestoNew);
            }
            if (planrequerimientosmateriaprimaNew != null) {
                planrequerimientosmateriaprimaNew = em.getReference(planrequerimientosmateriaprimaNew.getClass(), planrequerimientosmateriaprimaNew.getIdplanrequerimientosmateriaprima());
                pedido.setPlanrequerimientosmateriaprima(planrequerimientosmateriaprimaNew);
            }
            if (planprocesoscalidadNew != null) {
                planprocesoscalidadNew = em.getReference(planprocesoscalidadNew.getClass(), planprocesoscalidadNew.getIdplanprocesoscalidad());
                pedido.setPlanprocesoscalidad(planprocesoscalidadNew);
            }
            if (planprocedimientosNew != null) {
                planprocedimientosNew = em.getReference(planprocedimientosNew.getClass(), planprocedimientosNew.getIdplanprocedimientos());
                pedido.setPlanprocedimientos(planprocedimientosNew);
            }
            if (facturaNew != null) {
                facturaNew = em.getReference(facturaNew.getClass(), facturaNew.getIdfactura());
                pedido.setFactura(facturaNew);
            }
            if (estadoNew != null) {
                estadoNew = em.getReference(estadoNew.getClass(), estadoNew.getIdestado());
                pedido.setEstado(estadoNew);
            }
            if (clienteNew != null) {
                clienteNew = em.getReference(clienteNew.getClass(), clienteNew.getIdcliente());
                pedido.setCliente(clienteNew);
            }
            List<Planificacionproduccion> attachedPlanificacionproduccionListNew = new ArrayList<Planificacionproduccion>();
            for (Planificacionproduccion planificacionproduccionListNewPlanificacionproduccionToAttach : planificacionproduccionListNew) {
                planificacionproduccionListNewPlanificacionproduccionToAttach = em.getReference(planificacionproduccionListNewPlanificacionproduccionToAttach.getClass(), planificacionproduccionListNewPlanificacionproduccionToAttach.getIdplanificacionproduccion());
                attachedPlanificacionproduccionListNew.add(planificacionproduccionListNewPlanificacionproduccionToAttach);
            }
            planificacionproduccionListNew = attachedPlanificacionproduccionListNew;
            pedido.setPlanificacionproduccionList(planificacionproduccionListNew);
            List<Productoreal> attachedProductorealListNew = new ArrayList<Productoreal>();
            for (Productoreal productorealListNewProductorealToAttach : productorealListNew) {
                productorealListNewProductorealToAttach = em.getReference(productorealListNewProductorealToAttach.getClass(), productorealListNewProductorealToAttach.getIdproductoreal());
                attachedProductorealListNew.add(productorealListNewProductorealToAttach);
            }
            productorealListNew = attachedProductorealListNew;
            pedido.setProductorealList(productorealListNew);
//            List<Plano> attachedPlanoListNew = new ArrayList<Plano>();
//            for (Plano planoListNewPlanoToAttach : planoListNew) {
//                planoListNewPlanoToAttach = em.getReference(planoListNewPlanoToAttach.getClass(), planoListNewPlanoToAttach.getIdplano());
//                attachedPlanoListNew.add(planoListNewPlanoToAttach);
//            }
//            planoListNew = attachedPlanoListNew;
//            pedido.setPlanoList(planoListNew);
            List<Trabajotercerizado> attachedTrabajotercerizadoListNew = new ArrayList<Trabajotercerizado>();
            for (Trabajotercerizado trabajotercerizadoListNewTrabajotercerizadoToAttach : trabajotercerizadoListNew) {
                trabajotercerizadoListNewTrabajotercerizadoToAttach = em.getReference(trabajotercerizadoListNewTrabajotercerizadoToAttach.getClass(), trabajotercerizadoListNewTrabajotercerizadoToAttach.getIdtrabajo());
                attachedTrabajotercerizadoListNew.add(trabajotercerizadoListNewTrabajotercerizadoToAttach);
            }
            trabajotercerizadoListNew = attachedTrabajotercerizadoListNew;
            pedido.setTrabajotercerizadoList(trabajotercerizadoListNew);
            List<Planificacioncalidad> attachedPlanificacioncalidadListNew = new ArrayList<Planificacioncalidad>();
            for (Planificacioncalidad planificacioncalidadListNewPlanificacioncalidadToAttach : planificacioncalidadListNew) {
                planificacioncalidadListNewPlanificacioncalidadToAttach = em.getReference(planificacioncalidadListNewPlanificacioncalidadToAttach.getClass(), planificacioncalidadListNewPlanificacioncalidadToAttach.getIdplanificacion());
                attachedPlanificacioncalidadListNew.add(planificacioncalidadListNewPlanificacioncalidadToAttach);
            }
            planificacioncalidadListNew = attachedPlanificacioncalidadListNew;
            pedido.setPlanificacioncalidadList(planificacioncalidadListNew);
            List<Remito> attachedRemitoListNew = new ArrayList<Remito>();
            for (Remito remitoListNewRemitoToAttach : remitoListNew) {
                remitoListNewRemitoToAttach = em.getReference(remitoListNewRemitoToAttach.getClass(), remitoListNewRemitoToAttach.getIdremito());
                attachedRemitoListNew.add(remitoListNewRemitoToAttach);
            }
            remitoListNew = attachedRemitoListNew;
            pedido.setRemitoList(remitoListNew);
            List<Detallefactura> attachedDetallefacturaListNew = new ArrayList<Detallefactura>();
            for (Detallefactura detallefacturaListNewDetallefacturaToAttach : detallefacturaListNew) {
                detallefacturaListNewDetallefacturaToAttach = em.getReference(detallefacturaListNewDetallefacturaToAttach.getClass(), detallefacturaListNewDetallefacturaToAttach.getDetallefacturaPK());
                attachedDetallefacturaListNew.add(detallefacturaListNewDetallefacturaToAttach);
            }
            detallefacturaListNew = attachedDetallefacturaListNew;
            pedido.setDetallefacturaList(detallefacturaListNew);
            List<Detallepedido> attachedDetallepedidoListNew = new ArrayList<Detallepedido>();
            for (Detallepedido detallepedidoListNewDetallepedidoToAttach : detallepedidoListNew) {
                detallepedidoListNewDetallepedidoToAttach = em.getReference(detallepedidoListNewDetallepedidoToAttach.getClass(), detallepedidoListNewDetallepedidoToAttach.getIddetalle());
                attachedDetallepedidoListNew.add(detallepedidoListNewDetallepedidoToAttach);
            }
            detallepedidoListNew = attachedDetallepedidoListNew;
            pedido.setDetallepedidoList(detallepedidoListNew);
            pedido = em.merge(pedido);
            if (prioridadOld != null && !prioridadOld.equals(prioridadNew)) {
                prioridadOld.getPedidoList().remove(pedido);
                prioridadOld = em.merge(prioridadOld);
            }
            if (prioridadNew != null && !prioridadNew.equals(prioridadOld)) {
                prioridadNew.getPedidoList().add(pedido);
                prioridadNew = em.merge(prioridadNew);
            }
            if (presupuestoOld != null && !presupuestoOld.equals(presupuestoNew)) {
                presupuestoOld.getPedidoList().remove(pedido);
                presupuestoOld = em.merge(presupuestoOld);
            }
            if (presupuestoNew != null && !presupuestoNew.equals(presupuestoOld)) {
                presupuestoNew.getPedidoList().add(pedido);
                presupuestoNew = em.merge(presupuestoNew);
            }
            if (planrequerimientosmateriaprimaOld != null && !planrequerimientosmateriaprimaOld.equals(planrequerimientosmateriaprimaNew)) {
                planrequerimientosmateriaprimaOld.getPedidoList().remove(pedido);
                planrequerimientosmateriaprimaOld = em.merge(planrequerimientosmateriaprimaOld);
            }
            if (planrequerimientosmateriaprimaNew != null && !planrequerimientosmateriaprimaNew.equals(planrequerimientosmateriaprimaOld)) {
                planrequerimientosmateriaprimaNew.getPedidoList().add(pedido);
                planrequerimientosmateriaprimaNew = em.merge(planrequerimientosmateriaprimaNew);
            }
            if (planprocesoscalidadOld != null && !planprocesoscalidadOld.equals(planprocesoscalidadNew)) {
                planprocesoscalidadOld.getPedidoList().remove(pedido);
                planprocesoscalidadOld = em.merge(planprocesoscalidadOld);
            }
            if (planprocesoscalidadNew != null && !planprocesoscalidadNew.equals(planprocesoscalidadOld)) {
                planprocesoscalidadNew.getPedidoList().add(pedido);
                planprocesoscalidadNew = em.merge(planprocesoscalidadNew);
            }
            if (planprocedimientosOld != null && !planprocedimientosOld.equals(planprocedimientosNew)) {
                planprocedimientosOld.getPedidoList().remove(pedido);
                planprocedimientosOld = em.merge(planprocedimientosOld);
            }
            if (planprocedimientosNew != null && !planprocedimientosNew.equals(planprocedimientosOld)) {
                planprocedimientosNew.getPedidoList().add(pedido);
                planprocedimientosNew = em.merge(planprocedimientosNew);
            }
            if (facturaOld != null && !facturaOld.equals(facturaNew)) {
                facturaOld.getPedidoList().remove(pedido);
                facturaOld = em.merge(facturaOld);
            }
            if (facturaNew != null && !facturaNew.equals(facturaOld)) {
                facturaNew.getPedidoList().add(pedido);
                facturaNew = em.merge(facturaNew);
            }
            if (estadoOld != null && !estadoOld.equals(estadoNew)) {
                estadoOld.getPedidoList().remove(pedido);
                estadoOld = em.merge(estadoOld);
            }
            if (estadoNew != null && !estadoNew.equals(estadoOld)) {
                estadoNew.getPedidoList().add(pedido);
                estadoNew = em.merge(estadoNew);
            }
            if (clienteOld != null && !clienteOld.equals(clienteNew)) {
                clienteOld.getPedidoList().remove(pedido);
                clienteOld = em.merge(clienteOld);
            }
            if (clienteNew != null && !clienteNew.equals(clienteOld)) {
                clienteNew.getPedidoList().add(pedido);
                clienteNew = em.merge(clienteNew);
            }
            for (Planificacionproduccion planificacionproduccionListOldPlanificacionproduccion : planificacionproduccionListOld) {
                if (!planificacionproduccionListNew.contains(planificacionproduccionListOldPlanificacionproduccion)) {
                    planificacionproduccionListOldPlanificacionproduccion.setPedido(null);
                    planificacionproduccionListOldPlanificacionproduccion = em.merge(planificacionproduccionListOldPlanificacionproduccion);
                }
            }
            for (Planificacionproduccion planificacionproduccionListNewPlanificacionproduccion : planificacionproduccionListNew) {
                if (!planificacionproduccionListOld.contains(planificacionproduccionListNewPlanificacionproduccion)) {
                    Pedido oldPedidoOfPlanificacionproduccionListNewPlanificacionproduccion = planificacionproduccionListNewPlanificacionproduccion.getPedido();
                    planificacionproduccionListNewPlanificacionproduccion.setPedido(pedido);
                    planificacionproduccionListNewPlanificacionproduccion = em.merge(planificacionproduccionListNewPlanificacionproduccion);
                    if (oldPedidoOfPlanificacionproduccionListNewPlanificacionproduccion != null && !oldPedidoOfPlanificacionproduccionListNewPlanificacionproduccion.equals(pedido)) {
                        oldPedidoOfPlanificacionproduccionListNewPlanificacionproduccion.getPlanificacionproduccionList().remove(planificacionproduccionListNewPlanificacionproduccion);
                        oldPedidoOfPlanificacionproduccionListNewPlanificacionproduccion = em.merge(oldPedidoOfPlanificacionproduccionListNewPlanificacionproduccion);
                    }
                }
            }
            for (Productoreal productorealListOldProductoreal : productorealListOld) {
                if (!productorealListNew.contains(productorealListOldProductoreal)) {
                    productorealListOldProductoreal.setIdpedido(null);
                    productorealListOldProductoreal = em.merge(productorealListOldProductoreal);
                }
            }
            for (Productoreal productorealListNewProductoreal : productorealListNew) {
                if (!productorealListOld.contains(productorealListNewProductoreal)) {
                    Pedido oldIdpedidoOfProductorealListNewProductoreal = productorealListNewProductoreal.getIdpedido();
                    productorealListNewProductoreal.setIdpedido(pedido);
                    productorealListNewProductoreal = em.merge(productorealListNewProductoreal);
                    if (oldIdpedidoOfProductorealListNewProductoreal != null && !oldIdpedidoOfProductorealListNewProductoreal.equals(pedido)) {
                        oldIdpedidoOfProductorealListNewProductoreal.getProductorealList().remove(productorealListNewProductoreal);
                        oldIdpedidoOfProductorealListNewProductoreal = em.merge(oldIdpedidoOfProductorealListNewProductoreal);
                    }
                }
            }
//            for (Plano planoListOldPlano : planoListOld) {
//                if (!planoListNew.contains(planoListOldPlano)) {
//                    planoListOldPlano.setPedido(null);
//                    planoListOldPlano = em.merge(planoListOldPlano);
//                }
//            }
//            for (Plano planoListNewPlano : planoListNew) {
//                if (!planoListOld.contains(planoListNewPlano)) {
//                    Pedido oldPedidoOfPlanoListNewPlano = planoListNewPlano.getPedido();
//                    planoListNewPlano.setPedido(pedido);
//                    planoListNewPlano = em.merge(planoListNewPlano);
//                    if (oldPedidoOfPlanoListNewPlano != null && !oldPedidoOfPlanoListNewPlano.equals(pedido)) {
//                        oldPedidoOfPlanoListNewPlano.getPlanoList().remove(planoListNewPlano);
//                        oldPedidoOfPlanoListNewPlano = em.merge(oldPedidoOfPlanoListNewPlano);
//                    }
//                }
//            }
            for (Trabajotercerizado trabajotercerizadoListOldTrabajotercerizado : trabajotercerizadoListOld) {
                if (!trabajotercerizadoListNew.contains(trabajotercerizadoListOldTrabajotercerizado)) {
                    trabajotercerizadoListOldTrabajotercerizado.setPedido(null);
                    trabajotercerizadoListOldTrabajotercerizado = em.merge(trabajotercerizadoListOldTrabajotercerizado);
                }
            }
            for (Trabajotercerizado trabajotercerizadoListNewTrabajotercerizado : trabajotercerizadoListNew) {
                if (!trabajotercerizadoListOld.contains(trabajotercerizadoListNewTrabajotercerizado)) {
                    Pedido oldPedidoOfTrabajotercerizadoListNewTrabajotercerizado = trabajotercerizadoListNewTrabajotercerizado.getPedido();
                    trabajotercerizadoListNewTrabajotercerizado.setPedido(pedido);
                    trabajotercerizadoListNewTrabajotercerizado = em.merge(trabajotercerizadoListNewTrabajotercerizado);
                    if (oldPedidoOfTrabajotercerizadoListNewTrabajotercerizado != null && !oldPedidoOfTrabajotercerizadoListNewTrabajotercerizado.equals(pedido)) {
                        oldPedidoOfTrabajotercerizadoListNewTrabajotercerizado.getTrabajotercerizadoList().remove(trabajotercerizadoListNewTrabajotercerizado);
                        oldPedidoOfTrabajotercerizadoListNewTrabajotercerizado = em.merge(oldPedidoOfTrabajotercerizadoListNewTrabajotercerizado);
                    }
                }
            }
            for (Planificacioncalidad planificacioncalidadListOldPlanificacioncalidad : planificacioncalidadListOld) {
                if (!planificacioncalidadListNew.contains(planificacioncalidadListOldPlanificacioncalidad)) {
                    planificacioncalidadListOldPlanificacioncalidad.setPedido(null);
                    planificacioncalidadListOldPlanificacioncalidad = em.merge(planificacioncalidadListOldPlanificacioncalidad);
                }
            }
            for (Planificacioncalidad planificacioncalidadListNewPlanificacioncalidad : planificacioncalidadListNew) {
                if (!planificacioncalidadListOld.contains(planificacioncalidadListNewPlanificacioncalidad)) {
                    Pedido oldPedidoOfPlanificacioncalidadListNewPlanificacioncalidad = planificacioncalidadListNewPlanificacioncalidad.getPedido();
                    planificacioncalidadListNewPlanificacioncalidad.setPedido(pedido);
                    planificacioncalidadListNewPlanificacioncalidad = em.merge(planificacioncalidadListNewPlanificacioncalidad);
                    if (oldPedidoOfPlanificacioncalidadListNewPlanificacioncalidad != null && !oldPedidoOfPlanificacioncalidadListNewPlanificacioncalidad.equals(pedido)) {
                        oldPedidoOfPlanificacioncalidadListNewPlanificacioncalidad.getPlanificacioncalidadList().remove(planificacioncalidadListNewPlanificacioncalidad);
                        oldPedidoOfPlanificacioncalidadListNewPlanificacioncalidad = em.merge(oldPedidoOfPlanificacioncalidadListNewPlanificacioncalidad);
                    }
                }
            }
            for (Remito remitoListOldRemito : remitoListOld) {
                if (!remitoListNew.contains(remitoListOldRemito)) {
                    remitoListOldRemito.setPedido(null);
                    remitoListOldRemito = em.merge(remitoListOldRemito);
                }
            }
            for (Remito remitoListNewRemito : remitoListNew) {
                if (!remitoListOld.contains(remitoListNewRemito)) {
                    Pedido oldPedidoOfRemitoListNewRemito = remitoListNewRemito.getPedido();
                    remitoListNewRemito.setPedido(pedido);
                    remitoListNewRemito = em.merge(remitoListNewRemito);
                    if (oldPedidoOfRemitoListNewRemito != null && !oldPedidoOfRemitoListNewRemito.equals(pedido)) {
                        oldPedidoOfRemitoListNewRemito.getRemitoList().remove(remitoListNewRemito);
                        oldPedidoOfRemitoListNewRemito = em.merge(oldPedidoOfRemitoListNewRemito);
                    }
                }
            }
            for (Detallefactura detallefacturaListOldDetallefactura : detallefacturaListOld) {
                if (!detallefacturaListNew.contains(detallefacturaListOldDetallefactura)) {
                    detallefacturaListOldDetallefactura.setIdpedido(null);
                    detallefacturaListOldDetallefactura = em.merge(detallefacturaListOldDetallefactura);
                }
            }
            for (Detallefactura detallefacturaListNewDetallefactura : detallefacturaListNew) {
                if (!detallefacturaListOld.contains(detallefacturaListNewDetallefactura)) {
                    Pedido oldIdpedidoOfDetallefacturaListNewDetallefactura = detallefacturaListNewDetallefactura.getIdpedido();
                    detallefacturaListNewDetallefactura.setIdpedido(pedido);
                    detallefacturaListNewDetallefactura = em.merge(detallefacturaListNewDetallefactura);
                    if (oldIdpedidoOfDetallefacturaListNewDetallefactura != null && !oldIdpedidoOfDetallefacturaListNewDetallefactura.equals(pedido)) {
                        oldIdpedidoOfDetallefacturaListNewDetallefactura.getDetallefacturaList().remove(detallefacturaListNewDetallefactura);
                        oldIdpedidoOfDetallefacturaListNewDetallefactura = em.merge(oldIdpedidoOfDetallefacturaListNewDetallefactura);
                    }
                }
            }
            for (Detallepedido detallepedidoListNewDetallepedido : detallepedidoListNew) {
                if (!detallepedidoListOld.contains(detallepedidoListNewDetallepedido)) {
                    Pedido oldIdpedidoOfDetallepedidoListNewDetallepedido = detallepedidoListNewDetallepedido.getIdpedido();
                    detallepedidoListNewDetallepedido.setIdpedido(pedido);
                    detallepedidoListNewDetallepedido = em.merge(detallepedidoListNewDetallepedido);
                    if (oldIdpedidoOfDetallepedidoListNewDetallepedido != null && !oldIdpedidoOfDetallepedidoListNewDetallepedido.equals(pedido)) {
                        oldIdpedidoOfDetallepedidoListNewDetallepedido.getDetallepedidoList().remove(detallepedidoListNewDetallepedido);
                        oldIdpedidoOfDetallepedidoListNewDetallepedido = em.merge(oldIdpedidoOfDetallepedidoListNewDetallepedido);
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
            List<Detallepedido> detallepedidoListOrphanCheck = pedido.getDetallepedidoList();
            for (Detallepedido detallepedidoListOrphanCheckDetallepedido : detallepedidoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Pedido (" + pedido + ") cannot be destroyed since the Detallepedido " + detallepedidoListOrphanCheckDetallepedido + " in its detallepedidoList field has a non-nullable idpedido field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Prioridad prioridad = pedido.getPrioridad();
            if (prioridad != null) {
                prioridad.getPedidoList().remove(pedido);
                prioridad = em.merge(prioridad);
            }
            Presupuesto presupuesto = pedido.getPresupuesto();
            if (presupuesto != null) {
                presupuesto.getPedidoList().remove(pedido);
                presupuesto = em.merge(presupuesto);
            }
            Planrequerimientosmateriaprima planrequerimientosmateriaprima = pedido.getPlanrequerimientosmateriaprima();
            if (planrequerimientosmateriaprima != null) {
                planrequerimientosmateriaprima.getPedidoList().remove(pedido);
                planrequerimientosmateriaprima = em.merge(planrequerimientosmateriaprima);
            }
            Planprocesoscalidad planprocesoscalidad = pedido.getPlanprocesoscalidad();
            if (planprocesoscalidad != null) {
                planprocesoscalidad.getPedidoList().remove(pedido);
                planprocesoscalidad = em.merge(planprocesoscalidad);
            }
            Planprocedimientos planprocedimientos = pedido.getPlanprocedimientos();
            if (planprocedimientos != null) {
                planprocedimientos.getPedidoList().remove(pedido);
                planprocedimientos = em.merge(planprocedimientos);
            }
            Factura factura = pedido.getFactura();
            if (factura != null) {
                factura.getPedidoList().remove(pedido);
                factura = em.merge(factura);
            }
            Estadopedido estado = pedido.getEstado();
            if (estado != null) {
                estado.getPedidoList().remove(pedido);
                estado = em.merge(estado);
            }
            Cliente cliente = pedido.getCliente();
            if (cliente != null) {
                cliente.getPedidoList().remove(pedido);
                cliente = em.merge(cliente);
            }
            List<Planificacionproduccion> planificacionproduccionList = pedido.getPlanificacionproduccionList();
            for (Planificacionproduccion planificacionproduccionListPlanificacionproduccion : planificacionproduccionList) {
                planificacionproduccionListPlanificacionproduccion.setPedido(null);
                planificacionproduccionListPlanificacionproduccion = em.merge(planificacionproduccionListPlanificacionproduccion);
            }
            List<Productoreal> productorealList = pedido.getProductorealList();
            for (Productoreal productorealListProductoreal : productorealList) {
                productorealListProductoreal.setIdpedido(null);
                productorealListProductoreal = em.merge(productorealListProductoreal);
            }
            List<Plano> planoList = pedido.getPlanoList();
            for (Plano planoListPlano : planoList) {
                planoListPlano.setPedido(null);
                planoListPlano = em.merge(planoListPlano);
            }
            List<Trabajotercerizado> trabajotercerizadoList = pedido.getTrabajotercerizadoList();
            for (Trabajotercerizado trabajotercerizadoListTrabajotercerizado : trabajotercerizadoList) {
                trabajotercerizadoListTrabajotercerizado.setPedido(null);
                trabajotercerizadoListTrabajotercerizado = em.merge(trabajotercerizadoListTrabajotercerizado);
            }
            List<Planificacioncalidad> planificacioncalidadList = pedido.getPlanificacioncalidadList();
            for (Planificacioncalidad planificacioncalidadListPlanificacioncalidad : planificacioncalidadList) {
                planificacioncalidadListPlanificacioncalidad.setPedido(null);
                planificacioncalidadListPlanificacioncalidad = em.merge(planificacioncalidadListPlanificacioncalidad);
            }
            List<Remito> remitoList = pedido.getRemitoList();
            for (Remito remitoListRemito : remitoList) {
                remitoListRemito.setPedido(null);
                remitoListRemito = em.merge(remitoListRemito);
            }
            List<Detallefactura> detallefacturaList = pedido.getDetallefacturaList();
            for (Detallefactura detallefacturaListDetallefactura : detallefacturaList) {
                detallefacturaListDetallefactura.setIdpedido(null);
                detallefacturaListDetallefactura = em.merge(detallefacturaListDetallefactura);
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
