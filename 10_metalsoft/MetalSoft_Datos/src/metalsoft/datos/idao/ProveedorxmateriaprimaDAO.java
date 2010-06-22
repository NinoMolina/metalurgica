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
public interface ProveedorxmateriaprimaDAO
{
	public int insert(Proveedorxmateriaprima proveedorxmateriaprima, Connection con ) throws ProveedorxmateriaprimaException;
	public int update(ProveedorxmateriaprimaPK proveedorxmateriaprimapk, Proveedorxmateriaprima proveedorxmateriaprima, Connection con) throws ProveedorxmateriaprimaException;
	public int delete(ProveedorxmateriaprimaPK proveedorxmateriaprimapk, Connection con) throws ProveedorxmateriaprimaException;
	public Proveedorxmateriaprima findByPrimaryKey(ProveedorxmateriaprimaPK proveedorxmateriaprimapk , Connection con) throws ProveedorxmateriaprimaException;
	public Proveedorxmateriaprima[] findAll(Connection con) throws ProveedorxmateriaprimaException;
	public Proveedorxmateriaprima[] findByIdproveedor(long idproveedor,Connection con) throws ProveedorxmateriaprimaException;
	public Proveedorxmateriaprima[] findByIdmateriaprima(long idmateriaprima,Connection con) throws ProveedorxmateriaprimaException;
	public Proveedorxmateriaprima[] findByPrecio(double precio,Connection con) throws ProveedorxmateriaprimaException;
	public Proveedorxmateriaprima[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws ProveedorxmateriaprimaException;
	public Proveedorxmateriaprima[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws ProveedorxmateriaprimaException;
}
