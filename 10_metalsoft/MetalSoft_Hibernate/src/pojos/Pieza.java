package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Pieza generated by hbm2java
 */
public class Pieza  implements java.io.Serializable {


     private long id;
     private Materiaprima materiaprima;
     private Matriz matriz;
     private Unidadmedida unidadmedida;
     private String nombre;
     private Long tipomaterial;
     private BigDecimal alto;
     private BigDecimal ancho;
     private BigDecimal largo;
     private Set<Detalleplanificacionproduccion> detalleplanificacionproduccions = new HashSet<Detalleplanificacionproduccion>(0);
     private Set<Detalleproductopresupuesto> detalleproductopresupuestos = new HashSet<Detalleproductopresupuesto>(0);

    public Pieza() {
    }

	
    public Pieza(long idpieza) {
        this.id = idpieza;
    }
    public Pieza(long idpieza, Materiaprima materiaprima, Matriz matriz, Unidadmedida unidadmedida, String nombre, Long tipomaterial, BigDecimal alto, BigDecimal ancho, BigDecimal largo, Set<Detalleplanificacionproduccion> detalleplanificacionproduccions, Set<Detalleproductopresupuesto> detalleproductopresupuestos) {
       this.id = idpieza;
       this.materiaprima = materiaprima;
       this.matriz = matriz;
       this.unidadmedida = unidadmedida;
       this.nombre = nombre;
       this.tipomaterial = tipomaterial;
       this.alto = alto;
       this.ancho = ancho;
       this.largo = largo;
       this.detalleplanificacionproduccions = detalleplanificacionproduccions;
       this.detalleproductopresupuestos = detalleproductopresupuestos;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Materiaprima getMateriaprima() {
        return this.materiaprima;
    }
    
    public void setMateriaprima(Materiaprima materiaprima) {
        this.materiaprima = materiaprima;
    }
    public Matriz getMatriz() {
        return this.matriz;
    }
    
    public void setMatriz(Matriz matriz) {
        this.matriz = matriz;
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
    public Long getTipomaterial() {
        return this.tipomaterial;
    }
    
    public void setTipomaterial(Long tipomaterial) {
        this.tipomaterial = tipomaterial;
    }
    public BigDecimal getAlto() {
        return this.alto;
    }
    
    public void setAlto(BigDecimal alto) {
        this.alto = alto;
    }
    public BigDecimal getAncho() {
        return this.ancho;
    }
    
    public void setAncho(BigDecimal ancho) {
        this.ancho = ancho;
    }
    public BigDecimal getLargo() {
        return this.largo;
    }
    
    public void setLargo(BigDecimal largo) {
        this.largo = largo;
    }
    public Set<Detalleplanificacionproduccion> getDetalleplanificacionproduccions() {
        return this.detalleplanificacionproduccions;
    }
    
    public void setDetalleplanificacionproduccions(Set<Detalleplanificacionproduccion> detalleplanificacionproduccions) {
        this.detalleplanificacionproduccions = detalleplanificacionproduccions;
    }
    public Set<Detalleproductopresupuesto> getDetalleproductopresupuestos() {
        return this.detalleproductopresupuestos;
    }
    
    public void setDetalleproductopresupuestos(Set<Detalleproductopresupuesto> detalleproductopresupuestos) {
        this.detalleproductopresupuestos = detalleproductopresupuestos;
    }




}

