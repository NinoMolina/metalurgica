/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.DetallepresupuestoDB;
import metalsoft.datos.dbobject.PedidoDB;
import metalsoft.datos.dbobject.PresupuestoDB;
import metalsoft.negocio.access.AccessDetallePresupuesto;
import metalsoft.negocio.access.AccessPedido;
import metalsoft.negocio.access.AccessPresupuesto;
import metalsoft.negocio.access.AccessViews;

/**
 *
 * @author Nino
 */
public class GestorPresupuesto {
    private PedidoDB pedidoSeleccionadoDB;
    private PresupuestoDB presupuestoPedSelecDB;
    private DetallepresupuestoDB[] vDetallePresupuestoDB;

    public GestorPresupuesto() {
    }

    public LinkedList<ViewPedidoEnListadoProcedimientos> buscarPedidosConDetalleProcesoCalidad() {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewPedidoEnListadoProcedimientos> list=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.pedidosSegunEstado(IdsEstadoPedido.PEDIDOCONDETALLEDEPROCESOSDECALIDAD,cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public PedidoDB buscarPedidoSeleccionado(long idPed) {
        PostgreSQLManager pg=new PostgreSQLManager();
        PedidoDB db=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            db=AccessPedido.findByIdPedido(idPed, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return db;
    }

    public PresupuestoDB buscarPresupuestoDePedido(long idPresupuesto) {
        PostgreSQLManager pg=new PostgreSQLManager();
        PresupuestoDB db=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            db=AccessPresupuesto.findByIdPresupuesto(idPresupuesto, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return db;
    }

    public long buscarNroPresupuesto(long idPed) {
        pedidoSeleccionadoDB=buscarPedidoSeleccionado(idPed);
        presupuestoPedSelecDB=buscarPresupuestoDePedido(pedidoSeleccionadoDB.getPresupuesto());
        return presupuestoPedSelecDB.getNropresupuesto();
    }

    public void calcularTotales() {
        PostgreSQLManager pg=new PostgreSQLManager();
        PresupuestoDB db=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            vDetallePresupuestoDB=AccessDetallePresupuesto.findByIdPresupuesto(presupuestoPedSelecDB.getIdpresupuesto(), cn);
            //vDetalleProductoPresupuestoDB=buscarDetallesProdPresupuesto(vDetallePresupuestoDB)
        } catch (Exception ex) {
            Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ;

    }

    public LinkedList<ViewEtapasXPiezaPresupuesto> buscarEtapasXPiezaPresupuesto() {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewEtapasXPiezaPresupuesto> list=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.listEtapasXPiezaPresupuesto(presupuestoPedSelecDB.getIdpresupuesto(), cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public LinkedList<ViewMateriaPrimaXPiezaPresupuesto> buscarMatPrimaXPiezaPresupuesto() {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewMateriaPrimaXPiezaPresupuesto> list=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.listMateriaPrimaXPiezaPresupuesto(presupuestoPedSelecDB.getIdpresupuesto(), cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    public LinkedList<ViewProcesoCalidadXPiezaPresupuesto> buscarProCalidadXPiezaPresupuesto() {
        PostgreSQLManager pg=new PostgreSQLManager();
        LinkedList<ViewProcesoCalidadXPiezaPresupuesto> list=null;
        Connection cn=null;
        try {
            cn = pg.concectGetCn();
            list=AccessViews.listProCalidadXPiezaPresupuesto(presupuestoPedSelecDB.getIdpresupuesto(), cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPresupuesto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }


}
