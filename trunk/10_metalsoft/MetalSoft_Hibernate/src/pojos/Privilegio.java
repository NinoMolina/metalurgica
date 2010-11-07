package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Privilegio generated by hbm2java
 */
public class Privilegio  implements java.io.Serializable {


     private long idprivilegio;
     private String privilegio;
     private String descripcion;
     private Set<Rol> rols = new HashSet<Rol>(0);

    public Privilegio() {
    }

	
    public Privilegio(long idprivilegio) {
        this.idprivilegio = idprivilegio;
    }
    public Privilegio(long idprivilegio, String privilegio, String descripcion, Set<Rol> rols) {
       this.idprivilegio = idprivilegio;
       this.privilegio = privilegio;
       this.descripcion = descripcion;
       this.rols = rols;
    }
   
    public long getIdprivilegio() {
        return this.idprivilegio;
    }
    
    public void setIdprivilegio(long idprivilegio) {
        this.idprivilegio = idprivilegio;
    }
    public String getPrivilegio() {
        return this.privilegio;
    }
    
    public void setPrivilegio(String privilegio) {
        this.privilegio = privilegio;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Set<Rol> getRols() {
        return this.rols;
    }
    
    public void setRols(Set<Rol> rols) {
        this.rols = rols;
    }




}

