/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:05 ART 2010
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
* Implementation of TrabajotercerizadoDAO interface 
* 
*/


public class TrabajotercerizadoDAOImpl implements TrabajotercerizadoDAO
{


/**
* Method deletes a record from table TRABAJOTERCERIZADO
* @param TrabajotercerizadoPK trabajotercerizadopk
* @param  Connection  con
* @return  int
*
*/


	public int delete(TrabajotercerizadoPK trabajotercerizadopk , Connection con)throws TrabajotercerizadoException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  TRABAJOTERCERIZADO where idtrabajo = ?");
			ps.setLong(1, trabajotercerizadopk.getIdtrabajo());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new TrabajotercerizadoException(sqle);}
		catch(Exception e) {throw new TrabajotercerizadoException(e);}
	}



/**
* This method updates a record in table TRABAJOTERCERIZADO
* @param TrabajotercerizadoPK
* @param Trabajotercerizado
* @param  Connection con
* @return   int
*/

	public int update(TrabajotercerizadoPK trabajotercerizadopk, Trabajotercerizado trabajotercerizado, Connection con)throws TrabajotercerizadoException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update TRABAJOTERCERIZADO set NROTRABAJOTERCERIZADO = ? , FECHAPEDIDOCOTIZACION = ? , FECHAENTREGAESTIPULADA = ? , FECHACONFIRMACIONTRABAJO = ? , FECHACANCELACION = ? , FECHAENTREGAREAL = ? , FECHAENVIOAEMPRESA = ? , MOTIVOCANCELACION = ? , EMPRESA = ? , ESTADO = ? , PEDIDO = ?  where idtrabajo = ?");
				ps.setLong(1,trabajotercerizado.getNrotrabajotercerizado());
				ps.setDate(2,trabajotercerizado.getFechapedidocotizacion());
				ps.setDate(3,trabajotercerizado.getFechaentregaestipulada());
				ps.setDate(4,trabajotercerizado.getFechaconfirmaciontrabajo());
				ps.setDate(5,trabajotercerizado.getFechacancelacion());
				ps.setDate(6,trabajotercerizado.getFechaentregareal());
				ps.setDate(7,trabajotercerizado.getFechaenvioaempresa());
				ps.setString(8,trabajotercerizado.getMotivocancelacion());
				ps.setLong(9,trabajotercerizado.getEmpresa());
				ps.setInt(10,trabajotercerizado.getEstado());
				ps.setLong(11,trabajotercerizado.getPedido());
				ps.setLong(12,trabajotercerizadopk.getIdtrabajo());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new TrabajotercerizadoException(sqle);}
		catch(Exception e){throw new TrabajotercerizadoException(e);}
	}

/**
* This method inserts data in table TRABAJOTERCERIZADO
*
* @param Trabajotercerizado trabajotercerizado
* @param   Connection con
* @return  TrabajotercerizadoPK
*/

	public int insert(Trabajotercerizado trabajotercerizado ,Connection con)throws TrabajotercerizadoException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into TRABAJOTERCERIZADO( NROTRABAJOTERCERIZADO, FECHAPEDIDOCOTIZACION, FECHAENTREGAESTIPULADA, FECHACONFIRMACIONTRABAJO, FECHACANCELACION, FECHAENTREGAREAL, FECHAENVIOAEMPRESA, MOTIVOCANCELACION, EMPRESA, ESTADO, PEDIDO) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setLong(1,trabajotercerizado.getNrotrabajotercerizado());
				ps.setDate(2,trabajotercerizado.getFechapedidocotizacion());
				ps.setDate(3,trabajotercerizado.getFechaentregaestipulada());
				ps.setDate(4,trabajotercerizado.getFechaconfirmaciontrabajo());
				ps.setDate(5,trabajotercerizado.getFechacancelacion());
				ps.setDate(6,trabajotercerizado.getFechaentregareal());
				ps.setDate(7,trabajotercerizado.getFechaenvioaempresa());
				ps.setString(8,trabajotercerizado.getMotivocancelacion());
				ps.setLong(9,trabajotercerizado.getEmpresa());
				ps.setInt(10,trabajotercerizado.getEstado());
				ps.setLong(11,trabajotercerizado.getPedido());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new TrabajotercerizadoException(sqle);}
		catch(Exception e){throw new TrabajotercerizadoException(e);}
	}

