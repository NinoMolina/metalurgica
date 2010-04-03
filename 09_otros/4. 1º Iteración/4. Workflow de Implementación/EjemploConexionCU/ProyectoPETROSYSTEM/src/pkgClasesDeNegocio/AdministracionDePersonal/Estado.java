/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgClasesDeNegocio.AdministracionDePersonal;

import pkgRecursosDeSoporte.ClaseBase;

/**
 *
 * @author Armando
 */
public class Estado implements Comparable{

    private String nombre=new String();
    private String descripcion=new String();
    
     private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    

    public Estado() {
    }

    public Estado(String nombre,String descripcion) {
     this.nombre=nombre;
     this.descripcion=descripcion;
    }
    
    public int compareTo(Object o) {
        Estado otro=(Estado) o;
        
        return otro.nombre.compareTo(this.nombre);
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

   
    
    
}
