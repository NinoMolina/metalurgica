package pojos;
// Generated 18/10/2010 10:12:42 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Turno generated by hbm2java
 */
public class Turno  implements java.io.Serializable {


     private long idturno;
     private Date horainicio;
     private Date horafin;
     private String nombre;
     private String descripcion;
     private Set<Empleado> empleados = new HashSet<Empleado>(0);

    public Turno() {
    }

	
    public Turno(long idturno) {
        this.idturno = idturno;
    }
    public Turno(long idturno, Date horainicio, Date horafin, String nombre, String descripcion, Set<Empleado> empleados) {
       this.idturno = idturno;
       this.horainicio = horainicio;
       this.horafin = horafin;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.empleados = empleados;
    }
   
    public long getIdturno() {
        return this.idturno;
    }
    
    public void setIdturno(long idturno) {
        this.idturno = idturno;
    }
    public Date getHorainicio() {
        return this.horainicio;
    }
    
    public void setHorainicio(Date horainicio) {
        this.horainicio = horainicio;
    }
    public Date getHorafin() {
        return this.horafin;
    }
    
    public void setHorafin(Date horafin) {
        this.horafin = horafin;
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
    public Set<Empleado> getEmpleados() {
        return this.empleados;
    }
    
    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }




}


