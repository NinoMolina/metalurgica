/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Oct 17 21:47:14 ART 2010
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
public interface MpasignadaxpiezarealDAO
{
	public int insert(Mpasignadaxpiezareal mpasignadaxpiezareal, Connection con ) throws MpasignadaxpiezarealException;
	public int update(MpasignadaxpiezarealPK mpasignadaxpiezarealpk, Mpasignadaxpiezareal mpasignadaxpiezareal, Connection con) throws MpasignadaxpiezarealException;
	public int delete(MpasignadaxpiezarealPK mpasignadaxpiezarealpk, Connection con) throws MpasignadaxpiezarealException;
	public Mpasignadaxpiezareal findByPrimaryKey(MpasignadaxpiezarealPK mpasignadaxpiezarealpk , Connection con) throws MpasignadaxpiezarealException;
	public Mpasignadaxpiezareal[] findAll(Connection con) throws MpasignadaxpiezarealException;
	public Mpasignadaxpiezareal[] findByIdpiezareal(long idpiezareal,Connection con) throws MpasignadaxpiezarealException;
	public Mpasignadaxpiezareal[] findByIddetallempasignada(long iddetallempasignada,Connection con) throws MpasignadaxpiezarealException;
	public Mpasignadaxpiezareal[] findById(long id,Connection con) throws MpasignadaxpiezarealException;
	public Mpasignadaxpiezareal[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws MpasignadaxpiezarealException;
	public Mpasignadaxpiezareal[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws MpasignadaxpiezarealException;
}
