package pojos;
// Generated 18/10/2010 10:12:42 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Mantenimientopreventivo generated by hbm2java
 */
public class Mantenimientopreventivo  implements java.io.Serializable {


     private long idmantenimientopreventivo;
     private Proveedormantenimientomaquina proveedormantenimientomaquina;
     private Date fechamantenimientoprevisto;
     private Date fechaenviomantenimiento;
     private Date horaenviomantenimiento;
     private String periodo;
     private Long nromantenimietno;
     private Date fechafinmantenimientoreal;
     private Long maquina;
     private Set<Detallemantenimientopreventivo> detallemantenimientopreventivos = new HashSet<Detallemantenimientopreventivo>(0);

    public Mantenimientopreventivo() {
    }

	
    public Mantenimientopreventivo(long idmantenimientopreventivo) {
        this.idmantenimientopreventivo = idmantenimientopreventivo;
    }
    public Mantenimientopreventivo(long idmantenimientopreventivo, Proveedormantenimientomaquina proveedormantenimientomaquina, Date fechamantenimientoprevisto, Date fechaenviomantenimiento, Date horaenviomantenimiento, String periodo, Long nromantenimietno, Date fechafinmantenimientoreal, Long maquina, Set<Detallemantenimientopreventivo> detallemantenimientopreventivos) {
       this.idmantenimientopreventivo = idmantenimientopreventivo;
       this.proveedormantenimientomaquina = proveedormantenimientomaquina;
       this.fechamantenimientoprevisto = fechamantenimientoprevisto;
       this.fechaenviomantenimiento = fechaenviomantenimiento;
       this.horaenviomantenimiento = horaenviomantenimiento;
       this.periodo = periodo;
       this.nromantenimietno = nromantenimietno;
       this.fechafinmantenimientoreal = fechafinmantenimientoreal;
       this.maquina = maquina;
       this.detallemantenimientopreventivos = detallemantenimientopreventivos;
    }
   
    public long getIdmantenimientopreventivo() {
        return this.idmantenimientopreventivo;
    }
    
    public void setIdmantenimientopreventivo(long idmantenimientopreventivo) {
        this.idmantenimientopreventivo = idmantenimientopreventivo;
    }
    public Proveedormantenimientomaquina getProveedormantenimientomaquina() {
        return this.proveedormantenimientomaquina;
    }
    
    public void setProveedormantenimientomaquina(Proveedormantenimientomaquina proveedormantenimientomaquina) {
        this.proveedormantenimientomaquina = proveedormantenimientomaquina;
    }
    public Date getFechamantenimientoprevisto() {
        return this.fechamantenimientoprevisto;
    }
    
    public void setFechamantenimientoprevisto(Date fechamantenimientoprevisto) {
        this.fechamantenimientoprevisto = fechamantenimientoprevisto;
    }
    public Date getFechaenviomantenimiento() {
        return this.fechaenviomantenimiento;
    }
    
    public void setFechaenviomantenimiento(Date fechaenviomantenimiento) {
        this.fechaenviomantenimiento = fechaenviomantenimiento;
    }
    public Date getHoraenviomantenimiento() {
        return this.horaenviomantenimiento;
    }
    
    public void setHoraenviomantenimiento(Date horaenviomantenimiento) {
        this.horaenviomantenimiento = horaenviomantenimiento;
    }
    public String getPeriodo() {
        return this.periodo;
    }
    
    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    public Long getNromantenimietno() {
        return this.nromantenimietno;
    }
    
    public void setNromantenimietno(Long nromantenimietno) {
        this.nromantenimietno = nromantenimietno;
    }
    public Date getFechafinmantenimientoreal() {
        return this.fechafinmantenimientoreal;
    }
    
    public void setFechafinmantenimientoreal(Date fechafinmantenimientoreal) {
        this.fechafinmantenimientoreal = fechafinmantenimientoreal;
    }
    public Long getMaquina() {
        return this.maquina;
    }
    
    public void setMaquina(Long maquina) {
        this.maquina = maquina;
    }
    public Set<Detallemantenimientopreventivo> getDetallemantenimientopreventivos() {
        return this.detallemantenimientopreventivos;
    }
    
    public void setDetallemantenimientopreventivos(Set<Detallemantenimientopreventivo> detallemantenimientopreventivos) {
        this.detallemantenimientopreventivos = detallemantenimientopreventivos;
    }




}


