//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\rrhh\\Empleado.java

package metalsoft.negocio.rrhh;

import java.sql.Connection;
import java.util.Date;
import metalsoft.negocio.access.AccessDomicilio;
import metalsoft.negocio.adminusuarios.Usuario;

public class Empleado extends Persona 
{
   private long legajo;
   private Date fechaIngreso;
   private Turno turno;
   private Categoria categoria;
   private Asistencia asistencia;
   private Usuario usuario;
   private Date fechaEgreso;
   private String motivoEgreso;
   private Cargo cargo;
   public Usuario theUsuario;
   public TipoDocumento theTipoDocumento;
   public Domicilio theDomicilio;
   public Cargo theCargo;
   public Categoria theCategoria;
   public Turno theTurno[];
   public Asistencia theAsistencia[];

   public long crearDomicilio(metalsoft.negocio.rrhh.Domicilio dom, long idBarrio, Connection cn)
   {
        long result=-1;
        result=AccessDomicilio.registrarDomicilio(dom, idBarrio, cn);
        return result;
   }
    public Asistencia getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Asistencia asistencia) {
        this.asistencia = asistencia;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Date getFechaEgreso() {
        return fechaEgreso;
    }

    public void setFechaEgreso(Date fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public long getLegajo() {
        return legajo;
    }

    public void setLegajo(long legajo) {
        this.legajo = legajo;
    }

    public String getMotivoEgreso() {
        return motivoEgreso;
    }

    public void setMotivoEgreso(String motivoEgreso) {
        this.motivoEgreso = motivoEgreso;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
   
   /**
    * @roseuid 4C27F4650106
    */
   public Empleado() 
   {
    
   }
   
   /**
    * @roseuid 4BC25D9200B3
    */
   public void conocerTurno() 
   {
    
   }
   
   /**
    * @roseuid 4BC25D9B01AE
    */
   public void conocerCategoria() 
   {
    
   }
   
   /**
    * @roseuid 4BC25D9F0215
    */
   public void conocerUsusario() 
   {
    
   }
   
   /**
    * @roseuid 4BC25DA30317
    */
   public void conocerAsistencia() 
   {
    
   }
   
   /**
    * @roseuid 4C1F84E901BC
    */
   public void tomarModificaciones() 
   {
    
   }
   
   /**
    * @roseuid 4C1F851503E6
    */
   public void conocerSueldo() 
   {
    
   }
   
   /**
    * @roseuid 4C1FAA2703D9
    */
   public void esEmpleado() 
   {
    
   }
   
   /**
    * @roseuid 4C1FABEB0200
    */
   public void tomarPermisos() 
   {
    
   }
}
