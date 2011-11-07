package metalsoft.negocio.adminusuarios;

//Source file: Y:\\REPOSITORIO\\METALURGICA\\10_METALSOFT\\ROL.JAVA


public class Rol 
{
   private String rol;
   private String descripcion;
   private Privilegio privilegios;
   public Privilegio thePrivilegio[];
   
   /**
    * @roseuid 4C170926033E
    */
   public Rol() 
   {
    
   }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Privilegio getPrivilegios() {
        return privilegios;
    }

    public void setPrivilegios(Privilegio privilegios) {
        this.privilegios = privilegios;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public Privilegio[] getThePrivilegio() {
        return thePrivilegio;
    }

    public void setThePrivilegio(Privilegio[] thePrivilegio) {
        this.thePrivilegio = thePrivilegio;
    }
   
   /**
    * @roseuid 4BE5BF2702F6
    */
   public void conocerPrivilegios() 
   {
    
   }
   
   /**
    * @roseuid 4C0EC40D0210
    */
   public void mostrarPrivilegios() 
   {
    
   }
}
