/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:39:11 GYT 2010
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
public interface FormadepagoDAO
{
	public int insert(Formadepago formadepago, Connection con ) throws FormadepagoException;
	public int update(FormadepagoPK formadepagopk, Formadepago formadepago, Connection con) throws FormadepagoException;
	public int delete(FormadepagoPK formadepagopk, Connection con) throws FormadepagoException;
	public Formadepago findByPrimaryKey(FormadepagoPK formadepagopk , Connection con) throws FormadepagoException;
	public Formadepago[] findAll(Connection con) throws FormadepagoException;
	public Formadepago[] findByIdformapago(int idformapago,Connection con) throws FormadepagoException;
	public Formadepago[] findByNombre(String nombre,Connection con) throws FormadepagoException;
	public Formadepago[] findByDescripcion(String descripcion,Connection con) throws FormadepagoException;
	public Formadepago[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws FormadepagoException;
	public Formadepago[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws FormadepagoException;
}
