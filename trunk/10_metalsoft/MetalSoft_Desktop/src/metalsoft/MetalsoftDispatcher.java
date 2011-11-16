/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft;

import java.util.List;
import javax.swing.JOptionPane;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dao.UsuarioDAOImpl;
import metalsoft.datos.dbobject.UsuarioDB;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.UsuarioJpaController;
import metalsoft.datos.jpa.entity.Rol;
import metalsoft.datos.jpa.entity.Usuario;
import metalsoft.datos.jpa.entity.Usuarioxrol;
import metalsoft.negocio.gestores.IdsRol;
import metalsoft.presentacion.Principal;

/**
 *
 * @author Nino
 */
public class MetalsoftDispatcher {

    public static void main(String arg[]) throws Exception {

        UsuarioDB usuario = new UsuarioDAOImpl().findByPrimaryKey(2L, new PostgreSQLManager().concectGetCn());
        dispatchUser(usuario);
    }

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


        boolean lanzarHiloAvisoEtapaNoTerminada = false;
        boolean lanzarHiloEscuchadorFinEtapa = false;
        boolean lanzarHiloAvisoEtapaListaParaIniciar = false;
        boolean lanzarHiloAvisoProcesoCalidadNoTerminado = false;
        boolean lanzarHiloEscuchadorFinProcesoCalidad = false;
        boolean lanzarHiloAvisoProcesoCalidadListoParaLanzar = false;

        boolean usuarioConRol = false;

        Principal p = new Principal(usuario);

