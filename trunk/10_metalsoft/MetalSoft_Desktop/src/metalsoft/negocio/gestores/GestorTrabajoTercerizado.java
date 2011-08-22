/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

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
import metalsoft.datos.jpa.controller.TrabajotercerizadoJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Detalletrabajotercerizado;
import metalsoft.datos.jpa.entity.Empresametalurgica;
import metalsoft.datos.jpa.entity.Estadodetalletrabajotercerizado;
import metalsoft.datos.jpa.entity.Estadotrabajotercerizado;
import metalsoft.datos.jpa.entity.Etapadeproduccion;
import metalsoft.datos.jpa.entity.Pedido;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.access.AccessViews;


/**
 *
 * @author Vicky
 */
public class GestorTrabajoTercerizado {
    public List<Pedido> buscarPedidosNoFinalizados()
    {
        return JpaUtil.getPedidosNoFinalizados();
    }
    public List<Pedido> buscarPedidosNoFinalizadosLIKE(String param)
    {
        return JpaUtil.getPedidosNoFinalizadosLIKE(param);
    }
    public LinkedList<ViewDetallePedidoCotizacion> buscarDetallePedido(long idPed) {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewDetallePedidoCotizacion> list=null;
        ViewDetallePedidoCotizacion v=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.listDetallePedidoCotizacion(idPed, cn);
            Iterator<ViewDetallePedidoCotizacion> iter=list.iterator();
            while(iter.hasNext())
            {
                v=iter.next();
                int cantPiezas=AccessFunctions.cantPiezasXProducto(v.getIdProducto(), cn);
                v.setCantidadPiezas(cantPiezas);
            }
        } catch (Exception ex) {
            Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    public LinkedList<ViewDetalleProducto> buscarDetalleProducto(long idPro) {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewDetalleProducto> list=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.listDetalleProducto(idPro, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    public long generarNumeroPedido() {
        long result=-1;
        PostgreSQLManager pg=null;
        Connection cn=null;
        pg=new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
            result=AccessFunctions.nvoNroTrabajoTercerizado(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorTrabajoTercerizado.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorTrabajoTercerizado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
    public Etapadeproduccion buscarEtapa(long id)
    {
        EtapadeproduccionJpaController controller=new EtapadeproduccionJpaController();
        return controller.findEtapadeproduccion(id);
    }
    public Estadotrabajotercerizado buscarEstadoGenerado(){
        EstadotrabajotercerizadoJpaController controller=new EstadotrabajotercerizadoJpaController();
        return controller.findEstadotrabajotercerizado((long)1);
    }
    public Estadodetalletrabajotercerizado buscarEstadoGeneradoDetalle(){
        EstadodetalletrabajotercerizadoJpaController controller=new EstadodetalletrabajotercerizadoJpaController();
        return controller.findEstadodetalletrabajotercerizado((long)1);
    }
    public boolean nuevoPedido(metalsoft.datos.jpa.entity.Trabajotercerizado trabajo, LinkedList<Detalletrabajotercerizado> detalle)
    {
        TrabajotercerizadoJpaController controller=new TrabajotercerizadoJpaController();
        DetalletrabajotercerizadoJpaController controllerDetalle=new DetalletrabajotercerizadoJpaController();
        try {
            trabajo.setEstado(buscarEstadoGenerado());
            controller.create(trabajo);
            for(Detalletrabajotercerizado de : detalle){
                de.setEstado(buscarEstadoGeneradoDetalle());
                de.setTrabajotercerizado(trabajo);
                controllerDetalle.create(de);
            }
        } catch (PreexistingEntityException ex) {
            Logger.getLogger(GestorTrabajoTercerizado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (Exception ex) {
            Logger.getLogger(GestorTrabajoTercerizado.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
    public Empresametalurgica buscarEmpresa(long id){
        EmpresametalurgicaJpaController controller=new EmpresametalurgicaJpaController();
        return controller.findEmpresametalurgica(id);
    }

}
