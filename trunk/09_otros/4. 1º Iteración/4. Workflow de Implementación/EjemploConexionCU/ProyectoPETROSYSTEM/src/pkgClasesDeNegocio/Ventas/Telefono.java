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
public class Telefono implements Comparable {
    
    private int numero;
    private int caracteristica;
    private TipoTelefono tipoTelefono;
    
    private ClaseBase atributosBD=new ClaseBase();
   
    

    public int compareTo(Object o) {
        Telefono otro=(Telefono) o;
        return otro.numero-this.numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(int caracteristica) {
        this.caracteristica = caracteristica;
    }
    
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }

    public TipoTelefono getTipoTelefono() {
        return tipoTelefono;
    }

    public void setTipoTelefono(TipoTelefono tipoTelefono) {
        this.tipoTelefono = tipoTelefono;
    }
    
    

}
