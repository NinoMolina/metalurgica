/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JList;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dao.CompraDAOImpl;
import metalsoft.datos.dao.DetallecompraDAOImpl;
import metalsoft.datos.dao.DetallereclamoempresametalurgicaDAOImpl;
import metalsoft.datos.dao.DetallereclamoproveedorDAOImpl;
import metalsoft.datos.dao.DetalletrabajotercerizadoDAOImpl;
import metalsoft.datos.dao.EmpresametalurgicaDAOImpl;
import metalsoft.datos.dao.MateriaprimaDAOImpl;
import metalsoft.datos.dao.PiezaDAOImpl;
import metalsoft.datos.dao.ProveedorDAOImpl;
import metalsoft.datos.dao.ReclamoempresametalurgicaDAOImpl;
import metalsoft.datos.dao.ReclamoproveedorDAOImpl;
import metalsoft.datos.dao.TrabajotercerizadoDAOImpl;
import metalsoft.datos.dbobject.CompraDB;
import metalsoft.datos.dbobject.DetallecompraDB;
import metalsoft.datos.dbobject.DetallereclamoempresametalurgicaDB;
import metalsoft.datos.dbobject.DetallereclamoproveedorDB;
import metalsoft.datos.dbobject.DetalletrabajotercerizadoDB;
import metalsoft.datos.dbobject.EmpresametalurgicaDB;
import metalsoft.datos.dbobject.MateriaprimaDB;
import metalsoft.datos.dbobject.PiezaDB;
import metalsoft.datos.dbobject.ProveedorDB;
import metalsoft.datos.dbobject.ReclamoempresametalurgicaDB;
import metalsoft.datos.dbobject.ReclamoproveedorDB;
import metalsoft.datos.dbobject.TrabajotercerizadoDB;
import metalsoft.datos.exception.DetallecompraException;
import metalsoft.datos.exception.DetallereclamoproveedorException;
import metalsoft.datos.exception.DetalletrabajotercerizadoException;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.EmpresametalurgicaJpaController;
import metalsoft.datos.jpa.controller.PiezaJpaController;
import metalsoft.datos.jpa.entity.Compra;
import metalsoft.datos.jpa.entity.Empresametalurgica;
import metalsoft.datos.jpa.entity.Pieza;
import metalsoft.datos.jpa.entity.Proveedor;
import metalsoft.datos.jpa.entity.Trabajotercerizado;
import metalsoft.negocio.almacenamiento.MateriaPrima;
import metalsoft.negocio.compras.Reclamo;
import metalsoft.negocio.compras.ReclamoProveedor;
import metalsoft.util.ItemCombo;

/**
 *
 * @author Mariana
 */
public class GestorReclamo implements IBuscador {

    private List<MateriaPrima> materiaprima;
    private Proveedor prov;
    private Empresametalurgica emp;
    private String motivo;
    private LinkedList<ViewDetalleReclamo> filasDetalle;
    private String numeroReclamo;
    private List<Compra> compra;
    private List<Trabajotercerizado> trabajo;
    private ProveedorDB prove;
    private long idTransaccion;
    private EmpresametalurgicaDB empre;
    private List<ReclamoProveedor> reclamoProveedor;

    public GestorReclamo() {
    }

    public String getnumero() {
        return numeroReclamo;
    }

