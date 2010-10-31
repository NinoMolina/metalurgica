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
import java.util.Collection;
import java.util.ArrayList;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
import metalsoft.datos.idao.*;


/**
* 
* Implementation of FacturaDAO interface 
* 
*/


public class FacturaDAOImpl implements FacturaDAO
{


/**
* Method deletes a record from table FACTURA
* @param FacturaPK facturapk
* @param  Connection  con
* @return  int
*
*/


	public int delete(FacturaPK facturapk , Connection con)throws FacturaException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  FACTURA where idfactura = ?");
			ps.setLong(1, facturapk.getIdfactura());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new FacturaException(sqle);}
		catch(Exception e) {throw new FacturaException(e);}
	}



/**
* This method updates a record in table FACTURA
* @param FacturaPK
* @param FacturaDB
* @param  Connection con
* @return   int
*/

	public int update(FacturaPK facturapk, FacturaDB factura, Connection con)throws FacturaException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update FACTURA set NROFACTURA = ? , FECHAEMISION = ? , TIPOIVA = ? , FECHAREALCOBRO = ? , FORMAPAGO = ? , FECHAVENCIMIENTO = ? , USUARIO = ? , ESTADO = ? , TIPOFACTURA = ?  where idfactura = ?");
				ps.setLong(1,factura.getNrofactura());
				ps.setDate(2,factura.getFechaemision());
				ps.setLong(3,factura.getTipoiva());
				ps.setDate(4,factura.getFecharealcobro());
				ps.setLong(5,factura.getFormapago());
				ps.setDate(6,factura.getFechavencimiento());
				ps.setLong(7,factura.getUsuario());
				ps.setLong(8,factura.getEstado());
				ps.setString(9,factura.getTipofactura());
				ps.setLong(10,facturapk.getIdfactura());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new FacturaException(sqle);}
		catch(Exception e){throw new FacturaException(e);}
	}

/**
* This method inserts data in table FACTURA
*
* @param FacturaDB factura
* @param   Connection con
* @return  FacturaPK
*/

	public int insert(FacturaDB factura ,Connection con)throws FacturaException {

		PreparedStatement ps = null;
        ResultSet rs=null;
		try
		{
			ps = con.prepareStatement("insert into FACTURA( NROFACTURA, FECHAEMISION, TIPOIVA, FECHAREALCOBRO, FORMAPAGO, FECHAVENCIMIENTO, USUARIO, ESTADO, TIPOFACTURA) values (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING IDFACTURA");
				ps.setLong(1,factura.getNrofactura());
				ps.setDate(2,factura.getFechaemision());
                if(factura.getTipoiva()>0) ps.setLong(3,factura.getTipoiva());
                else ps.setNull(3,java.sql.Types.NULL);
				ps.setDate(4,factura.getFecharealcobro());
				ps.setLong(5,factura.getFormapago());
				ps.setDate(6,factura.getFechavencimiento());
                if(factura.getUsuario()>0) ps.setLong(7,factura.getUsuario());
                else ps.setNull(7,java.sql.Types.NULL);
				ps.setLong(8,factura.getEstado());
				ps.setString(9,factura.getTipofactura());

				rs=ps.executeQuery();
                rs.next();
				return (int) rs.getLong(1);
		}catch(SQLException sqle){throw new FacturaException(sqle);}
		catch(Exception e){throw new FacturaException(e);}
	}

