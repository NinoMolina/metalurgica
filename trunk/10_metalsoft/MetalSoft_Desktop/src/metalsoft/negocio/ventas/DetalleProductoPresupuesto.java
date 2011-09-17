//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\ventas\\PlanProcedimientos.java

package metalsoft.negocio.ventas;

import java.util.ArrayList;
import java.util.Date;
import metalsoft.negocio.almacenamiento.MateriaPrima;
import metalsoft.util.Calculos;


public class DetalleProductoPresupuesto
{


   private ArrayList<DetallePiezaPresupuesto> detallePiezaPresupuesto;
   private Pieza pieza;
   private MateriaPrima materiaPrima;
   private double preciomateriaprima;
   int cantidadPieza,cantidadMateriaPrima;
   /**
    * @roseuid 4C27ED190048
    */
   public DetalleProductoPresupuesto()
   {
    
   }

    public double getPreciomateriaprima() {
        return preciomateriaprima;
    }

    public void setPreciomateriaprima(double preciomateriaprima) {
        this.preciomateriaprima = preciomateriaprima;
    }

    public int getCantidadMateriaPrima() {
        return cantidadMateriaPrima;
    }

    public void setCantidadMateriaPrima(int cantidadMateriaPrima) {
        this.cantidadMateriaPrima = cantidadMateriaPrima;
    }

    public int getCantidadPieza() {
        return cantidadPieza;
    }

    public void setCantidadPieza(int cantidadPieza) {
        this.cantidadPieza = cantidadPieza;
    }

    public MateriaPrima getMateriaPrima() {
        return materiaPrima;
    }

    public void setMateriaPrima(MateriaPrima materiaPrima) {
        this.materiaPrima = materiaPrima;
    }
   

   public boolean agregarDetallePiezaPresupuesto(DetallePiezaPresupuesto dpp)
   {
       if(detallePiezaPresupuesto==null)detallePiezaPresupuesto=new ArrayList<DetallePiezaPresupuesto>();
       return detallePiezaPresupuesto.add(dpp);
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


    public static int calcularCapacidadMateriaPrima(double altoMatPrima, double anchoMatPrima, double largoMatPrima, double altoPieza, double anchoPieza, double largoPieza, String nombrePieza, String nombreMateriaPrima) {
        return Calculos.calcularCapacidadMateriaPrima(altoMatPrima,anchoMatPrima,largoMatPrima,altoPieza,anchoPieza,largoPieza,nombrePieza,nombreMateriaPrima);
    }

    public static int calcularCantidadMateriaPrima(int capacidadMatPrima, int cantPiezas) {
        return Calculos.calcularCantidadMateriaPrima(capacidadMatPrima,cantPiezas);
    }
    
}
