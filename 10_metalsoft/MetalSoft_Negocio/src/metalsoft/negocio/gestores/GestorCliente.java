//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\negocio\\gestores\\GestorCliente.java

package metalsoft.negocio.gestores;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.Barrio;
import metalsoft.datos.dbobject.Condicioniva;
import metalsoft.datos.dbobject.Domicilio;
import metalsoft.datos.dbobject.Estadocliente;
import metalsoft.datos.dbobject.Localidad;
import metalsoft.datos.dbobject.Prioridad;
import metalsoft.datos.dbobject.Provincia;
import metalsoft.datos.dbobject.Tipodocumento;
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
import metalsoft.negocio.ItemCombo;
import metalsoft.negocio.compras.Responsable;
import metalsoft.negocio.rrhh.TipoDocumento;
import metalsoft.negocio.ventas.Cliente;
import metalsoft.negocio.ventas.CondicionIva;


public class GestorCliente 
{
   private Condicioniva[] ci=null;
   private CondicionIva[] cinegocio=null;
   private Prioridad[] pr=null;
   private Estadocliente[] estados=null;
   private Provincia[] provincias=null;
   private Localidad[] localidades=null;
   private Barrio[] barrios=null;
   private metalsoft.datos.dbobject.Cliente[] clientes=null;
   private Domicilio domicilioClienteDB=null;
   private Domicilio domicilioResponsableDB=null;
   private Tipodocumento[] tiposDoc=null;
    private metalsoft.negocio.rrhh.Domicilio domicilioCliente;
    private Cliente cliente;
    private long idDomicilioCliente;
    private metalsoft.datos.dbobject.Responsable responsableDB;
    private long idResponsable;
    private metalsoft.negocio.compras.Responsable responsable;
    private long idCliente;
    private metalsoft.datos.dbobject.Cliente clienteDB;
   /**
    * @roseuid 4C280D160306
    */
   public GestorCliente() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D010D
    */
   public void razonSocial() 
   {
    
   }
   
   /**
    * @roseuid 4C1FF60D010E
    */
   public void numeroDeCuit() 
   {
    
   }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Domicilio getDomicilioResponsableDB() {
        return domicilioResponsableDB;
    }

    public void setDomicilioResponsableDB(Domicilio domicilioResponsableDB) {
        this.domicilioResponsableDB = domicilioResponsableDB;
    }
   public void tomarDomicilioResponsable(metalsoft.datos.dbobject.Domicilio domDB)
   {
       setDomicilioResponsableDB(domDB);
   }

    public metalsoft.datos.dbobject.Responsable getResponsableDB() {
        return responsableDB;
    }

    public void setResponsableDB(metalsoft.datos.dbobject.Responsable responsableDB) {
        this.responsableDB = responsableDB;
    }

    public Domicilio getDomicilioClienteDB() {
        return domicilioClienteDB;
    }

    public void setDomicilioClienteDB(Domicilio domicilioClienteDB) {
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
            ci = dao.findAll(pg.concectGetCn());
            cinegocio=Parser.parseToCondIva(ci);
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
            ci = dao.findAll(pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem("--Seleccionar--");
            for(int i=0;i<ci.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(i));
                item.setMostrar(ci[i].getNombre());
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

   public ItemCombo[] buscarClientes(String valor)
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
        return Parser.parseToItemCombo(clientes);
    }



   public void obtenerPrioridades(JComboBox combo) {
        PrioridadDAO dao=new DAOFactoryImpl().createPrioridadDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        combo.removeAllItems();
        try {
            pr = dao.findAll(pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem("--Seleccionar--");
            for(int i=0;i<pr.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(i));
                item.setMostrar(pr[i].getNombre());
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
            combo.addItem("--Seleccionar--");
            for(int i=0;i<estados.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(i));
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
                item.setId(String.valueOf(i));
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
                item.setId(String.valueOf(i));
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

   public void buscarLocalidadesDeProvincia(JComboBox combo, int index) {
        LocalidadDAO dao=new DAOFactoryImpl().createLocalidadDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        try {
            localidades = dao.findByProvincia(provincias[index].getIdprovincia(),pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<localidades.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(i));
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

   public void buscarBarriosDeLocalidad(JComboBox combo, int index) {
        BarrioDAO dao=new DAOFactoryImpl().createBarrioDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        try {
            barrios = dao.findByLocalidad(localidades[index].getIdlocalidad(),pg.concectGetCn());
            ItemCombo item=null;
            combo.addItem(new ItemCombo("-1","--Seleccionar--"));
            for(int i=0;i<barrios.length;i++)
            {
                item=new ItemCombo();
                item.setId(String.valueOf(i));
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
        return pr[index].getIdprioridad();
    }

    public long obtenerIdTipoDoc(int index)
    {
        return tiposDoc[index].getIdtipodocumento();
    }
    public long obtenerIdCondIva(int index)
    {
        return ci[index].getIdcondicioniva();
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
        domicilioClienteDB=new Domicilio();
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


    public long registrarResponsable(Responsable responsable, int indexTipoDoc, long idDomicilio) {
        long result=-1;
        ResponsableDAO dao=new DAOFactoryImpl().createResponsableDAO();
        PostgreSQLManager pg=new PostgreSQLManager();
        Connection cn=null;
        responsableDB=new metalsoft.datos.dbobject.Responsable();
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
        clienteDB=new metalsoft.datos.dbobject.Cliente();
        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();

        try {
            cn = pg.concectGetCn();
            clienteDB.setCelular(cliente.getCelular());
            clienteDB.setCondicioniva(ci[indexCondIva].getIdcondicioniva());
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
            clienteDB.setPrioridad(pr[indexPrioridad].getIdprioridad());
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
        clienteDB=new metalsoft.datos.dbobject.Cliente();
        //long idTipoDoc=tiposDoc[indexTipoDoc].getIdtipodocumento();

        try {
            cn = pg.concectGetCn();
            cn.setAutoCommit(false);
            clienteDB.setCelular(cliente.getCelular());
            clienteDB.setCondicioniva(ci[indexCondIva].getIdcondicioniva());
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
            clienteDB.setPrioridad(pr[indexPrioridad].getIdprioridad());
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
    * @roseuid 4C1FF60D0129
    */
   public void mostrarDatosCliente() 
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

    public void tomarDomicilioCliente(metalsoft.negocio.rrhh.Domicilio dom, metalsoft.datos.dbobject.Domicilio domDB) {
        domicilioCliente=dom;
        domicilioClienteDB=domDB;
    }

    public void tomarResponsableCliente(Responsable respNegocio, long idResponsable) {
        responsable=respNegocio;
        this.idResponsable=idResponsable;
    }

    public void tomarResponsableClienteDB(metalsoft.datos.dbobject.Responsable respDB) {
        setResponsableDB(respDB);
    }

    public void tomarDomicilioClienteDB(Domicilio domDB) {
        setDomicilioClienteDB(domDB);
    }

    public metalsoft.datos.dbobject.Cliente obtenerClienteSeleccionado(int index) {
        clienteDB=clientes[index];
        return clienteDB;
    }



}
