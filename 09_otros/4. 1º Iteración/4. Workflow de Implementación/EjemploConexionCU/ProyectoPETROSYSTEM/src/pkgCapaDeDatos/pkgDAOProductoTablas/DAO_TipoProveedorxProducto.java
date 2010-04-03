/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgDAOProductoTablas;

import java.sql.ResultSet;
import pkgCapaDeDatos.pkgSoporteConexionBD.DAOManager;
import pkgClasesDeNegocio.Compras.Producto;
import pkgClasesDeNegocio.CuentaCorriente.TipoProveedor;
import pkgRecursosDeSoporte.ValText;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author usuario
 */
public class DAO_TipoProveedorxProducto {

    
     DAOManager daoManager=new DAOManager();
    
     public Lista materializarProductosPorTipoProveedor(TipoProveedor tipoProveedor) throws Exception{
      Lista lstResultado=new Lista();
     
      this.daoManager.establecerConexion();
      
     
      int id_TipoProveedor=ValText.getInt_Integer(tipoProveedor.getAttribute("id_TipoProveedor"));
      
      String consulta="select p.id_Producto,p.nombre,p.precioCosto,p.precioventa,p.stockminimo,p.stockactual, p.unidad, p.autorizacionpedidoenplaya, p.fk_iva from producto p, tipoproveedor tp, tipoproveedorxproducto tpp where tpp.fk_tipoproveedor="+id_TipoProveedor+" and tpp.fk_Producto=p.id_Producto and tpp.fk_tipoproveedor=tp.id_TipoProveedor";
      
      ResultSet res=daoManager.ejecutarConsulta(consulta);
      
      if(res==null){
          System.out.println("No existen productos para el tipo de proveedor.");
          return null;
       }
       
     while (res.next()){
           Producto producto=new Producto();
            

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
          
          
            lstResultado.insertarOrdenado(producto);
           
      }
     
      

      daoManager.cerrarConexion();
 
      
      
      return lstResultado;
     
     }
    
        
        
        
    
}
