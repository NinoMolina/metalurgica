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
import metalsoft.datos.jpa.controller.DetalleejecucionplanificacionJpaController;
import metalsoft.datos.jpa.controller.EjecucionetapaproduccionJpaController;
import metalsoft.datos.jpa.controller.EstadoejecetapaprodJpaController;
import metalsoft.datos.jpa.entity.Detalleejecucionplanificacion;
import metalsoft.datos.jpa.entity.Detalleplanificacionproduccion;
import metalsoft.datos.jpa.entity.Ejecucionetapaproduccion;
import metalsoft.negocio.gestores.estados.IdsEstadoEjecucionEtapaProduccion;
import metalsoft.util.Fecha;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Nino
 */
public class GestorLanzarProximaEtapa {

//    public static void main(String arg[]){
//        try {
//            DetalleejecucionplanificacionJpaController con = new DetalleejecucionplanificacionJpaController(JpaUtil.getEntityManagerFactory());
//            Detalleejecucionplanificacion detalleejecucionplanificacion = con.findDetalleejecucionplanificacion(12L);
//            Detalleplanificacionproduccion detalleplanificacionproduccion = JpaUtil.getDetalleplanificacionproduccionPorIdDetalleejecucion(12L);
//            
//            GestorLanzarProximaEtapa g = new GestorLanzarProximaEtapa();
//            g.lanzarProximaEtapa(detalleejecucionplanificacion, detalleplanificacionproduccion);
//            
//        } catch (Exception ex) {
//            Logger.getLogger(GestorLanzarProximaEtapa.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void lanzarProximaEtapa(Detalleejecucionplanificacion detalleejecucionplanificacion, Detalleplanificacionproduccion detalleplanificacionproduccion) throws Exception {

        List<Detalleejecucionplanificacion> lstDetallejecucion = detalleejecucionplanificacion.getIdejecucionplanificacionproduccion().getDetalleejecucionplanificacionList();

        Long idPieza = detalleejecucionplanificacion.getPieza().getIdpieza();
        Long idProducto = detalleplanificacionproduccion.getIdproducto().getIdproducto();
        Integer orden = detalleejecucionplanificacion.getOrden();

        for (Detalleejecucionplanificacion detalleejec : lstDetallejecucion) {

            Detalleplanificacionproduccion detalleplanifprod = JpaUtil.getDetalleplanificacionproduccionPorIdDetalleejecucion(detalleejec.getId());

            if (detalleejec.getPieza().getIdpieza() == idPieza
                    && detalleplanifprod.getIdproducto().getIdproducto() == idProducto
                    && detalleejec.getOrden() == (orden + 1)) {

                lanzarEjecucionEtapa(detalleejec);

                break;

            }

        }
    }

    public void lanzarEjecucionEtapa(Detalleejecucionplanificacion detalleejecucionplanificacion) throws Exception {

        DetalleejecucionplanificacionJpaController detalleejecucionplanificacionJpaController = new DetalleejecucionplanificacionJpaController(JpaUtil.getEntityManagerFactory());
        EjecucionetapaproduccionJpaController ejecucionetapaproduccionJpaController = new EjecucionetapaproduccionJpaController(JpaUtil.getEntityManagerFactory());
        EstadoejecetapaprodJpaController estadoejecetapaprodJpaController = new EstadoejecetapaprodJpaController(JpaUtil.getEntityManagerFactory());

        Date fechaActual = Fecha.fechaActualDate();

        detalleejecucionplanificacion.setFechainicio(fechaActual);
        detalleejecucionplanificacion.setHorainicio(fechaActual);

        Ejecucionetapaproduccion ejecucionetapaproduccion = detalleejecucionplanificacion.getEjecucionetapa();
        ejecucionetapaproduccion.setEstado(estadoejecetapaprodJpaController.findEstadoejecetapaprod(IdsEstadoEjecucionEtapaProduccion.ENEJECUCION));

        ejecucionetapaproduccionJpaController.edit(ejecucionetapaproduccion);
        detalleejecucionplanificacionJpaController.edit(detalleejecucionplanificacion);

        /*
         * imprimir el codigo de barra para la ejecucion de la etapa
         */

        imprimirCodigoEjecucionEtapa(ejecucionetapaproduccion.getId());
    }

    private void imprimirCodigoEjecucionEtapa(Long id) {
        String sourceFile = "D:\\rpt\\RptSiguienteEjecucionEtapa.jasper";
        PostgreSQLManager pg = new PostgreSQLManager();
        System.out.println(sourceFile);
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();

            masterReport = (JasperReport) JRLoader.loadObject(sourceFile);
            param.put("ID_EJECUCION_ETAPA", id);

            final JasperPrint jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);

//            boolean dialogoImpresion = false;
//            JasperPrintManager.printReport(jasperPrint, dialogoImpresion);

            java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {
                    try {
                        JasperViewer jviewer = new JasperViewer(jasperPrint, false);
                        jviewer.setTitle("EJECUCION ETAPA - CODIGO DE BARRA");
                        jviewer.setVisible(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarEntregaPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }
}
