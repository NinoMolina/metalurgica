/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgClasesDeNegocio.Compras;

import pkgRecursosDeSoporte.ClaseBase;
import pkgRecursosDeSoporte.pkgLista.*;
/**
 *
 * @author Armando
 */
public class Combustible extends Producto{
    
    private Lista impuestos=new Lista();
    
  
    

    public Combustible(String nombre, float precioCosto, float precioVenta, float stockMinimo, float stockActual, boolean autorizacionPedidoEnPlaya, Iva iva, Lista impuestos) {
        super(nombre, precioCosto, precioVenta, stockMinimo, stockActual, autorizacionPedidoEnPlaya, iva);
        this.impuestos=impuestos;
    }

    public Combustible() {
     super();
    }

    public void agregarImpuesto(Impuesto impuesto){
     this.impuestos.insertarOrdenado(impuesto);
    }
    
    public Impuesto quitarImpuesto(Impuesto impuesto){
      Iterador itImpuestos=impuestos.crearIterador();
      
      Impuesto impuestoEncontrado=(Impuesto)itImpuestos.buscarElemento(impuesto);
      
      if(impuestoEncontrado!=null){
       this.impuestos.borrar(impuestoEncontrado);
      }
      
      return impuestoEncontrado;
      
    }

    
    public Lista getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(Lista impuestos) {
        this.impuestos = impuestos;
    }
    
    

}
