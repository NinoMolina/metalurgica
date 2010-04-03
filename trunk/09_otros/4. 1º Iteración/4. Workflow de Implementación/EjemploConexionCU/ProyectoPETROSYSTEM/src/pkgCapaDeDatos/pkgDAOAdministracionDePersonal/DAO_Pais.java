/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgDAOAdministracionDePersonal;

import pkgCapaDeDatos.pkgSoporteConexionBD.DAOManager;
import java.sql.ResultSet;
import pkgClasesDeNegocio.AdministracionDePersonal.Ciudad;
import pkgClasesDeNegocio.AdministracionDePersonal.Pais;
import pkgClasesDeNegocio.AdministracionDePersonal.Provincia;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgClasesDeNegocio.CuentaCorriente.TipoProveedor;
import pkgClasesDeNegocio.Ventas.Telefono;
import pkgRecursosDeSoporte.pkgLista.*;
import pkgRecursosDeSoporte.ValText;


/**
 *
 * @author Armando
 */
public class DAO_Pais {

      DAOManager daoManager=new DAOManager();

    public DAO_Pais() {}
    
    public Provincia materializar(String condicionBusqueda) throws Exception{
       return null;
    }   
    
    public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
      String consulta = "SELECT * FROM bdpetrosystem.pais " +condicionBusqueda;
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen ciudades con la condición de búsuqueda. (Materializar)");
          return null;
       }
       
      while (res.next()){
      
        int id_Pais=res.getInt(1); 
        String nombre=res.getString(2);
        
             
       Pais pais=new Pais();
       
       pais.addAttribute("id_Pais",new Integer(id_Pais));
       pais.setNombre(nombre);
       
       lstResultado.insertarOrdenado(pais);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }   
    
}
