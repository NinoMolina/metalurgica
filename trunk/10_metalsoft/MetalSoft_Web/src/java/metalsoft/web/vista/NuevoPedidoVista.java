/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.web.vista;

import com.icesoft.faces.context.effects.Effect;
import com.icesoft.faces.context.effects.Highlight;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.ListDataModel;
import metalsoft.datos.jpa.entity.Detallepedido;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.datos.jpa.entity.Producto;

/**
 *
 * @author Vicky
 */
@ManagedBean(name = "nuevoPedidoVista")
@SessionScoped
public class NuevoPedidoVista {

    private Pedido pedido;
    private List<Producto> listProductos;
    private Producto productoSeleccionado;
    private ListDataModel<Detallepedido> listDetalles;
    private List<Detallepedido> listPrevisoriaDetalles;
    private boolean seleccionoProducto = false;
    private int cantidadSeleccionada;
    private int nroAMostrar;
    private Date fechaNecesidad = new Date();
    private Date fechaPedido = new Date();
    private Effect valueChangeEffect2;
    private boolean activarBoton=false;
    private String mensValidacion="";
    /** Creates a new instance of nuevoPedidoVista */
    public NuevoPedidoVista() {
        pedido = new Pedido();
        fechaNecesidad = new GregorianCalendar().getTime();
        fechaPedido = new GregorianCalendar().getTime();
        listProductos = new LinkedList<Producto>();
        productoSeleccionado = new Producto();
        listPrevisoriaDetalles = new LinkedList<Detallepedido>();
        valueChangeEffect2 = new Highlight("#fda505");
        valueChangeEffect2.setFired(true);
        mensValidacion="";
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

    public Date getFechaNecesidad() {
        return fechaNecesidad;
    }

    public void setFechaNecesidad(Date fechaNecesidad) {
        this.fechaNecesidad = fechaNecesidad;
    }

    public Effect getValueChangeEffect2() {
        return valueChangeEffect2;
    }

    public void setValueChangeEffect2(Effect valueChangeEffect2) {
        this.valueChangeEffect2 = valueChangeEffect2;
    }

    public void effect2ChangeListener(ValueChangeEvent event) {
        valueChangeEffect2.setFired(false);
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public boolean isActivarBoton() {
        return activarBoton;
    }

    public void setActivarBoton(boolean activarBoton) {
        this.activarBoton = activarBoton;
    }

    public String getMensValidacion() {
        return mensValidacion;
    }

    public void setMensValidacion(String mensValidacion) {
        this.mensValidacion = mensValidacion;
    }
    
}
