/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.Barrio;
import metalsoft.datos.dbobject.Cargo;
import metalsoft.datos.dbobject.Categoria;
import metalsoft.datos.dbobject.DomicilioDB;

import metalsoft.datos.dbobject.EmpleadoDB;
import metalsoft.datos.dbobject.EmpleadoPK;
import metalsoft.datos.dbobject.Provincia;
import metalsoft.datos.dbobject.Tipodocumento;
import metalsoft.datos.dbobject.Turno;
import metalsoft.datos.exception.BarrioException;
import metalsoft.datos.exception.LocalidadException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.BarrioDAO;
import metalsoft.datos.idao.CargoDAO;
import metalsoft.datos.idao.CategoriaDAO;
import metalsoft.datos.idao.ClienteDAO;
import metalsoft.datos.idao.DomicilioDAO;
import metalsoft.datos.idao.EmpleadoDAO;
import metalsoft.datos.idao.LocalidadDAO;
import metalsoft.datos.idao.ProvinciaDAO;
import metalsoft.datos.idao.TipodocumentoDAO;
import metalsoft.datos.idao.TurnoDAO;
import metalsoft.negocio.access.AccessEmpleado;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.negocio.rrhh.Empleado;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.rrhh.TipoDocumento;

/**
 *
 * @author Vicky
 */
public class GestorEmpleado {

    private Turno[] turnos = null;
    private Categoria[] categorias = null;
    private Cargo[] cargos = null;
    private Provincia[] provincias = null;
    private metalsoft.datos.dbobject.Localidad[] localidades = null;
    private Barrio[] barrios = null;
    private metalsoft.datos.dbobject.EmpleadoDB[] empleados = null;
    private DomicilioDB domicilioDB = null;
    private DomicilioDB domicilioResponsableDB = null;
    private Tipodocumento[] tiposDoc = null;
    private metalsoft.negocio.rrhh.Domicilio domicilio;
    private Empleado empleado;
    private long idDomicilio;
    private long idBarrio,  idLocalidad,  idProvincia;
    private long idTipoDoc;
    private long idEmpleado;
    private long idcategoria, idusuario, idcargo, idtipodoc;
    private long[] idturno;
    private metalsoft.datos.dbobject.EmpleadoDB empleadoDB;
    private metalsoft.datos.dbobject.Barrio barrioDB;
    private metalsoft.datos.dbobject.Localidad localidadDB;

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public long getIdBarrio() {
        return idBarrio;
    }

    public void setIdBarrio(long idBarrio) {
        this.idBarrio = idBarrio;
    }

    public long getIdDomicilio() {
        return idDomicilio;
    }

    public void setIdDomicilio(long idDomicilio) {
        this.idDomicilio = idDomicilio;
    }

