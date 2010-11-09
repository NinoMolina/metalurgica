package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Estadoejecplanifpedido generated by hbm2java
 */
public class Estadoejecplanifpedido  implements java.io.Serializable {


     private long idestado;
     private String nombre;
     private String descripcion;
     private Set<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccions = new HashSet<Ejecucionplanificacionproduccion>(0);

    public Estadoejecplanifpedido() {
    }

	
    public Estadoejecplanifpedido(long idestado) {
        this.idestado = idestado;
    }
    public Estadoejecplanifpedido(long idestado, String nombre, String descripcion, Set<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccions) {
       this.idestado = idestado;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.ejecucionplanificacionproduccions = ejecucionplanificacionproduccions;
    }
   
    public long getIdestado() {
        return this.idestado;
    }
    
    public void setIdestado(long idestado) {
        this.idestado = idestado;
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
    public Set<Ejecucionplanificacionproduccion> getEjecucionplanificacionproduccions() {
        return this.ejecucionplanificacionproduccions;
    }
    
    public void setEjecucionplanificacionproduccions(Set<Ejecucionplanificacionproduccion> ejecucionplanificacionproduccions) {
        this.ejecucionplanificacionproduccions = ejecucionplanificacionproduccions;
    }




}

