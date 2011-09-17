/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.DetalletrabajotercerizadoJpaController;
import metalsoft.datos.jpa.controller.EmpresametalurgicaJpaController;
import metalsoft.datos.jpa.controller.EstadodetalletrabajotercerizadoJpaController;
import metalsoft.datos.jpa.controller.EstadotrabajotercerizadoJpaController;
import metalsoft.datos.jpa.controller.EtapadeproduccionJpaController;
import metalsoft.datos.jpa.controller.PiezaJpaController;
import metalsoft.datos.jpa.controller.TrabajotercerizadoJpaController;
import metalsoft.datos.jpa.controller.exceptions.IllegalOrphanException;
import metalsoft.datos.jpa.controller.exceptions.NonexistentEntityException;
import metalsoft.datos.jpa.entity.Detalletrabajotercerizado;
import metalsoft.datos.jpa.entity.Empresametalurgica;
import metalsoft.datos.jpa.entity.Estadodetalletrabajotercerizado;
import metalsoft.datos.jpa.entity.Estadotrabajotercerizado;
import metalsoft.datos.jpa.entity.Etapadeproduccion;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.datos.jpa.entity.Pieza;
import metalsoft.datos.jpa.entity.Trabajotercerizado;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.access.AccessViews;

/**
 *
 * @author Vicky
 */
public class GestorTrabajoTercerizado {

    
    public List<Pedido> buscarPedidosNoFinalizados() {
        return JpaUtil.getPedidosNoFinalizados();
    }

    public List<Pedido> buscarPedidosNoFinalizadosLIKE(String param) {
        return JpaUtil.getPedidosNoFinalizadosLIKE(param);
    }

