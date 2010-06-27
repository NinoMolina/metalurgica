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
import java.util.Collection;
import java.util.ArrayList;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
import metalsoft.datos.idao.*;


/**
* 
* Implementation of ComprobantepagoDAO interface 
* 
*/


public class ComprobantepagoDAOImpl implements ComprobantepagoDAO
{


/**
* Method deletes a record from table COMPROBANTEPAGO
* @param ComprobantepagoPK comprobantepagopk
* @param  Connection  con
* @return  int
*
*/


	public int delete(ComprobantepagoPK comprobantepagopk , Connection con)throws ComprobantepagoException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  COMPROBANTEPAGO where idcomprobantepago = ?");
			ps.setLong(1, comprobantepagopk.getIdcomprobantepago());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new ComprobantepagoException(sqle);}
		catch(Exception e) {throw new ComprobantepagoException(e);}
	}



/**
* This method updates a record in table COMPROBANTEPAGO
* @param ComprobantepagoPK
* @param Comprobantepago
* @param  Connection con
* @return   int
*/

	public int update(ComprobantepagoPK comprobantepagopk, Comprobantepago comprobantepago, Connection con)throws ComprobantepagoException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update COMPROBANTEPAGO set NROCOMPROBANTEPAGO = ? , FECHAEMISION = ? , MONTO = ? , FORMADEPAGO = ? , USUARIO = ? , FACTURA = ?  where idcomprobantepago = ?");
				ps.setLong(1,comprobantepago.getNrocomprobantepago());
				ps.setDate(2,comprobantepago.getFechaemision());
				ps.setDouble(3,comprobantepago.getMonto());
				ps.setLong(4,comprobantepago.getFormadepago());
				ps.setLong(5,comprobantepago.getUsuario());
				ps.setLong(6,comprobantepago.getFactura());
				ps.setLong(7,comprobantepagopk.getIdcomprobantepago());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new ComprobantepagoException(sqle);}
		catch(Exception e){throw new ComprobantepagoException(e);}
	}

/**
* This method inserts data in table COMPROBANTEPAGO
*
* @param Comprobantepago comprobantepago
* @param   Connection con
* @return  ComprobantepagoPK
*/

	public int insert(Comprobantepago comprobantepago ,Connection con)throws ComprobantepagoException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into COMPROBANTEPAGO( NROCOMPROBANTEPAGO, FECHAEMISION, MONTO, FORMADEPAGO, USUARIO, FACTURA) values (?, ?, ?, ?, ?, ?)");
				ps.setLong(1,comprobantepago.getNrocomprobantepago());
				ps.setDate(2,comprobantepago.getFechaemision());
				ps.setDouble(3,comprobantepago.getMonto());
				ps.setLong(4,comprobantepago.getFormadepago());
				ps.setLong(5,comprobantepago.getUsuario());
				ps.setLong(6,comprobantepago.getFactura());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new ComprobantepagoException(sqle);}
		catch(Exception e){throw new ComprobantepagoException(e);}
	}

