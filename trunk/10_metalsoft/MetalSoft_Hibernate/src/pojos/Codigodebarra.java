package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Codigodebarra generated by hbm2java
 */
public class Codigodebarra  implements java.io.Serializable {


     private long idcodigo;
     private String descripcion;
     private String codigo;
     private Set<Materiaprima> materiaprimas = new HashSet<Materiaprima>(0);
     private Set<Productoreal> productoreals = new HashSet<Productoreal>(0);
     private Set<Piezareal> piezareals = new HashSet<Piezareal>(0);

    public Codigodebarra() {
    }

	
    public Codigodebarra(long idcodigo) {
        this.idcodigo = idcodigo;
    }
    public Codigodebarra(long idcodigo, String descripcion, String codigo, Set<Materiaprima> materiaprimas, Set<Productoreal> productoreals, Set<Piezareal> piezareals) {
       this.idcodigo = idcodigo;
       this.descripcion = descripcion;
       this.codigo = codigo;
       this.materiaprimas = materiaprimas;
       this.productoreals = productoreals;
       this.piezareals = piezareals;
    }
   
    public long getIdcodigo() {
        return this.idcodigo;
    }
    
    public void setIdcodigo(long idcodigo) {
        this.idcodigo = idcodigo;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getCodigo() {
        return this.codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public Set<Materiaprima> getMateriaprimas() {
        return this.materiaprimas;
    }
    
    public void setMateriaprimas(Set<Materiaprima> materiaprimas) {
        this.materiaprimas = materiaprimas;
    }
    public Set<Productoreal> getProductoreals() {
        return this.productoreals;
    }
    
    public void setProductoreals(Set<Productoreal> productoreals) {
        this.productoreals = productoreals;
    }
    public Set<Piezareal> getPiezareals() {
        return this.piezareals;
    }
    
    public void setPiezareals(Set<Piezareal> piezareals) {
        this.piezareals = piezareals;
    }




}


