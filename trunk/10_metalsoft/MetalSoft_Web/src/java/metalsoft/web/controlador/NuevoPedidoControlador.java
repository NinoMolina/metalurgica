/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.web.controlador;

import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.ListDataModel;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.DetallepedidoJpaController;
import metalsoft.datos.jpa.controller.EstadopedidoJpaController;
import metalsoft.datos.jpa.controller.PedidoJpaController;
import metalsoft.datos.jpa.controller.PrioridadJpaController;
import metalsoft.datos.jpa.controller.ProductoJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Detallepedido;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.datos.jpa.entity.Prioridad;
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
    
    @ManagedProperty(value = "#{pedidosControlador}")
    private PedidosCotizacionControlador pedidoControlados;

    /** Creates a new instance of nuevoPedidoControlador */
    public NuevoPedidoControlador() {
    }

    public void seleccionarPlano() {
    }

    public String agregarProductos() {
        ProductoJpaController con = new ProductoJpaController(JpaUtil.getEntityManagerFactory());
        Producto prod = con.findProducto(nvoPedidoVista.getProductoSeleccionado().getIdproducto());
        for (Detallepedido det : nvoPedidoVista.getListPrevisoriaDetalles()) {
            if (prod.getIdproducto() == det.getProducto().getIdproducto()) {
                nvoPedidoVista.setMostrarMensaje(true);
                return null;
            }
        }
        int cant = nvoPedidoVista.getCantidadSeleccionada();
        Detallepedido detalle = new Detallepedido();
        detalle.setCantidad(cant);
        detalle.setProducto(prod);
        nvoPedidoVista.getListPrevisoriaDetalles().add(detalle);
        nvoPedidoVista.setListDetalles(new ListDataModel<Detallepedido>(nvoPedidoVista.getListPrevisoriaDetalles()));
        return null;
    }

    public String cambiarMensaje() {
        nvoPedidoVista.setMostrarMensaje(false);
        return null;
    }

    public String quitarProductos() {
        if (!nvoPedidoVista.getListPrevisoriaDetalles().isEmpty()) {
            nvoPedidoVista.setDetalleSeleccionado(nvoPedidoVista.getListDetalles().getRowData());
            nvoPedidoVista.getListPrevisoriaDetalles().remove(nvoPedidoVista.getDetalleSeleccionado());
            nvoPedidoVista.setListDetalles(new ListDataModel<Detallepedido>(nvoPedidoVista.getListPrevisoriaDetalles()));
            return null;
        }
        return null;
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
                EstadopedidoJpaController conEstadoPed = new EstadopedidoJpaController(JpaUtil.getEntityManagerFactory());
                PrioridadJpaController conPrioridad = new PrioridadJpaController(JpaUtil.getEntityManagerFactory());
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
                ped.setPrioridad(conPrioridad.findPrioridad(3L));
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
            
            return pedidoControlados.todosLosPedidos();
        } else {
            return null;
        }
    }

    public boolean validacionCampos() {
        if (nvoPedidoVista.getFechaNecesidad() != null && nvoPedidoVista.getFechaPedido() != null && !nvoPedidoVista.getListPrevisoriaDetalles().isEmpty()) {
            return true;
        } else {
            nvoPedidoVista.setMensValidacion("Debe completar todos los campos");
            return false;
        }
    }

    public NuevoPedidoVista getNvoPedidoVista() {
        return nvoPedidoVista;
    }

    public void setNvoPedidoVista(NuevoPedidoVista nvoPedidoVista) {
        this.nvoPedidoVista = nvoPedidoVista;
    }

    public SesionVista getSesionVista() {
        return sesionVista;
    }

    public void setSesionVista(SesionVista sesionVista) {
        this.sesionVista = sesionVista;
    }

    public PedidosCotizacionControlador getPedidoControlados() {
        return pedidoControlados;
    }

    public void setPedidoControlados(PedidosCotizacionControlador pedidoControlados) {
        this.pedidoControlados = pedidoControlados;
    }
    
}
