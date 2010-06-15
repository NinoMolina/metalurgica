/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Mon Jun 14 23:38:51 GYT 2010
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
* Implementation of DomicilioRecordCountDAO interface 
* 
*/

public class DomicilioRecordCountDAOImpl implements DomicilioRecordCountDAO
{
	public DomicilioRecordCountDAOImpl()
	{
	}
	public DomicilioRecordCount getRecordCount(Connection con) throws DomicilioRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM DOMICILIO");
			rs = stmt.executeQuery();
			rs.next();
 			DomicilioRecordCount vo = new DomicilioRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new DomicilioRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new DomicilioRecordCountException(e);
		}
	}



	public DomicilioRecordCount getRecordCount(Connection con, String whereClause) throws DomicilioRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM DOMICILIO  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			DomicilioRecordCount vo = new DomicilioRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new DomicilioRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new DomicilioRecordCountException(e);
		}
	}
}
