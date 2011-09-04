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
* Implementation of TipomaterialDAO interface 
* 
*/


public class TipomaterialDAOImpl implements TipomaterialDAO
{


/**
* Method deletes a record from table TIPOMATERIAL
* @param TipomaterialPK tipomaterialpk
* @param  Connection  con
* @return  int
*
*/


	public int delete(TipomaterialPKDB tipomaterialpk , Connection con)throws TipomaterialException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  TIPOMATERIAL where idtipomaterial = ?");
			ps.setLong(1, tipomaterialpk.getIdtipomaterial());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new TipomaterialException(sqle);}
		catch(Exception e) {throw new TipomaterialException(e);}
	}



/**
* This method updates a record in table TIPOMATERIAL
* @param TipomaterialPK
* @param Tipomaterial
* @param  Connection con
* @return   int
*/

	public int update(TipomaterialPKDB tipomaterialpk, TipomaterialDB tipomaterial, Connection con)throws TipomaterialException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update TIPOMATERIAL set NOMBRE = ? , DESCRIPCION = ?  where idtipomaterial = ?");
				ps.setString(1,tipomaterial.getNombre());
				ps.setString(2,tipomaterial.getDescripcion());
				ps.setLong(3,tipomaterialpk.getIdtipomaterial());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new TipomaterialException(sqle);}
		catch(Exception e){throw new TipomaterialException(e);}
	}

/**
* This method inserts data in table TIPOMATERIAL
*
* @param Tipomaterial tipomaterial
* @param   Connection con
* @return  TipomaterialPK
*/

	public int insert(TipomaterialDB tipomaterial ,Connection con)throws TipomaterialException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into TIPOMATERIAL( NOMBRE, DESCRIPCION) values (?, ?)");
				ps.setString(1,tipomaterial.getNombre());
				ps.setString(2,tipomaterial.getDescripcion());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new TipomaterialException(sqle);}
		catch(Exception e){throw new TipomaterialException(e);}
	}

/**
* 
* Returns a row from the Tipomaterial table for the primary key passed as parameter.
* 
*/

	public TipomaterialDB findByPrimaryKey(long idtipomaterial, Connection con) throws TipomaterialException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idtipomaterial, nombre, descripcion from tipomaterial where idtipomaterial = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idtipomaterial);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new TipomaterialException(sqle);
	  	}
	    catch(Exception e){throw new TipomaterialException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the Tipomaterial table for the primary key object passed as parameter.
* 
* @param  TipomaterialPK tipomaterialpk
* @param Connection con
* @return  Tipomaterial
*/

	public TipomaterialDB findByPrimaryKey(TipomaterialPKDB tipomaterialpk, Connection con) throws TipomaterialException{
		return findByPrimaryKey(tipomaterialpk.getIdtipomaterial(), con);
	}

/**
*
* Returns all rows from tipomaterial table where IDTIPOMATERIAL= idtipomaterial
*
* @param   long  idtipomaterial
* @param   Connection con
* @return  Tipomaterial[]
*/

	public TipomaterialDB[] findByIdtipomaterial(long idtipomaterial, Connection con) throws TipomaterialException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtipomaterial, nombre, descripcion from tipomaterial where idtipomaterial = ? order by idtipomaterial";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idtipomaterial );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TipomaterialException(sqle);
			}
			catch(Exception e){
					throw new TipomaterialException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from tipomaterial table where NOMBRE= nombre
*
* @param   String  nombre
* @param   Connection con
* @return  Tipomaterial[]
*/

	public TipomaterialDB[] findByNombre(String nombre, Connection con) throws TipomaterialException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtipomaterial, nombre, descripcion from tipomaterial where nombre = ? order by nombre";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, nombre );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TipomaterialException(sqle);
			}
			catch(Exception e){
					throw new TipomaterialException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from tipomaterial table where DESCRIPCION= descripcion
*
* @param   String  descripcion
* @param   Connection con
* @return  Tipomaterial[]
*/

	public TipomaterialDB[] findByDescripcion(String descripcion, Connection con) throws TipomaterialException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtipomaterial, nombre, descripcion from tipomaterial where descripcion = ? order by descripcion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, descripcion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TipomaterialException(sqle);
			}
			catch(Exception e){
					throw new TipomaterialException(e);
			}
			finally{}
	}

/**
* Returns all rows from Tipomaterial table 
*
* @param Connection con
* @return  Tipomaterial[]
*
*/

	public TipomaterialDB[] findAll( Connection con) throws TipomaterialException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtipomaterial, nombre, descripcion from tipomaterial";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TipomaterialException(sqle);
			}
			catch(Exception e){
					throw new TipomaterialException(e);
			}
			finally{}
	}

/**
* Returns rows from Tipomaterial table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Tipomaterial[]
*
*/

	public TipomaterialDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws TipomaterialException{
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
					throw new TipomaterialException(sqle);
			}
			catch(Exception e){
					throw new TipomaterialException(e);
			}
			finally{}
	}

/**
* Returns rows from Tipomaterial table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Tipomaterial[]
*
*/

	public TipomaterialDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws TipomaterialException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idtipomaterial, nombre, descripcion from tipomaterial";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TipomaterialException(sqle);
			}
			catch(Exception e){
					throw new TipomaterialException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Tipomaterial
*
*/

	protected TipomaterialDB fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					TipomaterialDB dto = new TipomaterialDB();
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
* @param Tipomaterial dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(TipomaterialDB dto, ResultSet rs) throws SQLException
	{
		 dto.setIdtipomaterial(rs.getLong("idtipomaterial"));
		 dto.setNombre(rs.getString("nombre"));
		 dto.setDescripcion(rs.getString("descripcion"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Tipomaterial[]
*/

	protected TipomaterialDB[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			TipomaterialDB dto = new TipomaterialDB();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		TipomaterialDB ret[] = new TipomaterialDB[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
