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
public class Remito implements Comparable{

    private int numero;
    private DetalleRecepcion detalle=new DetalleRecepcion();
    
     private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    
    
    public Remito() {}
    
    public Remito(int numero,DetalleRecepcion detalle) {
        this.numero = numero;
        this.detalle=detalle;
    }
    
    public int compareTo(Object o) {
        Remito otro=(Remito) o;
        
        return otro.numero-this.numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public DetalleRecepcion getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleRecepcion detalle) {
        this.detalle = detalle;
    }
    
}
