/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package metalsoft.negocio.gestores;

import java.lang.String;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.BarrioDB;
import metalsoft.datos.dbobject.CargoDB;
import metalsoft.datos.dbobject.CategoriaDB;
import metalsoft.datos.dbobject.DomicilioDB;

import metalsoft.datos.dbobject.EmpleadoDB;
import metalsoft.datos.dbobject.ProvinciaDB;
import metalsoft.datos.dbobject.TipodocumentoDB;
import metalsoft.datos.dbobject.TurnoDB;
import metalsoft.datos.exception.BarrioException;
import metalsoft.datos.exception.LocalidadException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.BarrioDAO;
import metalsoft.datos.idao.CargoDAO;
import metalsoft.datos.idao.CategoriaDAO;
import metalsoft.datos.idao.DomicilioDAO;
import metalsoft.datos.idao.LocalidadDAO;
import metalsoft.datos.idao.ProvinciaDAO;
import metalsoft.datos.idao.TipodocumentoDAO;
import metalsoft.datos.idao.TurnoDAO;
import metalsoft.negocio.access.AccessEmpleado;
import metalsoft.negocio.access.AccessFunctions;

import metalsoft.util.ItemCombo;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import metalsoft.datos.dbobject.AsistenciaDB;
import metalsoft.datos.dbobject.EmpleadoxturnoDB;
import metalsoft.datos.idao.AsistenciaDAO;
import metalsoft.datos.idao.EmpleadoxturnoDAO;
import metalsoft.datos.idao.UsuarioDAO;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.datos.jpa.controller.BarrioJpaController;
import metalsoft.datos.jpa.controller.CargoJpaController;
import metalsoft.datos.jpa.controller.CategoriaJpaController;
import metalsoft.datos.jpa.controller.DomicilioJpaController;
import metalsoft.datos.jpa.controller.EmpleadoJpaController;
import metalsoft.datos.jpa.controller.EmpleadoxturnoJpaController;
import metalsoft.datos.jpa.controller.LocalidadJpaController;
import metalsoft.datos.jpa.controller.TipodocumentoJpaController;
import metalsoft.datos.jpa.controller.TurnoJpaController;
import metalsoft.datos.jpa.controller.exceptions.PreexistingEntityException;
import metalsoft.datos.jpa.entity.Barrio;
import metalsoft.datos.jpa.entity.Cargo;
import metalsoft.datos.jpa.entity.Categoria;
import metalsoft.datos.jpa.entity.Empleado;
import metalsoft.datos.jpa.entity.Empleadoxturno;
import metalsoft.datos.jpa.entity.EmpleadoxturnoPK;
import metalsoft.datos.jpa.entity.Localidad;
import metalsoft.datos.jpa.entity.Tipodocumento;
import metalsoft.negocio.rrhh.Asistencia;

/**
 *
 * @author Vicky
 */
public class GestorEmpleado {

    private TurnoDB[] turnos = null;
    private CategoriaDB[] categorias = null;
    private CargoDB[] cargos = null;
    private ProvinciaDB[] provincias = null;
    private metalsoft.datos.dbobject.LocalidadDB[] localidades = null;
    private BarrioDB[] barrios = null;
    private metalsoft.datos.dbobject.EmpleadoDB[] empleados = null;
    private DomicilioDB domicilioDB = null;
    private DomicilioDB domicilioResponsableDB = null;
    private TipodocumentoDB[] tiposDoc = null;
    private metalsoft.negocio.rrhh.Domicilio domicilio;
    private Empleado empleado;
    private long idDomicilio;
    private long idBarrio, idLocalidad, idProvincia;
    private long idTipoDoc;
    private long idEmpleado;
    private long idcategoria, idusuario, idcargo, idtipodoc;
    private LinkedList idturnos;
    private metalsoft.datos.dbobject.EmpleadoDB empleadoDB;
    private metalsoft.datos.dbobject.BarrioDB barrioDB;
    private metalsoft.datos.dbobject.LocalidadDB localidadDB;
    private final String DEJAR = "dejar";
    private final String AGREGAR = "agregar";
    private final String ELIMINAR = "eliminar";

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public LinkedList getIdturnos() {
        return idturnos;
    }

