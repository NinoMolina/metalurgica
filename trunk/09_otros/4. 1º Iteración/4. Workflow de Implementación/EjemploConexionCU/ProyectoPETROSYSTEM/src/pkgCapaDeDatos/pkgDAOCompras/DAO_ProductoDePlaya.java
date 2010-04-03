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
/**
 *
 * @author Fer
 */
public class DAO_ProductoDePlaya {
DAOManager daoManager=new DAOManager();


public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
     
       //en la condicion de busqueda de la consulta se de be empezar con and y no con where
       String consulta = "SELECT y.id_combustible,y.fk_producto,p.nombre,p.preciocosto,p.precioventa,p.stockminimo,p.stockactual,p.unidad,p.autorizacionpedidoenplaya,p.fk_iva,y.stockEnPlaya   FROM bdpetrosystem.productosdeplaya y, bdpetrosystem.producto p where p.id_producto=y.fk_producto "+(condicionBusqueda == "" ? "" : " and " + condicionBusqueda) +condicionBusqueda;
       
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen proveedores con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
      while (res.next()){
      
       
         int id_ProductoDePlaya;
         int fk_Producto;
         String nombre=new String();
         double precioCosto;
         double precioVenta;
         double stockMinimo;
         double stockActual;
         String unidad=new String();
         boolean autorizacionPedidoEnPlaya;
         int fk_iva;
         double stockEnPlaya;
         
         id_ProductoDePlaya=res.getInt(1);
         fk_Producto=res.getInt(2);
         nombre=res.getString(3);
         precioCosto=res.getDouble(4);
         precioVenta=res.getDouble(5);
         stockMinimo=res.getDouble(6);
         stockActual=res.getDouble(7);
         unidad=res.getString(8);
         autorizacionPedidoEnPlaya=res.getBoolean(9);
         fk_iva=res.getInt(10);
         stockEnPlaya=res.getDouble(11);
    
ProductoDePlaya productoDePlaya=new ProductoDePlaya();

        productoDePlaya.addAttribute("id_ProductoDePlaya", new Integer(id_ProductoDePlaya));
        productoDePlaya.addAttribute("fk_Producto",new Integer(fk_Producto));
        productoDePlaya.setNombre(nombre);
        productoDePlaya.setPrecioCosto(precioCosto);
        productoDePlaya.setPrecioVenta(precioVenta);
        productoDePlaya.setStockMinimo(stockMinimo);
        productoDePlaya.setStockActual(stockActual);
        productoDePlaya.setUnidad(unidad);
        productoDePlaya.setAutorizacionPedidoEnPlaya(autorizacionPedidoEnPlaya);
        productoDePlaya.addAttribute("fk_iva",new Integer(fk_iva));
        productoDePlaya.setStockEnPlaya(stockEnPlaya);
                
       lstResultado.insertarOrdenado(productoDePlaya);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }  
}
