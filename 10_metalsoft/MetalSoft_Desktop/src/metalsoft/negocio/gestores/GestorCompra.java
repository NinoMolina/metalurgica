/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.awt.Dialog.ModalExclusionType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JList;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dao.CompraDAOImpl;
import metalsoft.datos.dao.DetallecompraDAOImpl;
import metalsoft.datos.dao.EstadocompraDAOImpl;
import metalsoft.datos.dao.EstadodetallecompraDAOImpl;
import metalsoft.datos.dbobject.CompraDB;
import metalsoft.datos.dbobject.DetallecompraDB;
import metalsoft.datos.dbobject.EstadocompraDB;
import metalsoft.datos.dbobject.EstadodetallecompraDB;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.ProveedorJpaController;
import metalsoft.datos.jpa.entity.Proveedor;
import metalsoft.negocio.access.AccessCompra;
import metalsoft.negocio.almacenamiento.MateriaPrima;
import metalsoft.negocio.compras.Compra;
import metalsoft.util.ItemCombo;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Mariana
 */
public class GestorCompra implements IBuscador {

    private List<MateriaPrima> materiaprima;
    private String numeroCompra;
    private Proveedor prov;
    private ArrayList<ViewDetalleCompra> arlDetalleAEliminar;
    private long idOrden;
    private LinkedList<ViewDetalleCompra> filasDetalle;
    private ArrayList arlIdsPiezasDetalleOrden;

    public String getnumero() {
        return numeroCompra;
    }

    public void setnumero(String numero) {
        numeroCompra = numero;
    }
    private List<Proveedor> proveedores;

    public List<Proveedor> getProv() {
        return proveedores;
    }

    public Proveedor getproveedor() {
        return prov;
    }

    public void setproveedor(Proveedor p) {
        prov = p;
    }

    public List<MateriaPrima> getMP() {
        return materiaprima;
    }

    public void setMateriaprima(List<MateriaPrima> materiaprima) {
        this.materiaprima = materiaprima;
    }

    public MateriaPrima getMateriaprimaSeleccionada(String id) {
        long idLong = Long.parseLong(id);
        return searchMateriaprimaById(idLong);
    }

    public MateriaPrima searchMateriaprimaById(long id) {
        for (MateriaPrima mp : materiaprima) {
            if (mp.getCodProducto() == id) {
                return mp;
            }
        }
        return null;
    }

    private ArrayList crearDetalleCompra(Compra compra) {
        ArrayList arlDetalle = new ArrayList();
        Iterator<ViewDetalleCompra> iter = filasDetalle.iterator();
        while (iter.hasNext()) {
            // TO-DO
        }
        if (!arlDetalle.isEmpty()) {
            return arlDetalle;
        } else {
            return null;
        }
    }

    public void cargarComboProveedor(JComboBox combo) {
        proveedores = null;
        ProveedorJpaController controller = new ProveedorJpaController(JpaUtil.getEntityManagerFactory());
        proveedores = controller.findProveedorEntities();
        ItemCombo item = null;
        combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
        for (Proveedor p : proveedores) {
            item = new ItemCombo();
            item.setId(String.valueOf(p.getIdproveedor()));
            item.setMostrar(p.getRazonsocial());
            combo.addItem(item);
        }
        combo.setSelectedIndex(0);
    }

    public void setListaDetalle(LinkedList<ViewDetalleCompra> filas) {
        filasDetalle = filas;
    }

