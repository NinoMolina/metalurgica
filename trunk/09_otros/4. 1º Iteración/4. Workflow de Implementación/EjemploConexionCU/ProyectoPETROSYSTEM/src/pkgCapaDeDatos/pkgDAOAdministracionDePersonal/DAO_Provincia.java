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
public class DAO_Provincia {

    DAOManager daoManager=new DAOManager();

    public DAO_Provincia() {}
    
    public Provincia materializar(String condicionBusqueda) throws Exception{
       return null;
    }   
    
    public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
      String consulta = "SELECT * FROM bdpetrosystem.provincia " +condicionBusqueda;
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen ciudades con la condición de búsuqueda. (Materializar)");
          return null;
       }
       
      while (res.next()){
      
        int id_Provincia=res.getInt(1); 
        String nombre=res.getString(2);
        int fk_Pais=res.getInt(3);
             
       Provincia provincia=new Provincia();
       
       provincia.addAttribute("id_Provincia",new Integer(id_Provincia));
       provincia.setNombre(nombre);
       provincia.addAttribute("fk_Pais",new Integer(fk_Pais));
       
       lstResultado.insertarOrdenado(provincia);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }   
    
    public Lista materializarProvinciaDeUnPais(Pais pais)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
      String consulta = "SELECT pro.Id_Provincia, pro.nombre FROM bdpetrosystem.provincia pro where pro.fk_Pais=" + ValText.getInt_Integer(pais.getAttribute("id_Pais"));
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen Provincias de ese pais. (Materializar)");
          return null;
       }
       
      while (res.next()){
      
        int id_Provincia=res.getInt(1); 
        String nombre=res.getString(2);
        //int fk_Pais=res.getInt(3);
             
       Provincia provincia=new Provincia();
       
       provincia.addAttribute("id_Provincia",new Integer(id_Provincia));
       provincia.setNombre(nombre);
       provincia.addAttribute("fk_Pais",new Integer(ValText.getInt_Integer(pais.getAttribute("id_Pais"))));
       
       lstResultado.insertarOrdenado(provincia);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }  
    
}
