/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgClasesDeNegocio.Compras;

import java.util.Date;
import pkgClasesDeNegocio.AdministracionDePersonal.Estado;
import pkgRecursosDeSoporte.ClaseBase;
import pkgRecursosDeSoporte.pkgLista.Lista;

/**
 *
 * @author Armando
 */
public class Tanque implements Comparable{

    private int numero;
    private double capacidad;
    private Date fechaInstalacion=new Date();
    private Lista mediciones=new Lista();
    private Estado estado=new Estado();
    private Combustible combustible=new Combustible();
    private double stockTanque;
    
     private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    

    public Tanque() {}
   
    public Tanque(int numero, double capacidad,Date fechaInstalacion,double stockTanque,
                  Lista mediciones,Estado estado,Combustible combustible) {
        this.numero = numero;
        this.capacidad = capacidad;
        this.fechaInstalacion=fechaInstalacion;
        this.stockTanque = stockTanque;
        this.mediciones=mediciones;
        this.estado=estado;
        this.combustible=combustible;
    }
   
    public Tanque getTanqueSiEstado(Estado e){
      int res=this.estado.compareTo(e);
      
      if(res==0){return this;}
      
      return null;
    
    }


   
    public int compareTo(Object o) {
        Tanque otro=(Tanque) o;
        
        return otro.numero-this.numero;
    }
    
   public boolean existeTanque(Tanque tanque){
    int i=this.compareTo(tanque);
    if(i==0) return true;
    return false;   
   }


    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(double capacidad) {
        this.capacidad = capacidad;
    }

    public Date getFechaInstalacion() {
        return fechaInstalacion;
    }

    public void setFechaInstalacion(Date fechaInstalacion) {
        this.fechaInstalacion = fechaInstalacion;
    }

    public Lista getMediciones() {
        return mediciones;
    }

    public void setMediciones(Lista mediciones) {
        this.mediciones = mediciones;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Combustible getCombustible() {
        return combustible;
    }

    public void setCombustible(Combustible combustible) {
        this.combustible = combustible;
    }

    public double getStockTanque() {
        return stockTanque;
    }

    public void setStockTanque(double stockTanque) {
        this.stockTanque = stockTanque;
    }
   
    
    
    
}