    public void setIdturnos(LinkedList idturnos) {
        this.idturnos = idturnos;
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

    public Empleado buscarEmpleadoDB(long id) {
        EmpleadoJpaController controller=new EmpleadoJpaController(JpaUtil.getEntityManagerFactory());

        return controller.findEmpleado(id);
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
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
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

    public metalsoft.datos.dbobject.UsuarioDB buscarUsuarioDB(long id) {
        PostgreSQLManager pg = new PostgreSQLManager();
        UsuarioDAO dao = new DAOFactoryImpl().createUsuarioDAO();
        Connection cn = null;
        metalsoft.datos.dbobject.UsuarioDB dom = null;
        try {
            cn = pg.concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            dom = dao.findByIdusuario(id, cn)[0];
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

    public EmpleadoxturnoDB[] buscarTurnosDB(long id) {
        PostgreSQLManager pg = new PostgreSQLManager();
        EmpleadoxturnoDAO dao = new DAOFactoryImpl().createEmpleadoxturnoDAO();
        Connection cn = null;
        EmpleadoxturnoDB[] turnosxempleado = null;
        try {
            cn = pg.concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            turnosxempleado = dao.findByIdempleado(id, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return turnosxempleado;
    }

    public AsistenciaDB[] buscarAsistencia(long id, Date fecha) {
        PostgreSQLManager pg = new PostgreSQLManager();
        AsistenciaDAO dao = new DAOFactoryImpl().createAsistenciaDAO();
        Connection cn = null;
        AsistenciaDB[] asistenciaEmpleado = null;
        try {
            cn = pg.concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            asistenciaEmpleado = dao.findByFechaingresoIdEmpleado(fecha, id, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return asistenciaEmpleado;
    }

    public AsistenciaDB[] buscarAsistenciaDelDia(Date fecha) {
        PostgreSQLManager pg = new PostgreSQLManager();
        AsistenciaDAO dao = new DAOFactoryImpl().createAsistenciaDAO();
        Connection cn = null;
        AsistenciaDB[] asistenciaEmpleado = null;
        try {
            cn = pg.concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            asistenciaEmpleado = dao.findByFechaingreso(fecha, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return asistenciaEmpleado;
    }

    public long registrarIngresoAsistencia(AsistenciaDB asistencia) {
        PostgreSQLManager pg = null;
        Connection cn = null;
        pg = new PostgreSQLManager();
        long result = -1;

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result = Asistencia.insert(asistencia, cn);
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

    public long registrarEgreso(AsistenciaDB asistencia) {
        PostgreSQLManager pg = null;
        Connection cn = null;
        pg = new PostgreSQLManager();
        long result = -1;

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            result = Asistencia.update(asistencia, cn);
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

    public metalsoft.datos.dbobject.LocalidadDB buscarLocalidadDeBarrio(long id) {
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

    public int bajaEmpleado(Empleado empleadoDB) {
        
        EmpleadoJpaController controller=new EmpleadoJpaController(JpaUtil.getEntityManagerFactory());
        try{
            controller.edit(empleadoDB);
            return 1;
        }
        catch(Exception e){
            return -1;
        }
        
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

    public boolean eliminarEmpleado(long id) {
        
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

    public long guardarEmpleado(Empleado empleado, LinkedList idturno) {

        EmpleadoJpaController controller = new EmpleadoJpaController(JpaUtil.getEntityManagerFactory());
        DomicilioJpaController controllerDomicilio = new DomicilioJpaController(JpaUtil.getEntityManagerFactory());
        EmpleadoxturnoJpaController controllerEmpTurno = new EmpleadoxturnoJpaController(JpaUtil.getEntityManagerFactory());
        TurnoJpaController controllerTurno = new TurnoJpaController(JpaUtil.getEntityManagerFactory());

        try {
            controllerDomicilio.create(empleado.getDomicilio());
            controller.create(empleado);
            Iterator it = idturno.iterator();
            while (it.hasNext()) {
                Empleadoxturno ext = new Empleadoxturno();
                ext.setEmpleado(controller.findEmpleado(empleado.getIdempleado()));
                ext.setTurno(controllerTurno.findTurno(Long.valueOf(String.valueOf(it.next()))));
                controllerEmpTurno.create(ext);
            }

        } catch (PreexistingEntityException ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleado.getIdempleado();
    }


    public long modificarEmpleado(Empleado empleado, Map<Integer, String> mapTurnos) {

        EmpleadoJpaController controller = new EmpleadoJpaController(JpaUtil.getEntityManagerFactory());
        DomicilioJpaController controllerDomicilio = new DomicilioJpaController(JpaUtil.getEntityManagerFactory());
        EmpleadoxturnoJpaController controllerEmpTurno = new EmpleadoxturnoJpaController(JpaUtil.getEntityManagerFactory());
        TurnoJpaController controllerTurno = new TurnoJpaController(JpaUtil.getEntityManagerFactory());

        try {

            controllerDomicilio.edit(empleado.getDomicilio());
            Set<Integer> setKeys = mapTurnos.keySet();
            for (Integer key : setKeys) {
                String valor = mapTurnos.get(key);
                if (valor.equals(AGREGAR)) {
                    Empleadoxturno ext = new Empleadoxturno();
                    ext.setEmpleado(controller.findEmpleado(empleado.getIdempleado()));
                    ext.setTurno(controllerTurno.findTurno(Long.valueOf(String.valueOf(key))));
                    controllerEmpTurno.create(ext);
                }
                if (valor.equals(ELIMINAR)) {
                    EmpleadoxturnoPK etPK=new EmpleadoxturnoPK(empleado.getIdempleado(), Long.valueOf(String.valueOf(key)));
                    controllerEmpTurno.destroy(etPK);
                }
            }
            controller.edit(empleado);
            

        } catch (PreexistingEntityException ex) {
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        return empleado.getIdempleado();
    }
    public Tipodocumento findTipoDoc(long id)
    {
        TipodocumentoJpaController td=new TipodocumentoJpaController(JpaUtil.getEntityManagerFactory());
        return td.findTipodocumento(id);
    }
    public Localidad findLocalidad(long id)
    {
        LocalidadJpaController td=new LocalidadJpaController(JpaUtil.getEntityManagerFactory());
        return td.findLocalidad(id);
    }
    public Categoria findCategoria(long id)
    {
        CategoriaJpaController td=new CategoriaJpaController(JpaUtil.getEntityManagerFactory());
        return td.findCategoria(id);
    }
    public Cargo findCargo(long id)
    {
        CargoJpaController td=new CargoJpaController(JpaUtil.getEntityManagerFactory());
        return td.findCargo(id);
    }
    public Barrio findBarrio(long id)
    {
        BarrioJpaController td=new BarrioJpaController(JpaUtil.getEntityManagerFactory());
        return td.findBarrio(id);
    }
}
