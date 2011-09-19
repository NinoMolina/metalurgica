/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.web.controlador;

import com.icesoft.faces.context.effects.Effect;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.DetallepedidoJpaController;
import metalsoft.datos.jpa.controller.EstadopedidoJpaController;
import metalsoft.datos.jpa.controller.PedidoJpaController;
import metalsoft.datos.jpa.controller.PrioridadJpaController;
import metalsoft.datos.jpa.controller.ProductoJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Detallepedido;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.datos.jpa.entity.Producto;
import metalsoft.web.vista.NuevoPedidoVista;
import metalsoft.web.vista.SesionVista;

/**
 *
 * @author Vicky
 */
@ManagedBean(name = "nuevoPedidoControlador")
@RequestScoped
public class NuevoPedidoControlador {

    @ManagedProperty(value = "#{nuevoPedidoVista}")
    private NuevoPedidoVista nvoPedidoVista;
    @ManagedProperty(value = "#{sesionVista}")
    private SesionVista sesionVista;

    /** Creates a new instance of nuevoPedidoControlador */
    public NuevoPedidoControlador() {
    }

    public void seleccionarPlano() {
    }

    public void agregarProductos() {
        nvoPedidoVista.setSeleccionoProducto(true);
        ProductoJpaController con = new ProductoJpaController(JpaUtil.getEntityManagerFactory());
        Producto prod = con.findProducto(nvoPedidoVista.getProductoSeleccionado().getIdproducto());
        int cant = nvoPedidoVista.getCantidadSeleccionada();
        Detallepedido detalle = new Detallepedido();
        detalle.setCantidad(cant);
        detalle.setProducto(prod);
        nvoPedidoVista.getListPrevisoriaDetalles().add(detalle);
        nvoPedidoVista.setListDetalles(new ListDataModel<Detallepedido>(nvoPedidoVista.getListPrevisoriaDetalles()));
    }

    public NuevoPedidoVista getNvoPedidoVista() {
        return nvoPedidoVista;
    }

    public void setNvoPedidoVista(NuevoPedidoVista nvoPedidoVista) {
        this.nvoPedidoVista = nvoPedidoVista;
    }

    public String irANuevoPedido() {
        ProductoJpaController contoller = new ProductoJpaController(JpaUtil.getEntityManagerFactory());
        List<Producto> list = contoller.findProductoEntities();
        PedidoJpaController conPed = new PedidoJpaController(JpaUtil.getEntityManagerFactory());
        List<Pedido> listPedidos = conPed.findPedidoEntities();
        int max = 0;
        for (Pedido p : listPedidos) {
            if (max < p.getNropedido()) {
                max = (int) p.getNropedido();
            }
        }
        nvoPedidoVista.setListProductos(list);
        nvoPedidoVista.setNroAMostrar(max + 1);
        return "nuevoPedido";
    }

    /**
     * Gets the default timezone of the host server.  The timezone is needed
     * by the convertDateTime for formatting the time dat values.
     *
     * @return timezone for the current JVM
     */
    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }

    public String guardarPedido() {
        if (validacionCampos()) {
            EntityManager em = null;
            try {
                PedidoJpaController conPed = new PedidoJpaController(JpaUtil.getEntityManagerFactory());
                DetallepedidoJpaController conDetalle = new DetallepedidoJpaController(JpaUtil.getEntityManagerFactory());
                EstadopedidoJpaController conEstadoPed=new EstadopedidoJpaController(JpaUtil.getEntityManagerFactory());
                PrioridadJpaController conPrioridad=new PrioridadJpaController(JpaUtil.getEntityManagerFactory());
                em = conPed.getEntityManager();
                em.getTransaction().begin();
                Pedido ped = new Pedido();
                ped.setCliente(sesionVista.getCliente());
                ped.setNropedido(nvoPedidoVista.getNroAMostrar());
                ped.setFecharegpedcotiz(nvoPedidoVista.getFechaPedido());
                ped.setFechapedidocotizacion(nvoPedidoVista.getFechaPedido());
                ped.setFecharequeridacotizacion(nvoPedidoVista.getFechaNecesidad());
                ped.setEspedidoweb(true);
                ped.setEstado(conEstadoPed.findEstadopedido(1L));
                ped.setPrioridad(conPrioridad.findPrioridad(nvoPedidoVista.getPrioridadSeleccionada().getIdprioridad()));
                conPed.create(ped);
                for (Detallepedido de : nvoPedidoVista.getListPrevisoriaDetalles()) {
                    de.setIdpedido(ped);
                    conDetalle.create(de);
                }
                em.getTransaction().commit();
                nvoPedidoVista.limpiarCampos();
            } catch (PreexistingEntityException ex) {
                Logger.getLogger(NuevoPedidoControlador.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(NuevoPedidoControlador.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "gestionPedidos";
        }else{
            return null;
        }
    }

    public SesionVista getSesionVista() {
        return sesionVista;
    }

    public void setSesionVista(SesionVista sesionVista) {
        this.sesionVista = sesionVista;
    }

    public boolean validacionCampos() {
        if (nvoPedidoVista.getFechaNecesidad() != null && nvoPedidoVista.getFechaPedido() != null && !nvoPedidoVista.getListPrevisoriaDetalles().isEmpty()) {
            return true;
        } else {
            nvoPedidoVista.setMensValidacion("Debe completar todos los campos");
            return false;
        }
    }
}
