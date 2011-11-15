/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.awt.Dialog.ModalExclusionType;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.DetalleejecucionplanificacioncalidadJpaController;
import metalsoft.datos.jpa.controller.DetalleplanificacioncalidadJpaController;
import metalsoft.datos.jpa.controller.EjecucionplanificacioncalidadJpaController;
import metalsoft.datos.jpa.controller.EjecucionprocesocalidadJpaController;
import metalsoft.datos.jpa.controller.EstadoejecplancalidadJpaController;
import metalsoft.datos.jpa.controller.EstadoejecucionprocesocalidadJpaController;
import metalsoft.datos.jpa.controller.EstadopedidoJpaController;
import metalsoft.datos.jpa.controller.PedidoJpaController;
import metalsoft.datos.jpa.controller.PlanificacioncalidadJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;
import metalsoft.datos.jpa.entity.Ejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.Ejecucionprocesocalidad;
import metalsoft.datos.jpa.entity.Estadoejecplancalidad;
import metalsoft.datos.jpa.entity.Estadoejecucionprocesocalidad;
import metalsoft.datos.jpa.entity.Estadopedido;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.datos.jpa.entity.Planificacioncalidad;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.access.AccessViews;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionPlanificacionCalidad;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionProcesoCalidad;
import metalsoft.negocio.gestores.estados.IdsEstadoPedido;
import metalsoft.util.Fecha;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Nino
 */
public class GestorLanzarCalidad {

