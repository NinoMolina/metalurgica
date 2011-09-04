/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.gestores;

import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.CondicionivaDB;
import metalsoft.datos.exception.ClienteException;
import metalsoft.datos.exception.CondicionivaException;
import metalsoft.datos.factory.DAOFactoryCreater;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.ClienteDAO;
import metalsoft.datos.idao.CondicionivaDAO;
import metalsoft.negocio.ventas.Cliente;
import metalsoft.negocio.ventas.CondicionIva;

/**
 *
 * @author Nino
 */
public class GestorPedidoCotizacionDB {

    public Cliente[] buscarClientes(String valor)
    {
        ClienteDAO dao=new DAOFactoryImpl().createClienteDAO();
        Connection cn=null;
        metalsoft.datos.dbobject.ClienteDB[] clientes=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            clientes = dao.findExecutingUserWhere("razonsocial ILIKE '"+valor+"%'", sqlParams, cn);
            cn.close();
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return parseToCliente(clientes);
    }

    private Cliente[] parseToCliente(metalsoft.datos.dbobject.ClienteDB[] clientes)
    {
        if(clientes==null)return null;
        Connection cn=null;
        CondicionivaDAO dao=null;
        try {
            cn = new PostgreSQLManager().concectGetCn();
            dao=new DAOFactoryImpl().createCondicionivaDAO();
        } catch (Exception ex) {
            Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        Cliente[] c=new Cliente[clientes.length];
        for(int i=0;i<clientes.length;i++)
        {
            Cliente x=new Cliente();
            x.setRazonSocial(clientes[i].getRazonsocial());
            x.setCUIT(clientes[i].getCuit());
            metalsoft.datos.dbobject.CondicionivaDB iva=null;
            try {
                iva = dao.findByIdcondicioniva(clientes[i].getCondicioniva(), cn)[0];
            } catch (CondicionivaException ex) {
                Logger.getLogger(GestorPedidoCotizacionDB.class.getName()).log(Level.SEVERE, null, ex);
            }
            CondicionIva ci=new CondicionIva();
            ci.setDescripcion(iva.getDescripcion());
            ci.setNombre(iva.getNombre());
            x.setIva(ci);
            c[i]=x;
        }
        return c;
    }
}
