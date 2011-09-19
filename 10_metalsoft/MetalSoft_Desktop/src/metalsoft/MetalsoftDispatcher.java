/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft;

import java.util.List;
import javax.swing.JOptionPane;
import metalsoft.datos.dbobject.UsuarioDB;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.UsuarioJpaController;
import metalsoft.datos.jpa.entity.Usuario;
import metalsoft.datos.jpa.entity.Usuarioxrol;
import metalsoft.presentacion.Principal;
import metalsoft.presentacion.PrincipalOperario;

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

        UsuarioJpaController usuarioController = new UsuarioJpaController(JpaUtil.getEntityManagerFactory());
        Usuario usuarioJpa = usuarioController.findUsuario(usuario.getIdusuario());

        List<Usuarioxrol> lstUsuarioXRol = usuarioJpa.getUsuarioxrolList();

        if (lstUsuarioXRol.size() == 1) {
            String rol = lstUsuarioXRol.get(0).getRol().getRol();
            if (rol.equals("OPERARIO")) {
                PrincipalOperario principalOperario = new PrincipalOperario();
                principalOperario.setVisible(true);
                principalOperario.setLocationRelativeTo(null);
            } else if (rol.equals("ADMIN")) {
                Principal p = new Principal(usuario);

                lanzarHiloAvisoEtapaNoTerminada(p);
                lanzarHiloEscuchadorFinEtapa(p);
                lanzarHiloAvisoProcesoCalidadNoTerminado(p);
                lanzarHiloEscuchadorFinProcesoCalidad(p);

                p.setVisible(true);
                p.setLocationRelativeTo(null);
            } else {
                JOptionPane.showMessageDialog(null, "El usuario " + usuarioJpa.getUsuario() + " no tiene ningún rol asociado.\nNo se puede iniciar la aplicación");
            }
        }
    }

    private static void lanzarHiloEscuchadorFinEtapa(Principal vtnPrincipal) {
        HiloEscuchadorFinEtapa hilo = new HiloEscuchadorFinEtapa();
        Thread thread = new Thread(hilo);
        hilo.setVtnPrincipal(vtnPrincipal);
        thread.start();
    }

    private static void lanzarHiloAvisoEtapaNoTerminada(Principal vtnPrincipal) {
        HiloAvisoEtapaNoTerminada hilo = new HiloAvisoEtapaNoTerminada();
        hilo.setVtnPrincipal(vtnPrincipal);
        Thread thread = new Thread(hilo);
        thread.start();
    }

    private static void lanzarHiloAvisoProcesoCalidadNoTerminado(Principal vtnPrincipal) {
        HiloAvisoProcesoCalidadNoTerminado hilo = new HiloAvisoProcesoCalidadNoTerminado();
        hilo.setVtnPrincipal(vtnPrincipal);
        Thread thread = new Thread(hilo);
        thread.start();
    }

    private static void lanzarHiloEscuchadorFinProcesoCalidad(Principal vtnPrincipal) {
        HiloEscuchadorFinProcesoCalidad hilo = new HiloEscuchadorFinProcesoCalidad();
        hilo.setVtnPrincipal(vtnPrincipal);
        Thread thread = new Thread(hilo);
        thread.start();
    }
}
