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
import java.util.*;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
public interface ProveedorxmateriaprimaDAO
{
	public int insert(ProveedorxmateriaprimaDB proveedorxmateriaprima, Connection con ) throws ProveedorxmateriaprimaException;
	public int update(ProveedorxmateriaprimaPKDB proveedorxmateriaprimapk, ProveedorxmateriaprimaDB proveedorxmateriaprima, Connection con) throws ProveedorxmateriaprimaException;
	public int delete(ProveedorxmateriaprimaPKDB proveedorxmateriaprimapk, Connection con) throws ProveedorxmateriaprimaException;
	public ProveedorxmateriaprimaDB findByPrimaryKey(ProveedorxmateriaprimaPKDB proveedorxmateriaprimapk , Connection con) throws ProveedorxmateriaprimaException;
	public ProveedorxmateriaprimaDB[] findAll(Connection con) throws ProveedorxmateriaprimaException;
	public ProveedorxmateriaprimaDB[] findByIdproveedor(long idproveedor,Connection con) throws ProveedorxmateriaprimaException;
	public ProveedorxmateriaprimaDB[] findByIdmateriaprima(long idmateriaprima,Connection con) throws ProveedorxmateriaprimaException;
	public ProveedorxmateriaprimaDB[] findByPrecio(double precio,Connection con) throws ProveedorxmateriaprimaException;
	public ProveedorxmateriaprimaDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws ProveedorxmateriaprimaException;
	public ProveedorxmateriaprimaDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws ProveedorxmateriaprimaException;
}