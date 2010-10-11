/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:03 ART 2010
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
* Implementation of MarcaDAO interface 
* 
*/


public class MarcaDAOImpl implements MarcaDAO
{


/**
* Method deletes a record from table MARCA
* @param MarcaPK marcapk
* @param  Connection  con
* @return  int
*
*/


	public int delete(MarcaPK marcapk , Connection con)throws MarcaException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  MARCA where idmarca = ?");
			ps.setLong(1, marcapk.getIdmarca());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new MarcaException(sqle);}
		catch(Exception e) {throw new MarcaException(e);}
	}



/**
* This method updates a record in table MARCA
* @param MarcaPK
* @param MarcaDB
* @param  Connection con
* @return   int
*/

	public int update(MarcaPK marcapk, MarcaDB marca, Connection con)throws MarcaException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update MARCA set NOMBRE = ? , DESCRIPCION = ?  where idmarca = ?");
				ps.setString(1,marca.getNombre());
				ps.setString(2,marca.getDescripcion());
				ps.setLong(3,marcapk.getIdmarca());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new MarcaException(sqle);}
		catch(Exception e){throw new MarcaException(e);}
	}

/**
* This method inserts data in table MARCA
*
* @param MarcaDB marca
* @param   Connection con
* @return  MarcaPK
*/

	public int insert(MarcaDB marca ,Connection con)throws MarcaException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into MARCA( NOMBRE, DESCRIPCION) values (?, ?)");
				ps.setString(1,marca.getNombre());
				ps.setString(2,marca.getDescripcion());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new MarcaException(sqle);}
		catch(Exception e){throw new MarcaException(e);}
	}

/**
* 
* Returns a row from the marca table for the primary key passed as parameter.
* 
*/

	public MarcaDB findByPrimaryKey(long idmarca, Connection con) throws MarcaException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idmarca, nombre, descripcion from marca where idmarca = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idmarca);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new MarcaException(sqle);
	  	}
	    catch(Exception e){throw new MarcaException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the marca table for the primary key object passed as parameter.
* 
* @param  MarcaPK marcapk
* @param Connection con
* @return  MarcaDB
*/

	public MarcaDB findByPrimaryKey(MarcaPK marcapk, Connection con) throws MarcaException{
		return findByPrimaryKey(marcapk.getIdmarca(), con);
	}

/**
*
* Returns all rows from marca table where IDMARCA= idmarca
*
* @param   long  idmarca
* @param   Connection con
* @return  MarcaDB[]
*/

	public MarcaDB[] findByIdmarca(long idmarca, Connection con) throws MarcaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmarca, nombre, descripcion from marca where idmarca = ? order by idmarca";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idmarca );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MarcaException(sqle);
			}
			catch(Exception e){
					throw new MarcaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from marca table where NOMBRE= nombre
*
* @param   String  nombre
* @param   Connection con
* @return  MarcaDB[]
*/

	public MarcaDB[] findByNombre(String nombre, Connection con) throws MarcaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmarca, nombre, descripcion from marca where nombre = ? order by nombre";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, nombre );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MarcaException(sqle);
			}
			catch(Exception e){
					throw new MarcaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from marca table where DESCRIPCION= descripcion
*
* @param   String  descripcion
* @param   Connection con
* @return  MarcaDB[]
*/

	public MarcaDB[] findByDescripcion(String descripcion, Connection con) throws MarcaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmarca, nombre, descripcion from marca where descripcion = ? order by descripcion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, descripcion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MarcaException(sqle);
			}
			catch(Exception e){
					throw new MarcaException(e);
			}
			finally{}
	}

/**
* Returns all rows from marca table 
*
* @param Connection con
* @return  MarcaDB[]
*
*/

	public MarcaDB[] findAll( Connection con) throws MarcaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmarca, nombre, descripcion from marca";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MarcaException(sqle);
			}
			catch(Exception e){
					throw new MarcaException(e);
			}
			finally{}
	}

/**
* Returns rows from marca table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  MarcaDB[]
*
*/

	public MarcaDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws MarcaException{
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
					throw new MarcaException(sqle);
			}
			catch(Exception e){
					throw new MarcaException(e);
			}
			finally{}
	}

/**
* Returns rows from marca table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  MarcaDB[]
*
*/

	public MarcaDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws MarcaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idmarca, nombre, descripcion from marca";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MarcaException(sqle);
			}
			catch(Exception e){
					throw new MarcaException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  MarcaDB
*
*/

	protected MarcaDB fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					MarcaDB dto = new MarcaDB();
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
* @param MarcaDB dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(MarcaDB dto, ResultSet rs) throws SQLException
	{
		 dto.setIdmarca(rs.getLong("idmarca"));
		 dto.setNombre(rs.getString("nombre"));
		 dto.setDescripcion(rs.getString("descripcion"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  MarcaDB[]
*/

	protected MarcaDB[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			MarcaDB dto = new MarcaDB();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		MarcaDB ret[] = new MarcaDB[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
