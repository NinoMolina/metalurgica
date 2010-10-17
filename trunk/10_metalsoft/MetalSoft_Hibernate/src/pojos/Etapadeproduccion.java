package pojos;
// Generated 17/10/2010 06:45:36 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Etapadeproduccion generated by hbm2java
 */
public class Etapadeproduccion  implements java.io.Serializable {


     private long idetapaproduccion;
     private Maquina maquina;
     private Unidadmedida unidadmedida;
     private Long nroetapaproduccion;
     private String nombre;
     private Date horasmaquina;
     private Date horashombre;
     private Date duracionestimada;
     private Date fechacreacion;
     private Set<Detalletrabajotercerizado> detalletrabajotercerizados = new HashSet<Detalletrabajotercerizado>(0);
     private Set<Detalleplanificacionproduccion> detalleplanificacionproduccions = new HashSet<Detalleplanificacionproduccion>(0);
     private Set<Ejecucionetapaproduccion> ejecucionetapaproduccions = new HashSet<Ejecucionetapaproduccion>(0);
     private Set<Piezaxetapadeproduccion> piezaxetapadeproduccions = new HashSet<Piezaxetapadeproduccion>(0);
     private Set<Detalleplanprocedimientos> detalleplanprocedimientoses = new HashSet<Detalleplanprocedimientos>(0);
     private Set<Detallepiezapresupuesto> detallepiezapresupuestos = new HashSet<Detallepiezapresupuesto>(0);

    public Etapadeproduccion() {
    }

	
    public Etapadeproduccion(long idetapaproduccion) {
        this.idetapaproduccion = idetapaproduccion;
    }
    public Etapadeproduccion(long idetapaproduccion, Maquina maquina, Unidadmedida unidadmedida, Long nroetapaproduccion, String nombre, Date horasmaquina, Date horashombre, Date duracionestimada, Date fechacreacion, Set<Detalletrabajotercerizado> detalletrabajotercerizados, Set<Detalleplanificacionproduccion> detalleplanificacionproduccions, Set<Ejecucionetapaproduccion> ejecucionetapaproduccions, Set<Piezaxetapadeproduccion> piezaxetapadeproduccions, Set<Detalleplanprocedimientos> detalleplanprocedimientoses, Set<Detallepiezapresupuesto> detallepiezapresupuestos) {
       this.idetapaproduccion = idetapaproduccion;
       this.maquina = maquina;
       this.unidadmedida = unidadmedida;
       this.nroetapaproduccion = nroetapaproduccion;
       this.nombre = nombre;
       this.horasmaquina = horasmaquina;
       this.horashombre = horashombre;
       this.duracionestimada = duracionestimada;
       this.fechacreacion = fechacreacion;
       this.detalletrabajotercerizados = detalletrabajotercerizados;
       this.detalleplanificacionproduccions = detalleplanificacionproduccions;
       this.ejecucionetapaproduccions = ejecucionetapaproduccions;
       this.piezaxetapadeproduccions = piezaxetapadeproduccions;
       this.detalleplanprocedimientoses = detalleplanprocedimientoses;
       this.detallepiezapresupuestos = detallepiezapresupuestos;
    }
   
    public long getIdetapaproduccion() {
        return this.idetapaproduccion;
    }
    
    public void setIdetapaproduccion(long idetapaproduccion) {
        this.idetapaproduccion = idetapaproduccion;
    }
    public Maquina getMaquina() {
        return this.maquina;
    }
    
    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }
    public Unidadmedida getUnidadmedida() {
        return this.unidadmedida;
    }
    
    public void setUnidadmedida(Unidadmedida unidadmedida) {
        this.unidadmedida = unidadmedida;
    }
    public Long getNroetapaproduccion() {
        return this.nroetapaproduccion;
    }
    
    public void setNroetapaproduccion(Long nroetapaproduccion) {
        this.nroetapaproduccion = nroetapaproduccion;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Date getHorasmaquina() {
        return this.horasmaquina;
    }
    
    public void setHorasmaquina(Date horasmaquina) {
        this.horasmaquina = horasmaquina;
    }
    public Date getHorashombre() {
        return this.horashombre;
    }
    
    public void setHorashombre(Date horashombre) {
        this.horashombre = horashombre;
    }
    public Date getDuracionestimada() {
        return this.duracionestimada;
    }
    
    public void setDuracionestimada(Date duracionestimada) {
        this.duracionestimada = duracionestimada;
    }
    public Date getFechacreacion() {
        return this.fechacreacion;
    }
    
    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }
    public Set<Detalletrabajotercerizado> getDetalletrabajotercerizados() {
        return this.detalletrabajotercerizados;
    }
    
    public void setDetalletrabajotercerizados(Set<Detalletrabajotercerizado> detalletrabajotercerizados) {
        this.detalletrabajotercerizados = detalletrabajotercerizados;
    }
    public Set<Detalleplanificacionproduccion> getDetalleplanificacionproduccions() {
        return this.detalleplanificacionproduccions;
    }
    
    public void setDetalleplanificacionproduccions(Set<Detalleplanificacionproduccion> detalleplanificacionproduccions) {
        this.detalleplanificacionproduccions = detalleplanificacionproduccions;
    }
    public Set<Ejecucionetapaproduccion> getEjecucionetapaproduccions() {
        return this.ejecucionetapaproduccions;
    }
    
    public void setEjecucionetapaproduccions(Set<Ejecucionetapaproduccion> ejecucionetapaproduccions) {
        this.ejecucionetapaproduccions = ejecucionetapaproduccions;
    }
    public Set<Piezaxetapadeproduccion> getPiezaxetapadeproduccions() {
        return this.piezaxetapadeproduccions;
    }
    
    public void setPiezaxetapadeproduccions(Set<Piezaxetapadeproduccion> piezaxetapadeproduccions) {
        this.piezaxetapadeproduccions = piezaxetapadeproduccions;
    }
    public Set<Detalleplanprocedimientos> getDetalleplanprocedimientoses() {
        return this.detalleplanprocedimientoses;
    }
    
    public void setDetalleplanprocedimientoses(Set<Detalleplanprocedimientos> detalleplanprocedimientoses) {
        this.detalleplanprocedimientoses = detalleplanprocedimientoses;
    }
    public Set<Detallepiezapresupuesto> getDetallepiezapresupuestos() {
        return this.detallepiezapresupuestos;
    }
    
    public void setDetallepiezapresupuestos(Set<Detallepiezapresupuesto> detallepiezapresupuestos) {
        this.detallepiezapresupuestos = detallepiezapresupuestos;
    }




}


