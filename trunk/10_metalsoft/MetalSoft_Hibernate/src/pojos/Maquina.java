package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Maquina generated by hbm2java
 */
public class Maquina  implements java.io.Serializable {


     private long id;
     private Marca marca;
     private Estadomaquina estadomaquina;
     private Tipomaquina tipomaquina;
     private Unidadmedida unidadmedida;
     private String nombre;
     private String descripcion;
     private Date fechaalta;
     private Date fechabaja;
     private Date tiempocapacidadproduccion;
     private Set<Detalleplanificacionproduccion> detalleplanificacionproduccions = new HashSet<Detalleplanificacionproduccion>(0);

    public Maquina() {
    }

	
    public Maquina(long idmaquina) {
        this.id = idmaquina;
    }
    public Maquina(long idmaquina, Marca marca, Estadomaquina estadomaquina, Tipomaquina tipomaquina, Unidadmedida unidadmedida, String nombre, String descripcion, Date fechaalta, Date fechabaja, Date tiempocapacidadproduccion, Set<Detalleplanificacionproduccion> detalleplanificacionproduccions) {
       this.id = idmaquina;
       this.marca = marca;
       this.estadomaquina = estadomaquina;
       this.tipomaquina = tipomaquina;
       this.unidadmedida = unidadmedida;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.fechaalta = fechaalta;
       this.fechabaja = fechabaja;
       this.tiempocapacidadproduccion = tiempocapacidadproduccion;
       this.detalleplanificacionproduccions = detalleplanificacionproduccions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
   

    public Marca getMarca() {
        return this.marca;
    }
    
    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    public Estadomaquina getEstadomaquina() {
        return this.estadomaquina;
    }
    
    public void setEstadomaquina(Estadomaquina estadomaquina) {
        this.estadomaquina = estadomaquina;
    }
    public Tipomaquina getTipomaquina() {
        return this.tipomaquina;
    }
    
    public void setTipomaquina(Tipomaquina tipomaquina) {
        this.tipomaquina = tipomaquina;
    }
    public Unidadmedida getUnidadmedida() {
        return this.unidadmedida;
    }
    
    public void setUnidadmedida(Unidadmedida unidadmedida) {
        this.unidadmedida = unidadmedida;
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
    public Date getFechaalta() {
        return this.fechaalta;
    }
    
    public void setFechaalta(Date fechaalta) {
        this.fechaalta = fechaalta;
    }
    public Date getFechabaja() {
        return this.fechabaja;
    }
    
    public void setFechabaja(Date fechabaja) {
        this.fechabaja = fechabaja;
    }
    public Date getTiempocapacidadproduccion() {
        return this.tiempocapacidadproduccion;
    }
    
    public void setTiempocapacidadproduccion(Date tiempocapacidadproduccion) {
        this.tiempocapacidadproduccion = tiempocapacidadproduccion;
    }
    public Set<Detalleplanificacionproduccion> getDetalleplanificacionproduccions() {
        return this.detalleplanificacionproduccions;
    }
    
    public void setDetalleplanificacionproduccions(Set<Detalleplanificacionproduccion> detalleplanificacionproduccions) {
        this.detalleplanificacionproduccions = detalleplanificacionproduccions;
    }

    @Override
    public String toString() {
        return getNombre();
    }




}

