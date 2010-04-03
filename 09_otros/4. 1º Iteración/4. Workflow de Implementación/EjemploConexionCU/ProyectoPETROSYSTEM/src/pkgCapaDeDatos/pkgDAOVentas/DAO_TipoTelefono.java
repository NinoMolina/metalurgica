/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgDAOVentas;

import java.sql.ResultSet;
import pkgCapaDeDatos.pkgSoporteConexionBD.DAOManager;
import pkgClasesDeNegocio.Ventas.TipoTelefono;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author Diego
 */
public class DAO_TipoTelefono {

    DAOManager daoManager=new DAOManager();

    public DAO_TipoTelefono() {
    }
    
    public TipoTelefono materializar(String condicionBusqueda) throws Exception{
       return null;
    }   
    
    public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
      String consulta = "SELECT * FROM bdpetrosystem.tipotelefono " +condicionBusqueda;
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen tipos de teléfonos con la condición de búsuqueda. (Materializar)");
          return null;
       }
       
      while (res.next()){
      
        int id_TipoTelefono=res.getInt(1); 
        String nombre=res.getString(2);
        
        TipoTelefono tipoTelefono=new TipoTelefono();
       
        tipoTelefono.addAttribute("id_TipoTelefono",new Integer(id_TipoTelefono));
        tipoTelefono.setNombre(nombre);
               
        lstResultado.insertarOrdenado(tipoTelefono);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }      
    
}
