/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgClasesDeNegocio.Compras;

import pkgClasesDeNegocio.AdministracionDePersonal.Estado;
import pkgRecursosDeSoporte.ClaseBase;

/**
 *
 * @author Armando
 */
public class DetallePedidoAProveedor implements Comparable{
    
    //LUEGO VER LO DEL ESTADO PARA CADA DETALLE, IMPLEMENTAR EL METODO getDlleSiEstado()
    
    
    private double cantidad;
    private double precioActual;
    private Producto producto=new Producto();
    private Estado estado=new Estado();
    
     private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    

    public DetallePedidoAProveedor(double cantidad, double precioActual, Producto producto,Estado estado) {
        this.cantidad = cantidad;
        this.precioActual = precioActual;
        this.producto = producto;
        this.estado=estado;
    }
    
    
    public DetallePedidoAProveedor() {
    }

    public DetallePedidoAProveedor getDetallePedidoAProveedorSiEstado(Estado e){
      int res=this.getEstado().compareTo(e);
      
      if(res==0){return this;}
      
      return null;
    
    }
    
    
    public double getPrecioTotal(){
      return (precioActual*cantidad);
    }
    
    
    public int compareTo(Object o) {
        DetallePedidoAProveedor otro=(DetallePedidoAProveedor) o;
        
        return otro.producto.compareTo(this.producto);
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioActual() {
        return precioActual;
    }

    public void setPrecioActual(double precioActual) {
        this.precioActual = precioActual;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
