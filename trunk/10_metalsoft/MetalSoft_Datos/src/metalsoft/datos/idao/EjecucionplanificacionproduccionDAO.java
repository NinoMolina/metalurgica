/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:38:55 GYT 2010
*
* DAO-Generator Version 1.2
*
*/
package metalsoft.datos.idao;
import java.math.*;
import java.sql.*;
import java.net.URL;

import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
public interface EjecucionplanificacionproduccionDAO
{
	public int insert(Ejecucionplanificacionproduccion ejecucionplanificacionproduccion, Connection con ) throws EjecucionplanificacionproduccionException;
	public int update(EjecucionplanificacionproduccionPK ejecucionplanificacionproduccionpk, Ejecucionplanificacionproduccion ejecucionplanificacionproduccion, Connection con) throws EjecucionplanificacionproduccionException;
	public int delete(EjecucionplanificacionproduccionPK ejecucionplanificacionproduccionpk, Connection con) throws EjecucionplanificacionproduccionException;
	public Ejecucionplanificacionproduccion findByPrimaryKey(EjecucionplanificacionproduccionPK ejecucionplanificacionproduccionpk , Connection con) throws EjecucionplanificacionproduccionException;
	public Ejecucionplanificacionproduccion[] findAll(Connection con) throws EjecucionplanificacionproduccionException;
	public Ejecucionplanificacionproduccion[] findByIdejecucion(long idejecucion,Connection con) throws EjecucionplanificacionproduccionException;
	public Ejecucionplanificacionproduccion[] findByIdplanificacionproduccion(long idplanificacionproduccion,Connection con) throws EjecucionplanificacionproduccionException;
	public Ejecucionplanificacionproduccion[] findByFechainicio(Date fechainicio,Connection con) throws EjecucionplanificacionproduccionException;
	public Ejecucionplanificacionproduccion[] findByFechafin(Date fechafin,Connection con) throws EjecucionplanificacionproduccionException;
	public Ejecucionplanificacionproduccion[] findByHorainicio(Time horainicio,Connection con) throws EjecucionplanificacionproduccionException;
	public Ejecucionplanificacionproduccion[] findByHorafin(Time horafin,Connection con) throws EjecucionplanificacionproduccionException;
	public Ejecucionplanificacionproduccion[] findByEstado(long estado,Connection con) throws EjecucionplanificacionproduccionException;
	public Ejecucionplanificacionproduccion[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws EjecucionplanificacionproduccionException;
	public Ejecucionplanificacionproduccion[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws EjecucionplanificacionproduccionException;
}
