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
public interface TipoivaDAO
{
	public int insert(TipoivaDB tipoiva, Connection con ) throws TipoivaException;
	public int update(TipoivaPKDB tipoivapk, TipoivaDB tipoiva, Connection con) throws TipoivaException;
	public int delete(TipoivaPKDB tipoivapk, Connection con) throws TipoivaException;
	public TipoivaDB findByPrimaryKey(TipoivaPKDB tipoivapk , Connection con) throws TipoivaException;
	public TipoivaDB[] findAll(Connection con) throws TipoivaException;
	public TipoivaDB[] findByIdtipoiva(long idtipoiva,Connection con) throws TipoivaException;
	public TipoivaDB[] findByNombre(String nombre,Connection con) throws TipoivaException;
	public TipoivaDB[] findByDescripcion(String descripcion,Connection con) throws TipoivaException;
	public TipoivaDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws TipoivaException;
	public TipoivaDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws TipoivaException;
}