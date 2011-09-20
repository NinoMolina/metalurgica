/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.util.*;
import java.sql.Connection;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.*;

/**
 *
 * @author Lorreine Prescott
 */
public class GestorReportes {


    public GestorReportes(){

    }

    public void ReporteClientes(){

        String sourceFile = "D:\\rpt\\reporteClientesMetalsoft.jasper";

        PostgreSQLManager pg = new PostgreSQLManager();
        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();

            masterReport = (JasperReport) JRLoader.loadObject(sourceFile);

            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("Reporte Clientes");
            jviewer.setVisible(true);


        } catch (Exception ex) {
            Logger.getLogger(GestorReportes.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void ReporteClientesMorosos() {

        String sourceFile = "D:\\rpt\\reporteClientesMorosos.jasper";

        PostgreSQLManager pg = new PostgreSQLManager();
        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();

            masterReport = (JasperReport) JRLoader.loadObject(sourceFile);

            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("Reporte Clientes Morosos");
            jviewer.setVisible(true);


        } catch (Exception ex) {
            Logger.getLogger(GestorReportes.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    public void ReportePedidos(Date fechaDesde, Date fechaHasta) {

        String sourceFile = "D:\\rpt\\reportePedidos.jasper";

        PostgreSQLManager pg = new PostgreSQLManager();
        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();

            masterReport = (JasperReport) JRLoader.loadObject(sourceFile);

            param.put("FECHA_DESDE", (fechaDesde));
            param.put("FECHA_HASTA",(fechaHasta));

            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("Reporte Pedidos");
            jviewer.setVisible(true);


        } catch (Exception ex) {
            Logger.getLogger(GestorReportes.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    public void ReporteProveedores(Date fechaDesde, Date fechaHasta) {

        String sourceFile = "D:\\rpt\\reporteReclamoProveedores.jasper";

        PostgreSQLManager pg = new PostgreSQLManager();
        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();

            masterReport = (JasperReport) JRLoader.loadObject(sourceFile);

            param.put("FECHA_DESDE", (fechaDesde));
            param.put("FECHA_HASTA",(fechaHasta));

            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("Reporte Proveedores");
            jviewer.setVisible(true);


        } catch (Exception ex) {
            Logger.getLogger(GestorReportes.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }

    public void ReporteEmpresasMetalurgicas(Date fechaDesde, Date fechaHasta) {

   String sourceFile = "D:\\rpt\\reporteReclamoEmpresasMetalurgicas.jasper";

        PostgreSQLManager pg = new PostgreSQLManager();
        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();

            masterReport = (JasperReport) JRLoader.loadObject(sourceFile);

            param.put("FECHA_DESDE", (fechaDesde));
            param.put("FECHA_HASTA",(fechaHasta));

            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("Reporte Empresas Metalúrgicas");
            jviewer.setVisible(true);


        } catch (Exception ex) {
            Logger.getLogger(GestorReportes.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorReportes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}