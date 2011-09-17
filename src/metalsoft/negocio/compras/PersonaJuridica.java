//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\negocio\\compras\\PersonaJuridica.java

package metalsoft.negocio.compras;

import java.util.Date;
import metalsoft.negocio.rrhh.Domicilio;
import metalsoft.negocio.ventas.CondicionIva;

public class PersonaJuridica
{
   private String razonSocial;
   private Responsable responsable;
   private String telefono;
   private String celular;
   private String mail;
   private Domicilio domicilio;
   private Date fechaAlta;
   private Date fechaBaja;
   private String CUIL;
   private CondicionIva iva;
   private String CUIT;
   public Domicilio theDomicilio;
   public Responsable theResponsable;
   public CondicionIva theCondicionIva;
   
   /**
    * @roseuid 4C205CD40125
    */
   public PersonaJuridica() 
   {
    
   }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public CondicionIva getTheCondicionIva() {
        return theCondicionIva;
    }

    public void setTheCondicionIva(CondicionIva theCondicionIva) {
        this.theCondicionIva = theCondicionIva;
    }

    public Domicilio getTheDomicilio() {
        return theDomicilio;
    }

    public void setTheDomicilio(Domicilio theDomicilio) {
        this.theDomicilio = theDomicilio;
    }

    public Responsable getTheResponsable() {
        return theResponsable;
    }

    public void setTheResponsable(Responsable theResponsable) {
        this.theResponsable = theResponsable;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }
   
   /**
    * @roseuid 4BC24F0802EE
    */
   public void crear() 
   {
    
   }

    public CondicionIva getIva() {
        return iva;
    }

    public void setIva(CondicionIva iva) {
        this.iva = iva;
    }

    public String getCUIL() {
        return CUIL;
    }

    public void setCUIL(String CUIL) {
        this.CUIL = CUIL;
    }

    public String getCUIT() {
        return CUIT;
    }

    public void setCUIT(String CUIT) {
        this.CUIT = CUIT;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

   /**
    * @roseuid 4BC24F0B0088
    */
   public void conocerCondicionIva() 
   {
    
   }
   
   /**
    * @roseuid 4C17F5AC0003
    */
   public void conocerResponsable() 
   {
    
   }
   
   /**
    * @roseuid 4C17F5BE02DA
    */
   public void conocerDomicilio() 
   {
    
   }
}
