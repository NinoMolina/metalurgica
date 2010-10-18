package pojos;
// Generated 18/10/2010 10:12:42 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Compra generated by hbm2java
 */
public class Compra  implements java.io.Serializable {


     private long idcompra;
     private Estadocompra estadocompra;
     private Proveedor proveedor;
     private Long nrocompra;
     private Date fechacompra;
     private Date vigencia;
     private Long documentoremito;
     private Double preciototal;
     private String motivo;
     private Set<Reclamoproveedor> reclamoproveedors = new HashSet<Reclamoproveedor>(0);
     private Set<Detallecompra> detallecompras = new HashSet<Detallecompra>(0);

    public Compra() {
    }

	
    public Compra(long idcompra) {
        this.idcompra = idcompra;
    }
    public Compra(long idcompra, Estadocompra estadocompra, Proveedor proveedor, Long nrocompra, Date fechacompra, Date vigencia, Long documentoremito, Double preciototal, String motivo, Set<Reclamoproveedor> reclamoproveedors, Set<Detallecompra> detallecompras) {
       this.idcompra = idcompra;
       this.estadocompra = estadocompra;
       this.proveedor = proveedor;
       this.nrocompra = nrocompra;
       this.fechacompra = fechacompra;
       this.vigencia = vigencia;
       this.documentoremito = documentoremito;
       this.preciototal = preciototal;
       this.motivo = motivo;
       this.reclamoproveedors = reclamoproveedors;
       this.detallecompras = detallecompras;
    }
   
    public long getIdcompra() {
        return this.idcompra;
    }
    
    public void setIdcompra(long idcompra) {
        this.idcompra = idcompra;
    }
    public Estadocompra getEstadocompra() {
        return this.estadocompra;
    }
    
    public void setEstadocompra(Estadocompra estadocompra) {
        this.estadocompra = estadocompra;
    }
    public Proveedor getProveedor() {
        return this.proveedor;
    }
    
    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }
    public Long getNrocompra() {
        return this.nrocompra;
    }
    
    public void setNrocompra(Long nrocompra) {
        this.nrocompra = nrocompra;
    }
    public Date getFechacompra() {
        return this.fechacompra;
    }
    
    public void setFechacompra(Date fechacompra) {
        this.fechacompra = fechacompra;
    }
    public Date getVigencia() {
        return this.vigencia;
    }
    
    public void setVigencia(Date vigencia) {
        this.vigencia = vigencia;
    }
    public Long getDocumentoremito() {
        return this.documentoremito;
    }
    
    public void setDocumentoremito(Long documentoremito) {
        this.documentoremito = documentoremito;
    }
    public Double getPreciototal() {
        return this.preciototal;
    }
    
    public void setPreciototal(Double preciototal) {
        this.preciototal = preciototal;
    }
    public String getMotivo() {
        return this.motivo;
    }
    
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    public Set<Reclamoproveedor> getReclamoproveedors() {
        return this.reclamoproveedors;
    }
    
    public void setReclamoproveedors(Set<Reclamoproveedor> reclamoproveedors) {
        this.reclamoproveedors = reclamoproveedors;
    }
    public Set<Detallecompra> getDetallecompras() {
        return this.detallecompras;
    }
    
    public void setDetallecompras(Set<Detallecompra> detallecompras) {
        this.detallecompras = detallecompras;
    }




}


