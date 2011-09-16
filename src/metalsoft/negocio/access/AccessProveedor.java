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
import metalsoft.datos.dbobject.ProveedorDB;
import metalsoft.datos.dbobject.ProveedorPKDB;
import metalsoft.datos.exception.ClienteException;
import metalsoft.datos.exception.ProveedorException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.ProveedorDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.compras.Proveedor;

/**
 *
 * @author Vicky
 */
public class AccessProveedor {
    public static long registrarProveedor(Proveedor proveedor, long idResponsable, long idDomicilio, long idCondIva, Connection cn) {
        long result=-1;
        ProveedorDAO dao=new DAOFactoryImpl().createProveedorDAO();
        ProveedorDB db = null;

        try {
            db=Parser.parseToProveedorDB(proveedor);
            db.setCondicion(idCondIva);
            db.setDomicilio(idDomicilio);
            db.setResponsable(idResponsable);

            result=dao.insert(db, cn);
            db.setIdproveedor(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long modificarProveedor(Proveedor proveedor,long idProveedor, long idResponsable, long idDomicilio, long idCondIva, Connection cn) {
        long result=-1;
        ProveedorDAO dao=new DAOFactoryImpl().createProveedorDAO();

        ProveedorDB db=null;

        ProveedorPKDB pk=new ProveedorPKDB(idProveedor);
        try {
            db=Parser.parseToProveedorDB(proveedor);
            db.setCondicion(idCondIva);
            db.setDomicilio(idDomicilio);
            db.setResponsable(idResponsable);

            result=dao.update(pk,db, cn);
        } catch (ProveedorException ex) {
            Logger.getLogger(AccessProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static long registrarProveedor(ProveedorDB db, Connection cn) {
        long result=-1;
        ProveedorDAO dao=new DAOFactoryImpl().createProveedorDAO ();

        try {
            result=dao.insert(db, cn);
        } catch (ProveedorException ex) {
            Logger.getLogger(AccessProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long modificarProveedor(ProveedorDB db, ProveedorPKDB pk, Connection cn) {
        long result=-1;
        ProveedorDAO dao=new DAOFactoryImpl().createProveedorDAO();
        try {
            result=dao.update(pk,db, cn);
        } catch (ProveedorException ex) {
            Logger.getLogger(AccessProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static ProveedorDB[] findByRazonsocialILIKE(String valor, Connection cn) {
        ProveedorDB[] x=null;
        ProveedorDAO dao=new DAOFactoryImpl().createProveedorDAO();
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            x = dao.findExecutingUserWhere("razonsocial ILIKE '"+valor+"%'", sqlParams, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
}
