/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JList;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dao.DetallereclamoempresametalurgicaDAOImpl;
import metalsoft.datos.dao.DetallereclamoproveedorDAOImpl;
import metalsoft.datos.dao.ReclamoempresametalurgicaDAOImpl;
import metalsoft.datos.dao.ReclamoproveedorDAOImpl;
import metalsoft.datos.dao.TiporeclamoDAOImpl;
import metalsoft.datos.dbobject.DetallereclamoempresametalurgicaDB;
import metalsoft.datos.dbobject.DetallereclamoproveedorDB;
import metalsoft.datos.dbobject.EstadodetallecompraDB;
import metalsoft.datos.dbobject.ReclamoempresametalurgicaDB;
import metalsoft.datos.dbobject.ReclamoproveedorDB;
import metalsoft.datos.dbobject.TiporeclamoDB;
import metalsoft.datos.jpa.controller.EmpresametalurgicaJpaController;
import metalsoft.datos.jpa.controller.PiezaJpaController;
import metalsoft.datos.jpa.entity.Empresametalurgica;
import metalsoft.datos.jpa.entity.Pieza;
import metalsoft.datos.jpa.entity.Proveedor;
import metalsoft.negocio.almacenamiento.MateriaPrima;
import metalsoft.negocio.compras.Reclamo;
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

    public List<MateriaPrima> getMateriaprima() {
        return materiaprima;
    }

    public void setMateriaprima(List<MateriaPrima> materiaprima) {
        this.materiaprima = materiaprima;
    }

    public Proveedor getProv() {
        return prov;
    }

    public void setProv(Proveedor prov) {
        this.prov = prov;
    }

    public void setEmpMet(Empresametalurgica empMet) {
        this.emp = empMet;
    }
    private List<Proveedor> proveedores;

    public List<Proveedor> getProveedores() {
        return proveedores;
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
        PiezaJpaController controller = new PiezaJpaController();
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
        EmpresametalurgicaJpaController controller = new EmpresametalurgicaJpaController();
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

    public boolean registrarReclamo(Integer tipo) {
        //Registrar reclamo a Proveedor
        if (tipo == 0) {
            try {
                ReclamoproveedorDB reclamo = new ReclamoproveedorDB();
                reclamo.setNroreclamo(Integer.parseInt((this.getnumero())));
                TiporeclamoDAOImpl daoTipoReclamo = new TiporeclamoDAOImpl();
                PostgreSQLManager pg = new PostgreSQLManager();
                Connection con = null;
                con = pg.concectGetCn();
                con.setAutoCommit(false);
                TiporeclamoDB tipoRec = daoTipoReclamo.findByPrimaryKey(1, con);
                reclamo.setTiporeclamo(tipoRec.getIdtiporeclamo());

                metalsoft.negocio.compras.Proveedor proveedor = new metalsoft.negocio.compras.Proveedor();
                proveedor.setNroProveedor(Integer.parseInt(prov.getIdproveedor().toString()));
                reclamo.setEntidad(proveedor.getNroProveedor());

                reclamo.setMotivo(motivo);

                ReclamoproveedorDAOImpl daoReclamo = new ReclamoproveedorDAOImpl();
                int result = daoReclamo.insert(reclamo, con);
                if (result < 0) {
                    return false;
                } else {
                    con.commit();
                }

                //Registrar los detalles del reclamo
                String idReclamo = daoReclamo.getUltimoIDReclamo(con);

                DetallereclamoproveedorDAOImpl daoDetalleReclamoProv = new DetallereclamoproveedorDAOImpl();
                Iterator<ViewDetalleReclamo> iter = filasDetalle.iterator();
                ViewDetalleReclamo datos = null;
                while (iter.hasNext()) {
                    datos = iter.next();
                    DetallereclamoproveedorDB detalleReclamoProv = new DetallereclamoproveedorDB();
                    detalleReclamoProv.setCantidad(datos.getCantidad());
                    detalleReclamoProv.setDescripcion("descripcion");
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
                reclamo.setNroreclamo(Integer.parseInt((this.getnumero())));
                TiporeclamoDAOImpl daoTipoReclamo = new TiporeclamoDAOImpl();
                PostgreSQLManager pg = new PostgreSQLManager();
                Connection con = null;
                con = pg.concectGetCn();
                con.setAutoCommit(false);
                TiporeclamoDB tipoRec = daoTipoReclamo.findByPrimaryKey(1, con);
                reclamo.setTiporeclamo(tipoRec.getIdtiporeclamo());

                metalsoft.negocio.trabajostercerizados.EmpresaMetalurgica empresametalurgica = new metalsoft.negocio.trabajostercerizados.EmpresaMetalurgica();
                empresametalurgica.setNroEmpresaMetalurgica(Integer.parseInt(this.emp.getIdempresametalurgica().toString()));
                // Agregar entidad?? Trabajo??? reclamo.setEntidad(empresametalurgica.getNroEmpresaMetalurgica());

                reclamo.setMotivo(motivo);

                ReclamoempresametalurgicaDAOImpl daoReclamoEmp = new ReclamoempresametalurgicaDAOImpl();
                int result = daoReclamoEmp.insert(reclamo, con);
                if (result < 0) {
                    return false;
                } else {
                    con.commit();
                }

                //Registrar los detalles del reclamo
                String idReclamo = daoReclamoEmp.getUltimoIDReclamo(con);

                DetallereclamoempresametalurgicaDAOImpl daoDetalleReclamoEmp = new DetallereclamoempresametalurgicaDAOImpl();
                Iterator<ViewDetalleReclamo> iter = filasDetalle.iterator();
                ViewDetalleReclamo datos = null;
                while (iter.hasNext()) {
                    datos = iter.next();
                    DetallereclamoempresametalurgicaDB detalleReclamoEmp = new  DetallereclamoempresametalurgicaDB();
                   detalleReclamoEmp.setCantidad(datos.getCantidad());
                   detalleReclamoEmp.setDescripcion("descripcion");
                    // continuar completando con los datos correctos de la BD

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
}
