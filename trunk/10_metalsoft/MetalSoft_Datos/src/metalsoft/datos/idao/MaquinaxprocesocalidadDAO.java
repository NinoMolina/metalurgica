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
	public int insert(Maquinaxprocesocalidad maquinaxprocesocalidad, Connection con ) throws MaquinaxprocesocalidadException;
	public int update(MaquinaxprocesocalidadPK maquinaxprocesocalidadpk, Maquinaxprocesocalidad maquinaxprocesocalidad, Connection con) throws MaquinaxprocesocalidadException;
	public int delete(MaquinaxprocesocalidadPK maquinaxprocesocalidadpk, Connection con) throws MaquinaxprocesocalidadException;
	public Maquinaxprocesocalidad findByPrimaryKey(MaquinaxprocesocalidadPK maquinaxprocesocalidadpk , Connection con) throws MaquinaxprocesocalidadException;
	public Maquinaxprocesocalidad[] findAll(Connection con) throws MaquinaxprocesocalidadException;
	public Maquinaxprocesocalidad[] findByIdprocesocalidad(long idprocesocalidad,Connection con) throws MaquinaxprocesocalidadException;
	public Maquinaxprocesocalidad[] findByIdmaquina(long idmaquina,Connection con) throws MaquinaxprocesocalidadException;
	public Maquinaxprocesocalidad[] findByDuracion(Time duracion,Connection con) throws MaquinaxprocesocalidadException;
	public Maquinaxprocesocalidad[] findByDescripcion(String descripcion,Connection con) throws MaquinaxprocesocalidadException;
	public Maquinaxprocesocalidad[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws MaquinaxprocesocalidadException;
	public Maquinaxprocesocalidad[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws MaquinaxprocesocalidadException;
}