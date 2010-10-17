package pojos;
// Generated 17/10/2010 04:46:17 by Hibernate Tools 3.2.1.GA


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Pieza generated by hbm2java
 */
public class Pieza  implements java.io.Serializable {


     private long idpieza;
     private Materiaprima materiaprima;
     private Matriz matriz;
     private String nombre;
     private Long tipomaterial;
     private BigDecimal alto;
     private BigDecimal ancho;
     private BigDecimal largo;
     private Set<Detalleplanificacioncalidad> detalleplanificacioncalidads = new HashSet<Detalleplanificacioncalidad>(0);
     private Set<Detalletrabajotercerizado> detalletrabajotercerizados = new HashSet<Detalletrabajotercerizado>(0);
     private Set<Piezareal> piezareals = new HashSet<Piezareal>(0);
     private Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimas = new HashSet<Detallerequerimientosmateriaprima>(0);
     private Set<Piezaxetapadeproduccion> piezaxetapadeproduccions = new HashSet<Piezaxetapadeproduccion>(0);
     private Set<Detalleproductopresupuesto> detalleproductopresupuestos = new HashSet<Detalleproductopresupuesto>(0);
     private Set<Detalleplanprocedimientos> detalleplanprocedimientoses = new HashSet<Detalleplanprocedimientos>(0);
     private Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidads = new HashSet<Detalleplanprocesoscalidad>(0);
     private Set<Detalleplanificacionproduccion> detalleplanificacionproduccions = new HashSet<Detalleplanificacionproduccion>(0);
     private Set<Detalleproducto> detalleproductos = new HashSet<Detalleproducto>(0);

    public Pieza() {
    }

	
    public Pieza(long idpieza) {
        this.idpieza = idpieza;
    }
    public Pieza(long idpieza, Materiaprima materiaprima, Matriz matriz, String nombre, Long tipomaterial, BigDecimal alto, BigDecimal ancho, BigDecimal largo, Set<Detalleplanificacioncalidad> detalleplanificacioncalidads, Set<Detalletrabajotercerizado> detalletrabajotercerizados, Set<Piezareal> piezareals, Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimas, Set<Piezaxetapadeproduccion> piezaxetapadeproduccions, Set<Detalleproductopresupuesto> detalleproductopresupuestos, Set<Detalleplanprocedimientos> detalleplanprocedimientoses, Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidads, Set<Detalleplanificacionproduccion> detalleplanificacionproduccions, Set<Detalleproducto> detalleproductos) {
       this.idpieza = idpieza;
       this.materiaprima = materiaprima;
       this.matriz = matriz;
       this.nombre = nombre;
       this.tipomaterial = tipomaterial;
       this.alto = alto;
       this.ancho = ancho;
       this.largo = largo;
       this.detalleplanificacioncalidads = detalleplanificacioncalidads;
       this.detalletrabajotercerizados = detalletrabajotercerizados;
       this.piezareals = piezareals;
       this.detallerequerimientosmateriaprimas = detallerequerimientosmateriaprimas;
       this.piezaxetapadeproduccions = piezaxetapadeproduccions;
       this.detalleproductopresupuestos = detalleproductopresupuestos;
       this.detalleplanprocedimientoses = detalleplanprocedimientoses;
       this.detalleplanprocesoscalidads = detalleplanprocesoscalidads;
       this.detalleplanificacionproduccions = detalleplanificacionproduccions;
       this.detalleproductos = detalleproductos;
    }
   
    public long getIdpieza() {
        return this.idpieza;
    }
    
    public void setIdpieza(long idpieza) {
        this.idpieza = idpieza;
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
    public Set<Detalleplanificacioncalidad> getDetalleplanificacioncalidads() {
        return this.detalleplanificacioncalidads;
    }
    
    public void setDetalleplanificacioncalidads(Set<Detalleplanificacioncalidad> detalleplanificacioncalidads) {
        this.detalleplanificacioncalidads = detalleplanificacioncalidads;
    }
    public Set<Detalletrabajotercerizado> getDetalletrabajotercerizados() {
        return this.detalletrabajotercerizados;
    }
    
    public void setDetalletrabajotercerizados(Set<Detalletrabajotercerizado> detalletrabajotercerizados) {
        this.detalletrabajotercerizados = detalletrabajotercerizados;
    }
    public Set<Piezareal> getPiezareals() {
        return this.piezareals;
    }
    
    public void setPiezareals(Set<Piezareal> piezareals) {
        this.piezareals = piezareals;
    }
    public Set<Detallerequerimientosmateriaprima> getDetallerequerimientosmateriaprimas() {
        return this.detallerequerimientosmateriaprimas;
    }
    
    public void setDetallerequerimientosmateriaprimas(Set<Detallerequerimientosmateriaprima> detallerequerimientosmateriaprimas) {
        this.detallerequerimientosmateriaprimas = detallerequerimientosmateriaprimas;
    }
    public Set<Piezaxetapadeproduccion> getPiezaxetapadeproduccions() {
        return this.piezaxetapadeproduccions;
    }
    
    public void setPiezaxetapadeproduccions(Set<Piezaxetapadeproduccion> piezaxetapadeproduccions) {
        this.piezaxetapadeproduccions = piezaxetapadeproduccions;
    }
    public Set<Detalleproductopresupuesto> getDetalleproductopresupuestos() {
        return this.detalleproductopresupuestos;
    }
    
    public void setDetalleproductopresupuestos(Set<Detalleproductopresupuesto> detalleproductopresupuestos) {
        this.detalleproductopresupuestos = detalleproductopresupuestos;
    }
    public Set<Detalleplanprocedimientos> getDetalleplanprocedimientoses() {
        return this.detalleplanprocedimientoses;
    }
    
    public void setDetalleplanprocedimientoses(Set<Detalleplanprocedimientos> detalleplanprocedimientoses) {
        this.detalleplanprocedimientoses = detalleplanprocedimientoses;
    }
    public Set<Detalleplanprocesoscalidad> getDetalleplanprocesoscalidads() {
        return this.detalleplanprocesoscalidads;
    }
    
    public void setDetalleplanprocesoscalidads(Set<Detalleplanprocesoscalidad> detalleplanprocesoscalidads) {
        this.detalleplanprocesoscalidads = detalleplanprocesoscalidads;
    }
    public Set<Detalleplanificacionproduccion> getDetalleplanificacionproduccions() {
        return this.detalleplanificacionproduccions;
    }
    
    public void setDetalleplanificacionproduccions(Set<Detalleplanificacionproduccion> detalleplanificacionproduccions) {
        this.detalleplanificacionproduccions = detalleplanificacionproduccions;
    }
    public Set<Detalleproducto> getDetalleproductos() {
        return this.detalleproductos;
    }
    
    public void setDetalleproductos(Set<Detalleproducto> detalleproductos) {
        this.detalleproductos = detalleproductos;
    }




}


