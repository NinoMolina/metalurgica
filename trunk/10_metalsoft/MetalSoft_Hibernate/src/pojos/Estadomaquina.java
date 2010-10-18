package pojos;
// Generated 18/10/2010 10:12:42 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Estadomaquina generated by hbm2java
 */
public class Estadomaquina  implements java.io.Serializable {


     private long idestado;
     private String nombre;
     private String descripcion;
     private Set<Maquina> maquinas = new HashSet<Maquina>(0);

    public Estadomaquina() {
    }

	
    public Estadomaquina(long idestado) {
        this.idestado = idestado;
    }
    public Estadomaquina(long idestado, String nombre, String descripcion, Set<Maquina> maquinas) {
       this.idestado = idestado;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.maquinas = maquinas;
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
    public Set<Maquina> getMaquinas() {
        return this.maquinas;
    }
    
    public void setMaquinas(Set<Maquina> maquinas) {
        this.maquinas = maquinas;
    }




}


