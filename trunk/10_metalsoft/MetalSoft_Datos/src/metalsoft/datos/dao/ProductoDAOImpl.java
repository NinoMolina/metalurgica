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
import java.util.*;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
import metalsoft.datos.idao.*;


/**
* 
* Implementation of ProductoDAO interface 
* 
*/


public class ProductoDAOImpl implements ProductoDAO
{


/**
* Method deletes a record from table PRODUCTO
* @param ProductoPK productopk
* @param  Connection  con
* @return  int
*
*/


	public int delete(ProductoPK productopk , Connection con)throws ProductoException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  PRODUCTO where idproducto = ?");
			ps.setLong(1, productopk.getIdproducto());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new ProductoException(sqle);}
		catch(Exception e) {throw new ProductoException(e);}
	}



/**
* This method updates a record in table PRODUCTO
* @param ProductoPK
* @param Producto
* @param  Connection con
* @return   int
*/

	public int update(ProductoPK productopk, Producto producto, Connection con)throws ProductoException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update PRODUCTO set NROPRODUCTO = ? , NOMBRE = ? , STOCK = ? , PRECIOUNITARIO = ? , DESCRIPCION = ? , CODBARRA = ?  where idproducto = ?");
				ps.setLong(1,producto.getNroproducto());
				ps.setString(2,producto.getNombre());
				ps.setInt(3,producto.getStock());
				ps.setDouble(4,producto.getPreciounitario());
				ps.setString(5,producto.getDescripcion());
				ps.setLong(6,producto.getCodbarra());
				ps.setLong(7,productopk.getIdproducto());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new ProductoException(sqle);}
		catch(Exception e){throw new ProductoException(e);}
	}

/**
* This method inserts data in table PRODUCTO
*
* @param Producto producto
* @param   Connection con
* @return  ProductoPK
*/

	public int insert(Producto producto ,Connection con)throws ProductoException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into PRODUCTO( NROPRODUCTO, NOMBRE, STOCK, PRECIOUNITARIO, DESCRIPCION, CODBARRA) values (?, ?, ?, ?, ?, ?)");
				ps.setLong(1,producto.getNroproducto());
				ps.setString(2,producto.getNombre());
				ps.setInt(3,producto.getStock());
				ps.setDouble(4,producto.getPreciounitario());
				ps.setString(5,producto.getDescripcion());
				ps.setLong(6,producto.getCodbarra());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new ProductoException(sqle);}
		catch(Exception e){throw new ProductoException(e);}
	}

