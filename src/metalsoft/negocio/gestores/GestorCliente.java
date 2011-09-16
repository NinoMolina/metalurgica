//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\negocio\\gestores\\GestorCliente.java

package metalsoft.negocio.gestores;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.BarrioDB;
import metalsoft.datos.dbobject.ClientePKDB;
import metalsoft.datos.dbobject.CondicionivaDB;
import metalsoft.datos.dbobject.DomicilioDB;
import metalsoft.datos.dbobject.EstadoclienteDB;

import metalsoft.datos.dbobject.PrioridadDB;
import metalsoft.datos.dbobject.ProvinciaDB;
import metalsoft.datos.dbobject.TipodocumentoDB;
import metalsoft.datos.exception.BarrioException;
import metalsoft.datos.exception.LocalidadException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.BarrioDAO;
import metalsoft.datos.idao.ClienteDAO;
import metalsoft.datos.idao.CondicionivaDAO;
import metalsoft.datos.idao.DomicilioDAO;
import metalsoft.datos.idao.EstadoclienteDAO;
import metalsoft.datos.idao.LocalidadDAO;
import metalsoft.datos.idao.PrioridadDAO;
import metalsoft.datos.idao.ProvinciaDAO;
import metalsoft.datos.idao.ResponsableDAO;
import metalsoft.datos.idao.TipodocumentoDAO;
import metalsoft.negocio.access.AccessCliente;
import metalsoft.negocio.access.AccessFunctions;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.compras.Responsable;
import metalsoft.negocio.rrhh.TipoDocumento;
import metalsoft.negocio.ventas.Cliente;
import metalsoft.negocio.ventas.CondicionIva;


public class GestorCliente 
{
   private CondicionivaDB[] condicionesIva=null;
   private CondicionIva[] cinegocio=null;
   private PrioridadDB[] prioridades=null;
   private EstadoclienteDB[] estados=null;
   private ProvinciaDB[] provincias=null;
   private metalsoft.datos.dbobject.LocalidadDB[] localidades=null;
   private BarrioDB[] barrios=null;
   private metalsoft.datos.dbobject.ClienteDB[] clientes=null;
   private DomicilioDB domicilioClienteDB=null;
   private DomicilioDB domicilioResponsableDB=null;
   private TipodocumentoDB[] tiposDoc=null;
    private metalsoft.negocio.rrhh.Domicilio domicilioCliente;
    private Cliente cliente;
    private long idDomicilioCliente,idDomicilioResponsable;
    private long idBarrioCliente,idLocalidadCliente,idProvinciaCliente;
    private long idBarrioResponsable,idLocalidadResponsable,idProvinciaResponsable;
    private long idPrioridadCliente,idEstadoCliente,idCondicionIva;
    private long idTipoDocResponsable;
    private metalsoft.datos.dbobject.ResponsableDB responsableDB;
    private long idResponsable;
    private metalsoft.negocio.compras.Responsable responsable;
    private long idCliente;
    private metalsoft.datos.dbobject.ClienteDB clienteDB;
    private metalsoft.datos.dbobject.BarrioDB barrioDB;
    private metalsoft.datos.dbobject.LocalidadDB localidadDB;
   /**
    * @roseuid 4C280D160306
    */
   public GestorCliente() 
   {
    
   }
   
    public long getIdDomicilioResponsable() {
        return idDomicilioResponsable;
    }

    public void setIdDomicilioResponsable(long idDomicilioResponsable) {
        this.idDomicilioResponsable = idDomicilioResponsable;
    }

    public long getIdTipoDocResponsable() {
        return idTipoDocResponsable;
    }

    public void setIdTipoDocResponsable(long idTipoDocResponsable) {
        this.idTipoDocResponsable = idTipoDocResponsable;
    }

    public long getIdCondicionIva() {
        return idCondicionIva;
    }

    public void setIdCondicionIva(long idCondicionIva) {
        this.idCondicionIva = idCondicionIva;
    }

    public long getIdEstadoCliente() {
        return idEstadoCliente;
    }

    public void setIdEstadoCliente(long idEstadoCliente) {
        this.idEstadoCliente = idEstadoCliente;
    }

    public long getIdPrioridadCliente() {
        return idPrioridadCliente;
    }

    public void setIdPrioridadCliente(long idPrioridadCliente) {
        this.idPrioridadCliente = idPrioridadCliente;
    }

    public long getIdBarrioCliente() {
        return idBarrioCliente;
    }

    public void setIdBarrioCliente(long idBarrioCliente) {
        this.idBarrioCliente = idBarrioCliente;
    }

    public long getIdBarrioResponsable() {
        return idBarrioResponsable;
    }

