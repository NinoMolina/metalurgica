/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.web.vista;

import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ListDataModel;
import metalsoft.datos.jpa.entity.Detallepedido;
import metalsoft.datos.jpa.entity.Pedido;

/**
 *
 * @author Vicky
 */
@ManagedBean(name = "pedidoVista")
@SessionScoped
public class PedidoCotizacionVista {

    private Pedido pedido;
    private ListDataModel<Detallepedido> detallePedido;
    private boolean selected = false;
    protected ListDataModel<Pedido> pedidosVista;
    private boolean activarBoton = false;
    private boolean activarBotonCancelar = false;

    /** Creates a new instance of PedidoCotizacion */
    public PedidoCotizacionVista() {
        pedido = new Pedido();
    }

    public void limpiarCampos() {

        pedido=new Pedido();
        detallePedido=new ListDataModel<Detallepedido>();
        selected = false;
        pedidosVista=new ListDataModel<Pedido>();
        activarBoton = false;
        activarBotonCancelar = false;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public ListDataModel<Detallepedido> getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(ListDataModel<Detallepedido> detallePedido) {
        this.detallePedido = detallePedido;
    }

    public ListDataModel<Pedido> getPedidosVista() {
        return pedidosVista;
    }

    public void setPedidosVista(ListDataModel<Pedido> pedidosVista) {
        this.pedidosVista = pedidosVista;
    }

    public boolean isActivarBoton() {
        return activarBoton;
    }

    public void setActivarBoton(boolean activarBoton) {
        this.activarBoton = activarBoton;
    }

    public boolean isActivarBotonCancelar() {
        return activarBotonCancelar;
    }

    public void setActivarBotonCancelar(boolean activarBotonCancelar) {
        this.activarBotonCancelar = activarBotonCancelar;
    }
    
}
