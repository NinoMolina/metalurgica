/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:11 ART 2010
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
* Implementation of TurnoDAO interface 
* 
*/


public class TurnoDAOImpl implements TurnoDAO
{


/**
* Method deletes a record from table TURNO
* @param TurnoPK turnopk
* @param  Connection  con
* @return  int
*
*/


	public int delete(TurnoPK turnopk , Connection con)throws TurnoException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  TURNO where idturno = ?");
			ps.setLong(1, turnopk.getIdturno());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new TurnoException(sqle);}
		catch(Exception e) {throw new TurnoException(e);}
	}



/**
* This method updates a record in table TURNO
* @param TurnoPK
* @param Turno
* @param  Connection con
* @return   int
*/

	public int update(TurnoPK turnopk, Turno turno, Connection con)throws TurnoException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update TURNO set HORAINICIO = ? , HORAFIN = ? , NOMBRE = ? , DESCRIPCION = ?  where idturno = ?");
				ps.setTime(1,turno.getHorainicio());
				ps.setTime(2,turno.getHorafin());
				ps.setString(3,turno.getNombre());
				ps.setString(4,turno.getDescripcion());
				ps.setLong(5,turnopk.getIdturno());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new TurnoException(sqle);}
		catch(Exception e){throw new TurnoException(e);}
	}

/**
* This method inserts data in table TURNO
*
* @param Turno turno
* @param   Connection con
* @return  TurnoPK
*/

	public int insert(Turno turno ,Connection con)throws TurnoException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into TURNO( IDTURNO, HORAINICIO, HORAFIN, NOMBRE, DESCRIPCION) values (?, ?, ?, ?, ?)");
				ps.setLong(1,turno.getIdturno());
				ps.setTime(2,turno.getHorainicio());
				ps.setTime(3,turno.getHorafin());
				ps.setString(4,turno.getNombre());
				ps.setString(5,turno.getDescripcion());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new TurnoException(sqle);}
		catch(Exception e){throw new TurnoException(e);}
	}

/**
* 
* Returns a row from the turno table for the primary key passed as parameter.
* 
*/

	public Turno findByPrimaryKey(long idturno, Connection con) throws TurnoException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idturno, horainicio, horafin, nombre, descripcion from turno where idturno = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idturno);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new TurnoException(sqle);
	  	}
	    catch(Exception e){throw new TurnoException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the turno table for the primary key object passed as parameter.
* 
* @param  TurnoPK turnopk
* @param Connection con
* @return  Turno
*/

	public Turno findByPrimaryKey(TurnoPK turnopk, Connection con) throws TurnoException{
		return findByPrimaryKey(turnopk.getIdturno(), con);
	}

/**
*
* Returns all rows from turno table where IDTURNO= idturno
*
* @param   long  idturno
* @param   Connection con
* @return  Turno[]
*/

	public Turno[] findByIdturno(long idturno, Connection con) throws TurnoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idturno, horainicio, horafin, nombre, descripcion from turno where idturno = ? order by idturno";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idturno );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TurnoException(sqle);
			}
			catch(Exception e){
					throw new TurnoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from turno table where HORAINICIO= horainicio
*
* @param   Time  horainicio
* @param   Connection con
* @return  Turno[]
*/

	public Turno[] findByHorainicio(Time horainicio, Connection con) throws TurnoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idturno, horainicio, horafin, nombre, descripcion from turno where horainicio = ? order by horainicio";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setTime( 1, horainicio );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TurnoException(sqle);
			}
			catch(Exception e){
					throw new TurnoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from turno table where HORAFIN= horafin
*
* @param   Time  horafin
* @param   Connection con
* @return  Turno[]
*/

	public Turno[] findByHorafin(Time horafin, Connection con) throws TurnoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idturno, horainicio, horafin, nombre, descripcion from turno where horafin = ? order by horafin";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setTime( 1, horafin );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TurnoException(sqle);
			}
			catch(Exception e){
					throw new TurnoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from turno table where NOMBRE= nombre
*
* @param   String  nombre
* @param   Connection con
* @return  Turno[]
*/

	public Turno[] findByNombre(String nombre, Connection con) throws TurnoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idturno, horainicio, horafin, nombre, descripcion from turno where nombre = ? order by nombre";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, nombre );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TurnoException(sqle);
			}
			catch(Exception e){
					throw new TurnoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from turno table where DESCRIPCION= descripcion
*
* @param   String  descripcion
* @param   Connection con
* @return  Turno[]
*/

	public Turno[] findByDescripcion(String descripcion, Connection con) throws TurnoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idturno, horainicio, horafin, nombre, descripcion from turno where descripcion = ? order by descripcion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, descripcion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TurnoException(sqle);
			}
			catch(Exception e){
					throw new TurnoException(e);
			}
			finally{}
	}

/**
* Returns all rows from turno table 
*
* @param Connection con
* @return  Turno[]
*
*/

	public Turno[] findAll( Connection con) throws TurnoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idturno, horainicio, horafin, nombre, descripcion from turno";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TurnoException(sqle);
			}
			catch(Exception e){
					throw new TurnoException(e);
			}
			finally{}
	}

/**
* Returns rows from turno table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Turno[]
*
*/

	public Turno[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws TurnoException{
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
					throw new TurnoException(sqle);
			}
			catch(Exception e){
					throw new TurnoException(e);
			}
			finally{}
	}

/**
* Returns rows from turno table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Turno[]
*
*/

	public Turno[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws TurnoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idturno, horainicio, horafin, nombre, descripcion from turno";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TurnoException(sqle);
			}
			catch(Exception e){
					throw new TurnoException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Turno
*
*/

	protected Turno fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Turno dto = new Turno();
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
* @param Turno dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Turno dto, ResultSet rs) throws SQLException
	{
		 dto.setIdturno(rs.getLong("idturno"));
		 dto.setHorainicio(rs.getTime("horainicio"));
		 dto.setHorafin(rs.getTime("horafin"));
		 dto.setNombre(rs.getString("nombre"));
		 dto.setDescripcion(rs.getString("descripcion"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Turno[]
*/

	protected Turno[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Turno dto = new Turno();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Turno ret[] = new Turno[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
