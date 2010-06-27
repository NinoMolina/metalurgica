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
* Implementation of PedidomatrizDAO interface 
* 
*/


public class PedidomatrizDAOImpl implements PedidomatrizDAO
{


/**
* Method deletes a record from table PEDIDOMATRIZ
* @param PedidomatrizPK pedidomatrizpk
* @param  Connection  con
* @return  int
*
*/


	public int delete(PedidomatrizPK pedidomatrizpk , Connection con)throws PedidomatrizException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  PEDIDOMATRIZ where idpedidomatriz = ?");
			ps.setLong(1, pedidomatrizpk.getIdpedidomatriz());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new PedidomatrizException(sqle);}
		catch(Exception e) {throw new PedidomatrizException(e);}
	}



/**
* This method updates a record in table PEDIDOMATRIZ
* @param PedidomatrizPK
* @param Pedidomatriz
* @param  Connection con
* @return   int
*/

	public int update(PedidomatrizPK pedidomatrizpk, Pedidomatriz pedidomatriz, Connection con)throws PedidomatrizException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update PEDIDOMATRIZ set NROPEDIDOMATRIZ = ? , FECHAPEDIDOMATRIZ = ? , IDMATRIZ = ? , OBSERVACIONES = ?  where idpedidomatriz = ?");
				ps.setLong(1,pedidomatriz.getNropedidomatriz());
				ps.setDate(2,pedidomatriz.getFechapedidomatriz());
				ps.setLong(3,pedidomatriz.getIdmatriz());
				ps.setString(4,pedidomatriz.getObservaciones());
				ps.setLong(5,pedidomatrizpk.getIdpedidomatriz());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new PedidomatrizException(sqle);}
		catch(Exception e){throw new PedidomatrizException(e);}
	}

/**
* This method inserts data in table PEDIDOMATRIZ
*
* @param Pedidomatriz pedidomatriz
* @param   Connection con
* @return  PedidomatrizPK
*/

	public int insert(Pedidomatriz pedidomatriz ,Connection con)throws PedidomatrizException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into PEDIDOMATRIZ( NROPEDIDOMATRIZ, FECHAPEDIDOMATRIZ, IDMATRIZ, OBSERVACIONES) values (?, ?, ?, ?)");
				ps.setLong(1,pedidomatriz.getNropedidomatriz());
				ps.setDate(2,pedidomatriz.getFechapedidomatriz());
				ps.setLong(3,pedidomatriz.getIdmatriz());
				ps.setString(4,pedidomatriz.getObservaciones());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new PedidomatrizException(sqle);}
		catch(Exception e){throw new PedidomatrizException(e);}
	}

