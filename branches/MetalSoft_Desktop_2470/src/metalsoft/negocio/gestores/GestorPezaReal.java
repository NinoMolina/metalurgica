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
import metalsoft.datos.dbobject.PiezarealDB;
import metalsoft.datos.exception.PiezarealException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.PiezarealDAO;
import metalsoft.negocio.access.AccessFunctions;
//import metalsoft.negocio.access.AccessMateriaPrima;
import metalsoft.negocio.access.AccessPiezaReal;
import metalsoft.negocio.produccion.PiezaReal;
import metalsoft.util.Combo;
import metalsoft.util.ItemCombo;
/**
 *
 * @author Vicky
 */
public class GestorPezaReal {

    public PiezarealDB buscarPorId(long valor) {
        Connection cn=null;
        PostgreSQLManager pg=null;
        PiezarealDB db=null;
        try {
            pg=new PostgreSQLManager();
            cn = pg.concectGetCn();
            db=AccessPiezaReal.findById(valor,cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPezaReal.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPezaReal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return db;
    }

     public boolean eliminar(long id, long idpieza) {
        PiezarealDAO dao=new DAOFactoryImpl().createPiezarealDAO();
        Connection cn=null;

        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPezaReal.class.getName()).log(Level.SEVERE, null, ex);
        }


        //realizo la eliminación

        long result=-1;
            result = AccessPiezaReal.delete(id, idpieza, cn);
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorPezaReal.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                cn.close();
                cn=null;
            } catch (SQLException ex) {
                Logger.getLogger(GestorPezaReal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(result>0)return true;
        else return false;
    }

    public long guardar(PiezaReal piezaReal,long idPieza, long idestado,long idCodBarra)
    {
        PostgreSQLManager pg=null;
        Connection cn=null;
        pg=new PostgreSQLManager();
        long result=-1;

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result=AccessPiezaReal.insert(piezaReal,idPieza, idestado,idCodBarra, cn);
            cn.commit();
        } catch (Exception ex) {
            Logger.getLogger(GestorPezaReal.class.getName()).log(Level.SEVERE, null, ex);
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorPezaReal.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPezaReal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }


    public long modificar(PiezaReal piezaReal, long idPiezaReal,long idPieza, long idestado,long idCodBarra)
    {
        PostgreSQLManager pg=null;
        Connection cn=null;
        pg=new PostgreSQLManager();
        long result=-1;

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result=AccessPiezaReal.update(piezaReal, idPiezaReal, idPieza, idestado, idCodBarra, cn);
            cn.commit();
        } catch (Exception ex) {
            Logger.getLogger(GestorPezaReal.class.getName()).log(Level.SEVERE, null, ex);
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorPezaReal.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPezaReal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    


}
