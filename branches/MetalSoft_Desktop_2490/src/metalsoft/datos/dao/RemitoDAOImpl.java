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
import java.util.Collection;
import java.util.ArrayList;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
import metalsoft.datos.idao.*;


/**
* 
* Implementation of RemitoDAO interface 
* 
*/


public class RemitoDAOImpl implements RemitoDAO
{


/**
* Method deletes a record from table REMITO
* @param RemitoPK remitopk
* @param  Connection  con
* @return  int
*
*/


	public int delete(RemitoPKDB remitopk , Connection con)throws RemitoException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  REMITO where idremito = ?");
			ps.setLong(1, remitopk.getIdremito());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new RemitoException(sqle);}
		catch(Exception e) {throw new RemitoException(e);}
	}



/**
* This method updates a record in table REMITO
* @param RemitoPK
* @param Remito
* @param  Connection con
* @return   int
*/

	public int update(RemitoPKDB remitopk, RemitoDB remito, Connection con)throws RemitoException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update REMITO set NROREMITO = ? , FECHAEMISION = ? , PEDIDO = ? , ESTADO = ?  where idremito = ?");
				ps.setLong(1,remito.getNroremito());
				ps.setDate(2,remito.getFechaemision());
				ps.setLong(3,remito.getPedido());
				if(remito.getEstado()>0) ps.setLong(4,remito.getEstado());
                else ps.setNull(4,java.sql.Types.NULL);
				ps.setLong(5,remitopk.getIdremito());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new RemitoException(sqle);}
		catch(Exception e){throw new RemitoException(e);}
	}

/**
* This method inserts data in table REMITO
*
* @param Remito remito
* @param   Connection con
* @return  RemitoPK
*/

	public int insert(RemitoDB remito ,Connection con)throws RemitoException {

		PreparedStatement ps = null;
        ResultSet rs=null;
		try
		{
			ps = con.prepareStatement("insert into REMITO( NROREMITO, FECHAEMISION, PEDIDO, ESTADO) values (?, ?, ?, ?) RETURNING IDREMITO");
				ps.setLong(1,remito.getNroremito());
				ps.setDate(2,remito.getFechaemision());
				ps.setLong(3,remito.getPedido());

                if(remito.getEstado()>0) ps.setLong(4,remito.getEstado());
                else ps.setNull(4,java.sql.Types.NULL);

				rs=ps.executeQuery();
                rs.next();
				return (int) rs.getLong(1);
		}catch(SQLException sqle){throw new RemitoException(sqle);}
		catch(Exception e){throw new RemitoException(e);}
	}

/**
* 
* Returns a row from the remito table for the primary key passed as parameter.
* 
*/

	public RemitoDB findByPrimaryKey(long idremito, Connection con) throws RemitoException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idremito, nroremito, fechaemision, pedido, estado from remito where idremito = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idremito);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new RemitoException(sqle);
	  	}
	    catch(Exception e){throw new RemitoException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the remito table for the primary key object passed as parameter.
* 
* @param  RemitoPK remitopk
* @param Connection con
* @return  Remito
*/

	public RemitoDB findByPrimaryKey(RemitoPKDB remitopk, Connection con) throws RemitoException{
		return findByPrimaryKey(remitopk.getIdremito(), con);
	}

/**
*
* Returns all rows from remito table where IDREMITO= idremito
*
* @param   long  idremito
* @param   Connection con
* @return  Remito[]
*/

	public RemitoDB[] findByIdremito(long idremito, Connection con) throws RemitoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idremito, nroremito, fechaemision, pedido, estado from remito where idremito = ? order by idremito";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idremito );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new RemitoException(sqle);
			}
			catch(Exception e){
					throw new RemitoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from remito table where NROREMITO= nroremito
*
* @param   long  nroremito
* @param   Connection con
* @return  Remito[]
*/

	public RemitoDB[] findByNroremito(long nroremito, Connection con) throws RemitoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idremito, nroremito, fechaemision, pedido, estado from remito where nroremito = ? order by nroremito";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, nroremito );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new RemitoException(sqle);
			}
			catch(Exception e){
					throw new RemitoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from remito table where FECHAEMISION= fechaemision
*
* @param   Date  fechaemision
* @param   Connection con
* @return  Remito[]
*/

	public RemitoDB[] findByFechaemision(Date fechaemision, Connection con) throws RemitoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idremito, nroremito, fechaemision, pedido, estado from remito where fechaemision = ? order by fechaemision";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechaemision );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new RemitoException(sqle);
			}
			catch(Exception e){
					throw new RemitoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from remito table where PEDIDO= pedido
*
* @param   long  pedido
* @param   Connection con
* @return  Remito[]
*/

	public RemitoDB[] findByPedido(long pedido, Connection con) throws RemitoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idremito, nroremito, fechaemision, pedido, estado from remito where pedido = ? order by pedido";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, pedido );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new RemitoException(sqle);
			}
			catch(Exception e){
					throw new RemitoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from remito table where ESTADO= estado
*
* @param   long  estado
* @param   Connection con
* @return  Remito[]
*/

	public RemitoDB[] findByEstado(long estado, Connection con) throws RemitoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idremito, nroremito, fechaemision, pedido, estado from remito where estado = ? order by estado";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, estado );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new RemitoException(sqle);
			}
			catch(Exception e){
					throw new RemitoException(e);
			}
			finally{}
	}

/**
* Returns all rows from remito table 
*
* @param Connection con
* @return  Remito[]
*
*/

	public RemitoDB[] findAll( Connection con) throws RemitoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idremito, nroremito, fechaemision, pedido, estado from remito";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new RemitoException(sqle);
			}
			catch(Exception e){
					throw new RemitoException(e);
			}
			finally{}
	}

/**
* Returns rows from remito table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Remito[]
*
*/

	public RemitoDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws RemitoException{
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
					throw new RemitoException(sqle);
			}
			catch(Exception e){
					throw new RemitoException(e);
			}
			finally{}
	}

/**
* Returns rows from remito table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Remito[]
*
*/

	public RemitoDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws RemitoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idremito, nroremito, fechaemision, pedido, estado from remito";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new RemitoException(sqle);
			}
			catch(Exception e){
					throw new RemitoException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Remito
*
*/

	protected RemitoDB fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					RemitoDB dto = new RemitoDB();
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
* @param Remito dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(RemitoDB dto, ResultSet rs) throws SQLException
	{
		 dto.setIdremito(rs.getLong("idremito"));
		 dto.setNroremito(rs.getLong("nroremito"));
		 dto.setFechaemision(rs.getDate("fechaemision"));
		 dto.setPedido(rs.getLong("pedido"));
		 dto.setEstado(rs.getLong("estado"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Remito[]
*/

	protected RemitoDB[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			RemitoDB dto = new RemitoDB();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		RemitoDB ret[] = new RemitoDB[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
