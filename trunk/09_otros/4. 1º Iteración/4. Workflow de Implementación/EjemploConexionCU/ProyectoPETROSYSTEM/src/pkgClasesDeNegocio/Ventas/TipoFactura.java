/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgClasesDeNegocio.Ventas;

import pkgRecursosDeSoporte.ClaseBase;

/**
 *
 * @author Armando
 */
public class TipoFactura implements Comparable{
    
    private String nombre=new String();
    
     private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    

    public TipoFactura() {
    }

    public TipoFactura(String nombre) {
        this.nombre = nombre;
    }
    
    public int compareTo(Object o) {
       TipoFactura otro=(TipoFactura) o;
       return (otro.nombre.compareTo(this.nombre));
    }
    
    public boolean existeTipoFactura(TipoFactura tipoFactura){
     int i=this.compareTo(tipoFactura);
     if(i==0) return true;
     return false;   
   }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

   

}
