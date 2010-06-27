/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:05 ART 2010
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
* Implementation of ServicioDAO interface 
* 
*/


public class ServicioDAOImpl implements ServicioDAO
{


/**
* Method deletes a record from table SERVICIO
* @param ServicioPK serviciopk
* @param  Connection  con
* @return  int
*
*/


	public int delete(ServicioPK serviciopk , Connection con)throws ServicioException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  SERVICIO where idservicio = ?");
			ps.setLong(1, serviciopk.getIdservicio());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new ServicioException(sqle);}
		catch(Exception e) {throw new ServicioException(e);}
	}



/**
* This method updates a record in table SERVICIO
* @param ServicioPK
* @param Servicio
* @param  Connection con
* @return   int
*/

	public int update(ServicioPK serviciopk, Servicio servicio, Connection con)throws ServicioException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update SERVICIO set NOMBRE = ? , DESCRIPCION = ?  where idservicio = ?");
				ps.setString(1,servicio.getNombre());
				ps.setString(2,servicio.getDescripcion());
				ps.setLong(3,serviciopk.getIdservicio());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new ServicioException(sqle);}
		catch(Exception e){throw new ServicioException(e);}
	}

/**
* This method inserts data in table SERVICIO
*
* @param Servicio servicio
* @param   Connection con
* @return  ServicioPK
*/

	public int insert(Servicio servicio ,Connection con)throws ServicioException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into SERVICIO( NOMBRE, DESCRIPCION) values (?, ?)");
				ps.setString(1,servicio.getNombre());
				ps.setString(2,servicio.getDescripcion());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new ServicioException(sqle);}
		catch(Exception e){throw new ServicioException(e);}
	}

/**
* 
* Returns a row from the servicio table for the primary key passed as parameter.
* 
*/

	public Servicio findByPrimaryKey(long idservicio, Connection con) throws ServicioException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idservicio, nombre, descripcion from servicio where idservicio = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idservicio);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new ServicioException(sqle);
	  	}
	    catch(Exception e){throw new ServicioException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the servicio table for the primary key object passed as parameter.
* 
* @param  ServicioPK serviciopk
* @param Connection con
* @return  Servicio
*/

	public Servicio findByPrimaryKey(ServicioPK serviciopk, Connection con) throws ServicioException{
		return findByPrimaryKey(serviciopk.getIdservicio(), con);
	}

/**
*
* Returns all rows from servicio table where IDSERVICIO= idservicio
*
* @param   long  idservicio
* @param   Connection con
* @return  Servicio[]
*/

	public Servicio[] findByIdservicio(long idservicio, Connection con) throws ServicioException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idservicio, nombre, descripcion from servicio where idservicio = ? order by idservicio";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idservicio );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ServicioException(sqle);
			}
			catch(Exception e){
					throw new ServicioException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from servicio table where NOMBRE= nombre
*
* @param   String  nombre
* @param   Connection con
* @return  Servicio[]
*/

	public Servicio[] findByNombre(String nombre, Connection con) throws ServicioException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idservicio, nombre, descripcion from servicio where nombre = ? order by nombre";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, nombre );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ServicioException(sqle);
			}
			catch(Exception e){
					throw new ServicioException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from servicio table where DESCRIPCION= descripcion
*
* @param   String  descripcion
* @param   Connection con
* @return  Servicio[]
*/

	public Servicio[] findByDescripcion(String descripcion, Connection con) throws ServicioException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idservicio, nombre, descripcion from servicio where descripcion = ? order by descripcion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, descripcion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ServicioException(sqle);
			}
			catch(Exception e){
					throw new ServicioException(e);
			}
			finally{}
	}

/**
* Returns all rows from servicio table 
*
* @param Connection con
* @return  Servicio[]
*
*/

	public Servicio[] findAll( Connection con) throws ServicioException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idservicio, nombre, descripcion from servicio";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ServicioException(sqle);
			}
			catch(Exception e){
					throw new ServicioException(e);
			}
			finally{}
	}

/**
* Returns rows from servicio table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Servicio[]
*
*/

	public Servicio[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws ServicioException{
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
					throw new ServicioException(sqle);
			}
			catch(Exception e){
					throw new ServicioException(e);
			}
			finally{}
	}

/**
* Returns rows from servicio table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Servicio[]
*
*/

	public Servicio[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws ServicioException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idservicio, nombre, descripcion from servicio";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ServicioException(sqle);
			}
			catch(Exception e){
					throw new ServicioException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Servicio
*
*/

	protected Servicio fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Servicio dto = new Servicio();
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
* @param Servicio dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Servicio dto, ResultSet rs) throws SQLException
	{
		 dto.setIdservicio(rs.getLong("idservicio"));
		 dto.setNombre(rs.getString("nombre"));
		 dto.setDescripcion(rs.getString("descripcion"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Servicio[]
*/

	protected Servicio[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Servicio dto = new Servicio();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Servicio ret[] = new Servicio[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
