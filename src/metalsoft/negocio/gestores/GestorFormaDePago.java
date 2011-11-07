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
import metalsoft.datos.dbobject.FormadepagoDB;
import metalsoft.datos.dbobject.FormadepagoPKDB;
import metalsoft.datos.exception.FormadepagoException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.FormadepagoDAO;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.FormadepagoJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.negocio.ventas.FormaDePago;


/**
 *
 * @author Lorreine Prescott
 */
public class GestorFormaDePago {

      public GestorFormaDePago()
    {}

 public long guardarFormaDePago(String nombre, String descripcion) {

        /*
         * JPA
         */
        metalsoft.datos.jpa.entity.Formadepago fp=new metalsoft.datos.jpa.entity.Formadepago();
        FormadepagoJpaController con=new FormadepagoJpaController(JpaUtil.getEntityManagerFactory());

        fp.setNombre(nombre);
        fp.setDescripcion(descripcion);
        try {
            con.create(fp);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(GestorFormaDePago.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GestorFormaDePago.class.getName()).log(Level.SEVERE, null, ex);
        }


        return fp.getIdformapago();
    }

    public FormaDePago[] buscarConLIKE(String valor) {
        FormadepagoDAO dao=new DAOFactoryImpl().createFormadepagoDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.FormadepagoDB[] fp=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorFormaDePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            fp = dao.findExecutingUserWhere("nombre ILIKE '"+valor+"%'", sqlParams, cn);
            cn.close();
        } catch (Exception ex) {
            Logger.getLogger(GestorFormaDePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parseFormadepagos(fp);
    }

    private FormaDePago[] parseFormadepagos(FormadepagoDB[] fp) {
        if(fp==null)return null;

        FormaDePago[] c=new FormaDePago[fp.length];
        for(int i=0;i<fp.length;i++)
        {
            FormaDePago x=new FormaDePago();
            x.setNombre(fp[i].getNombre());
            x.setDescripcion(fp[i].getDescripcion());
            c[i]=x;
        }
        return c;
    }

     public boolean modificarFormaDePago(FormaDePago formaPago, String nombre, String descripcion) {
        FormadepagoDAO dao=new DAOFactoryImpl().createFormadepagoDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.FormadepagoDB[] ci=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorFormaDePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];
        Object[] sqlParams=new Object[2];
        sqlParams[0]=formaPago.getNombre();
        sqlParams[1]=formaPago.getDescripcion();
        try {
            ci = dao.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorFormaDePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        long id=-1;
        if(ci.length>0)id=ci[0].getIdformapago();
        else return false;
        //realizo la modificación
        FormadepagoDB modificado=new FormadepagoDB();
        modificado.setDescripcion(descripcion);
        modificado.setNombre(nombre);
        modificado.setIdformapago(id);
        int result=-1;
        try {
            result = dao.update(new FormadepagoPKDB(id), modificado, cn);
        } catch (FormadepagoException ex) {
            Logger.getLogger(GestorFormaDePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorFormaDePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result>0)return true;
        else return false;
    }

    public boolean eliminarFormaDePago(FormaDePago formaPago) {
        FormadepagoDAO dao=new DAOFactoryImpl().createFormadepagoDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.FormadepagoDB[] ci=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorFormaDePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];
        Object[] sqlParams=new Object[2];
        sqlParams[0]=formaPago.getNombre();
        sqlParams[1]=formaPago.getDescripcion();
        try {
            ci = dao.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorFormaDePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        long id=-1;
        if(ci.length>0)id=ci[0].getIdformapago();
        else return false;

        //realizo la eliminación

        int result=-1;
        try {
            result = dao.delete(new FormadepagoPKDB(id), cn);
        } catch (FormadepagoException ex) {
            Logger.getLogger(GestorFormaDePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorFormaDePago.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result>0)return true;
        else return false;
    }

}


