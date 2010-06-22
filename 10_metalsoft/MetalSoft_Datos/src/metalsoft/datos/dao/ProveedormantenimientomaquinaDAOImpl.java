/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:10 ART 2010
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
* Implementation of ProveedormantenimientomaquinaDAO interface 
* 
*/


public class ProveedormantenimientomaquinaDAOImpl implements ProveedormantenimientomaquinaDAO
{


/**
* Method deletes a record from table PROVEEDORMANTENIMIENTOMAQUINA
* @param ProveedormantenimientomaquinaPK proveedormantenimientomaquinapk
* @param  Connection  con
* @return  int
*
*/


	public int delete(ProveedormantenimientomaquinaPK proveedormantenimientomaquinapk , Connection con)throws ProveedormantenimientomaquinaException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  PROVEEDORMANTENIMIENTOMAQUINA where idproveedormantenimiento = ?");
			ps.setLong(1, proveedormantenimientomaquinapk.getIdproveedormantenimiento());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new ProveedormantenimientomaquinaException(sqle);}
		catch(Exception e) {throw new ProveedormantenimientomaquinaException(e);}
	}



/**
* This method updates a record in table PROVEEDORMANTENIMIENTOMAQUINA
* @param ProveedormantenimientomaquinaPK
* @param Proveedormantenimientomaquina
* @param  Connection con
* @return   int
*/

	public int update(ProveedormantenimientomaquinaPK proveedormantenimientomaquinapk, Proveedormantenimientomaquina proveedormantenimientomaquina, Connection con)throws ProveedormantenimientomaquinaException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update PROVEEDORMANTENIMIENTOMAQUINA set NROPROVEEDOR = ? , RAZONSOCIAL = ? , RESPONSABLE = ? , TELEFONO = ? , CELULAR = ? , MAIL = ? , DOMICILIO = ? , FECHAALTA = ? , FECHABAJA = ? , CUIL = ? , CONDICIONIVA = ? , CUIT = ?  where idproveedormantenimiento = ?");
				ps.setLong(1,proveedormantenimientomaquina.getNroproveedor());
				ps.setString(2,proveedormantenimientomaquina.getRazonsocial());
				ps.setLong(3,proveedormantenimientomaquina.getResponsable());
				ps.setString(4,proveedormantenimientomaquina.getTelefono());
				ps.setString(5,proveedormantenimientomaquina.getCelular());
				ps.setString(6,proveedormantenimientomaquina.getMail());
				ps.setLong(7,proveedormantenimientomaquina.getDomicilio());
				ps.setDate(8,proveedormantenimientomaquina.getFechaalta());
				ps.setDate(9,proveedormantenimientomaquina.getFechabaja());
				ps.setString(10,proveedormantenimientomaquina.getCuil());
				ps.setLong(11,proveedormantenimientomaquina.getCondicioniva());
				ps.setString(12,proveedormantenimientomaquina.getCuit());
				ps.setLong(13,proveedormantenimientomaquinapk.getIdproveedormantenimiento());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new ProveedormantenimientomaquinaException(sqle);}
		catch(Exception e){throw new ProveedormantenimientomaquinaException(e);}
	}

