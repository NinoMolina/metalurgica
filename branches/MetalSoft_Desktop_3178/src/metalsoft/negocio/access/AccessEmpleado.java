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
import metalsoft.datos.dbobject.DomicilioDB;
import metalsoft.datos.dbobject.EmpleadoDB;
import metalsoft.datos.dbobject.EmpleadoxturnoDB;
import metalsoft.datos.dbobject.EmpleadoxturnoPKDB;
import metalsoft.datos.dbobject.EmpleadoPKDB;
import metalsoft.datos.exception.EmpleadoException;
import metalsoft.datos.exception.EmpleadoxturnoException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.DomicilioDAO;
import metalsoft.datos.idao.EmpleadoDAO;
import metalsoft.datos.idao.EmpleadoxturnoDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.rrhh.Domicilio;
import metalsoft.negocio.rrhh.Empleado;
import java.util.LinkedList;
import java.util.Iterator;
/**
 *
 * @author Vicky
 */
public class AccessEmpleado {

    public static EmpleadoDB[] findByNombreILIKE(String valor, Connection cn) {
        EmpleadoDB[] x = null;
        EmpleadoDAO dao = new DAOFactoryImpl().createEmpleadoDAO();
        Object[] sqlParams = new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            x = dao.findExecutingUserWhere("nombre ILIKE '" + valor + "%'", sqlParams, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }

    public static EmpleadoDB findById(long valor, Connection cn) {
        EmpleadoDB x = null;
        EmpleadoDAO dao = new DAOFactoryImpl().createEmpleadoDAO();
        try {
            x = dao.findByIdempleado(valor, cn)[0];
        } catch (Exception ex) {
            Logger.getLogger(AccessEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }

    public static long insert(Empleado empleado, LinkedList idturno, long idcategoria, long idusuario, long idcargo, long iddomicilio, long idtipodoc, Connection cn) {
        long result = -1;
        long resultdom = -1;
        EmpleadoDAO dao = new DAOFactoryImpl().createEmpleadoDAO();
        DomicilioDAO daodom=new DAOFactoryImpl().createDomicilioDAO();
        metalsoft.datos.dbobject.EmpleadoDB empleadoDB = null;
        DomicilioDB dom=null;

        try {

            empleadoDB = Parser.parseToEmpleadoDB(empleado);
            empleadoDB.setCategoria(idcategoria);
            empleadoDB.setUsuario(idusuario);
            empleadoDB.setCargo(idcargo);
            empleadoDB.setTipodocumento(idtipodoc);
            empleadoDB.setDomicilio(iddomicilio);

            result = dao.insert(empleadoDB, cn);
            empleadoDB.setIdempleado(result);


            
            Iterator it=idturno.iterator();
            while(it.hasNext()){
                EmpleadoxturnoDAO daoturnos = new DAOFactoryImpl().createEmpleadoxturnoDAO();
                EmpleadoxturnoDB ext = new EmpleadoxturnoDB();
                ext.setIdempleado(empleadoDB.getIdempleado());
                ext.setIdturno(Integer.parseInt(String.valueOf(it.next())));
                result = daoturnos.insert(ext, cn);
            }
        } catch (Exception ex) {
            Logger.getLogger(AccessEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long update(Empleado empleado, LinkedList idturno, long idempleado, long idcategoria, long idusuario, long idcargo, long iddomicilio, long idtipodoc, Connection cn) {
        long result = -1;
        EmpleadoDAO dao = new DAOFactoryImpl().createEmpleadoDAO();

        EmpleadoDB empleadoDB = null;

        EmpleadoPKDB pk = new EmpleadoPKDB(idempleado);
        try {
            empleadoDB = Parser.parseToEmpleadoDB(empleado);
            empleadoDB.setCategoria(idcategoria);
            empleadoDB.setUsuario(idusuario);
            empleadoDB.setDomicilio(iddomicilio);
            empleadoDB.setCargo(idcargo);
            empleadoDB.setTipodocumento(idtipodoc);

            result = dao.update(pk, empleadoDB, cn);

            EmpleadoxturnoDAO daoturnos = new DAOFactoryImpl().createEmpleadoxturnoDAO();
            try {
                EmpleadoxturnoDB empleadoxturnoDB = null;
                EmpleadoxturnoDB[] ext = daoturnos.findByIdempleado(idempleado, cn);
                for (int i = 0; i < ext.length; i++) {
                    ///ARREGLAR BIEN EL TEMA DE MODIFICAR EL TURNO
                    empleadoxturnoDB.setIdturno(ext[i].getIdturno());
                    EmpleadoxturnoPKDB pkext = new EmpleadoxturnoPKDB(ext[i].getIdempleado(),ext[i].getIdturno());

                    result = daoturnos.update(pkext, empleadoxturnoDB, cn);
                }
            } catch (EmpleadoxturnoException ex) {
                Logger.getLogger(AccessEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (EmpleadoException ex) {
            Logger.getLogger(AccessEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long delete(long id, Connection cn) {
        long result = -1;
        EmpleadoDAO dao = new DAOFactoryImpl().createEmpleadoDAO();

        EmpleadoPKDB pk = new EmpleadoPKDB(id);
        try {
            result = dao.delete(pk, cn);
        } catch (EmpleadoException ex) {
            Logger.getLogger(AccessEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
