package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import metalsoft.util.Fecha;

/**
 * Disponibilidadhoraria generated by hbm2java
 */
public class Disponibilidadhoraria  implements java.io.Serializable {


     private long id;
     private Empleado empleado;
     private Date fecha;
     private Date tiempodisponible;

    public Disponibilidadhoraria() {
    }

	
    public Disponibilidadhoraria(long id) {
        this.id = id;
    }
    public Disponibilidadhoraria(long id, Empleado empleado, Date fecha, Date tiempodisponible) {
       this.id = id;
       this.empleado = empleado;
       this.fecha = fecha;
       this.tiempodisponible = tiempodisponible;
    }
   
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public Empleado getEmpleado() {
        return this.empleado;
    }
    
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    public Date getFecha() {
        return this.fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public Date getTiempodisponible() {
        return this.tiempodisponible;
    }
    
    public void setTiempodisponible(Date tiempodisponible) {
        this.tiempodisponible = tiempodisponible;
    }

    @Override
    public String toString() {
        return Fecha.parseToString(getFecha())+": "+Fecha.parseToHourMinuteSecond(getTiempodisponible());
    }




}

