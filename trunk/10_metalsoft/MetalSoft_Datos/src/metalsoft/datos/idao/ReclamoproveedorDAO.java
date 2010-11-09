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
	public int insert(Reclamoproveedor reclamoproveedor, Connection con ) throws ReclamoproveedorException;
	public int update(ReclamoproveedorPK reclamoproveedorpk, Reclamoproveedor reclamoproveedor, Connection con) throws ReclamoproveedorException;
	public int delete(ReclamoproveedorPK reclamoproveedorpk, Connection con) throws ReclamoproveedorException;
	public Reclamoproveedor findByPrimaryKey(ReclamoproveedorPK reclamoproveedorpk , Connection con) throws ReclamoproveedorException;
	public Reclamoproveedor[] findAll(Connection con) throws ReclamoproveedorException;
	public Reclamoproveedor[] findByIdreclamo(long idreclamo,Connection con) throws ReclamoproveedorException;
	public Reclamoproveedor[] findByNroreclamo(long nroreclamo,Connection con) throws ReclamoproveedorException;
	public Reclamoproveedor[] findByTiporeclamo(long tiporeclamo,Connection con) throws ReclamoproveedorException;
	public Reclamoproveedor[] findByMotivo(String motivo,Connection con) throws ReclamoproveedorException;
	public Reclamoproveedor[] findByFechareclamo(Date fechareclamo,Connection con) throws ReclamoproveedorException;
	public Reclamoproveedor[] findByCompra(long compra,Connection con) throws ReclamoproveedorException;
	public Reclamoproveedor[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws ReclamoproveedorException;
	public Reclamoproveedor[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws ReclamoproveedorException;
}