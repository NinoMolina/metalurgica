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
* Implementation of TipoivaDAO interface 
* 
*/


public class TipoivaDAOImpl implements TipoivaDAO
{


/**
* Method deletes a record from table TIPOIVA
* @param TipoivaPK tipoivapk
* @param  Connection  con
* @return  int
*
*/


	public int delete(TipoivaPK tipoivapk , Connection con)throws TipoivaException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  TIPOIVA where idtipoiva = ?");
			ps.setLong(1, tipoivapk.getIdtipoiva());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new TipoivaException(sqle);}
		catch(Exception e) {throw new TipoivaException(e);}
	}



/**
* This method updates a record in table TIPOIVA
* @param TipoivaPK
* @param Tipoiva
* @param  Connection con
* @return   int
*/

	public int update(TipoivaPK tipoivapk, Tipoiva tipoiva, Connection con)throws TipoivaException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update TIPOIVA set NOMBRE = ? , DESCRIPCION = ?  where idtipoiva = ?");
				ps.setString(1,tipoiva.getNombre());
				ps.setString(2,tipoiva.getDescripcion());
				ps.setLong(3,tipoivapk.getIdtipoiva());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new TipoivaException(sqle);}
		catch(Exception e){throw new TipoivaException(e);}
	}

/**
* This method inserts data in table TIPOIVA
*
* @param Tipoiva tipoiva
* @param   Connection con
* @return  TipoivaPK
*/

	public int insert(Tipoiva tipoiva ,Connection con)throws TipoivaException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into TIPOIVA( IDTIPOIVA, NOMBRE, DESCRIPCION) values (?, ?, ?)");
				ps.setLong(1,tipoiva.getIdtipoiva());
				ps.setString(2,tipoiva.getNombre());
				ps.setString(3,tipoiva.getDescripcion());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new TipoivaException(sqle);}
		catch(Exception e){throw new TipoivaException(e);}
	}

/**
* 
* Returns a row from the tipoiva table for the primary key passed as parameter.
* 
*/

	public Tipoiva findByPrimaryKey(long idtipoiva, Connection con) throws TipoivaException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idtipoiva, nombre, descripcion from tipoiva where idtipoiva = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idtipoiva);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new TipoivaException(sqle);
	  	}
	    catch(Exception e){throw new TipoivaException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the tipoiva table for the primary key object passed as parameter.
* 
* @param  TipoivaPK tipoivapk
* @param Connection con
* @return  Tipoiva
*/

	public Tipoiva findByPrimaryKey(TipoivaPK tipoivapk, Connection con) throws TipoivaException{
		return findByPrimaryKey(tipoivapk.getIdtipoiva(), con);
	}

/**
*
* Returns all rows from tipoiva table where IDTIPOIVA= idtipoiva
*
* @param   long  idtipoiva
* @param   Connection con
* @return  Tipoiva[]
*/

	public Tipoiva[] findByIdtipoiva(long idtipoiva, Connection con) throws TipoivaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtipoiva, nombre, descripcion from tipoiva where idtipoiva = ? order by idtipoiva";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idtipoiva );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TipoivaException(sqle);
			}
			catch(Exception e){
					throw new TipoivaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from tipoiva table where NOMBRE= nombre
*
* @param   String  nombre
* @param   Connection con
* @return  Tipoiva[]
*/

	public Tipoiva[] findByNombre(String nombre, Connection con) throws TipoivaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtipoiva, nombre, descripcion from tipoiva where nombre = ? order by nombre";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, nombre );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TipoivaException(sqle);
			}
			catch(Exception e){
					throw new TipoivaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from tipoiva table where DESCRIPCION= descripcion
*
* @param   String  descripcion
* @param   Connection con
* @return  Tipoiva[]
*/

	public Tipoiva[] findByDescripcion(String descripcion, Connection con) throws TipoivaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtipoiva, nombre, descripcion from tipoiva where descripcion = ? order by descripcion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, descripcion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TipoivaException(sqle);
			}
			catch(Exception e){
					throw new TipoivaException(e);
			}
			finally{}
	}

/**
* Returns all rows from tipoiva table 
*
* @param Connection con
* @return  Tipoiva[]
*
*/

	public Tipoiva[] findAll( Connection con) throws TipoivaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtipoiva, nombre, descripcion from tipoiva";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TipoivaException(sqle);
			}
			catch(Exception e){
					throw new TipoivaException(e);
			}
			finally{}
	}

/**
* Returns rows from tipoiva table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Tipoiva[]
*
*/

	public Tipoiva[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws TipoivaException{
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
					throw new TipoivaException(sqle);
			}
			catch(Exception e){
					throw new TipoivaException(e);
			}
			finally{}
	}

/**
* Returns rows from tipoiva table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Tipoiva[]
*
*/

	public Tipoiva[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws TipoivaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idtipoiva, nombre, descripcion from tipoiva";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TipoivaException(sqle);
			}
			catch(Exception e){
					throw new TipoivaException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Tipoiva
*
*/

	protected Tipoiva fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Tipoiva dto = new Tipoiva();
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
* @param Tipoiva dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Tipoiva dto, ResultSet rs) throws SQLException
	{
		 dto.setIdtipoiva(rs.getLong("idtipoiva"));
		 dto.setNombre(rs.getString("nombre"));
		 dto.setDescripcion(rs.getString("descripcion"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Tipoiva[]
*/

	protected Tipoiva[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Tipoiva dto = new Tipoiva();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Tipoiva ret[] = new Tipoiva[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
