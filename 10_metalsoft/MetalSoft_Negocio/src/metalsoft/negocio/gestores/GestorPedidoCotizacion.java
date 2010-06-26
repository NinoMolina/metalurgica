//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\metalsoft\\sistema\\gestores\\GestorPedidoCotizacion.java

package metalsoft.negocio.gestores;

import metalsoft.negocio.ventas.Cliente;


public class GestorPedidoCotizacion 
{
   
   /**
    * @roseuid 4C2036120340
    */
   public GestorPedidoCotizacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01CE
    */
   public void clienteSeleccionado() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01CF
    */
   public void numeroPedido() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01D0
    */
   public void fechaPedido() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01D1
    */
   public void fechaRequeridaCotizacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01D2
    */
   public void buscarProducto() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01D3
    */
   public void productoSeleccionado() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01D4
    */
   public void cantidadProducto() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01D5
    */
   public void planoPedido() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01D6
    */
   public void confirmacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01D7
    */
   public void registrarPedidoCotizacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01D8
    */
   public void generarNumeroPedido() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01D9
    */
   public void obtenerFechaActual() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01DA
    */
   public void finCasoDeUso() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01DB
    */
   public void opcionRegistrarNuevoCliente() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01DC
    */
   public void llamarCasoDeUsoRegistrarCliente() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01DD
    */
   public void cancelacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01DE
    */
   public void cancelarCasoDeUso() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01E5
    */
   public void opcionRegistrarProducto() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01E6
    */
   public void llamarCasoDeUsoRegistrarProdcucto() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01ED
    */
   public void noConfirmacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01F1
    */
   public void criterioBusquedaSeleccionado() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01F2
    */
   public void mostrarDatosPedido() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01F3
    */
   public void imprimir() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01F4
    */
   public void generarInforme() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01F5
    */
   public void finConsulta() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01F8
    */
   public void seCancelaCasoDeUso() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01F9
    */
   public void noImprimir() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D01FB
    */
   public void burcarUltimoNumeroPedido() 
   {
    
   }

    public Cliente[] buscarClientes(String valor) {
        GestorPedidoCotizacionDB gestordb=new GestorPedidoCotizacionDB();
        return gestordb.buscarClientes(valor);
    }
}
