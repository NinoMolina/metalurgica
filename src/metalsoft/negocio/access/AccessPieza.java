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
import metalsoft.datos.dbobject.PiezaDB;
import metalsoft.datos.dbobject.PiezaPKDB;
import metalsoft.datos.exception.PiezaException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.PiezaDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.ventas.Pieza;
/**
 *
 * @author Vicky
 */
public class AccessPieza {

    public static long insert(Pieza pieza, String nombre, long idTipoMaterial, Double alto, Double ancho, Double largo, long idmateriaPrima, long idMatriz, Connection cn) {
        long result=-1;
        PiezaDAO dao=new DAOFactoryImpl().createPiezaDAO();
        PiezaDB piezaDB = null;

        try {
            piezaDB=Parser.parseToPiezaDB(pieza);
            piezaDB.setAlto(alto);
            piezaDB.setAncho(ancho);
            piezaDB.setLargo(largo);
            piezaDB.setMateriaprima(idmateriaPrima);
            piezaDB.setMatriz(idMatriz);
            piezaDB.setNombre(nombre);
            piezaDB.setTipomaterial(idTipoMaterial);
            

            result=dao.insert(piezaDB, cn);
            piezaDB.setIdpieza(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long update(Pieza pieza,long idPieza, String nombre, long idTipoMaterial, Double alto, Double ancho, Double largo, long idmateriaPrima, long idMatriz, Connection cn) {
        long result=-1;
        PiezaDAO dao=new DAOFactoryImpl().createPiezaDAO();

        PiezaDB piezaDB=null;

        PiezaPKDB pk=new PiezaPKDB(idPieza);
        try {
            piezaDB=Parser.parseToPiezaDB(pieza);
            piezaDB.setNombre(nombre);
            piezaDB.setAlto(alto);
            piezaDB.setAncho(ancho);
            piezaDB.setLargo(largo);
            piezaDB.setMateriaprima(idmateriaPrima);
            piezaDB.setMatriz(idMatriz);
            piezaDB.setTipomaterial(idTipoMaterial);
            
            result=dao.update(pk,piezaDB, cn);
        } catch (PiezaException ex) {
            Logger.getLogger(AccessPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static long insert(PiezaDB piezaDB, Connection cn) {
        long result=-1;
        PiezaDAO dao=new DAOFactoryImpl().createPiezaDAO();

        try {
            result=dao.insert(piezaDB, cn);
        } catch (PiezaException ex) {
            Logger.getLogger(AccessPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long update(PiezaDB piezaDB, PiezaPKDB pk, Connection cn) {
        long result=-1;
        PiezaDAO dao=new DAOFactoryImpl().createPiezaDAO();
        try {
            result=dao.update(pk,piezaDB, cn);
        } catch (PiezaException ex) {
            Logger.getLogger(AccessCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static PiezaDB findByIdpieza(long idPieza,Connection cn) {

        PiezaDAO dao=new DAOFactoryImpl().createPiezaDAO();
        PiezaDB db=null;
        try {
            db=dao.findByIdpieza(idPieza, cn)[0];
        } catch (PiezaException ex) {
            Logger.getLogger(AccessPieza.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }
}
