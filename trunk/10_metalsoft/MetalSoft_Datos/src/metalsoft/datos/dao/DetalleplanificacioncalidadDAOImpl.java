/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:01 ART 2010
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
* Implementation of DetalleplanificacioncalidadDAO interface 
* 
*/


public class DetalleplanificacioncalidadDAOImpl implements DetalleplanificacioncalidadDAO
{


/**
* Method deletes a record from table DETALLEPLANIFICACIONCALIDAD
* @param DetalleplanificacioncalidadPK detalleplanificacioncalidadpk
* @param  Connection  con
* @return  int
*
*/


	public int delete(DetalleplanificacioncalidadPK detalleplanificacioncalidadpk , Connection con)throws DetalleplanificacioncalidadException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  DETALLEPLANIFICACIONCALIDAD where iddetalle = ? AND idplanificacioncalidad = ?");
			ps.setLong(1, detalleplanificacioncalidadpk.getIddetalle());
			ps.setLong(2, detalleplanificacioncalidadpk.getIdplanificacioncalidad());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new DetalleplanificacioncalidadException(sqle);}
		catch(Exception e) {throw new DetalleplanificacioncalidadException(e);}
	}



/**
* This method updates a record in table DETALLEPLANIFICACIONCALIDAD
* @param DetalleplanificacioncalidadPK
* @param Detalleplanificacioncalidad
* @param  Connection con
* @return   int
*/

	public int update(DetalleplanificacioncalidadPK detalleplanificacioncalidadpk, Detalleplanificacioncalidad detalleplanificacioncalidad, Connection con)throws DetalleplanificacioncalidadException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update DETALLEPLANIFICACIONCALIDAD set IDDETALLEEJECUCIONPLANIFICACIONCALIDAD = ? , IDEJECUCIONPLANIFICACIONCALIDAD = ? , FECHAINICIOPLAN = ? , FECHAFINPLAN = ? , HORAINICIOPLAN = ? , HORAFINPLAN = ? , PROCESOCALIDAD = ? , PIEZA = ?  where iddetalle = ? AND idplanificacioncalidad = ?");
				ps.setLong(1,detalleplanificacioncalidad.getIddetalleejecucionplanificacioncalidad());
				ps.setLong(2,detalleplanificacioncalidad.getIdejecucionplanificacioncalidad());
				ps.setDate(3,detalleplanificacioncalidad.getFechainicioplan());
				ps.setDate(4,detalleplanificacioncalidad.getFechafinplan());
				ps.setTime(5,detalleplanificacioncalidad.getHorainicioplan());
				ps.setTime(6,detalleplanificacioncalidad.getHorafinplan());
				ps.setLong(7,detalleplanificacioncalidad.getProcesocalidad());
				ps.setLong(8,detalleplanificacioncalidad.getPieza());
				ps.setLong(9,detalleplanificacioncalidadpk.getIddetalle());
				ps.setLong(10,detalleplanificacioncalidadpk.getIdplanificacioncalidad());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new DetalleplanificacioncalidadException(sqle);}
		catch(Exception e){throw new DetalleplanificacioncalidadException(e);}
	}

/**
* This method inserts data in table DETALLEPLANIFICACIONCALIDAD
*
* @param Detalleplanificacioncalidad detalleplanificacioncalidad
* @param   Connection con
* @return  DetalleplanificacioncalidadPK
*/

	public int insert(Detalleplanificacioncalidad detalleplanificacioncalidad ,Connection con)throws DetalleplanificacioncalidadException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into DETALLEPLANIFICACIONCALIDAD( IDPLANIFICACIONCALIDAD, IDDETALLEEJECUCIONPLANIFICACIONCALIDAD, IDEJECUCIONPLANIFICACIONCALIDAD, FECHAINICIOPLAN, FECHAFINPLAN, HORAINICIOPLAN, HORAFINPLAN, PROCESOCALIDAD, PIEZA) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setLong(1,detalleplanificacioncalidad.getIdplanificacioncalidad());
				ps.setLong(2,detalleplanificacioncalidad.getIddetalleejecucionplanificacioncalidad());
				ps.setLong(3,detalleplanificacioncalidad.getIdejecucionplanificacioncalidad());
				ps.setDate(4,detalleplanificacioncalidad.getFechainicioplan());
				ps.setDate(5,detalleplanificacioncalidad.getFechafinplan());
				ps.setTime(6,detalleplanificacioncalidad.getHorainicioplan());
				ps.setTime(7,detalleplanificacioncalidad.getHorafinplan());
				ps.setLong(8,detalleplanificacioncalidad.getProcesocalidad());
				ps.setLong(9,detalleplanificacioncalidad.getPieza());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new DetalleplanificacioncalidadException(sqle);}
		catch(Exception e){throw new DetalleplanificacioncalidadException(e);}
	}

