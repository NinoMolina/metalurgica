/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.web.controlador;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.ListDataModel;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.PedidoJpaController;
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.web.vista.PedidoCotizacionVista;
import metalsoft.datos.jpa.controller.EstadopedidoJpaController;
import metalsoft.datos.jpa.entity.Detallepedido;
import metalsoft.util.Fecha;
import metalsoft.web.vista.SesionVista;

/**
 *
 * @author Vicky
 */
@ManagedBean(name = "pedidosControlador")
@RequestScoped
public class PedidosCotizacionControlador {

    /** Creates a new instance of PedidosCotizacionControlador */
    @ManagedProperty(value = "#{pedidoVista}")
    private PedidoCotizacionVista pedidoVista;
    @ManagedProperty(value = "#{sesionVista}")
    private SesionVista sesionVista;
    private String entroAListener = "";

    public PedidosCotizacionControlador() {
        //pedidosSeleccionados = JpaUtil.getDetallePedidoByPedido(36);
    }

    public String misPedidos() {

        pedidoVista.limpiarCampos();

        List<Pedido> list = JpaUtil.getPedidosByCliente(sesionVista.getCliente().getIdcliente());
        for(Pedido p : list){
            p.setFechapedidocotizacion(Fecha.parseToDate(String.valueOf(p.getFechapedidocotizacion()), "dd/MM/yyyy"));
        }
        pedidoVista.setPedidosVista(new ListDataModel(list));

        return "gestionPedidos";
    }

    public String todosLosPedidos() {

        pedidoVista.limpiarCampos();

        List<Pedido> list = JpaUtil.getPedidosNoFinalizados();
        for(Pedido p : list){
            p.setFechapedidocotizacion(Fecha.parseToDate(String.valueOf(p.getFechapedidocotizacion()), "dd/MM/yyyy"));
        }
        pedidoVista.setPedidosVista(new ListDataModel(list));

        return "gestionPedidos";
    }

    public PedidoCotizacionVista getPedidoVista() {
        return pedidoVista;
    }

    public void setPedidoVista(PedidoCotizacionVista pedidoVista) {
        this.pedidoVista = pedidoVista;
    }

    public SesionVista getSesionVista() {
        return sesionVista;
    }

    public void setSesionVista(SesionVista sesionVista) {
        this.sesionVista = sesionVista;
    }

    public String getEntroAListener() {
        return entroAListener;
    }

    public void setEntroAListener(String entroAListener) {
        this.entroAListener = entroAListener;
    }

    public String pedidoSeleccionado() {

        pedidoVista.setPedido(pedidoVista.getPedidosVista().getRowData());

        pedidoVista.setDetallePedido(new ListDataModel(JpaUtil.getDetallePedidoByPedido(pedidoVista.getPedido().getIdpedido())));

        pedidoVista.setSelected(true);
        if (pedidoVista.getPedido().getEstado().getIdestado() == 2) {
            pedidoVista.setActivarBoton(true);
        } else {
            pedidoVista.setActivarBoton(false);
        }
        if (pedidoVista.getPedido().getEstado().getIdestado() == 2 || pedidoVista.getPedido().getEstado().getIdestado() == 1) {
            pedidoVista.setActivarBotonCancelar(true);
        } else {
            pedidoVista.setActivarBotonCancelar(false);
        }

        return null;
    }

    public String confirmarPedido() {
        EstadopedidoJpaController conPedido = new EstadopedidoJpaController(JpaUtil.getEntityManagerFactory());
        pedidoVista.getPedido().setEstado(conPedido.findEstadopedido(4L));
        Pedido p = pedidoVista.getPedido();
        PedidoJpaController controller = new PedidoJpaController(JpaUtil.getEntityManagerFactory());
        try {
            controller.edit(p);
            entroAListener = "El Pedido ha sido " + p.getEstado().getNombre();
            List<Pedido> list = JpaUtil.getPedidosByCliente(sesionVista.getCliente().getIdcliente());
            pedidoVista.setPedidosVista(new ListDataModel(list));
            if (pedidoVista.getPedido().getEstado().getIdestado() == 2) {
                pedidoVista.setActivarBoton(true);
            } else {
                pedidoVista.setActivarBoton(false);
            }
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(PedidosCotizacionControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PedidosCotizacionControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PedidosCotizacionControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String cancelarPedido() {
        EstadopedidoJpaController conPedido = new EstadopedidoJpaController(JpaUtil.getEntityManagerFactory());

        Pedido p = pedidoVista.getPedido();
        p.setEstado(conPedido.findEstadopedido(15L));
        PedidoJpaController controller = new PedidoJpaController(JpaUtil.getEntityManagerFactory());
        try {
            controller.edit(p);
            entroAListener = "El Pedido ha sido " + p.getEstado().getNombre();
            if (sesionVista.isEsCliente()) {
                this.misPedidos();
            }
            if(sesionVista.isEsAdministrador()){
                this.todosLosPedidos();
            }
            if (pedidoVista.getPedido().getEstado().getIdestado() == 2 || pedidoVista.getPedido().getEstado().getIdestado() == 1) {
                pedidoVista.setActivarBotonCancelar(true);
            } else {
                pedidoVista.setActivarBotonCancelar(false);
            }
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(PedidosCotizacionControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(PedidosCotizacionControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(PedidosCotizacionControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
