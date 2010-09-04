/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.DetallepiezapresupuestoDB;
import metalsoft.datos.dbobject.DetallepresupuestoDB;
import metalsoft.datos.dbobject.DetalleproductopresupuestoDB;
import metalsoft.datos.dbobject.MateriaprimaDB;
import metalsoft.datos.dbobject.PedidoDB;
import metalsoft.datos.dbobject.PiezaDB;
import metalsoft.datos.dbobject.PresupuestoDB;
import metalsoft.datos.idao.DetallepresupuestoDAO;
import metalsoft.negocio.access.AccessDetallePiezaPresupuesto;
import metalsoft.negocio.access.AccessDetallePresupuesto;
import metalsoft.negocio.access.AccessDetalleProductoPresupuesto;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.access.AccessMateriaPrima;
import metalsoft.negocio.access.AccessPedido;
import metalsoft.negocio.access.AccessPieza;
import metalsoft.negocio.access.AccessPresupuesto;
import metalsoft.negocio.access.AccessViews;
import metalsoft.util.Calculos;
import metalsoft.util.sort.Sorts;

/**
 *
 * @author Nino
 */
public class GestorDetalleMateriaPrima {
    private ArrayList<PiezaXMateriaPrima> arlPiezasXMateriaPrima;

    public GestorDetalleMateriaPrima() {
        arlPiezasXMateriaPrima=null;
    }


