/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.awt.Dialog.ModalExclusionType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.entity.Detalleremito;
import metalsoft.datos.jpa.entity.Remito;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Vicky
 */
public class GestorRemito {
    public void imprimirRemito(long id) {
//        String sourceFile = "D:\\rpt\\RptRemito.jasper";

        PostgreSQLManager pg = new PostgreSQLManager();
//        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();

            masterReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/metalsoft/reportes/RptRemito.jasper"));

            param.put("ID_PEDIDO", new Long(id));
//            JRResultSetDataSource rsDatparam.put("ID_PEDIDO", new Long(pedidoSeleccionadoDB.getIdpedido()));aSource = new JRResultSetDataSource(rs);
            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);


            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
            jviewer.setTitle("Remito");
            jviewer.setVisible(true);


        } catch (Exception ex) {
            Logger.getLogger(GestorRemito.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRemito.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public List<Detalleremito> buscarDetallesRemitoByRemito(long id){
        List<Detalleremito> list=JpaUtil.getDetalleRemitoByRemito(id);
        return list;
    }
    public List<Remito> buscarRemitosByNroLIKE(String id){
        List<Remito> list=JpaUtil.getRemitosByNroLIKE(id);
        return list;
    }
    public List<Remito> buscarRemitosByFechaEmision(String id){
        List<Remito> list=JpaUtil.getRemitosByFechaEmision(id);
        return list;
    }
}