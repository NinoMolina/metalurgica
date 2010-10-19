package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Piezaxetapadeproduccion generated by hbm2java
 */
public class Piezaxetapadeproduccion  implements java.io.Serializable {


     private PiezaxetapadeproduccionId id;
     private Etapadeproduccion etapadeproduccion;
     private Date duracion;
     private String descripcion;

    public Piezaxetapadeproduccion() {
    }

	
    public Piezaxetapadeproduccion(PiezaxetapadeproduccionId id, Etapadeproduccion etapadeproduccion) {
        this.id = id;
        this.etapadeproduccion = etapadeproduccion;
    }
    public Piezaxetapadeproduccion(PiezaxetapadeproduccionId id, Etapadeproduccion etapadeproduccion, Date duracion, String descripcion) {
       this.id = id;
       this.etapadeproduccion = etapadeproduccion;
       this.duracion = duracion;
       this.descripcion = descripcion;
    }
   
    public PiezaxetapadeproduccionId getId() {
        return this.id;
    }
    
    public void setId(PiezaxetapadeproduccionId id) {
        this.id = id;
    }
    public Etapadeproduccion getEtapadeproduccion() {
        return this.etapadeproduccion;
    }
    
    public void setEtapadeproduccion(Etapadeproduccion etapadeproduccion) {
        this.etapadeproduccion = etapadeproduccion;
    }
    public Date getDuracion() {
        return this.duracion;
    }
    
    public void setDuracion(Date duracion) {
        this.duracion = duracion;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }




}


