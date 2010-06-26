/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Tue Jun 22 00:40:17 ART 2010
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
* Implementation of PlanprocedimientosDAO interface 
* 
*/


public class PlanprocedimientosDAOImpl implements PlanprocedimientosDAO
{


/**
* Method deletes a record from table PLANPROCEDIMIENTOS
* @param PlanprocedimientosPK planprocedimientospk
* @param  Connection  con
* @return  int
*
*/


	public int delete(PlanprocedimientosPK planprocedimientospk , Connection con)throws PlanprocedimientosException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  PLANPROCEDIMIENTOS where idplanprocedimientos = ?");
			ps.setInt(1, planprocedimientospk.getIdplanprocedimientos());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new PlanprocedimientosException(sqle);}
		catch(Exception e) {throw new PlanprocedimientosException(e);}
	}



/**
* This method updates a record in table PLANPROCEDIMIENTOS
* @param PlanprocedimientosPK
* @param Planprocedimientos
* @param  Connection con
* @return   int
*/

	public int update(PlanprocedimientosPK planprocedimientospk, Planprocedimientos planprocedimientos, Connection con)throws PlanprocedimientosException{
	return -1;
        }

/**
* This method inserts data in table PLANPROCEDIMIENTOS
*
* @param Planprocedimientos planprocedimientos
* @param   Connection con
* @return  PlanprocedimientosPK
*/

	public int insert(Planprocedimientos planprocedimientos ,Connection con)throws PlanprocedimientosException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into PLANPROCEDIMIENTOS( IDPLANPROCEDIMIENTOS) values (?)");
				ps.setInt(1,planprocedimientos.getIdplanprocedimientos());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new PlanprocedimientosException(sqle);}
		catch(Exception e){throw new PlanprocedimientosException(e);}
	}

/**
* 
* Returns a row from the planprocedimientos table for the primary key passed as parameter.
* 
*/

	public Planprocedimientos findByPrimaryKey(int idplanprocedimientos, Connection con) throws PlanprocedimientosException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idplanprocedimientos from planprocedimientos where idplanprocedimientos = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setInt(1, idplanprocedimientos);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new PlanprocedimientosException(sqle);
	  	}
	    catch(Exception e){throw new PlanprocedimientosException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the planprocedimientos table for the primary key object passed as parameter.
* 
* @param  PlanprocedimientosPK planprocedimientospk
* @param Connection con
* @return  Planprocedimientos
*/

	public Planprocedimientos findByPrimaryKey(PlanprocedimientosPK planprocedimientospk, Connection con) throws PlanprocedimientosException{
		return findByPrimaryKey(planprocedimientospk.getIdplanprocedimientos(), con);
	}

/**
*
* Returns all rows from planprocedimientos table where IDPLANPROCEDIMIENTOS= idplanprocedimientos
*
* @param   int  idplanprocedimientos
* @param   Connection con
* @return  Planprocedimientos[]
*/

	public Planprocedimientos[] findByIdplanprocedimientos(int idplanprocedimientos, Connection con) throws PlanprocedimientosException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idplanprocedimientos from planprocedimientos where idplanprocedimientos = ? order by idplanprocedimientos";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setInt( 1, idplanprocedimientos );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PlanprocedimientosException(sqle);
			}
			catch(Exception e){
					throw new PlanprocedimientosException(e);
			}
			finally{}
	}

/**
* Returns all rows from planprocedimientos table 
*
* @param Connection con
* @return  Planprocedimientos[]
*
*/

	public Planprocedimientos[] findAll( Connection con) throws PlanprocedimientosException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idplanprocedimientos from planprocedimientos";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PlanprocedimientosException(sqle);
			}
			catch(Exception e){
					throw new PlanprocedimientosException(e);
			}
			finally{}
	}

/**
* Returns rows from planprocedimientos table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Planprocedimientos[]
*
*/

	public Planprocedimientos[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws PlanprocedimientosException{
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
					throw new PlanprocedimientosException(sqle);
			}
			catch(Exception e){
					throw new PlanprocedimientosException(e);
			}
			finally{}
	}

/**
* Returns rows from planprocedimientos table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Planprocedimientos[]
*
*/

	public Planprocedimientos[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws PlanprocedimientosException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idplanprocedimientos from planprocedimientos";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new PlanprocedimientosException(sqle);
			}
			catch(Exception e){
					throw new PlanprocedimientosException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Planprocedimientos
*
*/

	protected Planprocedimientos fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					Planprocedimientos dto = new Planprocedimientos();
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
* @param Planprocedimientos dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(Planprocedimientos dto, ResultSet rs) throws SQLException
	{
		 dto.setIdplanprocedimientos(rs.getInt("idplanprocedimientos"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Planprocedimientos[]
*/

	protected Planprocedimientos[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			Planprocedimientos dto = new Planprocedimientos();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		Planprocedimientos ret[] = new Planprocedimientos[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}
}
