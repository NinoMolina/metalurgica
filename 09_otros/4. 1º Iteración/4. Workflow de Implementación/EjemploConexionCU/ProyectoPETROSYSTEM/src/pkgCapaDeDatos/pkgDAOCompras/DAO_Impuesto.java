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
public class DAO_Impuesto {
DAOManager daoManager=new DAOManager();


public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
     
       //en la condicion de busqueda de la consulta se de be empezar con and y no con where
       String consulta = "SELECT id_iva, porcentaje, nombre, monto from bdpetrosystem.impuesto "+ condicionBusqueda;
       
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existe Impuesto con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
      while (res.next()){
          
            int id_Impuesto;  
            Double porcentaje;
            String nombre=new String();
            Double monto;
            
            id_Impuesto=res.getInt(1);
            porcentaje= res.getDouble(2);
            nombre=res.getString(3);
            monto=res.getDouble(4);
         
            Impuesto impuesto=new Impuesto();
       
     
                impuesto.addAttribute("id_Impuesto", new Integer(id_Impuesto) );
                impuesto.setPorcentaje(porcentaje);
                impuesto.setNombre(nombre);
                impuesto.setMonto(monto);
    
                lstResultado.insertarOrdenado(impuesto);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }
}