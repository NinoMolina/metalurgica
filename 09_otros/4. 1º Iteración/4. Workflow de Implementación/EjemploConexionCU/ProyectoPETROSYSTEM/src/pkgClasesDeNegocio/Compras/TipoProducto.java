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
public class TipoProducto implements Comparable{

    private String nombre=new String();
    private String descripcion=new String();
    
     private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    

    public TipoProducto() {
    }
    
    public TipoProducto(String nombre,String descripcion) {
        this.nombre=nombre;
        this.descripcion=descripcion;
    }
    
   
    public int compareTo(Object o) {
        TipoProducto otro=(TipoProducto) o;
      
        return (otro.nombre.compareTo(this.nombre));
    }
    
   public boolean existeTipoProducto(TipoProducto tipoProducto){
    int i=this.compareTo(tipoProducto);
    if(i==0) return true;
    return false;   
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
