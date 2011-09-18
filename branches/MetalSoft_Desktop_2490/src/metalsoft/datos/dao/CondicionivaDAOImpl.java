/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:01 ART 2010
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
* Implementation of CondicionivaDAO interface 
* 
*/


public class CondicionivaDAOImpl implements CondicionivaDAO
{


/**
* Method deletes a record from table CONDICIONIVA
* @param CondicionivaPK condicionivapk
* @param  Connection  con
* @return  int
*
*/


	public int delete(CondicionivaPKDB condicionivapk , Connection con)throws CondicionivaException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  CONDICIONIVA where idcondicioniva = ?");
			ps.setLong(1, condicionivapk.getIdcondicioniva());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new CondicionivaException(sqle);}
		catch(Exception e) {throw new CondicionivaException(e);}
	}



/**
* This method updates a record in table CONDICIONIVA
* @param CondicionivaPK
* @param Condicioniva
* @param  Connection con
* @return   int
*/

	public int update(CondicionivaPKDB condicionivapk, CondicionivaDB condicioniva, Connection con)throws CondicionivaException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update CONDICIONIVA set NOMBRE = ? , DESCRIPCION = ?  where idcondicioniva = ?");
				ps.setString(1,condicioniva.getNombre());
				ps.setString(2,condicioniva.getDescripcion());
				ps.setLong(3,condicionivapk.getIdcondicioniva());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new CondicionivaException(sqle);}
		catch(Exception e){throw new CondicionivaException(e);}
	}

/**
* This method inserts data in table CONDICIONIVA
*
* @param Condicioniva condicioniva
* @param   Connection con
* @return  CondicionivaPK
*/

	public int insert(CondicionivaDB condicioniva ,Connection con)throws CondicionivaException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into CONDICIONIVA( NOMBRE, DESCRIPCION) values (?, ?)");
				ps.setString(1,condicioniva.getNombre());
				ps.setString(2,condicioniva.getDescripcion());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new CondicionivaException(sqle);}
		catch(Exception e){throw new CondicionivaException(e);}
	}

/**
* 
* Returns a row from the condicioniva table for the primary key passed as parameter.
* 
*/

	public CondicionivaDB findByPrimaryKey(long idcondicioniva, Connection con) throws CondicionivaException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idcondicioniva, nombre, descripcion from condicioniva where idcondicioniva = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idcondicioniva);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new CondicionivaException(sqle);
	  	}
	    catch(Exception e){throw new CondicionivaException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the condicioniva table for the primary key object passed as parameter.
* 
* @param  CondicionivaPK condicionivapk
* @param Connection con
* @return  Condicioniva
*/

	public CondicionivaDB findByPrimaryKey(CondicionivaPKDB condicionivapk, Connection con) throws CondicionivaException{
		return findByPrimaryKey(condicionivapk.getIdcondicioniva(), con);
	}

/**
*
* Returns all rows from condicioniva table where IDCONDICIONIVA= idcondicioniva
*
* @param   long  idcondicioniva
* @param   Connection con
* @return  Condicioniva[]
*/

	public CondicionivaDB[] findByIdcondicioniva(long idcondicioniva, Connection con) throws CondicionivaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcondicioniva, nombre, descripcion from condicioniva where idcondicioniva = ? order by idcondicioniva";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idcondicioniva );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new CondicionivaException(sqle);
			}
			catch(Exception e){
					throw new CondicionivaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from condicioniva table where NOMBRE= nombre
*
* @param   String  nombre
* @param   Connection con
* @return  Condicioniva[]
*/

	public CondicionivaDB[] findByNombre(String nombre, Connection con) throws CondicionivaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcondicioniva, nombre, descripcion from condicioniva where nombre = ? order by nombre";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, nombre );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new CondicionivaException(sqle);
			}
			catch(Exception e){
					throw new CondicionivaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from condicioniva table where DESCRIPCION= descripcion
*
* @param   String  descripcion
* @param   Connection con
* @return  Condicioniva[]
*/

	public CondicionivaDB[] findByDescripcion(String descripcion, Connection con) throws CondicionivaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcondicioniva, nombre, descripcion from condicioniva where descripcion = ? order by descripcion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, descripcion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new CondicionivaException(sqle);
			}
			catch(Exception e){
					throw new CondicionivaException(e);
			}
			finally{}
	}

/**
* Returns all rows from condicioniva table 
*
* @param Connection con
* @return  Condicioniva[]
*
*/

	public CondicionivaDB[] findAll( Connection con) throws CondicionivaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcondicioniva, nombre, descripcion from condicioniva";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new CondicionivaException(sqle);
			}
			catch(Exception e){
					throw new CondicionivaException(e);
			}
			finally{}
	}

/**
* Returns rows from condicioniva table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Condicioniva[]
*
*/

	public CondicionivaDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws CondicionivaException{
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
					throw new CondicionivaException(sqle);
			}
			catch(Exception e){
					throw new CondicionivaException(e);
			}
			finally{}
	}

/**
* Returns rows from condicioniva table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Condicioniva[]
*
*/

	public CondicionivaDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws CondicionivaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idcondicioniva, nombre, descripcion from condicioniva";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new CondicionivaException(sqle);
			}
			catch(Exception e){
					throw new CondicionivaException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Condicioniva
*
*/

	protected CondicionivaDB fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					CondicionivaDB dto = new CondicionivaDB();
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
* @param Condicioniva dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(CondicionivaDB dto, ResultSet rs) throws SQLException
	{
		 dto.setIdcondicioniva(rs.getLong("idcondicioniva"));
		 dto.setNombre(rs.getString("nombre"));
		 dto.setDescripcion(rs.getString("descripcion"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Condicioniva[]
*/

	protected CondicionivaDB[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			CondicionivaDB dto = new CondicionivaDB();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		CondicionivaDB ret[] = new CondicionivaDB[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}