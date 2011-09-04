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
import metalsoft.datos.dbobject.CondicionivaDB;
import metalsoft.datos.dbobject.CondicionivaPKDB;
import metalsoft.datos.exception.CondicionivaException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.CondicionivaDAO;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.CondicionivaJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.negocio.ventas.CondicionIva;

/**
 *
 * @author Lorreine Prescott
 */
public class GestorCondicionIva {

     public GestorCondicionIva()
    {}

 public long guardarCondicionIva(String nombre, String descripcion) {

        /*
         * JPA
         */
        metalsoft.datos.jpa.entity.Condicioniva ci=new metalsoft.datos.jpa.entity.Condicioniva();
        CondicionivaJpaController con=new CondicionivaJpaController(JpaUtil.getEntityManagerFactory());

        ci.setNombre(nombre);
        ci.setDescripcion(descripcion);
        try {
            con.create(ci);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(GestorCondicionIva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GestorCondicionIva.class.getName()).log(Level.SEVERE, null, ex);
        }


        return ci.getIdcondicioniva();
    }

    public CondicionIva[] buscarConLIKE(String valor) {
        CondicionivaDAO dao=new DAOFactoryImpl().createCondicionivaDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.CondicionivaDB[] ci=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorCondicionIva.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            ci = dao.findExecutingUserWhere("nombre ILIKE '"+valor+"%'", sqlParams, cn);
            cn.close();
        } catch (Exception ex) {
            Logger.getLogger(GestorCondicionIva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parseToCondicionIva(ci);
    }

    private CondicionIva[] parseToCondicionIva(CondicionivaDB[] ci) {
        if(ci==null)return null;

        CondicionIva[] c=new CondicionIva[ci.length];
        for(int i=0;i<ci.length;i++)
        {
            CondicionIva x=new CondicionIva();
            x.setNombre(ci[i].getNombre());
            x.setDescripcion(ci[i].getDescripcion());
            c[i]=x;
        }
        return c;
    }

     public boolean modificarCondicionIva(CondicionIva condicion, String nombre, String descripcion) {
        CondicionivaDAO dao=new DAOFactoryImpl().createCondicionivaDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.CondicionivaDB[] ci=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorCondicionIva.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];
        Object[] sqlParams=new Object[2];
        sqlParams[0]=condicion.getNombre();
        sqlParams[1]=condicion.getDescripcion();
        try {
            ci = dao.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorCondicionIva.class.getName()).log(Level.SEVERE, null, ex);
        }
        long id=-1;
        if(ci.length>0)id=ci[0].getIdcondicioniva();
        else return false;
        //realizo la modificación
        CondicionivaDB modificado=new CondicionivaDB();
        modificado.setDescripcion(descripcion);
        modificado.setNombre(nombre);
        modificado.setIdcondicioniva(id);
        int result=-1;
        try {
            result = dao.update(new CondicionivaPKDB(id), modificado, cn);
        } catch (CondicionivaException ex) {
            Logger.getLogger(GestorCondicionIva.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorCondicionIva.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result>0)return true;
        else return false;
    }

    public boolean eliminarCondicionIva(CondicionIva condicion) {
        CondicionivaDAO dao=new DAOFactoryImpl().createCondicionivaDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.CondicionivaDB[] ci=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorCondicionIva.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];
        Object[] sqlParams=new Object[2];
        sqlParams[0]=condicion.getNombre();
        sqlParams[1]=condicion.getDescripcion();
        try {
            ci = dao.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorCondicionIva.class.getName()).log(Level.SEVERE, null, ex);
        }
        long id=-1;
        if(ci.length>0)id=ci[0].getIdcondicioniva();
        else return false;

        //realizo la eliminación

        int result=-1;
        try {
            result = dao.delete(new CondicionivaPKDB(id), cn);
        } catch (CondicionivaException ex) {
            Logger.getLogger(GestorCondicionIva.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorCondicionIva.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result>0)return true;
        else return false;
    }

}
