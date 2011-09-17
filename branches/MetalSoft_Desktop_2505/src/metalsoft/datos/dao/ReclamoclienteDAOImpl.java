/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:04 ART 2010
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
* Implementation of ReclamoclienteDAO interface 
* 
*/


public class ReclamoclienteDAOImpl implements ReclamoclienteDAO
{


/**
* Method deletes a record from table RECLAMOCLIENTE
* @param ReclamoclientePK reclamoclientepk
* @param  Connection  con
* @return  int
*
*/


	public int delete(ReclamoclientePKDB reclamoclientepk , Connection con)throws ReclamoclienteException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  RECLAMOCLIENTE where idreclamo = ?");
			ps.setLong(1, reclamoclientepk.getIdreclamo());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new ReclamoclienteException(sqle);}
		catch(Exception e) {throw new ReclamoclienteException(e);}
	}



/**
* This method updates a record in table RECLAMOCLIENTE
* @param ReclamoclientePK
* @param Reclamocliente
* @param  Connection con
* @return   int
*/

	public int update(ReclamoclientePKDB reclamoclientepk, ReclamoclienteDB reclamocliente, Connection con)throws ReclamoclienteException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update RECLAMOCLIENTE set NRORECLAMO = ? , TIPORECLAMO = ? , MOTIVO = ? , FECHARECLAMO = ? , CLIENTE = ?  where idreclamo = ?");
				ps.setLong(1,reclamocliente.getNroreclamo());
				ps.setLong(2,reclamocliente.getTiporeclamo());
				ps.setString(3,reclamocliente.getMotivo());
				ps.setDate(4,reclamocliente.getFechareclamo());
				ps.setLong(5,reclamocliente.getCliente());
				ps.setLong(6,reclamoclientepk.getIdreclamo());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new ReclamoclienteException(sqle);}
		catch(Exception e){throw new ReclamoclienteException(e);}
	}

/**
* This method inserts data in table RECLAMOCLIENTE
*
* @param Reclamocliente reclamocliente
* @param   Connection con
* @return  ReclamoclientePK
*/

	public int insert(ReclamoclienteDB reclamocliente ,Connection con)throws ReclamoclienteException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into RECLAMOCLIENTE( NRORECLAMO, TIPORECLAMO, MOTIVO, FECHARECLAMO, CLIENTE) values (?, ?, ?, ?, ?)");
				ps.setLong(1,reclamocliente.getNroreclamo());
				ps.setLong(2,reclamocliente.getTiporeclamo());
				ps.setString(3,reclamocliente.getMotivo());
				ps.setDate(4,reclamocliente.getFechareclamo());
				ps.setLong(5,reclamocliente.getCliente());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new ReclamoclienteException(sqle);}
		catch(Exception e){throw new ReclamoclienteException(e);}
	}

