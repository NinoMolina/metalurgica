package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Estadoplanificacionproduccion generated by hbm2java
 */
public class Estadoplanificacionproduccion  implements java.io.Serializable {


     private long id;
     private String nombre;
     private String descripcion;
     private Set<Planificacionproduccion> planificacionproduccions = new HashSet<Planificacionproduccion>(0);

    public Estadoplanificacionproduccion() {
    }

	
    public Estadoplanificacionproduccion(long id) {
        this.id = id;
    }
    public Estadoplanificacionproduccion(long id, String nombre, String descripcion, Set<Planificacionproduccion> planificacionproduccions) {
       this.id = id;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.planificacionproduccions = planificacionproduccions;
    }
   
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Set<Planificacionproduccion> getPlanificacionproduccions() {
        return this.planificacionproduccions;
    }
    
    public void setPlanificacionproduccions(Set<Planificacionproduccion> planificacionproduccions) {
        this.planificacionproduccions = planificacionproduccions;
    }




}


