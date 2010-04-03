/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgCasosDeUso.pkgCompras.ConsultarPedidoProveedor;

import pkgClasesDeNegocio.Compras.PedidoAProveedor;
import pkgClasesDeNegocio.Compras.Recepcion;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgRecursosDeSoporte.Mensaje;
import pkgRecursosDeSoporte.pkgLista.Iterador;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author Armando
 */
public class GestorConsultarPedidoProveedor {

   private PllaConsultarPedidoProveedor pllaPrincipal;
   private PllaConsultarDetallePedido pllaConsultaDllePedido;
   private PllaConsultarRecepcionPedido pllaConsultaRecepcion;
   private IntermediarioBDConsultarPedidoProveedor intermediarioBD=new IntermediarioBDConsultarPedidoProveedor();
   private Lista proveedores;
   private Proveedor proveedor;
   private Lista pedidos;
   private PedidoAProveedor pedidoProveedorActual;
   private Recepcion recepcionPedidoActual;
    
    
    
   public void nuevaConsultaPedidoProveedor(PllaConsultarPedidoProveedor plla) {
       this.pllaPrincipal=plla; 
       proveedores=new Lista();
       proveedor=new Proveedor();
       pedidos=new Lista();
       pedidoProveedorActual=new PedidoAProveedor();
       
       this.buscarProveedores();
       pllaPrincipal.mostrarProveedores(proveedores);
    }

    public void tomarProveedor(String razonSocial) {
        
        Proveedor pABuscar=new Proveedor();
        pABuscar.setRazonSocial(razonSocial);
        
       try{ 
         proveedor=this.buscarProveedor(pABuscar);        
         
         if(proveedor==null){
          proveedor=new Proveedor();
          pllaPrincipal.mostrarMensajePantalla("No existe el proveedor: "+pABuscar.getRazonSocial(),Mensaje.TIPO_AVISO);
          return;
         }
         
         this.buscarPedidosProveedor();
         
         
         
         
         this.pllaPrincipal.mostrarPedidosDeProveedor(pedidos);
    
       }
       catch(Exception e){
        this.pllaPrincipal.mostrarMensajePantalla("Error al intentar acceder a los datos del proveedor",Mensaje.TIPO_ERROR);
       }
    }

    public void buscarRecepcionPedido(int nroPedido) {
       
      try{
        PedidoAProveedor pAux=new PedidoAProveedor();
        pAux.setNumeroPedido(nroPedido);
        
        pedidoProveedorActual=(PedidoAProveedor)this.pedidos.crearIterador().buscarElemento(pAux);
        
        if(pedidoProveedorActual.getEstado().getNombre().compareTo("Recibido parcial")==0 || pedidoProveedorActual.getEstado().getNombre().compareTo("Recibido total")==0){
        
          if(recepcionPedidoActual==null || (recepcionPedidoActual.getPedidoAProveedor().getNumeroPedido()!=pedidoProveedorActual.getNumeroPedido())){
            recepcionPedidoActual=intermediarioBD.getRecepcionDePedido(pedidoProveedorActual);
            recepcionPedidoActual.setPedidoAProveedor(pedidoProveedorActual);
          }
        
         if(recepcionPedidoActual!=null){
           pllaPrincipal.mostrarRecepcionPedido(recepcionPedidoActual);
         }
         else{
          this.pllaPrincipal.mostrarMensajePantalla("El pedido seleccionado no ha sido recibido. Verifique el estado del mismo",Mensaje.TIPO_ERROR);     
         }
            
        }
        else{
         this.pllaPrincipal.mostrarMensajePantalla("El pedido seleccionado no ha sido recibido. El estado del mismo es: "+pedidoProveedorActual.getEstado().getNombre(),Mensaje.TIPO_AVISO);
        }
        
 
       }
       catch(Exception ex){
         this.pllaPrincipal.mostrarMensajePantalla("Error al intentar acceder a los datos de recepción del pedido",Mensaje.TIPO_ERROR);     
       }
        
        
        
    }
    
    private Proveedor buscarProveedor(Proveedor pBuscado){
     Iterador itProveedores=proveedores.crearIterador();
     
 
     
     while(itProveedores.siguiente()){
      Proveedor pAux=(Proveedor) itProveedores.getElementoActual();
      
      if(pAux.getRazonSocial().compareTo(pBuscado.getRazonSocial())==0){return pAux;}
         
         
     }
    
    return null;
    
    }
    
    private void buscarPedidosProveedor(){
     
       try{
         pedidos=this.intermediarioBD.getPedidosDeProveedor(proveedor);
         
         if(pedidos.estaVacia()==false){
             Iterador itPedidos=pedidos.crearIterador();
             
             while(itPedidos.siguiente()){
                    
              PedidoAProveedor p=(PedidoAProveedor)itPedidos.getElementoActual();
              
              Lista detallesPedido=intermediarioBD.getDetallesDePedido(p);
              
              p.setProveedor(proveedor);
              p.setDetallesPedidoAProveedor(detallesPedido);
             }   
         }
                     
        }
        catch(Exception e){
         pllaPrincipal.mostrarMensajePantalla("Error al intentar buscar pedidos proveedor",Mensaje.TIPO_ERROR);
        }
    
    
    }

    private void buscarProveedores() {
     try{   
        
         proveedores=intermediarioBD.getProveedores();
           
     }
     catch(Exception e){
       pllaPrincipal.mostrarMensajePantalla("Error al intentar mostrar los proveedores",Mensaje.TIPO_ERROR);
     }
        
        
    }
    
    public void buscarDetallesPedido(int nroPedido){
    
        PedidoAProveedor pedidoAux=new PedidoAProveedor();
        pedidoAux.setNumeroPedido(nroPedido);
        
        pedidoProveedorActual=(PedidoAProveedor)this.pedidos.crearIterador().buscarElemento(pedidoAux);
        
        //Crear un metodo en pllaConsultarPedidoProveedor, para llamar a la otra pantalla de consulta detalles, pasandole el pedido entero seleccionado
        //Ver de llamar a la pantalla donde se mustran los detalles, desde el Gestor, y no de la pllaConsultarPedidoProveedor
        
        pllaPrincipal.mostrarDetallePedido(pedidoProveedorActual); 
             
    
    }

    public void setPllaConsultaDllePedido(PllaConsultarDetallePedido pllaConsultaDllePedido) {
        this.pllaConsultaDllePedido = pllaConsultaDllePedido;
    }

    public void setPllaConsultaRecepcion(PllaConsultarRecepcionPedido pllaConsultaRecepcion) {
        this.pllaConsultaRecepcion = pllaConsultaRecepcion;
    }
    
    
   
   

}
