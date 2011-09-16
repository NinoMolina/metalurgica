/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.web.vista;

import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Vicky
 */
@ManagedBean(name="listaPedidosVista")
@SessionScoped
public class ListaPedidosCotizacionVista {

    private List<PedidoCotizacionVista> listaPedidos;
    /** Creates a new instance of ListaPedidosCotizacionVista */
    public ListaPedidosCotizacionVista() {
        listaPedidos=new LinkedList<PedidoCotizacionVista>();
    }

    public List<PedidoCotizacionVista> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<PedidoCotizacionVista> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }
    
}
