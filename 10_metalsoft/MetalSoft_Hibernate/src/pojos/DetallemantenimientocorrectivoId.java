package pojos;
// Generated 18/10/2010 10:12:42 by Hibernate Tools 3.2.1.GA



/**
 * DetallemantenimientocorrectivoId generated by hbm2java
 */
public class DetallemantenimientocorrectivoId  implements java.io.Serializable {


     private long idmantenimientocorrectivo;
     private long iddetalle;

    public DetallemantenimientocorrectivoId() {
    }

    public DetallemantenimientocorrectivoId(long idmantenimientocorrectivo, long iddetalle) {
       this.idmantenimientocorrectivo = idmantenimientocorrectivo;
       this.iddetalle = iddetalle;
    }
   
    public long getIdmantenimientocorrectivo() {
        return this.idmantenimientocorrectivo;
    }
    
    public void setIdmantenimientocorrectivo(long idmantenimientocorrectivo) {
        this.idmantenimientocorrectivo = idmantenimientocorrectivo;
    }
    public long getIddetalle() {
        return this.iddetalle;
    }
    
    public void setIddetalle(long iddetalle) {
        this.iddetalle = iddetalle;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DetallemantenimientocorrectivoId) ) return false;
		 DetallemantenimientocorrectivoId castOther = ( DetallemantenimientocorrectivoId ) other; 
         
		 return (this.getIdmantenimientocorrectivo()==castOther.getIdmantenimientocorrectivo())
 && (this.getIddetalle()==castOther.getIddetalle());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getIdmantenimientocorrectivo();
         result = 37 * result + (int) this.getIddetalle();
         return result;
   }   


}


