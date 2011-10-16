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
import metalsoft.datos.dbobject.RoturaDB;
import metalsoft.datos.dbobject.RoturaPKDB;
import metalsoft.datos.exception.RoturaException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.RoturaDAO;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.RoturaJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.negocio.mantenimiento.Rotura;

/**
 *
 * @author Lorreine Prescott
 */
public class GestorRotura {

    public GestorRotura() {
    }

    public long guardarRotura(String nombre, String descripcion) {

        /*
         * JPA
         */
        metalsoft.datos.jpa.entity.Rotura ro = new metalsoft.datos.jpa.entity.Rotura();
        RoturaJpaController con = new RoturaJpaController(JpaUtil.getEntityManagerFactory());

        ro.setNombre(nombre);
        ro.setDescripcion(descripcion);

        con.create(ro);

        return ro.getIdrotura();
    }

    public Rotura[] buscarConLIKE(String valor) {
        RoturaDAO dao = new DAOFactoryImpl().createRoturaDAO();
        Connection cn = null;
        metalsoft.datos.dbobject.RoturaDB[] rot = null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorRotura.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[] sqlParams = new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            rot = dao.findExecutingUserWhere("nombre ILIKE '" + valor + "%'", sqlParams, cn);
            cn.close();
        } catch (Exception ex) {
            Logger.getLogger(GestorRotura.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parseToRotura(rot);
    }

    private Rotura[] parseToRotura(RoturaDB[] rot) {
        if (rot == null) {
            return null;
        }

        Rotura[] c = new Rotura[rot.length];
        for (int i = 0; i < rot.length; i++) {
            Rotura x = new Rotura();
            x.setNombre(rot[i].getNombre());
            x.setDescripcion(rot[i].getDescripcion());
            c[i] = x;
        }
        return c;
    }

    public boolean modificarRotura(Rotura rotura, String nombre, String descripcion) {
        RoturaDAO dao = new DAOFactoryImpl().createRoturaDAO();
        Connection cn = null;
        metalsoft.datos.dbobject.RoturaDB[] rot = null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorRotura.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];
        Object[] sqlParams = new Object[2];
        sqlParams[0] = rotura.getNombre();
        sqlParams[1] = rotura.getDescripcion();
        try {
            rot = dao.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorRotura.class.getName()).log(Level.SEVERE, null, ex);
        }
        long id = -1;
        if (rot.length > 0) {
            id = rot[0].getIdrotura();
        } else {
            return false;
        }
        //realizo la modificación
        RoturaDB modificado = new RoturaDB();
        modificado.setDescripcion(descripcion);
        modificado.setNombre(nombre);
        modificado.setIdrotura(id);
        int result = -1;
        try {
            result = dao.update(new RoturaPKDB(id), modificado, cn);
        } catch (RoturaException ex) {
            Logger.getLogger(GestorRotura.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorRotura.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean eliminarRotura(Rotura rotura) {
        RoturaDAO dao = new DAOFactoryImpl().createRoturaDAO();
        Connection cn = null;
        metalsoft.datos.dbobject.RoturaDB[] rot = null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorRotura.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];
        Object[] sqlParams = new Object[2];
        sqlParams[0] = rotura.getNombre();
        sqlParams[1] = rotura.getDescripcion();
        try {
            rot = dao.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorRotura.class.getName()).log(Level.SEVERE, null, ex);
        }
        long id = -1;
        if (rot.length > 0) {
            id = rot[0].getIdrotura();
        } else {
            return false;
        }

        //realizo la eliminación

        int result = -1;
        try {
            result = dao.delete(new RoturaPKDB(id), cn);
        } catch (RoturaException ex) {
            Logger.getLogger(GestorRotura.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorRotura.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }
}
