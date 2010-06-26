//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\negocio\\compras\\PersonaJuridica.java

package metalsoft.negocio.compras;

import java.util.Date;
import metalsoft.negocio.ventas.CondicionIva;

public class PersonaJuridica 
{
   private String razonSocial;
   //private Responsable responsable;
   private String telefono;
   private String celular;
   private String mail;
   //private Domicilio domicilio;
   private Date fechaAlta;
   private Date fechaBaja;
   private String CUIL;
   private CondicionIva iva;
   private String CUIT;
   //public Domicilio theDomicilio;
   //public Responsable theResponsable;
   //public CondicionIva theCondicionIva;
   
   /**
    * @roseuid 4C205CD40125
    */
   public PersonaJuridica() 
   {
    
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
