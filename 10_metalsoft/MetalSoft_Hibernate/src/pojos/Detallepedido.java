package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA



/**
 * Detallepedido generated by hbm2java
 */
public class Detallepedido  implements java.io.Serializable {


     private long iddetalle;
     private Producto producto;
     private Pedido pedido;
     private Double precio;
     private Integer cantidad;

    public Detallepedido() {
    }

	
    public Detallepedido(long iddetalle, Pedido pedido) {
        this.iddetalle = iddetalle;
        this.pedido = pedido;
    }
    public Detallepedido(long iddetalle, Producto producto, Pedido pedido, Double precio, Integer cantidad) {
       this.iddetalle = iddetalle;
       this.producto = producto;
       this.pedido = pedido;
       this.precio = precio;
       this.cantidad = cantidad;
    }
   
    public long getIddetalle() {
        return this.iddetalle;
    }
    
    public void setIddetalle(long iddetalle) {
        this.iddetalle = iddetalle;
    }
    public Producto getProducto() {
        return this.producto;
    }
    
    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    public Pedido getPedido() {
        return this.pedido;
    }
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public Double getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public Integer getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }




}


