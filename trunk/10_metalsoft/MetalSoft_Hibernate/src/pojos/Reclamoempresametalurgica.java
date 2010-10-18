package pojos;
// Generated 18/10/2010 10:12:42 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Reclamoempresametalurgica generated by hbm2java
 */
public class Reclamoempresametalurgica  implements java.io.Serializable {


     private long idreclamo;
     private Trabajotercerizado trabajotercerizado;
     private Tiporeclamo tiporeclamo;
     private Long nroreclamo;
     private String motivo;
     private Date fechareclamo;
     private Set<Detallereclamoempresametalurgica> detallereclamoempresametalurgicas = new HashSet<Detallereclamoempresametalurgica>(0);

    public Reclamoempresametalurgica() {
    }

	
    public Reclamoempresametalurgica(long idreclamo) {
        this.idreclamo = idreclamo;
    }
    public Reclamoempresametalurgica(long idreclamo, Trabajotercerizado trabajotercerizado, Tiporeclamo tiporeclamo, Long nroreclamo, String motivo, Date fechareclamo, Set<Detallereclamoempresametalurgica> detallereclamoempresametalurgicas) {
       this.idreclamo = idreclamo;
       this.trabajotercerizado = trabajotercerizado;
       this.tiporeclamo = tiporeclamo;
       this.nroreclamo = nroreclamo;
       this.motivo = motivo;
       this.fechareclamo = fechareclamo;
       this.detallereclamoempresametalurgicas = detallereclamoempresametalurgicas;
    }
   
    public long getIdreclamo() {
        return this.idreclamo;
    }
    
    public void setIdreclamo(long idreclamo) {
        this.idreclamo = idreclamo;
    }
    public Trabajotercerizado getTrabajotercerizado() {
        return this.trabajotercerizado;
    }
    
    public void setTrabajotercerizado(Trabajotercerizado trabajotercerizado) {
        this.trabajotercerizado = trabajotercerizado;
    }
    public Tiporeclamo getTiporeclamo() {
        return this.tiporeclamo;
    }
    
    public void setTiporeclamo(Tiporeclamo tiporeclamo) {
        this.tiporeclamo = tiporeclamo;
    }
    public Long getNroreclamo() {
        return this.nroreclamo;
    }
    
    public void setNroreclamo(Long nroreclamo) {
        this.nroreclamo = nroreclamo;
    }
    public String getMotivo() {
        return this.motivo;
    }
    
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    public Date getFechareclamo() {
        return this.fechareclamo;
    }
    
    public void setFechareclamo(Date fechareclamo) {
        this.fechareclamo = fechareclamo;
    }
    public Set<Detallereclamoempresametalurgica> getDetallereclamoempresametalurgicas() {
        return this.detallereclamoempresametalurgicas;
    }
    
    public void setDetallereclamoempresametalurgicas(Set<Detallereclamoempresametalurgica> detallereclamoempresametalurgicas) {
        this.detallereclamoempresametalurgicas = detallereclamoempresametalurgicas;
    }




}


