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
import metalsoft.datos.dbobject.EtapadeproduccionDB;
import metalsoft.datos.dbobject.EtapadeproduccionPKDB;
import metalsoft.datos.exception.EtapadeproduccionException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.EtapadeproduccionDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.ventas.EtapaDeProduccion;
/**
 *
 * @author Vicky
 */
public class AccessEtapaDeProduccion {

    public static EtapadeproduccionDB[] findByNombreILIKE(String valor, Connection cn) {
        EtapadeproduccionDB[] x=null;
        EtapadeproduccionDAO dao=new DAOFactoryImpl().createEtapadeproduccionDAO();
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            x = dao.findExecutingUserWhere("nombre ILIKE '"+valor+"%'", sqlParams, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
    public static EtapadeproduccionDB[] findAll(Connection cn)
    {
        EtapadeproduccionDB[] db=null;
        EtapadeproduccionDAO dao=new DAOFactoryImpl().createEtapadeproduccionDAO();
        try {
            db = dao.findAll(cn);
        } catch (EtapadeproduccionException ex) {
            Logger.getLogger(AccessEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return db;
    }
    public static EtapadeproduccionDB findById(long valor, Connection cn)
    {
        EtapadeproduccionDB x=null;
        EtapadeproduccionDAO dao=new DAOFactoryImpl().createEtapadeproduccionDAO();
        try {
            x = dao.findByIdetapaproduccion(valor, cn)[0];
        } catch (Exception ex) {
            Logger.getLogger(AccessEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
    public static long insert(EtapaDeProduccion etapaDeProduccion,long idmaquina, long idunidadmedida, Connection cn) {
        long result=-1;
        EtapadeproduccionDAO dao=new DAOFactoryImpl().createEtapadeproduccionDAO();
        metalsoft.datos.dbobject.EtapadeproduccionDB etapaDeProduccionDB = null;

        try {
            etapaDeProduccionDB=Parser.parseToEtapaDeProduccionDB(etapaDeProduccion);
            etapaDeProduccionDB.setTipomaquina(idmaquina);
            etapaDeProduccionDB.setUnidadmedida(idunidadmedida);

            
            result=dao.insert(etapaDeProduccionDB, cn);
            etapaDeProduccionDB.setIdetapaproduccion(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long update(EtapaDeProduccion etapaDeProduccion,long idEtapaDeProduccion,long idmaquina, long idUnidadMedida, Connection cn) {
        long result=-1;
        EtapadeproduccionDAO dao=new DAOFactoryImpl().createEtapadeproduccionDAO();

        EtapadeproduccionDB etapaDeProduccionDB=null;

        EtapadeproduccionPKDB pk=new EtapadeproduccionPKDB(idEtapaDeProduccion);
        try {
            etapaDeProduccionDB=Parser.parseToEtapaDeProduccionDB(etapaDeProduccion);
            etapaDeProduccionDB.setTipomaquina(idmaquina);
            etapaDeProduccionDB.setUnidadmedida(idUnidadMedida);

            result=dao.update(pk,etapaDeProduccionDB, cn);
        } catch (EtapadeproduccionException ex) {
            Logger.getLogger(AccessEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static long delete(long id, Connection cn)
    {
        long result=-1;
        EtapadeproduccionDAO dao=new DAOFactoryImpl().createEtapadeproduccionDAO();

        EtapadeproduccionPKDB pk=new EtapadeproduccionPKDB(id);
        try {
            result=dao.delete(pk, cn);
        } catch (EtapadeproduccionException ex) {
            Logger.getLogger(AccessEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
