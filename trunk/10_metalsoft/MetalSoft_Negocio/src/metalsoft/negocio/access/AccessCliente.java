/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.ClienteDB;
import metalsoft.datos.dbobject.ClientePK;
import metalsoft.datos.exception.ClienteException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.ClienteDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.ventas.Cliente;

/**
 *
 * @author Nino
 */
public class AccessCliente {

    public static long registrarCliente(Cliente cliente, long idResponsable, long idDomicilio, long idEstado, long idCondIva, long idPrioridad, Connection cn) {
        long result=-1;
        ClienteDAO dao=new DAOFactoryImpl().createClienteDAO();
        ClienteDB clienteDB = null;

        try {
            clienteDB=Parser.parseToClienteDB(cliente);
            clienteDB.setCondicioniva(idCondIva);
            clienteDB.setDomicilio(idDomicilio);
            clienteDB.setEstado(idEstado);
            clienteDB.setPrioridad(idPrioridad);
            clienteDB.setResponsable(idResponsable);
            clienteDB.setUsuario(1);
            
            result=dao.insert(clienteDB, cn);
            clienteDB.setIdcliente(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public static long modificarCliente(Cliente cliente,long idCliente, long idResponsable, long idDomicilio, long idEstado, long idCondIva, long idPrioridad, Connection cn) {
        long result=-1;
        ClienteDAO dao=new DAOFactoryImpl().createClienteDAO();

        ClienteDB clienteDB=null;

        ClientePK pk=new ClientePK(idCliente);
        try {
            clienteDB=Parser.parseToClienteDB(cliente);
            clienteDB.setCondicioniva(idCondIva);
            clienteDB.setDomicilio(idDomicilio);
            clienteDB.setEstado(idEstado);
            clienteDB.setPrioridad(idPrioridad);
            clienteDB.setResponsable(idResponsable);
            //deberia autogenerar un usario y contraseña
            clienteDB.setUsuario(1);

            result=dao.update(pk,clienteDB, cn);
        } catch (ClienteException ex) {
            Logger.getLogger(AccessCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static long registrarCliente(ClienteDB clienteDB, Connection cn) {
        long result=-1;
        ClienteDAO dao=new DAOFactoryImpl().createClienteDAO();

        try {
            result=dao.insert(clienteDB, cn);
        } catch (ClienteException ex) {
            Logger.getLogger(AccessCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long modificarCliente(ClienteDB clienteDB, ClientePK pk, Connection cn) {
        long result=-1;
        ClienteDAO dao=new DAOFactoryImpl().createClienteDAO();
        try {
            result=dao.update(pk,clienteDB, cn);
        } catch (ClienteException ex) {
            Logger.getLogger(AccessCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
