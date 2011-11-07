/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.ProductoDB;
import metalsoft.datos.dbobject.ProductoPKDB;
import metalsoft.datos.exception.ProductoException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.ProductoDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.gestores.ViewDetalleProducto;
import metalsoft.negocio.ventas.Producto;

/**
 *
 * @author Nino
 */
public class AccessProducto {

    public static long insert(Producto x, Connection cn) {
        long result=-1;
        ProductoDAO dao=new DAOFactoryImpl().createProductoDAO();
        ProductoDB db = null;

        try {
            db=Parser.parseToProductoDB(x);
            result=dao.insert(db, cn);
            db.setIdproducto(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static ProductoDB[] findByNombreILIKE(String valor, Connection cn) {
        ProductoDB[] x=null;
        ProductoDAO dao=new DAOFactoryImpl().createProductoDAO();
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            x = dao.findExecutingUserWhere("nombre ILIKE '"+valor+"%'", sqlParams, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }

    public static ProductoDB findById(long idProducto, Connection cn) {
        ProductoDB[] vDB=null;
        ProductoDB db=null;
        ProductoDAO dao=new DAOFactoryImpl().createProductoDAO();
        try {
            vDB = dao.findByIdproducto(idProducto, cn);
            db=vDB[0];
        } catch (ProductoException ex) {
            Logger.getLogger(AccessProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }

    public static ArrayList<ViewDetalleProducto> viewDetalleProducto(long id, Connection cn)
    {
        ArrayList<ViewDetalleProducto> arl=null;
        ViewDetalleProducto view=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        String consulta="SELECT nombrepieza,descripcion,cantidadpiezas,alto,ancho,largo,nombretipomaterial,idpieza,iddetalle,idproducto"+
                        " FROM viewDetalleProducto"+
                        " WHERE idproducto=?";
        try {
            pstmt=cn.prepareStatement(consulta);
            pstmt.setLong(1, id);
            rs=pstmt.executeQuery();
            arl=new ArrayList<ViewDetalleProducto>();
            while(rs.next())
            {
                view=new ViewDetalleProducto();
                view.setNombrePieza(rs.getString("nombrepieza"));
                view.setDescripcion(rs.getString("descripcion"));
                view.setCantidadPieza(rs.getInt("cantidadpiezas"));
                view.setAlto(rs.getDouble("alto"));
                view.setAncho(rs.getDouble("ancho"));
                view.setLargo(rs.getDouble("largo"));
                view.setNombreTipoMaterial(rs.getString("nombretipomaterial"));
                view.setIdPieza(rs.getLong("idpieza"));
                view.setIdDetalle(rs.getLong("iddetalle"));
                view.setIdProducto(rs.getLong("idproducto"));

                arl.add(view);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccessProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arl;
    }

    /**
     * @param id el id del producto
     * @param x el objeto producto de negocio
     * @param cn objeto Connection
     * @return la cantidad de elementos actualizados
     * @exception
     */
    public static long update(long id,Producto x, Connection cn) {
        long result=-1;
        ProductoDAO dao=new DAOFactoryImpl().createProductoDAO();
        ProductoDB db = null;

        try {
            db=Parser.parseToProductoDB(x);
            db.setIdproducto(id);
            result=dao.update(new ProductoPKDB(id),db, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
