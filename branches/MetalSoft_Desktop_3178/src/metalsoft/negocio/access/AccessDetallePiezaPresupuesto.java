/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.DetallepiezapresupuestoDB;
import metalsoft.datos.exception.DetallepiezapresupuestoException;
import metalsoft.datos.factory.DAOFactoryCreater;
import metalsoft.datos.idao.DetallepiezapresupuestoDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.ventas.DetallePiezaPresupuesto;

/**
 *
 * @author Nino
 */
public class AccessDetallePiezaPresupuesto {

    public static long insert(DetallePiezaPresupuesto dpp,long idDetProdPres,long idEtapa,Connection cn)
    {
        DetallepiezapresupuestoDAO dao=new DAOFactoryCreater().getFactry().createDetallepiezapresupuestoDAO();
        DetallepiezapresupuestoDB db=null;
        long result=-1;
        try {
            db=Parser.parseToDetallepiezapresupuestoDB(dpp);
            db.setIddetalleproductopresupuesto(idDetProdPres);
            db.setIdetapa(idEtapa);
            result=dao.insert(db, cn);
        } catch (DetallepiezapresupuestoException ex) {
            Logger.getLogger(AccessPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
