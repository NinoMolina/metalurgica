/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:02 ART 2010
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
public interface EstadocompraDAO
{
	public int insert(Estadocompra estadocompra, Connection con ) throws EstadocompraException;
	public int update(EstadocompraPK estadocomprapk, Estadocompra estadocompra, Connection con) throws EstadocompraException;
	public int delete(EstadocompraPK estadocomprapk, Connection con) throws EstadocompraException;
	public Estadocompra findByPrimaryKey(EstadocompraPK estadocomprapk , Connection con) throws EstadocompraException;
	public Estadocompra[] findAll(Connection con) throws EstadocompraException;
	public Estadocompra[] findByIdestado(long idestado,Connection con) throws EstadocompraException;
	public Estadocompra[] findByNombre(String nombre,Connection con) throws EstadocompraException;
	public Estadocompra[] findByDescripcion(String descripcion,Connection con) throws EstadocompraException;
	public Estadocompra[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws EstadocompraException;
	public Estadocompra[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws EstadocompraException;
}
