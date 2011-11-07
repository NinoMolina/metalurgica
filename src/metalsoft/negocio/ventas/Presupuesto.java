//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\ventas\\Presupuesto.java

package metalsoft.negocio.ventas;

import java.util.ArrayList;
import java.util.Date;


public class Presupuesto 
{
   private long nroPresupuesto;
   private Date fechaPresupuesto;
   private float montoTotal;
   private DetallePresupuesto detalle;
   private Date fechaVencimiento;
   private ArrayList<DetallePresupuesto> detallePresupuesto;
//   private ArrayList<DetallePrsptoMateriaPrima> detalleMateriaPrima;
//   private ArrayList<DetalleProductoPresupuesto> detalleProcedimientos;
//   private ArrayList<DetallePrsptoProcesosCalidad> detalleProcesosCalidad;

   
   /**
    * @roseuid 4C27ED140181
    */
   public Presupuesto() 
   {
    
   }

    public DetallePresupuesto getDetalle() {
        return detalle;
    }

    public void setDetalle(DetallePresupuesto detalle) {
        this.detalle = detalle;
    }

//    public ArrayList<DetallePrsptoMateriaPrima> getDetalleMateriaPrima() {
//        return detalleMateriaPrima;
//    }
//
//    public void setDetalleMateriaPrima(ArrayList<DetallePrsptoMateriaPrima> detalleMateriaPrima) {
//        this.detalleMateriaPrima = detalleMateriaPrima;
//    }

    public ArrayList<DetallePresupuesto> getDetallePresupuesto() {
        return detallePresupuesto;
    }

    public void setDetallePresupuesto(ArrayList<DetallePresupuesto> detallePresupuesto) {
        this.detallePresupuesto = detallePresupuesto;
    }

//    public ArrayList<DetalleProductoPresupuesto> getDetalleProcedimientos() {
//        return detalleProcedimientos;
//    }
//
//    public void setDetalleProcedimientos(ArrayList<DetalleProductoPresupuesto> detalleProcedimientos) {
//        this.detalleProcedimientos = detalleProcedimientos;
//    }
//
//    public ArrayList<DetallePrsptoProcesosCalidad> getDetalleProcesosCalidad() {
//        return detalleProcesosCalidad;
//    }
//
//    public void setDetalleProcesosCalidad(ArrayList<DetallePrsptoProcesosCalidad> detalleProcesosCalidad) {
//        this.detalleProcesosCalidad = detalleProcesosCalidad;
//    }

    public Date getFechaPresupuesto() {
        return fechaPresupuesto;
    }

    public void setFechaPresupuesto(Date fechaPresupuesto) {
        this.fechaPresupuesto = fechaPresupuesto;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public float getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(float montoTotal) {
        this.montoTotal = montoTotal;
    }

    public long getNroPresupuesto() {
        return nroPresupuesto;
    }

    public void setNroPresupuesto(long nroPresupuesto) {
        this.nroPresupuesto = nroPresupuesto;
    }


   /**
    * @roseuid 4BC262090257
    */
   public void crear() 
   {
    
   }
   
   /**
    * @roseuid 4BC2620B02D0
    */
   public void conocerDetallePresupuesto() 
   {
    
   }
   
   /**
    * @roseuid 4C1FA5FF0189
    */
   public void esPresupuesto() 
   {
    
   }

    public DetallePresupuesto crearDetallePresupuesto() {
        DetallePresupuesto dp=new DetallePresupuesto();
        return dp;
    }
}
