/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.negocio.gestores.ViewDetallePedidoCotizacion;
import metalsoft.negocio.gestores.ViewDetalleProducto;
import metalsoft.negocio.gestores.ViewPedidoEnListadoProcedimientos;
import metalsoft.util.Fecha;

/**
 *
 * @author Nino
 */
public class AccessViews {

    public static ViewDetallePedidoCotizacion detallePedidoCotizacion(long id, Connection cn)
    {
        ViewDetallePedidoCotizacion view=null;
        String query="SELECT nroproducto,nombre,descripcion,cantidad,precio,idproducto,iddetalle,idpedido"+
                     " FROM viewdetallepedidocotizacion"+
                     " WHERE idproducto=?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = cn.prepareStatement(query);
            ps.setLong(1, id);
            rs=ps.executeQuery();
            if(rs.next())
            {
                view=new ViewDetallePedidoCotizacion();
                view.setCantidad(rs.getInt("cantidad"));
                view.setDescripcion(rs.getString("descripcion"));
                view.setIdProducto(rs.getLong("idproducto"));
                view.setIdDetalle(rs.getLong("iddetalle"));
                view.setIdPedido(rs.getLong("idpedido"));
                view.setNombreProducto(rs.getString("nombre"));
                view.setNumeroProducto(rs.getInt("nroproducto"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccessViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return view;
    }

    public static LinkedList<ViewDetallePedidoCotizacion> listDetallePedidoCotizacion(long idPedido, Connection cn)
    {
        ViewDetallePedidoCotizacion view=null;
        LinkedList<ViewDetallePedidoCotizacion> ll=new LinkedList<ViewDetallePedidoCotizacion>();
        String query="SELECT nroproducto,nombre,descripcion,cantidad,precio,idproducto,iddetalle,idpedido"+
                     " FROM viewdetallepedidocotizacion"+
                     " WHERE idpedido=?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = cn.prepareStatement(query);
            ps.setLong(1, idPedido);
            rs=ps.executeQuery();
            while(rs.next())
            {
                view=new ViewDetallePedidoCotizacion();
                view.setCantidad(rs.getInt("cantidad"));
                view.setDescripcion(rs.getString("descripcion"));
                view.setIdProducto(rs.getLong("idproducto"));
                view.setIdDetalle(rs.getLong("iddetalle"));
                view.setIdPedido(rs.getLong("idpedido"));
                view.setNombreProducto(rs.getString("nombre"));
                view.setNumeroProducto(rs.getInt("nroproducto"));
                ll.addLast(view);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccessViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ll;
    }

    public static LinkedList<ViewDetalleProducto> listDetalleProducto(long idProdcuto, Connection cn)
    {
        ViewDetalleProducto view=null;
        LinkedList<ViewDetalleProducto> ll=new LinkedList<ViewDetalleProducto>();
        String query="SELECT nombrepieza,descripcion,cantidadpiezas,dimensiones,nombretipomaterial,"+
                     "idpieza,iddetalle,idproducto"+
                     " FROM viewdetalleproducto"+
                     " WHERE idproducto=?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = cn.prepareStatement(query);
            ps.setLong(1, idProdcuto);
            rs=ps.executeQuery();
            while(rs.next())
            {
                view=new ViewDetalleProducto();
                view.setCantidad(rs.getInt("cantidadpiezas"));
                view.setDescripcion(rs.getString("descripcion"));
                view.setIdProducto(rs.getLong("idproducto"));
                view.setIdDetalle(rs.getLong("iddetalle"));
                view.setIdPieza(rs.getLong("idpieza"));
                view.setIdProducto(rs.getLong("idproducto"));
                view.setNombrePieza(rs.getString("nombrepieza"));
                view.setNombreTipoMaterial(rs.getString("nombretipomaterial"));
                ll.addLast(view);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccessViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ll;
    }

    public static LinkedList<ViewPedidoEnListadoProcedimientos> pedidoEnListadoProcedimientos(Connection cn)
    {
        ViewPedidoEnListadoProcedimientos view=null;
        LinkedList<ViewPedidoEnListadoProcedimientos> ll=new LinkedList<ViewPedidoEnListadoProcedimientos>();
        String query="SELECT nropedido,nropedidocotizacioncliente,fechapedidocotizacion,fecharequeridacotizacion,"+
                     "fechaentregaestipulada,espedidoweb,estado,prioridad,cliente,idpedido,idestado"+
                     " FROM viewpedidoenlistadoprocedimientos"+
                     " WHERE idestado=1";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = cn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                view=new ViewPedidoEnListadoProcedimientos();
                view.setCliente(rs.getString("cliente"));
                view.setEspedidoweb(rs.getBoolean("espedidoweb"));
                view.setEstado(rs.getString("estado"));
                
//                String f=Fecha.parseToString(rs.getDate("fechaentregaestipulada").getTime());
//                view.setFechaentregaestipulada(Fecha.parseToDate(f));
//                f=Fecha.parseToString(rs.getDate("fechapedidocotizacion").getTime());
//                view.setFechapedidocotizacion(Fecha.parseToDate(f));
//                f=Fecha.parseToString(rs.getDate("fecharequeridacotizacion").getTime());
//                view.setFecharequeridacotizacion(Fecha.parseToDate(f));
                view.setFechaentregaestipulada(rs.getDate("fechaentregaestipulada"));
                view.setFechapedidocotizacion(rs.getDate("fechapedidocotizacion"));
                view.setFecharequeridacotizacion(rs.getDate("fecharequeridacotizacion"));
                view.setIdestado(rs.getLong("idestado"));
                view.setIdpedido(rs.getLong("idpedido"));
                view.setNropedido(rs.getInt("nropedido"));
                view.setNropedidocotizacioncliente(rs.getInt("nropedidocotizacioncliente"));
                view.setPrioridad(rs.getString("prioridad"));
                ll.addLast(view);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccessViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(ll.isEmpty())return null;
        else return ll;
    }
}
