package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA



/**
 * DetalletrabajotercerizadoId generated by hbm2java
 */
public class DetalletrabajotercerizadoId  implements java.io.Serializable {


     private long iddetalle;
     private long idtrabajotercerizado;

    public DetalletrabajotercerizadoId() {
    }

    public DetalletrabajotercerizadoId(long iddetalle, long idtrabajotercerizado) {
       this.iddetalle = iddetalle;
       this.idtrabajotercerizado = idtrabajotercerizado;
    }
   
    public long getIddetalle() {
        return this.iddetalle;
    }
    
    public void setIddetalle(long iddetalle) {
        this.iddetalle = iddetalle;
    }
    public long getIdtrabajotercerizado() {
        return this.idtrabajotercerizado;
    }
    
    public void setIdtrabajotercerizado(long idtrabajotercerizado) {
        this.idtrabajotercerizado = idtrabajotercerizado;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DetalletrabajotercerizadoId) ) return false;
		 DetalletrabajotercerizadoId castOther = ( DetalletrabajotercerizadoId ) other; 
         
		 return (this.getIddetalle()==castOther.getIddetalle())
 && (this.getIdtrabajotercerizado()==castOther.getIdtrabajotercerizado());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getIddetalle();
         result = 37 * result + (int) this.getIdtrabajotercerizado();
         return result;
   }   


}