/**
* 
* Returns a row from the Trabajotercerizado table for the primary key passed as parameter.
* 
*/

	public Trabajotercerizado findByPrimaryKey(long idtrabajo, Connection con) throws TrabajotercerizadoException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idtrabajo, nrotrabajotercerizado, fechapedidocotizacion, fechaentregaestipulada, fechaconfirmaciontrabajo, fechacancelacion, fechaentregareal, fechaenvioaempresa, motivocancelacion, empresa, estado, pedido from trabajotercerizado where idtrabajo = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idtrabajo);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new TrabajotercerizadoException(sqle);
	  	}
	    catch(Exception e){throw new TrabajotercerizadoException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the Trabajotercerizado table for the primary key object passed as parameter.
* 
* @param  TrabajotercerizadoPK trabajotercerizadopk
* @param Connection con
* @return  Trabajotercerizado
*/

	public Trabajotercerizado findByPrimaryKey(TrabajotercerizadoPK trabajotercerizadopk, Connection con) throws TrabajotercerizadoException{
		return findByPrimaryKey(trabajotercerizadopk.getIdtrabajo(), con);
	}

/**
*
* Returns all rows from trabajotercerizado table where IDTRABAJO= idtrabajo
*
* @param   long  idtrabajo
* @param   Connection con
* @return  Trabajotercerizado[]
*/

	public Trabajotercerizado[] findByIdtrabajo(long idtrabajo, Connection con) throws TrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtrabajo, nrotrabajotercerizado, fechapedidocotizacion, fechaentregaestipulada, fechaconfirmaciontrabajo, fechacancelacion, fechaentregareal, fechaenvioaempresa, motivocancelacion, empresa, estado, pedido from trabajotercerizado where idtrabajo = ? order by idtrabajo";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idtrabajo );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new TrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from trabajotercerizado table where NROTRABAJOTERCERIZADO= nrotrabajotercerizado
*
* @param   long  nrotrabajotercerizado
* @param   Connection con
* @return  Trabajotercerizado[]
*/

	public Trabajotercerizado[] findByNrotrabajotercerizado(long nrotrabajotercerizado, Connection con) throws TrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtrabajo, nrotrabajotercerizado, fechapedidocotizacion, fechaentregaestipulada, fechaconfirmaciontrabajo, fechacancelacion, fechaentregareal, fechaenvioaempresa, motivocancelacion, empresa, estado, pedido from trabajotercerizado where nrotrabajotercerizado = ? order by nrotrabajotercerizado";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, nrotrabajotercerizado );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new TrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from trabajotercerizado table where FECHAPEDIDOCOTIZACION= fechapedidocotizacion
*
* @param   Date  fechapedidocotizacion
* @param   Connection con
* @return  Trabajotercerizado[]
*/

	public Trabajotercerizado[] findByFechapedidocotizacion(Date fechapedidocotizacion, Connection con) throws TrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtrabajo, nrotrabajotercerizado, fechapedidocotizacion, fechaentregaestipulada, fechaconfirmaciontrabajo, fechacancelacion, fechaentregareal, fechaenvioaempresa, motivocancelacion, empresa, estado, pedido from trabajotercerizado where fechapedidocotizacion = ? order by fechapedidocotizacion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechapedidocotizacion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new TrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from trabajotercerizado table where FECHAENTREGAESTIPULADA= fechaentregaestipulada
*
* @param   Date  fechaentregaestipulada
* @param   Connection con
* @return  Trabajotercerizado[]
*/

	public Trabajotercerizado[] findByFechaentregaestipulada(Date fechaentregaestipulada, Connection con) throws TrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtrabajo, nrotrabajotercerizado, fechapedidocotizacion, fechaentregaestipulada, fechaconfirmaciontrabajo, fechacancelacion, fechaentregareal, fechaenvioaempresa, motivocancelacion, empresa, estado, pedido from trabajotercerizado where fechaentregaestipulada = ? order by fechaentregaestipulada";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechaentregaestipulada );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new TrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from trabajotercerizado table where FECHACONFIRMACIONTRABAJO= fechaconfirmaciontrabajo
*
* @param   Date  fechaconfirmaciontrabajo
* @param   Connection con
* @return  Trabajotercerizado[]
*/

	public Trabajotercerizado[] findByFechaconfirmaciontrabajo(Date fechaconfirmaciontrabajo, Connection con) throws TrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtrabajo, nrotrabajotercerizado, fechapedidocotizacion, fechaentregaestipulada, fechaconfirmaciontrabajo, fechacancelacion, fechaentregareal, fechaenvioaempresa, motivocancelacion, empresa, estado, pedido from trabajotercerizado where fechaconfirmaciontrabajo = ? order by fechaconfirmaciontrabajo";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechaconfirmaciontrabajo );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new TrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from trabajotercerizado table where FECHACANCELACION= fechacancelacion
*
* @param   Date  fechacancelacion
* @param   Connection con
* @return  Trabajotercerizado[]
*/

	public Trabajotercerizado[] findByFechacancelacion(Date fechacancelacion, Connection con) throws TrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtrabajo, nrotrabajotercerizado, fechapedidocotizacion, fechaentregaestipulada, fechaconfirmaciontrabajo, fechacancelacion, fechaentregareal, fechaenvioaempresa, motivocancelacion, empresa, estado, pedido from trabajotercerizado where fechacancelacion = ? order by fechacancelacion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechacancelacion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new TrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from trabajotercerizado table where FECHAENTREGAREAL= fechaentregareal
*
* @param   Date  fechaentregareal
* @param   Connection con
* @return  Trabajotercerizado[]
*/

	public Trabajotercerizado[] findByFechaentregareal(Date fechaentregareal, Connection con) throws TrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtrabajo, nrotrabajotercerizado, fechapedidocotizacion, fechaentregaestipulada, fechaconfirmaciontrabajo, fechacancelacion, fechaentregareal, fechaenvioaempresa, motivocancelacion, empresa, estado, pedido from trabajotercerizado where fechaentregareal = ? order by fechaentregareal";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechaentregareal );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new TrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from trabajotercerizado table where FECHAENVIOAEMPRESA= fechaenvioaempresa
*
* @param   Date  fechaenvioaempresa
* @param   Connection con
* @return  Trabajotercerizado[]
*/

	public Trabajotercerizado[] findByFechaenvioaempresa(Date fechaenvioaempresa, Connection con) throws TrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtrabajo, nrotrabajotercerizado, fechapedidocotizacion, fechaentregaestipulada, fechaconfirmaciontrabajo, fechacancelacion, fechaentregareal, fechaenvioaempresa, motivocancelacion, empresa, estado, pedido from trabajotercerizado where fechaenvioaempresa = ? order by fechaenvioaempresa";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechaenvioaempresa );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new TrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from trabajotercerizado table where MOTIVOCANCELACION= motivocancelacion
*
* @param   String  motivocancelacion
* @param   Connection con
* @return  Trabajotercerizado[]
*/

	public Trabajotercerizado[] findByMotivocancelacion(String motivocancelacion, Connection con) throws TrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtrabajo, nrotrabajotercerizado, fechapedidocotizacion, fechaentregaestipulada, fechaconfirmaciontrabajo, fechacancelacion, fechaentregareal, fechaenvioaempresa, motivocancelacion, empresa, estado, pedido from trabajotercerizado where motivocancelacion = ? order by motivocancelacion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, motivocancelacion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new TrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from trabajotercerizado table where EMPRESA= empresa
*
* @param   long  empresa
* @param   Connection con
* @return  Trabajotercerizado[]
*/

	public Trabajotercerizado[] findByEmpresa(long empresa, Connection con) throws TrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtrabajo, nrotrabajotercerizado, fechapedidocotizacion, fechaentregaestipulada, fechaconfirmaciontrabajo, fechacancelacion, fechaentregareal, fechaenvioaempresa, motivocancelacion, empresa, estado, pedido from trabajotercerizado where empresa = ? order by empresa";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, empresa );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new TrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from trabajotercerizado table where ESTADO= estado
*
* @param   int  estado
* @param   Connection con
* @return  Trabajotercerizado[]
*/

	public Trabajotercerizado[] findByEstado(int estado, Connection con) throws TrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtrabajo, nrotrabajotercerizado, fechapedidocotizacion, fechaentregaestipulada, fechaconfirmaciontrabajo, fechacancelacion, fechaentregareal, fechaenvioaempresa, motivocancelacion, empresa, estado, pedido from trabajotercerizado where estado = ? order by estado";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setInt( 1, estado );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new TrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from trabajotercerizado table where PEDIDO= pedido
*
* @param   long  pedido
* @param   Connection con
* @return  Trabajotercerizado[]
*/

	public Trabajotercerizado[] findByPedido(long pedido, Connection con) throws TrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtrabajo, nrotrabajotercerizado, fechapedidocotizacion, fechaentregaestipulada, fechaconfirmaciontrabajo, fechacancelacion, fechaentregareal, fechaenvioaempresa, motivocancelacion, empresa, estado, pedido from trabajotercerizado where pedido = ? order by pedido";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, pedido );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new TrabajotercerizadoException(e);
			}
			finally{}
	}

