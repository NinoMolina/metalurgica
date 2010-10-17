/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Oct 17 02:56:00 ART 2010
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
public interface PlanificacionproduccionDAO
{
	public int insert(Planificacionproduccion planificacionproduccion, Connection con ) throws PlanificacionproduccionException;
	public int update(PlanificacionproduccionPK planificacionproduccionpk, Planificacionproduccion planificacionproduccion, Connection con) throws PlanificacionproduccionException;
	public int delete(PlanificacionproduccionPK planificacionproduccionpk, Connection con) throws PlanificacionproduccionException;
	public Planificacionproduccion findByPrimaryKey(PlanificacionproduccionPK planificacionproduccionpk , Connection con) throws PlanificacionproduccionException;
	public Planificacionproduccion[] findAll(Connection con) throws PlanificacionproduccionException;
	public Planificacionproduccion[] findByIdplanificacionproduccion(long idplanificacionproduccion,Connection con) throws PlanificacionproduccionException;
	public Planificacionproduccion[] findByNroplanificacion(long nroplanificacion,Connection con) throws PlanificacionproduccionException;
	public Planificacionproduccion[] findByFechacreacion(Date fechacreacion,Connection con) throws PlanificacionproduccionException;
	public Planificacionproduccion[] findByObservaciones(String observaciones,Connection con) throws PlanificacionproduccionException;
	public Planificacionproduccion[] findByFechainicioprevista(Date fechainicioprevista,Connection con) throws PlanificacionproduccionException;
	public Planificacionproduccion[] findByFechafinprevista(Date fechafinprevista,Connection con) throws PlanificacionproduccionException;
	public Planificacionproduccion[] findByPedido(long pedido,Connection con) throws PlanificacionproduccionException;
	public Planificacionproduccion[] findByIdestado(long idestado,Connection con) throws PlanificacionproduccionException;
	public Planificacionproduccion[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws PlanificacionproduccionException;
	public Planificacionproduccion[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws PlanificacionproduccionException;
}
