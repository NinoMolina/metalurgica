/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.web.controlador;

import com.icesoft.faces.component.ext.RowSelectorEvent;
import java.util.AbstractList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.PedidoJpaController;
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.web.vista.ListaPedidosCotizacionVista;
import metalsoft.web.vista.PedidoCotizacionVista;
import com.icesoft.faces.component.ext.RowSelector;

import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.ListDataModel;
import metalsoft.datos.jpa.controller.EstadopedidoJpaController;
import metalsoft.datos.jpa.entity.Detallepedido;

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
    private long idCliente = 5;
    private String entroAListener = "";

    public PedidosCotizacionControlador() {
        //pedidosSeleccionados = JpaUtil.getDetallePedidoByPedido(36);
    }

    public String misPedidos() {

        pedidoVista.limpiarCampos();

        List<Pedido> list = JpaUtil.getPedidosByCliente(idCliente);

        pedidoVista.setPedidosVista(new ListDataModel(list));

        return "gestionPedidos";
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public PedidoCotizacionVista getPedidoVista() {
        return pedidoVista;
    }

    public void setPedidoVista(PedidoCotizacionVista pedidoVista) {
        this.pedidoVista = pedidoVista;
    }

    public String getEntroAListener() {
        return entroAListener;
    }

    public void setEntroAListener(String entroAListener) {
        this.entroAListener = entroAListener;
    }

    public String pedidoSeleccionado() {

        pedidoVista.setPedido(pedidoVista.getPedidosVista().getRowData());

        pedidoVista.setDetallePedido(JpaUtil.getDetallePedidoByPedido(pedidoVista.getPedido().getIdpedido()));

        pedidoVista.setSelected(true);
        if (pedidoVista.getPedido().getEstado().getIdestado() == 2) {
            pedidoVista.setActivarBoton(true);
        }else{
            pedidoVista.setActivarBoton(false);
        }

        return null;
    }

    public String confirmarPedido() {
        EstadopedidoJpaController conPedido = new EstadopedidoJpaController(JpaUtil.getEntityManagerFactory());
        pedidoVista.getPedido().setEstado(conPedido.findEstadopedido(4L));
        Pedido p=pedidoVista.getPedido();
        PedidoJpaController controller = new PedidoJpaController(JpaUtil.getEntityManagerFactory());
        try {
            controller.edit(p);
            entroAListener = "El Pedido ha sido confirmadop "+p.getEstado().getNombre();
            List<Pedido> list = JpaUtil.getPedidosByCliente(idCliente);
            pedidoVista.setPedidosVista(new ListDataModel(list));
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
