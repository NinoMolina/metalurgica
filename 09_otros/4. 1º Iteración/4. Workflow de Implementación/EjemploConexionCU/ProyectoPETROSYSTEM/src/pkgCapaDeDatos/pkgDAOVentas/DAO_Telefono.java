/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgDAOVentas;

import pkgCapaDeDatos.pkgSoporteConexionBD.DAOManager;
import java.sql.ResultSet;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgClasesDeNegocio.CuentaCorriente.TipoProveedor;
import pkgClasesDeNegocio.Ventas.Telefono;
import pkgRecursosDeSoporte.pkgLista.*;
import pkgRecursosDeSoporte.ValText;
/**
 *
 * @author Armando
 */
public class DAO_Telefono {
 DAOManager daoManager=new DAOManager();

    public DAO_Telefono() {}
    
    public Telefono materializar(String condicionBusqueda) throws Exception{
       return null;
    }   
    
    public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
      String consulta = "SELECT * FROM bdpetrosystem.telefono " +condicionBusqueda;
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen telefonos con la condición de búsuqueda. (Materializar)");
          return null;
       }
       
      while (res.next()){
      
        int id_Telefono=res.getInt(1); 
        int numero=res.getInt(2);
        int caracteristica=res.getInt(3);
        int fk_TipoTelefono=res.getInt(4);
             
       Telefono telefono=new Telefono();
       
       telefono.addAttribute("id_Telefono",new Integer(id_Telefono));
       telefono.setNumero(numero);
       telefono.setCaracteristica(caracteristica);
        telefono.addAttribute("fk_TipoTelefono", new Integer(fk_TipoTelefono));
       
       lstResultado.insertarOrdenado(telefono);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }   
    
   public boolean desmaterializar_CmdInsert(Telefono telefono) throws Exception{
      boolean res=true;
     
      this.daoManager.establecerConexion();
      
      
      
       String consulta="INSERT INTO bdpetrosystem.telefono (numero,caracteristica,fk_TipoTelefono) VALUES ("+telefono.getNumero()+","+telefono.getCaracteristica()+","+ValText.getInt_Integer(telefono.getTipoTelefono().getAttribute("id_TipoTelefono"))+")";
      
      int cantRows=daoManager.ejecutarUpdate(consulta);
       
      if(cantRows==0){
         
          res=false;
       }
       
     daoManager.cerrarConexion(); 
      
     return res;
   }
   
   public boolean cmdUpdate(Telefono telefono,String condicionUpdate) throws Exception{
     
     return false;
   }  

    public int getUltimoId() throws Exception{
      
        daoManager.establecerConexion();
        
        int valor=-1;
        String consulta="SELECT max(p.id_Telefono) FROM telefono p";
          
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
