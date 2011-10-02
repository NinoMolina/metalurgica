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
* Implementation of MantenimientopreventivoDAO interface 
* 
*/


public class MantenimientopreventivoDAOImpl implements MantenimientopreventivoDAO
{


/**
* Method deletes a record from table MANTENIMIENTOPREVENTIVO
* @param MantenimientopreventivoPK mantenimientopreventivopk
* @param  Connection  con
* @return  int
*
*/


	public int delete(MantenimientopreventivoPKDB mantenimientopreventivopk , Connection con)throws MantenimientopreventivoException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  MANTENIMIENTOPREVENTIVO where idmantenimientopreventivo = ?");
			ps.setLong(1, mantenimientopreventivopk.getIdmantenimientopreventivo());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new MantenimientopreventivoException(sqle);}
		catch(Exception e) {throw new MantenimientopreventivoException(e);}
	}



/**
* This method updates a record in table MANTENIMIENTOPREVENTIVO
* @param MantenimientopreventivoPK
* @param Mantenimientopreventivo
* @param  Connection con
* @return   int
*/

	public int update(MantenimientopreventivoPKDB mantenimientopreventivopk, MantenimientopreventivoDB mantenimientopreventivo, Connection con)throws MantenimientopreventivoException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update MANTENIMIENTOPREVENTIVO set FECHAMANTENIMIENTOPREVISTO = ? , FECHAENVIOMANTENIMIENTO = ? , HORAENVIOMANTENIMIENTO = ? , PERIODO = ? , NROMANTENIMIETNO = ? , FECHAFINMANTENIMIENTOREAL = ? , PROVEEDORMANTENIMIENTO = ? , MAQUINA = ?  where idmantenimientopreventivo = ?");
				ps.setDate(1,mantenimientopreventivo.getFechamantenimientoprevisto());
				ps.setDate(2,mantenimientopreventivo.getFechaenviomantenimiento());
				ps.setTime(3,mantenimientopreventivo.getHoraenviomantenimiento());
				ps.setString(4,mantenimientopreventivo.getPeriodo());
				ps.setLong(5,mantenimientopreventivo.getNromantenimietno());
				ps.setDate(6,mantenimientopreventivo.getFechafinmantenimientoreal());
				ps.setLong(7,mantenimientopreventivo.getProveedormantenimiento());
				ps.setLong(8,mantenimientopreventivo.getMaquina());
				ps.setLong(9,mantenimientopreventivopk.getIdmantenimientopreventivo());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new MantenimientopreventivoException(sqle);}
		catch(Exception e){throw new MantenimientopreventivoException(e);}
	}

/**
* This method inserts data in table MANTENIMIENTOPREVENTIVO
*
* @param Mantenimientopreventivo mantenimientopreventivo
* @param   Connection con
* @return  MantenimientopreventivoPK
*/

	public int insert(MantenimientopreventivoDB mantenimientopreventivo ,Connection con)throws MantenimientopreventivoException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into MANTENIMIENTOPREVENTIVO( FECHAMANTENIMIENTOPREVISTO, FECHAENVIOMANTENIMIENTO, HORAENVIOMANTENIMIENTO, PERIODO, NROMANTENIMIETNO, FECHAFINMANTENIMIENTOREAL, PROVEEDORMANTENIMIENTO, MAQUINA) values (?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setDate(1,mantenimientopreventivo.getFechamantenimientoprevisto());
				ps.setDate(2,mantenimientopreventivo.getFechaenviomantenimiento());
				ps.setTime(3,mantenimientopreventivo.getHoraenviomantenimiento());
				ps.setString(4,mantenimientopreventivo.getPeriodo());
				ps.setLong(5,mantenimientopreventivo.getNromantenimietno());
				ps.setDate(6,mantenimientopreventivo.getFechafinmantenimientoreal());
				ps.setLong(7,mantenimientopreventivo.getProveedormantenimiento());
				ps.setLong(8,mantenimientopreventivo.getMaquina());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new MantenimientopreventivoException(sqle);}
		catch(Exception e){throw new MantenimientopreventivoException(e);}
	}

