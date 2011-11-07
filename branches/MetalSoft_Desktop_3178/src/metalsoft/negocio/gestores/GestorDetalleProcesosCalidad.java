/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import metalsoft.negocio.gestores.estados.IdsEstadoPedido;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.DetallepresupuestoDB;
import metalsoft.datos.dbobject.DetalleproductopresupuestoDB;
import metalsoft.datos.dbobject.PedidoDB;
import metalsoft.datos.dbobject.PresupuestoDB;
import metalsoft.negocio.access.AccessDetallePiezaCalidadPresupuesto;
import metalsoft.negocio.access.AccessDetallePresupuesto;
import metalsoft.negocio.access.AccessDetalleProductoPresupuesto;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.access.AccessPedido;
import metalsoft.negocio.access.AccessPresupuesto;
import metalsoft.negocio.access.AccessViews;
import metalsoft.negocio.calidad.DetallePiezaCalidadPresupuesto;
import metalsoft.util.sort.Sorts;

/**
 *
 * @author Nino
 */
public class GestorDetalleProcesosCalidad {

    private ArrayList<PiezaXProcesosCalidad> arlPiezaXProcesoCalidad;
    public GestorDetalleProcesosCalidad() {
        arlPiezaXProcesoCalidad=null;
    }

    public LinkedList<ViewPedidoEnListadoProcedimientos> buscarPedidosConDetalleMateriaPrima() {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewPedidoEnListadoProcedimientos> list=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.pedidosSegunEstado(IdsEstadoPedido.PEDIDOCONDETALLEDEMATERIAPRIMA,cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorDetalleProcesosCalidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleProcesosCalidad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
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
            Logger.getLogger(GestorDetalleProcesosCalidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleProcesosCalidad.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorDetalleProcesosCalidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleProcesosCalidad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public LinkedList<ViewProcesoCalidad> obtenerProcesosCalidad() {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewProcesoCalidad> list=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.allProcesosCalidad(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorDetalleProcesosCalidad.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleProcesosCalidad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public int addPiezaXProcesoCalidad(PiezaXProcesosCalidad pxpc) {
        if(arlPiezaXProcesoCalidad==null)arlPiezaXProcesoCalidad=new ArrayList<PiezaXProcesosCalidad>();
        int index=pxpc.contain(pxpc,arlPiezaXProcesoCalidad);
        int result=-1;
        try
        {
            //si obtuve un indice mayor a -1 entonces
            //voy a modificar el objeto
            if(index>-1)
            {
                arlPiezaXProcesoCalidad.set(index, pxpc);
                result = 1;
            }
            //si no obtuve un indice mayor a -1 entonces
            //voy a insertar un nuevo objeto
            else
            {
                boolean b=arlPiezaXProcesoCalidad.add(pxpc);
                if(b)result = -1;
                else result = 0;
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            result=0;
        }
        return result;
    }

    public boolean guardarPiezaXProcesoCalidad() {
        if(arlPiezaXProcesoCalidad.isEmpty())return false;

        boolean flag=false;

        long idPed=-1;
        long idPro=-1;
        long idPi=-1;
        long idDetPedido=-1;
        long idDetProPre=-1;
        long idDetPres=-1;
        long idProCalidad=-1;

        PresupuestoDB presDB=null;
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        long idPres=-1;

        //lo ordeno para ir actualizando cada pedido en orden
        Object[] obj=Sorts.seleccion(arlPiezaXProcesoCalidad);
        arlPiezaXProcesoCalidad.clear();
        for(int r=0;r<obj.length;r++)
        {
            arlPiezaXProcesoCalidad.add((PiezaXProcesosCalidad) obj[r]);
            //System.out.println(arlPiezaXEtapas.get(r).getIdPedido()+" "+arlPiezaXEtapas.get(r).getIdDetallePedido()+" "+arlPiezaXEtapas.get(r).getIdProducto()+" "+arlPiezaXEtapas.get(r).getIdPieza());
        }

        Iterator<PiezaXProcesosCalidad> iter=arlPiezaXProcesoCalidad.iterator();
        PiezaXProcesosCalidad pxpc=null;
        try
        {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            int cantPiezasDePedido=-1;
            while(iter.hasNext())
            {
                pxpc=iter.next();
                //si el id pedido anterior es distinto al nuevo entonces
                //creo un nuevo presupuesto para dicho pedido
                if(idPed!=pxpc.getIdPedido())
                {
                    if(cantPiezasDePedido==0)
                    {
                        AccessPedido.update(idPed, idPres, IdsEstadoPedido.PEDIDOCONDETALLEDEPROCESOSDECALIDAD, cn);
                    }
                    idPed=pxpc.getIdPedido();
                    cantPiezasDePedido=AccessFunctions.cantPiezasDePedido2(idPed, cn);
                    PedidoDB pedDB=AccessPedido.findByIdPedido(idPed, cn);
                    idPres=pedDB.getPresupuesto();

                    //obtengo el presupuesto (para que?)
                    presDB=AccessPresupuesto.findByIdPresupuesto(idPres,cn);
                }

                DetallepresupuestoDB detPresDB=null;
                DetalleproductopresupuestoDB detProdPresDB=null;
                //si el id producto anterior es distinto al nuevo
                //creo un nuevo detalle de presupuesto
                if(idPro!=pxpc.getIdProducto())
                {
                    idPro=pxpc.getIdProducto();
                    idDetPedido=pxpc.getIdDetallePedido();
                    detPresDB=AccessDetallePresupuesto.findByIdPresupuestoANDIdProducto(idPres,idPro,cn);
                    idDetPres=detPresDB.getIddetalle();
                }

                idPi=pxpc.getIdPieza();
                detProdPresDB=AccessDetalleProductoPresupuesto.findByIdDetallePresupuestoANDIdPieza(idDetPres,idPi,cn);

                Iterator<ViewProcesoCalidad> i=pxpc.getProcesoCalidad().iterator();
                ViewProcesoCalidad view=null;
                DetallePiezaCalidadPresupuesto detPiCalPres=null;
                while(i.hasNext())
                {
                    view=i.next();
                    detPiCalPres=new DetallePiezaCalidadPresupuesto();
                    detPiCalPres.setCantidadProcesoCalidad(view.getCantProcesos());
                    Date duracionXPieza=detPiCalPres.calcularDuracionXPieza(view.getDuracionestimada());
                    detPiCalPres.setDuracionXPieza(duracionXPieza);
                    idDetProPre=detProdPresDB.getIddetalle();
                    idProCalidad=view.getIdprocesocalidad();
                    //creo un nuevo DetallePiezaCalidadPresupuestoDB
                    //con los datos necesarios y le paso el id del
                    //detalle producto presupuesto al que hace referencia
                    AccessDetallePiezaCalidadPresupuesto.insert(detPiCalPres,idDetProPre,idProCalidad,cn);
                }
                
                cantPiezasDePedido--;
                if(cantPiezasDePedido==0 && !iter.hasNext())
                {
                    AccessPedido.update(idPed, idPres, IdsEstadoPedido.PEDIDOCONDETALLEDEPROCESOSDECALIDAD, cn);
                }
            }
            cn.commit();
            flag=true;
        }
        catch (Exception ex)
        {
            try {
                Logger.getLogger(GestorDetalleProcesosCalidad.class.getName()).log(Level.SEVERE, null, ex);
                cn.rollback();
                flag=false;
            } catch (SQLException ex1) {
                Logger.getLogger(GestorDetalleProcesosCalidad.class.getName()).log(Level.SEVERE, null, ex1);
                flag=false;
            }

        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleProcesosCalidad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return flag;
    }

}
