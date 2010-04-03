/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgDAOAdministracionDePersonal;
import pkgClasesDeNegocio.Compras.*;
import pkgCapaDeDatos.pkgSoporteConexionBD.*;
import pkgCapaDeDatos.pkgSoporteConexionBD.DAOManager;
import java.sql.ResultSet;
import java.util.Date;
import pkgClasesDeNegocio.AdministracionDePersonal.Empleado;
import pkgRecursosDeSoporte.pkgLista.*;
import pkgRecursosDeSoporte.ValText;/**
/**
/**
 *
 * @author Fer
 */
public class DAO_Empleado {
    DAOManager daoManager=new DAOManager();
public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
     
       //en la condicion de busqueda de la consulta se de be empezar con and y no con where
       String consulta = "SELECT id_empleado, nombre, apellido from bdpetrosystem.empleado "+ condicionBusqueda;
       
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen Tipo Producto con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
      while (res.next()){
          
            int id_Empleado;  
            String nombre=new String();
            String apellido=new String();
           
            id_Empleado=res.getInt(1);
            nombre= res.getString(2);
            apellido=res.getString(3);
 
         
   Empleado empleado=new Empleado();
       
     
                empleado.addAttribute("id_Empleado", new Integer (id_Empleado));
                empleado.setNombre(nombre);
                empleado.setApellido(apellido);
 
    lstResultado.insertarOrdenado(empleado);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }
}