/**
* 
* Returns a row from the factura table for the primary key passed as parameter.
* 
*/

	public FacturaDB findByPrimaryKey(long idfactura, Connection con) throws FacturaException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idfactura, nrofactura, fechaemision, tipoiva, fecharealcobro, formapago, fechavencimiento, usuario, estado, tipofactura from factura where idfactura = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idfactura);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new FacturaException(sqle);
	  	}
	    catch(Exception e){throw new FacturaException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the factura table for the primary key object passed as parameter.
* 
* @param  FacturaPK facturapk
* @param Connection con
* @return  FacturaDB
*/

	public FacturaDB findByPrimaryKey(FacturaPK facturapk, Connection con) throws FacturaException{
		return findByPrimaryKey(facturapk.getIdfactura(), con);
	}

/**
*
* Returns all rows from factura table where IDFACTURA= idfactura
*
* @param   long  idfactura
* @param   Connection con
* @return  FacturaDB[]
*/

	public FacturaDB[] findByIdfactura(long idfactura, Connection con) throws FacturaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idfactura, nrofactura, fechaemision, tipoiva, fecharealcobro, formapago, fechavencimiento, usuario, estado, tipofactura from factura where idfactura = ? order by idfactura";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idfactura );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new FacturaException(sqle);
			}
			catch(Exception e){
					throw new FacturaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from factura table where NROFACTURA= nrofactura
*
* @param   long  nrofactura
* @param   Connection con
* @return  FacturaDB[]
*/

	public FacturaDB[] findByNrofactura(long nrofactura, Connection con) throws FacturaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idfactura, nrofactura, fechaemision, tipoiva, fecharealcobro, formapago, fechavencimiento, usuario, estado, tipofactura from factura where nrofactura = ? order by nrofactura";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, nrofactura );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new FacturaException(sqle);
			}
			catch(Exception e){
					throw new FacturaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from factura table where FECHAEMISION= fechaemision
*
* @param   Date  fechaemision
* @param   Connection con
* @return  FacturaDB[]
*/

	public FacturaDB[] findByFechaemision(Date fechaemision, Connection con) throws FacturaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idfactura, nrofactura, fechaemision, tipoiva, fecharealcobro, formapago, fechavencimiento, usuario, estado, tipofactura from factura where fechaemision = ? order by fechaemision";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechaemision );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new FacturaException(sqle);
			}
			catch(Exception e){
					throw new FacturaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from factura table where TIPOIVA= tipoiva
*
* @param   long  tipoiva
* @param   Connection con
* @return  FacturaDB[]
*/

	public FacturaDB[] findByTipoiva(long tipoiva, Connection con) throws FacturaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idfactura, nrofactura, fechaemision, tipoiva, fecharealcobro, formapago, fechavencimiento, usuario, estado, tipofactura from factura where tipoiva = ? order by tipoiva";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, tipoiva );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new FacturaException(sqle);
			}
			catch(Exception e){
					throw new FacturaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from factura table where FECHAREALCOBRO= fecharealcobro
*
* @param   Date  fecharealcobro
* @param   Connection con
* @return  FacturaDB[]
*/

	public FacturaDB[] findByFecharealcobro(Date fecharealcobro, Connection con) throws FacturaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idfactura, nrofactura, fechaemision, tipoiva, fecharealcobro, formapago, fechavencimiento, usuario, estado, tipofactura from factura where fecharealcobro = ? order by fecharealcobro";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fecharealcobro );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new FacturaException(sqle);
			}
			catch(Exception e){
					throw new FacturaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from factura table where FORMAPAGO= formapago
*
* @param   long  formapago
* @param   Connection con
* @return  FacturaDB[]
*/

	public FacturaDB[] findByFormapago(long formapago, Connection con) throws FacturaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idfactura, nrofactura, fechaemision, tipoiva, fecharealcobro, formapago, fechavencimiento, usuario, estado, tipofactura from factura where formapago = ? order by formapago";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, formapago );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new FacturaException(sqle);
			}
			catch(Exception e){
					throw new FacturaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from factura table where FECHAVENCIMIENTO= fechavencimiento
*
* @param   Date  fechavencimiento
* @param   Connection con
* @return  FacturaDB[]
*/

	public FacturaDB[] findByFechavencimiento(Date fechavencimiento, Connection con) throws FacturaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idfactura, nrofactura, fechaemision, tipoiva, fecharealcobro, formapago, fechavencimiento, usuario, estado, tipofactura from factura where fechavencimiento = ? order by fechavencimiento";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechavencimiento );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new FacturaException(sqle);
			}
			catch(Exception e){
					throw new FacturaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from factura table where USUARIO= usuario
*
* @param   long  usuario
* @param   Connection con
* @return  FacturaDB[]
*/

	public FacturaDB[] findByUsuario(long usuario, Connection con) throws FacturaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idfactura, nrofactura, fechaemision, tipoiva, fecharealcobro, formapago, fechavencimiento, usuario, estado, tipofactura from factura where usuario = ? order by usuario";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, usuario );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new FacturaException(sqle);
			}
			catch(Exception e){
					throw new FacturaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from factura table where ESTADO= estado
*
* @param   long  estado
* @param   Connection con
* @return  FacturaDB[]
*/

	public FacturaDB[] findByEstado(long estado, Connection con) throws FacturaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idfactura, nrofactura, fechaemision, tipoiva, fecharealcobro, formapago, fechavencimiento, usuario, estado, tipofactura from factura where estado = ? order by estado";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, estado );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new FacturaException(sqle);
			}
			catch(Exception e){
					throw new FacturaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from factura table where TIPOFACTURA= tipofactura
*
* @param   String  tipofactura
* @param   Connection con
* @return  FacturaDB[]
*/

	public FacturaDB[] findByTipofactura(String tipofactura, Connection con) throws FacturaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idfactura, nrofactura, fechaemision, tipoiva, fecharealcobro, formapago, fechavencimiento, usuario, estado, tipofactura from factura where tipofactura = ? order by tipofactura";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, tipofactura );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new FacturaException(sqle);
			}
			catch(Exception e){
					throw new FacturaException(e);
			}
			finally{}
	}

