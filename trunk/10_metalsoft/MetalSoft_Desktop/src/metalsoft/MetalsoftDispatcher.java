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
                lanzarHiloAvisoEtapaListaParaIniciar(p);
                lanzarHiloAvisoProcesoCalidadNoTerminado(p);
                lanzarHiloEscuchadorFinProcesoCalidad(p);
                lanzarHiloAvisoProcesoCalidadListoParaLanzar(p);

                p.setVisible(true);
                p.setLocationRelativeTo(null);
            } else {
                JOptionPane.showMessageDialog(null, "El usuario " + usuarioJpa.getUsuario() + " no tiene ningún rol asociado.\nNo se puede iniciar la aplicación");
            }
        }


//        boolean lanzarHiloAvisoEtapaNoTerminada = false;
//        boolean lanzarHiloEscuchadorFinEtapa = false;
//        boolean lanzarHiloAvisoEtapaListaParaIniciar = false;
//        boolean lanzarHiloAvisoProcesoCalidadNoTerminado = false;
//        boolean lanzarHiloEscuchadorFinProcesoCalidad = false;
//        boolean lanzarHiloAvisoProcesoCalidadListoParaLanzar = false;
//
//        boolean usuarioConRol = false;
//        for (Usuarioxrol usuarioxrol : lstUsuarioXRol) {
//            String rol = usuarioxrol.getRol().getRol();
//            if (rol.equals("OPERARIO")) {
//                PrincipalOperario principalOperario = new PrincipalOperario();
//                principalOperario.setVisible(true);
//                principalOperario.setLocationRelativeTo(null);
//
//                usuarioConRol = true;
//            } else if (rol.equals("ADMIN")) {
//
//                usuarioConRol = true;
//
//                lanzarHiloAvisoEtapaNoTerminada = true;
//                lanzarHiloEscuchadorFinEtapa = true;
//                lanzarHiloAvisoEtapaListaParaIniciar = true;
//                lanzarHiloAvisoProcesoCalidadNoTerminado = true;
//                lanzarHiloEscuchadorFinProcesoCalidad = true;
//                lanzarHiloAvisoProcesoCalidadListoParaLanzar = true;
//            }
//        }
//
//        if (usuarioConRol) {
//            Principal p = new Principal(usuario);
//            p.setVisible(true);
//            p.setLocationRelativeTo(null);
//            lanzarHilos(lanzarHiloAvisoEtapaNoTerminada, lanzarHiloEscuchadorFinEtapa, lanzarHiloAvisoEtapaListaParaIniciar, lanzarHiloAvisoProcesoCalidadNoTerminado, lanzarHiloEscuchadorFinProcesoCalidad, lanzarHiloAvisoProcesoCalidadListoParaLanzar, p);
//        } else {
//            JOptionPane.showMessageDialog(null, "El usuario " + usuarioJpa.getUsuario() + " no tiene ningún rol asociado.\nNo se puede iniciar la aplicación");
//        }



    }

    private static void lanzarHilos(boolean lanzarHiloAvisoEtapaNoTerminada,
            boolean lanzarHiloEscuchadorFinEtapa,
            boolean lanzarHiloAvisoEtapaListaParaIniciar,
            boolean lanzarHiloAvisoProcesoCalidadNoTerminado,
            boolean lanzarHiloEscuchadorFinProcesoCalidad,
            boolean lanzarHiloAvisoProcesoCalidadListoParaLanzar, Principal vtnPrincipal) {

        if (lanzarHiloAvisoEtapaListaParaIniciar) {
            lanzarHiloAvisoEtapaListaParaIniciar(vtnPrincipal);
        }
        if (lanzarHiloAvisoEtapaNoTerminada) {
            lanzarHiloAvisoEtapaNoTerminada(vtnPrincipal);
        }
        if (lanzarHiloAvisoProcesoCalidadListoParaLanzar) {
            lanzarHiloAvisoProcesoCalidadListoParaLanzar(vtnPrincipal);
        }
        if (lanzarHiloAvisoProcesoCalidadNoTerminado) {
            lanzarHiloAvisoProcesoCalidadNoTerminado(vtnPrincipal);
        }
        if (lanzarHiloEscuchadorFinEtapa) {
            lanzarHiloEscuchadorFinEtapa(vtnPrincipal);
        }
        if (lanzarHiloEscuchadorFinProcesoCalidad) {
            lanzarHiloEscuchadorFinProcesoCalidad(vtnPrincipal);
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

    private static void lanzarHiloAvisoEtapaListaParaIniciar(Principal vtnPrincipal) {
        HiloAvisoEtapaListaParaIniciar hilo = new HiloAvisoEtapaListaParaIniciar();
        hilo.setVtnPrincipal(vtnPrincipal);
        Thread thread = new Thread(hilo);
        thread.start();
    }

    private static void lanzarHiloAvisoProcesoCalidadListoParaLanzar(Principal vtnPrincipal) {
        HiloAvisoProcesoCalidadListoParaIniciar hilo = new HiloAvisoProcesoCalidadListoParaIniciar();
        hilo.setVtnPrincipal(vtnPrincipal);
        Thread thread = new Thread(hilo);
        thread.start();
    }
}
