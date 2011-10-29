/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.BarrioDB;
import metalsoft.datos.dbobject.ProveedorPKDB;
import metalsoft.datos.dbobject.CondicionivaDB;
import metalsoft.datos.dbobject.DomicilioDB;

import metalsoft.datos.dbobject.ProvinciaDB;
import metalsoft.datos.dbobject.TipodocumentoDB;
import metalsoft.datos.exception.BarrioException;
import metalsoft.datos.exception.LocalidadException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.BarrioDAO;
import metalsoft.datos.idao.ProveedorDAO;
import metalsoft.datos.idao.CondicionivaDAO;
import metalsoft.datos.idao.DomicilioDAO;
import metalsoft.datos.idao.LocalidadDAO;
import metalsoft.datos.idao.ProvinciaDAO;
import metalsoft.datos.idao.ResponsableDAO;
import metalsoft.datos.idao.TipodocumentoDAO;
import metalsoft.datos.jpa.JpaUtil;
import metalsoft.negocio.access.AccessProveedor;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.compras.Responsable;
import metalsoft.negocio.compras.Proveedor;
import metalsoft.negocio.ventas.CondicionIva;

/**
 *
 * @author Vicky
 */
public class GestorProveedor {
    private CondicionivaDB[] condicionesIva=null;
    private CondicionIva[] cinegocio=null;
    private ProvinciaDB[] provincias=null;
    private metalsoft.datos.dbobject.LocalidadDB[] localidades=null;
    private BarrioDB[] barrios=null;
    private metalsoft.datos.dbobject.ProveedorDB[] proveedores=null;
    private DomicilioDB domicilioProveedorDB=null;
    private DomicilioDB domicilioResponsableDB=null;
    private TipodocumentoDB[] tiposDoc=null;
    private metalsoft.negocio.rrhh.Domicilio domicilioProveedor;
    private Proveedor proveedor;
    private long idDomicilioProveedor,idDomicilioResponsable;
    private long idBarrioProveedor,idLocalidadProveedor,idProvinciaProveedor;
    private long idBarrioResponsable,idLocalidadResponsable,idProvinciaResponsable;
    private long idPrioridadProveedor,idCondicionIva;
    private long idTipoDocResponsable;
    private metalsoft.datos.dbobject.ResponsableDB responsableDB;
    private long idResponsable;
    private metalsoft.negocio.compras.Responsable responsable;
    private long idProveedor;
    private metalsoft.datos.dbobject.ProveedorDB proveedorDB;
    private metalsoft.datos.dbobject.BarrioDB barrioDB;
    private metalsoft.datos.dbobject.LocalidadDB localidadDB;

    public GestorProveedor()
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
    public long getIdPrioridadCliente() {
        return idPrioridadProveedor;
    }

    public void setIdPrioridadCliente(long idPrioridadCliente) {
        this.idPrioridadProveedor = idPrioridadCliente;
    }

    public long getIdBarrioCliente() {
        return idBarrioProveedor;
    }

    public void setIdBarrioCliente(long idBarrioCliente) {
        this.idBarrioProveedor = idBarrioCliente;
    }

    public long getIdBarrioResponsable() {
        return idBarrioResponsable;
    }

    public void setIdBarrioResponsable(long idBarrioResponsable) {
        this.idBarrioResponsable = idBarrioResponsable;
    }

    public long getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(long idCliente) {
        this.idProveedor = idCliente;
    }

    public long getIdDomicilioCliente() {
        return idDomicilioProveedor;
    }

    public void setIdDomicilioCliente(long idDomicilioCliente) {
        this.idDomicilioProveedor = idDomicilioCliente;
    }

    public long getIdLocalidadCliente() {
        return idLocalidadProveedor;
    }

    public void setIdLocalidadCliente(long idLocalidadCliente) {
        this.idLocalidadProveedor = idLocalidadCliente;
    }

    public long getIdLocalidadResponsable() {
        return idLocalidadResponsable;
    }

    public void setIdLocalidadResponsable(long idLocalidadResponsable) {
        this.idLocalidadResponsable = idLocalidadResponsable;
    }

    public long getIdProvinciaCliente() {
        return idProvinciaProveedor;
    }

