package pojos;
// Generated 18/10/2010 10:12:42 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private long idusuario;
     private String usuario;
     private String clave;
     private Set<Rol> rols = new HashSet<Rol>(0);
     private Set<Empleado> empleados = new HashSet<Empleado>(0);
     private Set<Cliente> clientes = new HashSet<Cliente>(0);
     private Set<Sesion> sesions = new HashSet<Sesion>(0);
     private Set<Comprobantepago> comprobantepagos = new HashSet<Comprobantepago>(0);
     private Set<Factura> facturas = new HashSet<Factura>(0);

    public Usuario() {
    }

	
    public Usuario(long idusuario) {
        this.idusuario = idusuario;
    }
    public Usuario(long idusuario, String usuario, String clave, Set<Rol> rols, Set<Empleado> empleados, Set<Cliente> clientes, Set<Sesion> sesions, Set<Comprobantepago> comprobantepagos, Set<Factura> facturas) {
       this.idusuario = idusuario;
       this.usuario = usuario;
       this.clave = clave;
       this.rols = rols;
       this.empleados = empleados;
       this.clientes = clientes;
       this.sesions = sesions;
       this.comprobantepagos = comprobantepagos;
       this.facturas = facturas;
    }
   
    public long getIdusuario() {
        return this.idusuario;
    }
    
    public void setIdusuario(long idusuario) {
        this.idusuario = idusuario;
    }
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getClave() {
        return this.clave;
    }
    
    public void setClave(String clave) {
        this.clave = clave;
    }
    public Set<Rol> getRols() {
        return this.rols;
    }
    
    public void setRols(Set<Rol> rols) {
        this.rols = rols;
    }
    public Set<Empleado> getEmpleados() {
        return this.empleados;
    }
    
    public void setEmpleados(Set<Empleado> empleados) {
        this.empleados = empleados;
    }
    public Set<Cliente> getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }
    public Set<Sesion> getSesions() {
        return this.sesions;
    }
    
    public void setSesions(Set<Sesion> sesions) {
        this.sesions = sesions;
    }
    public Set<Comprobantepago> getComprobantepagos() {
        return this.comprobantepagos;
    }
    
    public void setComprobantepagos(Set<Comprobantepago> comprobantepagos) {
        this.comprobantepagos = comprobantepagos;
    }
    public Set<Factura> getFacturas() {
        return this.facturas;
    }
    
    public void setFacturas(Set<Factura> facturas) {
        this.facturas = facturas;
    }




}


