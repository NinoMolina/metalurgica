/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgDAOAdministracionDePersonal;

import pkgCapaDeDatos.pkgSoporteConexionBD.DAOManager;
import java.sql.ResultSet;
import pkgClasesDeNegocio.AdministracionDePersonal.Domicilio;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgClasesDeNegocio.CuentaCorriente.TipoProveedor;
import pkgClasesDeNegocio.Ventas.Telefono;
import pkgRecursosDeSoporte.pkgLista.*;
import pkgRecursosDeSoporte.ValText;

/**
 *
 * @author Armando
 */
public class DAO_Domicilio {

    DAOManager daoManager=new DAOManager();

    public DAO_Domicilio() {}
    
    public Domicilio materializar(String condicionBusqueda) throws Exception{
      Domicilio domicilio=null;
      
      this.daoManager.establecerConexion();
      
      String consulta = "SELECT * FROM bdpetrosystem.domicilio " +condicionBusqueda;
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen domicilios con la condición de búsuqueda. (Materializar)");
          return null;
       }
       
      while (res.next()){
      
        int id_Domicilio=res.getInt(1); 
        String nombreCalle=res.getString(2);
        int nroCalle=res.getInt(3);
        String departamento=res.getString(4);
        int nroPiso=res.getInt(5);
        String barrio=res.getString(6);
        int fk_Ciudad=res.getInt(7);
        int fk_Provincia=res.getInt(8);
       
        domicilio=new Domicilio();
        
        domicilio.addAttribute("id_Domicilio",new Integer(id_Domicilio));
        domicilio.setNombreCalle(nombreCalle);
        domicilio.setNroCalle(nroCalle);
        domicilio.setDepartamento(departamento);
        domicilio.setNroPiso(nroPiso);
        domicilio.setBarrio(barrio);
        domicilio.addAttribute("fk_Ciudad",new Integer(fk_Ciudad));
        domicilio.addAttribute("fk_Provincia",new Integer(fk_Provincia));
        
      }

      daoManager.cerrarConexion();
      
      return domicilio;
    }   
    
    public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
      String consulta = "SELECT * FROM bdpetrosystem.domicilio " +condicionBusqueda;
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen domicilios con la condición de búsuqueda. (Materializar)");
          return null;
       }
       
      while (res.next()){
      
        int id_Domicilio=res.getInt(1); 
        String nombreCalle=res.getString(2);
        int nroCalle=res.getInt(3);
        String departamento=res.getString(4);
        int nroPiso=res.getInt(5);
        String barrio=res.getString(6);
        int fk_Ciudad=res.getInt(7);
        int fk_Provincia=res.getInt(8);
       
        Domicilio domicilio=new Domicilio();
        
        domicilio.addAttribute("id_Domicilio",new Integer(id_Domicilio));
        domicilio.setNombreCalle(nombreCalle);
        domicilio.setNroCalle(nroCalle);
        domicilio.setDepartamento(departamento);
        domicilio.setNroPiso(nroPiso);
        domicilio.setBarrio(barrio);
        domicilio.addAttribute("fk_Ciudad",new Integer(fk_Ciudad));
        domicilio.addAttribute("fk_Provincia",new Integer(fk_Provincia));
        
        
        
        lstResultado.insertarOrdenado(domicilio);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }   
    
   public boolean desmaterializar_CmdInsert(Domicilio domicilio) throws Exception{
      boolean res=true;
     
      this.daoManager.establecerConexion();
      
      
      
     String consulta="INSERT INTO bdpetrosystem.domicilio (nombreCalle,nroCalle,departamento,nroPiso,barrio,fk_Ciudad,fk_Provincia) VALUES ('"+
                       domicilio.getNombreCalle()+"',"+domicilio.getNroCalle()+",'"+domicilio.getDepartamento()+"',"+domicilio.getNroPiso()+
                       ",'"+domicilio.getBarrio()+"',"+ValText.getInt_Integer(domicilio.getCiudad().getAttribute("id_Ciudad"))+","+ValText.getInt_Integer(domicilio.getProvincia().getAttribute("id_Provincia"))+")"; 
      
       
        
      
      
      
      int cantRows=daoManager.ejecutarUpdate(consulta);
       
      if(cantRows==0){
         
          res=false;
       }
       
     daoManager.cerrarConexion(); 
      
     return res;
   }
   
    
   public int getUltimoId() throws Exception{
      
        daoManager.establecerConexion();
        
        int valor=-1;
        String consulta="SELECT max(d.id_Domicilio) FROM domicilio d";
          
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
