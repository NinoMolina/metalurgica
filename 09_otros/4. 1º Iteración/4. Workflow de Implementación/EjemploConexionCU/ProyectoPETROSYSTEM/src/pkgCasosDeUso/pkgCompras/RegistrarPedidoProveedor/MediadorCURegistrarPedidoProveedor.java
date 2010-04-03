/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCasosDeUso.pkgCompras.RegistrarPedidoProveedor;

import pkgCasosDeUso.pkgCompras.RegistrarPedidoProveedor.pkgConexionesCU.ConexionRegistrarPedidoProveedor_RegistrarRepresentanteProveedor;
import pkgCasosDeUso.pkgSoporteConexionCU.Conexion;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;

/**
 *
 * @author Armando
 */
public class MediadorCURegistrarPedidoProveedor {

    private ConexionRegistrarPedidoProveedor_RegistrarRepresentanteProveedor c_RegRepresentanteProv=new ConexionRegistrarPedidoProveedor_RegistrarRepresentanteProveedor();
    private GestorRegistrarPedidoProveedor gestor;

    private Conexion conexionCliente;
             
    public void ejecutarCU(){}
    
    public void llamarCURegistrarRepresentanteProveedor() {
        c_RegRepresentanteProv.setMCU_Origen(this);
        c_RegRepresentanteProv.llamarCU();       
    }
    
   public void llamarCURegistrarRepresentanteProveedor(Proveedor pAux) {
       c_RegRepresentanteProv.setProveedor(pAux);
       c_RegRepresentanteProv.setMCU_Origen(this);
       c_RegRepresentanteProv.llamarCU();       
   }
    

    public void setCliente(Conexion cliente) {
        this.conexionCliente = cliente;
    }

    public void setGestor(GestorRegistrarPedidoProveedor gestor) {
        this.gestor = gestor;
    }

    public void finEjecucionCURegistrarRepresentanteProveedor(){
      gestor.actualizarListaRepresentantesPrueba(c_RegRepresentanteProv.getRepresentantes());
      gestor.finEjecucionCURegRepresentanteProveedor(c_RegRepresentanteProv.getProveedor());
    }

     public boolean poseeConexion(){
     if(conexionCliente==null){return false;}
        
     return true;
    }
    
   
    
    
    
}
