/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:39:05 GYT 2010
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
public interface EstadomaquinaDAO
{
	public int insert(Estadomaquina estadomaquina, Connection con ) throws EstadomaquinaException;
	public int update(EstadomaquinaPK estadomaquinapk, Estadomaquina estadomaquina, Connection con) throws EstadomaquinaException;
	public int delete(EstadomaquinaPK estadomaquinapk, Connection con) throws EstadomaquinaException;
	public Estadomaquina findByPrimaryKey(EstadomaquinaPK estadomaquinapk , Connection con) throws EstadomaquinaException;
	public Estadomaquina[] findAll(Connection con) throws EstadomaquinaException;
	public Estadomaquina[] findByIdestado(long idestado,Connection con) throws EstadomaquinaException;
	public Estadomaquina[] findByNombre(String nombre,Connection con) throws EstadomaquinaException;
	public Estadomaquina[] findByDescripcion(String descripcion,Connection con) throws EstadomaquinaException;
	public Estadomaquina[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws EstadomaquinaException;
	public Estadomaquina[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws EstadomaquinaException;
}
