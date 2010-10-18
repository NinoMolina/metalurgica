package pojos;
// Generated 18/10/2010 10:12:42 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Rotura generated by hbm2java
 */
public class Rotura  implements java.io.Serializable {


     private long idrotura;
     private String nombre;
     private String descripcion;
     private Set<Detallemantenimientocorrectivo> detallemantenimientocorrectivos = new HashSet<Detallemantenimientocorrectivo>(0);

    public Rotura() {
    }

	
    public Rotura(long idrotura) {
        this.idrotura = idrotura;
    }
    public Rotura(long idrotura, String nombre, String descripcion, Set<Detallemantenimientocorrectivo> detallemantenimientocorrectivos) {
       this.idrotura = idrotura;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.detallemantenimientocorrectivos = detallemantenimientocorrectivos;
    }
   
    public long getIdrotura() {
        return this.idrotura;
    }
    
    public void setIdrotura(long idrotura) {
        this.idrotura = idrotura;
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
    public Set<Detallemantenimientocorrectivo> getDetallemantenimientocorrectivos() {
        return this.detallemantenimientocorrectivos;
    }
    
    public void setDetallemantenimientocorrectivos(Set<Detallemantenimientocorrectivo> detallemantenimientocorrectivos) {
        this.detallemantenimientocorrectivos = detallemantenimientocorrectivos;
    }




}


