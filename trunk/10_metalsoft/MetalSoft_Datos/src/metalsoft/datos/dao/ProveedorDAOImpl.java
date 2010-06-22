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
* Implementation of ProveedorDAO interface 
* 
*/


public class ProveedorDAOImpl implements ProveedorDAO
{


/**
* Method deletes a record from table PROVEEDOR
* @param ProveedorPK proveedorpk
* @param  Connection  con
* @return  int
*
*/


	public int delete(ProveedorPK proveedorpk , Connection con)throws ProveedorException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  PROVEEDOR where idproveedor = ?");
			ps.setLong(1, proveedorpk.getIdproveedor());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new ProveedorException(sqle);}
		catch(Exception e) {throw new ProveedorException(e);}
	}



/**
* This method updates a record in table PROVEEDOR
* @param ProveedorPK
* @param Proveedor
* @param  Connection con
* @return   int
*/

	public int update(ProveedorPK proveedorpk, Proveedor proveedor, Connection con)throws ProveedorException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update PROVEEDOR set NROPROVEEDOR = ? , RAZONSOCIAL = ? , RESPONSABLE = ? , TELEFONO = ? , CELULAR = ? , MAIL = ? , DOMICILIO = ? , FECHAALTA = ? , FECHABAJA = ? , CUIL = ? , CONDICION = ? , CUIT = ?  where idproveedor = ?");
				ps.setLong(1,proveedor.getNroproveedor());
				ps.setString(2,proveedor.getRazonsocial());
				ps.setLong(3,proveedor.getResponsable());
				ps.setString(4,proveedor.getTelefono());
				ps.setString(5,proveedor.getCelular());
				ps.setString(6,proveedor.getMail());
				ps.setLong(7,proveedor.getDomicilio());
				ps.setDate(8,proveedor.getFechaalta());
				ps.setDate(9,proveedor.getFechabaja());
				ps.setString(10,proveedor.getCuil());
				ps.setLong(11,proveedor.getCondicion());
				ps.setString(12,proveedor.getCuit());
				ps.setLong(13,proveedorpk.getIdproveedor());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new ProveedorException(sqle);}
		catch(Exception e){throw new ProveedorException(e);}
	}

/**
* This method inserts data in table PROVEEDOR
*
* @param Proveedor proveedor
* @param   Connection con
* @return  ProveedorPK
*/

	public int insert(Proveedor proveedor ,Connection con)throws ProveedorException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into PROVEEDOR( IDPROVEEDOR, NROPROVEEDOR, RAZONSOCIAL, RESPONSABLE, TELEFONO, CELULAR, MAIL, DOMICILIO, FECHAALTA, FECHABAJA, CUIL, CONDICION, CUIT) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setLong(1,proveedor.getIdproveedor());
				ps.setLong(2,proveedor.getNroproveedor());
				ps.setString(3,proveedor.getRazonsocial());
				ps.setLong(4,proveedor.getResponsable());
				ps.setString(5,proveedor.getTelefono());
				ps.setString(6,proveedor.getCelular());
				ps.setString(7,proveedor.getMail());
				ps.setLong(8,proveedor.getDomicilio());
				ps.setDate(9,proveedor.getFechaalta());
				ps.setDate(10,proveedor.getFechabaja());
				ps.setString(11,proveedor.getCuil());
				ps.setLong(12,proveedor.getCondicion());
				ps.setString(13,proveedor.getCuit());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new ProveedorException(sqle);}
		catch(Exception e){throw new ProveedorException(e);}
	}

