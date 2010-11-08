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
public interface PiezarealDAO
{
	public int insert(PiezarealDB piezareal, Connection con ) throws PiezarealException;
	public int update(PiezarealPK piezarealpk, PiezarealDB piezareal, Connection con) throws PiezarealException;
	public int delete(PiezarealPK piezarealpk, Connection con) throws PiezarealException;
	public PiezarealDB findByPrimaryKey(PiezarealPK piezarealpk , Connection con) throws PiezarealException;
	public PiezarealDB[] findAll(Connection con) throws PiezarealException;
	public PiezarealDB[] findByIdpiezareal(long idpiezareal,Connection con) throws PiezarealException;
	public PiezarealDB[] findByIdpieza(long idpieza,Connection con) throws PiezarealException;
	public PiezarealDB[] findByEstado(long estado,Connection con) throws PiezarealException;
	public PiezarealDB[] findByNropieza(int nropieza,Connection con) throws PiezarealException;
	public PiezarealDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws PiezarealException;
	public PiezarealDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws PiezarealException;
}