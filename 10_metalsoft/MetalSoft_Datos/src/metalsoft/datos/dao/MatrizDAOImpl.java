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
* Implementation of MatrizDAO interface 
* 
*/


public class MatrizDAOImpl implements MatrizDAO
{


/**
* Method deletes a record from table MATRIZ
* @param MatrizPK matrizpk
* @param  Connection  con
* @return  int
*
*/


	public int delete(MatrizPK matrizpk , Connection con)throws MatrizException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  MATRIZ where idmatriz = ?");
			ps.setLong(1, matrizpk.getIdmatriz());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new MatrizException(sqle);}
		catch(Exception e) {throw new MatrizException(e);}
	}



/**
* This method updates a record in table MATRIZ
* @param MatrizPK
* @param Matriz
* @param  Connection con
* @return   int
*/

	public int update(MatrizPK matrizpk, Matriz matriz, Connection con)throws MatrizException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update MATRIZ set CODMATRIZ = ? , NOMBRE = ? , DESCRIPCION = ? , OBSERVACIONES = ? , FECHACREACION = ? , MATERIAPRIMA = ? , TIPOMATERIAL = ?  where idmatriz = ?");
				ps.setLong(1,matriz.getCodmatriz());
				ps.setString(2,matriz.getNombre());
				ps.setString(3,matriz.getDescripcion());
				ps.setString(4,matriz.getObservaciones());
				ps.setDate(5,matriz.getFechacreacion());
				ps.setLong(6,matriz.getMateriaprima());
				ps.setLong(7,matriz.getTipomaterial());
				ps.setLong(8,matrizpk.getIdmatriz());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new MatrizException(sqle);}
		catch(Exception e){throw new MatrizException(e);}
	}

/**
* This method inserts data in table MATRIZ
*
* @param Matriz matriz
* @param   Connection con
* @return  MatrizPK
*/

	public int insert(Matriz matriz ,Connection con)throws MatrizException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into MATRIZ( CODMATRIZ, NOMBRE, DESCRIPCION, OBSERVACIONES, FECHACREACION, MATERIAPRIMA, TIPOMATERIAL) values (?, ?, ?, ?, ?, ?, ?)");
				ps.setLong(1,matriz.getCodmatriz());
				ps.setString(2,matriz.getNombre());
				ps.setString(3,matriz.getDescripcion());
				ps.setString(4,matriz.getObservaciones());
				ps.setDate(5,matriz.getFechacreacion());
				ps.setLong(6,matriz.getMateriaprima());
				ps.setLong(7,matriz.getTipomaterial());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new MatrizException(sqle);}
		catch(Exception e){throw new MatrizException(e);}
	}

