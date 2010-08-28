//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\ventas\\PlanRequerimientosMateriaPrima.java

package metalsoft.negocio.ventas;

import metalsoft.negocio.almacenamiento.MateriaPrima;


public class DetallePrsptoMateriaPrima
{
   private int cantMateriaPrimaPorPieza;
   private MateriaPrima materiaPrima;
   
   /**
    * @roseuid 4C27ED160318
    */
   public DetallePrsptoMateriaPrima()
   {
    
   }

    public int getCantMateriaPrimaPorPieza() {
        return cantMateriaPrimaPorPieza;
    }

    public void setCantMateriaPrimaPorPieza(int cantMateriaPrimaPorPieza) {
        this.cantMateriaPrimaPorPieza = cantMateriaPrimaPorPieza;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }



}
