/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgClasesDeNegocio.AdministracionDePersonal;

import pkgRecursosDeSoporte.ClaseBase;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author Armando
 */
public class Provincia implements Comparable{

    private String nombre=new String();
    private Lista ciudades=new Lista();

    
   private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    
    
    public Provincia() {
    }
    
    public Provincia(String nombre,Lista ciudades) {
        this.nombre=nombre;
        this.ciudades=ciudades;
    }
    
   
    public int compareTo(Object o) {
        Provincia otro=(Provincia) o;
        
        return otro.nombre.compareTo(this.nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Lista getCiudades() {
        return ciudades;
    }

    public void setCiudades(Lista ciudades) {
        this.ciudades = ciudades;
    }
    

}