/**
* 
* Returns a row from the detalleplanificacioncalidad table for the primary key passed as parameter.
* 
*/

	public Detalleplanificacioncalidad findByPrimaryKey(long iddetalle, long idplanificacioncalidad, Connection con) throws DetalleplanificacioncalidadException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, idejecucionplanificacioncalidad, fechainicioplan, fechafinplan, horainicioplan, horafinplan, procesocalidad, pieza from detalleplanificacioncalidad where iddetalle = ? AND idplanificacioncalidad = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, iddetalle);
	  		stmt.setLong(2, idplanificacioncalidad);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new DetalleplanificacioncalidadException(sqle);
	  	}
	    catch(Exception e){throw new DetalleplanificacioncalidadException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the detalleplanificacioncalidad table for the primary key object passed as parameter.
* 
* @param  DetalleplanificacioncalidadPK detalleplanificacioncalidadpk
* @param Connection con
* @return  Detalleplanificacioncalidad
*/

	public Detalleplanificacioncalidad findByPrimaryKey(DetalleplanificacioncalidadPK detalleplanificacioncalidadpk, Connection con) throws DetalleplanificacioncalidadException{
		return findByPrimaryKey(detalleplanificacioncalidadpk.getIddetalle(), detalleplanificacioncalidadpk.getIdplanificacioncalidad(), con);
	}

/**
*
* Returns all rows from detalleplanificacioncalidad table where IDDETALLE= iddetalle
*
* @param   long  iddetalle
* @param   Connection con
* @return  Detalleplanificacioncalidad[]
*/

	public Detalleplanificacioncalidad[] findByIddetalle(long iddetalle, Connection con) throws DetalleplanificacioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, idejecucionplanificacioncalidad, fechainicioplan, fechafinplan, horainicioplan, horafinplan, procesocalidad, pieza from detalleplanificacioncalidad where iddetalle = ? order by iddetalle";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, iddetalle );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleplanificacioncalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalleplanificacioncalidad table where IDPLANIFICACIONCALIDAD= idplanificacioncalidad
*
* @param   long  idplanificacioncalidad
* @param   Connection con
* @return  Detalleplanificacioncalidad[]
*/

	public Detalleplanificacioncalidad[] findByIdplanificacioncalidad(long idplanificacioncalidad, Connection con) throws DetalleplanificacioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, idejecucionplanificacioncalidad, fechainicioplan, fechafinplan, horainicioplan, horafinplan, procesocalidad, pieza from detalleplanificacioncalidad where idplanificacioncalidad = ? order by idplanificacioncalidad";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idplanificacioncalidad );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleplanificacioncalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalleplanificacioncalidad table where IDDETALLEEJECUCIONPLANIFICACIONCALIDAD= iddetalleejecucionplanificacioncalidad
*
* @param   long  iddetalleejecucionplanificacioncalidad
* @param   Connection con
* @return  Detalleplanificacioncalidad[]
*/

	public Detalleplanificacioncalidad[] findByIddetalleejecucionplanificacioncalidad(long iddetalleejecucionplanificacioncalidad, Connection con) throws DetalleplanificacioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, idejecucionplanificacioncalidad, fechainicioplan, fechafinplan, horainicioplan, horafinplan, procesocalidad, pieza from detalleplanificacioncalidad where iddetalleejecucionplanificacioncalidad = ? order by iddetalleejecucionplanificacioncalidad";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, iddetalleejecucionplanificacioncalidad );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleplanificacioncalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalleplanificacioncalidad table where IDEJECUCIONPLANIFICACIONCALIDAD= idejecucionplanificacioncalidad
*
* @param   long  idejecucionplanificacioncalidad
* @param   Connection con
* @return  Detalleplanificacioncalidad[]
*/

	public Detalleplanificacioncalidad[] findByIdejecucionplanificacioncalidad(long idejecucionplanificacioncalidad, Connection con) throws DetalleplanificacioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, idejecucionplanificacioncalidad, fechainicioplan, fechafinplan, horainicioplan, horafinplan, procesocalidad, pieza from detalleplanificacioncalidad where idejecucionplanificacioncalidad = ? order by idejecucionplanificacioncalidad";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idejecucionplanificacioncalidad );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleplanificacioncalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalleplanificacioncalidad table where FECHAINICIOPLAN= fechainicioplan
*
* @param   Date  fechainicioplan
* @param   Connection con
* @return  Detalleplanificacioncalidad[]
*/

	public Detalleplanificacioncalidad[] findByFechainicioplan(Date fechainicioplan, Connection con) throws DetalleplanificacioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, idejecucionplanificacioncalidad, fechainicioplan, fechafinplan, horainicioplan, horafinplan, procesocalidad, pieza from detalleplanificacioncalidad where fechainicioplan = ? order by fechainicioplan";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechainicioplan );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleplanificacioncalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalleplanificacioncalidad table where FECHAFINPLAN= fechafinplan
*
* @param   Date  fechafinplan
* @param   Connection con
* @return  Detalleplanificacioncalidad[]
*/

	public Detalleplanificacioncalidad[] findByFechafinplan(Date fechafinplan, Connection con) throws DetalleplanificacioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, idejecucionplanificacioncalidad, fechainicioplan, fechafinplan, horainicioplan, horafinplan, procesocalidad, pieza from detalleplanificacioncalidad where fechafinplan = ? order by fechafinplan";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechafinplan );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleplanificacioncalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalleplanificacioncalidad table where HORAINICIOPLAN= horainicioplan
*
* @param   Time  horainicioplan
* @param   Connection con
* @return  Detalleplanificacioncalidad[]
*/

	public Detalleplanificacioncalidad[] findByHorainicioplan(Time horainicioplan, Connection con) throws DetalleplanificacioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, idejecucionplanificacioncalidad, fechainicioplan, fechafinplan, horainicioplan, horafinplan, procesocalidad, pieza from detalleplanificacioncalidad where horainicioplan = ? order by horainicioplan";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setTime( 1, horainicioplan );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleplanificacioncalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalleplanificacioncalidad table where HORAFINPLAN= horafinplan
*
* @param   Time  horafinplan
* @param   Connection con
* @return  Detalleplanificacioncalidad[]
*/

	public Detalleplanificacioncalidad[] findByHorafinplan(Time horafinplan, Connection con) throws DetalleplanificacioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, idejecucionplanificacioncalidad, fechainicioplan, fechafinplan, horainicioplan, horafinplan, procesocalidad, pieza from detalleplanificacioncalidad where horafinplan = ? order by horafinplan";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setTime( 1, horafinplan );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleplanificacioncalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalleplanificacioncalidad table where PROCESOCALIDAD= procesocalidad
*
* @param   long  procesocalidad
* @param   Connection con
* @return  Detalleplanificacioncalidad[]
*/

	public Detalleplanificacioncalidad[] findByProcesocalidad(long procesocalidad, Connection con) throws DetalleplanificacioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, idejecucionplanificacioncalidad, fechainicioplan, fechafinplan, horainicioplan, horafinplan, procesocalidad, pieza from detalleplanificacioncalidad where procesocalidad = ? order by procesocalidad";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, procesocalidad );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleplanificacioncalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalleplanificacioncalidad table where PIEZA= pieza
*
* @param   long  pieza
* @param   Connection con
* @return  Detalleplanificacioncalidad[]
*/

	public Detalleplanificacioncalidad[] findByPieza(long pieza, Connection con) throws DetalleplanificacioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, idejecucionplanificacioncalidad, fechainicioplan, fechafinplan, horainicioplan, horafinplan, procesocalidad, pieza from detalleplanificacioncalidad where pieza = ? order by pieza";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, pieza );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleplanificacioncalidadException(e);
			}
			finally{}
	}

