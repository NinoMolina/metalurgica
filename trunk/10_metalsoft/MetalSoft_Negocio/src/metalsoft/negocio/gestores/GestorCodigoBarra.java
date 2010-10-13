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
import metalsoft.datos.dbobject.CodigodebarraDB;
import metalsoft.datos.dbobject.CodigodebarraPK;
import metalsoft.datos.dbobject.PiezarealPK;
import metalsoft.datos.exception.CodigodebarraException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.CodigodebarraDAO;
import metalsoft.datos.idao.PiezarealDAO;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.produccion.CodigoDeBarra;
import metalsoft.util.Combo;
import metalsoft.util.ItemCombo;
/**
 *
 * @author Vicky
 */
public class GestorCodigoBarra {

    public CodigodebarraDB[] buscarConILIKE(String valor) {
        Connection cn=null;
        PostgreSQLManager pg=null;
        CodigodebarraDB[] x=null;
        CodigodebarraDAO dao=new DAOFactoryImpl().createCodigodebarraDAO();
        Object[] sqlParams=new Object[0];
        try {
            pg=new PostgreSQLManager();
            cn = pg.concectGetCn();
            x = dao.findExecutingUserWhere("nombre ILIKE '"+valor+"%'", sqlParams, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return x;
    }

    public CodigodebarraDB buscarPorId(long valor) {
        Connection cn=null;
        PostgreSQLManager pg=null;
        CodigodebarraDB db=null;
        CodigodebarraDAO dao=new DAOFactoryImpl().createCodigodebarraDAO();
        try {
            pg=new PostgreSQLManager();
            cn = pg.concectGetCn();
            db=dao.findByIdcodigo(valor, cn)[0];
        } catch (Exception ex) {
            Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return db;
    }

     public boolean eliminar(long id) {
        CodigodebarraDAO dao=new DAOFactoryImpl().createCodigodebarraDAO();
        Connection cn=null;

        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
        }

        long result=-1;

        CodigodebarraPK pk=new CodigodebarraPK(id);
        try {
            result=dao.delete(pk, cn);
        } catch (CodigodebarraException ex) {
            Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
        }

            try {
                cn.close();
                cn=null;
            } catch (SQLException ex) {
                Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
            }
        if(result>0)return true;
        else return false;
    }

    public long guardarCodPieza(CodigoDeBarra codBarra, long idArticulo)
    {
        CodigodebarraDAO dao=new DAOFactoryImpl().createCodigodebarraDAO();
        CodigodebarraDB p=new CodigodebarraDB();
        p.setDescripcion(codBarra.getDescripcion());

        int id=-1;
        int idPieza=-1;

        Connection cn=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
        }


        try {
            id=dao.insert(p, cn);
            p.setIdcodigo(id);
            p.setCodigo("PIE"+id);
            codBarra.setCodigo("PIE"+id);
            PiezarealDAO daoPieza=new DAOFactoryImpl().createPiezarealDAO();
            PiezarealPK pipk=new PiezarealPK(idArticulo, idPieza);
            idPieza=daoPieza.update(piezarealpk, piezareal, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if(cn!=null)try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCodigoBarra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return id;
    }


    public long modificarEtapaDeProduccion(MateriaPrima materiaPrima, long idMateriaPrima,String idTipoMaterial, String idUnidadMedida, String idCodBarra)
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
}
