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
import metalsoft.negocio.rrhh.Empleado;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.rrhh.TipoDocumento;
/**
 *
 * @author Vicky
 */
public class GestorEmpleado {
   private Turno[] turnos=null;
   private Categoria[] categorias=null;
   private Cargo[] cargos=null;
   private Provincia[] provincias=null;
   private metalsoft.datos.dbobject.Localidad[] localidades=null;
   private Barrio[] barrios=null;
   private metalsoft.datos.dbobject.ClienteDB[] clientes=null;
   private DomicilioDB domicilioClienteDB=null;
   private DomicilioDB domicilioResponsableDB=null;
   private Tipodocumento[] tiposDoc=null;
    private metalsoft.negocio.rrhh.Domicilio domicilioCliente;
    private Empleado empleado;
    private long idDomicilioResponsable;
    private long idBarrioResponsable,idLocalidadResponsable,idProvinciaResponsable;
    private long idTipoDocResponsable;
    private long idEmpleado;
    private metalsoft.datos.dbobject.EmpleadoDB empleadoDB;
    private metalsoft.datos.dbobject.Barrio barrioDB;
    private metalsoft.datos.dbobject.Localidad localidadDB;

    public metalsoft.datos.dbobject.EmpleadoDB[] buscarEmpleados(String valor)
    {
        EmpleadoDAO dao=new DAOFactoryImpl().createEmpleadoDAO();
        Connection cn=null;

        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            clientes = dao.findExecutingUserWhere("razonsocial ILIKE '"+valor+"%'", sqlParams, cn);
            cn.close();
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes;
    }
    public void buscarTurno(JComboBox combo)
   {
        TurnoDAO dao=new DAOFactoryImpl().createTurnoDAO();
        PostgreSQLManager pg=new PostgreSQLManager();

        try {
            turnos = dao.findAll(pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<turnos.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(turnos[i].getIdturno()));
                item.setMostrar(turnos[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
   }
     public void obtenerCategorias(JComboBox combo) {

        CategoriaDAO dao=new DAOFactoryImpl().createCategoriaDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        combo.removeAllItems();
        try {
            categorias = dao.findAll(pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<categorias.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(categorias[i].getIdcategoria()));
                item.setMostrar(categorias[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
     public void obtenerCargos(JComboBox combo) {
        CargoDAO dao=new DAOFactoryImpl().createCargoDAO();
        PostgreSQLManager pg=new PostgreSQLManager();

        try {
            cargos = dao.findAll(pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<cargos.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(cargos[i].getIdcargo()));
                item.setMostrar(cargos[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void obtenerTipoDocumentos(JComboBox combo) {
        TipodocumentoDAO dao=new DAOFactoryImpl().createTipodocumentoDAO();
        PostgreSQLManager pg=new PostgreSQLManager();

        try {
            tiposDoc = dao.findAll(pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<tiposDoc.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(tiposDoc[i].getIdtipodocumento()));
                item.setMostrar(tiposDoc[i].getTipo());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


   public void obtenerProvincias(JComboBox combo) {

        ProvinciaDAO dao=new DAOFactoryImpl().createProvinciaDAO();
        PostgreSQLManager pg=new PostgreSQLManager();

        try {
            provincias = dao.findAll(pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<provincias.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(provincias[i].getIdprovincia()));
                item.setMostrar(provincias[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   public void buscarLocalidadesDeProvincia(JComboBox combo, long id) {
        LocalidadDAO dao=new DAOFactoryImpl().createLocalidadDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        try {
            localidades = dao.findByProvincia(id,pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<localidades.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(localidades[i].getIdlocalidad()));
                item.setMostrar(localidades[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   public void buscarBarriosDeLocalidad(JComboBox combo, long id) {
        BarrioDAO dao=new DAOFactoryImpl().createBarrioDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        try {
            barrios = dao.findByLocalidad(id,pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<barrios.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(barrios[i].getIdbarrio()));
                item.setMostrar(barrios[i].getNombre());
                combo.addItem(item);
            }
            combo.setSelectedIndex(0);
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public long obtenerIdBarrio(int index)
    {
        return barrios[index].getIdbarrio();
    }
    public long obtenerIdTipoDoc(int index)
    {
        return tiposDoc[index].getIdtipodocumento();
    }
    public int registrarDomicilio(String calle, int numero, int piso, String depto, String torre, int indexBarrio) {
        int result=-1;
        DomicilioDAO dao=new DAOFactoryImpl().createDomicilioDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        domicilioClienteDB=new DomicilioDB();
        long idBarrio=barrios[indexBarrio].getIdbarrio();
        //long idLocalidad=localidades[Integer.parseInt(indexLocalidad)].getIdlocalidad();
        //long idProvincia=provincias[Integer.parseInt(indexProvincia)].getIdprovincia();
        try {
            cn = pg.concectGetCn();
            domicilioClienteDB.setBarrio(idBarrio);
            domicilioClienteDB.setCalle(calle);
            domicilioClienteDB.setDepto(depto);
            domicilioClienteDB.setNumerocalle(numero);
            domicilioClienteDB.setPiso(piso);
            domicilioClienteDB.setTorre(torre);
            result=dao.insert(domicilioClienteDB, cn);
            domicilioClienteDB.setIddomicilio(result);
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int registrarDomicilio(metalsoft.negocio.rrhh.Domicilio dom, long idBarrio, Connection cn) {
        int result=-1;
        DomicilioDAO dao=new DAOFactoryImpl().createDomicilioDAO();

        try {
            domicilioClienteDB=Parser.parseToDomicilioDB(dom);
            domicilioClienteDB.setBarrio(idBarrio);
            result=dao.insert(domicilioClienteDB, cn);
            domicilioClienteDB.setIddomicilio(result);
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    private int registrarResponsable(Responsable responsable,long idBarrioResp, long idTipoDocResp, Connection cn) {
        int result=-1;
        ResponsableDAO dao=new DAOFactoryImpl().createResponsableDAO();

        try {
            responsableDB=Parser.parseToResponsableDB(responsable);
            int idDomResp=registrarDomicilio(responsable.getDomicilio(), idBarrioResp, cn);
            responsableDB.setDomicilio(idDomResp);
            responsableDB.setTipodocumento(idTipoDocResp);
            result=dao.insert(responsableDB, cn);
            responsableDB.setIdresponsable(result);
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public long registrarResponsable(Responsable responsable, int indexTipoDoc, long idDomicilio) {
        long result=-1;
        ResponsableDAO dao=new DAOFactoryImpl().createResponsableDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        responsableDB=new metalsoft.datos.dbobject.ResponsableDB();
        long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();
        //long idLocalidad=localidades[Integer.parseInt(indexLocalidad)].getIdlocalidad();
        //long idProvincia=provincias[Integer.parseInt(indexProvincia)].getIdprovincia();
        try {
            cn = pg.concectGetCn();
            responsableDB.setApellido(responsable.getApellido());
            responsableDB.setDomicilio(idDomicilio);
            responsableDB.setEmail(responsable.getEmail());
            responsableDB.setFax(responsable.getFax());
            responsableDB.setNombre(responsable.getNombre());
            responsableDB.setNrodocumento(responsable.getNroDocumento());
            responsableDB.setTelefono(responsable.getTelefono());
            responsableDB.setTipodocumento(idTipoDoc);
            result=dao.insert(responsableDB, cn);
            responsableDB.setIdresponsable(result);
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public long registrarCliente(Cliente cliente, long idResponsable, long idDomicilio, int indexEstado, int indexCondIva, int indexPrioridad) {
        long result=-1;
        ClienteDAO dao=new DAOFactoryImpl().createClienteDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        empleadoDB=new metalsoft.datos.dbobject.ClienteDB();
        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();

        try {
            cn = pg.concectGetCn();
            empleadoDB.setCelular(cliente.getCelular());
            empleadoDB.setCondicioniva(turnos[indexCondIva].getIdcondicioniva());
            empleadoDB.setCuit(cliente.getCUIT());
            empleadoDB.setDomicilio(idDomicilio);
            empleadoDB.setEstado(cargos[indexEstado].getIdestado());

            if(cliente.getFechaAlta()!=null)
                empleadoDB.setFechaalta(new java.sql.Date(cliente.getFechaAlta().getTime()));
            else
                empleadoDB.setFechaalta(null);

            if(cliente.getFechaBaja()!=null)
                empleadoDB.setFechabaja(new java.sql.Date(cliente.getFechaBaja().getTime()));
            else
                empleadoDB.setFechabaja(null);

            empleadoDB.setMail(cliente.getMail());
            empleadoDB.setNrocliente(cliente.getNroCliente());
            empleadoDB.setPrioridad(categorias[indexPrioridad].getIdprioridad());
            empleadoDB.setRazonsocial(cliente.getRazonSocial());
            empleadoDB.setResponsable(idResponsable);
            empleadoDB.setTelefono(cliente.getTelefono());
            empleadoDB.setUsuario(1);

            result=dao.insert(empleadoDB, cn);
            empleadoDB.setIdcliente(result);
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public long registrarCliente(Cliente cliente,int indexEstado, int indexCondIva, int indexPrioridad) {
        long result=-1;
        ClienteDAO dao=new DAOFactoryImpl().createClienteDAO();
        DomicilioDAO daoDom=new DAOFactoryImpl().createDomicilioDAO();
        ResponsableDAO daoResp=new DAOFactoryImpl().createResponsableDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        empleadoDB=new metalsoft.datos.dbobject.ClienteDB();
        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            empleadoDB.setCelular(cliente.getCelular());
            empleadoDB.setCondicioniva(turnos[indexCondIva].getIdcondicioniva());
            empleadoDB.setCuit(cliente.getCUIT());

            int idDom=daoDom.insert(domicilioClienteDB, cn);
            empleadoDB.setDomicilio(idDom);

            empleadoDB.setEstado(cargos[indexEstado].getIdestado());

            if(cliente.getFechaAlta()!=null)
                empleadoDB.setFechaalta(new java.sql.Date(cliente.getFechaAlta().getTime()));
            else
                empleadoDB.setFechaalta(null);

            if(cliente.getFechaBaja()!=null)
                empleadoDB.setFechabaja(new java.sql.Date(cliente.getFechaBaja().getTime()));
            else
                empleadoDB.setFechabaja(null);

            empleadoDB.setMail(cliente.getMail());
            empleadoDB.setNrocliente(cliente.getNroCliente());
            empleadoDB.setPrioridad(categorias[indexPrioridad].getIdprioridad());
            empleadoDB.setRazonsocial(cliente.getRazonSocial());

            int idDomResp=daoDom.insert(domicilioResponsableDB, cn);
            responsableDB.setDomicilio(idDomResp);
            int idResp=daoResp.insert(responsableDB, cn);
            empleadoDB.setResponsable(idResp);

            empleadoDB.setTelefono(cliente.getTelefono());
            //deberia autogenerar un usario y contraseña
            empleadoDB.setUsuario(1);

            result=dao.insert(empleadoDB, cn);
            cn.commit();
            empleadoDB.setIdcliente(result);
        } catch (Exception ex) {
            try {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public long registrarCliente(Cliente cliente) {
        long result=-1;

        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        empleadoDB=new metalsoft.datos.dbobject.ClienteDB();
        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();

        try {

            cn = pg.concectGetCn();
            cn.setAutoCommit(false);

            long idDom=cliente.crearDomicilio(cliente.getDomicilio(),idBarrioCliente, cn);
            long idResp=cliente.crearResponsable(cliente.getResponsable(),idBarrioResponsable,idTipoDocResponsable,cn);
            result=AccessCliente.registrarCliente(cliente, idResp, idDom, idEstadoCliente, idCondicionIva, idPrioridadCliente, cn);
            cn.commit();
            idEmpleado=result;

        } catch (Exception ex) {
            try {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public long modificarCliente(Cliente cliente) {
        long result=-1;
        ClienteDAO dao=new DAOFactoryImpl().createClienteDAO();

        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        empleadoDB=new metalsoft.datos.dbobject.ClienteDB();
        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();
        ClientePK pk=new ClientePK(idEmpleado);
        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            empleadoDB.setCelular(cliente.getCelular());
            empleadoDB.setCondicioniva(idCondicionIva);
            empleadoDB.setCuit(cliente.getCUIT());

            int idDom=cliente.modificarDomicilio(cliente.getDomicilio(),idDomicilioCliente, idBarrioCliente, cn);
            empleadoDB.setDomicilio(idDomicilioCliente);

            empleadoDB.setEstado(idEstadoCliente);

            if(cliente.getFechaAlta()!=null)
                empleadoDB.setFechaalta(new java.sql.Date(cliente.getFechaAlta().getTime()));
            else
                empleadoDB.setFechaalta(null);

            if(cliente.getFechaBaja()!=null)
                empleadoDB.setFechabaja(new java.sql.Date(cliente.getFechaBaja().getTime()));
            else
                empleadoDB.setFechabaja(null);

            empleadoDB.setMail(cliente.getMail());
            empleadoDB.setNrocliente(cliente.getNroCliente());
            empleadoDB.setPrioridad(idPrioridadCliente);
            empleadoDB.setRazonsocial(cliente.getRazonSocial());


            int idResp=cliente.modificarResponsable(cliente.getResponsable(),idResponsable,idDomicilioResponsable,idBarrioResponsable,idTipoDocResponsable,cn);
            empleadoDB.setResponsable(idResponsable);

            empleadoDB.setTelefono(cliente.getTelefono());
            //deberia autogenerar un usario y contraseña
            empleadoDB.setUsuario(1);

            result=dao.update(pk,empleadoDB, cn);
            empleadoDB.setIdcliente(idEmpleado);
            cn.commit();

        } catch (Exception ex) {
            try {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }
     public void tomarDomicilioCliente(metalsoft.negocio.rrhh.Domicilio dom, long id) {
        domicilioCliente=dom;
        idDomicilioCliente=id;
    }

    public void tomarDomicilioCliente(metalsoft.negocio.rrhh.Domicilio dom, metalsoft.datos.dbobject.DomicilioDB domDB) {
        domicilioCliente=dom;
        domicilioClienteDB=domDB;
    }

    public void tomarResponsableCliente(Responsable respNegocio, long idResponsable) {
        responsable=respNegocio;
        this.idResponsable=idResponsable;
    }

    

    public metalsoft.datos.dbobject.ClienteDB obtenerClienteSeleccionado(long id) {
        empleadoDB=buscarClienteEnArray(id);
        return empleadoDB;
    }

    public metalsoft.datos.dbobject.ClienteDB buscarClienteEnArray(long id)
    {
        for(metalsoft.datos.dbobject.ClienteDB c:clientes)
        {
            if(c.getIdcliente()==id)return c;
        }
        return null;
    }

    public metalsoft.datos.dbobject.ClienteDB buscarClienteDB(long id) {
        PostgreSQLManager pg=new PostgreSQLManager();
        ClienteDAO dao=new DAOFactoryImpl().createClienteDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.ClienteDB[] array;
        try {
            cn = pg.concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            array = dao.findByIdcliente(id, cn);
            empleadoDB=array[0];
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return empleadoDB;
    }

    public DomicilioDB buscarDomicilioClienteDB(long id) {
        domicilioClienteDB=buscarDomicilioDB(id);
        return domicilioClienteDB;
    }

    public DomicilioDB buscarDomicilioResponsableDB(long id) {
        domicilioResponsableDB=buscarDomicilioDB(id);
        return domicilioResponsableDB;
    }

    public metalsoft.datos.dbobject.DomicilioDB buscarDomicilioDB(long id)
    {
        PostgreSQLManager pg=new PostgreSQLManager();
        DomicilioDAO dao=new DAOFactoryImpl().createDomicilioDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.DomicilioDB dom=null;
        try {
            cn = pg.concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            dom = dao.findByIddomicilio(id, cn)[0];
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dom;
    }

    public metalsoft.datos.dbobject.ResponsableDB buscarResponsableClienteDB(long id) {
        PostgreSQLManager pg=new PostgreSQLManager();
        ResponsableDAO dao=new DAOFactoryImpl().createResponsableDAO();
        Connection cn=null;

        try {
            cn = pg.concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            responsableDB = dao.findByIdresponsable(id, cn)[0];
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return responsableDB;
    }

    public metalsoft.datos.dbobject.Localidad buscarLocalidadDeBarrio(long id) {
        LocalidadDAO daoLocalidad=new DAOFactoryImpl().createLocalidadDAO();
        BarrioDAO daoBarrio=new DAOFactoryImpl().createBarrioDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        localidadDB=null;
        try {
            cn = pg.concectGetCn();
            barrioDB = daoBarrio.findByIdbarrio(id, cn)[0];
            localidadDB=daoLocalidad.findByIdlocalidad(barrioDB.getLocalidad(), cn)[0];
        }
        catch (BarrioException ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (LocalidadException ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return localidadDB;
    }

    public int bajaCliente(metalsoft.datos.dbobject.ClienteDB clienteDB) {
        int result=-1;
        ClienteDAO dao=new DAOFactoryImpl().createClienteDAO();

        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;

        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();
        ClientePK pk=new ClientePK(idEmpleado);
        try {
            cn = pg.concectGetCn();
            clienteDB.setEstado(idEstadoCliente);
            long idEstadoBaja=buscarIdEstadoBaja(cn);
            clienteDB.setEstado(idEstadoBaja);
            result=dao.update(pk,clienteDB, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public long buscarIdEstadoBaja(Connection cn) {
        EstadoclienteDAO dao=new DAOFactoryImpl().createEstadoclienteDAO();
        long id=-1;
        try {
            id = dao.findByNombre("Baja",cn)[0].getIdestado();
        } catch (Exception ex) {
            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public long generarNvoNroEmpleado() {
        long result=-1;
        PostgreSQLManager pg=null;
        Connection cn=null;
        pg=new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
            result=AccessFunctions.nvoNroCliente(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

}
