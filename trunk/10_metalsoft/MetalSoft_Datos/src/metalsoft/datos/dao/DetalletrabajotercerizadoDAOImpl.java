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
import java.util.*;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
import metalsoft.datos.idao.*;


/**
* 
* Implementation of DetalletrabajotercerizadoDAO interface 
* 
*/


public class DetalletrabajotercerizadoDAOImpl implements DetalletrabajotercerizadoDAO
{


/**
* Method deletes a record from table DETALLETRABAJOTERCERIZADO
* @param DetalletrabajotercerizadoPK detalletrabajotercerizadopk
* @param  Connection  con
* @return  int
*
*/


	public int delete(DetalletrabajotercerizadoPK detalletrabajotercerizadopk , Connection con)throws DetalletrabajotercerizadoException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  DETALLETRABAJOTERCERIZADO where iddetalle = ? AND idtrabajotercerizado = ?");
			ps.setLong(1, detalletrabajotercerizadopk.getIddetalle());
			ps.setLong(2, detalletrabajotercerizadopk.getIdtrabajotercerizado());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new DetalletrabajotercerizadoException(sqle);}
		catch(Exception e) {throw new DetalletrabajotercerizadoException(e);}
	}



/**
* This method updates a record in table DETALLETRABAJOTERCERIZADO
* @param DetalletrabajotercerizadoPK
* @param Detalletrabajotercerizado
* @param  Connection con
* @return   int
*/

	public int update(DetalletrabajotercerizadoPK detalletrabajotercerizadopk, Detalletrabajotercerizado detalletrabajotercerizado, Connection con)throws DetalletrabajotercerizadoException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update DETALLETRABAJOTERCERIZADO set MONTOPARCIAL = ? , CANTIDAD = ? , DESCRIPCION = ? , FECHAENVIOREAL = ? , FECHAENTREGAREAL = ? , PIEZA = ? , PROCESO = ? , ESTADO = ?  where iddetalle = ? AND idtrabajotercerizado = ?");
				ps.setDouble(1,detalletrabajotercerizado.getMontoparcial());
				ps.setInt(2,detalletrabajotercerizado.getCantidad());
				ps.setString(3,detalletrabajotercerizado.getDescripcion());
				ps.setDate(4,detalletrabajotercerizado.getFechaenvioreal());
				ps.setDate(5,detalletrabajotercerizado.getFechaentregareal());
				ps.setLong(6,detalletrabajotercerizado.getPieza());
				ps.setLong(7,detalletrabajotercerizado.getProceso());
				ps.setLong(8,detalletrabajotercerizado.getEstado());
				ps.setLong(9,detalletrabajotercerizadopk.getIddetalle());
				ps.setLong(10,detalletrabajotercerizadopk.getIdtrabajotercerizado());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new DetalletrabajotercerizadoException(sqle);}
		catch(Exception e){throw new DetalletrabajotercerizadoException(e);}
	}

/**
* This method inserts data in table DETALLETRABAJOTERCERIZADO
*
* @param Detalletrabajotercerizado detalletrabajotercerizado
* @param   Connection con
* @return  DetalletrabajotercerizadoPK
*/

	public int insert(Detalletrabajotercerizado detalletrabajotercerizado ,Connection con)throws DetalletrabajotercerizadoException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into DETALLETRABAJOTERCERIZADO( IDTRABAJOTERCERIZADO, MONTOPARCIAL, CANTIDAD, DESCRIPCION, FECHAENVIOREAL, FECHAENTREGAREAL, PIEZA, PROCESO, ESTADO) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setLong(1,detalletrabajotercerizado.getIdtrabajotercerizado());
				ps.setDouble(2,detalletrabajotercerizado.getMontoparcial());
				ps.setInt(3,detalletrabajotercerizado.getCantidad());
				ps.setString(4,detalletrabajotercerizado.getDescripcion());
				ps.setDate(5,detalletrabajotercerizado.getFechaenvioreal());
				ps.setDate(6,detalletrabajotercerizado.getFechaentregareal());
				ps.setLong(7,detalletrabajotercerizado.getPieza());
				ps.setLong(8,detalletrabajotercerizado.getProceso());
				ps.setLong(9,detalletrabajotercerizado.getEstado());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new DetalletrabajotercerizadoException(sqle);}
		catch(Exception e){throw new DetalletrabajotercerizadoException(e);}
	}

