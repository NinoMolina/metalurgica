/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:01 ART 2010
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
public interface CargoDAO
{
	public int insert(CargoDB cargo, Connection con ) throws CargoException;
	public int update(CargoPKDB cargopk, CargoDB cargo, Connection con) throws CargoException;
	public int delete(CargoPKDB cargopk, Connection con) throws CargoException;
	public CargoDB findByPrimaryKey(CargoPKDB cargopk , Connection con) throws CargoException;
	public CargoDB[] findAll(Connection con) throws CargoException;
	public CargoDB[] findByIdcargo(long idcargo,Connection con) throws CargoException;
	public CargoDB[] findByNombre(String nombre,Connection con) throws CargoException;
	public CargoDB[] findByDescripcion(String descripcion,Connection con) throws CargoException;
	public CargoDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws CargoException;
	public CargoDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws CargoException;
}