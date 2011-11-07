//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\rrhh\\Asistencia.java

package metalsoft.negocio.rrhh;

import java.sql.Connection;
import java.sql.Time;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.AsistenciaDB;
import metalsoft.datos.dbobject.AsistenciaPKDB;
import metalsoft.datos.exception.AsistenciaException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.AsistenciaDAO;
import metalsoft.util.Fecha;


public class Asistencia 
{
   private Date fechaIngreso;
   private Time horaIngreso;
   private Time horaEgreso;
   private String observaciones;
   private Empleado empleado;

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Time getHoraEgreso() {
        return horaEgreso;
    }

    public void setHoraEgreso(Time horaEgreso) {
        this.horaEgreso = horaEgreso;
    }

    public Time getHoraIngreso() {
        return horaIngreso;
    }

    public void setHoraIngreso(Time horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    public static long insert(AsistenciaDB asistencia, Connection cn) {
        long result=-1;
        AsistenciaDAO dao=new DAOFactoryImpl().createAsistenciaDAO();

        try {
            result=dao.insert(asistencia, cn);
        } catch (Exception ex) {
            Logger.getLogger(Asistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long update(AsistenciaDB asistencia, Connection cn) {
        long result=-1;
        AsistenciaDAO dao=new DAOFactoryImpl().createAsistenciaDAO();

        AsistenciaPKDB pk=new AsistenciaPKDB(asistencia.getEmpleado(), asistencia.getFechaingreso(), asistencia.getHoraingreso());
        try {
            asistencia.setHoraegreso(Fecha.parseToTimeSQL(Fecha.parseToDate(Fecha.fechaHoraMinutoSegundoActual())));

            result=dao.update(pk,asistencia, cn);
        } catch (AsistenciaException ex) {
            Logger.getLogger(Asistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
   /**
    * @roseuid 4C27ED180028
    */
   public Asistencia() 
   {
    
   }
   
   /**
    * @roseuid 4BC2602F005D
    */
   public void crear() 
   {
    
   }
}
