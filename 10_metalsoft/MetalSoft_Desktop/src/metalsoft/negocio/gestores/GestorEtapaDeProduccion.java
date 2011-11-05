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
import metalsoft.datos.dbobject.UnidadmedidaDB;
import metalsoft.datos.exception.EtapadeproduccionException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.EtapadeproduccionDAO;
import metalsoft.datos.idao.MaquinaDAO;
import metalsoft.negocio.access.AccessEtapaDeProduccion;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.access.AccessMaquina;
import metalsoft.negocio.access.AccessUnidadDeMedida;
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
        PostgreSQLManager pg=null;
        EtapadeproduccionDB[] db=null;
        try {
            pg=new PostgreSQLManager();
            cn = pg.concectGetCn();
            db=AccessEtapaDeProduccion.findByNombreILIKE(valor,cn);
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
        return db;
    }

    public EtapadeproduccionDB buscarEtapaDeProduccionId(long valor) {
        Connection cn=null;
        PostgreSQLManager pg=null;
        EtapadeproduccionDB db=null;
        try {
            pg=new PostgreSQLManager();
            cn = pg.concectGetCn();
            db=AccessEtapaDeProduccion.findById(valor,cn);
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
        return db;
    }

     public boolean eliminarEtapaDeProduccion(long id) {
        EtapadeproduccionDAO dao=new DAOFactoryImpl().createEtapadeproduccionDAO();
        Connection cn=null;

        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }


        //realizo la eliminación

        long result=-1;
            result = AccessEtapaDeProduccion.delete(id, cn);
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                cn.close();
                cn=null;
            } catch (SQLException ex) {
                Logger.getLogger(GestorEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(result>0)return true;
        else return false;
    }

    public long guardarEtapaDeProduccion(EtapaDeProduccion etapaDeProduccion,String idMaquina, String idUnidadmedida)
    {
        PostgreSQLManager pg=null;
        Connection cn=null;
        pg=new PostgreSQLManager();
        long result=-1;

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result=etapaDeProduccion.guardarEtapaDeProduccion(etapaDeProduccion,Long.parseLong(idMaquina),Long.parseLong(idUnidadmedida), cn);
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
    public long modificarEtapaDeProduccion(EtapaDeProduccion etapaDeProduccion, long idEtapaDeProduccion,String idMaquina, String idUnidadMedida)
    {
        PostgreSQLManager pg=null;
        Connection cn=null;
        pg=new PostgreSQLManager();
        long result=-1;

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result=etapaDeProduccion.modificarEtapaDeProduccion(etapaDeProduccion, idEtapaDeProduccion,Long.parseLong(idMaquina), Long.parseLong(idUnidadMedida), cn);
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
    public void obtenerEtapas(JComboBox combo) {
        PostgreSQLManager pg=null;
        Connection cn=null;
        EtapadeproduccionDB[] etapas=null;
        try {
            pg=new PostgreSQLManager();
            cn=pg.concectGetCn();

            etapas=AccessEtapaDeProduccion.findAll(cn);

            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<etapas.length;i++)
            {
                Combo.cargarCombo(combo,String.valueOf(etapas[i].getIdetapaproduccion()),etapas[i].getNombre());
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

    public long generarNvoNroEtapa() {
        long result=-1;
        PostgreSQLManager pg=null;
        Connection cn=null;
        pg=new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
            result=AccessFunctions.nvoNroEtapa(cn);
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
        return result;
    }   
}
