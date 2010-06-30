//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\ventas\\EtapaDeProduccion.java

package metalsoft.negocio.ventas;

import metalsoft.negocio.produccion.EjecucionEtapaDeProduccion;
import metalsoft.negocio.mantmaquinarias.Maquina;
import metalsoft.negocio.rrhh.Empleado;

public class EtapaDeProduccion 
{
   private int numeroEtapa;
   private int nombre;
   private int horasMaquina;
   private int horasHombre;
   private Maquina maquina;
   private int duracionEstimadaXUnidMed;
   private int fechaCreacion;
   private int unidadDeMedida;
   private EjecucionEtapaDeProduccion ejecucionEtapa;
   public EjecucionEtapaDeProduccion theEjecucionEtapaDeProduccion;
   public Maquina theMaquina;
   public Empleado theEmpleado;


   /**
    * @roseuid 4C27ED2402FC
    */
   public EtapaDeProduccion() 
   {
    
   }
   public int getDuracionEstimadaXUnidMed() {
        return duracionEstimadaXUnidMed;
    }

    public void setDuracionEstimadaXUnidMed(int duracionEstimadaXUnidMed) {
        this.duracionEstimadaXUnidMed = duracionEstimadaXUnidMed;
    }

    public EjecucionEtapaDeProduccion getEjecucionEtapa() {
        return ejecucionEtapa;
    }

    public void setEjecucionEtapa(EjecucionEtapaDeProduccion ejecucionEtapa) {
        this.ejecucionEtapa = ejecucionEtapa;
    }

    public int getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(int fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getHorasHombre() {
        return horasHombre;
    }

    public void setHorasHombre(int horasHombre) {
        this.horasHombre = horasHombre;
    }

    public int getHorasMaquina() {
        return horasMaquina;
    }

    public void setHorasMaquina(int horasMaquina) {
        this.horasMaquina = horasMaquina;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public int getNumeroEtapa() {
        return numeroEtapa;
    }

    public void setNumeroEtapa(int numeroEtapa) {
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

    public int getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(int unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
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
