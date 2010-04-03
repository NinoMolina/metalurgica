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
public class DAO_Combustible {
DAOManager daoManager=new DAOManager();


public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
     
       //en la condicion de busqueda de la consulta se de be empezar con and y no con where
       String consulta = "SELECT c.id_combustible,c.fk_producto,p.nombre,p.preciocosto,p.precioventa,p.stockminimo,p.stockactual,p.unidad,p.autorizacionpedidoenplaya,p.fk_iva   FROM bdpetrosystem.combustibles c, bdpetrosystem.producto p where p.id_producto=c.fk_producto "+(condicionBusqueda == "" ? "" : " and " + condicionBusqueda) +condicionBusqueda;
       
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen proveedores con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
      while (res.next()){
      
       
         int id_Combustible;
         int fk_Producto;
         String nombre=new String();
         double precioCosto;
         double precioVenta;
         double stockMinimo;
         double stockActual;
         String unidad=new String();
         boolean autorizacionPedidoEnPlaya;
         int fk_iva;
    
         id_Combustible=res.getInt(1);
         fk_Producto=res.getInt(2);
         nombre=res.getString(3);
         precioCosto=res.getDouble(4);
         precioVenta=res.getDouble(5);
         stockMinimo=res.getDouble(6);
         stockActual=res.getDouble(7);
         unidad=res.getString(8);
         autorizacionPedidoEnPlaya=res.getBoolean(9);
         fk_iva=res.getInt(10);
    
Combustible combustible=new Combustible();

        combustible.addAttribute("id_Combustible", new Integer(id_Combustible));
        combustible.addAttribute("fk_Producto",new Integer(fk_Producto));
        combustible.setNombre(nombre);
        combustible.setPrecioCosto(precioCosto);
        combustible.setPrecioVenta(precioVenta);
        combustible.setStockMinimo(stockMinimo);
        combustible.setStockActual(stockActual);
        combustible.setUnidad(unidad);
        combustible.setAutorizacionPedidoEnPlaya(autorizacionPedidoEnPlaya);
        combustible.addAttribute("fk_iva",new Integer(fk_iva));

       lstResultado.insertarOrdenado(combustible);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }  
}
