/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.util.Date;
import metalsoft.datos.dbobject.PedidoDB;
import metalsoft.datos.dbobject.Planificacionproduccion;

/**
 *
 * @author Nino
 */
public class HiloProduccion implements Runnable{

    private PedidoDB pedido;
    private Planificacionproduccion planificacion;
    private Date fechaInicio,fechaFin;
    public void run() {
        
    }

    public PedidoDB getPedido() {
        return pedido;
    }

    public void setPedido(PedidoDB pedido) {
        this.pedido = pedido;
    }

    public Planificacionproduccion getPlanificacion() {
        return planificacion;
    }

    public void setPlanificacion(Planificacionproduccion planificacion) {
        this.planificacion = planificacion;
    }

    
}
