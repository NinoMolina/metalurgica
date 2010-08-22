/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JList;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.EtapadeproduccionDB;
import metalsoft.datos.dbobject.MaquinaDB;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.MaquinaDAO;
import metalsoft.negocio.access.AccessEtapaDeProduccion;
import metalsoft.negocio.access.AccessMaquina;
import metalsoft.negocio.ventas.EtapaDeProduccion;
import metalsoft.util.Combo;
import metalsoft.util.ItemCombo;


/**
 *
 * @author Vicky
 */
public class GestorEtapaDeProduccion {

    public EtapadeproduccionDB[] buscarConILIKE(String valor) {
        Connection cn=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return AccessEtapaDeProduccion.findByNombreILIKE(valor,cn);
    }


    public long guardarEtapaDeProduccion(EtapaDeProduccion etapaDeProduccion,String idMaquina)
    {
        PostgreSQLManager pg=null;
        Connection cn=null;
        pg=new PostgreSQLManager();
        long result=-1;

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result=etapaDeProduccion.guardarEtapaDeProduccion(etapaDeProduccion,Long.parseLong(idMaquina), cn);
            cn.commit();
        } catch (Exception ex) {
            Logger.getLogger(GestorEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    public long modificarEtapaDeProduccion(EtapaDeProduccion etapaDeProduccion, long idEtapaDeProduccion,String idMaquina)
    {
        PostgreSQLManager pg=null;
        Connection cn=null;
        pg=new PostgreSQLManager();
        long result=-1;

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result=etapaDeProduccion.modificarEtapaDeProduccion(etapaDeProduccion, idEtapaDeProduccion,Long.parseLong(idMaquina), cn);
            cn.commit();
        } catch (Exception ex) {
            Logger.getLogger(GestorEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    public void obtenerMaquinas(JComboBox combo) {
        PostgreSQLManager pg=null;
        Connection cn=null;
        MaquinaDB[] maquinas=null;
        try {
            pg=new PostgreSQLManager();
            cn=pg.concectGetCn();

            maquinas=AccessMaquina.findAll(cn);

            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<maquinas.length;i++)
            {
                Combo.cargarCombo(combo,String.valueOf(maquinas[i].getIdmaquina()),maquinas[i].getNombre());
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

   
}
