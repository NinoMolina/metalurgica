//Source file: D:\\Mis documentos\\Facultad\\Proyecto Final\\Repositorio\\metalurgica\\10_metalsoft\\rational\\metalsoft\\sistema\\compras\\Responsable.java

package metalsoft.negocio.compras;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.DomicilioPK;
import metalsoft.datos.exception.DomicilioException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.DomicilioDAO;
import metalsoft.negocio.access.AccessDomicilio;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.rrhh.Domicilio;
import metalsoft.negocio.rrhh.Persona;

public class Responsable extends Persona 
{
   private String fax;
   
   /**
    * @roseuid 4C27ED0D030D
    */
   public Responsable() 
   {
    
   }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public long crearDomicilio(metalsoft.negocio.rrhh.Domicilio dom, long idBarrio, Connection cn)
   {
        long result=-1;
        result=AccessDomicilio.registrarDomicilio(dom, idBarrio, cn);
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
            Logger.getLogger(Responsable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
   

}
