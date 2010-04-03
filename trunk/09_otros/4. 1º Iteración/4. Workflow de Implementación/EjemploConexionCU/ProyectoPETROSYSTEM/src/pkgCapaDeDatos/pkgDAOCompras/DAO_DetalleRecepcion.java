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
public class DAO_DetalleRecepcion {
DAOManager daoManager=new DAOManager();


public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
     
       //en la condicion de busqueda de la consulta se de be empezar con and y no con where
       String consulta = "SELECT id_detallerecepcion, fecharecepcion, horarealrecepcion, cantidadrecibida,preciounitario,fk_producto, fk_empleado, fk_recepcion, fk_DetallePedidoAProveedor from bdpetrosystem.detallerecepcion "+ condicionBusqueda;
       
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen proveedores con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
      while (res.next()){
          
    int id_DetalleRecepcion;  

    Date fechaRecepcion=new Date();
    String horaRealRecepcion=new String();
    double cantidadRecibida;
    double precioUnitario;
    int fk_Producto;
    int fk_Empleado;
    int fk_Recepcion;
    int fk_DetallePedidoAProveedor;
    
    id_DetalleRecepcion=res.getInt(1);
    fechaRecepcion= res.getDate(2);
    horaRealRecepcion=res.getString(3);
    cantidadRecibida=res.getDouble(4);
    precioUnitario=res.getDouble(5);
    fk_Producto=res.getInt(6);
    fk_Empleado=res.getInt(7);
    fk_Recepcion=res.getInt(8);
    fk_DetallePedidoAProveedor=res.getInt(9);
         
        
     
  DetalleRecepcion detalleRecepcion=new DetalleRecepcion();
   detalleRecepcion.addAttribute("id_DetalleRecepcion",  new Integer(id_DetalleRecepcion));
 detalleRecepcion.setCantidadRecibida(cantidadRecibida);
 detalleRecepcion.setFechaRecepcion(fechaRecepcion);
 detalleRecepcion.setHoraRealRecepcion(horaRealRecepcion);
 detalleRecepcion.setPrecioUnitario(precioUnitario);
 detalleRecepcion.addAttribute("fk_Producto", new Integer(fk_Producto));
   detalleRecepcion.addAttribute("fk_Empleado", new Integer(fk_Empleado));
    detalleRecepcion.addAttribute("fk_recepcion", new Integer(fk_Recepcion));
     detalleRecepcion.addAttribute("fk_DetallePedidoAProveedor", new Integer(fk_DetallePedidoAProveedor));
  
     lstResultado.insertarOrdenado(detalleRecepcion);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }  
public boolean desmaterializar_CmdInsert(DetalleRecepcion detalleRecepcion) throws Exception{
      boolean res=true;
     
      this.daoManager.establecerConexion();
      
      
      
      String consulta="INSERT INTO bdpetrosystem.detallerecepcion ( fecharecepcion, horarealrecepcion, cantidadrecibida,preciounitario,fk_producto, fk_empleado, fk_recepcion, fk_DetallePedidoAProveedor) VALUES ('"+ValText.getFormatStringDate(detalleRecepcion.getFechaRecepcion())+"','"+detalleRecepcion.getHoraRealRecepcion().toString()+"',"+detalleRecepcion.getCantidadRecibida()+","+detalleRecepcion.getPrecioUnitario()+","+ValText.getInt_Integer(detalleRecepcion.getAttribute("fk_Producto"))+","+ValText.getInt_Integer(detalleRecepcion.getAttribute("fk_Empleado"))+","+ValText.getInt_Integer(detalleRecepcion.getAttribute("fk_Recepcion"))+","+ValText.getInt_Integer(detalleRecepcion.getAttribute("fk_DetallePedidoAProveedor"))+")";
      
    
    
      int cantRows=daoManager.ejecutarUpdate(consulta);
       
      if(cantRows==0){
         
          res=false;
       }
       
     return res;
   }
 public boolean cmdUpdate(DetalleRecepcion detalleRecepcion,String condicionUpdate) throws Exception{
      boolean res=true;
     
      this.daoManager.establecerConexion();
      
      String consulta="UPDATE bdpetrosystem.detallerecepcion set fecharecepcion='"+detalleRecepcion.getFechaRecepcion().toString()+"', horarealrecepcion='"+detalleRecepcion.getHoraRealRecepcion().toString()+"', cantidadrecibida="+detalleRecepcion.getCantidadRecibida()+",preciounitario="+detalleRecepcion.getPrecioUnitario()+",fk_producto="+ValText.getInt_Integer(detalleRecepcion.getAttribute("fk_Producto"))+", fk_empleado="+ValText.getInt_Integer(detalleRecepcion.getAttribute("fk_Empleado"))+", fk_recepcion="+ValText.getInt_Integer(detalleRecepcion.getAttribute("fk_Recepcion"))+", fk_DetallePedidoAProveedor="+ValText.getInt_Integer(detalleRecepcion.getAttribute("fk_DetallePedidoAProveedor"))+" "+condicionUpdate;
      int cantRows=daoManager.ejecutarUpdate(consulta);
       
      if(cantRows==0){
         
          res=false;
       }
       
     return res;
   } 
 
 public int getUltimoId() throws Exception{
      
        daoManager.establecerConexion();
        
        int valor=-1;
        String consulta="SELECT max(d.id_detallerecepcion) FROM detallerecepcion d";
          
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
