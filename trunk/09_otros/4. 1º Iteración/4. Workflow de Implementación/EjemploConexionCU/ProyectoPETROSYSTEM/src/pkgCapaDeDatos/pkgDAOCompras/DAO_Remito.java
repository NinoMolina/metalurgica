/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgDAOCompras;
import pkgClasesDeNegocio.Compras.*;
import pkgCapaDeDatos.pkgSoporteConexionBD.*;
import pkgCapaDeDatos.pkgSoporteConexionBD.DAOManager;
import java.sql.ResultSet;
import pkgRecursosDeSoporte.pkgLista.*;
import pkgRecursosDeSoporte.ValText;/**
 *
 * @author Fer
 */
public class DAO_Remito {
DAOManager daoManager=new DAOManager();


public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
     
       //en la condicion de busqueda de la consulta se de be empezar con and y no con where
       String consulta = "SELECT id_remito, fk_recepcion, fk_detallerecepcion, numero from bdpetrosystem.remito "+ condicionBusqueda;
       
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen proveedores con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
      while (res.next()){
      
       
         int id_Remito;
         int fk_Recepcion;
         int fk_DetalleRecepcion;
         int numero;  
       
         
         
         id_Remito= res.getInt(1);
         fk_Recepcion=res.getInt(2);
         fk_DetalleRecepcion=res.getInt(3);
         numero=res.getInt(4);
     
  Remito remito= new Remito();
      
      remito.setNumero(numero);
      remito.addAttribute("id_Remito",new Integer(id_Remito));
      remito.addAttribute("fk_Recepcion", new Integer(fk_Recepcion));
      remito.addAttribute("fk_DetalleRecepcion",new Integer(fk_DetalleRecepcion));
  
      lstResultado.insertarOrdenado(remito);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }  
public boolean desmaterializar_CmdInsert(Remito remito) throws Exception{
      boolean res=true;
     
      this.daoManager.establecerConexion();
      
      
      
      String consulta="INSERT INTO bdpetrosystem.remito (numero,fk_recepcion,fk_detallerecepcion) VALUES ("+remito.getNumero()+","+ValText.getInt_Integer(remito.getAttribute("fk_Recepcion"))+","+ValText.getInt_Integer(remito.getAttribute("fk_DetalleRecepcion"))+")";
      
    
    
      int cantRows=daoManager.ejecutarUpdate(consulta);
       
      if(cantRows==0){
         
          res=false;
       }
       
     return res;
   }
 public boolean cmdUpdate(Remito remito,String condicionUpdate) throws Exception{
      boolean res=true;
     
      this.daoManager.establecerConexion();
      
      String consulta="UPDATE bdpetrosystem.remito set numero="+remito.getNumero()+", fk_Recepcion="+ValText.getInt_Integer(remito.getAttribute("fk_Recepcion"))+", fk_DetalleRecepcion="+ValText.getInt_Integer(remito.getAttribute("fk_DetalleRemito"))+" "+condicionUpdate;
 
      int cantRows=daoManager.ejecutarUpdate(consulta);
       
      if(cantRows==0){
         
          res=false;
       }
       
     return res;
   }  


}
