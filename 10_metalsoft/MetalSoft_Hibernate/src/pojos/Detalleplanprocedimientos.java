package pojos;
// Generated 18/10/2010 10:12:42 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Detalleplanprocedimientos generated by hbm2java
 */
public class Detalleplanprocedimientos  implements java.io.Serializable {


     private DetalleplanprocedimientosId id;
     private Etapadeproduccion etapadeproduccion;
     private Planprocedimientos planprocedimientos;
     private Long idpieza;
     private Date duracionestimada;

    public Detalleplanprocedimientos() {
    }

	
    public Detalleplanprocedimientos(DetalleplanprocedimientosId id, Planprocedimientos planprocedimientos) {
        this.id = id;
        this.planprocedimientos = planprocedimientos;
    }
    public Detalleplanprocedimientos(DetalleplanprocedimientosId id, Etapadeproduccion etapadeproduccion, Planprocedimientos planprocedimientos, Long idpieza, Date duracionestimada) {
       this.id = id;
       this.etapadeproduccion = etapadeproduccion;
       this.planprocedimientos = planprocedimientos;
       this.idpieza = idpieza;
       this.duracionestimada = duracionestimada;
    }
   
    public DetalleplanprocedimientosId getId() {
        return this.id;
    }
    
    public void setId(DetalleplanprocedimientosId id) {
        this.id = id;
    }
    public Etapadeproduccion getEtapadeproduccion() {
        return this.etapadeproduccion;
    }
    
    public void setEtapadeproduccion(Etapadeproduccion etapadeproduccion) {
        this.etapadeproduccion = etapadeproduccion;
    }
    public Planprocedimientos getPlanprocedimientos() {
        return this.planprocedimientos;
    }
    
    public void setPlanprocedimientos(Planprocedimientos planprocedimientos) {
        this.planprocedimientos = planprocedimientos;
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


