/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:38:44 GYT 2010
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
public interface DetallepedidoDAO
{
	public int insert(Detallepedido detallepedido, Connection con ) throws DetallepedidoException;
	public int update(DetallepedidoPK detallepedidopk, Detallepedido detallepedido, Connection con) throws DetallepedidoException;
	public int delete(DetallepedidoPK detallepedidopk, Connection con) throws DetallepedidoException;
	public Detallepedido findByPrimaryKey(DetallepedidoPK detallepedidopk , Connection con) throws DetallepedidoException;
	public Detallepedido[] findAll(Connection con) throws DetallepedidoException;
	public Detallepedido[] findByIddetalle(long iddetalle,Connection con) throws DetallepedidoException;
	public Detallepedido[] findByIdpedido(long idpedido,Connection con) throws DetallepedidoException;
	public Detallepedido[] findByPrecio(double precio,Connection con) throws DetallepedidoException;
	public Detallepedido[] findByCantidad(int cantidad,Connection con) throws DetallepedidoException;
	public Detallepedido[] findByProducto(long producto,Connection con) throws DetallepedidoException;
	public Detallepedido[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws DetallepedidoException;
	public Detallepedido[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws DetallepedidoException;
}
