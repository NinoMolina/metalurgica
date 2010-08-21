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
import metalsoft.datos.dbobject.EtapadeproduccionPK;
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
    public static long registrarPieza(EtapaDeProduccion etapaDeProduccion,long idmaquina, Connection cn) {
        long result=-1;
        EtapadeproduccionDAO dao=new DAOFactoryImpl().createEtapadeproduccionDAO();
        metalsoft.datos.dbobject.EtapadeproduccionDB etapaDeProduccionDB = null;

        try {
            etapaDeProduccionDB=Parser.parseToEtapaDeProduccionDB(etapaDeProduccion);
            etapaDeProduccionDB.setMaquina(idmaquina);
            
            result=dao.insert(etapaDeProduccionDB, cn);
            etapaDeProduccionDB.setIdetapaproduccion(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long modificarPieza(EtapaDeProduccion etapaDeProduccion,long idEtapaDeProduccion,long idmaquina, Connection cn) {
        long result=-1;
        EtapadeproduccionDAO dao=new DAOFactoryImpl().createEtapadeproduccionDAO();

        EtapadeproduccionDB etapaDeProduccionDB=null;

        EtapadeproduccionPK pk=new EtapadeproduccionPK(idEtapaDeProduccion);
        try {
            etapaDeProduccionDB=Parser.parseToEtapaDeProduccionDB(etapaDeProduccion);
            etapaDeProduccionDB.setMaquina(idmaquina);

            result=dao.update(pk,etapaDeProduccionDB, cn);
        } catch (EtapadeproduccionException ex) {
            Logger.getLogger(AccessEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static long registrarPieza(EtapadeproduccionDB etapaDeProduccionDB, Connection cn) {
        long result=-1;
        EtapadeproduccionDAO dao=new DAOFactoryImpl().createEtapadeproduccionDAO();

        try {
            result=dao.insert(etapaDeProduccionDB, cn);
        } catch (EtapadeproduccionException ex) {
            Logger.getLogger(AccessPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long modificarPieza(EtapadeproduccionDB etapaDeProduccionDB, EtapadeproduccionPK pk, Connection cn) {
        long result=-1;
        EtapadeproduccionDAO dao=new DAOFactoryImpl().createEtapadeproduccionDAO();
        try {
            result=dao.update(pk,etapaDeProduccionDB, cn);
        } catch (EtapadeproduccionException ex) {
            Logger.getLogger(AccessCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
