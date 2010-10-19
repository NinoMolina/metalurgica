package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Proveedor generated by hbm2java
 */
public class Proveedor  implements java.io.Serializable {


     private long idproveedor;
     private Domicilio domicilio;
     private Responsable responsable;
     private Condicioniva condicioniva;
     private Long nroproveedor;
     private String razonsocial;
     private String telefono;
     private String celular;
     private String mail;
     private Date fechaalta;
     private Date fechabaja;
     private String cuil;
     private String cuit;
     private Set<Proveedorxmateriaprima> proveedorxmateriaprimas = new HashSet<Proveedorxmateriaprima>(0);
     private Set<Compra> compras = new HashSet<Compra>(0);

    public Proveedor() {
    }

	
    public Proveedor(long idproveedor) {
        this.idproveedor = idproveedor;
    }
    public Proveedor(long idproveedor, Domicilio domicilio, Responsable responsable, Condicioniva condicioniva, Long nroproveedor, String razonsocial, String telefono, String celular, String mail, Date fechaalta, Date fechabaja, String cuil, String cuit, Set<Proveedorxmateriaprima> proveedorxmateriaprimas, Set<Compra> compras) {
       this.idproveedor = idproveedor;
       this.domicilio = domicilio;
       this.responsable = responsable;
       this.condicioniva = condicioniva;
       this.nroproveedor = nroproveedor;
       this.razonsocial = razonsocial;
       this.telefono = telefono;
       this.celular = celular;
       this.mail = mail;
       this.fechaalta = fechaalta;
       this.fechabaja = fechabaja;
       this.cuil = cuil;
       this.cuit = cuit;
       this.proveedorxmateriaprimas = proveedorxmateriaprimas;
       this.compras = compras;
    }
   
    public long getIdproveedor() {
        return this.idproveedor;
    }
    
    public void setIdproveedor(long idproveedor) {
        this.idproveedor = idproveedor;
    }
    public Domicilio getDomicilio() {
        return this.domicilio;
    }
    
    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }
    public Responsable getResponsable() {
        return this.responsable;
    }
    
    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }
    public Condicioniva getCondicioniva() {
        return this.condicioniva;
    }
    
    public void setCondicioniva(Condicioniva condicioniva) {
        this.condicioniva = condicioniva;
    }
    public Long getNroproveedor() {
        return this.nroproveedor;
    }
    
    public void setNroproveedor(Long nroproveedor) {
        this.nroproveedor = nroproveedor;
    }
    public String getRazonsocial() {
        return this.razonsocial;
    }
    
    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCelular() {
        return this.celular;
    }
    
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getMail() {
        return this.mail;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    public Date getFechaalta() {
        return this.fechaalta;
    }
    
    public void setFechaalta(Date fechaalta) {
        this.fechaalta = fechaalta;
    }
    public Date getFechabaja() {
        return this.fechabaja;
    }
    
    public void setFechabaja(Date fechabaja) {
        this.fechabaja = fechabaja;
    }
    public String getCuil() {
        return this.cuil;
    }
    
    public void setCuil(String cuil) {
        this.cuil = cuil;
    }
    public String getCuit() {
        return this.cuit;
    }
    
    public void setCuit(String cuit) {
        this.cuit = cuit;
    }
    public Set<Proveedorxmateriaprima> getProveedorxmateriaprimas() {
        return this.proveedorxmateriaprimas;
    }
    
    public void setProveedorxmateriaprimas(Set<Proveedorxmateriaprima> proveedorxmateriaprimas) {
        this.proveedorxmateriaprimas = proveedorxmateriaprimas;
    }
    public Set<Compra> getCompras() {
        return this.compras;
    }
    
    public void setCompras(Set<Compra> compras) {
        this.compras = compras;
    }




}


