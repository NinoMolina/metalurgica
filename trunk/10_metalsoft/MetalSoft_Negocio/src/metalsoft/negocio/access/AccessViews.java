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
import metalsoft.negocio.gestores.ViewPedidoNoConfirmado;
import metalsoft.negocio.gestores.ViewPedidoNoPlanificado;
import metalsoft.negocio.gestores.ViewPlanificacion;
import metalsoft.negocio.gestores.ViewProcesoCalidad;
import metalsoft.negocio.gestores.ViewProcesoCalidadXPiezaPresupuesto;
import metalsoft.negocio.gestores.ViewProductoPresupuesto;
import metalsoft.negocio.gestores.ViewProveedorXMateriaPrima;
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
                     "nombremateriaprima,preciomateriaprima,cantmateriaprima,canttotal,preciototal, "+
                     "idpresupuesto,iddetallepresupuesto,iddetalleproductopresupuesto, "+
                     "idproducto,idpieza,idmateriaprima,idproveedor"+
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
                view.setIdproveedor(rs.getLong("idproveedor"));
                view.setNombremateriaprima(rs.getString("nombremateriaprima"));
                view.setNombrepieza(rs.getString("nombrepieza"));
                view.setNombreproducto(rs.getString("nombreproducto"));
                view.setNroproducto(rs.getLong("nroproducto"));
                view.setPreciomateriaprima(rs.getDouble("preciomateriaprima"));
                view.setPreciototal(rs.getDouble("preciototal"));
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

    public static LinkedList<ViewPlanificacion> planificacionConRecursosAsignados(long estado, Connection cn)
    {
        ViewPlanificacion view=null;
        LinkedList<ViewPlanificacion> ll=new LinkedList<ViewPlanificacion>();
        String query="SELECT nropedido,nropedidocotizacioncliente,razonsocial,prioridad,fechaentregaestipulada, "+
                    "idpedido,idpresupuesto,idcliente,idprioridad,idestado "+
                     " FROM viewpedidosconrecasignados"+
                     " WHERE idestado="+estado;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = cn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                view=new ViewPlanificacion();
                view.setIdcliente(rs.getLong("idcliente"));
                view.setRazonsocial(rs.getString("razonsocial"));
                view.setFechaentregaestipulada(rs.getDate("fechaentregaestipulada"));
                view.setIdpedido(rs.getLong("idpedido"));
                view.setIdpresupuesto(rs.getLong("idpresupuesto"));
                view.setNropedido(rs.getInt("nropedido"));
                view.setIdprioridad(rs.getLong("idprioridad"));
                view.setNropedidocotizacioncliente(rs.getInt("nropedidocotizacioncliente"));
                view.setPrioridad(rs.getString("prioridad"));
                view.setIdestado(rs.getLong("idestado"));
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

    public static LinkedList<ViewPedidoNoConfirmado> allPedidosNoConfirmados(Connection cn) {
        ViewPedidoNoConfirmado view=null;
        LinkedList<ViewPedidoNoConfirmado> ll=new LinkedList<ViewPedidoNoConfirmado>();
        String query="SELECT nropedido,nropedcotcli,fechapedido,estado,nropresupuesto,montototal,vencimientopresupuesto,razonsocial,idpedido,idpresupuesto,idestado,idcliente"+
                     " FROM viewpedidosnoconfirmados";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = cn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                view=new ViewPedidoNoConfirmado();
                view.setEstado(rs.getString("estado"));
                view.setFechapedido(rs.getDate("fechapedido"));
                view.setIdcliente(rs.getLong("idcliente"));
                view.setIdestado(rs.getLong("idestado"));
                view.setIdpedido(rs.getLong("idpedido"));
                view.setIdpresupuesto(rs.getLong("idpresupuesto"));
                view.setMontototal(rs.getDouble("montototal"));
                view.setNropedcotcli(rs.getLong("nropedcotcli"));
                view.setNropedido(rs.getLong("nropedido"));
                view.setNropresupuesto(rs.getLong("nropresupuesto"));
                view.setRazonsocial(rs.getString("razonsocial"));
                view.setVencimientopresupuesto(rs.getDate("vencimientopresupuesto"));
                ll.addLast(view);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccessViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ll;
    }

    public static LinkedList<ViewPedidoNoPlanificado> allPedidosNoPlanificados(Connection cn) {
        ViewPedidoNoPlanificado view=null;
        LinkedList<ViewPedidoNoPlanificado> ll=new LinkedList<ViewPedidoNoPlanificado>();
        String query="SELECT nropedido,nropedcotcli,fechapedido,estado,nropresupuesto,montototal,nrocliente,razonsocial,idpedido,idpresupuesto,idestado,idcliente"+
                     " FROM viewpedidosnoplanificados";
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = cn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                view=new ViewPedidoNoPlanificado();
                view.setEstado(rs.getString("estado"));
                view.setFechapedido(rs.getDate("fechapedido"));
                view.setIdcliente(rs.getLong("idcliente"));
                view.setIdestado(rs.getLong("idestado"));
                view.setIdpedido(rs.getLong("idpedido"));
                view.setIdpresupuesto(rs.getLong("idpresupuesto"));
                view.setMontototal(rs.getDouble("montototal"));
                view.setNropedcotcli(rs.getLong("nropedcotcli"));
                view.setNropedido(rs.getLong("nropedido"));
                view.setNropresupuesto(rs.getLong("nropresupuesto"));
                view.setRazonsocial(rs.getString("razonsocial"));
                view.setNrocliente(rs.getLong("nrocliente"));
                ll.addLast(view);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccessViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ll;
    }

    public static LinkedList<ViewProveedorXMateriaPrima> listProveedorXMateriaPrima(long idMatPrima,Connection cn) {
        ViewProveedorXMateriaPrima view=null;
        LinkedList<ViewProveedorXMateriaPrima> ll=new LinkedList<ViewProveedorXMateriaPrima>();
        String query="SELECT nroproveedor,razonsocial,condicioniva,provincia,telefono,mail,responsable,precio,idproveedor,idresponsable,idmateriaprima"+
                     " FROM viewproveedorxmateriaprima" +
                     " WHERE idmateriaprima="+idMatPrima;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = cn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                view=new ViewProveedorXMateriaPrima();
                view.setCondicioniva(rs.getString("condicioniva"));
                view.setIdmateriaprima(rs.getLong("idmateriaprima"));
                view.setIdproveedor(rs.getLong("idproveedor"));
                view.setIdresponsable(rs.getLong("idresponsable"));
                view.setMail(rs.getString("mail"));
                view.setNroproveedor(rs.getLong("nroproveedor"));
                view.setPrecio(rs.getDouble("precio"));
                view.setProvincia(rs.getString("provincia"));
                view.setRazonsocial(rs.getString("razonsocial"));
                view.setResponsable(rs.getString("responsable"));
                view.setTelefono(rs.getString("telefono"));
                ll.addLast(view);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccessViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ll;
    }

    public static LinkedList<ViewProductoPresupuesto> listProductoPresupuesto(long idPedido, Connection cn) {
        ViewProductoPresupuesto view=null;
        LinkedList<ViewProductoPresupuesto> ll=new LinkedList<ViewProductoPresupuesto>();
        String query="SELECT cantidadproducto,nombreproducto,preciounitario,importe,idpedido,idpresupuesto,iddetallepresupuesto,idproducto"+
                     " FROM viewproductopresupuesto" +
                     " WHERE idpedido="+idPedido;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            ps = cn.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next())
            {
                view=new ViewProductoPresupuesto();
                view.setCantidadproducto(rs.getInt("cantidadproducto"));
                view.setIddetallepresupuesto(rs.getLong("iddetallepresupuesto"));
                view.setIdpedido(rs.getLong("idpedido"));
                view.setIdpresupuesto(rs.getLong("idpresupuesto"));
                view.setIdproducto(rs.getLong("idproducto"));
                view.setImporte(rs.getDouble("importe"));
                view.setNombreproducto(rs.getString("nombreproducto"));
                view.setPreciounitario(rs.getDouble("preciounitario"));

                ll.addLast(view);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccessViews.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ll;
    }
}