/**
* Returns all rows from factura table 
*
* @param Connection con
* @return  FacturaDB[]
*
*/

	public FacturaDB[] findAll( Connection con) throws FacturaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idfactura, nrofactura, fechaemision, tipoiva, fecharealcobro, formapago, fechavencimiento, usuario, estado, tipofactura from factura";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new FacturaException(sqle);
			}
			catch(Exception e){
					throw new FacturaException(e);
			}
			finally{}
	}

/**
* Returns rows from factura table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  FacturaDB[]
*
*/

	public FacturaDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws FacturaException{
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
					throw new FacturaException(sqle);
			}
			catch(Exception e){
					throw new FacturaException(e);
			}
			finally{}
	}

/**
* Returns rows from factura table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  FacturaDB[]
*
*/

	public FacturaDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws FacturaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idfactura, nrofactura, fechaemision, tipoiva, fecharealcobro, formapago, fechavencimiento, usuario, estado, tipofactura from factura";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new FacturaException(sqle);
			}
			catch(Exception e){
					throw new FacturaException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  FacturaDB
*
*/

	protected FacturaDB fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					FacturaDB dto = new FacturaDB();
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
* @param FacturaDB dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(FacturaDB dto, ResultSet rs) throws SQLException
	{
		 dto.setIdfactura(rs.getLong("idfactura"));
		 dto.setNrofactura(rs.getLong("nrofactura"));
		 dto.setFechaemision(rs.getDate("fechaemision"));
		 dto.setTipoiva(rs.getLong("tipoiva"));
		 dto.setFecharealcobro(rs.getDate("fecharealcobro"));
		 dto.setFormapago(rs.getLong("formapago"));
		 dto.setFechavencimiento(rs.getDate("fechavencimiento"));
		 dto.setUsuario(rs.getLong("usuario"));
		 dto.setEstado(rs.getLong("estado"));
		 dto.setTipofactura(rs.getString("tipofactura"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  FacturaDB[]
*/

	protected FacturaDB[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			FacturaDB dto = new FacturaDB();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		FacturaDB ret[] = new FacturaDB[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
