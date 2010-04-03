/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgClasesDeNegocio.Compras;

import java.util.Date;
import pkgClasesDeNegocio.AdministracionDePersonal.Empleado;
import pkgClasesDeNegocio.AdministracionDePersonal.Estado;
import pkgClasesDeNegocio.CuentaCorriente.Proveedor;
import pkgClasesDeNegocio.CuentaCorriente.RepresentanteDeProveedor;
import pkgRecursosDeSoporte.ClaseBase;
import pkgRecursosDeSoporte.pkgLista.Iterador;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author Armando
 */
public class PedidoAProveedor implements Comparable{

    private int numeroPedido;
    private Date fechaRealizacion=new Date();//En este atributo solo se gestiona la fecha. Lo mismo vale para fechaEstimadaEntrega
    private String horaRealizacion=new String();//En este atributo solo se gestiona la hora. Lo mismo vale para horaEstimadaEntrega  
    private Date fechaEstimadaEntrega=new Date();
    private String horaEstimadaEntrega=new String();
    private double montoTotal;
    private Proveedor proveedor=new Proveedor();
    private RepresentanteDeProveedor representanteProveedor=new RepresentanteDeProveedor();
    private Estado estado=new Estado();
    private Empleado empleado=new Empleado();
    private Lista detallesPedidoAProveedor=new Lista();
    
     private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    
    
    public PedidoAProveedor(){}
    
    public PedidoAProveedor(Date fechaRealizacion,String horaRealizacion,Date fechaEstimadaEntrega,
                            String horaEstimadaEntrega,double montoTotal,Proveedor proveedor,
                            RepresentanteDeProveedor representanteProveedor,Estado estado,
                            Empleado empleado,Lista detallesPedidoAProveedor){
    
    this.fechaRealizacion=fechaRealizacion;
    this.horaRealizacion=horaRealizacion;
    this.fechaEstimadaEntrega=fechaEstimadaEntrega;
    this.horaEstimadaEntrega=horaEstimadaEntrega;
    this.montoTotal=montoTotal;
    this.proveedor=proveedor;
    this.representanteProveedor=representanteProveedor;
    this.estado=estado;
    this.empleado=empleado;
    this.detallesPedidoAProveedor=detallesPedidoAProveedor;
    
    }
    
    public PedidoAProveedor getPedidoAProveedorSiEstado(Estado e){
      int res=this.estado.compareTo(e);
      
      if(res==0){return this;}
      
      return null;
    
    }
    
   
    public int compareTo(Object o) {
       PedidoAProveedor otro=(PedidoAProveedor) o;
       
       return otro.numeroPedido-this.numeroPedido;
    }
    
    public void crearDetallePedidoAProveedor(double cantidad,double precioActual,Producto producto,Estado estado){
      DetallePedidoAProveedor dlle=new DetallePedidoAProveedor(cantidad, precioActual, producto,estado);
      
      if(this.detallesPedidoAProveedor==null){this.detallesPedidoAProveedor=new Lista();}
      
      this.detallesPedidoAProveedor.insertarOrdenado(dlle);
      
    }
    
     public void crearDetallePedidoAProveedor(){
      DetallePedidoAProveedor dlle=new DetallePedidoAProveedor();
      
      if(this.detallesPedidoAProveedor==null){this.detallesPedidoAProveedor=new Lista();}
      this.detallesPedidoAProveedor.insertarOrdenado(dlle);
      
    }


    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

   
    public Date getFechaEstimadaEntrega() {
        return fechaEstimadaEntrega;
    }

    public void setFechaEstimadaEntrega(Date fechaEstimadaEntrega) {
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
    }

    public String getHoraEstimadaEntrega() {
        return horaEstimadaEntrega;
    }

    public void setHoraEstimadaEntrega(String horaEstimadaEntrega) {
        this.horaEstimadaEntrega = horaEstimadaEntrega;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public RepresentanteDeProveedor getRepresentanteProveedor() {
        return representanteProveedor;
    }

    public void setRepresentanteProveedor(RepresentanteDeProveedor representanteProveedor) {
        this.representanteProveedor = representanteProveedor;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Lista getDetallesPedidoAProveedor() {
        return detallesPedidoAProveedor;
    }

    public void setDetallesPedidoAProveedor(Lista detallesPedidoAProveedor) {
        this.detallesPedidoAProveedor = detallesPedidoAProveedor;
    }

    public String getHoraRealizacion() {
        return horaRealizacion;
    }

    public void setHoraRealizacion(String horaRealizacion) {
        this.horaRealizacion = horaRealizacion;
    }

    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

   
    
    
}
