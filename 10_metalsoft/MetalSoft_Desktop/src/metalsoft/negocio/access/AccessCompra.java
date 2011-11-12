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
import metalsoft.datos.dbobject.CompraDB;
import metalsoft.datos.dbobject.CompraPKDB;
import metalsoft.datos.exception.CompraException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.CompraDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.gestores.ViewDetalleCompra;
import metalsoft.negocio.compras.Compra;

/**
 *
 * @author Mariana
 */
public class AccessCompra {
    
    public static long insert(Compra x, Connection cn) {
        long result=-1;
        CompraDAO dao=new DAOFactoryImpl().createCompraDAO();
        CompraDB db = null;

        try {
            db=Parser.parseToCompraDB(x);
            result=dao.insert(db, cn);
            db.setIdcompra(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static CompraDB[] findByNumeroILIKE(String valor, Connection cn) {
        CompraDB[] x=null;
        CompraDAO dao=new DAOFactoryImpl().createCompraDAO();
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            x = dao.findByNrocompra(Integer.parseInt(valor), cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }

    public static CompraDB findById(long idCompra, Connection cn) {
        CompraDB[] vDB=null;
        CompraDB db=null;
        CompraDAO dao=new DAOFactoryImpl().createCompraDAO();
        try {
            vDB = dao.findByIdcompra(idCompra, cn);
            db=vDB[0];
        } catch (CompraException ex) {
            Logger.getLogger(AccessCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return db;
    }

    public static ArrayList<ViewDetalleCompra> viewDetalleCompra(long id, Connection cn)
    {
        ArrayList<ViewDetalleCompra> arl=null;
        ViewDetalleCompra view=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;

        String consulta="SELECT detalle.idDetalle, detalle.idCompra, detalle.cantidad, detalle.materiaPrima, detalle.precioHistorico, detalle.estado, mp.nombre, pxm.precio " +
                        "FROM DetalleCompra detalle, MateriaPrima mp, Compra com, proveedorxmateriaprima pxm, proveedor p"+
                        "WHERE detalle.materiaPrima=mp.idMateriaPrima AND com.idcompra=detalle.idcompra AND com.idCompra=? "+
                        "AND pxm.idproveedor = p.idproveedor AND pxm.idmateriaprima = mp.idmateriaprima";
        try {
            pstmt=cn.prepareStatement(consulta);
            pstmt.setLong(1, id);
            rs=pstmt.executeQuery();
            arl=new ArrayList<ViewDetalleCompra>();
            while(rs.next())
            {
                view=new ViewDetalleCompra();
                view.setCantidad(rs.getInt(3));
                view.setIdMateriaPrima(rs.getInt(4));
                view.setNombreMateriaPrima(rs.getString(7));
                view.setPrecioProv(rs.getDouble(8));
                arl.add(view);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccessCompra.class.getName()).log(Level.SEVERE, null, ex);
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
    public static long update(long id,Compra x, Connection cn) {
        long result=-1;
        CompraDAO dao=new DAOFactoryImpl().createCompraDAO();
        CompraDB db = null;

        try {
            db=Parser.parseToCompraDB(x);
            db.setIdcompra(id);
            result=dao.update(new CompraPKDB(id),db, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