/**
* Returns all rows from detalleplanificacioncalidad table 
*
* @param Connection con
* @return  Detalleplanificacioncalidad[]
*
*/

	public Detalleplanificacioncalidad[] findAll( Connection con) throws DetalleplanificacioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, idejecucionplanificacioncalidad, fechainicioplan, fechafinplan, horainicioplan, horafinplan, procesocalidad, pieza from detalleplanificacioncalidad";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleplanificacioncalidadException(e);
			}
			finally{}
	}

/**
* Returns rows from detalleplanificacioncalidad table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Detalleplanificacioncalidad[]
*
*/

	public Detalleplanificacioncalidad[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws DetalleplanificacioncalidadException{
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
					throw new DetalleplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleplanificacioncalidadException(e);
			}
			finally{}
	}

/**
* Returns rows from detalleplanificacioncalidad table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Detalleplanificacioncalidad[]
*
*/

	public Detalleplanificacioncalidad[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws DetalleplanificacioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select iddetalle, idplanificacioncalidad, iddetalleejecucionplanificacioncalidad, idejecucionplanificacioncalidad, fechainicioplan, fechafinplan, horainicioplan, horafinplan, procesocalidad, pieza from detalleplanificacioncalidad";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleplanificacioncalidadException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Detalleplanificacioncalidad
*
*/

	protected Detalleplanificacioncalidad fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Detalleplanificacioncalidad dto = new Detalleplanificacioncalidad();
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
* @param Detalleplanificacioncalidad dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Detalleplanificacioncalidad dto, ResultSet rs) throws SQLException
	{
		 dto.setIddetalle(rs.getLong("iddetalle"));
		 dto.setIdplanificacioncalidad(rs.getLong("idplanificacioncalidad"));
		 dto.setIddetalleejecucionplanificacioncalidad(rs.getLong("iddetalleejecucionplanificacioncalidad"));
		 dto.setIdejecucionplanificacioncalidad(rs.getLong("idejecucionplanificacioncalidad"));
		 dto.setFechainicioplan(rs.getDate("fechainicioplan"));
		 dto.setFechafinplan(rs.getDate("fechafinplan"));
		 dto.setHorainicioplan(rs.getTime("horainicioplan"));
		 dto.setHorafinplan(rs.getTime("horafinplan"));
		 dto.setProcesocalidad(rs.getLong("procesocalidad"));
		 dto.setPieza(rs.getLong("pieza"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Detalleplanificacioncalidad[]
*/

	protected Detalleplanificacioncalidad[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Detalleplanificacioncalidad dto = new Detalleplanificacioncalidad();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Detalleplanificacioncalidad ret[] = new Detalleplanificacioncalidad[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
