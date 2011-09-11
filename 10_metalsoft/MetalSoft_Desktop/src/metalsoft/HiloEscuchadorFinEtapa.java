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
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.DetalleejecucionplanificacionJpaController;
import metalsoft.datos.jpa.controller.EjecucionetapaproduccionJpaController;
import metalsoft.datos.jpa.controller.EstadoejecetapaprodJpaController;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import metalsoft.datos.jpa.entity.Ejecucionetapaproduccion;
import metalsoft.datos.jpa.entity.Estadoejecetapaprod;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.gestores.GestorLanzarProximaEtapa;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionEtapaProduccion;
import metalsoft.presentacion.Principal;
import metalsoft.util.Fecha;
import metalsoft.util.MetalsoftProperties;

/**
 *
 * @author Nino
 */
public class HiloEscuchadorFinEtapa extends HiloEtapaBase implements Runnable {

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

                procesarSocket();

            } catch (IOException ex) {
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

    private synchronized void procesarSocket() {

        procesarDatos();

    }

    void setVtnPrincipal(Principal vtnPrincipal) {
        this.vtnPrincipal = vtnPrincipal;
    }

    @Override
    public void templatedMethod() {
        try {
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

                    String[] partes = datosRecibidos.split(Pattern.quote("-"));
                    String idEjecEtapa = partes[1];
                    
                    boolean esUltimaEjecucionEtapaDeProduccion = false;
                    boolean esUltimaEjecucionEtapaDePieza = true;

                    long idejecucionproduccion = 0;
                    long idpieza = 0;
                    long idproducto = 0;

                    EjecucionetapaproduccionJpaController ejecEtapaController = new EjecucionetapaproduccionJpaController(JpaUtil.getEntityManagerFactory());
                    Ejecucionetapaproduccion ejecucionetapaproduccion = ejecEtapaController.findEjecucionetapaproduccion(Long.parseLong(idEjecEtapa));

                    Detalleejecucionplanificacion detalleejecucionplanificacion = JpaUtil.getDetalleejecucionplanificacionByEjecucionetapa(ejecucionetapaproduccion.getId());

                    idejecucionproduccion = detalleejecucionplanificacion.getIdejecucionplanificacionproduccion().getIdejecucion();
                    idpieza = detalleejecucionplanificacion.getPieza().getIdpieza();
                    Detalleplanificacionproduccion detalleplanificacionproduccion = JpaUtil.getDetalleplanificacionproduccionPorIdDetalleejecucion(detalleejecucionplanificacion.getId());
                    idproducto = detalleplanificacionproduccion.getIdproducto().getIdproducto();

                    esUltimaEjecucionEtapaDePieza = AccessFunctions.esUltimaEjecucionEtapaDePieza(idejecucionproduccion,idproducto,idpieza);
                    esUltimaEjecucionEtapaDeProduccion = AccessFunctions.esUltimaEjecucionEtapaDeProduccion(detalleejecucionplanificacion.getIdejecucionplanificacionproduccion().getIdejecucion());

                    /*
                     * si no es la ultima etapa de una pieza tengo que
                     * lanzar la etapa que sigue segun el orden
                     */
                    if(!esUltimaEjecucionEtapaDePieza){
                        GestorLanzarProximaEtapa gestorLanzarProximaEtapa = new GestorLanzarProximaEtapa();
                        gestorLanzarProximaEtapa.lanzarProximaEtapa(detalleejecucionplanificacion, detalleplanificacionproduccion);
                    } else {
                        /*
                         * armar el producto real
                         */
                    }
                    
                   /*
                     * ################################################################
                     * SI ES ULTIMA ETAPA DE PIEZA Y NO ES ULTIMA ETAPA DE PRODUCCION
                     * QUE HAGO??????????????? armo el producto real?
                     * ################################################################
                     */


                    /*
                     * si es la ultima etapa de la produccion tengo que registrar
                     * la finalizacion de la linea de produccion
                     */
                    if (esUltimaEjecucionEtapaDeProduccion) {
                        /*
                         * registrar que se completo la produccion, contempla la calidad o no?
                         */
                    }

                    EstadoejecetapaprodJpaController estadoEjecController = new EstadoejecetapaprodJpaController(JpaUtil.getEntityManagerFactory());
                    Estadoejecetapaprod estadoejecetapaprod = estadoEjecController.findEstadoejecetapaprod(IdsEstadoEjecucionEtapaProduccion.FINALIZADA);
                    ejecucionetapaproduccion.setEstado(estadoejecetapaprod);

                    Date fechaActual = Fecha.fechaActualDate();
                    ejecucionetapaproduccion.setFechafin(fechaActual);
                    ejecucionetapaproduccion.setHorafin(fechaActual);

                    detalleejecucionplanificacion.setFechafin(fechaActual);
                    detalleejecucionplanificacion.setHorafin(fechaActual);

                    DetalleejecucionplanificacionJpaController detalleejecucionplanificacionJpaController = new DetalleejecucionplanificacionJpaController(JpaUtil.getEntityManagerFactory());
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
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
