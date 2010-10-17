/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Oct 17 02:56:00 ART 2010
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
* Implementation of EstadoplanificacionproduccionDAO interface 
* 
*/


public class EstadoplanificacionproduccionDAOImpl implements EstadoplanificacionproduccionDAO
{


/**
* Method deletes a record from table ESTADOPLANIFICACIONPRODUCCION
* @param EstadoplanificacionproduccionPK estadoplanificacionproduccionpk
* @param  Connection  con
* @return  int
*
*/


	public int delete(EstadoplanificacionproduccionPK estadoplanificacionproduccionpk , Connection con)throws EstadoplanificacionproduccionException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  ESTADOPLANIFICACIONPRODUCCION where id = ?");
			ps.setLong(1, estadoplanificacionproduccionpk.getId());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new EstadoplanificacionproduccionException(sqle);}
		catch(Exception e) {throw new EstadoplanificacionproduccionException(e);}
	}



/**
* This method updates a record in table ESTADOPLANIFICACIONPRODUCCION
* @param EstadoplanificacionproduccionPK
* @param Estadoplanificacionproduccion
* @param  Connection con
* @return   int
*/

	public int update(EstadoplanificacionproduccionPK estadoplanificacionproduccionpk, Estadoplanificacionproduccion estadoplanificacionproduccion, Connection con)throws EstadoplanificacionproduccionException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update ESTADOPLANIFICACIONPRODUCCION set NOMBRE = ? , DESCRIPCION = ?  where id = ?");
				ps.setString(1,estadoplanificacionproduccion.getNombre());
				ps.setString(2,estadoplanificacionproduccion.getDescripcion());
				ps.setLong(3,estadoplanificacionproduccionpk.getId());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new EstadoplanificacionproduccionException(sqle);}
		catch(Exception e){throw new EstadoplanificacionproduccionException(e);}
	}

/**
* This method inserts data in table ESTADOPLANIFICACIONPRODUCCION
*
* @param Estadoplanificacionproduccion estadoplanificacionproduccion
* @param   Connection con
* @return  EstadoplanificacionproduccionPK
*/

	public int insert(Estadoplanificacionproduccion estadoplanificacionproduccion ,Connection con)throws EstadoplanificacionproduccionException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into ESTADOPLANIFICACIONPRODUCCION( NOMBRE, DESCRIPCION) values (?, ?)");
				ps.setString(1,estadoplanificacionproduccion.getNombre());
				ps.setString(2,estadoplanificacionproduccion.getDescripcion());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new EstadoplanificacionproduccionException(sqle);}
		catch(Exception e){throw new EstadoplanificacionproduccionException(e);}
	}

/**
* 
* Returns a row from the estadoplanificacionproduccion table for the primary key passed as parameter.
* 
*/

	public Estadoplanificacionproduccion findByPrimaryKey(long id, Connection con) throws EstadoplanificacionproduccionException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select id, nombre, descripcion from estadoplanificacionproduccion where id = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, id);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new EstadoplanificacionproduccionException(sqle);
	  	}
	    catch(Exception e){throw new EstadoplanificacionproduccionException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the estadoplanificacionproduccion table for the primary key object passed as parameter.
* 
* @param  EstadoplanificacionproduccionPK estadoplanificacionproduccionpk
* @param Connection con
* @return  Estadoplanificacionproduccion
*/

	public Estadoplanificacionproduccion findByPrimaryKey(EstadoplanificacionproduccionPK estadoplanificacionproduccionpk, Connection con) throws EstadoplanificacionproduccionException{
		return findByPrimaryKey(estadoplanificacionproduccionpk.getId(), con);
	}

/**
*
* Returns all rows from estadoplanificacionproduccion table where ID= id
*
* @param   long  id
* @param   Connection con
* @return  Estadoplanificacionproduccion[]
*/

	public Estadoplanificacionproduccion[] findById(long id, Connection con) throws EstadoplanificacionproduccionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select id, nombre, descripcion from estadoplanificacionproduccion where id = ? order by id";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, id );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadoplanificacionproduccionException(sqle);
			}
			catch(Exception e){
					throw new EstadoplanificacionproduccionException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from estadoplanificacionproduccion table where NOMBRE= nombre
*
* @param   String  nombre
* @param   Connection con
* @return  Estadoplanificacionproduccion[]
*/

	public Estadoplanificacionproduccion[] findByNombre(String nombre, Connection con) throws EstadoplanificacionproduccionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select id, nombre, descripcion from estadoplanificacionproduccion where nombre = ? order by nombre";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, nombre );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadoplanificacionproduccionException(sqle);
			}
			catch(Exception e){
					throw new EstadoplanificacionproduccionException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from estadoplanificacionproduccion table where DESCRIPCION= descripcion
*
* @param   String  descripcion
* @param   Connection con
* @return  Estadoplanificacionproduccion[]
*/

	public Estadoplanificacionproduccion[] findByDescripcion(String descripcion, Connection con) throws EstadoplanificacionproduccionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select id, nombre, descripcion from estadoplanificacionproduccion where descripcion = ? order by descripcion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, descripcion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadoplanificacionproduccionException(sqle);
			}
			catch(Exception e){
					throw new EstadoplanificacionproduccionException(e);
			}
			finally{}
	}

/**
* Returns all rows from estadoplanificacionproduccion table 
*
* @param Connection con
* @return  Estadoplanificacionproduccion[]
*
*/

	public Estadoplanificacionproduccion[] findAll( Connection con) throws EstadoplanificacionproduccionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select id, nombre, descripcion from estadoplanificacionproduccion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadoplanificacionproduccionException(sqle);
			}
			catch(Exception e){
					throw new EstadoplanificacionproduccionException(e);
			}
			finally{}
	}

/**
* Returns rows from estadoplanificacionproduccion table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Estadoplanificacionproduccion[]
*
*/

	public Estadoplanificacionproduccion[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws EstadoplanificacionproduccionException{
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
					throw new EstadoplanificacionproduccionException(sqle);
			}
			catch(Exception e){
					throw new EstadoplanificacionproduccionException(e);
			}
			finally{}
	}

/**
* Returns rows from estadoplanificacionproduccion table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Estadoplanificacionproduccion[]
*
*/

	public Estadoplanificacionproduccion[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws EstadoplanificacionproduccionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select id, nombre, descripcion from estadoplanificacionproduccion";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadoplanificacionproduccionException(sqle);
			}
			catch(Exception e){
					throw new EstadoplanificacionproduccionException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Estadoplanificacionproduccion
*
*/

	protected Estadoplanificacionproduccion fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Estadoplanificacionproduccion dto = new Estadoplanificacionproduccion();
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
* @param Estadoplanificacionproduccion dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Estadoplanificacionproduccion dto, ResultSet rs) throws SQLException
	{
		 dto.setId(rs.getLong("id"));
		 dto.setNombre(rs.getString("nombre"));
		 dto.setDescripcion(rs.getString("descripcion"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Estadoplanificacionproduccion[]
*/

	protected Estadoplanificacionproduccion[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Estadoplanificacionproduccion dto = new Estadoplanificacionproduccion();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Estadoplanificacionproduccion ret[] = new Estadoplanificacionproduccion[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