/**
* Returns all rows from Trabajotercerizado table 
*
* @param Connection con
* @return  Trabajotercerizado[]
*
*/

	public Trabajotercerizado[] findAll( Connection con) throws TrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idtrabajo, nrotrabajotercerizado, fechapedidocotizacion, fechaentregaestipulada, fechaconfirmaciontrabajo, fechacancelacion, fechaentregareal, fechaenvioaempresa, motivocancelacion, empresa, estado, pedido from trabajotercerizado";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new TrabajotercerizadoException(e);
			}
			finally{}
	}

/**
* Returns rows from Trabajotercerizado table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Trabajotercerizado[]
*
*/

	public Trabajotercerizado[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws TrabajotercerizadoException{
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
					throw new TrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new TrabajotercerizadoException(e);
			}
			finally{}
	}

/**
* Returns rows from Trabajotercerizado table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Trabajotercerizado[]
*
*/

	public Trabajotercerizado[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws TrabajotercerizadoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idtrabajo, nrotrabajotercerizado, fechapedidocotizacion, fechaentregaestipulada, fechaconfirmaciontrabajo, fechacancelacion, fechaentregareal, fechaenvioaempresa, motivocancelacion, empresa, estado, pedido from trabajotercerizado";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new TrabajotercerizadoException(sqle);
			}
			catch(Exception e){
					throw new TrabajotercerizadoException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Trabajotercerizado
*
*/

	protected Trabajotercerizado fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Trabajotercerizado dto = new Trabajotercerizado();
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
* @param Trabajotercerizado dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Trabajotercerizado dto, ResultSet rs) throws SQLException
	{
		 dto.setIdtrabajo(rs.getLong("idtrabajo"));
		 dto.setNrotrabajotercerizado(rs.getLong("nrotrabajotercerizado"));
		 dto.setFechapedidocotizacion(rs.getDate("fechapedidocotizacion"));
		 dto.setFechaentregaestipulada(rs.getDate("fechaentregaestipulada"));
		 dto.setFechaconfirmaciontrabajo(rs.getDate("fechaconfirmaciontrabajo"));
		 dto.setFechacancelacion(rs.getDate("fechacancelacion"));
		 dto.setFechaentregareal(rs.getDate("fechaentregareal"));
		 dto.setFechaenvioaempresa(rs.getDate("fechaenvioaempresa"));
		 dto.setMotivocancelacion(rs.getString("motivocancelacion"));
		 dto.setEmpresa(rs.getLong("empresa"));
		 dto.setEstado(rs.getInt("estado"));
		 dto.setPedido(rs.getLong("pedido"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Trabajotercerizado[]
*/

	protected Trabajotercerizado[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Trabajotercerizado dto = new Trabajotercerizado();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Trabajotercerizado ret[] = new Trabajotercerizado[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
