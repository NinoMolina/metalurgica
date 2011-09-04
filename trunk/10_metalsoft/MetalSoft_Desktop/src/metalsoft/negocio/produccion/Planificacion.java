//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\produccion\\Planificacion.java

package metalsoft.negocio.produccion;

import java.util.Date;


public class Planificacion 
{
   private long nroPlanificacion;
   private Date fechaCreacion;
   private String observaciones;
   private Date fechaFinPrevista;
   private Date fechaInicioPrevista;
   private int cantHorasATrabajar;
   private Date horaInicioPRevista;
   private Date horaFinPrevista;
   
   /**
    * @roseuid 4C27ED140010
    */
   public Planificacion() 
   {
   }

    public int getCantHorasATrabajar() {
        return cantHorasATrabajar;
    }

    public void setCantHorasATrabajar(int cantHorasATrabajar) {
        this.cantHorasATrabajar = cantHorasATrabajar;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaFinPrevista() {
        return fechaFinPrevista;
    }

    public void setFechaFinPrevista(Date fechaFinPrevista) {
        this.fechaFinPrevista = fechaFinPrevista;
    }

    public Date getFechaInicioPrevista() {
        return fechaInicioPrevista;
    }

    public void setFechaInicioPrevista(Date fechaInicioPrevista) {
        this.fechaInicioPrevista = fechaInicioPrevista;
    }

    public Date getHoraFinPrevista() {
        return horaFinPrevista;
    }

    public void setHoraFinPrevista(Date horaFinPrevista) {
        this.horaFinPrevista = horaFinPrevista;
    }

    public Date getHoraInicioPRevista() {
        return horaInicioPRevista;
    }

    public void setHoraInicioPRevista(Date horaInicioPRevista) {
        this.horaInicioPRevista = horaInicioPRevista;
    }

    public long getNroPlanificacion() {
        return nroPlanificacion;
    }

    public void setNroPlanificacion(long nroPlanificacion) {
        this.nroPlanificacion = nroPlanificacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
   


   public void calcFechaFinPrevista() 
   {
    
   }
   
   /**
    * @roseuid 4C18117303A1
    */
   public void calcularHoraFinalizacion() 
   {
    
   }
}
