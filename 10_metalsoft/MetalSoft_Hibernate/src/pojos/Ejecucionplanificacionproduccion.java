package pojos;
// Generated 18/10/2010 10:12:42 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Ejecucionplanificacionproduccion generated by hbm2java
 */
public class Ejecucionplanificacionproduccion  implements java.io.Serializable {


     private EjecucionplanificacionproduccionId id;
     private Planificacionproduccion planificacionproduccion;
     private Estadoejecplanifpedido estadoejecplanifpedido;
     private Date fechainicio;
     private Date fechafin;
     private Date horainicio;
     private Date horafin;
     private Set<Detalleejecucionplanificacion> detalleejecucionplanificacions = new HashSet<Detalleejecucionplanificacion>(0);

    public Ejecucionplanificacionproduccion() {
    }

	
    public Ejecucionplanificacionproduccion(EjecucionplanificacionproduccionId id, Planificacionproduccion planificacionproduccion) {
        this.id = id;
        this.planificacionproduccion = planificacionproduccion;
    }
    public Ejecucionplanificacionproduccion(EjecucionplanificacionproduccionId id, Planificacionproduccion planificacionproduccion, Estadoejecplanifpedido estadoejecplanifpedido, Date fechainicio, Date fechafin, Date horainicio, Date horafin, Set<Detalleejecucionplanificacion> detalleejecucionplanificacions) {
       this.id = id;
       this.planificacionproduccion = planificacionproduccion;
       this.estadoejecplanifpedido = estadoejecplanifpedido;
       this.fechainicio = fechainicio;
       this.fechafin = fechafin;
       this.horainicio = horainicio;
       this.horafin = horafin;
       this.detalleejecucionplanificacions = detalleejecucionplanificacions;
    }
   
    public EjecucionplanificacionproduccionId getId() {
        return this.id;
    }
    
    public void setId(EjecucionplanificacionproduccionId id) {
        this.id = id;
    }
    public Planificacionproduccion getPlanificacionproduccion() {
        return this.planificacionproduccion;
    }
    
    public void setPlanificacionproduccion(Planificacionproduccion planificacionproduccion) {
        this.planificacionproduccion = planificacionproduccion;
    }
    public Estadoejecplanifpedido getEstadoejecplanifpedido() {
        return this.estadoejecplanifpedido;
    }
    
    public void setEstadoejecplanifpedido(Estadoejecplanifpedido estadoejecplanifpedido) {
        this.estadoejecplanifpedido = estadoejecplanifpedido;
    }
    public Date getFechainicio() {
        return this.fechainicio;
    }
    
    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }
    public Date getFechafin() {
        return this.fechafin;
    }
    
    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }
    public Date getHorainicio() {
        return this.horainicio;
    }
    
    public void setHorainicio(Date horainicio) {
        this.horainicio = horainicio;
    }
    public Date getHorafin() {
        return this.horafin;
    }
    
    public void setHorafin(Date horafin) {
        this.horafin = horafin;
    }
    public Set<Detalleejecucionplanificacion> getDetalleejecucionplanificacions() {
        return this.detalleejecucionplanificacions;
    }
    
    public void setDetalleejecucionplanificacions(Set<Detalleejecucionplanificacion> detalleejecucionplanificacions) {
        this.detalleejecucionplanificacions = detalleejecucionplanificacions;
    }




}