    public void setIdBarrioResponsable(long idBarrioResponsable) {
        this.idBarrioResponsable = idBarrioResponsable;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public long getIdDomicilioCliente() {
        return idDomicilioCliente;
    }

    public void setIdDomicilioCliente(long idDomicilioCliente) {
        this.idDomicilioCliente = idDomicilioCliente;
    }

    public long getIdLocalidadCliente() {
        return idLocalidadCliente;
    }

    public void setIdLocalidadCliente(long idLocalidadCliente) {
        this.idLocalidadCliente = idLocalidadCliente;
    }

    public long getIdLocalidadResponsable() {
        return idLocalidadResponsable;
    }

    public void setIdLocalidadResponsable(long idLocalidadResponsable) {
        this.idLocalidadResponsable = idLocalidadResponsable;
    }

    public long getIdProvinciaCliente() {
        return idProvinciaCliente;
    }

    public void setIdProvinciaCliente(long idProvinciaCliente) {
        this.idProvinciaCliente = idProvinciaCliente;
    }

    public long getIdProvinciaResponsable() {
        return idProvinciaResponsable;
    }

    public void setIdProvinciaResponsable(long idProvinciaResponsable) {
        this.idProvinciaResponsable = idProvinciaResponsable;
    }

    public long getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(long idResponsable) {
        this.idResponsable = idResponsable;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public DomicilioDB getDomicilioResponsableDB() {
        return domicilioResponsableDB;
    }

    public void setDomicilioResponsableDB(DomicilioDB domicilioResponsableDB) {
        this.domicilioResponsableDB = domicilioResponsableDB;
    }
   public void tomarDomicilioResponsable(metalsoft.datos.dbobject.DomicilioDB domDB)
   {
       setDomicilioResponsableDB(domDB);
   }

    public metalsoft.datos.dbobject.ResponsableDB getResponsableDB() {
        return responsableDB;
    }

    public void setResponsableDB(metalsoft.datos.dbobject.ResponsableDB responsableDB) {
        this.responsableDB = responsableDB;
    }

    public DomicilioDB getDomicilioClienteDB() {
        return domicilioClienteDB;
    }

    public void setDomicilioClienteDB(DomicilioDB domicilioClienteDB) {
        this.domicilioClienteDB = domicilioClienteDB;
    }


   /**
    * @roseuid 4C1FF60D010F
    */
   public CondicionIva[] buscarCondicionIva()
   {
        CondicionivaDAO dao=new DAOFactoryImpl().createCondicionivaDAO();
        PostgreSQLManager pg=new PostgreSQLManager();

        try {
            condicionesIva = dao.findAll(pg.concectGetCn());
            cinegocio=Parser.parseToCondIva(condicionesIva);
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
        return cinegocio;
   }

   public void buscarCondicionIva(JComboBox combo)
   {
        CondicionivaDAO dao=new DAOFactoryImpl().createCondicionivaDAO();
        PostgreSQLManager pg=new PostgreSQLManager();

        try {
            condicionesIva = dao.findAll(pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<condicionesIva.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(condicionesIva[i].getIdcondicioniva()));
                item.setMostrar(condicionesIva[i].getNombre());
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

   public metalsoft.datos.dbobject.ClienteDB[] buscarClientes(String valor)
    {
        ClienteDAO dao=new DAOFactoryImpl().createClienteDAO();
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



   public void obtenerPrioridades(JComboBox combo) {

        PrioridadDAO dao=new DAOFactoryImpl().createPrioridadDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        combo.removeAllItems();
        try {
            prioridades = dao.findAll(pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<prioridades.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(prioridades[i].getIdprioridad()));
                item.setMostrar(prioridades[i].getNombre());
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

   public void obtenerEstados(JComboBox combo) {
        EstadoclienteDAO dao=new DAOFactoryImpl().createEstadoclienteDAO();
        PostgreSQLManager pg=new PostgreSQLManager();

        try {
            estados = dao.findAll(pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<estados.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(estados[i].getIdestado()));
                item.setMostrar(estados[i].getNombre());
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

//   public void buscarLocalidadesDeProvincia(JComboBox combo, int index) {
//        LocalidadDAO dao=new DAOFactoryImpl().createLocalidadDAO();
//        PostgreSQLManager pg=new PostgreSQLManager();
//        try {
//            localidades = dao.findByProvincia(provincias[index].getIdprovincia(),pg.concectGetCn());
//            ItemCombo item=null;
//            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
//            for(int i=0;i<localidades.length;i++)
//            {
//                item=new ItemCombo();
//                item.setId(String.valueOf(localidades[i].getIdlocalidad()));
//                item.setMostrar(localidades[i].getNombre());
//                combo.addItem(item);
//            }
//            combo.setSelectedIndex(0);
//        } catch (Exception ex) {
//            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        finally
//        {
//            try {
//                pg.disconnect();
//            } catch (SQLException ex) {
//                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }

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

//   public void buscarBarriosDeLocalidad(JComboBox combo, int index) {
//        BarrioDAO dao=new DAOFactoryImpl().createBarrioDAO();
//        PostgreSQLManager pg=new PostgreSQLManager();
//        try {
//            barrios = dao.findByLocalidad(localidades[index].getIdlocalidad(),pg.concectGetCn());
//            ItemCombo item=null;
//            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
//            for(int i=0;i<barrios.length;i++)
//            {
//                item=new ItemCombo();
//                item.setId(String.valueOf(barrios[i].getIdbarrio()));
//                item.setMostrar(barrios[i].getNombre());
//                combo.addItem(item);
//            }
//            combo.setSelectedIndex(0);
//        } catch (Exception ex) {
//            Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        finally
//        {
//            try {
//                pg.disconnect();
//            } catch (SQLException ex) {
//                Logger.getLogger(GestorCliente.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }

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

    public long obtenerIdPrioridad(int index)
    {
        return prioridades[index].getIdprioridad();
    }

    public long obtenerIdTipoDoc(int index)
    {
        return tiposDoc[index].getIdtipodocumento();
    }
    public long obtenerIdCondIva(int index)
    {
        return condicionesIva[index].getIdcondicioniva();
    }

    public long obtenerIdEstado(int index)
    {
        return estados[index].getIdestado();
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
        clienteDB=new metalsoft.datos.dbobject.ClienteDB();
        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();

        try {
            cn = pg.concectGetCn();
            clienteDB.setCelular(cliente.getCelular());
            clienteDB.setCondicioniva(condicionesIva[indexCondIva].getIdcondicioniva());
            clienteDB.setCuit(cliente.getCUIT());
            clienteDB.setDomicilio(idDomicilio);
            clienteDB.setEstado(estados[indexEstado].getIdestado());

            if(cliente.getFechaAlta()!=null)
                clienteDB.setFechaalta(new java.sql.Date(cliente.getFechaAlta().getTime()));
            else
                clienteDB.setFechaalta(null);
            
            if(cliente.getFechaBaja()!=null)
                clienteDB.setFechabaja(new java.sql.Date(cliente.getFechaBaja().getTime()));
            else
                clienteDB.setFechabaja(null);

            clienteDB.setMail(cliente.getMail());
            clienteDB.setNrocliente(cliente.getNroCliente());
            clienteDB.setPrioridad(prioridades[indexPrioridad].getIdprioridad());
            clienteDB.setRazonsocial(cliente.getRazonSocial());
            clienteDB.setResponsable(idResponsable);
            clienteDB.setTelefono(cliente.getTelefono());
            clienteDB.setUsuario(1);
            
            result=dao.insert(clienteDB, cn);
            clienteDB.setIdcliente(result);
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
        clienteDB=new metalsoft.datos.dbobject.ClienteDB();
        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            clienteDB.setCelular(cliente.getCelular());
            clienteDB.setCondicioniva(condicionesIva[indexCondIva].getIdcondicioniva());
            clienteDB.setCuit(cliente.getCUIT());

            int idDom=daoDom.insert(domicilioClienteDB, cn);
            clienteDB.setDomicilio(idDom);

            clienteDB.setEstado(estados[indexEstado].getIdestado());

            if(cliente.getFechaAlta()!=null)
                clienteDB.setFechaalta(new java.sql.Date(cliente.getFechaAlta().getTime()));
            else
                clienteDB.setFechaalta(null);

            if(cliente.getFechaBaja()!=null)
                clienteDB.setFechabaja(new java.sql.Date(cliente.getFechaBaja().getTime()));
            else
                clienteDB.setFechabaja(null);

            clienteDB.setMail(cliente.getMail());
            clienteDB.setNrocliente(cliente.getNroCliente());
            clienteDB.setPrioridad(prioridades[indexPrioridad].getIdprioridad());
            clienteDB.setRazonsocial(cliente.getRazonSocial());

            int idDomResp=daoDom.insert(domicilioResponsableDB, cn);
            responsableDB.setDomicilio(idDomResp);
            int idResp=daoResp.insert(responsableDB, cn);
            clienteDB.setResponsable(idResp);

            clienteDB.setTelefono(cliente.getTelefono());
            //deberia autogenerar un usario y contraseña
            clienteDB.setUsuario(1);

            result=dao.insert(clienteDB, cn);
            cn.commit();
            clienteDB.setIdcliente(result);
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
        clienteDB=new metalsoft.datos.dbobject.ClienteDB();
        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();

        try {

            cn = pg.concectGetCn();
            cn.setAutoCommit(false);

            long idDom=cliente.crearDomicilio(cliente.getDomicilio(),idBarrioCliente, cn);
            long idResp=cliente.crearResponsable(cliente.getResponsable(),idBarrioResponsable,idTipoDocResponsable,cn);
            result=AccessCliente.registrarCliente(cliente, idResp, idDom, idEstadoCliente, idCondicionIva, idPrioridadCliente, cn);
            cn.commit();
            idCliente=result;

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
        clienteDB=new metalsoft.datos.dbobject.ClienteDB();
        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();
        ClientePKDB pk=new ClientePKDB(idCliente);
        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            clienteDB.setCelular(cliente.getCelular());
            clienteDB.setCondicioniva(idCondicionIva);
            clienteDB.setCuit(cliente.getCUIT());

            int idDom=cliente.modificarDomicilio(cliente.getDomicilio(),idDomicilioCliente, idBarrioCliente, cn);
            clienteDB.setDomicilio(idDomicilioCliente);

            clienteDB.setEstado(idEstadoCliente);

            if(cliente.getFechaAlta()!=null)
                clienteDB.setFechaalta(new java.sql.Date(cliente.getFechaAlta().getTime()));
            else
                clienteDB.setFechaalta(null);

            if(cliente.getFechaBaja()!=null)
                clienteDB.setFechabaja(new java.sql.Date(cliente.getFechaBaja().getTime()));
            else
                clienteDB.setFechabaja(null);

            clienteDB.setMail(cliente.getMail());
            clienteDB.setNrocliente(cliente.getNroCliente());
            clienteDB.setPrioridad(idPrioridadCliente);
            clienteDB.setRazonsocial(cliente.getRazonSocial());


            int idResp=cliente.modificarResponsable(cliente.getResponsable(),idResponsable,idDomicilioResponsable,idBarrioResponsable,idTipoDocResponsable,cn);
            clienteDB.setResponsable(idResponsable);

            clienteDB.setTelefono(cliente.getTelefono());
            //deberia autogenerar un usario y contraseña
            clienteDB.setUsuario(1);

            result=dao.update(pk,clienteDB, cn);
            clienteDB.setIdcliente(idCliente);
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
   /**
    * @roseuid 4C1FF60D0110
    */
   public void direccionCliente() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0111
    */
   public void buscarBarriosExistentes() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0112
    */
   public void barrioSeleccionado() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0113
    */
   public void buscarLocalidadesExistentes() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0114
    */
   public void localidadSeleccionada() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0115
    */
   public void datosResponsables() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0116
    */
   public void confirmacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0117
    */
   public void registrarNuevoCliente() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0118
    */
   public void generarNumeroCliente() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0119
    */
   public void buscarUltimoNumeroCliente() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D011A
    */
   public void obtenerFechaActual() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D011B
    */
   public void finCasoDeUso() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D011C
    */
   public void cancelacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D011D
    */
   public void cancelarCasoDeUso() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D011E
    */
   public void ingresoNuevoBarrio() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D011F
    */
   public void llamarCasoDeUsoRegistrarBarrio() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0122
    */
   public void ingresoNuevaLocalidad() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0123
    */
   public void llamarCasoDeUsoRegistrarLocalidad() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0126
    */
   public void noConfirmacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0128
    */
   public void clienteSeleccionado() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D012A
    */
   public void consultaFinalizada() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D012C
    */
   public void opcionImprimir() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0130
    */
   public void datosAModificar() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D0132
    */
   public void actualizarCambios() 
   {
    
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

    public void tomarResponsableClienteDB(metalsoft.datos.dbobject.ResponsableDB respDB) {
        setResponsableDB(respDB);
    }

    public void tomarDomicilioClienteDB(DomicilioDB domDB) {
        setDomicilioClienteDB(domDB);
    }

    public metalsoft.datos.dbobject.ClienteDB obtenerClienteSeleccionado(long id) {
        clienteDB=buscarClienteEnArray(id);
        return clienteDB;
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
            clienteDB=array[0];
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
        return clienteDB;
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

    public metalsoft.datos.dbobject.LocalidadDB buscarLocalidadDeBarrio(long id) {
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
        ClientePKDB pk=new ClientePKDB(idCliente);
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

    public long generarNvoNroCliente() {
        long result=-1;
        PostgreSQLManager pg=null;
        Connection cn=null;
        pg=new PostgreSQLManager();
        try {
            cn = pg.concectGetCn();
            result=AccessFunctions.nvoNroCliente(cn);
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorPedidoCotizacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    
}
