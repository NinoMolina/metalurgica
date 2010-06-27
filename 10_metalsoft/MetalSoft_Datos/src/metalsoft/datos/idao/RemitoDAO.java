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
import java.util.Collection;
import java.util.ArrayList;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
public interface RemitoDAO
{
	public int insert(Remito remito, Connection con ) throws RemitoException;
	public int update(RemitoPK remitopk, Remito remito, Connection con) throws RemitoException;
	public int delete(RemitoPK remitopk, Connection con) throws RemitoException;
	public Remito findByPrimaryKey(RemitoPK remitopk , Connection con) throws RemitoException;
	public Remito[] findAll(Connection con) throws RemitoException;
	public Remito[] findByIdremito(long idremito,Connection con) throws RemitoException;
	public Remito[] findByNroremito(long nroremito,Connection con) throws RemitoException;
	public Remito[] findByFechaemision(Date fechaemision,Connection con) throws RemitoException;
	public Remito[] findByPedido(long pedido,Connection con) throws RemitoException;
	public Remito[] findByEstado(long estado,Connection con) throws RemitoException;
	public Remito[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws RemitoException;
	public Remito[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws RemitoException;
}
