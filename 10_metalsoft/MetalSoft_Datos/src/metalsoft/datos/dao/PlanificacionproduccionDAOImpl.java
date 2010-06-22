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
* Implementation of PlanificacionproduccionDAO interface 
* 
*/


public class PlanificacionproduccionDAOImpl implements PlanificacionproduccionDAO
{


/**
* Method deletes a record from table PLANIFICACIONPRODUCCION
* @param PlanificacionproduccionPK planificacionproduccionpk
* @param  Connection  con
* @return  int
*
*/


	public int delete(PlanificacionproduccionPK planificacionproduccionpk , Connection con)throws PlanificacionproduccionException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  PLANIFICACIONPRODUCCION where idplanificacionproduccion = ?");
			ps.setLong(1, planificacionproduccionpk.getIdplanificacionproduccion());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new PlanificacionproduccionException(sqle);}
		catch(Exception e) {throw new PlanificacionproduccionException(e);}
	}



/**
* This method updates a record in table PLANIFICACIONPRODUCCION
* @param PlanificacionproduccionPK
* @param Planificacionproduccion
* @param  Connection con
* @return   int
*/

	public int update(PlanificacionproduccionPK planificacionproduccionpk, Planificacionproduccion planificacionproduccion, Connection con)throws PlanificacionproduccionException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update PLANIFICACIONPRODUCCION set NROPLANIFICACION = ? , FECHACREACION = ? , OBSERVACIONES = ? , FECHAINICIOPREVISTA = ? , FECHAFINPREVISTA = ? , PEDIDO = ?  where idplanificacionproduccion = ?");
				ps.setLong(1,planificacionproduccion.getNroplanificacion());
				ps.setDate(2,planificacionproduccion.getFechacreacion());
				ps.setString(3,planificacionproduccion.getObservaciones());
				ps.setDate(4,planificacionproduccion.getFechainicioprevista());
				ps.setDate(5,planificacionproduccion.getFechafinprevista());
				ps.setLong(6,planificacionproduccion.getPedido());
				ps.setLong(7,planificacionproduccionpk.getIdplanificacionproduccion());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new PlanificacionproduccionException(sqle);}
		catch(Exception e){throw new PlanificacionproduccionException(e);}
	}

/**
* This method inserts data in table PLANIFICACIONPRODUCCION
*
* @param Planificacionproduccion planificacionproduccion
* @param   Connection con
* @return  PlanificacionproduccionPK
*/

	public int insert(Planificacionproduccion planificacionproduccion ,Connection con)throws PlanificacionproduccionException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into PLANIFICACIONPRODUCCION( IDPLANIFICACIONPRODUCCION, NROPLANIFICACION, FECHACREACION, OBSERVACIONES, FECHAINICIOPREVISTA, FECHAFINPREVISTA, PEDIDO) values (?, ?, ?, ?, ?, ?, ?)");
				ps.setLong(1,planificacionproduccion.getIdplanificacionproduccion());
				ps.setLong(2,planificacionproduccion.getNroplanificacion());
				ps.setDate(3,planificacionproduccion.getFechacreacion());
				ps.setString(4,planificacionproduccion.getObservaciones());
				ps.setDate(5,planificacionproduccion.getFechainicioprevista());
				ps.setDate(6,planificacionproduccion.getFechafinprevista());
				ps.setLong(7,planificacionproduccion.getPedido());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new PlanificacionproduccionException(sqle);}
		catch(Exception e){throw new PlanificacionproduccionException(e);}
	}

