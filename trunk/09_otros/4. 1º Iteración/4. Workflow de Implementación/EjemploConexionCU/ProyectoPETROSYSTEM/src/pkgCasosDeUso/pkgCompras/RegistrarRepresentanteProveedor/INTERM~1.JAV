/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCasosDeUso.pkgCompras.RegistrarRepresentanteProveedor;

import pkgCapaDeDatos.pkgDAOCuentaCorriente.DAO_Proveedor;
import pkgCapaDeDatos.pkgDAOCuentaCorriente.DAO_RepresentanteDeProveedor;
import pkgCapaDeDatos.pkgDAOProductoTablas.DAO_TelefonoxRepresentanteProveedor;
import pkgCapaDeDatos.pkgDAOVentas.DAO_Telefono;
import pkgCapaDeDatos.pkgDAOVentas.DAO_TipoTelefono;
import pkgClasesDeNegocio.CuentaCorriente.RepresentanteDeProveedor;
import pkgClasesDeNegocio.Ventas.Telefono;
import pkgRecursosDeSoporte.pkgLista.Iterador;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author Diego
 */
public class IntermediarioBDRegistrarRepresentanteProveedor {

 private DAO_RepresentanteDeProveedor daoRepresentanteDeProveedor=new DAO_RepresentanteDeProveedor();
 private DAO_TipoTelefono daoTipoTelefono=new DAO_TipoTelefono();
 private DAO_Telefono daoTelefono=new DAO_Telefono(); 
 private DAO_Proveedor daoProveedor=new DAO_Proveedor();
 private DAO_TelefonoxRepresentanteProveedor daoTelefonoxRepresentanteProveedor=new DAO_TelefonoxRepresentanteProveedor();
    
    
public Lista getTelefonos() throws Exception {
        
        Lista telefonos=daoTipoTelefono.materializarTodo("");
        
        return telefonos;
        
    }

   public void crear(RepresentanteDeProveedor representanteDeProveedor) throws Exception {
 
   daoRepresentanteDeProveedor.desmaterializar_CmdInsert(representanteDeProveedor);
   
   representanteDeProveedor.addAttribute("id_RepresentanteDeProveedor",new Integer(daoRepresentanteDeProveedor.getUltimoId()));
   
   Iterador itTelefono=representanteDeProveedor.getTelefonos().crearIterador();
              
   while(itTelefono.siguiente()){
      
    Telefono tel=(Telefono)itTelefono.getElementoActual();
    
    this.daoTelefono.desmaterializar_CmdInsert(tel);
    
    tel.addAttribute("id_Telefono", new Integer(daoTelefono.getUltimoId()));
    
   }
      
   daoTelefonoxRepresentanteProveedor.desmaterializarTelefonosxRepresentante_CmdInsert(representanteDeProveedor, representanteDeProveedor.getTelefonos());
           
   }

    public Lista getProveedores() throws Exception{
    Lista proveedores=daoProveedor.materializarTodo("");
        
        return proveedores;
    }
    
    public int getIdUltimoRegistro() throws Exception{
     return daoRepresentanteDeProveedor.getUltimoId();
    }

}
