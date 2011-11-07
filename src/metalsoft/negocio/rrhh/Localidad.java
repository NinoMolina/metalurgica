//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\rrhh\\Localidad.java

package metalsoft.negocio.rrhh;


public class Localidad 
{
   private String nombre;
   private int codPostal;
   private Barrio barrios[];
   private Provincia provincia;
   
   /**
    * @roseuid 4C27ED0D01AF
    */
   public Localidad() 
   {

   }
    public Localidad(String nombre, int codPostal, Provincia provincia)
    {
        this.nombre=nombre;
        this.codPostal=codPostal;
        this.provincia=provincia;
    }

    public Barrio[] getBarrios() {
        return barrios;
    }

    public void setBarrios(Barrio[] barrios) {
        this.barrios = barrios;
    }

    public int getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
   
   /**
    * @roseuid 4C0EC5B3009B
    */
   public void crear() 
   {
    
   }
   
   /**
    * @roseuid 4C17FD4A0260
    */
   public void conocerBarrio() 
   {
    
   }
   
   /**
    * @roseuid 4C1F86E703BB
    */
   public void mostrarBarrio() 
   {
    
   }
   
   /**
    * @roseuid 4C1F86F60393
    */
   public void mostrarProvincia() 
   {
    
   }
   
   /**
    * @roseuid 4C205E9B000F
    */
   public void esLocalidad() 
   {
    
   }
   
   /**
    * @roseuid 4C205EF50316
    */
   public void crearBarrio() 
   {
    
   }
}
