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
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgRecursosDeSoporte.pkgLista.*;
import pkgRecursosDeSoporte.ValText;

/**
 *
 * @author Armando
 */
public class DAO_Producto {
    
    DAOManager daoManager=new DAOManager();

    public DAO_Producto() {
    }
    
     public Producto materializarProducto (String condicion) throws Exception{
     
     
     this.daoManager.establecerConexion();
    
     String consulta="select id_Producto,nombre,precioCosto,precioventa,stockminimo,stockactual, unidad, autorizacionpedidoenplaya, fk_iva  from  bdpetrosystem.producto "+condicion;   
        
     ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen Productos con la condición de búsuqueda. (Materalizar)");
          return null;
      }
     Producto producto=new Producto();
       while (res.next()){
          
            

           int id_Producto=res.getInt(1);
           String nombre= res.getString(2);
           double precioCosto=res.getDouble(3);
           double precioVenta=res.getDouble(4);
           double stockMinimo=res.getDouble(5);
           double stockActual=res.getDouble(6);
           String unidad=res.getString(7);
           boolean autorizacionPedidoEnPlaya=res.getBoolean(8);
           int fk_Iva=res.getInt(9);
           
           
 
         
           
       
     
           producto.addAttribute("id_Producto", new Integer(id_Producto) );
           producto.setNombre(nombre);
           producto.setPrecioCosto(precioCosto);
           producto.setPrecioVenta(precioVenta);
           producto.setStockMinimo(stockMinimo);
           producto.setStockActual(stockActual);
           producto.setUnidad(unidad);
           producto.setAutorizacionPedidoEnPlaya(autorizacionPedidoEnPlaya);
           producto.addAttribute("fk_Iva", fk_Iva);
          
          
        
           
      }
     
     daoManager.cerrarConexion();
     
     return producto;
    }
    

    /*public Lista materializarTodo(String condicionBusqueda)  throws Exception{
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
      
    }*/
    
    public Lista materializarProductosProveedor (Proveedor proveedor) throws Exception{
     Lista lstResultado=new Lista();
     
     this.daoManager.establecerConexion();
    
     String consulta="select pro.id_Producto,pro.nombre,pro.precioCosto,pro.precioVenta,pro.stockMinimo,pro.stockActual,pro.unidad from bdpetrosystem.producto pro, bdpetrosystem.productoxproveedor proxprov where proxprov.fk_Proveedor="+ValText.getInt_Integer(proveedor.getAttribute("id_Proveedor"))+" and pro.id_Producto=proxprov.fk_Producto";   
        
     ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen Productos con la condición de búsuqueda. (Materalizar)");
          return null;
      }
     
       while (res.next()){
          
            

           int id_Producto=res.getInt(1);
           String nombre= res.getString(2);
           double precioCosto=res.getDouble(3);
           double precioVenta=res.getDouble(4);
           double stockMinimo=res.getDouble(5);
           double stockActual=res.getDouble(6);
           String unidad=res.getString(7);
 
         
           Producto producto=new Producto();
       
     
           producto.addAttribute("id_Producto", new Integer(id_Producto) );
           producto.setNombre(nombre);
           producto.setPrecioCosto(precioCosto);
           producto.setPrecioVenta(precioVenta);
           producto.setStockMinimo(stockMinimo);
           producto.setStockActual(stockActual);
           producto.setUnidad(unidad);
 
           lstResultado.insertarOrdenado(producto);
           
      }
     
     daoManager.cerrarConexion();
     
     return lstResultado;
    }
    
    
    
    
}
