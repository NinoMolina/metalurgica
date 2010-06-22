/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:09 ART 2010
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
public interface EstadopiezarealDAO
{
	public int insert(Estadopiezareal estadopiezareal, Connection con ) throws EstadopiezarealException;
	public int update(EstadopiezarealPK estadopiezarealpk, Estadopiezareal estadopiezareal, Connection con) throws EstadopiezarealException;
	public int delete(EstadopiezarealPK estadopiezarealpk, Connection con) throws EstadopiezarealException;
	public Estadopiezareal findByPrimaryKey(EstadopiezarealPK estadopiezarealpk , Connection con) throws EstadopiezarealException;
	public Estadopiezareal[] findAll(Connection con) throws EstadopiezarealException;
	public Estadopiezareal[] findByIdestado(long idestado,Connection con) throws EstadopiezarealException;
	public Estadopiezareal[] findByNombre(String nombre,Connection con) throws EstadopiezarealException;
	public Estadopiezareal[] findByDescripcion(String descripcion,Connection con) throws EstadopiezarealException;
	public Estadopiezareal[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws EstadopiezarealException;
	public Estadopiezareal[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws EstadopiezarealException;
}
