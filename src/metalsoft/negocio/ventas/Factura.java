//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\ventas\\Factura.java

package metalsoft.negocio.ventas;

import metalsoft.negocio.adminusuarios.Usuario;

public class Factura 
{
   private int nroFactura;
   private int fechaEmision;
   private int tipoFactura;
   private TipoIva tipoIva;
   private int fechaRealCobro;
   private FormaDePago formaPago;
   private int fechaVencimiento;
   private DetalleFactura detalle;
   private Usuario usuario;
   private EstadoFactura estado;
   private ComprobantePago pagos;
   public FormaDePago theFormaDePago;
   public DetalleFactura theDetalleFactura[];
   public EstadoFactura theEstadoFactura;
   public Usuario theUsuario;
   public ComprobantePago theComprobantePago[];
   public TipoIva theTipoIva;
   public Cliente theCliente;
   public Pedido thePedido;
   
   /**
    * @roseuid 4C27ED1300A9
    */
   public Factura() 
   {
    
   }
   
   /**
    * @roseuid 4BC260D900E2
    */
   public void crear() 
   {
    
   }
   
   /**
    * @roseuid 4BC260EB0171
    */
   public void conocerEstadoFactura() 
   {
    
   }
   
   /**
    * @roseuid 4BC260F00253
    */
   public void conocerTipoIva() 
   {
    
   }
   
   /**
    * @roseuid 4BE582B8014F
    */
   public void conocerFormaDePago() 
   {
    
   }
   
   /**
    * @roseuid 4BE585DE0069
    */
   public void calcularMontoTotal() 
   {
    
   }
   
   /**
    * @roseuid 4BE587510250
    */
   public void conocerDetalleFactura() 
   {
    
   }
   
   /**
    * @roseuid 4C180FCF0159
    */
   public void conocerComprobantePago() 
   {
    
   }
   
   /**
    * @roseuid 4C180FE601DE
    */
   public void conocerUsuario() 
   {
    
   }
   
   /**
    * @roseuid 4C1FAFAD02B3
    */
   public void esFacturaAdeudada() 
   {
    
   }
   
   /**
    * @roseuid 4C1FAFD500BE
    */
   public void mostrarPagos() 
   {
    
   }
}
