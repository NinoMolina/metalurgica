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
import metalsoft.datos.dbobject.ServicioDB;
import metalsoft.datos.dbobject.ServicioPKDB;
import metalsoft.datos.exception.ServicioException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.ServicioDAO;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.ServicioJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.negocio.mantenimiento.ServicioMaquina;
/**
 *
 * @author Lorreine Prescott
 */
public class GestorServicioMaquina {

     public GestorServicioMaquina()
    {}

 public long guardarServicioMaquina(String nombre, String descripcion) {

        /*
         * JPA
         */
        metalsoft.datos.jpa.entity.Servicio sm=new metalsoft.datos.jpa.entity.Servicio();
        ServicioJpaController con=new ServicioJpaController(JpaUtil.getEntityManagerFactory());

        sm.setNombre(nombre);
        sm.setDescripcion(descripcion);
        try {
            con.create(sm);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(GestorServicioMaquina.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GestorServicioMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }


        return sm.getIdservicio();
    }

    public ServicioMaquina[] buscarConLIKE(String valor) {
        ServicioDAO dao=new DAOFactoryImpl().createServicioDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.ServicioDB[] sm=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorServicioMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            sm = dao.findExecutingUserWhere("nombre ILIKE '"+valor+"%'", sqlParams, cn);
            cn.close();
        } catch (Exception ex) {
            Logger.getLogger(GestorServicioMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parseToServicioMaquina(sm);
    }

    private ServicioMaquina[] parseToServicioMaquina(ServicioDB[] sm) {
        if(sm==null)return null;

        ServicioMaquina[] c=new ServicioMaquina[sm.length];
        for(int i=0;i<sm.length;i++)
        {
            ServicioMaquina x=new ServicioMaquina();
            x.setNombre(sm[i].getNombre());
            x.setDescripcion(sm[i].getDescripcion());
            c[i]=x;
        }
        return c;
    }

     public boolean modificarServicioMaquina(ServicioMaquina servicio, String nombre, String descripcion) {
        ServicioDAO dao=new DAOFactoryImpl().createServicioDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.ServicioDB[] sm=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorServicioMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];
        Object[] sqlParams=new Object[2];
        sqlParams[0]=servicio.getNombre();
        sqlParams[1]=servicio.getDescripcion();
        try {
            sm = dao.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorServicioMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        long id=-1;
        if(sm.length>0)id=sm[0].getIdservicio();
        else return false;
        //realizo la modificación
        ServicioDB modificado=new ServicioDB();
        modificado.setDescripcion(descripcion);
        modificado.setNombre(nombre);
        modificado.setIdservicio(id);
        int result=-1;
        try {
            result = dao.update(new ServicioPKDB(id), modificado, cn);
        } catch (ServicioException ex) {
            Logger.getLogger(GestorServicioMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorServicioMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result>0)return true;
        else return false;
    }

    public boolean eliminarServicioMaquina(ServicioMaquina servicio) {
        ServicioDAO dao=new DAOFactoryImpl().createServicioDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.ServicioDB[] sm=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorServicioMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];
        Object[] sqlParams=new Object[2];
        sqlParams[0]=servicio.getNombre();
        sqlParams[1]=servicio.getDescripcion();
        try {
            sm = dao.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorServicioMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        long id=-1;
        if(sm.length>0)id=sm[0].getIdservicio();
        else return false;

        //realizo la eliminación

        int result=-1;
        try {
            result = dao.delete(new ServicioPKDB(id), cn);
        } catch (ServicioException ex) {
            Logger.getLogger(GestorServicioMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorServicioMaquina.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result>0)return true;
        else return false;
    }

}
