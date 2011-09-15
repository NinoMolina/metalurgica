/**
*					--DAO-Generator--
*
* Java code generated by the DAO-Generator developed by akcess(www.akcess.in)
* Date of code generation: Sun Jun 27 03:16:05 ART 2010
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
* Implementation of ResponsableRecordCountDAO interface 
* 
*/

public class ResponsableRecordCountDAOImpl implements ResponsableRecordCountDAO
{
	public ResponsableRecordCountDAOImpl()
	{
	}
	public ResponsableRecordCount getRecordCount(Connection con) throws ResponsableRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM RESPONSABLE");
			rs = stmt.executeQuery();
			rs.next();
 			ResponsableRecordCount vo = new ResponsableRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new ResponsableRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new ResponsableRecordCountException(e);
		}
	}



	public ResponsableRecordCount getRecordCount(Connection con, String whereClause) throws ResponsableRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM RESPONSABLE  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			ResponsableRecordCount vo = new ResponsableRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new ResponsableRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new ResponsableRecordCountException(e);
		}
	}
}