/**
* 
* Returns a row from the comprobantepago table for the primary key passed as parameter.
* 
*/

	public Comprobantepago findByPrimaryKey(long idcomprobantepago, Connection con) throws ComprobantepagoException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idcomprobantepago, nrocomprobantepago, fechaemision, monto, formadepago, usuario, factura from comprobantepago where idcomprobantepago = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idcomprobantepago);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new ComprobantepagoException(sqle);
	  	}
	    catch(Exception e){throw new ComprobantepagoException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the comprobantepago table for the primary key object passed as parameter.
* 
* @param  ComprobantepagoPK comprobantepagopk
* @param Connection con
* @return  Comprobantepago
*/

	public Comprobantepago findByPrimaryKey(ComprobantepagoPK comprobantepagopk, Connection con) throws ComprobantepagoException{
		return findByPrimaryKey(comprobantepagopk.getIdcomprobantepago(), con);
	}

/**
*
* Returns all rows from comprobantepago table where IDCOMPROBANTEPAGO= idcomprobantepago
*
* @param   long  idcomprobantepago
* @param   Connection con
* @return  Comprobantepago[]
*/

	public Comprobantepago[] findByIdcomprobantepago(long idcomprobantepago, Connection con) throws ComprobantepagoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcomprobantepago, nrocomprobantepago, fechaemision, monto, formadepago, usuario, factura from comprobantepago where idcomprobantepago = ? order by idcomprobantepago";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idcomprobantepago );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ComprobantepagoException(sqle);
			}
			catch(Exception e){
					throw new ComprobantepagoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from comprobantepago table where NROCOMPROBANTEPAGO= nrocomprobantepago
*
* @param   long  nrocomprobantepago
* @param   Connection con
* @return  Comprobantepago[]
*/

	public Comprobantepago[] findByNrocomprobantepago(long nrocomprobantepago, Connection con) throws ComprobantepagoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcomprobantepago, nrocomprobantepago, fechaemision, monto, formadepago, usuario, factura from comprobantepago where nrocomprobantepago = ? order by nrocomprobantepago";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, nrocomprobantepago );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ComprobantepagoException(sqle);
			}
			catch(Exception e){
					throw new ComprobantepagoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from comprobantepago table where FECHAEMISION= fechaemision
*
* @param   Date  fechaemision
* @param   Connection con
* @return  Comprobantepago[]
*/

	public Comprobantepago[] findByFechaemision(Date fechaemision, Connection con) throws ComprobantepagoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcomprobantepago, nrocomprobantepago, fechaemision, monto, formadepago, usuario, factura from comprobantepago where fechaemision = ? order by fechaemision";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechaemision );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ComprobantepagoException(sqle);
			}
			catch(Exception e){
					throw new ComprobantepagoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from comprobantepago table where MONTO= monto
*
* @param   double  monto
* @param   Connection con
* @return  Comprobantepago[]
*/

	public Comprobantepago[] findByMonto(double monto, Connection con) throws ComprobantepagoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcomprobantepago, nrocomprobantepago, fechaemision, monto, formadepago, usuario, factura from comprobantepago where monto = ? order by monto";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDouble( 1, monto );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ComprobantepagoException(sqle);
			}
			catch(Exception e){
					throw new ComprobantepagoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from comprobantepago table where FORMADEPAGO= formadepago
*
* @param   long  formadepago
* @param   Connection con
* @return  Comprobantepago[]
*/

	public Comprobantepago[] findByFormadepago(long formadepago, Connection con) throws ComprobantepagoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcomprobantepago, nrocomprobantepago, fechaemision, monto, formadepago, usuario, factura from comprobantepago where formadepago = ? order by formadepago";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, formadepago );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ComprobantepagoException(sqle);
			}
			catch(Exception e){
					throw new ComprobantepagoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from comprobantepago table where USUARIO= usuario
*
* @param   long  usuario
* @param   Connection con
* @return  Comprobantepago[]
*/

	public Comprobantepago[] findByUsuario(long usuario, Connection con) throws ComprobantepagoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcomprobantepago, nrocomprobantepago, fechaemision, monto, formadepago, usuario, factura from comprobantepago where usuario = ? order by usuario";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, usuario );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ComprobantepagoException(sqle);
			}
			catch(Exception e){
					throw new ComprobantepagoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from comprobantepago table where FACTURA= factura
*
* @param   long  factura
* @param   Connection con
* @return  Comprobantepago[]
*/

	public Comprobantepago[] findByFactura(long factura, Connection con) throws ComprobantepagoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcomprobantepago, nrocomprobantepago, fechaemision, monto, formadepago, usuario, factura from comprobantepago where factura = ? order by factura";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, factura );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ComprobantepagoException(sqle);
			}
			catch(Exception e){
					throw new ComprobantepagoException(e);
			}
			finally{}
	}

/**
* Returns all rows from comprobantepago table 
*
* @param Connection con
* @return  Comprobantepago[]
*
*/

	public Comprobantepago[] findAll( Connection con) throws ComprobantepagoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcomprobantepago, nrocomprobantepago, fechaemision, monto, formadepago, usuario, factura from comprobantepago";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ComprobantepagoException(sqle);
			}
			catch(Exception e){
					throw new ComprobantepagoException(e);
			}
			finally{}
	}

/**
* Returns rows from comprobantepago table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Comprobantepago[]
*
*/

	public Comprobantepago[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws ComprobantepagoException{
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
					throw new ComprobantepagoException(sqle);
			}
			catch(Exception e){
					throw new ComprobantepagoException(e);
			}
			finally{}
	}

/**
* Returns rows from comprobantepago table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Comprobantepago[]
*
*/

	public Comprobantepago[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws ComprobantepagoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idcomprobantepago, nrocomprobantepago, fechaemision, monto, formadepago, usuario, factura from comprobantepago";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ComprobantepagoException(sqle);
			}
			catch(Exception e){
					throw new ComprobantepagoException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Comprobantepago
*
*/

	protected Comprobantepago fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Comprobantepago dto = new Comprobantepago();
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
* @param Comprobantepago dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Comprobantepago dto, ResultSet rs) throws SQLException
	{
		 dto.setIdcomprobantepago(rs.getLong("idcomprobantepago"));
		 dto.setNrocomprobantepago(rs.getLong("nrocomprobantepago"));
		 dto.setFechaemision(rs.getDate("fechaemision"));
		 dto.setMonto(rs.getDouble("monto"));
		 dto.setFormadepago(rs.getLong("formadepago"));
		 dto.setUsuario(rs.getLong("usuario"));
		 dto.setFactura(rs.getLong("factura"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Comprobantepago[]
*/

	protected Comprobantepago[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Comprobantepago dto = new Comprobantepago();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Comprobantepago ret[] = new Comprobantepago[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
