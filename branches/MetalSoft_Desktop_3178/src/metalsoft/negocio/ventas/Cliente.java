//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\negocio\\ventas\\Cliente.java

package metalsoft.negocio.ventas;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.DomicilioPK;
import metalsoft.datos.dbobject.ResponsablePKDB;
import metalsoft.datos.exception.DomicilioException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.DomicilioDAO;
import metalsoft.datos.idao.ResponsableDAO;
import metalsoft.negocio.access.AccessDomicilio;
import metalsoft.negocio.access.AccessResponsable;
import metalsoft.negocio.adminusuarios.Usuario;
import metalsoft.negocio.compras.PersonaJuridica;
import metalsoft.negocio.compras.Reclamo;
import metalsoft.negocio.compras.Responsable;
import metalsoft.negocio.gestores.AbsGuardable;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.rrhh.Domicilio;
import metalsoft.negocio.rrhh.TipoDocumento;


public class Cliente extends PersonaJuridica
{
   private EstadoCliente estado;
   private long nroCliente;
   private Pedido pedido;
   private Prioridad priodidad;
   private Reclamo reclamo;
   private Usuario usuario;
   public ComprobantePago theComprobantePago[];
   public Usuario theUsuario;
   public Domicilio theDomicilio;
   public TipoDocumento theTipoDocumento;
   public Pedido thePedido[];
   public EstadoCliente theEstadoCliente;
   public Reclamo theReclamo[];
   public PersonaJuridica thePersonaJuridica;
   
   /**
    * @roseuid 4C20571101CC
    */
   public Cliente() 
   {
    
   }

    public EstadoCliente getEstado() {
        return estado;
    }

    public void setEstado(EstadoCliente estado) {
        this.estado = estado;
    }

    public long getNroCliente() {
        return nroCliente;
    }

    public void setNroCliente(long nroCliente) {
        this.nroCliente = nroCliente;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Prioridad getPriodidad() {
        return priodidad;
    }

    public void setPriodidad(Prioridad priodidad) {
        this.priodidad = priodidad;
    }

    public Reclamo getReclamo() {
        return reclamo;
    }

    public void setReclamo(Reclamo reclamo) {
        this.reclamo = reclamo;
    }

    public ComprobantePago[] getTheComprobantePago() {
        return theComprobantePago;
    }

    public void setTheComprobantePago(ComprobantePago[] theComprobantePago) {
        this.theComprobantePago = theComprobantePago;
    }

    public Domicilio getTheDomicilio() {
        return theDomicilio;
    }

    public void setTheDomicilio(Domicilio theDomicilio) {
        this.theDomicilio = theDomicilio;
    }

    public EstadoCliente getTheEstadoCliente() {
        return theEstadoCliente;
    }

    public void setTheEstadoCliente(EstadoCliente theEstadoCliente) {
        this.theEstadoCliente = theEstadoCliente;
    }

    public Pedido[] getThePedido() {
        return thePedido;
    }

    public void setThePedido(Pedido[] thePedido) {
        this.thePedido = thePedido;
    }

    public PersonaJuridica getThePersonaJuridica() {
        return thePersonaJuridica;
    }

    public void setThePersonaJuridica(PersonaJuridica thePersonaJuridica) {
        this.thePersonaJuridica = thePersonaJuridica;
    }

    public Reclamo[] getTheReclamo() {
        return theReclamo;
    }

    public void setTheReclamo(Reclamo[] theReclamo) {
        this.theReclamo = theReclamo;
    }

    public TipoDocumento getTheTipoDocumento() {
        return theTipoDocumento;
    }

    public void setTheTipoDocumento(TipoDocumento theTipoDocumento) {
        this.theTipoDocumento = theTipoDocumento;
    }

    public Usuario getTheUsuario() {
        return theUsuario;
    }

    public void setTheUsuario(Usuario theUsuario) {
        this.theUsuario = theUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
   
   /**
    * @roseuid 4C17F151005A
    */
   public void conocerEstadoCliente() 
   {
    
   }
   
   /**
    * @roseuid 4C17F1940092
    */
   public void conocerPedido() 
   {
    
   }
   
   /**
    * @roseuid 4BC25E7603C9
    */
   public void conocerPrioridad() 
   {
    
   }
   
   /**
    * @roseuid 4BC25E720288
    */
   public void conocerReclamo() 
   {
    
   }
   
   /**
    * @roseuid 4C17F19C022E
    */
   public void conocerUsuario() 
   {
    
   }
   

   
   /**
    * @roseuid 4C1FA6FF0147
    */
   public long crearDomicilio(metalsoft.negocio.rrhh.Domicilio dom, long idBarrio, Connection cn)
   {
        long result=-1;
        result=AccessDomicilio.registrarDomicilio(dom, idBarrio, cn);
        return result;
   }

   public long crearResponsable(Responsable responsable,long idBarrioResp, long idTipoDocResp, Connection cn)
   {
        long result=-1;
        long idDomResp=responsable.crearDomicilio(responsable.getDomicilio(), idBarrioResp, cn);
        result=AccessResponsable.registrar(responsable, idDomResp, idTipoDocResp, cn);
        return result;
   }
   public int modificarResponsable(Responsable responsable, long idResp, long idDom, long idBarrio, long idTipoDoc, Connection cn) {
        int result=-1;
        ResponsableDAO dao=new DAOFactoryImpl().createResponsableDAO();
        metalsoft.datos.dbobject.ResponsableDB responsableDB;
        ResponsablePKDB pk=new ResponsablePKDB(idResp);
        try {
            responsableDB=Parser.parseToResponsableDB(responsable);
            int id=responsable.modificarDomicilio(responsable.getDomicilio(),idDom, idBarrio, cn);
            responsableDB.setDomicilio(idDom);
            responsableDB.setTipodocumento(idTipoDoc);
            result=dao.update(pk, responsableDB, cn);
        } catch (Exception ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
   }

   public int modificarDomicilio(Domicilio dom, long idDom, long idBarrio, Connection cn) {
        int result=-1;
        DomicilioDAO dao=new DAOFactoryImpl().createDomicilioDAO();
        metalsoft.datos.dbobject.DomicilioDB domDB;
        DomicilioPK pk=new DomicilioPK(idDom);
        try {
            domDB=Parser.parseToDomicilioDB(dom);
            domDB.setBarrio(idBarrio);
            result=dao.update(pk,domDB, cn);
        } catch (DomicilioException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
   
   /**
    * @roseuid 4C1FA6F801FD
    */
   public void esCliente() 
   {
    
   }
   
   /**
    * @roseuid 4C1FAE720269
    */
   public void esMoroso() 
   {
    
   }
   
   /**
    * @roseuid 4C1FDB5902CC
    */
   public void esPedidoCotizacion() 
   {
    
   }
   
   /**
    * @roseuid 4C1FAF8D02C7
    */
   public void mostrarFacturrasAdeudadas() 
   {
    
   }
   
   /**
    * @roseuid 4C1FAF5D0342
    */
   public void mostrarNombreResponsable() 
   {
    
   }
   
   /**
    * @roseuid 4C1FA3150028
    */
   public void mostrarPedidosNoPresupuestados() 
   {
    
   }
   
   /**
    * @roseuid 4C1FA73E03BF
    */
   public void tomarUsuarioYContrasenia() 
   {
    
   }

}
