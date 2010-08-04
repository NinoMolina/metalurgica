//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\ventas\\Producto.java

package metalsoft.negocio.ventas;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import metalsoft.negocio.access.AccessDetalleProducto;
import metalsoft.negocio.produccion.CodigoDeBarra;
import metalsoft.negocio.produccion.ProductoReal;

public class Producto 
{
   private int nroProducto;
   private String nombre;
   private float precioUnitario;
   private String descripcion;
   private ArrayList detalle;
   private ProductoReal item;
   
   
   /**
    * @roseuid 4C27ED1E010C
    */
   public Producto() 
   {
    
   }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList getDetalle() {
        return detalle;
    }

    public void setDetalle(ArrayList detalle) {
        this.detalle = detalle;
    }

    public ProductoReal getItem() {
        return item;
    }

    public void setItem(ProductoReal item) {
        this.item = item;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNroProducto() {
        return nroProducto;
    }

    public void setNroProducto(int nroProducto) {
        this.nroProducto = nroProducto;
    }

    public float getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(float precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
   
    public DetalleProducto crearDetalleProducto(int cantPieza, String desc)
    {
        DetalleProducto dp=new DetalleProducto();
        dp.setCantidadPiezas(cantPieza);
        dp.setDescripcion(desc);
        return dp;
    }

    public void guardar(Producto p,ArrayList arlIdsPiezasDetalleProducto, Connection cn) {
        ArrayList arlDetalle=p.getDetalle();
        Iterator iter=arlDetalle.iterator();
        Iterator iterIds=arlIdsPiezasDetalleProducto.iterator();
        DetalleProducto x=null;
        while(iter.hasNext())
        {
            x = (DetalleProducto) iter.next();
            long idPieza=Long.parseLong(String.valueOf(iterIds.next()));
            AccessDetalleProducto.insert(x,idPieza,cn);
        }

    }
}
