/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgDAOAdministracionDePersonal;

import pkgCapaDeDatos.pkgSoporteConexionBD.DAOManager;
import java.sql.ResultSet;
import pkgClasesDeNegocio.AdministracionDePersonal.Ciudad;
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
public class DAO_Ciudad {

    DAOManager daoManager=new DAOManager();

    public DAO_Ciudad() {}
    
    public Ciudad materializar(String condicionBusqueda) throws Exception{
     Ciudad ciudad=null;
      
      this.daoManager.establecerConexion();
      
      String consulta = "SELECT * FROM bdpetrosystem.ciudad " +condicionBusqueda;
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen ciudades con la condición de búsuqueda. (Materializar)");
          return null;
       }
       
      while (res.next()){
      
        int id_Ciudad=res.getInt(1); 
        String nombre=res.getString(2);
        int fk_Provincia=res.getInt(3);
             
       ciudad=new Ciudad();
       
       ciudad.addAttribute("id_Ciudad",new Integer(id_Ciudad));
       ciudad.setNombre(nombre);
       ciudad.addAttribute("fk_Provincia",new Integer(fk_Provincia));
       
       
           
      }

      daoManager.cerrarConexion();
      
      return ciudad;
    }   
    
    public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
      String consulta = "SELECT * FROM bdpetrosystem.ciudad " +condicionBusqueda;
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen ciudades con la condición de búsuqueda. (Materializar)");
          return null;
       }
       
      while (res.next()){
      
        int id_Ciudad=res.getInt(1); 
        String nombre=res.getString(2);
        int fk_Provincia=res.getInt(3);
             
       Ciudad ciudad=new Ciudad();
       
       ciudad.addAttribute("id_Ciudad",new Integer(id_Ciudad));
       ciudad.setNombre(nombre);
       ciudad.addAttribute("fk_Provincia",new Integer(fk_Provincia));
       
       lstResultado.insertarOrdenado(ciudad);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }   
    
    public Lista materializarCiudadDeUnaProvincia(Provincia provincia) throws Exception {
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
      String consulta = "SELECT ci.Id_Ciudad, ci.nombre FROM bdpetrosystem.ciudad ci where ci.fk_Provincia=" + ValText.getInt_Integer(provincia.getAttribute("id_Provincia"));
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen Ciudades de esa provincia. (Materializar)");
          return null;
       }
       
      while (res.next()){
      
        int id_Ciudad=res.getInt(1); 
        String nombre=res.getString(2);
        //int fk_Pais=res.getInt(3);
             
       Ciudad ciudad=new Ciudad();
       
       ciudad.addAttribute("id_Ciudad",new Integer(id_Ciudad));
       ciudad.setNombre(nombre);
       ciudad.addAttribute("fk_Provincia",new Integer(ValText.getInt_Integer(provincia.getAttribute("id_Provincia"))));
       
       lstResultado.insertarOrdenado(ciudad);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
    }
    
}
