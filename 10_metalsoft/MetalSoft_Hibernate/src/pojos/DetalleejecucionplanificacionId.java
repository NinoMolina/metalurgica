package pojos;
// Generated 18/10/2010 10:12:42 by Hibernate Tools 3.2.1.GA



/**
 * DetalleejecucionplanificacionId generated by hbm2java
 */
public class DetalleejecucionplanificacionId  implements java.io.Serializable {


     private long iddetalle;
     private long idejecucionplanificacionproduccion;
     private long idplanificacionproduccion;

    public DetalleejecucionplanificacionId() {
    }

    public DetalleejecucionplanificacionId(long iddetalle, long idejecucionplanificacionproduccion, long idplanificacionproduccion) {
       this.iddetalle = iddetalle;
       this.idejecucionplanificacionproduccion = idejecucionplanificacionproduccion;
       this.idplanificacionproduccion = idplanificacionproduccion;
    }
   
    public long getIddetalle() {
        return this.iddetalle;
    }
    
    public void setIddetalle(long iddetalle) {
        this.iddetalle = iddetalle;
    }
    public long getIdejecucionplanificacionproduccion() {
        return this.idejecucionplanificacionproduccion;
    }
    
    public void setIdejecucionplanificacionproduccion(long idejecucionplanificacionproduccion) {
        this.idejecucionplanificacionproduccion = idejecucionplanificacionproduccion;
    }
    public long getIdplanificacionproduccion() {
        return this.idplanificacionproduccion;
    }
    
    public void setIdplanificacionproduccion(long idplanificacionproduccion) {
        this.idplanificacionproduccion = idplanificacionproduccion;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DetalleejecucionplanificacionId) ) return false;
		 DetalleejecucionplanificacionId castOther = ( DetalleejecucionplanificacionId ) other; 
         
		 return (this.getIddetalle()==castOther.getIddetalle())
 && (this.getIdejecucionplanificacionproduccion()==castOther.getIdejecucionplanificacionproduccion())
 && (this.getIdplanificacionproduccion()==castOther.getIdplanificacionproduccion());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getIddetalle();
         result = 37 * result + (int) this.getIdejecucionplanificacionproduccion();
         result = 37 * result + (int) this.getIdplanificacionproduccion();
         return result;
   }   


}


