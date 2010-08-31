/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.DetalleproductopresupuestoDB;
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
}
