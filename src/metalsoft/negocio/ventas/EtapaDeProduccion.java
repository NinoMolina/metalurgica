//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\ventas\\EtapaDeProduccion.java

package metalsoft.negocio.ventas;

import java.sql.Connection;
import java.sql.Time;
import java.util.Date;
import java.util.Timer;
import metalsoft.negocio.access.AccessEtapaDeProduccion;
import metalsoft.negocio.produccion.EjecucionEtapaDeProduccion;
import metalsoft.negocio.mantmaquinarias.Maquina;
import metalsoft.negocio.mantmaquinarias.TipoMaquina;
import metalsoft.negocio.produccion.UnidadMedida;
import metalsoft.negocio.rrhh.Empleado;

public class EtapaDeProduccion 
{
   private long numeroEtapa;
   private String nombre;
   private Date horasMaquina;
   private Date horasHombre;
   private TipoMaquina maquina;
   private UnidadMedida unidadMedida;
   private Date duracionEstimadaXUnidMed;
   private Date fechaCreacion;
   private EjecucionEtapaDeProduccion ejecucionEtapa;
   public EjecucionEtapaDeProduccion theEjecucionEtapaDeProduccion;
   public Maquina theMaquina;
   public Empleado theEmpleado;

   public long guardarEtapaDeProduccion(EtapaDeProduccion et,long idMaquina,long idunidadmedida, Connection cn)
   {
       long result=-1;
       result=AccessEtapaDeProduccion.insert(et, idMaquina, idunidadmedida, cn);

       return result;
   }
   public long modificarEtapaDeProduccion(EtapaDeProduccion et,long idEtapaDeProduccion,long idMaquina, long idUnidadMedida,Connection cn)
   {
       long result=-1;
       result=AccessEtapaDeProduccion.update(et, idEtapaDeProduccion, idMaquina, idUnidadMedida, cn);

       return result;
   }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Date getDuracionEstimadaXUnidMed() {
        return duracionEstimadaXUnidMed;
    }

    public void setDuracionEstimadaXUnidMed(Date duracionEstimadaXUnidMed) {
        this.duracionEstimadaXUnidMed = duracionEstimadaXUnidMed;
    }

    public EjecucionEtapaDeProduccion getEjecucionEtapa() {
        return ejecucionEtapa;
    }

    public void setEjecucionEtapa(EjecucionEtapaDeProduccion ejecucionEtapa) {
        this.ejecucionEtapa = ejecucionEtapa;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getHorasHombre() {
        return horasHombre;
    }

    public void setHorasHombre(Date horasHombre) {
        this.horasHombre = horasHombre;
    }

    public Date getHorasMaquina() {
        return horasMaquina;
    }

    public void setHorasMaquina(Date horasMaquina) {
        this.horasMaquina = horasMaquina;
    }

    public TipoMaquina getMaquina() {
        return maquina;
    }

    public void setMaquina(TipoMaquina maquina) {
        this.maquina = maquina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getNumeroEtapa() {
        return numeroEtapa;
    }

    public void setNumeroEtapa(long numeroEtapa) {
        this.numeroEtapa = numeroEtapa;
    }

    public EjecucionEtapaDeProduccion getTheEjecucionEtapaDeProduccion() {
        return theEjecucionEtapaDeProduccion;
    }

    public void setTheEjecucionEtapaDeProduccion(EjecucionEtapaDeProduccion theEjecucionEtapaDeProduccion) {
        this.theEjecucionEtapaDeProduccion = theEjecucionEtapaDeProduccion;
    }

    public Empleado getTheEmpleado() {
        return theEmpleado;
    }

    public void setTheEmpleado(Empleado theEmpleado) {
        this.theEmpleado = theEmpleado;
    }

    public Maquina getTheMaquina() {
        return theMaquina;
    }

    public void setTheMaquina(Maquina theMaquina) {
        this.theMaquina = theMaquina;
    }

   /**
    * @roseuid 4C27ED2402FC
    */
   public EtapaDeProduccion() 
   {
    
   }
   
   /**
    * @roseuid 4BC24F6801B8
    */
   public void crear() 
   {
    
   }
   
   /**
    * @roseuid 4BC24F6C006A
    */
   public void conocerMaquina() 
   {
    
   }
   
   /**
    * @roseuid 4BE712FD0228
    */
   public void calcDuracionPromedio() 
   {
    
   }
   
   /**
    * @roseuid 4BE7131102FC
    */
   public void construirEjecEtapaProd() 
   {
    
   }
   
   /**
    * @roseuid 4C184C88018D
    */
   public void conocerEjecucionEtapaProduccion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FB7B90174
    */
   public void tomarCambios() 
   {
    
   }
}
