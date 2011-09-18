/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:02 ART 2010
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
public interface DetalletrabajotercerizadoDAO
{
	public int insert(DetalletrabajotercerizadoDB detalletrabajotercerizado, Connection con ) throws DetalletrabajotercerizadoException;
	public int update(DetalletrabajotercerizadoPKDB detalletrabajotercerizadopk, DetalletrabajotercerizadoDB detalletrabajotercerizado, Connection con) throws DetalletrabajotercerizadoException;
	public int delete(DetalletrabajotercerizadoPKDB detalletrabajotercerizadopk, Connection con) throws DetalletrabajotercerizadoException;
	public DetalletrabajotercerizadoDB findByPrimaryKey(DetalletrabajotercerizadoPKDB detalletrabajotercerizadopk , Connection con) throws DetalletrabajotercerizadoException;
	public DetalletrabajotercerizadoDB[] findAll(Connection con) throws DetalletrabajotercerizadoException;
	public DetalletrabajotercerizadoDB[] findByIddetalle(long iddetalle,Connection con) throws DetalletrabajotercerizadoException;
	public DetalletrabajotercerizadoDB[] findByIdtrabajotercerizado(long idtrabajotercerizado,Connection con) throws DetalletrabajotercerizadoException;
	public DetalletrabajotercerizadoDB[] findByMontoparcial(double montoparcial,Connection con) throws DetalletrabajotercerizadoException;
	public DetalletrabajotercerizadoDB[] findByCantidad(int cantidad,Connection con) throws DetalletrabajotercerizadoException;
	public DetalletrabajotercerizadoDB[] findByDescripcion(String descripcion,Connection con) throws DetalletrabajotercerizadoException;
	public DetalletrabajotercerizadoDB[] findByFechaenvioreal(Date fechaenvioreal,Connection con) throws DetalletrabajotercerizadoException;
	public DetalletrabajotercerizadoDB[] findByFechaentregareal(Date fechaentregareal,Connection con) throws DetalletrabajotercerizadoException;
	public DetalletrabajotercerizadoDB[] findByPieza(long pieza,Connection con) throws DetalletrabajotercerizadoException;
	public DetalletrabajotercerizadoDB[] findByProceso(long proceso,Connection con) throws DetalletrabajotercerizadoException;
	public DetalletrabajotercerizadoDB[] findByEstado(long estado,Connection con) throws DetalletrabajotercerizadoException;
	public DetalletrabajotercerizadoDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws DetalletrabajotercerizadoException;
	public DetalletrabajotercerizadoDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws DetalletrabajotercerizadoException;
}