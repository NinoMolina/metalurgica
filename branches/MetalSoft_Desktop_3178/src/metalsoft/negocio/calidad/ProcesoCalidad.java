//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\calidad\\ProcesoCalidad.java

package metalsoft.negocio.calidad;

import java.util.Date;
import metalsoft.negocio.mantmaquinarias.Maquina;

public class ProcesoCalidad 
{
   private String nombre;
   private long nroProceso;
   private String especificacion;
   private String tolerancia;
   private String descripcion;
   private Date duracionEstimada;
   private Date fechaCreacion;
   private Maquina maquina;
   private String herramienta;
   private AccionCalidad accion;
   public EjecucionProcesoCalidad theEjecucionProcesoCalidad[];
   public Maquina theMaquina[];
   public AccionCalidad theAccionCalidad;

    public AccionCalidad getAccion() {
        return accion;
    }

    public void setAccion(AccionCalidad accion) {
        this.accion = accion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(Date duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    public String getEspecificacion() {
        return especificacion;
    }

    public void setEspecificacion(String especificacion) {
        this.especificacion = especificacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getHerramienta() {
        return herramienta;
    }

    public void setHerramienta(String herramienta) {
        this.herramienta = herramienta;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getNroProceso() {
        return nroProceso;
    }

    public void setNroProceso(long nroProceso) {
        this.nroProceso = nroProceso;
    }

    public String getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(String tolerancia) {
        this.tolerancia = tolerancia;
    }
   
   /**
    * @roseuid 4C27ED1D03B4
    */
   public ProcesoCalidad() 
   {
    
   }
   
   /**
    * @roseuid 4BC24D970174
    */
   public void crear() 
   {
    
   }
   
   /**
    * @roseuid 4C1852D602A7
    */
   public void conoerAccionCalidad() 
   {
    
   }
   
   /**
    * @roseuid 4C1852E400EE
    */
   public void conocerMaquina() 
   {
    
   }
}
