/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:02 ART 2010
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
* Implementation of EmpleadoDAO interface 
* 
*/


public class EmpleadoDAOImpl implements EmpleadoDAO
{


/**
* Method deletes a record from table EMPLEADO
* @param EmpleadoPK empleadopk
* @param  Connection  con
* @return  int
*
*/


	public int delete(EmpleadoPKDB empleadopk , Connection con)throws EmpleadoException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  EMPLEADO where idempleado = ?");
			ps.setLong(1, empleadopk.getIdempleado());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new EmpleadoException(sqle);}
		catch(Exception e) {throw new EmpleadoException(e);}
	}



/**
* This method updates a record in table EMPLEADO
* @param EmpleadoPK
* @param EmpleadoDB
* @param  Connection con
* @return   int
*/

	public int update(EmpleadoPKDB empleadopk, EmpleadoDB empleado, Connection con)throws EmpleadoException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update EMPLEADO set LEGAJO = ? , FECHAINGRESO = ? , NOMBRE = ? , APELLIDO = ? , TELEFONO = ? , EMAIL = ? , DOMICILIO = ? , NRODOCUMENTO = ? , TIPODOCUMENTO = ? , CATEGORIA = ? , USUARIO = ? , FECHAEGRESO = ? , MOTIVOEGRESO = ? , CARGO = ?  where idempleado = ?");
				ps.setLong(1,empleado.getLegajo());
				ps.setDate(2,empleado.getFechaingreso());
				ps.setString(3,empleado.getNombre());
				ps.setString(4,empleado.getApellido());
				ps.setString(5,empleado.getTelefono());
				ps.setString(6,empleado.getEmail());
                long idem=empleado.getDomicilio();
                if(idem>0) ps.setLong(7,empleado.getDomicilio());
                else ps.setNull(7,java.sql.Types.NULL);
				
				ps.setLong(8,empleado.getNrodocumento());
                long idtd=empleado.getTipodocumento();
                if(idtd>0) ps.setLong(9,empleado.getTipodocumento());
                else ps.setNull(9,java.sql.Types.NULL);
				long idc=empleado.getCategoria();
                if(idc>0) ps.setLong(10,empleado.getCategoria());
                else ps.setNull(10,java.sql.Types.NULL);
				long idu=empleado.getUsuario();
                if(idu>0) ps.setLong(11,empleado.getUsuario());
                else ps.setNull(11,java.sql.Types.NULL);
				
				ps.setDate(12,empleado.getFechaegreso());
				ps.setString(13,empleado.getMotivoegreso());
                long idcar=empleado.getUsuario();
                if(idcar>0) ps.setLong(14,empleado.getCargo());
                else ps.setNull(14,java.sql.Types.NULL);
				
				ps.setLong(15,empleadopk.getIdempleado());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new EmpleadoException(sqle);}
		catch(Exception e){throw new EmpleadoException(e);}
	}

/**
* This method inserts data in table EMPLEADO
*
* @param EmpleadoDB empleado
* @param   Connection con
* @return  EmpleadoPK
*/

	public int insert(EmpleadoDB empleado ,Connection con)throws EmpleadoException {

		PreparedStatement ps = null;
        ResultSet rs=null;
		try
		{
			ps = con.prepareStatement("insert into EMPLEADO( LEGAJO, FECHAINGRESO, NOMBRE, APELLIDO, TELEFONO, EMAIL, DOMICILIO, NRODOCUMENTO, TIPODOCUMENTO, CATEGORIA, USUARIO, FECHAEGRESO, MOTIVOEGRESO, CARGO) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)RETURNING idempleado");
				ps.setLong(1,empleado.getLegajo());
				ps.setDate(2,empleado.getFechaingreso());
				ps.setString(3,empleado.getNombre());
				ps.setString(4,empleado.getApellido());
				ps.setString(5,empleado.getTelefono());
				ps.setString(6,empleado.getEmail());
				ps.setLong(7,empleado.getDomicilio());
				ps.setLong(8,empleado.getNrodocumento());
				long idtd=empleado.getTipodocumento();
                if(idtd>0) ps.setLong(9,empleado.getTipodocumento());
                else ps.setNull(9,java.sql.Types.NULL);
				long idc=empleado.getCategoria();
                if(idc>0) ps.setLong(10,empleado.getCategoria());
                else ps.setNull(10,java.sql.Types.NULL);
				long idu=empleado.getUsuario();
                if(idu>0) ps.setLong(11,empleado.getUsuario());
                else ps.setNull(11,java.sql.Types.NULL);

				ps.setDate(12,empleado.getFechaegreso());
				ps.setString(13,empleado.getMotivoegreso());
                long idcar=empleado.getUsuario();
                if(idcar>0) ps.setLong(14,empleado.getCargo());
                else ps.setNull(14,java.sql.Types.NULL);

				rs=ps.executeQuery();
                rs.next();
                return (int)rs.getLong("idempleado");
		}catch(SQLException sqle){throw new EmpleadoException(sqle);}
		catch(Exception e){throw new EmpleadoException(e);}
	}