/**
* 
* Returns a row from the reclamocliente table for the primary key passed as parameter.
* 
*/

	public ReclamoclienteDB findByPrimaryKey(long idreclamo, Connection con) throws ReclamoclienteException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idreclamo, nroreclamo, tiporeclamo, motivo, fechareclamo, cliente from reclamocliente where idreclamo = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idreclamo);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new ReclamoclienteException(sqle);
	  	}
	    catch(Exception e){throw new ReclamoclienteException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the reclamocliente table for the primary key object passed as parameter.
* 
* @param  ReclamoclientePK reclamoclientepk
* @param Connection con
* @return  Reclamocliente
*/

	public ReclamoclienteDB findByPrimaryKey(ReclamoclientePKDB reclamoclientepk, Connection con) throws ReclamoclienteException{
		return findByPrimaryKey(reclamoclientepk.getIdreclamo(), con);
	}

/**
*
* Returns all rows from reclamocliente table where IDRECLAMO= idreclamo
*
* @param   long  idreclamo
* @param   Connection con
* @return  Reclamocliente[]
*/

	public ReclamoclienteDB[] findByIdreclamo(long idreclamo, Connection con) throws ReclamoclienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idreclamo, nroreclamo, tiporeclamo, motivo, fechareclamo, cliente from reclamocliente where idreclamo = ? order by idreclamo";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idreclamo );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ReclamoclienteException(sqle);
			}
			catch(Exception e){
					throw new ReclamoclienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from reclamocliente table where NRORECLAMO= nroreclamo
*
* @param   long  nroreclamo
* @param   Connection con
* @return  Reclamocliente[]
*/

	public ReclamoclienteDB[] findByNroreclamo(long nroreclamo, Connection con) throws ReclamoclienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idreclamo, nroreclamo, tiporeclamo, motivo, fechareclamo, cliente from reclamocliente where nroreclamo = ? order by nroreclamo";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, nroreclamo );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ReclamoclienteException(sqle);
			}
			catch(Exception e){
					throw new ReclamoclienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from reclamocliente table where TIPORECLAMO= tiporeclamo
*
* @param   long  tiporeclamo
* @param   Connection con
* @return  Reclamocliente[]
*/

	public ReclamoclienteDB[] findByTiporeclamo(long tiporeclamo, Connection con) throws ReclamoclienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idreclamo, nroreclamo, tiporeclamo, motivo, fechareclamo, cliente from reclamocliente where tiporeclamo = ? order by tiporeclamo";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, tiporeclamo );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ReclamoclienteException(sqle);
			}
			catch(Exception e){
					throw new ReclamoclienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from reclamocliente table where MOTIVO= motivo
*
* @param   String  motivo
* @param   Connection con
* @return  Reclamocliente[]
*/

	public ReclamoclienteDB[] findByMotivo(String motivo, Connection con) throws ReclamoclienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idreclamo, nroreclamo, tiporeclamo, motivo, fechareclamo, cliente from reclamocliente where motivo = ? order by motivo";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, motivo );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ReclamoclienteException(sqle);
			}
			catch(Exception e){
					throw new ReclamoclienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from reclamocliente table where FECHARECLAMO= fechareclamo
*
* @param   Date  fechareclamo
* @param   Connection con
* @return  Reclamocliente[]
*/

	public ReclamoclienteDB[] findByFechareclamo(Date fechareclamo, Connection con) throws ReclamoclienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idreclamo, nroreclamo, tiporeclamo, motivo, fechareclamo, cliente from reclamocliente where fechareclamo = ? order by fechareclamo";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechareclamo );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ReclamoclienteException(sqle);
			}
			catch(Exception e){
					throw new ReclamoclienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from reclamocliente table where CLIENTE= cliente
*
* @param   long  cliente
* @param   Connection con
* @return  Reclamocliente[]
*/

	public ReclamoclienteDB[] findByCliente(long cliente, Connection con) throws ReclamoclienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idreclamo, nroreclamo, tiporeclamo, motivo, fechareclamo, cliente from reclamocliente where cliente = ? order by cliente";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, cliente );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ReclamoclienteException(sqle);
			}
			catch(Exception e){
					throw new ReclamoclienteException(e);
			}
			finally{}
	}

/**
* Returns all rows from reclamocliente table 
*
* @param Connection con
* @return  Reclamocliente[]
*
*/

	public ReclamoclienteDB[] findAll( Connection con) throws ReclamoclienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idreclamo, nroreclamo, tiporeclamo, motivo, fechareclamo, cliente from reclamocliente";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ReclamoclienteException(sqle);
			}
			catch(Exception e){
					throw new ReclamoclienteException(e);
			}
			finally{}
	}

/**
* Returns rows from reclamocliente table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Reclamocliente[]
*
*/

	public ReclamoclienteDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws ReclamoclienteException{
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
					throw new ReclamoclienteException(sqle);
			}
			catch(Exception e){
					throw new ReclamoclienteException(e);
			}
			finally{}
	}

/**
* Returns rows from reclamocliente table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Reclamocliente[]
*
*/

	public ReclamoclienteDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws ReclamoclienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idreclamo, nroreclamo, tiporeclamo, motivo, fechareclamo, cliente from reclamocliente";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ReclamoclienteException(sqle);
			}
			catch(Exception e){
					throw new ReclamoclienteException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Reclamocliente
*
*/

	protected ReclamoclienteDB fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					ReclamoclienteDB dto = new ReclamoclienteDB();
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
* @param Reclamocliente dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(ReclamoclienteDB dto, ResultSet rs) throws SQLException
	{
		 dto.setIdreclamo(rs.getLong("idreclamo"));
		 dto.setNroreclamo(rs.getLong("nroreclamo"));
		 dto.setTiporeclamo(rs.getLong("tiporeclamo"));
		 dto.setMotivo(rs.getString("motivo"));
		 dto.setFechareclamo(rs.getDate("fechareclamo"));
		 dto.setCliente(rs.getLong("cliente"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Reclamocliente[]
*/

	protected ReclamoclienteDB[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			ReclamoclienteDB dto = new ReclamoclienteDB();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		ReclamoclienteDB ret[] = new ReclamoclienteDB[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