/**
* 
* Returns a row from the producto table for the primary key passed as parameter.
* 
*/

	public Producto findByPrimaryKey(long idproducto, Connection con) throws ProductoException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idproducto, nroproducto, nombre, stock, preciounitario, descripcion, codbarra from producto where idproducto = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idproducto);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new ProductoException(sqle);
	  	}
	    catch(Exception e){throw new ProductoException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the producto table for the primary key object passed as parameter.
* 
* @param  ProductoPK productopk
* @param Connection con
* @return  Producto
*/

	public Producto findByPrimaryKey(ProductoPK productopk, Connection con) throws ProductoException{
		return findByPrimaryKey(productopk.getIdproducto(), con);
	}

/**
*
* Returns all rows from producto table where IDPRODUCTO= idproducto
*
* @param   long  idproducto
* @param   Connection con
* @return  Producto[]
*/

	public Producto[] findByIdproducto(long idproducto, Connection con) throws ProductoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproducto, nroproducto, nombre, stock, preciounitario, descripcion, codbarra from producto where idproducto = ? order by idproducto";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idproducto );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProductoException(sqle);
			}
			catch(Exception e){
					throw new ProductoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from producto table where NROPRODUCTO= nroproducto
*
* @param   long  nroproducto
* @param   Connection con
* @return  Producto[]
*/

	public Producto[] findByNroproducto(long nroproducto, Connection con) throws ProductoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproducto, nroproducto, nombre, stock, preciounitario, descripcion, codbarra from producto where nroproducto = ? order by nroproducto";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, nroproducto );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProductoException(sqle);
			}
			catch(Exception e){
					throw new ProductoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from producto table where NOMBRE= nombre
*
* @param   String  nombre
* @param   Connection con
* @return  Producto[]
*/

	public Producto[] findByNombre(String nombre, Connection con) throws ProductoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproducto, nroproducto, nombre, stock, preciounitario, descripcion, codbarra from producto where nombre = ? order by nombre";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, nombre );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProductoException(sqle);
			}
			catch(Exception e){
					throw new ProductoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from producto table where STOCK= stock
*
* @param   int  stock
* @param   Connection con
* @return  Producto[]
*/

	public Producto[] findByStock(int stock, Connection con) throws ProductoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproducto, nroproducto, nombre, stock, preciounitario, descripcion, codbarra from producto where stock = ? order by stock";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setInt( 1, stock );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProductoException(sqle);
			}
			catch(Exception e){
					throw new ProductoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from producto table where PRECIOUNITARIO= preciounitario
*
* @param   double  preciounitario
* @param   Connection con
* @return  Producto[]
*/

	public Producto[] findByPreciounitario(double preciounitario, Connection con) throws ProductoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproducto, nroproducto, nombre, stock, preciounitario, descripcion, codbarra from producto where preciounitario = ? order by preciounitario";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDouble( 1, preciounitario );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProductoException(sqle);
			}
			catch(Exception e){
					throw new ProductoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from producto table where DESCRIPCION= descripcion
*
* @param   String  descripcion
* @param   Connection con
* @return  Producto[]
*/

	public Producto[] findByDescripcion(String descripcion, Connection con) throws ProductoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproducto, nroproducto, nombre, stock, preciounitario, descripcion, codbarra from producto where descripcion = ? order by descripcion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, descripcion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProductoException(sqle);
			}
			catch(Exception e){
					throw new ProductoException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from producto table where CODBARRA= codbarra
*
* @param   long  codbarra
* @param   Connection con
* @return  Producto[]
*/

	public Producto[] findByCodbarra(long codbarra, Connection con) throws ProductoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproducto, nroproducto, nombre, stock, preciounitario, descripcion, codbarra from producto where codbarra = ? order by codbarra";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, codbarra );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProductoException(sqle);
			}
			catch(Exception e){
					throw new ProductoException(e);
			}
			finally{}
	}

/**
* Returns all rows from producto table 
*
* @param Connection con
* @return  Producto[]
*
*/

	public Producto[] findAll( Connection con) throws ProductoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idproducto, nroproducto, nombre, stock, preciounitario, descripcion, codbarra from producto";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProductoException(sqle);
			}
			catch(Exception e){
					throw new ProductoException(e);
			}
			finally{}
	}

/**
* Returns rows from producto table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Producto[]
*
*/

	public Producto[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws ProductoException{
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
					throw new ProductoException(sqle);
			}
			catch(Exception e){
					throw new ProductoException(e);
			}
			finally{}
	}

/**
* Returns rows from producto table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Producto[]
*
*/

	public Producto[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws ProductoException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idproducto, nroproducto, nombre, stock, preciounitario, descripcion, codbarra from producto";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new ProductoException(sqle);
			}
			catch(Exception e){
					throw new ProductoException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Producto
*
*/

	protected Producto fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Producto dto = new Producto();
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
* @param Producto dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Producto dto, ResultSet rs) throws SQLException
	{
		 dto.setIdproducto(rs.getLong("idproducto"));
		 dto.setNroproducto(rs.getLong("nroproducto"));
		 dto.setNombre(rs.getString("nombre"));
		 dto.setStock(rs.getInt("stock"));
		 dto.setPreciounitario(rs.getDouble("preciounitario"));
		 dto.setDescripcion(rs.getString("descripcion"));
		 dto.setCodbarra(rs.getLong("codbarra"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Producto[]
*/

	protected Producto[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Producto dto = new Producto();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Producto ret[] = new Producto[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
