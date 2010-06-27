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
public interface EstadoremitoDAO
{
	public int insert(Estadoremito estadoremito, Connection con ) throws EstadoremitoException;
	public int update(EstadoremitoPK estadoremitopk, Estadoremito estadoremito, Connection con) throws EstadoremitoException;
	public int delete(EstadoremitoPK estadoremitopk, Connection con) throws EstadoremitoException;
	public Estadoremito findByPrimaryKey(EstadoremitoPK estadoremitopk , Connection con) throws EstadoremitoException;
	public Estadoremito[] findAll(Connection con) throws EstadoremitoException;
	public Estadoremito[] findByIdestado(long idestado,Connection con) throws EstadoremitoException;
	public Estadoremito[] findByNombre(String nombre,Connection con) throws EstadoremitoException;
	public Estadoremito[] findByDescripcion(String descripcion,Connection con) throws EstadoremitoException;
	public Estadoremito[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws EstadoremitoException;
	public Estadoremito[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws EstadoremitoException;
}
