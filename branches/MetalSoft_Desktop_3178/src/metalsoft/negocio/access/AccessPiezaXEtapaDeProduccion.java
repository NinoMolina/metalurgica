/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.dbobject.PiezaxetapadeproduccionDB;
import metalsoft.datos.exception.PiezaxetapadeproduccionException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.PiezaxetapadeproduccionDAO;

/**
 *
 * @author Nino
 */
public class AccessPiezaXEtapaDeProduccion {

    public static PiezaxetapadeproduccionDB[] findByIdpieza(long id, Connection cn)
    {
        PiezaxetapadeproduccionDB[] vDB=null;
        PiezaxetapadeproduccionDAO dao=new DAOFactoryImpl().createPiezaxetapadeproduccionDAO();
        try {
            vDB = dao.findByIdpieza(id,cn);
        } catch (PiezaxetapadeproduccionException ex) {
            Logger.getLogger(AccessPiezaXEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vDB;
    }
}
