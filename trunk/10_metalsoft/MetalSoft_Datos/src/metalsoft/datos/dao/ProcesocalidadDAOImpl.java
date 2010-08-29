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
* Implementation of ProcesocalidadDAO interface 
* 
*/


public class ProcesocalidadDAOImpl implements ProcesocalidadDAO
{


/**
* Method deletes a record from table PROCESOCALIDAD
* @param ProcesocalidadPK procesocalidadpk
* @param  Connection  con
* @return  int
*
*/


	public int delete(ProcesocalidadPK procesocalidadpk , Connection con)throws ProcesocalidadException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  PROCESOCALIDAD where idprocesocalidad = ?");
			ps.setLong(1, procesocalidadpk.getIdprocesocalidad());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new ProcesocalidadException(sqle);}
		catch(Exception e) {throw new ProcesocalidadException(e);}
	}



/**
* This method updates a record in table PROCESOCALIDAD
* @param ProcesocalidadPK
* @param ProcesocalidadDB
* @param  Connection con
* @return   int
*/

	public int update(ProcesocalidadPK procesocalidadpk, ProcesocalidadDB procesocalidad, Connection con)throws ProcesocalidadException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update PROCESOCALIDAD set NOMBRE = ? , NROPROCESO = ? , ESPECIFICACION = ? , TOLERANCIA = ? , DESCRIPCION = ? , DURACIONESTIMADA = ? , FECHACREACION = ? , HERRAMIENTA = ? , ACCIONCALIDAD = ?  where idprocesocalidad = ?");
				ps.setString(1,procesocalidad.getNombre());
				ps.setLong(2,procesocalidad.getNroproceso());
				ps.setString(3,procesocalidad.getEspecificacion());
				ps.setString(4,procesocalidad.getTolerancia());
				ps.setString(5,procesocalidad.getDescripcion());
				ps.setTime(6,procesocalidad.getDuracionestimada());
				ps.setDate(7,procesocalidad.getFechacreacion());
				ps.setString(8,procesocalidad.getHerramienta());
				ps.setLong(9,procesocalidad.getAccioncalidad());
				ps.setLong(10,procesocalidadpk.getIdprocesocalidad());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new ProcesocalidadException(sqle);}
		catch(Exception e){throw new ProcesocalidadException(e);}
	}

/**
* This method inserts data in table PROCESOCALIDAD
*
* @param ProcesocalidadDB procesocalidad
* @param   Connection con
* @return  ProcesocalidadPK
*/

	public int insert(ProcesocalidadDB procesocalidad ,Connection con)throws ProcesocalidadException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into PROCESOCALIDAD( NOMBRE, NROPROCESO, ESPECIFICACION, TOLERANCIA, DESCRIPCION, DURACIONESTIMADA, FECHACREACION, HERRAMIENTA, ACCIONCALIDAD) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setString(1,procesocalidad.getNombre());
				ps.setLong(2,procesocalidad.getNroproceso());
				ps.setString(3,procesocalidad.getEspecificacion());
				ps.setString(4,procesocalidad.getTolerancia());
				ps.setString(5,procesocalidad.getDescripcion());
				ps.setTime(6,procesocalidad.getDuracionestimada());
				ps.setDate(7,procesocalidad.getFechacreacion());
				ps.setString(8,procesocalidad.getHerramienta());
				ps.setLong(9,procesocalidad.getAccioncalidad());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new ProcesocalidadException(sqle);}
		catch(Exception e){throw new ProcesocalidadException(e);}
	}

