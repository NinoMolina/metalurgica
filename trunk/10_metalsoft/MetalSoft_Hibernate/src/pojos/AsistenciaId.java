package pojos;
// Generated 18/10/2010 10:12:42 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * AsistenciaId generated by hbm2java
 */
public class AsistenciaId  implements java.io.Serializable {


     private long empleado;
     private Date horaingreso;
     private Date fechaingreso;

    public AsistenciaId() {
    }

    public AsistenciaId(long empleado, Date horaingreso, Date fechaingreso) {
       this.empleado = empleado;
       this.horaingreso = horaingreso;
       this.fechaingreso = fechaingreso;
    }
   
    public long getEmpleado() {
        return this.empleado;
    }
    
    public void setEmpleado(long empleado) {
        this.empleado = empleado;
    }
    public Date getHoraingreso() {
        return this.horaingreso;
    }
    
    public void setHoraingreso(Date horaingreso) {
        this.horaingreso = horaingreso;
    }
    public Date getFechaingreso() {
        return this.fechaingreso;
    }
    
    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof AsistenciaId) ) return false;
		 AsistenciaId castOther = ( AsistenciaId ) other; 
         
		 return (this.getEmpleado()==castOther.getEmpleado())
 && ( (this.getHoraingreso()==castOther.getHoraingreso()) || ( this.getHoraingreso()!=null && castOther.getHoraingreso()!=null && this.getHoraingreso().equals(castOther.getHoraingreso()) ) )
 && ( (this.getFechaingreso()==castOther.getFechaingreso()) || ( this.getFechaingreso()!=null && castOther.getFechaingreso()!=null && this.getFechaingreso().equals(castOther.getFechaingreso()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getEmpleado();
         result = 37 * result + ( getHoraingreso() == null ? 0 : this.getHoraingreso().hashCode() );
         result = 37 * result + ( getFechaingreso() == null ? 0 : this.getFechaingreso().hashCode() );
         return result;
   }   


}


