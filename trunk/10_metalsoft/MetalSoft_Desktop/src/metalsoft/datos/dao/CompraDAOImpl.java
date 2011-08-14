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
import java.util.Collection;
import java.util.ArrayList;
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
import metalsoft.datos.idao.*;


/**
* 
* Implementation of CompraDAO interface 
* 
*/


public class CompraDAOImpl implements CompraDAO
{


/**
* Method deletes a record from table COMPRA
* @param CompraPK comprapk
* @param  Connection  con
* @return  int
*
*/


	public int delete(CompraPKDB comprapk , Connection con)throws CompraException{
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  COMPRA where idcompra = ?");
			ps.setLong(1, comprapk.getIdcompra());
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new CompraException(sqle);}
		catch(Exception e) {throw new CompraException(e);}
	}



/**
* This method updates a record in table COMPRA
* @param CompraPK
* @param Compra
* @param  Connection con
* @return   int
*/

	public int update(CompraPKDB comprapk, CompraDB compra, Connection con)throws CompraException{
		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("update COMPRA set NROCOMPRA = ? , FECHACOMPRA = ? , VIGENCIA = ? , DOCUMENTOREMITO = ? , PRECIOTOTAL = ? , ESTADO = ? , MOTIVO = ? , PROVEEDOR = ?  where idcompra = ?");
				ps.setLong(1,compra.getNrocompra());
				ps.setDate(2,compra.getFechacompra());
				ps.setDate(3,compra.getVigencia());
				ps.setLong(4,compra.getDocumentoremito());
				ps.setDouble(5,compra.getPreciototal());
				ps.setLong(6,compra.getEstado());
				ps.setString(7,compra.getMotivo());
				ps.setLong(8,compra.getProveedor());
				ps.setLong(9,comprapk.getIdcompra());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new CompraException(sqle);}
		catch(Exception e){throw new CompraException(e);}
	}

/**
* This method inserts data in table COMPRA
*
* @param Compra compra
* @param   Connection con
* @return  CompraPK
*/

	public int insert(CompraDB compra ,Connection con)throws CompraException {

		PreparedStatement ps = null;
		try
		{
			ps = con.prepareStatement("insert into COMPRA( NROCOMPRA, FECHACOMPRA, VIGENCIA, DOCUMENTOREMITO, PRECIOTOTAL, ESTADO, MOTIVO, PROVEEDOR) values (?, ?, ?, ?, ?, ?, ?, ?)");
				ps.setLong(1,compra.getNrocompra());
				ps.setDate(2,compra.getFechacompra());
				ps.setDate(3,compra.getVigencia());
				ps.setLong(4,compra.getDocumentoremito());
				ps.setDouble(5,compra.getPreciototal());
				ps.setLong(6,compra.getEstado());
				ps.setString(7,compra.getMotivo());
				ps.setLong(8,compra.getProveedor());

				return(ps.executeUpdate());
		}catch(SQLException sqle){throw new CompraException(sqle);}
		catch(Exception e){throw new CompraException(e);}
	}

/**
* 
* Returns a row from the compra table for the primary key passed as parameter.
* 
*/

	public CompraDB findByPrimaryKey(long idcompra, Connection con) throws CompraException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
	  		final String SQLSTATEMENT = "Select idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor from compra where idcompra = ?";
	  		stmt=con.prepareStatement(SQLSTATEMENT);
	  		stmt.setLong(1, idcompra);
	  		rs = stmt.executeQuery();
	  		return fetchSingleResult(rs);
	  	}
	  	catch (SQLException sqle) {
	  		throw new CompraException(sqle);
	  	}
	    catch(Exception e){throw new CompraException(e);}
	  	 finally {}
	}

/**
* 
* Returns a row from the compra table for the primary key object passed as parameter.
* 
* @param  CompraPK comprapk
* @param Connection con
* @return  Compra
*/

	public CompraDB findByPrimaryKey(CompraPKDB comprapk, Connection con) throws CompraException{
		return findByPrimaryKey(comprapk.getIdcompra(), con);
	}

