/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:07 ART 2010
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
public interface CondicionivaDAO
{
	public int insert(Condicioniva condicioniva, Connection con ) throws CondicionivaException;
	public int update(CondicionivaPK condicionivapk, Condicioniva condicioniva, Connection con) throws CondicionivaException;
	public int delete(CondicionivaPK condicionivapk, Connection con) throws CondicionivaException;
	public Condicioniva findByPrimaryKey(CondicionivaPK condicionivapk , Connection con) throws CondicionivaException;
	public Condicioniva[] findAll(Connection con) throws CondicionivaException;
	public Condicioniva[] findByIdcondicioniva(long idcondicioniva,Connection con) throws CondicionivaException;
	public Condicioniva[] findByNombre(String nombre,Connection con) throws CondicionivaException;
	public Condicioniva[] findByDescripcion(String descripcion,Connection con) throws CondicionivaException;
	public Condicioniva[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws CondicionivaException;
	public Condicioniva[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws CondicionivaException;
}
