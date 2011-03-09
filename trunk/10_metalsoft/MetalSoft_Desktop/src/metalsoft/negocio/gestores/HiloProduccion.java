/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.util.Date;
import metalsoft.datos.dbobject.PedidoDB;
import metalsoft.datos.dbobject.PlanificacionproduccionDB;

/**
 *
 * @author Nino
 */
public class HiloProduccion implements Runnable{

    private PedidoDB pedido;
    private PlanificacionproduccionDB planificacion;
    private Date fechaInicio,fechaFin;
    public void run() {
        
    }

    public PedidoDB getPedido() {
        return pedido;
    }

    public void setPedido(PedidoDB pedido) {
        this.pedido = pedido;
    }

    public PlanificacionproduccionDB getPlanificacion() {
        return planificacion;
    }

    public void setPlanificacion(PlanificacionproduccionDB planificacion) {
        this.planificacion = planificacion;
    }

    
}
