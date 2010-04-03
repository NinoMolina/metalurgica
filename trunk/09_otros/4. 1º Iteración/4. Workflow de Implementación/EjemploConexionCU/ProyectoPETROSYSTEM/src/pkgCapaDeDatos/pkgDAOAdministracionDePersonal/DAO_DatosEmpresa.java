/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgDAOAdministracionDePersonal;

import java.sql.ResultSet;
import pkgCapaDeDatos.pkgSoporteConexionBD.DAOManager;
import pkgClasesDeNegocio.AdministracionDePersonal.DatosEmpresa;
import pkgClasesDeNegocio.Compras.AmbitoEstado;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author Armando
 */
public class DAO_DatosEmpresa {

    DAOManager daoManager=new DAOManager();
    
    public DatosEmpresa materializar(String condicionBusqueda) throws Exception{
     DatosEmpresa datosEmpresa=null;  
   
     this.daoManager.establecerConexion();
        
        long cuit;
        String razonSocial=new String();
        int fk_Domicilio;
        
        
        
      
       String consulta = "SELECT * FROM bdpetrosystem.datosempresa " +condicionBusqueda;
    
       ResultSet res=daoManager.ejecutarConsulta(consulta);
       
      if(res==null){
          System.out.println("No existen datos de la empresa con la condición de búsuqueda. (Materalizar)");
          return null;
       }
       
     while (res.next()){
      
        double cuitAux=res.getDouble(1);
        cuit=(long)cuitAux;
        razonSocial=res.getString(2); 
        fk_Domicilio=res.getInt(3);
        
        
              
       datosEmpresa=new DatosEmpresa();
       
       datosEmpresa.addAttribute("id_DatosEmpresa",new Double(cuit));
       datosEmpresa.setRazonSocial(razonSocial);
       datosEmpresa.setCuit(cuit);
       
       datosEmpresa.addAttribute("fk_Domicilio", new Integer(fk_Domicilio));
       
      }
   
      daoManager.cerrarConexion();

      return datosEmpresa;
    }   
    
    
}
