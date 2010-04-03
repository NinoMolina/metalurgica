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
public class DAO_Tanque {
DAOManager daoManager=new DAOManager();


public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
     
       //en la condicion de busqueda de la consulta se de be empezar con and y no con where
       String consulta = "SELECT id_tanque, numero, capacidad,fechainstalacion,stocktanque, fk_estado, fk_combustible from bdpetrosystem.tanque "+ condicionBusqueda;
       
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen tanques con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
      while (res.next()){
   
            int id_Tanque;   
            int numero;
            double capacidad;
            Date fechaInstalacion=new Date();
            double stockTanque;
            int fk_Estado;
            int fk_Combustible;
    
    
            id_Tanque=res.getInt(1);
            numero= res.getInt(2);
            capacidad=res.getDouble(3);
            fechaInstalacion=res.getDate(4);
            stockTanque=res.getDouble(5);
            fk_Estado=res.getInt(6);
            fk_Combustible=res.getInt(7);

         
        
     
  Tanque tanque=new Tanque();
  
              tanque.addAttribute("id_Tanque", new Integer(id_Tanque));
              tanque.setNumero(numero);
              tanque.setCapacidad(capacidad);
              tanque.setFechaInstalacion(fechaInstalacion);
              tanque.setStockTanque(stockTanque);
              tanque.addAttribute("fk_Estado", new Integer(fk_Estado));
              tanque.addAttribute("fk_Combustible",new Integer(fk_Combustible));
  
  
     lstResultado.insertarOrdenado(tanque);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }  
}
