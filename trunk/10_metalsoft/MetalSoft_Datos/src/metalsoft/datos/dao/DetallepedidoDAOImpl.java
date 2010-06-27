/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:01 ART 2010
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
* Implementation of DetallepedidoDAO interface 
* 
*/


public class DetallepedidoDAOImpl implements DetallepedidoDAO
{


/**
* Method deletes a record from table DETALLEPEDIDO
* @param DetallepedidoPK detallepedidopk
* @param  Connection  con
* @return  int
*
*/


	public int delete(DetallepedidoPK detallepedidopk , Connection con)throws DetallepedidoException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  DETALLEPEDIDO where iddetalle = ? AND idpedido = ?");
			ps.setLong(1, detallepedidopk.getIddetalle());
			ps.setLong(2, detallepedidopk.getIdpedido());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new DetallepedidoException(sqle);}
		catch(Exception e) {throw new DetallepedidoException(e);}
	}



/**
* This method updates a record in table DETALLEPEDIDO
* @param DetallepedidoPK
* @param Detallepedido
* @param  Connection con
* @return   int
*/

	public int update(DetallepedidoPK detallepedidopk, Detallepedido detallepedido, Connection con)throws DetallepedidoException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update DETALLEPEDIDO set PRECIO = ? , CANTIDAD = ? , PRODUCTO = ?  where iddetalle = ? AND idpedido = ?");
				ps.setDouble(1,detallepedido.getPrecio());
				ps.setInt(2,detallepedido.getCantidad());
				ps.setLong(3,detallepedido.getProducto());
				ps.setLong(4,detallepedidopk.getIddetalle());
				ps.setLong(5,detallepedidopk.getIdpedido());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new DetallepedidoException(sqle);}
		catch(Exception e){throw new DetallepedidoException(e);}
	}

/**
* This method inserts data in table DETALLEPEDIDO
*
* @param Detallepedido detallepedido
* @param   Connection con
* @return  DetallepedidoPK
*/

	public int insert(Detallepedido detallepedido ,Connection con)throws DetallepedidoException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into DETALLEPEDIDO( IDPEDIDO, PRECIO, CANTIDAD, PRODUCTO) values (?, ?, ?, ?)");
				ps.setLong(1,detallepedido.getIdpedido());
				ps.setDouble(2,detallepedido.getPrecio());
				ps.setInt(3,detallepedido.getCantidad());
				ps.setLong(4,detallepedido.getProducto());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new DetallepedidoException(sqle);}
		catch(Exception e){throw new DetallepedidoException(e);}
	}

/**
* 
* Returns a row from the detallepedido table for the primary key passed as parameter.
* 
*/

	public Detallepedido findByPrimaryKey(long iddetalle, long idpedido, Connection con) throws DetallepedidoException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select iddetalle, idpedido, precio, cantidad, producto from detallepedido where iddetalle = ? AND idpedido = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, iddetalle);
	  		stmt.setLong(2, idpedido);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new DetallepedidoException(sqle);
	  	}
	    catch(Exception e){throw new DetallepedidoException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the detallepedido table for the primary key object passed as parameter.
* 
* @param  DetallepedidoPK detallepedidopk
* @param Connection con
* @return  Detallepedido
*/

	public Detallepedido findByPrimaryKey(DetallepedidoPK detallepedidopk, Connection con) throws DetallepedidoException{
		return findByPrimaryKey(detallepedidopk.getIddetalle(), detallepedidopk.getIdpedido(), con);
	}

/**
*
* Returns all rows from detallepedido table where IDDETALLE= iddetalle
*
* @param   long  iddetalle
* @param   Connection con
* @return  Detallepedido[]
*/

	public Detallepedido[] findByIddetalle(long iddetalle, Connection con) throws DetallepedidoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idpedido, precio, cantidad, producto from detallepedido where iddetalle = ? order by iddetalle";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, iddetalle );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetallepedidoException(sqle);
			}
			catch(Exception e){
					throw new DetallepedidoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detallepedido table where IDPEDIDO= idpedido
*
* @param   long  idpedido
* @param   Connection con
* @return  Detallepedido[]
*/

	public Detallepedido[] findByIdpedido(long idpedido, Connection con) throws DetallepedidoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idpedido, precio, cantidad, producto from detallepedido where idpedido = ? order by idpedido";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idpedido );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetallepedidoException(sqle);
			}
			catch(Exception e){
					throw new DetallepedidoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detallepedido table where PRECIO= precio
*
* @param   double  precio
* @param   Connection con
* @return  Detallepedido[]
*/

	public Detallepedido[] findByPrecio(double precio, Connection con) throws DetallepedidoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idpedido, precio, cantidad, producto from detallepedido where precio = ? order by precio";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDouble( 1, precio );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetallepedidoException(sqle);
			}
			catch(Exception e){
					throw new DetallepedidoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detallepedido table where CANTIDAD= cantidad
*
* @param   int  cantidad
* @param   Connection con
* @return  Detallepedido[]
*/

	public Detallepedido[] findByCantidad(int cantidad, Connection con) throws DetallepedidoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idpedido, precio, cantidad, producto from detallepedido where cantidad = ? order by cantidad";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setInt( 1, cantidad );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetallepedidoException(sqle);
			}
			catch(Exception e){
					throw new DetallepedidoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from detallepedido table where PRODUCTO= producto
*
* @param   long  producto
* @param   Connection con
* @return  Detallepedido[]
*/

	public Detallepedido[] findByProducto(long producto, Connection con) throws DetallepedidoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idpedido, precio, cantidad, producto from detallepedido where producto = ? order by producto";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, producto );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetallepedidoException(sqle);
			}
			catch(Exception e){
					throw new DetallepedidoException(e);
			}
			finally{}
	}

/**
* Returns all rows from detallepedido table 
*
* @param Connection con
* @return  Detallepedido[]
*
*/

	public Detallepedido[] findAll( Connection con) throws DetallepedidoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select iddetalle, idpedido, precio, cantidad, producto from detallepedido";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetallepedidoException(sqle);
			}
			catch(Exception e){
					throw new DetallepedidoException(e);
			}
			finally{}
	}

/**
* Returns rows from detallepedido table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Detallepedido[]
*
*/

	public Detallepedido[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws DetallepedidoException{
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
					throw new DetallepedidoException(sqle);
			}
			catch(Exception e){
					throw new DetallepedidoException(e);
			}
			finally{}
	}

/**
* Returns rows from detallepedido table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Detallepedido[]
*
*/

	public Detallepedido[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws DetallepedidoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select iddetalle, idpedido, precio, cantidad, producto from detallepedido";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new DetallepedidoException(sqle);
			}
			catch(Exception e){
					throw new DetallepedidoException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Detallepedido
*
*/

	protected Detallepedido fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Detallepedido dto = new Detallepedido();
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
* @param Detallepedido dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Detallepedido dto, ResultSet rs) throws SQLException
	{
		 dto.setIddetalle(rs.getLong("iddetalle"));
		 dto.setIdpedido(rs.getLong("idpedido"));
		 dto.setPrecio(rs.getDouble("precio"));
		 dto.setCantidad(rs.getInt("cantidad"));
		 dto.setProducto(rs.getLong("producto"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Detallepedido[]
*/

	protected Detallepedido[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Detallepedido dto = new Detallepedido();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Detallepedido ret[] = new Detallepedido[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
