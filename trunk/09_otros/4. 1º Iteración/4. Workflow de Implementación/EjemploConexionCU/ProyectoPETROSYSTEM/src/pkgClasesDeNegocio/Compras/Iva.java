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
public class Iva implements Comparable{
    private double porcentaje;
    private String descripcion=new String();
    
     private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    

    public Iva(){}
    
    public Iva(double porcentaje,String descripcion){
     this.porcentaje=porcentaje;
     this.descripcion=descripcion;
    }
    
   public int compareTo(Object o)
   {
     Iva otro = (Iva) o;
     return (int) (otro.getPorcentaje()-this.getPorcentaje());
   }
   
   public boolean existeIva(Iva iva){
    int i=this.compareTo(iva);
    if(i==0) return true;
    return false;   
   }

    
    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
    
}
