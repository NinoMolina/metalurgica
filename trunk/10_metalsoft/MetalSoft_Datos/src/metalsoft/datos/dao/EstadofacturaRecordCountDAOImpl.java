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
* Implementation of EstadofacturaRecordCountDAO interface 
* 
*/

public class EstadofacturaRecordCountDAOImpl implements EstadofacturaRecordCountDAO
{
	public EstadofacturaRecordCountDAOImpl()
	{
	}
	public EstadofacturaRecordCount getRecordCount(Connection con) throws EstadofacturaRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM ESTADOFACTURA");
			rs = stmt.executeQuery();
			rs.next();
 			EstadofacturaRecordCount vo = new EstadofacturaRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new EstadofacturaRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new EstadofacturaRecordCountException(e);
		}
	}



	public EstadofacturaRecordCount getRecordCount(Connection con, String whereClause) throws EstadofacturaRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM ESTADOFACTURA  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			EstadofacturaRecordCount vo = new EstadofacturaRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new EstadofacturaRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new EstadofacturaRecordCountException(e);
		}
	}
}