/**
* 
* Returns a row from the proveedor table for the primary key passed as parameter.
* 
*/

	public Proveedor findByPrimaryKey(long idproveedor, Connection con) throws ProveedorException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit from proveedor where idproveedor = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idproveedor);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new ProveedorException(sqle);
	  	}
	    catch(Exception e){throw new ProveedorException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the proveedor table for the primary key object passed as parameter.
* 
* @param  ProveedorPK proveedorpk
* @param Connection con
* @return  Proveedor
*/

	public Proveedor findByPrimaryKey(ProveedorPK proveedorpk, Connection con) throws ProveedorException{
		return findByPrimaryKey(proveedorpk.getIdproveedor(), con);
	}

/**
*
* Returns all rows from proveedor table where IDPROVEEDOR= idproveedor
*
* @param   long  idproveedor
* @param   Connection con
* @return  Proveedor[]
*/

	public Proveedor[] findByIdproveedor(long idproveedor, Connection con) throws ProveedorException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit from proveedor where idproveedor = ? order by idproveedor";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idproveedor );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedorException(sqle);
			}
			catch(Exception e){
					throw new ProveedorException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedor table where NROPROVEEDOR= nroproveedor
*
* @param   long  nroproveedor
* @param   Connection con
* @return  Proveedor[]
*/

	public Proveedor[] findByNroproveedor(long nroproveedor, Connection con) throws ProveedorException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit from proveedor where nroproveedor = ? order by nroproveedor";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, nroproveedor );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedorException(sqle);
			}
			catch(Exception e){
					throw new ProveedorException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedor table where RAZONSOCIAL= razonsocial
*
* @param   String  razonsocial
* @param   Connection con
* @return  Proveedor[]
*/

	public Proveedor[] findByRazonsocial(String razonsocial, Connection con) throws ProveedorException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit from proveedor where razonsocial = ? order by razonsocial";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, razonsocial );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedorException(sqle);
			}
			catch(Exception e){
					throw new ProveedorException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedor table where RESPONSABLE= responsable
*
* @param   long  responsable
* @param   Connection con
* @return  Proveedor[]
*/

	public Proveedor[] findByResponsable(long responsable, Connection con) throws ProveedorException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit from proveedor where responsable = ? order by responsable";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, responsable );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedorException(sqle);
			}
			catch(Exception e){
					throw new ProveedorException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedor table where TELEFONO= telefono
*
* @param   String  telefono
* @param   Connection con
* @return  Proveedor[]
*/

	public Proveedor[] findByTelefono(String telefono, Connection con) throws ProveedorException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit from proveedor where telefono = ? order by telefono";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, telefono );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedorException(sqle);
			}
			catch(Exception e){
					throw new ProveedorException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedor table where CELULAR= celular
*
* @param   String  celular
* @param   Connection con
* @return  Proveedor[]
*/

	public Proveedor[] findByCelular(String celular, Connection con) throws ProveedorException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit from proveedor where celular = ? order by celular";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, celular );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedorException(sqle);
			}
			catch(Exception e){
					throw new ProveedorException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedor table where MAIL= mail
*
* @param   String  mail
* @param   Connection con
* @return  Proveedor[]
*/

	public Proveedor[] findByMail(String mail, Connection con) throws ProveedorException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit from proveedor where mail = ? order by mail";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, mail );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedorException(sqle);
			}
			catch(Exception e){
					throw new ProveedorException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedor table where DOMICILIO= domicilio
*
* @param   long  domicilio
* @param   Connection con
* @return  Proveedor[]
*/

	public Proveedor[] findByDomicilio(long domicilio, Connection con) throws ProveedorException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit from proveedor where domicilio = ? order by domicilio";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, domicilio );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedorException(sqle);
			}
			catch(Exception e){
					throw new ProveedorException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedor table where FECHAALTA= fechaalta
*
* @param   Date  fechaalta
* @param   Connection con
* @return  Proveedor[]
*/

	public Proveedor[] findByFechaalta(Date fechaalta, Connection con) throws ProveedorException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit from proveedor where fechaalta = ? order by fechaalta";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechaalta );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedorException(sqle);
			}
			catch(Exception e){
					throw new ProveedorException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedor table where FECHABAJA= fechabaja
*
* @param   Date  fechabaja
* @param   Connection con
* @return  Proveedor[]
*/

	public Proveedor[] findByFechabaja(Date fechabaja, Connection con) throws ProveedorException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit from proveedor where fechabaja = ? order by fechabaja";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechabaja );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedorException(sqle);
			}
			catch(Exception e){
					throw new ProveedorException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedor table where CUIL= cuil
*
* @param   String  cuil
* @param   Connection con
* @return  Proveedor[]
*/

	public Proveedor[] findByCuil(String cuil, Connection con) throws ProveedorException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit from proveedor where cuil = ? order by cuil";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, cuil );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedorException(sqle);
			}
			catch(Exception e){
					throw new ProveedorException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedor table where CONDICION= condicion
*
* @param   long  condicion
* @param   Connection con
* @return  Proveedor[]
*/

	public Proveedor[] findByCondicion(long condicion, Connection con) throws ProveedorException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit from proveedor where condicion = ? order by condicion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, condicion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedorException(sqle);
			}
			catch(Exception e){
					throw new ProveedorException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from proveedor table where CUIT= cuit
*
* @param   String  cuit
* @param   Connection con
* @return  Proveedor[]
*/

	public Proveedor[] findByCuit(String cuit, Connection con) throws ProveedorException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit from proveedor where cuit = ? order by cuit";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, cuit );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedorException(sqle);
			}
			catch(Exception e){
					throw new ProveedorException(e);
			}
			finally{}
	}

/**
* Returns all rows from proveedor table 
*
* @param Connection con
* @return  Proveedor[]
*
*/

	public Proveedor[] findAll( Connection con) throws ProveedorException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit from proveedor";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedorException(sqle);
			}
			catch(Exception e){
					throw new ProveedorException(e);
			}
			finally{}
	}

/**
* Returns rows from proveedor table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Proveedor[]
*
*/

	public Proveedor[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws ProveedorException{
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
					throw new ProveedorException(sqle);
			}
			catch(Exception e){
					throw new ProveedorException(e);
			}
			finally{}
	}

/**
* Returns rows from proveedor table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Proveedor[]
*
*/

	public Proveedor[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws ProveedorException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idproveedor, nroproveedor, razonsocial, responsable, telefono, celular, mail, domicilio, fechaalta, fechabaja, cuil, condicion, cuit from proveedor";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProveedorException(sqle);
			}
			catch(Exception e){
					throw new ProveedorException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Proveedor
*
*/

	protected Proveedor fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Proveedor dto = new Proveedor();
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
* @param Proveedor dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Proveedor dto, ResultSet rs) throws SQLException
	{
		 dto.setIdproveedor(rs.getLong("idproveedor"));
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
		 dto.setCondicion(rs.getLong("condicion"));
		 dto.setCuit(rs.getString("cuit"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Proveedor[]
*/

	protected Proveedor[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Proveedor dto = new Proveedor();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Proveedor ret[] = new Proveedor[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
