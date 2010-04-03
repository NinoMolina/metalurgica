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
public class DAO_Recepcion {
DAOManager daoManager=new DAOManager();

 public Recepcion materializarRecepcion (String condicionBusqueda) throws Exception{
    Recepcion recepcion=null;
      
    this.daoManager.establecerConexion();
      
     
    
    String consulta = "SELECT id_recepcion, fecharealrecepcion, horarealrecepcion, montototal, fk_facturaproveedor, fk_pedidoaproveedor from bdpetrosystem.recepcion "+ condicionBusqueda;
       
    
    ResultSet res=daoManager.ejecutarConsulta(consulta);
       
    if(res==null){
          System.out.println("No existen proveedores con la condición de búsuqueda. (Materalizar)");
          return null;
     }
       
    while (res.next()){
      
     int id_Recepcion;   
     Date fechaRealRecepcion=new Date();
     String horaRealRecepcion=new String();
     double montoTotal;

     int fk_PedidoAProveedor;
   
     int fk_FacturaProveedor;
  
    
         
     id_Recepcion= res.getInt(1);
     fechaRealRecepcion=res.getDate(2);
     horaRealRecepcion=res.getString(3);
     montoTotal=res.getDouble(4);
     fk_FacturaProveedor=res.getInt(5);
     fk_PedidoAProveedor=res.getInt(6);
  
     
     recepcion=new Recepcion();
  
     recepcion.setFechaRealRecepcion(fechaRealRecepcion);
     recepcion.setHoraRealRecepcion(horaRealRecepcion);
     recepcion.setMontoTotal(montoTotal);
     recepcion.addAttribute("id_Recepcion",new Integer(id_Recepcion));
     recepcion.addAttribute("fk_FacturaProveedor", new Integer(fk_FacturaProveedor));
     recepcion.addAttribute("fk_DetalleRecepcion",new Integer(fk_PedidoAProveedor));
  
  
  
           
      }

      daoManager.cerrarConexion();
      
      return recepcion;
 }


public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
     
       //en la condicion de busqueda de la consulta se de be empezar con and y no con where
       String consulta = "SELECT id_recepcion, fecharealrecepcion, horarealrecepcion, montototal, fk_facturaproveedor, fk_pedidoaproveedor from bdpetrosystem.recepcion "+ condicionBusqueda;
       
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen proveedores con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
      while (res.next()){
      
    int id_Recepcion;   
  Date fechaRealRecepcion=new Date();
     String horaRealRecepcion=new String();
  double montoTotal;

  int fk_PedidoAProveedor;
   
 int fk_FacturaProveedor;
  
    
         
         id_Recepcion= res.getInt(1);
         fechaRealRecepcion=res.getDate(2);
         horaRealRecepcion=res.getString(3);
         montoTotal=res.getDouble(4);
         fk_FacturaProveedor=res.getInt(5);
         fk_PedidoAProveedor=res.getInt(6);
  
     
  Recepcion recepcion=new Recepcion();
  
  recepcion.setFechaRealRecepcion(fechaRealRecepcion);
  recepcion.setHoraRealRecepcion(horaRealRecepcion);
  recepcion.setMontoTotal(montoTotal);
  recepcion.addAttribute("id_Recepcion",new Integer(id_Recepcion));
  recepcion.addAttribute("fk_FacturaProveedor", new Integer(fk_FacturaProveedor));
  recepcion.addAttribute("fk_DetalleRecepcion",new Integer(fk_PedidoAProveedor));
  
  
  lstResultado.insertarOrdenado(recepcion);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }  
public boolean desmaterializar_CmdInsert(Recepcion recepcion) throws Exception{
      boolean res=true;
     
      this.daoManager.establecerConexion();
      
      
      
        String consulta="INSERT INTO bdpetrosystem.recepcion (fecharealrecepcion, horarealrecepcion, montototal, fk_facturaproveedor, fk_pedidoaproveedor) VALUES ('"+ValText.getFormatStringDate(recepcion.getFechaRealRecepcion())+"','"+recepcion.getHoraRealRecepcion().toString()+"',"+recepcion.getMontoTotal()+","+(Integer)(recepcion.getAttribute("fk_FacturaProveedor"))+","+ValText.getInt_Integer(recepcion.getAttribute("fk_PedidoAProveedor"))+")";
      
    
    
      int cantRows=daoManager.ejecutarUpdate(consulta);
       
      if(cantRows==0){
         
          res=false;
       }
       
     return res;
   }
 public boolean cmdUpdate(Recepcion recepcion,String condicionUpdate) throws Exception{
      boolean res=true;
     
      this.daoManager.establecerConexion();
      
      String consulta="UPDATE bdpetrosystem.recepcion set fecharealrecepcion='"+recepcion.getFechaRealRecepcion().toString()+"', horarealrecepcion='"+recepcion.getHoraRealRecepcion()+"', montototal="+recepcion.getMontoTotal()+", fk_facturaproveedor="+ValText.getInt_Integer(recepcion.getAttribute("fk_FacturaProveedor"))+", fk_pedidoaproveedor="+ValText.getInt_Integer(recepcion.getAttribute("fk_PedidoAProveedor"))+" "+condicionUpdate;
      int cantRows=daoManager.ejecutarUpdate(consulta);
       
      if(cantRows==0){
         
          res=false;
       }
       
     return res;
   }  
 
 public int getUltimoId() throws Exception{
      
        daoManager.establecerConexion();
        
        int valor=-1;
        String consulta="SELECT max(d.id_recepcion) FROM recepcion d";
          
        ResultSet res=daoManager.ejecutarConsulta(consulta);
        
        if(res==null){
         return -1;
        }
        
        while(res.next()){
        
         valor=res.getInt(1);
        }
        
       daoManager.cerrarConexion(); 
        
       return valor;
    
    }


}
