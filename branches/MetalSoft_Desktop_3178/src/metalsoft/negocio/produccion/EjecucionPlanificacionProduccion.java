//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\produccion\\EjecucionPlanificacionProduccion.java

package metalsoft.negocio.produccion;

import java.util.Date;


public class EjecucionPlanificacionProduccion 
{
   private long nroEjecucion;
   private Date fechaInicio;
   private Date fechaFin;
   private Date horaInicio;
   private Date horaFin;
   private EstadoEjecPlanifPedido estado;
   private DetalleEjecucionPlanificacion detalle;
   
   /**
    * @roseuid 4C27ED210111
    */
   public EjecucionPlanificacionProduccion() 
   {
   }

    public EjecucionPlanificacionProduccion(long nroEjecucion, Date fechaInicio, Date fechaFin, Date horaInicio, Date horaFin, EstadoEjecPlanifPedido estado, DetalleEjecucionPlanificacion detalle) {
        this.nroEjecucion = nroEjecucion;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.estado = estado;
        this.detalle = detalle;
    }

    public DetalleEjecucionPlanificacion getDetalle() {
        return detalle;
    }

    public void setDetalle(DetalleEjecucionPlanificacion detalle) {
        this.detalle = detalle;
    }

    public EstadoEjecPlanifPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoEjecPlanifPedido estado) {
        this.estado = estado;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
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

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public long getNroEjecucion() {
        return nroEjecucion;
    }

    public void setNroEjecucion(long nroEjecucion) {
        this.nroEjecucion = nroEjecucion;
    }
   
}
