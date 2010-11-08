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
	public int insert(Cargo cargo, Connection con ) throws CargoException;
	public int update(CargoPK cargopk, Cargo cargo, Connection con) throws CargoException;
	public int delete(CargoPK cargopk, Connection con) throws CargoException;
	public Cargo findByPrimaryKey(CargoPK cargopk , Connection con) throws CargoException;
	public Cargo[] findAll(Connection con) throws CargoException;
	public Cargo[] findByIdcargo(long idcargo,Connection con) throws CargoException;
	public Cargo[] findByNombre(String nombre,Connection con) throws CargoException;
	public Cargo[] findByDescripcion(String descripcion,Connection con) throws CargoException;
	public Cargo[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws CargoException;
	public Cargo[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws CargoException;
}