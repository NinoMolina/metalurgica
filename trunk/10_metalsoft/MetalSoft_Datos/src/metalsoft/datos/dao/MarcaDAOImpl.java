/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:09 ART 2010
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
* @param Marca
* @param  Connection con
* @return   int
*/

	public int update(MarcaPK marcapk, Marca marca, Connection con)throws MarcaException{
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
* @param Marca marca
* @param   Connection con
* @return  MarcaPK
*/

	public int insert(Marca marca ,Connection con)throws MarcaException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into MARCA( IDMARCA, NOMBRE, DESCRIPCION) values (?, ?, ?)");
				ps.setLong(1,marca.getIdmarca());
				ps.setString(2,marca.getNombre());
				ps.setString(3,marca.getDescripcion());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new MarcaException(sqle);}
		catch(Exception e){throw new MarcaException(e);}
	}

/**
* 
* Returns a row from the marca table for the primary key passed as parameter.
* 
*/

	public Marca findByPrimaryKey(long idmarca, Connection con) throws MarcaException{
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
* @return  Marca
*/

	public Marca findByPrimaryKey(MarcaPK marcapk, Connection con) throws MarcaException{
		return findByPrimaryKey(marcapk.getIdmarca(), con);
	}

/**
*
* Returns all rows from marca table where IDMARCA= idmarca
*
* @param   long  idmarca
* @param   Connection con
* @return  Marca[]
*/

	public Marca[] findByIdmarca(long idmarca, Connection con) throws MarcaException{
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
* @return  Marca[]
*/

	public Marca[] findByNombre(String nombre, Connection con) throws MarcaException{
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
* @return  Marca[]
*/

	public Marca[] findByDescripcion(String descripcion, Connection con) throws MarcaException{
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
* @return  Marca[]
*
*/

	public Marca[] findAll( Connection con) throws MarcaException{
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
* @return  Marca[]
*
*/

	public Marca[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws MarcaException{
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
* @return  Marca[]
*
*/

	public Marca[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws MarcaException{
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
* @return  Marca
*
*/

	protected Marca fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Marca dto = new Marca();
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
* @param Marca dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Marca dto, ResultSet rs) throws SQLException
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
* @return  Marca[]
*/

	protected Marca[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Marca dto = new Marca();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Marca ret[] = new Marca[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
