/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgDAOCuentaCorriente;


import pkgCapaDeDatos.pkgSoporteConexionBD.DAOManager;
import java.sql.ResultSet;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgClasesDeNegocio.CuentaCorriente.RepresentanteDeProveedor;
import pkgClasesDeNegocio.CuentaCorriente.TipoProveedor;
import pkgClasesDeNegocio.Ventas.Telefono;
import pkgRecursosDeSoporte.pkgLista.*;
import pkgRecursosDeSoporte.ValText;
/**
 *
 * @author Armando
 */
public class DAO_RepresentanteDeProveedor {

     DAOManager daoManager=new DAOManager();

    public DAO_RepresentanteDeProveedor() {}
    
    public RepresentanteDeProveedor materializar(String condicionBusqueda) throws Exception{
      
      RepresentanteDeProveedor representante=new RepresentanteDeProveedor();
      
      this.daoManager.establecerConexion();
      
      String consulta = "SELECT * FROM bdpetrosystem.representantedeproveedor " +condicionBusqueda;
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen representantes de proveedor con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
      while (res.next()){
      
        int id_RepresentanteDeProveedor=res.getInt(1);
        String nombre=res.getString(2); 
        String apellido=res.getString(3);
       
        int fk_Proveedor=res.getInt(4);
   
       representante.addAttribute("id_RepresentanteDeProveedor",new Integer(id_RepresentanteDeProveedor));
       representante.setNombre(nombre);
       representante.setApellido(apellido);
       representante.addAttribute("fk_Proveedor",new Integer(fk_Proveedor));
       
       
      }

      daoManager.cerrarConexion();
      
      return representante;
      
    }   
    
    public Lista materializarTodo(String condicionBusqueda)  throws Exception{
      Lista lstResultado=new Lista();
      
      this.daoManager.establecerConexion();
      
      String consulta = "SELECT * FROM bdpetrosystem.representantedeproveedor " +condicionBusqueda;
    
      ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen representantes de proveedor con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
      while (res.next()){
      
        int id_RepresentanteDeProveedor=res.getInt(1);
        String nombre=res.getString(2); 
        String apellido=res.getString(3);
       
        int fk_Proveedor=res.getInt(4);
        
        
              
       RepresentanteDeProveedor representante=new RepresentanteDeProveedor();
       representante.addAttribute("id_RepresentanteDeProveedor",new Integer(id_RepresentanteDeProveedor));
       representante.setNombre(nombre);
       representante.setApellido(apellido);
       representante.addAttribute("fk_Proveedor",new Integer(fk_Proveedor));
       
       lstResultado.insertarOrdenado(representante);
           
      }

      daoManager.cerrarConexion();
      
      return lstResultado;
      
    }   
    
   public boolean desmaterializar_CmdInsert(RepresentanteDeProveedor representante) throws Exception{
      boolean res=true;
     
      this.daoManager.establecerConexion();
      
     
      String consulta="INSERT INTO bdpetrosystem.representantedeproveedor (nombre,apellido,fk_Proveedor) VALUES ('"+representante.getNombre()+"','"+
                             representante.getApellido()+"',"+ValText.getInt_Integer(representante.getAttribute("fk_Proveedor"))+")";
      
    
    
      int cantRows=daoManager.ejecutarUpdate(consulta);
       
      if(cantRows==0){
         
          res=false;
       }
       
     daoManager.cerrarConexion();
      
     return res;
   }
   
   public boolean cmdUpdate(RepresentanteDeProveedor representante,String condicionUpdate) throws Exception{
     return false;
   }  

   public Lista materializarTelefonos(RepresentanteDeProveedor representante) throws Exception{
      Lista lstTelefonos=new Lista();   
         
      daoManager.addNuevaConexion("conRepreXTel", null);
      daoManager.seleccionarConexion("conRepreXTel");
      daoManager.establecerConexion();
      
      String consultaRepreXTel= "SELECT max(fk_Telefono) idMax,min(fk_Telefono) idMin FROM bdpetrosystem.TelefonoXRepresentanteProveedor where fk_RepresentanteDeProveedor= "+ValText.getInt_Integer(representante.getAttribute("id_RepresentanteDeProveedor"));
      
      ResultSet res=daoManager.ejecutarConsulta(consultaRepreXTel);
      
      int idMin=-1;
      int idMax=-1;
      
      while(res.next()){
       
          idMax=res.getInt("idMax");
          idMin=res.getInt("idMin"); 
      }
      
      if(idMin!=-1 && idMax!=-1){
       
          daoManager.seleccionarConexion(daoManager.Connection_Default);
          daoManager.establecerConexion();
          
          //numero,caracteristica
          String consultaTelefProv="SELECT * FROM bdpetrosystem.telefono where id_Telefono>="+idMin+" AND "+"id_Telefono<="+idMax;
          
          ResultSet resTelefonos=daoManager.ejecutarConsulta(consultaTelefProv);
          
          while(resTelefonos.next()){
           
             int id_Telefono=resTelefonos.getInt(1);
             int numero=resTelefonos.getInt(2);
             int caracteristica=resTelefonos.getInt(3);
             
             Telefono telefono=new Telefono();
             telefono.addAttribute("id_Telefono",new Integer(id_Telefono));
             telefono.setNumero(numero);
             telefono.setCaracteristica(caracteristica);
             
             lstTelefonos.insertarOrdenado(telefono);
          }
          
         
      
      }
    
      daoManager.cerrarTodasLasConexiones();
      
      return lstTelefonos;
   }
   
   
    public int getUltimoId() throws Exception{
      
        daoManager.establecerConexion();
        
        int valor=-1;
        String consulta="SELECT max(p.id_RepresentanteDeProveedor) FROM representantedeproveedor p";
          
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
      
   
   
   /*public boolean cmdDelete(Proveedor proveedor){
     boolean res=false;    
     return res;
   }*/ 
    
    
    
    
    
}