    public void setnumero(String numero) {
        numeroReclamo = numero;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public long getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(long idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public List<MateriaPrima> getMateriaprima() {
        return materiaprima;
    }

    public void setMateriaprima(List<MateriaPrima> materiaprima) {
        this.materiaprima = materiaprima;
    }

    public ProveedorDB getProv() {
        return prove;
    }
    public String generarNuevoNumeroReclamoProveedor() {
        String result = "";
        PostgreSQLManager pg = null;
        Connection cn = null;
        pg = new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
            ReclamoproveedorDAOImpl daoReclamoProv = new ReclamoproveedorDAOImpl();
            result = daoReclamoProv.getUltimoNumeroReclamoproveedor(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorReclamo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorReclamo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public long getIdProveedorByName(String nombreProveedor) {
        try {
            ProveedorDAOImpl daoProveedor = new ProveedorDAOImpl();
            PostgreSQLManager pg = new PostgreSQLManager();
            Connection con = null;
            con = pg.concectGetCn();
            con.setAutoCommit(false);
            ProveedorDB[] proveedores = daoProveedor.findByRazonsocial(nombreProveedor, con);
            ProveedorDB proveedor = proveedores[0];
            return proveedor.getIdproveedor();
        } catch (Exception ex) {
            return -1;
        }
    }

    public void setProv(ProveedorDB prov) {
        this.prove = prov;
    }

    public void setEmpMet(EmpresametalurgicaDB empMet) {
        this.empre = empMet;
    }
    private List<Proveedor> proveedores;

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public ProveedorDB getProveedorById(long idProv){
         try {
            ProveedorDAOImpl daoProveedor = new ProveedorDAOImpl();
            PostgreSQLManager pg = new PostgreSQLManager();
            Connection con = null;
            con = pg.concectGetCn();
            con.setAutoCommit(false);
            ProveedorDB[] proveedores = daoProveedor.findByIdproveedor(idProv, con);
            ProveedorDB pro = proveedores[0];
            return pro;
        } catch (Exception ex) {
            return null;
        }
    }

    public int compareTo(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    private List<Pieza> pieza;

    public List<Pieza> getPieza() {
        return pieza;
    }

    public void setPieza(List<Pieza> pieza) {
        this.pieza = pieza;
    }

    public Pieza getPiezaSeleccionada(String id) {
        long idLong = Long.parseLong(id);
        return searchPiezaById(idLong);
    }

    private Pieza searchPiezaById(long id) {
        for (Pieza p : pieza) {
            if (p.getIdpieza() == id) {
                return p;
            }
        }
        return null;
    }

    public void cargarComboPieza(JComboBox combo) {
        pieza = null;
        PiezaJpaController controller = new PiezaJpaController(JpaUtil.getEntityManagerFactory());
        pieza = controller.findPiezaEntities();
        ItemCombo item = null;
        combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
        for (Pieza p : pieza) {
            item = new ItemCombo();
            item.setId(String.valueOf(p.getIdpieza()));
            item.setMostrar(p.getNombre());
            combo.addItem(item);
        }
        combo.setSelectedIndex(0);
    }
    private List<Empresametalurgica> empresa;

    public List<Empresametalurgica> getEmpresa() {
        return empresa;
    }

    public void setEmpresa(List<Empresametalurgica> empresa) {
        this.empresa = empresa;
    }

    public Empresametalurgica getEmpresaSeleccionada(String id) {
        long idLong = Long.parseLong(id);
        return searchEmpresaById(idLong);
    }

    private Empresametalurgica searchEmpresaById(long id) {
        for (Empresametalurgica empMet : empresa) {
            if (empMet.getIdempresametalurgica() == id) {
                return empMet;
            }
        }
        return null;
    }

    public void cargarComboEmpresa(JComboBox combo) {
        empresa = null;
        EmpresametalurgicaJpaController controller = new EmpresametalurgicaJpaController(JpaUtil.getEntityManagerFactory());
        empresa = controller.findEmpresametalurgicaEntities();
        ItemCombo item = null;
        combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
        for (Empresametalurgica empMet : empresa) {
            item = new ItemCombo();
            item.setId(String.valueOf(empMet.getIdempresametalurgica()));
            item.setMostrar(empMet.getRazonsocial());
            combo.addItem(item);
        }
        combo.setSelectedIndex(0);
    }

    private ArrayList crearDetalleReclamo(Reclamo reclamo) {
        ArrayList arlDetalle = new ArrayList();
        Iterator<ViewDetalleReclamo> iter = filasDetalle.iterator();
        while (iter.hasNext()) {
            // TO-DO
        }
        if (!arlDetalle.isEmpty()) {
            return arlDetalle;
        } else {
            return null;
        }
    }

    public void setListaDetalle(LinkedList<ViewDetalleReclamo> filas) {
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

    public boolean registrarReclamo(int tipo) {
        //Registrar reclamo a Proveedor
        if (tipo == 0) {
            try {
                ReclamoproveedorDB reclamo = new ReclamoproveedorDB();
                String numero = this.generarNuevoNumeroReclamoProveedor();
                reclamo.setNroreclamo(Long.parseLong(numero));
                PostgreSQLManager pg = new PostgreSQLManager();
                Connection con = null;
                con = pg.concectGetCn();
                con.setAutoCommit(false);
                reclamo.setTiporeclamo(1);
                reclamo.setEstado(1);
                metalsoft.negocio.compras.Proveedor proveedor = new metalsoft.negocio.compras.Proveedor();
                proveedor.setNroProveedor(prove.getIdproveedor());
                reclamo.setEntidad(proveedor.getNroProveedor());

                reclamo.setMotivo(motivo);
                reclamo.setCompra(this.getIdTransaccion());

                ReclamoproveedorDAOImpl daoReclamo = new ReclamoproveedorDAOImpl();
                int result = daoReclamo.insert(reclamo, con);
                if (result < 0) {
                    return false;
                } else {
                    con.commit();
                }

                //Registrar los detalles del reclamo
                long idReclamo = Long.parseLong(daoReclamo.getUltimoIDReclamo(con));

                DetallereclamoproveedorDAOImpl daoDetalleReclamoProv = new DetallereclamoproveedorDAOImpl();
                Iterator<ViewDetalleReclamo> iter = filasDetalle.iterator();
                ViewDetalleReclamo datos = null;
                while (iter.hasNext()) {
                    datos = iter.next();
                    DetallereclamoproveedorDB detalleReclamoProv = new DetallereclamoproveedorDB();
                    detalleReclamoProv.setIdreclamo(idReclamo);
                    detalleReclamoProv.setCantidad(datos.getCantidad());
                    detalleReclamoProv.setDescripcion(datos.nombreMateriaPrima);
                    Date fechaEgreso = new Date(new java.util.Date().getTime());
                    detalleReclamoProv.setFechaegreso(fechaEgreso);
                    detalleReclamoProv.setIdcompra(datos.getIdCompra());
                    detalleReclamoProv.setIddetallecompra(datos.getIdDetalle());
                    detalleReclamoProv.setMotivo(datos.getMotivo());
                    // continuar completando con los datos correctos de la BD

                    daoDetalleReclamoProv.insert(detalleReclamoProv, con);
                }

                con.commit();
                return true;

            } catch (Exception ex) {
                Logger.getLogger(GestorReclamo.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {

            try {
                ReclamoempresametalurgicaDB reclamo = new ReclamoempresametalurgicaDB();
                String numero = this.generarNuevoNumeroReclamoEmpresa();
                reclamo.setNroreclamo(Long.parseLong(numero));
                PostgreSQLManager pg = new PostgreSQLManager();
                Connection con = null;
                con = pg.concectGetCn();
                con.setAutoCommit(false);
                reclamo.setTiporeclamo(1);

                metalsoft.negocio.trabajostercerizados.EmpresaMetalurgica empresametalurgica = new metalsoft.negocio.trabajostercerizados.EmpresaMetalurgica();
                empresametalurgica.setNroEmpresaMetalurgica(this.empre.getIdempresametalurgica());
                reclamo.setEntidad(empresametalurgica.getNroEmpresaMetalurgica());

                reclamo.setMotivo(motivo);
                reclamo.setTrabajotercerizado(this.getIdTransaccion());


                ReclamoempresametalurgicaDAOImpl daoReclamoEmp = new ReclamoempresametalurgicaDAOImpl();
                int result = daoReclamoEmp.insert(reclamo, con);
                if (result < 0) {
                    return false;
                } else {
                    con.commit();
                }

                //Registrar los detalles del reclamo
                long idReclamo =  Long.parseLong(daoReclamoEmp.getUltimoIDReclamo(con));

                DetallereclamoempresametalurgicaDAOImpl daoDetalleReclamoEmp = new DetallereclamoempresametalurgicaDAOImpl();
                Iterator<ViewDetalleReclamo> iter = filasDetalle.iterator();
                ViewDetalleReclamo datos = null;
                while (iter.hasNext()) {
                    datos = iter.next();
                    DetallereclamoempresametalurgicaDB detalleReclamoEmp = new DetallereclamoempresametalurgicaDB();
                    detalleReclamoEmp.setIdreclamo(idReclamo);
                    detalleReclamoEmp.setCantidad(datos.getCantidad());
                    detalleReclamoEmp.setDescripcion(datos.nombrePieza);
                    Date fechaEgreso = new Date(new java.util.Date().getTime());
                    detalleReclamoEmp.setFechaegreso(fechaEgreso);
                    detalleReclamoEmp.setIddetalletrabajo(datos.getIdDetalle());
                    detalleReclamoEmp.setMotivo(datos.getMotivo());
                   
                    daoDetalleReclamoEmp.insert(detalleReclamoEmp, con);
                }

                con.commit();
                return true;

            } catch (Exception ex) {
                Logger.getLogger(GestorReclamo.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }

    }

    public void cargarComboOrdenCompra(JComboBox combo, long idProv) {
        try {
            compra = null;
            CompraDAOImpl daoCompra = new CompraDAOImpl();
            PostgreSQLManager pg = new PostgreSQLManager();
            Connection con = null;
            con = pg.concectGetCn();
            con.setAutoCommit(false);
            CompraDB[] compras = daoCompra.findByProveedor(idProv, con);
            ItemCombo item = null;
            combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
            for (CompraDB c : compras) {
                item = new ItemCombo();
                item.setId(String.valueOf(c.getIdcompra()));
                item.setMostrar(String.valueOf(c.getNrocompra()));
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorReclamo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarComboTrabajo(JComboBox combo, long idEmpresa) {
        try {
            TrabajotercerizadoDAOImpl daoTrabajo = new  TrabajotercerizadoDAOImpl();
            PostgreSQLManager pg = new PostgreSQLManager();
            Connection con = null;
            con = pg.concectGetCn();
            con.setAutoCommit(false);
            TrabajotercerizadoDB[] trabajos = daoTrabajo.findByEmpresa(idEmpresa, con);
            ItemCombo item = null;
            combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
            for (TrabajotercerizadoDB tr : trabajos) {
                item = new ItemCombo();
                item.setId(String.valueOf(tr.getIdtrabajo()));
                item.setMostrar(String.valueOf(tr.getNrotrabajotercerizado()));
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorReclamo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public long getIdEmpresaByName(String nombreEmpresa) {
        try {
            EmpresametalurgicaDAOImpl daoEmpresa = new EmpresametalurgicaDAOImpl();
            PostgreSQLManager pg = new PostgreSQLManager();
            Connection con = null;
            con = pg.concectGetCn();
            con.setAutoCommit(false);
            EmpresametalurgicaDB[] empresas = daoEmpresa.findByRazonsocial(nombreEmpresa, con);
            EmpresametalurgicaDB empres = empresas[0];
            return empres.getIdempresametalurgica();
        } catch (Exception ex) {
            return -1;
        }
    }

    public long getIdCompraByNumero(long nrocompra){
         try {
            CompraDAOImpl daoCompra = new CompraDAOImpl();
            PostgreSQLManager pg = new PostgreSQLManager();
            Connection con = null;
            con = pg.concectGetCn();
            con.setAutoCommit(false);
            CompraDB[] compras = daoCompra.findByNrocompra(nrocompra, con);
            CompraDB comp = compras[0];
            return comp.getIdcompra();
        } catch (Exception ex) {
            return -1;
        }
    }

    public DetallecompraDB[] getDetalleByIdCompra(long idComp) throws DetallecompraException {
        try {
            DetallecompraDAOImpl daoDetalleCompra = new DetallecompraDAOImpl();
            PostgreSQLManager pg = new PostgreSQLManager();
            Connection con = null;
            con = pg.concectGetCn();
            con.setAutoCommit(false);
            DetallecompraDB[] detalleCompras = daoDetalleCompra.findByIdcompra(idComp, con);
            return detalleCompras;
        } catch (Exception ex) {
            throw new DetallecompraException(ex);
        }
    }

    public String getMateriaprimaById(long idMateriaPrima){
         try {
            MateriaprimaDAOImpl daoMateriaPrima = new MateriaprimaDAOImpl();
            PostgreSQLManager pg = new PostgreSQLManager();
            Connection con = null;
            con = pg.concectGetCn();
            con.setAutoCommit(false);
            MateriaprimaDB[] materias = daoMateriaPrima.findByIdmateriaprima(idMateriaPrima, con);
            MateriaprimaDB mp = materias[0];
            return mp.getNombre();
        } catch (Exception ex) {
            return "";
        }
    }

     public long getMateriaprimaByName(String nombreMateriaPrima){
         try {
            MateriaprimaDAOImpl daoMateriaPrima = new MateriaprimaDAOImpl();
            PostgreSQLManager pg = new PostgreSQLManager();
            Connection con = null;
            con = pg.concectGetCn();
            con.setAutoCommit(false);
            MateriaprimaDB[] materias = daoMateriaPrima.findByNombre(nombreMateriaPrima, con);
            MateriaprimaDB mp = materias[0];
            return mp.getIdmateriaprima();
        } catch (Exception ex) {
            return -1;
        }
    }

    public long getIdTrabajoByNumero(long nroTrabajo) {
        try {
            TrabajotercerizadoDAOImpl daoTrabajo = new TrabajotercerizadoDAOImpl();
            PostgreSQLManager pg = new PostgreSQLManager();
            Connection con = null;
            con = pg.concectGetCn();
            con.setAutoCommit(false);
            TrabajotercerizadoDB[] trabajoTercerizado = daoTrabajo.findByNrotrabajotercerizado(nroTrabajo, con);
            TrabajotercerizadoDB trab = trabajoTercerizado[0];
            return trab.getIdtrabajo();
        } catch (Exception ex) {
            return -1;
        }
    }

    public DetalletrabajotercerizadoDB[] getDetalleByIdTrabajo(long idTrabajo) throws DetalletrabajotercerizadoException {
        try {
            DetalletrabajotercerizadoDAOImpl daoDetalleTrabajo = new DetalletrabajotercerizadoDAOImpl();
            PostgreSQLManager pg = new PostgreSQLManager();
            Connection con = null;
            con = pg.concectGetCn();
            con.setAutoCommit(false);
            DetalletrabajotercerizadoDB[] detallesTrabajo = daoDetalleTrabajo.findByIdtrabajotercerizado(idTrabajo, con);
            return detallesTrabajo;
        } catch (Exception ex) {
            throw new DetalletrabajotercerizadoException(ex);
        }
    }

    public String getPiezaById(long idPieza) {
         try {
            PiezaDAOImpl daoPieza = new PiezaDAOImpl();
            PostgreSQLManager pg = new PostgreSQLManager();
            Connection con = null;
            con = pg.concectGetCn();
            con.setAutoCommit(false);
            PiezaDB[]piezas = daoPieza.findByIdpieza(idPieza, con);
            PiezaDB p = piezas[0];
            return p.getNombre();
        } catch (Exception ex) {
            return "";
        }
    }

    public long getIdDetalleByMPrimaCantidadAndIdCompra(long idComp, String nombre, int cantidad) {
        long idMP = this.getMateriaprimaByName(nombre);
        try {
            DetallecompraDAOImpl daodetalleCompra = new DetallecompraDAOImpl();
            PostgreSQLManager pg = new PostgreSQLManager();
            Connection con = null;
            con = pg.concectGetCn();
            con.setAutoCommit(false);
            DetallecompraDB[] detalles = daodetalleCompra.findByMPrimaCantidadAndIdCompra(idComp, idMP, cantidad, con);
            DetallecompraDB dc = detalles[0];
            return dc.getIddetalle();
        } catch (Exception ex) {
            return -1;
        }
    }

    public EmpresametalurgicaDB getEmpresaById(long idEmpMet) {
        try {
            EmpresametalurgicaDAOImpl daoEmpresa = new EmpresametalurgicaDAOImpl();
            PostgreSQLManager pg = new PostgreSQLManager();
            Connection con = null;
            con = pg.concectGetCn();
            con.setAutoCommit(false);
            EmpresametalurgicaDB[] empresas = daoEmpresa.findByIdempresametalurgica(idEmpMet, con);
            EmpresametalurgicaDB emp = empresas[0];
            return emp;
        } catch (Exception ex) {
            return null;
        }
    }

    private String generarNuevoNumeroReclamoEmpresa() {
        String result = "";
        PostgreSQLManager pg = null;
        Connection cn = null;
        pg = new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
            ReclamoempresametalurgicaDAOImpl daoReclamoEmp = new ReclamoempresametalurgicaDAOImpl();
            result = daoReclamoEmp.getUltimoNumeroReclamoempresa(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorReclamo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorReclamo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public long getIdDetalleByPiezaCantidadAndIdTrabajo(long idTrabajo, String nombre, int cantidad) {
        long idPieza = this.getPiezaByName(nombre);
        try {
            DetalletrabajotercerizadoDAOImpl daodetalleTrabajo = new DetalletrabajotercerizadoDAOImpl();
            PostgreSQLManager pg = new PostgreSQLManager();
            Connection con = null;
            con = pg.concectGetCn();
            con.setAutoCommit(false);
            DetalletrabajotercerizadoDB[] detalles = daodetalleTrabajo.findByPiezaCantidadAndIdTrabajo(idTrabajo, idPieza, cantidad, con);
            DetalletrabajotercerizadoDB dtt = detalles[0];
            return dtt.getIddetalle();
        } catch (Exception ex) {
            return -1;
        }
    }

    private long getPiezaByName(String nombre) {
          try {
            PiezaDAOImpl daoPieza = new PiezaDAOImpl();
            PostgreSQLManager pg = new PostgreSQLManager();
            Connection con = null;
            con = pg.concectGetCn();
            con.setAutoCommit(false);
            PiezaDB[] piezas = daoPieza.findByNombre(nombre, con);
            PiezaDB pi = piezas[0];
            return pi.getIdpieza();
          } catch (Exception ex) {
            return -1;
        }
    }

    public void cargarComboReclamoProveedor(JComboBox combo, long idProveedor) {
        try {
            reclamoProveedor = null;
            ReclamoproveedorDAOImpl daoReclamoProveedor = new ReclamoproveedorDAOImpl();
            PostgreSQLManager pg = new PostgreSQLManager();
            Connection con = null;
            con = pg.concectGetCn();
            con.setAutoCommit(false);
            ReclamoproveedorDB[] reclamos = daoReclamoProveedor.findByComprasProveedor(idProveedor, con);
            ItemCombo item = null;
            combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
           for (ReclamoproveedorDB rec : reclamos) {
                item = new ItemCombo();
                item.setId(String.valueOf(rec.getNroreclamo()));
                item.setMostrar(String.valueOf(rec.getNroreclamo()));
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorReclamo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ReclamoproveedorDB getReclamoByNroReclamo(long nroReclamo) {
        try {
            ReclamoproveedorDAOImpl daoReclamoproveedor = new ReclamoproveedorDAOImpl();
            PostgreSQLManager pg = new PostgreSQLManager();
            Connection con = null;
            con = pg.concectGetCn();
            con.setAutoCommit(false);
            ReclamoproveedorDB[] reclamos = daoReclamoproveedor.findByNroreclamo(nroReclamo, con);
            ReclamoproveedorDB recl = reclamos[0];
            return recl;
        } catch (Exception ex) {
            return null;
        }
    }

    public DetallereclamoproveedorDB[] getDetalleByIdReclamo(long idreclamo) throws DetallereclamoproveedorException {
        try {
            DetallereclamoproveedorDAOImpl daoDetallereclamoproveedor = new DetallereclamoproveedorDAOImpl();
            PostgreSQLManager pg = new PostgreSQLManager();
            Connection con = null;
            con = pg.concectGetCn();
            con.setAutoCommit(false);
            DetallereclamoproveedorDB[] detalleReclamos = daoDetallereclamoproveedor.findByIdreclamo(idreclamo, con);
            return detalleReclamos;
        } catch (Exception ex) {
            throw new DetallereclamoproveedorException(ex);
        }
    }

    public boolean modificarReclamo(long idreclamo, String observ) {
        try {
                ReclamoproveedorDB reclamo = new ReclamoproveedorDB();
                PostgreSQLManager pg = new PostgreSQLManager();
                Connection con = null;
                con = pg.concectGetCn();
                con.setAutoCommit(false);
                reclamo.setIdreclamo(idreclamo);
                reclamo.setMotivo(observ);
                reclamo.setEstado(2);
                ReclamoproveedorDAOImpl daoReclamo = new ReclamoproveedorDAOImpl();
                int result = daoReclamo.updateEstado(reclamo, con);
                if (result < 0) {
                    return false;
                } else {
                    con.commit();
                    return true;
                }
            } catch (Exception ex) {
                Logger.getLogger(GestorReclamo.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
    }
}
