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
* Implementation of EstadoejecetapaprodRecordCountDAO interface 
* 
*/

public class EstadoejecetapaprodRecordCountDAOImpl implements EstadoejecetapaprodRecordCountDAO
{
	public EstadoejecetapaprodRecordCountDAOImpl()
	{
	}
	public EstadoejecetapaprodRecordCount getRecordCount(Connection con) throws EstadoejecetapaprodRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM ESTADOEJECETAPAPROD");
			rs = stmt.executeQuery();
			rs.next();
 			EstadoejecetapaprodRecordCount vo = new EstadoejecetapaprodRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new EstadoejecetapaprodRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new EstadoejecetapaprodRecordCountException(e);
		}
	}



	public EstadoejecetapaprodRecordCount getRecordCount(Connection con, String whereClause) throws EstadoejecetapaprodRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM ESTADOEJECETAPAPROD  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			EstadoejecetapaprodRecordCount vo = new EstadoejecetapaprodRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new EstadoejecetapaprodRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new EstadoejecetapaprodRecordCountException(e);
		}
	}
}
