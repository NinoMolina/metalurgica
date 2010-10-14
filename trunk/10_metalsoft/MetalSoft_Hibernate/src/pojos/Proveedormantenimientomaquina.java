package pojos;
// Generated 12/10/2010 01:33:18 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Proveedormantenimientomaquina generated by hbm2java
 */
public class Proveedormantenimientomaquina  implements java.io.Serializable {


     private long idproveedormantenimiento;
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
     private Set<Mantenimientocorrectivo> mantenimientocorrectivos = new HashSet<Mantenimientocorrectivo>(0);
     private Set<Mantenimientopreventivo> mantenimientopreventivos = new HashSet<Mantenimientopreventivo>(0);

    public Proveedormantenimientomaquina() {
    }

	
    public Proveedormantenimientomaquina(long idproveedormantenimiento) {
        this.idproveedormantenimiento = idproveedormantenimiento;
    }
    public Proveedormantenimientomaquina(long idproveedormantenimiento, Domicilio domicilio, Responsable responsable, Condicioniva condicioniva, Long nroproveedor, String razonsocial, String telefono, String celular, String mail, Date fechaalta, Date fechabaja, String cuil, String cuit, Set<Mantenimientocorrectivo> mantenimientocorrectivos, Set<Mantenimientopreventivo> mantenimientopreventivos) {
       this.idproveedormantenimiento = idproveedormantenimiento;
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
       this.mantenimientocorrectivos = mantenimientocorrectivos;
       this.mantenimientopreventivos = mantenimientopreventivos;
    }
   
    public long getIdproveedormantenimiento() {
        return this.idproveedormantenimiento;
    }
    
    public void setIdproveedormantenimiento(long idproveedormantenimiento) {
        this.idproveedormantenimiento = idproveedormantenimiento;
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
    public Set<Mantenimientocorrectivo> getMantenimientocorrectivos() {
        return this.mantenimientocorrectivos;
    }
    
    public void setMantenimientocorrectivos(Set<Mantenimientocorrectivo> mantenimientocorrectivos) {
        this.mantenimientocorrectivos = mantenimientocorrectivos;
    }
    public Set<Mantenimientopreventivo> getMantenimientopreventivos() {
        return this.mantenimientopreventivos;
    }
    
    public void setMantenimientopreventivos(Set<Mantenimientopreventivo> mantenimientopreventivos) {
        this.mantenimientopreventivos = mantenimientopreventivos;
    }




}


