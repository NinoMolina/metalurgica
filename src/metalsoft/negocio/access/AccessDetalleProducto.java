/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.DetalleproductoDB;
import metalsoft.datos.dbobject.DetalleproductoPKDB;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.DetalleproductoDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.ventas.DetalleProducto;


/**
 *
 * @author Nino
 */
public class AccessDetalleProducto {

    public static long insert(DetalleProducto x, long idProd, long idPieza, Connection cn) {
        long result=-1;
        DetalleproductoDAO dao=new DAOFactoryImpl().createDetalleproductoDAO();
        DetalleproductoDB detalleDB = null;

        try {
            detalleDB=Parser.parseToDetalleProductoDB(x);
            detalleDB.setIdproducto(idProd);
            detalleDB.setPieza(idPieza);
            result=dao.insert(detalleDB, cn);
            detalleDB.setIddetalle(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static int delete(long idDetalle, Connection cn) {
        long result=-1;
        DetalleproductoDAO dao=new DAOFactoryImpl().createDetalleproductoDAO();
        try {
            
            result=dao.delete(new DetalleproductoPKDB(idDetalle), cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (int)result;
    }

    public static int update(DetalleProducto dp, long idProd, long idDet, long idPieza, Connection cn) {
        int result=-1;
        DetalleproductoDAO dao=new DAOFactoryImpl().createDetalleproductoDAO();
        DetalleproductoDB db=null;
        try {
            db=Parser.parseToDetalleProductoDB(dp);
            db.setIddetalle(idDet);
            db.setIdproducto(idProd);
            db.setPieza(idPieza);
            result=dao.update(new DetalleproductoPKDB(idDet),db, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
