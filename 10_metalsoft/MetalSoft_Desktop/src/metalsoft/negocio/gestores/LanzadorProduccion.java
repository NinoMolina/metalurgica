/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.util.HashSet;
import metalsoft.datos.dbobject.PedidoDB;
import metalsoft.datos.jpa.entity.Planificacionproduccion;


/**
 *
 * @author Nino
 */
public class LanzadorProduccion {

    private static HashSet<HiloProduccion> hash;
    public static void main(String args[]){
        hash=new HashSet<HiloProduccion>();
    }

    public static void lanzarProduccion(Planificacionproduccion planificacion){
        HiloProduccion hilo=new HiloProduccion();
        hilo.setPlanificacion(planificacion);
        Thread thread=new Thread(hilo);
        hash.add(hilo);
        thread.start();
    }
}
