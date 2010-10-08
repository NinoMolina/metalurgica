/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package metalsoft.negocio.access;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import metalsoft.datos.PostgreSQLManager;
import metalsoft.datos.dbobject.EmpleadoDB;
import metalsoft.datos.dbobject.EmpleadoxturnoDB;
import metalsoft.datos.dbobject.EmpleadoxturnoPK;
import metalsoft.datos.dbobject.EmpleadoPK;
import metalsoft.datos.exception.EmpleadoException;
import metalsoft.datos.factory.DAOFactoryImpl;
import metalsoft.datos.idao.EmpleadoDAO;
import metalsoft.datos.idao.EmpleadoxturnoDAO;
import metalsoft.negocio.gestores.Parser;
import metalsoft.negocio.rrhh.Empleado;
/**
 *
 * @author Vicky
 */
public class AccessEmpleado {

    public static EmpleadoDB[] findByNombreILIKE(String valor, Connection cn) {
        EmpleadoDB[] x=null;
        EmpleadoDAO dao=new DAOFactoryImpl().createEmpleadoDAO();
        Object[] sqlParams=new Object[0];
        //Object[] sqlParams=new Object[1];
        //sqlParams[0]=valor;
        try {
            x = dao.findExecutingUserWhere("nombre ILIKE '"+valor+"%'", sqlParams, cn);
        } catch (Exception ex) {
            Logger.getLogger(AccessEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
    public static EmpleadoDB findById(long valor, Connection cn)
    {
        EmpleadoDB x=null;
        EmpleadoDAO dao=new DAOFactoryImpl().createEmpleadoDAO();
        try {
            x = dao.findByIdempleado(valor, cn)[0];
        } catch (Exception ex) {
            Logger.getLogger(AccessEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
    public static long insert(Empleado empleado,long[] idturno, long idcategoria, long idusuario, long idcargo, long iddomicilio, Connection cn) {
        long result=-1;
        EmpleadoDAO dao=new DAOFactoryImpl().createEmpleadoDAO();
        metalsoft.datos.dbobject.EmpleadoDB empleadoDB = null;

        try {
            empleadoDB=Parser.parseToEmpleadoDB(empleado);
            empleadoDB.setCategoria(idcategoria);
            empleadoDB.setUsuario(idusuario);
            empleadoDB.setDomicilio(iddomicilio);
            empleadoDB.setCargo(idcargo);



            result=dao.insert(empleadoDB, cn);
            empleadoDB.setIdempleado(result);

            EmpleadoxturnoDAO daoturnos=new DAOFactoryImpl().createEmpleadoxturnoDAO();
            for(int i=0; i<idturno.length;i++)
            {
                EmpleadoxturnoDB ext=new EmpleadoxturnoDB();
                ext.setIdempleado(empleadoDB.getIdempleado());
                ext.setIdturno(idturno[i]);
                result=dao.insert(empleadoDB, cn);
            }
        } catch (Exception ex) {
            Logger.getLogger(AccessEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static long update(EtapaDeProduccion etapaDeProduccion,long idEtapaDeProduccion,long idmaquina, long idUnidadMedida, Connection cn) {
        long result=-1;
        EtapadeproduccionDAO dao=new DAOFactoryImpl().createEtapadeproduccionDAO();

        EtapadeproduccionDB etapaDeProduccionDB=null;

        EtapadeproduccionPK pk=new EtapadeproduccionPK(idEtapaDeProduccion);
        try {
            etapaDeProduccionDB=Parser.parseToEtapaDeProduccionDB(etapaDeProduccion);
            etapaDeProduccionDB.setMaquina(idmaquina);
            etapaDeProduccionDB.setUnidadmedida(idUnidadMedida);

            result=dao.update(pk,etapaDeProduccionDB, cn);
        } catch (EtapadeproduccionException ex) {
            Logger.getLogger(AccessEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    public static long delete(long id, Connection cn)
    {
        long result=-1;
        EtapadeproduccionDAO dao=new DAOFactoryImpl().createEtapadeproduccionDAO();

        EtapadeproduccionPK pk=new EtapadeproduccionPK(id);
        try {
            result=dao.delete(pk, cn);
        } catch (EtapadeproduccionException ex) {
            Logger.getLogger(AccessEtapaDeProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
