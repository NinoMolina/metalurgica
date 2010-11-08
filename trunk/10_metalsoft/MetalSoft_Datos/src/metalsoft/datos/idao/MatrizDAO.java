/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:03 ART 2010
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
public interface MatrizDAO
{
	public int insert(Matriz matriz, Connection con ) throws MatrizException;
	public int update(MatrizPK matrizpk, Matriz matriz, Connection con) throws MatrizException;
	public int delete(MatrizPK matrizpk, Connection con) throws MatrizException;
	public Matriz findByPrimaryKey(MatrizPK matrizpk , Connection con) throws MatrizException;
	public Matriz[] findAll(Connection con) throws MatrizException;
	public Matriz[] findByIdmatriz(long idmatriz,Connection con) throws MatrizException;
	public Matriz[] findByCodmatriz(long codmatriz,Connection con) throws MatrizException;
	public Matriz[] findByNombre(String nombre,Connection con) throws MatrizException;
	public Matriz[] findByDescripcion(String descripcion,Connection con) throws MatrizException;
	public Matriz[] findByObservaciones(String observaciones,Connection con) throws MatrizException;
	public Matriz[] findByFechacreacion(Date fechacreacion,Connection con) throws MatrizException;
	public Matriz[] findByMateriaprima(long materiaprima,Connection con) throws MatrizException;
	public Matriz[] findByTipomaterial(long tipomaterial,Connection con) throws MatrizException;
	public Matriz[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws MatrizException;
	public Matriz[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws MatrizException;
}