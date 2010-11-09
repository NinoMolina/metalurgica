package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Producto generated by hbm2java
 */
public class Producto  implements java.io.Serializable {


     private long id;
     private Long nroproducto;
     private String nombre;
     private Double preciounitario;
     private String descripcion;
     private Set<Detalleremito> detalleremitos = new HashSet<Detalleremito>(0);
     private Set<Detallereclamocliente> detallereclamoclientes = new HashSet<Detallereclamocliente>(0);
     private Set<Detallepresupuesto> detallepresupuestos = new HashSet<Detallepresupuesto>(0);
     private Set<Detallepedido> detallepedidos = new HashSet<Detallepedido>(0);
     private Set<Detalleproducto> detalleproductos = new HashSet<Detalleproducto>(0);

    public Producto() {
    }

	
    public Producto(long idproducto) {
        this.id = idproducto;
    }
    public Producto(long idproducto, Long nroproducto, String nombre, Double preciounitario, String descripcion, Set<Detalleremito> detalleremitos, Set<Detallereclamocliente> detallereclamoclientes, Set<Detallepresupuesto> detallepresupuestos, Set<Detallepedido> detallepedidos, Set<Detalleproducto> detalleproductos) {
       this.id = idproducto;
       this.nroproducto = nroproducto;
       this.nombre = nombre;
       this.preciounitario = preciounitario;
       this.descripcion = descripcion;
       this.detalleremitos = detalleremitos;
       this.detallereclamoclientes = detallereclamoclientes;
       this.detallepresupuestos = detallepresupuestos;
       this.detallepedidos = detallepedidos;
       this.detalleproductos = detalleproductos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
   

    public Long getNroproducto() {
        return this.nroproducto;
    }
    
    public void setNroproducto(Long nroproducto) {
        this.nroproducto = nroproducto;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getPreciounitario() {
        return this.preciounitario;
    }
    
    public void setPreciounitario(Double preciounitario) {
        this.preciounitario = preciounitario;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Set<Detalleremito> getDetalleremitos() {
        return this.detalleremitos;
    }
    
    public void setDetalleremitos(Set<Detalleremito> detalleremitos) {
        this.detalleremitos = detalleremitos;
    }
    public Set<Detallereclamocliente> getDetallereclamoclientes() {
        return this.detallereclamoclientes;
    }
    
    public void setDetallereclamoclientes(Set<Detallereclamocliente> detallereclamoclientes) {
        this.detallereclamoclientes = detallereclamoclientes;
    }
    public Set<Detallepresupuesto> getDetallepresupuestos() {
        return this.detallepresupuestos;
    }
    
    public void setDetallepresupuestos(Set<Detallepresupuesto> detallepresupuestos) {
        this.detallepresupuestos = detallepresupuestos;
    }
    public Set<Detallepedido> getDetallepedidos() {
        return this.detallepedidos;
    }
    
    public void setDetallepedidos(Set<Detallepedido> detallepedidos) {
        this.detallepedidos = detallepedidos;
    }
    public Set<Detalleproducto> getDetalleproductos() {
        return this.detalleproductos;
    }
    
    public void setDetalleproductos(Set<Detalleproducto> detalleproductos) {
        this.detalleproductos = detalleproductos;
    }




}

