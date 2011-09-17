/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Oct 31 02:26:41 ART 2010
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
* Implementation of DetallefacturaRecordCountDAO interface 
* 
*/

public class DetallefacturaRecordCountDAOImpl implements DetallefacturaRecordCountDAO
{
	public DetallefacturaRecordCountDAOImpl()
	{
	}
	public DetallefacturaRecordCount getRecordCount(Connection con) throws DetallefacturaRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM DETALLEFACTURA");
			rs = stmt.executeQuery();
			rs.next();
 			DetallefacturaRecordCount vo = new DetallefacturaRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new DetallefacturaRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new DetallefacturaRecordCountException(e);
		}
	}



	public DetallefacturaRecordCount getRecordCount(Connection con, String whereClause) throws DetallefacturaRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM DETALLEFACTURA  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			DetallefacturaRecordCount vo = new DetallefacturaRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new DetallefacturaRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new DetallefacturaRecordCountException(e);
		}
	}
}
