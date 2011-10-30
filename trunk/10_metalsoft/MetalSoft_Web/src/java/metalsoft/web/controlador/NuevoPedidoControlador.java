/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.web.controlador;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
import org.icefaces.component.fileentry.FileEntry;
import org.icefaces.component.fileentry.FileEntryEvent;
import org.icefaces.component.fileentry.FileEntryLoader;
import org.icefaces.component.fileentry.FileEntryResults;
import org.icefaces.component.fileentry.FileEntryResults.FileInfo;

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
    
    // file upload completed percent (Progress)
    private int fileProgress;

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
        nvoPedidoVista.setGuardarPedido(false);
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
                ped.setNropedidocotizacioncliente(0);
                conPed.create(ped);
                for (Detallepedido de : nvoPedidoVista.getListPrevisoriaDetalles()) {
                    de.setIdpedido(ped);
                    conDetalle.create(de);
                }
                em.getTransaction().commit();
                nvoPedidoVista.setMensValidacion("El pedido se ha guardado correctamente");
                nvoPedidoVista.setGuardarPedido(true);
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
        if (nvoPedidoVista.getFechaNecesidad() == null || !nvoPedidoVista.getFechaNecesidad().after(new Date())) {
            nvoPedidoVista.setMensValidacion("La fecha de Necesidad de Cotización debe ser mayor a la fecha actual");
            nvoPedidoVista.setGuardarPedido(true);
            return false;
        } 
        if(nvoPedidoVista.getFechaPedido() == null){
            nvoPedidoVista.setMensValidacion("Debe seleccionar una fecha de pedido");
            nvoPedidoVista.setGuardarPedido(true);
            return false;
        }
        if(nvoPedidoVista.getListPrevisoriaDetalles().isEmpty()){
            nvoPedidoVista.setMensValidacion("Debe seleccionar al menos un producto");
            nvoPedidoVista.setGuardarPedido(true);
            return false;
        }
        
        return true;
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
   
    public void listener(FileEntryEvent event) {
        FileEntry fileEntry = (FileEntry) event.getSource();
        FileEntryResults results = fileEntry.getResults();
        this.nvoPedidoVista.setSeleccionoPlano(true);
        for (FileEntryResults.FileInfo fileInfo : results.getFiles()) {
            if (fileInfo.isSaved()) {
//                System.out.println(fileInfo.getFileName());
                this.nvoPedidoVista.setPlanoSeleccionado(fileInfo);
                
                synchronized (this.nvoPedidoVista.getFileList()) {
                    this.nvoPedidoVista.getFileList().add(this.nvoPedidoVista.getPlanoSeleccionado());
                    //this.nvoPedidoVista.setMensSubePlano("No hay plano seleccionado");
                }
            }
        }
    }

    public void removeUploadedFile(ActionEvent event) {
        // Get the inventory item ID from the context.
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        String fileName = (String) map.get("fileName");

        synchronized (this.nvoPedidoVista.getFileList()) {
            FileInfo inputFileData;
            for (int i = 0; i < this.nvoPedidoVista.getFileList().size(); i++) {
                inputFileData = (FileInfo) this.nvoPedidoVista.getFileList().get(i);
                // remove our file
                if (inputFileData.getFile().getName().equals(fileName)) {
                    this.nvoPedidoVista.getFileList().remove(i);
                    if(this.nvoPedidoVista.getFileList().isEmpty()){
                        this.nvoPedidoVista.setSeleccionoPlano(false);
                    }
                    break;
                }
            }
        }
    }
}
