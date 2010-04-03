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
public class DAO_FacturaProveedor {
    DAOManager daoManager=new DAOManager();
    
    public FacturaProveedor materializar(String condicionBusqueda)  throws Exception{
      FacturaProveedor factura=null;
      
      this.daoManager.establecerConexion();
      
     
       //en la condicion de busqueda de la consulta se de be empezar con and y no con where
       String consulta = "SELECT * from bdpetrosystem.facturaproveedor "+ condicionBusqueda;
       
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen Factura Proveedor con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
      while (res.next()){
          
            int id_FacturaProveedor=res.getInt(1);
            int numero=res.getInt(2);
            Date fecha=res.getDate(3);
            double monto=res.getDouble(4);
            int fk_TipoFactura=res.getInt(5);
            int fk_PedidoAProveedor=res.getInt(6);
 
         
            factura=new FacturaProveedor();
       
            factura.addAttribute("id_FacturaProveedor",new Integer(id_FacturaProveedor));
            factura.setNumero(numero);
            factura.setFecha(fecha);
            factura.setMontoTotal(monto);
            
            factura.addAttribute("fk_TipoFactura",new Integer(fk_TipoFactura));
            factura.addAttribute("fk_PedidoAProveedor", new Integer(fk_PedidoAProveedor));
            
      }

      daoManager.cerrarConexion();
      
      return factura;
      
    }
    
public boolean desmaterializar_CmdInsert(FacturaProveedor facturaProveedor) throws Exception{
      boolean res=true;
     
      this.daoManager.establecerConexion();
      
      
      
      String consulta="INSERT INTO bdpetrosystem.facturaproveedor ( numero, fecha,montototal, fk_tipofactura, fk_PedidoAProveedor) VALUES ("+facturaProveedor.getNumero()+",'"+ValText.getFormatStringDate(facturaProveedor.getFecha())+"',"+facturaProveedor.getMontoTotal()+","+ValText.getInt_Integer(facturaProveedor.getAttribute("fk_TipoFactura"))+","+ValText.getInt_Integer(facturaProveedor.getAttribute("fk_PedidoAProveedor"))+")";
      
    
    
      int cantRows=daoManager.ejecutarUpdate(consulta);
       
      if(cantRows==0){
         
          res=false;
       }
       
     return res;
   }

public int getUltimoId() throws Exception{
      
        daoManager.establecerConexion();
        
        int valor=-1;
        String consulta="SELECT max(d.id_facturaproveedor) FROM facturaproveedor d";
          
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
