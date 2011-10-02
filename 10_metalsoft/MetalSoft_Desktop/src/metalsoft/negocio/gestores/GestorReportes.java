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

        String sourceFile = "L:\\rpt\\reporteClientesMetalsoft.jasper";

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

        String sourceFile = "L:\\rpt\\reporteClientesMorosos.jasper";

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

        String sourceFile = "L:\\rpt\\reportePedidos.jasper";

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

        String sourceFile = "L:\\rpt\\reporteReclamoProveedores.jasper";

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

    String sourceFile = "L:\\rpt\\reporteReclamoEmpresasMetalurgicas.jasper";

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


   
    

    public void ReporteAusentismo(Date fecha) {

        String sourceFile = "L:\\rpt\\reporteAusentismo.jasper";

        PostgreSQLManager pg = new PostgreSQLManager();
        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();

            masterReport = (JasperReport) JRLoader.loadObject(sourceFile);

            param.put("FECHA", (fecha));


            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("Reporte Ausentismo");
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

    public void ReportePedidosXEstados(Date fechaDesde, Date fechaHasta) {

        String sourceFile = "L:\\rpt\\reportePedidosXEstado.jasper";

        PostgreSQLManager pg = new PostgreSQLManager();
        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();

            masterReport = (JasperReport) JRLoader.loadObject(sourceFile);

            param.put("Fecha_Desde", (fechaDesde));
            param.put("Fecha_Hasta",(fechaHasta));

            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("Reporte Estados por Estados");
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

    public void ReporteMateriasPrimas() {

         String sourceFile = "L:\\rpt\\reporteMateriasPrimas.jasper";

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
            jviewer.setTitle("Reporte Materias Primas en Stock");
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

 

    public void ReporteMateriaPrimaXProveedor(String materiaprima) {

         String sourceFile = "L:\\rpt\\reporteMateriaPrimaXProveedor.jasper";

        PostgreSQLManager pg = new PostgreSQLManager();
        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();

            masterReport = (JasperReport) JRLoader.loadObject(sourceFile);

            param.put("MATERIA_PRIMA", (materiaprima));


            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("Reporte Materia Prima por Proveedor");
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

    public void ReporteVolumenReclamoXProveedor() {

        String sourceFile = "L:\\rpt\\reporteVolumenReclamosXProveedor.jasper";

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
            jviewer.setTitle("Reporte Cantidad de Reclamos por Proveedor");
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

    public void ReporteProveedoresMayorNroCompras(Date fechaDesde, Date fechaHasta) {

         String sourceFile = "L:\\rpt\\reporteProveedorresMayorNroCompras.jasper";

        PostgreSQLManager pg = new PostgreSQLManager();
        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();

            masterReport = (JasperReport) JRLoader.loadObject(sourceFile);

            param.put("Fecha_Desde", (fechaDesde));
            param.put("Fecha_Hasta",(fechaHasta));

            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("Reporte Proveedores con mayor volumen de Compras");
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

    public void ReporteCobros(Date fechaDesde, Date fechaHasta) {

        String sourceFile = "L:\\rpt\\reporteCobros.jasper";

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
            jviewer.setTitle("Reporte Cobros");
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

    public void ReporteCobrosXFormaPago(Date fechaDesde, Date fechaHasta) {

         String sourceFile = "L:\\rpt\\reporteCobrosXFormaPago.jasper";

        PostgreSQLManager pg = new PostgreSQLManager();
        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();

            masterReport = (JasperReport) JRLoader.loadObject(sourceFile);

            param.put("Fecha_Desde", (fechaDesde));
            param.put("Fecha_Hasta",(fechaHasta));

            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("Reporte Cobros por Forma de Pago");
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

    public void ReporteVolumenReclamoXEmpresaMetalurgica() {

         String sourceFile = "L:\\rpt\\reporteVolumenReclamosXEmpresaM.jasper";

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
            jviewer.setTitle("Reporte Cantidad de Reclamos por Empresa Metalúrgica");
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

    public void ReporteEmpresasMetalurgicasMayorNroCompras(Date fechaDesde, Date fechaHasta) {

        String sourceFile = "L:\\rpt\\reporteEmpresasMetalurgicasMayorNroTrabajos.jasper";

        PostgreSQLManager pg = new PostgreSQLManager();
        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();

            masterReport = (JasperReport) JRLoader.loadObject(sourceFile);

            param.put("Fecha_Desde", (fechaDesde));
            param.put("Fecha_Hasta",(fechaHasta));

            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("Reporte Empresas Metalúrgicas con mayor volumen de Trabajos");
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

    public void ReporteEmpleadosCompleto() {

        String sourceFile = "L:\\rpt\\reporteEmpleados.jasper";

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
            jviewer.setTitle("Reporte Completo de Empleados");
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

    public void ReporteEmpleadosXCargo() {

        String sourceFile = "L:\\rpt\\reporteEmpleadosXCargo.jasper";

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
            jviewer.setTitle("Reporte de Empleados por Cargo");
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

    public void ReporteEmpleadosXCategoria() {

        String sourceFile = "L:\\rpt\\reporteEmpleadosXCategoria.jasper";

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
            jviewer.setTitle("Reporte de Empleados por Categoría");
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
