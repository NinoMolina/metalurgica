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
* Implementation of ReclamoproveedorRecordCountDAO interface 
* 
*/

public class ReclamoproveedorRecordCountDAOImpl implements ReclamoproveedorRecordCountDAO
{
	public ReclamoproveedorRecordCountDAOImpl()
	{
	}
	public ReclamoproveedorRecordCount getRecordCount(Connection con) throws ReclamoproveedorRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM RECLAMOPROVEEDOR");
			rs = stmt.executeQuery();
			rs.next();
 			ReclamoproveedorRecordCount vo = new ReclamoproveedorRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new ReclamoproveedorRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new ReclamoproveedorRecordCountException(e);
		}
	}



	public ReclamoproveedorRecordCount getRecordCount(Connection con, String whereClause) throws ReclamoproveedorRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM RECLAMOPROVEEDOR  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			ReclamoproveedorRecordCount vo = new ReclamoproveedorRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new ReclamoproveedorRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new ReclamoproveedorRecordCountException(e);
		}
	}
}