    public LinkedList<ViewDetallePedidoCotizacion> buscarDetallePedido(long idPed) {
        PostgreSQLManager pg = new PostgreSQLManager();
        LinkedList<ViewDetallePedidoCotizacion> list = null;
        ViewDetallePedidoCotizacion v = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            list = AccessViews.listDetallePedidoCotizacion(idPed, cn);
            Iterator<ViewDetallePedidoCotizacion> iter = list.iterator();
            while (iter.hasNext()) {
                v = iter.next();
                int cantPiezas = AccessFunctions.cantPiezasXProducto(v.getIdProducto(), cn);
                v.setCantidadPiezas(cantPiezas);
            }
        } catch (Exception ex) {
            Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public LinkedList<ViewDetalleProducto> buscarDetalleProducto(long idPro) {
        PostgreSQLManager pg = new PostgreSQLManager();
        LinkedList<ViewDetalleProducto> list = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            list = AccessViews.listDetalleProducto(idPro, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public long generarNumeroPedido() {
        long result = -1;
        PostgreSQLManager pg = null;
        Connection cn = null;
        pg = new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
            result = AccessFunctions.nvoNroTrabajoTercerizado(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorTrabajoTercerizado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorTrabajoTercerizado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public Etapadeproduccion buscarEtapa(long id) {
        EtapadeproduccionJpaController controller = new EtapadeproduccionJpaController(JpaUtil.getEntityManagerFactory());
        return controller.findEtapadeproduccion(id);
    }
    //BUSQUEDA DE ESTADOS DE TRABAJO TERCERIZADO
    public Estadotrabajotercerizado buscarEstadoGenerado() {
        EstadotrabajotercerizadoJpaController controller = new EstadotrabajotercerizadoJpaController(JpaUtil.getEntityManagerFactory());
        return controller.findEstadotrabajotercerizado((long) 1);
    }
    public Estadotrabajotercerizado buscarEstadoEnEsperaPresupuesto() {
        EstadotrabajotercerizadoJpaController controller = new EstadotrabajotercerizadoJpaController(JpaUtil.getEntityManagerFactory());
        return controller.findEstadotrabajotercerizado((long) 2);
    }
    public Estadotrabajotercerizado buscarEstadoPresupuestado() {
        EstadotrabajotercerizadoJpaController controller = new EstadotrabajotercerizadoJpaController(JpaUtil.getEntityManagerFactory());
        return controller.findEstadotrabajotercerizado((long) 3);
    }
    public Estadotrabajotercerizado buscarEstadoConfirmado() {
        EstadotrabajotercerizadoJpaController controller = new EstadotrabajotercerizadoJpaController(JpaUtil.getEntityManagerFactory());
        return controller.findEstadotrabajotercerizado((long) 4);
    }
    public Estadotrabajotercerizado buscarEstadoCancelado() {
        EstadotrabajotercerizadoJpaController controller = new EstadotrabajotercerizadoJpaController(JpaUtil.getEntityManagerFactory());
        return controller.findEstadotrabajotercerizado((long) 5);
    }
    public Estadotrabajotercerizado buscarEstadoPlanificadoParaControlDeCalidad() {
        EstadotrabajotercerizadoJpaController controller = new EstadotrabajotercerizadoJpaController(JpaUtil.getEntityManagerFactory());
        return controller.findEstadotrabajotercerizado((long) 6);
    }

    //BUSQUEDA DE ESTADOS DE DETALLES DE TRABAJO TERCERIZADO
    public Estadodetalletrabajotercerizado buscarEstadoGeneradoDetalle() {
        EstadodetalletrabajotercerizadoJpaController controller = new EstadodetalletrabajotercerizadoJpaController(JpaUtil.getEntityManagerFactory());
        return controller.findEstadodetalletrabajotercerizado((long) 1);
    }

    //NUEVO PEDIDO DE COTIZACION DE TRABAJO TERCERIZADO
    public boolean nuevoPedido(metalsoft.datos.jpa.entity.Trabajotercerizado trabajo, LinkedList<ViewPedidoCotizacion> detalle) {
        TrabajotercerizadoJpaController controller = new TrabajotercerizadoJpaController(JpaUtil.getEntityManagerFactory());
        DetalletrabajotercerizadoJpaController controllerDetalle = new DetalletrabajotercerizadoJpaController(JpaUtil.getEntityManagerFactory());
        try {
            
            trabajo.setEstado(buscarEstadoGenerado());
            controller.create(trabajo);
            
            //long c=JpaUtil.getUltimoDetalleTrabajoTercerizado().getDetalletrabajotercerizadoPK().getIddetalle();
            for (ViewPedidoCotizacion v : detalle) {
                //c=c+1;
                //DetalletrabajotercerizadoPK pk=new DetalletrabajotercerizadoPK(c,99);
                Detalletrabajotercerizado de = new Detalletrabajotercerizado();
                de.setCantidad(v.getCantidad());
                de.setPieza(BigInteger.valueOf(v.getIdPieza()));
                de.setProceso(buscarEtapa(v.getIdetapa()));
                de.setEstado(buscarEstadoGeneradoDetalle());
                de.setIdtrabajotercerizado(trabajo);
                controllerDetalle.create(de);
            }

        } catch (Exception ex) {
            Logger.getLogger(GestorTrabajoTercerizado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public Empresametalurgica buscarEmpresa(long id) {
        EmpresametalurgicaJpaController controller = new EmpresametalurgicaJpaController(JpaUtil.getEntityManagerFactory());
        return controller.findEmpresametalurgica(id);
    }
    public List<Trabajotercerizado> obtenerTrabajosGenerados()
    {
        List<Trabajotercerizado> list=new LinkedList<Trabajotercerizado>();
        list=JpaUtil.getTrabajosTercerizadosByEstado((long)1);
        return list;
    }
    public List<Trabajotercerizado> obtenerTrabajosEnviados()
    {
        List<Trabajotercerizado> list=new LinkedList<Trabajotercerizado>();
        list=JpaUtil.getTrabajosTercerizadosByEstado((long)2);
        return list;
    }
    public List<Trabajotercerizado> obtenerTrabajosPresupuestados()
    {
        List<Trabajotercerizado> list=new LinkedList<Trabajotercerizado>();
        list=JpaUtil.getTrabajosTercerizadosByEstado((long)3);
        return list;
    }
    public List<Trabajotercerizado> obtenerTrabajosACancelar()
    {
        List<Trabajotercerizado> list=new LinkedList<Trabajotercerizado>();
        list=JpaUtil.getTrabajosTercerizadosCanCancel();
        return list;
    }
    public long modificarTrabajoTercerizado(Trabajotercerizado trb){
        TrabajotercerizadoJpaController con=new TrabajotercerizadoJpaController(JpaUtil.getEntityManagerFactory());
        try {
            con.edit(trb);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(GestorTrabajoTercerizado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(GestorTrabajoTercerizado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GestorTrabajoTercerizado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trb.getIdtrabajo();
    }
    public long modificarTrabajoTercerizado(Trabajotercerizado trb, List<Detalletrabajotercerizado> detalle){
        TrabajotercerizadoJpaController con=new TrabajotercerizadoJpaController(JpaUtil.getEntityManagerFactory());
        DetalletrabajotercerizadoJpaController conDe=new DetalletrabajotercerizadoJpaController(JpaUtil.getEntityManagerFactory());
        try {
            for(Detalletrabajotercerizado de : detalle){
                conDe.edit(de);
            }
            con.edit(trb);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(GestorTrabajoTercerizado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(GestorTrabajoTercerizado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GestorTrabajoTercerizado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trb.getIdtrabajo();
    }
    public Pieza buscarPieza(long id){
        PiezaJpaController con=new PiezaJpaController(JpaUtil.getEntityManagerFactory());
        return con.findPieza(id);
    }
    public List<Detalletrabajotercerizado> buscarDetalleTrabajoTercerizado(long id){
        return (List<Detalletrabajotercerizado>)JpaUtil.getDetalleTrabajoTercerizadoByTrabajo(id);
    }
}