/**
*
* Returns all rows from compra table where IDCOMPRA= idcompra
*
* @param   long  idcompra
* @param   Connection con
* @return  Compra[]
*/

	public CompraDB[] findByIdcompra(long idcompra, Connection con) throws CompraException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor from compra where idcompra = ? order by idcompra";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, idcompra );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new CompraException(sqle);
			}
			catch(Exception e){
					throw new CompraException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from compra table where NROCOMPRA= nrocompra
*
* @param   long  nrocompra
* @param   Connection con
* @return  Compra[]
*/

	public CompraDB[] findByNrocompra(long nrocompra, Connection con) throws CompraException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor from compra where nrocompra = ? order by nrocompra";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, nrocompra );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new CompraException(sqle);
			}
			catch(Exception e){
					throw new CompraException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from compra table where FECHACOMPRA= fechacompra
*
* @param   Date  fechacompra
* @param   Connection con
* @return  Compra[]
*/

	public CompraDB[] findByFechacompra(Date fechacompra, Connection con) throws CompraException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor from compra where fechacompra = ? order by fechacompra";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, fechacompra );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new CompraException(sqle);
			}
			catch(Exception e){
					throw new CompraException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from compra table where VIGENCIA= vigencia
*
* @param   Date  vigencia
* @param   Connection con
* @return  Compra[]
*/

	public CompraDB[] findByVigencia(Date vigencia, Connection con) throws CompraException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor from compra where vigencia = ? order by vigencia";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDate( 1, vigencia );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new CompraException(sqle);
			}
			catch(Exception e){
					throw new CompraException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from compra table where DOCUMENTOREMITO= documentoremito
*
* @param   long  documentoremito
* @param   Connection con
* @return  Compra[]
*/

	public CompraDB[] findByDocumentoremito(long documentoremito, Connection con) throws CompraException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor from compra where documentoremito = ? order by documentoremito";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, documentoremito );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new CompraException(sqle);
			}
			catch(Exception e){
					throw new CompraException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from compra table where PRECIOTOTAL= preciototal
*
* @param   double  preciototal
* @param   Connection con
* @return  Compra[]
*/

	public CompraDB[] findByPreciototal(double preciototal, Connection con) throws CompraException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor from compra where preciototal = ? order by preciototal";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setDouble( 1, preciototal );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new CompraException(sqle);
			}
			catch(Exception e){
					throw new CompraException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from compra table where ESTADO= estado
*
* @param   long  estado
* @param   Connection con
* @return  Compra[]
*/

	public CompraDB[] findByEstado(long estado, Connection con) throws CompraException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor from compra where estado = ? order by estado";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, estado );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new CompraException(sqle);
			}
			catch(Exception e){
					throw new CompraException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from compra table where MOTIVO= motivo
*
* @param   String  motivo
* @param   Connection con
* @return  Compra[]
*/

	public CompraDB[] findByMotivo(String motivo, Connection con) throws CompraException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor from compra where motivo = ? order by motivo";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setString( 1, motivo );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new CompraException(sqle);
			}
			catch(Exception e){
					throw new CompraException(e);
			}
			finally{}
	}

/**
*
* Returns all rows from compra table where PROVEEDOR= proveedor
*
* @param   long  proveedor
* @param   Connection con
* @return  Compra[]
*/

	public CompraDB[] findByProveedor(long proveedor, Connection con) throws CompraException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor from compra where proveedor = ? order by proveedor";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					stmt.setLong( 1, proveedor );
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new CompraException(sqle);
			}
			catch(Exception e){
					throw new CompraException(e);
			}
			finally{}
	}

/**
* Returns all rows from compra table 
*
* @param Connection con
* @return  Compra[]
*
*/

	public CompraDB[] findAll( Connection con) throws CompraException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_STATEMENT ="Select idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor from compra";
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new CompraException(sqle);
			}
			catch(Exception e){
					throw new CompraException(e);
			}
			finally{}
	}

/**
* Returns rows from compra table by executing the passed sql statement
* after setting the passed values in Object[]
*
* @param String selectStatement
* @param Object[] sqlParams
* @param Connection con
* @return  Compra[]
*
*/

	public CompraDB[] findExecutingUserSelect(String selectStatement, Object[] sqlParams, Connection con) throws CompraException{
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
					throw new CompraException(sqle);
			}
			catch(Exception e){
					throw new CompraException(e);
			}
			finally{}
	}

