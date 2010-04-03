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
public class DAO_TipoProducto {
DAOManager daoManager=new DAOManager();


public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
     
       //en la condicion de busqueda de la consulta se de be empezar con and y no con where
       String consulta = "SELECT id_tipoproducto, nombre, descripcion from bdpetrosystem.tipoproducto "+ condicionBusqueda;
       
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen Tipo Producto con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
      while (res.next()){
          
            int id_TipoProducto;  
            String nombre=new String();
            String descripcion=new String();

            id_TipoProducto=res.getInt(1);
            nombre= res.getString(2);
            descripcion=res.getString(3);
 
         
    TipoProducto tipoProducto=new TipoProducto();
       
     
                tipoProducto.addAttribute("id_TipoProducto", new Integer(id_TipoProducto) );
                tipoProducto.setNombre(nombre);
                tipoProducto.setDescripcion(descripcion);
 
    lstResultado.insertarOrdenado(tipoProducto);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }
}
