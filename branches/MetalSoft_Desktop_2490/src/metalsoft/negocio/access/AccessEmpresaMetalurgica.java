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
import metalsoft.datos.dbobject.EmpresametalurgicaDB;
import metalsoft.datos.dbobject.EmpresametalurgicaPKDB;
import metalsoft.datos.exception.ClienteException;
import metalsoft.datos.exception.EmpresametalurgicaException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.EmpresametalurgicaDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.trabajostercerizados.EmpresaMetalurgica;

/**
 *
 * @author Lorreine Prescott
 */
public class AccessEmpresaMetalurgica {

 public static long registrarEmpresaMetalurgica(EmpresaMetalurgica empresaMetalurgica, long idResponsable, long idDomicilio, long idCondIva, Connection cn) {
        long result=-1;
        EmpresametalurgicaDAO dao=new DAOFactoryImpl().createEmpresametalurgicaDAO();
        EmpresametalurgicaDB db = null;

        try {
            db=Parser.parseToEmpresametalurgicaDB(empresaMetalurgica);
            db.setCondicioniva(idCondIva);
            db.setDomicilio(idDomicilio);
            db.setResponsable(idResponsable);

            result=dao.insert(db, cn);
            db.setIdempresametalurgica(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long modificarEmpresaMetalurgica(EmpresaMetalurgica empresaMetalurgica,long idEmpresaMetalurgica, long idResponsable, long idDomicilio, long idCondIva, Connection cn) {
        long result=-1;
        EmpresametalurgicaDAO dao=new DAOFactoryImpl().createEmpresametalurgicaDAO();

        EmpresametalurgicaDB db=null;

        EmpresametalurgicaPKDB pk=new EmpresametalurgicaPKDB(idEmpresaMetalurgica);
        try {
            db=Parser.parseToEmpresametalurgicaDB(empresaMetalurgica);
            db.setCondicioniva(idCondIva);
            db.setDomicilio(idDomicilio);
            db.setResponsable(idResponsable);

            result=dao.update(pk,db, cn);
        } catch (EmpresametalurgicaException ex) {
            Logger.getLogger(AccessEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static long registrarEmpresaMetalurgica(EmpresametalurgicaDB db, Connection cn) {
        long result=-1;
        EmpresametalurgicaDAO dao=new DAOFactoryImpl().createEmpresametalurgicaDAO ();

        try {
            result=dao.insert(db, cn);
        } catch (EmpresametalurgicaException ex) {
            Logger.getLogger(AccessEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long modificarEmpresaMetalurgica(EmpresametalurgicaDB db, EmpresametalurgicaPKDB pk, Connection cn) {
        long result=-1;
        EmpresametalurgicaDAO dao=new DAOFactoryImpl().createEmpresametalurgicaDAO();
        try {
            result=dao.update(pk,db, cn);
        } catch (EmpresametalurgicaException ex) {
            Logger.getLogger(AccessEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static EmpresametalurgicaDB[] findByRazonsocialILIKE(String valor, Connection cn) {
        EmpresametalurgicaDB[] x=null;
        EmpresametalurgicaDAO dao=new DAOFactoryImpl().createEmpresametalurgicaDAO();
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            x = dao.findExecutingUserWhere("razonsocial ILIKE '"+valor+"%'", sqlParams, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
}
