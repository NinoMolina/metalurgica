/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.DetallepresupuestoDB;
import metalsoft.datos.exception.DetallepresupuestoException;
import metalsoft.datos.factory.DAOFactoryCreater;
import metalsoft.datos.idao.DetallepresupuestoDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.ventas.DetallePresupuesto;

/**
 *
 * @author Nino
 */
public class AccessDetallePresupuesto {

    public static long insert(DetallePresupuesto dp,long idPresupuesto,long idDetallePedido,long idProducto,Connection cn)
    {
        DetallepresupuestoDAO dao=new DAOFactoryCreater().getFactry().createDetallepresupuestoDAO();
        DetallepresupuestoDB db=null;
        long result=-1;
        try {
            db=Parser.parseToDetallepresupuestoDB(dp);
            db.setIdpresupuesto(idPresupuesto);
            db.setIddetallepedido(idDetallePedido);
            db.setIdproducto(idProducto);
            result=dao.insert(db, cn);
        } catch (DetallepresupuestoException ex) {
            Logger.getLogger(AccessPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static DetallepresupuestoDB[] findByIdPresupuesto(long idPres, Connection cn) {
        DetallepresupuestoDAO dao=new DAOFactoryCreater().getFactry().createDetallepresupuestoDAO();
        DetallepresupuestoDB[] db=null;
        try {
            db=dao.findByIdpresupuesto(idPres, cn);
        } catch (DetallepresupuestoException ex) {
            Logger.getLogger(AccessPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }
}
