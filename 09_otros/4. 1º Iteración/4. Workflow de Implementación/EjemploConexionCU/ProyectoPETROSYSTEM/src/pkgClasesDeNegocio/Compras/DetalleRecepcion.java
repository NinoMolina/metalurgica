/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgClasesDeNegocio.Compras;

import java.util.Date;
import pkgClasesDeNegocio.AdministracionDePersonal.Empleado;
import pkgRecursosDeSoporte.ClaseBase;

/**
 *
 * @author Armando
 */
public class DetalleRecepcion implements Comparable{
  
    private Date fechaRecepcion=new Date();
    private String horaRealRecepcion=new String();
    private double cantidadRecibida;
    private double precioUnitario;
    private Empleado empleado=new Empleado();
    private DetallePedidoAProveedor detallePedido=new DetallePedidoAProveedor();
    private Producto producto=new Producto();
   
    
     private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    

    
    public DetalleRecepcion() {}
    
    public DetalleRecepcion(Date fechaRecepcion,String horaRealRecepcion,double cantidadRecibida, 
                           double precioUnitario,Empleado empleado,DetallePedidoAProveedor detallePedido) {
        this.fechaRecepcion=fechaRecepcion;
        this.horaRealRecepcion=horaRealRecepcion;
        this.cantidadRecibida = cantidadRecibida;
        this.precioUnitario = precioUnitario;
        this.empleado=empleado;
        this.detallePedido=detallePedido;
    }

    public int compareTo(Object o) {
        DetalleRecepcion otro=(DetalleRecepcion) o;
        
        return otro.fechaRecepcion.compareTo(this.fechaRecepcion);
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getHoraRealRecepcion() {
        return horaRealRecepcion;
    }

    public void setHoraRealRecepcion(String horaRealRecepcion) {
        this.horaRealRecepcion = horaRealRecepcion;
    }

    public double getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(double cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public DetallePedidoAProveedor getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(DetallePedidoAProveedor detallePedido) {
        this.detallePedido = detallePedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }
    
    public double getMontoTotal(){
     return (this.cantidadRecibida*this.precioUnitario);
    
    }
    
}
