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
	public int insert(Categoria categoria, Connection con ) throws CategoriaException;
	public int update(CategoriaPK categoriapk, Categoria categoria, Connection con) throws CategoriaException;
	public int delete(CategoriaPK categoriapk, Connection con) throws CategoriaException;
	public Categoria findByPrimaryKey(CategoriaPK categoriapk , Connection con) throws CategoriaException;
	public Categoria[] findAll(Connection con) throws CategoriaException;
	public Categoria[] findByIdcategoria(long idcategoria,Connection con) throws CategoriaException;
	public Categoria[] findByNombre(String nombre,Connection con) throws CategoriaException;
	public Categoria[] findByDescripcion(String descripcion,Connection con) throws CategoriaException;
	public Categoria[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws CategoriaException;
	public Categoria[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws CategoriaException;
}
