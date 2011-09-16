/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.html.HTMLEditorKit.Parser;
import metalsoft.datos.dbobject.ResponsableDB;
import metalsoft.datos.dbobject.ResponsablePKDB;
import metalsoft.datos.exception.ResponsableException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.ResponsableDAO;
import metalsoft.negocio.compras.Responsable;

/**
 *
 * @author Nino
 */
public class AccessResponsable {

    public static long registrar(Responsable resp, long idDomicilio, long idTipoDoc, Connection cn) {
        long result=-1;
        ResponsableDAO dao=new DAOFactoryImpl().createResponsableDAO();
        ResponsableDB respDB = null;

        try {
            respDB=metalsoft.negocio.gestores.Parser.parseToResponsableDB(resp);
            respDB.setDomicilio(idDomicilio);
            respDB.setTipodocumento(idTipoDoc);
            result=dao.insert(respDB, cn);
            respDB.setIdresponsable(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessResponsable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long modificarCliente(Responsable resp, long idResp, long idDomicilio, long idTipoDoc, Connection cn) {
        long result=-1;
        ResponsableDAO dao=new DAOFactoryImpl().createResponsableDAO();
        ResponsableDB respDB = null;
        ResponsablePKDB pk=new ResponsablePKDB(idResp);
        try {
            respDB=metalsoft.negocio.gestores.Parser.parseToResponsableDB(resp);
            respDB.setDomicilio(idDomicilio);
            respDB.setTipodocumento(idTipoDoc);
            result=dao.update(pk,respDB, cn);
            respDB.setIdresponsable(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessResponsable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static long registrarCliente(ResponsableDB respDB, Connection cn) {
        long result=-1;
        ResponsableDAO dao=new DAOFactoryImpl().createResponsableDAO();

        try {
            result=dao.insert(respDB, cn);
        } catch (ResponsableException ex) {
            Logger.getLogger(AccessResponsable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long modificarCliente(ResponsableDB respDB, ResponsablePKDB pk, Connection cn) {
        long result=-1;
        ResponsableDAO dao=new DAOFactoryImpl().createResponsableDAO();

        try {
            result=dao.update(pk,respDB, cn);
        } catch (ResponsableException ex) {
            Logger.getLogger(AccessResponsable.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
