/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgClasesDeNegocio.Compras;

import java.util.Date;
import pkgClasesDeNegocio.AdministracionDePersonal.Empleado;
import pkgRecursosDeSoporte.ClaseBase;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author Armando
 */
public class Recepcion implements Comparable{
  
    private Date fechaRealRecepcion=new Date();
    private String horaRealRecepcion=new String();
    private double montoTotal;
    private PedidoAProveedor pedidoAProveedor=new PedidoAProveedor();
    private Lista remito=new Lista();
    private FacturaProveedor factura=new FacturaProveedor();
    private Lista detallesRecepcion=new Lista();
    
     private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    

    
    
    public Recepcion() {}
    
    public Recepcion(Date fechaRealRecepcion, String horaRealRecepcion, double montoTotal, PedidoAProveedor pedidoAProveedor, Lista remito, FacturaProveedor factura) {
        this.fechaRealRecepcion = fechaRealRecepcion;
        this.horaRealRecepcion = horaRealRecepcion;
        this.montoTotal = montoTotal;
        this.pedidoAProveedor = pedidoAProveedor;
        this.remito = remito;
        this.factura = factura;
    }
    
   public int compareTo(Object o) {
        Recepcion otro=(Recepcion) o;
        
        return otro.fechaRealRecepcion.compareTo(this.fechaRealRecepcion);
    }
   
   public void crearDetalleRecepcion(Date fechaRecepcion,String horaRealRecepcion,double cantidadRecibida,
                                     double precioUnitario,Empleado empleado,DetallePedidoAProveedor detallePedido){
   
      DetalleRecepcion dlle=new DetalleRecepcion(fechaRecepcion, horaRealRecepcion, cantidadRecibida, precioUnitario, empleado, detallePedido);  
      if(this.getDetallesRecepcion()==null){this.setDetallesRecepcion(new Lista());}
      
        this.getDetallesRecepcion().insertarOrdenado(dlle);
   
   }
   
   public void crearDetalleRecepcion(){
   
      DetalleRecepcion dlle=new DetalleRecepcion();  
      if(this.getDetallesRecepcion()==null){this.setDetallesRecepcion(new Lista());}
      
        this.getDetallesRecepcion().insertarOrdenado(dlle);
   
   }
    
    

    public Date getFechaRealRecepcion() {
        return fechaRealRecepcion;
    }

    public void setFechaRealRecepcion(Date fechaRealRecepcion) {
        this.fechaRealRecepcion = fechaRealRecepcion;
    }

    public String getHoraRealRecepcion() {
        return horaRealRecepcion;
    }

    public void setHoraRealRecepcion(String horaRealRecepcion) {
        this.horaRealRecepcion = horaRealRecepcion;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public PedidoAProveedor getPedidoAProveedor() {
        return pedidoAProveedor;
    }

    public void setPedidoAProveedor(PedidoAProveedor pedidoAProveedor) {
        this.pedidoAProveedor = pedidoAProveedor;
    }

    public Lista getRemito() {
        return remito;
    }

    public void setRemito(Lista remito) {
        this.remito = remito;
    }

    public FacturaProveedor getFactura() {
        return factura;
    }

    public void setFactura(FacturaProveedor factura) {
        this.factura = factura;
    }

    public Lista getDetallesRecepcion() {
        return detallesRecepcion;
    }

    public void setDetallesRecepcion(Lista detallesRecepcion) {
        this.detallesRecepcion = detallesRecepcion;
    }

  
    
    
}
