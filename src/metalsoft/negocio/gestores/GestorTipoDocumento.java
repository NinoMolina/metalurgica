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
import metalsoft.datos.dbobject.TipodocumentoDB;
import metalsoft.datos.dbobject.TipodocumentoPKDB;
import metalsoft.datos.exception.TipodocumentoException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.TipodocumentoDAO;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.TipodocumentoJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.negocio.rrhh.TipoDocumento;

/**
 *
 * @author Lorreine Prescott
 */
public class GestorTipoDocumento {

 public GestorTipoDocumento(){
    }

 public long guardarTipoDocumento(String tipo, String nombre) {

        /*
         * JPA
         */
        metalsoft.datos.jpa.entity.Tipodocumento td=new metalsoft.datos.jpa.entity.Tipodocumento();
        TipodocumentoJpaController con=new TipodocumentoJpaController(JpaUtil.getEntityManagerFactory());

        td.setTipo(tipo);
        td.setNombre(nombre);
        try {
            con.create(td);
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(GestorTipoDocumento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GestorTipoDocumento.class.getName()).log(Level.SEVERE, null, ex);
        }


        return td.getIdtipodocumento();
    }

    public TipoDocumento[] buscarConLIKE(String valor) {
        TipodocumentoDAO dao=new DAOFactoryImpl().createTipodocumentoDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.TipodocumentoDB[] td=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorTipoDocumento.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            td = dao.findExecutingUserWhere("tipo ILIKE '"+valor+"%'", sqlParams, cn);
            cn.close();
        } catch (Exception ex) {
            Logger.getLogger(GestorTipoDocumento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parseToTipodocumento(td);
    }

    private TipoDocumento[] parseToTipodocumento(TipodocumentoDB[] td) {
        if(td==null)return null;

        TipoDocumento[] c=new TipoDocumento[td.length];
        for(int i=0;i<td.length;i++)
        {
            TipoDocumento x=new TipoDocumento();
            x.setTipo(td[i].getTipo());
            x.setNombre(td[i].getNombre());
            c[i]=x;
        }
        return c;
    }

     public boolean modificarTipoDocumento(TipoDocumento tipodoc, String tipo, String nombre) {
        TipodocumentoDAO dao=new DAOFactoryImpl().createTipodocumentoDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.TipodocumentoDB[] td=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorTipoDocumento.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];
        Object[] sqlParams=new Object[2];
        sqlParams[0]=tipodoc.getTipo();
        sqlParams[1]=tipodoc.getNombre();
        try {
            td = dao.findExecutingUserWhere("tipo = ? AND nombre = ?", sqlParams, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorTipoDocumento.class.getName()).log(Level.SEVERE, null, ex);
        }
        long id=-1;
        if(td.length>0)id=td[0].getIdtipodocumento();
        else return false;
        //realizo la modificación
        TipodocumentoDB modificado=new TipodocumentoDB();
        modificado.setNombre(nombre);
        modificado.setTipo(tipo);
        modificado.setIdtipodocumento(id);
        int result=-1;
        try {
            result = dao.update(new TipodocumentoPKDB(id), modificado, cn);
        } catch (TipodocumentoException ex) {
            Logger.getLogger(GestorTipoDocumento.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorTipoDocumento.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result>0)return true;
        else return false;
    }

    public boolean eliminarTipoDocumento(TipoDocumento tipodoc) {
        TipodocumentoDAO dao=new DAOFactoryImpl().createTipodocumentoDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.TipodocumentoDB[] td=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorTipoDocumento.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];
        Object[] sqlParams=new Object[2];
        sqlParams[0]=tipodoc.getTipo();
        sqlParams[1]=tipodoc.getNombre();
        try {
            td = dao.findExecutingUserWhere("tipo = ? AND nombre = ?", sqlParams, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorTipoDocumento.class.getName()).log(Level.SEVERE, null, ex);
        }
        long id=-1;
        if(td.length>0)id=td[0].getIdtipodocumento();
        else return false;

        //realizo la eliminación

        int result=-1;
        try {
            result = dao.delete(new TipodocumentoPKDB(id), cn);
        } catch (TipodocumentoException ex) {
            Logger.getLogger(GestorTipoDocumento.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorTipoDocumento.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result>0)return true;
        else return false;
    }

}
