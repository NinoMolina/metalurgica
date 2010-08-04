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
import java.util.*;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
public interface DetalleproductoDAO
{
	public int insert(DetalleproductoDB detalleproducto, Connection con ) throws DetalleproductoException;
	public int update(DetalleproductoPK detalleproductopk, DetalleproductoDB detalleproducto, Connection con) throws DetalleproductoException;
	public int delete(DetalleproductoPK detalleproductopk, Connection con) throws DetalleproductoException;
	public DetalleproductoDB findByPrimaryKey(DetalleproductoPK detalleproductopk , Connection con) throws DetalleproductoException;
	public DetalleproductoDB[] findAll(Connection con) throws DetalleproductoException;
	public DetalleproductoDB[] findByIddetalle(long iddetalle,Connection con) throws DetalleproductoException;
	public DetalleproductoDB[] findByIdproducto(long idproducto,Connection con) throws DetalleproductoException;
	public DetalleproductoDB[] findByCantidadpiezas(int cantidadpiezas,Connection con) throws DetalleproductoException;
	public DetalleproductoDB[] findByDescripcion(String descripcion,Connection con) throws DetalleproductoException;
	public DetalleproductoDB[] findByPieza(long pieza,Connection con) throws DetalleproductoException;
	public DetalleproductoDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws DetalleproductoException;
	public DetalleproductoDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws DetalleproductoException;
}
