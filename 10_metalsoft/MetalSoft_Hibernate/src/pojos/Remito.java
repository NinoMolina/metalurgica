package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Remito generated by hbm2java
 */
public class Remito  implements java.io.Serializable {


     private long idremito;
     private Estadoremito estadoremito;
     private Pedido pedido;
     private Long nroremito;
     private Date fechaemision;
     private Set<Detalleremito> detalleremitos = new HashSet<Detalleremito>(0);

    public Remito() {
    }

	
    public Remito(long idremito) {
        this.idremito = idremito;
    }
    public Remito(long idremito, Estadoremito estadoremito, Pedido pedido, Long nroremito, Date fechaemision, Set<Detalleremito> detalleremitos) {
       this.idremito = idremito;
       this.estadoremito = estadoremito;
       this.pedido = pedido;
       this.nroremito = nroremito;
       this.fechaemision = fechaemision;
       this.detalleremitos = detalleremitos;
    }
   
    public long getIdremito() {
        return this.idremito;
    }
    
    public void setIdremito(long idremito) {
        this.idremito = idremito;
    }
    public Estadoremito getEstadoremito() {
        return this.estadoremito;
    }
    
    public void setEstadoremito(Estadoremito estadoremito) {
        this.estadoremito = estadoremito;
    }
    public Pedido getPedido() {
        return this.pedido;
    }
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public Long getNroremito() {
        return this.nroremito;
    }
    
    public void setNroremito(Long nroremito) {
        this.nroremito = nroremito;
    }
    public Date getFechaemision() {
        return this.fechaemision;
    }
    
    public void setFechaemision(Date fechaemision) {
        this.fechaemision = fechaemision;
    }
    public Set<Detalleremito> getDetalleremitos() {
        return this.detalleremitos;
    }
    
    public void setDetalleremitos(Set<Detalleremito> detalleremitos) {
        this.detalleremitos = detalleremitos;
    }




}

