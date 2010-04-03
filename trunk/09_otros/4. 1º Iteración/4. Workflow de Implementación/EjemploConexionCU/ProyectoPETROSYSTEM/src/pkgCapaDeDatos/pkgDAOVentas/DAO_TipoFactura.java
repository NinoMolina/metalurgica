/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgDAOVentas;

import pkgCapaDeDatos.pkgSoporteConexionBD.DAOManager;
import java.sql.ResultSet;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgClasesDeNegocio.CuentaCorriente.TipoProveedor;
import pkgClasesDeNegocio.Ventas.CondicionIva;
import pkgClasesDeNegocio.Ventas.Telefono;
import pkgClasesDeNegocio.Ventas.TipoFactura;
import pkgRecursosDeSoporte.pkgLista.*;
import pkgRecursosDeSoporte.ValText;

/**
 *
 * @author Armando
 */
public class DAO_TipoFactura {

    DAOManager daoManager=new DAOManager();

    public DAO_TipoFactura() {}
    
    public TipoFactura materializar(String condicionBusqueda) throws Exception{
       return null;
    }   
    
    public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
      String consulta = "SELECT * FROM bdpetrosystem.tipofactura " +condicionBusqueda;
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen tipos de factura con la condición de búsuqueda. (Materializar)");
          return null;
       }
       
      while (res.next()){
      
        int id_TipoFactura=res.getInt(1); 
        String nombre=res.getString(2);
       
        
        
        
        
        TipoFactura tipoFactura=new TipoFactura();
        tipoFactura.addAttribute("id_TipoFactura",new Integer(id_TipoFactura));
        tipoFactura.setNombre(nombre);
               
        lstResultado.insertarOrdenado(tipoFactura);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }   
    
  
    
}
