/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.DetallepiezacalidadpresupuestoDB;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.DetallepiezacalidadpresupuestoDAO;
import metalsoft.negocio.calidad.DetallePiezaCalidadPresupuesto;
import metalsoft.negocio.gestores.Parser;

/**
 *
 * @author Nino
 */
public class AccessDetallePiezaCalidadPresupuesto {

    public static long insert(DetallePiezaCalidadPresupuesto dpcp,long idDetProdPres,long idProCalidad,Connection cn)
    {
        long result=-1;
        DetallepiezacalidadpresupuestoDAO dao=new DAOFactoryImpl().createDetallepiezacalidadpresupuestoDAO();
        DetallepiezacalidadpresupuestoDB db = null;

        try {
            db=Parser.parseToDetallepiezacalidadpresupuestoDB(dpcp);
            db.setIddetalleproductopresupuesto(idDetProdPres);
            db.setIdprocesocalidad(idProCalidad);
            result=dao.insert(db, cn);
            db.setIddetalle(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessDetallePiezaCalidadPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
