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
public class Impuesto implements Comparable{
  
    private String nombre=new String();
    private double porcentaje;
    private double monto;
    
     private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    

    public Impuesto() {
    }

    public Impuesto(String nombre,double porcentaje, float monto) {
        this.nombre=nombre;
        this.porcentaje = porcentaje;
        this.monto = monto;
    }
    
    
    
   public int compareTo(Object o)
   {
     Impuesto otro = (Impuesto) o;
     return otro.nombre.compareTo(this.nombre);
   }
   
   public boolean existeImpuesto(Impuesto impuesto){
    int i=this.compareTo(impuesto);
    if(i==0) return true;
    return false;   
   }


    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    
    
    
}
