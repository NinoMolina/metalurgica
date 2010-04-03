/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgDAOVentas;

import pkgCapaDeDatos.pkgSoporteConexionBD.DAOManager;
import java.sql.ResultSet;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgClasesDeNegocio.CuentaCorriente.TipoProveedor;
import pkgClasesDeNegocio.Ventas.CondicionIva;
import pkgClasesDeNegocio.Ventas.Telefono;
import pkgRecursosDeSoporte.pkgLista.*;
import pkgRecursosDeSoporte.ValText;

/**
 *
 * @author Armando
 */
public class DAO_CondicionIva {

    DAOManager daoManager=new DAOManager();

    public DAO_CondicionIva() {}
    
    public CondicionIva materializar(String condicionBusqueda) throws Exception{
       return null;
    }   
    
    public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
      String consulta = "SELECT * FROM bdpetrosystem.condicioniva " +condicionBusqueda;
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen telefonos con la condición de búsuqueda. (Materializar)");
          return null;
       }
       
      while (res.next()){
      
        int id_CondicionIva=res.getInt(1); 
        String nombre=res.getString(2);
        String descripcion=res.getString(3);
        
        
        
        
        CondicionIva condicionIva=new CondicionIva();
        condicionIva.addAttribute("id_CondicionIva",new Integer(id_CondicionIva));
        condicionIva.setNombre(nombre);
        condicionIva.setDescripcion(descripcion);
               
        lstResultado.insertarOrdenado(condicionIva);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }   
    
  

  
   
   
   
    
}
