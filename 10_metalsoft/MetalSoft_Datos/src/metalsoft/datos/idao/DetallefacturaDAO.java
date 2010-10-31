/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Oct 31 02:26:41 ART 2010
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
public interface DetallefacturaDAO
{
	public int insert(Detallefactura detallefactura, Connection con ) throws DetallefacturaException;
	public int update(DetallefacturaPK detallefacturapk, Detallefactura detallefactura, Connection con) throws DetallefacturaException;
	public int delete(DetallefacturaPK detallefacturapk, Connection con) throws DetallefacturaException;
	public Detallefactura findByPrimaryKey(DetallefacturaPK detallefacturapk , Connection con) throws DetallefacturaException;
	public Detallefactura[] findAll(Connection con) throws DetallefacturaException;
	public Detallefactura[] findByIddetalle(long iddetalle,Connection con) throws DetallefacturaException;
	public Detallefactura[] findByIdfactura(long idfactura,Connection con) throws DetallefacturaException;
	public Detallefactura[] findByIdpedido(long idpedido,Connection con) throws DetallefacturaException;
	public Detallefactura[] findByMontoparcial(double montoparcial,Connection con) throws DetallefacturaException;
	public Detallefactura[] findByCantidad(int cantidad,Connection con) throws DetallefacturaException;
	public Detallefactura[] findByIddetallepedido(long iddetallepedido,Connection con) throws DetallefacturaException;
	public Detallefactura[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws DetallefacturaException;
	public Detallefactura[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws DetallefacturaException;
}
