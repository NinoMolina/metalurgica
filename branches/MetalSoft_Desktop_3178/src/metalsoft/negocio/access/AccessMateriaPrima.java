/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import metalsoft.datos.dbobject.MateriaprimaDB;
import metalsoft.datos.idao.MateriaprimaDAO;
import metalsoft.negocio.almacenamiento.MateriaPrima;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.MateriaprimaPKDB;
import metalsoft.datos.exception.MateriaprimaException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.negocio.gestores.Parser;

/**
 *
 * @author Vicky
 */
public class AccessMateriaPrima {

    public static long delete(long id, Connection cn) {
        long result=-1;
        MateriaprimaDAO dao=new DAOFactoryImpl().createMateriaprimaDAO();

        MateriaprimaPKDB pk=new MateriaprimaPKDB(id);
        try {
            result=dao.delete(pk, cn);
        } catch (MateriaprimaException ex) {
            Logger.getLogger(AccessMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static MateriaprimaDB findById(long valor, Connection cn) {
        MateriaprimaDB x=null;
        MateriaprimaDAO dao=new DAOFactoryImpl().createMateriaprimaDAO();
        try {
            x = dao.findByIdmateriaprima(valor, cn)[0];
        } catch (Exception ex) {
            Logger.getLogger(AccessMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }

    public static MateriaprimaDB[] findByNombreILIKE(String valor, Connection cn) {
        MateriaprimaDB[] x=null;
        MateriaprimaDAO dao=new DAOFactoryImpl().createMateriaprimaDAO();
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            x = dao.findExecutingUserWhere("nombre ILIKE '"+valor+"%'", sqlParams, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }

    public static long insert(MateriaPrima materiaPrima, long idTipoMaterial, long idUnidadMedida,long idCodBarra, Connection cn) {
        long result=-1;
        MateriaprimaDAO dao=new DAOFactoryImpl().createMateriaprimaDAO();
        MateriaprimaDB materiaPrimaDB = null;

        try {
            materiaPrimaDB=Parser.parseToMateriaPrimaDB(materiaPrima);
            materiaPrimaDB.setTipomaterial(idTipoMaterial);
            materiaPrimaDB.setUnidaddemedida(idUnidadMedida);
            materiaPrimaDB.setCodbarra(idCodBarra);

            result=dao.insert(materiaPrimaDB, cn);
            materiaPrimaDB.setIdmateriaprima(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long update(MateriaPrima materiaPrima, long idMateriaPrima, long idTipoMaterial, long idUnidadMedida, long idCodBarra, Connection cn) {
        long result=-1;
        MateriaprimaDAO dao=new DAOFactoryImpl().createMateriaprimaDAO();

        MateriaprimaDB materiaPrimaDB=null;

        MateriaprimaPKDB pk=new MateriaprimaPKDB(idMateriaPrima);
        try {
            materiaPrimaDB=Parser.parseToMateriaPrimaDB(materiaPrima);
            materiaPrimaDB.setTipomaterial(idTipoMaterial);
            materiaPrimaDB.setUnidaddemedida(idUnidadMedida);
            materiaPrimaDB.setCodbarra(idCodBarra);

            result=dao.update(pk,materiaPrimaDB, cn);
        } catch (MateriaprimaException ex) {
            Logger.getLogger(AccessMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static long updateMateriaPrimaDB(metalsoft.datos.dbobject.MateriaprimaDB materiaPrima, Connection cn) {
        long result=-1;
        MateriaprimaDAO dao=new DAOFactoryImpl().createMateriaprimaDAO();

        MateriaprimaPKDB pk=new MateriaprimaPKDB(materiaPrima.getIdmateriaprima());
        try {
            result=dao.update(pk,materiaPrima, cn);
        } catch (MateriaprimaException ex) {
            Logger.getLogger(AccessMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
