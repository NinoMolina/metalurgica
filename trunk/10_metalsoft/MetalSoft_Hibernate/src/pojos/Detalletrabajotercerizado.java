package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Detalletrabajotercerizado generated by hbm2java
 */
public class Detalletrabajotercerizado  implements java.io.Serializable {


     private DetalletrabajotercerizadoId id;
     private Estadodetalletrabajotercerizado estadodetalletrabajotercerizado;
     private Trabajotercerizado trabajotercerizado;
     private Etapadeproduccion etapadeproduccion;
     private Double montoparcial;
     private Integer cantidad;
     private String descripcion;
     private Date fechaenvioreal;
     private Date fechaentregareal;
     private Long pieza;

    public Detalletrabajotercerizado() {
    }

	
    public Detalletrabajotercerizado(DetalletrabajotercerizadoId id, Trabajotercerizado trabajotercerizado) {
        this.id = id;
        this.trabajotercerizado = trabajotercerizado;
    }
    public Detalletrabajotercerizado(DetalletrabajotercerizadoId id, Estadodetalletrabajotercerizado estadodetalletrabajotercerizado, Trabajotercerizado trabajotercerizado, Etapadeproduccion etapadeproduccion, Double montoparcial, Integer cantidad, String descripcion, Date fechaenvioreal, Date fechaentregareal, Long pieza) {
       this.id = id;
       this.estadodetalletrabajotercerizado = estadodetalletrabajotercerizado;
       this.trabajotercerizado = trabajotercerizado;
       this.etapadeproduccion = etapadeproduccion;
       this.montoparcial = montoparcial;
       this.cantidad = cantidad;
       this.descripcion = descripcion;
       this.fechaenvioreal = fechaenvioreal;
       this.fechaentregareal = fechaentregareal;
       this.pieza = pieza;
    }
   
    public DetalletrabajotercerizadoId getId() {
        return this.id;
    }
    
    public void setId(DetalletrabajotercerizadoId id) {
        this.id = id;
    }
    public Estadodetalletrabajotercerizado getEstadodetalletrabajotercerizado() {
        return this.estadodetalletrabajotercerizado;
    }
    
    public void setEstadodetalletrabajotercerizado(Estadodetalletrabajotercerizado estadodetalletrabajotercerizado) {
        this.estadodetalletrabajotercerizado = estadodetalletrabajotercerizado;
    }
    public Trabajotercerizado getTrabajotercerizado() {
        return this.trabajotercerizado;
    }
    
    public void setTrabajotercerizado(Trabajotercerizado trabajotercerizado) {
        this.trabajotercerizado = trabajotercerizado;
    }
    public Etapadeproduccion getEtapadeproduccion() {
        return this.etapadeproduccion;
    }
    
    public void setEtapadeproduccion(Etapadeproduccion etapadeproduccion) {
        this.etapadeproduccion = etapadeproduccion;
    }
    public Double getMontoparcial() {
        return this.montoparcial;
    }
    
    public void setMontoparcial(Double montoparcial) {
        this.montoparcial = montoparcial;
    }
    public Integer getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Date getFechaenvioreal() {
        return this.fechaenvioreal;
    }
    
    public void setFechaenvioreal(Date fechaenvioreal) {
        this.fechaenvioreal = fechaenvioreal;
    }
    public Date getFechaentregareal() {
        return this.fechaentregareal;
    }
    
    public void setFechaentregareal(Date fechaentregareal) {
        this.fechaentregareal = fechaentregareal;
    }
    public Long getPieza() {
        return this.pieza;
    }
    
    public void setPieza(Long pieza) {
        this.pieza = pieza;
    }




}

