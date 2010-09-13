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
import metalsoft.datos.dbobject.EmpresametalurgicaPK;
import metalsoft.datos.dbobject.Condicioniva;
import metalsoft.datos.dbobject.DomicilioDB;

import metalsoft.datos.dbobject.Provincia;
import metalsoft.datos.dbobject.Tipodocumento;
import metalsoft.datos.exception.BarrioException;
import metalsoft.datos.exception.LocalidadException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.BarrioDAO;
import metalsoft.datos.idao.EmpresametalurgicaDAO;
import metalsoft.datos.idao.CondicionivaDAO;
import metalsoft.datos.idao.DomicilioDAO;
import metalsoft.datos.idao.LocalidadDAO;
import metalsoft.datos.idao.PrioridadDAO;
import metalsoft.datos.idao.ProvinciaDAO;
import metalsoft.datos.idao.ResponsableDAO;
import metalsoft.datos.idao.TipodocumentoDAO;
import metalsoft.util.ItemCombo;
import metalsoft.negocio.compras.Responsable;
import metalsoft.negocio.gestores.GestorCliente;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.rrhh.TipoDocumento;
import metalsoft.negocio.trabajostercerizados.EmpresaMetalurgica;
import metalsoft.negocio.ventas.CondicionIva;

/**
 *
 * @author Lorreine Prescott
 */
public class GestorEmpresaMetalurgica {

    private Condicioniva[] condicionesIva=null;
    private CondicionIva[] cinegocio=null;
    private Provincia[] provincias=null;
    private metalsoft.datos.dbobject.Localidad[] localidades=null;
    private Barrio[] barrios=null;
    private metalsoft.datos.dbobject.EmpresametalurgicaDB[] empresasMetalurgicas=null;
    private DomicilioDB domicilioEmpresaMetalurgicaDB=null;
    private DomicilioDB domicilioResponsableDB=null;
    private Tipodocumento[] tiposDoc=null;
    private metalsoft.negocio.rrhh.Domicilio domicilioEmpresaMetalurgica;
    private EmpresaMetalurgica empresaMetalurgica;
    private long idDomicilioEmpresaMetalurgica,idDomicilioResponsable;
    private long idBarrioEmpresaMetalurgica,idLocalidadEmpresaMetalurgica,idProvinciaEmpresaMetalurgica;
    private long idBarrioResponsable,idLocalidadResponsable,idProvinciaResponsable;
    private long idPrioridadEmpresaMetalurgica,idCondicionIva;
    private long idTipoDocResponsable;
    private metalsoft.datos.dbobject.ResponsableDB responsableDB;
    private long idResponsable;
    private metalsoft.negocio.compras.Responsable responsable;
    private long idEmpresaMetalurgica;
    private metalsoft.datos.dbobject.EmpresametalurgicaDB EmpresametalurgicaDB;
    private metalsoft.datos.dbobject.Barrio barrioDB;
    private metalsoft.datos.dbobject.Localidad localidadDB;

