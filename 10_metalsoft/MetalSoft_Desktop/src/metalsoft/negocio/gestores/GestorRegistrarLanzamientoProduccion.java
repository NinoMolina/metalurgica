/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import metalsoft.negocio.gestores.estados.IdsEstadoPedido;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.jpa.controller.DetalleejecucionplanificacionJpaController;
import metalsoft.datos.jpa.controller.EjecucionetapaproduccionJpaController;
import metalsoft.datos.jpa.controller.EjecucionplanificacionproduccionJpaController;
import metalsoft.datos.jpa.controller.EstadoejecetapaprodJpaController;
import metalsoft.datos.jpa.controller.EstadoejecplanifpedidoJpaController;
import metalsoft.datos.jpa.controller.PlanificacionproduccionJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;
import metalsoft.datos.jpa.entity.Detallempasignada;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import metalsoft.datos.jpa.entity.Ejecucionetapaproduccion;
import metalsoft.datos.jpa.entity.Ejecucionplanificacionproduccion;
import metalsoft.datos.jpa.entity.Estadoejecetapaprod;
import metalsoft.datos.jpa.entity.Estadoejecplanifpedido;
import metalsoft.datos.jpa.entity.Materiaprima;
import metalsoft.datos.jpa.entity.Mpasignadaxpiezareal;
import metalsoft.datos.jpa.entity.Planificacionproduccion;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.access.AccessPedido;
import metalsoft.negocio.access.AccessViews;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionEtapaProduccion;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionPlanificacionPedido;
import metalsoft.util.Fecha;

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
        GregorianCalendar inicio = (GregorianCalendar) Fecha.parseToCalendar(fechaInicioPrevista);
//        System.out.println(Fecha.parseToString(inicio.getTime()));
        GregorianCalendar fin = (GregorianCalendar) Fecha.parseToCalendar(fechafinprevista);
//        System.out.println(Fecha.parseToString(fin.getTime()));
        int year = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
        int month = fin.get(Calendar.MONTH) - inicio.get(Calendar.MONTH);
        int day = fin.get(Calendar.DATE) - inicio.get(Calendar.DATE);
        inicio.add(Calendar.YEAR, year);
        GregorianCalendar actual = (GregorianCalendar) Fecha.parseToCalendar(fechaActual);
//        System.out.println(Fecha.parseToString(actual.getTime()));
        actual.add(Calendar.YEAR, year);
        actual.add(Calendar.MONTH, month);
        actual.add(Calendar.DATE, day);
//        System.out.println(Fecha.parseToString(actual.getTime()));
        return actual.getTime();
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

        EjecucionplanificacionproduccionJpaController controller = new EjecucionplanificacionproduccionJpaController();
        PlanificacionproduccionJpaController controllerPlanificacion = new PlanificacionproduccionJpaController();
        Planificacionproduccion planificacionProduccion = null;

        EstadoejecplanifpedidoJpaController controllerEstadoEjecPlanif = new EstadoejecplanifpedidoJpaController();
        Estadoejecplanifpedido estadoEjecucion = null;

        List<Detallempasignada> lstDetallempasignada = null;

        List<Detalleplanificacionproduccion> lstDetallePlanificacion = null;

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
            
            lstDetallePlanificacion = planificacionProduccion.getDetalleplanificacionproduccionList();

            DetalleejecucionplanificacionJpaController depController = new DetalleejecucionplanificacionJpaController();
            EjecucionetapaproduccionJpaController eepController = new EjecucionetapaproduccionJpaController();
            Detalleejecucionplanificacion detalleejecucionplanificacion = null;

            Map<Long, Integer> mapIndexPiezaReal = new HashMap<Long, Integer>();

            for (Detalleplanificacionproduccion detalleplanificacionproduccion : lstDetallePlanificacion) {
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

                if(!mapIndexPiezaReal.containsKey(idMateriaPrima)){
                    mapIndexPiezaReal.put(idMateriaPrima, 0);
                }

                forDetallempasignada: for(Detallempasignada detallempasignada: lstDetallempasignada){
                    if(idMateriaPrima == detallempasignada.getIdmateriaprima().getIdmateriaprima()){
                        List<Mpasignadaxpiezareal> lstMpasignadaxpiezareal = detallempasignada.getMpasignadaxpiezarealList();
                        Integer index = mapIndexPiezaReal.get(idMateriaPrima);
                        detalleejecucionplanificacion.setPiezareal(lstMpasignadaxpiezareal.get(index).getIdpiezareal());
                        mapIndexPiezaReal.put(idMateriaPrima, index + 1);
                        break forDetallempasignada;
                    }
                }
                
                /*
                 * Creacion ejecucion etapa produccion
                 */
                Ejecucionetapaproduccion ejecucionetapaproduccion = new Ejecucionetapaproduccion();
                ejecucionetapaproduccion.setEmpleado(detalleplanificacionproduccion.getIdempleado());
                ejecucionetapaproduccion.setIdetapaproduccion(detalleplanificacionproduccion.getIdetapaproduccion());
                long nroEjecucion = generarNvoNroEjecucionEtapa();
                ejecucionetapaproduccion.setNroejecucion(nroEjecucion);
                EstadoejecetapaprodJpaController estadoEjecEtapaController = new EstadoejecetapaprodJpaController();

                Estadoejecetapaprod estadoEjecEtapaProd = null;
                if (detalleplanificacionproduccion.getOrden() == 1) {
                    detalleejecucionplanificacion.setFechainicio(Fecha.fechaActualDate());
                    detalleejecucionplanificacion.setHorainicio(Fecha.fechaActualDate());
                    estadoEjecEtapaProd = estadoEjecEtapaController.findEstadoejecetapaprod(IdsEstadoEjecucionEtapaProduccion.ENEJECUCION);
                } else {
                    estadoEjecEtapaProd = estadoEjecEtapaController.findEstadoejecetapaprod(IdsEstadoEjecucionEtapaProduccion.GENERADA);
                }
                ejecucionetapaproduccion.setEstado(estadoEjecEtapaProd);

                /*
                 * guardar en la base de datos
                 */
                eepController.create(ejecucionetapaproduccion);
                detalleejecucionplanificacion.setEjecucionetapa(ejecucionetapaproduccion);
                depController.create(detalleejecucionplanificacion);
            }


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
}
