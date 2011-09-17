//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\produccion\\PlanificacionProduccion.java

package metalsoft.negocio.produccion;


public class PlanificacionProduccion extends Planificacion 
{
   private DetallePlanificacionProduccion detalle;

    public DetallePlanificacionProduccion getDetalle() {
        return detalle;
    }

    public void setDetalle(DetallePlanificacionProduccion detalle) {
        this.detalle = detalle;
    }

    public PlanificacionProduccion() {
    }

    public PlanificacionProduccion(DetallePlanificacionProduccion detalle) {
        this.detalle = detalle;
    }
   
}
