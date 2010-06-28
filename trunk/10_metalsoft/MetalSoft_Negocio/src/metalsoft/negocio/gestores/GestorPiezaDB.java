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
import metalsoft.datos.dbobject.Pieza;
import metalsoft.datos.dbobject.TipomaterialPK;
import metalsoft.datos.exception.TipomaterialException;
import metalsoft.datos.factory.DAOFactoryImpl;
//import metalsoft.datos.idao.PiezaDAO;
import metalsoft.datos.idao.PiezaDAO;
import metalsoft.negocio.produccion.TipoMaterial;
import metalsoft.datos.dbobject.Tipomaterial;
import metalsoft.datos.idao.TipomaterialDAO;
//import metalsoft.negocio.ventas.Cliente;
/**
 *
 * @author Vicky
 */
public class GestorPiezaDB {

    public int guardar(String nombre, String dimensiones, TipoMaterial tipomaterial) {
        PiezaDAO dao=new DAOFactoryImpl().createPiezaDAO();
        TipomaterialDAO daoTM=new DAOFactoryImpl().createTipomaterialDAO();
        Pieza p=new Pieza();
        p.setNombre(nombre);
        p.setDimensiones(dimensiones);
        int id=-1;
        long idTM=-1;
        Connection cn=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];

        Object[] sqlParams=new Object[2];
        sqlParams[0]=tipomaterial.getNombre();
        sqlParams[1]=tipomaterial.getDescripcion();
        try {
            Tipomaterial[] tm = daoTM.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);
            if(tm.length>0) idTM=tm[0].getIdtipomaterial();

        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(idTM!=-1) p.setTipomaterial(idTM);
        try {
            id=dao.insert(p, cn);
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

    public metalsoft.negocio.ventas.Pieza[] buscarConLIKE(String valor) {
        PiezaDAO dao=new DAOFactoryImpl().createPiezaDAO();
        Connection cn=null;
        Pieza[] p=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            p = dao.findExecutingUserWhere("nombre ILIKE '"+valor+"%'", sqlParams, cn);
            cn.close();
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parseToPieza(p);
    }

    private TipoMaterial[] parseToTipomaterial(Tipomaterial[] tm) {
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

    public boolean modificarTipoMaterial(TipoMaterial tipoMaterial, String nombre, String descripcion) {
        TipomaterialDAO dao=new DAOFactoryImpl().createTipomaterialDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.Tipomaterial[] tm=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];
        Object[] sqlParams=new Object[2];
        sqlParams[0]=tipoMaterial.getNombre();
        sqlParams[1]=tipoMaterial.getDescripcion();
        try {
            tm = dao.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        long id=-1;
        if(tm.length>0)id=tm[0].getIdtipomaterial();
        else return false;
        //realizo la modificación
        Tipomaterial modificado=new Tipomaterial();
        modificado.setDescripcion(descripcion);
        modificado.setNombre(nombre);
        modificado.setIdtipomaterial(id);
        int result=-1;
        try {
            result = dao.update(new TipomaterialPK(id), modificado, cn);
        } catch (TipomaterialException ex) {
            Logger.getLogger(GestorTipoMaterialDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorTipoMaterialDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result>0)return true;
        else return false;
    }

    boolean eliminarTipoMaterial(TipoMaterial tipoMaterial) {
        TipomaterialDAO dao=new DAOFactoryImpl().createTipomaterialDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.Tipomaterial[] tm=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Object[] sqlParams=new Object[0];
        Object[] sqlParams=new Object[2];
        sqlParams[0]=tipoMaterial.getNombre();
        sqlParams[1]=tipoMaterial.getDescripcion();
        try {
            tm = dao.findExecutingUserWhere("nombre = ? AND descripcion = ?", sqlParams, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        long id=-1;
        if(tm.length>0)id=tm[0].getIdtipomaterial();
        else return false;

        //realizo la eliminación

        int result=-1;
        try {
            result = dao.delete(new TipomaterialPK(id), cn);
        } catch (TipomaterialException ex) {
            Logger.getLogger(GestorTipoMaterialDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorTipoMaterialDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(result>0)return true;
        else return false;
    }
}
