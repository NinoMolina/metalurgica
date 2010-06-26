/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.Tipomaterial;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.TipomaterialDAO;

/**
 *
 * @author Nino
 */
public class GestorTipoMaterialDB {

    public int guardar(String nombre, String descripcion) {
        TipomaterialDAO dao=new DAOFactoryImpl().createTipomaterialDAO();
        Tipomaterial tm=new Tipomaterial();
        tm.setDescripcion(descripcion);
        tm.setNombre(nombre);
        Connection cn=null;
        int id=-1;
        try {
            cn = new PostgreSQLManager().concectGetCn();
            id=dao.insert(tm, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaterialDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if(cn!=null)try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestorTipoMaterialDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return id;
    }

}
