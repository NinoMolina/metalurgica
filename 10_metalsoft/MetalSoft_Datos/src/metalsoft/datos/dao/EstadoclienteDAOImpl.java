/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:08 ART 2010
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
* Implementation of EstadoclienteDAO interface 
* 
*/


public class EstadoclienteDAOImpl implements EstadoclienteDAO
{


/**
* Method deletes a record from table ESTADOCLIENTE
* @param EstadoclientePK estadoclientepk
* @param  Connection  con
* @return  int
*
*/


	public int delete(EstadoclientePK estadoclientepk , Connection con)throws EstadoclienteException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  ESTADOCLIENTE where idestado = ?");
			ps.setLong(1, estadoclientepk.getIdestado());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new EstadoclienteException(sqle);}
		catch(Exception e) {throw new EstadoclienteException(e);}
	}



/**
* This method updates a record in table ESTADOCLIENTE
* @param EstadoclientePK
* @param Estadocliente
* @param  Connection con
* @return   int
*/

	public int update(EstadoclientePK estadoclientepk, Estadocliente estadocliente, Connection con)throws EstadoclienteException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update ESTADOCLIENTE set NOMBRE = ? , DESCRIPCION = ?  where idestado = ?");
				ps.setString(1,estadocliente.getNombre());
				ps.setString(2,estadocliente.getDescripcion());
				ps.setLong(3,estadoclientepk.getIdestado());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new EstadoclienteException(sqle);}
		catch(Exception e){throw new EstadoclienteException(e);}
	}

/**
* This method inserts data in table ESTADOCLIENTE
*
* @param Estadocliente estadocliente
* @param   Connection con
* @return  EstadoclientePK
*/

	public int insert(Estadocliente estadocliente ,Connection con)throws EstadoclienteException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into ESTADOCLIENTE( IDESTADO, NOMBRE, DESCRIPCION) values (?, ?, ?)");
				ps.setLong(1,estadocliente.getIdestado());
				ps.setString(2,estadocliente.getNombre());
				ps.setString(3,estadocliente.getDescripcion());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new EstadoclienteException(sqle);}
		catch(Exception e){throw new EstadoclienteException(e);}
	}

/**
* 
* Returns a row from the estadocliente table for the primary key passed as parameter.
* 
*/

	public Estadocliente findByPrimaryKey(long idestado, Connection con) throws EstadoclienteException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idestado, nombre, descripcion from estadocliente where idestado = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idestado);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new EstadoclienteException(sqle);
	  	}
	    catch(Exception e){throw new EstadoclienteException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the estadocliente table for the primary key object passed as parameter.
* 
* @param  EstadoclientePK estadoclientepk
* @param Connection con
* @return  Estadocliente
*/

	public Estadocliente findByPrimaryKey(EstadoclientePK estadoclientepk, Connection con) throws EstadoclienteException{
		return findByPrimaryKey(estadoclientepk.getIdestado(), con);
	}

/**
*
* Returns all rows from estadocliente table where IDESTADO= idestado
*
* @param   long  idestado
* @param   Connection con
* @return  Estadocliente[]
*/

	public Estadocliente[] findByIdestado(long idestado, Connection con) throws EstadoclienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idestado, nombre, descripcion from estadocliente where idestado = ? order by idestado";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idestado );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadoclienteException(sqle);
			}
			catch(Exception e){
					throw new EstadoclienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from estadocliente table where NOMBRE= nombre
*
* @param   String  nombre
* @param   Connection con
* @return  Estadocliente[]
*/

	public Estadocliente[] findByNombre(String nombre, Connection con) throws EstadoclienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idestado, nombre, descripcion from estadocliente where nombre = ? order by nombre";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, nombre );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadoclienteException(sqle);
			}
			catch(Exception e){
					throw new EstadoclienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from estadocliente table where DESCRIPCION= descripcion
*
* @param   String  descripcion
* @param   Connection con
* @return  Estadocliente[]
*/

	public Estadocliente[] findByDescripcion(String descripcion, Connection con) throws EstadoclienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idestado, nombre, descripcion from estadocliente where descripcion = ? order by descripcion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, descripcion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadoclienteException(sqle);
			}
			catch(Exception e){
					throw new EstadoclienteException(e);
			}
			finally{}
	}

/**
* Returns all rows from estadocliente table 
*
* @param Connection con
* @return  Estadocliente[]
*
*/

	public Estadocliente[] findAll( Connection con) throws EstadoclienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idestado, nombre, descripcion from estadocliente";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadoclienteException(sqle);
			}
			catch(Exception e){
					throw new EstadoclienteException(e);
			}
			finally{}
	}

/**
* Returns rows from estadocliente table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Estadocliente[]
*
*/

	public Estadocliente[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws EstadoclienteException{
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
					throw new EstadoclienteException(sqle);
			}
			catch(Exception e){
					throw new EstadoclienteException(e);
			}
			finally{}
	}

/**
* Returns rows from estadocliente table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Estadocliente[]
*
*/

	public Estadocliente[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws EstadoclienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idestado, nombre, descripcion from estadocliente";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadoclienteException(sqle);
			}
			catch(Exception e){
					throw new EstadoclienteException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Estadocliente
*
*/

	protected Estadocliente fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Estadocliente dto = new Estadocliente();
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
* @param Estadocliente dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Estadocliente dto, ResultSet rs) throws SQLException
	{
		 dto.setIdestado(rs.getLong("idestado"));
		 dto.setNombre(rs.getString("nombre"));
		 dto.setDescripcion(rs.getString("descripcion"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Estadocliente[]
*/

	protected Estadocliente[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Estadocliente dto = new Estadocliente();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Estadocliente ret[] = new Estadocliente[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
