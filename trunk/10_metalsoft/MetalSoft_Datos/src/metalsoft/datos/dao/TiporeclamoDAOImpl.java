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
* Implementation of TiporeclamoDAO interface 
* 
*/


public class TiporeclamoDAOImpl implements TiporeclamoDAO
{


/**
* Method deletes a record from table TIPORECLAMO
* @param TiporeclamoPK tiporeclamopk
* @param  Connection  con
* @return  int
*
*/


	public int delete(TiporeclamoPK tiporeclamopk , Connection con)throws TiporeclamoException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  TIPORECLAMO where idtiporeclamo = ?");
			ps.setLong(1, tiporeclamopk.getIdtiporeclamo());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new TiporeclamoException(sqle);}
		catch(Exception e) {throw new TiporeclamoException(e);}
	}



/**
* This method updates a record in table TIPORECLAMO
* @param TiporeclamoPK
* @param Tiporeclamo
* @param  Connection con
* @return   int
*/

	public int update(TiporeclamoPK tiporeclamopk, Tiporeclamo tiporeclamo, Connection con)throws TiporeclamoException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update TIPORECLAMO set NOMBRE = ? , DESCRIPCION = ?  where idtiporeclamo = ?");
				ps.setString(1,tiporeclamo.getNombre());
				ps.setString(2,tiporeclamo.getDescripcion());
				ps.setLong(3,tiporeclamopk.getIdtiporeclamo());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new TiporeclamoException(sqle);}
		catch(Exception e){throw new TiporeclamoException(e);}
	}

/**
* This method inserts data in table TIPORECLAMO
*
* @param Tiporeclamo tiporeclamo
* @param   Connection con
* @return  TiporeclamoPK
*/

	public int insert(Tiporeclamo tiporeclamo ,Connection con)throws TiporeclamoException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into TIPORECLAMO( NOMBRE, DESCRIPCION) values (?, ?)");
				ps.setString(1,tiporeclamo.getNombre());
				ps.setString(2,tiporeclamo.getDescripcion());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new TiporeclamoException(sqle);}
		catch(Exception e){throw new TiporeclamoException(e);}
	}

/**
* 
* Returns a row from the Tiporeclamo table for the primary key passed as parameter.
* 
*/

	public Tiporeclamo findByPrimaryKey(long idtiporeclamo, Connection con) throws TiporeclamoException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idtiporeclamo, nombre, descripcion from tiporeclamo where idtiporeclamo = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idtiporeclamo);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new TiporeclamoException(sqle);
	  	}
	    catch(Exception e){throw new TiporeclamoException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the Tiporeclamo table for the primary key object passed as parameter.
* 
* @param  TiporeclamoPK tiporeclamopk
* @param Connection con
* @return  Tiporeclamo
*/

	public Tiporeclamo findByPrimaryKey(TiporeclamoPK tiporeclamopk, Connection con) throws TiporeclamoException{
		return findByPrimaryKey(tiporeclamopk.getIdtiporeclamo(), con);
	}

/**
*
* Returns all rows from tiporeclamo table where IDTIPORECLAMO= idtiporeclamo
*
* @param   long  idtiporeclamo
* @param   Connection con
* @return  Tiporeclamo[]
*/

	public Tiporeclamo[] findByIdtiporeclamo(long idtiporeclamo, Connection con) throws TiporeclamoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtiporeclamo, nombre, descripcion from tiporeclamo where idtiporeclamo = ? order by idtiporeclamo";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idtiporeclamo );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TiporeclamoException(sqle);
			}
			catch(Exception e){
					throw new TiporeclamoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from tiporeclamo table where NOMBRE= nombre
*
* @param   String  nombre
* @param   Connection con
* @return  Tiporeclamo[]
*/

	public Tiporeclamo[] findByNombre(String nombre, Connection con) throws TiporeclamoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtiporeclamo, nombre, descripcion from tiporeclamo where nombre = ? order by nombre";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, nombre );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TiporeclamoException(sqle);
			}
			catch(Exception e){
					throw new TiporeclamoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from tiporeclamo table where DESCRIPCION= descripcion
*
* @param   String  descripcion
* @param   Connection con
* @return  Tiporeclamo[]
*/

	public Tiporeclamo[] findByDescripcion(String descripcion, Connection con) throws TiporeclamoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtiporeclamo, nombre, descripcion from tiporeclamo where descripcion = ? order by descripcion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, descripcion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TiporeclamoException(sqle);
			}
			catch(Exception e){
					throw new TiporeclamoException(e);
			}
			finally{}
	}

/**
* Returns all rows from Tiporeclamo table 
*
* @param Connection con
* @return  Tiporeclamo[]
*
*/

	public Tiporeclamo[] findAll( Connection con) throws TiporeclamoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtiporeclamo, nombre, descripcion from tiporeclamo";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TiporeclamoException(sqle);
			}
			catch(Exception e){
					throw new TiporeclamoException(e);
			}
			finally{}
	}

/**
* Returns rows from Tiporeclamo table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Tiporeclamo[]
*
*/

	public Tiporeclamo[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws TiporeclamoException{
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
					throw new TiporeclamoException(sqle);
			}
			catch(Exception e){
					throw new TiporeclamoException(e);
			}
			finally{}
	}

/**
* Returns rows from Tiporeclamo table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Tiporeclamo[]
*
*/

	public Tiporeclamo[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws TiporeclamoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idtiporeclamo, nombre, descripcion from tiporeclamo";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TiporeclamoException(sqle);
			}
			catch(Exception e){
					throw new TiporeclamoException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Tiporeclamo
*
*/

	protected Tiporeclamo fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Tiporeclamo dto = new Tiporeclamo();
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
* @param Tiporeclamo dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Tiporeclamo dto, ResultSet rs) throws SQLException
	{
		 dto.setIdtiporeclamo(rs.getLong("idtiporeclamo"));
		 dto.setNombre(rs.getString("nombre"));
		 dto.setDescripcion(rs.getString("descripcion"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Tiporeclamo[]
*/

	protected Tiporeclamo[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Tiporeclamo dto = new Tiporeclamo();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Tiporeclamo ret[] = new Tiporeclamo[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
