/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Sep 07 12:31:17 ART 2010
*
* DAO-Generator Version 1.2
*
*/

package metalsoft.datos.dao;
import java.math.*;
import java.sql.*;
import java.net.URL;
import java.util.Collection;
import java.util.ArrayList;
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


	public int delete(UnidadmedidaPKDB unidadmedidapk , Connection con)throws UnidadmedidaException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  UNIDADMEDIDA where idunidadmedida = ?");
			ps.setLong(1, unidadmedidapk.getIdunidadmedida());
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

	public int update(UnidadmedidaPKDB unidadmedidapk, UnidadmedidaDB unidadmedida, Connection con)throws UnidadmedidaException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update UNIDADMEDIDA set NOMBRE = ? , DESCRIPCION = ? , ENCM = ? , ENMM = ?  where idunidadmedida = ?");
				ps.setString(1,unidadmedida.getNombre());
				ps.setString(2,unidadmedida.getDescripcion());
				ps.setDouble(3,unidadmedida.getEncm());
				ps.setDouble(4,unidadmedida.getEnmm());
				ps.setLong(5,unidadmedidapk.getIdunidadmedida());

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

	public int insert(UnidadmedidaDB unidadmedida ,Connection con)throws UnidadmedidaException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into UNIDADMEDIDA( NOMBRE, DESCRIPCION, ENCM, ENMM) values (?, ?, ?, ?)");
				ps.setString(1,unidadmedida.getNombre());
				ps.setString(2,unidadmedida.getDescripcion());
				ps.setDouble(3,unidadmedida.getEncm());
				ps.setDouble(4,unidadmedida.getEnmm());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new UnidadmedidaException(sqle);}
		catch(Exception e){throw new UnidadmedidaException(e);}
	}

/**
* 
* Returns a row from the unidadmedida table for the primary key passed as parameter.
* 
*/

	public UnidadmedidaDB findByPrimaryKey(long idunidadmedida, Connection con) throws UnidadmedidaException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idunidadmedida, nombre, descripcion, encm, enmm from unidadmedida where idunidadmedida = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idunidadmedida);
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

	public UnidadmedidaDB findByPrimaryKey(UnidadmedidaPKDB unidadmedidapk, Connection con) throws UnidadmedidaException{
		return findByPrimaryKey(unidadmedidapk.getIdunidadmedida(), con);
	}

/**
*
* Returns all rows from unidadmedida table where IDUNIDADMEDIDA= idunidadmedida
*
* @param   long  idunidadmedida
* @param   Connection con
* @return  Unidadmedida[]
*/

	public UnidadmedidaDB[] findByIdunidadmedida(long idunidadmedida, Connection con) throws UnidadmedidaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idunidadmedida, nombre, descripcion, encm, enmm from unidadmedida where idunidadmedida = ? order by idunidadmedida";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idunidadmedida );
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

	public UnidadmedidaDB[] findByNombre(String nombre, Connection con) throws UnidadmedidaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idunidadmedida, nombre, descripcion, encm, enmm from unidadmedida where nombre = ? order by nombre";
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

	public UnidadmedidaDB[] findByDescripcion(String descripcion, Connection con) throws UnidadmedidaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idunidadmedida, nombre, descripcion, encm, enmm from unidadmedida where descripcion = ? order by descripcion";
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
*
* Returns all rows from unidadmedida table where ENCM= encm
*
* @param   double  encm
* @param   Connection con
* @return  Unidadmedida[]
*/

	public UnidadmedidaDB[] findByEncm(double encm, Connection con) throws UnidadmedidaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idunidadmedida, nombre, descripcion, encm, enmm from unidadmedida where encm = ? order by encm";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDouble( 1, encm );
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
* Returns all rows from unidadmedida table where ENMM= enmm
*
* @param   double  enmm
* @param   Connection con
* @return  Unidadmedida[]
*/

	public UnidadmedidaDB[] findByEnmm(double enmm, Connection con) throws UnidadmedidaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idunidadmedida, nombre, descripcion, encm, enmm from unidadmedida where enmm = ? order by enmm";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDouble( 1, enmm );
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

	public UnidadmedidaDB[] findAll( Connection con) throws UnidadmedidaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idunidadmedida, nombre, descripcion, encm, enmm from unidadmedida";
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

	public UnidadmedidaDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws UnidadmedidaException{
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

	public UnidadmedidaDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws UnidadmedidaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idunidadmedida, nombre, descripcion, encm, enmm from unidadmedida";
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

	protected UnidadmedidaDB fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					UnidadmedidaDB dto = new UnidadmedidaDB();
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

	protected void populateVO(UnidadmedidaDB dto, ResultSet rs) throws SQLException
	{
		 dto.setIdunidadmedida(rs.getLong("idunidadmedida"));
		 dto.setNombre(rs.getString("nombre"));
		 dto.setDescripcion(rs.getString("descripcion"));
		 dto.setEncm(rs.getDouble("encm"));
		 dto.setEnmm(rs.getDouble("enmm"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Unidadmedida[]
*/

	protected UnidadmedidaDB[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			UnidadmedidaDB dto = new UnidadmedidaDB();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		UnidadmedidaDB ret[] = new UnidadmedidaDB[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
