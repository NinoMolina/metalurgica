/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:07 ART 2010
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
* Implementation of AsistenciaDAO interface 
* 
*/


public class AsistenciaDAOImpl implements AsistenciaDAO
{


/**
* Method deletes a record from table ASISTENCIA
* @param AsistenciaPK asistenciapk
* @param  Connection  con
* @return  int
*
*/


	public int delete(AsistenciaPK asistenciapk , Connection con)throws AsistenciaException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  ASISTENCIA where empleado = ? AND fechaingreso = ? AND horaingreso = ?");
			ps.setLong(1, asistenciapk.getEmpleado());
			ps.setLong(2, asistenciapk.getFechaingreso());
			ps.setTime(3, asistenciapk.getHoraingreso());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new AsistenciaException(sqle);}
		catch(Exception e) {throw new AsistenciaException(e);}
	}



/**
* This method updates a record in table ASISTENCIA
* @param AsistenciaPK
* @param Asistencia
* @param  Connection con
* @return   int
*/

	public int update(AsistenciaPK asistenciapk, Asistencia asistencia, Connection con)throws AsistenciaException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update ASISTENCIA set HORAEGRESO = ? , OBSERVACIONES = ?  where empleado = ? AND fechaingreso = ? AND horaingreso = ?");
				ps.setTime(1,asistencia.getHoraegreso());
				ps.setString(2,asistencia.getObservaciones());
				ps.setLong(3,asistenciapk.getEmpleado());
				ps.setLong(4,asistenciapk.getFechaingreso());
				ps.setTime(5,asistenciapk.getHoraingreso());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new AsistenciaException(sqle);}
		catch(Exception e){throw new AsistenciaException(e);}
	}

/**
* This method inserts data in table ASISTENCIA
*
* @param Asistencia asistencia
* @param   Connection con
* @return  AsistenciaPK
*/

	public int insert(Asistencia asistencia ,Connection con)throws AsistenciaException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into ASISTENCIA( EMPLEADO, FECHAINGRESO, HORAINGRESO, HORAEGRESO, OBSERVACIONES) values (?, ?, ?, ?, ?)");
				ps.setLong(1,asistencia.getEmpleado());
				ps.setLong(2,asistencia.getFechaingreso());
				ps.setTime(3,asistencia.getHoraingreso());
				ps.setTime(4,asistencia.getHoraegreso());
				ps.setString(5,asistencia.getObservaciones());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new AsistenciaException(sqle);}
		catch(Exception e){throw new AsistenciaException(e);}
	}

