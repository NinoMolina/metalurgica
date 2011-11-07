/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.util.LinkedList;


/**
 *
 * @author Nino
 */
public class PiezaXProcesosCalidad extends PiezaXAlgo{
    
    private LinkedList<ViewProcesoCalidad> procesosCalidad;

    public PiezaXProcesosCalidad() {
    }


    public LinkedList<ViewProcesoCalidad> getProcesoCalidad() {
        return procesosCalidad;
    }

    public void setProcesoCalidad(LinkedList<ViewProcesoCalidad> procesosCalidad) {
        this.procesosCalidad = procesosCalidad;
    }

    
}
