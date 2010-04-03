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
public class Pais implements Comparable{

    private String nombre=new String();
    private Lista provincias=new Lista();
    
     private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    

    public Pais() {
    }
    
    public Pais(String nombre,Lista provincias) {
        this.nombre=nombre;
        this.provincias=provincias;
    }

    public int compareTo(Object o) {
        Pais otro=(Pais) o;
        
        return otro.nombre.compareTo(this.nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Lista getProvincias() {
        return provincias;
    }

    public void setProvincias(Lista provincias) {
        this.provincias = provincias;
    }
    
}
