/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import metalsoft.datos.dbobject.DetalleproductoDB;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.DetalleproductoDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.ventas.DetalleProducto;


/**
 *
 * @author Nino
 */
public class AccessDetalleProducto {

    public static void insert(DetalleProducto x, long idPieza, Connection cn) {
        long result=-1;
        DetalleproductoDAO dao=new DAOFactoryImpl().createDetalleproductoDAO();
        DetalleproductoDB detalleDB = null;

        try {
            detalleDB=Parser.parseToDetalleProductoDB(x);
            clienteDB.setCondicioniva(idCondIva);
            clienteDB.setDomicilio(idDomicilio);
            clienteDB.setEstado(idEstado);
            clienteDB.setPrioridad(idPrioridad);
            clienteDB.setResponsable(idResponsable);
            clienteDB.setUsuario(1);

            result=dao.insert(clienteDB, cn);
            clienteDB.setIdcliente(result);
        } catch (Exception ex) {
            Logger.getLogger(AccessCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
