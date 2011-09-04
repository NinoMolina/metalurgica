//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\ventas\\DetallePresupuesto.java

package metalsoft.negocio.ventas;

import java.util.ArrayList;


public class DetallePresupuesto 
{
   private double precio;
   private int cantidad;
   private Producto producto;
   private ArrayList<DetalleProductoPresupuesto> detalleProductoPresupuesto;
   
   /**
    * @roseuid 4C2800880009
    */
   public DetallePresupuesto() 
   {
    
   }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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

    public ArrayList<DetalleProductoPresupuesto> getDetalleProductoPresupuesto() {
        return detalleProductoPresupuesto;
    }

    public void setDetalleProductoPresupuesto(ArrayList<DetalleProductoPresupuesto> detalleProductoPresupuesto) {
        this.detalleProductoPresupuesto = detalleProductoPresupuesto;
    }
    
    public DetalleProductoPresupuesto crearDetalleProductoPresupuesto() {
        return new DetalleProductoPresupuesto();
    }
}
