package pojos;
// Generated 12/10/2010 01:33:18 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Cargo generated by hbm2java
 */
public class Cargo  implements java.io.Serializable {


     private long idcargo;
     private String nombre;
     private String descripcion;
     private Set<Empleado> empleados = new HashSet<Empleado>(0);

    public Cargo() {
    }

	
    public Cargo(long idcargo) {
        this.idcargo = idcargo;
    }
    public Cargo(long idcargo, String nombre, String descripcion, Set<Empleado> empleados) {
       this.idcargo = idcargo;
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.empleados = empleados;
    }
   
    public long getIdcargo() {
        return this.idcargo;
    }
    
    public void setIdcargo(long idcargo) {
        this.idcargo = idcargo;
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


