/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Time;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.DetalleejecucionplanificacionJpaController;
import metalsoft.datos.jpa.controller.EjecucionetapaproduccionJpaController;
import metalsoft.datos.jpa.controller.EstadoejecetapaprodJpaController;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;
import metalsoft.datos.jpa.entity.Ejecucionetapaproduccion;
import metalsoft.datos.jpa.entity.Estadoejecetapaprod;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionEtapaProduccion;
import metalsoft.presentacion.Principal;
import metalsoft.util.Fecha;
import metalsoft.util.MetalsoftProperties;

/**
 *
 * @author Nino
 */
public class HiloEscuchadorFinEtapa extends Thread {

    private Principal vtnPrincipal;
    private ServerSocket serverSocket = null;
    private Socket clienteSocket = null;
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;

    @Override
    public void run() {


        while (true) {
            try {
                if (serverSocket == null) {
                    String puertoString = MetalsoftProperties.getProperty(MetalsoftProperties.PUERTO_FIN_ETAPA);
                    serverSocket = new ServerSocket(Integer.parseInt(puertoString));
                }
                clienteSocket = serverSocket.accept();

                procesarDatos();

            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                try {
                    if (oos != null) {
                        oos.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                try {
                    if (clienteSocket != null) {
                        clienteSocket.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }

    private synchronized void procesarDatos() throws IOException, ClassNotFoundException {

        oos = new ObjectOutputStream(clienteSocket.getOutputStream());
        ois = new ObjectInputStream(clienteSocket.getInputStream());
        String datosRecibidos = (String) ois.readObject();
        /*
         * si coincide con la expresion regular de los codigos de barra proceso los datos
         */
        String expresionRegularCodigoBarra = MetalsoftProperties.getProperty(MetalsoftProperties.EXPRESION_REGULAR_CODIGO_BARRA);

        Pattern pattern = null;
        Matcher matcher = null;

        pattern = Pattern.compile(expresionRegularCodigoBarra);
        matcher = pattern.matcher(datosRecibidos);

        if (matcher.find()) {

            System.out.println("INFO: Se recibio un patron de datos correspondiente a un codigo de barras de fin de etapa...");

            try {
                /*
                 * Registrar el fin de la etapa (el id se recibe del cliente) y
                 * dejar
                 */
                String partes[] = datosRecibidos.split(Pattern.quote("-"));
                String idEjecEtapa = partes[1];
                EjecucionetapaproduccionJpaController ejecEtapaController = new EjecucionetapaproduccionJpaController();
                Ejecucionetapaproduccion ejecucionetapaproduccion = ejecEtapaController.findEjecucionetapaproduccion(Long.parseLong(idEjecEtapa));

                EstadoejecetapaprodJpaController estadoEjecController = new EstadoejecetapaprodJpaController();
                Estadoejecetapaprod estadoejecetapaprod = estadoEjecController.findEstadoejecetapaprod(IdsEstadoEjecucionEtapaProduccion.FINALIZADA);

                ejecucionetapaproduccion.setEstado(estadoejecetapaprod);

                Date fechaActual = Fecha.fechaActualDate();

                ejecucionetapaproduccion.setFechafin(fechaActual);
                ejecucionetapaproduccion.setHorafin(fechaActual);

                Detalleejecucionplanificacion detalleejecucionplanificacion = JpaUtil.getDetalleejecucionplanificacionByEjecucionetapa(ejecucionetapaproduccion.getId());
                detalleejecucionplanificacion.setFechafin(fechaActual);
                detalleejecucionplanificacion.setHorafin(fechaActual);
                DetalleejecucionplanificacionJpaController detalleejecucionplanificacionJpaController = new DetalleejecucionplanificacionJpaController();
                detalleejecucionplanificacionJpaController.edit(detalleejecucionplanificacion);

                Date fechaInicio = null;
                fechaInicio = Fecha.setHoraMinutoSegundo(ejecucionetapaproduccion.getFechainicio(), ejecucionetapaproduccion.getHorainicio());

                Date difHoras = Fecha.diferenciaEnHoras(fechaInicio, fechaActual);

                int difDias = Fecha.diferenciaEnDias(fechaInicio, fechaActual);

                int horas = difHoras.getHours();
                int minutos = difHoras.getMinutes();
                int segundos = difHoras.getSeconds();

                String totalHrsHombre = String.valueOf((difDias * 24) + horas) + ":" + String.valueOf(minutos) + ":" + String.valueOf(segundos);

                ejecucionetapaproduccion.setTotalhorashombre(totalHrsHombre);

                ejecEtapaController.edit(ejecucionetapaproduccion);

                vtnPrincipal.eliminarEtapaNoFinalizada(detalleejecucionplanificacion.getId());
                /*
                 * si los datos se procesaron correctamente aviso que todo esta ok
                 */
                System.out.println("INFO: Se procesaron los datos recibidos.");
                oos.writeObject("OK");
            } catch (Exception e) {
                /*
                 * sino aviso que hubo errores al procesar los datos
                 */
                oos.writeObject("ERROR: " + e.getMessage());
                e.printStackTrace();
            }


        } else {
            /*
             * si los datos recibidos no coinciden con la expresion regular
             * entonces no se hace nada
             */
            System.out.println("WARNING: Los datos recibidos no coinciden con el patron de fin de etapa esperado.");
            oos.writeObject("WARNING: Los datos enviados no coinciden con el patron de fin de etapa esperado por el servidor.");
        }

    }

    void setVtnPrincipal(Principal vtnPrincipal) {
        this.vtnPrincipal = vtnPrincipal;
    }
}