/**
* 
* Returns a row from the empleado table for the primary key passed as parameter.
* 
*/

	public EmpleadoDB findByPrimaryKey(long idempleado, Connection con) throws EmpleadoException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo from empleado where idempleado = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idempleado);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new EmpleadoException(sqle);
	  	}
	    catch(Exception e){throw new EmpleadoException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the empleado table for the primary key object passed as parameter.
* 
* @param  EmpleadoPK empleadopk
* @param Connection con
* @return  EmpleadoDB
*/

	public EmpleadoDB findByPrimaryKey(EmpleadoPKDB empleadopk, Connection con) throws EmpleadoException{
		return findByPrimaryKey(empleadopk.getIdempleado(), con);
	}

/**
*
* Returns all rows from empleado table where IDEMPLEADO= idempleado
*
* @param   long  idempleado
* @param   Connection con
* @return  EmpleadoDB[]
*/

	public EmpleadoDB[] findByIdempleado(long idempleado, Connection con) throws EmpleadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo from empleado where idempleado = ? order by idempleado";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idempleado );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EmpleadoException(sqle);
			}
			catch(Exception e){
					throw new EmpleadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from empleado table where LEGAJO= legajo
*
* @param   long  legajo
* @param   Connection con
* @return  EmpleadoDB[]
*/

	public EmpleadoDB[] findByLegajo(long legajo, Connection con) throws EmpleadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo from empleado where legajo = ? order by legajo";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, legajo );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EmpleadoException(sqle);
			}
			catch(Exception e){
					throw new EmpleadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from empleado table where FECHAINGRESO= fechaingreso
*
* @param   Date  fechaingreso
* @param   Connection con
* @return  EmpleadoDB[]
*/

	public EmpleadoDB[] findByFechaingreso(Date fechaingreso, Connection con) throws EmpleadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo from empleado where fechaingreso = ? order by fechaingreso";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechaingreso );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EmpleadoException(sqle);
			}
			catch(Exception e){
					throw new EmpleadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from empleado table where NOMBRE= nombre
*
* @param   String  nombre
* @param   Connection con
* @return  EmpleadoDB[]
*/

	public EmpleadoDB[] findByNombre(String nombre, Connection con) throws EmpleadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo from empleado where nombre = ? order by nombre";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, nombre );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EmpleadoException(sqle);
			}
			catch(Exception e){
					throw new EmpleadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from empleado table where APELLIDO= apellido
*
* @param   String  apellido
* @param   Connection con
* @return  EmpleadoDB[]
*/

	public EmpleadoDB[] findByApellido(String apellido, Connection con) throws EmpleadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo from empleado where apellido = ? order by apellido";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, apellido );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EmpleadoException(sqle);
			}
			catch(Exception e){
					throw new EmpleadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from empleado table where TELEFONO= telefono
*
* @param   String  telefono
* @param   Connection con
* @return  EmpleadoDB[]
*/

	public EmpleadoDB[] findByTelefono(String telefono, Connection con) throws EmpleadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo from empleado where telefono = ? order by telefono";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, telefono );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EmpleadoException(sqle);
			}
			catch(Exception e){
					throw new EmpleadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from empleado table where EMAIL= email
*
* @param   String  email
* @param   Connection con
* @return  EmpleadoDB[]
*/

	public EmpleadoDB[] findByEmail(String email, Connection con) throws EmpleadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo from empleado where email = ? order by email";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, email );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EmpleadoException(sqle);
			}
			catch(Exception e){
					throw new EmpleadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from empleado table where DOMICILIO= domicilio
*
* @param   long  domicilio
* @param   Connection con
* @return  EmpleadoDB[]
*/

	public EmpleadoDB[] findByDomicilio(long domicilio, Connection con) throws EmpleadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo from empleado where domicilio = ? order by domicilio";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, domicilio );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EmpleadoException(sqle);
			}
			catch(Exception e){
					throw new EmpleadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from empleado table where NRODOCUMENTO= nrodocumento
*
* @param   int  nrodocumento
* @param   Connection con
* @return  EmpleadoDB[]
*/

	public EmpleadoDB[] findByNrodocumento(int nrodocumento, Connection con) throws EmpleadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo from empleado where nrodocumento = ? order by nrodocumento";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setInt( 1, nrodocumento );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EmpleadoException(sqle);
			}
			catch(Exception e){
					throw new EmpleadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from empleado table where TIPODOCUMENTO= tipodocumento
*
* @param   long  tipodocumento
* @param   Connection con
* @return  EmpleadoDB[]
*/

	public EmpleadoDB[] findByTipodocumento(long tipodocumento, Connection con) throws EmpleadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo from empleado where tipodocumento = ? order by tipodocumento";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, tipodocumento );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EmpleadoException(sqle);
			}
			catch(Exception e){
					throw new EmpleadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from empleado table where CATEGORIA= categoria
*
* @param   long  categoria
* @param   Connection con
* @return  EmpleadoDB[]
*/

	public EmpleadoDB[] findByCategoria(long categoria, Connection con) throws EmpleadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo from empleado where categoria = ? order by categoria";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, categoria );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EmpleadoException(sqle);
			}
			catch(Exception e){
					throw new EmpleadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from empleado table where USUARIO= usuario
*
* @param   long  usuario
* @param   Connection con
* @return  EmpleadoDB[]
*/

	public EmpleadoDB[] findByUsuario(long usuario, Connection con) throws EmpleadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo from empleado where usuario = ? order by usuario";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, usuario );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EmpleadoException(sqle);
			}
			catch(Exception e){
					throw new EmpleadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from empleado table where FECHAEGRESO= fechaegreso
*
* @param   Date  fechaegreso
* @param   Connection con
* @return  EmpleadoDB[]
*/

	public EmpleadoDB[] findByFechaegreso(Date fechaegreso, Connection con) throws EmpleadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo from empleado where fechaegreso = ? order by fechaegreso";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechaegreso );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EmpleadoException(sqle);
			}
			catch(Exception e){
					throw new EmpleadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from empleado table where MOTIVOEGRESO= motivoegreso
*
* @param   String  motivoegreso
* @param   Connection con
* @return  EmpleadoDB[]
*/

	public EmpleadoDB[] findByMotivoegreso(String motivoegreso, Connection con) throws EmpleadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo from empleado where motivoegreso = ? order by motivoegreso";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, motivoegreso );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EmpleadoException(sqle);
			}
			catch(Exception e){
					throw new EmpleadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from empleado table where CARGO= cargo
*
* @param   long  cargo
* @param   Connection con
* @return  EmpleadoDB[]
*/

	public EmpleadoDB[] findByCargo(long cargo, Connection con) throws EmpleadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo from empleado where cargo = ? order by cargo";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, cargo );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EmpleadoException(sqle);
			}
			catch(Exception e){
					throw new EmpleadoException(e);
			}
			finally{}
	}

