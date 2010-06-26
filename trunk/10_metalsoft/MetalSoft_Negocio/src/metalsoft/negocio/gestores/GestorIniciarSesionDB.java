/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import metalsoft.datos.PostgreSQLManager;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import metalsoft.datos.exception.UsuarioException;
import metalsoft.datos.idao.UsuarioDAO;
import metalsoft.datos.factory.DAOFactoryCreater;
import metalsoft.negocio.adminusuarios.Usuario;

/**
 *
 * @author Administrador
 */
public class GestorIniciarSesionDB {

    public GestorIniciarSesionDB()
    {
    }
    
    public long esUsuario(String user,String pass)
    {
        UsuarioDAO dao=new DAOFactoryCreater().getFactry().createUsuarioDAO();
        metalsoft.datos.dbobject.Usuario u[];
        Usuario usuario=null;
        Object sqlParam[]=new Object[2];
        sqlParam[0]=user;
        sqlParam[1]=pass;
        Connection cn=null;
        metalsoft.datos.dbobject.Usuario usuariodb=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Problema de conexión", JOptionPane.ERROR_MESSAGE);
        }
        try {
            u=dao.findExecutingUserWhere("usuario=? AND clave=?", sqlParam,cn);
            if(u.length>0)
            {
                usuariodb=u[0];
            }
            else
            { 
                return -1;
            }
            
        }
        catch (UsuarioException ex) {
            Logger.getLogger(GestorIniciarSesionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex) {
            Logger.getLogger(GestorIniciarSesionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuariodb.getIdusuario();
    }
}