        for (Usuarioxrol usuarioxrol : lstUsuarioXRol) {
            Rol rol = usuarioxrol.getRol();
            if (rol.getIdrol() == IdsRol.ADMIN) {

                usuarioConRol = true;

                lanzarHiloAvisoEtapaNoTerminada = true;
                lanzarHiloEscuchadorFinEtapa = true;
                lanzarHiloAvisoEtapaListaParaIniciar = true;
                lanzarHiloAvisoProcesoCalidadNoTerminado = true;
                lanzarHiloEscuchadorFinProcesoCalidad = true;
                lanzarHiloAvisoProcesoCalidadListoParaLanzar = true;

                habilitarComponentesSegunRol(rol.getIdrol(), p);
            }
            if (rol.getIdrol() == IdsRol.OPERARIO_PRODUCCION) {
                usuarioConRol = true;

                habilitarComponentesSegunRol(rol.getIdrol(), p);
            }
            if (rol.getIdrol() == IdsRol.OPERARIO_CALIDAD) {
                usuarioConRol = true;

                habilitarComponentesSegunRol(rol.getIdrol(), p);
            }
            if (rol.getIdrol() == IdsRol.RESP_PRODUCCION) {
                usuarioConRol = true;

                lanzarHiloAvisoEtapaListaParaIniciar = true;
                lanzarHiloAvisoEtapaNoTerminada = true;
                lanzarHiloEscuchadorFinEtapa = true;

                habilitarComponentesSegunRol(rol.getIdrol(), p);
            }
            if (rol.getIdrol() == IdsRol.RESP_CALIDAD) {
                usuarioConRol = true;

                lanzarHiloAvisoProcesoCalidadListoParaLanzar = true;
                lanzarHiloAvisoProcesoCalidadNoTerminado = true;
                lanzarHiloEscuchadorFinProcesoCalidad = true;

                habilitarComponentesSegunRol(rol.getIdrol(), p);
            }
            if (rol.getIdrol() == IdsRol.RESP_ALMACENAMIENTO) {
                usuarioConRol = true;

                habilitarComponentesSegunRol(rol.getIdrol(), p);
            }
            if (rol.getIdrol() == IdsRol.RESP_COMPRAS) {
                usuarioConRol = true;

                habilitarComponentesSegunRol(rol.getIdrol(), p);
            }
            if (rol.getIdrol() == IdsRol.RESP_VENTAS) {
                usuarioConRol = true;

                habilitarComponentesSegunRol(rol.getIdrol(), p);
            }
            if (rol.getIdrol() == IdsRol.RESP_MANTENIMIENTO) {
                usuarioConRol = true;

                habilitarComponentesSegunRol(rol.getIdrol(), p);
            }
            if (rol.getIdrol() == IdsRol.RESP_RRHH) {
                usuarioConRol = true;

                habilitarComponentesSegunRol(rol.getIdrol(), p);
            }

        }
        if (usuarioConRol) {
            p.setVisible(true);
            p.setLocationRelativeTo(null);
            lanzarHilos(lanzarHiloAvisoEtapaNoTerminada, lanzarHiloEscuchadorFinEtapa, lanzarHiloAvisoEtapaListaParaIniciar, lanzarHiloAvisoProcesoCalidadNoTerminado, lanzarHiloEscuchadorFinProcesoCalidad, lanzarHiloAvisoProcesoCalidadListoParaLanzar, p);
        } else {
            JOptionPane.showMessageDialog(null, "El usuario " + usuarioJpa.getUsuario() + " no tiene ningún rol asociado.\nNo se puede iniciar la aplicación");
        }


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
        HiloEscuchadorFinEtapa hilo = HiloEscuchadorFinEtapa.getInstance();
        hilo.setVtnPrincipal(vtnPrincipal);
        hilo.start();

    }

    private static void lanzarHiloAvisoEtapaNoTerminada(Principal vtnPrincipal) {
        HiloAvisoEtapaNoTerminada hilo = HiloAvisoEtapaNoTerminada.getInstance();
        hilo.setVtnPrincipal(vtnPrincipal);
        hilo.start();

    }

    private static void lanzarHiloAvisoProcesoCalidadNoTerminado(Principal vtnPrincipal) {
        HiloAvisoProcesoCalidadNoTerminado hilo = HiloAvisoProcesoCalidadNoTerminado.getInstance();
        hilo.setVtnPrincipal(vtnPrincipal);
        hilo.start();
    }

    private static void lanzarHiloEscuchadorFinProcesoCalidad(Principal vtnPrincipal) {
        HiloEscuchadorFinProcesoCalidad hilo = HiloEscuchadorFinProcesoCalidad.getInstance();
        hilo.setVtnPrincipal(vtnPrincipal);
        hilo.start();
    }

    private static void lanzarHiloAvisoEtapaListaParaIniciar(Principal vtnPrincipal) {
        HiloAvisoEtapaListaParaIniciar hilo = HiloAvisoEtapaListaParaIniciar.getInstance();
        hilo.setVtnPrincipal(vtnPrincipal);
        hilo.start();
    }

    private static void lanzarHiloAvisoProcesoCalidadListoParaLanzar(Principal vtnPrincipal) {
        HiloAvisoProcesoCalidadListoParaIniciar hilo = HiloAvisoProcesoCalidadListoParaIniciar.getInstance();
        hilo.setVtnPrincipal(vtnPrincipal);
        hilo.start();
    }

    public static void detenerTodosLosHilos() {
        HiloAvisoProcesoCalidadListoParaIniciar.getInstance().stop();
        HiloAvisoEtapaListaParaIniciar.getInstance().stop();
        HiloEscuchadorFinProcesoCalidad.getInstance().stop();
        HiloAvisoProcesoCalidadNoTerminado.getInstance().stop();
        HiloAvisoEtapaNoTerminada.getInstance().stop();
        HiloEscuchadorFinEtapa.getInstance().stop();

    }

    private static void habilitarComponentesSegunRol(Long idrol, Principal vtnPrincipal) {

        switch (idrol.intValue()) {
            case (int) IdsRol.ADMIN:
                vtnPrincipal.setVisibleComponents(true);
                vtnPrincipal.getPnlRegistrarFinalizacion().setVisible(false);
                break;
            case (int) IdsRol.CLIENTE:
                break;
            case (int) IdsRol.OPERARIO_CALIDAD:
                vtnPrincipal.getPnlRegistrarFinalizacion().setVisible(true);
                break;
            case (int) IdsRol.OPERARIO_PRODUCCION:
                vtnPrincipal.getPnlRegistrarFinalizacion().setVisible(true);
                break;
            case (int) IdsRol.RESP_ALMACENAMIENTO:
                vtnPrincipal.getMnuAlmacenamiento().setVisible(true);
                break;
            case (int) IdsRol.RESP_CALIDAD:
                vtnPrincipal.getMnuCalidad().setVisible(true);
                vtnPrincipal.getPnlCalidad().setVisible(true);
                break;
            case (int) IdsRol.RESP_COMPRAS:
                vtnPrincipal.getMnuCompras().setVisible(true);
                vtnPrincipal.getMnuTrabajosTercerizados().setVisible(true);
                break;
            case (int) IdsRol.RESP_PRODUCCION:
                vtnPrincipal.getMnuProduccion().setVisible(true);
                vtnPrincipal.setVisibleItemsMenuProduccion(true);
                vtnPrincipal.getPnlProduccion().setVisible(true);
                break;
            case (int) IdsRol.RESP_VENTAS:
                vtnPrincipal.getMnuVentas().setVisible(true);
                vtnPrincipal.getMnuProduccion().setVisible(true);
                vtnPrincipal.getMniEtapaDeProduccion().setVisible(true);
                vtnPrincipal.getMniProducto().setVisible(true);
                vtnPrincipal.getMniPieza().setVisible(true);
                vtnPrincipal.getPnlVentas().setVisible(true);
                break;
            case (int) IdsRol.RESP_MANTENIMIENTO:
                vtnPrincipal.getMnuMantenimiento().setVisible(true);
                vtnPrincipal.getMnuProduccion().setVisible(true);
                vtnPrincipal.getMniMaquinaria().setVisible(true);
                break;
            case (int) IdsRol.RESP_RRHH:
                vtnPrincipal.getMnuRRHH().setVisible(true);
                vtnPrincipal.setVisibleItemsMenuInicio(true);
                break;
            default:
                break;
        }
    }
}
