/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCasosDeUso.pkgCompras.RegistrarRepresentanteProveedor;

import pkgCasosDeUso.pkgSoporteConexionCU.Conexion;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgClasesDeNegocio.CuentaCorriente.RepresentanteDeProveedor;

/**
 *
 * @author Armando
 */
public class MediadorCURegistrarRepresentanteProveedor {

    private GestorRegistrarRepresentanteProveedor gestor;
    private Conexion conexionCliente;

   
    public void ejecutarCU(){
     
     /*Cuando se llame a este método, es porque lo llama otro CU, por lo tanto no se ejcuta
       antes el método setGestor, luego se ejecuta el setGestor, pero el parámetro recibido
       es el mismo gestor creado aqui, pero con sus atributos inicializados, mediante el llamado
      del método al gestor, desde la plla, nuevoRepresentanteProveedor()  
        */   
     if(gestor==null)gestor= new GestorRegistrarRepresentanteProveedor();
     
        
     PllaRegistrarRepresentanteProveedor plla=new PllaRegistrarRepresentanteProveedor();
     gestor.setMediadorCU(this);   
     
     plla.setGestor(gestor);
     
     plla.opcionNuevoRepresentanteProveedor();
     
    }
    
    public void setConexionCliente(Conexion c){
     this.conexionCliente=c;
    }

    public void setGestor(GestorRegistrarRepresentanteProveedor gestor) {
        this.gestor = gestor;
    }
    
    public void setProveedorDeRepresentantes(Proveedor p){
      if(gestor==null)gestor= new GestorRegistrarRepresentanteProveedor();
      
      gestor.setProveedorSeleccionado(p);
    }
    
    public void nuevoRepresentante(RepresentanteDeProveedor representante){
        if(conexionCliente==null)return;
        
        String nom=representante.getNombre();
        String app=representante.getApellido();
        
        this.conexionCliente.addRegistro(representante);
    }
    
    public void finCU(){
      if(conexionCliente==null)return;
     
      this.conexionCliente.finalizarConexion();
    }
    
    public boolean poseeConexion(){
     if(conexionCliente==null){return false;}
        
     return true;
    }
    
    
    
}
