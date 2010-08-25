/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.PedidoDB;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.PedidoDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.ventas.Pedido;

/**
 *
 * @author Nino
 */
public class AccessPedido {

    public static long insert(Pedido x,long idCliente,long idEstado,long idPrioridad, Connection cn) {
        long result=-1;
        PedidoDAO dao=new DAOFactoryImpl().createPedidoDAO();
        PedidoDB db = null;

        try {
            db=Parser.parseToPedidoDB(x);
            db.setEstado(idEstado);
            db.setPrioridad(idPrioridad);
            db.setCliente(idCliente);
            result=dao.insert(db, cn);
            db.setIdpedido(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
