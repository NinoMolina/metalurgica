/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.web.controlador;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.model.ListDataModel;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.ProductoJpaController;
import metalsoft.datos.jpa.entity.Detallepedido;
import metalsoft.datos.jpa.entity.Producto;
import metalsoft.web.vista.NuevoPedidoVista;

/**
 *
 * @author Vicky
 */
@ManagedBean(name = "nuevoPedidoControlador")
@RequestScoped
public class nuevoPedidoControlador {

    @ManagedProperty(value="#{nuevoPedidoVista}")
    private NuevoPedidoVista nvoPedidoVista;
    /** Creates a new instance of nuevoPedidoControlador */
    public nuevoPedidoControlador() {
    }

    public void seleccionarPlano() {
        
    }
    
    public void agregarProductos(){
        nvoPedidoVista.setSeleccionoProducto(true);
        ProductoJpaController con=new ProductoJpaController(JpaUtil.getEntityManagerFactory());
        Producto prod=con.findProducto(nvoPedidoVista.getProductoSeleccionado().getIdproducto());
        int cant=nvoPedidoVista.getCantidadSeleccionada();
        Detallepedido detalle=new Detallepedido();
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
    
    public String irANuevoPedido(){
        ProductoJpaController contoller=new ProductoJpaController(JpaUtil.getEntityManagerFactory());
        List<Producto> list=contoller.findProductoEntities();
        int max=0;
        for(Producto p : list){
            if(max<p.getNroproducto().intValue()){
                max=p.getNroproducto().intValue();
            }
        }
        nvoPedidoVista.setListProductos(list);
        nvoPedidoVista.setNroAMostrar(max+1);
        return "nuevoPedido";
    }
}
