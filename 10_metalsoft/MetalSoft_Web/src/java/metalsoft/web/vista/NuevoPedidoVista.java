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
import metalsoft.datos.jpa.entity.Producto;

/**
 *
 * @author Vicky
 */
@ManagedBean(name="nuevoPedidoVista")
@SessionScoped
public class NuevoPedidoVista {

    private Pedido pedido;
    private List<Producto> listProductos;
    private Producto productoSeleccionado;
    private ListDataModel<Detallepedido> listDetalles;
    private List<Detallepedido> listPrevisoriaDetalles;
    private boolean seleccionoProducto=false;
    private int cantidadSeleccionada;
    private int nroAMostrar;
    /** Creates a new instance of nuevoPedidoVista */
    public NuevoPedidoVista() {
        pedido=new Pedido();
        listProductos=new LinkedList<Producto>();
        productoSeleccionado=new Producto();
        listPrevisoriaDetalles=new LinkedList<Detallepedido>();
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Producto> getListProductos() {
        return listProductos;
    }

    public void setListProductos(List<Producto> listProductos) {
        this.listProductos = listProductos;
    }

    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public ListDataModel<Detallepedido> getListDetalles() {
        return listDetalles;
    }

    public void setListDetalles(ListDataModel<Detallepedido> listDetalles) {
        this.listDetalles = listDetalles;
    }

    public boolean isSeleccionoProducto() {
        return seleccionoProducto;
    }

    public void setSeleccionoProducto(boolean seleccionoProducto) {
        this.seleccionoProducto = seleccionoProducto;
    }

    public int getCantidadSeleccionada() {
        return cantidadSeleccionada;
    }

    public void setCantidadSeleccionada(int cantidadSeleccionada) {
        this.cantidadSeleccionada = cantidadSeleccionada;
    }

    public List<Detallepedido> getListPrevisoriaDetalles() {
        return listPrevisoriaDetalles;
    }

    public void setListPrevisoriaDetalles(List<Detallepedido> listPrevisoriaDetalles) {
        this.listPrevisoriaDetalles = listPrevisoriaDetalles;
    }

    public int getNroAMostrar() {
        return nroAMostrar;
    }

    public void setNroAMostrar(int nroAMostrar) {
        this.nroAMostrar = nroAMostrar;
    }
    
}
