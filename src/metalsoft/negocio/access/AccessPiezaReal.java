/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.PiezarealDB;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.PiezarealDAO;
import metalsoft.negocio.produccion.PiezaReal;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.MateriaprimaDB;
import metalsoft.datos.dbobject.PiezarealPKDB;
import metalsoft.datos.exception.PiezarealException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.negocio.gestores.Parser;
/**
 *
 * @author Vicky
 */
public class AccessPiezaReal {
    public static long delete(long id, long idPieza, Connection cn) {
        long result=-1;
        PiezarealDAO dao=new DAOFactoryImpl().createPiezarealDAO();

        PiezarealPKDB pk=new PiezarealPKDB(id, idPieza);
        try {
            result=dao.delete(pk, cn);
        } catch ( PiezarealException ex) {
            Logger.getLogger(AccessPiezaReal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static PiezarealDB findById(long valor, Connection cn) {
        PiezarealDB x=null;
        PiezarealDAO dao=new DAOFactoryImpl().createPiezarealDAO();
        try {
            x = dao.findByIdpiezareal(valor, cn)[0];
        } catch (Exception ex) {
            Logger.getLogger(AccessPiezaReal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }

    public static PiezarealDB[] findByEstado(long valor, Connection cn) {
        PiezarealDB[] x=null;
        PiezarealDAO dao=new DAOFactoryImpl().createPiezarealDAO();

        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            x = dao.findByIdpieza(valor, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessPiezaReal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }

    public static long insert(PiezaReal piezaReal, long idPieza, long idestado,long idCodBarra, Connection cn) {
        long result=-1;
        PiezarealDAO dao=new DAOFactoryImpl().createPiezarealDAO();
        PiezarealDB prDB = new PiezarealDB();

        try {
            prDB.setNropieza((int) piezaReal.getNroPieza());
            prDB.setIdpieza(idPieza);
            prDB.setEstado(idestado);
            prDB.setIdcodbarra(idCodBarra);

            result=dao.insert(prDB, cn);
            prDB.setIdpiezareal(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessPiezaReal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long update(PiezaReal piezaReal,long idPiezaReal, long idPieza, long idestado,long idCodBarra, Connection cn) {
        long result=-1;
        PiezarealDAO dao=new DAOFactoryImpl().createPiezarealDAO();

        PiezarealDB prDB=new PiezarealDB();

        PiezarealPKDB pk=new PiezarealPKDB(idPiezaReal, idPieza);
        try {
            prDB.setNropieza((int) piezaReal.getNroPieza());
            prDB.setIdpieza(idPieza);
            prDB.setEstado(idestado);
            prDB.setIdcodbarra(idCodBarra);

            result=dao.update(pk,prDB, cn);
        } catch (PiezarealException ex) {
            Logger.getLogger(AccessPiezaReal.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
