/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
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

    public static long insert(Pedido x,long idEstado,long idPrioridad, Connection cn) {
        long result=-1;
        PedidoDAO dao=new DAOFactoryImpl().createPedidoDAO();
        PedidoDB db = null;

        try {
            db=Parser.parseToPedidoDB(x);
            db.setEstado(idEstado);
            result=dao.insert(db, cn);
            db.setIdproducto(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
