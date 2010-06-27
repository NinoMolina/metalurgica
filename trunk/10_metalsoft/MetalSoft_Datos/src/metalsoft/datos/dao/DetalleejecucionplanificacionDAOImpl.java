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
* Implementation of DetalleejecucionplanificacionDAO interface 
* 
*/


public class DetalleejecucionplanificacionDAOImpl implements DetalleejecucionplanificacionDAO
{


/**
* Method deletes a record from table DETALLEEJECUCIONPLANIFICACION
* @param DetalleejecucionplanificacionPK detalleejecucionplanificacionpk
* @param  Connection  con
* @return  int
*
*/


	public int delete(DetalleejecucionplanificacionPK detalleejecucionplanificacionpk , Connection con)throws DetalleejecucionplanificacionException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  DETALLEEJECUCIONPLANIFICACION where iddetalle = ? AND idejecucionplanificacionproduccion = ? AND idplanificacionproduccion = ?");
			ps.setLong(1, detalleejecucionplanificacionpk.getIddetalle());
			ps.setLong(2, detalleejecucionplanificacionpk.getIdejecucionplanificacionproduccion());
			ps.setLong(3, detalleejecucionplanificacionpk.getIdplanificacionproduccion());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new DetalleejecucionplanificacionException(sqle);}
		catch(Exception e) {throw new DetalleejecucionplanificacionException(e);}
	}



/**
* This method updates a record in table DETALLEEJECUCIONPLANIFICACION
* @param DetalleejecucionplanificacionPK
* @param Detalleejecucionplanificacion
* @param  Connection con
* @return   int
*/

	public int update(DetalleejecucionplanificacionPK detalleejecucionplanificacionpk, Detalleejecucionplanificacion detalleejecucionplanificacion, Connection con)throws DetalleejecucionplanificacionException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update DETALLEEJECUCIONPLANIFICACION set PIEZA = ? , EJECUCIONETAPA = ? , IDETAPAPRODUCCION = ? , PIEZAREAL = ?  where iddetalle = ? AND idejecucionplanificacionproduccion = ? AND idplanificacionproduccion = ?");
				ps.setLong(1,detalleejecucionplanificacion.getPieza());
				ps.setLong(2,detalleejecucionplanificacion.getEjecucionetapa());
				ps.setLong(3,detalleejecucionplanificacion.getIdetapaproduccion());
				ps.setLong(4,detalleejecucionplanificacion.getPiezareal());
				ps.setLong(5,detalleejecucionplanificacionpk.getIddetalle());
				ps.setLong(6,detalleejecucionplanificacionpk.getIdejecucionplanificacionproduccion());
				ps.setLong(7,detalleejecucionplanificacionpk.getIdplanificacionproduccion());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new DetalleejecucionplanificacionException(sqle);}
		catch(Exception e){throw new DetalleejecucionplanificacionException(e);}
	}

/**
* This method inserts data in table DETALLEEJECUCIONPLANIFICACION
*
* @param Detalleejecucionplanificacion detalleejecucionplanificacion
* @param   Connection con
* @return  DetalleejecucionplanificacionPK
*/

	public int insert(Detalleejecucionplanificacion detalleejecucionplanificacion ,Connection con)throws DetalleejecucionplanificacionException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into DETALLEEJECUCIONPLANIFICACION( IDEJECUCIONPLANIFICACIONPRODUCCION, PIEZA, EJECUCIONETAPA, IDPLANIFICACIONPRODUCCION, IDETAPAPRODUCCION, PIEZAREAL) values (?, ?, ?, ?, ?, ?)");
				ps.setLong(1,detalleejecucionplanificacion.getIdejecucionplanificacionproduccion());
				ps.setLong(2,detalleejecucionplanificacion.getPieza());
				ps.setLong(3,detalleejecucionplanificacion.getEjecucionetapa());
				ps.setLong(4,detalleejecucionplanificacion.getIdplanificacionproduccion());
				ps.setLong(5,detalleejecucionplanificacion.getIdetapaproduccion());
				ps.setLong(6,detalleejecucionplanificacion.getPiezareal());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new DetalleejecucionplanificacionException(sqle);}
		catch(Exception e){throw new DetalleejecucionplanificacionException(e);}
	}

