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
import metalsoft.datos.jpa.controller.DetallepedidoJpaController;
import metalsoft.datos.jpa.controller.FacturaJpaController;
import metalsoft.datos.jpa.controller.PedidoJpaController;
import metalsoft.datos.jpa.entity.Detallefactura;
import metalsoft.datos.jpa.entity.Detallepedido;
import metalsoft.datos.jpa.entity.Factura;
import metalsoft.datos.jpa.entity.Pedido;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.*;

/**
 *
 * @author Vicky
 */
public class GestorFactura {

    public GestorFactura(){

    }
    public Detallepedido buscarDetallePedido(long id){
        DetallepedidoJpaController con=new DetallepedidoJpaController();
        return con.findDetallepedido(id);
    }
    public List<Factura> buscarFacturasByNroLike(String param){
        List<Factura> list=JpaUtil.getFacturasByNroLIKE(param);
        return list;
    }
    public List<Factura> buscarFacturasByFechaEmision(String param){
        List<Factura> list=JpaUtil.getFacturasByFechaEmision(param);
        return list;
    }
    public List<Factura> buscarFacturasByFechaVto(String param){
        List<Factura> list=JpaUtil.getFacturasByFechaVto(param);
        return list;
    }
    public List<Detallefactura> buscarDetalleFacturaByFactura(long id){
        List<Detallefactura> list=JpaUtil.getDetalleFacturaByFactura(id);
        return list;
    }

//    public static void main(String args[]){
//        imprimirFactura(3L, 12.6);
//    }

    public void imprimirFactura(long id, double monto) {
        String sourceFile = "D:\\rpt\\RptFactura.jasper";

        PostgreSQLManager pg = new PostgreSQLManager();
        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();
            
            masterReport = (JasperReport) JRLoader.loadObject(sourceFile);

            param.put("ID_PEDIDO", new Long(id));
            param.put("MONTO", new Double(monto));
//            JRResultSetDataSource rsDatparam.put("ID_PEDIDO", new Long(pedidoSeleccionadoDB.getIdpedido()));aSource = new JRResultSetDataSource(rs);
            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);


            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setTitle("Factura");
            jviewer.setVisible(true);


        } catch (Exception ex) {
            Logger.getLogger(GestorFactura.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorFactura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
