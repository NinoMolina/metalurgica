/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:02 ART 2010
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
public interface DetallerequerimientosmateriaprimaDAO
{
	public int insert(Detallerequerimientosmateriaprima detallerequerimientosmateriaprima, Connection con ) throws DetallerequerimientosmateriaprimaException;
	public int update(DetallerequerimientosmateriaprimaPK detallerequerimientosmateriaprimapk, Detallerequerimientosmateriaprima detallerequerimientosmateriaprima, Connection con) throws DetallerequerimientosmateriaprimaException;
	public int delete(DetallerequerimientosmateriaprimaPK detallerequerimientosmateriaprimapk, Connection con) throws DetallerequerimientosmateriaprimaException;
	public Detallerequerimientosmateriaprima findByPrimaryKey(DetallerequerimientosmateriaprimaPK detallerequerimientosmateriaprimapk , Connection con) throws DetallerequerimientosmateriaprimaException;
	public Detallerequerimientosmateriaprima[] findAll(Connection con) throws DetallerequerimientosmateriaprimaException;
	public Detallerequerimientosmateriaprima[] findByIddetalle(long iddetalle,Connection con) throws DetallerequerimientosmateriaprimaException;
	public Detallerequerimientosmateriaprima[] findByIdplanrequerimientosmateriaprima(long idplanrequerimientosmateriaprima,Connection con) throws DetallerequerimientosmateriaprimaException;
	public Detallerequerimientosmateriaprima[] findByCantidadmateriaprima(int cantidadmateriaprima,Connection con) throws DetallerequerimientosmateriaprimaException;
	public Detallerequerimientosmateriaprima[] findByIdpieza(long idpieza,Connection con) throws DetallerequerimientosmateriaprimaException;
	public Detallerequerimientosmateriaprima[] findByIdmateriaprima(long idmateriaprima,Connection con) throws DetallerequerimientosmateriaprimaException;
	public Detallerequerimientosmateriaprima[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws DetallerequerimientosmateriaprimaException;
	public Detallerequerimientosmateriaprima[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws DetallerequerimientosmateriaprimaException;
}