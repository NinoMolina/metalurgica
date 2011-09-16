//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\ventas\\PlanProcesosCalidad.java

package metalsoft.negocio.ventas;

import metalsoft.negocio.calidad.ProcesoCalidad;


public class DetallePrsptoProcesosCalidad
{
   private int cantidadProcesosCalidad;
   private ProcesoCalidad procesoPorPieza;
   private int duracionEstimadaPorProceso;
   
   /**
    * @roseuid 4C27ED14039A
    */
   public DetallePrsptoProcesosCalidad()
   {
    
   }

    public int getCantidadProcesosCalidad() {
        return cantidadProcesosCalidad;
    }

    public void setCantidadProcesosCalidad(int cantidadProcesosCalidad) {
        this.cantidadProcesosCalidad = cantidadProcesosCalidad;
    }

    public int getDuracionEstimadaPorProceso() {
        return duracionEstimadaPorProceso;
    }

    public void setDuracionEstimadaPorProceso(int duracionEstimadaPorProceso) {
        this.duracionEstimadaPorProceso = duracionEstimadaPorProceso;
    }

    public ProcesoCalidad getProcesoPorPieza() {
        return procesoPorPieza;
    }

    public void setProcesoPorPieza(ProcesoCalidad procesoPorPieza) {
        this.procesoPorPieza = procesoPorPieza;
    }
   
}
