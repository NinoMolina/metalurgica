/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;


/**
 *
 * @author Nino
 */
public class PiezaXProcesosCalidad extends PiezaXAlgo{

    private int cantProcesoCalidad;
    private ViewProcesoCalidad procesosCalidad;

    public PiezaXProcesosCalidad() {
    }

    public int getCantProcesoCalidad() {
        return cantProcesoCalidad;
    }

    public void setCantProcesoCalidad(int cantProcesoCalidad) {
        this.cantProcesoCalidad = cantProcesoCalidad;
    }

    public ViewProcesoCalidad getProcesoCalidad() {
        return procesosCalidad;
    }

    public void setProcesoCalidad(ViewProcesoCalidad procesosCalidad) {
        this.procesosCalidad = procesosCalidad;
    }

    
}
