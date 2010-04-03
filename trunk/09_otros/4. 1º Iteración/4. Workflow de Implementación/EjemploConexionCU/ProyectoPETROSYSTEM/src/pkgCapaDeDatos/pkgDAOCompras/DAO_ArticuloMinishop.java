/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgDAOCompras;
import pkgCapaDeDatos.pkgSoporteConexionBD.DAOManager;
import java.sql.ResultSet;
import pkgClasesDeNegocio.Compras.ArticuloMinishop;
import pkgClasesDeNegocio.Compras.Iva;
import pkgClasesDeNegocio.Compras.TipoProducto;
import pkgRecursosDeSoporte.pkgLista.*;
import pkgRecursosDeSoporte.ValText;
/**
/**
 *
 * @author Fer
 */
public class DAO_ArticuloMinishop {
    
    DAOManager daoManager=new DAOManager();
    
     public DAO_ArticuloMinishop() {}
     
     
 public ArticuloMinishop materializar(String condicionBusqueda) throws Exception{
     ArticuloMinishop articuloMinishop=null;  
   
     this.daoManager.establecerConexion();
        
        int id_ArticuloMinishop;
         String nombre=new String();
         double precioCosto;
         float precioVenta;
         double stockMinimo;
         double stockActual;
         String unidad=new String();
         boolean autorizacionPedidoEnPlaya;
         //Iva iva=new Iva();
         int fk_iva;
         double numeroCodigoBarra;
         //TipoProducto tipoProducto=new TipoProducto();
         int fk_TipoProducto;
         int fk_Producto;
       
         condicionBusqueda=this.getSinWhere(condicionBusqueda);
       //en la condicion de busqueda de la consulta se de be empezar con and y no con where
       String consulta = "SELECT p.id_articulominishop,p.nombre,p.preciocosto,p.precioventa,p.stockminimo,p.stockactual,p.unidad,p.autorizacionpedidoenplaya,p.fk_iva,a.numerocodigobarra,a.fk_tipoproducto,a.fkproducto   FROM bdpetrosystem.articulominishop a, bdpetrosystem.producto p where p.id_producto=a.fk_producto "+(condicionBusqueda == "" ? "" : " and " + condicionBusqueda) +condicionBusqueda;
    
       ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen proveedores con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
     while (res.next()){
      id_ArticuloMinishop=res.getInt(1);
      nombre=res.getString(2);
     precioCosto =res.getDouble(3);
      precioVenta=res.getFloat(4);
      stockMinimo=res.getDouble(5);
     stockActual =res.getDouble(6);
     unidad=res.getString(7);
     autorizacionPedidoEnPlaya=res.getBoolean(8);
     fk_iva=res.getInt(9);
     numeroCodigoBarra=res.getDouble(10);
     fk_TipoProducto=res.getInt(11);
     fk_Producto=res.getInt(12);
     
   articuloMinishop=new ArticuloMinishop();
   
   articuloMinishop.addAttribute("id_ArticuloMinishop",new Integer(id_ArticuloMinishop));
   articuloMinishop.setNombre(nombre);
   articuloMinishop.setPrecioCosto(precioCosto);
   articuloMinishop.setPrecioVenta(precioVenta);

   articuloMinishop.setStockMinimo(stockMinimo);
   articuloMinishop.setStockActual(stockActual);
   articuloMinishop.setUnidad(unidad);
   articuloMinishop.setAutorizacionPedidoEnPlaya(autorizacionPedidoEnPlaya);
   articuloMinishop.addAttribute("fk_Iva", new Integer(fk_iva));
   articuloMinishop.setNumeroCodigoBarra(numeroCodigoBarra);
   articuloMinishop.addAttribute("fk_TipoProducto", new Integer(fk_TipoProducto));
   articuloMinishop.addAttribute("fk_Producto", new Integer(fk_Producto));        
      }
   
      daoManager.cerrarConexion();

      return articuloMinishop;
    }   
    
