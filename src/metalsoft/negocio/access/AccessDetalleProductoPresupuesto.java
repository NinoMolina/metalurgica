/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.DetalleproductopresupuestoDB;
import metalsoft.datos.dbobject.DetalleproductopresupuestoPKDB;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.DetalleproductopresupuestoDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.ventas.DetalleProductoPresupuesto;

/**
 *
 * @author Nino
 */
public class AccessDetalleProductoPresupuesto {

    public static long insert(DetalleProductoPresupuesto x,long idDetPres,long idPieza,Connection cn)
    {
        long result=-1;
        DetalleproductopresupuestoDAO dao=new DAOFactoryImpl().createDetalleproductopresupuestoDAO();
        DetalleproductopresupuestoDB db = null;

        try {
            db=Parser.parseToDetalleproductopresupuestoDB(x);
            db.setIddetallepresupuesto(idDetPres);
            db.setIdpieza(idPieza);
            result=dao.insert(db, cn);
            db.setIddetalle(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessDetalleProductoPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static DetalleproductopresupuestoDB[] findByIdDetallePresupuesto(long idDetPres, Connection cn) {
        DetalleproductopresupuestoDAO dao=new DAOFactoryImpl().createDetalleproductopresupuestoDAO();
        DetalleproductopresupuestoDB[] db = null;

        try {
            db=dao.findByIddetallepresupuesto(idDetPres, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessDetalleProductoPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }

    public static DetalleproductopresupuestoDB[] findByIdDetalle(long idDet, Connection cn) {
        DetalleproductopresupuestoDAO dao=new DAOFactoryImpl().createDetalleproductopresupuestoDAO();
        DetalleproductopresupuestoDB[] db = null;
        try {
            db=dao.findByIddetalle(idDet, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessDetalleProductoPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }

    public static int update(DetalleproductopresupuestoDB db,Connection cn)
    {
        int result=-1;
        DetalleproductopresupuestoDAO dao=new DAOFactoryImpl().createDetalleproductopresupuestoDAO();
        DetalleproductopresupuestoPKDB pk=new DetalleproductopresupuestoPKDB(db.getIddetalle());
        try {
            result=dao.update(pk,db, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static DetalleproductopresupuestoDB findByIdDetallePresupuestoANDIdPieza(long idDetPres, long idPi, Connection cn) {
        DetalleproductopresupuestoDAO dao=new DAOFactoryImpl().createDetalleproductopresupuestoDAO();
        DetalleproductopresupuestoDB db = null;
        String query="iddetallepresupuesto=? AND idpieza=?";
        Object[] param=new Object[2];
        param[0]=idDetPres;
        param[1]=idPi;
        try {
            db=dao.findExecutingUserWhere(query,param, cn)[0];
        } catch (Exception ex) {
            Logger.getLogger(AccessDetalleProductoPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }
}
