package pojos;
// Generated 18/10/2010 10:12:42 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Detallepiezacalidadpresupuesto generated by hbm2java
 */
public class Detallepiezacalidadpresupuesto  implements java.io.Serializable {


     private long iddetalle;
     private Procesocalidad procesocalidad;
     private Detalleproductopresupuesto detalleproductopresupuesto;
     private Integer cantprocesocalidad;
     private Date duracionxpieza;

    public Detallepiezacalidadpresupuesto() {
    }

	
    public Detallepiezacalidadpresupuesto(long iddetalle) {
        this.iddetalle = iddetalle;
    }
    public Detallepiezacalidadpresupuesto(long iddetalle, Procesocalidad procesocalidad, Detalleproductopresupuesto detalleproductopresupuesto, Integer cantprocesocalidad, Date duracionxpieza) {
       this.iddetalle = iddetalle;
       this.procesocalidad = procesocalidad;
       this.detalleproductopresupuesto = detalleproductopresupuesto;
       this.cantprocesocalidad = cantprocesocalidad;
       this.duracionxpieza = duracionxpieza;
    }
   
    public long getIddetalle() {
        return this.iddetalle;
    }
    
    public void setIddetalle(long iddetalle) {
        this.iddetalle = iddetalle;
    }
    public Procesocalidad getProcesocalidad() {
        return this.procesocalidad;
    }
    
    public void setProcesocalidad(Procesocalidad procesocalidad) {
        this.procesocalidad = procesocalidad;
    }
    public Detalleproductopresupuesto getDetalleproductopresupuesto() {
        return this.detalleproductopresupuesto;
    }
    
    public void setDetalleproductopresupuesto(Detalleproductopresupuesto detalleproductopresupuesto) {
        this.detalleproductopresupuesto = detalleproductopresupuesto;
    }
    public Integer getCantprocesocalidad() {
        return this.cantprocesocalidad;
    }
    
    public void setCantprocesocalidad(Integer cantprocesocalidad) {
        this.cantprocesocalidad = cantprocesocalidad;
    }
    public Date getDuracionxpieza() {
        return this.duracionxpieza;
    }
    
    public void setDuracionxpieza(Date duracionxpieza) {
        this.duracionxpieza = duracionxpieza;
    }




}