    public JList getList(String className) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public JComboBox getCombo(String className) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setBusqueda(Object[] obj) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void imprimirOrden(Long id){
        PostgreSQLManager pg = new PostgreSQLManager();
//        System.out.println(sourceFile);
        JasperPrint jasperPrint = null;
        Connection cn = null;
        Map param = new HashMap();
        JasperReport masterReport = null;

        try {
            cn = pg.concectGetCn();

            masterReport = (JasperReport) JRLoader.loadObject(getClass().getResource("/metalsoft/reportes/RptOrdenDeCompra.jasper"));

            param.put("ID_PEDIDO", new Long(id));
//            JRResultSetDataSource rsDatparam.put("ID_PEDIDO", new Long(pedidoSeleccionadoDB.getIdpedido()));aSource = new JRResultSetDataSource(rs);
            jasperPrint = JasperFillManager.fillReport(masterReport, param, cn);


            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
            jviewer.setTitle("Orden de compra");
            jviewer.setVisible(true);


        } catch (Exception ex) {
            Logger.getLogger(GestorListadoMateriaPrimaAComprar.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorListadoMateriaPrimaAComprar.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public boolean registrarOrden() {
        try {
            //Registrar la compra
            CompraDB compra = new CompraDB();
            compra.setNrocompra(Integer.parseInt((this.getnumero())));
            Date fechaCompra = new Date(new java.util.Date().getTime());
            compra.setFechacompra(fechaCompra);
            Date vigencia = new Date(fechaCompra.getYear(), fechaCompra.getMonth() + 2, fechaCompra.getDate());
            compra.setVigencia(vigencia);

            EstadocompraDAOImpl daoEstadoCompra = new EstadocompraDAOImpl();
            PostgreSQLManager pg = new PostgreSQLManager();
            Connection con = null;
            con = pg.concectGetCn();
            con.setAutoCommit(false);
            EstadocompraDB estado = daoEstadoCompra.findByPrimaryKey(1, con);
            compra.setEstado(estado.getIdestado());

            metalsoft.negocio.compras.Proveedor proveedor = new metalsoft.negocio.compras.Proveedor();
            proveedor.setNroProveedor(Integer.parseInt(prov.getIdproveedor().toString()));
            compra.setProveedor(proveedor.getNroProveedor());

            compra.setDocumentoremito(0);
            double monto=0d;
            Iterator<ViewDetalleCompra> iter = filasDetalle.iterator();
            ViewDetalleCompra datos = null;
            
            while (iter.hasNext()) {
                datos = iter.next();
                monto = monto + datos.getCantidad() * datos.getPrecioProv();
            }
            compra.setPreciototal(monto);
            compra.setMotivo("");

            CompraDAOImpl daoCompra = new CompraDAOImpl();
            int result = daoCompra.insert(compra, con);
            if (result < 0) {
                return false;
            } else {
                con.commit();
            }

            //Registrar los detalles de la compra
            EstadodetallecompraDAOImpl daoEstadoDetalleCompra = new EstadodetallecompraDAOImpl();
            EstadodetallecompraDB estadoDetalle = daoEstadoDetalleCompra.findByPrimaryKey(1, con);
            String idCompra = daoCompra.getUltimoIDCompra(con);

            DetallecompraDAOImpl daoDetalleCompra = new DetallecompraDAOImpl();
            
            while (iter.hasNext()) {
                datos = iter.next();
                DetallecompraDB detalleCompra = new DetallecompraDB();
                detalleCompra.setCantidad(datos.getCantidad());
                detalleCompra.setEstado(estadoDetalle.getIdestado());
                detalleCompra.setIdcompra(Long.parseLong(idCompra));
                detalleCompra.setMateriaprima(datos.getIdMateriaPrima());
                detalleCompra.setPreciohistorico(0);
                daoDetalleCompra.insert(detalleCompra, con);
            }

            con.commit();
            return true;

        } catch (Exception ex) {
            Logger.getLogger(GestorNuevoUsuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean modificarOrden() {
        int result1, result2 = -1;
        boolean result3 = false;
        PostgreSQLManager pg = null;
        Connection cn = null;
        pg = new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
            CompraDAOImpl daoCompra = new CompraDAOImpl();
            DetallecompraDAOImpl daoDetalleCompra = new DetallecompraDAOImpl();
            result1 = daoDetalleCompra.eliminarDetalle(cn, idOrden);
            if (result1 != -1) {
                result2 = daoCompra.eliminarCompra(cn, idOrden);
                if (result2 != -1) {
                    result3 = this.registrarOrden();
                }
                return result3;
            }
        } catch (Exception ex) {
            Logger.getLogger(GestorCompra.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result3;
    }

    public long getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(long idOrden) {
        this.idOrden = idOrden;
    }

    public String generarNuevoNumeroOrden() {
        String result = "";
        PostgreSQLManager pg = null;
        Connection cn = null;
        pg = new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
            CompraDAOImpl daoCompra = new CompraDAOImpl();
            result = daoCompra.getUltimoNumeroCompra(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorCompra.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public CompraDB buscarCompraDB(long idCompra) {
        CompraDB db = null;
        PostgreSQLManager pg = null;
        Connection cn = null;
        try {
            pg = new PostgreSQLManager();
            cn = pg.concectGetCn();
            db = AccessCompra.findById(idCompra, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorCompra.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return db;
    }

    public ArrayList<ViewDetalleCompra> viewDetalleCompra(long idCompra) {
        Connection cn = null;
        ArrayList<ViewDetalleCompra> arl = null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
            arl = AccessCompra.viewDetalleCompra(idCompra, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arl;
    }

    public int cancelarOrden(long idOrden, String text) {
        int result = -1;
        PostgreSQLManager pg = null;
        Connection cn = null;
        pg = new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
            CompraDAOImpl daoCompra = new CompraDAOImpl();
            result = daoCompra.cancelarCompra(cn, idOrden, text);
            return result;
        } catch (Exception ex) {
            Logger.getLogger(GestorCompra.class.getName()).log(Level.SEVERE, null, ex);
            return result;
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
