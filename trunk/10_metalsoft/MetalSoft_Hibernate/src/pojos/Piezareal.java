package pojos;
// Generated 18/10/2010 10:12:42 by Hibernate Tools 3.2.1.GA



/**
 * Piezareal generated by hbm2java
 */
public class Piezareal  implements java.io.Serializable {


     private long idpiezareal;
     private Estadopiezareal estadopiezareal;
     private Codigodebarra codigodebarra;
     private long idpieza;
     private Integer nropieza;

    public Piezareal() {
    }

	
    public Piezareal(long idpiezareal, long idpieza) {
        this.idpiezareal = idpiezareal;
        this.idpieza = idpieza;
    }
    public Piezareal(long idpiezareal, Estadopiezareal estadopiezareal, Codigodebarra codigodebarra, long idpieza, Integer nropieza) {
       this.idpiezareal = idpiezareal;
       this.estadopiezareal = estadopiezareal;
       this.codigodebarra = codigodebarra;
       this.idpieza = idpieza;
       this.nropieza = nropieza;
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




}


