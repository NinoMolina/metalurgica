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
* Implementation of EstadomaquinaDAO interface 
* 
*/


public class EstadomaquinaDAOImpl implements EstadomaquinaDAO
{


/**
* Method deletes a record from table ESTADOMAQUINA
* @param EstadomaquinaPK estadomaquinapk
* @param  Connection  con
* @return  int
*
*/


	public int delete(EstadomaquinaPK estadomaquinapk , Connection con)throws EstadomaquinaException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  ESTADOMAQUINA where idestado = ?");
			ps.setLong(1, estadomaquinapk.getIdestado());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new EstadomaquinaException(sqle);}
		catch(Exception e) {throw new EstadomaquinaException(e);}
	}



/**
* This method updates a record in table ESTADOMAQUINA
* @param EstadomaquinaPK
* @param Estadomaquina
* @param  Connection con
* @return   int
*/

	public int update(EstadomaquinaPK estadomaquinapk, Estadomaquina estadomaquina, Connection con)throws EstadomaquinaException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update ESTADOMAQUINA set NOMBRE = ? , DESCRIPCION = ?  where idestado = ?");
				ps.setString(1,estadomaquina.getNombre());
				ps.setString(2,estadomaquina.getDescripcion());
				ps.setLong(3,estadomaquinapk.getIdestado());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new EstadomaquinaException(sqle);}
		catch(Exception e){throw new EstadomaquinaException(e);}
	}

/**
* This method inserts data in table ESTADOMAQUINA
*
* @param Estadomaquina estadomaquina
* @param   Connection con
* @return  EstadomaquinaPK
*/

	public int insert(Estadomaquina estadomaquina ,Connection con)throws EstadomaquinaException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into ESTADOMAQUINA( IDESTADO, NOMBRE, DESCRIPCION) values (?, ?, ?)");
				ps.setLong(1,estadomaquina.getIdestado());
				ps.setString(2,estadomaquina.getNombre());
				ps.setString(3,estadomaquina.getDescripcion());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new EstadomaquinaException(sqle);}
		catch(Exception e){throw new EstadomaquinaException(e);}
	}

/**
* 
* Returns a row from the estadomaquina table for the primary key passed as parameter.
* 
*/

	public Estadomaquina findByPrimaryKey(long idestado, Connection con) throws EstadomaquinaException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idestado, nombre, descripcion from estadomaquina where idestado = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idestado);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new EstadomaquinaException(sqle);
	  	}
	    catch(Exception e){throw new EstadomaquinaException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the estadomaquina table for the primary key object passed as parameter.
* 
* @param  EstadomaquinaPK estadomaquinapk
* @param Connection con
* @return  Estadomaquina
*/

	public Estadomaquina findByPrimaryKey(EstadomaquinaPK estadomaquinapk, Connection con) throws EstadomaquinaException{
		return findByPrimaryKey(estadomaquinapk.getIdestado(), con);
	}

/**
*
* Returns all rows from estadomaquina table where IDESTADO= idestado
*
* @param   long  idestado
* @param   Connection con
* @return  Estadomaquina[]
*/

	public Estadomaquina[] findByIdestado(long idestado, Connection con) throws EstadomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idestado, nombre, descripcion from estadomaquina where idestado = ? order by idestado";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idestado );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadomaquinaException(sqle);
			}
			catch(Exception e){
					throw new EstadomaquinaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from estadomaquina table where NOMBRE= nombre
*
* @param   String  nombre
* @param   Connection con
* @return  Estadomaquina[]
*/

	public Estadomaquina[] findByNombre(String nombre, Connection con) throws EstadomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idestado, nombre, descripcion from estadomaquina where nombre = ? order by nombre";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, nombre );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadomaquinaException(sqle);
			}
			catch(Exception e){
					throw new EstadomaquinaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from estadomaquina table where DESCRIPCION= descripcion
*
* @param   String  descripcion
* @param   Connection con
* @return  Estadomaquina[]
*/

	public Estadomaquina[] findByDescripcion(String descripcion, Connection con) throws EstadomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idestado, nombre, descripcion from estadomaquina where descripcion = ? order by descripcion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, descripcion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadomaquinaException(sqle);
			}
			catch(Exception e){
					throw new EstadomaquinaException(e);
			}
			finally{}
	}

/**
* Returns all rows from estadomaquina table 
*
* @param Connection con
* @return  Estadomaquina[]
*
*/

	public Estadomaquina[] findAll( Connection con) throws EstadomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idestado, nombre, descripcion from estadomaquina";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadomaquinaException(sqle);
			}
			catch(Exception e){
					throw new EstadomaquinaException(e);
			}
			finally{}
	}

/**
* Returns rows from estadomaquina table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Estadomaquina[]
*
*/

	public Estadomaquina[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws EstadomaquinaException{
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
					throw new EstadomaquinaException(sqle);
			}
			catch(Exception e){
					throw new EstadomaquinaException(e);
			}
			finally{}
	}

/**
* Returns rows from estadomaquina table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Estadomaquina[]
*
*/

	public Estadomaquina[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws EstadomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idestado, nombre, descripcion from estadomaquina";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadomaquinaException(sqle);
			}
			catch(Exception e){
					throw new EstadomaquinaException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Estadomaquina
*
*/

	protected Estadomaquina fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Estadomaquina dto = new Estadomaquina();
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
* @param Estadomaquina dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Estadomaquina dto, ResultSet rs) throws SQLException
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
* @return  Estadomaquina[]
*/

	protected Estadomaquina[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Estadomaquina dto = new Estadomaquina();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Estadomaquina ret[] = new Estadomaquina[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
