package pojos;
// Generated 19/10/2010 02:40:26 by Hibernate Tools 3.2.1.GA



/**
 * MaquinaxprocesocalidadId generated by hbm2java
 */
public class MaquinaxprocesocalidadId  implements java.io.Serializable {


     private long idprocesocalidad;
     private long idmaquina;

    public MaquinaxprocesocalidadId() {
    }

    public MaquinaxprocesocalidadId(long idprocesocalidad, long idmaquina) {
       this.idprocesocalidad = idprocesocalidad;
       this.idmaquina = idmaquina;
    }
   
    public long getIdprocesocalidad() {
        return this.idprocesocalidad;
    }
    
    public void setIdprocesocalidad(long idprocesocalidad) {
        this.idprocesocalidad = idprocesocalidad;
    }
    public long getIdmaquina() {
        return this.idmaquina;
    }
    
    public void setIdmaquina(long idmaquina) {
        this.idmaquina = idmaquina;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof MaquinaxprocesocalidadId) ) return false;
		 MaquinaxprocesocalidadId castOther = ( MaquinaxprocesocalidadId ) other; 
         
		 return (this.getIdprocesocalidad()==castOther.getIdprocesocalidad())
 && (this.getIdmaquina()==castOther.getIdmaquina());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + (int) this.getIdprocesocalidad();
         result = 37 * result + (int) this.getIdmaquina();
         return result;
   }   


}

