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
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import metalsoft.datos.jpa.entity.Ejecucionplanificacionproduccion;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionEtapaProduccion;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionPlanificacionPedido;
import metalsoft.presentacion.Principal;
import metalsoft.util.Fecha;

/**
 *
 * @author Nino
 */
public class HiloAvisoEtapaNoTerminada extends HiloSyncBase implements Runnable {

    private Principal vtnPrincipal;
    private Thread thread;
    private boolean stop = false;
    private TimerTask timerTask;
    private Timer timer;
    
    private static HiloAvisoEtapaNoTerminada instance;

    public static HiloAvisoEtapaNoTerminada getInstance() {
        if (instance == null) {
            instance = new HiloAvisoEtapaNoTerminada();
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
                procesarDatos();
            }
        };
        timer.schedule(timerTask, 0, 30000);
    }

    private void notificarAlerta(Ejecucionplanificacionproduccion ejecucionplanificacionproduccion, Detalleejecucionplanificacion detalleejecucionplanificacion) {
        vtnPrincipal.alertaEtapaNoFinalizada(ejecucionplanificacionproduccion, detalleejecucionplanificacion);
    }

    @Override
    public void templatedMethod() {

        System.out.println(Fecha.fechaActualDate() + ": ############# HiloAvisoEtapaNoTerminada --> Running #############");
        try {

            List<Ejecucionplanificacionproduccion> lstEjecucionplanificacionproduccion = null;
            lstEjecucionplanificacionproduccion = JpaUtil.getEjecucionplanificacionproduccionSegunEstado(IdsEstadoEjecucionPlanificacionPedido.ENEJECUCION);

            for (Ejecucionplanificacionproduccion ejecucionplanificacionproduccion : lstEjecucionplanificacionproduccion) {

                List<Detalleejecucionplanificacion> lstDetalleejecucionplanificacion = ejecucionplanificacionproduccion.getDetalleejecucionplanificacionList();

                for (Detalleejecucionplanificacion detalleejecucionplanificacion : lstDetalleejecucionplanificacion) {

                    Long idEstadoEjecEtapa = detalleejecucionplanificacion.getEjecucionetapa().getEstado().getIdestado();

                    if (idEstadoEjecEtapa == IdsEstadoEjecucionEtapaProduccion.ENEJECUCION) {

                        Detalleplanificacionproduccion detalleplanificacionproduccion = JpaUtil.getDetalleplanificacionproduccionPorIdDetalleejecucion(detalleejecucionplanificacion.getId());

                        Date fechaFinEsperada = detalleplanificacionproduccion.getFechafin();
                        Date horaFinEsperada = detalleplanificacionproduccion.getHorafin();
                        System.out.println(fechaFinEsperada);
                        System.out.println(horaFinEsperada);
                        Date fechaActual = Fecha.fechaActualDate();

                        int difDias = Fecha.diferenciaEnDiasJoda(fechaFinEsperada, fechaActual);

                        if (difDias < 0) {
                            notificarAlerta(ejecucionplanificacionproduccion, detalleejecucionplanificacion);
                            System.out.println("Etapa ID: " + detalleejecucionplanificacion.getEjecucionetapa().getId() + " no a finalizado en el tiempo esperado");
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
                                notificarAlerta(ejecucionplanificacionproduccion, detalleejecucionplanificacion);
                                System.out.println("Etapa ID: " + detalleejecucionplanificacion.getEjecucionetapa().getId() + " no a finalizado en el tiempo esperado");
                                System.out.println("horas");
                            } else if (horaFin == horaActual) {
                                /*
                                 * Comparo minutos de la hora
                                 */
                                int minutoFin = calHoraFinEsperada.get(Calendar.MINUTE);
                                int minutoActual = calHoraActual.get(Calendar.MINUTE);

                                if (minutoFin < minutoActual) {
                                    notificarAlerta(ejecucionplanificacionproduccion, detalleejecucionplanificacion);
                                    System.out.println("Etapa ID: " + detalleejecucionplanificacion.getEjecucionetapa().getId() + " no a finalizado en el tiempo esperado");
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

    @Override
    public void start() {
        if (thread == null) {
            thread = new Thread(instance);
            thread.start();
        }
        
        stop = false;
    }
    
    @Override
    public void stop(){
        if (thread != null){
            stop = true;
            thread = null;
            timerTask.cancel();
            timer.cancel();
        }
    }
}