/**
* 
* Returns a row from the detalletrabajotercerizado table for the primary key passed as parameter.
* 
*/

	public Detalletrabajotercerizado findByPrimaryKey(long iddetalle, long idtrabajotercerizado, Connection con) throws DetalletrabajotercerizadoException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select iddetalle, idtrabajotercerizado, montoparcial, cantidad, descripcion, fechaenvioreal, fechaentregareal, pieza, proceso, estado from detalletrabajotercerizado where iddetalle = ? AND idtrabajotercerizado = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, iddetalle);
	  		stmt.setLong(2, idtrabajotercerizado);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new DetalletrabajotercerizadoException(sqle);
	  	}
	    catch(Exception e){throw new DetalletrabajotercerizadoException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the detalletrabajotercerizado table for the primary key object passed as parameter.
* 
* @param  DetalletrabajotercerizadoPK detalletrabajotercerizadopk
* @param Connection con
* @return  Detalletrabajotercerizado
*/

	public Detalletrabajotercerizado findByPrimaryKey(DetalletrabajotercerizadoPK detalletrabajotercerizadopk, Connection con) throws DetalletrabajotercerizadoException{
		return findByPrimaryKey(detalletrabajotercerizadopk.getIddetalle(), detalletrabajotercerizadopk.getIdtrabajotercerizado(), con);
	}

/**
*
* Returns all rows from detalletrabajotercerizado table where IDDETALLE= iddetalle
*
* @param   long  iddetalle
* @param   Connection con
* @return  Detalletrabajotercerizado[]
*/

	public Detalletrabajotercerizado[] findByIddetalle(long iddetalle, Connection con) throws DetalletrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idtrabajotercerizado, montoparcial, cantidad, descripcion, fechaenvioreal, fechaentregareal, pieza, proceso, estado from detalletrabajotercerizado where iddetalle = ? order by iddetalle";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, iddetalle );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalletrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new DetalletrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalletrabajotercerizado table where IDTRABAJOTERCERIZADO= idtrabajotercerizado
*
* @param   long  idtrabajotercerizado
* @param   Connection con
* @return  Detalletrabajotercerizado[]
*/

	public Detalletrabajotercerizado[] findByIdtrabajotercerizado(long idtrabajotercerizado, Connection con) throws DetalletrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idtrabajotercerizado, montoparcial, cantidad, descripcion, fechaenvioreal, fechaentregareal, pieza, proceso, estado from detalletrabajotercerizado where idtrabajotercerizado = ? order by idtrabajotercerizado";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idtrabajotercerizado );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalletrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new DetalletrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalletrabajotercerizado table where MONTOPARCIAL= montoparcial
*
* @param   double  montoparcial
* @param   Connection con
* @return  Detalletrabajotercerizado[]
*/

	public Detalletrabajotercerizado[] findByMontoparcial(double montoparcial, Connection con) throws DetalletrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idtrabajotercerizado, montoparcial, cantidad, descripcion, fechaenvioreal, fechaentregareal, pieza, proceso, estado from detalletrabajotercerizado where montoparcial = ? order by montoparcial";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDouble( 1, montoparcial );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalletrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new DetalletrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalletrabajotercerizado table where CANTIDAD= cantidad
*
* @param   int  cantidad
* @param   Connection con
* @return  Detalletrabajotercerizado[]
*/

	public Detalletrabajotercerizado[] findByCantidad(int cantidad, Connection con) throws DetalletrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idtrabajotercerizado, montoparcial, cantidad, descripcion, fechaenvioreal, fechaentregareal, pieza, proceso, estado from detalletrabajotercerizado where cantidad = ? order by cantidad";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setInt( 1, cantidad );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalletrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new DetalletrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalletrabajotercerizado table where DESCRIPCION= descripcion
*
* @param   String  descripcion
* @param   Connection con
* @return  Detalletrabajotercerizado[]
*/

	public Detalletrabajotercerizado[] findByDescripcion(String descripcion, Connection con) throws DetalletrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idtrabajotercerizado, montoparcial, cantidad, descripcion, fechaenvioreal, fechaentregareal, pieza, proceso, estado from detalletrabajotercerizado where descripcion = ? order by descripcion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, descripcion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalletrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new DetalletrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalletrabajotercerizado table where FECHAENVIOREAL= fechaenvioreal
*
* @param   Date  fechaenvioreal
* @param   Connection con
* @return  Detalletrabajotercerizado[]
*/

	public Detalletrabajotercerizado[] findByFechaenvioreal(Date fechaenvioreal, Connection con) throws DetalletrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idtrabajotercerizado, montoparcial, cantidad, descripcion, fechaenvioreal, fechaentregareal, pieza, proceso, estado from detalletrabajotercerizado where fechaenvioreal = ? order by fechaenvioreal";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechaenvioreal );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalletrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new DetalletrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalletrabajotercerizado table where FECHAENTREGAREAL= fechaentregareal
*
* @param   Date  fechaentregareal
* @param   Connection con
* @return  Detalletrabajotercerizado[]
*/

	public Detalletrabajotercerizado[] findByFechaentregareal(Date fechaentregareal, Connection con) throws DetalletrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idtrabajotercerizado, montoparcial, cantidad, descripcion, fechaenvioreal, fechaentregareal, pieza, proceso, estado from detalletrabajotercerizado where fechaentregareal = ? order by fechaentregareal";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechaentregareal );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalletrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new DetalletrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalletrabajotercerizado table where PIEZA= pieza
*
* @param   long  pieza
* @param   Connection con
* @return  Detalletrabajotercerizado[]
*/

	public Detalletrabajotercerizado[] findByPieza(long pieza, Connection con) throws DetalletrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idtrabajotercerizado, montoparcial, cantidad, descripcion, fechaenvioreal, fechaentregareal, pieza, proceso, estado from detalletrabajotercerizado where pieza = ? order by pieza";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, pieza );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalletrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new DetalletrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalletrabajotercerizado table where PROCESO= proceso
*
* @param   long  proceso
* @param   Connection con
* @return  Detalletrabajotercerizado[]
*/

	public Detalletrabajotercerizado[] findByProceso(long proceso, Connection con) throws DetalletrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idtrabajotercerizado, montoparcial, cantidad, descripcion, fechaenvioreal, fechaentregareal, pieza, proceso, estado from detalletrabajotercerizado where proceso = ? order by proceso";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, proceso );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalletrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new DetalletrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detalletrabajotercerizado table where ESTADO= estado
*
* @param   long  estado
* @param   Connection con
* @return  Detalletrabajotercerizado[]
*/

	public Detalletrabajotercerizado[] findByEstado(long estado, Connection con) throws DetalletrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idtrabajotercerizado, montoparcial, cantidad, descripcion, fechaenvioreal, fechaentregareal, pieza, proceso, estado from detalletrabajotercerizado where estado = ? order by estado";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, estado );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalletrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new DetalletrabajotercerizadoException(e);
			}
			finally{}
	}

