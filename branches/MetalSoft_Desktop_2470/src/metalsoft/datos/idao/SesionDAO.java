/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:05 ART 2010
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
public interface SesionDAO
{
	public int insert(SesionDB sesion, Connection con ) throws SesionException;
	public int update(SesionPKDB sesionpk, SesionDB sesion, Connection con) throws SesionException;
	public int delete(SesionPKDB sesionpk, Connection con) throws SesionException;
	public SesionDB findByPrimaryKey(SesionPKDB sesionpk , Connection con) throws SesionException;
	public SesionDB[] findAll(Connection con) throws SesionException;
	public SesionDB[] findByIdsesion(long idsesion,Connection con) throws SesionException;
	public SesionDB[] findByFechainicio(Date fechainicio,Connection con) throws SesionException;
	public SesionDB[] findByFechafin(Date fechafin,Connection con) throws SesionException;
	public SesionDB[] findByHorainicio(Time horainicio,Connection con) throws SesionException;
	public SesionDB[] findByHorafin(Time horafin,Connection con) throws SesionException;
	public SesionDB[] findByUsuario(long usuario,Connection con) throws SesionException;
	public SesionDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws SesionException;
	public SesionDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws SesionException;
}
