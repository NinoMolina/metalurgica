/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:38:33 GYT 2010
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
* Implementation of ClienteDAO interface 
* 
*/


public class ClienteDAOImpl implements ClienteDAO
{


/**
* Method deletes a record from table CLIENTE
* @param ClientePK clientepk
* @param  Connection  con
* @return  int
*
*/


	public int delete(ClientePK clientepk , Connection con)throws ClienteException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  CLIENTE where idcliente = ?");
			ps.setLong(1, clientepk.getIdcliente());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new ClienteException(sqle);}
		catch(Exception e) {throw new ClienteException(e);}
	}



/**
* This method updates a record in table CLIENTE
* @param ClientePK
* @param Cliente
* @param  Connection con
* @return   int
*/

	public int update(ClientePK clientepk, Cliente cliente, Connection con)throws ClienteException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update CLIENTE set NROCLIENTE = ? , PRIORIDAD = ? , ESTADO = ? , ESMOROSO = ? , USUARIO = ? , RAZONSOCIAL = ? , RESPONSABLE = ? , TELEFONO = ? , CELULAR = ? , MAIL = ? , DOMICILIO = ? , FECHAALTA = ? , FECHABAJA = ? , CUIL = ? , CONDICIONIVA = ? , CUIT = ?  where idcliente = ?");
				ps.setLong(1,cliente.getNrocliente());
				ps.setLong(2,cliente.getPrioridad());
				ps.setLong(3,cliente.getEstado());
				ps.setBoolean(4,cliente.getEsmoroso());
				ps.setLong(5,cliente.getUsuario());
				ps.setString(6,cliente.getRazonsocial());
				ps.setLong(7,cliente.getResponsable());
				ps.setString(8,cliente.getTelefono());
				ps.setString(9,cliente.getCelular());
				ps.setString(10,cliente.getMail());
				ps.setLong(11,cliente.getDomicilio());
				ps.setDate(12,cliente.getFechaalta());
				ps.setDate(13,cliente.getFechabaja());
				ps.setString(14,cliente.getCuil());
				ps.setLong(15,cliente.getCondicioniva());
				ps.setString(16,cliente.getCuit());
				ps.setLong(17,clientepk.getIdcliente());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new ClienteException(sqle);}
		catch(Exception e){throw new ClienteException(e);}
	}

/**
* This method inserts data in table CLIENTE
*
* @param Cliente cliente
* @param   Connection con
* @return  ClientePK
*/

	public int insert(Cliente cliente ,Connection con)throws ClienteException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into CLIENTE( NROCLIENTE, IDCLIENTE, PRIORIDAD, ESTADO, ESMOROSO, USUARIO, RAZONSOCIAL, RESPONSABLE, TELEFONO, CELULAR, MAIL, DOMICILIO, FECHAALTA, FECHABAJA, CUIL, CONDICIONIVA, CUIT) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setLong(1,cliente.getNrocliente());
				ps.setLong(2,cliente.getIdcliente());
				ps.setLong(3,cliente.getPrioridad());
				ps.setLong(4,cliente.getEstado());
				ps.setBoolean(5,cliente.getEsmoroso());
				ps.setLong(6,cliente.getUsuario());
				ps.setString(7,cliente.getRazonsocial());
				ps.setLong(8,cliente.getResponsable());
				ps.setString(9,cliente.getTelefono());
				ps.setString(10,cliente.getCelular());
				ps.setString(11,cliente.getMail());
				ps.setLong(12,cliente.getDomicilio());
				ps.setDate(13,cliente.getFechaalta());
				ps.setDate(14,cliente.getFechabaja());
				ps.setString(15,cliente.getCuil());
				ps.setLong(16,cliente.getCondicioniva());
				ps.setString(17,cliente.getCuit());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new ClienteException(sqle);}
		catch(Exception e){throw new ClienteException(e);}
	}

