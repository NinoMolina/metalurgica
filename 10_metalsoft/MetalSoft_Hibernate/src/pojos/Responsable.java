package pojos;
// Generated 18/10/2010 10:12:42 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Responsable generated by hbm2java
 */
public class Responsable  implements java.io.Serializable {


     private long idresponsable;
     private Tipodocumento tipodocumento;
     private Domicilio domicilio;
     private String nombre;
     private String apellido;
     private String telefono;
     private String email;
     private Integer nrodocumento;
     private String fax;
     private Set<Cliente> clientes = new HashSet<Cliente>(0);
     private Set<Proveedor> proveedors = new HashSet<Proveedor>(0);
     private Set<Empresametalurgica> empresametalurgicas = new HashSet<Empresametalurgica>(0);
     private Set<Proveedormantenimientomaquina> proveedormantenimientomaquinas = new HashSet<Proveedormantenimientomaquina>(0);

    public Responsable() {
    }

	
    public Responsable(long idresponsable) {
        this.idresponsable = idresponsable;
    }
    public Responsable(long idresponsable, Tipodocumento tipodocumento, Domicilio domicilio, String nombre, String apellido, String telefono, String email, Integer nrodocumento, String fax, Set<Cliente> clientes, Set<Proveedor> proveedors, Set<Empresametalurgica> empresametalurgicas, Set<Proveedormantenimientomaquina> proveedormantenimientomaquinas) {
       this.idresponsable = idresponsable;
       this.tipodocumento = tipodocumento;
       this.domicilio = domicilio;
       this.nombre = nombre;
       this.apellido = apellido;
       this.telefono = telefono;
       this.email = email;
       this.nrodocumento = nrodocumento;
       this.fax = fax;
       this.clientes = clientes;
       this.proveedors = proveedors;
       this.empresametalurgicas = empresametalurgicas;
       this.proveedormantenimientomaquinas = proveedormantenimientomaquinas;
    }
   
    public long getIdresponsable() {
        return this.idresponsable;
    }
    
    public void setIdresponsable(long idresponsable) {
        this.idresponsable = idresponsable;
    }
    public Tipodocumento getTipodocumento() {
        return this.tipodocumento;
    }
    
    public void setTipodocumento(Tipodocumento tipodocumento) {
        this.tipodocumento = tipodocumento;
    }
    public Domicilio getDomicilio() {
        return this.domicilio;
    }
    
    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getNrodocumento() {
        return this.nrodocumento;
    }
    
    public void setNrodocumento(Integer nrodocumento) {
        this.nrodocumento = nrodocumento;
    }
    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }
    public Set<Cliente> getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }
    public Set<Proveedor> getProveedors() {
        return this.proveedors;
    }
    
    public void setProveedors(Set<Proveedor> proveedors) {
        this.proveedors = proveedors;
    }
    public Set<Empresametalurgica> getEmpresametalurgicas() {
        return this.empresametalurgicas;
    }
    
    public void setEmpresametalurgicas(Set<Empresametalurgica> empresametalurgicas) {
        this.empresametalurgicas = empresametalurgicas;
    }
    public Set<Proveedormantenimientomaquina> getProveedormantenimientomaquinas() {
        return this.proveedormantenimientomaquinas;
    }
    
    public void setProveedormantenimientomaquinas(Set<Proveedormantenimientomaquina> proveedormantenimientomaquinas) {
        this.proveedormantenimientomaquinas = proveedormantenimientomaquinas;
    }




}


