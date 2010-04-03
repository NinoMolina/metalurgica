/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgDAOCompras;

import pkgCapaDeDatos.pkgSoporteConexionBD.DAOManager;
import java.sql.ResultSet;
import java.util.Date;
import pkgClasesDeNegocio.Compras.PedidoAProveedor;
import pkgClasesDeNegocio.Compras.*;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgClasesDeNegocio.CuentaCorriente.TipoProveedor;
import pkgClasesDeNegocio.Ventas.Telefono;
import pkgRecursosDeSoporte.pkgLista.*;
import pkgRecursosDeSoporte.ValText;

/**
 *
 * @author Armando
 */
public class DAO_DetallePedidoAProveedor {

     DAOManager daoManager=new DAOManager();

    public DAO_DetallePedidoAProveedor() {}
    
    public PedidoAProveedor materializar(String condicionBusqueda) throws Exception{
      return null;
    }   
    
    public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
      String consulta = "SELECT * FROM bdpetrosystem.detallepedidoaproveedor " +condicionBusqueda;
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen detalles de pedidos de proveedores con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
      while (res.next()){
      
        int id_DetallePedidoAProveedor=res.getInt(1);
        double cantidad=res.getDouble(2);
        double precioActual=res.getDouble(3);
        
        int fk_Producto=res.getInt(4);
        int fk_PedidoAProveedor=res.getInt(5);
        int fk_Estado=res.getInt(6);
        
        DetallePedidoAProveedor dlle=new DetallePedidoAProveedor();
        
        dlle.addAttribute("id_DetallePedidoAProveedor",new Integer(id_DetallePedidoAProveedor));
        dlle.setCantidad(cantidad);
        dlle.setPrecioActual(precioActual);
        
        dlle.addAttribute("fk_Producto",new Integer(fk_Producto));
        dlle.addAttribute("fk_PedidoAProveedor",new Integer(fk_PedidoAProveedor));
        dlle.addAttribute("fk_Estado",new Integer(fk_Estado));
        
       
       lstResultado.insertarOrdenado(dlle);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }   
    
   public boolean desmaterializar_CmdInsert(DetallePedidoAProveedor detalle) throws Exception{
      boolean res=true;
     
      this.daoManager.establecerConexion();
      
     
      String consulta="INSERT INTO bdpetrosystem.detallepedidoaproveedor (cantidad,precioActual,fk_Producto,fk_PedidoAProveedor,fk_Estado) VALUES ("+
                             detalle.getCantidad()+","+detalle.getPrecioActual()+","+ValText.getInt_Integer(detalle.getProducto().getAttribute("id_Producto"))+
                             ","+ValText.getInt_Integer(detalle.getAttribute("fk_PedidoAProveedor"))+","+ValText.getInt_Integer(detalle.getEstado().getAttribute("id_Estado"))+")";
      
    
      
      int cantRows=daoManager.ejecutarUpdate(consulta);
       
      if(cantRows==0){
         
          res=false;
       }
       
     daoManager.cerrarConexion(); 
      
     return res;
   }
   
   public boolean cmdUpdate(DetallePedidoAProveedor detalle,String condicionUpdate) throws Exception{
      boolean res=true;
     
      this.daoManager.establecerConexion();
      
      String consulta="UPDATE bdpetrosystem.detallepedidoaproveedor set cantidad="+detalle.getCantidad()+",precioActual='"+detalle.getPrecioActual()+
                      ",fk_Producto="+ValText.getInt_Integer(detalle.getAttribute("fk_Producto"))+
                      ",fk_PedidoAProveedor="+ValText.getInt_Integer(detalle.getAttribute("fk_PedidoAProveedor"))+",fk_Estado="+ValText.getInt_Integer(detalle.getAttribute("fk_Estado"))+" "+condicionUpdate;
 
      int cantRows=daoManager.ejecutarUpdate(consulta);
       
      if(cantRows==0){
         
          res=false;
       }
       
     return res;
   }  

  
   
   
   
   
   
   
   /*public boolean cmdDelete(Proveedor proveedor){
     boolean res=false;    
     return res;
   }*/ 
    
    
    
}
