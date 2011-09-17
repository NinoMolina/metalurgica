//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\rrhh\\Calendario.java

package metalsoft.negocio.rrhh;

import java.util.Date;


public class Calendario 
{
   private int dia;
   private int mes;
   private int anio;
   private boolean todoElDia;
   private Date horaDesde,horaHasta;
   private Date fecha;
   
   /**
    * @roseuid 4C27ED0C0351
    */
   public Calendario() 
   {
   }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHoraDesde() {
        return horaDesde;
    }

    public void setHoraDesde(Date horaDesde) {
        this.horaDesde = horaDesde;
    }

    public Date getHoraHasta() {
        return horaHasta;
    }

    public void setHoraHasta(Date horaHasta) {
        this.horaHasta = horaHasta;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public boolean isTodoElDia() {
        return todoElDia;
    }

    public void setTodoElDia(boolean todoElDia) {
        this.todoElDia = todoElDia;
    }
   
}
