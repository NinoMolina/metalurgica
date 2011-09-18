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
public interface CategoriaDAO
{
	public int insert(CategoriaDB categoria, Connection con ) throws CategoriaException;
	public int update(CategoriaPKDB categoriapk, CategoriaDB categoria, Connection con) throws CategoriaException;
	public int delete(CategoriaPKDB categoriapk, Connection con) throws CategoriaException;
	public CategoriaDB findByPrimaryKey(CategoriaPKDB categoriapk , Connection con) throws CategoriaException;
	public CategoriaDB[] findAll(Connection con) throws CategoriaException;
	public CategoriaDB[] findByIdcategoria(long idcategoria,Connection con) throws CategoriaException;
	public CategoriaDB[] findByNombre(String nombre,Connection con) throws CategoriaException;
	public CategoriaDB[] findByDescripcion(String descripcion,Connection con) throws CategoriaException;
	public CategoriaDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws CategoriaException;
	public CategoriaDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws CategoriaException;
}