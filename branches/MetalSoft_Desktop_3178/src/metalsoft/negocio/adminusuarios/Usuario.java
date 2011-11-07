package metalsoft.negocio.adminusuarios;

//Source file: Y:\\REPOSITORIO\\METALURGICA\\10_METALSOFT\\USUARIO.JAVA

import metalsoft.negocio.gestores.GestorIniciarSesionDB;
import metalsoft.negocio.gestores.GestorRolDB;



public class Usuario 
{
   private String user;
   private String pass;
   private Rol rol[];

   
   /**
    * @roseuid 4C1709250364
    */
   public Usuario() 
   {
    
   }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Rol[] getRol() {
        return rol;
    }

    public void setRol(Rol rol[]) {
        this.rol = rol;
    }


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
   
   /**
    * @roseuid 4BE5BCF9025E
    */
   public void validarUsuario() 
   {
    
   }
   
   /**
    * @roseuid 4BE5BF85029E
    */
   public void conocerRol() 
   {
    
   }
   
   /**
    * @roseuid 4BFBF0130005
    */
   public void mostrarApellido() 
   {
    
   }
   
   /**
    * @roseuid 4BFBF05B0108
    */
   public void mostrarTipoDocumento() 
   {
    
   }
   
   /**
    * @roseuid 4BFBF0640242
    */
   public void mostrarDocumento() 
   {
    
   }
   
   /**
    * @roseuid 4BFBF0CF00A5
    */
   public void mostrarNumeroDocumento() 
   {
    
   }
   
   /**
    * @roseuid 4BFBF0DF0156
    */
   public void mostrarCargo() 
   {
    
   }
   
   /**
    * @roseuid 4BFBF20D002E
    */
   public void tomarContrasenia()
   {
    
   }
   
   /**
    * @roseuid 4C0EBD7901CA
    */
   public void mostrarPrivilegio() 
   {
    
   }
   
   /**
    * @roseuid 4C0EC3E70375
    */
   public void mostrarRol() 
   {
    
   }
   
   /**
    * @roseuid 4C0ECE1303CC
    */
   public void mostrarPerfiles() 
   {
    
   }

   public metalsoft.datos.dbobject.UsuarioDB esUsuario(String user, String pass)
   {
       GestorIniciarSesionDB gdb=new GestorIniciarSesionDB();
       return gdb.esUsuario(user, pass);
   }

   public static Rol[] obtenerRoles(long idUsuario)
   {
       GestorRolDB gdb=new GestorRolDB();
       return gdb.obtenerRoles(idUsuario);
   }
}
