package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Planificacioncalidad generated by hbm2java
 */
public class Planificacioncalidad  implements java.io.Serializable {


     private long idplanificacion;
     private Pedido pedido;
     private Long nroplanificacion;
     private Date fechacreacion;
     private String observaciones;
     private Date fechainicioprevista;
     private Date fechafinprevista;
     private Set<Detalleplanificacioncalidad> detalleplanificacioncalidads = new HashSet<Detalleplanificacioncalidad>(0);
     private Set<Ejecucionplanificacioncalidad> ejecucionplanificacioncalidads = new HashSet<Ejecucionplanificacioncalidad>(0);

    public Planificacioncalidad() {
    }

	
    public Planificacioncalidad(long idplanificacion) {
        this.idplanificacion = idplanificacion;
    }
    public Planificacioncalidad(long idplanificacion, Pedido pedido, Long nroplanificacion, Date fechacreacion, String observaciones, Date fechainicioprevista, Date fechafinprevista, Set<Detalleplanificacioncalidad> detalleplanificacioncalidads, Set<Ejecucionplanificacioncalidad> ejecucionplanificacioncalidads) {
       this.idplanificacion = idplanificacion;
       this.pedido = pedido;
       this.nroplanificacion = nroplanificacion;
       this.fechacreacion = fechacreacion;
       this.observaciones = observaciones;
       this.fechainicioprevista = fechainicioprevista;
       this.fechafinprevista = fechafinprevista;
       this.detalleplanificacioncalidads = detalleplanificacioncalidads;
       this.ejecucionplanificacioncalidads = ejecucionplanificacioncalidads;
    }
   
    public long getIdplanificacion() {
        return this.idplanificacion;
    }
    
    public void setIdplanificacion(long idplanificacion) {
        this.idplanificacion = idplanificacion;
    }
    public Pedido getPedido() {
        return this.pedido;
    }
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public Long getNroplanificacion() {
        return this.nroplanificacion;
    }
    
    public void setNroplanificacion(Long nroplanificacion) {
        this.nroplanificacion = nroplanificacion;
    }
    public Date getFechacreacion() {
        return this.fechacreacion;
    }
    
    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }
    public String getObservaciones() {
        return this.observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public Date getFechainicioprevista() {
        return this.fechainicioprevista;
    }
    
    public void setFechainicioprevista(Date fechainicioprevista) {
        this.fechainicioprevista = fechainicioprevista;
    }
    public Date getFechafinprevista() {
        return this.fechafinprevista;
    }
    
    public void setFechafinprevista(Date fechafinprevista) {
        this.fechafinprevista = fechafinprevista;
    }
    public Set<Detalleplanificacioncalidad> getDetalleplanificacioncalidads() {
        return this.detalleplanificacioncalidads;
    }
    
    public void setDetalleplanificacioncalidads(Set<Detalleplanificacioncalidad> detalleplanificacioncalidads) {
        this.detalleplanificacioncalidads = detalleplanificacioncalidads;
    }
    public Set<Ejecucionplanificacioncalidad> getEjecucionplanificacioncalidads() {
        return this.ejecucionplanificacioncalidads;
    }
    
    public void setEjecucionplanificacioncalidads(Set<Ejecucionplanificacioncalidad> ejecucionplanificacioncalidads) {
        this.ejecucionplanificacioncalidads = ejecucionplanificacioncalidads;
    }




}


