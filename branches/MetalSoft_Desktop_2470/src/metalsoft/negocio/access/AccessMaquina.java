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
import metalsoft.datos.dbobject.MaquinaDB;
import metalsoft.datos.dbobject.MaquinaPKDB;
import metalsoft.datos.exception.MaquinaException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.MaquinaDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.mantmaquinarias.Maquina;
/**
 *
 * @author Vicky
 */
public class AccessMaquina {

    public static MaquinaDB[] findAll(Connection cn)
    {
        MaquinaDB[] db=null;
        MaquinaDAO dao=new DAOFactoryImpl().createMaquinaDAO();
        try {
            db = dao.findAll(cn);
        } catch (MaquinaException ex) {
            Logger.getLogger(AccessMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }

        return db;
    }
    public static long delete(long id, Connection cn) {
        long result=-1;
        MaquinaDAO dao=new DAOFactoryImpl().createMaquinaDAO();

        MaquinaPKDB pk=new MaquinaPKDB(id);
        try {
            result=dao.delete(pk, cn);
        } catch (MaquinaException ex) {
            Logger.getLogger(AccessMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static MaquinaDB findById(long valor, Connection cn) {
        MaquinaDB x=null;
        MaquinaDAO dao=new DAOFactoryImpl().createMaquinaDAO();
        try {
            x = dao.findByIdmaquina(valor, cn)[0];
        } catch (Exception ex) {
            Logger.getLogger(AccessMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }

    public static MaquinaDB[] findByNombreILIKE(String valor, Connection cn) {
        MaquinaDB[] x=null;
        MaquinaDAO dao=new DAOFactoryImpl().createMaquinaDAO();
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            x = dao.findExecutingUserWhere("nombre ILIKE '"+valor+"%'", sqlParams, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }

    public static long insert(Maquina maquina, long idTipoMaquina, long idUnidadMedida,long idMarca, long idEstado, Connection cn) {
        long result=-1;
        MaquinaDAO dao=new DAOFactoryImpl().createMaquinaDAO();
        MaquinaDB db = null;

        try {
            db=Parser.parseToMaquinaDB(maquina);
            db.setTipomaquina(idTipoMaquina);
            db.setUnidadMedida(idUnidadMedida);
            db.setMarca(idMarca);
            db.setEstado(idEstado);

            result=dao.insert(db, cn);
            db.setIdmaquina(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long update(Maquina maquina, long idMaquina, long idTipoMaquina, long idUnidadMedida,long idMarca, long idEstado, Connection cn) {
        long result=-1;
        MaquinaDAO dao=new DAOFactoryImpl().createMaquinaDAO();

        MaquinaDB db=null;

        MaquinaPKDB pk=new MaquinaPKDB(idMaquina);
        try {
            db=Parser.parseToMaquinaDB(maquina);
            db.setTipomaquina(idTipoMaquina);
            db.setUnidadMedida(idUnidadMedida);
            db.setMarca(idMarca);
            db.setEstado(idEstado);

            result=dao.update(pk,db, cn);
        } catch (MaquinaException ex) {
            Logger.getLogger(AccessMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