/**
* 
* Returns a row from the mantenimientopreventivo table for the primary key passed as parameter.
* 
*/

	public MantenimientopreventivoDB findByPrimaryKey(long idmantenimientopreventivo, Connection con) throws MantenimientopreventivoException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idmantenimientopreventivo, fechamantenimientoprevisto, fechaenviomantenimiento, horaenviomantenimiento, periodo, nromantenimietno, fechafinmantenimientoreal, proveedormantenimiento, maquina from mantenimientopreventivo where idmantenimientopreventivo = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idmantenimientopreventivo);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new MantenimientopreventivoException(sqle);
	  	}
	    catch(Exception e){throw new MantenimientopreventivoException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the mantenimientopreventivo table for the primary key object passed as parameter.
* 
* @param  MantenimientopreventivoPK mantenimientopreventivopk
* @param Connection con
* @return  Mantenimientopreventivo
*/

	public MantenimientopreventivoDB findByPrimaryKey(MantenimientopreventivoPKDB mantenimientopreventivopk, Connection con) throws MantenimientopreventivoException{
		return findByPrimaryKey(mantenimientopreventivopk.getIdmantenimientopreventivo(), con);
	}

/**
*
* Returns all rows from mantenimientopreventivo table where IDMANTENIMIENTOPREVENTIVO= idmantenimientopreventivo
*
* @param   long  idmantenimientopreventivo
* @param   Connection con
* @return  Mantenimientopreventivo[]
*/

	public MantenimientopreventivoDB[] findByIdmantenimientopreventivo(long idmantenimientopreventivo, Connection con) throws MantenimientopreventivoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmantenimientopreventivo, fechamantenimientoprevisto, fechaenviomantenimiento, horaenviomantenimiento, periodo, nromantenimietno, fechafinmantenimientoreal, proveedormantenimiento, maquina from mantenimientopreventivo where idmantenimientopreventivo = ? order by idmantenimientopreventivo";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idmantenimientopreventivo );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MantenimientopreventivoException(sqle);
			}
			catch(Exception e){
					throw new MantenimientopreventivoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from mantenimientopreventivo table where FECHAMANTENIMIENTOPREVISTO= fechamantenimientoprevisto
*
* @param   Date  fechamantenimientoprevisto
* @param   Connection con
* @return  Mantenimientopreventivo[]
*/

	public MantenimientopreventivoDB[] findByFechamantenimientoprevisto(Date fechamantenimientoprevisto, Connection con) throws MantenimientopreventivoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmantenimientopreventivo, fechamantenimientoprevisto, fechaenviomantenimiento, horaenviomantenimiento, periodo, nromantenimietno, fechafinmantenimientoreal, proveedormantenimiento, maquina from mantenimientopreventivo where fechamantenimientoprevisto = ? order by fechamantenimientoprevisto";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechamantenimientoprevisto );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MantenimientopreventivoException(sqle);
			}
			catch(Exception e){
					throw new MantenimientopreventivoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from mantenimientopreventivo table where FECHAENVIOMANTENIMIENTO= fechaenviomantenimiento
*
* @param   Date  fechaenviomantenimiento
* @param   Connection con
* @return  Mantenimientopreventivo[]
*/

	public MantenimientopreventivoDB[] findByFechaenviomantenimiento(Date fechaenviomantenimiento, Connection con) throws MantenimientopreventivoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmantenimientopreventivo, fechamantenimientoprevisto, fechaenviomantenimiento, horaenviomantenimiento, periodo, nromantenimietno, fechafinmantenimientoreal, proveedormantenimiento, maquina from mantenimientopreventivo where fechaenviomantenimiento = ? order by fechaenviomantenimiento";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechaenviomantenimiento );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MantenimientopreventivoException(sqle);
			}
			catch(Exception e){
					throw new MantenimientopreventivoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from mantenimientopreventivo table where HORAENVIOMANTENIMIENTO= horaenviomantenimiento
*
* @param   Time  horaenviomantenimiento
* @param   Connection con
* @return  Mantenimientopreventivo[]
*/

	public MantenimientopreventivoDB[] findByHoraenviomantenimiento(Time horaenviomantenimiento, Connection con) throws MantenimientopreventivoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmantenimientopreventivo, fechamantenimientoprevisto, fechaenviomantenimiento, horaenviomantenimiento, periodo, nromantenimietno, fechafinmantenimientoreal, proveedormantenimiento, maquina from mantenimientopreventivo where horaenviomantenimiento = ? order by horaenviomantenimiento";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setTime( 1, horaenviomantenimiento );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MantenimientopreventivoException(sqle);
			}
			catch(Exception e){
					throw new MantenimientopreventivoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from mantenimientopreventivo table where PERIODO= periodo
*
* @param   String  periodo
* @param   Connection con
* @return  Mantenimientopreventivo[]
*/

	public MantenimientopreventivoDB[] findByPeriodo(String periodo, Connection con) throws MantenimientopreventivoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmantenimientopreventivo, fechamantenimientoprevisto, fechaenviomantenimiento, horaenviomantenimiento, periodo, nromantenimietno, fechafinmantenimientoreal, proveedormantenimiento, maquina from mantenimientopreventivo where periodo = ? order by periodo";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, periodo );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MantenimientopreventivoException(sqle);
			}
			catch(Exception e){
					throw new MantenimientopreventivoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from mantenimientopreventivo table where NROMANTENIMIETNO= nromantenimietno
*
* @param   long  nromantenimietno
* @param   Connection con
* @return  Mantenimientopreventivo[]
*/

	public MantenimientopreventivoDB[] findByNromantenimietno(long nromantenimietno, Connection con) throws MantenimientopreventivoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmantenimientopreventivo, fechamantenimientoprevisto, fechaenviomantenimiento, horaenviomantenimiento, periodo, nromantenimietno, fechafinmantenimientoreal, proveedormantenimiento, maquina from mantenimientopreventivo where nromantenimietno = ? order by nromantenimietno";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, nromantenimietno );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MantenimientopreventivoException(sqle);
			}
			catch(Exception e){
					throw new MantenimientopreventivoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from mantenimientopreventivo table where FECHAFINMANTENIMIENTOREAL= fechafinmantenimientoreal
*
* @param   Date  fechafinmantenimientoreal
* @param   Connection con
* @return  Mantenimientopreventivo[]
*/

	public MantenimientopreventivoDB[] findByFechafinmantenimientoreal(Date fechafinmantenimientoreal, Connection con) throws MantenimientopreventivoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmantenimientopreventivo, fechamantenimientoprevisto, fechaenviomantenimiento, horaenviomantenimiento, periodo, nromantenimietno, fechafinmantenimientoreal, proveedormantenimiento, maquina from mantenimientopreventivo where fechafinmantenimientoreal = ? order by fechafinmantenimientoreal";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechafinmantenimientoreal );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MantenimientopreventivoException(sqle);
			}
			catch(Exception e){
					throw new MantenimientopreventivoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from mantenimientopreventivo table where PROVEEDORMANTENIMIENTO= proveedormantenimiento
*
* @param   long  proveedormantenimiento
* @param   Connection con
* @return  Mantenimientopreventivo[]
*/

	public MantenimientopreventivoDB[] findByProveedormantenimiento(long proveedormantenimiento, Connection con) throws MantenimientopreventivoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmantenimientopreventivo, fechamantenimientoprevisto, fechaenviomantenimiento, horaenviomantenimiento, periodo, nromantenimietno, fechafinmantenimientoreal, proveedormantenimiento, maquina from mantenimientopreventivo where proveedormantenimiento = ? order by proveedormantenimiento";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, proveedormantenimiento );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MantenimientopreventivoException(sqle);
			}
			catch(Exception e){
					throw new MantenimientopreventivoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from mantenimientopreventivo table where MAQUINA= maquina
*
* @param   long  maquina
* @param   Connection con
* @return  Mantenimientopreventivo[]
*/

	public MantenimientopreventivoDB[] findByMaquina(long maquina, Connection con) throws MantenimientopreventivoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmantenimientopreventivo, fechamantenimientoprevisto, fechaenviomantenimiento, horaenviomantenimiento, periodo, nromantenimietno, fechafinmantenimientoreal, proveedormantenimiento, maquina from mantenimientopreventivo where maquina = ? order by maquina";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, maquina );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MantenimientopreventivoException(sqle);
			}
			catch(Exception e){
					throw new MantenimientopreventivoException(e);
			}
			finally{}
	}

