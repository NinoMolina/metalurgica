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
import metalsoft.datos.dbobject.ClientePKDB;
import metalsoft.datos.exception.ClienteException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.ClienteDAO;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.RolJpaController;
import metalsoft.datos.jpa.controller.UsuarioJpaController;
import metalsoft.datos.jpa.controller.UsuarioxrolJpaController;
import metalsoft.datos.jpa.entity.Usuario;
import metalsoft.datos.jpa.entity.Usuarioxrol;
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
        UsuarioJpaController con=new UsuarioJpaController(JpaUtil.getEntityManagerFactory());
        UsuarioxrolJpaController conUXR=new UsuarioxrolJpaController(JpaUtil.getEntityManagerFactory());
        RolJpaController conRol=new RolJpaController(JpaUtil.getEntityManagerFactory());
        Usuario user=new Usuario();
        Usuarioxrol uxr=new Usuarioxrol();
        
        try {
            clienteDB=Parser.parseToClienteDB(cliente);
            clienteDB.setCondicioniva(idCondIva);
            clienteDB.setDomicilio(idDomicilio);
            clienteDB.setEstado(idEstado);
            clienteDB.setPrioridad(idPrioridad);
            clienteDB.setResponsable(idResponsable);
            user.setClave(clienteDB.getCuit());
            user.setUsuario(clienteDB.getRazonsocial());
            con.create(user);
            clienteDB.setUsuario(user.getIdusuario());
            uxr.setRol(conRol.findRol(8L));
            uxr.setUsuario(user);
            conUXR.create(uxr);
            
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

        ClientePKDB pk=new ClientePKDB(idCliente);
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

    public static long modificarCliente(ClienteDB clienteDB, ClientePKDB pk, Connection cn) {
        long result=-1;
        ClienteDAO dao=new DAOFactoryImpl().createClienteDAO();
        try {
            result=dao.update(pk,clienteDB, cn);
        } catch (ClienteException ex) {
            Logger.getLogger(AccessCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static ClienteDB[] findByRazonsocialILIKE(String valor, Connection cn) {
        ClienteDB[] x=null;
        ClienteDAO dao=new DAOFactoryImpl().createClienteDAO();
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            x = dao.findExecutingUserWhere("razonsocial ILIKE '"+valor+"%'", sqlParams, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
    public static ClienteDB findByIdCliente(long id, Connection cn) {
        ClienteDAO dao=new DAOFactoryImpl().createClienteDAO();
        ClienteDB db=null;
        try {
            db=dao.findByIdcliente(id, cn)[0];
        } catch (Exception ex) {
            Logger.getLogger(AccessCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }
}
