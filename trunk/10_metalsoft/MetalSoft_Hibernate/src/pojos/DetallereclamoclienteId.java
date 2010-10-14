package pojos;
// Generated 12/10/2010 01:33:18 by Hibernate Tools 3.2.1.GA



/**
 * DetallereclamoclienteId generated by hbm2java
 */
public class DetallereclamoclienteId  implements java.io.Serializable {


     private long iddetalle;
     private long idreclamo;

    public DetallereclamoclienteId() {
    }

    public DetallereclamoclienteId(long iddetalle, long idreclamo) {
       this.iddetalle = iddetalle;
       this.idreclamo = idreclamo;
    }
   
    public long getIddetalle() {
        return this.iddetalle;
    }
    
    public void setIddetalle(long iddetalle) {
        this.iddetalle = iddetalle;
    }
    public long getIdreclamo() {
        return this.idreclamo;
    }
    
    public void setIdreclamo(long idreclamo) {
        this.idreclamo = idreclamo;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DetallereclamoclienteId) ) return false;
		 DetallereclamoclienteId castOther = ( DetallereclamoclienteId ) other; 
         
		 return (this.getIddetalle()==castOther.getIddetalle())
 && (this.getIdreclamo()==castOther.getIdreclamo());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getIddetalle();
         result = 37 * result + (int) this.getIdreclamo();
         return result;
   }   


}