/**
* Returns all rows from mantenimientopreventivo table 
*
* @param Connection con
* @return  Mantenimientopreventivo[]
*
*/

	public MantenimientopreventivoDB[] findAll( Connection con) throws MantenimientopreventivoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmantenimientopreventivo, fechamantenimientoprevisto, fechaenviomantenimiento, horaenviomantenimiento, periodo, nromantenimietno, fechafinmantenimientoreal, proveedormantenimiento, maquina from mantenimientopreventivo";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MantenimientopreventivoException(sqle);
			}
			catch(Exception e){
					throw new MantenimientopreventivoException(e);
			}
			finally{}
	}

/**
* Returns rows from mantenimientopreventivo table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Mantenimientopreventivo[]
*
*/

	public MantenimientopreventivoDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws MantenimientopreventivoException{
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
					throw new MantenimientopreventivoException(sqle);
			}
			catch(Exception e){
					throw new MantenimientopreventivoException(e);
			}
			finally{}
	}

/**
* Returns rows from mantenimientopreventivo table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Mantenimientopreventivo[]
*
*/

	public MantenimientopreventivoDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws MantenimientopreventivoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idmantenimientopreventivo, fechamantenimientoprevisto, fechaenviomantenimiento, horaenviomantenimiento, periodo, nromantenimietno, fechafinmantenimientoreal, proveedormantenimiento, maquina from mantenimientopreventivo";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MantenimientopreventivoException(sqle);
			}
			catch(Exception e){
					throw new MantenimientopreventivoException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Mantenimientopreventivo
*
*/

	protected MantenimientopreventivoDB fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					MantenimientopreventivoDB dto = new MantenimientopreventivoDB();
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
* @param Mantenimientopreventivo dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(MantenimientopreventivoDB dto, ResultSet rs) throws SQLException
	{
		 dto.setIdmantenimientopreventivo(rs.getLong("idmantenimientopreventivo"));
		 dto.setFechamantenimientoprevisto(rs.getDate("fechamantenimientoprevisto"));
		 dto.setFechaenviomantenimiento(rs.getDate("fechaenviomantenimiento"));
		 dto.setHoraenviomantenimiento(rs.getTime("horaenviomantenimiento"));
		 dto.setPeriodo(rs.getString("periodo"));
		 dto.setNromantenimietno(rs.getLong("nromantenimietno"));
		 dto.setFechafinmantenimientoreal(rs.getDate("fechafinmantenimientoreal"));
		 dto.setProveedormantenimiento(rs.getLong("proveedormantenimiento"));
		 dto.setMaquina(rs.getLong("maquina"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Mantenimientopreventivo[]
*/

	protected MantenimientopreventivoDB[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			MantenimientopreventivoDB dto = new MantenimientopreventivoDB();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		MantenimientopreventivoDB ret[] = new MantenimientopreventivoDB[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}