package pojos;
// Generated 12/10/2010 01:33:18 by Hibernate Tools 3.2.1.GA


import java.util.Date;

/**
 * Maquinaxejecucionetapaproduccion generated by hbm2java
 */
public class Maquinaxejecucionetapaproduccion  implements java.io.Serializable {


     private MaquinaxejecucionetapaproduccionId id;
     private Maquina maquina;
     private Ejecucionetapaproduccion ejecucionetapaproduccion;
     private Date horasmaquina;
     private Date horashombre;

    public Maquinaxejecucionetapaproduccion() {
    }

	
    public Maquinaxejecucionetapaproduccion(MaquinaxejecucionetapaproduccionId id, Maquina maquina, Ejecucionetapaproduccion ejecucionetapaproduccion) {
        this.id = id;
        this.maquina = maquina;
        this.ejecucionetapaproduccion = ejecucionetapaproduccion;
    }
    public Maquinaxejecucionetapaproduccion(MaquinaxejecucionetapaproduccionId id, Maquina maquina, Ejecucionetapaproduccion ejecucionetapaproduccion, Date horasmaquina, Date horashombre) {
       this.id = id;
       this.maquina = maquina;
       this.ejecucionetapaproduccion = ejecucionetapaproduccion;
       this.horasmaquina = horasmaquina;
       this.horashombre = horashombre;
    }
   
    public MaquinaxejecucionetapaproduccionId getId() {
        return this.id;
    }
    
    public void setId(MaquinaxejecucionetapaproduccionId id) {
        this.id = id;
    }
    public Maquina getMaquina() {
        return this.maquina;
    }
    
    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }
    public Ejecucionetapaproduccion getEjecucionetapaproduccion() {
        return this.ejecucionetapaproduccion;
    }
    
    public void setEjecucionetapaproduccion(Ejecucionetapaproduccion ejecucionetapaproduccion) {
        this.ejecucionetapaproduccion = ejecucionetapaproduccion;
    }
    public Date getHorasmaquina() {
        return this.horasmaquina;
    }
    
    public void setHorasmaquina(Date horasmaquina) {
        this.horasmaquina = horasmaquina;
    }
    public Date getHorashombre() {
        return this.horashombre;
    }
    
    public void setHorashombre(Date horashombre) {
        this.horashombre = horashombre;
    }




}


