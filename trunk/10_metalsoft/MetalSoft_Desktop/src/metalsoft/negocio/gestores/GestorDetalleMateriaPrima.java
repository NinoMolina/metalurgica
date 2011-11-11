/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import metalsoft.negocio.gestores.estados.IdsEstadoPedido;
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
import metalsoft.negocio.ventas.DetalleProductoPresupuesto;
import metalsoft.util.Calculos;
import metalsoft.util.sort.Sorts;

/**
 *
 * @author Nino
 */
public class GestorDetalleMateriaPrima {

    private ArrayList<PiezaXMateriaPrima> arlPiezasXMateriaPrima;

    public GestorDetalleMateriaPrima() {
        arlPiezasXMateriaPrima = null;
    }

    public LinkedList<ViewPedidoEnListadoProcedimientos> buscarPedidosCDetalleProcedimientos() {
        PostgreSQLManager pg = new PostgreSQLManager();
        LinkedList<ViewPedidoEnListadoProcedimientos> list = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            list = AccessViews.pedidosSegunEstado(IdsEstadoPedido.PEDIDOCONDETALLEDEPROCEDIMIENTOS, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorDetalleMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
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

    public LinkedList<ViewMateriaPrima> obtenerAllMateriaPrima() {
        PostgreSQLManager pg = new PostgreSQLManager();
        LinkedList<ViewMateriaPrima> list = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            list = AccessViews.allMateriaPrima(cn);
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

    public MateriaprimaDB buscarMateriaPrimaDePieza(long idPieza) {
        PostgreSQLManager pg = new PostgreSQLManager();
        MateriaprimaDB matprimaDB = null;
        PiezaDB piezaDB = null;
        Connection cn = null;
        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            piezaDB = AccessPieza.findByIdpieza(idPieza, cn);
            matprimaDB = AccessMateriaPrima.findById(piezaDB.getMateriaprima(), cn);
            cn.commit();
        } catch (Exception ex) {
            Logger.getLogger(GestorDetalleMateriaPrima.class.getName()).log(Level.SEVERE, null, ex);
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorDetalleMateriaPrima.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
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
        if (arlPiezasXMateriaPrima == null) {
            arlPiezasXMateriaPrima = new ArrayList<PiezaXMateriaPrima>();
        }
        int index = pxmp.contain(pxmp, arlPiezasXMateriaPrima);
        int result = -1;
        try {
            //si obtuve un indice mayor a -1 entonces
            //voy a modificar el objeto
            if (index > -1) {
                arlPiezasXMateriaPrima.set(index, pxmp);
                result = 1;
            } //si no obtuve un indice mayor a -1 entonces
            //voy a insertar un nuevo objeto
            else {
                boolean b = arlPiezasXMateriaPrima.add(pxmp);
                if (b) {
                    result = -1;
                } else {
                    result = 0;
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            result = 0;
        }
        return result;
    }

    public boolean guardarMateriaPrimaPiezaPresupuesto() {
        if (arlPiezasXMateriaPrima.isEmpty()) {
            return false;
        }

        boolean flag = false;

        long idPed = -1;
        long idPro = -1;
        long idPi = -1;
        long idDetPedido = -1;
        long idDetPres = -1;
        PostgreSQLManager pg = new PostgreSQLManager();
        Connection cn = null;
        long idPres = -1;

        Object[] obj = Sorts.seleccion(arlPiezasXMateriaPrima);
        arlPiezasXMateriaPrima.clear();
        for (int r = 0; r < obj.length; r++) {
            arlPiezasXMateriaPrima.add((PiezaXMateriaPrima) obj[r]);
            System.out.println(arlPiezasXMateriaPrima.get(r).getIdPedido() + " " + arlPiezasXMateriaPrima.get(r).getIdDetallePedido() + " " + arlPiezasXMateriaPrima.get(r).getIdProducto() + " " + arlPiezasXMateriaPrima.get(r).getIdPieza());
        }

        Iterator<PiezaXMateriaPrima> iter = arlPiezasXMateriaPrima.iterator();
        PiezaXMateriaPrima pxmp = null;

        //para cada conjunto de etapas de una pieza
        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            //boolean noFirst=false;
            int cantPiezasDePedido = -1;
            while (iter.hasNext()) {
                pxmp = iter.next();
                PresupuestoDB presDB = null;
                if (idPed != pxmp.getIdPedido()) {
                    if (cantPiezasDePedido == 0) {
                        AccessPedido.update(idPed, idPres, IdsEstadoPedido.PEDIDOCONDETALLEDEMATERIAPRIMA, cn);
                    }
                    //noFirst=true;
                    idPed = pxmp.getIdPedido();
                    cantPiezasDePedido = AccessFunctions.cantPiezasDePedido2(idPed, cn);
                    PedidoDB pedDB = AccessPedido.findByIdPedido(idPed, cn);
                    idPres = pedDB.getPresupuesto();

                    //obtengo el presupuesto (para que?)
                    presDB = AccessPresupuesto.findByIdPresupuesto(idPres, cn);

                }

                DetallepresupuestoDB detPresDB = null;
                DetalleproductopresupuestoDB detProdPresDB = null;
                //si el id producto anterior es distinto al nuevo
                //creo un nuevo detalle de presupuesto
                if (idPro != pxmp.getIdProducto()) {
                    idPro = pxmp.getIdProducto();
                    idDetPedido = pxmp.getIdDetallePedido();

                    detPresDB = AccessDetallePresupuesto.findByIdPresupuestoANDIdProducto(idPres, idPro, cn);
                    idDetPres = detPresDB.getIddetalle();
                }

                idPi = pxmp.getIdPieza();
                detProdPresDB = AccessDetalleProductoPresupuesto.findByIdDetallePresupuestoANDIdPieza(idDetPres, idPi, cn);
                detProdPresDB.setIdmateriaprima(pxmp.getIdMateriaPrima());
                int capacidadMatPrima = DetalleProductoPresupuesto.calcularCapacidadMateriaPrima(pxmp.getAltoMatPrima(), pxmp.getAnchoMatPrima(), pxmp.getLargoMatPrima(), pxmp.getAltoPieza(), pxmp.getAnchoPieza(), pxmp.getLargoPieza(), pxmp.getNombrePieza(), pxmp.getMateriaPrima().getNombreMateriaPrima());
                System.out.println("######### CAPACIDAD MATERIA PRIMA: " + capacidadMatPrima);
                int cantPiezas = pxmp.getCantidadPieza();
                System.out.println("######### CANTIDAD PIEZAS: " + cantPiezas);
                int cantMateriaPrima = DetalleProductoPresupuesto.calcularCantidadMateriaPrima(capacidadMatPrima, cantPiezas);
                System.out.println("######### CANTIDAD MATERIA PRIMA: " + cantMateriaPrima);
                detProdPresDB.setCantmateriaprima(cantMateriaPrima);

                //actualizo el detalle producto presupuesto agregando la materia prima
                //y la cantidad necesaria.
                AccessDetalleProductoPresupuesto.update(detProdPresDB, cn);
                cantPiezasDePedido--;
                if (cantPiezasDePedido == 0 && !iter.hasNext()) {
                    AccessPedido.update(idPed, idPres, IdsEstadoPedido.PEDIDOCONDETALLEDEMATERIAPRIMA, cn);
                }
            }
            cn.commit();
            flag = true;
        } catch (Exception ex) {
            try {
                Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
                cn.rollback();
                flag = false;
            } catch (SQLException ex1) {
                Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex1);
                flag = false;
            }

        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorDetalleProcedimientos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return flag;

    }
}
