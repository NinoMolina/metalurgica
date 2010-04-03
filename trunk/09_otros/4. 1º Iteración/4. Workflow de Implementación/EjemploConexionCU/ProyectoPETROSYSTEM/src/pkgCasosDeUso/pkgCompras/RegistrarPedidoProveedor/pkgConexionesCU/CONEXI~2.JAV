/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCasosDeUso.pkgCompras.RegistrarPedidoProveedor.pkgConexionesCU;

import pkgCasosDeUso.pkgCompras.RegistrarPedidoProveedor.MediadorCURegistrarPedidoProveedor;
import pkgCasosDeUso.pkgCompras.RegistrarRepresentanteProveedor.MediadorCURegistrarRepresentanteProveedor;
import pkgCasosDeUso.pkgSoporteConexionCU.Conexion;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgClasesDeNegocio.CuentaCorriente.RepresentanteDeProveedor;
import pkgRecursosDeSoporte.pkgLista.Iterador;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author Armando
 */
public class ConexionRegistrarPedidoProveedor_RegistrarRepresentanteProveedor extends Conexion{

    private MediadorCURegistrarRepresentanteProveedor mCU_RegRepresentanteProv=new MediadorCURegistrarRepresentanteProveedor();
    private MediadorCURegistrarPedidoProveedor mCU_RegPedidoProveedor;
    
    /*Atributos propios de la conexión, entre los dos casos de uso.
    Varian, según los casos de uso que se conectan*/
    private Lista representantes=new Lista();
    private Proveedor proveedor=null;
    
    
    public void llamarCU() {
    
     if(proveedor!=null){
      mCU_RegRepresentanteProv.setProveedorDeRepresentantes(proveedor);
     }
   
      mCU_RegRepresentanteProv.setConexionCliente(this);   
      
      mCU_RegRepresentanteProv.ejecutarCU();
  
    }

   
    public void addRegistro(Comparable x) { 
         /*Esta primera sentencia, es solo a modo de prueba. Siempre se debería pasar un proveedor, 
           que sea a quien se les cargue los representantes.
         */        
        RepresentanteDeProveedor r=(RepresentanteDeProveedor)x;
        String nom=r.getNombre();
        String app=r.getApellido();
        
         this.representantes.insertarUltimo(x);
         
         if(this.proveedor!=null){
          proveedor.getRepresentantes().insertarUltimo(x);
         }
    }

    
    public void finalizarConexion() {
      
       //Prueba
       /*Iterador i=proveedor.getRepresentantes().crearIterador(); 
        
       while(i.siguiente()){
         RepresentanteDeProveedor r=new RepresentanteDeProveedor();
         r=(RepresentanteDeProveedor) i.getElementoActual();
         String nom=r.getNombre();
         String app=r.getApellido();
         System.out.println(nom+" "+app);
       }
      //Fin prueba*/ 
       
       mCU_RegPedidoProveedor.finEjecucionCURegistrarRepresentanteProveedor(); 
    }
    
  
    public int compareTo(Object o) {
        return 0;
    }

    public void setMCU_Origen(MediadorCURegistrarPedidoProveedor mCU_Origen) {
        this.mCU_RegPedidoProveedor = mCU_Origen;
    }

    public Lista getRepresentantes() {
        return representantes;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    
}