/**
* 
* Returns a row from the detalleejecucionplanificacion table for the primary key passed as parameter.
* 
*/

	public Detalleejecucionplanificacion findByPrimaryKey(long iddetalle, long idejecucionplanificacionproduccion, long idplanificacionproduccion, Connection con) throws DetalleejecucionplanificacionException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select iddetalle, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idplanificacionproduccion, idetapaproduccion, piezareal from detalleejecucionplanificacion where iddetalle = ? AND idejecucionplanificacionproduccion = ? AND idplanificacionproduccion = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, iddetalle);
	  		stmt.setLong(2, idejecucionplanificacionproduccion);
	  		stmt.setLong(3, idplanificacionproduccion);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new DetalleejecucionplanificacionException(sqle);
	  	}
	    catch(Exception e){throw new DetalleejecucionplanificacionException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the detalleejecucionplanificacion table for the primary key object passed as parameter.
* 
* @param  DetalleejecucionplanificacionPK detalleejecucionplanificacionpk
* @param Connection con
* @return  Detalleejecucionplanificacion
*/

	public Detalleejecucionplanificacion findByPrimaryKey(DetalleejecucionplanificacionPK detalleejecucionplanificacionpk, Connection con) throws DetalleejecucionplanificacionException{
		return findByPrimaryKey(detalleejecucionplanificacionpk.getIddetalle(), detalleejecucionplanificacionpk.getIdejecucionplanificacionproduccion(), detalleejecucionplanificacionpk.getIdplanificacionproduccion(), con);
	}

/**
*
* Returns all rows from detalleejecucionplanificacion table where IDDETALLE= iddetalle
*
* @param   long  iddetalle
* @param   Connection con
* @return  Detalleejecucionplanificacion[]
*/

	public Detalleejecucionplanificacion[] findByIddetalle(long iddetalle, Connection con) throws DetalleejecucionplanificacionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idplanificacionproduccion, idetapaproduccion, piezareal from detalleejecucionplanificacion where iddetalle = ? order by iddetalle";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, iddetalle );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleejecucionplanificacionException(sqle);
			}
			catch(Exception e){
					throw new DetalleejecucionplanificacionException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalleejecucionplanificacion table where IDEJECUCIONPLANIFICACIONPRODUCCION= idejecucionplanificacionproduccion
*
* @param   long  idejecucionplanificacionproduccion
* @param   Connection con
* @return  Detalleejecucionplanificacion[]
*/

	public Detalleejecucionplanificacion[] findByIdejecucionplanificacionproduccion(long idejecucionplanificacionproduccion, Connection con) throws DetalleejecucionplanificacionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idplanificacionproduccion, idetapaproduccion, piezareal from detalleejecucionplanificacion where idejecucionplanificacionproduccion = ? order by idejecucionplanificacionproduccion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idejecucionplanificacionproduccion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleejecucionplanificacionException(sqle);
			}
			catch(Exception e){
					throw new DetalleejecucionplanificacionException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalleejecucionplanificacion table where PIEZA= pieza
*
* @param   long  pieza
* @param   Connection con
* @return  Detalleejecucionplanificacion[]
*/

	public Detalleejecucionplanificacion[] findByPieza(long pieza, Connection con) throws DetalleejecucionplanificacionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idplanificacionproduccion, idetapaproduccion, piezareal from detalleejecucionplanificacion where pieza = ? order by pieza";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, pieza );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleejecucionplanificacionException(sqle);
			}
			catch(Exception e){
					throw new DetalleejecucionplanificacionException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalleejecucionplanificacion table where EJECUCIONETAPA= ejecucionetapa
*
* @param   long  ejecucionetapa
* @param   Connection con
* @return  Detalleejecucionplanificacion[]
*/

	public Detalleejecucionplanificacion[] findByEjecucionetapa(long ejecucionetapa, Connection con) throws DetalleejecucionplanificacionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idplanificacionproduccion, idetapaproduccion, piezareal from detalleejecucionplanificacion where ejecucionetapa = ? order by ejecucionetapa";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, ejecucionetapa );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleejecucionplanificacionException(sqle);
			}
			catch(Exception e){
					throw new DetalleejecucionplanificacionException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalleejecucionplanificacion table where IDPLANIFICACIONPRODUCCION= idplanificacionproduccion
*
* @param   long  idplanificacionproduccion
* @param   Connection con
* @return  Detalleejecucionplanificacion[]
*/

	public Detalleejecucionplanificacion[] findByIdplanificacionproduccion(long idplanificacionproduccion, Connection con) throws DetalleejecucionplanificacionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idplanificacionproduccion, idetapaproduccion, piezareal from detalleejecucionplanificacion where idplanificacionproduccion = ? order by idplanificacionproduccion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idplanificacionproduccion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleejecucionplanificacionException(sqle);
			}
			catch(Exception e){
					throw new DetalleejecucionplanificacionException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalleejecucionplanificacion table where IDETAPAPRODUCCION= idetapaproduccion
*
* @param   long  idetapaproduccion
* @param   Connection con
* @return  Detalleejecucionplanificacion[]
*/

	public Detalleejecucionplanificacion[] findByIdetapaproduccion(long idetapaproduccion, Connection con) throws DetalleejecucionplanificacionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idplanificacionproduccion, idetapaproduccion, piezareal from detalleejecucionplanificacion where idetapaproduccion = ? order by idetapaproduccion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idetapaproduccion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleejecucionplanificacionException(sqle);
			}
			catch(Exception e){
					throw new DetalleejecucionplanificacionException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalleejecucionplanificacion table where PIEZAREAL= piezareal
*
* @param   long  piezareal
* @param   Connection con
* @return  Detalleejecucionplanificacion[]
*/

	public Detalleejecucionplanificacion[] findByPiezareal(long piezareal, Connection con) throws DetalleejecucionplanificacionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idplanificacionproduccion, idetapaproduccion, piezareal from detalleejecucionplanificacion where piezareal = ? order by piezareal";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, piezareal );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleejecucionplanificacionException(sqle);
			}
			catch(Exception e){
					throw new DetalleejecucionplanificacionException(e);
			}
			finally{}
	}

