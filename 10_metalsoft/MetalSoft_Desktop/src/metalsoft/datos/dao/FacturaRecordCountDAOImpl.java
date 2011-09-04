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
import metalsoft.datos.exception.*;
import metalsoft.datos.dbobject.*;
import metalsoft.datos.idao.*;

/**
* 
* Implementation of FacturaRecordCountDAO interface 
* 
*/

public class FacturaRecordCountDAOImpl implements FacturaRecordCountDAO
{
	public FacturaRecordCountDAOImpl()
	{
	}
	public FacturaRecordCount getRecordCount(Connection con) throws FacturaRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM FACTURA");
			rs = stmt.executeQuery();
			rs.next();
 			FacturaRecordCount vo = new FacturaRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new FacturaRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new FacturaRecordCountException(e);
		}
	}



	public FacturaRecordCount getRecordCount(Connection con, String whereClause) throws FacturaRecordCountException
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("SELECT COUNT(*) FROM FACTURA  " + whereClause);
			rs = stmt.executeQuery();
			rs.next();
 			FacturaRecordCount vo = new FacturaRecordCount();
			vo.setCount(rs.getInt(1));
			return vo;
		}catch (SQLException sqle) {
			throw new FacturaRecordCountException(sqle);
		}
		catch (Exception e) {
			throw new FacturaRecordCountException(e);
		}
	}
}
