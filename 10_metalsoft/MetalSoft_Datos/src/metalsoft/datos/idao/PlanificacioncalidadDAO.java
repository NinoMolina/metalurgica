/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:39:32 GYT 2010
*
* DAO-Generator Version 1.2
*
*/
package metalsoft.datos.idao;
import java.math.*;
import java.sql.*;
import java.net.URL;
import java.util.Collection;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
public interface PlanificacioncalidadDAO
{
	public int insert(Planificacioncalidad planificacioncalidad, Connection con ) throws PlanificacioncalidadException;
	public int update(PlanificacioncalidadPK planificacioncalidadpk, Planificacioncalidad planificacioncalidad, Connection con) throws PlanificacioncalidadException;
	public int delete(PlanificacioncalidadPK planificacioncalidadpk, Connection con) throws PlanificacioncalidadException;
	public Planificacioncalidad findByPrimaryKey(PlanificacioncalidadPK planificacioncalidadpk , Connection con) throws PlanificacioncalidadException;
	public Planificacioncalidad[] findAll(Connection con) throws PlanificacioncalidadException;
	public Planificacioncalidad[] findByIdplanificacion(long idplanificacion,Connection con) throws PlanificacioncalidadException;
	public Planificacioncalidad[] findByNroplanificacion(long nroplanificacion,Connection con) throws PlanificacioncalidadException;
	public Planificacioncalidad[] findByFechacreacion(Date fechacreacion,Connection con) throws PlanificacioncalidadException;
	public Planificacioncalidad[] findByObservaciones(String observaciones,Connection con) throws PlanificacioncalidadException;
	public Planificacioncalidad[] findByFechainicioprevista(Date fechainicioprevista,Connection con) throws PlanificacioncalidadException;
	public Planificacioncalidad[] findByFechafinprevista(Date fechafinprevista,Connection con) throws PlanificacioncalidadException;
	public Planificacioncalidad[] findByPedido(long pedido,Connection con) throws PlanificacioncalidadException;
	public Planificacioncalidad[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws PlanificacioncalidadException;
	public Planificacioncalidad[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws PlanificacioncalidadException;
}
