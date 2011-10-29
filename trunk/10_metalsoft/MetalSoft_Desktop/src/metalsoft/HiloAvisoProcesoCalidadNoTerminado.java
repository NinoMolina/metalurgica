/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;
import metalsoft.datos.jpa.entity.Ejecucionplanificacioncalidad;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionPlanificacionCalidad;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionProcesoCalidad;
import metalsoft.presentacion.Principal;
import metalsoft.util.Fecha;

/**
 *
 * @author Nino
 */
public class HiloAvisoProcesoCalidadNoTerminado extends HiloSyncBase implements Runnable {

    private Principal vtnPrincipal;
    private Thread thread;
    private boolean stop = false;
    private TimerTask timerTask;
    private Timer timer;
    private static HiloAvisoProcesoCalidadNoTerminado instance;

    public static HiloAvisoProcesoCalidadNoTerminado getInstance() {
        if (instance == null) {
            instance = new HiloAvisoProcesoCalidadNoTerminado();
        }
        return instance;
    }

    public Principal getVtnPrincipal() {
        return vtnPrincipal;
    }

    @Override
    public void setVtnPrincipal(Principal vtnPrincipal) {
        this.vtnPrincipal = vtnPrincipal;
    }

    @Override
    public void run() {
        timer = new Timer();
        timerTask = new TimerTask() {

            @Override
            public void run() {
//                if (stop) {
//                    this.cancel();
//                    return;
//                }
                procesarDatos();
            }
        };
        timer.schedule(timerTask, 0, 30000);
    }

    @Override
    public void templatedMethod() {

        try {

            List<Ejecucionplanificacioncalidad> lstEjecucionplanificacioncalidad = null;
            lstEjecucionplanificacioncalidad = JpaUtil.getEjecucionplanificacioncalidadSegunEstado(IdsEstadoEjecucionPlanificacionCalidad.ENEJECUCION);

            for (Ejecucionplanificacioncalidad ejecucionplanificacioncalidad : lstEjecucionplanificacioncalidad) {

                List<Detalleejecucionplanificacioncalidad> lstDetalleejecucionplanificacioncalidad = ejecucionplanificacioncalidad.getDetalleejecucionplanificacioncalidadList();

                for (Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad : lstDetalleejecucionplanificacioncalidad) {

                    Long idEstadoProcesoCalidad = detalleejecucionplanificacioncalidad.getEjecucionprocesocalidad().getEstado().getIdestado();

                    if (idEstadoProcesoCalidad == IdsEstadoEjecucionProcesoCalidad.ENEJECUCION) {

                        Detalleplanificacioncalidad detalleplanificacioncalidad = JpaUtil.getDetalleplanificacioncalidadPorIdDetalleejecucion(detalleejecucionplanificacioncalidad.getIddetalle());

                        Date fechaFinEsperada = detalleplanificacioncalidad.getFechafin();
                        Date horaFinEsperada = detalleplanificacioncalidad.getHorafin();

                        Date fechaActual = Fecha.fechaActualDate();

                        int difDias = Fecha.diferenciaEnDiasJoda(fechaFinEsperada, fechaActual);

                        if (difDias < 0) {
                            notificarAlerta(ejecucionplanificacioncalidad, detalleejecucionplanificacioncalidad);
                            System.out.println("ProcesoCalidad " + detalleejecucionplanificacioncalidad.getEjecucionprocesocalidad().getIdejecucion() + " no a finalizado en el tiempo esperado");
                            System.out.println("dias");
                        } else if (difDias == 0) {
                            /*
                             * Comparo horas del dia
                             */
                            GregorianCalendar calHoraFinEsperada = new GregorianCalendar();
                            calHoraFinEsperada.setTime(horaFinEsperada);

                            GregorianCalendar calHoraActual = new GregorianCalendar();
                            calHoraActual.setTime(fechaActual);

                            int horaFin = calHoraFinEsperada.get(Calendar.HOUR_OF_DAY);
                            int horaActual = calHoraActual.get(Calendar.HOUR_OF_DAY);

                            if (horaFin < horaActual) {
                                notificarAlerta(ejecucionplanificacioncalidad, detalleejecucionplanificacioncalidad);
                                System.out.println("ProcesoCalidad " + detalleejecucionplanificacioncalidad.getEjecucionprocesocalidad().getIdejecucion() + " no a finalizado en el tiempo esperado");
                                System.out.println("horas");
                            } else if (horaFin == horaActual) {
                                /*
                                 * Comparo minutos de la hora
                                 */
                                int minutoFin = calHoraFinEsperada.get(Calendar.MINUTE);
                                int minutoActual = calHoraActual.get(Calendar.MINUTE);

                                if (minutoFin < minutoActual) {
                                    notificarAlerta(ejecucionplanificacioncalidad, detalleejecucionplanificacioncalidad);
                                    System.out.println("ProcesoCalidad " + detalleejecucionplanificacioncalidad.getEjecucionprocesocalidad().getIdejecucion() + " no a finalizado en el tiempo esperado");
                                    System.out.println("minutos");
                                }
                            }

                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void notificarAlerta(Ejecucionplanificacioncalidad ejecucionplanificacioncalidad, Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad) {
        vtnPrincipal.alertaProcesoCalidadNoFinalizado(ejecucionplanificacioncalidad, detalleejecucionplanificacioncalidad);
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
}
