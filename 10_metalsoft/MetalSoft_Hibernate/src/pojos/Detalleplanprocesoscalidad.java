package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Detalleplanprocesoscalidad generated by hbm2java
 */
public class Detalleplanprocesoscalidad  implements java.io.Serializable {


     private DetalleplanprocesoscalidadId id;
     private Procesocalidad procesocalidad;
     private Planprocesoscalidad planprocesoscalidad;
     private Long idpieza;
     private Date duracionestimada;

    public Detalleplanprocesoscalidad() {
    }

	
    public Detalleplanprocesoscalidad(DetalleplanprocesoscalidadId id, Planprocesoscalidad planprocesoscalidad) {
        this.id = id;
        this.planprocesoscalidad = planprocesoscalidad;
    }
    public Detalleplanprocesoscalidad(DetalleplanprocesoscalidadId id, Procesocalidad procesocalidad, Planprocesoscalidad planprocesoscalidad, Long idpieza, Date duracionestimada) {
       this.id = id;
       this.procesocalidad = procesocalidad;
       this.planprocesoscalidad = planprocesoscalidad;
       this.idpieza = idpieza;
       this.duracionestimada = duracionestimada;
    }
   
    public DetalleplanprocesoscalidadId getId() {
        return this.id;
    }
    
    public void setId(DetalleplanprocesoscalidadId id) {
        this.id = id;
    }
    public Procesocalidad getProcesocalidad() {
        return this.procesocalidad;
    }
    
    public void setProcesocalidad(Procesocalidad procesocalidad) {
        this.procesocalidad = procesocalidad;
    }
    public Planprocesoscalidad getPlanprocesoscalidad() {
        return this.planprocesoscalidad;
    }
    
    public void setPlanprocesoscalidad(Planprocesoscalidad planprocesoscalidad) {
        this.planprocesoscalidad = planprocesoscalidad;
    }
    public Long getIdpieza() {
        return this.idpieza;
    }
    
    public void setIdpieza(Long idpieza) {
        this.idpieza = idpieza;
    }
    public Date getDuracionestimada() {
        return this.duracionestimada;
    }
    
    public void setDuracionestimada(Date duracionestimada) {
        this.duracionestimada = duracionestimada;
    }




}


