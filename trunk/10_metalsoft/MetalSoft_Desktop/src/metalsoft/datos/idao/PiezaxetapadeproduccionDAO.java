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
import java.util.*;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
public interface PiezaxetapadeproduccionDAO
{
	public int insert(PiezaxetapadeproduccionDB piezaxetapadeproduccion, Connection con ) throws PiezaxetapadeproduccionException;
	public int update(PiezaxetapadeproduccionPKDB piezaxetapadeproduccionpk, PiezaxetapadeproduccionDB piezaxetapadeproduccion, Connection con) throws PiezaxetapadeproduccionException;
	public int delete(PiezaxetapadeproduccionPKDB piezaxetapadeproduccionpk, Connection con) throws PiezaxetapadeproduccionException;
	public PiezaxetapadeproduccionDB findByPrimaryKey(PiezaxetapadeproduccionPKDB piezaxetapadeproduccionpk , Connection con) throws PiezaxetapadeproduccionException;
	public PiezaxetapadeproduccionDB[] findAll(Connection con) throws PiezaxetapadeproduccionException;
	public PiezaxetapadeproduccionDB[] findByIdpieza(long idpieza,Connection con) throws PiezaxetapadeproduccionException;
	public PiezaxetapadeproduccionDB[] findByIdetapaproduccion(long idetapaproduccion,Connection con) throws PiezaxetapadeproduccionException;
	public PiezaxetapadeproduccionDB[] findByDuracion(Time duracion,Connection con) throws PiezaxetapadeproduccionException;
	public PiezaxetapadeproduccionDB[] findByDescripcion(String descripcion,Connection con) throws PiezaxetapadeproduccionException;
	public PiezaxetapadeproduccionDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws PiezaxetapadeproduccionException;
	public PiezaxetapadeproduccionDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws PiezaxetapadeproduccionException;
}
