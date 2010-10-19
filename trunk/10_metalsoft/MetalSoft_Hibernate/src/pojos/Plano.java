package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Plano generated by hbm2java
 */
public class Plano  implements java.io.Serializable {


     private long idplano;
     private Pedido pedido;
     private Long nroplano;
     private Integer escala;
     private Serializable imagen;
     private Set<Pedido> pedidos = new HashSet<Pedido>(0);

    public Plano() {
    }

	
    public Plano(long idplano) {
        this.idplano = idplano;
    }
    public Plano(long idplano, Pedido pedido, Long nroplano, Integer escala, Serializable imagen, Set<Pedido> pedidos) {
       this.idplano = idplano;
       this.pedido = pedido;
       this.nroplano = nroplano;
       this.escala = escala;
       this.imagen = imagen;
       this.pedidos = pedidos;
    }
   
    public long getIdplano() {
        return this.idplano;
    }
    
    public void setIdplano(long idplano) {
        this.idplano = idplano;
    }
    public Pedido getPedido() {
        return this.pedido;
    }
    
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
    public Long getNroplano() {
        return this.nroplano;
    }
    
    public void setNroplano(Long nroplano) {
        this.nroplano = nroplano;
    }
    public Integer getEscala() {
        return this.escala;
    }
    
    public void setEscala(Integer escala) {
        this.escala = escala;
    }
    public Serializable getImagen() {
        return this.imagen;
    }
    
    public void setImagen(Serializable imagen) {
        this.imagen = imagen;
    }
    public Set<Pedido> getPedidos() {
        return this.pedidos;
    }
    
    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }




}


