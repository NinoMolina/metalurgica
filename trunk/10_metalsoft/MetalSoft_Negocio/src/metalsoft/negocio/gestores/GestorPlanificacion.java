/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.MateriaprimaDB;
import metalsoft.datos.dbobject.Tipomaterial;
import metalsoft.datos.dbobject.UnidadmedidaDB;
import metalsoft.negocio.access.AccessMateriaPrima;
import metalsoft.negocio.access.AccessTipoMaterial;
import metalsoft.negocio.access.AccessUnidadDeMedida;
import metalsoft.negocio.access.AccessViews;
import metalsoft.util.Combo;
import metalsoft.util.ItemCombo;

/**
 *
 * @author Vicky
 */
public class GestorPlanificacion {

    public GestorPlanificacion(){

    }

    public LinkedList<ViewPlanificacion> buscarPlanificacionConRecursosAsignados() {
        PostgreSQLManager pg = new PostgreSQLManager();
        LinkedList<ViewPlanificacion> list = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            list = AccessViews.planificacionConRecursosAsignados(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPlanificacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

}
