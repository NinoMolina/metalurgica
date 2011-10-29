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
import metalsoft.datos.jpa.controller.DetalleplanificacioncalidadJpaController;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;
import metalsoft.datos.jpa.entity.Ejecucionprocesocalidad;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionEtapaProduccion;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionProcesoCalidad;
import metalsoft.presentacion.Principal;
import metalsoft.util.Fecha;

/**
 *
 * @author Nino
 */
public class HiloAvisoProcesoCalidadListoParaIniciar extends HiloSyncBase implements Runnable {

    private Principal vtnPrincipal;
    private Thread thread;
    private boolean stop = false;
    private TimerTask timerTask;
    private Timer timer;
    private static HiloAvisoProcesoCalidadListoParaIniciar instance;

    public static HiloAvisoProcesoCalidadListoParaIniciar getInstance() {
        if (instance == null) {
            instance = new HiloAvisoProcesoCalidadListoParaIniciar();
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

    private void comprobarEstadoProcesoAnteriorYLanzar(Detalleplanificacioncalidad detalleplanificacioncalidad, Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad) throws Exception {
        Long idDetAnt = detalleplanificacioncalidad.getDetalleanterior();

        if (idDetAnt == null) {
            /*
             * etapa lista para lanzar, esta en tiempo y es la primera de la pieza
             */
            vtnPrincipal.alertaProcesoListoParaLanzar(detalleplanificacioncalidad);
        } else {
            /*
             * no es la primera de la pieza, ver si la anterior esta finalizada
             */

            DetalleplanificacioncalidadJpaController detalleplanificacioncalidadJpaController = new DetalleplanificacioncalidadJpaController(JpaUtil.getEntityManagerFactory());
            Detalleplanificacioncalidad detalleplanificacioncalidadAnterior = detalleplanificacioncalidadJpaController.findDetalleplanificacioncalidad(idDetAnt);
            Long idEstadoEjecucionAnterior = detalleplanificacioncalidadAnterior.getIddetalleejecucionplanificacioncalidad().getEjecucionprocesocalidad().getEstado().getIdestado();

            if (idEstadoEjecucionAnterior != null && idEstadoEjecucionAnterior == IdsEstadoEjecucionEtapaProduccion.FINALIZADA) {
                /*
                 * la etapa anterior esta finalizada, con lo cual se puede lanzar la etapa actual
                 */
                vtnPrincipal.alertaProcesoListoParaLanzar(detalleplanificacioncalidad);
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

        try {
            List<Ejecucionprocesocalidad> lstEjecucionProcesoCalidad = JpaUtil.getEjecucionprocesocalidadByEstado(IdsEstadoEjecucionProcesoCalidad.GENERADA);

            for (Ejecucionprocesocalidad ejecucionprocesocalidad : lstEjecucionProcesoCalidad) {

                System.out.println("HiloAvisoProcesoCalidadListoParaIniciar: Analizando ejecucion proceso calidad " + ejecucionprocesocalidad.getIdejecucion());
                Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad = JpaUtil.getDetalleejecucionplanificacioncalidadByEjecucionProcesoCalidad(ejecucionprocesocalidad.getIdejecucion());
                Detalleplanificacioncalidad detalleplanificacioncalidad = JpaUtil.getDetalleplanificacioncalidadPorIdDetalleejecucion(detalleejecucionplanificacioncalidad.getIddetalle());
                Date fechaInicio = detalleplanificacioncalidad.getFechainicio();
                fechaInicio.setHours(detalleplanificacioncalidad.getHorainicio().getHours());
                fechaInicio.setMinutes(detalleplanificacioncalidad.getHorainicio().getMinutes());
                fechaInicio.setSeconds(detalleplanificacioncalidad.getHorainicio().getSeconds());

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

                        comprobarEstadoProcesoAnteriorYLanzar(detalleplanificacioncalidad, detalleejecucionplanificacioncalidad);

                    }
                } else if (Fecha.diferenciaEnDias(fechaActual, fechaInicio) < 0) {
                    /*
                     * es una etapa que tiene fecha de inicio anterior a la fecha actual.
                     * hay que comprobar si se puede lanzar.
                     */
                    comprobarEstadoProcesoAnteriorYLanzar(detalleplanificacioncalidad, detalleejecucionplanificacioncalidad);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