/**
* Returns all rows from detalleejecucionplanificacion table 
*
* @param Connection con
* @return  Detalleejecucionplanificacion[]
*
*/

	public Detalleejecucionplanificacion[] findAll( Connection con) throws DetalleejecucionplanificacionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idplanificacionproduccion, idetapaproduccion, piezareal from detalleejecucionplanificacion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleejecucionplanificacionException(sqle);
			}
			catch(Exception e){
					throw new DetalleejecucionplanificacionException(e);
			}
			finally{}
	}

/**
* Returns rows from detalleejecucionplanificacion table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Detalleejecucionplanificacion[]
*
*/

	public Detalleejecucionplanificacion[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws DetalleejecucionplanificacionException{
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
					throw new DetalleejecucionplanificacionException(sqle);
			}
			catch(Exception e){
					throw new DetalleejecucionplanificacionException(e);
			}
			finally{}
	}

/**
* Returns rows from detalleejecucionplanificacion table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Detalleejecucionplanificacion[]
*
*/

	public Detalleejecucionplanificacion[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws DetalleejecucionplanificacionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select iddetalle, idejecucionplanificacionproduccion, pieza, ejecucionetapa, idplanificacionproduccion, idetapaproduccion, piezareal from detalleejecucionplanificacion";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalleejecucionplanificacionException(sqle);
			}
			catch(Exception e){
					throw new DetalleejecucionplanificacionException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Detalleejecucionplanificacion
*
*/

	protected Detalleejecucionplanificacion fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Detalleejecucionplanificacion dto = new Detalleejecucionplanificacion();
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
* @param Detalleejecucionplanificacion dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Detalleejecucionplanificacion dto, ResultSet rs) throws SQLException
	{
		 dto.setIddetalle(rs.getLong("iddetalle"));
		 dto.setIdejecucionplanificacionproduccion(rs.getLong("idejecucionplanificacionproduccion"));
		 dto.setPieza(rs.getLong("pieza"));
		 dto.setEjecucionetapa(rs.getLong("ejecucionetapa"));
		 dto.setIdplanificacionproduccion(rs.getLong("idplanificacionproduccion"));
		 dto.setIdetapaproduccion(rs.getLong("idetapaproduccion"));
		 dto.setPiezareal(rs.getLong("piezareal"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Detalleejecucionplanificacion[]
*/

	protected Detalleejecucionplanificacion[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Detalleejecucionplanificacion dto = new Detalleejecucionplanificacion();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Detalleejecucionplanificacion ret[] = new Detalleejecucionplanificacion[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
