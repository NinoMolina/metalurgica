/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.DomicilioDB;
import metalsoft.datos.dbobject.DomicilioPK;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.DomicilioDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.rrhh.Domicilio;

/**
 *
 * @author Nino
 */
public class AccessDomicilio {

    public static int registrarDomicilio(Domicilio dom, long idBarrio, Connection cn) {
        int result=-1;
        DomicilioDAO dao=new DAOFactoryImpl().createDomicilioDAO();
        DomicilioDB domDB=null;
        try {
            domDB=Parser.parseToDomicilioDB(dom);
            domDB.setBarrio(idBarrio);
            result=dao.insert(domDB, cn);
            domDB.setIddomicilio(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessDomicilio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static int modificarDomicilio(Domicilio dom, long idDom, long idBarrio, Connection cn) {
        int result=-1;
        DomicilioDAO dao=new DAOFactoryImpl().createDomicilioDAO();
        DomicilioDB domDB=null;
        DomicilioPK pk=new DomicilioPK(idDom);
        try {
            domDB=Parser.parseToDomicilioDB(dom);
            domDB.setBarrio(idBarrio);
            result=dao.update(pk,domDB, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessDomicilio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
