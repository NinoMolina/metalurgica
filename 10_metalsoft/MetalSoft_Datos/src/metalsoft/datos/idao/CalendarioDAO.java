/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Oct 05 14:15:16 ART 2010
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
public interface CalendarioDAO
{
	public int insert(CalendarioDB calendario, Connection con ) throws CalendarioException;
	public int update(CalendarioPK calendariopk, CalendarioDB calendario, Connection con) throws CalendarioException;
	public int delete(CalendarioPK calendariopk, Connection con) throws CalendarioException;
	public CalendarioDB findByPrimaryKey(CalendarioPK calendariopk , Connection con) throws CalendarioException;
	public CalendarioDB[] findAll(Connection con) throws CalendarioException;
	public CalendarioDB[] findByDia(int dia,Connection con) throws CalendarioException;
	public CalendarioDB[] findByMes(int mes,Connection con) throws CalendarioException;
	public CalendarioDB[] findByAnio(int anio,Connection con) throws CalendarioException;
	public CalendarioDB[] findById(long id,Connection con) throws CalendarioException;
	public CalendarioDB[] findByFecha(Date fecha,Connection con) throws CalendarioException;
	public CalendarioDB[] findByTodoeldia(boolean todoeldia,Connection con) throws CalendarioException;
	public CalendarioDB[] findByHoradesde(Time horadesde,Connection con) throws CalendarioException;
	public CalendarioDB[] findByHorahasta(Time horahasta,Connection con) throws CalendarioException;
	public CalendarioDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws CalendarioException;
	public CalendarioDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws CalendarioException;
}
