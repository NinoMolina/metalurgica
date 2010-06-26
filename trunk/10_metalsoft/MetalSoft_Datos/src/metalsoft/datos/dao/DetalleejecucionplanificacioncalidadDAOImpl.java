/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:38:41 GYT 2010
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
* Implementation of DetalleejecucionplanificacioncalidadDAO interface 
* 
*/


public class DetalleejecucionplanificacioncalidadDAOImpl implements DetalleejecucionplanificacioncalidadDAO
{


/**
* Method deletes a record from table DETALLEEJECUCIONPLANIFICACIONCALIDAD
* @param DetalleejecucionplanificacioncalidadPK detalleejecucionplanificacioncalidadpk
* @param  Connection  con
* @return  int
*
*/


	public int delete(DetalleejecucionplanificacioncalidadPK detalleejecucionplanificacioncalidadpk , Connection con)throws DetalleejecucionplanificacioncalidadException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  DETALLEEJECUCIONPLANIFICACIONCALIDAD where iddetalle = ? AND idejecucionplanificacioncalidad = ? AND idplanificacioncalidad = ?");
			ps.setLong(1, detalleejecucionplanificacioncalidadpk.getIddetalle());
			ps.setLong(2, detalleejecucionplanificacioncalidadpk.getIdejecucionplanificacioncalidad());
			ps.setLong(3, detalleejecucionplanificacioncalidadpk.getIdplanificacioncalidad());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new DetalleejecucionplanificacioncalidadException(sqle);}
		catch(Exception e) {throw new DetalleejecucionplanificacioncalidadException(e);}
	}



/**
* This method updates a record in table DETALLEEJECUCIONPLANIFICACIONCALIDAD
* @param DetalleejecucionplanificacioncalidadPK
* @param Detalleejecucionplanificacioncalidad
* @param  Connection con
* @return   int
*/

	public int update(DetalleejecucionplanificacioncalidadPK detalleejecucionplanificacioncalidadpk, Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad, Connection con)throws DetalleejecucionplanificacioncalidadException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update DETALLEEJECUCIONPLANIFICACIONCALIDAD set EJECUCIONPROCESOCALIDAD = ? , IDPROCESOCALIDAD = ? , PIEZA = ? , PIEZAREAL = ?  where iddetalle = ? AND idejecucionplanificacioncalidad = ? AND idplanificacioncalidad = ?");
				ps.setLong(1,detalleejecucionplanificacioncalidad.getEjecucionprocesocalidad());
				ps.setLong(2,detalleejecucionplanificacioncalidad.getIdprocesocalidad());
				ps.setLong(3,detalleejecucionplanificacioncalidad.getPieza());
				ps.setLong(4,detalleejecucionplanificacioncalidad.getPiezareal());
				ps.setLong(5,detalleejecucionplanificacioncalidadpk.getIddetalle());
				ps.setLong(6,detalleejecucionplanificacioncalidadpk.getIdejecucionplanificacioncalidad());
				ps.setLong(7,detalleejecucionplanificacioncalidadpk.getIdplanificacioncalidad());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new DetalleejecucionplanificacioncalidadException(sqle);}
		catch(Exception e){throw new DetalleejecucionplanificacioncalidadException(e);}
	}

/**
* This method inserts data in table DETALLEEJECUCIONPLANIFICACIONCALIDAD
*
* @param Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad
* @param   Connection con
* @return  DetalleejecucionplanificacioncalidadPK
*/

	public int insert(Detalleejecucionplanificacioncalidad detalleejecucionplanificacioncalidad ,Connection con)throws DetalleejecucionplanificacioncalidadException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into DETALLEEJECUCIONPLANIFICACIONCALIDAD( IDDETALLE, IDEJECUCIONPLANIFICACIONCALIDAD, IDPLANIFICACIONCALIDAD, EJECUCIONPROCESOCALIDAD, IDPROCESOCALIDAD, PIEZA, PIEZAREAL) values (?, ?, ?, ?, ?, ?, ?)");
				ps.setLong(1,detalleejecucionplanificacioncalidad.getIddetalle());
				ps.setLong(2,detalleejecucionplanificacioncalidad.getIdejecucionplanificacioncalidad());
				ps.setLong(3,detalleejecucionplanificacioncalidad.getIdplanificacioncalidad());
				ps.setLong(4,detalleejecucionplanificacioncalidad.getEjecucionprocesocalidad());
				ps.setLong(5,detalleejecucionplanificacioncalidad.getIdprocesocalidad());
				ps.setLong(6,detalleejecucionplanificacioncalidad.getPieza());
				ps.setLong(7,detalleejecucionplanificacioncalidad.getPiezareal());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new DetalleejecucionplanificacioncalidadException(sqle);}
		catch(Exception e){throw new DetalleejecucionplanificacioncalidadException(e);}
	}

