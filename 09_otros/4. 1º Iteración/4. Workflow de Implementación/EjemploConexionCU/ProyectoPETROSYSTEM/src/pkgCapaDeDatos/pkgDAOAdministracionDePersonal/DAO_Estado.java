/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgDAOAdministracionDePersonal;

import pkgCapaDeDatos.pkgSoporteConexionBD.DAOManager;
import java.sql.ResultSet;
import pkgClasesDeNegocio.AdministracionDePersonal.Estado;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgClasesDeNegocio.CuentaCorriente.TipoProveedor;
import pkgClasesDeNegocio.Ventas.Telefono;
import pkgRecursosDeSoporte.pkgLista.*;
import pkgRecursosDeSoporte.ValText;

/**
 *
 * @author Armando
 */
public class DAO_Estado {
 
     DAOManager daoManager=new DAOManager();

    public DAO_Estado() {}
    
   public Estado materializar(String condicionBusqueda) throws Exception{
    Estado estado=new Estado();
      
      this.daoManager.establecerConexion();
      
      String consulta = "SELECT * FROM bdpetrosystem.estado " +condicionBusqueda;
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen estados con la condición de búsuqueda. (Materializar)");
          return null;
       }
       
      while (res.next()){
      
        int id_Estado=res.getInt(1); 
        String nombre=res.getString(2);
        String descripcion=res.getString(3);
        int fk_AmbitoEstado=res.getInt(4);
        
        
        
 
        estado.addAttribute("id_Estado",new Integer(id_Estado));
        estado.setNombre(nombre);
        estado.setDescripcion(descripcion);
        estado.addAttribute("fk_AmbitoEstado",new Integer(fk_AmbitoEstado));
       
 
           
      }
      
      return estado;
      
     } 
 
    
    public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
      String consulta = "SELECT * FROM bdpetrosystem.estado " +condicionBusqueda;
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen estados con la condición de búsuqueda. (Materializar)");
          return null;
       }
       
      while (res.next()){
      
        int id_Estado=res.getInt(1); 
        String nombre=res.getString(2);
        String descripcion=res.getString(3);
        int fk_AmbitoEstado=res.getInt(4);
        
        
        
        Estado estado=new Estado();
        estado.addAttribute("id_Estado",new Integer(id_Estado));
        estado.setNombre(nombre);
        estado.setDescripcion(descripcion);
        estado.addAttribute("fk_AmbitoEstado",new Integer(fk_AmbitoEstado));
       
        lstResultado.insertarOrdenado(estado);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }   
    
    
    
}
