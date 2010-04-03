/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCapaDeDatos.pkgDAOProductoTablas;

import pkgCapaDeDatos.pkgSoporteConexionBD.DAOManager;
import pkgClasesDeNegocio.CuentaCorriente.RepresentanteDeProveedor;
import pkgClasesDeNegocio.Ventas.Telefono;
import pkgRecursosDeSoporte.ValText;
import pkgRecursosDeSoporte.pkgLista.Iterador;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author usuario
 */
public class DAO_TelefonoxRepresentanteProveedor {

     DAOManager daoManager=new DAOManager();
    
public boolean desmaterializarTelefonosxRepresentante_CmdInsert(RepresentanteDeProveedor representante, Lista telefonos) throws Exception{

boolean res=true;
     
this.daoManager.establecerConexion();
       
String consulta="INSERT INTO bdpetrosystem.telefonoxrepresentanteproveedor (fk_RepresentanteDeProveedor, fk_Telefono) VALUES ("+ValText.getInt_Integer(representante.getAttribute("id_RepresentanteDeProveedor"))+",";

Iterador itListaTelefonos = telefonos.crearIterador();

int cantRows= 0;
        
while (itListaTelefonos.siguiente())
{
    Telefono telefono=(Telefono)itListaTelefonos.getElementoActual();
    String consultaTel= ValText.getInt_Integer(telefono.getAttribute("id_Telefono"))+")";   

    cantRows+=daoManager.ejecutarUpdate(consulta+consultaTel);
               
}
         
      if(cantRows==0){
          res=false;
       }
       
     daoManager.cerrarConexion(); 
      
     return res;
     
}
}
