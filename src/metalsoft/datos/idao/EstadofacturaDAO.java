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
public interface EstadofacturaDAO
{
	public int insert(EstadofacturaDB estadofactura, Connection con ) throws EstadofacturaException;
	public int update(EstadofacturaPKDB estadofacturapk, EstadofacturaDB estadofactura, Connection con) throws EstadofacturaException;
	public int delete(EstadofacturaPKDB estadofacturapk, Connection con) throws EstadofacturaException;
	public EstadofacturaDB findByPrimaryKey(EstadofacturaPKDB estadofacturapk , Connection con) throws EstadofacturaException;
	public EstadofacturaDB[] findAll(Connection con) throws EstadofacturaException;
	public EstadofacturaDB[] findByIdestado(long idestado,Connection con) throws EstadofacturaException;
	public EstadofacturaDB[] findByNombre(String nombre,Connection con) throws EstadofacturaException;
	public EstadofacturaDB[] findByDescripcion(String descripcion,Connection con) throws EstadofacturaException;
	public EstadofacturaDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws EstadofacturaException;
	public EstadofacturaDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws EstadofacturaException;
}