    public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
       condicionBusqueda=this.getSinWhere(condicionBusqueda);
       //en la condicion de busqueda de la consulta se de be empezar con and y no con where
       String consulta = "SELECT p.id_articulominishop,p.nombre,p.preciocosto,p.precioventa,p.stockminimo,p.stockactual,p.unidad,p.autorizacionpedidoenplaya,p.fk_iva,a.numerocodigobarra,a.fk_tipoproducto,a.fkproducto   FROM bdpetrosystem.articulominishop a, bdpetrosystem.producto p where p.id_producto=a.fk_producto "+(condicionBusqueda == "" ? "" : " and " + condicionBusqueda) +condicionBusqueda;
    
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen proveedores con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
      while (res.next()){
      
           int id_ArticuloMinishop;
         String nombre=new String();
         double precioCosto;
         float precioVenta;
         double stockMinimo;
         double stockActual;
         String unidad=new String();
         boolean autorizacionPedidoEnPlaya;
         //Iva iva=new Iva();
         int fk_iva;
         double numeroCodigoBarra;
         //TipoProducto tipoProducto=new TipoProducto();
         int fk_TipoProducto;
         int fk_Producto;
              
        id_ArticuloMinishop=res.getInt(1);
      nombre=res.getString(2);
     precioCosto =res.getDouble(3);
      precioVenta=res.getFloat(4);
      stockMinimo=res.getDouble(5);
     stockActual =res.getDouble(6);
     unidad=res.getString(7);
     autorizacionPedidoEnPlaya=res.getBoolean(8);
     fk_iva=res.getInt(9);
     numeroCodigoBarra=res.getDouble(10);
     fk_TipoProducto=res.getInt(11);
     fk_Producto=res.getInt(12);
     
    ArticuloMinishop articuloMinishop=new ArticuloMinishop();
   
   articuloMinishop.addAttribute("id_ArticuloMinishop",id_ArticuloMinishop);
   articuloMinishop.setNombre(nombre);
   articuloMinishop.setPrecioCosto(precioCosto);
   articuloMinishop.setPrecioVenta(precioVenta);

   articuloMinishop.setStockMinimo(stockMinimo);
   articuloMinishop.setStockActual(stockActual);
   articuloMinishop.setUnidad(unidad);
   articuloMinishop.setAutorizacionPedidoEnPlaya(autorizacionPedidoEnPlaya);
   articuloMinishop.addAttribute("fk_Iva", new Integer(fk_iva));
   articuloMinishop.setNumeroCodigoBarra(numeroCodigoBarra);
   articuloMinishop.addAttribute("fk_TipoProducto", new Integer(fk_TipoProducto));
   articuloMinishop.addAttribute("fk_Producto", new Integer(fk_Producto));       
       lstResultado.insertarOrdenado(articuloMinishop);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }   
    
   public boolean desmaterializar_CmdInsert(ArticuloMinishop articuloMinishop) throws Exception{
      boolean res=true;
     
      this.daoManager.establecerConexion();
      
      
      
      String consulta="INSERT INTO bdpetrosystem.articulominishop";
      
    
    
      int cantRows=daoManager.ejecutarUpdate(consulta);
       
      if(cantRows==0){
         
          res=false;
       }
       
     return res;
   }
   
   public boolean cmdUpdate(ArticuloMinishop articuloMinishop,String condicionUpdate) throws Exception{
      boolean res=true;
     
      this.daoManager.establecerConexion();
      
      String consulta="UPDATE bdpetrosystem."+condicionUpdate;
 
      int cantRows=daoManager.ejecutarUpdate(consulta);
       
      if(cantRows==0){
         
          res=false;
       }
       
     return res;
   }  

  
   
   
   
   
   public Lista materializarTelefonos(ArticuloMinishop articuloMinishop) throws Exception{
      Lista lstTelefonos=new Lista();   
         
      daoManager.addNuevaConexion("conProvXTel", null);
      daoManager.seleccionarConexion("conProvXTel");
      daoManager.establecerConexion();
      
      String consultaProvXTel= "SELECT id_Telefono FROM bdpetrosystem.ProveedorXTelefono where fk_Proveedor= "+ValText.getInt_Integer(articuloMinishop.getAttribute("id_Proveedor"));
      
      ResultSet res=daoManager.ejecutarConsulta(consultaProvXTel);
      
      
      
   
      return lstTelefonos;
   }
   
    private String getSinWhere(String cad){
       
       cad=cad.trim();
       char valores[]=cad.toCharArray();
       
       valores[0]=' ';
       valores[1]=' ';
       valores[2]=' ';
       valores[3]=' ';
       valores[4]=' ';
       
       String sinWhere=String.valueOf(valores);
       
       
       

       return sinWhere.trim();
   }

   
   
}
