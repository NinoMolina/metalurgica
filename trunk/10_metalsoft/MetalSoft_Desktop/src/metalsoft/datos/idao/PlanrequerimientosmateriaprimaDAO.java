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
public interface PlanrequerimientosmateriaprimaDAO
{
	public int insert(PlanrequerimientosmateriaprimaDB planrequerimientosmateriaprima, Connection con ) throws PlanrequerimientosmateriaprimaException;
	public int update(PlanrequerimientosmateriaprimaPKDB planrequerimientosmateriaprimapk, PlanrequerimientosmateriaprimaDB planrequerimientosmateriaprima, Connection con) throws PlanrequerimientosmateriaprimaException;
	public int delete(PlanrequerimientosmateriaprimaPKDB planrequerimientosmateriaprimapk, Connection con) throws PlanrequerimientosmateriaprimaException;
	public PlanrequerimientosmateriaprimaDB findByPrimaryKey(PlanrequerimientosmateriaprimaPKDB planrequerimientosmateriaprimapk , Connection con) throws PlanrequerimientosmateriaprimaException;
	public PlanrequerimientosmateriaprimaDB[] findAll(Connection con) throws PlanrequerimientosmateriaprimaException;
	public PlanrequerimientosmateriaprimaDB[] findByIdplanrequerimientosmateriaprima(long idplanrequerimientosmateriaprima,Connection con) throws PlanrequerimientosmateriaprimaException;
	public PlanrequerimientosmateriaprimaDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws PlanrequerimientosmateriaprimaException;
	public PlanrequerimientosmateriaprimaDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws PlanrequerimientosmateriaprimaException;
}