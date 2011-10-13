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
import metalsoft.datos.jpa.controller.DetalleejecucionplanificacioncalidadJpaController;
import metalsoft.datos.jpa.controller.EjecucionplanificacioncalidadJpaController;
import metalsoft.datos.jpa.controller.EjecucionprocesocalidadJpaController;
import metalsoft.datos.jpa.controller.EstadoejecplancalidadJpaController;
import metalsoft.datos.jpa.controller.EstadoejecucionprocesocalidadJpaController;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;
import metalsoft.datos.jpa.entity.Ejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.Ejecucionprocesocalidad;
import metalsoft.datos.jpa.entity.Estadoejecplancalidad;
import metalsoft.datos.jpa.entity.Estadoejecucionprocesocalidad;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.gestores.GestorLanzarProximoProcesoCalidad;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionPlanificacionCalidad;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionProcesoCalidad;
import metalsoft.presentacion.Principal;
import metalsoft.util.Fecha;
import metalsoft.util.MetalsoftProperties;

/**
 *
 * @author Nino
 */
public class HiloEscuchadorFinProcesoCalidad extends HiloSyncBase implements Runnable {

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
                    String puertoString = MetalsoftProperties.getProperty(MetalsoftProperties.PUERTO_FIN_PROCESO_CALIDAD);
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

                    procesarFinEjecucionProcesoCalidad();

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

    private void procesarFinEjecucionProcesoCalidad() throws Exception {
        String idEjecProcesoCalidad = partes[2];

        boolean esUltimaEjecucionProcesoCalida = false;
        boolean esUltimaEjecucionProcesoDePieza = true;

        long idejecucionplanificacioncalidad = 0;
        long idpieza = 0;
        long idproducto = 0;

        EjecucionprocesocalidadJpaController ejecProcesoCalidadController = new EjecucionprocesocalidadJpaController(JpaUtil.getEntityManagerFactory());
        Ejecucionprocesocalidad ejecucionprocesocalidad = ejecProcesoCalidadController.findEjecucionprocesocalidad(Long.parseLong(idEjecProcesoCalidad));

        Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad = JpaUtil.getDetalleejecucionplanificacioncalidadByEjecucionProcesoCalidad(ejecucionprocesocalidad.getIdejecucion());

        idejecucionplanificacioncalidad = detalleejecucionplanificacioncalidad.getIdejecucionplanificacioncalidad().getIdejecucion();
        idpieza = detalleejecucionplanificacioncalidad.getPieza().getIdpieza();
        Detalleplanificacioncalidad detalleplanificacioncalidad = JpaUtil.getDetalleplanificacioncalidadPorIdDetalleejecucion(detalleejecucionplanificacioncalidad.getIddetalle());
        idproducto = detalleplanificacioncalidad.getProducto().getIdproducto();

        esUltimaEjecucionProcesoDePieza = AccessFunctions.esUltimaEjecucionProcesoCalidadDePieza(idejecucionplanificacioncalidad, idproducto, idpieza);
        esUltimaEjecucionProcesoCalida = AccessFunctions.esUltimaEjecucionProcesoCalidadDeCalidad(detalleejecucionplanificacioncalidad.getIdejecucionplanificacioncalidad().getIdejecucion());

        /*
         * si no es la ultima etapa de una pieza tengo que
         * lanzar la etapa que sigue segun el orden
         */
        if (!esUltimaEjecucionProcesoDePieza) {
//            GestorLanzarProximoProcesoCalidad gestorLanzarProximoProcesoCalidad = new GestorLanzarProximoProcesoCalidad();
//            gestorLanzarProximoProcesoCalidad.lanzarProximoProceso(detalleejecucionplanificacioncalidad, detalleplanificacioncalidad);
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
        if (esUltimaEjecucionProcesoCalida) {
            /*
             * registrar que se completo la produccion
             */

            EstadoejecplancalidadJpaController estadoejecplancalidadJpaController = new EstadoejecplancalidadJpaController(JpaUtil.getEntityManagerFactory());
            Estadoejecplancalidad estadoejecplancalidad = estadoejecplancalidadJpaController.findEstadoejecplancalidad(IdsEstadoEjecucionPlanificacionCalidad.FINALIZADA);

            Ejecucionplanificacioncalidad ejecucionplanificacioncalidad = detalleejecucionplanificacioncalidad.getIdejecucionplanificacioncalidad();
            ejecucionplanificacioncalidad.setEstado(estadoejecplancalidad);
            ejecucionplanificacioncalidad.setFechafin(Fecha.fechaActualDate());
            ejecucionplanificacioncalidad.setHorafin(Fecha.fechaActualDate());

            EjecucionplanificacioncalidadJpaController ejecucionplanificacioncalidadJpaController = new EjecucionplanificacioncalidadJpaController(JpaUtil.getEntityManagerFactory());
            ejecucionplanificacioncalidadJpaController.edit(ejecucionplanificacioncalidad);
        }

        EstadoejecucionprocesocalidadJpaController estadoEjecProcesoCalidadController = new EstadoejecucionprocesocalidadJpaController(JpaUtil.getEntityManagerFactory());
        Estadoejecucionprocesocalidad estadoejecucionprocesocalidad = estadoEjecProcesoCalidadController.findEstadoejecucionprocesocalidad(IdsEstadoEjecucionProcesoCalidad.FINALIZADA);
        ejecucionprocesocalidad.setEstado(estadoejecucionprocesocalidad);

        Date fechaActual = Fecha.fechaActualDate();
        ejecucionprocesocalidad.setFechafin(fechaActual);
        ejecucionprocesocalidad.setHorafin(fechaActual);

        detalleejecucionplanificacioncalidad.setFechafin(fechaActual);
        detalleejecucionplanificacioncalidad.setHorafin(fechaActual);

        DetalleejecucionplanificacioncalidadJpaController detalleejecucionplanificacioncalidadJpaController = new DetalleejecucionplanificacioncalidadJpaController(JpaUtil.getEntityManagerFactory());
        detalleejecucionplanificacioncalidadJpaController.edit(detalleejecucionplanificacioncalidad);

//        Date fechaInicio = null;
//        fechaInicio = Fecha.setHoraMinutoSegundo(ejecucionprocesocalidad.getFechainicio(), ejecucionprocesocalidad.getHorainicio());
//        Date difHoras = Fecha.diferenciaEnHoras(fechaInicio, fechaActual);
//        int difDias = Fecha.diferenciaEnDias(fechaInicio, fechaActual);
//        int horas = difHoras.getHours();
//        int minutos = difHoras.getMinutes();
//        int segundos = difHoras.getSeconds();
//        String totalHrsHombre = String.valueOf((difDias * 24) + horas) + ":" + String.valueOf(minutos) + ":" + String.valueOf(segundos);
//        ejecucionprocesocalidad.setTotalhorashombre(totalHrsHombre);

        ejecProcesoCalidadController.edit(ejecucionprocesocalidad);

        vtnPrincipal.eliminarProcesoCalidadNoFinalizado(detalleejecucionplanificacioncalidad.getIddetalle());
        /*
         * si los datos se procesaron correctamente aviso que todo esta ok
         */
        System.out.println("INFO: Se procesaron los datos recibidos.");
        oos.writeObject("OK");
    }

    public Principal getVtnPrincipal() {
        return vtnPrincipal;
    }

    public void setVtnPrincipal(Principal vtnPrincipal) {
        this.vtnPrincipal = vtnPrincipal;
    }
}