/**
* 
* Returns a row from the matriz table for the primary key passed as parameter.
* 
*/

	public Matriz findByPrimaryKey(long idmatriz, Connection con) throws MatrizException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial from matriz where idmatriz = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idmatriz);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new MatrizException(sqle);
	  	}
	    catch(Exception e){throw new MatrizException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the matriz table for the primary key object passed as parameter.
* 
* @param  MatrizPK matrizpk
* @param Connection con
* @return  Matriz
*/

	public Matriz findByPrimaryKey(MatrizPK matrizpk, Connection con) throws MatrizException{
		return findByPrimaryKey(matrizpk.getIdmatriz(), con);
	}

/**
*
* Returns all rows from matriz table where IDMATRIZ= idmatriz
*
* @param   long  idmatriz
* @param   Connection con
* @return  Matriz[]
*/

	public Matriz[] findByIdmatriz(long idmatriz, Connection con) throws MatrizException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial from matriz where idmatriz = ? order by idmatriz";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idmatriz );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MatrizException(sqle);
			}
			catch(Exception e){
					throw new MatrizException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from matriz table where CODMATRIZ= codmatriz
*
* @param   long  codmatriz
* @param   Connection con
* @return  Matriz[]
*/

	public Matriz[] findByCodmatriz(long codmatriz, Connection con) throws MatrizException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial from matriz where codmatriz = ? order by codmatriz";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, codmatriz );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MatrizException(sqle);
			}
			catch(Exception e){
					throw new MatrizException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from matriz table where NOMBRE= nombre
*
* @param   String  nombre
* @param   Connection con
* @return  Matriz[]
*/

	public Matriz[] findByNombre(String nombre, Connection con) throws MatrizException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial from matriz where nombre = ? order by nombre";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, nombre );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MatrizException(sqle);
			}
			catch(Exception e){
					throw new MatrizException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from matriz table where DESCRIPCION= descripcion
*
* @param   String  descripcion
* @param   Connection con
* @return  Matriz[]
*/

	public Matriz[] findByDescripcion(String descripcion, Connection con) throws MatrizException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial from matriz where descripcion = ? order by descripcion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, descripcion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MatrizException(sqle);
			}
			catch(Exception e){
					throw new MatrizException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from matriz table where OBSERVACIONES= observaciones
*
* @param   String  observaciones
* @param   Connection con
* @return  Matriz[]
*/

	public Matriz[] findByObservaciones(String observaciones, Connection con) throws MatrizException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial from matriz where observaciones = ? order by observaciones";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, observaciones );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MatrizException(sqle);
			}
			catch(Exception e){
					throw new MatrizException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from matriz table where FECHACREACION= fechacreacion
*
* @param   Date  fechacreacion
* @param   Connection con
* @return  Matriz[]
*/

	public Matriz[] findByFechacreacion(Date fechacreacion, Connection con) throws MatrizException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial from matriz where fechacreacion = ? order by fechacreacion";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechacreacion );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MatrizException(sqle);
			}
			catch(Exception e){
					throw new MatrizException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from matriz table where MATERIAPRIMA= materiaprima
*
* @param   long  materiaprima
* @param   Connection con
* @return  Matriz[]
*/

	public Matriz[] findByMateriaprima(long materiaprima, Connection con) throws MatrizException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial from matriz where materiaprima = ? order by materiaprima";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, materiaprima );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MatrizException(sqle);
			}
			catch(Exception e){
					throw new MatrizException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from matriz table where TIPOMATERIAL= tipomaterial
*
* @param   long  tipomaterial
* @param   Connection con
* @return  Matriz[]
*/

	public Matriz[] findByTipomaterial(long tipomaterial, Connection con) throws MatrizException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial from matriz where tipomaterial = ? order by tipomaterial";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, tipomaterial );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MatrizException(sqle);
			}
			catch(Exception e){
					throw new MatrizException(e);
			}
			finally{}
	}

/**
* Returns all rows from matriz table 
*
* @param Connection con
* @return  Matriz[]
*
*/

	public Matriz[] findAll( Connection con) throws MatrizException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial from matriz";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MatrizException(sqle);
			}
			catch(Exception e){
					throw new MatrizException(e);
			}
			finally{}
	}

/**
* Returns rows from matriz table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Matriz[]
*
*/

	public Matriz[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws MatrizException{
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
					throw new MatrizException(sqle);
			}
			catch(Exception e){
					throw new MatrizException(e);
			}
			finally{}
	}

/**
* Returns rows from matriz table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Matriz[]
*
*/

	public Matriz[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws MatrizException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idmatriz, codmatriz, nombre, descripcion, observaciones, fechacreacion, materiaprima, tipomaterial from matriz";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new MatrizException(sqle);
			}
			catch(Exception e){
					throw new MatrizException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Matriz
*
*/

	protected Matriz fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Matriz dto = new Matriz();
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
* @param Matriz dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Matriz dto, ResultSet rs) throws SQLException
	{
		 dto.setIdmatriz(rs.getLong("idmatriz"));
		 dto.setCodmatriz(rs.getLong("codmatriz"));
		 dto.setNombre(rs.getString("nombre"));
		 dto.setDescripcion(rs.getString("descripcion"));
		 dto.setObservaciones(rs.getString("observaciones"));
		 dto.setFechacreacion(rs.getDate("fechacreacion"));
		 dto.setMateriaprima(rs.getLong("materiaprima"));
		 dto.setTipomaterial(rs.getLong("tipomaterial"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Matriz[]
*/

	protected Matriz[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Matriz dto = new Matriz();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Matriz ret[] = new Matriz[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
