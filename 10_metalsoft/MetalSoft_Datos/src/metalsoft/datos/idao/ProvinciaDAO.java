/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:04 ART 2010
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
public interface ProvinciaDAO
{
	public int insert(Provincia provincia, Connection con ) throws ProvinciaException;
	public int update(ProvinciaPK provinciapk, Provincia provincia, Connection con) throws ProvinciaException;
	public int delete(ProvinciaPK provinciapk, Connection con) throws ProvinciaException;
	public Provincia findByPrimaryKey(ProvinciaPK provinciapk , Connection con) throws ProvinciaException;
	public Provincia[] findAll(Connection con) throws ProvinciaException;
	public Provincia[] findByIdprovincia(long idprovincia,Connection con) throws ProvinciaException;
	public Provincia[] findByNombre(String nombre,Connection con) throws ProvinciaException;
	public Provincia[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws ProvinciaException;
	public Provincia[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws ProvinciaException;
}
