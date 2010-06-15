/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:38:56 GYT 2010
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
* Implementation of EjecucionprocesocalidadDAO interface 
* 
*/


public class EjecucionprocesocalidadDAOImpl implements EjecucionprocesocalidadDAO
{


/**
* Method deletes a record from table EJECUCIONPROCESOCALIDAD
* @param EjecucionprocesocalidadPK ejecucionprocesocalidadpk
* @param  Connection  con
* @return  int
*
*/


	public int delete(EjecucionprocesocalidadPK ejecucionprocesocalidadpk , Connection con)throws EjecucionprocesocalidadException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  EJECUCIONPROCESOCALIDAD where nroejecucion = ? AND idprocesocalidad = ?");
			ps.setLong(1, ejecucionprocesocalidadpk.getNroejecucion());
			ps.setLong(2, ejecucionprocesocalidadpk.getIdprocesocalidad());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new EjecucionprocesocalidadException(sqle);}
		catch(Exception e) {throw new EjecucionprocesocalidadException(e);}
	}



/**
* This method updates a record in table EJECUCIONPROCESOCALIDAD
* @param EjecucionprocesocalidadPK
* @param Ejecucionprocesocalidad
* @param  Connection con
* @return   int
*/

	public int update(EjecucionprocesocalidadPK ejecucionprocesocalidadpk, Ejecucionprocesocalidad ejecucionprocesocalidad, Connection con)throws EjecucionprocesocalidadException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update EJECUCIONPROCESOCALIDAD set DURACIONREAL = ? , RESULTADO = ?  where nroejecucion = ? AND idprocesocalidad = ?");
				ps.setTime(1,ejecucionprocesocalidad.getDuracionreal());
				ps.setString(2,ejecucionprocesocalidad.getResultado());
				ps.setLong(3,ejecucionprocesocalidadpk.getNroejecucion());
				ps.setLong(4,ejecucionprocesocalidadpk.getIdprocesocalidad());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new EjecucionprocesocalidadException(sqle);}
		catch(Exception e){throw new EjecucionprocesocalidadException(e);}
	}

/**
* This method inserts data in table EJECUCIONPROCESOCALIDAD
*
* @param Ejecucionprocesocalidad ejecucionprocesocalidad
* @param   Connection con
* @return  EjecucionprocesocalidadPK
*/

	public int insert(Ejecucionprocesocalidad ejecucionprocesocalidad ,Connection con)throws EjecucionprocesocalidadException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into EJECUCIONPROCESOCALIDAD( NROEJECUCION, IDPROCESOCALIDAD, DURACIONREAL, RESULTADO) values (?, ?, ?, ?)");
				ps.setLong(1,ejecucionprocesocalidad.getNroejecucion());
				ps.setLong(2,ejecucionprocesocalidad.getIdprocesocalidad());
				ps.setTime(3,ejecucionprocesocalidad.getDuracionreal());
				ps.setString(4,ejecucionprocesocalidad.getResultado());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new EjecucionprocesocalidadException(sqle);}
		catch(Exception e){throw new EjecucionprocesocalidadException(e);}
	}

