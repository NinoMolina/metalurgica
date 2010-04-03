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
public class AmbitoEstado implements Comparable{

    private String nombre=new String();
    private ClaseBase atributosBD=new ClaseBase();

    public AmbitoEstado() {
    }
  
    public AmbitoEstado(String nombre) {
        this.nombre=nombre;
    }

    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
     public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }

    public int compareTo(Object o) {
        AmbitoEstado otro=(AmbitoEstado) o;
        
        return otro.nombre.compareTo(this.nombre);
    }
    
    
}
