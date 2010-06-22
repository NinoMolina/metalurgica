/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:02:09 ART 2010
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
* Implementation of MateriaprimaDAO interface 
* 
*/


public class MateriaprimaDAOImpl implements MateriaprimaDAO
{


/**
* Method deletes a record from table MATERIAPRIMA
* @param MateriaprimaPK materiaprimapk
* @param  Connection  con
* @return  int
*
*/


	public int delete(MateriaprimaPK materiaprimapk , Connection con)throws MateriaprimaException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  MATERIAPRIMA where idmateriaprima = ?");
			ps.setLong(1, materiaprimapk.getIdmateriaprima());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new MateriaprimaException(sqle);}
		catch(Exception e) {throw new MateriaprimaException(e);}
	}



/**
* This method updates a record in table MATERIAPRIMA
* @param MateriaprimaPK
* @param Materiaprima
* @param  Connection con
* @return   int
*/

	public int update(MateriaprimaPK materiaprimapk, Materiaprima materiaprima, Connection con)throws MateriaprimaException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update MATERIAPRIMA set CODPRODUCTO = ? , NOMBRE = ? , PRECIO = ? , PROVEEDOR = ? , FECHAALTA = ? , FECHABAJA = ? , CODBARRA = ?  where idmateriaprima = ?");
				ps.setLong(1,materiaprima.getCodproducto());
				ps.setString(2,materiaprima.getNombre());
				ps.setDouble(3,materiaprima.getPrecio());
				ps.setLong(4,materiaprima.getProveedor());
				ps.setDate(5,materiaprima.getFechaalta());
				ps.setDate(6,materiaprima.getFechabaja());
				ps.setLong(7,materiaprima.getCodbarra());
				ps.setLong(8,materiaprimapk.getIdmateriaprima());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new MateriaprimaException(sqle);}
		catch(Exception e){throw new MateriaprimaException(e);}
	}

/**
* This method inserts data in table MATERIAPRIMA
*
* @param Materiaprima materiaprima
* @param   Connection con
* @return  MateriaprimaPK
*/

	public int insert(Materiaprima materiaprima ,Connection con)throws MateriaprimaException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into MATERIAPRIMA( IDMATERIAPRIMA, CODPRODUCTO, NOMBRE, PRECIO, PROVEEDOR, FECHAALTA, FECHABAJA, CODBARRA) values (?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setLong(1,materiaprima.getIdmateriaprima());
				ps.setLong(2,materiaprima.getCodproducto());
				ps.setString(3,materiaprima.getNombre());
				ps.setDouble(4,materiaprima.getPrecio());
				ps.setLong(5,materiaprima.getProveedor());
				ps.setDate(6,materiaprima.getFechaalta());
				ps.setDate(7,materiaprima.getFechabaja());
				ps.setLong(8,materiaprima.getCodbarra());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new MateriaprimaException(sqle);}
		catch(Exception e){throw new MateriaprimaException(e);}
	}