/**
* 
* Returns a row from the ejecucionprocesocalidad table for the primary key passed as parameter.
* 
*/

	public Ejecucionprocesocalidad findByPrimaryKey(long nroejecucion, long idprocesocalidad, Connection con) throws EjecucionprocesocalidadException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select nroejecucion, idprocesocalidad, duracionreal, resultado from ejecucionprocesocalidad where nroejecucion = ? AND idprocesocalidad = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, nroejecucion);
	  		stmt.setLong(2, idprocesocalidad);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new EjecucionprocesocalidadException(sqle);
	  	}
	    catch(Exception e){throw new EjecucionprocesocalidadException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the ejecucionprocesocalidad table for the primary key object passed as parameter.
* 
* @param  EjecucionprocesocalidadPK ejecucionprocesocalidadpk
* @param Connection con
* @return  Ejecucionprocesocalidad
*/

	public Ejecucionprocesocalidad findByPrimaryKey(EjecucionprocesocalidadPK ejecucionprocesocalidadpk, Connection con) throws EjecucionprocesocalidadException{
		return findByPrimaryKey(ejecucionprocesocalidadpk.getNroejecucion(), ejecucionprocesocalidadpk.getIdprocesocalidad(), con);
	}

/**
*
* Returns all rows from ejecucionprocesocalidad table where NROEJECUCION= nroejecucion
*
* @param   long  nroejecucion
* @param   Connection con
* @return  Ejecucionprocesocalidad[]
*/

	public Ejecucionprocesocalidad[] findByNroejecucion(long nroejecucion, Connection con) throws EjecucionprocesocalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nroejecucion, idprocesocalidad, duracionreal, resultado from ejecucionprocesocalidad where nroejecucion = ? order by nroejecucion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, nroejecucion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EjecucionprocesocalidadException(sqle);
			}
			catch(Exception e){
					throw new EjecucionprocesocalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from ejecucionprocesocalidad table where IDPROCESOCALIDAD= idprocesocalidad
*
* @param   long  idprocesocalidad
* @param   Connection con
* @return  Ejecucionprocesocalidad[]
*/

	public Ejecucionprocesocalidad[] findByIdprocesocalidad(long idprocesocalidad, Connection con) throws EjecucionprocesocalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nroejecucion, idprocesocalidad, duracionreal, resultado from ejecucionprocesocalidad where idprocesocalidad = ? order by idprocesocalidad";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idprocesocalidad );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EjecucionprocesocalidadException(sqle);
			}
			catch(Exception e){
					throw new EjecucionprocesocalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from ejecucionprocesocalidad table where DURACIONREAL= duracionreal
*
* @param   Time  duracionreal
* @param   Connection con
* @return  Ejecucionprocesocalidad[]
*/

	public Ejecucionprocesocalidad[] findByDuracionreal(Time duracionreal, Connection con) throws EjecucionprocesocalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nroejecucion, idprocesocalidad, duracionreal, resultado from ejecucionprocesocalidad where duracionreal = ? order by duracionreal";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setTime( 1, duracionreal );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EjecucionprocesocalidadException(sqle);
			}
			catch(Exception e){
					throw new EjecucionprocesocalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from ejecucionprocesocalidad table where RESULTADO= resultado
*
* @param   String  resultado
* @param   Connection con
* @return  Ejecucionprocesocalidad[]
*/

	public Ejecucionprocesocalidad[] findByResultado(String resultado, Connection con) throws EjecucionprocesocalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nroejecucion, idprocesocalidad, duracionreal, resultado from ejecucionprocesocalidad where resultado = ? order by resultado";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, resultado );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EjecucionprocesocalidadException(sqle);
			}
			catch(Exception e){
					throw new EjecucionprocesocalidadException(e);
			}
			finally{}
	}

/**
* Returns all rows from ejecucionprocesocalidad table 
*
* @param Connection con
* @return  Ejecucionprocesocalidad[]
*
*/

	public Ejecucionprocesocalidad[] findAll( Connection con) throws EjecucionprocesocalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nroejecucion, idprocesocalidad, duracionreal, resultado from ejecucionprocesocalidad";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EjecucionprocesocalidadException(sqle);
			}
			catch(Exception e){
					throw new EjecucionprocesocalidadException(e);
			}
			finally{}
	}

/**
* Returns rows from ejecucionprocesocalidad table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Ejecucionprocesocalidad[]
*
*/

	public Ejecucionprocesocalidad[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws EjecucionprocesocalidadException{
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
					throw new EjecucionprocesocalidadException(sqle);
			}
			catch(Exception e){
					throw new EjecucionprocesocalidadException(e);
			}
			finally{}
	}

/**
* Returns rows from ejecucionprocesocalidad table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Ejecucionprocesocalidad[]
*
*/

	public Ejecucionprocesocalidad[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws EjecucionprocesocalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select nroejecucion, idprocesocalidad, duracionreal, resultado from ejecucionprocesocalidad";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EjecucionprocesocalidadException(sqle);
			}
			catch(Exception e){
					throw new EjecucionprocesocalidadException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Ejecucionprocesocalidad
*
*/

	protected Ejecucionprocesocalidad fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Ejecucionprocesocalidad dto = new Ejecucionprocesocalidad();
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
* @param Ejecucionprocesocalidad dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Ejecucionprocesocalidad dto, ResultSet rs) throws SQLException
	{
		 dto.setNroejecucion(rs.getLong("nroejecucion"));
		 dto.setIdprocesocalidad(rs.getLong("idprocesocalidad"));
		 dto.setDuracionreal(rs.getTime("duracionreal"));
		 dto.setResultado(rs.getString("resultado"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Ejecucionprocesocalidad[]
*/

	protected Ejecucionprocesocalidad[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Ejecucionprocesocalidad dto = new Ejecucionprocesocalidad();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Ejecucionprocesocalidad ret[] = new Ejecucionprocesocalidad[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
