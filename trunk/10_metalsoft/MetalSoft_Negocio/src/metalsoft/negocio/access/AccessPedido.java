/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.PedidoDB;
import metalsoft.datos.dbobject.PedidoPK;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.PedidoDAO;
import metalsoft.negocio.gestores.IdsEstadoPedido;
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

    public static int update(long idPed, long idPres,long idEstado, Connection cn) {
        int result=-1;
        PedidoDAO dao=new DAOFactoryImpl().createPedidoDAO();
        PedidoDB db = null;
        PedidoPK pk=new PedidoPK(idPed);
        try {
            db=findByIdPedido(idPed, cn);
            db.setPresupuesto(idPres);
            db.setEstado(idEstado);
            result=dao.update(pk,db, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int update(long idpedido, long idestado, Connection cn) {
        int result=-1;
        PedidoDAO dao=new DAOFactoryImpl().createPedidoDAO();
        PedidoDB db = null;
        PedidoPK pk=new PedidoPK(idpedido);
        try {
            db=findByIdPedido(idpedido, cn);
            db.setEstado(idestado);
            result=dao.update(pk,db, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int update(PedidoDB db, Connection cn) {
        int result=-1;
        PedidoDAO dao=new DAOFactoryImpl().createPedidoDAO();
        PedidoPK pk=new PedidoPK(db.getIdpedido());
        try {
            result=dao.update(pk,db, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static PedidoDB findByIdPedido(long id,Connection cn)
    {
        PedidoDAO dao=new DAOFactoryImpl().createPedidoDAO();
        PedidoDB db = null;

        try {
            db=dao.findByIdpedido(id, cn)[0];
        } catch (Exception ex) {
            Logger.getLogger(AccessPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }
    public static PedidoDB[] findByIdClienteIdEstado(long idCliente, long idEstado,Connection cn)
    {
        PedidoDAO dao=new DAOFactoryImpl().createPedidoDAO();
        PedidoDB[] db = null;

        try {
            db=dao.findByClienteAndEstado(idCliente, idEstado, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }

}