/**
* 
* Returns a row from the procesocalidad table for the primary key passed as parameter.
* 
*/

	public ProcesocalidadDB findByPrimaryKey(long idprocesocalidad, Connection con) throws ProcesocalidadException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad from procesocalidad where idprocesocalidad = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idprocesocalidad);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new ProcesocalidadException(sqle);
	  	}
	    catch(Exception e){throw new ProcesocalidadException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the procesocalidad table for the primary key object passed as parameter.
* 
* @param  ProcesocalidadPK procesocalidadpk
* @param Connection con
* @return  ProcesocalidadDB
*/

	public ProcesocalidadDB findByPrimaryKey(ProcesocalidadPK procesocalidadpk, Connection con) throws ProcesocalidadException{
		return findByPrimaryKey(procesocalidadpk.getIdprocesocalidad(), con);
	}

/**
*
* Returns all rows from procesocalidad table where IDPROCESOCALIDAD= idprocesocalidad
*
* @param   long  idprocesocalidad
* @param   Connection con
* @return  ProcesocalidadDB[]
*/

	public ProcesocalidadDB[] findByIdprocesocalidad(long idprocesocalidad, Connection con) throws ProcesocalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad from procesocalidad where idprocesocalidad = ? order by idprocesocalidad";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idprocesocalidad );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProcesocalidadException(sqle);
			}
			catch(Exception e){
					throw new ProcesocalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from procesocalidad table where NOMBRE= nombre
*
* @param   String  nombre
* @param   Connection con
* @return  ProcesocalidadDB[]
*/

	public ProcesocalidadDB[] findByNombre(String nombre, Connection con) throws ProcesocalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad from procesocalidad where nombre = ? order by nombre";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, nombre );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProcesocalidadException(sqle);
			}
			catch(Exception e){
					throw new ProcesocalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from procesocalidad table where NROPROCESO= nroproceso
*
* @param   long  nroproceso
* @param   Connection con
* @return  ProcesocalidadDB[]
*/

	public ProcesocalidadDB[] findByNroproceso(long nroproceso, Connection con) throws ProcesocalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad from procesocalidad where nroproceso = ? order by nroproceso";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, nroproceso );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProcesocalidadException(sqle);
			}
			catch(Exception e){
					throw new ProcesocalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from procesocalidad table where ESPECIFICACION= especificacion
*
* @param   String  especificacion
* @param   Connection con
* @return  ProcesocalidadDB[]
*/

	public ProcesocalidadDB[] findByEspecificacion(String especificacion, Connection con) throws ProcesocalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad from procesocalidad where especificacion = ? order by especificacion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, especificacion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProcesocalidadException(sqle);
			}
			catch(Exception e){
					throw new ProcesocalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from procesocalidad table where TOLERANCIA= tolerancia
*
* @param   String  tolerancia
* @param   Connection con
* @return  ProcesocalidadDB[]
*/

	public ProcesocalidadDB[] findByTolerancia(String tolerancia, Connection con) throws ProcesocalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad from procesocalidad where tolerancia = ? order by tolerancia";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, tolerancia );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProcesocalidadException(sqle);
			}
			catch(Exception e){
					throw new ProcesocalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from procesocalidad table where DESCRIPCION= descripcion
*
* @param   String  descripcion
* @param   Connection con
* @return  ProcesocalidadDB[]
*/

	public ProcesocalidadDB[] findByDescripcion(String descripcion, Connection con) throws ProcesocalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad from procesocalidad where descripcion = ? order by descripcion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, descripcion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProcesocalidadException(sqle);
			}
			catch(Exception e){
					throw new ProcesocalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from procesocalidad table where DURACIONESTIMADA= duracionestimada
*
* @param   Time  duracionestimada
* @param   Connection con
* @return  ProcesocalidadDB[]
*/

	public ProcesocalidadDB[] findByDuracionestimada(Time duracionestimada, Connection con) throws ProcesocalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad from procesocalidad where duracionestimada = ? order by duracionestimada";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setTime( 1, duracionestimada );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProcesocalidadException(sqle);
			}
			catch(Exception e){
					throw new ProcesocalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from procesocalidad table where FECHACREACION= fechacreacion
*
* @param   Date  fechacreacion
* @param   Connection con
* @return  ProcesocalidadDB[]
*/

	public ProcesocalidadDB[] findByFechacreacion(Date fechacreacion, Connection con) throws ProcesocalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad from procesocalidad where fechacreacion = ? order by fechacreacion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechacreacion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProcesocalidadException(sqle);
			}
			catch(Exception e){
					throw new ProcesocalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from procesocalidad table where HERRAMIENTA= herramienta
*
* @param   String  herramienta
* @param   Connection con
* @return  ProcesocalidadDB[]
*/

	public ProcesocalidadDB[] findByHerramienta(String herramienta, Connection con) throws ProcesocalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad from procesocalidad where herramienta = ? order by herramienta";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, herramienta );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProcesocalidadException(sqle);
			}
			catch(Exception e){
					throw new ProcesocalidadException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from procesocalidad table where ACCIONCALIDAD= accioncalidad
*
* @param   long  accioncalidad
* @param   Connection con
* @return  ProcesocalidadDB[]
*/

	public ProcesocalidadDB[] findByAccioncalidad(long accioncalidad, Connection con) throws ProcesocalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad from procesocalidad where accioncalidad = ? order by accioncalidad";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, accioncalidad );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProcesocalidadException(sqle);
			}
			catch(Exception e){
					throw new ProcesocalidadException(e);
			}
			finally{}
	}

/**
* Returns all rows from procesocalidad table 
*
* @param Connection con
* @return  ProcesocalidadDB[]
*
*/

	public ProcesocalidadDB[] findAll( Connection con) throws ProcesocalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad from procesocalidad";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProcesocalidadException(sqle);
			}
			catch(Exception e){
					throw new ProcesocalidadException(e);
			}
			finally{}
	}

/**
* Returns rows from procesocalidad table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  ProcesocalidadDB[]
*
*/

	public ProcesocalidadDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws ProcesocalidadException{
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
					throw new ProcesocalidadException(sqle);
			}
			catch(Exception e){
					throw new ProcesocalidadException(e);
			}
			finally{}
	}

/**
* Returns rows from procesocalidad table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  ProcesocalidadDB[]
*
*/

	public ProcesocalidadDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws ProcesocalidadException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idprocesocalidad, nombre, nroproceso, especificacion, tolerancia, descripcion, duracionestimada, fechacreacion, herramienta, accioncalidad from procesocalidad";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProcesocalidadException(sqle);
			}
			catch(Exception e){
					throw new ProcesocalidadException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  ProcesocalidadDB
*
*/

	protected ProcesocalidadDB fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					ProcesocalidadDB dto = new ProcesocalidadDB();
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
* @param ProcesocalidadDB dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(ProcesocalidadDB dto, ResultSet rs) throws SQLException
	{
		 dto.setIdprocesocalidad(rs.getLong("idprocesocalidad"));
		 dto.setNombre(rs.getString("nombre"));
		 dto.setNroproceso(rs.getLong("nroproceso"));
		 dto.setEspecificacion(rs.getString("especificacion"));
		 dto.setTolerancia(rs.getString("tolerancia"));
		 dto.setDescripcion(rs.getString("descripcion"));
		 dto.setDuracionestimada(rs.getTime("duracionestimada"));
		 dto.setFechacreacion(rs.getDate("fechacreacion"));
		 dto.setHerramienta(rs.getString("herramienta"));
		 dto.setAccioncalidad(rs.getLong("accioncalidad"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  ProcesocalidadDB[]
*/

	protected ProcesocalidadDB[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			ProcesocalidadDB dto = new ProcesocalidadDB();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		ProcesocalidadDB ret[] = new ProcesocalidadDB[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
