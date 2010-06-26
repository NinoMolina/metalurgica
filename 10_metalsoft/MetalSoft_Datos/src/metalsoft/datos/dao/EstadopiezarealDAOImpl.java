/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:39:08 GYT 2010
*
* DAO-Generator Version 1.2
*
*/

package metalsoft.datos.dao;
import java.math.*;
import java.sql.*;
import java.net.URL;
import java.util.*;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
import metalsoft.datos.idao.*;


/**
* 
* Implementation of EstadopiezarealDAO interface 
* 
*/


public class EstadopiezarealDAOImpl implements EstadopiezarealDAO
{


/**
* Method deletes a record from table ESTADOPIEZAREAL
* @param EstadopiezarealPK estadopiezarealpk
* @param  Connection  con
* @return  int
*
*/


	public int delete(EstadopiezarealPK estadopiezarealpk , Connection con)throws EstadopiezarealException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  ESTADOPIEZAREAL where idestado = ?");
			ps.setLong(1, estadopiezarealpk.getIdestado());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new EstadopiezarealException(sqle);}
		catch(Exception e) {throw new EstadopiezarealException(e);}
	}



/**
* This method updates a record in table ESTADOPIEZAREAL
* @param EstadopiezarealPK
* @param Estadopiezareal
* @param  Connection con
* @return   int
*/

	public int update(EstadopiezarealPK estadopiezarealpk, Estadopiezareal estadopiezareal, Connection con)throws EstadopiezarealException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update ESTADOPIEZAREAL set NOMBRE = ? , DESCRIPCION = ?  where idestado = ?");
				ps.setString(1,estadopiezareal.getNombre());
				ps.setString(2,estadopiezareal.getDescripcion());
				ps.setLong(3,estadopiezarealpk.getIdestado());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new EstadopiezarealException(sqle);}
		catch(Exception e){throw new EstadopiezarealException(e);}
	}

/**
* This method inserts data in table ESTADOPIEZAREAL
*
* @param Estadopiezareal estadopiezareal
* @param   Connection con
* @return  EstadopiezarealPK
*/

	public int insert(Estadopiezareal estadopiezareal ,Connection con)throws EstadopiezarealException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into ESTADOPIEZAREAL( IDESTADO, NOMBRE, DESCRIPCION) values (?, ?, ?)");
				ps.setLong(1,estadopiezareal.getIdestado());
				ps.setString(2,estadopiezareal.getNombre());
				ps.setString(3,estadopiezareal.getDescripcion());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new EstadopiezarealException(sqle);}
		catch(Exception e){throw new EstadopiezarealException(e);}
	}

/**
* 
* Returns a row from the estadopiezareal table for the primary key passed as parameter.
* 
*/

	public Estadopiezareal findByPrimaryKey(long idestado, Connection con) throws EstadopiezarealException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idestado, nombre, descripcion from estadopiezareal where idestado = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idestado);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new EstadopiezarealException(sqle);
	  	}
	    catch(Exception e){throw new EstadopiezarealException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the estadopiezareal table for the primary key object passed as parameter.
* 
* @param  EstadopiezarealPK estadopiezarealpk
* @param Connection con
* @return  Estadopiezareal
*/

	public Estadopiezareal findByPrimaryKey(EstadopiezarealPK estadopiezarealpk, Connection con) throws EstadopiezarealException{
		return findByPrimaryKey(estadopiezarealpk.getIdestado(), con);
	}

/**
*
* Returns all rows from estadopiezareal table where IDESTADO= idestado
*
* @param   long  idestado
* @param   Connection con
* @return  Estadopiezareal[]
*/

	public Estadopiezareal[] findByIdestado(long idestado, Connection con) throws EstadopiezarealException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idestado, nombre, descripcion from estadopiezareal where idestado = ? order by idestado";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idestado );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadopiezarealException(sqle);
			}
			catch(Exception e){
					throw new EstadopiezarealException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from estadopiezareal table where NOMBRE= nombre
*
* @param   String  nombre
* @param   Connection con
* @return  Estadopiezareal[]
*/

	public Estadopiezareal[] findByNombre(String nombre, Connection con) throws EstadopiezarealException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idestado, nombre, descripcion from estadopiezareal where nombre = ? order by nombre";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, nombre );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadopiezarealException(sqle);
			}
			catch(Exception e){
					throw new EstadopiezarealException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from estadopiezareal table where DESCRIPCION= descripcion
*
* @param   String  descripcion
* @param   Connection con
* @return  Estadopiezareal[]
*/

	public Estadopiezareal[] findByDescripcion(String descripcion, Connection con) throws EstadopiezarealException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idestado, nombre, descripcion from estadopiezareal where descripcion = ? order by descripcion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, descripcion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadopiezarealException(sqle);
			}
			catch(Exception e){
					throw new EstadopiezarealException(e);
			}
			finally{}
	}

/**
* Returns all rows from estadopiezareal table 
*
* @param Connection con
* @return  Estadopiezareal[]
*
*/

	public Estadopiezareal[] findAll( Connection con) throws EstadopiezarealException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idestado, nombre, descripcion from estadopiezareal";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadopiezarealException(sqle);
			}
			catch(Exception e){
					throw new EstadopiezarealException(e);
			}
			finally{}
	}

/**
* Returns rows from estadopiezareal table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Estadopiezareal[]
*
*/

	public Estadopiezareal[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws EstadopiezarealException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			final String SQL_STATEMENT = selectStatement;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadopiezarealException(sqle);
			}
			catch(Exception e){
					throw new EstadopiezarealException(e);
			}
			finally{}
	}

/**
* Returns rows from estadopiezareal table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Estadopiezareal[]
*
*/

	public Estadopiezareal[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws EstadopiezarealException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idestado, nombre, descripcion from estadopiezareal";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadopiezarealException(sqle);
			}
			catch(Exception e){
					throw new EstadopiezarealException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Estadopiezareal
*
*/

	protected Estadopiezareal fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Estadopiezareal dto = new Estadopiezareal();
					populateVO( dto, rs);
				return dto;
			} else {
				return null;
			}
	}

/**
* 
* Populates a Data Transfer Object by fetching data from  ResultSet
* 
* @param Estadopiezareal dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Estadopiezareal dto, ResultSet rs) throws SQLException
	{
		 dto.setIdestado(rs.getLong("idestado"));
		 dto.setNombre(rs.getString("nombre"));
		 dto.setDescripcion(rs.getString("descripcion"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Estadopiezareal[]
*/

	protected Estadopiezareal[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Estadopiezareal dto = new Estadopiezareal();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Estadopiezareal ret[] = new Estadopiezareal[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
