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
import java.util.*;
import javax.lang.model.type.NullType;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
import metalsoft.datos.idao.*;


/**
* 
* Implementation of PiezarealDAO interface 
* 
*/


public class PiezarealDAOImpl implements PiezarealDAO
{


/**
* Method deletes a record from table PIEZAREAL
* @param PiezarealPK piezarealpk
* @param  Connection  con
* @return  int
*
*/


	public int delete(PiezarealPK piezarealpk , Connection con)throws PiezarealException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  PIEZAREAL where idpiezareal = ? AND idpieza = ?");
			ps.setLong(1, piezarealpk.getIdpiezareal());
			ps.setLong(2, piezarealpk.getIdpieza());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new PiezarealException(sqle);}
		catch(Exception e) {throw new PiezarealException(e);}
	}



/**
* This method updates a record in table PIEZAREAL
* @param PiezarealPK
* @param PiezarealDB
* @param  Connection con
* @return   int
*/

	public int update(PiezarealPK piezarealpk, PiezarealDB piezareal, Connection con)throws PiezarealException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update PIEZAREAL set ESTADO = ? , NROPIEZA = ?, IDCODIGOBARRA = ?  where idpiezareal = ? AND idpieza = ?");
                if(piezareal.getEstado()>-1)ps.setLong(1,piezareal.getEstado());
                else ps.setNull(1, java.sql.Types.NULL);
                if(piezareal.getNropieza()>-1) ps.setInt(2,piezareal.getNropieza());
                else ps.setNull(2, java.sql.Types.NULL);
                if(piezareal.getIdcodbarra()>-1)ps.setLong(3,piezareal.getIdcodbarra());
                else ps.setNull(3,java.sql.Types.NULL);

				ps.setLong(4,piezarealpk.getIdpiezareal());
				ps.setLong(5,piezarealpk.getIdpieza());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new PiezarealException(sqle);}
		catch(Exception e){throw new PiezarealException(e);}
	}

/**
* This method inserts data in table PIEZAREAL
*
* @param PiezarealDB piezareal
* @param   Connection con
* @return  PiezarealPK
*/

	public int insert(PiezarealDB piezareal ,Connection con)throws PiezarealException {

		PreparedStatement ps = null;
        ResultSet rs=null;
		try
		{
			ps = con.prepareStatement("insert into PIEZAREAL( IDPIEZA, ESTADO, NROPIEZA, IDCODIGOBARRA) values (?, ?, ?, ?) RETURNING IDPIEZAREAL");
				ps.setLong(1,piezareal.getIdpieza());
                if(piezareal.getEstado()>-1)ps.setLong(2,piezareal.getEstado());
                else ps.setNull(2, java.sql.Types.NULL);
                if(piezareal.getNropieza()>-1) ps.setInt(3,piezareal.getNropieza());
                else ps.setNull(3, java.sql.Types.NULL);
                if(piezareal.getIdcodbarra()>-1)ps.setLong(4,piezareal.getIdcodbarra());
                else ps.setNull(4,java.sql.Types.NULL);
                

				rs=ps.executeQuery();
                rs.next();
				return (int) rs.getLong(1);
		}catch(SQLException sqle){throw new PiezarealException(sqle);}
		catch(Exception e){throw new PiezarealException(e);}
	}

/**
* 
* Returns a row from the piezareal table for the primary key passed as parameter.
* 
*/

	public PiezarealDB findByPrimaryKey(long idpiezareal, long idpieza, Connection con) throws PiezarealException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idpiezareal, idpieza, estado, nropieza from piezareal where idpiezareal = ? AND idpieza = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idpiezareal);
	  		stmt.setLong(2, idpieza);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new PiezarealException(sqle);
	  	}
	    catch(Exception e){throw new PiezarealException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the piezareal table for the primary key object passed as parameter.
* 
* @param  PiezarealPK piezarealpk
* @param Connection con
* @return  PiezarealDB
*/

	public PiezarealDB findByPrimaryKey(PiezarealPK piezarealpk, Connection con) throws PiezarealException{
		return findByPrimaryKey(piezarealpk.getIdpiezareal(), piezarealpk.getIdpieza(), con);
	}

/**
*
* Returns all rows from piezareal table where IDPIEZAREAL= idpiezareal
*
* @param   long  idpiezareal
* @param   Connection con
* @return  PiezarealDB[]
*/

	public PiezarealDB[] findByIdpiezareal(long idpiezareal, Connection con) throws PiezarealException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idpiezareal, idpieza, estado, nropieza from piezareal where idpiezareal = ? order by idpiezareal";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idpiezareal );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PiezarealException(sqle);
			}
			catch(Exception e){
					throw new PiezarealException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from piezareal table where IDPIEZA= idpieza
*
* @param   long  idpieza
* @param   Connection con
* @return  PiezarealDB[]
*/

	public PiezarealDB[] findByIdpieza(long idpieza, Connection con) throws PiezarealException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idpiezareal, idpieza, estado, nropieza from piezareal where idpieza = ? order by idpieza";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idpieza );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PiezarealException(sqle);
			}
			catch(Exception e){
					throw new PiezarealException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from piezareal table where ESTADO= estado
*
* @param   long  estado
* @param   Connection con
* @return  PiezarealDB[]
*/

	public PiezarealDB[] findByEstado(long estado, Connection con) throws PiezarealException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idpiezareal, idpieza, estado, nropieza from piezareal where estado = ? order by estado";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, estado );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PiezarealException(sqle);
			}
			catch(Exception e){
					throw new PiezarealException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from piezareal table where NROPIEZA= nropieza
*
* @param   int  nropieza
* @param   Connection con
* @return  PiezarealDB[]
*/

	public PiezarealDB[] findByNropieza(int nropieza, Connection con) throws PiezarealException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idpiezareal, idpieza, estado, nropieza from piezareal where nropieza = ? order by nropieza";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setInt( 1, nropieza );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PiezarealException(sqle);
			}
			catch(Exception e){
					throw new PiezarealException(e);
			}
			finally{}
	}

/**
* Returns all rows from piezareal table 
*
* @param Connection con
* @return  PiezarealDB[]
*
*/

	public PiezarealDB[] findAll( Connection con) throws PiezarealException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idpiezareal, idpieza, estado, nropieza from piezareal";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PiezarealException(sqle);
			}
			catch(Exception e){
					throw new PiezarealException(e);
			}
			finally{}
	}

/**
* Returns rows from piezareal table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  PiezarealDB[]
*
*/

	public PiezarealDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws PiezarealException{
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
					throw new PiezarealException(sqle);
			}
			catch(Exception e){
					throw new PiezarealException(e);
			}
			finally{}
	}

/**
* Returns rows from piezareal table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  PiezarealDB[]
*
*/

	public PiezarealDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws PiezarealException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idpiezareal, idpieza, estado, nropieza from piezareal";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PiezarealException(sqle);
			}
			catch(Exception e){
					throw new PiezarealException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  PiezarealDB
*
*/

	protected PiezarealDB fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					PiezarealDB dto = new PiezarealDB();
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
* @param PiezarealDB dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(PiezarealDB dto, ResultSet rs) throws SQLException
	{
		 dto.setIdpiezareal(rs.getLong("idpiezareal"));
		 dto.setIdpieza(rs.getLong("idpieza"));
		 dto.setEstado(rs.getLong("estado"));
		 dto.setNropieza(rs.getInt("nropieza"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  PiezarealDB[]
*/

	protected PiezarealDB[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			PiezarealDB dto = new PiezarealDB();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		PiezarealDB ret[] = new PiezarealDB[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
