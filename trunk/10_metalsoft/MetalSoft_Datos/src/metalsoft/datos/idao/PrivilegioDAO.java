/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:10 ART 2010
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
public interface PrivilegioDAO
{
	public int insert(Privilegio privilegio, Connection con ) throws PrivilegioException;
	public int update(PrivilegioPK privilegiopk, Privilegio privilegio, Connection con) throws PrivilegioException;
	public int delete(PrivilegioPK privilegiopk, Connection con) throws PrivilegioException;
	public Privilegio findByPrimaryKey(PrivilegioPK privilegiopk , Connection con) throws PrivilegioException;
	public Privilegio[] findAll(Connection con) throws PrivilegioException;
	public Privilegio[] findByIdprivilegio(long idprivilegio,Connection con) throws PrivilegioException;
	public Privilegio[] findByPrivilegio(String privilegio,Connection con) throws PrivilegioException;
	public Privilegio[] findByDescripcion(String descripcion,Connection con) throws PrivilegioException;
	public Privilegio[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws PrivilegioException;
	public Privilegio[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws PrivilegioException;
}
