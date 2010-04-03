/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgClasesDeNegocio.AdministracionDePersonal;

import java.util.Date;
import pkgRecursosDeSoporte.ClaseBase;

/**
 *
 * @author Armando
 */
public class Sesion implements Comparable{

    private Date horaInicio=new Date();
    private Date fechaInicio=new Date();
    private Date horaFin=new Date();
    private Date fechaFin=new Date();
    
     private ClaseBase atributosBD=new ClaseBase();
   
    public Object getAttribute(String nombre) {  
     return this.atributosBD.getAttribute(nombre);
    }
 
    public void addAttribute(String nombre, Object valor) {
     if (valor!=null && nombre!=null) this.atributosBD.setAttribute(nombre,valor);
    }
    

    public Sesion() {
    }
    
    public Sesion(Date horaInicio,Date fechaInicio,Date horaFin,Date fechaFin) {
      this.horaInicio=horaInicio;
      this.fechaInicio=fechaInicio;
      this.horaFin=horaFin;
      this.fechaFin=fechaFin;   
    }
    
    public int compareTo(Object o) {
        Sesion otro=(Sesion) o;
       
        return otro.fechaInicio.compareTo(this.fechaInicio);
    }
    
    

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

   
    
    
}
