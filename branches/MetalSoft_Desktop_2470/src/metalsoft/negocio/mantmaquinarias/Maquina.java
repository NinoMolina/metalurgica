//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\mantmaquinarias\\Maquina.java

package metalsoft.negocio.mantmaquinarias;

import java.sql.Time;
import java.util.Date;
import metalsoft.negocio.produccion.UnidadMedida;


public class Maquina 
{
   private long nroMaquina;
   private String nombre;
   private Marca marca;
   private String descripcion;
   private EstadoMaquina estado;
   private MantenimientoPreventivo mantenimientoPreventivo;
   private MantenimientoCorrectivo mantenimientoCorrectivo;
   private TipoMaquina tipo;
   private Date fechaAlta;
   private Date fechaBaja;
   private Time tiempoCapacidadProduccion;
   private UnidadMedida unidadMedida;
   public EstadoMaquina theEstadoMaquina;
   public Marca theMarca;
   public TipoMaquina theTipoMaquina;
   public MantenimientoPreventivo theMantenimientoPreventivo[];
   public MantenimientoCorrectivo theMantenimientoCorrectivo[];

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EstadoMaquina getEstado() {
        return estado;
    }

    public void setEstado(EstadoMaquina estado) {
        this.estado = estado;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public MantenimientoCorrectivo getMantenimientoCorrectivo() {
        return mantenimientoCorrectivo;
    }

    public void setMantenimientoCorrectivo(MantenimientoCorrectivo mantenimientoCorrectivo) {
        this.mantenimientoCorrectivo = mantenimientoCorrectivo;
    }

    public MantenimientoPreventivo getMantenimientoPreventivo() {
        return mantenimientoPreventivo;
    }

    public void setMantenimientoPreventivo(MantenimientoPreventivo mantenimientoPreventivo) {
        this.mantenimientoPreventivo = mantenimientoPreventivo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getNroMaquina() {
        return nroMaquina;
    }

    public void setNroMaquina(long nroMaquina) {
        this.nroMaquina = nroMaquina;
    }

    public Time getTiempoCapacidadProduccion() {
        return tiempoCapacidadProduccion;
    }

    public void setTiempoCapacidadProduccion(Time tiempoCapacidadProduccion) {
        this.tiempoCapacidadProduccion = tiempoCapacidadProduccion;
    }

    public TipoMaquina getTipo() {
        return tipo;
    }

    public void setTipo(TipoMaquina tipo) {
        this.tipo = tipo;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
   
   /**
    * @roseuid 4C27FA9E03DC
    */
   public Maquina() 
   {
    
   }
   
   /**
    * @roseuid 4BC24CBC02AC
    */
   public void crear() 
   {
    
   }
   
   /**
    * @roseuid 4BC24CD7038A
    */
   public void conocerMantenimientoPreventivo() 
   {
    
   }
   
   /**
    * @roseuid 4BC24CDF0226
    */
   public void conocerTipoMaquina() 
   {
    
   }
   
   /**
    * @roseuid 4BE0AD0F0024
    */
   public void conocerMantenimientoCorrectivo() 
   {
    
   }
   
   /**
    * @roseuid 4C17FB050341
    */
   public void conocerEstadoMaquina() 
   {
    
   }
   
   /**
    * @roseuid 4C17FB1A0305
    */
   public void conocerMarca() 
   {
    
   }
   
   /**
    * @roseuid 4C1FDDFE031B
    */
   public void tomarCambios() 
   {
    
   }
}
