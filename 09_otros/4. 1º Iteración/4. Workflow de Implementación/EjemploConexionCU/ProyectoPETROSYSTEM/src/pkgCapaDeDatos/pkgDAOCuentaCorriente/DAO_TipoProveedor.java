/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgDAOCuentaCorriente;

import pkgClasesDeNegocio.Compras.*;
import pkgCapaDeDatos.pkgSoporteConexionBD.*;
import pkgCapaDeDatos.pkgSoporteConexionBD.DAOManager;
import java.sql.ResultSet;
import java.util.Date;
import pkgClasesDeNegocio.CuentaCorriente.TipoProveedor;
import pkgRecursosDeSoporte.pkgLista.*;
import pkgRecursosDeSoporte.ValText;

/**
 *
 * @author Armando
 */
public class DAO_TipoProveedor {
 
    DAOManager daoManager=new DAOManager();

    public DAO_TipoProveedor() {
    }


public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
     
       //en la condicion de busqueda de la consulta se de be empezar con and y no con where
       String consulta = "SELECT * from bdpetrosystem.tipoproveedor "+ condicionBusqueda;
       
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existe Tipo Proveedor con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
      while (res.next()){
          
            int id_TipoProveedor;  
            String nombre=new String();
            String descripcion=new String();

            id_TipoProveedor=res.getInt(1);
            nombre= res.getString(2);
            descripcion=res.getString(3);
 
         
            TipoProveedor tipoProveedor=new TipoProveedor();
       
     
            tipoProveedor.addAttribute("id_TipoProveedor", new Integer(id_TipoProveedor) );
            tipoProveedor.setNombre(nombre);
            tipoProveedor.setDescripcion(descripcion);
 
       lstResultado.insertarOrdenado(tipoProveedor);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }
    
    
}