/**
* Returns rows from compra table by executing the select all fields statement
* after setting the passed where clause and values in Object[]
*
* @param String whereClause
* @param Object[] sqlParams
* @param Connection con
* @return  Compra[]
*
*/

	public CompraDB[] findExecutingUserWhere(String whereClause, Object[] sqlParams, Connection con) throws CompraException{
			PreparedStatement stmt = null;
			ResultSet rs = null;
			String SQL_SELECT ="Select idcompra, nrocompra, fechacompra, vigencia, documentoremito, preciototal, estado, motivo, proveedor from compra";
			final String SQL_STATEMENT =SQL_SELECT + " where " + whereClause;
			try {
					stmt = con.prepareStatement(SQL_STATEMENT);
					for (int i=0; i<sqlParams.length; i++ ) {
							stmt.setObject( i+1, sqlParams[i] );
					}
					rs = stmt.executeQuery();
					return fetchMultiResults(rs);
			}catch(SQLException sqle){
					throw new CompraException(sqle);
			}
			catch(Exception e){
					throw new CompraException(e);
			}
			finally{}
	}

/**
*
* Populates a Data Transfer Object by fetching single record from resultSet 
*
* @param ResultSet rs
* @return  Compra
*
*/

	protected CompraDB fetchSingleResult(ResultSet rs) throws SQLException
	{
			if (rs.next()) {
					CompraDB dto = new CompraDB();
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
* @param Compra dto
* @param   ResultSet rs
* @return  void
*/

	protected void populateVO(CompraDB dto, ResultSet rs) throws SQLException
	{
		 dto.setIdcompra(rs.getLong("idcompra"));
		 dto.setNrocompra(rs.getInt("nrocompra"));
		 dto.setFechacompra(rs.getDate("fechacompra"));
		 dto.setVigencia(rs.getDate("vigencia"));
		 dto.setDocumentoremito(rs.getLong("documentoremito"));
		 dto.setPreciototal(rs.getDouble("preciototal"));
		 dto.setEstado(rs.getLong("estado"));
		 dto.setMotivo(rs.getString("motivo"));
		 dto.setProveedor(rs.getLong("proveedor"));
	}

/**
* 
* Returns an array of Value Objects by fetching data from resultSet
* 
* @param   ResultSet rs
* @return  Compra[]
*/

	protected CompraDB[]  fetchMultiResults(ResultSet rs) throws SQLException
	{
		Collection resultList = new ArrayList();
		while (rs.next()) {
			CompraDB dto = new CompraDB();
			populateVO( dto, rs);
			resultList.add(dto);
		}
		CompraDB ret[] = new CompraDB[ resultList.size() ];
		resultList.toArray( ret );
		return ret;
	}

        public String getUltimoNumeroCompra(Connection con) throws Exception
        {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String SQL_STATEMENT ="SELECT (MAX(nrocompra) + 1) AS maximo FROM compra";
            try {
                    stmt = con.prepareStatement(SQL_STATEMENT);
                    rs = stmt.executeQuery();
                    rs.next();
                    Object id = rs.getInt("maximo");
                    return id.toString();
            }
            catch(Exception e){
                throw new Exception();
            }
        }

        public String getUltimoIDCompra(Connection con) throws Exception
        {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            String SQL_STATEMENT ="Select max (idcompra) from compra";
            try {
                    stmt = con.prepareStatement(SQL_STATEMENT);
                    rs = stmt.executeQuery();
                    rs.next();
                    Object id = rs.getInt("max");
                    return id.toString();
            }
            catch(Exception e){
                throw new Exception();
            }
        }

    public int cancelarCompra(Connection con, long idOrden, String text)throws CompraException {
        PreparedStatement ps = null;
        try
        {
                ps = con.prepareStatement("UPDATE COMPRA set ESTADO = ? , MOTIVO = ? where idcompra = ?");
                        ps.setLong(1, 3);
                        ps.setString(2, text);
                        ps.setLong(3, idOrden);
                        return(ps.executeUpdate());
        }
        catch(SQLException sqle){throw new CompraException(sqle);}
        catch(Exception e){throw new CompraException(e);}
    }

    public int eliminarCompra(Connection con, long idOrden) throws CompraException {
       PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from  COMPRA where idcompra = ?");
			ps.setLong(1, idOrden);
			return(ps.executeUpdate());
		}catch(SQLException sqle) {throw new CompraException(sqle);}
		catch(Exception e) {throw new CompraException(e);}
    }
}