/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Aug 30 13:24:00 ART 2010
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
public interface DetallepresupuestoDAO
{
	public int insert(DetallepresupuestoDB detallepresupuesto, Connection con ) throws DetallepresupuestoException;
	public int update(DetallepresupuestoPKDB detallepresupuestopk, DetallepresupuestoDB detallepresupuesto, Connection con) throws DetallepresupuestoException;
	public int delete(DetallepresupuestoPKDB detallepresupuestopk, Connection con) throws DetallepresupuestoException;
	public DetallepresupuestoDB findByPrimaryKey(DetallepresupuestoPKDB detallepresupuestopk , Connection con) throws DetallepresupuestoException;
	public DetallepresupuestoDB[] findAll(Connection con) throws DetallepresupuestoException;
	public DetallepresupuestoDB[] findByIddetalle(long iddetalle,Connection con) throws DetallepresupuestoException;
	public DetallepresupuestoDB[] findByIdpresupuesto(long idpresupuesto,Connection con) throws DetallepresupuestoException;
	public DetallepresupuestoDB[] findByIddetallepedido(long iddetallepedido,Connection con) throws DetallepresupuestoException;
	public DetallepresupuestoDB[] findByIdproducto(long idproducto,Connection con) throws DetallepresupuestoException;
	public DetallepresupuestoDB[] findByCantidad(int cantidad,Connection con) throws DetallepresupuestoException;
	public DetallepresupuestoDB[] findByPrecio(double precio,Connection con) throws DetallepresupuestoException;
	public DetallepresupuestoDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws DetallepresupuestoException;
	public DetallepresupuestoDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws DetallepresupuestoException;
}
