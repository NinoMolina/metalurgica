/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft;

import metalsoft.datos.dbobject.UsuarioDB;
import metalsoft.presentacion.Principal;

/**
 *
 * @author Nino
 */
public class MetalsoftDispatcher {

    public static void dispatchUser(UsuarioDB usuario) {
        /*
         * TODO
         * Segun el usuario (su rol) se tiene que lanzar o no el escucha
         * del fin de las etapas de produccion o las alertas de etapas que
         * deberian haber terminado.
         */

        Principal p = new Principal(usuario);

        lanzarHiloAvisoEtapaNoTerminada(p);
        lanzarHiloEscuchadorFinEtapa();

        p.setVisible(true);
        p.setLocationRelativeTo(null);
    }

    private static void lanzarHiloEscuchadorFinEtapa(){
        HiloEscuchadorFinEtapa hilo = new HiloEscuchadorFinEtapa();
        hilo.start();
    }

    private static void lanzarHiloAvisoEtapaNoTerminada(Principal vtnPrincipal){
        HiloAvisoEtapaNoTerminada hilo = new HiloAvisoEtapaNoTerminada();
        hilo.setVtnPrincipal(vtnPrincipal);
        hilo.start();
    }
}
