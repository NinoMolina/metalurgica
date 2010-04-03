/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgClasesDeNegocio.Compras;

import java.util.Date;
import pkgClasesDeNegocio.Ventas.TipoFactura;
import pkgRecursosDeSoporte.ClaseBase;

/**
 *
 * @author Armando
 */
public class FacturaProveedor implements Comparable{

    private int numero;
    private Date fecha=new Date();
    private double montoTotal;
    private TipoFactura tipoFactura=new TipoFactura();
    
     private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    

    
    public FacturaProveedor() {
    }
    
    
    public FacturaProveedor(int numero, Date fecha, double montoTotal, TipoFactura tipoFactura) {
        this.numero = numero;
        this.fecha = fecha;
        this.montoTotal = montoTotal;
        this.tipoFactura = tipoFactura;
    }

    public int compareTo(Object o) {
          FacturaProveedor otro=(FacturaProveedor) o;
          return otro.numero-this.numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public TipoFactura getTipoFactura() {
        return tipoFactura;
    }

    public void setTipoFactura(TipoFactura tipoFactura) {
        this.tipoFactura = tipoFactura;
    }

    
    
}
