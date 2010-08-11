//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\ventas\\DetalleProducto.java

package metalsoft.negocio.ventas;

import java.sql.Connection;
import metalsoft.negocio.access.AccessDetalleProducto;


public class DetalleProducto 
{


   private int cantidadPiezas;
   private String descripcion;
   private Pieza pieza;
   
   /**
    * @roseuid 4C27ED2301F4
    */
   public DetalleProducto() 
   {
    
   }

   public static int eliminar(long idDetalle, Connection cn) {
        return AccessDetalleProducto.delete(idDetalle,cn);
    }

    public int getCantidadPiezas() {
        return cantidadPiezas;
    }

    public void setCantidadPiezas(int cantidadPiezas) {
        this.cantidadPiezas = cantidadPiezas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Pieza getPieza() {
        return pieza;
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }
   
   
}
