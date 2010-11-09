package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA



/**
 * DetallefacturaId generated by hbm2java
 */
public class DetallefacturaId  implements java.io.Serializable {


     private long iddetalle;
     private long idfactura;

    public DetallefacturaId() {
    }

    public DetallefacturaId(long iddetalle, long idfactura) {
       this.iddetalle = iddetalle;
       this.idfactura = idfactura;
    }
   
    public long getIddetalle() {
        return this.iddetalle;
    }
    
    public void setIddetalle(long iddetalle) {
        this.iddetalle = iddetalle;
    }
    public long getIdfactura() {
        return this.idfactura;
    }
    
    public void setIdfactura(long idfactura) {
        this.idfactura = idfactura;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DetallefacturaId) ) return false;
		 DetallefacturaId castOther = ( DetallefacturaId ) other; 
         
		 return (this.getIddetalle()==castOther.getIddetalle())
 && (this.getIdfactura()==castOther.getIdfactura());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getIddetalle();
         result = 37 * result + (int) this.getIdfactura();
         return result;
   }   


}