/**
* Returns all rows from detalletrabajotercerizado table 
*
* @param Connection con
* @return  Detalletrabajotercerizado[]
*
*/

	public Detalletrabajotercerizado[] findAll( Connection con) throws DetalletrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idtrabajotercerizado, montoparcial, cantidad, descripcion, fechaenvioreal, fechaentregareal, pieza, proceso, estado from detalletrabajotercerizado";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalletrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new DetalletrabajotercerizadoException(e);
			}
			finally{}
	}

/**
* Returns rows from detalletrabajotercerizado table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Detalletrabajotercerizado[]
*
*/

	public Detalletrabajotercerizado[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws DetalletrabajotercerizadoException{
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
					throw new DetalletrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new DetalletrabajotercerizadoException(e);
			}
			finally{}
	}

/**
* Returns rows from detalletrabajotercerizado table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Detalletrabajotercerizado[]
*
*/

	public Detalletrabajotercerizado[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws DetalletrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select iddetalle, idtrabajotercerizado, montoparcial, cantidad, descripcion, fechaenvioreal, fechaentregareal, pieza, proceso, estado from detalletrabajotercerizado";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetalletrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new DetalletrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Detalletrabajotercerizado
*
*/

	protected Detalletrabajotercerizado fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Detalletrabajotercerizado dto = new Detalletrabajotercerizado();
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
* @param Detalletrabajotercerizado dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Detalletrabajotercerizado dto, ResultSet rs) throws SQLException
	{
		 dto.setIddetalle(rs.getLong("iddetalle"));
		 dto.setIdtrabajotercerizado(rs.getLong("idtrabajotercerizado"));
		 dto.setMontoparcial(rs.getDouble("montoparcial"));
		 dto.setCantidad(rs.getInt("cantidad"));
		 dto.setDescripcion(rs.getString("descripcion"));
		 dto.setFechaenvioreal(rs.getDate("fechaenvioreal"));
		 dto.setFechaentregareal(rs.getDate("fechaentregareal"));
		 dto.setPieza(rs.getLong("pieza"));
		 dto.setProceso(rs.getLong("proceso"));
		 dto.setEstado(rs.getLong("estado"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Detalletrabajotercerizado[]
*/

	protected Detalletrabajotercerizado[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Detalletrabajotercerizado dto = new Detalletrabajotercerizado();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Detalletrabajotercerizado ret[] = new Detalletrabajotercerizado[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
