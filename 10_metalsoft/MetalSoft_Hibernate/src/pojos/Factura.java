package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Factura generated by hbm2java
 */
public class Factura  implements java.io.Serializable {


     private long idfactura;
     private Usuario usuario;
     private Formadepago formadepago;
     private Estadofactura estadofactura;
     private Tipoiva tipoiva;
     private Long nrofactura;
     private Date fechaemision;
     private Date fecharealcobro;
     private Date fechavencimiento;
     private String tipofactura;
     private Set<Detallefactura> detallefacturas = new HashSet<Detallefactura>(0);
     private Set<Comprobantepago> comprobantepagos = new HashSet<Comprobantepago>(0);
     private Set<Pedido> pedidos = new HashSet<Pedido>(0);

    public Factura() {
    }

	
    public Factura(long idfactura) {
        this.idfactura = idfactura;
    }
    public Factura(long idfactura, Usuario usuario, Formadepago formadepago, Estadofactura estadofactura, Tipoiva tipoiva, Long nrofactura, Date fechaemision, Date fecharealcobro, Date fechavencimiento, String tipofactura, Set<Detallefactura> detallefacturas, Set<Comprobantepago> comprobantepagos, Set<Pedido> pedidos) {
       this.idfactura = idfactura;
       this.usuario = usuario;
       this.formadepago = formadepago;
       this.estadofactura = estadofactura;
       this.tipoiva = tipoiva;
       this.nrofactura = nrofactura;
       this.fechaemision = fechaemision;
       this.fecharealcobro = fecharealcobro;
       this.fechavencimiento = fechavencimiento;
       this.tipofactura = tipofactura;
       this.detallefacturas = detallefacturas;
       this.comprobantepagos = comprobantepagos;
       this.pedidos = pedidos;
    }
   
    public long getIdfactura() {
        return this.idfactura;
    }
    
    public void setIdfactura(long idfactura) {
        this.idfactura = idfactura;
    }
    public Usuario getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Formadepago getFormadepago() {
        return this.formadepago;
    }
    
    public void setFormadepago(Formadepago formadepago) {
        this.formadepago = formadepago;
    }
    public Estadofactura getEstadofactura() {
        return this.estadofactura;
    }
    
    public void setEstadofactura(Estadofactura estadofactura) {
        this.estadofactura = estadofactura;
    }
    public Tipoiva getTipoiva() {
        return this.tipoiva;
    }
    
    public void setTipoiva(Tipoiva tipoiva) {
        this.tipoiva = tipoiva;
    }
    public Long getNrofactura() {
        return this.nrofactura;
    }
    
    public void setNrofactura(Long nrofactura) {
        this.nrofactura = nrofactura;
    }
    public Date getFechaemision() {
        return this.fechaemision;
    }
    
    public void setFechaemision(Date fechaemision) {
        this.fechaemision = fechaemision;
    }
    public Date getFecharealcobro() {
        return this.fecharealcobro;
    }
    
    public void setFecharealcobro(Date fecharealcobro) {
        this.fecharealcobro = fecharealcobro;
    }
    public Date getFechavencimiento() {
        return this.fechavencimiento;
    }
    
    public void setFechavencimiento(Date fechavencimiento) {
        this.fechavencimiento = fechavencimiento;
    }
    public String getTipofactura() {
        return this.tipofactura;
    }
    
    public void setTipofactura(String tipofactura) {
        this.tipofactura = tipofactura;
    }
    public Set<Detallefactura> getDetallefacturas() {
        return this.detallefacturas;
    }
    
    public void setDetallefacturas(Set<Detallefactura> detallefacturas) {
        this.detallefacturas = detallefacturas;
    }
    public Set<Comprobantepago> getComprobantepagos() {
        return this.comprobantepagos;
    }
    
    public void setComprobantepagos(Set<Comprobantepago> comprobantepagos) {
        this.comprobantepagos = comprobantepagos;
    }
    public Set<Pedido> getPedidos() {
        return this.pedidos;
    }
    
    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }




}

