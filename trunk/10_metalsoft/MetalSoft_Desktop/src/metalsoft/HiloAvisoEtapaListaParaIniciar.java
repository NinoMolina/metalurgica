/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.DetalleplanificacionproduccionJpaController;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import metalsoft.datos.jpa.entity.Ejecucionetapaproduccion;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionEtapaProduccion;
import metalsoft.presentacion.Principal;
import metalsoft.util.Fecha;

/**
 *
 * @author Nino
 */
public class HiloAvisoEtapaListaParaIniciar extends HiloSyncBase implements Runnable {

    private Principal vtnPrincipal;
    private Thread thread;
    private boolean stop = false;
    private TimerTask timerTask;
    private Timer timer;
    private static HiloAvisoEtapaListaParaIniciar instance;

    public static HiloAvisoEtapaListaParaIniciar getInstance() {
        if (instance == null) {
            instance = new HiloAvisoEtapaListaParaIniciar();
        }
        return instance;
    }

    @Override
    public void run() {
        timer = new Timer();
        timerTask = new TimerTask() {

            @Override
            public void run() {
                procesarDatos();
            }
        };
        timer.schedule(timerTask, 0, 30000);

    }

    @Override
    public void setVtnPrincipal(Principal vtnPrincipal) {
        this.vtnPrincipal = vtnPrincipal;
    }

    private void comprobarEstadoEtapaAnteriorYLanzar(Detalleplanificacionproduccion detalleplanificacionproduccion, Detalleejecucionplanificacion detalleejecucionplanificacion) throws Exception {
        Long idDetAnt = detalleplanificacionproduccion.getDetalleanterior();

        if (idDetAnt == null) {
            /*
             * etapa lista para lanzar, esta en tiempo y es la primera de la pieza
             */
            System.out.println("HiloAvisoEtapaListaParaIniciar: etapa " + detalleejecucionplanificacion.getEjecucionetapa().getId() + " lista para lanzar, esta en tiempo y es la primera de la pieza...");
            vtnPrincipal.alertaEtapaListaParaLanzar(detalleplanificacionproduccion);
//            gestor.lanzarEjecucionEtapa(detalleejecucionplanificacion);
        } else {
            /*
             * no es la primera de la pieza, ver si la anterior esta finalizada
             */

            DetalleplanificacionproduccionJpaController detalleplanificacionproduccionJpaController = new DetalleplanificacionproduccionJpaController(JpaUtil.getEntityManagerFactory());
            Detalleplanificacionproduccion detalleplanificacionproduccionAnterior = detalleplanificacionproduccionJpaController.findDetalleplanificacionproduccion(idDetAnt);
            Long idEstadoEjecucionAnterior = detalleplanificacionproduccionAnterior.getIddetalleejecucionplanificacion().getEjecucionetapa().getEstado().getIdestado();

            if (idEstadoEjecucionAnterior != null && idEstadoEjecucionAnterior == IdsEstadoEjecucionEtapaProduccion.FINALIZADA) {
                /*
                 * la etapa anterior esta finalizada, con lo cual se puede lanzar la etapa actual
                 */
                System.out.println("HiloAvisoEtapaListaParaIniciar: la etapa anterior esta finalizada, con lo cual se puede lanzar la etapa " + detalleejecucionplanificacion.getEjecucionetapa().getId());
                vtnPrincipal.alertaEtapaListaParaLanzar(detalleplanificacionproduccion);
//                gestor.lanzarEjecucionEtapa(detalleejecucionplanificacion);
            }
        }
    }

    @Override
    public void start() {
        if (thread == null) {
            thread = new Thread(instance);
            thread.start();
        }

        stop = false;
    }

    @Override
    public void stop() {
        if (thread != null) {
            stop = true;
            thread = null;
            timerTask.cancel();
            timer.cancel();
        }
    }

    @Override
    public void templatedMethod() {
        /*
         * -Buscar las etapas en estado GENERADA
         * -Ver si está en fecha y hora de iniciar 
         * (si faltan 3 min para que inicie avisar que esta por iniciar,
         * dejar un tiempo de gracia)
         * -Si la etapa esta en fecha y hora ver si la etapa anterior esta 
         * en estado finalizado. Si no esta finalizada no se puede iniciar la proxima etapa
         */
        System.out.println("HiloAvisoEtapaListaParaIniciar: Procesando datos...");
        try {
            List<Ejecucionetapaproduccion> lstEjecucionEtapaProduccion = JpaUtil.getEjecucionetapaproduccionByEstado(IdsEstadoEjecucionEtapaProduccion.GENERADA);

            for (Ejecucionetapaproduccion ejecucionetapaproduccion : lstEjecucionEtapaProduccion) {

                System.out.println("HiloAvisoEtapaListaParaIniciar: Analizando ejecucion etapa " + ejecucionetapaproduccion.getId());
                Detalleejecucionplanificacion detalleejecucionplanificacion = JpaUtil.getDetalleejecucionplanificacionByEjecucionetapa(ejecucionetapaproduccion.getId());
                Detalleplanificacionproduccion detalleplanificacionproduccion = JpaUtil.getDetalleplanificacionproduccionPorIdDetalleejecucion(detalleejecucionplanificacion.getId());
                Date fechaInicio = detalleplanificacionproduccion.getFechainicio();
                fechaInicio.setHours(detalleplanificacionproduccion.getHorainicio().getHours());
                fechaInicio.setMinutes(detalleplanificacionproduccion.getHorainicio().getMinutes());
                fechaInicio.setSeconds(detalleplanificacionproduccion.getHorainicio().getSeconds());

                Date fechaActual = Fecha.fechaActualDate();

                Date fechaMayor = null;
                Date fechaMenor = null;

                if (Fecha.diferenciaEnDias(fechaActual, fechaInicio) == 0) {
                    if (fechaActual.compareTo(fechaInicio) < 0) {
                        fechaMayor = fechaInicio;
                        fechaMenor = fechaActual;
                    } else if (fechaActual.compareTo(fechaInicio) > 0) {
                        fechaMayor = fechaActual;
                        fechaMenor = fechaInicio;
                    }

                    Date difMinHoras = Fecha.diferenciaEnMinutosHoras(fechaMenor, fechaMayor);
                    int horasDif = difMinHoras.getHours();
                    int minDif = difMinHoras.getMinutes();

                    if (horasDif == 0 && minDif < 3) {
                        /*
                         * es una etapa en tiempo para lanzar, ver si tiene etapa anterior y si esta finalizada
                         */

                        comprobarEstadoEtapaAnteriorYLanzar(detalleplanificacionproduccion, detalleejecucionplanificacion);

                    }
                } else if (Fecha.diferenciaEnDias(fechaActual, fechaInicio) < 0) {
                    /*
                     * es una etapa que tiene fecha de inicio anterior a la fecha actual.
                     * hay que comprobar si se puede lanzar.
                     */
                    comprobarEstadoEtapaAnteriorYLanzar(detalleplanificacionproduccion, detalleejecucionplanificacion);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
