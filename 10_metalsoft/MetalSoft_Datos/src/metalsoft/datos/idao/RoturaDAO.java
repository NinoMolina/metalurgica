/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:05 ART 2010
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
public interface RoturaDAO
{
	public int insert(Rotura rotura, Connection con ) throws RoturaException;
	public int update(RoturaPK roturapk, Rotura rotura, Connection con) throws RoturaException;
	public int delete(RoturaPK roturapk, Connection con) throws RoturaException;
	public Rotura findByPrimaryKey(RoturaPK roturapk , Connection con) throws RoturaException;
	public Rotura[] findAll(Connection con) throws RoturaException;
	public Rotura[] findByIdrotura(long idrotura,Connection con) throws RoturaException;
	public Rotura[] findByNombre(String nombre,Connection con) throws RoturaException;
	public Rotura[] findByDescripcion(String descripcion,Connection con) throws RoturaException;
	public Rotura[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws RoturaException;
	public Rotura[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws RoturaException;
}
