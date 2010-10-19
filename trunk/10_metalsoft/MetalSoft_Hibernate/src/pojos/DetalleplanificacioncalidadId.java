package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA



/**
 * DetalleplanificacioncalidadId generated by hbm2java
 */
public class DetalleplanificacioncalidadId  implements java.io.Serializable {


     private long iddetalle;
     private long idplanificacioncalidad;

    public DetalleplanificacioncalidadId() {
    }

    public DetalleplanificacioncalidadId(long iddetalle, long idplanificacioncalidad) {
       this.iddetalle = iddetalle;
       this.idplanificacioncalidad = idplanificacioncalidad;
    }
   
    public long getIddetalle() {
        return this.iddetalle;
    }
    
    public void setIddetalle(long iddetalle) {
        this.iddetalle = iddetalle;
    }
    public long getIdplanificacioncalidad() {
        return this.idplanificacioncalidad;
    }
    
    public void setIdplanificacioncalidad(long idplanificacioncalidad) {
        this.idplanificacioncalidad = idplanificacioncalidad;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof DetalleplanificacioncalidadId) ) return false;
		 DetalleplanificacioncalidadId castOther = ( DetalleplanificacioncalidadId ) other; 
         
		 return (this.getIddetalle()==castOther.getIddetalle())
 && (this.getIdplanificacioncalidad()==castOther.getIdplanificacioncalidad());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getIddetalle();
         result = 37 * result + (int) this.getIdplanificacioncalidad();
         return result;
   }   


}


