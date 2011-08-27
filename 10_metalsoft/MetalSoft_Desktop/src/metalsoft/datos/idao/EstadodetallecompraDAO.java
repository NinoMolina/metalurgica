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
public interface EstadodetallecompraDAO
{
	public int insert(EstadodetallecompraDB estadodetallecompra, Connection con ) throws EstadodetallecompraException;
	public int update(EstadodetallecompraPKDB estadodetallecomprapk, EstadodetallecompraDB estadodetallecompra, Connection con) throws EstadodetallecompraException;
	public int delete(EstadodetallecompraPKDB estadodetallecomprapk, Connection con) throws EstadodetallecompraException;
	public EstadodetallecompraDB findByPrimaryKey(EstadodetallecompraPKDB estadodetallecomprapk , Connection con) throws EstadodetallecompraException;
	public EstadodetallecompraDB[] findAll(Connection con) throws EstadodetallecompraException;
	public EstadodetallecompraDB[] findByIdestado(long idestado,Connection con) throws EstadodetallecompraException;
	public EstadodetallecompraDB[] findByNombre(String nombre,Connection con) throws EstadodetallecompraException;
	public EstadodetallecompraDB[] findByDescripcion(String descripcion,Connection con) throws EstadodetallecompraException;
	public EstadodetallecompraDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws EstadodetallecompraException;
	public EstadodetallecompraDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws EstadodetallecompraException;
}