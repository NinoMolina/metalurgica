package pojos;
// Generated 12/10/2010 01:33:18 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Detalleejecucionplanificacioncalidad generated by hbm2java
 */
public class Detalleejecucionplanificacioncalidad  implements java.io.Serializable {


     private DetalleejecucionplanificacioncalidadId id;
     private Ejecucionplanificacioncalidad ejecucionplanificacioncalidad;
     private Ejecucionprocesocalidad ejecucionprocesocalidad;
     private Long pieza;
     private Long piezareal;
     private Set<Detalleplanificacioncalidad> detalleplanificacioncalidads = new HashSet<Detalleplanificacioncalidad>(0);

    public Detalleejecucionplanificacioncalidad() {
    }

	
    public Detalleejecucionplanificacioncalidad(DetalleejecucionplanificacioncalidadId id, Ejecucionplanificacioncalidad ejecucionplanificacioncalidad) {
        this.id = id;
        this.ejecucionplanificacioncalidad = ejecucionplanificacioncalidad;
    }
    public Detalleejecucionplanificacioncalidad(DetalleejecucionplanificacioncalidadId id, Ejecucionplanificacioncalidad ejecucionplanificacioncalidad, Ejecucionprocesocalidad ejecucionprocesocalidad, Long pieza, Long piezareal, Set<Detalleplanificacioncalidad> detalleplanificacioncalidads) {
       this.id = id;
       this.ejecucionplanificacioncalidad = ejecucionplanificacioncalidad;
       this.ejecucionprocesocalidad = ejecucionprocesocalidad;
       this.pieza = pieza;
       this.piezareal = piezareal;
       this.detalleplanificacioncalidads = detalleplanificacioncalidads;
    }
   
    public DetalleejecucionplanificacioncalidadId getId() {
        return this.id;
    }
    
    public void setId(DetalleejecucionplanificacioncalidadId id) {
        this.id = id;
    }
    public Ejecucionplanificacioncalidad getEjecucionplanificacioncalidad() {
        return this.ejecucionplanificacioncalidad;
    }
    
    public void setEjecucionplanificacioncalidad(Ejecucionplanificacioncalidad ejecucionplanificacioncalidad) {
        this.ejecucionplanificacioncalidad = ejecucionplanificacioncalidad;
    }
    public Ejecucionprocesocalidad getEjecucionprocesocalidad() {
        return this.ejecucionprocesocalidad;
    }
    
    public void setEjecucionprocesocalidad(Ejecucionprocesocalidad ejecucionprocesocalidad) {
        this.ejecucionprocesocalidad = ejecucionprocesocalidad;
    }
    public Long getPieza() {
        return this.pieza;
    }
    
    public void setPieza(Long pieza) {
        this.pieza = pieza;
    }
    public Long getPiezareal() {
        return this.piezareal;
    }
    
    public void setPiezareal(Long piezareal) {
        this.piezareal = piezareal;
    }
    public Set<Detalleplanificacioncalidad> getDetalleplanificacioncalidads() {
        return this.detalleplanificacioncalidads;
    }
    
    public void setDetalleplanificacioncalidads(Set<Detalleplanificacioncalidad> detalleplanificacioncalidads) {
        this.detalleplanificacioncalidads = detalleplanificacioncalidads;
    }




}


