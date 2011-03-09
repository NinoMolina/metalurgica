/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:03 ART 2010
*
* DAO-Generator Version 1.2
*
*/
package metalsoft.datos.idao;
import java.math.*;
import java.sql.*;
import java.net.URL;
import java.util.*;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
public interface MaquinaxprocesocalidadDAO
{
	public int insert(MaquinaxprocesocalidadDB maquinaxprocesocalidad, Connection con ) throws MaquinaxprocesocalidadException;
	public int update(MaquinaxprocesocalidadPKDB maquinaxprocesocalidadpk, MaquinaxprocesocalidadDB maquinaxprocesocalidad, Connection con) throws MaquinaxprocesocalidadException;
	public int delete(MaquinaxprocesocalidadPKDB maquinaxprocesocalidadpk, Connection con) throws MaquinaxprocesocalidadException;
	public MaquinaxprocesocalidadDB findByPrimaryKey(MaquinaxprocesocalidadPKDB maquinaxprocesocalidadpk , Connection con) throws MaquinaxprocesocalidadException;
	public MaquinaxprocesocalidadDB[] findAll(Connection con) throws MaquinaxprocesocalidadException;
	public MaquinaxprocesocalidadDB[] findByIdprocesocalidad(long idprocesocalidad,Connection con) throws MaquinaxprocesocalidadException;
	public MaquinaxprocesocalidadDB[] findByIdmaquina(long idmaquina,Connection con) throws MaquinaxprocesocalidadException;
	public MaquinaxprocesocalidadDB[] findByDuracion(Time duracion,Connection con) throws MaquinaxprocesocalidadException;
	public MaquinaxprocesocalidadDB[] findByDescripcion(String descripcion,Connection con) throws MaquinaxprocesocalidadException;
	public MaquinaxprocesocalidadDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws MaquinaxprocesocalidadException;
	public MaquinaxprocesocalidadDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws MaquinaxprocesocalidadException;
}