/**
* 
* Returns a row from the pedidomatriz table for the primary key passed as parameter.
* 
*/

	public Pedidomatriz findByPrimaryKey(long idpedidomatriz, Connection con) throws PedidomatrizException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idpedidomatriz, nropedidomatriz, fechapedidomatriz, idmatriz, observaciones from pedidomatriz where idpedidomatriz = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idpedidomatriz);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new PedidomatrizException(sqle);
	  	}
	    catch(Exception e){throw new PedidomatrizException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the pedidomatriz table for the primary key object passed as parameter.
* 
* @param  PedidomatrizPK pedidomatrizpk
* @param Connection con
* @return  Pedidomatriz
*/

	public Pedidomatriz findByPrimaryKey(PedidomatrizPK pedidomatrizpk, Connection con) throws PedidomatrizException{
		return findByPrimaryKey(pedidomatrizpk.getIdpedidomatriz(), con);
	}

/**
*
* Returns all rows from pedidomatriz table where IDPEDIDOMATRIZ= idpedidomatriz
*
* @param   long  idpedidomatriz
* @param   Connection con
* @return  Pedidomatriz[]
*/

	public Pedidomatriz[] findByIdpedidomatriz(long idpedidomatriz, Connection con) throws PedidomatrizException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idpedidomatriz, nropedidomatriz, fechapedidomatriz, idmatriz, observaciones from pedidomatriz where idpedidomatriz = ? order by idpedidomatriz";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idpedidomatriz );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PedidomatrizException(sqle);
			}
			catch(Exception e){
					throw new PedidomatrizException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from pedidomatriz table where NROPEDIDOMATRIZ= nropedidomatriz
*
* @param   long  nropedidomatriz
* @param   Connection con
* @return  Pedidomatriz[]
*/

	public Pedidomatriz[] findByNropedidomatriz(long nropedidomatriz, Connection con) throws PedidomatrizException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idpedidomatriz, nropedidomatriz, fechapedidomatriz, idmatriz, observaciones from pedidomatriz where nropedidomatriz = ? order by nropedidomatriz";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, nropedidomatriz );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PedidomatrizException(sqle);
			}
			catch(Exception e){
					throw new PedidomatrizException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from pedidomatriz table where FECHAPEDIDOMATRIZ= fechapedidomatriz
*
* @param   Date  fechapedidomatriz
* @param   Connection con
* @return  Pedidomatriz[]
*/

	public Pedidomatriz[] findByFechapedidomatriz(Date fechapedidomatriz, Connection con) throws PedidomatrizException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idpedidomatriz, nropedidomatriz, fechapedidomatriz, idmatriz, observaciones from pedidomatriz where fechapedidomatriz = ? order by fechapedidomatriz";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechapedidomatriz );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PedidomatrizException(sqle);
			}
			catch(Exception e){
					throw new PedidomatrizException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from pedidomatriz table where IDMATRIZ= idmatriz
*
* @param   long  idmatriz
* @param   Connection con
* @return  Pedidomatriz[]
*/

	public Pedidomatriz[] findByIdmatriz(long idmatriz, Connection con) throws PedidomatrizException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idpedidomatriz, nropedidomatriz, fechapedidomatriz, idmatriz, observaciones from pedidomatriz where idmatriz = ? order by idmatriz";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idmatriz );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PedidomatrizException(sqle);
			}
			catch(Exception e){
					throw new PedidomatrizException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from pedidomatriz table where OBSERVACIONES= observaciones
*
* @param   String  observaciones
* @param   Connection con
* @return  Pedidomatriz[]
*/

	public Pedidomatriz[] findByObservaciones(String observaciones, Connection con) throws PedidomatrizException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idpedidomatriz, nropedidomatriz, fechapedidomatriz, idmatriz, observaciones from pedidomatriz where observaciones = ? order by observaciones";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, observaciones );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PedidomatrizException(sqle);
			}
			catch(Exception e){
					throw new PedidomatrizException(e);
			}
			finally{}
	}

/**
* Returns all rows from pedidomatriz table 
*
* @param Connection con
* @return  Pedidomatriz[]
*
*/

	public Pedidomatriz[] findAll( Connection con) throws PedidomatrizException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idpedidomatriz, nropedidomatriz, fechapedidomatriz, idmatriz, observaciones from pedidomatriz";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PedidomatrizException(sqle);
			}
			catch(Exception e){
					throw new PedidomatrizException(e);
			}
			finally{}
	}

/**
* Returns rows from pedidomatriz table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Pedidomatriz[]
*
*/

	public Pedidomatriz[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws PedidomatrizException{
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
					throw new PedidomatrizException(sqle);
			}
			catch(Exception e){
					throw new PedidomatrizException(e);
			}
			finally{}
	}

/**
* Returns rows from pedidomatriz table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Pedidomatriz[]
*
*/

	public Pedidomatriz[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws PedidomatrizException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idpedidomatriz, nropedidomatriz, fechapedidomatriz, idmatriz, observaciones from pedidomatriz";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PedidomatrizException(sqle);
			}
			catch(Exception e){
					throw new PedidomatrizException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Pedidomatriz
*
*/

	protected Pedidomatriz fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Pedidomatriz dto = new Pedidomatriz();
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
* @param Pedidomatriz dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Pedidomatriz dto, ResultSet rs) throws SQLException
	{
		 dto.setIdpedidomatriz(rs.getLong("idpedidomatriz"));
		 dto.setNropedidomatriz(rs.getLong("nropedidomatriz"));
		 dto.setFechapedidomatriz(rs.getDate("fechapedidomatriz"));
		 dto.setIdmatriz(rs.getLong("idmatriz"));
		 dto.setObservaciones(rs.getString("observaciones"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Pedidomatriz[]
*/

	protected Pedidomatriz[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Pedidomatriz dto = new Pedidomatriz();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Pedidomatriz ret[] = new Pedidomatriz[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