/**
* 
* Returns a row from the materiaprima table for the primary key passed as parameter.
* 
*/

	public Materiaprima findByPrimaryKey(long idmateriaprima, Connection con) throws MateriaprimaException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idmateriaprima, codproducto, nombre, precio, proveedor, fechaalta, fechabaja, codbarra from materiaprima where idmateriaprima = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idmateriaprima);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new MateriaprimaException(sqle);
	  	}
	    catch(Exception e){throw new MateriaprimaException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the materiaprima table for the primary key object passed as parameter.
* 
* @param  MateriaprimaPK materiaprimapk
* @param Connection con
* @return  Materiaprima
*/

	public Materiaprima findByPrimaryKey(MateriaprimaPK materiaprimapk, Connection con) throws MateriaprimaException{
		return findByPrimaryKey(materiaprimapk.getIdmateriaprima(), con);
	}

/**
*
* Returns all rows from materiaprima table where IDMATERIAPRIMA= idmateriaprima
*
* @param   long  idmateriaprima
* @param   Connection con
* @return  Materiaprima[]
*/

	public Materiaprima[] findByIdmateriaprima(long idmateriaprima, Connection con) throws MateriaprimaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmateriaprima, codproducto, nombre, precio, proveedor, fechaalta, fechabaja, codbarra from materiaprima where idmateriaprima = ? order by idmateriaprima";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idmateriaprima );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MateriaprimaException(sqle);
			}
			catch(Exception e){
					throw new MateriaprimaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from materiaprima table where CODPRODUCTO= codproducto
*
* @param   long  codproducto
* @param   Connection con
* @return  Materiaprima[]
*/

	public Materiaprima[] findByCodproducto(long codproducto, Connection con) throws MateriaprimaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmateriaprima, codproducto, nombre, precio, proveedor, fechaalta, fechabaja, codbarra from materiaprima where codproducto = ? order by codproducto";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, codproducto );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MateriaprimaException(sqle);
			}
			catch(Exception e){
					throw new MateriaprimaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from materiaprima table where NOMBRE= nombre
*
* @param   String  nombre
* @param   Connection con
* @return  Materiaprima[]
*/

	public Materiaprima[] findByNombre(String nombre, Connection con) throws MateriaprimaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmateriaprima, codproducto, nombre, precio, proveedor, fechaalta, fechabaja, codbarra from materiaprima where nombre = ? order by nombre";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, nombre );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MateriaprimaException(sqle);
			}
			catch(Exception e){
					throw new MateriaprimaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from materiaprima table where PRECIO= precio
*
* @param   double  precio
* @param   Connection con
* @return  Materiaprima[]
*/

	public Materiaprima[] findByPrecio(double precio, Connection con) throws MateriaprimaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmateriaprima, codproducto, nombre, precio, proveedor, fechaalta, fechabaja, codbarra from materiaprima where precio = ? order by precio";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDouble( 1, precio );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MateriaprimaException(sqle);
			}
			catch(Exception e){
					throw new MateriaprimaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from materiaprima table where PROVEEDOR= proveedor
*
* @param   long  proveedor
* @param   Connection con
* @return  Materiaprima[]
*/

	public Materiaprima[] findByProveedor(long proveedor, Connection con) throws MateriaprimaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmateriaprima, codproducto, nombre, precio, proveedor, fechaalta, fechabaja, codbarra from materiaprima where proveedor = ? order by proveedor";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, proveedor );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MateriaprimaException(sqle);
			}
			catch(Exception e){
					throw new MateriaprimaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from materiaprima table where FECHAALTA= fechaalta
*
* @param   Date  fechaalta
* @param   Connection con
* @return  Materiaprima[]
*/

	public Materiaprima[] findByFechaalta(Date fechaalta, Connection con) throws MateriaprimaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmateriaprima, codproducto, nombre, precio, proveedor, fechaalta, fechabaja, codbarra from materiaprima where fechaalta = ? order by fechaalta";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechaalta );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MateriaprimaException(sqle);
			}
			catch(Exception e){
					throw new MateriaprimaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from materiaprima table where FECHABAJA= fechabaja
*
* @param   Date  fechabaja
* @param   Connection con
* @return  Materiaprima[]
*/

	public Materiaprima[] findByFechabaja(Date fechabaja, Connection con) throws MateriaprimaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmateriaprima, codproducto, nombre, precio, proveedor, fechaalta, fechabaja, codbarra from materiaprima where fechabaja = ? order by fechabaja";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechabaja );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MateriaprimaException(sqle);
			}
			catch(Exception e){
					throw new MateriaprimaException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from materiaprima table where CODBARRA= codbarra
*
* @param   long  codbarra
* @param   Connection con
* @return  Materiaprima[]
*/

	public Materiaprima[] findByCodbarra(long codbarra, Connection con) throws MateriaprimaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmateriaprima, codproducto, nombre, precio, proveedor, fechaalta, fechabaja, codbarra from materiaprima where codbarra = ? order by codbarra";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, codbarra );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MateriaprimaException(sqle);
			}
			catch(Exception e){
					throw new MateriaprimaException(e);
			}
			finally{}
	}

/**
* Returns all rows from materiaprima table 
*
* @param Connection con
* @return  Materiaprima[]
*
*/

	public Materiaprima[] findAll( Connection con) throws MateriaprimaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmateriaprima, codproducto, nombre, precio, proveedor, fechaalta, fechabaja, codbarra from materiaprima";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MateriaprimaException(sqle);
			}
			catch(Exception e){
					throw new MateriaprimaException(e);
			}
			finally{}
	}

/**
* Returns rows from materiaprima table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Materiaprima[]
*
*/

	public Materiaprima[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws MateriaprimaException{
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
					throw new MateriaprimaException(sqle);
			}
			catch(Exception e){
					throw new MateriaprimaException(e);
			}
			finally{}
	}

/**
* Returns rows from materiaprima table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Materiaprima[]
*
*/

	public Materiaprima[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws MateriaprimaException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idmateriaprima, codproducto, nombre, precio, proveedor, fechaalta, fechabaja, codbarra from materiaprima";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MateriaprimaException(sqle);
			}
			catch(Exception e){
					throw new MateriaprimaException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Materiaprima
*
*/

	protected Materiaprima fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Materiaprima dto = new Materiaprima();
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
* @param Materiaprima dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Materiaprima dto, ResultSet rs) throws SQLException
	{
		 dto.setIdmateriaprima(rs.getLong("idmateriaprima"));
		 dto.setCodproducto(rs.getLong("codproducto"));
		 dto.setNombre(rs.getString("nombre"));
		 dto.setPrecio(rs.getDouble("precio"));
		 dto.setProveedor(rs.getLong("proveedor"));
		 dto.setFechaalta(rs.getDate("fechaalta"));
		 dto.setFechabaja(rs.getDate("fechabaja"));
		 dto.setCodbarra(rs.getLong("codbarra"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Materiaprima[]
*/

	protected Materiaprima[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Materiaprima dto = new Materiaprima();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Materiaprima ret[] = new Materiaprima[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
