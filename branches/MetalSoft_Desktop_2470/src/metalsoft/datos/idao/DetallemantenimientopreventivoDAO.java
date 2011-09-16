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
public interface DetallemantenimientopreventivoDAO
{
	public int insert(DetallemantenimientopreventivoDB detallemantenimientopreventivo, Connection con ) throws DetallemantenimientopreventivoException;
	public int update(DetallemantenimientopreventivoPKDB detallemantenimientopreventivopk, DetallemantenimientopreventivoDB detallemantenimientopreventivo, Connection con) throws DetallemantenimientopreventivoException;
	public int delete(DetallemantenimientopreventivoPKDB detallemantenimientopreventivopk, Connection con) throws DetallemantenimientopreventivoException;
	public DetallemantenimientopreventivoDB findByPrimaryKey(DetallemantenimientopreventivoPKDB detallemantenimientopreventivopk , Connection con) throws DetallemantenimientopreventivoException;
	public DetallemantenimientopreventivoDB[] findAll(Connection con) throws DetallemantenimientopreventivoException;
	public DetallemantenimientopreventivoDB[] findByIdmantenimientopreventivo(long idmantenimientopreventivo,Connection con) throws DetallemantenimientopreventivoException;
	public DetallemantenimientopreventivoDB[] findByIddetalle(long iddetalle,Connection con) throws DetallemantenimientopreventivoException;
	public DetallemantenimientopreventivoDB[] findByDuracion(Time duracion,Connection con) throws DetallemantenimientopreventivoException;
	public DetallemantenimientopreventivoDB[] findByServicio(long servicio,Connection con) throws DetallemantenimientopreventivoException;
	public DetallemantenimientopreventivoDB[] findByObservaciones(String observaciones,Connection con) throws DetallemantenimientopreventivoException;
	public DetallemantenimientopreventivoDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws DetallemantenimientopreventivoException;
	public DetallemantenimientopreventivoDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws DetallemantenimientopreventivoException;
}
