/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgClasesDeNegocio.Ventas;

import pkgRecursosDeSoporte.ClaseBase;

/**
 *
 * @author Diego
 */
public class TipoTelefono implements Comparable{

    private String nombre;
    
    private ClaseBase atributosBD=new ClaseBase();
    
    
    public int compareTo(Object o) {
        TipoTelefono otro=(TipoTelefono) o;
        
        return otro.nombre.compareTo(this.nombre);
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
}
