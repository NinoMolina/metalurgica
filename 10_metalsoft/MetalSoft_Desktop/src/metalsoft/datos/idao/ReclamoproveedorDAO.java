/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:04 ART 2010
*
* DAO-Generator Version 1.2
*
*/
package metalsoft.datos.idao;
import java.math.*;
import java.sql.*;
import java.net.URL;
import java.util.Collection;
import java.util.ArrayList;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
public interface ReclamoproveedorDAO
{
	public int insert(ReclamoproveedorDB reclamoproveedor, Connection con ) throws ReclamoproveedorException;
	public int update(ReclamoproveedorPKDB reclamoproveedorpk, ReclamoproveedorDB reclamoproveedor, Connection con) throws ReclamoproveedorException;
	public int delete(ReclamoproveedorPKDB reclamoproveedorpk, Connection con) throws ReclamoproveedorException;
	public ReclamoproveedorDB findByPrimaryKey(ReclamoproveedorPKDB reclamoproveedorpk , Connection con) throws ReclamoproveedorException;
	public ReclamoproveedorDB[] findAll(Connection con) throws ReclamoproveedorException;
	public ReclamoproveedorDB[] findByIdreclamo(long idreclamo,Connection con) throws ReclamoproveedorException;
	public ReclamoproveedorDB[] findByNroreclamo(long nroreclamo,Connection con) throws ReclamoproveedorException;
	public ReclamoproveedorDB[] findByTiporeclamo(long tiporeclamo,Connection con) throws ReclamoproveedorException;
	public ReclamoproveedorDB[] findByMotivo(String motivo,Connection con) throws ReclamoproveedorException;
	public ReclamoproveedorDB[] findByFechareclamo(Date fechareclamo,Connection con) throws ReclamoproveedorException;
	public ReclamoproveedorDB[] findByCompra(long compra,Connection con) throws ReclamoproveedorException;
	public ReclamoproveedorDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws ReclamoproveedorException;
	public ReclamoproveedorDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws ReclamoproveedorException;
}