/**
* This method inserts data in table PROVEEDORMANTENIMIENTOMAQUINA
*
* @param Proveedormantenimientomaquina proveedormantenimientomaquina
* @param   Connection con
* @return  ProveedormantenimientomaquinaPK
*/

	public int insert(Proveedormantenimientomaquina proveedormantenimientomaquina ,Connection con)throws ProveedormantenimientomaquinaException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into PROVEEDORMANTENIMIENTOMAQUINA( IDPROVEEDORMANTENIMIENTO, NROPROVEEDOR, RAZONSOCIAL, RESPONSABLE, TELEFONO, CELULAR, MAIL, DOMICILIO, FECHAALTA, FECHABAJA, CUIL, CONDICIONIVA, CUIT) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setLong(1,proveedormantenimientomaquina.getIdproveedormantenimiento());
				ps.setLong(2,proveedormantenimientomaquina.getNroproveedor());
				ps.setString(3,proveedormantenimientomaquina.getRazonsocial());
				ps.setLong(4,proveedormantenimientomaquina.getResponsable());
				ps.setString(5,proveedormantenimientomaquina.getTelefono());
				ps.setString(6,proveedormantenimientomaquina.getCelular());
				ps.setString(7,proveedormantenimientomaquina.getMail());
				ps.setLong(8,proveedormantenimientomaquina.getDomicilio());
				ps.setDate(9,proveedormantenimientomaquina.getFechaalta());
				ps.setDate(10,proveedormantenimientomaquina.getFechabaja());
				ps.setString(11,proveedormantenimientomaquina.getCuil());
				ps.setLong(12,proveedormantenimientomaquina.getCondicioniva());
				ps.setString(13,proveedormantenimientomaquina.getCuit());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new ProveedormantenimientomaquinaException(sqle);}
		catch(Exception e){throw new ProveedormantenimientomaquinaException(e);}
	}

