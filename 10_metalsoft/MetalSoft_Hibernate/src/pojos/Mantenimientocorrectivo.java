package pojos;
// Generated 18/10/2010 10:12:42 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Mantenimientocorrectivo generated by hbm2java
 */
public class Mantenimientocorrectivo  implements java.io.Serializable {


     private long idmantenimientocorrectivo;
     private Empleado empleado;
     private Proveedormantenimientomaquina proveedormantenimientomaquina;
     private Date fechaenviomantenimiento;
     private Date horaenviomantenimiento;
     private Long nromantenimientocorrectivo;
     private Long nrocomprobantearreglo;
     private Date fechafinmantenimientoreal;
     private Long maquina;
     private Set<Detallemantenimientocorrectivo> detallemantenimientocorrectivos = new HashSet<Detallemantenimientocorrectivo>(0);

    public Mantenimientocorrectivo() {
    }

	
    public Mantenimientocorrectivo(long idmantenimientocorrectivo) {
        this.idmantenimientocorrectivo = idmantenimientocorrectivo;
    }
    public Mantenimientocorrectivo(long idmantenimientocorrectivo, Empleado empleado, Proveedormantenimientomaquina proveedormantenimientomaquina, Date fechaenviomantenimiento, Date horaenviomantenimiento, Long nromantenimientocorrectivo, Long nrocomprobantearreglo, Date fechafinmantenimientoreal, Long maquina, Set<Detallemantenimientocorrectivo> detallemantenimientocorrectivos) {
       this.idmantenimientocorrectivo = idmantenimientocorrectivo;
       this.empleado = empleado;
       this.proveedormantenimientomaquina = proveedormantenimientomaquina;
       this.fechaenviomantenimiento = fechaenviomantenimiento;
       this.horaenviomantenimiento = horaenviomantenimiento;
       this.nromantenimientocorrectivo = nromantenimientocorrectivo;
       this.nrocomprobantearreglo = nrocomprobantearreglo;
       this.fechafinmantenimientoreal = fechafinmantenimientoreal;
       this.maquina = maquina;
       this.detallemantenimientocorrectivos = detallemantenimientocorrectivos;
    }
   
    public long getIdmantenimientocorrectivo() {
        return this.idmantenimientocorrectivo;
    }
    
    public void setIdmantenimientocorrectivo(long idmantenimientocorrectivo) {
        this.idmantenimientocorrectivo = idmantenimientocorrectivo;
    }
    public Empleado getEmpleado() {
        return this.empleado;
    }
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    public Proveedormantenimientomaquina getProveedormantenimientomaquina() {
        return this.proveedormantenimientomaquina;
    }
    
    public void setProveedormantenimientomaquina(Proveedormantenimientomaquina proveedormantenimientomaquina) {
        this.proveedormantenimientomaquina = proveedormantenimientomaquina;
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
    public Long getNromantenimientocorrectivo() {
        return this.nromantenimientocorrectivo;
    }
    
    public void setNromantenimientocorrectivo(Long nromantenimientocorrectivo) {
        this.nromantenimientocorrectivo = nromantenimientocorrectivo;
    }
    public Long getNrocomprobantearreglo() {
        return this.nrocomprobantearreglo;
    }
    
    public void setNrocomprobantearreglo(Long nrocomprobantearreglo) {
        this.nrocomprobantearreglo = nrocomprobantearreglo;
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
    public Set<Detallemantenimientocorrectivo> getDetallemantenimientocorrectivos() {
        return this.detallemantenimientocorrectivos;
    }
    
    public void setDetallemantenimientocorrectivos(Set<Detallemantenimientocorrectivo> detallemantenimientocorrectivos) {
        this.detallemantenimientocorrectivos = detallemantenimientocorrectivos;
    }




}