/**
* 
* Returns a row from the cliente table for the primary key passed as parameter.
* 
*/

	public Cliente findByPrimaryKey(long idcliente, Connection con) throws ClienteException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, CUIL, condicioniva, cuit from cliente where idcliente = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idcliente);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new ClienteException(sqle);
	  	}
	    catch(Exception e){throw new ClienteException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the cliente table for the primary key object passed as parameter.
* 
* @param  ClientePK clientepk
* @param Connection con
* @return  Cliente
*/

	public Cliente findByPrimaryKey(ClientePK clientepk, Connection con) throws ClienteException{
		return findByPrimaryKey(clientepk.getIdcliente(), con);
	}

/**
*
* Returns all rows from cliente table where NROCLIENTE= nrocliente
*
* @param   long  nrocliente
* @param   Connection con
* @return  Cliente[]
*/

	public Cliente[] findByNrocliente(long nrocliente, Connection con) throws ClienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, CUIL, condicioniva, cuit from cliente where nrocliente = ? order by nrocliente";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, nrocliente );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ClienteException(sqle);
			}
			catch(Exception e){
					throw new ClienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from cliente table where IDCLIENTE= idcliente
*
* @param   long  idcliente
* @param   Connection con
* @return  Cliente[]
*/

	public Cliente[] findByIdcliente(long idcliente, Connection con) throws ClienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, CUIL, condicioniva, cuit from cliente where idcliente = ? order by idcliente";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idcliente );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ClienteException(sqle);
			}
			catch(Exception e){
					throw new ClienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from cliente table where PRIORIDAD= prioridad
*
* @param   long  prioridad
* @param   Connection con
* @return  Cliente[]
*/

	public Cliente[] findByPrioridad(long prioridad, Connection con) throws ClienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, CUIL, condicioniva, cuit from cliente where prioridad = ? order by prioridad";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, prioridad );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ClienteException(sqle);
			}
			catch(Exception e){
					throw new ClienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from cliente table where ESTADO= estado
*
* @param   long  estado
* @param   Connection con
* @return  Cliente[]
*/

	public Cliente[] findByEstado(long estado, Connection con) throws ClienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, CUIL, condicioniva, cuit from cliente where estado = ? order by estado";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, estado );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ClienteException(sqle);
			}
			catch(Exception e){
					throw new ClienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from cliente table where ESMOROSO= esmoroso
*
* @param   boolean  esmoroso
* @param   Connection con
* @return  Cliente[]
*/

	public Cliente[] findByEsmoroso(boolean esmoroso, Connection con) throws ClienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, CUIL, condicioniva, cuit from cliente where esmoroso = ? order by esmoroso";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setBoolean( 1, esmoroso );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ClienteException(sqle);
			}
			catch(Exception e){
					throw new ClienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from cliente table where USUARIO= usuario
*
* @param   long  usuario
* @param   Connection con
* @return  Cliente[]
*/

	public Cliente[] findByUsuario(long usuario, Connection con) throws ClienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, CUIL, condicioniva, cuit from cliente where usuario = ? order by usuario";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, usuario );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ClienteException(sqle);
			}
			catch(Exception e){
					throw new ClienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from cliente table where RAZONSOCIAL= razonsocial
*
* @param   String  razonsocial
* @param   Connection con
* @return  Cliente[]
*/

	public Cliente[] findByRazonsocial(String razonsocial, Connection con) throws ClienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, CUIL, condicioniva, cuit from cliente where razonsocial = ? order by razonsocial";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, razonsocial );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ClienteException(sqle);
			}
			catch(Exception e){
					throw new ClienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from cliente table where RESPONSABLE= responsable
*
* @param   long  responsable
* @param   Connection con
* @return  Cliente[]
*/

	public Cliente[] findByResponsable(long responsable, Connection con) throws ClienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, CUIL, condicioniva, cuit from cliente where responsable = ? order by responsable";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, responsable );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ClienteException(sqle);
			}
			catch(Exception e){
					throw new ClienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from cliente table where TELEFONO= telefono
*
* @param   String  telefono
* @param   Connection con
* @return  Cliente[]
*/

	public Cliente[] findByTelefono(String telefono, Connection con) throws ClienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, CUIL, condicioniva, cuit from cliente where telefono = ? order by telefono";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, telefono );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ClienteException(sqle);
			}
			catch(Exception e){
					throw new ClienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from cliente table where CELULAR= celular
*
* @param   String  celular
* @param   Connection con
* @return  Cliente[]
*/

	public Cliente[] findByCelular(String celular, Connection con) throws ClienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, CUIL, condicioniva, cuit from cliente where celular = ? order by celular";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, celular );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ClienteException(sqle);
			}
			catch(Exception e){
					throw new ClienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from cliente table where MAIL= mail
*
* @param   String  mail
* @param   Connection con
* @return  Cliente[]
*/

	public Cliente[] findByMail(String mail, Connection con) throws ClienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, CUIL, condicioniva, cuit from cliente where mail = ? order by mail";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, mail );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ClienteException(sqle);
			}
			catch(Exception e){
					throw new ClienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from cliente table where DOMICILIO= domicilio
*
* @param   long  domicilio
* @param   Connection con
* @return  Cliente[]
*/

	public Cliente[] findByDomicilio(long domicilio, Connection con) throws ClienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, CUIL, condicioniva, cuit from cliente where domicilio = ? order by domicilio";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, domicilio );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ClienteException(sqle);
			}
			catch(Exception e){
					throw new ClienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from cliente table where FECHAALTA= fechaalta
*
* @param   Date  fechaalta
* @param   Connection con
* @return  Cliente[]
*/

	public Cliente[] findByFechaalta(Date fechaalta, Connection con) throws ClienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, CUIL, condicioniva, cuit from cliente where fechaalta = ? order by fechaalta";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechaalta );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ClienteException(sqle);
			}
			catch(Exception e){
					throw new ClienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from cliente table where FECHABAJA= fechabaja
*
* @param   Date  fechabaja
* @param   Connection con
* @return  Cliente[]
*/

	public Cliente[] findByFechabaja(Date fechabaja, Connection con) throws ClienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, CUIL, condicioniva, cuit from cliente where fechabaja = ? order by fechabaja";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechabaja );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ClienteException(sqle);
			}
			catch(Exception e){
					throw new ClienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from cliente table where CUIL= cuil
*
* @param   String  cuil
* @param   Connection con
* @return  Cliente[]
*/

	public Cliente[] findByCuil(String cuil, Connection con) throws ClienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, CUIL, condicioniva, cuit from cliente where CUIL = ? order by CUIL";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, cuil );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ClienteException(sqle);
			}
			catch(Exception e){
					throw new ClienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from cliente table where CONDICIONIVA= condicioniva
*
* @param   long  condicioniva
* @param   Connection con
* @return  Cliente[]
*/

	public Cliente[] findByCondicioniva(long condicioniva, Connection con) throws ClienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, CUIL, condicioniva, cuit from cliente where condicioniva = ? order by condicioniva";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, condicioniva );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ClienteException(sqle);
			}
			catch(Exception e){
					throw new ClienteException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from cliente table where CUIT= cuit
*
* @param   String  cuit
* @param   Connection con
* @return  Cliente[]
*/

	public Cliente[] findByCuit(String cuit, Connection con) throws ClienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, CUIL, condicioniva, cuit from cliente where cuit = ? order by cuit";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, cuit );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ClienteException(sqle);
			}
			catch(Exception e){
					throw new ClienteException(e);
			}
			finally{}
	}

