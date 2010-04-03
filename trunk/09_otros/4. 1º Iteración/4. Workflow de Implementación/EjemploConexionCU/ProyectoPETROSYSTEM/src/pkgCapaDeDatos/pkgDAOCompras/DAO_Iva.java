/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgDAOCompras;
import pkgClasesDeNegocio.Compras.*;
import pkgCapaDeDatos.pkgSoporteConexionBD.*;
import pkgCapaDeDatos.pkgSoporteConexionBD.DAOManager;
import java.sql.ResultSet;
import java.util.Date;
import pkgRecursosDeSoporte.pkgLista.*;
import pkgRecursosDeSoporte.ValText;/**
/**
 *
 * @author Fer
 */
public class DAO_Iva {
DAOManager daoManager=new DAOManager();


public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
     
       //en la condicion de busqueda de la consulta se de be empezar con and y no con where
       String consulta = "SELECT id_iva, porcentaje, descripcion from bdpetrosystem.iva "+ condicionBusqueda;
       
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existe Iva con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
      while (res.next()){
          
            int id_Iva;  
            Double porcentaje;
            String descripcion=new String();

            id_Iva=res.getInt(1);
            porcentaje= res.getDouble(2);
            descripcion=res.getString(3);
 
         
            Iva iva=new Iva();
       
     
                iva.addAttribute("id_Iva", new Integer(id_Iva) );
               iva.setPorcentaje(porcentaje);
            iva.setDescripcion(descripcion);
 
    lstResultado.insertarOrdenado(iva);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }
}
