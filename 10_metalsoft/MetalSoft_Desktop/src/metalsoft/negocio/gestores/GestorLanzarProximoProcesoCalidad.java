/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.DetalleejecucionplanificacioncalidadJpaController;
import metalsoft.datos.jpa.controller.EjecucionprocesocalidadJpaController;
import metalsoft.datos.jpa.controller.EstadoejecucionprocesocalidadJpaController;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacioncalidad;
import metalsoft.datos.jpa.entity.Detalleplanificacioncalidad;
import metalsoft.datos.jpa.entity.Ejecucionprocesocalidad;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionProcesoCalidad;
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
public class GestorLanzarProximoProcesoCalidad {

    public void lanzarProximoProceso(Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad, Detalleplanificacioncalidad detalleplanificacioncalidad) throws Exception {
        List<Detalleejecucionplanificacioncalidad> lstDetallejecucion = detalleejecucionplanificacioncalidad.getIdejecucionplanificacioncalidad().getDetalleejecucionplanificacioncalidadList();

        Long idPieza = detalleejecucionplanificacioncalidad.getPieza().getIdpieza();
        Long idProducto = detalleplanificacioncalidad.getProducto().getIdproducto();
        Integer orden = detalleejecucionplanificacioncalidad.getOrden();

        for (Detalleejecucionplanificacioncalidad detalleejec : lstDetallejecucion) {

            Detalleplanificacioncalidad detalleplanifcalidad = JpaUtil.getDetalleplanificacioncalidadPorIdDetalleejecucion(detalleejec.getIddetalle());

            if (detalleejec.getPieza().getIdpieza() == idPieza
                    && detalleplanifcalidad.getProducto().getIdproducto() == idProducto
                    && detalleejec.getOrden() == (orden + 1)) {

                lanzarEjecucionProcesoCalidad(detalleejec);

            }

        }
    }

    public void lanzarEjecucionProcesoCalidad(Detalleejecucionplanificacioncalidad detalleEjecPlanifCalidad) throws Exception {
        
        DetalleejecucionplanificacioncalidadJpaController detalleejecucionplanificacioncalidadJpaController = new DetalleejecucionplanificacioncalidadJpaController(JpaUtil.getEntityManagerFactory());
        EjecucionprocesocalidadJpaController ejecucionprocesocalidadJpaController = new EjecucionprocesocalidadJpaController(JpaUtil.getEntityManagerFactory());
        EstadoejecucionprocesocalidadJpaController estadoejecucionprocesocalidadJpaController = new EstadoejecucionprocesocalidadJpaController(JpaUtil.getEntityManagerFactory());

        Date fechaActual = Fecha.fechaActualDate();

        detalleEjecPlanifCalidad.setFechainicio(fechaActual);
        detalleEjecPlanifCalidad.setHorainicio(fechaActual);

        Ejecucionprocesocalidad ejecucionprocesocalidad = detalleEjecPlanifCalidad.getEjecucionprocesocalidad();
        ejecucionprocesocalidad.setEstado(estadoejecucionprocesocalidadJpaController.findEstadoejecucionprocesocalidad(IdsEstadoEjecucionProcesoCalidad.ENEJECUCION));

        ejecucionprocesocalidadJpaController.edit(ejecucionprocesocalidad);
        detalleejecucionplanificacioncalidadJpaController.edit(detalleEjecPlanifCalidad);

        /*
         * imprimir el codigo de barra para la ejecucion de la etapa
         */

        imprimirCodigoEjecucionProcesoCalidad(ejecucionprocesocalidad.getIdejecucion());
    }

    private void imprimirCodigoEjecucionProcesoCalidad(Long idejecucion) {
        String sourceFile = "D:\\rpt\\RptSiguienteEjecucionProcesoCalidad.jasper";
        PostgreSQLManager pg = new PostgreSQLManager();
        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();

            masterReport = (JasperReport) JRLoader.loadObject(sourceFile);
            param.put("ID_EJECUCION_PROCESO", idejecucion);

            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);

//            boolean dialogoImpresion = false;
//            JasperPrintManager.printReport(jasperPrint, dialogoImpresion);

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("PROCESO DE CALIDAD - CODIGO DE BARRA");
            jviewer.setVisible(true);

        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
