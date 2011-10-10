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
import metalsoft.datos.jpa.controller.EjecucionplanificacionproduccionJpaController;
import metalsoft.datos.jpa.controller.EstadoejecetapaprodJpaController;
import metalsoft.datos.jpa.controller.EstadoejecplanifpedidoJpaController;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import metalsoft.datos.jpa.entity.Ejecucionetapaproduccion;
import metalsoft.datos.jpa.entity.Ejecucionplanificacionproduccion;
import metalsoft.datos.jpa.entity.Estadoejecetapaprod;
import metalsoft.datos.jpa.entity.Estadoejecplanifpedido;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.gestores.GestorLanzarProximaEtapa;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionEtapaProduccion;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionPlanifPedido;
import metalsoft.presentacion.Principal;
import metalsoft.util.Fecha;
import metalsoft.util.MetalsoftProperties;

/**
 *
 * @author Nino
 */
public class HiloEscuchadorFinEtapa extends HiloSyncBase implements Runnable {

    private Principal vtnPrincipal;
    private ServerSocket serverSocket = null;
    private Socket clienteSocket = null;
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;
    private String[] partes = null;

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
                System.out.println("INFO: Se recibio un patron de datos correspondiente a un codigo de barras de MetalSoft...");
                try {

                    partes = datosRecibidos.split(Pattern.quote("-"));

                    procesarFinEjecucionEtapaProduccion();

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
                System.out.println("WARNING: Los datos recibidos no coinciden con el patron de codigo de barra de MetalSoft.");
                oos.writeObject("WARNING: Los datos enviados no coinciden con el patron de codigo de barra de MetalSoft.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void procesarFinEjecucionEtapaProduccion() throws Exception {
        String idEjecEtapa = partes[2];

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

        esUltimaEjecucionEtapaDePieza = AccessFunctions.esUltimaEjecucionEtapaDePieza(idejecucionproduccion, idproducto, idpieza);
        esUltimaEjecucionEtapaDeProduccion = AccessFunctions.esUltimaEjecucionEtapaDeProduccion(detalleejecucionplanificacion.getIdejecucionplanificacionproduccion().getIdejecucion());

        /*
         * si no es la ultima etapa de una pieza tengo que
         * lanzar la etapa que sigue segun el orden
         */
        if (!esUltimaEjecucionEtapaDePieza) {
//            GestorLanzarProximaEtapa gestorLanzarProximaEtapa = new GestorLanzarProximaEtapa();
//            gestorLanzarProximaEtapa.lanzarProximaEtapa(detalleejecucionplanificacion, detalleplanificacionproduccion);
        } else {
            /*
             * armar el producto real
             */
//                        Productoreal productoReal = new Productoreal();
//                        
//                        EstadoproductorealJpaController estadoproductorealJpaController = new EstadoproductorealJpaController(JpaUtil.getEntityManagerFactory());
//                        productoReal.setEstado(estadoproductorealJpaController.findEstadoproductoreal(IdsEstadoProductoReal.EN_PRODUCCION));
//                        
//                        productoReal.setFechaterminacion(Fecha.fechaActualDate());
//                        productoReal.setProducto(detalleplanificacionproduccion.getIdproducto());
//                        productoReal.setNroproducto(BigInteger.valueOf(AccessFunctions.nvoNroProductoReal()));
//                        productoReal.setIdpedido(detalleplanificacionproduccion.getIdplanificacionproduccion().getPedido());
//                        
//                        ProductorealJpaController productorealJpaController = new ProductorealJpaController(JpaUtil.getEntityManagerFactory());
//                        productorealJpaController.create(productoReal);
//                        
//                        Codigodebarra codigodebarra = new Codigodebarra();
//                        codigodebarra.setCodigo(BarCodeUtil.generarCodigo(BarCodeUtil.COD_PRODUCTO_REAL, String.valueOf(productoReal.getIdproductoreal())));
//                        CodigodebarraJpaController codigodebarraJpaController = new CodigodebarraJpaController(JpaUtil.getEntityManagerFactory());
//                        codigodebarraJpaController.create(codigodebarra);
//                        
//                        productoReal.setCodigobarra(codigodebarra);
//                        
//                        productorealJpaController.edit(productoReal);
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
             * registrar que se completo la produccion
             */

            EstadoejecplanifpedidoJpaController estadoejecplanifpedidoJpaController = new EstadoejecplanifpedidoJpaController(JpaUtil.getEntityManagerFactory());
            Estadoejecplanifpedido estadoejecplanifpedido = estadoejecplanifpedidoJpaController.findEstadoejecplanifpedido(IdsEstadoEjecucionPlanifPedido.FINALIZADA);

            Ejecucionplanificacionproduccion ejecucionplanificacionproduccion = detalleejecucionplanificacion.getIdejecucionplanificacionproduccion();
            ejecucionplanificacionproduccion.setEstado(estadoejecplanifpedido);
            ejecucionplanificacionproduccion.setFechafin(Fecha.fechaActualDate());
            ejecucionplanificacionproduccion.setHorafin(Fecha.fechaActualDate());

            EjecucionplanificacionproduccionJpaController ejecucionplanificacionproduccionJpaController = new EjecucionplanificacionproduccionJpaController(JpaUtil.getEntityManagerFactory());
            ejecucionplanificacionproduccionJpaController.edit(ejecucionplanificacionproduccion);
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
    }
}