    public void setIdProvinciaCliente(long idProvinciaCliente) {
        this.idProvinciaProveedor = idProvinciaCliente;
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

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor p) {
        this.proveedor = p;
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
        return domicilioProveedorDB;
    }

    public void setDomicilioClienteDB(DomicilioDB domicilioClienteDB) {
        this.domicilioProveedorDB = domicilioClienteDB;
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
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
   }

   public metalsoft.datos.dbobject.ProveedorDB[] buscarProveedores(String valor)
    {
        ProveedorDAO dao=new DAOFactoryImpl().createProveedorDAO();
        Connection cn=null;

        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            proveedores = dao.findExecutingUserWhere("razonsocial ILIKE '"+valor+"%'", sqlParams, cn);
            cn.close();
        } catch (Exception ex) {
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return proveedores;
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
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
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
//            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
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
//            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        finally
//        {
//            try {
//                pg.disconnect();
//            } catch (SQLException ex) {
//                Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
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
    public long obtenerIdCondIva(int index)
    {
        return condicionesIva[index].getIdcondicioniva();
    }

    public int registrarDomicilio(String calle, int numero, int piso, String depto, String torre, int indexBarrio) {
        int result=-1;
        DomicilioDAO dao=new DAOFactoryImpl().createDomicilioDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        domicilioProveedorDB=new DomicilioDB();
        long idBarrio=barrios[indexBarrio].getIdbarrio();
        //long idLocalidad=localidades[Integer.parseInt(indexLocalidad)].getIdlocalidad();
        //long idProvincia=provincias[Integer.parseInt(indexProvincia)].getIdprovincia();
        try {
            cn = pg.concectGetCn();
            domicilioProveedorDB.setBarrio(idBarrio);
            domicilioProveedorDB.setCalle(calle);
            domicilioProveedorDB.setDepto(depto);
            domicilioProveedorDB.setNumerocalle(numero);
            domicilioProveedorDB.setPiso(piso);
            domicilioProveedorDB.setTorre(torre);
            result=dao.insert(domicilioProveedorDB, cn);
            domicilioProveedorDB.setIddomicilio(result);
        } catch (Exception ex) {
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int registrarDomicilio(metalsoft.negocio.rrhh.Domicilio dom, long idBarrio, Connection cn) {
        int result=-1;
        DomicilioDAO dao=new DAOFactoryImpl().createDomicilioDAO();

        try {
            domicilioProveedorDB=Parser.parseToDomicilioDB(dom);
            domicilioProveedorDB.setBarrio(idBarrio);
            result=dao.insert(domicilioProveedorDB, cn);
            domicilioProveedorDB.setIddomicilio(result);
        } catch (Exception ex) {
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public long registrarProveedor(Proveedor proveedor, long idResponsable, long idDomicilio, int indexEstado, int indexCondIva, int indexPrioridad) {
        long result=-1;
        ProveedorDAO dao=new DAOFactoryImpl().createProveedorDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        proveedorDB=new metalsoft.datos.dbobject.ProveedorDB();
        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();

        try {
            cn = pg.concectGetCn();
            proveedorDB.setCelular(proveedor.getCelular());
            proveedorDB.setCondicion(condicionesIva[indexCondIva].getIdcondicioniva());
            proveedorDB.setCuit(proveedor.getCUIT());
            proveedorDB.setDomicilio(idDomicilio);
            //proveedorDB.setEstado(estados[indexEstado].getIdestado());

            if(proveedor.getFechaAlta()!=null)
                proveedorDB.setFechaalta(new java.sql.Date(proveedor.getFechaAlta().getTime()));
            else
                proveedorDB.setFechaalta(null);

            if(proveedor.getFechaBaja()!=null)
                proveedorDB.setFechabaja(new java.sql.Date(proveedor.getFechaBaja().getTime()));
            else
                proveedorDB.setFechabaja(null);

            proveedorDB.setMail(proveedor.getMail());
            proveedorDB.setNroproveedor(proveedor.getNroProveedor());
            //proveedorDB.setPrioridad(prioridades[indexPrioridad].getIdprioridad());
            proveedorDB.setRazonsocial(proveedor.getRazonSocial());
            proveedorDB.setResponsable(idResponsable);
            proveedorDB.setTelefono(proveedor.getTelefono());
            

            result=dao.insert(proveedorDB, cn);
            proveedorDB.setIdproveedor(result);
        } catch (Exception ex) {
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public long registrarProveedor(Proveedor proveedor,int indexEstado, int indexCondIva, int indexPrioridad) {
        long result=-1;
        ProveedorDAO dao=new DAOFactoryImpl().createProveedorDAO();
        DomicilioDAO daoDom=new DAOFactoryImpl().createDomicilioDAO();
        ResponsableDAO daoResp=new DAOFactoryImpl().createResponsableDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        proveedorDB=new metalsoft.datos.dbobject.ProveedorDB();
        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            proveedorDB.setCelular(proveedor.getCelular());
            proveedorDB.setCondicion(condicionesIva[indexCondIva].getIdcondicioniva());
            proveedorDB.setCuit(proveedor.getCUIT());

            int idDom=daoDom.insert(domicilioProveedorDB, cn);
            proveedorDB.setDomicilio(idDom);

            if(proveedor.getFechaAlta()!=null)
                proveedorDB.setFechaalta(new java.sql.Date(proveedor.getFechaAlta().getTime()));
            else
                proveedorDB.setFechaalta(null);

            if(proveedor.getFechaBaja()!=null)
                proveedorDB.setFechabaja(new java.sql.Date(proveedor.getFechaBaja().getTime()));
            else
                proveedorDB.setFechabaja(null);

            proveedorDB.setMail(proveedor.getMail());
            proveedorDB.setNroproveedor(proveedor.getNroProveedor());
            //proveedorDB.setPrioridad(prioridades[indexPrioridad].getIdprioridad());
            proveedorDB.setRazonsocial(proveedor.getRazonSocial());

            int idDomResp=daoDom.insert(domicilioResponsableDB, cn);
            responsableDB.setDomicilio(idDomResp);
            int idResp=daoResp.insert(responsableDB, cn);
            proveedorDB.setResponsable(idResp);

            proveedorDB.setTelefono(proveedor.getTelefono());
            

            result=dao.insert(proveedorDB, cn);
            cn.commit();
            proveedorDB.setIdproveedor(result);
        } catch (Exception ex) {
            try {
                Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public long registrarProveedor(Proveedor proveedor) {
        long result=-1;

        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        proveedorDB=new metalsoft.datos.dbobject.ProveedorDB();
        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();

        try {

            cn = pg.concectGetCn();
            cn.setAutoCommit(false);

            long idDom=proveedor.crearDomicilio(proveedor.getDomicilio(),idBarrioProveedor, cn);
            long idResp=proveedor.crearResponsable(proveedor.getResponsable(),idBarrioResponsable,idTipoDocResponsable,cn);
            result=AccessProveedor.registrarProveedor(proveedor, idResp, idDom, idCondicionIva, cn);
            cn.commit();
            idProveedor=result;

        } catch (Exception ex) {
            try {
                Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex1);
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

    public long modificarProveedor(Proveedor proveedor) {
        long result=-1;
        ProveedorDAO dao=new DAOFactoryImpl().createProveedorDAO();

        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        proveedorDB=new metalsoft.datos.dbobject.ProveedorDB();
        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();
        ProveedorPKDB pk=new ProveedorPKDB(idProveedor);
        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            proveedorDB.setCelular(proveedor.getCelular());
            proveedorDB.setCondicion(idCondicionIva);
            proveedorDB.setCuit(proveedor.getCUIT());

            int idDom=proveedor.modificarDomicilio(proveedor.getDomicilio(),idDomicilioProveedor, idBarrioProveedor, cn);
            proveedorDB.setDomicilio(idDomicilioProveedor);

            if(proveedor.getFechaAlta()!=null)
                proveedorDB.setFechaalta(new java.sql.Date(proveedor.getFechaAlta().getTime()));
            else
                proveedorDB.setFechaalta(null);

            if(proveedor.getFechaBaja()!=null)
                proveedorDB.setFechabaja(new java.sql.Date(proveedor.getFechaBaja().getTime()));
            else
                proveedorDB.setFechabaja(null);

            proveedorDB.setMail(proveedor.getMail());
            proveedorDB.setNroproveedor(proveedor.getNroProveedor());
            //proveedorDB.setPrioridad(idPrioridadProveedor);
            proveedorDB.setRazonsocial(proveedor.getRazonSocial());


            int idResp=proveedor.modificarResponsable(proveedor.getResponsable(),idResponsable,idDomicilioResponsable,idBarrioResponsable,idTipoDocResponsable,cn);
            proveedorDB.setResponsable(idResponsable);

            proveedorDB.setTelefono(proveedor.getTelefono());
            
            result=dao.update(pk,proveedorDB, cn);
            proveedorDB.setIdproveedor(idProveedor);
            cn.commit();

        } catch (Exception ex) {
            try {
                Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
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

    public void tomarDomicilioProveedor(metalsoft.negocio.rrhh.Domicilio dom, long id) {
        domicilioProveedor=dom;
        idDomicilioProveedor=id;
    }

    public void tomarDomicilioProveedor(metalsoft.negocio.rrhh.Domicilio dom, metalsoft.datos.dbobject.DomicilioDB domDB) {
        domicilioProveedor=dom;
        domicilioProveedorDB=domDB;
    }

    public void tomarResponsableProveedor(Responsable respNegocio, long idResponsable) {
        responsable=respNegocio;
        this.idResponsable=idResponsable;
    }

    public void tomarResponsableProveedorDB(metalsoft.datos.dbobject.ResponsableDB respDB) {
        setResponsableDB(respDB);
    }

    public void tomarDomicilioProveedorDB(DomicilioDB domDB) {
        setDomicilioClienteDB(domDB);
    }

    public metalsoft.datos.dbobject.ProveedorDB obtenerProveedorSeleccionado(long id) {
        proveedorDB=buscarProveedorEnArray(id);
        return proveedorDB;
    }

    public metalsoft.datos.dbobject.ProveedorDB buscarProveedorEnArray(long id)
    {
        for(metalsoft.datos.dbobject.ProveedorDB c:proveedores)
        {
            if(c.getIdproveedor()==id)return c;
        }
        return null;
    }

    public metalsoft.datos.dbobject.ProveedorDB buscarProveedorDB(long id) {
        PostgreSQLManager pg=new PostgreSQLManager();
        ProveedorDAO dao=new DAOFactoryImpl().createProveedorDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.ProveedorDB[] array;
        try {
            cn = pg.concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            array = dao.findByIdproveedor(id, cn);
            proveedorDB=array[0];
        } catch (Exception ex) {
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return proveedorDB;
    }

    public DomicilioDB buscarDomicilioProveedorDB(long id) {
        domicilioProveedorDB=buscarDomicilioDB(id);
        return domicilioProveedorDB;
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
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            dom = dao.findByIddomicilio(id, cn)[0];
        } catch (Exception ex) {
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dom;
    }

    public metalsoft.datos.dbobject.ResponsableDB buscarResponsableProveedorDB(long id) {
        PostgreSQLManager pg=new PostgreSQLManager();
        ResponsableDAO dao=new DAOFactoryImpl().createResponsableDAO();
        Connection cn=null;

        try {
            cn = pg.concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            responsableDB = dao.findByIdresponsable(id, cn)[0];
        } catch (Exception ex) {
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (LocalidadException ex) {
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex) {
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return localidadDB;
    }

    public int bajaProveedor(metalsoft.datos.dbobject.ProveedorDB proveedorDB) {
        int result=-1;
        ProveedorDAO dao=new DAOFactoryImpl().createProveedorDAO();

        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;

        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();
        ProveedorPKDB pk=new ProveedorPKDB(idProveedor);
        try {
            cn = pg.concectGetCn();
            //proveedorDB.setEstado(idEstadoCliente);
            //long idEstadoBaja=buscarIdEstadoBaja(cn);
            //proveedorDB.setEstado(idEstadoBaja);
            result=dao.update(pk,proveedorDB, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public List<metalsoft.datos.jpa.entity.Proveedor> buscarProveedorByNroLike(String text) {
        List<metalsoft.datos.jpa.entity.Proveedor> list=JpaUtil.getProveedorByNombreLike(text);
        return list;
    }

//    public long buscarIdEstadoBaja(Connection cn) {
//        EstadoclienteDAO dao=new DAOFactoryImpl().createEstadoclienteDAO();
//        long id=-1;
//        try {
//            id = dao.findByNombre("Baja",cn)[0].getIdestado();
//        } catch (Exception ex) {
//            Logger.getLogger(GestorProveedor.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return id;
//    }
}
