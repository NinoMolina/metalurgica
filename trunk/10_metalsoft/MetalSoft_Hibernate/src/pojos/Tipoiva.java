package pojos;
// Generated 18/10/2010 10:12:42 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Tipoiva generated by hbm2java
 */
public class Tipoiva  implements java.io.Serializable {


     private long idtipoiva;
     private String nombre;
     private String descripcion;
     private Set<Factura> facturas = new HashSet<Factura>(0);

    public Tipoiva() {
    }

	
    public Tipoiva(long idtipoiva) {
        this.idtipoiva = idtipoiva;
    }
    public Tipoiva(long idtipoiva, String nombre, String descripcion, Set<Factura> facturas) {
       this.idtipoiva = idtipoiva;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.facturas = facturas;
    }
   
    public long getIdtipoiva() {
        return this.idtipoiva;
    }
    
    public void setIdtipoiva(long idtipoiva) {
        this.idtipoiva = idtipoiva;
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
    public Set<Factura> getFacturas() {
        return this.facturas;
    }
    
    public void setFacturas(Set<Factura> facturas) {
        this.facturas = facturas;
    }




}


