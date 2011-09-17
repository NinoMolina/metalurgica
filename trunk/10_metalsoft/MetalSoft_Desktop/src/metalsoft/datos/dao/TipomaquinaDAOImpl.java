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
* Implementation of TipomaquinaDAO interface 
* 
*/


public class TipomaquinaDAOImpl implements TipomaquinaDAO
{


/**
* Method deletes a record from table TIPOMAQUINA
* @param TipomaquinaPK tipomaquinapk
* @param  Connection  con
* @return  int
*
*/


	public int delete(TipomaquinaPKDB tipomaquinapk , Connection con)throws TipomaquinaException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  TIPOMAQUINA where idtipomaquina = ?");
			ps.setLong(1, tipomaquinapk.getIdtipomaquina());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new TipomaquinaException(sqle);}
		catch(Exception e) {throw new TipomaquinaException(e);}
	}



/**
* This method updates a record in table TIPOMAQUINA
* @param TipomaquinaPK
* @param Tipomaquina
* @param  Connection con
* @return   int
*/

	public int update(TipomaquinaPKDB tipomaquinapk, TipomaquinaDB tipomaquina, Connection con)throws TipomaquinaException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update TIPOMAQUINA set NOMBRE = ? , DESCRIPCION = ?  where idtipomaquina = ?");
				ps.setString(1,tipomaquina.getNombre());
				ps.setString(2,tipomaquina.getDescripcion());
				ps.setLong(3,tipomaquinapk.getIdtipomaquina());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new TipomaquinaException(sqle);}
		catch(Exception e){throw new TipomaquinaException(e);}
	}

/**
* This method inserts data in table TIPOMAQUINA
*
* @param Tipomaquina tipomaquina
* @param   Connection con
* @return  TipomaquinaPK
*/

	public int insert(TipomaquinaDB tipomaquina ,Connection con)throws TipomaquinaException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into TIPOMAQUINA( NOMBRE, DESCRIPCION) values (?, ?)");
				ps.setString(1,tipomaquina.getNombre());
				ps.setString(2,tipomaquina.getDescripcion());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new TipomaquinaException(sqle);}
		catch(Exception e){throw new TipomaquinaException(e);}
	}

/**
* 
* Returns a row from the tipomaquina table for the primary key passed as parameter.
* 
*/

	public TipomaquinaDB findByPrimaryKey(long idtipomaquina, Connection con) throws TipomaquinaException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idtipomaquina, nombre, descripcion from tipomaquina where idtipomaquina = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idtipomaquina);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new TipomaquinaException(sqle);
	  	}
	    catch(Exception e){throw new TipomaquinaException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the tipomaquina table for the primary key object passed as parameter.
* 
* @param  TipomaquinaPK tipomaquinapk
* @param Connection con
* @return  Tipomaquina
*/

	public TipomaquinaDB findByPrimaryKey(TipomaquinaPKDB tipomaquinapk, Connection con) throws TipomaquinaException{
		return findByPrimaryKey(tipomaquinapk.getIdtipomaquina(), con);
	}

/**
*
* Returns all rows from tipomaquina table where IDTIPOMAQUINA= idtipomaquina
*
* @param   long  idtipomaquina
* @param   Connection con
* @return  Tipomaquina[]
*/

	public TipomaquinaDB[] findByIdtipomaquina(long idtipomaquina, Connection con) throws TipomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtipomaquina, nombre, descripcion from tipomaquina where idtipomaquina = ? order by idtipomaquina";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idtipomaquina );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TipomaquinaException(sqle);
			}
			catch(Exception e){
					throw new TipomaquinaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from tipomaquina table where NOMBRE= nombre
*
* @param   String  nombre
* @param   Connection con
* @return  Tipomaquina[]
*/

	public TipomaquinaDB[] findByNombre(String nombre, Connection con) throws TipomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtipomaquina, nombre, descripcion from tipomaquina where nombre = ? order by nombre";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, nombre );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TipomaquinaException(sqle);
			}
			catch(Exception e){
					throw new TipomaquinaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from tipomaquina table where DESCRIPCION= descripcion
*
* @param   String  descripcion
* @param   Connection con
* @return  Tipomaquina[]
*/

	public TipomaquinaDB[] findByDescripcion(String descripcion, Connection con) throws TipomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtipomaquina, nombre, descripcion from tipomaquina where descripcion = ? order by descripcion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, descripcion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TipomaquinaException(sqle);
			}
			catch(Exception e){
					throw new TipomaquinaException(e);
			}
			finally{}
	}

/**
* Returns all rows from tipomaquina table 
*
* @param Connection con
* @return  Tipomaquina[]
*
*/

	public TipomaquinaDB[] findAll( Connection con) throws TipomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtipomaquina, nombre, descripcion from tipomaquina";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TipomaquinaException(sqle);
			}
			catch(Exception e){
					throw new TipomaquinaException(e);
			}
			finally{}
	}

/**
* Returns rows from tipomaquina table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Tipomaquina[]
*
*/

	public TipomaquinaDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws TipomaquinaException{
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
					throw new TipomaquinaException(sqle);
			}
			catch(Exception e){
					throw new TipomaquinaException(e);
			}
			finally{}
	}

/**
* Returns rows from tipomaquina table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Tipomaquina[]
*
*/

	public TipomaquinaDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws TipomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idtipomaquina, nombre, descripcion from tipomaquina";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TipomaquinaException(sqle);
			}
			catch(Exception e){
					throw new TipomaquinaException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Tipomaquina
*
*/

	protected TipomaquinaDB fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					TipomaquinaDB dto = new TipomaquinaDB();
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
* @param Tipomaquina dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(TipomaquinaDB dto, ResultSet rs) throws SQLException
	{
		 dto.setIdtipomaquina(rs.getLong("idtipomaquina"));
		 dto.setNombre(rs.getString("nombre"));
		 dto.setDescripcion(rs.getString("descripcion"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Tipomaquina[]
*/

	protected TipomaquinaDB[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			TipomaquinaDB dto = new TipomaquinaDB();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		TipomaquinaDB ret[] = new TipomaquinaDB[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