/**
* Returns all rows from cliente table 
*
* @param Connection con
* @return  Cliente[]
*
*/

	public Cliente[] findAll( Connection con) throws ClienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, CUIL, condicioniva, cuit from cliente";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ClienteException(sqle);
			}
			catch(Exception e){
					throw new ClienteException(e);
			}
			finally{}
	}

/**
* Returns rows from cliente table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Cliente[]
*
*/

	public Cliente[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws ClienteException{
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
					throw new ClienteException(sqle);
			}
			catch(Exception e){
					throw new ClienteException(e);
			}
			finally{}
	}

/**
* Returns rows from cliente table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Cliente[]
*
*/

	public Cliente[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws ClienteException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select nrocliente, idcliente, prioridad, estado, esmoroso, usuario, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, CUIL, condicioniva, cuit from cliente";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ClienteException(sqle);
			}
			catch(Exception e){
					throw new ClienteException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Cliente
*
*/

	protected Cliente fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Cliente dto = new Cliente();
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
* @param Cliente dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Cliente dto, ResultSet rs) throws SQLException
	{
		 dto.setNrocliente(rs.getLong("nrocliente"));
		 dto.setIdcliente(rs.getLong("idcliente"));
		 dto.setPrioridad(rs.getLong("prioridad"));
		 dto.setEstado(rs.getLong("estado"));
		 dto.setEsmoroso(rs.getBoolean("esmoroso"));
		 dto.setUsuario(rs.getLong("usuario"));
		 dto.setRazonsocial(rs.getString("razonsocial"));
		 dto.setResponsable(rs.getLong("responsable"));
		 dto.setTelefono(rs.getString("telefono"));
		 dto.setCelular(rs.getString("celular"));
		 dto.setMail(rs.getString("mail"));
		 dto.setDomicilio(rs.getLong("domicilio"));
		 dto.setFechaalta(rs.getDate("fechaalta"));
		 dto.setFechabaja(rs.getDate("fechabaja"));
		 dto.setCuil(rs.getString("CUIL"));
		 dto.setCondicioniva(rs.getLong("condicioniva"));
		 dto.setCuit(rs.getString("cuit"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Cliente[]
*/

	protected Cliente[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Cliente dto = new Cliente();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Cliente ret[] = new Cliente[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
