package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Detalleplanificacioncalidad generated by hbm2java
 */
public class Detalleplanificacioncalidad  implements java.io.Serializable {


     private DetalleplanificacioncalidadId id;
     private Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad;
     private Procesocalidad procesocalidad;
     private Planificacioncalidad planificacioncalidad;
     private Date fechainicioplan;
     private Date fechafinplan;
     private Date horainicioplan;
     private Date horafinplan;
     private Long pieza;

    public Detalleplanificacioncalidad() {
    }

	
    public Detalleplanificacioncalidad(DetalleplanificacioncalidadId id, Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad, Planificacioncalidad planificacioncalidad) {
        this.id = id;
        this.detalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidad;
        this.planificacioncalidad = planificacioncalidad;
    }
    public Detalleplanificacioncalidad(DetalleplanificacioncalidadId id, Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad, Procesocalidad procesocalidad, Planificacioncalidad planificacioncalidad, Date fechainicioplan, Date fechafinplan, Date horainicioplan, Date horafinplan, Long pieza) {
       this.id = id;
       this.detalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidad;
       this.procesocalidad = procesocalidad;
       this.planificacioncalidad = planificacioncalidad;
       this.fechainicioplan = fechainicioplan;
       this.fechafinplan = fechafinplan;
       this.horainicioplan = horainicioplan;
       this.horafinplan = horafinplan;
       this.pieza = pieza;
    }
   
    public DetalleplanificacioncalidadId getId() {
        return this.id;
    }
    
    public void setId(DetalleplanificacioncalidadId id) {
        this.id = id;
    }
    public Detalleejecucionplanificacioncalidad getDetalleejecucionplanificacioncalidad() {
        return this.detalleejecucionplanificacioncalidad;
    }
    
    public void setDetalleejecucionplanificacioncalidad(Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad) {
        this.detalleejecucionplanificacioncalidad = detalleejecucionplanificacioncalidad;
    }
    public Procesocalidad getProcesocalidad() {
        return this.procesocalidad;
    }
    
    public void setProcesocalidad(Procesocalidad procesocalidad) {
        this.procesocalidad = procesocalidad;
    }
    public Planificacioncalidad getPlanificacioncalidad() {
        return this.planificacioncalidad;
    }
    
    public void setPlanificacioncalidad(Planificacioncalidad planificacioncalidad) {
        this.planificacioncalidad = planificacioncalidad;
    }
    public Date getFechainicioplan() {
        return this.fechainicioplan;
    }
    
    public void setFechainicioplan(Date fechainicioplan) {
        this.fechainicioplan = fechainicioplan;
    }
    public Date getFechafinplan() {
        return this.fechafinplan;
    }
    
    public void setFechafinplan(Date fechafinplan) {
        this.fechafinplan = fechafinplan;
    }
    public Date getHorainicioplan() {
        return this.horainicioplan;
    }
    
    public void setHorainicioplan(Date horainicioplan) {
        this.horainicioplan = horainicioplan;
    }
    public Date getHorafinplan() {
        return this.horafinplan;
    }
    
    public void setHorafinplan(Date horafinplan) {
        this.horafinplan = horafinplan;
    }
    public Long getPieza() {
        return this.pieza;
    }
    
    public void setPieza(Long pieza) {
        this.pieza = pieza;
    }




}

