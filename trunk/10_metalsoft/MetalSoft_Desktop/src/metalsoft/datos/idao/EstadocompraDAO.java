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
	public int insert(EstadocompraDB estadocompra, Connection con ) throws EstadocompraException;
	public int update(EstadocompraPKDB estadocomprapk, EstadocompraDB estadocompra, Connection con) throws EstadocompraException;
	public int delete(EstadocompraPKDB estadocomprapk, Connection con) throws EstadocompraException;
	public EstadocompraDB findByPrimaryKey(EstadocompraPKDB estadocomprapk , Connection con) throws EstadocompraException;
	public EstadocompraDB[] findAll(Connection con) throws EstadocompraException;
	public EstadocompraDB[] findByIdestado(long idestado,Connection con) throws EstadocompraException;
	public EstadocompraDB[] findByNombre(String nombre,Connection con) throws EstadocompraException;
	public EstadocompraDB[] findByDescripcion(String descripcion,Connection con) throws EstadocompraException;
	public EstadocompraDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws EstadocompraException;
	public EstadocompraDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws EstadocompraException;
}
