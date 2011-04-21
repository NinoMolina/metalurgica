/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:04 ART 2010
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
public interface PlanprocesoscalidadDAO
{
	public int insert(PlanprocesoscalidadDB planprocesoscalidad, Connection con ) throws PlanprocesoscalidadException;
	public int update(PlanprocesoscalidadPKDB planprocesoscalidadpk, PlanprocesoscalidadDB planprocesoscalidad, Connection con) throws PlanprocesoscalidadException;
	public int delete(PlanprocesoscalidadPKDB planprocesoscalidadpk, Connection con) throws PlanprocesoscalidadException;
	public PlanprocesoscalidadDB findByPrimaryKey(PlanprocesoscalidadPKDB planprocesoscalidadpk , Connection con) throws PlanprocesoscalidadException;
	public PlanprocesoscalidadDB[] findAll(Connection con) throws PlanprocesoscalidadException;
	public PlanprocesoscalidadDB[] findByIdplanprocesoscalidad(long idplanprocesoscalidad,Connection con) throws PlanprocesoscalidadException;
	public PlanprocesoscalidadDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws PlanprocesoscalidadException;
	public PlanprocesoscalidadDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws PlanprocesoscalidadException;
}