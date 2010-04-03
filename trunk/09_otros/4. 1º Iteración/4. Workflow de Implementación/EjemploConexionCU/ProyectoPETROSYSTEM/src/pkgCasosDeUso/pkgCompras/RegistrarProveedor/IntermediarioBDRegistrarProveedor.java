/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCasosDeUso.pkgCompras.RegistrarProveedor;

import pkgCapaDeDatos.pkgDAOAdministracionDePersonal.DAO_Ciudad;
import pkgCapaDeDatos.pkgDAOAdministracionDePersonal.DAO_Domicilio;
import pkgCapaDeDatos.pkgDAOAdministracionDePersonal.DAO_Pais;
import pkgCapaDeDatos.pkgDAOAdministracionDePersonal.DAO_Provincia;
import pkgCapaDeDatos.pkgDAOCuentaCorriente.DAO_Proveedor;
import pkgCapaDeDatos.pkgDAOCuentaCorriente.DAO_TipoProveedor;
import pkgCapaDeDatos.pkgDAOProductoTablas.DAO_TipoProveedorxProducto;
import pkgCapaDeDatos.pkgDAOVentas.DAO_CondicionIva;
import pkgCapaDeDatos.pkgDAOVentas.DAO_Telefono;
import pkgCapaDeDatos.pkgDAOVentas.DAO_TipoTelefono;
import pkgClasesDeNegocio.AdministracionDePersonal.Pais;
import pkgClasesDeNegocio.AdministracionDePersonal.Provincia;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgClasesDeNegocio.CuentaCorriente.TipoProveedor;
import pkgClasesDeNegocio.Ventas.Telefono;
import pkgRecursosDeSoporte.pkgLista.Iterador;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author Armando
 */

public class IntermediarioBDRegistrarProveedor {

    private DAO_Proveedor daoProveedor=new DAO_Proveedor();
    private DAO_Domicilio daoDomicilio=new DAO_Domicilio();
    private DAO_Pais daoPais=new DAO_Pais();
    private DAO_TipoProveedor daoTipoProveedor=new DAO_TipoProveedor();
    private DAO_CondicionIva daoCondicionIva=new DAO_CondicionIva();
    private DAO_Provincia daoProvincia=new DAO_Provincia();
    private DAO_Ciudad daoCiudad=new DAO_Ciudad();
    private DAO_Telefono daoTelefono=new DAO_Telefono(); 
    private DAO_TipoTelefono daoTipoTelefono=new DAO_TipoTelefono();
    private DAO_TipoProveedorxProducto daoTipoProveedorXProducto=new DAO_TipoProveedorxProducto();
    
    public IntermediarioBDRegistrarProveedor() {
    }
    
    public Lista existeNroCUIT(int nroCuit) throws Exception{
     
     String condicion="where cuit="+nroCuit;
        
     Lista proveedor=daoProveedor.materializarTodo(condicion);
     
     return proveedor;
    
    }

    public Lista getPaises() throws Exception {
        
        Lista paises=daoPais.materializarTodo("");
        
        return paises;
        
    }
    
     public Lista getTipoProveedor() throws Exception {
        
        Lista tipoProveedor=daoTipoProveedor.materializarTodo("");
        
        return tipoProveedor;
        
    }

    public Lista getCondicionIva() throws Exception {
       Lista condicionIva=daoCondicionIva.materializarTodo("");
        
        return condicionIva;
  
    }
    
    public Lista getProvincia(Pais pais) throws Exception {
           
        Lista provincia=daoProvincia.materializarProvinciaDeUnPais(pais);
        
        return provincia;
  
    }

 public Lista getCiudad(Provincia provincia) throws Exception {
           
        Lista ciudad=daoCiudad.materializarCiudadDeUnaProvincia(provincia);
        
        return ciudad;
  
    }

    public void crear(Proveedor proveedor) throws Exception {
       daoDomicilio.desmaterializar_CmdInsert(proveedor.getDomicilio());
       
       int idDomicilio=daoDomicilio.getUltimoId();
           
       if(idDomicilio!=-1){
       
           proveedor.getDomicilio().addAttribute("id_Domicilio",new Integer(idDomicilio));
           
       }
       
       daoProveedor.desmaterializar_CmdInsert(proveedor);
       
   Iterador itTelefono=proveedor.getTelefonos().crearIterador();
              
    while(itTelefono.siguiente()){
      
      Telefono tel=(Telefono)itTelefono.getElementoActual();
      
      this.daoTelefono.desmaterializar_CmdInsert(tel);
          
    }
   
   this.registrarTelefonos(proveedor);
    
   this.daoProveedor.desmaterializarTiposProveedor(proveedor);     
}

    public Lista getProductosSegunTipoProveedor(TipoProveedor tipoProveedor) throws Exception {
        Lista lstResultado=new Lista();
        
        
        
        lstResultado=daoTipoProveedorXProducto.materializarProductosPorTipoProveedor(tipoProveedor);
        
        
      
        return lstResultado;
        
        
        
        
    }

    public Lista getTelefonos() throws Exception {
        
        Lista telefonos=daoTipoTelefono.materializarTodo("");
        
        return telefonos;

    }
    
private void registrarTelefonos(Proveedor proveedor) throws Exception{

    int id_Proveedor=daoProveedor.getUltimoId();
    
    if(id_Proveedor!=-1){
     proveedor.addAttribute("id_Proveedor",new Integer(id_Proveedor));
     
     Iterador itTelProv=proveedor.getTelefonos().crearIterador();
     Lista lstResultado=new Lista();
     
     while(itTelProv.siguiente()){
      Telefono t=(Telefono) itTelProv.getElementoActual();
     
      String condicion="where numero="+t.getNumero()+" and caracteristica="+t.getCaracteristica();
       
      Lista telAux=daoTelefono.materializarTodo(condicion);
      Iterador itAux=telAux.crearIterador();
      
      while(itAux.siguiente()){
       Telefono tNuevo=(Telefono) itAux.getElementoActual();
       
       lstResultado.insertarUltimo(tNuevo);
      
      }
      
      
      
         
     }

     proveedor.setTelefonos(lstResultado);
     
     daoProveedor.desmaterializarTelefonos(proveedor);
        
    }
    
    

}
    
    
}