/**
* 
* Returns a row from the asistencia table for the primary key passed as parameter.
* 
*/

	public Asistencia findByPrimaryKey(long empleado, long fechaingreso, Time horaingreso, Connection con) throws AsistenciaException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select empleado, fechaingreso, horaingreso, horaegreso, observaciones from asistencia where empleado = ? AND fechaingreso = ? AND horaingreso = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, empleado);
	  		stmt.setLong(2, fechaingreso);
	  		stmt.setTime(3, horaingreso);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new AsistenciaException(sqle);
	  	}
	    catch(Exception e){throw new AsistenciaException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the asistencia table for the primary key object passed as parameter.
* 
* @param  AsistenciaPK asistenciapk
* @param Connection con
* @return  Asistencia
*/

	public Asistencia findByPrimaryKey(AsistenciaPK asistenciapk, Connection con) throws AsistenciaException{
		return findByPrimaryKey(asistenciapk.getEmpleado(), asistenciapk.getFechaingreso(), asistenciapk.getHoraingreso(), con);
	}

/**
*
* Returns all rows from asistencia table where EMPLEADO= empleado
*
* @param   long  empleado
* @param   Connection con
* @return  Asistencia[]
*/

	public Asistencia[] findByEmpleado(long empleado, Connection con) throws AsistenciaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select empleado, fechaingreso, horaingreso, horaegreso, observaciones from asistencia where empleado = ? order by empleado";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, empleado );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new AsistenciaException(sqle);
			}
			catch(Exception e){
					throw new AsistenciaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from asistencia table where FECHAINGRESO= fechaingreso
*
* @param   long  fechaingreso
* @param   Connection con
* @return  Asistencia[]
*/

	public Asistencia[] findByFechaingreso(long fechaingreso, Connection con) throws AsistenciaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select empleado, fechaingreso, horaingreso, horaegreso, observaciones from asistencia where fechaingreso = ? order by fechaingreso";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, fechaingreso );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new AsistenciaException(sqle);
			}
			catch(Exception e){
					throw new AsistenciaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from asistencia table where HORAINGRESO= horaingreso
*
* @param   Time  horaingreso
* @param   Connection con
* @return  Asistencia[]
*/

	public Asistencia[] findByHoraingreso(Time horaingreso, Connection con) throws AsistenciaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select empleado, fechaingreso, horaingreso, horaegreso, observaciones from asistencia where horaingreso = ? order by horaingreso";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setTime( 1, horaingreso );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new AsistenciaException(sqle);
			}
			catch(Exception e){
					throw new AsistenciaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from asistencia table where HORAEGRESO= horaegreso
*
* @param   Time  horaegreso
* @param   Connection con
* @return  Asistencia[]
*/

	public Asistencia[] findByHoraegreso(Time horaegreso, Connection con) throws AsistenciaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select empleado, fechaingreso, horaingreso, horaegreso, observaciones from asistencia where horaegreso = ? order by horaegreso";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setTime( 1, horaegreso );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new AsistenciaException(sqle);
			}
			catch(Exception e){
					throw new AsistenciaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from asistencia table where OBSERVACIONES= observaciones
*
* @param   String  observaciones
* @param   Connection con
* @return  Asistencia[]
*/

	public Asistencia[] findByObservaciones(String observaciones, Connection con) throws AsistenciaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select empleado, fechaingreso, horaingreso, horaegreso, observaciones from asistencia where observaciones = ? order by observaciones";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, observaciones );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new AsistenciaException(sqle);
			}
			catch(Exception e){
					throw new AsistenciaException(e);
			}
			finally{}
	}

/**
* Returns all rows from asistencia table 
*
* @param Connection con
* @return  Asistencia[]
*
*/

	public Asistencia[] findAll( Connection con) throws AsistenciaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select empleado, fechaingreso, horaingreso, horaegreso, observaciones from asistencia";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new AsistenciaException(sqle);
			}
			catch(Exception e){
					throw new AsistenciaException(e);
			}
			finally{}
	}

/**
* Returns rows from asistencia table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Asistencia[]
*
*/

	public Asistencia[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws AsistenciaException{
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
					throw new AsistenciaException(sqle);
			}
			catch(Exception e){
					throw new AsistenciaException(e);
			}
			finally{}
	}

/**
* Returns rows from asistencia table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Asistencia[]
*
*/

	public Asistencia[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws AsistenciaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select empleado, fechaingreso, horaingreso, horaegreso, observaciones from asistencia";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new AsistenciaException(sqle);
			}
			catch(Exception e){
					throw new AsistenciaException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Asistencia
*
*/

	protected Asistencia fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Asistencia dto = new Asistencia();
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
* @param Asistencia dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Asistencia dto, ResultSet rs) throws SQLException
	{
		 dto.setEmpleado(rs.getLong("empleado"));
		 dto.setFechaingreso(rs.getLong("fechaingreso"));
		 dto.setHoraingreso(rs.getTime("horaingreso"));
		 dto.setHoraegreso(rs.getTime("horaegreso"));
		 dto.setObservaciones(rs.getString("observaciones"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Asistencia[]
*/

	protected Asistencia[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Asistencia dto = new Asistencia();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Asistencia ret[] = new Asistencia[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
