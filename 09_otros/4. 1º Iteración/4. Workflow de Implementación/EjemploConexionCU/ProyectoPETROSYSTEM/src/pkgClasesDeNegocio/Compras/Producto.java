/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgClasesDeNegocio.Compras;

import pkgRecursosDeSoporte.ClaseBase;

/**
 *
 * @author Armando
 */
public class Producto implements Comparable{

    private String nombre=new String();
    private double precioCosto;
    private double precioVenta;
    private double stockMinimo;
    private double stockActual;
    private String unidad=new String();
    private boolean autorizacionPedidoEnPlaya;
    private Iva iva=new Iva();
    
     private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    
    
    public Producto() {
    }

    public Producto(String nombre,double precioCosto, double precioVenta, double stockMinimo, double stockActual, boolean autorizacionPedidoEnPlaya,Iva iva) {
        this.nombre=nombre;
        this.precioCosto = precioCosto;
        this.precioVenta = precioVenta;
        this.stockMinimo = stockMinimo;
        this.stockActual = stockActual;
        this.autorizacionPedidoEnPlaya = autorizacionPedidoEnPlaya;
        this.iva=iva;
    }
    
   public int compareTo(Object o)
   {
     Producto otro = (Producto) o;
     return otro.nombre.compareTo(this.nombre);
   }
   
   public boolean existeProducto(Producto producto){
    int i=this.compareTo(producto);
    if(i==0) return true;
    return false;   
   }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecioCosto() {
        return precioCosto;
    }

    public void setPrecioCosto(double precioCosto) {
        this.precioCosto = precioCosto;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public double getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(double stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public double getStockActual() {
        return stockActual;
    }

    public void setStockActual(double stockActual) {
        this.stockActual = stockActual;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public boolean isAutorizacionPedidoEnPlaya() {
        return autorizacionPedidoEnPlaya;
    }

    public void setAutorizacionPedidoEnPlaya(boolean autorizacionPedidoEnPlaya) {
        this.autorizacionPedidoEnPlaya = autorizacionPedidoEnPlaya;
    }

    public Iva getIva() {
        return iva;
    }

    public void setIva(Iva iva) {
        this.iva = iva;
    }
    
    
}
