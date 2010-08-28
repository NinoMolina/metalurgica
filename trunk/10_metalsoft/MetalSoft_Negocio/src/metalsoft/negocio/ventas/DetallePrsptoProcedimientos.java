//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\ventas\\PlanProcedimientos.java

package metalsoft.negocio.ventas;

import java.util.Date;


public class DetallePrsptoProcedimientos
{
   private int cantidadEtapasProduccion;
   private EtapaDeProduccion etapa;
   //private Pieza pieza;
   private Date duracionEstimadaPorEtapa;
   
   /**
    * @roseuid 4C27ED190048
    */
   public DetallePrsptoProcedimientos()
   {
    
   }

    public int getCantidadEtapasProduccion() {
        return cantidadEtapasProduccion;
    }

    public void setCantidadEtapasProduccion(int cantidadEtapasProduccion) {
        this.cantidadEtapasProduccion = cantidadEtapasProduccion;
    }

    public Date getDuracionEstimadaPorEtapa() {
        return duracionEstimadaPorEtapa;
    }

    public void setDuracionEstimadaPorEtapa(Date duracionEstimadaPorEtapa) {
        this.duracionEstimadaPorEtapa = duracionEstimadaPorEtapa;
    }

    public EtapaDeProduccion getEtapa() {
        return etapa;
    }

    public void setEtapa(EtapaDeProduccion etapa) {
        this.etapa = etapa;
    }

   

}
