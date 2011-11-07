/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;
import java.sql.Connection;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.MaquinaDB;
import metalsoft.datos.dbobject.TipomaquinaDB;
import metalsoft.datos.dbobject.UnidadmedidaDB;
import metalsoft.datos.dbobject.MarcaDB;
import metalsoft.datos.dbobject.EstadomaquinaDB;
import metalsoft.datos.dbobject.TipomaterialDB;
import metalsoft.datos.exception.MaquinaException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.EstadomaquinaDAO;
import metalsoft.datos.idao.MaquinaDAO;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.access.AccessMaquina;
import metalsoft.negocio.access.AccessMarca;
import metalsoft.negocio.access.AccessTipoMaquina;
import metalsoft.negocio.access.AccessTipoMaterial;
import metalsoft.negocio.access.AccessUnidadDeMedida;

import metalsoft.negocio.mantmaquinarias.Maquina;
import metalsoft.util.Combo;
import metalsoft.util.ItemCombo;
/**
 *
 * @author Vicky
 */
public class GestorMaquina {
    public MaquinaDB[] buscarConILIKE(String valor) {
        Connection cn=null;
        PostgreSQLManager pg=null;
        MaquinaDB[] db=null;
        try {
            pg=new PostgreSQLManager();
            cn = pg.concectGetCn();
            db=AccessMaquina.findByNombreILIKE(valor,cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorMaquina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return db;
    }

    public MaquinaDB buscarPorId(long valor) {
        Connection cn=null;
        PostgreSQLManager pg=null;
        MaquinaDB db=null;
        try {
            pg=new PostgreSQLManager();
            cn = pg.concectGetCn();
            db=AccessMaquina.findById(valor,cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorMaquina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return db;
    }

     public boolean eliminar(long id) {
        MaquinaDAO dao=new DAOFactoryImpl().createMaquinaDAO();
        Connection cn=null;

        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }


        //realizo la eliminación

        long result=-1;
            result = AccessMaquina.delete(id, cn);
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                cn.close();
                cn=null;
            } catch (SQLException ex) {
                Logger.getLogger(GestorMaquina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(result>0)return true;
        else return false;
    }

    public long guardar(Maquina maquina,long idTipoMaquina, long idUnidadMedida,long idMarca, long idEstado)
    {
        PostgreSQLManager pg=null;
        Connection cn=null;
        pg=new PostgreSQLManager();
        long result=-1;

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result=AccessMaquina.insert(maquina, idTipoMaquina, idUnidadMedida, idMarca, idEstado, cn);
            cn.commit();
        } catch (Exception ex) {
            Logger.getLogger(GestorMaquina.class.getName()).log(Level.SEVERE, null, ex);
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorMaquina.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorMaquina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }


    public long modificar(Maquina maquina, long idMaquina,long idTipoMaquina, long idUnidadMedida,long idMarca, long idEstado)
    {
        PostgreSQLManager pg=null;
        Connection cn=null;
        pg=new PostgreSQLManager();
        long result=-1;

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result=AccessMaquina.update(maquina, idMaquina,idTipoMaquina, idUnidadMedida, idMarca, idEstado, cn);
            cn.commit();
        } catch (Exception ex) {
            Logger.getLogger(GestorMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorMateriaPrima.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    public void obtenerTipoMaquina(JComboBox combo) {
        PostgreSQLManager pg=null;
        Connection cn=null;
        TipomaquinaDB[] tipoMateriales=null;
        try {
            pg=new PostgreSQLManager();
            cn=pg.concectGetCn();

            tipoMateriales=AccessTipoMaquina.findAll(cn);

            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<tipoMateriales.length;i++)
            {
                Combo.cargarCombo(combo,String.valueOf(tipoMateriales[i].getIdtipomaquina()),tipoMateriales[i].getNombre());
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorMaquina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void obtenerMarca(JComboBox combo) {
        PostgreSQLManager pg=null;
        Connection cn=null;
        MarcaDB[] tipoMateriales=null;
        try {
            pg=new PostgreSQLManager();
            cn=pg.concectGetCn();

            tipoMateriales=AccessMarca.findAll(cn);

            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<tipoMateriales.length;i++)
            {
                Combo.cargarCombo(combo,String.valueOf(tipoMateriales[i].getIdmarca()),tipoMateriales[i].getNombre());
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorMaquina.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorMaquina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void obternerEstadoMaquina(JComboBox combo) {

        PostgreSQLManager pg=null;
        Connection cn=null;
        EstadomaquinaDB[] datos=null;
        EstadomaquinaDAO dao=new DAOFactoryImpl().createEstadomaquinaDAO();
        try {
            pg=new PostgreSQLManager();
            cn=pg.concectGetCn();

            datos=dao.findAll(cn);

            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<datos.length;i++)
            {
                Combo.cargarCombo(combo,String.valueOf(datos[i].getIdestado()),datos[i].getNombre());
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorMaquina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public long generarNvoNroMaquina() {
        long result=-1;
        PostgreSQLManager pg=null;
        Connection cn=null;
        pg=new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
            result=AccessFunctions.nvoNroMaquina(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorMaquina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

}
