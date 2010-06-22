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
* Implementation of EstadoejecplanifpedidoDAO interface 
* 
*/


public class EstadoejecplanifpedidoDAOImpl implements EstadoejecplanifpedidoDAO
{


/**
* Method deletes a record from table ESTADOEJECPLANIFPEDIDO
* @param EstadoejecplanifpedidoPK estadoejecplanifpedidopk
* @param  Connection  con
* @return  int
*
*/


	public int delete(EstadoejecplanifpedidoPK estadoejecplanifpedidopk , Connection con)throws EstadoejecplanifpedidoException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  ESTADOEJECPLANIFPEDIDO where idestado = ?");
			ps.setLong(1, estadoejecplanifpedidopk.getIdestado());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new EstadoejecplanifpedidoException(sqle);}
		catch(Exception e) {throw new EstadoejecplanifpedidoException(e);}
	}



/**
* This method updates a record in table ESTADOEJECPLANIFPEDIDO
* @param EstadoejecplanifpedidoPK
* @param Estadoejecplanifpedido
* @param  Connection con
* @return   int
*/

	public int update(EstadoejecplanifpedidoPK estadoejecplanifpedidopk, Estadoejecplanifpedido estadoejecplanifpedido, Connection con)throws EstadoejecplanifpedidoException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update ESTADOEJECPLANIFPEDIDO set NOMBRE = ? , DESCRIPCION = ?  where idestado = ?");
				ps.setString(1,estadoejecplanifpedido.getNombre());
				ps.setString(2,estadoejecplanifpedido.getDescripcion());
				ps.setLong(3,estadoejecplanifpedidopk.getIdestado());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new EstadoejecplanifpedidoException(sqle);}
		catch(Exception e){throw new EstadoejecplanifpedidoException(e);}
	}

/**
* This method inserts data in table ESTADOEJECPLANIFPEDIDO
*
* @param Estadoejecplanifpedido estadoejecplanifpedido
* @param   Connection con
* @return  EstadoejecplanifpedidoPK
*/

	public int insert(Estadoejecplanifpedido estadoejecplanifpedido ,Connection con)throws EstadoejecplanifpedidoException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into ESTADOEJECPLANIFPEDIDO( IDESTADO, NOMBRE, DESCRIPCION) values (?, ?, ?)");
				ps.setLong(1,estadoejecplanifpedido.getIdestado());
				ps.setString(2,estadoejecplanifpedido.getNombre());
				ps.setString(3,estadoejecplanifpedido.getDescripcion());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new EstadoejecplanifpedidoException(sqle);}
		catch(Exception e){throw new EstadoejecplanifpedidoException(e);}
	}

/**
* 
* Returns a row from the estadoejecplanifpedido table for the primary key passed as parameter.
* 
*/

	public Estadoejecplanifpedido findByPrimaryKey(long idestado, Connection con) throws EstadoejecplanifpedidoException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idestado, nombre, descripcion from estadoejecplanifpedido where idestado = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idestado);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new EstadoejecplanifpedidoException(sqle);
	  	}
	    catch(Exception e){throw new EstadoejecplanifpedidoException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the estadoejecplanifpedido table for the primary key object passed as parameter.
* 
* @param  EstadoejecplanifpedidoPK estadoejecplanifpedidopk
* @param Connection con
* @return  Estadoejecplanifpedido
*/

	public Estadoejecplanifpedido findByPrimaryKey(EstadoejecplanifpedidoPK estadoejecplanifpedidopk, Connection con) throws EstadoejecplanifpedidoException{
		return findByPrimaryKey(estadoejecplanifpedidopk.getIdestado(), con);
	}

/**
*
* Returns all rows from estadoejecplanifpedido table where IDESTADO= idestado
*
* @param   long  idestado
* @param   Connection con
* @return  Estadoejecplanifpedido[]
*/

	public Estadoejecplanifpedido[] findByIdestado(long idestado, Connection con) throws EstadoejecplanifpedidoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idestado, nombre, descripcion from estadoejecplanifpedido where idestado = ? order by idestado";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idestado );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadoejecplanifpedidoException(sqle);
			}
			catch(Exception e){
					throw new EstadoejecplanifpedidoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from estadoejecplanifpedido table where NOMBRE= nombre
*
* @param   String  nombre
* @param   Connection con
* @return  Estadoejecplanifpedido[]
*/

	public Estadoejecplanifpedido[] findByNombre(String nombre, Connection con) throws EstadoejecplanifpedidoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idestado, nombre, descripcion from estadoejecplanifpedido where nombre = ? order by nombre";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, nombre );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadoejecplanifpedidoException(sqle);
			}
			catch(Exception e){
					throw new EstadoejecplanifpedidoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from estadoejecplanifpedido table where DESCRIPCION= descripcion
*
* @param   String  descripcion
* @param   Connection con
* @return  Estadoejecplanifpedido[]
*/

	public Estadoejecplanifpedido[] findByDescripcion(String descripcion, Connection con) throws EstadoejecplanifpedidoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idestado, nombre, descripcion from estadoejecplanifpedido where descripcion = ? order by descripcion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, descripcion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadoejecplanifpedidoException(sqle);
			}
			catch(Exception e){
					throw new EstadoejecplanifpedidoException(e);
			}
			finally{}
	}

/**
* Returns all rows from estadoejecplanifpedido table 
*
* @param Connection con
* @return  Estadoejecplanifpedido[]
*
*/

	public Estadoejecplanifpedido[] findAll( Connection con) throws EstadoejecplanifpedidoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idestado, nombre, descripcion from estadoejecplanifpedido";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadoejecplanifpedidoException(sqle);
			}
			catch(Exception e){
					throw new EstadoejecplanifpedidoException(e);
			}
			finally{}
	}

/**
* Returns rows from estadoejecplanifpedido table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Estadoejecplanifpedido[]
*
*/

	public Estadoejecplanifpedido[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws EstadoejecplanifpedidoException{
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
					throw new EstadoejecplanifpedidoException(sqle);
			}
			catch(Exception e){
					throw new EstadoejecplanifpedidoException(e);
			}
			finally{}
	}

/**
* Returns rows from estadoejecplanifpedido table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Estadoejecplanifpedido[]
*
*/

	public Estadoejecplanifpedido[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws EstadoejecplanifpedidoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idestado, nombre, descripcion from estadoejecplanifpedido";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EstadoejecplanifpedidoException(sqle);
			}
			catch(Exception e){
					throw new EstadoejecplanifpedidoException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Estadoejecplanifpedido
*
*/

	protected Estadoejecplanifpedido fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Estadoejecplanifpedido dto = new Estadoejecplanifpedido();
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
* @param Estadoejecplanifpedido dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Estadoejecplanifpedido dto, ResultSet rs) throws SQLException
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
* @return  Estadoejecplanifpedido[]
*/

	protected Estadoejecplanifpedido[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Estadoejecplanifpedido dto = new Estadoejecplanifpedido();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Estadoejecplanifpedido ret[] = new Estadoejecplanifpedido[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
