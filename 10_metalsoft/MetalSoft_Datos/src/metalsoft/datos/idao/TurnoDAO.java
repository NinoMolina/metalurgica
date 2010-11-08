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
import java.util.*;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
public interface TurnoDAO
{
	public int insert(Turno turno, Connection con ) throws TurnoException;
	public int update(TurnoPK turnopk, Turno turno, Connection con) throws TurnoException;
	public int delete(TurnoPK turnopk, Connection con) throws TurnoException;
	public Turno findByPrimaryKey(TurnoPK turnopk , Connection con) throws TurnoException;
	public Turno[] findAll(Connection con) throws TurnoException;
	public Turno[] findByIdturno(long idturno,Connection con) throws TurnoException;
	public Turno[] findByHorainicio(Time horainicio,Connection con) throws TurnoException;
	public Turno[] findByHorafin(Time horafin,Connection con) throws TurnoException;
	public Turno[] findByNombre(String nombre,Connection con) throws TurnoException;
	public Turno[] findByDescripcion(String descripcion,Connection con) throws TurnoException;
	public Turno[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws TurnoException;
	public Turno[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws TurnoException;
}