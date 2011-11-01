/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.awt.Dialog.ModalExclusionType;
import java.math.BigInteger;
import metalsoft.negocio.gestores.estados.IdsEstadoPedido;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.CodigodebarraJpaController;
import metalsoft.datos.jpa.controller.DetalleejecucionplanificacionJpaController;
import metalsoft.datos.jpa.controller.DetalleplanificacionproduccionJpaController;
import metalsoft.datos.jpa.controller.DetalleproductorealJpaController;
import metalsoft.datos.jpa.controller.EjecucionetapaproduccionJpaController;
import metalsoft.datos.jpa.controller.EjecucionplanificacionproduccionJpaController;
import metalsoft.datos.jpa.controller.EstadoejecetapaprodJpaController;
import metalsoft.datos.jpa.controller.EstadoejecplanifpedidoJpaController;
import metalsoft.datos.jpa.controller.PlanificacionproduccionJpaController;
import metalsoft.datos.jpa.controller.ProductorealJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Codigodebarra;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;
import metalsoft.datos.jpa.entity.Detallempasignada;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import metalsoft.datos.jpa.entity.Detalleproductoreal;
import metalsoft.datos.jpa.entity.Ejecucionetapaproduccion;
import metalsoft.datos.jpa.entity.Ejecucionplanificacionproduccion;
import metalsoft.datos.jpa.entity.Estadoejecetapaprod;
import metalsoft.datos.jpa.entity.Estadoejecplanifpedido;
import metalsoft.datos.jpa.entity.Materiaprima;
import metalsoft.datos.jpa.entity.Mpasignadaxpiezareal;
import metalsoft.datos.jpa.entity.Piezareal;
import metalsoft.datos.jpa.entity.Planificacionproduccion;
import metalsoft.datos.jpa.entity.Productoreal;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.access.AccessPedido;
import metalsoft.negocio.access.AccessViews;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionEtapaProduccion;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionPlanificacionPedido;
import metalsoft.util.BarCodeUtil;
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
public class GestorRegistrarLanzamientoProduccion {

