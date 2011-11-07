/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.util.Date;
import metalsoft.datos.jpa.entity.Planificacionproduccion;


/**
 *
 * @author Nino
 */
public class HiloProduccion implements Runnable{

    private Planificacionproduccion planificacion;
    private Date fechaInicio,fechaFin;
    public void run() {
        
    }

    public Planificacionproduccion getPlanificacion() {
        return planificacion;
    }

    public void setPlanificacion(Planificacionproduccion planificacion) {
        this.planificacion = planificacion;
    }

    
}