/**
* 
* Returns a row from the detalleejecucionplanificacioncalidad table for the primary key passed as parameter.
* 
*/

	public Detalleejecucionplanificacioncalidad findByPrimaryKey(long iddetalle, long idejecucionplanificacioncalidad, long idplanificacioncalidad, Connection con) throws DetalleejecucionplanificacioncalidadException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad, ejecucionprocesocalidad, idprocesocalidad, pieza, piezareal from detalleejecucionplanificacioncalidad where iddetalle = ? AND idejecucionplanificacioncalidad = ? AND idplanificacioncalidad = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, iddetalle);
	  		stmt.setLong(2, idejecucionplanificacioncalidad);
	  		stmt.setLong(3, idplanificacioncalidad);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new DetalleejecucionplanificacioncalidadException(sqle);
	  	}
	    catch(Exception e){throw new DetalleejecucionplanificacioncalidadException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the detalleejecucionplanificacioncalidad table for the primary key object passed as parameter.
* 
* @param  DetalleejecucionplanificacioncalidadPK detalleejecucionplanificacioncalidadpk
* @param Connection con
* @return  Detalleejecucionplanificacioncalidad
*/

	public Detalleejecucionplanificacioncalidad findByPrimaryKey(DetalleejecucionplanificacioncalidadPK detalleejecucionplanificacioncalidadpk, Connection con) throws DetalleejecucionplanificacioncalidadException{
		return findByPrimaryKey(detalleejecucionplanificacioncalidadpk.getIddetalle(), detalleejecucionplanificacioncalidadpk.getIdejecucionplanificacioncalidad(), detalleejecucionplanificacioncalidadpk.getIdplanificacioncalidad(), con);
	}

/**
*
* Returns all rows from detalleejecucionplanificacioncalidad table where IDDETALLE= iddetalle
*
* @param   long  iddetalle
* @param   Connection con
* @return  Detalleejecucionplanificacioncalidad[]
*/

	public Detalleejecucionplanificacioncalidad[] findByIddetalle(long iddetalle, Connection con) throws DetalleejecucionplanificacioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad, ejecucionprocesocalidad, idprocesocalidad, pieza, piezareal from detalleejecucionplanificacioncalidad where iddetalle = ? order by iddetalle";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, iddetalle );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleejecucionplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleejecucionplanificacioncalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalleejecucionplanificacioncalidad table where IDEJECUCIONPLANIFICACIONCALIDAD= idejecucionplanificacioncalidad
*
* @param   long  idejecucionplanificacioncalidad
* @param   Connection con
* @return  Detalleejecucionplanificacioncalidad[]
*/

	public Detalleejecucionplanificacioncalidad[] findByIdejecucionplanificacioncalidad(long idejecucionplanificacioncalidad, Connection con) throws DetalleejecucionplanificacioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad, ejecucionprocesocalidad, idprocesocalidad, pieza, piezareal from detalleejecucionplanificacioncalidad where idejecucionplanificacioncalidad = ? order by idejecucionplanificacioncalidad";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idejecucionplanificacioncalidad );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleejecucionplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleejecucionplanificacioncalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalleejecucionplanificacioncalidad table where IDPLANIFICACIONCALIDAD= idplanificacioncalidad
*
* @param   long  idplanificacioncalidad
* @param   Connection con
* @return  Detalleejecucionplanificacioncalidad[]
*/

	public Detalleejecucionplanificacioncalidad[] findByIdplanificacioncalidad(long idplanificacioncalidad, Connection con) throws DetalleejecucionplanificacioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad, ejecucionprocesocalidad, idprocesocalidad, pieza, piezareal from detalleejecucionplanificacioncalidad where idplanificacioncalidad = ? order by idplanificacioncalidad";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idplanificacioncalidad );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleejecucionplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleejecucionplanificacioncalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalleejecucionplanificacioncalidad table where EJECUCIONPROCESOCALIDAD= ejecucionprocesocalidad
*
* @param   long  ejecucionprocesocalidad
* @param   Connection con
* @return  Detalleejecucionplanificacioncalidad[]
*/

	public Detalleejecucionplanificacioncalidad[] findByEjecucionprocesocalidad(long ejecucionprocesocalidad, Connection con) throws DetalleejecucionplanificacioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad, ejecucionprocesocalidad, idprocesocalidad, pieza, piezareal from detalleejecucionplanificacioncalidad where ejecucionprocesocalidad = ? order by ejecucionprocesocalidad";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, ejecucionprocesocalidad );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleejecucionplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleejecucionplanificacioncalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalleejecucionplanificacioncalidad table where IDPROCESOCALIDAD= idprocesocalidad
*
* @param   long  idprocesocalidad
* @param   Connection con
* @return  Detalleejecucionplanificacioncalidad[]
*/

	public Detalleejecucionplanificacioncalidad[] findByIdprocesocalidad(long idprocesocalidad, Connection con) throws DetalleejecucionplanificacioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad, ejecucionprocesocalidad, idprocesocalidad, pieza, piezareal from detalleejecucionplanificacioncalidad where idprocesocalidad = ? order by idprocesocalidad";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idprocesocalidad );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleejecucionplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleejecucionplanificacioncalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalleejecucionplanificacioncalidad table where PIEZA= pieza
*
* @param   long  pieza
* @param   Connection con
* @return  Detalleejecucionplanificacioncalidad[]
*/

	public Detalleejecucionplanificacioncalidad[] findByPieza(long pieza, Connection con) throws DetalleejecucionplanificacioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad, ejecucionprocesocalidad, idprocesocalidad, pieza, piezareal from detalleejecucionplanificacioncalidad where pieza = ? order by pieza";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, pieza );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleejecucionplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleejecucionplanificacioncalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalleejecucionplanificacioncalidad table where PIEZAREAL= piezareal
*
* @param   long  piezareal
* @param   Connection con
* @return  Detalleejecucionplanificacioncalidad[]
*/

	public Detalleejecucionplanificacioncalidad[] findByPiezareal(long piezareal, Connection con) throws DetalleejecucionplanificacioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad, ejecucionprocesocalidad, idprocesocalidad, pieza, piezareal from detalleejecucionplanificacioncalidad where piezareal = ? order by piezareal";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, piezareal );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleejecucionplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleejecucionplanificacioncalidadException(e);
			}
			finally{}
	}

