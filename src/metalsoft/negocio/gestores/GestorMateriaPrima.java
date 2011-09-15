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
import metalsoft.datos.dbobject.MateriaprimaDB;
import metalsoft.datos.dbobject.TipomaterialDB;
import metalsoft.datos.dbobject.UnidadmedidaDB;
import metalsoft.datos.exception.MateriaprimaException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.MateriaprimaDAO;
import metalsoft.datos.idao.TipomaterialDAO;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.access.AccessMateriaPrima;
import metalsoft.negocio.access.AccessTipoMaterial;
import metalsoft.negocio.access.AccessUnidadDeMedida;
import metalsoft.negocio.almacenamiento.MateriaPrima;
import metalsoft.util.Combo;
import metalsoft.util.ItemCombo;
/**
 *
 * @author Vicky
 */
public class GestorMateriaPrima {
    public MateriaprimaDB[] buscarConILIKE(String valor) {
        Connection cn=null;
        PostgreSQLManager pg=null;
        MateriaprimaDB[] db=null;
        try {
            pg=new PostgreSQLManager();
            cn = pg.concectGetCn();
            db=AccessMateriaPrima.findByNombreILIKE(valor,cn);
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

    public MateriaprimaDB buscarPorId(long valor) {
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

     public boolean eliminar(long id) {
        MateriaprimaDAO dao=new DAOFactoryImpl().createMateriaprimaDAO();
        Connection cn=null;

        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }


        //realizo la eliminación

        long result=-1;
            result = AccessMateriaPrima.delete(id, cn);
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                cn.close();
                cn=null;
            } catch (SQLException ex) {
                Logger.getLogger(GestorMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(result>0)return true;
        else return false;
    }

    public long guardar(MateriaPrima materiaPrima,String idTipoMaterial, String idUnidadmedida, String idCodBarra)
    {
        PostgreSQLManager pg=null;
        Connection cn=null;
        pg=new PostgreSQLManager();
        long result=-1;

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result=materiaPrima.guardarMateriaPrima(materiaPrima,Long.parseLong(idTipoMaterial),Long.parseLong(idUnidadmedida),Long.parseLong(idCodBarra), cn);
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

    
    public long modificar(MateriaPrima materiaPrima, long idMateriaPrima,String idTipoMaterial, String idUnidadMedida, String idCodBarra)
    {
        PostgreSQLManager pg=null;
        Connection cn=null;
        pg=new PostgreSQLManager();
        long result=-1;

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result=materiaPrima.modificar(materiaPrima, idMateriaPrima,Long.parseLong(idTipoMaterial), Long.parseLong(idUnidadMedida),Long.parseLong(idCodBarra), cn);
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
    public long modificarMateriaPrimaDB(metalsoft.datos.dbobject.MateriaprimaDB materiaPrima, Connection cn)
    {
        long result=-1;

        try {
            result=AccessMateriaPrima.updateMateriaPrimaDB(materiaPrima, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public void obtenerTipoMateriales(JComboBox combo) {
        PostgreSQLManager pg=null;
        Connection cn=null;
        TipomaterialDB[] tipoMateriales=null;
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

    public long generarNvoNroMateriaPrima() {
        long result=-1;
        PostgreSQLManager pg=null;
        Connection cn=null;
        pg=new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
            result=AccessFunctions.nvoNroMateriaPrima(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPedidoCotizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
