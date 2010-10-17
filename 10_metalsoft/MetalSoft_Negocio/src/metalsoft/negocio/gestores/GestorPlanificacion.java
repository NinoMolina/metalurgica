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
    public void obtenerTipoMateriales(JComboBox combo) {
        PostgreSQLManager pg=null;
        Connection cn=null;
        Tipomaterial[] tipoMateriales=null;
        try {
            pg=new PostgreSQLManager();
            cn=pg.concectGetCn();

            tipoMateriales=AccessTipoMaterial.findAll(cn);

            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<tipoMateriales.length;i++)
            {
                Combo.cargarCombo(combo,String.valueOf(tipoMateriales[i].getIdtipomaterial()),tipoMateriales[i].getNombre());
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void obternerUnidadMedida(JComboBox combo) {
        PostgreSQLManager pg=null;
        Connection cn=null;
        UnidadmedidaDB[] datos=null;
        try {
            pg=new PostgreSQLManager();
            cn=pg.concectGetCn();

            datos=AccessUnidadDeMedida.findAll(cn);

            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<datos.length;i++)
            {
                Combo.cargarCombo(combo,String.valueOf(datos[i].getIdunidadmedida()),datos[i].getNombre());
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public MateriaprimaDB buscarMateriaPrimaPorId(long valor) {
        Connection cn=null;
        PostgreSQLManager pg=null;
        MateriaprimaDB db=null;
        try {
            pg=new PostgreSQLManager();
            cn = pg.concectGetCn();
            db=AccessMateriaPrima.findById(valor,cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return db;
    }
}
