/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Aug 24 11:31:46 ART 2010
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
* Implementation of UnidadmedidaDAO interface 
* 
*/


public class UnidadmedidaDAOImpl implements UnidadmedidaDAO
{


/**
* Method deletes a record from table UNIDADMEDIDA
* @param UnidadmedidaPK unidadmedidapk
* @param  Connection  con
* @return  int
*
*/


	public int delete(UnidadmedidaPK unidadmedidapk , Connection con)throws UnidadmedidaException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  UNIDADMEDIDA where idunidadmedida = ?");
			ps.setInt(1, unidadmedidapk.getIdunidadmedida());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new UnidadmedidaException(sqle);}
		catch(Exception e) {throw new UnidadmedidaException(e);}
	}



/**
* This method updates a record in table UNIDADMEDIDA
* @param UnidadmedidaPK
* @param Unidadmedida
* @param  Connection con
* @return   int
*/

	public int update(UnidadmedidaPK unidadmedidapk, Unidadmedida unidadmedida, Connection con)throws UnidadmedidaException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update UNIDADMEDIDA set NOMBRE = ? , DESCRIPCION = ?  where idunidadmedida = ?");
				ps.setString(1,unidadmedida.getNombre());
				ps.setString(2,unidadmedida.getDescripcion());
				ps.setInt(3,unidadmedidapk.getIdunidadmedida());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new UnidadmedidaException(sqle);}
		catch(Exception e){throw new UnidadmedidaException(e);}
	}

/**
* This method inserts data in table UNIDADMEDIDA
*
* @param Unidadmedida unidadmedida
* @param   Connection con
* @return  UnidadmedidaPK
*/

	public int insert(Unidadmedida unidadmedida ,Connection con)throws UnidadmedidaException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into UNIDADMEDIDA( NOMBRE, DESCRIPCION) values (?, ?)");
				ps.setString(1,unidadmedida.getNombre());
				ps.setString(2,unidadmedida.getDescripcion());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new UnidadmedidaException(sqle);}
		catch(Exception e){throw new UnidadmedidaException(e);}
	}

/**
* 
* Returns a row from the unidadmedida table for the primary key passed as parameter.
* 
*/

	public Unidadmedida findByPrimaryKey(int idunidadmedida, Connection con) throws UnidadmedidaException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idunidadmedida, nombre, descripcion from unidadmedida where idunidadmedida = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setInt(1, idunidadmedida);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new UnidadmedidaException(sqle);
	  	}
	    catch(Exception e){throw new UnidadmedidaException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the unidadmedida table for the primary key object passed as parameter.
* 
* @param  UnidadmedidaPK unidadmedidapk
* @param Connection con
* @return  Unidadmedida
*/

	public Unidadmedida findByPrimaryKey(UnidadmedidaPK unidadmedidapk, Connection con) throws UnidadmedidaException{
		return findByPrimaryKey(unidadmedidapk.getIdunidadmedida(), con);
	}

/**
*
* Returns all rows from unidadmedida table where IDUNIDADMEDIDA= idunidadmedida
*
* @param   int  idunidadmedida
* @param   Connection con
* @return  Unidadmedida[]
*/

	public Unidadmedida[] findByIdunidadmedida(int idunidadmedida, Connection con) throws UnidadmedidaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idunidadmedida, nombre, descripcion from unidadmedida where idunidadmedida = ? order by idunidadmedida";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setInt( 1, idunidadmedida );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new UnidadmedidaException(sqle);
			}
			catch(Exception e){
					throw new UnidadmedidaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from unidadmedida table where NOMBRE= nombre
*
* @param   String  nombre
* @param   Connection con
* @return  Unidadmedida[]
*/

	public Unidadmedida[] findByNombre(String nombre, Connection con) throws UnidadmedidaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idunidadmedida, nombre, descripcion from unidadmedida where nombre = ? order by nombre";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, nombre );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new UnidadmedidaException(sqle);
			}
			catch(Exception e){
					throw new UnidadmedidaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from unidadmedida table where DESCRIPCION= descripcion
*
* @param   String  descripcion
* @param   Connection con
* @return  Unidadmedida[]
*/

	public Unidadmedida[] findByDescripcion(String descripcion, Connection con) throws UnidadmedidaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idunidadmedida, nombre, descripcion from unidadmedida where descripcion = ? order by descripcion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, descripcion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new UnidadmedidaException(sqle);
			}
			catch(Exception e){
					throw new UnidadmedidaException(e);
			}
			finally{}
	}

/**
* Returns all rows from unidadmedida table 
*
* @param Connection con
* @return  Unidadmedida[]
*
*/

	public Unidadmedida[] findAll( Connection con) throws UnidadmedidaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idunidadmedida, nombre, descripcion from unidadmedida";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new UnidadmedidaException(sqle);
			}
			catch(Exception e){
					throw new UnidadmedidaException(e);
			}
			finally{}
	}

/**
* Returns rows from unidadmedida table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Unidadmedida[]
*
*/

	public Unidadmedida[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws UnidadmedidaException{
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
					throw new UnidadmedidaException(sqle);
			}
			catch(Exception e){
					throw new UnidadmedidaException(e);
			}
			finally{}
	}

/**
* Returns rows from unidadmedida table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Unidadmedida[]
*
*/

	public Unidadmedida[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws UnidadmedidaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idunidadmedida, nombre, descripcion from unidadmedida";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new UnidadmedidaException(sqle);
			}
			catch(Exception e){
					throw new UnidadmedidaException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Unidadmedida
*
*/

	protected Unidadmedida fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Unidadmedida dto = new Unidadmedida();
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
* @param Unidadmedida dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Unidadmedida dto, ResultSet rs) throws SQLException
	{
		 dto.setIdunidadmedida(rs.getInt("idunidadmedida"));
		 dto.setNombre(rs.getString("nombre"));
		 dto.setDescripcion(rs.getString("descripcion"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Unidadmedida[]
*/

	protected Unidadmedida[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Unidadmedida dto = new Unidadmedida();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Unidadmedida ret[] = new Unidadmedida[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