    public long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public long getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(long idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public long getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(long idProvincia) {
        this.idProvincia = idProvincia;
    }

    public long getIdTipoDoc() {
        return idTipoDoc;
    }

    public void setIdTipoDoc(long idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public long getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(long idcargo) {
        this.idcargo = idcargo;
    }

    public long getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(long idcategoria) {
        this.idcategoria = idcategoria;
    }

    public long getIdtipodoc() {
        return idtipodoc;
    }

    public void setIdtipodoc(long idtipodoc) {
        this.idtipodoc = idtipodoc;
    }

    public long[] getIdturno() {
        return idturno;
    }

    public void setIdturno(long[] idturno) {
        this.idturno = idturno;
    }

    public long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(long idusuario) {
        this.idusuario = idusuario;
    }

    public void buscarTurno(JComboBox combo) {
        TurnoDAO dao = new DAOFactoryImpl().createTurnoDAO();
        PostgreSQLManager pg = new PostgreSQLManager();

        try {
            turnos = dao.findAll(pg.concectGetCn());
            ItemCombo item = null;
            combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
            for (int i = 0; i < turnos.length; i++) {
                item = new ItemCombo();
                item.setId(String.valueOf(turnos[i].getIdturno()));
                item.setMostrar(turnos[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void obtenerCategorias(JComboBox combo) {

        CategoriaDAO dao = new DAOFactoryImpl().createCategoriaDAO();
        PostgreSQLManager pg = new PostgreSQLManager();
        combo.removeAllItems();
        try {
            categorias = dao.findAll(pg.concectGetCn());
            ItemCombo item = null;
            combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
            for (int i = 0; i < categorias.length; i++) {
                item = new ItemCombo();
                item.setId(String.valueOf(categorias[i].getIdcategoria()));
                item.setMostrar(categorias[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void obtenerCargos(JComboBox combo) {
        CargoDAO dao = new DAOFactoryImpl().createCargoDAO();
        PostgreSQLManager pg = new PostgreSQLManager();

        try {
            cargos = dao.findAll(pg.concectGetCn());
            ItemCombo item = null;
            combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
            for (int i = 0; i < cargos.length; i++) {
                item = new ItemCombo();
                item.setId(String.valueOf(cargos[i].getIdcargo()));
                item.setMostrar(cargos[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void obtenerTipoDocumentos(JComboBox combo) {
        TipodocumentoDAO dao = new DAOFactoryImpl().createTipodocumentoDAO();
        PostgreSQLManager pg = new PostgreSQLManager();

        try {
            tiposDoc = dao.findAll(pg.concectGetCn());
            ItemCombo item = null;
            combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
            for (int i = 0; i < tiposDoc.length; i++) {
                item = new ItemCombo();
                item.setId(String.valueOf(tiposDoc[i].getIdtipodocumento()));
                item.setMostrar(tiposDoc[i].getTipo());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void obtenerProvincias(JComboBox combo) {

        ProvinciaDAO dao = new DAOFactoryImpl().createProvinciaDAO();
        PostgreSQLManager pg = new PostgreSQLManager();

        try {
            provincias = dao.findAll(pg.concectGetCn());
            ItemCombo item = null;
            combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
            for (int i = 0; i < provincias.length; i++) {
                item = new ItemCombo();
                item.setId(String.valueOf(provincias[i].getIdprovincia()));
                item.setMostrar(provincias[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void buscarLocalidadesDeProvincia(JComboBox combo, long id) {
        LocalidadDAO dao = new DAOFactoryImpl().createLocalidadDAO();
        PostgreSQLManager pg = new PostgreSQLManager();
        try {
            localidades = dao.findByProvincia(id, pg.concectGetCn());
            ItemCombo item = null;
            combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
            for (int i = 0; i < localidades.length; i++) {
                item = new ItemCombo();
                item.setId(String.valueOf(localidades[i].getIdlocalidad()));
                item.setMostrar(localidades[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void buscarBarriosDeLocalidad(JComboBox combo, long id) {
        BarrioDAO dao = new DAOFactoryImpl().createBarrioDAO();
        PostgreSQLManager pg = new PostgreSQLManager();
        try {
            barrios = dao.findByLocalidad(id, pg.concectGetCn());
            ItemCombo item = null;
            combo.addItem(new ItemCombo("-1", "--Seleccionar--"));
            for (int i = 0; i < barrios.length; i++) {
                item = new ItemCombo();
                item.setId(String.valueOf(barrios[i].getIdbarrio()));
                item.setMostrar(barrios[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public long obtenerIdBarrio(int index) {
        return barrios[index].getIdbarrio();
    }

    public long obtenerIdTipoDoc(int index) {
        return tiposDoc[index].getIdtipodocumento();
    }

    public int registrarDomicilio(String calle, int numero, int piso, String depto, String torre, int indexBarrio) {
        int result = -1;
        DomicilioDAO dao = new DAOFactoryImpl().createDomicilioDAO();
        PostgreSQLManager pg = new PostgreSQLManager();
        Connection cn = null;
        domicilioDB = new DomicilioDB();
        long idBarrio = barrios[indexBarrio].getIdbarrio();
        //long idLocalidad=localidades[Integer.parseInt(indexLocalidad)].getIdlocalidad();
        //long idProvincia=provincias[Integer.parseInt(indexProvincia)].getIdprovincia();
        try {
            cn = pg.concectGetCn();
            domicilioDB.setBarrio(idBarrio);
            domicilioDB.setCalle(calle);
            domicilioDB.setDepto(depto);
            domicilioDB.setNumerocalle(numero);
            domicilioDB.setPiso(piso);
            domicilioDB.setTorre(torre);
            result = dao.insert(domicilioDB, cn);
            domicilioDB.setIddomicilio(result);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int registrarDomicilio(metalsoft.negocio.rrhh.Domicilio dom, long idBarrio, Connection cn) {
        int result = -1;
        DomicilioDAO dao = new DAOFactoryImpl().createDomicilioDAO();

        try {
            domicilioDB = Parser.parseToDomicilioDB(dom);
            domicilioDB.setBarrio(idBarrio);
            result = dao.insert(domicilioDB, cn);
            domicilioDB.setIddomicilio(result);
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public long registrarEmpleado(Empleado empleado, long[] idturno, long idcategoria, long idusuario, long idcargo, long iddomicilio, long idtipodoc) {
        PostgreSQLManager pg = null;
        Connection cn = null;
        pg = new PostgreSQLManager();
        long result = -1;

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result = AccessEmpleado.insert(empleado, idturno, idcategoria, idusuario, idcargo, iddomicilio, idtipodoc, cn);
            cn.commit();
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public long registrarEmpleado(Empleado empleado) {
        PostgreSQLManager pg = null;
        Connection cn = null;
        pg = new PostgreSQLManager();
        long result = -1;
        empleadoDB = new metalsoft.datos.dbobject.EmpleadoDB();
        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();

        try {

            cn = pg.concectGetCn();
            cn.setAutoCommit(false);

            long idDom = empleado.crearDomicilio(empleado.getDomicilio(), idBarrio, cn);
            result =  AccessEmpleado.insert(empleado, idturno, idcategoria, idusuario, idcargo, idDom, idtipodoc, cn);

            cn.commit();
            idEmpleado = result;

        } catch (Exception ex) {
            try {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public long modificarCliente(Empleado empleado) {
    //Setear todos los camposss!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11
        long result = -1;
        EmpleadoDAO dao = new DAOFactoryImpl().createEmpleadoDAO();

        PostgreSQLManager pg = new PostgreSQLManager();
        Connection cn = null;
        empleadoDB = new EmpleadoDB();
        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();
        EmpleadoPK pk = new EmpleadoPK(idEmpleado);
        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            

            int idDom = empleado.modificarDomicilio(empleado.getDomicilio(), idDomicilio, idBarrio, cn);
            empleadoDB.setDomicilio(idDom);

            if (empleado.getFechaIngreso() != null) {
                empleadoDB.setFechaingreso(new java.sql.Date(empleado.getFechaIngreso().getTime()));
            } else {
                empleadoDB.setFechaingreso(null);
            }

            if (empleado.getFechaEgreso() != null) {
                empleadoDB.setFechaegreso(new java.sql.Date(empleado.getFechaEgreso().getTime()));
            } else {
                empleadoDB.setFechaegreso(null);
            }

            empleadoDB.setLegajo(empleado.getLegajo());

            empleadoDB.setTelefono(empleado.getTelefono());
            //deberia autogenerar un usario y contraseña
            empleadoDB.setUsuario(1);

            result = dao.update(pk, empleadoDB, cn);
            empleadoDB.setIdempleado(idEmpleado);
            cn.commit();

        } catch (Exception ex) {
            try {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public void tomarDomicilio(metalsoft.negocio.rrhh.Domicilio dom, long id) {
        domicilio = dom;
        idDomicilio = id;
    }

    public void tomarDomicilio(metalsoft.negocio.rrhh.Domicilio dom, metalsoft.datos.dbobject.DomicilioDB domDB) {
        domicilio = dom;
        domicilioDB = domDB;
    }

    public metalsoft.datos.dbobject.EmpleadoDB obtenerEmpleadoSeleccionado(long id) {
        empleadoDB = buscarEmpleadoEnArray(id);
        return empleadoDB;
    }

    public metalsoft.datos.dbobject.EmpleadoDB buscarEmpleadoEnArray(long id) {
        for (metalsoft.datos.dbobject.EmpleadoDB c : empleados) {
            if (c.getIdempleado() == id) {
                return c;
            }
        }
        return null;
    }

    public metalsoft.datos.dbobject.EmpleadoDB buscarEmpleadoDB(long id) {
        PostgreSQLManager pg = new PostgreSQLManager();
        EmpleadoDAO dao = new DAOFactoryImpl().createEmpleadoDAO();
        Connection cn = null;
        metalsoft.datos.dbobject.EmpleadoDB[] array;
        try {
            cn = pg.concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            array = dao.findByIdempleado(id, cn);
            empleadoDB = array[0];
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return empleadoDB;
    }

    public DomicilioDB buscarDomicilioEmpleadoDB(long id) {
        domicilioDB = buscarDomicilioDB(id);
        return domicilioDB;
    }

    public DomicilioDB buscarDomicilioResponsableDB(long id) {
        domicilioResponsableDB = buscarDomicilioDB(id);
        return domicilioResponsableDB;
    }

    public metalsoft.datos.dbobject.DomicilioDB buscarDomicilioDB(long id) {
        PostgreSQLManager pg = new PostgreSQLManager();
        DomicilioDAO dao = new DAOFactoryImpl().createDomicilioDAO();
        Connection cn = null;
        metalsoft.datos.dbobject.DomicilioDB dom = null;
        try {
            cn = pg.concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            dom = dao.findByIddomicilio(id, cn)[0];
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dom;
    }

    public metalsoft.datos.dbobject.Localidad buscarLocalidadDeBarrio(long id) {
        LocalidadDAO daoLocalidad = new DAOFactoryImpl().createLocalidadDAO();
        BarrioDAO daoBarrio = new DAOFactoryImpl().createBarrioDAO();
        PostgreSQLManager pg = new PostgreSQLManager();
        Connection cn = null;
        localidadDB = null;
        try {
            cn = pg.concectGetCn();
            barrioDB = daoBarrio.findByIdbarrio(id, cn)[0];
            localidadDB = daoLocalidad.findByIdlocalidad(barrioDB.getLocalidad(), cn)[0];
        } catch (BarrioException ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LocalidadException ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return localidadDB;
    }

    public int bajaEmpleado(metalsoft.datos.dbobject.EmpleadoDB empleadoDB) {
        int result = -1;
        EmpleadoDAO dao = new DAOFactoryImpl().createEmpleadoDAO();

        PostgreSQLManager pg = new PostgreSQLManager();
        Connection cn = null;

        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();
        EmpleadoPK pk = new EmpleadoPK(idEmpleado);
        try {
            cn = pg.concectGetCn();
            
            result = dao.update(pk, empleadoDB, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public long generarNvoNroEmpleado() {
        long result = -1;
        PostgreSQLManager pg = null;
        Connection cn = null;
        pg = new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
            result = AccessFunctions.nvoNroEmpleado(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public EmpleadoDB[] buscarConILIKE(String valor) {
        Connection cn = null;
        PostgreSQLManager pg = null;
        EmpleadoDB[] db = null;
        try {
            pg = new PostgreSQLManager();
            cn = pg.concectGetCn();
            db = AccessEmpleado.findByNombreILIKE(valor, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return db;
    }

    public EmpleadoDB buscarEmpleadoId(long valor) {
        Connection cn = null;
        PostgreSQLManager pg = null;
        EmpleadoDB db = null;
        try {
            pg = new PostgreSQLManager();
            cn = pg.concectGetCn();
            db = AccessEmpleado.findById(valor, cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return db;
    }

    public boolean eliminarEmpleado(long id) {;
        Connection cn = null;

        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }


        //realizo la eliminación

        long result = -1;
        result = AccessEmpleado.delete(id, cn);
        try {
            cn.close();
        } catch (SQLException ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                cn.close();
                cn = null;
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    public long guardarEmpleado(Empleado empleado,  long[] idturno, long idcategoria, long idusuario, long idcargo, long iddomicilio, long idtipodoc) {
        PostgreSQLManager pg = null;
        Connection cn = null;
        pg = new PostgreSQLManager();
        long result = -1;

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result = AccessEmpleado.insert(empleado, idturno, idcategoria, idusuario, idcargo, iddomicilio, idtipodoc, cn);
            cn.commit();
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public long modificarEmpleado(Empleado empleado, long idEmpleado, long[] idturno, long idcategoria, long idusuario, long idcargo, long iddomicilio, long idtipodoc) {
        PostgreSQLManager pg = null;
        Connection cn = null;
        pg = new PostgreSQLManager();
        long result = -1;

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result = AccessEmpleado.update(empleado, idturno, idEmpleado, idcategoria, idusuario, idcargo, iddomicilio, idtipodoc, cn);
            cn.commit();
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            try {
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
}