    public LinkedList<ViewPedidoEnListadoProcedimientos> buscarPedidosCDetalleProcedimientos() {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewPedidoEnListadoProcedimientos> list=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.pedidosSegunEstado(IdsEstadoPedido.PEDIDOCONDETALLEDEPROCEDIMIENTOS,cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorDetalleMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
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

    public LinkedList<ViewMateriaPrima> obtenerAllMateriaPrima() {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewMateriaPrima> list=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.allMateriaPrima(cn);
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

    public MateriaprimaDB buscarMateriaPrimaDePieza(long idPieza) {
        PostgreSQLManager pg=new PostgreSQLManager();
        MateriaprimaDB matprimaDB=null;
        PiezaDB piezaDB=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            piezaDB=AccessPieza.findByIdpieza(idPieza,cn);
            matprimaDB=AccessMateriaPrima.findById(piezaDB.getMateriaprima(), cn);
            cn.commit();
        } catch (Exception ex) {
            Logger.getLogger(GestorDetalleMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorDetalleMateriaPrima.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return matprimaDB;
    }

    /*
     * 0: no se pudo agregar
     * -1: se agrego correctamente
     * 1: se modifico correctamente
     */
    public int addPiezaXMateriaPrima(PiezaXMateriaPrima pxmp) {
        if(arlPiezasXMateriaPrima==null)arlPiezasXMateriaPrima=new ArrayList<PiezaXMateriaPrima>();
        int index=pxmp.contain(pxmp,arlPiezasXMateriaPrima);
        int result=-1;
        try
        {
            //si obtuve un indice mayor a -1 entonces
            //voy a modificar el objeto
            if(index>-1)
            {
                arlPiezasXMateriaPrima.add(index, pxmp);
                result = 1;
            }
            //si no obtuve un indice mayor a -1 entonces
            //voy a insertar un nuevo objeto
            else
            {
                boolean b=arlPiezasXMateriaPrima.add(pxmp);
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

    public boolean guardarMateriaPrimaPiezaPresupuesto() {
        if(arlPiezasXMateriaPrima.isEmpty())return false;

        boolean flag=false;

        long idPed=-1;
        long idPro=-1;
        long idPi=-1;
        long idDetPedido=-1;

        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        long idPres=-1;

        Object[] obj=Sorts.seleccion(arlPiezasXMateriaPrima);
        arlPiezasXMateriaPrima.clear();
        for(int r=0;r<obj.length;r++)
        {
            arlPiezasXMateriaPrima.add((PiezaXMateriaPrima) obj[r]);
            System.out.println(arlPiezasXMateriaPrima.get(r).getIdPedido()+" "+arlPiezasXMateriaPrima.get(r).getIdDetallePedido()+" "+arlPiezasXMateriaPrima.get(r).getIdProducto()+" "+arlPiezasXMateriaPrima.get(r).getIdPieza());
        }

        Iterator<PiezaXMateriaPrima> iter=arlPiezasXMateriaPrima.iterator();
        PiezaXMateriaPrima pxmp=null;
        //para cada conjunto de etapas de una pieza
        try
        {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            while(iter.hasNext())
            {
                pxmp=iter.next();
                idPed=pxmp.getIdPedido();
                idPro=pxmp.getIdProducto();
                idPi=pxmp.getIdPieza();
                idDetPedido=pxmp.getIdDetallePedido();

                //busco el pedido para obtener el id de presupuesto
                PedidoDB pedDB=AccessPedido.findByIdPedido(idPed, cn);
                idPres=pedDB.getPresupuesto();

                //obtengo el presupuesto y busco sus detalles
                PresupuestoDB presDB=AccessPresupuesto.findByIdPresupuesto(idPres,cn);
                DetallepresupuestoDB[] vDetPresDB=AccessDetallePresupuesto.findByIdPresupuestoORDERBYIdProducto(idPres,cn);

                //para cada detalle de presupuesto que estan ordenados segun el numero de producto voy buscando
                //los detalles de producto presupuesto para actualizar en cada uno la materia prima
                long idDetPres=-1;
                DetalleproductopresupuestoDB[] vDetProdPresDB=null;
                DetallepresupuestoDB detPresDB=null;
                for(int i=0;i<vDetPresDB.length;i++)
                {
                    detPresDB=vDetPresDB[i];
                    idDetPres=detPresDB.getIddetalle();
                    vDetProdPresDB=AccessDetalleProductoPresupuesto.findByIdDetallePresupuesto(idDetPres,cn);
                    DetalleproductopresupuestoDB detProdPresDB=null;
                    //para cada detalle producto presupuesto busco las PiezaXMateriaPrima que tengan el idproducto
                    //igual al del detallepresupuesto y el idpieza para cada detalleproductopresupuesto
                    PiezaXMateriaPrima aux=null;
                    for(int j=0;j<vDetProdPresDB.length;j++)
                    {
                        detProdPresDB=vDetProdPresDB[j];
                        aux=buscarEnArlPiezaXMateriaPrima(idPed,detPresDB.getIddetallepedido(),detPresDB.getIdproducto(),detProdPresDB.getIdpieza());
                        detProdPresDB.setIdmateriaprima(aux.getIdMateriaPrima());

                        int capacidadMatPrima=calcularCapacidadMateriaPrima(aux.getAltoMatPrima(),aux.getAnchoMatPrima(),aux.getLargoMatPrima(),aux.getAltoPieza(),aux.getAnchoPieza(),aux.getLargoPieza(),aux.getNombrePieza(),aux.getMateriaPrima().getNombreMateriaPrima());
                        AccessDetalleProductoPresupuesto.update(detProdPresDB,cn);
                    }

                }
                //actualizo el pedido con estado PEDIDOCONDETALLEDEMATERIAPRIMA
//                AccessPedido.update(idPed,idPres,IdsEstadoPedido.PEDIDOCONDETALLEDEMATERIAPRIMA, cn);
//
//                DetalleProductoPresupuesto dpropre=new DetalleProductoPresupuesto();
//                long idDetProPre=AccessDetalleProductoPresupuesto.insert(dpropre,idDetPres,idPi,cn);
//
//                //tengo que recorrer todas las etapas seleccionadas para la pieza
//                //y guardar un DetallePiezaPresupuesto por cada combinacion de
//                //la pieza con cada etapa
//                DetallePiezaPresupuesto dpipre=new DetallePiezaPresupuesto();
//                Iterator<ViewEtapaDeProduccion> iEtapas=pxe.getEtapas().iterator();
//                ViewEtapaDeProduccion v=null;
//                long idDetPiPres=-1;
//                while(iEtapas.hasNext())
//                {
//                    v=iEtapas.next();
//                    Date duracion=dpipre.calcularDuracion(v.getDuracionEstimada(),pxe.getAlto(),pxe.getAncho(),pxe.getLargo());
//                    dpipre.setDuracionEtapaXPieza(duracion);
//                    idDetPiPres=AccessDetallePiezaPresupuesto.insert(dpipre,idDetProPre,v.getIdetapa(),cn);
//                }
            }
//            cn.commit();
//            flag=true;
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

    private PiezaXMateriaPrima buscarEnArlPiezaXMateriaPrima(long idPed, long iddetallepedido, long idproducto, long idpieza) {
        Iterator<PiezaXMateriaPrima> iter=arlPiezasXMateriaPrima.iterator();
        PiezaXMateriaPrima x=null;
        while(iter.hasNext())
        {
            x=iter.next();
            if(idPed==x.getIdPedido() && iddetallepedido==x.getIdDetallePedido() && idpieza==x.getIdPieza() && idproducto==x.getIdProducto())
                return x;
        }
        return x;

    }

    //-1: cancelar
    private int calcularCapacidadMateriaPrima(double altoMatPrima, double anchoMatPrima, double largoMatPrima, double altoPieza, double anchoPieza, double largoPieza,String nombrePieza,String nombreMatPrima) {
        double volumenPieza=altoPieza*anchoPieza*largoPieza;
        double volumenMatPrima=altoMatPrima*anchoMatPrima*largoMatPrima;
        int respuesta=-1;
        if(volumenPieza>volumenMatPrima)respuesta=JOptionPane.showConfirmDialog(null, "El volumen de la Pieza '"+nombrePieza+"' es mayor que el volumen de la materia prima '"+nombreMatPrima+"' seleccionada, desea continuar?","ATENCIÓN",JOptionPane.OK_CANCEL_OPTION);
        if(respuesta==JOptionPane.CANCEL_OPTION)return -1;

        //calculo los valores max med y min de la pieza y materia prima, donde los valores de la pieza
        //tienen que ser iguales o menores a los respectivos valores de la materia prima.
        double maxMatPrima=Calculos.mayor(altoMatPrima, anchoMatPrima, largoMatPrima);
        double minMatPrima=Calculos.menor(altoMatPrima, anchoMatPrima, largoMatPrima);
        double medMatPrima=Calculos.medio(altoMatPrima, anchoMatPrima, largoMatPrima);
        double maxPieza=Calculos.mayor(altoPieza, anchoPieza, largoPieza);
        double minPieza=Calculos.menor(altoPieza, anchoPieza, largoPieza);
        double medPieza=Calculos.medio(altoPieza, anchoPieza, largoPieza);

        if(maxPieza>maxMatPrima || medPieza>medMatPrima || minPieza>minMatPrima)respuesta=JOptionPane.showConfirmDialog(null, "Las dimensiones de la Pieza '"+nombrePieza+"' son mayores que la materia prima '"+nombreMatPrima+"' seleccionada, desea continuar?","ATENCIÓN",JOptionPane.OK_CANCEL_OPTION);
        if(respuesta==JOptionPane.CANCEL_OPTION)return -1;

        int entranEnMax=(int) (maxMatPrima / maxPieza);
        int entranEnMed=(int) (medMatPrima / medPieza);
        int entranEnMin=(int) (minMatPrima / minPieza);

        int entranTotal=entranEnMax*entranEnMed*entranEnMin;

        return entranTotal;
    }



}
