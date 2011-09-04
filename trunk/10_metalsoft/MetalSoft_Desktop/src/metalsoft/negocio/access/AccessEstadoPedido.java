/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.EstadopedidoDB;
import metalsoft.datos.exception.EstadopedidoException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.EstadopedidoDAO;

/**
 *
 * @author Nino
 */
public class AccessEstadoPedido {

    public static EstadopedidoDB[] findAll(Connection cn) {
        EstadopedidoDB[] vDB=null;
        EstadopedidoDAO dao=new DAOFactoryImpl().createEstadopedidoDAO();
        try {
            vDB = dao.findAll(cn);
        } catch (EstadopedidoException ex) {
            Logger.getLogger(AccessEstadoPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vDB;
    }
}
