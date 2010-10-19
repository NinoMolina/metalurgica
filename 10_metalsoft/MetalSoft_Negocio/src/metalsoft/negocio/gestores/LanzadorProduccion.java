/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.util.HashSet;
import metalsoft.datos.dbobject.PedidoDB;
import metalsoft.datos.dbobject.Planificacionproduccion;

/**
 *
 * @author Nino
 */
public class LanzadorProduccion {

    private static HashSet<HiloProduccion> hash;
    public static void main(String args[]){
        hash=new HashSet<HiloProduccion>();
    }

    public static void lanzarProduccion(PedidoDB pedido, Planificacionproduccion planificacion){
        HiloProduccion hilo=new HiloProduccion();
        hilo.setPedido(pedido);
        hilo.setPlanificacion(planificacion);
        Thread thread=new Thread(hilo);
        hash.add(hilo);
        thread.start();
    }
}