    public LinkedList<ViewPedidosConProduccionFinalizada> buscarPedidosConProduccionFinalizada(Date fecha) {
        PostgreSQLManager pg = new PostgreSQLManager();
        LinkedList<ViewPedidosConProduccionFinalizada> list = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            list = AccessViews.listPedidosConProduccionFinalizada(fecha, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public Date calcularFechaFin(Date fechaActual, Date fechainicioprevista, Date fechafinprevista) {
        return Fecha.calcularFechaFin(fechaActual, fechainicioprevista, fechafinprevista);
    }

    public long generarNvoNroEjecucionPlanificacionCalidad() {
        PostgreSQLManager pg = new PostgreSQLManager();
        Connection cn = null;
        long result = -1;
        try {
            cn = pg.concectGetCn();
            result = AccessFunctions.nvoNroEjecucionPlanificacionCalidad(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarLanzamientoProduccion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarLanzamientoProduccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public void actualizarEstadoPedido(Long idpedido) throws Exception {
        PedidoJpaController controller = new PedidoJpaController(JpaUtil.getEntityManagerFactory());
        EstadopedidoJpaController estadoController = new EstadopedidoJpaController(JpaUtil.getEntityManagerFactory());

        Estadopedido estado = null;
        Pedido pedido = null;

        estado = estadoController.findEstadopedido(IdsEstadoPedido.ENCALIDAD);

        pedido = controller.findPedido(idpedido);

        pedido.setEstado(estado);

        controller.edit(pedido);
    }

    public long guardarEjecucionPlanificacion(Ejecucionplanificacioncalidad ejecucion, Long idplanificacioncalidad) {
        long result = -1;

        EjecucionplanificacioncalidadJpaController controller = new EjecucionplanificacioncalidadJpaController(JpaUtil.getEntityManagerFactory());
        PlanificacioncalidadJpaController controllerPlanificacion = new PlanificacioncalidadJpaController(JpaUtil.getEntityManagerFactory());
        Planificacioncalidad planificacionCalidad = null;

        EstadoejecplancalidadJpaController controllerEstadoEjecCalidad = new EstadoejecplancalidadJpaController(JpaUtil.getEntityManagerFactory());
        Estadoejecplancalidad estadoEjecucion = null;

        List<Detalleplanificacioncalidad> lstDetallePlanificacion = null;

//        List<Long> lstIdsEjecucionProcesosAIniciar = new ArrayList<Long>();

        try {
            /*
             * Buscar el estado inicial y asignarlo
             */
            estadoEjecucion = controllerEstadoEjecCalidad.findEstadoejecplancalidad(IdsEstadoEjecucionPlanificacionCalidad.ENEJECUCION);
            ejecucion.setEstado(estadoEjecucion);
            planificacionCalidad = controllerPlanificacion.findPlanificacioncalidad(idplanificacioncalidad);
            ejecucion.setIdplanificacioncalidad(planificacionCalidad);
            controller.create(ejecucion);
            /*
             * Asigno el id de ejecucion al resultado
             * Si retorno un valor >0 es porque se guardo correctamente
             */
            result = ejecucion.getIdejecucion();

            lstDetallePlanificacion = planificacionCalidad.getDetalleplanificacioncalidadList();

            DetalleejecucionplanificacioncalidadJpaController detalleEjecPlanificacionCalidadController = new DetalleejecucionplanificacioncalidadJpaController(JpaUtil.getEntityManagerFactory());
            EjecucionprocesocalidadJpaController ejecProcesoCalidadController = new EjecucionprocesocalidadJpaController(JpaUtil.getEntityManagerFactory());
            Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad = null;

            DetalleplanificacioncalidadJpaController detallePlanificacionCalidadController = new DetalleplanificacioncalidadJpaController(JpaUtil.getEntityManagerFactory());

//            List<Detalleejecucionplanificacion> lstDetalleEjecProduccion = planificacionCalidad.getPedido().getPlanificacionproduccionList().get(0).getEjecucionplanificacionproduccionList().get(0).getDetalleejecucionplanificacionList();
//            System.out.println("Detalle ejec prod: " + lstDetalleEjecProduccion.size());
            
            List<ViewPiezarealParaCalidad> lstViewPiezasParaCalidad = null;
            
            for (Detalleplanificacioncalidad detalleplanificacioncalidad : lstDetallePlanificacion) {
                /*
                 * Creacion del detalle ejecucion planificacion
                 */
                detalleejecucionplanificacioncalidad = new Detalleejecucionplanificacioncalidad();
                detalleejecucionplanificacioncalidad.setIdejecucionplanificacioncalidad(ejecucion);
                detalleejecucionplanificacioncalidad.setIdprocesocalidad(detalleplanificacioncalidad.getProcesocalidad());
                detalleejecucionplanificacioncalidad.setOrden(detalleplanificacioncalidad.getOrden());
                detalleejecucionplanificacioncalidad.setPieza(detalleplanificacioncalidad.getPieza());
                
                if(lstViewPiezasParaCalidad == null){
                    lstViewPiezasParaCalidad = AccessViews.getListViewPiezarealParaCalidad(detalleplanificacioncalidad.getIdplanificacioncalidad().getPedido().getIdpedido()); 
                }
                
                forPiezaReal: for (ViewPiezarealParaCalidad viewPiezarealParaCalidad : lstViewPiezasParaCalidad) {
                    
                    String keyView = viewPiezarealParaCalidad.getIdproducto().toString() + viewPiezarealParaCalidad.getIndexproducto().toString() + viewPiezarealParaCalidad.getIdpieza().toString() + viewPiezarealParaCalidad.getIndexpieza().toString();
                    String keyPiezaDetalle = detalleplanificacioncalidad.getProducto().getIdproducto().toString() + detalleplanificacioncalidad.getIndexproducto().toString() + detalleplanificacioncalidad.getPieza().getIdpieza().toString() + detalleplanificacioncalidad.getIndexpieza().toString();
                    
                    if(keyView.equals(keyPiezaDetalle)){
                        detalleejecucionplanificacioncalidad.setPiezareal(JpaUtil.getPiezaReal(viewPiezarealParaCalidad.getIdpiezareal()));
                        break forPiezaReal;
                    }
                }
                /*
                 * Creacion ejecucion etapa produccion
                 */
                Ejecucionprocesocalidad ejecucionprocesocalidad = new Ejecucionprocesocalidad();
                ejecucionprocesocalidad.setEmpleado(detalleplanificacioncalidad.getEmpleado());
                ejecucionprocesocalidad.setMaquina(detalleplanificacioncalidad.getMaquina());
                long nroEjecucion = generarNvoNroEjecucionProcesoCalidad();
                ejecucionprocesocalidad.setNroejecucion(BigInteger.valueOf(nroEjecucion));

                EstadoejecucionprocesocalidadJpaController estadoEjecProcesoCalidadController = new EstadoejecucionprocesocalidadJpaController(JpaUtil.getEntityManagerFactory());

                Estadoejecucionprocesocalidad estadoejecucionprocesocalidad = null;
//                boolean procesoEnEjecucion = false;
//                if (detalleplanificacioncalidad.getOrden() == 1) {
                Date fechaActual = Fecha.fechaActualDate();
                detalleejecucionplanificacioncalidad.setFechainicio(fechaActual);
                detalleejecucionplanificacioncalidad.setHorainicio(fechaActual);
                ejecucionprocesocalidad.setFechainicio(fechaActual);
                ejecucionprocesocalidad.setHorainicio(fechaActual);
//                    estadoejecucionprocesocalidad = estadoEjecProcesoCalidadController.findEstadoejecucionprocesocalidad(IdsEstadoEjecucionProcesoCalidad.ENEJECUCION);
//                    procesoEnEjecucion = true;
//                } else {
                estadoejecucionprocesocalidad = estadoEjecProcesoCalidadController.findEstadoejecucionprocesocalidad(IdsEstadoEjecucionProcesoCalidad.GENERADA);
//                }
                ejecucionprocesocalidad.setEstado(estadoejecucionprocesocalidad);

                /*
                 * guardar en la base de datos
                 */
                ejecProcesoCalidadController.create(ejecucionprocesocalidad);

//                if (procesoEnEjecucion) {
//                    lstIdsEjecucionProcesosAIniciar.add(ejecucionprocesocalidad.getIdejecucion());
//                }

                detalleejecucionplanificacioncalidad.setEjecucionprocesocalidad(ejecucionprocesocalidad);

                detalleEjecPlanificacionCalidadController.create(detalleejecucionplanificacioncalidad);
                detalleplanificacioncalidad.setIddetalleejecucionplanificacioncalidad(detalleejecucionplanificacioncalidad);
                detallePlanificacionCalidadController.edit(detalleplanificacioncalidad);
            }

//            imprimirInicioProcesoCalidad(lstIdsEjecucionProcesosAIniciar);


        } catch (PreexistingEntityException ex) {
            Logger.getLogger(GestorRegistrarLanzamientoProduccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarLanzamientoProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }


        return result;
    }

    private long generarNvoNroEjecucionProcesoCalidad() {
        long result = -1;
        PostgreSQLManager pg = new PostgreSQLManager();
        try {
            result = AccessFunctions.nvoNroEjecucionProcesoCalidad(pg.concectGetCn());
        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarLanzamientoProduccion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorLanzarCalidad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    private void imprimirInicioProcesoCalidad(List<Long> lstIdsEjecucionProcesosAIniciar) {

//        String sourceFile = "D:\\rpt\\RptInicioEjecucionProcesoCalidad.jasper";
        PostgreSQLManager pg = new PostgreSQLManager();
//        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();

            masterReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/metalsoft/reportes/RptInicioEjecucionProcesoCalidad.jasper"));
            param.put("ID_EJECUCION_PROCESO", lstIdsEjecucionProcesosAIniciar);

            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
            jviewer.setTitle("PROCESOS DE CALIDAD EN EJECUCION - CODIGOS DE BARRA");
            jviewer.setVisible(true);

        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    public void imprimirHojaDePieza(long idPedido){
        PostgreSQLManager pg = new PostgreSQLManager();
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;
        try {
            cn = pg.concectGetCn();
            masterReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/metalsoft/reportes/RptHojaDePieza.jasper"));
            
            param.put("ID_PEDIDO", new Long(idPedido));
           
            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);


            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
            jviewer.setTitle("Hoja de Pieza");
            jviewer.setVisible(true);

        } catch (Exception ex) {
            Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
