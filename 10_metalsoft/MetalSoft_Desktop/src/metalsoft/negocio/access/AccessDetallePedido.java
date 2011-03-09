/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.DetallepedidoDB;
import metalsoft.datos.factory.DAOFactoryCreater;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.DetallepedidoDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.ventas.DetallePedido;

/**
 *
 * @author Nino
 */
public class AccessDetallePedido {

    public static long insert(DetallePedido x, long idPed, long idProd, Connection cn) {
        long result=-1;
        DetallepedidoDAO dao=new DAOFactoryImpl().createDetallepedidoDAO();
        DetallepedidoDB db = null;

        try {
            db=Parser.parseToDetallePedidoDB(x);
            db.setIdpedido(idPed);
            db.setProducto(idProd);

            result=dao.insert(db, cn);
            db.setIddetalle(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessDetallePedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static DetallepedidoDB[] findByIdPedido(long idPres, Connection cn) {
        DetallepedidoDAO dao=new DAOFactoryCreater().getFactry().createDetallepedidoDAO();
        DetallepedidoDB[] db=null;
        try {
            db=dao.findByIdpedido(idPres, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessFactura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }

}