/**
* 
* Returns a row from the planificacionproduccion table for the primary key passed as parameter.
* 
*/

	public Planificacionproduccion findByPrimaryKey(long idplanificacionproduccion, Connection con) throws PlanificacionproduccionException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido from planificacionproduccion where idplanificacionproduccion = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idplanificacionproduccion);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new PlanificacionproduccionException(sqle);
	  	}
	    catch(Exception e){throw new PlanificacionproduccionException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the planificacionproduccion table for the primary key object passed as parameter.
* 
* @param  PlanificacionproduccionPK planificacionproduccionpk
* @param Connection con
* @return  Planificacionproduccion
*/

	public Planificacionproduccion findByPrimaryKey(PlanificacionproduccionPK planificacionproduccionpk, Connection con) throws PlanificacionproduccionException{
		return findByPrimaryKey(planificacionproduccionpk.getIdplanificacionproduccion(), con);
	}

/**
*
* Returns all rows from planificacionproduccion table where IDPLANIFICACIONPRODUCCION= idplanificacionproduccion
*
* @param   long  idplanificacionproduccion
* @param   Connection con
* @return  Planificacionproduccion[]
*/

	public Planificacionproduccion[] findByIdplanificacionproduccion(long idplanificacionproduccion, Connection con) throws PlanificacionproduccionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido from planificacionproduccion where idplanificacionproduccion = ? order by idplanificacionproduccion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idplanificacionproduccion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PlanificacionproduccionException(sqle);
			}
			catch(Exception e){
					throw new PlanificacionproduccionException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from planificacionproduccion table where NROPLANIFICACION= nroplanificacion
*
* @param   long  nroplanificacion
* @param   Connection con
* @return  Planificacionproduccion[]
*/

	public Planificacionproduccion[] findByNroplanificacion(long nroplanificacion, Connection con) throws PlanificacionproduccionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido from planificacionproduccion where nroplanificacion = ? order by nroplanificacion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, nroplanificacion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PlanificacionproduccionException(sqle);
			}
			catch(Exception e){
					throw new PlanificacionproduccionException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from planificacionproduccion table where FECHACREACION= fechacreacion
*
* @param   Date  fechacreacion
* @param   Connection con
* @return  Planificacionproduccion[]
*/

	public Planificacionproduccion[] findByFechacreacion(Date fechacreacion, Connection con) throws PlanificacionproduccionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido from planificacionproduccion where fechacreacion = ? order by fechacreacion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechacreacion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PlanificacionproduccionException(sqle);
			}
			catch(Exception e){
					throw new PlanificacionproduccionException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from planificacionproduccion table where OBSERVACIONES= observaciones
*
* @param   String  observaciones
* @param   Connection con
* @return  Planificacionproduccion[]
*/

	public Planificacionproduccion[] findByObservaciones(String observaciones, Connection con) throws PlanificacionproduccionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido from planificacionproduccion where observaciones = ? order by observaciones";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, observaciones );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PlanificacionproduccionException(sqle);
			}
			catch(Exception e){
					throw new PlanificacionproduccionException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from planificacionproduccion table where FECHAINICIOPREVISTA= fechainicioprevista
*
* @param   Date  fechainicioprevista
* @param   Connection con
* @return  Planificacionproduccion[]
*/

	public Planificacionproduccion[] findByFechainicioprevista(Date fechainicioprevista, Connection con) throws PlanificacionproduccionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido from planificacionproduccion where fechainicioprevista = ? order by fechainicioprevista";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechainicioprevista );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PlanificacionproduccionException(sqle);
			}
			catch(Exception e){
					throw new PlanificacionproduccionException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from planificacionproduccion table where FECHAFINPREVISTA= fechafinprevista
*
* @param   Date  fechafinprevista
* @param   Connection con
* @return  Planificacionproduccion[]
*/

	public Planificacionproduccion[] findByFechafinprevista(Date fechafinprevista, Connection con) throws PlanificacionproduccionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido from planificacionproduccion where fechafinprevista = ? order by fechafinprevista";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechafinprevista );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PlanificacionproduccionException(sqle);
			}
			catch(Exception e){
					throw new PlanificacionproduccionException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from planificacionproduccion table where PEDIDO= pedido
*
* @param   long  pedido
* @param   Connection con
* @return  Planificacionproduccion[]
*/

	public Planificacionproduccion[] findByPedido(long pedido, Connection con) throws PlanificacionproduccionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido from planificacionproduccion where pedido = ? order by pedido";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, pedido );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PlanificacionproduccionException(sqle);
			}
			catch(Exception e){
					throw new PlanificacionproduccionException(e);
			}
			finally{}
	}

/**
* Returns all rows from planificacionproduccion table 
*
* @param Connection con
* @return  Planificacionproduccion[]
*
*/

	public Planificacionproduccion[] findAll( Connection con) throws PlanificacionproduccionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido from planificacionproduccion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PlanificacionproduccionException(sqle);
			}
			catch(Exception e){
					throw new PlanificacionproduccionException(e);
			}
			finally{}
	}

/**
* Returns rows from planificacionproduccion table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Planificacionproduccion[]
*
*/

	public Planificacionproduccion[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws PlanificacionproduccionException{
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
					throw new PlanificacionproduccionException(sqle);
			}
			catch(Exception e){
					throw new PlanificacionproduccionException(e);
			}
			finally{}
	}

/**
* Returns rows from planificacionproduccion table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Planificacionproduccion[]
*
*/

	public Planificacionproduccion[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws PlanificacionproduccionException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idplanificacionproduccion, nroplanificacion, fechacreacion, observaciones, fechainicioprevista, fechafinprevista, pedido from planificacionproduccion";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PlanificacionproduccionException(sqle);
			}
			catch(Exception e){
					throw new PlanificacionproduccionException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Planificacionproduccion
*
*/

	protected Planificacionproduccion fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Planificacionproduccion dto = new Planificacionproduccion();
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
* @param Planificacionproduccion dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Planificacionproduccion dto, ResultSet rs) throws SQLException
	{
		 dto.setIdplanificacionproduccion(rs.getLong("idplanificacionproduccion"));
		 dto.setNroplanificacion(rs.getLong("nroplanificacion"));
		 dto.setFechacreacion(rs.getDate("fechacreacion"));
		 dto.setObservaciones(rs.getString("observaciones"));
		 dto.setFechainicioprevista(rs.getDate("fechainicioprevista"));
		 dto.setFechafinprevista(rs.getDate("fechafinprevista"));
		 dto.setPedido(rs.getLong("pedido"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Planificacionproduccion[]
*/

	protected Planificacionproduccion[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Planificacionproduccion dto = new Planificacionproduccion();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Planificacionproduccion ret[] = new Planificacionproduccion[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
