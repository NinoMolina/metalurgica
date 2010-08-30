//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\ventas\\PlanProcedimientos.java

package metalsoft.negocio.ventas;

import java.util.ArrayList;
import java.util.Date;


public class DetalleProductoPresupuesto
{
   private int cantidadEtapasProduccion;
   private ArrayList<DetallePiezaPresupuesto> detallePiezaPresupuesto;
   private Pieza pieza;
   
   /**
    * @roseuid 4C27ED190048
    */
   public DetalleProductoPresupuesto()
   {
    
   }

   public boolean agregarDetallePiezaPresupuesto(DetallePiezaPresupuesto dpp)
   {
       if(detallePiezaPresupuesto==null)detallePiezaPresupuesto=new ArrayList<DetallePiezaPresupuesto>();
       return detallePiezaPresupuesto.add(dpp);
   }

    public int getCantidadEtapasProduccion() {
        return cantidadEtapasProduccion;
    }

    public void setCantidadEtapasProduccion(int cantidadEtapasProduccion) {
        this.cantidadEtapasProduccion = cantidadEtapasProduccion;
    }

    public ArrayList<DetallePiezaPresupuesto> getDetallePiezaPresupuesto() {
        return detallePiezaPresupuesto;
    }

    public void setDetallePiezaPresupuesto(ArrayList<DetallePiezaPresupuesto> detallePiezaPresupuesto) {
        this.detallePiezaPresupuesto = detallePiezaPresupuesto;
    }

    public Pieza getPieza() {
        return pieza;
    }

    public void setPieza(Pieza pieza) {
        this.pieza = pieza;
    }

    public DetallePiezaPresupuesto crearDetallePiezaPresupuesto() {
        return new DetallePiezaPresupuesto();
    }
   
    
}
