package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Estadoremito generated by hbm2java
 */
public class Estadoremito  implements java.io.Serializable {


     private long idestado;
     private String nombre;
     private String descripcion;
     private Set<Remito> remitos = new HashSet<Remito>(0);

    public Estadoremito() {
    }

	
    public Estadoremito(long idestado) {
        this.idestado = idestado;
    }
    public Estadoremito(long idestado, String nombre, String descripcion, Set<Remito> remitos) {
       this.idestado = idestado;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.remitos = remitos;
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
    public Set<Remito> getRemitos() {
        return this.remitos;
    }
    
    public void setRemitos(Set<Remito> remitos) {
        this.remitos = remitos;
    }




}

