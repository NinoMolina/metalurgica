/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:00 ART 2010
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
public interface AsistenciaDAO
{
	public int insert(AsistenciaDB asistencia, Connection con ) throws AsistenciaException;
	public int update(AsistenciaPKDB asistenciapk, AsistenciaDB asistencia, Connection con) throws AsistenciaException;
	public int delete(AsistenciaPKDB asistenciapk, Connection con) throws AsistenciaException;
	public AsistenciaDB findByPrimaryKey(AsistenciaPKDB asistenciapk , Connection con) throws AsistenciaException;
	public AsistenciaDB[] findAll(Connection con) throws AsistenciaException;
	public AsistenciaDB[] findByEmpleado(long empleado,Connection con) throws AsistenciaException;
	public AsistenciaDB[] findByFechaingreso(Date fechaingreso,Connection con) throws AsistenciaException;
    public AsistenciaDB[] findByFechaingresoIdEmpleado(java.sql.Date fechaingreso, long idEmpleado, Connection con) throws AsistenciaException;
	public AsistenciaDB[] findByHoraingreso(Time horaingreso,Connection con) throws AsistenciaException;
	public AsistenciaDB[] findByHoraegreso(Time horaegreso,Connection con) throws AsistenciaException;
	public AsistenciaDB[] findByObservaciones(String observaciones,Connection con) throws AsistenciaException;
	public AsistenciaDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws AsistenciaException;
	public AsistenciaDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws AsistenciaException;
}