/**
* 
* Returns a row from the proveedormantenimientomaquina table for the primary key passed as parameter.
* 
*/

	public Proveedormantenimientomaquina findByPrimaryKey(long idproveedormantenimiento, Connection con) throws ProveedormantenimientomaquinaException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idproveedormantenimiento, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit from proveedormantenimientomaquina where idproveedormantenimiento = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idproveedormantenimiento);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new ProveedormantenimientomaquinaException(sqle);
	  	}
	    catch(Exception e){throw new ProveedormantenimientomaquinaException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the proveedormantenimientomaquina table for the primary key object passed as parameter.
* 
* @param  ProveedormantenimientomaquinaPK proveedormantenimientomaquinapk
* @param Connection con
* @return  Proveedormantenimientomaquina
*/

	public Proveedormantenimientomaquina findByPrimaryKey(ProveedormantenimientomaquinaPK proveedormantenimientomaquinapk, Connection con) throws ProveedormantenimientomaquinaException{
		return findByPrimaryKey(proveedormantenimientomaquinapk.getIdproveedormantenimiento(), con);
	}

/**
*
* Returns all rows from proveedormantenimientomaquina table where IDPROVEEDORMANTENIMIENTO= idproveedormantenimiento
*
* @param   long  idproveedormantenimiento
* @param   Connection con
* @return  Proveedormantenimientomaquina[]
*/

	public Proveedormantenimientomaquina[] findByIdproveedormantenimiento(long idproveedormantenimiento, Connection con) throws ProveedormantenimientomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedormantenimiento, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit from proveedormantenimientomaquina where idproveedormantenimiento = ? order by idproveedormantenimiento";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idproveedormantenimiento );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedormantenimientomaquinaException(sqle);
			}
			catch(Exception e){
					throw new ProveedormantenimientomaquinaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedormantenimientomaquina table where NROPROVEEDOR= nroproveedor
*
* @param   long  nroproveedor
* @param   Connection con
* @return  Proveedormantenimientomaquina[]
*/

	public Proveedormantenimientomaquina[] findByNroproveedor(long nroproveedor, Connection con) throws ProveedormantenimientomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedormantenimiento, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit from proveedormantenimientomaquina where nroproveedor = ? order by nroproveedor";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, nroproveedor );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedormantenimientomaquinaException(sqle);
			}
			catch(Exception e){
					throw new ProveedormantenimientomaquinaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedormantenimientomaquina table where RAZONSOCIAL= razonsocial
*
* @param   String  razonsocial
* @param   Connection con
* @return  Proveedormantenimientomaquina[]
*/

	public Proveedormantenimientomaquina[] findByRazonsocial(String razonsocial, Connection con) throws ProveedormantenimientomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedormantenimiento, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit from proveedormantenimientomaquina where razonsocial = ? order by razonsocial";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, razonsocial );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedormantenimientomaquinaException(sqle);
			}
			catch(Exception e){
					throw new ProveedormantenimientomaquinaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedormantenimientomaquina table where RESPONSABLE= responsable
*
* @param   long  responsable
* @param   Connection con
* @return  Proveedormantenimientomaquina[]
*/

	public Proveedormantenimientomaquina[] findByResponsable(long responsable, Connection con) throws ProveedormantenimientomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedormantenimiento, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit from proveedormantenimientomaquina where responsable = ? order by responsable";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, responsable );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedormantenimientomaquinaException(sqle);
			}
			catch(Exception e){
					throw new ProveedormantenimientomaquinaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedormantenimientomaquina table where TELEFONO= telefono
*
* @param   String  telefono
* @param   Connection con
* @return  Proveedormantenimientomaquina[]
*/

	public Proveedormantenimientomaquina[] findByTelefono(String telefono, Connection con) throws ProveedormantenimientomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedormantenimiento, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit from proveedormantenimientomaquina where telefono = ? order by telefono";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, telefono );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedormantenimientomaquinaException(sqle);
			}
			catch(Exception e){
					throw new ProveedormantenimientomaquinaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedormantenimientomaquina table where CELULAR= celular
*
* @param   String  celular
* @param   Connection con
* @return  Proveedormantenimientomaquina[]
*/

	public Proveedormantenimientomaquina[] findByCelular(String celular, Connection con) throws ProveedormantenimientomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedormantenimiento, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit from proveedormantenimientomaquina where celular = ? order by celular";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, celular );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedormantenimientomaquinaException(sqle);
			}
			catch(Exception e){
					throw new ProveedormantenimientomaquinaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedormantenimientomaquina table where MAIL= mail
*
* @param   String  mail
* @param   Connection con
* @return  Proveedormantenimientomaquina[]
*/

	public Proveedormantenimientomaquina[] findByMail(String mail, Connection con) throws ProveedormantenimientomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedormantenimiento, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit from proveedormantenimientomaquina where mail = ? order by mail";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, mail );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedormantenimientomaquinaException(sqle);
			}
			catch(Exception e){
					throw new ProveedormantenimientomaquinaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedormantenimientomaquina table where DOMICILIO= domicilio
*
* @param   long  domicilio
* @param   Connection con
* @return  Proveedormantenimientomaquina[]
*/

	public Proveedormantenimientomaquina[] findByDomicilio(long domicilio, Connection con) throws ProveedormantenimientomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedormantenimiento, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit from proveedormantenimientomaquina where domicilio = ? order by domicilio";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, domicilio );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedormantenimientomaquinaException(sqle);
			}
			catch(Exception e){
					throw new ProveedormantenimientomaquinaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedormantenimientomaquina table where FECHAALTA= fechaalta
*
* @param   Date  fechaalta
* @param   Connection con
* @return  Proveedormantenimientomaquina[]
*/

	public Proveedormantenimientomaquina[] findByFechaalta(Date fechaalta, Connection con) throws ProveedormantenimientomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedormantenimiento, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit from proveedormantenimientomaquina where fechaalta = ? order by fechaalta";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechaalta );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedormantenimientomaquinaException(sqle);
			}
			catch(Exception e){
					throw new ProveedormantenimientomaquinaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedormantenimientomaquina table where FECHABAJA= fechabaja
*
* @param   Date  fechabaja
* @param   Connection con
* @return  Proveedormantenimientomaquina[]
*/

	public Proveedormantenimientomaquina[] findByFechabaja(Date fechabaja, Connection con) throws ProveedormantenimientomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedormantenimiento, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit from proveedormantenimientomaquina where fechabaja = ? order by fechabaja";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechabaja );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedormantenimientomaquinaException(sqle);
			}
			catch(Exception e){
					throw new ProveedormantenimientomaquinaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedormantenimientomaquina table where CUIL= cuil
*
* @param   String  cuil
* @param   Connection con
* @return  Proveedormantenimientomaquina[]
*/

	public Proveedormantenimientomaquina[] findByCuil(String cuil, Connection con) throws ProveedormantenimientomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedormantenimiento, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit from proveedormantenimientomaquina where cuil = ? order by cuil";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, cuil );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedormantenimientomaquinaException(sqle);
			}
			catch(Exception e){
					throw new ProveedormantenimientomaquinaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedormantenimientomaquina table where CONDICIONIVA= condicioniva
*
* @param   long  condicioniva
* @param   Connection con
* @return  Proveedormantenimientomaquina[]
*/

	public Proveedormantenimientomaquina[] findByCondicioniva(long condicioniva, Connection con) throws ProveedormantenimientomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedormantenimiento, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit from proveedormantenimientomaquina where condicioniva = ? order by condicioniva";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, condicioniva );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedormantenimientomaquinaException(sqle);
			}
			catch(Exception e){
					throw new ProveedormantenimientomaquinaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedormantenimientomaquina table where CUIT= cuit
*
* @param   String  cuit
* @param   Connection con
* @return  Proveedormantenimientomaquina[]
*/

	public Proveedormantenimientomaquina[] findByCuit(String cuit, Connection con) throws ProveedormantenimientomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedormantenimiento, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit from proveedormantenimientomaquina where cuit = ? order by cuit";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, cuit );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedormantenimientomaquinaException(sqle);
			}
			catch(Exception e){
					throw new ProveedormantenimientomaquinaException(e);
			}
			finally{}
	}

