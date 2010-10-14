package pojos;
// Generated 12/10/2010 01:33:18 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Productoreal generated by hbm2java
 */
public class Productoreal  implements java.io.Serializable {


     private long idproductoreal;
     private Estadoproductoreal estadoproductoreal;
     private Pedido pedido;
     private Codigodebarra codigodebarra;
     private Long nroproducto;
     private Date fechaterminacion;
     private Date fechainicioproduccion;
     private Long idpieza;
     private Long idpiezareal;
     private Set<Detalleproductoreal> detalleproductoreals = new HashSet<Detalleproductoreal>(0);

    public Productoreal() {
    }

	
    public Productoreal(long idproductoreal) {
        this.idproductoreal = idproductoreal;
    }
    public Productoreal(long idproductoreal, Estadoproductoreal estadoproductoreal, Pedido pedido, Codigodebarra codigodebarra, Long nroproducto, Date fechaterminacion, Date fechainicioproduccion, Long idpieza, Long idpiezareal, Set<Detalleproductoreal> detalleproductoreals) {
       this.idproductoreal = idproductoreal;
       this.estadoproductoreal = estadoproductoreal;
       this.pedido = pedido;
       this.codigodebarra = codigodebarra;
       this.nroproducto = nroproducto;
       this.fechaterminacion = fechaterminacion;
       this.fechainicioproduccion = fechainicioproduccion;
       this.idpieza = idpieza;
       this.idpiezareal = idpiezareal;
       this.detalleproductoreals = detalleproductoreals;
    }
   
    public long getIdproductoreal() {
        return this.idproductoreal;
    }
    
    public void setIdproductoreal(long idproductoreal) {
        this.idproductoreal = idproductoreal;
    }
    public Estadoproductoreal getEstadoproductoreal() {
        return this.estadoproductoreal;
    }
    
    public void setEstadoproductoreal(Estadoproductoreal estadoproductoreal) {
        this.estadoproductoreal = estadoproductoreal;
    }
    public Pedido getPedido() {
        return this.pedido;
    }
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public Codigodebarra getCodigodebarra() {
        return this.codigodebarra;
    }
    
    public void setCodigodebarra(Codigodebarra codigodebarra) {
        this.codigodebarra = codigodebarra;
    }
    public Long getNroproducto() {
        return this.nroproducto;
    }
    
    public void setNroproducto(Long nroproducto) {
        this.nroproducto = nroproducto;
    }
    public Date getFechaterminacion() {
        return this.fechaterminacion;
    }
    
    public void setFechaterminacion(Date fechaterminacion) {
        this.fechaterminacion = fechaterminacion;
    }
    public Date getFechainicioproduccion() {
        return this.fechainicioproduccion;
    }
    
    public void setFechainicioproduccion(Date fechainicioproduccion) {
        this.fechainicioproduccion = fechainicioproduccion;
    }
    public Long getIdpieza() {
        return this.idpieza;
    }
    
    public void setIdpieza(Long idpieza) {
        this.idpieza = idpieza;
    }
    public Long getIdpiezareal() {
        return this.idpiezareal;
    }
    
    public void setIdpiezareal(Long idpiezareal) {
        this.idpiezareal = idpiezareal;
    }
    public Set<Detalleproductoreal> getDetalleproductoreals() {
        return this.detalleproductoreals;
    }
    
    public void setDetalleproductoreals(Set<Detalleproductoreal> detalleproductoreals) {
        this.detalleproductoreals = detalleproductoreals;
    }




}


