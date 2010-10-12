/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import com.sun.org.apache.bcel.internal.util.JavaWrapper;
import dao.ServicioDao;
import dao.ServicioDaoPresupuestoImpl;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.negocio.access.AccessViews;
import pojos.Presupuesto;

/**
 *
 * @author Nino
 */
public class GestorRegistrarPlanificacionProduccion {

    public GestorRegistrarPlanificacionProduccion() {
    }

    public LinkedList<ViewPedidoNoPlanificado> buscarPedidosNoPlanificados() {
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        LinkedList<ViewPedidoNoPlanificado> list=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.allPedidosNoPlanificados(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorRegistrarPlanificacionProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorRegistrarPlanificacionProduccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public Presupuesto buscarPresupuesto(long idPresupuesto) {
        ServicioDao<Presupuesto> dao=new ServicioDaoPresupuestoImpl<Presupuesto>();
        return dao.findById(idPresupuesto, Presupuesto.class.getSimpleName());
    }

}
