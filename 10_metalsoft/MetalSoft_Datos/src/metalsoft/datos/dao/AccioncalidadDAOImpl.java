/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:00 ART 2010
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
* Implementation of AccioncalidadDAO interface 
* 
*/


public class AccioncalidadDAOImpl implements AccioncalidadDAO
{


/**
* Method deletes a record from table ACCIONCALIDAD
* @param AccioncalidadPK accioncalidadpk
* @param  Connection  con
* @return  int
*
*/


	public int delete(AccioncalidadPK accioncalidadpk , Connection con)throws AccioncalidadException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  ACCIONCALIDAD where idaccioncalidad = ?");
			ps.setLong(1, accioncalidadpk.getIdaccioncalidad());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new AccioncalidadException(sqle);}
		catch(Exception e) {throw new AccioncalidadException(e);}
	}



/**
* This method updates a record in table ACCIONCALIDAD
* @param AccioncalidadPK
* @param Accioncalidad
* @param  Connection con
* @return   int
*/

	public int update(AccioncalidadPK accioncalidadpk, Accioncalidad accioncalidad, Connection con)throws AccioncalidadException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update ACCIONCALIDAD set NOMBRE = ? , DESCRIPCION = ?  where idaccioncalidad = ?");
				ps.setString(1,accioncalidad.getNombre());
				ps.setString(2,accioncalidad.getDescripcion());
				ps.setLong(3,accioncalidadpk.getIdaccioncalidad());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new AccioncalidadException(sqle);}
		catch(Exception e){throw new AccioncalidadException(e);}
	}

/**
* This method inserts data in table ACCIONCALIDAD
*
* @param Accioncalidad accioncalidad
* @param   Connection con
* @return  AccioncalidadPK
*/

	public int insert(Accioncalidad accioncalidad ,Connection con)throws AccioncalidadException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into ACCIONCALIDAD( NOMBRE, DESCRIPCION) values (?, ?)");
				ps.setString(1,accioncalidad.getNombre());
				ps.setString(2,accioncalidad.getDescripcion());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new AccioncalidadException(sqle);}
		catch(Exception e){throw new AccioncalidadException(e);}
	}

/**
* 
* Returns a row from the accioncalidad table for the primary key passed as parameter.
* 
*/

	public Accioncalidad findByPrimaryKey(long idaccioncalidad, Connection con) throws AccioncalidadException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idaccioncalidad, nombre, descripcion from accioncalidad where idaccioncalidad = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idaccioncalidad);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new AccioncalidadException(sqle);
	  	}
	    catch(Exception e){throw new AccioncalidadException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the accioncalidad table for the primary key object passed as parameter.
* 
* @param  AccioncalidadPK accioncalidadpk
* @param Connection con
* @return  Accioncalidad
*/

	public Accioncalidad findByPrimaryKey(AccioncalidadPK accioncalidadpk, Connection con) throws AccioncalidadException{
		return findByPrimaryKey(accioncalidadpk.getIdaccioncalidad(), con);
	}

/**
*
* Returns all rows from accioncalidad table where IDACCIONCALIDAD= idaccioncalidad
*
* @param   long  idaccioncalidad
* @param   Connection con
* @return  Accioncalidad[]
*/

	public Accioncalidad[] findByIdaccioncalidad(long idaccioncalidad, Connection con) throws AccioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idaccioncalidad, nombre, descripcion from accioncalidad where idaccioncalidad = ? order by idaccioncalidad";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idaccioncalidad );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new AccioncalidadException(sqle);
			}
			catch(Exception e){
					throw new AccioncalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from accioncalidad table where NOMBRE= nombre
*
* @param   String  nombre
* @param   Connection con
* @return  Accioncalidad[]
*/

	public Accioncalidad[] findByNombre(String nombre, Connection con) throws AccioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idaccioncalidad, nombre, descripcion from accioncalidad where nombre = ? order by nombre";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, nombre );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new AccioncalidadException(sqle);
			}
			catch(Exception e){
					throw new AccioncalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from accioncalidad table where DESCRIPCION= descripcion
*
* @param   String  descripcion
* @param   Connection con
* @return  Accioncalidad[]
*/

	public Accioncalidad[] findByDescripcion(String descripcion, Connection con) throws AccioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idaccioncalidad, nombre, descripcion from accioncalidad where descripcion = ? order by descripcion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, descripcion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new AccioncalidadException(sqle);
			}
			catch(Exception e){
					throw new AccioncalidadException(e);
			}
			finally{}
	}

/**
* Returns all rows from accioncalidad table 
*
* @param Connection con
* @return  Accioncalidad[]
*
*/

	public Accioncalidad[] findAll( Connection con) throws AccioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idaccioncalidad, nombre, descripcion from accioncalidad";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new AccioncalidadException(sqle);
			}
			catch(Exception e){
					throw new AccioncalidadException(e);
			}
			finally{}
	}

/**
* Returns rows from accioncalidad table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Accioncalidad[]
*
*/

	public Accioncalidad[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws AccioncalidadException{
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
					throw new AccioncalidadException(sqle);
			}
			catch(Exception e){
					throw new AccioncalidadException(e);
			}
			finally{}
	}

/**
* Returns rows from accioncalidad table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Accioncalidad[]
*
*/

	public Accioncalidad[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws AccioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idaccioncalidad, nombre, descripcion from accioncalidad";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new AccioncalidadException(sqle);
			}
			catch(Exception e){
					throw new AccioncalidadException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Accioncalidad
*
*/

	protected Accioncalidad fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Accioncalidad dto = new Accioncalidad();
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
* @param Accioncalidad dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Accioncalidad dto, ResultSet rs) throws SQLException
	{
		 dto.setIdaccioncalidad(rs.getLong("idaccioncalidad"));
		 dto.setNombre(rs.getString("nombre"));
		 dto.setDescripcion(rs.getString("descripcion"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Accioncalidad[]
*/

	protected Accioncalidad[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Accioncalidad dto = new Accioncalidad();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Accioncalidad ret[] = new Accioncalidad[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}