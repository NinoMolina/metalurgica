//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\rrhh\\Persona.java

package metalsoft.negocio.rrhh;


public class Persona 
{
   private String nombre;
   private String apellido;
   private String telefono;
   private String email;
   private Domicilio domicilio;
   private long nroDocumento;
   private TipoDocumento tipoDocumento;
   
   /**
    * @roseuid 4C27ED0E03C1
    */
   public Persona() 
   {
    
   }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Domicilio getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(long nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
   
   /**
    * @roseuid 4C0ECE29037F
    */
   public void crear() 
   {
    
   }
   
   /**
    * @roseuid 4C17F62B01F0
    */
   public void conocerDomicilio() 
   {
    
   }
   
   /**
    * @roseuid 4C17F635033F
    */
   public void conocerTipoDocumento() 
   {
    
   }
}
