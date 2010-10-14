package pojos;
// Generated 12/10/2010 01:33:18 by Hibernate Tools 3.2.1.GA



/**
 * Detallefactura generated by hbm2java
 */
public class Detallefactura  implements java.io.Serializable {


     private DetallefacturaId id;
     private Factura factura;
     private Pedido pedido;
     private Double montoparcial;
     private Integer cantidad;
     private Long iddetallepedido;

    public Detallefactura() {
    }

	
    public Detallefactura(DetallefacturaId id, Factura factura) {
        this.id = id;
        this.factura = factura;
    }
    public Detallefactura(DetallefacturaId id, Factura factura, Pedido pedido, Double montoparcial, Integer cantidad, Long iddetallepedido) {
       this.id = id;
       this.factura = factura;
       this.pedido = pedido;
       this.montoparcial = montoparcial;
       this.cantidad = cantidad;
       this.iddetallepedido = iddetallepedido;
    }
   
    public DetallefacturaId getId() {
        return this.id;
    }
    
    public void setId(DetallefacturaId id) {
        this.id = id;
    }
    public Factura getFactura() {
        return this.factura;
    }
    
    public void setFactura(Factura factura) {
        this.factura = factura;
    }
    public Pedido getPedido() {
        return this.pedido;
    }
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public Double getMontoparcial() {
        return this.montoparcial;
    }
    
    public void setMontoparcial(Double montoparcial) {
        this.montoparcial = montoparcial;
    }
    public Integer getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public Long getIddetallepedido() {
        return this.iddetallepedido;
    }
    
    public void setIddetallepedido(Long iddetallepedido) {
        this.iddetallepedido = iddetallepedido;
    }




}


