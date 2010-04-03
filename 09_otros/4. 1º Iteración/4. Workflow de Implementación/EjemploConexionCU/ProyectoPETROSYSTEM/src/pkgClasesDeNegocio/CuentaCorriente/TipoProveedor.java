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
public class TipoProveedor implements Comparable{

    private String nombre=new String();
    private String descripcion=new String();
    private Lista productos=new Lista();
    
     private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    
    
    public TipoProveedor() {}

    public TipoProveedor(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

     public int compareTo(Object o) {
       TipoProveedor otro=(TipoProveedor) o;
       return (otro.nombre.compareTo(this.nombre));
     }
     
   public boolean existeTipoProveedor(TipoProveedor tipoProveedor){
     int i=this.compareTo(tipoProveedor);
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

    public Lista getProductos() {
        return productos;
    }

    public void setProductos(Lista productos) {
        this.productos = productos;
    }

   
    
}
