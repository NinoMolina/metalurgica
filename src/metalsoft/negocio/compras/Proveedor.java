//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\compras\\Proveedor.java

package metalsoft.negocio.compras;

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
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.rrhh.Domicilio;


public class Proveedor extends PersonaJuridica 
{
   private long nroProveedor;

    public long getNroProveedor() {
        return nroProveedor;
    }

    public void setNroProveedor(long nroProveedor) {
        this.nroProveedor = nroProveedor;
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
   public Reclamo theReclamo[];
   public PersonaJuridica thePersonaJuridica;
   
   /**
    * @roseuid 4C27F2B20156
    */
   public Proveedor() 
   {
    
   }
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
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
   /**
    * @roseuid 4BC24EE80350
    */
   public void conocerReclamo() 
   {
    
   }
   
   /**
    * @roseuid 4C1F8FC501FD
    */
   public void tomarCambios() 
   {
    
   }
   
   /**
    * @roseuid 4C1FB3AD006B
    */
   public void esProveedor() 
   {
    
   }

}
