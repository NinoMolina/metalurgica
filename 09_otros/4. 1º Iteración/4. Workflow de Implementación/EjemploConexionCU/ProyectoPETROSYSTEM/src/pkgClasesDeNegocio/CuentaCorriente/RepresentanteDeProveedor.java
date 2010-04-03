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
public class RepresentanteDeProveedor implements Comparable{
  
    private String nombre=new String();
    private String apellido=new String();
    private Lista  telefonos=new Lista();
    
     private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    

    public RepresentanteDeProveedor() {
    }

    public RepresentanteDeProveedor(String nombre,String apellido,Lista telefonos) {
        this.nombre=nombre;
        this.apellido=apellido;
        this.telefonos=telefonos;
    } 
        
    public int compareTo(Object o) {
        RepresentanteDeProveedor otro=(RepresentanteDeProveedor) o;
        
        String nomApOtro=otro.nombre+" "+otro.apellido;
        String nomApEste=this.nombre+" "+this.apellido;
        
        return nomApOtro.compareTo(nomApEste);
    }
    
    public boolean existeRepresentanteDeProveedor(RepresentanteDeProveedor rpteProveedor){
     int i=this.compareTo(rpteProveedor);
     if(i==0) return true;
     return false;   
   }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Lista getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(Lista telefonos) {
        this.telefonos = telefonos;
    }

   
    
    
}