/**
* Returns all rows from proveedormantenimientomaquina table 
*
* @param Connection con
* @return  Proveedormantenimientomaquina[]
*
*/

	public Proveedormantenimientomaquina[] findAll( Connection con) throws ProveedormantenimientomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedormantenimiento, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit from proveedormantenimientomaquina";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedormantenimientomaquinaException(sqle);
			}
			catch(Exception e){
					throw new ProveedormantenimientomaquinaException(e);
			}
			finally{}
	}

/**
* Returns rows from proveedormantenimientomaquina table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Proveedormantenimientomaquina[]
*
*/

	public Proveedormantenimientomaquina[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws ProveedormantenimientomaquinaException{
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
					throw new ProveedormantenimientomaquinaException(sqle);
			}
			catch(Exception e){
					throw new ProveedormantenimientomaquinaException(e);
			}
			finally{}
	}

/**
* Returns rows from proveedormantenimientomaquina table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Proveedormantenimientomaquina[]
*
*/

	public Proveedormantenimientomaquina[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws ProveedormantenimientomaquinaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idproveedormantenimiento, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicioniva, cuit from proveedormantenimientomaquina";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedormantenimientomaquinaException(sqle);
			}
			catch(Exception e){
					throw new ProveedormantenimientomaquinaException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Proveedormantenimientomaquina
*
*/

	protected Proveedormantenimientomaquina fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Proveedormantenimientomaquina dto = new Proveedormantenimientomaquina();
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
* @param Proveedormantenimientomaquina dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Proveedormantenimientomaquina dto, ResultSet rs) throws SQLException
	{
		 dto.setIdproveedormantenimiento(rs.getLong("idproveedormantenimiento"));
		 dto.setNroproveedor(rs.getLong("nroproveedor"));
		 dto.setRazonsocial(rs.getString("razonsocial"));
		 dto.setResponsable(rs.getLong("responsable"));
		 dto.setTelefono(rs.getString("telefono"));
		 dto.setCelular(rs.getString("celular"));
		 dto.setMail(rs.getString("mail"));
		 dto.setDomicilio(rs.getLong("domicilio"));
		 dto.setFechaalta(rs.getDate("fechaalta"));
		 dto.setFechabaja(rs.getDate("fechabaja"));
		 dto.setCuil(rs.getString("cuil"));
		 dto.setCondicioniva(rs.getLong("condicioniva"));
		 dto.setCuit(rs.getString("cuit"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Proveedormantenimientomaquina[]
*/

	protected Proveedormantenimientomaquina[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Proveedormantenimientomaquina dto = new Proveedormantenimientomaquina();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Proveedormantenimientomaquina ret[] = new Proveedormantenimientomaquina[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