    public LinkedList<ViewPedidosConMPAsignada> buscarPedidosConMPAsignada() {
        PostgreSQLManager pg = new PostgreSQLManager();
        LinkedList<ViewPedidosConMPAsignada> list = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            list = AccessViews.listPedidosConMPAsignada(cn);
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

    public Date calcularFechaFin(Date fechaActual, Date fechaInicioPrevista, Date fechafinprevista) {
        return Fecha.calcularFechaFin(fechaActual, fechaInicioPrevista, fechafinprevista);
    }

    public long generarNvoNroEjecucionPlanificacionProduccion() {
        PostgreSQLManager pg = new PostgreSQLManager();
        Connection cn = null;
        long result = -1;
        try {
            cn = pg.concectGetCn();
            result = AccessFunctions.nvoNroEjecucionPlanificacionProduccion(cn);
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

    public long guardarEjecucionPlanificacion(Ejecucionplanificacionproduccion ejecucion, long idPlanificacionProduccion) {

        long result = -1;

        EjecucionplanificacionproduccionJpaController controller = new EjecucionplanificacionproduccionJpaController(JpaUtil.getEntityManagerFactory());
        PlanificacionproduccionJpaController controllerPlanificacion = new PlanificacionproduccionJpaController(JpaUtil.getEntityManagerFactory());
        Planificacionproduccion planificacionProduccion = null;

        EstadoejecplanifpedidoJpaController controllerEstadoEjecPlanif = new EstadoejecplanifpedidoJpaController(JpaUtil.getEntityManagerFactory());
        Estadoejecplanifpedido estadoEjecucion = null;

        List<Detallempasignada> lstDetallempasignada = null;

        List<Detalleplanificacionproduccion> lstDetallePlanificacion = null;

//        List<Long> lstIdsEjecucionEtapasAIniciar = new ArrayList<Long>();

        try {
            /*
             * Buscar el estado inicial y asignarlo
             */
            estadoEjecucion = controllerEstadoEjecPlanif.findEstadoejecplanifpedido(IdsEstadoEjecucionPlanificacionPedido.ENEJECUCION);
            ejecucion.setEstado(estadoEjecucion);
            planificacionProduccion = controllerPlanificacion.findPlanificacionproduccion(idPlanificacionProduccion);
            ejecucion.setIdplanificacionproduccion(planificacionProduccion);
            controller.create(ejecucion);
            /*
             * Asigno el id de ejecucion al resultado
             * Si retorno un valor >0 es porque se guardo correctamente
             */
            result = ejecucion.getIdejecucion();


            lstDetallempasignada = planificacionProduccion.getDetallempasignadaList();

//            lstDetallePlanificacion = planificacionProduccion.getDetalleplanificacionproduccionList();

            lstDetallePlanificacion = JpaUtil.getDetalleplanificacionproduccionPorIdPlanificacionProduccion(planificacionProduccion.getIdplanificacionproduccion());

            DetalleejecucionplanificacionJpaController depController = new DetalleejecucionplanificacionJpaController(JpaUtil.getEntityManagerFactory());
            EjecucionetapaproduccionJpaController eepController = new EjecucionetapaproduccionJpaController(JpaUtil.getEntityManagerFactory());
            Detalleejecucionplanificacion detalleejecucionplanificacion = null;

            DetalleplanificacionproduccionJpaController dppController = new DetalleplanificacionproduccionJpaController(JpaUtil.getEntityManagerFactory());

            /*
             * map que va guardando el ultimo indice accedido para cada pieza real segun materia prima
             */
            Map<Long, Integer> mapIndexPiezaReal = new HashMap<Long, Integer>();
            Map<String, Productoreal> mapProductoReal = new HashMap<String, Productoreal>();
            Map<String, Piezareal> mapProdPiezaXPiezareal = new HashMap<String, Piezareal>();

            for (Detalleplanificacionproduccion detalleplanificacionproduccion : lstDetallePlanificacion) {

                Long idProducto = detalleplanificacionproduccion.getIdproducto().getIdproducto();
                Long idPieza = detalleplanificacionproduccion.getIdpieza().getIdpieza();
                Integer indexProducto = detalleplanificacionproduccion.getIndexproducto();
                String keyProductoMasIndex = String.valueOf(idProducto) + String.valueOf(indexProducto);
                String keyProdIndexPieza = keyProductoMasIndex + String.valueOf(idPieza);
                Productoreal productoreal = null;

                if (!mapProductoReal.containsKey(keyProductoMasIndex)) {
                    productoreal = new Productoreal();
                    productoreal.setDetalleproductorealList(new ArrayList<Detalleproductoreal>());
                    productoreal.setProducto(detalleplanificacionproduccion.getIdproducto());
                    productoreal.setIdpedido(detalleplanificacionproduccion.getIdplanificacionproduccion().getPedido());
                    productoreal.setNroproducto(BigInteger.valueOf(AccessFunctions.nvoNroProductoReal()));
                    Codigodebarra codigodebarra = new Codigodebarra();
                    codigodebarra.setCodigo(BarCodeUtil.generarCodigo(BarCodeUtil.COD_PRODUCTO_REAL, String.valueOf(productoreal.getNroproducto())));
                    productoreal.setCodigobarra(codigodebarra);
                    mapProductoReal.put(keyProductoMasIndex, productoreal);
                }

                /*
                 * Creacion del detalle ejecucion planificacion
                 */
                detalleejecucionplanificacion = new Detalleejecucionplanificacion();
                detalleejecucionplanificacion.setIdejecucionplanificacionproduccion(ejecucion);
                detalleejecucionplanificacion.setIdetapaproduccion(detalleplanificacionproduccion.getIdetapaproduccion());
                detalleejecucionplanificacion.setOrden(detalleplanificacionproduccion.getOrden());
                detalleejecucionplanificacion.setPieza(detalleplanificacionproduccion.getIdpieza());

                Materiaprima matPrimaPiezaDetallePlanif = detalleplanificacionproduccion.getIdpieza().getMateriaprima();
                Long idMateriaPrima = matPrimaPiezaDetallePlanif.getIdmateriaprima();

                if (!mapIndexPiezaReal.containsKey(idMateriaPrima)) {
                    mapIndexPiezaReal.put(idMateriaPrima, 0);
                }

                forDetallempasignada:
                for (Detallempasignada detallempasignada : lstDetallempasignada) {
                    if (idMateriaPrima == detallempasignada.getIdmateriaprima().getIdmateriaprima()) {
                        List<Mpasignadaxpiezareal> lstMpasignadaxpiezareal = detallempasignada.getMpasignadaxpiezarealList();
                        Integer index = mapIndexPiezaReal.get(idMateriaPrima);
                        Piezareal piezareal = null;

                        if (!mapProdPiezaXPiezareal.containsKey(keyProdIndexPieza)) {
                            piezareal = lstMpasignadaxpiezareal.get(index).getIdpiezareal();
                            mapProdPiezaXPiezareal.put(keyProdIndexPieza, piezareal);
                            mapIndexPiezaReal.put(idMateriaPrima, index + 1);
                        } else {
                            piezareal = mapProdPiezaXPiezareal.get(keyProdIndexPieza);
                        }
                        detalleejecucionplanificacion.setPiezareal(piezareal);

                        break forDetallempasignada;
                    }
                }

                productoreal = mapProductoReal.get(keyProductoMasIndex);
                Detalleproductoreal detalleproductoreal = new Detalleproductoreal();
                detalleproductoreal.setIdpiezareal(detalleejecucionplanificacion.getPiezareal());
                detalleproductoreal.setIdproductoreal(productoreal);
                productoreal.getDetalleproductorealList().add(detalleproductoreal);
                mapProductoReal.put(keyProductoMasIndex, productoreal);

                /*
                 * Creacion ejecucion etapa produccion
                 */
                Ejecucionetapaproduccion ejecucionetapaproduccion = new Ejecucionetapaproduccion();
                ejecucionetapaproduccion.setEmpleado(detalleplanificacionproduccion.getIdempleado());
                ejecucionetapaproduccion.setIdetapaproduccion(detalleplanificacionproduccion.getIdetapaproduccion());
                long nroEjecucion = generarNvoNroEjecucionEtapa();
                ejecucionetapaproduccion.setNroejecucion(nroEjecucion);
                ejecucionetapaproduccion.setMaquina(detalleplanificacionproduccion.getIdmaquina());

                EstadoejecetapaprodJpaController estadoEjecEtapaController = new EstadoejecetapaprodJpaController(JpaUtil.getEntityManagerFactory());

                Estadoejecetapaprod estadoEjecEtapaProd = null;
//                boolean etapaEnEjecucion = false;
//                if (detalleplanificacionproduccion.getOrden() == 1) {
                Date fechaActual = Fecha.fechaActualDate();
                detalleejecucionplanificacion.setFechainicio(fechaActual);
                detalleejecucionplanificacion.setHorainicio(fechaActual);
                ejecucionetapaproduccion.setFechainicio(fechaActual);
                ejecucionetapaproduccion.setHorainicio(fechaActual);
//                    estadoEjecEtapaProd = estadoEjecEtapaController.findEstadoejecetapaprod(IdsEstadoEjecucionEtapaProduccion.ENEJECUCION);
//                    etapaEnEjecucion = true;
//                } else {
                estadoEjecEtapaProd = estadoEjecEtapaController.findEstadoejecetapaprod(IdsEstadoEjecucionEtapaProduccion.GENERADA);
//                }
                ejecucionetapaproduccion.setEstado(estadoEjecEtapaProd);

                /*
                 * guardar en la base de datos
                 */
                eepController.create(ejecucionetapaproduccion);
//                if (etapaEnEjecucion) {
//                    lstIdsEjecucionEtapasAIniciar.add(ejecucionetapaproduccion.getId());
//                }
                detalleejecucionplanificacion.setEjecucionetapa(ejecucionetapaproduccion);
//                List<Detalleplanificacionproduccion> lstDetalleplanificacionproduccion = new ArrayList<Detalleplanificacionproduccion>();
//                detalleejecucionplanificacion.setDetalleplanificacionproduccionList(lstDetalleplanificacionproduccion);
                depController.create(detalleejecucionplanificacion);
                detalleplanificacionproduccion.setIddetalleejecucionplanificacion(detalleejecucionplanificacion);
                dppController.edit(detalleplanificacionproduccion);
            }


            ProductorealJpaController productorealJpaController = new ProductorealJpaController(JpaUtil.getEntityManagerFactory());
            DetalleproductorealJpaController detalleproductorealJpaController = new DetalleproductorealJpaController(JpaUtil.getEntityManagerFactory());
            CodigodebarraJpaController codigodebarraJpaController = new CodigodebarraJpaController(JpaUtil.getEntityManagerFactory());
            Collection<Productoreal> lstProductoReal = mapProductoReal.values();

            for (Productoreal productoreal : lstProductoReal) {

                List<Detalleproductoreal> lstDetalle = productoreal.getDetalleproductorealList();

                codigodebarraJpaController.create(productoreal.getCodigobarra());

                productoreal.setDetalleproductorealList(null);

                productorealJpaController.create(productoreal);

                productoreal.setDetalleproductorealList(lstDetalle);

                for (Detalleproductoreal detalleproductoreal : lstDetalle) {
                    detalleproductoreal.setIdproductoreal(productoreal);
                    detalleproductorealJpaController.create(detalleproductoreal);
                }



            }

//            imprimirInicioEtapasProduccion(lstIdsEjecucionEtapasAIniciar);


        } catch (PreexistingEntityException ex) {
            Logger.getLogger(GestorRegistrarLanzamientoProduccion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarLanzamientoProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }


        return result;
    }

    public long actualizarEstadoPedido(long idpedido) {
        PostgreSQLManager pg = new PostgreSQLManager();
        Connection cn = null;
        long result = -1;
        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result = AccessPedido.update(idpedido, IdsEstadoPedido.ENPRODUCCION, cn);
            cn.commit();
        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarLanzamientoProduccion.class.getName()).log(Level.SEVERE, null, ex);
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorRegistrarLanzamientoProduccion.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarLanzamientoProduccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    private long generarNvoNroEjecucionEtapa() {
        long result = -1;
        try {
            result = AccessFunctions.nvoNroEjecucionEtapa(new PostgreSQLManager().concectGetCn());
        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarLanzamientoProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static void main(String arg[]) {
        GestorRegistrarLanzamientoProduccion gestor = new GestorRegistrarLanzamientoProduccion();

        List<Long> lst = new ArrayList<Long>();
        lst.add(6L);
        lst.add(7L);
        lst.add(8L);

        gestor.imprimirInicioEtapasProduccion(lst);
    }

    private void imprimirInicioEtapasProduccion(List<Long> lstIdsEjecucionEtapasAIniciar) {

        String sourceFile = "D:\\rpt\\RptInicioEjecucionEtapa.jasper";
        PostgreSQLManager pg = new PostgreSQLManager();
        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();

            masterReport = (JasperReport) JRLoader.loadObject(sourceFile);
            param.put("ID_EJECUCION_ETAPA", lstIdsEjecucionEtapasAIniciar);

            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
            jviewer.setTitle("ETAPAS EN EJECUCION - CODIGOS DE BARRA");
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
}
