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
import metalsoft.negocio.gestores.IdsEstadoPedido;
import metalsoft.negocio.gestores.ViewDetallePedidoCotizacion;
import metalsoft.negocio.gestores.ViewDetalleProducto;
import metalsoft.negocio.gestores.ViewEtapaDeProduccion;
import metalsoft.negocio.gestores.ViewEtapasXPiezaPresupuesto;
import metalsoft.negocio.gestores.ViewMateriaPrima;
import metalsoft.negocio.gestores.ViewMateriaPrimaXPiezaPresupuesto;
import metalsoft.negocio.gestores.ViewPedidoEnListadoProcedimientos;
import metalsoft.negocio.gestores.ViewProcesoCalidad;
import metalsoft.negocio.gestores.ViewProcesoCalidadXPiezaPresupuesto;
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
                view.setPrecio(rs.getDouble("precio"));
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
                view.setPrecio(rs.getDouble("precio"));
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
        String query="SELECT nombrepieza,descripcion,cantidadpiezas,alto,ancho,largo,nombretipomaterial,"+
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
                view.setCantidadPieza(rs.getInt("cantidadpiezas"));
                view.setDescripcion(rs.getString("descripcion"));
                view.setAlto(rs.getDouble("alto"));
                view.setAncho(rs.getDouble("ancho"));
                view.setLargo(rs.getDouble("largo"));
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

    public static LinkedList<ViewEtapasXPiezaPresupuesto> listEtapasXPiezaPresupuesto(long idPresupuesto, Connection cn)
    {
        ViewEtapasXPiezaPresupuesto view=null;
        LinkedList<ViewEtapasXPiezaPresupuesto> ll=new LinkedList<ViewEtapasXPiezaPresupuesto>();
        String query="SELECT nroproducto,nombreproducto,cantproducto,nombrepieza,cantpieza, "+
                     "nroetapaproduccion,nombreetapaproduccion,duracionetapaxpieza,duraciontotal, "+
                     "idpresupuesto,iddetallepresupuesto,iddetalleproductopresupuesto,iddetallepiezapresupuesto, "+
                     "idproducto,idpieza,idetapaproduccion"+
                     " FROM viewetapasxpiezapresupuesto"+
                     " WHERE idpresupuesto=?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = cn.prepareStatement(query);
            ps.setLong(1, idPresupuesto);
            rs=ps.executeQuery();
            while(rs.next())
            {
                view=new ViewEtapasXPiezaPresupuesto();
                view.setCantpieza(rs.getInt("cantpieza"));
                view.setCantproducto(rs.getInt("cantproducto"));
                view.setDuracionetapaxpieza(rs.getTime("duracionetapaxpieza"));
                view.setDuraciontotal(rs.getString("duraciontotal"));
                view.setIddetallepiezapresupuesto(rs.getLong("iddetallepiezapresupuesto"));
                view.setIddetallepresupuesto(rs.getLong("iddetallepresupuesto"));
                view.setIddetalleproductopresupuesto(rs.getLong("iddetalleproductopresupuesto"));
                view.setIdetapaproduccion(rs.getLong("idetapaproduccion"));
                view.setIdpieza(rs.getLong("idpieza"));
                view.setIdpresupuesto(rs.getLong("idpresupuesto"));
                view.setIdproducto(rs.getLong("idproducto"));
                view.setNombreetapaproduccion(rs.getString("nombreetapaproduccion"));
                view.setNombrepieza(rs.getString("nombrepieza"));
                view.setNombreproducto(rs.getString("nombreproducto"));
                view.setNroetapaproduccion(rs.getLong("nroetapaproduccion"));
                view.setNroproducto(rs.getLong("nroproducto"));
                ll.addLast(view);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccessViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ll;
    }

    public static LinkedList<ViewMateriaPrimaXPiezaPresupuesto> listMateriaPrimaXPiezaPresupuesto(long idPresupuesto, Connection cn)
    {
        ViewMateriaPrimaXPiezaPresupuesto view=null;
        LinkedList<ViewMateriaPrimaXPiezaPresupuesto> ll=new LinkedList<ViewMateriaPrimaXPiezaPresupuesto>();
        String query="SELECT nroproducto,nombreproducto,cantproducto,nombrepieza,cantpieza, "+
                     "nombremateriaprima,cantmateriaprima,canttotal, "+
                     "idpresupuesto,iddetallepresupuesto,iddetalleproductopresupuesto, "+
                     "idproducto,idpieza,idmateriaprima"+
                     " FROM viewmpxpiezapresupuesto"+
                     " WHERE idpresupuesto=?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = cn.prepareStatement(query);
            ps.setLong(1, idPresupuesto);
            rs=ps.executeQuery();
            while(rs.next())
            {
                view=new ViewMateriaPrimaXPiezaPresupuesto();
                view.setCantpieza(rs.getInt("cantpieza"));
                view.setCantproducto(rs.getInt("cantproducto"));
                view.setCantmateriaprima(rs.getInt("cantmateriaprima"));
                view.setCanttotal(rs.getInt("canttotal"));
                view.setIddetallepresupuesto(rs.getLong("iddetallepresupuesto"));
                view.setIddetalleproductopresupuesto(rs.getLong("iddetalleproductopresupuesto"));
                view.setIdmateriaprima(rs.getLong("idmateriaprima"));
                view.setIdpieza(rs.getLong("idpieza"));
                view.setIdpresupuesto(rs.getLong("idpresupuesto"));
                view.setIdproducto(rs.getLong("idproducto"));
                view.setNombremateriaprima(rs.getString("nombremateriaprima"));
                view.setNombrepieza(rs.getString("nombrepieza"));
                view.setNombreproducto(rs.getString("nombreproducto"));
                view.setNroproducto(rs.getLong("nroproducto"));
                ll.addLast(view);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccessViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ll;
    }

    public static LinkedList<ViewProcesoCalidadXPiezaPresupuesto> listProCalidadXPiezaPresupuesto(long idPresupuesto, Connection cn)
    {
        ViewProcesoCalidadXPiezaPresupuesto view=null;
        LinkedList<ViewProcesoCalidadXPiezaPresupuesto> ll=new LinkedList<ViewProcesoCalidadXPiezaPresupuesto>();
        String query="SELECT nroproducto,nombreproducto,cantproducto,nombrepieza,cantpieza, "+
                     "nroprocesocalidad,nombreprocesocalidad,cantprocesocalidad,duracionprocalidadxpieza,duraciontotal, "+
                     "idpresupuesto,iddetallepresupuesto,iddetalleproductopresupuesto,iddetallepiezacalidadpresupuesto, "+
                     "idproducto,idpieza,idprocesocalidad"+
                     " FROM viewprocalidadxpiezapresupesto"+
                     " WHERE idpresupuesto=?";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = cn.prepareStatement(query);
            ps.setLong(1, idPresupuesto);
            rs=ps.executeQuery();
            while(rs.next())
            {
                view=new ViewProcesoCalidadXPiezaPresupuesto();
                view.setCantpieza(rs.getInt("cantpieza"));
                view.setCantprocesocalidad(rs.getInt("cantprocesocalidad"));
                view.setCantproducto(rs.getInt("cantproducto"));
                view.setDuracionprocalidadxpieza(rs.getTime("duracionprocalidadxpieza"));
                view.setDuraciontotal(rs.getString("duraciontotal"));
                view.setIddetallepiezacalidadpresupuesto(rs.getLong("iddetallepiezacalidadpresupuesto"));
                view.setIddetallepresupuesto(rs.getLong("iddetallepresupuesto"));
                view.setIddetalleproductopresupuesto(rs.getLong("iddetalleproductopresupuesto"));
                view.setIdprocesocalidad(rs.getLong("idprocesocalidad"));
                view.setIdpieza(rs.getLong("idpieza"));
                view.setIdpresupuesto(rs.getLong("idpresupuesto"));
                view.setIdproducto(rs.getLong("idproducto"));
                view.setNombreprocesocalidad(rs.getString("nombreprocesocalidad"));
                view.setNombrepieza(rs.getString("nombrepieza"));
                view.setNombreproducto(rs.getString("nombreproducto"));
                view.setNroprocesocalidad(rs.getLong("nroprocesocalidad"));
                view.setNroproducto(rs.getLong("nroproducto"));
                ll.addLast(view);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccessViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ll;
    }

    public static LinkedList<ViewPedidoEnListadoProcedimientos> pedidosSegunEstado(long estado, Connection cn)
    {
        ViewPedidoEnListadoProcedimientos view=null;
        LinkedList<ViewPedidoEnListadoProcedimientos> ll=new LinkedList<ViewPedidoEnListadoProcedimientos>();
        String query="SELECT nropedido,nropedidocotizacioncliente,fechapedidocotizacion,fecharequeridacotizacion,"+
                     "fechaentregaestipulada,espedidoweb,estado,prioridad,cliente,idpedido,idestado"+
                     " FROM viewpedidoendetalleprocedimientos"+
                     " WHERE idestado="+estado;
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

    public static LinkedList<ViewEtapaDeProduccion> allEtapasDeProduccion(Connection cn) {
        ViewEtapaDeProduccion view=null;
        LinkedList<ViewEtapaDeProduccion> ll=new LinkedList<ViewEtapaDeProduccion>();
        String query="SELECT numero,nombre,horashombre,horasmaquina,duracionestimada,idetapa"+
                     " FROM viewetapadeproduccion";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = cn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                view=new ViewEtapaDeProduccion();
                view.setIdetapa(rs.getLong("idetapa"));
                view.setNombre(rs.getString("nombre"));
                view.setNumero(rs.getInt("numero"));
                view.setHorasHombre(rs.getTime("horashombre"));
                view.setHorasMaquina(rs.getTime("horasmaquina"));
                view.setDuracionEstimada(rs.getTime("duracionestimada"));
                ll.addLast(view);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccessViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ll;
    }

    public static LinkedList<ViewMateriaPrima> allMateriaPrima(Connection cn) {
        ViewMateriaPrima view=null;
        LinkedList<ViewMateriaPrima> ll=new LinkedList<ViewMateriaPrima>();
        String query="SELECT nombremateriaprima,descripcion,alto,ancho,largo,unidadmedida,tipomaterial,idmateriaprima"+
                     " FROM viewmateriaprima";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = cn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                view=new ViewMateriaPrima();
                view.setAlto(rs.getDouble("alto"));
                view.setAncho(rs.getDouble("ancho"));
                view.setDescripcion(rs.getString("descripcion"));
                view.setIdmateriaprima(rs.getLong("idmateriaprima"));
                view.setLargo(rs.getDouble("largo"));
                view.setNombreMateriaPrima(rs.getString("nombremateriaprima"));
                view.setTipomaterial(rs.getString("tipomaterial"));
                view.setUnidadmedida(rs.getString("unidadmedida"));
                ll.addLast(view);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccessViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ll;
    }

    public static LinkedList<ViewEtapaDeProduccion> etapasDeProduccionILIKE(String valor,Connection cn) {
        ViewEtapaDeProduccion view=null;
        LinkedList<ViewEtapaDeProduccion> ll=new LinkedList<ViewEtapaDeProduccion>();
        String query="SELECT numero,nombre,idetapa"+
                     " FROM viewetapadeproduccion"+
                     " WHERE nombre ILIKE '"+valor+"%'";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = cn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                view=new ViewEtapaDeProduccion();
                view.setIdetapa(rs.getLong("idetapa"));
                view.setNombre(rs.getString("nombre"));
                view.setNumero(rs.getInt("numero"));
                ll.addLast(view);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccessViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ll;
    }

    public static LinkedList<ViewProcesoCalidad> allProcesosCalidad(Connection cn) {
        ViewProcesoCalidad view=null;
        LinkedList<ViewProcesoCalidad> ll=new LinkedList<ViewProcesoCalidad>();
        String query="SELECT nroproceso,nombreproceso,duracionestimada,herramienta,nombreaccioncalidad,idprocesocalidad"+
                     " FROM viewprocesocalidad";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = cn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                view=new ViewProcesoCalidad();
                view.setNroproceso(rs.getLong("nroproceso"));
                view.setNombreproceso(rs.getString("nombreproceso"));
                view.setNombreaccioncalidad(rs.getString("nombreaccioncalidad"));
                view.setHerramienta(rs.getString("herramienta"));
                view.setDuracionestimada(rs.getTime("duracionestimada"));
                view.setIdprocesocalidad(rs.getLong("idprocesocalidad"));
                ll.addLast(view);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccessViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ll;
    }
}
