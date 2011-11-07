/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:01 ART 2010
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
public interface DetallereclamoproveedorDAO
{
	public int insert(DetallereclamoproveedorDB detallereclamoproveedor, Connection con ) throws DetallereclamoproveedorException;
	public int update(DetallereclamoproveedorPKDB detallereclamoproveedorpk, DetallereclamoproveedorDB detallereclamoproveedor, Connection con) throws DetallereclamoproveedorException;
	public int delete(DetallereclamoproveedorPKDB detallereclamoproveedorpk, Connection con) throws DetallereclamoproveedorException;
	public DetallereclamoproveedorDB findByPrimaryKey(DetallereclamoproveedorPKDB detallereclamoproveedorpk , Connection con) throws DetallereclamoproveedorException;
	public DetallereclamoproveedorDB[] findAll(Connection con) throws DetallereclamoproveedorException;
	public DetallereclamoproveedorDB[] findByIddetalle(long iddetalle,Connection con) throws DetallereclamoproveedorException;
	public DetallereclamoproveedorDB[] findByIdreclamo(long idreclamo,Connection con) throws DetallereclamoproveedorException;
	public DetallereclamoproveedorDB[] findByCantidad(int cantidad,Connection con) throws DetallereclamoproveedorException;
	public DetallereclamoproveedorDB[] findByDescripcion(String descripcion,Connection con) throws DetallereclamoproveedorException;
	public DetallereclamoproveedorDB[] findByMotivo(String motivo,Connection con) throws DetallereclamoproveedorException;
	public DetallereclamoproveedorDB[] findByIddetallecompra(long iddetallecompra,Connection con) throws DetallereclamoproveedorException;
	public DetallereclamoproveedorDB[] findByFechaegreso(Date fechaegreso,Connection con) throws DetallereclamoproveedorException;
	public DetallereclamoproveedorDB[] findByIdcompra(long idcompra,Connection con) throws DetallereclamoproveedorException;
	public DetallereclamoproveedorDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws DetallereclamoproveedorException;
	public DetallereclamoproveedorDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws DetallereclamoproveedorException;
}