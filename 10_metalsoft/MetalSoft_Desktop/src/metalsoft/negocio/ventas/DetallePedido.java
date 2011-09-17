//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\ventas\\DetallePedido.java

package metalsoft.negocio.ventas;


public class DetallePedido 
{
   private double precio;
   private int cantidad;
   private Producto producto;
   private DetallePresupuesto detallePresupuesto;
   private DetalleFactura detalleFactura;
   
   /**
    * @roseuid 4C27ED1601B6
    */
   public DetallePedido() 
   {
    
   }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantiddad) {
        this.cantidad = cantiddad;
    }

    public DetalleFactura getDetalleFactura() {
        return detalleFactura;
    }

    public void setDetalleFactura(DetalleFactura detalleFactura) {
        this.detalleFactura = detalleFactura;
    }

    public DetallePresupuesto getDetallePresupuesto() {
        return detallePresupuesto;
    }

    public void setDetallePresupuesto(DetallePresupuesto detallePresupuesto) {
        this.detallePresupuesto = detallePresupuesto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
   
   /**
    * @roseuid 4BC25E8700FF
    */
   public void crear() 
   {
    
   }
   
   /**
    * @roseuid 4BC25E900045
    */
   public void conocerProducto() 
   {
    
   }
   
   /**
    * @roseuid 4C1812A00015
    */
   public void conocerDetallePresupuesto() 
   {
    
   }
   
   /**
    * @roseuid 4C1812AF020B
    */
   public void conocerDetalleFactura() 
   {
    
   }
}
