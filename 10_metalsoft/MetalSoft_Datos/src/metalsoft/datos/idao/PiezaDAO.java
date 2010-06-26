/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:39:28 GYT 2010
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
public interface PiezaDAO
{
	public int insert(Pieza pieza, Connection con ) throws PiezaException;
	public int update(PiezaPK piezapk, Pieza pieza, Connection con) throws PiezaException;
	public int delete(PiezaPK piezapk, Connection con) throws PiezaException;
	public Pieza findByPrimaryKey(PiezaPK piezapk , Connection con) throws PiezaException;
	public Pieza[] findAll(Connection con) throws PiezaException;
	public Pieza[] findByIdpieza(long idpieza,Connection con) throws PiezaException;
	public Pieza[] findByNombre(String nombre,Connection con) throws PiezaException;
	public Pieza[] findByTipomaterial(long tipomaterial,Connection con) throws PiezaException;
	public Pieza[] findByDimensiones(String dimensiones,Connection con) throws PiezaException;
	public Pieza[] findByMateriaprima(long materiaprima,Connection con) throws PiezaException;
	public Pieza[] findByMatriz(long matriz,Connection con) throws PiezaException;
	public Pieza[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws PiezaException;
	public Pieza[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws PiezaException;
}
