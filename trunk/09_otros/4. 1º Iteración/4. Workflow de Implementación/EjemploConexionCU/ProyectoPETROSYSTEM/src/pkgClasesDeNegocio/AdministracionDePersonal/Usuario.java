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
public class Usuario implements Comparable{
   
    private String nombreUsuario=new String();
    private String password=new String();
    private Lista sesiones=new Lista();
    
     private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    

    public Usuario() {
    }

    public Usuario(String nombreUsuario,String password,Lista sesiones) {
        this.nombreUsuario=nombreUsuario;
        this.password=password;
        this.sesiones=sesiones;     
    }
    
   public int compareTo(Object o) {
        Usuario otro=(Usuario) o;
        
        return otro.nombreUsuario.compareTo(this.nombreUsuario);
    }
    
    
      
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Lista getSesiones() {
        return sesiones;
    }

    public void setSesiones(Lista sesiones) {
        this.sesiones = sesiones;
    }

}
