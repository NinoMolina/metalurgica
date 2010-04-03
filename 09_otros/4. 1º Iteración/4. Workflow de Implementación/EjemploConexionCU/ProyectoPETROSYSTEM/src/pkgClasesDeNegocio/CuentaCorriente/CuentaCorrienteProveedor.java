/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgClasesDeNegocio.CuentaCorriente;

import pkgRecursosDeSoporte.ClaseBase;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author Armando
 */
public class CuentaCorrienteProveedor implements Comparable{
     
    private double totalAdeudado;
    private int numeroCuenta;
    //private Lista pagos=new Lista();
    //private Lista planDePagos=new Lista();
     private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    
  
    
    public CuentaCorrienteProveedor(){
    }
    
     /*
     *El constructor está listo para los nuevos atributos, nada más
     *que ahora no los setea.  
     */
    public CuentaCorrienteProveedor(double totalAdeudado,int numeroCuenta,Lista pago,Lista planDePagos){
        this.totalAdeudado=totalAdeudado;
        this.numeroCuenta=numeroCuenta;
        //this.pagos=pagos;
        //this.planDePagos=planDePagos;
    }
    
    public int compareTo(Object o) {
        CuentaCorrienteProveedor otro=(CuentaCorrienteProveedor) o;
        return otro.numeroCuenta-this.numeroCuenta;
    }
    
   public boolean existeCuentaCorrienteProveedor(CuentaCorrienteProveedor ctaCteProveedor){
    int i=this.compareTo(ctaCteProveedor);
    if(i==0) return true;
    return false;   
   }


    public double getTotalAdeudado() {
        return totalAdeudado;
    }

    public void setTotalAdeudado(double totalAdeudado) {
        this.totalAdeudado = totalAdeudado;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    
    
}
