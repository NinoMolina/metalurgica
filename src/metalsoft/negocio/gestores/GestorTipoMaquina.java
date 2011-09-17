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
import metalsoft.datos.dbobject.TipomaquinaDB;
import metalsoft.datos.dbobject.TipomaquinaPKDB;
import metalsoft.datos.exception.TipomaquinaException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.TipomaquinaDAO;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.TipomaquinaJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.negocio.mantmaquinarias.TipoMaquina;
/**
 *
 * @author Lorreine Prescott
 */
public class GestorTipoMaquina {

     public GestorTipoMaquina()
    {}

    public long guardarTipoMaquina(String nombre, String descripcion) {

        /*
         * JPA
         */
        metalsoft.datos.jpa.entity.Tipomaquina tm=new metalsoft.datos.jpa.entity.Tipomaquina();
        TipomaquinaJpaController con=new TipomaquinaJpaController(JpaUtil.getEntityManagerFactory());

        tm.setNombre(nombre);
        tm.setDescripcion(descripcion);
        try {
            con.create(tm);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(GestorTipoMaquina.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tm.getIdtipomaquina();
    }

    public TipoMaquina[] buscarConLIKE(String valor) {
        TipomaquinaDAO dao=new DAOFactoryImpl().createTipomaquinaDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.TipomaquinaDB[] tmaq=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            tmaq = dao.findExecutingUserWhere("nombre ILIKE '"+valor+"%'", sqlParams, cn);
            cn.close();
        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parseToTipoMaquina(tmaq);
    }

    private TipoMaquina[] parseToTipoMaquina(TipomaquinaDB[] tmaq) {
        if(tmaq==null)return null;

        TipoMaquina[] c=new TipoMaquina[tmaq.length];
        for(int i=0;i<tmaq.length;i++)
        {
            TipoMaquina x=new TipoMaquina();
            x.setNombre(tmaq[i].getNombre());
            x.setDescripcion(tmaq[i].getDescripcion());
            c[i]=x;
        }
        return c;
    }

     public boolean modificarTipoMaquina(TipoMaquina tmaquina, String nombre, String descripcion) {
        TipomaquinaDAO dao=new DAOFactoryImpl().createTipomaquinaDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.TipomaquinaDB[] tmaq=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];
        Object[] sqlParams=new Object[2];
        sqlParams[0]=tmaquina.getNombre();
        sqlParams[1]=tmaquina.getDescripcion();
        try {
            tmaq = dao.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        long id=-1;
        if(tmaq.length>0)id=tmaq[0].getIdtipomaquina();
        else return false;
        //realizo la modificación
        TipomaquinaDB modificado=new TipomaquinaDB();
        modificado.setDescripcion(descripcion);
        modificado.setNombre(nombre);
        modificado.setIdtipomaquina(id);
        int result=-1;
        try {
            result = dao.update(new TipomaquinaPKDB(id), modificado, cn);
        } catch (TipomaquinaException ex) {
            Logger.getLogger(GestorTipoMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorTipoMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result>0)return true;
        else return false;
    }

    public boolean eliminarTipoMaquina(TipoMaquina tmaquina) {
        TipomaquinaDAO dao=new DAOFactoryImpl().createTipomaquinaDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.TipomaquinaDB[] tmaq=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];
        Object[] sqlParams=new Object[2];
        sqlParams[0]=tmaquina.getNombre();
        sqlParams[1]=tmaquina.getDescripcion();
        try {
            tmaq = dao.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        long id=-1;
        if(tmaq.length>0)id=tmaq[0].getIdtipomaquina();
        else return false;

        //realizo la eliminación

        int result=-1;
        try {
            result = dao.delete(new TipomaquinaPKDB(id), cn);
        } catch (TipomaquinaException ex) {
            Logger.getLogger(GestorTipoMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorTipoMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result>0)return true;
        else return false;
    }


}
