package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Estadodetallecompra generated by hbm2java
 */
public class Estadodetallecompra  implements java.io.Serializable {


     private long idestado;
     private String nombre;
     private String descripcion;
     private Set<Detallecompra> detallecompras = new HashSet<Detallecompra>(0);

    public Estadodetallecompra() {
    }

	
    public Estadodetallecompra(long idestado) {
        this.idestado = idestado;
    }
    public Estadodetallecompra(long idestado, String nombre, String descripcion, Set<Detallecompra> detallecompras) {
       this.idestado = idestado;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.detallecompras = detallecompras;
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
    public Set<Detallecompra> getDetallecompras() {
        return this.detallecompras;
    }
    
    public void setDetallecompras(Set<Detallecompra> detallecompras) {
        this.detallecompras = detallecompras;
    }




}


