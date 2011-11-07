/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.calidad;

import java.util.Date;
import metalsoft.util.Calculos;

/**
 *
 * @author Nino
 */
public class DetallePiezaCalidadPresupuesto {

    private int cantidadProcesoCalidad;
    private Date duracionXPieza;
    private ProcesoCalidad procesoCalidad;

    public DetallePiezaCalidadPresupuesto() {
    }

    public int getCantidadProcesoCalidad() {
        return cantidadProcesoCalidad;
    }

    public void setCantidadProcesoCalidad(int cantidadProcesoCalidad) {
        this.cantidadProcesoCalidad = cantidadProcesoCalidad;
    }

    public Date getDuracionXPieza() {
        return duracionXPieza;
    }

    public void setDuracionXPieza(Date duracionXPieza) {
        this.duracionXPieza = duracionXPieza;
    }

    public ProcesoCalidad getProcesoCalidad() {
        return procesoCalidad;
    }

    public void setProcesoCalidad(ProcesoCalidad procesoCalidad) {
        this.procesoCalidad = procesoCalidad;
    }

    public Date calcularDuracionXPieza(Date duracionestimada) {
        return Calculos.calcularDuracionPiezaXProcesoCalidad(duracionestimada);
    }


}
