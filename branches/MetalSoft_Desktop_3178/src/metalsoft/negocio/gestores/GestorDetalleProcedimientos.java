/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import metalsoft.negocio.gestores.estados.IdsEstadoPedido;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.PiezaxetapadeproduccionDB;
import metalsoft.negocio.access.AccessDetallePiezaPresupuesto;
import metalsoft.negocio.access.AccessDetallePresupuesto;
import metalsoft.negocio.access.AccessDetalleProductoPresupuesto;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.access.AccessPedido;
import metalsoft.negocio.access.AccessPiezaXEtapaDeProduccion;
import metalsoft.negocio.access.AccessPresupuesto;
import metalsoft.negocio.access.AccessViews;
import metalsoft.negocio.ventas.DetallePiezaPresupuesto;
import metalsoft.negocio.ventas.DetallePresupuesto;
import metalsoft.negocio.ventas.DetalleProductoPresupuesto;
import metalsoft.negocio.ventas.Presupuesto;
import metalsoft.util.sort.Sorts;

/**
 *
 * @author Nino
 */
public class GestorDetalleProcedimientos {

    private ArrayList<PiezaXEtapas> arlPiezaXEtapas;
    public GestorDetalleProcedimientos() {
        arlPiezaXEtapas=null;
    }

    public LinkedList<ViewPedidoEnListadoProcedimientos> buscarPedidosGenerados() {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewPedidoEnListadoProcedimientos> list=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.pedidosSegunEstado(IdsEstadoPedido.GENERADO,cn);
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

    public LinkedList<ViewEtapaDeProduccion> obtenerEtapasDeProduccion()
    {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewEtapaDeProduccion> list=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.allEtapasDeProduccion(cn);
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

    public boolean esProductoSinAlgunaEtapa(long idProd) {
        PostgreSQLManager pg=new PostgreSQLManager();
        boolean result=false;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            result=AccessFunctions.esProductoSinAlgunaEtapa(idProd,cn);
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
        return result;
    }

    public PiezaxetapadeproduccionDB[] buscarEtapasDePieza(long idPieza) {
        PostgreSQLManager pg=new PostgreSQLManager();
        PiezaxetapadeproduccionDB[] result=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            result=AccessPiezaXEtapaDeProduccion.findByIdpieza(idPieza, cn);
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
        return result;
    }

    /*
     * 0: no se pudo agregar
     * -1: se agrego correctamente
     * 1: se modifico correctamente
     */
    public int addPiezaXEtapas(PiezaXEtapas pxe) {
        if(arlPiezaXEtapas==null)arlPiezaXEtapas=new ArrayList<PiezaXEtapas>();
        int index=pxe.contain(pxe,arlPiezaXEtapas);
        int result=-1;
        try
        {
            //si obtuve un indice mayor a -1 entonces
            //voy a modificar el objeto
            if(index>-1)
            {
                arlPiezaXEtapas.set(index, pxe);
                result = 1;
            }
            //si no obtuve un indice mayor a -1 entonces
            //voy a insertar un nuevo objeto
            else
            {
                boolean b=arlPiezaXEtapas.add(pxe);
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

//    private int containPXE(PiezaXEtapas pxe)
//    {
//        Iterator<PiezaXEtapas> i=arlPiezaXEtapas.iterator();
//        PiezaXEtapas x=null;
//        int contador=0;
//        while(i.hasNext())
//        {
//            x=i.next();
//            if(x.compareTo(pxe)==0)return contador;
//            contador++;
//        }
//        return -1;
//    }
    public boolean guardarEtapasPiezaPresupuesto() {
        if(arlPiezaXEtapas.isEmpty())return false;

        boolean flag=false;

        long idPed=-1;
        long idPro=-1;
        long idPi=-1;
        long idDetPedido=-1;
        double precioProd=-1;
        long idDetProPre=-1;
        long idDetPres=-1;
        long idDetPiPres=-1;
        int cantProd=-1;
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        long idPres=-1;

        //lo ordeno para ir actualizando cada pedido en orden
        Object[] obj=Sorts.seleccion(arlPiezaXEtapas);
        arlPiezaXEtapas.clear();
        for(int r=0;r<obj.length;r++)
        {
            arlPiezaXEtapas.add((PiezaXEtapas) obj[r]);
            //System.out.println(arlPiezaXEtapas.get(r).getIdPedido()+" "+arlPiezaXEtapas.get(r).getIdDetallePedido()+" "+arlPiezaXEtapas.get(r).getIdProducto()+" "+arlPiezaXEtapas.get(r).getIdPieza());
        }

        Iterator<PiezaXEtapas> i=arlPiezaXEtapas.iterator();
        PiezaXEtapas pxe=null;


        //para cada conjunto de etapas de una pieza
        //al estar ordenadas aparecen como muestra el sig ejemplo
        //pedido|detPed|producto|pieza
//        1 1 5 6
//        1 1 5 8
//        1 1 5 9
//        1 2 6 6
//        1 2 6 8
//        2 3 4 6
//        2 3 4 8
//        2 5 5 6
//        2 5 5 8
//        2 5 5 9
//        3 6 5 6
//        3 6 5 6
//        3 6 5 8
//        3 6 5 8
//        3 6 5 9
//        6 11 4 6
        try
        {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            int cantPiezasDePedido=-1;
            while(i.hasNext())
            {
                pxe=i.next();
                //si el id pedido anterior es distinto al nuevo entonces
                //creo un nuevo presupuesto para dicho pedido
                if(idPed!=pxe.getIdPedido())
                {
                    if(cantPiezasDePedido==0)
                    {
                        AccessPedido.update(idPed, idPres, IdsEstadoPedido.PEDIDOCONDETALLEDEPROCEDIMIENTOS, cn);
                    }
                    idPed=pxe.getIdPedido();
                    cantPiezasDePedido=AccessFunctions.cantPiezasDePedido2(idPed, cn);

                    Presupuesto pres=new Presupuesto();
                    long nroPresupuesto=AccessFunctions.nvoNroPresupuesto(cn);
                    pres.setNroPresupuesto(nroPresupuesto);
                    idPres=AccessPresupuesto.insert(pres, cn);
                }

                //si el id producto anterior es distinto al nuevo
                //creo un nuevo detalle de presupuesto
                if(idPro!=pxe.getIdProducto())
                {
                    idPro=pxe.getIdProducto();
                    idDetPedido=pxe.getIdDetallePedido();
                    precioProd=pxe.getPrecioProducto();
                    cantProd=pxe.getCantProductos();

                    DetallePresupuesto dpres=new DetallePresupuesto();
                    dpres.setPrecio(precioProd);
                    dpres.setCantidad(cantProd);
                    idDetPres=AccessDetallePresupuesto.insert(dpres,idPres,idDetPedido,idPro,cn);
                }

                idPi=pxe.getIdPieza();
                DetalleProductoPresupuesto dpropre=new DetalleProductoPresupuesto();
                dpropre.setCantidadPieza(pxe.getCantPiezas());
                idDetProPre=AccessDetalleProductoPresupuesto.insert(dpropre,idDetPres,idPi,cn);
                //tengo que recorrer todas las etapas seleccionadas para la pieza
                //y guardar un DetallePiezaPresupuesto por cada combinacion de
                //la pieza con cada etapa
                DetallePiezaPresupuesto dpipre=new DetallePiezaPresupuesto();
                Iterator<ViewEtapaDeProduccion> iEtapas=pxe.getEtapas().iterator();
                ViewEtapaDeProduccion v=null;
                
                while(iEtapas.hasNext())
                {
                    v=iEtapas.next();
                    Date duracion=dpipre.calcularDuracion(v.getDuracionEstimada(),pxe.getAlto(),pxe.getAncho(),pxe.getLargo());
                    dpipre.setDuracionEtapaXPieza(duracion);
                    idDetPiPres=AccessDetallePiezaPresupuesto.insert(dpipre,idDetProPre,v.getIdetapa(),cn);
                }
                cantPiezasDePedido--;
                if(cantPiezasDePedido==0 && !i.hasNext())
                {
                    AccessPedido.update(idPed, idPres, IdsEstadoPedido.PEDIDOCONDETALLEDEPROCEDIMIENTOS, cn);
                }
            }
            cn.commit();
            flag=true;
        }
        catch (Exception ex)
        {
            try {
                Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
                cn.rollback();
                flag=false;
            } catch (SQLException ex1) {
                Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex1);
                flag=false;
            }

        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return flag;

    }

}
