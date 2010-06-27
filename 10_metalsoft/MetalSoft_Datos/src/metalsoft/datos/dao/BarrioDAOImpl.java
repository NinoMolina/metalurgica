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
* Implementation of BarrioDAO interface 
* 
*/


public class BarrioDAOImpl implements BarrioDAO
{


/**
* Method deletes a record from table BARRIO
* @param BarrioPK barriopk
* @param  Connection  con
* @return  int
*
*/


	public int delete(BarrioPK barriopk , Connection con)throws BarrioException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  BARRIO where idbarrio = ?");
			ps.setLong(1, barriopk.getIdbarrio());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new BarrioException(sqle);}
		catch(Exception e) {throw new BarrioException(e);}
	}



/**
* This method updates a record in table BARRIO
* @param BarrioPK
* @param Barrio
* @param  Connection con
* @return   int
*/

	public int update(BarrioPK barriopk, Barrio barrio, Connection con)throws BarrioException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update BARRIO set NOMBRE = ? , CODPOSTAL = ? , LOCALIDAD = ?  where idbarrio = ?");
				ps.setLong(1,barrio.getNombre());
				ps.setLong(2,barrio.getCodpostal());
				ps.setLong(3,barrio.getLocalidad());
				ps.setLong(4,barriopk.getIdbarrio());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new BarrioException(sqle);}
		catch(Exception e){throw new BarrioException(e);}
	}

/**
* This method inserts data in table BARRIO
*
* @param Barrio barrio
* @param   Connection con
* @return  BarrioPK
*/

	public int insert(Barrio barrio ,Connection con)throws BarrioException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into BARRIO( NOMBRE, CODPOSTAL, LOCALIDAD) values (?, ?, ?)");
				ps.setLong(1,barrio.getNombre());
				ps.setLong(2,barrio.getCodpostal());
				ps.setLong(3,barrio.getLocalidad());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new BarrioException(sqle);}
		catch(Exception e){throw new BarrioException(e);}
	}

/**
* 
* Returns a row from the barrio table for the primary key passed as parameter.
* 
*/

	public Barrio findByPrimaryKey(long idbarrio, Connection con) throws BarrioException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idbarrio, nombre, codpostal, localidad from barrio where idbarrio = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idbarrio);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new BarrioException(sqle);
	  	}
	    catch(Exception e){throw new BarrioException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the barrio table for the primary key object passed as parameter.
* 
* @param  BarrioPK barriopk
* @param Connection con
* @return  Barrio
*/

	public Barrio findByPrimaryKey(BarrioPK barriopk, Connection con) throws BarrioException{
		return findByPrimaryKey(barriopk.getIdbarrio(), con);
	}

/**
*
* Returns all rows from barrio table where IDBARRIO= idbarrio
*
* @param   long  idbarrio
* @param   Connection con
* @return  Barrio[]
*/

	public Barrio[] findByIdbarrio(long idbarrio, Connection con) throws BarrioException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idbarrio, nombre, codpostal, localidad from barrio where idbarrio = ? order by idbarrio";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idbarrio );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new BarrioException(sqle);
			}
			catch(Exception e){
					throw new BarrioException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from barrio table where NOMBRE= nombre
*
* @param   long  nombre
* @param   Connection con
* @return  Barrio[]
*/

	public Barrio[] findByNombre(long nombre, Connection con) throws BarrioException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idbarrio, nombre, codpostal, localidad from barrio where nombre = ? order by nombre";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, nombre );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new BarrioException(sqle);
			}
			catch(Exception e){
					throw new BarrioException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from barrio table where CODPOSTAL= codpostal
*
* @param   long  codpostal
* @param   Connection con
* @return  Barrio[]
*/

	public Barrio[] findByCodpostal(long codpostal, Connection con) throws BarrioException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idbarrio, nombre, codpostal, localidad from barrio where codpostal = ? order by codpostal";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, codpostal );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new BarrioException(sqle);
			}
			catch(Exception e){
					throw new BarrioException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from barrio table where LOCALIDAD= localidad
*
* @param   long  localidad
* @param   Connection con
* @return  Barrio[]
*/

	public Barrio[] findByLocalidad(long localidad, Connection con) throws BarrioException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idbarrio, nombre, codpostal, localidad from barrio where localidad = ? order by localidad";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, localidad );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new BarrioException(sqle);
			}
			catch(Exception e){
					throw new BarrioException(e);
			}
			finally{}
	}

/**
* Returns all rows from barrio table 
*
* @param Connection con
* @return  Barrio[]
*
*/

	public Barrio[] findAll( Connection con) throws BarrioException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idbarrio, nombre, codpostal, localidad from barrio";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new BarrioException(sqle);
			}
			catch(Exception e){
					throw new BarrioException(e);
			}
			finally{}
	}

/**
* Returns rows from barrio table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Barrio[]
*
*/

	public Barrio[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws BarrioException{
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
					throw new BarrioException(sqle);
			}
			catch(Exception e){
					throw new BarrioException(e);
			}
			finally{}
	}

/**
* Returns rows from barrio table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Barrio[]
*
*/

	public Barrio[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws BarrioException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idbarrio, nombre, codpostal, localidad from barrio";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new BarrioException(sqle);
			}
			catch(Exception e){
					throw new BarrioException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Barrio
*
*/

	protected Barrio fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Barrio dto = new Barrio();
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
* @param Barrio dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Barrio dto, ResultSet rs) throws SQLException
	{
		 dto.setIdbarrio(rs.getLong("idbarrio"));
		 dto.setNombre(rs.getLong("nombre"));
		 dto.setCodpostal(rs.getLong("codpostal"));
		 dto.setLocalidad(rs.getLong("localidad"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Barrio[]
*/

	protected Barrio[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Barrio dto = new Barrio();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Barrio ret[] = new Barrio[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
