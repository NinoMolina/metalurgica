/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:09 ART 2010
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
public interface EstadoejecplancalidadDAO
{
	public int insert(Estadoejecplancalidad estadoejecplancalidad, Connection con ) throws EstadoejecplancalidadException;
	public int update(EstadoejecplancalidadPK estadoejecplancalidadpk, Estadoejecplancalidad estadoejecplancalidad, Connection con) throws EstadoejecplancalidadException;
	public int delete(EstadoejecplancalidadPK estadoejecplancalidadpk, Connection con) throws EstadoejecplancalidadException;
	public Estadoejecplancalidad findByPrimaryKey(EstadoejecplancalidadPK estadoejecplancalidadpk , Connection con) throws EstadoejecplancalidadException;
	public Estadoejecplancalidad[] findAll(Connection con) throws EstadoejecplancalidadException;
	public Estadoejecplancalidad[] findByIdestado(long idestado,Connection con) throws EstadoejecplancalidadException;
	public Estadoejecplancalidad[] findByNombre(String nombre,Connection con) throws EstadoejecplancalidadException;
	public Estadoejecplancalidad[] findByDescripcion(String descripcion,Connection con) throws EstadoejecplancalidadException;
	public Estadoejecplancalidad[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws EstadoejecplancalidadException;
	public Estadoejecplancalidad[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws EstadoejecplancalidadException;
}
