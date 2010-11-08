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
public interface TipomaquinaDAO
{
	public int insert(Tipomaquina tipomaquina, Connection con ) throws TipomaquinaException;
	public int update(TipomaquinaPK tipomaquinapk, Tipomaquina tipomaquina, Connection con) throws TipomaquinaException;
	public int delete(TipomaquinaPK tipomaquinapk, Connection con) throws TipomaquinaException;
	public Tipomaquina findByPrimaryKey(TipomaquinaPK tipomaquinapk , Connection con) throws TipomaquinaException;
	public Tipomaquina[] findAll(Connection con) throws TipomaquinaException;
	public Tipomaquina[] findByIdtipomaquina(long idtipomaquina,Connection con) throws TipomaquinaException;
	public Tipomaquina[] findByNombre(String nombre,Connection con) throws TipomaquinaException;
	public Tipomaquina[] findByDescripcion(String descripcion,Connection con) throws TipomaquinaException;
	public Tipomaquina[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws TipomaquinaException;
	public Tipomaquina[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws TipomaquinaException;
}