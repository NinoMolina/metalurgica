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
public interface DetallereclamoempresametalurgicaDAO
{
	public int insert(Detallereclamoempresametalurgica detallereclamoempresametalurgica, Connection con ) throws DetallereclamoempresametalurgicaException;
	public int update(DetallereclamoempresametalurgicaPK detallereclamoempresametalurgicapk, Detallereclamoempresametalurgica detallereclamoempresametalurgica, Connection con) throws DetallereclamoempresametalurgicaException;
	public int delete(DetallereclamoempresametalurgicaPK detallereclamoempresametalurgicapk, Connection con) throws DetallereclamoempresametalurgicaException;
	public Detallereclamoempresametalurgica findByPrimaryKey(DetallereclamoempresametalurgicaPK detallereclamoempresametalurgicapk , Connection con) throws DetallereclamoempresametalurgicaException;
	public Detallereclamoempresametalurgica[] findAll(Connection con) throws DetallereclamoempresametalurgicaException;
	public Detallereclamoempresametalurgica[] findByIddetalle(long iddetalle,Connection con) throws DetallereclamoempresametalurgicaException;
	public Detallereclamoempresametalurgica[] findByIdreclamo(long idreclamo,Connection con) throws DetallereclamoempresametalurgicaException;
	public Detallereclamoempresametalurgica[] findByCantidad(int cantidad,Connection con) throws DetallereclamoempresametalurgicaException;
	public Detallereclamoempresametalurgica[] findByDescripcion(String descripcion,Connection con) throws DetallereclamoempresametalurgicaException;
	public Detallereclamoempresametalurgica[] findByMotivo(String motivo,Connection con) throws DetallereclamoempresametalurgicaException;
	public Detallereclamoempresametalurgica[] findByPieza(long pieza,Connection con) throws DetallereclamoempresametalurgicaException;
	public Detallereclamoempresametalurgica[] findByFechaegreso(Date fechaegreso,Connection con) throws DetallereclamoempresametalurgicaException;
	public Detallereclamoempresametalurgica[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws DetallereclamoempresametalurgicaException;
	public Detallereclamoempresametalurgica[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws DetallereclamoempresametalurgicaException;
}
