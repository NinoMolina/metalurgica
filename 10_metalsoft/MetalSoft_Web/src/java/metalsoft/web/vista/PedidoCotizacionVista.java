/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.web.vista;

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
    private List<Detallepedido> detallePedido;
    private boolean selected = false;
    private boolean edit;
    private long id;
    protected ListDataModel<Pedido> pedidosVista;
    private boolean activarBoton = false;

    /** Creates a new instance of PedidoCotizacion */
    public PedidoCotizacionVista() {
        pedido = new Pedido();
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

    public List<Detallepedido> getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(List<Detallepedido> detallePedido) {
        this.detallePedido = detallePedido;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ListDataModel<Pedido> getPedidosVista() {
        return pedidosVista;
    }

    public void setPedidosVista(ListDataModel<Pedido> pedidosVista) {
        this.pedidosVista = pedidosVista;
    }

    public void limpiarCampos() {
        
    }

    public boolean isActivarBoton() {
        return activarBoton;
    }

    public void setActivarBoton(boolean activarBoton) {
        this.activarBoton = activarBoton;
    }
    
    
}
