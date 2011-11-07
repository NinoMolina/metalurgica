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
import metalsoft.datos.dbobject.TipomaterialDB;
import metalsoft.datos.dbobject.TipomaterialPKDB;
import metalsoft.datos.exception.TipomaterialException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.TipomaterialDAO;
import metalsoft.negocio.produccion.TipoMaterial;
import metalsoft.negocio.ventas.Cliente;
/**
 *
 * @author Nino
 */
public class GestorTipoMaterial {

    public GestorTipoMaterial()
    {}

    public int guardarTipoMaterial(String nombre, String descripcion) {
        TipomaterialDAO dao=new DAOFactoryImpl().createTipomaterialDAO();
        TipomaterialDB tm=new TipomaterialDB();
        tm.setDescripcion(descripcion);
        tm.setNombre(nombre);
        Connection cn=null;
        int id=-1;
        try {
            cn = new PostgreSQLManager().concectGetCn();
            id=dao.insert(tm, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            if(cn!=null)try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(GestorTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return id;
    }

    public TipoMaterial[] buscarConLIKE(String valor) {
        TipomaterialDAO dao=new DAOFactoryImpl().createTipomaterialDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.TipomaterialDB[] tm=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            tm = dao.findExecutingUserWhere("nombre ILIKE '"+valor+"%'", sqlParams, cn);
            cn.close();
        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parseToTipomaterial(tm);
    }

    private TipoMaterial[] parseToTipomaterial(TipomaterialDB[] tm) {
        if(tm==null)return null;

        TipoMaterial[] c=new TipoMaterial[tm.length];
        for(int i=0;i<tm.length;i++)
        {
            TipoMaterial x=new TipoMaterial();
            x.setNombre(tm[i].getNombre());
            x.setDescripcion(tm[i].getDescripcion());
            c[i]=x;
        }
        return c;
    }

//    public TipoMaterial[] buscarTipoMaterial(String valor) {
//        GestorTipoMaterialDB gestor=new GestorTipoMaterialDB();
//        return gestor.buscarConLIKE(valor);
//    }

    public boolean modificarTipoMaterial(TipoMaterial tipoMaterial, String nombre, String descripcion) {
        TipomaterialDAO dao=new DAOFactoryImpl().createTipomaterialDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.TipomaterialDB[] tm=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];
        Object[] sqlParams=new Object[2];
        sqlParams[0]=tipoMaterial.getNombre();
        sqlParams[1]=tipoMaterial.getDescripcion();
        try {
            tm = dao.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        long id=-1;
        if(tm.length>0)id=tm[0].getIdtipomaterial();
        else return false;
        //realizo la modificación
        TipomaterialDB modificado=new TipomaterialDB();
        modificado.setDescripcion(descripcion);
        modificado.setNombre(nombre);
        modificado.setIdtipomaterial(id);
        int result=-1;
        try {
            result = dao.update(new TipomaterialPKDB(id), modificado, cn);
        } catch (TipomaterialException ex) {
            Logger.getLogger(GestorTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result>0)return true;
        else return false;
    }

    public boolean eliminarTipoMaterial(TipoMaterial tipoMaterial) {
        TipomaterialDAO dao=new DAOFactoryImpl().createTipomaterialDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.TipomaterialDB[] tm=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];
        Object[] sqlParams=new Object[2];
        sqlParams[0]=tipoMaterial.getNombre();
        sqlParams[1]=tipoMaterial.getDescripcion();
        try {
            tm = dao.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        long id=-1;
        if(tm.length>0)id=tm[0].getIdtipomaterial();
        else return false;

        //realizo la eliminación

        int result=-1;
        try {
            result = dao.delete(new TipomaterialPKDB(id), cn);
        } catch (TipomaterialException ex) {
            Logger.getLogger(GestorTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorTipoMaterial.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result>0)return true;
        else return false;
    }


}