    public GestorEmpresaMetalurgica()
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
        return idPrioridadEmpresaMetalurgica;
    }

    public void setIdPrioridadCliente(long idPrioridadCliente) {
        this.idPrioridadEmpresaMetalurgica = idPrioridadCliente;
    }

    public long getIdBarrioCliente() {
        return idBarrioEmpresaMetalurgica;
    }

    public void setIdBarrioCliente(long idBarrioCliente) {
        this.idBarrioEmpresaMetalurgica = idBarrioCliente;
    }

    public long getIdBarrioResponsable() {
        return idBarrioResponsable;
    }

    public void setIdBarrioResponsable(long idBarrioResponsable) {
        this.idBarrioResponsable = idBarrioResponsable;
    }

    public long getIdEmpresaMetalurgica() {
        return idEmpresaMetalurgica;
    }

    public void setIdEmpresaMetalurgica(long idCliente) {
        this.idEmpresaMetalurgica = idCliente;
    }

    public long getIdDomicilioCliente() {
        return idDomicilioEmpresaMetalurgica;
    }

    public void setIdDomicilioCliente(long idDomicilioCliente) {
        this.idDomicilioEmpresaMetalurgica = idDomicilioCliente;
    }

    public long getIdLocalidadCliente() {
        return idLocalidadEmpresaMetalurgica;
    }

    public void setIdLocalidadCliente(long idLocalidadCliente) {
        this.idLocalidadEmpresaMetalurgica = idLocalidadCliente;
    }

    public long getIdLocalidadResponsable() {
        return idLocalidadResponsable;
    }

    public void setIdLocalidadResponsable(long idLocalidadResponsable) {
        this.idLocalidadResponsable = idLocalidadResponsable;
    }

    public long getIdProvinciaCliente() {
        return idProvinciaEmpresaMetalurgica;
    }

    public void setIdProvinciaCliente(long idProvinciaCliente) {
        this.idProvinciaEmpresaMetalurgica = idProvinciaCliente;
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

    public EmpresaMetalurgica getEmpresaMetalurgica() {
        return empresaMetalurgica;
    }

    public void setProveedor(EmpresaMetalurgica em) {
        this.empresaMetalurgica = em;
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
        return domicilioEmpresaMetalurgicaDB;
    }

    public void setDomicilioClienteDB(DomicilioDB domicilioClienteDB) {
        this.domicilioEmpresaMetalurgicaDB = domicilioClienteDB;
    }

    public CondicionIva[] buscarCondicionIva()
   {
        CondicionivaDAO dao=new DAOFactoryImpl().createCondicionivaDAO();
        PostgreSQLManager pg=new PostgreSQLManager();

        try {
            condicionesIva = dao.findAll(pg.concectGetCn());
            cinegocio=Parser.parseToCondIva(condicionesIva);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
   }

   public metalsoft.datos.dbobject.EmpresametalurgicaDB[] buscarEmpresasMetalurgicas(String valor)
    {
        EmpresametalurgicaDAO dao=new DAOFactoryImpl().createEmpresametalurgicaDAO();
        Connection cn=null;

        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            empresasMetalurgicas = dao.findExecutingUserWhere("razonsocial ILIKE '"+valor+"%'", sqlParams, cn);
            cn.close();
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empresasMetalurgicas;
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
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
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
        domicilioEmpresaMetalurgicaDB=new DomicilioDB();
        long idBarrio=barrios[indexBarrio].getIdbarrio();
        //long idLocalidad=localidades[Integer.parseInt(indexLocalidad)].getIdlocalidad();
        //long idProvincia=provincias[Integer.parseInt(indexProvincia)].getIdprovincia();
        try {
            cn = pg.concectGetCn();
            domicilioEmpresaMetalurgicaDB.setBarrio(idBarrio);
            domicilioEmpresaMetalurgicaDB.setCalle(calle);
            domicilioEmpresaMetalurgicaDB.setDepto(depto);
            domicilioEmpresaMetalurgicaDB.setNumerocalle(numero);
            domicilioEmpresaMetalurgicaDB.setPiso(piso);
            domicilioEmpresaMetalurgicaDB.setTorre(torre);
            result=dao.insert(domicilioEmpresaMetalurgicaDB, cn);
            domicilioEmpresaMetalurgicaDB.setIddomicilio(result);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public int registrarDomicilio(metalsoft.negocio.rrhh.Domicilio dom, long idBarrio, Connection cn) {
        int result=-1;
        DomicilioDAO dao=new DAOFactoryImpl().createDomicilioDAO();

        try {
            domicilioEmpresaMetalurgicaDB=Parser.parseToDomicilioDB(dom);
            domicilioEmpresaMetalurgicaDB.setBarrio(idBarrio);
            result=dao.insert(domicilioEmpresaMetalurgicaDB, cn);
            domicilioEmpresaMetalurgicaDB.setIddomicilio(result);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public long registrarProveedor(EmpresaMetalurgica empresaMetalurgica, long idResponsable, long idDomicilio, int indexEstado, int indexCondIva, int indexPrioridad) {
        long result=-1;
        EmpresametalurgicaDAO dao=new DAOFactoryImpl().createEmpresametalurgicaDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        EmpresametalurgicaDB=new metalsoft.datos.dbobject.EmpresametalurgicaDB();
        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();

        try {
            cn = pg.concectGetCn();
            EmpresametalurgicaDB.setCelular(empresaMetalurgica.getCelular());
            EmpresametalurgicaDB.setCondicioniva(condicionesIva[indexCondIva].getIdcondicioniva());
            EmpresametalurgicaDB.setCuit(empresaMetalurgica.getCUIT());
            EmpresametalurgicaDB.setDomicilio(idDomicilio);
            //proveedorDB.setEstado(estados[indexEstado].getIdestado());

            if(empresaMetalurgica.getFechaAlta()!=null)
                EmpresametalurgicaDB.setFechaalta(new java.sql.Date(empresaMetalurgica.getFechaAlta().getTime()));
            else
                EmpresametalurgicaDB.setFechaalta(null);

            if(empresaMetalurgica.getFechaBaja()!=null)
                EmpresametalurgicaDB.setFechabaja(new java.sql.Date(empresaMetalurgica.getFechaBaja().getTime()));
            else
                EmpresametalurgicaDB.setFechabaja(null);

            EmpresametalurgicaDB.setMail(empresaMetalurgica.getMail());
            EmpresametalurgicaDB.setNroempresametalurgica(empresaMetalurgica.getNroEmpresaMetalurgica());
            //proveedorDB.setPrioridad(prioridades[indexPrioridad].getIdprioridad());
            EmpresametalurgicaDB.setResponsable(idResponsable);
            EmpresametalurgicaDB.setTelefono(empresaMetalurgica.getTelefono());


            result=dao.insert(EmpresametalurgicaDB, cn);
            EmpresametalurgicaDB.setIdempresametalurgica(result);
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public long registrarEmpresaMetalurgica(EmpresaMetalurgica empresaMetalurgica,int indexEstado, int indexCondIva, int indexPrioridad) {
        long result=-1;
        EmpresametalurgicaDAO dao=new DAOFactoryImpl().createEmpresametalurgicaDAO();
        DomicilioDAO daoDom=new DAOFactoryImpl().createDomicilioDAO();
        ResponsableDAO daoResp=new DAOFactoryImpl().createResponsableDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        EmpresametalurgicaDB=new metalsoft.datos.dbobject.EmpresametalurgicaDB();
        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            EmpresametalurgicaDB.setCelular(empresaMetalurgica.getCelular());
            EmpresametalurgicaDB.setCondicioniva(condicionesIva[indexCondIva].getIdcondicioniva());
            EmpresametalurgicaDB.setCuit(empresaMetalurgica.getCUIT());

            int idDom=daoDom.insert(domicilioEmpresaMetalurgicaDB, cn);
            EmpresametalurgicaDB.setDomicilio(idDom);

            if(empresaMetalurgica.getFechaAlta()!=null)
                EmpresametalurgicaDB.setFechaalta(new java.sql.Date(empresaMetalurgica.getFechaAlta().getTime()));
            else
                EmpresametalurgicaDB.setFechaalta(null);

            if(empresaMetalurgica.getFechaBaja()!=null)
                EmpresametalurgicaDB.setFechabaja(new java.sql.Date(empresaMetalurgica.getFechaBaja().getTime()));
            else
                EmpresametalurgicaDB.setFechabaja(null);

            EmpresametalurgicaDB.setMail(empresaMetalurgica.getMail());
            EmpresametalurgicaDB.setNroempresametalurgica(empresaMetalurgica.getNroEmpresaMetalurgica());
            //proveedorDB.setPrioridad(prioridades[indexPrioridad].getIdprioridad());
            EmpresametalurgicaDB.setRazonsocial(empresaMetalurgica.getRazonSocial());

            int idDomResp=daoDom.insert(domicilioResponsableDB, cn);
            responsableDB.setDomicilio(idDomResp);
            int idResp=daoResp.insert(responsableDB, cn);
            EmpresametalurgicaDB.setResponsable(idResp);

            EmpresametalurgicaDB.setTelefono(empresaMetalurgica.getTelefono());


            result=dao.insert(EmpresametalurgicaDB, cn);
            cn.commit();
            EmpresametalurgicaDB.setIdempresametalurgica(result);
        } catch (Exception ex) {
            try {
                Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    public long registrarEmpresaMetalurgica(EmpresaMetalurgica empresaMetalurgica) {
        long result=-1;

        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        EmpresametalurgicaDB=new metalsoft.datos.dbobject.EmpresametalurgicaDB();
        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();

        try {

            cn = pg.concectGetCn();
            cn.setAutoCommit(false);

            long idDom=empresaMetalurgica.crearDomicilio(empresaMetalurgica.getDomicilio(),idBarrioEmpresaMetalurgica, cn);
            long idResp=empresaMetalurgica.crearResponsable(empresaMetalurgica.getResponsable(),idBarrioResponsable,idTipoDocResponsable,cn);
            result=AccessEmpresaMetalurgica.registrarEmpresaMetalurgica(empresaMetalurgica, idResp, idDom, idCondicionIva, cn);
            cn.commit();
            idEmpresaMetalurgica=result;

        } catch (Exception ex) {
            try {
                Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex1);
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

    public long modificarEmpresaMetalurgica(EmpresaMetalurgica empresaMetalurgica) {
        long result=-1;
        EmpresametalurgicaDAO dao=new DAOFactoryImpl().createEmpresametalurgicaDAO();

        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        EmpresametalurgicaDB=new metalsoft.datos.dbobject.EmpresametalurgicaDB();
        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();
        EmpresametalurgicaPK pk=new EmpresametalurgicaPK(idEmpresaMetalurgica);
        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            EmpresametalurgicaDB.setCelular(empresaMetalurgica.getCelular());
            EmpresametalurgicaDB.setCondicioniva(idCondicionIva);
            EmpresametalurgicaDB.setCuit(empresaMetalurgica.getCUIT());

            int idDom=empresaMetalurgica.modificarDomicilio(empresaMetalurgica.getDomicilio(),idDomicilioEmpresaMetalurgica, idBarrioEmpresaMetalurgica, cn);
            EmpresametalurgicaDB.setDomicilio(idDomicilioEmpresaMetalurgica);

            if(empresaMetalurgica.getFechaAlta()!=null)
                EmpresametalurgicaDB.setFechaalta(new java.sql.Date(empresaMetalurgica.getFechaAlta().getTime()));
            else
                EmpresametalurgicaDB.setFechaalta(null);

            if(empresaMetalurgica.getFechaBaja()!=null)
                EmpresametalurgicaDB.setFechabaja(new java.sql.Date(empresaMetalurgica.getFechaBaja().getTime()));
            else
                EmpresametalurgicaDB.setFechabaja(null);

            EmpresametalurgicaDB.setMail(empresaMetalurgica.getMail());
            EmpresametalurgicaDB.setNroempresametalurgica(empresaMetalurgica.getNroEmpresaMetalurgica());
            //proveedorDB.setPrioridad(idPrioridadProveedor);
            EmpresametalurgicaDB.setRazonsocial(empresaMetalurgica.getRazonSocial());


            int idResp=empresaMetalurgica.modificarResponsable(empresaMetalurgica.getResponsable(),idResponsable,idDomicilioResponsable,idBarrioResponsable,idTipoDocResponsable,cn);
            EmpresametalurgicaDB.setResponsable(idResponsable);

            EmpresametalurgicaDB.setTelefono(empresaMetalurgica.getTelefono());

            result=dao.update(pk,EmpresametalurgicaDB, cn);
            EmpresametalurgicaDB.setIdempresametalurgica(idEmpresaMetalurgica);
            cn.commit();

        } catch (Exception ex) {
            try {
                Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
                cn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

     public void direccionCliente()
   {

   }


       public void barrioSeleccionado()
   {

   }

       public void buscarLocalidadesExistentes()
   {

   }


       public void buscarBarriosExistentes()
   {

   }

       public void localidadSeleccionada()
   {

   }

       public void datosResponsables()
   {

   }

       public void confirmacion()
   {

   }

       public void registrarNuevoCliente()
   {

   }

       public void generarNumeroCliente()
   {

   }

       public void buscarUltimoNumeroCliente()
   {

   }

       public void obtenerFechaActual()
   {

   }

       public void finCasoDeUso()
   {

   }
       public void cancelacion()
   {

   }

       public void cancelarCasoDeUso()
   {

   }


       public void ingresoNuevoBarrio()
   {

   }


       public void llamarCasoDeUsoRegistrarBarrio()
   {

   }


      public void ingresoNuevaLocalidad()
   {

   }


      public void llamarCasoDeUsoRegistrarLocalidad()
   {

   }


      public void noConfirmacion()
   {

   }


      public void clienteSeleccionado()
   {

   }


      public void consultaFinalizada()
   {

   }


      public void opcionImprimir()
   {

   }


      public void datosAModificar()
   {

   }


      public void actualizarCambios()
   {

   }


    public void tomarDomicilioEmpresaMetalurgica(metalsoft.negocio.rrhh.Domicilio dom, long id) {
        domicilioEmpresaMetalurgica=dom;
        idDomicilioEmpresaMetalurgica=id;
    }

    public void tomarDomicilioEmpresaMetalurgica(metalsoft.negocio.rrhh.Domicilio dom, metalsoft.datos.dbobject.DomicilioDB domDB) {
        domicilioEmpresaMetalurgica=dom;
        domicilioEmpresaMetalurgicaDB=domDB;
    }

    public void tomarResponsableEmpresaMetalurgica(Responsable respNegocio, long idResponsable) {
        responsable=respNegocio;
        this.idResponsable=idResponsable;
    }

    public void tomarResponsableEmpresaMetalurgicaDB(metalsoft.datos.dbobject.ResponsableDB respDB) {
        setResponsableDB(respDB);
    }

    public void tomarDomicilioEmpresaMetalurgicaDB(DomicilioDB domDB) {
        setDomicilioClienteDB(domDB);
    }

    public metalsoft.datos.dbobject.EmpresametalurgicaDB obtenerEmpresaMetalurgicaSeleccionada(long id) {
        EmpresametalurgicaDB=buscarEmpresaMetalurgicaEnArray(id);
        return EmpresametalurgicaDB;
    }

    public metalsoft.datos.dbobject.EmpresametalurgicaDB buscarEmpresaMetalurgicaEnArray(long id)
    {
        for(metalsoft.datos.dbobject.EmpresametalurgicaDB c:empresasMetalurgicas)
        {
            if(c.getIdempresametalurgica()==id)return c;
        }
        return null;
    }

    public metalsoft.datos.dbobject.EmpresametalurgicaDB buscarEmpresaMetalurgicaDB(long id) {
        PostgreSQLManager pg=new PostgreSQLManager();
        EmpresametalurgicaDAO dao=new DAOFactoryImpl().createEmpresametalurgicaDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.EmpresametalurgicaDB[] array;
        try {
            cn = pg.concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            array = dao.findByIdempresametalurgica(id, cn);
            EmpresametalurgicaDB=array[0];
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return EmpresametalurgicaDB;
    }

    public DomicilioDB buscarDomicilioEmpresaMetalurgicaDB(long id) {
        domicilioEmpresaMetalurgicaDB=buscarDomicilioDB(id);
        return domicilioEmpresaMetalurgicaDB;
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
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            dom = dao.findByIddomicilio(id, cn)[0];
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dom;
    }

    public metalsoft.datos.dbobject.ResponsableDB buscarResponsableEmpresaMetalurgicaDB(long id) {
        PostgreSQLManager pg=new PostgreSQLManager();
        ResponsableDAO dao=new DAOFactoryImpl().createResponsableDAO();
        Connection cn=null;

        try {
            cn = pg.concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            responsableDB = dao.findByIdresponsable(id, cn)[0];
        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (LocalidadException ex) {
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return localidadDB;
    }

    public int bajaEmpresaMetalurgica(metalsoft.datos.dbobject.EmpresametalurgicaDB EmpresametalurgicaDB) {
        int result=-1;
        EmpresametalurgicaDAO dao=new DAOFactoryImpl().createEmpresametalurgicaDAO();

        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;

        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();
        EmpresametalurgicaPK pk=new EmpresametalurgicaPK(idEmpresaMetalurgica);
        try {
            cn = pg.concectGetCn();
            //proveedorDB.setEstado(idEstadoCliente);
            //long idEstadoBaja=buscarIdEstadoBaja(cn);
            //proveedorDB.setEstado(idEstadoBaja);
            result=dao.update(pk,EmpresametalurgicaDB, cn);

        } catch (Exception ex) {
            Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try {
                pg.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(GestorEmpresaMetalurgica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

}
