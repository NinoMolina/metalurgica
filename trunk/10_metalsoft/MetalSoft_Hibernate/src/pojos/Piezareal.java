package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA


import java.util.HashSet;
import java.util.Set;

/**
 * Piezareal generated by hbm2java
 */
public class Piezareal  implements java.io.Serializable {


     private long idpiezareal;
     private Estadopiezareal estadopiezareal;
     private Codigodebarra codigodebarra;
     private long idpieza;
     private Integer nropieza;
     private Set<Detalleejecucionplanificacion> detalleejecucionplanificacions = new HashSet<Detalleejecucionplanificacion>(0);

    public Piezareal() {
    }

	
    public Piezareal(long idpiezareal, long idpieza) {
        this.idpiezareal = idpiezareal;
        this.idpieza = idpieza;
    }
    public Piezareal(long idpiezareal, Estadopiezareal estadopiezareal, Codigodebarra codigodebarra, long idpieza, Integer nropieza, Set<Detalleejecucionplanificacion> detalleejecucionplanificacions) {
       this.idpiezareal = idpiezareal;
       this.estadopiezareal = estadopiezareal;
       this.codigodebarra = codigodebarra;
       this.idpieza = idpieza;
       this.nropieza = nropieza;
       this.detalleejecucionplanificacions = detalleejecucionplanificacions;
    }
   
    public long getIdpiezareal() {
        return this.idpiezareal;
    }
    
    public void setIdpiezareal(long idpiezareal) {
        this.idpiezareal = idpiezareal;
    }
    public Estadopiezareal getEstadopiezareal() {
        return this.estadopiezareal;
    }
    
    public void setEstadopiezareal(Estadopiezareal estadopiezareal) {
        this.estadopiezareal = estadopiezareal;
    }
    public Codigodebarra getCodigodebarra() {
        return this.codigodebarra;
    }
    
    public void setCodigodebarra(Codigodebarra codigodebarra) {
        this.codigodebarra = codigodebarra;
    }
    public long getIdpieza() {
        return this.idpieza;
    }
    
    public void setIdpieza(long idpieza) {
        this.idpieza = idpieza;
    }
    public Integer getNropieza() {
        return this.nropieza;
    }
    
    public void setNropieza(Integer nropieza) {
        this.nropieza = nropieza;
    }
    public Set<Detalleejecucionplanificacion> getDetalleejecucionplanificacions() {
        return this.detalleejecucionplanificacions;
    }
    
    public void setDetalleejecucionplanificacions(Set<Detalleejecucionplanificacion> detalleejecucionplanificacions) {
        this.detalleejecucionplanificacions = detalleejecucionplanificacions;
    }




}

