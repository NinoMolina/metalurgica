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
public interface DomicilioDAO
{
	public int insert(Domicilio domicilio, Connection con ) throws DomicilioException;
	public int update(DomicilioPK domiciliopk, Domicilio domicilio, Connection con) throws DomicilioException;
	public int delete(DomicilioPK domiciliopk, Connection con) throws DomicilioException;
	public Domicilio findByPrimaryKey(DomicilioPK domiciliopk , Connection con) throws DomicilioException;
	public Domicilio[] findAll(Connection con) throws DomicilioException;
	public Domicilio[] findByIddomicilio(long iddomicilio,Connection con) throws DomicilioException;
	public Domicilio[] findByCalle(String calle,Connection con) throws DomicilioException;
	public Domicilio[] findByNumerocalle(int numerocalle,Connection con) throws DomicilioException;
	public Domicilio[] findByPiso(int piso,Connection con) throws DomicilioException;
	public Domicilio[] findByDepto(String depto,Connection con) throws DomicilioException;
	public Domicilio[] findByTorre(String torre,Connection con) throws DomicilioException;
	public Domicilio[] findByBarrio(long barrio,Connection con) throws DomicilioException;
	public Domicilio[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws DomicilioException;
	public Domicilio[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws DomicilioException;
}