/**
* Returns all rows from detalleejecucionplanificacioncalidad table 
*
* @param Connection con
* @return  Detalleejecucionplanificacioncalidad[]
*
*/

	public Detalleejecucionplanificacioncalidad[] findAll( Connection con) throws DetalleejecucionplanificacioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad, ejecucionprocesocalidad, idprocesocalidad, pieza, piezareal from detalleejecucionplanificacioncalidad";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleejecucionplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleejecucionplanificacioncalidadException(e);
			}
			finally{}
	}

/**
* Returns rows from detalleejecucionplanificacioncalidad table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Detalleejecucionplanificacioncalidad[]
*
*/

	public Detalleejecucionplanificacioncalidad[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws DetalleejecucionplanificacioncalidadException{
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
					throw new DetalleejecucionplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleejecucionplanificacioncalidadException(e);
			}
			finally{}
	}

/**
* Returns rows from detalleejecucionplanificacioncalidad table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Detalleejecucionplanificacioncalidad[]
*
*/

	public Detalleejecucionplanificacioncalidad[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws DetalleejecucionplanificacioncalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select iddetalle, idejecucionplanificacioncalidad, idplanificacioncalidad, ejecucionprocesocalidad, idprocesocalidad, pieza, piezareal from detalleejecucionplanificacioncalidad";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleejecucionplanificacioncalidadException(sqle);
			}
			catch(Exception e){
					throw new DetalleejecucionplanificacioncalidadException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Detalleejecucionplanificacioncalidad
*
*/

	protected Detalleejecucionplanificacioncalidad fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Detalleejecucionplanificacioncalidad dto = new Detalleejecucionplanificacioncalidad();
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
* @param Detalleejecucionplanificacioncalidad dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Detalleejecucionplanificacioncalidad dto, ResultSet rs) throws SQLException
	{
		 dto.setIddetalle(rs.getLong("iddetalle"));
		 dto.setIdejecucionplanificacioncalidad(rs.getLong("idejecucionplanificacioncalidad"));
		 dto.setIdplanificacioncalidad(rs.getLong("idplanificacioncalidad"));
		 dto.setEjecucionprocesocalidad(rs.getLong("ejecucionprocesocalidad"));
		 dto.setIdprocesocalidad(rs.getLong("idprocesocalidad"));
		 dto.setPieza(rs.getLong("pieza"));
		 dto.setPiezareal(rs.getLong("piezareal"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Detalleejecucionplanificacioncalidad[]
*/

	protected Detalleejecucionplanificacioncalidad[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Detalleejecucionplanificacioncalidad dto = new Detalleejecucionplanificacioncalidad();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Detalleejecucionplanificacioncalidad ret[] = new Detalleejecucionplanificacioncalidad[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
