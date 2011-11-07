/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import metalsoft.datos.PostgreSQLManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.UsuarioxrolDB;
import metalsoft.datos.exception.RolException;
import metalsoft.datos.exception.UsuarioxrolException;
import metalsoft.datos.factory.DAOFactoryCreater;
import metalsoft.datos.idao.RolDAO;
import metalsoft.datos.idao.UsuarioxrolDAO;
import metalsoft.negocio.adminusuarios.Rol;

/**
 *
 * @author Administrador
 */
public class GestorRolDB {

    public Rol[] obtenerRol(long idrol)
    {
        RolDAO dao=new DAOFactoryCreater().getFactry().createRolDAO();
        metalsoft.datos.dbobject.RolDB[] roldb=null;
        metalsoft.negocio.adminusuarios.Rol rol[]=null;
        try {
            roldb = dao.findByIdrol(idrol, new PostgreSQLManager().concectGetCn());
            if(roldb.length==0){ return null;}
            rol=parseToRol(roldb);
        }
        catch (RolException ex) {
            Logger.getLogger(GestorRolDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex){
            Logger.getLogger(GestorRolDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rol;
    }

    public static metalsoft.negocio.adminusuarios.Rol[] parseToRol(metalsoft.datos.dbobject.RolDB roldb[])
    {
        metalsoft.negocio.adminusuarios.Rol rol[]=new Rol[roldb.length];
        metalsoft.negocio.adminusuarios.Rol r=null;

        for(int i=0;i<roldb.length;i++)
        {
            r=new Rol();
            r.setDescripcion(roldb[i].getDescripcion());
            r.setRol(roldb[i].getRol());
            rol[i]=r;
        }

        return rol;
    }

    public Rol[] obtenerRoles(long idUsuario) {
        DAOFactoryCreater dfc=new DAOFactoryCreater();
        UsuarioxrolDAO daouxr=dfc.getFactry().createUsuarioxrolDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        RolDAO daorol=dfc.getFactry().createRolDAO();
        UsuarioxrolDB uxr[]=null;
        long idrol=-1;
        metalsoft.datos.dbobject.RolDB roldb[];
        metalsoft.negocio.adminusuarios.Rol addrol[];

        try
        {
            pg.connect();
        }
        catch (Exception ex)
        {
            Logger.getLogger(GestorRolDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        try
        {
            uxr = daouxr.findByIdusuario(idUsuario, pg.getCn());
        } 
        catch (UsuarioxrolException ex)
        {
            Logger.getLogger(GestorRolDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        try
        {
            pg.disconnect();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(GestorRolDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        roldb=new metalsoft.datos.dbobject.RolDB[1];
        addrol=new metalsoft.negocio.adminusuarios.Rol[uxr.length];

        try
        {
            pg.connect();
        } 
        catch (Exception ex)
        {
            Logger.getLogger(GestorRolDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        for(int i=0;i<uxr.length;i++)
        {
            idrol=uxr[i].getIdrol();      
            try
            {
                roldb = daorol.findByIdrol(idrol, pg.getCn());
                if(roldb.length>0) addrol[i]=parseToRol(roldb[0]);
            } 
            catch (RolException ex)
            {
                Logger.getLogger(GestorRolDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try
        {
            pg.disconnect();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(GestorRolDB.class.getName()).log(Level.SEVERE, null, ex);
        }

        return addrol;
    }

    public static Rol parseToRol(metalsoft.datos.dbobject.RolDB rol) {
        metalsoft.negocio.adminusuarios.Rol r=new Rol();
        r.setDescripcion(rol.getDescripcion());
        r.setRol(rol.getRol());
        return r;
    }
}
