//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\rrhh\\Barrio.java

package metalsoft.negocio.rrhh;


public class Barrio 
{
   private String nombre;
   private Localidad localidad;
   
   /**
    * @roseuid 4C27ED0E0001
    */
   public Barrio() 
   {
    
   }

    public Barrio(String nombre, Localidad localidad)
    {
        this.nombre=nombre;
        this.localidad=localidad;
    }
    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
   /**
    * @roseuid 4C0EC5AE0371
    */
   public void crear() 
   {
    
   }
   
   /**
    * @roseuid 4C1F868301EB
    */
   public void mostrarLocalidad() 
   {
    
   }
   
   /**
    * @roseuid 4C205ED4032D
    */
   public void esBarrio() 
   {
    
   }
}