/**
* Returns all rows from empleado table 
*
* @param Connection con
* @return  EmpleadoDB[]
*
*/

	public EmpleadoDB[] findAll( Connection con) throws EmpleadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo from empleado";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EmpleadoException(sqle);
			}
			catch(Exception e){
					throw new EmpleadoException(e);
			}
			finally{}
	}

/**
* Returns rows from empleado table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  EmpleadoDB[]
*
*/

	public EmpleadoDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws EmpleadoException{
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
					throw new EmpleadoException(sqle);
			}
			catch(Exception e){
					throw new EmpleadoException(e);
			}
			finally{}
	}

/**
* Returns rows from empleado table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  EmpleadoDB[]
*
*/

	public EmpleadoDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws EmpleadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idempleado, legajo, fechaingreso, nombre, apellido, telefono, email, domicilio, nrodocumento, tipodocumento, categoria, usuario, fechaegreso, motivoegreso, cargo from empleado";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new EmpleadoException(sqle);
			}
			catch(Exception e){
					throw new EmpleadoException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  EmpleadoDB
*
*/

	protected EmpleadoDB fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					EmpleadoDB dto = new EmpleadoDB();
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
* @param EmpleadoDB dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(EmpleadoDB dto, ResultSet rs) throws SQLException
	{
		 dto.setIdempleado(rs.getLong("idempleado"));
		 dto.setLegajo(rs.getLong("legajo"));
		 dto.setFechaingreso(rs.getDate("fechaingreso"));
		 dto.setNombre(rs.getString("nombre"));
		 dto.setApellido(rs.getString("apellido"));
		 dto.setTelefono(rs.getString("telefono"));
		 dto.setEmail(rs.getString("email"));
		 dto.setDomicilio(rs.getLong("domicilio"));
		 dto.setNrodocumento(rs.getInt("nrodocumento"));
		 dto.setTipodocumento(rs.getLong("tipodocumento"));
		 dto.setCategoria(rs.getLong("categoria"));
		 dto.setUsuario(rs.getLong("usuario"));
		 dto.setFechaegreso(rs.getDate("fechaegreso"));
		 dto.setMotivoegreso(rs.getString("motivoegreso"));
		 dto.setCargo(rs.getLong("cargo"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  EmpleadoDB[]
*/

	protected EmpleadoDB[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			EmpleadoDB dto = new EmpleadoDB();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		EmpleadoDB ret[] = new EmpleadoDB[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
